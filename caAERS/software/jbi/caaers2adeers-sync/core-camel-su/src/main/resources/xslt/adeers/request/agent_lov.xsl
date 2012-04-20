<xsl:stylesheet
  xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:ser="http://services.ctep.nci.nih.gov/"
  version='1.0'>
  <xsl:include href="adeers_req_common.xsl"  />
  <xsl:template match="operation[@name='getAgentsLOV']/criteria">
	  <ser:getAgentsLOV>
	         <AgentLOVCriteria>
			   	<xsl:if test="criterion[@name = 'nscNumber']">
			   		<nscNumber><xsl:value-of select="criterion[@name = 'nscNumber']"/></nscNumber>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'agentName']">
			   		<agentName><xsl:value-of select="criterion[@name = 'agentName']"/></agentName>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'createdDate']">
			   		<createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'lastUpdatedDate']">
			   		<lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
			   	</xsl:if>
	   	 	</AgentLOVCriteria>
	  </ser:getAgentsLOV>
  </xsl:template>
  
</xsl:stylesheet>
