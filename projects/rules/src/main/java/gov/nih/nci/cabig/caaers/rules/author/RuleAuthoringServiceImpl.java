package gov.nih.nci.cabig.caaers.rules.author;

import gov.nih.nci.cabig.caaers.rules.brxml.Category;
import gov.nih.nci.cabig.caaers.rules.brxml.MetaData;
import gov.nih.nci.cabig.caaers.rules.brxml.Rule;
import gov.nih.nci.cabig.caaers.rules.brxml.RuleSet;
import gov.nih.nci.cabig.caaers.rules.common.RuleServiceContext;
import gov.nih.nci.cabig.caaers.rules.common.RuleUtil;
import gov.nih.nci.cabig.caaers.rules.common.XMLUtil;
import gov.nih.nci.cabig.caaers.rules.exception.RuleException;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryService;
import gov.nih.nci.cabig.caaers.rules.repository.RepositoryServiceImpl;
import gov.nih.nci.cabig.caaers.rules.runtime.RuleExecutionServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.rules.ConfigurationException;
import javax.rules.RuleServiceProvider;
import javax.rules.RuleServiceProviderManager;
import javax.rules.admin.RuleExecutionSet;
import javax.rules.admin.RuleExecutionSetCreateException;
import javax.rules.admin.RuleExecutionSetRegisterException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.log4j.Logger;
import org.drools.repository.AssetItem;
import org.drools.repository.CategoryItem;
import org.drools.repository.PackageItem;
import org.drools.repository.RulesRepositoryException;

/**
 * The entry point for Managing Rules.
 * 
 * The client applications can
 * <p>
 * 1) either accessed by reference 2) make use of this as a service exposed using WSDL.
 * </p>
 * 
 * @author Sujith Vellat Thayyilthodi
 */

public class RuleAuthoringServiceImpl implements RuleAuthoringService {
    private static Logger logger = Logger.getLogger(RuleAuthoringServiceImpl.class);

    private RuleServiceProvider ruleServiceProvider;

    private RepositoryService repositoryService;

    public RuleAuthoringServiceImpl() {
        super();
        initializeService();
    }

