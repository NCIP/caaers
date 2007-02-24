package gov.nih.nci.cabig.caaers.rules.repository.jbossrules;

import java.io.InputStream;
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
    public static final String COMPILED_RULE_PACKAGE_TYPE_NAME           = "drools:compiledRulepackageNodeType";

    
	public static final String CONTENT_PROPERTY_BINARY_NAME = "drools:binaryContent";
	public static final String CONTENT_PROPERTY_ATTACHMENT_FILENAME = "drools:attachmentFileName";
	
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
