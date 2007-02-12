/**
 * 
 */
package gov.nih.nci.cabig.ctms.web.sso;

import gov.nih.nci.cagrid.gridca.common.CRLEntry;
import gov.nih.nci.cagrid.gridca.common.CertUtil;
import gov.nih.nci.cagrid.gridca.common.KeyUtil;
import gov.nih.nci.cagrid.gridca.common.ProxyCreator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.security.KeyPair;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.bouncycastle.asn1.x509.CRLReason;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.globus.gsi.GlobusCredential;

import junit.framework.TestCase;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 *
 */
public class GridProxyValidatorTest extends TestCase {

    /**
     * 
     */
    public GridProxyValidatorTest() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param arg0
     */
    public GridProxyValidatorTest(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    
    public void testValidate(){
        try{
            
            // Create directory structure
            File trustedCertsDir = new File("test/temp/certs");
            trustedCertsDir.mkdirs();
            File crlsDir = new File("test/temp/certs");
            crlsDir.mkdirs();

            // Create a CA cert
            File certFile = new File(trustedCertsDir.getAbsolutePath() + "/testCA.0");
            String caDN = "O=test,OU=test,CN=testCA";
            KeyPair caPair = KeyUtil.generateRSAKeyPair1024();
            GregorianCalendar date = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            date.add(Calendar.MINUTE, -5);
            Date start = new Date(date.getTimeInMillis());
            date.add(Calendar.MINUTE, 5);
            date.add(Calendar.DAY_OF_MONTH, 5);
            Date end = new Date(date.getTimeInMillis());
            X509Certificate caCert = CertUtil.generateCACertificate(new X509Name(caDN), start, end,
                            caPair);

            // Write to trusted certs dir
            CertUtil.writeCertificate(caCert, certFile);

            // Create an end cert
            String userSubj = "CN=testUser";
            KeyPair userPair = KeyUtil.generateRSAKeyPair1024();
            PKCS10CertificationRequest request = CertUtil.generateCertficateRequest(userSubj,
                            userPair);
            date = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            start = new Date(date.getTimeInMillis());
            date.add(Calendar.DAY_OF_MONTH, 4);
            end = new Date(date.getTimeInMillis());
            X509Certificate userCert = CertUtil.signCertificateRequest(request, start, end, caCert,
                            caPair.getPrivate());

            // Create proxy
            KeyPair proxyPair = KeyUtil.generateRSAKeyPair512();
            X509Certificate[] proxyChain = ProxyCreator.createImpersonationProxyCertificate(
                            userCert, userPair.getPrivate(), proxyPair.getPublic(), 12, 0, 0);
            GlobusCredential proxy = new GlobusCredential(proxyPair.getPrivate(), proxyChain);
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            proxy.save(buf);
            String proxyStr = buf.toString();

            GridProxyValidatorImpl validator = new GridProxyValidatorImpl();
            validator.setTrustedCertsLocations(trustedCertsDir.getAbsolutePath());
            validator.setCrlLocations(crlsDir.getAbsolutePath());

            assertTrue("Proxy should be valid", validator.validate(proxyStr));

            // Create CRL
            File crlFile = new File(crlsDir.getAbsolutePath() + "/testCRL.r1");
            CRLEntry[] crlEntries = new CRLEntry[] { new CRLEntry(userCert.getSerialNumber(),
                            CRLReason.PRIVILEGE_WITHDRAWN) };
            X509CRL crl = CertUtil.createCRL(caCert, caPair.getPrivate(), crlEntries, caCert
                            .getNotAfter());
            CertUtil.writeCRL(crl, crlFile);

            assertTrue("Proxy should NOT be valid", !validator.validate(proxyStr));

            certFile.delete();
            crlFile.delete();

            
        }catch(Exception ex){
            ex.printStackTrace();
            fail("Error encountered: " + ex.getMessage());
        }
    }

}
