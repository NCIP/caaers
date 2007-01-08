package test;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.globus.gsi.GlobusCredential;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationServiceClient;
import gov.nih.nci.cagrid.dorian.bean.SAMLAssertion;
import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.ifs.bean.DelegationPathLength;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.dorian.ifs.bean.PublicKey;
import gov.nih.nci.cagrid.gridca.common.CertUtil;
import gov.nih.nci.cagrid.gridca.common.KeyUtil;

public class DemoProxyClient {

	private static final int PROXY_LIFETIME_HOURS = 12;
	private static final int PROXY_LIFETIME_MINUTES = 0;
	private static final int PROXY_LIFETIME_SECONDS = 0;
	private static final int DELEGATION_PATH_LENGTH = 0;
	private static final String IFS_URL = "https://cagrid-dorian-qa.nci.nih.gov:8443/wsrf/services/cagrid/Dorian";
	private static final String IDP_URL = "https://cagrid-auth-qa.nci.nih.gov:8443/wsrf/services/cagrid/AuthenticationService";

	public static void main(String[] args) throws Exception {
		String username = args[0];
		String password = args[1];
		BasicAuthenticationCredential bac = new BasicAuthenticationCredential();
		bac.setUserId(username);
		bac.setPassword(password);

		AuthenticationServiceClient idpClient = new AuthenticationServiceClient(
				IDP_URL);
		Credential cred = new Credential();
		cred.setBasicAuthenticationCredential(bac);
		String samlXml = idpClient.authenticate(cred).getXml();

		ProxyLifetime lifetime = new ProxyLifetime();
		lifetime.setHours(PROXY_LIFETIME_HOURS);
		lifetime.setMinutes(PROXY_LIFETIME_MINUTES);
		lifetime.setSeconds(PROXY_LIFETIME_SECONDS);

		DorianClient ifsClient = new DorianClient(IFS_URL);
		KeyPair pair = KeyUtil.generateRSAKeyPair512();
		PublicKey key = new PublicKey(KeyUtil.writePublicKey(pair.getPublic()));
		SAMLAssertion samlObj = new SAMLAssertion(samlXml);
		gov.nih.nci.cagrid.dorian.bean.X509Certificate list[] = ifsClient
				.createProxy(samlObj, key, lifetime, new DelegationPathLength(
						DELEGATION_PATH_LENGTH));

		X509Certificate[] certs = new X509Certificate[list.length];
		for (int i = 0; i < list.length; i++) {
			certs[i] = CertUtil.loadCertificate(list[i]
					.getCertificateAsString());
		}
		GlobusCredential proxy = new GlobusCredential(pair.getPrivate(), certs);
		System.out.println(proxy.getIdentity());
	}

}
