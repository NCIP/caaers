package gov.nih.nci.cabig.caaers.api;

import gov.nih.nci.cabig.caaers.utils.XsltTransformer;



public class AdeersReportGenerator {

	//TO-DO set in spring config 
	private String xmlXsltFile = "xslt/Caaers2Adeers-xml-AEReport.xslt";
	private String xslFOXsltFile = "xslt/Caaers2Adeers-pdf-AEReport.xslt";
	private String pdfOutFile = "xslt/Caaers2Adeers-pdf-AEReport.xslt";
	
	
	public AdeersReportGenerator () { };
	   
	public String getAdeersXml(String  adverseEventReportXml) throws Exception{
		XsltTransformer xsltTrans = new XsltTransformer();
		String transformedToAdeers = xsltTrans.toXml(adverseEventReportXml , xmlXsltFile);
	
		return transformedToAdeers;
	}

	public void genatePdf(String  adverseEventReportXml) throws Exception{
		
		XsltTransformer xsltTrans = new XsltTransformer();
		xsltTrans.toPdf(adverseEventReportXml, pdfOutFile, xslFOXsltFile);
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
