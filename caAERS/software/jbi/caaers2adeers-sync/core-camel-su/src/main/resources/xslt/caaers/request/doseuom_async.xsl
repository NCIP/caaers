<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateConfigProperties>
        <xsl:apply-templates select="AgentDoseUOM" />
    </com:createOrUpdateConfigProperties>
  </xsl:template>

  <xsl:template match="AgentDoseUOM">
	 <com:ConfigProperties>
        <name>AGENT_UOM</name>
	 	<xsl:apply-templates  />
	 </com:ConfigProperties>
  </xsl:template>
  
  <xsl:template match="agentDoseUOM">
        <com:ConfigProperty>
            <code><xsl:value-of select="unitOfMeasure"/></code>
            <name><xsl:value-of select="unitOfMeasure"/></name>
            <description><xsl:value-of select="unitOfMeasure"/></description>
        </com:ConfigProperty>
  </xsl:template>

</xsl:stylesheet>
