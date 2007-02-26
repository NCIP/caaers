package gov.nih.nci.cabig.caaers.rules.repository.jbossrules;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.drools.repository.RulesRepository;
import org.drools.repository.RulesRepositoryException;
import org.drools.repository.VersionableItem;

/**
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class CompiledPackageItem extends VersionableItem{

	private static final Logger log = Logger.getLogger(CompiledPackageItem.class.getName());
	
    /**
     * The dublin core format attribute.
     */
    public static final String PACKAGE_FORMAT                    = "package";	

    /**
     * The name of the rule package node type
     */
    public static final String RULE_PACKAGE_TYPE_NAME           = "droolsex:compiledRulepackageNodeType";
    
	public static final String CONTENT_PROPERTY_BINARY_NAME = "droolsex:binaryContent";
	public static final String CONTENT_PROPERTY_ATTACHMENT_FILENAME = "droolsex:attachmentFileName";
	
	
    public static final String TITLE_PROPERTY_NAME            = "droolsex:title";
    public static final String DESCRIPTION_PROPERTY_NAME      = "droolsex:description";
    public static final String FORMAT_PROPERTY_NAME           = "droolsex:format";
    public static final String LAST_MODIFIED_PROPERTY_NAME    = "droolsex:lastModified";
	
	public CompiledPackageItem(RulesRepository rulesRepository, Node node) {
		super(rulesRepository, node);
	}

    /**
     * If the asset is a binary asset, then use this to update the content
     * (do NOT use text).
     */
    public CompiledPackageItem updateBinaryContent(InputStream data) {
        try {
            this.node.setProperty( CONTENT_PROPERTY_BINARY_NAME, data );            
            return this;
        } catch (RepositoryException e ) {
            log.log( Level.SEVERE, "Unable to update the assets binary content", e );
            throw new RulesRepositoryException( e );
        }
    }
    
    /**
     * If this asset contains binary data, this is how you return it. 
     * Otherwise it will return null.
     */
    public InputStream getBinaryContent() {
        try {
            if ( node.hasProperty( CONTENT_PROPERTY_BINARY_NAME ) ) {
                Property data = node.getProperty( CONTENT_PROPERTY_BINARY_NAME );
                return data.getStream();
            } else {
                return null;
            }
        } catch ( Exception e ) {
            log.log(Level.SEVERE, "Caught Exception",
                       e );
            throw new RulesRepositoryException( e );
        }        
    }
    
	public Object convertByteArrayToObject(byte[] bytes) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				bytes);
		ObjectInputStream objectInputStream = new ObjectInputStream(
				byteArrayInputStream);
		return objectInputStream.readObject();
	}
    
    /**
     * This is a convenience method for returning the binary data as a byte array.
     */
    public byte[] getBinaryContentAsBytes() {
        try {
            Node ruleNode = getVersionContentNode();
            if ( ruleNode.hasProperty( CONTENT_PROPERTY_BINARY_NAME ) ) {
                Property data = ruleNode.getProperty( CONTENT_PROPERTY_BINARY_NAME );
                InputStream in = data.getStream();
                
                // Create the byte array to hold the data
                byte[] bytes = new byte[(int) data.getLength()];
            
                // Read in the bytes
                int offset = 0;
                int numRead = 0;
                while (offset < bytes.length
                       && (numRead=in.read(bytes, offset, bytes.length-offset)) >= 0) {
                    offset += numRead;
                }
            
                // Ensure all the bytes have been read in
                if (offset < bytes.length) {
                    throw new RulesRepositoryException("Could not completely read asset "+ getName());
                }
            
                // Close the input stream and return bytes
                in.close();   
                return bytes;
            } else {
                return null;
            }
        } catch ( Exception e ) {
            if (e instanceof RuntimeException) throw (RuntimeException) e;
            throw new RulesRepositoryException( e );
        }  
    }

	@Override
	public VersionableItem getPrecedingVersion() throws RulesRepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VersionableItem getSucceedingVersion() throws RulesRepositoryException {
		// TODO Auto-generated method stub
		return null;
	}
   
}
