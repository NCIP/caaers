<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:ser="http://services.ctep.nci.nih.gov/">
    <xsl:include href="adeers_response_common.xsl"/>
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="soapenv:Body">
        <entity>mergedorganization</entity>
        <operation name="getMergedOrganization">
           <xsl:apply-templates />
        </operation>
    </xsl:template>
    <xsl:template match="ser:getMergedOrganizationResponse">
        <xsl:if test="MergedOrganization">
                <xsl:apply-templates  select="MergedOrganization"/>
        </xsl:if>
    </xsl:template>
	<xsl:template match="MergedOrganization">
		<data>
			<xsl:copy-of select="."/>
		</data>
	</xsl:template>
   
</xsl:stylesheet>