/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.caxchange.common.security.principals.GridIdentifierPrincipal;

import java.io.ByteArrayInputStream;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.Set;

import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.security.auth.Subject;
import javax.xml.transform.stream.StreamSource;

import org.apache.servicemix.components.util.TransformComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class JAASSubject2GridProxy extends TransformComponentSupport {

    /* (non-Javadoc)
     * @see org.apache.servicemix.components.util.TransformComponentSupport#transform(javax.jbi.messaging.MessageExchange, javax.jbi.messaging.NormalizedMessage, javax.jbi.messaging.NormalizedMessage)
     */
    @Override
    protected boolean transform(MessageExchange exchange, NormalizedMessage inMsg, NormalizedMessage outMsg)
                    throws Exception {
        boolean success = false;
        
        try{
            Subject subject = inMsg.getSecuritySubject();
            
            //Get principal
            Principal principal = null;
            Set<GridIdentifierPrincipal> principals = subject.getPrincipals(GridIdentifierPrincipal.class);
            if(principals == null || principals.size() == 0){
                throw new RuntimeException("No GridIdentifierPrincipal found!");
            }else if(principals.size() > 1){
                throw new RuntimeException(principals.size() + " GridIdentifierPrincipals found!");
            }
            principal = principals.iterator().next();
            System.out.println("## Principal is: " + principal.getName());

            //Get cert chain
            X509Certificate[] chain = null;
            Set<X509Certificate[]> chains = subject.getPublicCredentials(X509Certificate[].class);
            if(chains == null || chains.size() == 0){
                throw new RuntimeException("No X509Certificate[] found!");
            }else if(chains.size() > 1){
                throw new RuntimeException(chains.size() + " X509Certificate[]s found!");
            }
            chain = chains.iterator().next();
            
            //Get private key
            PrivateKey privateKey = null;
            Set<PrivateKey> privateKeys = subject.getPrivateCredentials(PrivateKey.class);
            if(privateKeys == null || privateKeys.size() == 0){
                throw new RuntimeException("No PrivateKey found!");
            }else if(privateKeys.size() > 1){
                throw new RuntimeException(chains.size() + " PrivateKeys found!");
            }
            privateKey = privateKeys.iterator().next();
            
            GlobusCredential cred = new GlobusCredential(privateKey, chain);
            
            System.out.println("## Identity is: " + cred.getIdentity());
            
            String inMsgXml = new SourceTransformer().contentToString(inMsg);
            outMsg.setContent(new StreamSource(new ByteArrayInputStream(inMsgXml
                            .getBytes())));
            
            success = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return success;
    }

}
