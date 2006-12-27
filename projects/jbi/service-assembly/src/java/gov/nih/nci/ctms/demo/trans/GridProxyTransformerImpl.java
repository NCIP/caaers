/**
 * 
 */
package gov.nih.nci.ctms.demo.trans;

import gov.nih.nci.cagrid.authentication.bean.BasicAuthenticationCredential;
import gov.nih.nci.cagrid.authentication.bean.Credential;
import gov.nih.nci.cagrid.authentication.client.AuthenticationServiceClient;
import gov.nih.nci.cagrid.authentication.stubs.types.AuthenticationProviderFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InsufficientAttributeFault;
import gov.nih.nci.cagrid.authentication.stubs.types.InvalidCredentialFault;
import gov.nih.nci.cagrid.dorian.bean.SAMLAssertion;
import gov.nih.nci.cagrid.dorian.client.DorianClient;
import gov.nih.nci.cagrid.dorian.ifs.bean.DelegationPathLength;
import gov.nih.nci.cagrid.dorian.ifs.bean.ProxyLifetime;
import gov.nih.nci.cagrid.dorian.ifs.bean.PublicKey;
import gov.nih.nci.cagrid.gridca.common.CertUtil;
import gov.nih.nci.cagrid.gridca.common.KeyUtil;

import java.security.KeyPair;
import java.security.cert.X509Certificate;

import org.globus.gsi.GlobusCredential;

/**
 * @author <a href="mailto:joshua.phillips@semanticbits.com>Joshua Phillips</a>
 * 
 */
public class GridProxyTransformerImpl implements GridProxyTransformer {

	private int delegationPathLength;

	/**
	 * The AuthenticationService URL
	 */
	private String idpUrl;

	/**
	 * The Dorian URL
	 */
	private String ifsUrl;

	private int proxyLifetimeHours;

	private int proxyLifetimeMinutes;

	private int proxyLifetimeSeconds;

	public int getDelegationPathLength() {
		return delegationPathLength;
	}

	public String getIdpUrl() {
		return idpUrl;
	}

	public String getIfsUrl() {
		return ifsUrl;
	}

	public int getProxyLifetimeHours() {
		return proxyLifetimeHours;
	}

	public int getProxyLifetimeMinutes() {
		return proxyLifetimeMinutes;
	}

	public int getProxyLifetimeSeconds() {
		return proxyLifetimeSeconds;
	}

	public void setDelegationPathLength(int delegationPathLength) {
		this.delegationPathLength = delegationPathLength;
	}

	public void setIdpUrl(String idpUrl) {
		this.idpUrl = idpUrl;
	}

	public void setIfsUrl(String ifsUrl) {
		this.ifsUrl = ifsUrl;
	}

	public void setProxyLifetimeHours(int proxyLifetimeHours) {
		this.proxyLifetimeHours = proxyLifetimeHours;
	}

	public void setProxyLifetimeMinutes(int proxyLifetimeMinutes) {
		this.proxyLifetimeMinutes = proxyLifetimeMinutes;
	}

	public void setProxyLifetimeSeconds(int proxyLifetimeSeconds) {
		this.proxyLifetimeSeconds = proxyLifetimeSeconds;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gov.nih.nci.ctms.demo.GridProxyTransformer#transform(java.lang.String,
	 *      java.lang.String)
	 */
	public GlobusCredential transform(BasicAuthenticationCredential bac)
			throws InvalidCredentialFault, InsufficientAttributeFault,
			AuthenticationProviderFault {
		
		GlobusCredential proxy = null;

		AuthenticationServiceClient idpClient = null;
		try {
			idpClient = new AuthenticationServiceClient(getIdpUrl());
		} catch (Exception ex) {
			throw new RuntimeException(
					"Error instantiating AuthenticationServiceClient: "
							+ ex.getMessage(), ex);
		}

		Credential cred = new Credential();
		cred.setBasicAuthenticationCredential(bac);
		String samlXml = null;
		try {
			samlXml = idpClient.authenticate(cred).getXml();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error authenticating: "
					+ ex.getMessage(), ex);
			
		}

		ProxyLifetime lifetime = new ProxyLifetime();
		lifetime.setHours(getProxyLifetimeHours());
		lifetime.setMinutes(getProxyLifetimeMinutes());
		lifetime.setSeconds(getProxyLifetimeSeconds());

		DorianClient ifsClient = null;
		try {
			ifsClient = new DorianClient(getIfsUrl());
		} catch (Exception ex) {
			throw new RuntimeException("Error instantiating DorianClient: "
					+ ex.getMessage(), ex);
		}
		KeyPair pair = null;
		PublicKey key = null;
		try {
			pair = KeyUtil.generateRSAKeyPair512();
			key = new PublicKey(KeyUtil.writePublicKey(pair.getPublic()));
		} catch (Exception ex) {
			throw new RuntimeException("Error generating key pair: "
					+ ex.getMessage(), ex);
		}
		SAMLAssertion samlObj = new SAMLAssertion(samlXml);

		gov.nih.nci.cagrid.dorian.bean.X509Certificate list[] = null;
		try {
			list = ifsClient.createProxy(samlObj, key, lifetime,
					new DelegationPathLength(getDelegationPathLength()));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error getting proxy: "
					+ ex.getMessage(), ex);
		}

		if (list != null) {
			try {
				X509Certificate[] certs = new X509Certificate[list.length];
				for (int i = 0; i < list.length; i++) {
					certs[i] = CertUtil.loadCertificate(list[i]
							.getCertificateAsString());
				}
				proxy = new GlobusCredential(pair.getPrivate(), certs);
			} catch (Exception ex) {
				throw new RuntimeException(
						"Error constructing GlobusCredential: "
								+ ex.getMessage(), ex);
			}
		}

		return proxy;
	}

}
