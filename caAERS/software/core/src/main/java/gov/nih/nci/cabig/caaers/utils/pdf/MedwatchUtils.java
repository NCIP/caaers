package gov.nih.nci.cabig.caaers.utils.pdf;

import org.apache.commons.lang.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

/**
 * @author Biju Joseph
 * @date 6/30/11
 */
public class MedwatchUtils {

    protected static final Log log = LogFactory.getLog(MedwatchUtils.class);



    public static int possibleElements(NodeList nodes , int n , String commaSeperatedXPath){

        if(nodes == null || nodes.getLength() == 0) return 1;
        String[] xpath = StringUtils.isEmpty(commaSeperatedXPath) ? new String[]{"."} : commaSeperatedXPath.split(",");
        int l = 0;
        int k = 1;
        for(int i = 0; i < nodes.getLength(); i++){
            Node node = nodes.item(i);
            for(int j = 0 ; j < xpath.length; j++){
               String s = evalXPathsOnNode(node, xpath[j]);
               l += StringUtils.length(s);
            }
            if(l >= n) return k;
            
            k++;
        }

        return k;
    }

    public static String evalXPathsOnNode(Node n, String strXPath){
        try{

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            DOMSource domSource = new DOMSource(n);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,"yes");
            StringWriter sw = new StringWriter();
            StreamResult result = new StreamResult(sw);
            transformer.transform(domSource, result);
            return evalXPathOnXML(sw.toString(), strXPath);

        }catch (Exception e){
           log.debug(e);
        }
        
        return "";
    }

    public static String evalXPathOnXML(String xml, String xpathExpression){
        try{

            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
            NodeList l = XPathAPI.selectNodeList(doc, xpathExpression);
            StringBuffer sb = new StringBuffer();
            if(l != null ){
                for(int i =0; i < l.getLength(); i++){
                    Node n = l.item(i);
                    sb.append(n.getTextContent());
                }
            }
            return sb.toString();
        }catch (Exception e){
             log.debug(e);
        }
        return "";
    }


    public static String before(String sentence, int n){

        int l = sentence.length();

        if(n >= l) return sentence;


        char c = sentence.charAt(n);
        if(c == ' ' || c == '\n') return sentence.substring(0, n);


        String s1 = StringUtils.substring(sentence, 0, n);
        l = n;
        c = s1.charAt(l-1);

        int lastIndex = n;
        if(c != '\n' || c != ' '){
            int a = s1.lastIndexOf('\n');
            int b = s1.lastIndexOf(' ');
            lastIndex = (a > b) ? a : b;
        }

        return s1.substring(0, lastIndex);

    }


    public static String after(String sentence, int n){

        int l = sentence.length();
        if(n >= l) return "";
                

        char c = sentence.charAt(n);
        if(c == ' ' || c == '\n') return sentence.substring(n + 1);

        int lastIndex = n;
        String s1 = StringUtils.substring(sentence, 0, n);
        int a = s1.lastIndexOf('\n');
        int b = s1.lastIndexOf(' ');
        lastIndex = (a > b) ? a : b;


        return sentence.substring(lastIndex+1);

    }


}
