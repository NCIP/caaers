package gov.nih.nci.cabig.caaers.utils.pdf;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

/**
 * @author Biju Joseph
 * @date 6/30/11
 */
public class MedwatchUtils {

    protected static final Log log = LogFactory.getLog(MedwatchUtils.class);
    public static ThreadLocal<Counter> localCache = new ThreadLocal<Counter>();

    public static Counter get(){
        Counter c = localCache.get();
        if(c == null) {
            c = new Counter();
            localCache.set(c);
        }
        return c;
    }

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

    public static String description(String s, int linesPossible, int charPerLine){
        if(StringUtils.isEmpty(s)) return "";
        int length = s.length();
        if(getDescriptionIndex() >= length) {
            return "";
        }
        //am i in continue section ?
        if(getDescriptionIndex() > 0){
            String desc = s.substring(getDescriptionIndex())  ;
            setDescriptionIndex(length);
            return desc;
        }

        int currentLine = descriptionCount();
        //am I in continue for the first time
        if(getDescriptionIndex() == 0 && currentLine > linesPossible ) {
           setDescriptionIndex(length);
           return s;
        }

        //I am in main section

        int linesLeft = linesPossible - currentLine;
        if(linesLeft <=0){
            setContinueDescription();
            return "";
        }
        for(int i = 0; i < linesLeft; i++) {
            incrementDescription();
        }

        int charsPossible = linesLeft * charPerLine;
        if(charsPossible > length) charsPossible = length;

        for(int i =0; i < charsPossible; i++){
            char c = s.charAt(i);
            if(c == '\n') charsPossible = charsPossible - charPerLine;
        }
        if(charsPossible == 0){
            setContinueDescription();
            return "";
        }
        if(charsPossible >= length) {
            setDescriptionIndex(length);
            return s;
        }

        //find word boundary
        for(int i = charsPossible; i >=0; i-- ) {
            char c = s.charAt(i -1 );
            if(c == '.' || c == ' '){
                setDescriptionIndex(i);
                setContinueDescription();
                return s.substring(0, i);
            }
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

    public static void incrementLab(){
        get().incrementLab();
    }
    public static int labCount(){
        return get().getLabCount();
    }
    public static int resetLab(){
        return get().resetLabCount();
    }

    public static void incrementMedHistory(){
        get().incrementMedHistory();
    }
    public static int medHistoryCount(){
        return get().getMedHistoryCount();
    }
    public static int resetMedHistory(){
        return get().resetMedHistoryCount();
    }

    public static void incrementDescription(){
        get().incrementDescription();
    }
    public static int descriptionCount(){
        return get().getDescriptionCount();
    }
    public static boolean continueDescription(){
        return get().getContinueDescription();
    }
    public static void setContinueDescription(){
         get().setContinueDescription(true);
    }

    public static int descriptionIndex(){
        return get().getDescriptionBeginIndex();
    }
    public static int resetDescription(){
        return get().resetDescriptionCount();
    }

    public static int resetDescriptionIndex(){
        get().setDescriptionBeginIndex(0);
        return 0;
    }

    public static int getDescriptionIndex(){
       return get().getDescriptionBeginIndex();
    }

    public static int setDescriptionIndex(int beginIndex){
        get().setDescriptionBeginIndex(beginIndex);
        return getDescriptionIndex();
    }
    public static class Counter {
        private  int labCount = 0;
        private int medHistoryCount = 0;
        private int descriptionCount = 0;
        private int descriptionBeginIndex = 0;
        private boolean continueDescription = false;

        public boolean getContinueDescription() {
            return continueDescription;
        }

        public void setContinueDescription(boolean continueDescription) {
            this.continueDescription = continueDescription;
        }

        public int getDescriptionBeginIndex() {
            return descriptionBeginIndex;
        }

        public void setDescriptionBeginIndex(int descriptionBeginIndex) {
            this.descriptionBeginIndex = descriptionBeginIndex;
        }

        public int resetDescriptionCount(){
            descriptionCount = 0;
            return descriptionCount;
        }
        public int resetMedHistoryCount(){
            medHistoryCount = 0;
            return medHistoryCount;
        }
        public int resetLabCount(){
            labCount = 0;
            return labCount;
        }

        public int incrementDescription(){
            descriptionCount++;
            return descriptionCount;
        }
        public int incrementMedHistory(){
            medHistoryCount++;
            return medHistoryCount;
        }
        public int incrementLab(){
            labCount++;
            return labCount;
        }
        public int getDescriptionCount(){
            return descriptionCount;
        }
        public int getMedHistoryCount(){
            return medHistoryCount;
        }
        public int getLabCount(){
            return labCount;
        }
    }

}
