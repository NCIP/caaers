<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
    <xsl:include href="adeers_req_common.xsl"  />
    <xsl:template match="operation[@name='getMergedOrganization']/criteria">
		<ser:getMergedOrganization>
		   <CTEPId><xsl:value-of select="criterion[@name = 'CTEPId']"/></CTEPId>
		</ser:getMergedOrganization>
    </xsl:template>

</xsl:stylesheet>
