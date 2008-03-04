package gov.nih.nci.cabig.caaers.rules.objectgraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * This class will be used by caAERS Rules Engine.
 * 
 * @author vinaykumar
 * 
 */

public class FactResolverUsingBeanWrapper {
    private ObjectGraphFactory objectGraphFactory;

    /**
     * This method will evalaute a fact that is being asserted. For example a condition which says
     * that - If CTEP is one of the IND holder for this study. In that case folllowing argument will
     * be passed - (study,"gov.nih....INDHolder","name","CTEP")
     * 
     * @param sourceObject
     * @param targetObjectType
     * @param targetAttributeName
     * @param targetAttributeValue
     * @return boolean
     */

    public boolean assertFact(Object sourceObject, String targetObjectType,
                    String targetAttributeName, String targetAttributeValue) throws Exception {

        objectGraphFactory = ObjectGraphFactory.getInstance();

        NavigationPath np = objectGraphFactory.findNavigationPath(
                        sourceObject.getClass().getName(), targetObjectType);

        /*
         * NavigationPath np = new NavigationPath();
         * np.setSourceObjectType(sourceObject.getClass().getName());
         * np.setTargetObjectType(targetObjectType);
         */

        List<Node> pathNodes = np.getNode();
        /**
         * We need minimum two nodes in the path for this method to work.
         */
        int size = pathNodes.size();
        if (size < 2) {
            return false;
        }
        int i = 0;
        Object sourceObjectInChain = sourceObject;
        Node sourceNode = null;
        Node targetNode = null;
        Iterator<Node> it = pathNodes.iterator();
        while (it.hasNext()) {

            Object obj = null;
            if (i < 1) {
                sourceNode = it.next();
            }
            if (i > 0) {
                targetNode = it.next();
                /**
                 * For M:N relationship
                 */
                if ((sourceNode.isCollection()) && (targetNode.isCollection())) {
                    obj = getListOfNextTargetObjectsForEverySourceObject(targetNode,
                                    sourceObjectInChain);
                }
                /**
                 * For 1:M relationship
                 */
                if ((!sourceNode.isCollection()) && (targetNode.isCollection())) {
                    obj = getListOfNextTargetObjects(targetNode, sourceObjectInChain);
                }
                /**
                 * For M:1 relationship
                 */
                if ((sourceNode.isCollection()) && (!targetNode.isCollection())) {
                    obj = getListOfSingleNextTargetObjectsForEverySourceObject(targetNode,
                                    sourceObjectInChain);
                }
                /**
                 * For 1:1 relationship
                 */
                if ((!sourceNode.isCollection()) && (!targetNode.isCollection())) {
                    obj = getListOfNextTargetObjects(targetNode, sourceObjectInChain);
                }

                if (targetNode.isCollection()) {
                    if (obj == null) {
                        return false;
                    } else {
                        List l = (List) obj;
                        if (l.size() == 0) {
                            return false;
                        }
                    }
                } else {
                    if (obj == null) {
                        return false;
                    }
                }

                sourceNode = targetNode;
                sourceObjectInChain = obj;
            }
            i++;

        }

        /**
         * When you exhausted of all the result then wrap it up
         */

        return wrapUp(sourceObjectInChain, targetNode, targetObjectType, targetAttributeName,
                        targetAttributeValue);

    }

    private Object getListOfNextTargetObjectsForEverySourceObject(Node node, Object sourceObject)
                    throws Exception {
        List list = new ArrayList();
        String name = node.getName();
        List outerList = (List) sourceObject;
        Object obj = null;
        Iterator outerIterator = outerList.iterator();
        while (outerIterator.hasNext()) {
            Object tempObject = outerIterator.next();
            BeanWrapper brw = new BeanWrapperImpl(tempObject);
            obj = brw.getPropertyValue(name);
            if (obj != null) {
                List innerList = (List) obj;
                Iterator it = innerList.iterator();
                while (it.hasNext()) {
                    list.add(it.next());
                }
            }

        }
        return list;
    }

    private Object getListOfNextTargetObjects(Node node, Object nextSourceObject) throws Exception {
        Object obj = null;
        BeanWrapper brw = new BeanWrapperImpl(nextSourceObject);
        String name = node.getName();
        obj = brw.getPropertyValue(name);
        return obj;
    }

    private Object getListOfSingleNextTargetObjectsForEverySourceObject(Node node,
                    Object nextSourceObject) throws Exception {

        List listToBeReturned = new ArrayList();
        String name = node.getName();
        List list = (List) nextSourceObject;
        Object obj = null;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Object objectInList = it.next();
            BeanWrapper brw = new BeanWrapperImpl(objectInList);
            obj = brw.getPropertyValue(name);
            if (obj != null) {
                listToBeReturned.add(obj);
            }
        }
        return listToBeReturned;
    }

    private boolean wrapUp(Object targetObject, Node targetNode, String targetObjectType,
                    String targetAttributeName, String targetAttributeValue) throws Exception {
        boolean test = false;

        Class class_ = targetObject.getClass();

        if (class_.getName().startsWith("java.util")) {

            List list = (List) targetObject;
            Iterator it = list.iterator();

            while (it.hasNext()) {
                Object obj = it.next();
                Object value = null;
                BeanWrapper brw = new BeanWrapperImpl(obj);
                value = brw.getPropertyValue(targetAttributeName);

                if (value.toString().equalsIgnoreCase(targetAttributeValue)) {
                    test = true;
                    break;
                }
            }

        } else {

            Object value = null;
            BeanWrapper brw = new BeanWrapperImpl(targetObject);
            value = brw.getPropertyValue(targetAttributeName);

            if (value.toString().equalsIgnoreCase(targetAttributeValue)) {
                test = true;

            }

        }
        return test;

    }

    private String getMethodName(String name) {
        String prop = "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        return prop;
    }

}
