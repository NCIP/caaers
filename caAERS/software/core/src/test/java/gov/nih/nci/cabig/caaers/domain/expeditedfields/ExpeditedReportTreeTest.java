package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersError;
import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.domain.*;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.MutablePropertyValues;

/**
 * @author Rhett Sutphin
 * @author Biju Joseph
 */
public class ExpeditedReportTreeTest extends CaaersTestCase {
    
    private ExpeditedReportTree tree;

    @Override
    protected void setUp() throws Exception {
        super.setUp();    
        tree = (ExpeditedReportTree)getDeployedApplicationContext().getBean("expeditedReportTree");
    }

    // makes sure that a single instance of the bean is loaded.
    //http://jira.semanticbits.com/browse/CAAERS-3736
    public void testSingletonBeanLoading(){
      Object anotherTree =  getDeployedApplicationContext().getBean("expeditedReportTree");
      assertSame(anotherTree, tree);
    }

    public void testAllPropertiesImpliedByTreeExistInModel() throws Exception {
        assertChildPropertiesExist(tree, ExpeditedAdverseEventReport.class);
    }

    public void testRecurcivelyCollectListNodes() {
        List<TreeNode> nodes = new ArrayList();
        tree.recursivelyCollectListNodes(nodes);
        assertEquals("adverseEvents", nodes.get(0).getPropertyName());
    }

    public void testFetchListPropertyValue(){
        ExpeditedAdverseEventReport aeReport = Fixtures.createSavableExpeditedReport();
        AdverseEvent ae1 = Fixtures.createAdverseEvent(1, Grade.LIFE_THREATENING);
        AdverseEvent ae2 = Fixtures.createAdverseEvent(2, Grade.LIFE_THREATENING);
        aeReport.addAdverseEvent(ae1);
        aeReport.addAdverseEvent(ae2);

        TreeNode node = tree.getNodeForSection(ExpeditedReportSection.ADVERSE_EVENT_SECTION);
        List<TreeNode> listNodes = new ArrayList<TreeNode>();

        node.recursivelyCollectListNodes(listNodes);

        TreeNode aListNode = listNodes.get(0);
        MutablePropertyValues mpvs = aListNode.getPropertyValuesFrom(aeReport);
        assertSame(ae2, mpvs.getPropertyValue("adverseEvents[1]").getValue());
        assertSame(ae1, mpvs.getPropertyValue("adverseEvents[0]").getValue());
    }

