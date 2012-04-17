<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:ser="http://services.ctep.nci.nih.gov/">
    <xsl:include href="adeers_response_common.xsl"/>
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="soapenv:Body">
        <entity>agent</entity>
        <operation name="getAgentsLOV">
           <xsl:apply-templates />
        </operation>
    </xsl:template>
    <xsl:template match="ser:getAgentsLOVResponse">
        <xsl:if test="Agents">
                <xsl:apply-templates  select="Agents"/>
        </xsl:if>
    </xsl:template>
	<xsl:template match="Agents">
		<data>
			<xsl:copy-of select="."/>
		</data>
	</xsl:template>
   
</xsl:stylesheet>