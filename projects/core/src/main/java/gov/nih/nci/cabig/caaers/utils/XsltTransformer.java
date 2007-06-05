package gov.nih.nci.cabig.caaers.utils;



import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FormattingResults;
import org.apache.fop.apps.MimeConstants;
import org.apache.fop.apps.PageSequenceResults;

public class XsltTransformer {
		
	/**
	 * Transforms XML to XML using provided XSLT file
	 * @param inXml
	 * @param xsltFile
	 * @return
	 * @throws Exception
	 */
		public String toXml(String inXml,String xsltFile)  throws Exception{
			Source xmlSource = new StreamSource(new ByteArrayInputStream(inXml.getBytes()));
			File xslt = new File(xsltFile);
			
			Source xsltSource = new StreamSource(xslt);

			StringWriter outStr = new StringWriter();
			StreamResult result = new StreamResult(outStr);
			
	        // the factory pattern supports different XSLT processors
	        TransformerFactory transFact = TransformerFactory.newInstance();
	        Transformer trans = transFact.newTransformer(xsltSource);

	        trans.transform(xmlSource, result);//transform(xmlSource, new StreamResult(System.out));
			
			return outStr.toString();
		}
		
		/**
		 * Transforms XML to PDF using provided XSL-FO XSLT  file.
		 * This is a 2 step process , first generate .fo from getFO method 
		 * and then generate PDF .
		 * @param inXml
		 * @param outPdfFile
		 * @param xsltFile
		 * @throws Exception
		 */
		public void toPdf(String inXml,String outPdfFile, String xsltFile) throws Exception{
		
			String fo = getFO(inXml,xsltFile);
			//System.out.println(fo);
			
			FopFactory fopFactory = FopFactory.newInstance();
			
			  OutputStream out = null;
		        
		        try {
		            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
		            // configure foUserAgent as desired
		    
		            // Setup output stream.  Note: Using BufferedOutputStream
		            // for performance reasons (helpful with FileOutputStreams).
		            out = new FileOutputStream(outPdfFile);
		            out = new BufferedOutputStream(out);

		            // Construct fop with desired output format
		            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

		            // Setup JAXP using identity transformer
		            TransformerFactory factory = TransformerFactory.newInstance();
		            Transformer transformer = factory.newTransformer(); // identity transformer
		            
		            // Setup input stream
		            Source src = new StreamSource(new ByteArrayInputStream(fo.getBytes()));

		            // Resulting SAX events (the generated FO) must be piped through to FOP
		            Result res = new SAXResult(fop.getDefaultHandler());
		            
		            // Start XSLT transformation and FOP processing
		            transformer.transform(src, res);
		            
		            // Result processing
		            FormattingResults foResults = fop.getResults();
		            java.util.List pageSequences = foResults.getPageSequences();
		            for (java.util.Iterator it = pageSequences.iterator(); it.hasNext();) {
		                PageSequenceResults pageSequenceResults = (PageSequenceResults)it.next();
		                System.out.println("PageSequence " 
		                        + (String.valueOf(pageSequenceResults.getID()).length() > 0 
		                                ? pageSequenceResults.getID() : "<no id>") 
		                        + " generated " + pageSequenceResults.getPageCount() + " pages.");
		            }
		            System.out.println("Generated " + foResults.getPageCount() + " pages in total.");

		        } catch (Exception e) {		        	
		            throw new Exception (e);		            
		        } finally {
		            out.close();
		        }
		}
		
		/**
		 * Generates FO document ..
		 * @param inXml
		 * @param xsltFile
		 * @return
		 * @throws Exception
		 */
		private String getFO (String inXml, String xsltFile)  throws Exception{
        	File xslt = new File(xsltFile);
        	
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
        
            //Setup input for XSLT transformation
            Source src = new StreamSource(new ByteArrayInputStream(inXml.getBytes()));
        
            //Resulting SAX events (the generated FO) must be piped through to FOP
            StringWriter outStr = new StringWriter();
            Result res = new StreamResult(outStr);

            //Start XSLT transformation and FOP processing
            transformer.transform(src, res);
            //FO
            return outStr.toString();
            
            //FO to PDF 
		}
		


}