    @SuppressWarnings( { "RawUseOfParameterizedType" })
    private void assertChildPropertiesExist(TreeNode node, Class nodePropertyClass) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(nodePropertyClass);
        for (TreeNode child : node.getChildren()) {
            String childPropName = child.getPropertyName();
            if (childPropName == null) {
                // this child does not map to a property -- push down
                assertChildPropertiesExist(child, nodePropertyClass);

            } else {
                if (childPropName.indexOf('[') >= 0) {
                    childPropName = childPropName.substring(0, childPropName.indexOf('['));
                }
                
                if(childPropName.indexOf(".") > 0){
                	childPropName = childPropName.substring(0, childPropName.indexOf("."));
                }
                // look for matching property
                boolean found = false;
                for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                    if (descriptor.getName().equals(childPropName)) {
                        // found matching descriptor; recurse
                        assertChildPropertiesExist(child, getPropertyType(descriptor));
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    fail("Did not find property " + childPropName + " in "
                                    + nodePropertyClass.getSimpleName() + ".  Properties: "
                                    + listNames(beanInfo.getPropertyDescriptors()));
                }
                // check for "other" property, if applicable
                if (child instanceof CodedOrOtherPropertyNode) {
                    boolean otherFound = false;
                    CodedOrOtherPropertyNode codedOrOther = ((CodedOrOtherPropertyNode) child);
                    for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                        otherFound = pd.getName().equals(codedOrOther.getOtherPropertyName());
                        if (otherFound) break;
                    }
                    if (!otherFound) {
                        fail("Did not find property " + codedOrOther.getOtherPropertyName()
                                        + " ('other' for coded " + childPropName + ')' + " in "
                                        + nodePropertyClass.getSimpleName() + ".  Properties: "
                                        + listNames(beanInfo.getPropertyDescriptors()));
                    }
                }
            }
        }
    }

    public void testFindSimplePropertyNode() throws Exception {
        TreeNode found = tree.find("reporter");
        assertNotNull(found);
        assertEquals("Reporter details", found.getDisplayName());
    }

    public void testFindNestedPropertyNode() throws Exception {
        TreeNode found = tree.find("responseDescription.eventDescription");
        assertNotNull(found);
        assertEquals("Description and treatment of event(s)", found.getDisplayName());
    }

    public void testFindListPropertyChildNode() throws Exception {
        TreeNode found = tree.find("diseaseHistory.metastaticDiseaseSites[].codedSite");
        assertNotNull(found);
        assertEquals("Site name", found.getDisplayName());
    }

    public void testNameForPrimaryAdverseEvent() throws Exception {
        assertEquals("Primary adverse event", tree.find("adverseEvents").getDisplayName(0));
    }

    public void testGetNodeForSection() throws Exception {
        TreeNode node = tree.getNodeForSection(ExpeditedReportSection.ADDITIONAL_INFO_SECTION);
        assertEquals("Name should be ", node.getDisplayName(),
                        ExpeditedReportSection.ADDITIONAL_INFO_SECTION.name());
    }

    public void testSimplePropertyIsSatisfied() throws Exception {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();

        assertUnsatisfiedProperties("Reported present for null prop", "reporter.lastName", report,
                        "reporter.lastName");
        report.setReporter(new Reporter());
        assertUnsatisfiedProperties("Reported present for null prop", "reporter.lastName", report,
                        "reporter.lastName");
        report.getReporter().setLastName("Mendoza");
        assertNoUnsatisfiedProperties("Reported not present when present", "reporter.lastName",
                        report);
    }

    public void testListPropertyIsSatisfied() throws Exception {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
        LabTerm labTerm = new LabTerm();
        labTerm.setTerm("Test");

        assertNoUnsatisfiedProperties("Reported not present for empty list", "labs[].labTerm",
                        report);
        report.addLab(new Lab());
        assertUnsatisfiedProperties("Reported present for null prop", "labs[].labTerm", report,
                        "labs[0].labTerm");
        report.getLabs().get(0).setLabTerm(labTerm);
        assertNoUnsatisfiedProperties("Reported not present for set prop", "labs[].labTerm", report);
    }

    public void testListPropertyIsNotSatisfiedWhenOneInstanceIsMissing() throws Exception {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
        LabTerm labTerm = new LabTerm();
        labTerm.setTerm("Test");

        assertNoUnsatisfiedProperties("Reported not present for empty list", "labs[].labTerm",
                        report);
        report.getLabs().get(0).setLabTerm(labTerm);
        report.getLabs().get(1).setLabTerm(null);
        assertUnsatisfiedProperties("Wrong unsatisfied properties found", "labs[].labTerm", report,
                        "labs[1].labTerm");
    }
    
    public void testListPropertyIsNotSatisfiedWhenTheSecondInstanceInCodedOrOtherIsMissing(){
    	//aeReport.labs[1].other
    	 ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
         LabTerm labTerm = new LabTerm();
         report.getLabs().get(0).setOther(null);
    	 List<UnsatisfiedProperty> actualUnsatisfied = tree.verifyPropertiesPresent("labs[1].other",
                 report);
    	 System.out.println(actualUnsatisfied);
    }

    public void testCodedOrOtherSatisfiedByCoded() throws Exception {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();

        assertUnsatisfiedProperties("Should be initially unsatisfied",
                        "diseaseHistory.abstractStudyDisease", report,
                        "diseaseHistory.abstractStudyDisease");

        report.setDiseaseHistory(new DiseaseHistory());
        report.getDiseaseHistory().setCtepStudyDisease(new CtepStudyDisease());
        assertNoUnsatisfiedProperties("Coded didn't satisfy it", "diseaseHistory.abstractStudyDisease",
                        report);
    }

    public void testCodedOrOtherSatisfiedByOther() throws Exception {
        ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();

        assertUnsatisfiedProperties("Should be initially unsatisfied",
                        "diseaseHistory.abstractStudyDisease", report,
                        "diseaseHistory.abstractStudyDisease");

        report.setDiseaseHistory(new DiseaseHistory());
        report.getDiseaseHistory().setOtherPrimaryDisease("Hoolitis");
        assertNoUnsatisfiedProperties("Other didn't satisfy it", "diseaseHistory.abstractStudyDisease",
                        report);
    }
    
    public void testDisplayNames(){
    	populateFieldMap(tree);
    	TreeNode node = tree.find("additionalInformation");
    	ExpeditedAdverseEventReport report = new ExpeditedAdverseEventReport();
    	AdditionalInformation ai = new AdditionalInformation();
    	ai.setAutopsyReport(true);
    	report.setAdditionalInformation(ai);
    	ArrayList list = new ArrayList();
    	for(TreeNode tn : node.getChildren()){
    		System.out.println(tn.getPropertyValuesFrom(report).getPropertyValues()[0].getValue());
    		list.add(tn.getPropertyPath());
    	}
    	
    	

    	
    	List<UnsatisfiedProperty> props = tree.verifyPropertiesPresent(list, report);
    	System.out.println(props);
    }
    
    
    public void populateFieldMap(
            TreeNode node) {
		    		// only add leaf nodes in the filed map. (others are just sections)
		if (node.isLeaf()) {
		    String key = node.getParent().getQualifiedDisplayName();
		
		    String displayName = node.getDisplayName();
		    String path = node.getPropertyPath();
		    if (StringUtils.isEmpty(path)) return;
		    if (StringUtils.isEmpty(displayName)) displayName = node.getParent().getDisplayName();
		    System.out.println(key + ">>" + displayName);
		   
		
		    		
		} else {
		    // add children of this node in the map
		    for (TreeNode n : node.getChildren())
		        populateFieldMap( n);
		}

}


    public void testGetSectionForNode() throws Exception {
        assertEquals(ExpeditedReportSection.LABS_SECTION, tree.getSectionForNode(tree
                        .find("labs[].baseline.value")));
        assertEquals(ExpeditedReportSection.MEDICAL_INFO_SECTION, tree.getSectionForNode(tree
                        .find("diseaseHistory")));
    }

    private void assertNoUnsatisfiedProperties(String msg, String expectedProp, ExpeditedAdverseEventReport report) {
        assertUnsatisfiedProperties(msg, expectedProp, report);
    }

    private void assertUnsatisfiedProperties(String msg, String expectedProp, ExpeditedAdverseEventReport report, String... expectedUnsatisfiedProperties) {
        List<UnsatisfiedProperty> actualUnsatisfied = tree.verifyPropertiesPresent(expectedProp, report);
        assertEquals(msg + ": Wrong number of unsatisfied props: " + actualUnsatisfied, expectedUnsatisfiedProperties.length, actualUnsatisfied.size());
        for (int i = 0; i < expectedUnsatisfiedProperties.length; i++) {
            String expected = expectedUnsatisfiedProperties[i];
            assertEquals(msg + ": Mismatched prop", expected, actualUnsatisfied.get(i).getBeanPropertyName());
        }
    }

    /**
     * Figures out the next domain object type down from the descriptor given.
     */
    private Class<?> getPropertyType(PropertyDescriptor descriptor) {
        if (Map.class.isAssignableFrom(descriptor.getPropertyType())) {
            Type returnType = descriptor.getReadMethod().getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType) returnType).getActualTypeArguments()[1];
            } else {
                fail("Could not extract type of value for map property " + descriptor.getName());
            }
        } else if (List.class.isAssignableFrom(descriptor.getPropertyType())) {
            Type returnType = descriptor.getReadMethod().getGenericReturnType();
            if (returnType instanceof ParameterizedType) {
                return (Class<?>) ((ParameterizedType) returnType).getActualTypeArguments()[0];
            } else {
                fail("Could not extract type of value for list property " + descriptor.getName());
            }
        } else {
            return descriptor.getPropertyType();
        }
        throw new CaaersError("That's unpossible");
    }

    private List<String> listNames(PropertyDescriptor[] propertyDescriptors) {
        List<String> names = new ArrayList<String>(propertyDescriptors.length);
        for (PropertyDescriptor descriptor : propertyDescriptors) {
            names.add(descriptor.getName());
        }
        return names;
    }

}
