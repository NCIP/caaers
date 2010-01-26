package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.utils.CoreUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/*
* @author Ion C. Olaru
*
* */

public class BasePDFGenerator {
    protected final Log log = LogFactory.getLog(getClass());

/*
* This method generated the PDF based on the given XML & XSL file using FOP framework
* @author   Ion C. Olaru
* @param    xml                 Serialized xml content
* @param    pdfOutFileName      Path for the generated PDF
* @param    XSLFile             Path of the XSL file, see next parameter.
* @param    xslFromClassPath    If true, The XSL file is taken from inside the classpath of the context, otherwise it's taken from the given path  
*
* */
    public synchronized void generatePdf(String xml, String pdfOutFileName, String XSLFile) throws Exception {
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

            Transformer transformer = null;
            transformer = factory.newTransformer(new StreamSource(CoreUtils.findFile(XSLFile)));

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

}