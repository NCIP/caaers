/**
 * 
 */
package gov.nih.nci.cabig.ctms.web.sso;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.cert.X509Certificate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.globus.gsi.CertificateRevocationLists;
import org.globus.gsi.GlobusCredential;
import org.globus.gsi.TrustedCertificates;
import org.globus.gsi.proxy.ProxyPathValidator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class GridProxyValidatorImpl implements GridProxyValidator {

    private static final Log logger = LogFactory.getLog(GridProxyValidatorImpl.class);

    private String trustedCertsLocations;

    private String crlLocations;

    public String getCrlLocations() {
        return crlLocations;
    }

    public void setCrlLocations(String crlLocations) {
        this.crlLocations = crlLocations;
    }

    public String getTrustedCertsLocations() {
        return trustedCertsLocations;
    }

    public void setTrustedCertsLocations(String trustedCertsLocations) {
        this.trustedCertsLocations = trustedCertsLocations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see sso.GridProxyValidator#validate(java.lang.String)
     */
    public boolean validate(String proxy) throws GridProxyValidationException {
        boolean valid = false;

        GlobusCredential cred = null;
        try {
            cred = new GlobusCredential(new ByteArrayInputStream(proxy.getBytes()));
        } catch (Exception ex) {
            throw new GridProxyValidationException("Error instantiating GlobusCredential: "
                            + ex.getMessage(), ex);
        }

        try {

            X509Certificate[] proxyChain = cred.getCertificateChain();
            X509Certificate[] trustedCerts = null;
            CertificateRevocationLists crls = null;

            String tcLoc = getTrustedCertsLocations();
            if (tcLoc != null) {
                trustedCerts = TrustedCertificates.loadCertificates(tcLoc);
            } else {
                trustedCerts = TrustedCertificates.getDefaultTrustedCertificates()
                                .getCertificates();
            }

            String crlLoc = getCrlLocations();
            if (crlLoc != null) {
                crls = CertificateRevocationLists.getCertificateRevocationLists(crlLoc);
            } else {
                crls = CertificateRevocationLists.getDefaultCertificateRevocationLists();
            }

            ProxyPathValidator ppv = new ProxyPathValidator();
            ppv.validate(proxyChain, trustedCerts, crls);
            
            valid = true;

        } catch (Exception ex) {
            logger.debug("Proxy validation failed: " + ex.getMessage());
        }

        return valid;
    }

}
