<xsl:stylesheet
  xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
  version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateAgent>
        <xsl:apply-templates select="Agents" />
    </com:createOrUpdateAgent>
  </xsl:template>

  <xsl:template match="Agents">
	 <com:Agents>
	 	<xsl:apply-templates  />
	 </com:Agents>
  </xsl:template>
  
  <xsl:template match="agent">
        <com:agent>
            <name><xsl:value-of select="agentName"/></name>
            <descriptionText><xsl:value-of select="agentName"/></descriptionText>
            <nscNumber><xsl:value-of select="nscNumber"/></nscNumber>
            <status><xsl:value-of select="agentStatus"/></status>
        </com:agent>
  </xsl:template>

</xsl:stylesheet>