    private void initializeService() {
        try {
            this.repositoryService = (RepositoryServiceImpl) RuleServiceContext.getInstance().repositoryService;

            RuleServiceProviderManager
                            .registerRuleServiceProvider(
                                            RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER,
                                            Class
                                                            .forName("gov.nih.nci.cabig.caaers.rules.jsr94.jbossrules.RuleServiceProviderImpl"));

            this.ruleServiceProvider = RuleServiceProviderManager
                            .getRuleServiceProvider(RuleExecutionServiceImpl.RULE_SERVICE_PROVIDER);

        } catch (ConfigurationException e) {
            throw new RuleException(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuleException(e.getMessage(), e);
        }
    }

    /**
     * Save the set of rules. This will save the set of rules in three different formats. 1) The
     * generic XML will be saved. 2) The converted XML (or any language specific to the Rule Engine
     * being used) 3) The compiled AST representation specific to the Rule Engine.
     * 
     */
    /*
     * public void createRuleSet(RuleSet ruleSet) { String bindUri = UUID.randomUUID().toString();
     * ruleSet.setId(bindUri); final Map<String, Object> properties = new HashMap<String,
     * Object>(); properties.put("source", "xml"); try { RuleAdapter ruleAdapter =
     * (RuleAdapter)Class.forName("gov.nih.nci.cabig.caaers.rules.adapter.JBossXSLTRuleAdapter").newInstance();
     * //Please note that we can only pass the Package here to the RuleExecution set. //Since we
     * still use drools implementation of LocalRuleExecutionSetProvider final RuleExecutionSet
     * ruleExecutionSet = this.ruleSetProvider .createRuleExecutionSet(ruleAdapter.adapt(ruleSet),
     * properties); this.ruleAdministrator.registerRuleExecutionSet(bindUri, ruleExecutionSet,
     * properties); } catch (RuleExecutionSetCreateException e) { throw new
     * RuleException(e.getMessage(), e); } catch (RuleExecutionSetRegisterException e) { throw new
     * RuleException(e.getMessage(), e); } catch (RemoteException e) { throw new
     * RuleException(e.getMessage(), e); } catch (InstantiationException e) { throw new
     * RuleException(e.getMessage(), e); } catch (IllegalAccessException e) { throw new
     * RuleException(e.getMessage(), e); } catch (ClassNotFoundException e) { throw new
     * RuleException(e.getMessage(), e); }
     *  }
     */

    /**
     * This is a direct method for the testing code. So that rules can be tested by directly
     * authoring them in the JBoss Rule XML or DRL format.
     */
    public void addRuleExecutionSet(final String bindUri, final InputStream resourceAsStream,
                    final Map properties) {
        final Reader ruleReader = new InputStreamReader(resourceAsStream);

        RuleExecutionSet ruleExecutionSet;
        try {
            ruleExecutionSet = this.ruleServiceProvider.getRuleAdministrator()
                            .getLocalRuleExecutionSetProvider(null).createRuleExecutionSet(
                                            ruleReader, properties);
            this.ruleServiceProvider.getRuleAdministrator().registerRuleExecutionSet(bindUri,
                            ruleExecutionSet, properties);
        } catch (RuleExecutionSetCreateException e) {
            throw new RuleException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuleException(e.getMessage(), e);
        } catch (RuleExecutionSetRegisterException e) {
            throw new RuleException(e.getMessage(), e);
        } catch (ConfigurationException e) {
            throw new RuleException(e.getMessage(), e);
        }
    }

    public void createCategory(Category category) throws RemoteException {
        this.repositoryService.createCategory(category);
    }

    public void createRuleSet(RuleSet ruleSet) throws RemoteException {
        this.repositoryService.createRuleSet(ruleSet);
    }

    public String createRule(Rule rule) throws RemoteException {
        String ruleId = this.repositoryService.createRule(rule);
        rule.setId(ruleId);
        MetaData metaData = rule.getMetaData();
        metaData.setCheckinComment("Initial Version");
        try {
            metaData.setDateEffective(DatatypeFactory.newInstance().newXMLGregorianCalendar());
            metaData.setDateExpired(DatatypeFactory.newInstance().newXMLGregorianCalendar());
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        this.updateRule(rule);
        return ruleId;
    }

    public void updateRule(Rule rule) throws RemoteException {
        this.repositoryService.updateRule(rule);
    }

    public void updateRuleSet(RuleSet ruleSet) throws RemoteException {
        this.repositoryService.createRuleSet(ruleSet);
    }

    public RuleSet getRuleSet(String ruleSetName, boolean cached) throws RemoteException {
        return this.repositoryService.getRuleSet(ruleSetName, cached);
    }

    public Rule getRule(String ruleId) throws RemoteException {
        return this.repositoryService.getRule(ruleId);
    }

    public List<RuleSet> getAllRuleSets() throws RemoteException {
        return this.repositoryService.listRuleSets();
    }

    public List<Rule> getRulesByCategory(String categoryPath) throws RemoteException {
        // First check if the category exists
        try {
            this.repositoryService.getCategory(categoryPath);
        } catch (RulesRepositoryException rulesRepositoryException) {
            if (rulesRepositoryException.getCause() instanceof PathNotFoundException) {
                // Category does not exist
                return new ArrayList<Rule>();
            } else {
                throw new RemoteException(rulesRepositoryException.getMessage(),
                                rulesRepositoryException);
            }
        }

        return this.repositoryService.getRulesByCategory(categoryPath);
    }

    public Category getCategory(String categoryPath) throws RemoteException {
        try {
            this.repositoryService.getCategory(categoryPath);
        } catch (RulesRepositoryException rulesRepositoryException) {
            if (rulesRepositoryException.getCause() instanceof PathNotFoundException) {
                // Category does not exist
                return null;
            } else {
                throw new RemoteException(rulesRepositoryException.getMessage(),
                                rulesRepositoryException);
            }
        }
        return this.repositoryService.getCategory(categoryPath);
    }

    /*
     * REVISIT: THis is added for testing purpose, will be removed
     */
    public void listPackages() {

        Iterator<PackageItem> packItr = this.repositoryService.getRulesRepository().listPackages();

        System.out.println("Packages: ");

        while (packItr.hasNext()) {
            // System.out.println(packItr.next().toString());

            PackageItem packItem = packItr.next();

            System.out.println("Package Name: " + packItem.getName());
            System.out.println("Package Description: " + packItem.getDescription());

            // Display Rules in this package
            System.out.println("Rules: ");

            List<Rule> rules = new ArrayList<Rule>();
            MetaData metaData = null;
            Rule rule = null;

            Iterator<AssetItem> assetItr = packItem.getAssets();

            int i = 0;
            while (assetItr.hasNext()) {
                AssetItem assetItem = assetItr.next();
                rule = (Rule) XMLUtil.unmarshal(assetItem.getContent());
                rule.setId(assetItem.getUUID());
                copyToMetaData(rule.getMetaData(), assetItem);
                XMLUtil.unmarshal(XMLUtil.marshal(rule));
                rules.add(rule);

                System.out.println("Rule " + ++i + " : " + assetItem.getName() + " : "
                                + assetItem.getDescription());

            }

        }
    }

    private void copyToMetaData(MetaData metaData, AssetItem assetItem) {
        Category category = null;
        metaData.setName(assetItem.getName());
        List<CategoryItem> categoryItems = assetItem.getCategories();
        List<Category> categories = new ArrayList<Category>();
        for (CategoryItem categoryItem : categoryItems) {
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

    public boolean containsRuleSet(String ruleSetName) {
        return this.repositoryService.containsRuleSet(ruleSetName);
    }

    /*
     * This method returns a list of RuleSets belonging to the specified sponsor (non-Javadoc)
     * 
     * @see gov.nih.nci.cabig.caaers.rules.author.RuleAuthoringService#findRuleSetsForSponsor(java.lang.String)
     */
    public List<RuleSet> findRuleSetsForSponsor(String sponsorName) {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();

        final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.sponsor";

        String sponsorPackageName = SPONSOR_BASE_PACKAGE + "."
                        + RuleUtil.getStringWithoutSpaces(sponsorName);

        Iterator<PackageItem> packItr = this.repositoryService.getRulesRepository().listPackages();

        while (packItr.hasNext()) {
            PackageItem packItem = packItr.next();

            System.out.println("Package Name: " + packItem.getName());
            System.out.println("Package Description: " + packItem.getDescription());

            if (packItem.getName().indexOf(sponsorPackageName, 0) != -1) {
                RuleSet ruleSet = new RuleSet();
                ruleSet.setId(packItem.getUUID());
                ruleSet.getImport().add(packItem.getHeader());
                ruleSet.setDescription(packItem.getDescription());
                ruleSet.setName(packItem.getName());
                ruleSets.add(ruleSet);
            }
        }

        if (ruleSets.size() == 0) {
            return null;
        } else {
            return ruleSets;
        }
    }

    /*
     * This method returns a list of RuleSets belonging to the Study
     */
    public List<RuleSet> findRuleSetsForStudy(String sponsorName, String studyName) {
        List<RuleSet> ruleSets = new ArrayList<RuleSet>();

        final String SPONSOR_BASE_PACKAGE = "gov.nih.nci.cabig.caaers.rule.study";

        String studyPackageName = SPONSOR_BASE_PACKAGE + "."
                        + RuleUtil.getStringWithoutSpaces(sponsorName) + "."
                        + RuleUtil.getStringWithoutSpaces(studyName);

        Iterator<PackageItem> packItr = this.repositoryService.getRulesRepository().listPackages();

        while (packItr.hasNext()) {
            PackageItem packItem = packItr.next();

            System.out.println("Package Name: " + packItem.getName());
            System.out.println("Package Description: " + packItem.getDescription());

            if (packItem.getName().indexOf(studyPackageName, 0) != -1) {
                RuleSet ruleSet = new RuleSet();
                ruleSet.setId(packItem.getUUID());
                ruleSet.getImport().add(packItem.getHeader());
                ruleSet.setDescription(packItem.getDescription());
                ruleSet.setName(packItem.getName());
                ruleSets.add(ruleSet);
            }
        }

        if (ruleSets.size() == 0) {
            return null;
        } else {
            return ruleSets;
        }
    }

}