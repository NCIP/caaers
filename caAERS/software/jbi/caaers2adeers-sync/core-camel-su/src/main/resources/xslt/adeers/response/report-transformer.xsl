<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns1="http://localhost:8080/AdEERSWSMap/services/AEReportXMLService"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:param name="c2a_ae_report_id" />
    <xsl:param name="c2a_caaersr_id" />
    <xsl:param name="c2a_submitter_email" />
    <xsl:param name="c2a_message_combo_id" />
    <xsl:param name="c2a_correlation_id" />


    <xsl:template match="//ns1:AEReportCancelInfo|//ns1:AEReportJobInfo">
        <xsl:copy>
            <xsl:call-template name="copy-children"/>
            <xsl:element name="CAEERS_AEREPORT_ID">
                <xsl:value-of select="$c2a_ae_report_id" />
            </xsl:element>
            <xsl:element name="CAAERSRID">
                <xsl:value-of select="$c2a_caaersr_id" />
            </xsl:element>
            <xsl:element name="SUBMITTER_EMAIL">
                <xsl:value-of select="$c2a_submitter_email" />
            </xsl:element>
            <xsl:element name="MESSAGE_COMBO_ID">
                <xsl:value-of select="$c2a_message_combo_id" />
            </xsl:element>
            <xsl:element name="CORRELATION_ID">
                <xsl:value-of select="$c2a_correlation_id" />
            </xsl:element>
        </xsl:copy>
    </xsl:template>

    <xsl:template name="copy-children">
        <xsl:copy-of select="./*"/>
    </xsl:template>

    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="@*"/>
            <xsl:apply-templates/>
        </xsl:copy>
    </xsl:template>
</xsl:stylesheet>