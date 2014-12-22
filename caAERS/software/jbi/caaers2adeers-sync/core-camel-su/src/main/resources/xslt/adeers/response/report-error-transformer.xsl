<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:param name="c2a_submission_error_code" />
    <xsl:param name="c2a_submission_error_msg" />
    <xsl:param name="c2a_submission_error_details" />
    <xsl:param name="c2a_ae_report_id" />
    <xsl:param name="c2a_caaersr_id" />
    <xsl:param name="c2a_submitter_email" />
    <xsl:param name="c2a_message_combo_id" />
    <xsl:param name="c2a_correlation_id" />
    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
            <soapenv:Body>
                <submitAEDataXMLAsAttachmentResponse xmlns="http://types.ws.adeers.ctep.nci.nih.gov">
                    <ns1:AEReportJobInfo xmlns:ns1="http://localhost:8080/AdEERSWSMap/services/AEReportXMLService">
                        <jobExceptions xmlns="">
                            <code><xsl:value-of select="$c2a_submission_error_code" /></code>
                            <description><xsl:value-of select="$c2a_submission_error_msg" /></description>
                        </jobExceptions>
                        <reportStatus xmlns="">ERROR</reportStatus>
                        <comments xmlns=""><xsl:value-of select="$c2a_submission_error_details" /></comments>
                        <CAEERS_AEREPORT_ID><xsl:value-of select="$c2a_ae_report_id" /></CAEERS_AEREPORT_ID>
                        <CAAERSRID><xsl:value-of select="$c2a_caaersr_id" /></CAAERSRID>
                        <SUBMITTER_EMAIL><xsl:value-of select="$c2a_submitter_email"  /></SUBMITTER_EMAIL>
                        <MESSAGE_COMBO_ID><xsl:value-of select="$c2a_message_combo_id" /></MESSAGE_COMBO_ID>
                        <CORRELATION_ID><xsl:value-of select="$c2a_correlation_id"/></CORRELATION_ID>
                    </ns1:AEReportJobInfo>
                </submitAEDataXMLAsAttachmentResponse>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>