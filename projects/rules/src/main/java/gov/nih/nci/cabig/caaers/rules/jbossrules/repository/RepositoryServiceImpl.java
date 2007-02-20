package gov.nih.nci.cabig.caaers.rules.jbossrules.repository;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.DateUtil;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.drools.repository.AssetItem;
import org.drools.repository.CategoryItem;
import org.drools.repository.PackageItem;
import org.drools.repository.RepositoryConfigurator;
import org.drools.repository.RulesRepository;
import org.drools.repository.RulesRepositoryException;
import org.springmodules.jcr.JcrTemplate;
import org.springmodules.jcr.support.JcrDaoSupport;

/**
 * Repository Service implementation for Drools. This delegates most of the work to the
 * org.drools.repository.RulesRepository. In case we need to get more functionality from the
 * repository than what drools provide we have the flexibility to do so in this class,
 * since this class has access to the JCR Repository and the JCRSession.
 * 
 * @author Sujith Vellat Thayyilthodi
 * */
public class RepositoryServiceImpl extends JcrDaoSupport implements RepositoryService {

	private RulesRepository rulesRepository;
	
	private JcrTemplate template;
	
	private Repository repository;

	

	public Boolean createCategory(Category category) throws RemoteException {
        String path = category.getPath();
		if (path == null || "".equals(path)) {
            path = "/";
        }
        
        CategoryItem item = getRulesRepository().loadCategory( path );
        item.addCategory( category.getName(), category.getDescription() );
        return Boolean.TRUE;
	}

	public String createRule(Rule rule) throws RemoteException {
        try {
			PackageItem packageItem = getRulesRepository().loadPackage( rule.getPackageName() );
	        AssetItem asset = packageItem.addAsset( rule.getName(), rule.getDescription(), rule.getCategory(), rule.getFormat() ); 
	        asset.updateContent(XMLUtil.toXML(rule));
	        getRulesRepository().save();
	        return asset.getUUID();
        }
        catch (RulesRepositoryException ex) {
            throw new RemoteException(ex.getMessage(), ex);
        }
	}

	public String createRuleSet(RuleSet ruleSet) throws RemoteException {
        PackageItem item = getRulesRepository().createPackage( ruleSet.getName(), ruleSet.getDescription() );
        return item.getUUID();
	}
	
	
	public Rule getRule(String ruleId) throws RemoteException {
        AssetItem item = getRulesRepository().loadAssetByUUID( ruleId );
        Rule rule = (Rule)XMLUtil.toObject(item.getContent());
        return rule;
	}

	public RuleSet[] listRuleSets() {
		RuleSet ruleSet = null;
		Iterator iterator = getRulesRepository().listPackages();
		ArrayList<RuleSet> ruleSetList = new ArrayList<RuleSet>();
		while(iterator.hasNext()) {
			PackageItem packageItem = (PackageItem)iterator.next();
			ruleSet = new RuleSet();
			ruleSet.setDescription(packageItem.getDescription());
			ruleSet.setName(packageItem.getName());
			ruleSetList.add(ruleSet);
		}
		return ruleSetList.toArray(new RuleSet[ruleSetList.size()]);
	}

	public RuleSet getRuleSet(String name) {
        PackageItem item = getRulesRepository().loadPackage( name );
        RuleSet ruleSet = new RuleSet();
        ruleSet.setId(item.getUUID());
//        ruleSet.header = item.getHeader();
//        ruleSet.externalURI = item.getExternalURI();
        ruleSet.setDescription(item.getDescription());
        ruleSet.setName(item.getName());
//        ruleSet.lastModified = item.getLastModified().getTime();
//        ruleSet.lasContributor = item.getLastContributor();
//        ruleSet.state = item.getStateDescription();
		return ruleSet;
	}
	
	public String checkinVersion(Rule rule) throws RemoteException {
        AssetItem assetItem = getRulesRepository().loadAssetByUUID(rule.getId());
        MetaData meta = rule.getMetaData();
        //getMetaDataMapper().copyFromMetaData( meta, repoAsset );
        assetItem.updateDateEffective(DateUtil.dateToCalendar( meta.getDateEffective()));
        assetItem.updateDateExpired( DateUtil.dateToCalendar( meta.getDateExpired() ) );
        
        int numberOfCategories = meta.sizeCategoryList();
        String[] categories = new String[numberOfCategories];
        for(int i = 0; i < numberOfCategories; i ++) {
        	categories[i] = meta.getCategory(i).getName();
        }
        assetItem.updateCategoryList( categories );
        assetItem.updateContent(XMLUtil.toXML(rule));
        assetItem.checkin( meta.getCheckinComment() );
        
        return assetItem.getUUID();
	}
	
	public void moveRule(String newRuleSetName, String ruleId) throws RemoteException {
		getRulesRepository().moveRuleItemPackage(newRuleSetName, ruleId, "");
	}

	
	public JcrTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JcrTemplate template) {
		this.template = template;
	}

	public Repository getRepository() {
		return repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}

    private RulesRepository getRulesRepository() {
    	
    	Session session = getSession();
    	if(rulesRepository == null) {
    		RepositoryConfigurator repositoryConfigurator = new RepositoryConfigurator();
    		try {
				session = (repositoryConfigurator.login(getRepository()));
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		repositoryConfigurator.setupRulesRepository(session);
    		rulesRepository = new RulesRepository(session);
    	}
    	return rulesRepository;
    }


}