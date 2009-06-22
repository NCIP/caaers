package gov.nih.nci.caaersinstaller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * 
 * In order for the client to trust the WebSSO Server you need to install the WebSSO Server's Public CA Cert into its truststore. 
 * This can be done by the following steps
 *  1. On the target application server, simply copy the cacerts to cacerts.old (to save it just in case)
 *  2. Run the java program given below by giving the following command - run java InstallCert <<CAS_SERVER_ADDRESS>>:<<CAS_SERVER_PORT>> 
 *  (i.e. provide the argument "<<CAS_SERVER_ADDRESS>>:<<CAS_SERVER_PORT>>" to the executable "InstallCert"). 
 *
 */
public class InstallCert {
	
    public static void main(String[] args) {
    	
    	try{
    	    String host;
    	    int port;
    	    char[] passphrase;
    	    String trustStore; 
    	    
    	    if("".equals(args[0])){
    	    	System.out.println("Server Address not provided");
    	    	return;
    	    }else{
    	    	host=args[0];
    	    }
    	    System.out.println("Server Address :: " +host);
    	    
    	    if("".equals(args[1])){
    	    	System.out.println("Server Port not provided");
    	    	return;
    	    }else{
    	    	port=Integer.parseInt(args[1]);
    	    }
    	    System.out.println("Server Port ::  " + port);
    	    
    	    if("".equals(args[2])){
    	    	System.out.println("TrustStore PassPhrase not provided");
    	    	passphrase = "changeit".toCharArray();
    	    }else{
    	    	passphrase=args[2].toCharArray();
    	    }
    	    System.out.println("Passphrase :: "  + new String(passphrase));
    	    
    	    if("".equals(args[3])){
    	    	System.out.println("TrustStore Location not provided using JAVA_HOME/lib/security/cacerts");
    	    	char SEP = File.separatorChar;
    	    	trustStore = System.getProperty("java.home") + SEP + "lib" + SEP  + "security";
    	    }else{
    	    	trustStore=args[3];
    	    }
    	    System.out.println("TrustStore  :: " + trustStore);
    	    
    	    File file = new File(trustStore);
    	    
    	    System.out.println("Loading KeyStore " + file + "...");
    	    InputStream in = new FileInputStream(file);
    	    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    	    ks.load(in, passphrase);
    	    in.close();
    	
    	    SSLContext context = SSLContext.getInstance("TLS");
    	    TrustManagerFactory tmf =
    	        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
    	    tmf.init(ks);
    	    X509TrustManager defaultTrustManager = (X509TrustManager)tmf.getTrustManagers()[0];
    	    SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
    	    context.init(null, new TrustManager[] {tm}, null);
    	    SSLSocketFactory factory = context.getSocketFactory();
    	
    	    System.out.println("Opening connection to " + host + ":" + port + "...");
    	    SSLSocket socket = (SSLSocket)factory.createSocket(host, port);
    	    socket.setSoTimeout(10000);
    	    
    	    try {
    	        System.out.println("Starting SSL handshake...");
    	        socket.startHandshake();
    	        socket.close();
    	        System.out.println();
    	        System.out.println("No errors, certificate is already trusted");
    	    } catch (SSLException e) {
    	        //System.out.println();
    	        //e.printStackTrace(System.out);
    	    }
    	    
  
    	    X509Certificate[] chain = tm.chain;
    	    if (chain == null) {
    	        System.out.println("Could not obtain server certificate chain");
    	        return;
    	    }
    	
    	    System.out.println();
    	    System.out.println("Server sent " + chain.length + " certificate(s):");
    	    System.out.println();
    	    MessageDigest sha1 = MessageDigest.getInstance("SHA1");
    	    MessageDigest md5 = MessageDigest.getInstance("MD5");
    	    for (int i = 0; i < chain.length; i++) {
    	        X509Certificate cert = chain[i];
    	        System.out.println
    	            (" " + (i + 1) + " Subject " + cert.getSubjectDN());
    	        System.out.println("   Issuer  " + cert.getIssuerDN());
    	        sha1.update(cert.getEncoded());
    	        System.out.println("   sha1    " + toHexString(sha1.digest()));
    	        md5.update(cert.getEncoded());
    	        System.out.println("   md5     " + toHexString(md5.digest()));
    	        System.out.println();
    	    }
    	    
    	    int k = 0;
    	    X509Certificate cert = chain[k];
    	    String alias = host + "-" + (k + 1);
    	    ks.setCertificateEntry(alias, cert);
    	
    	    OutputStream out = new FileOutputStream(file);
    	    ks.store(out, passphrase);
    	    out.close();
    	
    	    System.out.println();
    	    System.out.println(cert);
    	    System.out.println();
    	    System.out.println
    	        ("Added certificate to keystore 'cacerts' using alias '"
    	        + alias + "'");
    		
    		
    	}catch(Exception e){
    		System.out.println("Error Importing Cert");
    		System.out.println(e.getMessage());
    	}
	  }
	
	    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();
	
	    private static String toHexString(byte[] bytes) {
	    StringBuilder sb = new StringBuilder(bytes.length * 3);
	    for (int b : bytes) {
	        b &= 0xff;
	        sb.append(HEXDIGITS[b >> 4]);
	        sb.append(HEXDIGITS[b & 15]);
	        sb.append(' ');
	    }
	    return sb.toString();
    }

    private static class SavingTrustManager implements X509TrustManager {

      private final X509TrustManager tm;
      private X509Certificate[] chain;

      SavingTrustManager(X509TrustManager tm) {
          this.tm = tm;
      }

      public X509Certificate[] getAcceptedIssuers() {
          throw new UnsupportedOperationException();
      }

      public void checkClientTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
          throw new UnsupportedOperationException();
      }

      public void checkServerTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
          this.chain = chain;
          tm.checkServerTrusted(chain, authType);
      }
    }

}

