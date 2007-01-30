package gov.nih.nci.cabig.caaers.rules.jbossrules.jsr94.repository.db;

import gov.nih.nci.cabig.caaers.RuleException;
import gov.nih.nci.cabig.caaers.rules.jbossrules.jsr94.repository.RuleExecutionSetRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetRegisterException;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RuleExecutionSetRepositoryImpl implements RuleExecutionSetRepository {

	public static String CATALINA_HOME = "C:"+ File.separator + "caAERS";
	
    /** The Singleton instance of the repository. */
    // private static RuleExecutionSetRepository REPOSITORY;
    /** Holds the registered <code>RuleExecutionSet</code> objects. */
    private final Map         map              = new HashMap();

    /** Private constructor; use <code>getInstance</code> instead. */
    public RuleExecutionSetRepositoryImpl() {
        // Hide the constructor.
    }


    /**
     * Retrieves a <code>List</code> of the URIs that currently have
     * <code>RuleExecutionSet</code>s associated with them.
     * 
     * An empty list is returned is there are no associations.
     * 
     * @return a <code>List</code> of the URIs that currently have
     *         <code>RuleExecutionSet</code>s associated with them.
     */
    public List getRegistrations() {
        final List list = new ArrayList();
        list.addAll( this.map.keySet() );
        return list;
    }

    /**
     * Get the <code>RuleExecutionSet</code> bound to this URI, or return
     * <code>null</code>.
     * 
     * @param bindUri
     *            the URI associated with the wanted
     *            <code>RuleExecutionSet</code>.
     * 
     * @return the <code>RuleExecutionSet</code> bound to the given URI.
     */
    public RuleExecutionSet getRuleExecutionSet(final String bindUri) {
        
    	RuleExecutionSet ruleExecutionSet = (RuleExecutionSet) this.map.get( bindUri );
    	if(ruleExecutionSet == null) {
    		ruleExecutionSet = (RuleExecutionSet)readFromFile(bindUri);
    	}
    	return ruleExecutionSet;
    }

    /**
     * Register a <code>RuleExecutionSet</code> under the given URI.
     * 
     * @param bindUri
     *            the URI to associate with the <code>RuleExecutionSet</code>.
     * @param ruleSet
     *            the <code>RuleExecutionSet</code> to associate with the URI
     * 
     * @throws RuleExecutionSetRegisterException
     *             if an error occurred that prevented registration (i.e. if
     *             bindUri or ruleSet are <code>null</code>)
     */
    public void registerRuleExecutionSet(final String bindUri,
                                         final RuleExecutionSet ruleSet) throws RuleExecutionSetRegisterException {
        if ( bindUri == null ) {
            throw new RuleExecutionSetRegisterException( "bindUri cannot be null" );
        }
        if ( ruleSet == null ) {
            throw new RuleExecutionSetRegisterException( "ruleSet cannot be null" );
        }
		
        this.map.put( bindUri,
                      ruleSet );
        writeToFile( bindUri,
                ruleSet );
    }
    
    private Object readFromFile(final String bindUri) {
    	String filePath = "";
    	if (System.getProperty("CATALINA_HOME") != null) {
			CATALINA_HOME = System.getProperty("CATALINA_HOME");
			filePath = CATALINA_HOME + File.separator + "webapps"
			+ File.separator + "caaers" + File.separator + "JBossRules"
			+ File.separator + bindUri + File.separator + "RuleSet";
    	} else {
			filePath = CATALINA_HOME + File.separator + "JBossRules"
			+ File.separator + bindUri + File.separator + "RuleSet";
    	}

        RuleExecutionSet ruleExecutionSet = null;
    	FileInputStream fis;
		try {
			fis = new FileInputStream(filePath);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        ruleExecutionSet = (RuleExecutionSet) ois.readObject();
	        ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return ruleExecutionSet;
    }
    
    private void writeToFile(final String bindUri,
                                         final RuleExecutionSet ruleSet) {
    	String filePath = "";
    	if (System.getProperty("CATALINA_HOME") != null) {
			CATALINA_HOME = System.getProperty("CATALINA_HOME");
			filePath = CATALINA_HOME + File.separator + "webapps"
			+ File.separator + "caaers" + File.separator + "JBossRules"
			+ File.separator + bindUri;
        } else {
        	filePath = CATALINA_HOME + File.separator + "JBossRules"
    		+ File.separator + bindUri;
        }

		File temp = new File(filePath);
		temp.mkdirs();
		try {
			FileOutputStream fos = new FileOutputStream(filePath + File.separator + "RuleSet");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ruleSet);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    /**
     * Unregister a <code>RuleExecutionSet</code> from the given URI.
     * 
     * @param bindUri
     *            the URI to disassociate with the <code>RuleExecutionSet</code>.
     */
    public void unregisterRuleExecutionSet(final String bindUri) {
        if ( bindUri == null ) {
            throw new NullPointerException( "bindUri cannot be null" );
        }
        this.map.remove( bindUri );
    }	
	

    /**
	 Serialize an object into a byte array, which can be stored in a database as a blob
	 */
	public static byte[] objectToByteArray(Object obj)
			throws RuleException {
		try {
			ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
			ObjectOutputStream objectStream = new ObjectOutputStream(
					byteArrayStream);
			objectStream.writeObject(obj);
			objectStream.flush();
			return byteArrayStream.toByteArray();
		} catch (NotSerializableException exception) {
			throw new RuleException("Class, " + obj.getClass()
					+ " is not serializable.", exception);
		} catch (IOException ioe) {
			throw new RuleException("Could not serialize class: "
					+ obj.getClass() + ".", ioe);
		}
	}

	/**
	 Turn a byteArray back into an Object
	 */
	public static Object byteArrayToObject(byte[] byteArray)
			throws RuleException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(
					byteArray);
			ObjectInputStream objectInputStream = new ObjectInputStream(
					inputStream);
			return objectInputStream.readObject();
		} catch (ClassNotFoundException cex) {
			throw new RuleException(
					"Could not find the class to deserialize to.", cex);
		} catch (IOException ioe) {
			throw new RuleException(
					"Could not deserialize byte array.", ioe);
		}
	}    
    
}
