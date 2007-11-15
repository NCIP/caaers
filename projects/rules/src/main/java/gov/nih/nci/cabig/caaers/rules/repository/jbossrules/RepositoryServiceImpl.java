package gov.nih.nci.cabig.caaers.rules.repository.jbossrules;

import gov.nih.nci.cabig.caaers.rules.RuleException;
import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.runtime.RulesCache;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;
import javax.rules.admin.RuleExecutionSet;

import org.apache.log4j.Logger;
import org.drools.repository.AssetItem;
import org.drools.repository.AssetItemIterator;
import org.drools.repository.CategoryItem;
import org.drools.repository.PackageItem;
import org.drools.repository.RulesRepository;
import org.drools.repository.RulesRepositoryException;
import org.drools.repository.StateItem;
import org.springmodules.jcr.JcrTemplate;
import org.springmodules.jcr.support.JcrDaoSupport;

/**
 * Repository Service implementation for Drools. 
 * This delegates most of the work  to the <i>org.drools.repository.RulesRepository</i>.
 * <p>
 * In case we need to get more functionality from the repository than what drools provide we have the
 * flexibility to do so in this class. 
 * 
 * This class has access to the JCR Repository and the JCRSession.
 * 
 * Spring transactional control also can be applied on this class.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class RepositoryServiceImpl extends JcrDaoSupport implements
		RepositoryService {

	private static Logger logger = Logger.getLogger(RepositoryServiceImpl.class);
	
	private RulesRepository rulesRepository;

	private JcrTemplate template;

	private Repository repository;
	
	RulesCache rc = RulesCache.getInstance();

	public Boolean createCategory(Category category) 
	{
		String path = category.getPath();
		if (path == null || "".equals(path)) {
			path = "/";
		}

		CategoryItem item = getRulesRepository().loadCategory(path);
		item.addCategory(category.getMetaData().getName(), category
				.getMetaData().getDescription());
		getRulesRepository().save();
		return Boolean.TRUE;
	}

	/*
	 * This method creates a rule
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.repository.RepositoryService#createRule(gov.nih.nci.cabig.caaers.rules.brxml.Rule)
	 */
	public String createRule(Rule rule) 
	{
		MetaData metaData = rule.getMetaData();
		PackageItem packageItem = getRulesRepository().loadPackage(metaData.getPackageName());
		
		String categoryName = null;
		
		if (metaData.getCategory() != null && metaData.getCategory().size() > 0)
		{	
			Category initialCategory = metaData.getCategory().get(0);
		
			categoryName = (initialCategory != null) ? initialCategory.getPath() + "/" + initialCategory
							.getMetaData().getName() : getDefaultCategory();
		}
		
		AssetItem asset = packageItem.addAsset(metaData.getName(), metaData
				.getDescription(), categoryName, metaData.getFormat());
		asset.updateContent(XMLUtil.marshal(rule));
		getRulesRepository().save();
		return asset.getUUID();
	}

	private String getDefaultCategory() 
	{
		return "default";
	}

	public void updateRule(Rule rule) 
	{
			AssetItem assetItem = getRulesRepository().loadAssetByUUID(rule.getId());

			// Check whether the node is updateable
			try 
			{
				if (assetItem.getNode().getPrimaryNodeType().getName().equals("nt:version")) 
				{
					String message = "Error. Tags can only be added to the head version of a rule node";
					throw new RulesRepositoryException(message);
				}
			} 
			catch (RepositoryException e) 
			{
				logger.error("Exception: ",	e);
				throw new RuleException(e.getMessage(), e); 			
			}
			
			MetaData meta = rule.getMetaData();
			if(meta.getDateEffective() != null)
				assetItem.updateDateEffective(meta.getDateEffective()
					.toGregorianCalendar());
			
			if(meta.getDateExpired() != null)
				assetItem.updateDateExpired(meta.getDateExpired()
					.toGregorianCalendar());
			
			List<Category> categoryList = meta.getCategory();
			int numberOfCategories = categoryList.size();
			String[] categories = new String[numberOfCategories];
			
			for (int i = 0; i < numberOfCategories; i++) 
			{
				categories[i] = categoryList.get(i).getPath() + "/"+ categoryList.get(i).getMetaData().getName();
			}
			
			assetItem.updateCategoryList(categories);
			
			rule.getMetaData().setDateEffective(null);
			rule.getMetaData().setDateExpired(null);
			assetItem.updateContent(XMLUtil.marshal(rule));
			assetItem.updateState(StateItem.DRAFT_STATE_NAME);
			getRulesRepository().save();

	}

	/*
	 * This method creates a packageitem in the repository and it is equivalent to RuleSet.
	 * 
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.repository.RepositoryService#createRuleSet(gov.nih.nci.cabig.caaers.rules.brxml.RuleSet)
	 */
	public String createRuleSet(RuleSet ruleSet) 
	{
		PackageItem item = getRulesRepository().createPackage(ruleSet.getName(), ruleSet.getDescription());
		item.updateSubject(ruleSet.getSubject());
		item.updateCoverage(ruleSet.getCoverage());
		
		List imports = ruleSet.getImport();
		String header = "";
		
		for (int count = 0; count < imports.size(); count++) 
		{
			header += imports.get(count).toString();
		}
		
		item.updateHeader(header);
		getRulesRepository().save();
		
		return item.getUUID();
	}

	public Rule getRule(String ruleId) {
		AssetItem item = getRulesRepository().loadAssetByUUID(ruleId);
		Rule rule = (Rule) XMLUtil.unmarshal(item.getContent());
		return rule;
	}

	public List<RuleSet> listRuleSets() {
		RuleSet ruleSet = null;
		Iterator iterator = getRulesRepository().listPackages();
		ArrayList<RuleSet> ruleSetList = new ArrayList<RuleSet>();
		while (iterator.hasNext()) {
			PackageItem packageItem = (PackageItem) iterator.next();
			ruleSet = new RuleSet();
			ruleSet.setId(packageItem.getUUID());
			ruleSet.setDescription(packageItem.getDescription());
			ruleSet.setName(packageItem.getName());
			ruleSet.setSubject(packageItem.getSubject());
			ruleSet.setCoverage(packageItem.getCoverage());
			
			//
			String subject = packageItem.getSubject();
			String[] tokens = subject.split("\\|\\|");
			
			if (tokens.length>1){
				ruleSet.setLevel(tokens[0]);
				ruleSet.setOrganization(tokens[1]);
			}
			if (tokens.length>2){
				ruleSet.setStudy(tokens[2]);
			} else {
				ruleSet.setStudy(" N/A ");
			}
			if (!ruleSet.getName().equals("default")) {
				ruleSetList.add(ruleSet);
			}
		}
		return ruleSetList;
	}

	/*
	 * This method loads the package aka RuleSet from the repository
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.repository.RepositoryService#getRuleSet(java.lang.String)
	 */
	public RuleSet getRuleSet(String name) 
	{
		if (!getRulesRepository().containsPackage(name))
		{
			return null;
		}
		
		RuleSet ruleSet;
    	if (rc.getRuleSet(name) != null){
    		ruleSet= rc.getRuleSet(name);
    	} else {
    		PackageItem item = getRulesRepository().loadPackage(name);

    		ruleSet = new RuleSet();
    		ruleSet.setId(item.getUUID());
    		ruleSet.getImport().add(item.getHeader());
    		// ruleSet.header = item.getHeader();
    		// ruleSet.externalURI = item.getExternalURI();
    		ruleSet.setDescription(item.getDescription());
    		ruleSet.setName(item.getName());
    		ruleSet.setSubject(item.getSubject());
    		ruleSet.setCoverage(item.getCoverage());
    		
    		
    		// ruleSet.lastModified = item.getLastModified().getTime();
    		// ruleSet.lasContributor = item.getLastContributor();
    		// ruleSet.state = item.getStateDescription();

    		//make 2 assets , one is rule set info asset. and rule set asset 
    		//when we create rule set , acll the method add asset 
    		//when we load the the rules load asset (string name);
    		//
    		long t2 = System.currentTimeMillis();
    		AssetItemIterator iterator = (AssetItemIterator) item.getAssets();
    		
    		while (iterator.hasNext()) 
    		{
    			AssetItem ruleItem = (AssetItem) iterator.next();
    			//long t3=System.currentTimeMillis();
    			Rule rule = (Rule) XMLUtil.unmarshal(ruleItem.getContent());
    			//System.out.println("EACH UNMARSHAL ELAPSED " + (System.currentTimeMillis()-t3));
    			ruleSet.getRule().add(rule);
    		}
    		System.out.println("LOAD RULES UNMARSHAL ELAPSED " + (System.currentTimeMillis()-t2));
    		rc.putRuleSet(name, ruleSet);
    	}
    	
		
		return ruleSet;
	}
	
	public void deleteRuleSet(String ruleSetName) throws Exception {

		PackageItem item = getRulesRepository().loadPackage(ruleSetName);
		item.getNode().remove();
		

		getRulesRepository().save();
		rc.ruleSetDeployed(ruleSetName);
		rc.ruleSetModified(ruleSetName);
	}
	
	public void deleteRule(String ruleSetName, String ruleName) {
		
		try {
			PackageItem item = getRulesRepository().loadPackage(ruleSetName);
			AssetItem ai = item.loadAsset(ruleName);
			ai.remove();
		
			System.out.println("removed .." + ruleName);
		} catch (Exception e) {
			// not able to delete rule as the rule was not save , this exception is fine ... 
			e.printStackTrace();
		}
		/*
		String r = "";
		
		Iterator itr = item.getAssets();
		
		while (itr.hasNext()) {
			AssetItem a = (AssetItem) itr.next();
			if(a.getUUID().equals(ruleName)) {
				r = a.getName();
				break;
			}
			//a.remove();
			System.out.println(a.getName());
		}
		*/
		
		//item.removeAsset(ruleName);		
	}

	public String checkinVersion(Rule rule) {
		AssetItem assetItem = getRulesRepository()
				.loadAssetByUUID(rule.getId());
		MetaData meta = rule.getMetaData();
		// getMetaDataMapper().copyFromMetaData( meta, repoAsset );
		assetItem.updateDateEffective(meta.getDateEffective()
				.toGregorianCalendar());
		assetItem
				.updateDateExpired(meta.getDateExpired().toGregorianCalendar());

		List<Category> categoryList = meta.getCategory();
		int numberOfCategories = categoryList.size();
		String[] categories = new String[numberOfCategories];
		for (int i = 0; i < numberOfCategories; i++) {
			categories[i] = categoryList.get(i).getMetaData().getName();
		}
		assetItem.updateCategoryList(categories);
		assetItem.updateContent(XMLUtil.marshal(rule));
		assetItem.checkin(meta.getCheckinComment());

		return assetItem.getUUID();
	}

	public void moveRule(String newRuleSetName, String ruleId) {
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

	/*
	 * REVISIT: Change this to private
	 */
	public RulesRepository getRulesRepository() {

		Session session = null;//getSession();
		if (rulesRepository == null) {
			RulesRepositoryEx.RepositoryConfiguratorEx repositoryConfigurator = new RulesRepositoryEx.RepositoryConfiguratorEx();
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
			rulesRepository = new RulesRepositoryEx(session);
		}
		
		return rulesRepository;
	}

	public String registerRuleSet(String name, RuleSetInfo ruleSetInfo) {
		CompiledPackageItem compiledPackageItem = ((RulesRepositoryEx) getRulesRepository())
				.createCompiledPackage(name, ruleSetInfo.getContent());
		rc.ruleSetDeployed(name);
		rc.ruleSetModified(name);
		return compiledPackageItem.getUUID();
	}

	public RuleSetInfo[] listRegistrations() {
		RuleSetInfo ruleSet = null;
		Iterator iterator = getRulesRepository().listPackages();
		ArrayList<RuleSetInfo> ruleSetList = new ArrayList<RuleSetInfo>();
		while (iterator.hasNext()) {
			CompiledPackageItem packageItem = (CompiledPackageItem) iterator
					.next();
			ruleSet = new RuleSetInfo();
			ruleSet.setBindUri(packageItem.getName());
			ruleSet.setContent(getObjectFromInputStream(packageItem
					.getBinaryContent()));
			ruleSetList.add(ruleSet);
		}
		return ruleSetList.toArray(new RuleSetInfo[ruleSetList.size()]);
	}

	public RuleSetInfo getRegisteredRuleset(String bindUri) {
		RuleSetInfo ruleSetInfo = new RuleSetInfo();
		try {
			RulesRepositoryEx rulesRepositoryEx = ((RulesRepositoryEx) getRulesRepository());
			ruleSetInfo.setContent(rulesRepositoryEx
					.convertByteArrayToObject(rulesRepositoryEx
							.loadCompiledPackage(bindUri)
							.getBinaryContentAsBytes()));

		} catch (IOException e) {
			throw new RulesRepositoryException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new RulesRepositoryException(e.getMessage(), e);
		}
		return ruleSetInfo;
	}

	private Object getObjectFromInputStream(InputStream inputStream) {
		ObjectInputStream objectInputStream = (ObjectInputStream) inputStream;
		try {
			return objectInputStream.readObject();
		} catch (IOException e) {
			throw new RulesRepositoryException(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			throw new RulesRepositoryException(e.getMessage(), e);
		}
	}

	public void deregisterRuleExecutionSet(String bindUri) {
		((RulesRepositoryEx) getRulesRepository())
				.removeCompiledPackage(bindUri);
		rc.ruleSetDeployed(bindUri);
		rc.ruleSetModified(bindUri);
	}

	/**
	 * Load all the Rules associated with the Category Tag mentioned.
	 *  
	 * */
	public List<Rule> getRulesByCategory(String categoryTag) {
		List<Rule> rules = new ArrayList<Rule>();
		MetaData metaData = null;
		Rule rule = null;

		List<AssetItem> assetItems = getRulesRepository().findAssetsByCategory(categoryTag);
		List<String> addedIds = new ArrayList<String>();
		for(AssetItem assetItem : assetItems) {
			//This is a hack ... because the drools-repository doesn't treat versioning well. Lets avoid duplicates for this release
			if(addedIds.contains(assetItem.getUUID())) continue;
			
			rule = (Rule)XMLUtil.unmarshal(assetItem.getContent());
			rule.setId(assetItem.getUUID());
			addedIds.add(assetItem.getUUID());
			copyToMetaData(rule.getMetaData(), assetItem);
			XMLUtil.unmarshal(XMLUtil.marshal(rule));
			rules.add(rule);
		}
		return rules;
	}
	
	/**
	 * This will be improved by using Reflection + Mapping.
	 * Dirty way of manual mapping now.
	 * */
	private void copyToMetaData(MetaData metaData, AssetItem assetItem) {
		Category category = null;
		metaData.setName(assetItem.getName());
		List<CategoryItem> categoryItems = assetItem.getCategories();
		List<Category> categories = new ArrayList<Category>();
		for(CategoryItem categoryItem : categoryItems) {
			category = new Category();
			try {
				category.setId(categoryItem.getNode().getUUID());
				category.setPath(categoryItem.getNode().getPath());
				MetaData catMetaData = new MetaData();
				catMetaData.setName(categoryItem.getName());
				category.setMetaData(catMetaData);
			} catch (UnsupportedRepositoryOperationException e) {
				throw new RuleException(e.getMessage(), e);
			} catch (RepositoryException e) {
				throw new RuleException(e.getMessage(), e);
			}
			metaData.getCategory().add(category);
		}
	}

	public Category getCategory(String categoryPath) {
		CategoryItem categoryItem = getRulesRepository().loadCategory(categoryPath);
		
		Category category = new Category();
		String fullPath = categoryItem.getFullPath();
		if(fullPath.lastIndexOf("/") != -1) {
			category.setPath(fullPath.substring(0, fullPath.lastIndexOf("/")));
		} else {
			category.setPath("");
		}
		MetaData metaData = new MetaData();
		metaData.setName(categoryItem.getName());
		category.setMetaData(metaData);
		return category;
	}
	
	public List<String> getAllImmediateChildren(String categoryPath){
		CategoryItem categoryItem = getRulesRepository().loadCategory(categoryPath);
		System.out.println(categoryItem.getName());
		
		
		List<CategoryItem> items = categoryItem.getChildTags();
		
		List<String> ruleSetNames = new ArrayList<String>();
		//ruleSetNames.add(arg0);
		
		for( CategoryItem s: items){
			System.out.println(s.getName());
			System.out.println(s.getFullPath());
			
			ruleSetNames.add(s.getName());
		}
		
		return ruleSetNames;
	}

	/*
	 * This method check for the existence of the package aka RuleSet
	 * (non-Javadoc)
	 * @see gov.nih.nci.cabig.caaers.rules.repository.RepositoryService#containsRuleSet(java.lang.String)
	 */
	public boolean containsRuleSet(String ruleSetName)
	{
		return getRulesRepository().containsPackage(ruleSetName);
	}
}
