package gov.nih.nci.cabig.caaers.rules.repository.jbossrules;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.deploy.sxml.RuleSetInfo;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

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
 * Repository Service implementation for Drools. This delegates most of the work
 * to the org.drools.repository.RulesRepository. In case we need to get more
 * functionality from the repository than what drools provide we have the
 * flexibility to do so in this class, since this class has access to the JCR
 * Repository and the JCRSession.
 * 
 * @author Sujith Vellat Thayyilthodi
 */
public class RepositoryServiceImpl extends JcrDaoSupport implements
		RepositoryService {

	private RulesRepository rulesRepository;

	private JcrTemplate template;

	private Repository repository;

	public Boolean createCategory(Category category) throws RemoteException {
		String path = category.getPath();
		if (path == null || "".equals(path)) {
			path = "/";
		}

		CategoryItem item = getRulesRepository().loadCategory(path);
		item.addCategory(category.getMetaData().getName(), category
				.getMetaData().getDescription());
		return Boolean.TRUE;
	}

	public String createRule(Rule rule) throws RemoteException {
		try {
			MetaData metaData = rule.getMetaData();
			PackageItem packageItem = getRulesRepository().loadPackage(
					metaData.getPackageName());
			Category initialCategory = metaData.getCategory().get(0);
			String categoryName = (initialCategory != null) ? initialCategory
					.getMetaData().getName() : getDefaultCategory();
			AssetItem asset = packageItem.addAsset(metaData.getName(), metaData
					.getDescription(), categoryName, metaData.getFormat());
			asset.updateContent(XMLUtil.marshal(rule));
			getRulesRepository().save();
			return asset.getUUID();
		} catch (RulesRepositoryException ex) {
			throw new RemoteException(ex.getMessage(), ex);
		}
	}

	private String getDefaultCategory() {
		return "default";
	}

	public void updateRule(Rule rule) throws RemoteException {
		try {
			AssetItem assetItem = getRulesRepository().loadAssetByUUID(
					rule.getId());

			// Check whether the node is updateable
			if (assetItem.getNode().getPrimaryNodeType().getName().equals(
					"nt:version")) {
				String message = "Error. Tags can only be added to the head version of a rule node";
				throw new RulesRepositoryException(message);
			}
			MetaData meta = rule.getMetaData();
			assetItem.updateDateEffective(meta.getDateEffective()
					.toGregorianCalendar());
			assetItem.updateDateExpired(meta.getDateExpired()
					.toGregorianCalendar());
			List<Category> categoryList = meta.getCategory();
			int numberOfCategories = categoryList.size();
			String[] categories = new String[numberOfCategories];
			for (int i = 0; i < numberOfCategories; i++) {
				categories[i] = categoryList.get(i).getMetaData().getName();
			}
			assetItem.updateCategoryList(categories);
			assetItem.updateContent(XMLUtil.marshal(rule));
			assetItem.updateState(StateItem.DRAFT_STATE_NAME);
			getRulesRepository().save();
		} catch (RulesRepositoryException ex) {
			throw new RemoteException(ex.getMessage(), ex);
		} catch (RepositoryException ex) {
			throw new RemoteException(ex.getMessage(), ex);
		}

	}

	public String createRuleSet(RuleSet ruleSet) throws RemoteException {
		PackageItem item = getRulesRepository().createPackage(
				ruleSet.getName(), ruleSet.getDescription());
		List imports = ruleSet.getImport();
		String header = "";
		for (int count = 0; count < imports.size(); count++) {
			header += imports.get(count).toString();
		}
		item.updateHeader(header);
		getRulesRepository().save();
		return item.getUUID();
	}

	public Rule getRule(String ruleId) throws RemoteException {
		AssetItem item = getRulesRepository().loadAssetByUUID(ruleId);
		Rule rule = (Rule) XMLUtil.unmarshal(item.getContent());
		return rule;
	}

	public RuleSet[] listRuleSets() {
		RuleSet ruleSet = null;
		Iterator iterator = getRulesRepository().listPackages();
		ArrayList<RuleSet> ruleSetList = new ArrayList<RuleSet>();
		while (iterator.hasNext()) {
			PackageItem packageItem = (PackageItem) iterator.next();
			ruleSet = new RuleSet();
			ruleSet.setDescription(packageItem.getDescription());
			ruleSet.setName(packageItem.getName());
			ruleSetList.add(ruleSet);
		}
		return ruleSetList.toArray(new RuleSet[ruleSetList.size()]);
	}

	public RuleSet getRuleSet(String name) {
		PackageItem item = getRulesRepository().loadPackage(name);
		RuleSet ruleSet = new RuleSet();
		ruleSet.setId(item.getUUID());

		ruleSet.getImport().add(item.getHeader());
		// ruleSet.header = item.getHeader();
		// ruleSet.externalURI = item.getExternalURI();
		ruleSet.setDescription(item.getDescription());
		ruleSet.setName(item.getName());
		// ruleSet.lastModified = item.getLastModified().getTime();
		// ruleSet.lasContributor = item.getLastContributor();
		// ruleSet.state = item.getStateDescription();

		AssetItemIterator iterator = (AssetItemIterator) item.getAssets();
		while (iterator.hasNext()) {
			AssetItem ruleItem = (AssetItem) iterator.next();
			Rule rule = (Rule) XMLUtil.unmarshal(ruleItem.getContent());
			ruleSet.getRule().add(rule);
		}
		return ruleSet;
	}

	public String checkinVersion(Rule rule) throws RemoteException {
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

	public void moveRule(String newRuleSetName, String ruleId)
			throws RemoteException {
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

	public String registerRuleSet(String name, RuleSetInfo ruleSetInfo)
			throws RemoteException {
		CompiledPackageItem compiledPackageItem = ((RulesRepositoryEx) getRulesRepository())
				.createCompiledPackage(name, ruleSetInfo.getContent());
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
		ObjectInputStream objectInputStream;
		try {
			/*
			 * objectInputStream = new ObjectInputStream(((RulesRepositoryEx)
			 * getRulesRepository())
			 * .loadCompiledPackage(bindUri).getBinaryContent());
			 * ruleSetInfo.setContent(objectInputStream.readObject());
			 */
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
	}

}