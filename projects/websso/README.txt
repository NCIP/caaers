First, some background. In caGrid, authentication is achieved through the use of X.509 certificates. Therefore, each user needs a certificate in order to access a resource on the grid. The caGrid components that enable each user to obtain a certificate are the Dorian service and the AuthenticationService service. AuthenticationService plays the role of an Identity Provider (IdP). Dorian plays the role of an Identity Federation Service (IFS).

A user obtains a certificate by following these steps:
1.) User provides IdP username and password
2.) IdP returns a singed SAML assertion attesting to the user's identity
3.) User provides SAML assertion to IFS
4.) IFS returns a certificate

The certificate returned by the IFS is a proxy certificate, which we commonly refer to as a grid proxy. It is basically just a short-lived certificate (usually valid for only 12 hours).

I've implemented SSO across web applications using these grid proxies. There are four components involved in this implementation:
 - gov.nih.nci.cabig.ctms.web.sso.GridProxyFilter
 - gov.nih.nci.cabig.ctms.web.sso.GrixProxyRetrievalStrategy
 - gov.nih.nci.cabig.ctms.web.sso.GridBasicAuthenticationClient
 - gov.nih.nci.cabig.ctms.web.sso.GridProxyValidator

#  gov.nih.nci.cabig.ctms.web.sso.GridProxyFilter #

This is a servlet filter which will use an instance of GridProxyRetrievalStrategy to check for the presence of a grid proxy in the request or session scopes. If not found, it will redirect to a configured page. If found, it will continue chain processing. This filter can operate in "strict" or "non-strict" modes. In non-strict mode, the filter will continue chain processing even if a proxy is not found. This permits use of the application by users that do not have grid identities. 

This filter uses Spring to instantiate the desired implementation of GridProxyRetrievalStrategy.

# gov.nih.nci.cabig.ctms.web.sso.DefaultGridProxyRetrievalStrategy #

This implementation of of GridProxyRetrievalStrategy looks a proxy first in the request and then the session under a configured parameter/attribute name. If a proxy is found, it delegates to an instance of GridProxyValidator to validate the proxy. 

This implementation can also me configured to check for username/password credentials under configurable parameter/attribute names. If these credentials are found, this class will delegate to an instance of GridBasicAuthenticationClient to obtain a grid proxy.

If a grid proxy is located and valid, or newly obtained, it is placed under a configurable attribute name in the session.

DefaultGridProxyRetrievalStrategy is the class that should be extended to work with a local authentication mechanism. In caAERS, it is gov.nih.nci.cabig.caaers.tools.accesscontrol.CaaersGridProxyRetrievalStrategy that interacts with this systems ApplicatinSecurityManager to set the current user to the grid identity obtained from the proxy.

# gov.nih.nci.cabig.ctms.web.sso.GridBasicAuthenticationClient #

This is simple API that handles interaction with the IdP and IFS. The single method looks like this:

    /**
     * 
     * @param username
     * @param password
     * @return String representation of grid proxy
     * @throws AuthenticationErrorException if an error is encountered during authentication
     */
    String authenticate(String username, String password) throws AuthenticationErrorException;

# gov.nih.nci.cabig.ctms.web.sso.GridProxyValidator #

This class verifies the the proxy is still valid and checks that it has been signed by one of the "trusted" certificates.  The convention used by Globus is to store trusted certificates under $HOME/.globus/certificates. So, this class checks the proxy against certificates in that directory.

Three steps are required to integrate this approach into a web application. First, GridProxyFilter must be configured in web.xml. Second, the existing authentication mechanism must ensure that grid proxy ends up in the session under a pre-defined attribute name. Third, "hot links" between applications must include the grid proxy as a pre-defined parameter in the request.

The implications of using this approach are that, first, unless we set up our own IFS and IdP, each user will have to be a real NIH user. Second, each system will have to be synchronized with the trust fabric. That means, the root certificates used to sign the IFS and IdP containers and proxies will have to exist locally under $HOME/.cagrid/certificates (There is a caGrid component, called SyncGTS, that automates this process). Third, authorization will have to be based on membership in grid groups (There is a caGrid component which integrates CSM groups with grid groups). Finally, all involved systems need to agree on the name of the parameter (both session and request) in which the proxy will be stored.

I made the following changes to incorporate SSO into the caAERS web app
 - Added src/main/java/gov/nih/nci/cabig/caaers/applicationContext-web-sso.xml
    - this configures GridBasicAuthenticationClientImpl and GridProxyValidatorImpl
 - Added GridProxyFilter to web.xml
 - Extended DefaultGridProxyRetrievalStrategy to work with ApplicationSecurityManager.
 - Modified LoginController to first check if a user (grid identity) has already been set in the session.

To test out this functionality, do this:
1.) Configure your container to accept HTTPS connections (described below)
2.) Synchronize with the trust fabric (described below)
3.) In projects/websso, run: ant dist
4.) In projects/web, run: ant war
5.) Deploy the war and make sure the container is started
6.) In projects/websso, run: ant print-proxy
     - this will prompt you for your NIH username and password
     - your proxy will be saved to "proxy.txt" in the working directory
7.) In your browser, open this file: projects/websso/test/sso-link.html
8.) Paste the contents of proxy.txt into the textarea on that page.
9.) Click the link

If all works well, you should reach the caAERS welcome page without having to log in.

# Configuring Tomcat for HTTPS #

This is required to ensure that your proxy is passed encrypted from your browser to the webapp. You need to generate a certificate for tomcat. Follow the "Quick Start" described here: http://tomcat.apache.org/tomcat-5.5-doc/ssl-howto.html

# Synchronizing with the trust fabric #

To synchronize with the NCICB trust fabric, you can either copy the files from projects/websso/src/test/certificates to $HOME/.globus/certificates (where $HOME is the home directory of the account that is running Tomcat), or you can download caGrid and use the SyncGTS tool. Let me know if you want to do the latter and I'll walk you through it.

