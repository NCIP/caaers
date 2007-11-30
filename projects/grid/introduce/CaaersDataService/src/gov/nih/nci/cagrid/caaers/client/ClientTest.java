
package gov.nih.nci.cagrid.caaers.client;

import gov.nih.nci.cagrid.common.security.ProxyUtil;
import gov.nih.nci.cagrid.cqlquery.CQLQuery;
import gov.nih.nci.cagrid.cqlresultset.CQLQueryResults;
import gov.nih.nci.cagrid.dorian.bean.SAMLAssertion;
import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.idp.bean.BasicAuthCredential;
import gov.nih.nci.cagrid.dorian.ifs.bean.DelegationPathLength;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.dorian.ifs.bean.PublicKey;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidAssertionFault;
import gov.nih.nci.cagrid.dorian.stubs.types.InvalidProxyFault;
import gov.nih.nci.cagrid.dorian.stubs.types.PermissionDeniedFault;
import gov.nih.nci.cagrid.dorian.stubs.types.UserPolicyFault;
import gov.nih.nci.cagrid.gridca.common.CertUtil;
import gov.nih.nci.cagrid.gridca.common.KeyUtil;

import java.io.FileInputStream;
import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.globus.gsi.GlobusCredential;
import org.globus.wsrf.encoding.ObjectDeserializer;
import org.xml.sax.InputSource;

public class ClientTest {

	
	public  GlobusCredential  authenticate(String dorianURL,String uid,String pwd) {
		
		GlobusCredential proxy = null;
		 try {
			 //String dorianURL = "https://localhost:8443/wsrf/services/cagrid/Dorian";
			 DorianClient dClient = null;
	
	         dClient = new DorianClient(dorianURL);
	            
			 BasicAuthCredential bac1 = new BasicAuthCredential();
	            
			 bac1.setUserId(uid);
			 bac1.setPassword(pwd);
			// SAMLAssertion s = c.authenticateWithIdP(bac1);
			 
			 String xml = dClient.authenticateWithIdP(bac1).getXml();
			 
			 //System.out.println(xml);
			 
			 ProxyLifetime proxyLifetime = new ProxyLifetime();
			 proxyLifetime.setHours(12);
			 proxyLifetime.setMinutes(0);
			 proxyLifetime.setSeconds(0);
			 
			 KeyPair pair = KeyUtil.generateRSAKeyPair512();
			 PublicKey 		 key = new PublicKey(KeyUtil.writePublicKey(pair.getPublic()));
			 
			 SAMLAssertion saml2 = null;
	            try {
	                saml2 = new SAMLAssertion(xml);
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            
	            
	      
	            gov.nih.nci.cagrid.dorian.bean.X509Certificate list[] = null;
	            try {
	                list = dClient.createProxy(saml2, key, proxyLifetime, new DelegationPathLength(0));
	                
	            } catch (InvalidAssertionFault ex) {
	                ex.printStackTrace();
	            } catch (InvalidProxyFault ex) {
	                ex.printStackTrace();
	            } catch (UserPolicyFault ex) {
	                ex.printStackTrace();
	            } catch (PermissionDeniedFault ex) {
	                ex.printStackTrace();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	            
	            if (list != null) {
	                X509Certificate[] certs = new X509Certificate[list.length];
	                for (int i = 0; i < list.length; i++) {
	                    try {
	                        certs[i] = CertUtil.loadCertificate(list[i].getCertificateAsString());
	                    } catch (Exception ex) {
	                        ex.printStackTrace();
	                    }
	                }
	                
	                try {
	                    proxy = new GlobusCredential(pair.getPrivate(), certs);
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                
	                try {
	                    ProxyUtil.saveProxyAsDefault(proxy);
	                    
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                } 
	                               
	            }

		} catch (Exception ex) {
			 ex.printStackTrace();
		}
		return  proxy;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
try{
			
			String serviceURL = args[0];
			String dorianURL = args[1];
			String uid = args[2];
			String pwd = args[3];
			
			ClientTest test = new ClientTest();
			GlobusCredential proxy = test.authenticate(dorianURL,uid,pwd);
			
			///System.out.print(proxy.getIssuer());
			

			CaaersClient client = new CaaersClient(serviceURL,proxy);
			//CaaersClient client = new CaaersClient("https://localhost/wsrf-ds/services/cagrid/Caaers",proxy);

            java.lang.Object qryObj = ObjectDeserializer.deserialize(new InputSource(new FileInputStream("src/gov/nih/nci/cagrid/caaers/client/testcql.xml")),CQLQuery.class);

			CQLQuery cqlQuery = (CQLQuery)qryObj;
            CQLQueryResults results = client.query(cqlQuery);
            

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	


}
