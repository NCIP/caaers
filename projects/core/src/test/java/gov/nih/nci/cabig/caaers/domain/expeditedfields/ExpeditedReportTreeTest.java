package gov.nih.nci.cabig.caaers.domain.expeditedfields;

import gov.nih.nci.cabig.caaers.CaaersTestCase;
import gov.nih.nci.cabig.caaers.CaaersError;
import gov.nih.nci.cabig.caaers.domain.ExpeditedAdverseEventReport;

import java.beans.Introspector;
import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.beans.IntrospectionException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

import junit.framework.TestCase;

/**
 * @author Rhett Sutphin
 */
public class ExpeditedReportTreeTest extends TestCase {
    private ExpeditedReportTree tree = new ExpeditedReportTree();

    public void testAllPropertiesImpliedByTreeExistInModel() throws Exception {
        assertChildPropertiesExist(tree, ExpeditedAdverseEventReport.class);
    }

    @SuppressWarnings({ "RawUseOfParameterizedType" })
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
                    fail("Did not find property " + childPropName
                        + " in " + nodePropertyClass.getSimpleName()
                        + ".  Properties: " + listNames(beanInfo.getPropertyDescriptors())
                    );
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
        TreeNode found = tree.find("radiationIntervention.description");
        assertNotNull(found);
        assertEquals("Treatment arm description", found.getDisplayName());
    }

    public void testFindListPropertyChildNode() throws Exception {
        TreeNode found = tree.find("diseaseHistory.metastaticDiseaseSites[].otherSite");
        assertNotNull(found);
        assertEquals("Other site", found.getDisplayName());
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
