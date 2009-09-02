package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.domain.report.Report;
import gov.nih.nci.cabig.caaers.utils.XsltTransformer;
import gov.nih.nci.cabig.caaers.utils.XmlMarshaller;
import gov.nih.nci.cabig.caaers.domain.*;
import gov.nih.nci.cabig.caaers.domain.Study;
import gov.nih.nci.cabig.caaers.webservice.*;
import gov.nih.nci.cabig.caaers.dao.StudyDao;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FOUserAgent;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.Result;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.JAXBElement;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.namespace.QName;


public class BlankFormGenerator {

    protected final Log log = LogFactory.getLog(getClass());
    private static String XMLFile = "d:/AE-Blank-Test.xml";
    private static String PDFFile = "d:/AE-Blank-Test.pdf";
    private String XSLFile = "xslt/CALGB_AE_FORM.xslt";
//    private String XSLFile = "C:\\.SemanticBits\\caAERS\\trunk\\caAERS\\software\\core\\src\\main\\resources\\xslt\\CALGB_AE_FORM.xslt";

    private JAXBContext jaxbContext = null;
    private Marshaller marshaller = null;
    private gov.nih.nci.cabig.caaers.webservice.Studies studies = null;

    public BlankFormGenerator() {
    }

    public synchronized void  generatePdf(String xml, String pdfOutFileName) throws Exception {
        FopFactory fopFactory = FopFactory.newInstance();

        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        // configure foUserAgent as desired

        // Setup output
        OutputStream out = new java.io.FileOutputStream(pdfOutFileName);
        out = new java.io.BufferedOutputStream(out);

        try {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Setup XSLT
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(BlankFormGenerator.class.getClassLoader().getResourceAsStream(XSLFile)));
//            Transformer transformer = factory.newTransformer(new StreamSource(XSLFile));

            // Set the value of a <param> in the stylesheet
            transformer.setParameter("versionParam", "2.0");

            // Setup XML String as input for XSLT transformation
            Source src = new StreamSource(new ByteArrayInputStream(xml.getBytes("UTF-8")));

            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
        } finally {
            out.close();
        }
    }

    public String serialize(Study study, Epoch epoch) throws Exception {
        jaxbContext = JAXBContext.newInstance("gov.nih.nci.cabig.caaers.webservice");
        marshaller = jaxbContext.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        studies = new Studies();
        List<gov.nih.nci.cabig.caaers.webservice.Study> list = new ArrayList();
        gov.nih.nci.cabig.caaers.webservice.Study wsStudy = new gov.nih.nci.cabig.caaers.webservice.Study();

        wsStudy.setShortTitle(study.getShortTitle());
        wsStudy.setLongTitle(study.getLongTitle());
        wsStudy.setId(BigInteger.valueOf(study.getId()));

        gov.nih.nci.cabig.caaers.webservice.Study.Identifiers studyIdentifiers = new gov.nih.nci.cabig.caaers.webservice.Study.Identifiers();
        SystemAssignedIdentifierType i = new SystemAssignedIdentifierType();
        i.setPrimaryIndicator(true);
        i.setValue(study.getPrimaryIdentifier().getValue());
        List<SystemAssignedIdentifierType> iSystemAssigned = new ArrayList<SystemAssignedIdentifierType>();
        iSystemAssigned.add(i);
        studyIdentifiers.setSystemAssignedIdentifier(iSystemAssigned);
        wsStudy.setIdentifiers(studyIdentifiers);

        EvaluationPeriodType ept = new EvaluationPeriodType();
        EvaluationPeriodType.SolicitedAdverseEvents wsSAE = new EvaluationPeriodType.SolicitedAdverseEvents();
        List<SolicitedAdverseEventType> wsSAET = new ArrayList<SolicitedAdverseEventType>();
        if(epoch != null){
        	ept.setName(epoch.getName());
        	ept.setDescriptionText(epoch.getDescriptionText());
        	ept.setSolicitedAdverseEvents(new EvaluationPeriodType.SolicitedAdverseEvents());
        
        	for (SolicitedAdverseEvent domainSAE : epoch.getArms().get(0).getSolicitedAdverseEvents()) {
        		SolicitedAdverseEventType saet = new SolicitedAdverseEventType();
        		if (domainSAE.getCtcterm() != null) saet.setName(domainSAE.getCtcterm().getTerm());
        		if (domainSAE.getLowLevelTerm() != null) saet.setName(domainSAE.getLowLevelTerm().getMeddraTerm());
        		wsSAET.add(saet);
        	}
        }
        wsSAE.setSolicitedAdverseEvent(wsSAET);
        ept.setSolicitedAdverseEvents(wsSAE);

        gov.nih.nci.cabig.caaers.webservice.Study.EvaluationPeriods eps = new gov.nih.nci.cabig.caaers.webservice.Study.EvaluationPeriods();
        List<EvaluationPeriodType> eptl = new ArrayList<EvaluationPeriodType>();
        eptl.add(ept);
        eps.setEvaluationPeriod(eptl);

        wsStudy.setEvaluationPeriods(eps);

        list.add(wsStudy);
        studies.setStudy(list);
        marshaller.marshal(studies, sw);
//        marshaller.marshal(studies, new FileOutputStream(XMLFile));
        return sw.toString();
    }

    public static void testXMLGenaration() {
        Study s;
        Epoch e;

        BlankFormGenerator g;
        g = new BlankFormGenerator();
        s = new Study();
        s.setShortTitle("ST");
        s.setLongTitle("LT");
        s.setId(55588);

        e = new Epoch();
        e.setName("PT");
        e.setDescriptionText("DT");
        e.setId(88);

        SolicitedAdverseEvent sae = new SolicitedAdverseEvent();
        s.addEpoch(e);
        e.addArm(new Arm());
        List<SolicitedAdverseEvent> sael = new ArrayList<SolicitedAdverseEvent>();
        SolicitedAdverseEvent sae1 = new SolicitedAdverseEvent();
        sae1.setCtcterm(new CtcTerm());
        sae1.getCtcterm().setTerm("Nausea");
        sael.add(sae1);

        sae1 = new SolicitedAdverseEvent();
        sae1.setCtcterm(new CtcTerm());
        sae1.getCtcterm().setTerm("Bone Pain");
        sael.add(sae1);

        e.getArms().get(0).setSolicitedAdverseEvents(sael);
        try {
            String xml = g.serialize(s, e);
            FileOutputStream out = new FileOutputStream(XMLFile);
            out.write(xml.getBytes());
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();  
        }
    }

    public static void testPDFgeneration() {
        BlankFormGenerator gen = new BlankFormGenerator();
        StringBuffer s = new StringBuffer("");
        try {
            FileReader input = new FileReader(XMLFile);
            BufferedReader bufRead = new BufferedReader(input);
            String line = bufRead.readLine();

            while (line != null) {
                s.append(line);
                line = bufRead.readLine();
            }

            gen.generatePdf(s.toString(), PDFFile);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // testXMLGenaration();
        // testPDFgeneration();
    }

}