/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.caxchange.common.security.principals.GridIdentifierPrincipal;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import javax.jbi.messaging.MessageExchange;
import javax.jbi.messaging.NormalizedMessage;
import javax.security.auth.Subject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.servicemix.components.util.TransformComponentSupport;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.globus.gsi.GlobusCredential;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class GridProxy2JAASSubject extends TransformComponentSupport {

    private String proxyXPath;

    private String contentXPath;

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.servicemix.components.util.TransformComponentSupport#transform(javax.jbi.messaging.MessageExchange,
     *      javax.jbi.messaging.NormalizedMessage, javax.jbi.messaging.NormalizedMessage)
     */
    @Override
    protected boolean transform(MessageExchange exchange, NormalizedMessage inMsg,
                    NormalizedMessage outMsg) throws Exception {

        boolean success = false;

        try {

            String inMsgXml = new SourceTransformer().contentToString(inMsg);
            XPath xpathEngine = XPathFactory.newInstance().newXPath();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(inMsgXml.getBytes()));
            Node proxyNode = (Node)xpathEngine.evaluate(getProxyXPath(), doc, XPathConstants.NODE);
            if(proxyNode == null){
                throw new RuntimeException("Couldn't locate proxy node with '" + getProxyXPath() + "'.");
            }
            Node contentNode = (Node)xpathEngine.evaluate(getContentXPath(), doc, XPathConstants.NODE);
            if(contentNode == null){
                throw new RuntimeException("Couldn't locate content node with '" + getContentXPath() + "'.");
            }
            
            String proxyStr = toString(proxyNode);
            String contentStr = toString(contentNode);
            
            System.out.println("Content:\n" + contentStr);
//            System.out.println("Proxy:\n" + proxyStr);
            
            GlobusCredential cred = new GlobusCredential(new ByteArrayInputStream(proxyStr.getBytes()));
            PrivateKey privateKey = cred.getPrivateKey();
            
            String identity = cred.getIdentity();
            Principal principal = new GridIdentifierPrincipal(identity);
            
            X509Certificate[] chain = cred.getCertificateChain();
            Subject subject = inMsg.getSecuritySubject();
            if(subject == null){
                subject = new Subject();
            }

            subject.getPrivateCredentials().add(privateKey);
            subject.getPublicCredentials().add(chain);
            subject.getPrincipals().add(principal);
            outMsg.setSecuritySubject(subject);
            outMsg.setContent(new StreamSource(new ByteArrayInputStream(contentStr
                .getBytes())));

            success = true;
        } catch (Exception ex) {
           ex.printStackTrace();
        }

        return success;
    }
    
    private String toString(Node node) throws Exception {
        StringWriter w = new StringWriter();
        TransformerFactory transformerFactory = TransformerFactory
                .newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("omit-xml-declaration", "yes");
        transformer.transform(new DOMSource(node), new StreamResult(w));
        return w.getBuffer().toString();
    }

    public String getContentXPath() {
        return contentXPath;
    }

    public void setContentXPath(String contentXPath) {
        this.contentXPath = contentXPath;
    }

    public String getProxyXPath() {
        return proxyXPath;
    }

    public void setProxyXPath(String proxyXPath) {
        this.proxyXPath = proxyXPath;
    }

}
