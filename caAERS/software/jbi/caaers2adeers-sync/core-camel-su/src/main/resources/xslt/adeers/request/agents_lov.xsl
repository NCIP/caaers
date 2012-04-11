<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
  xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
  xmlns:ser="http://services.ctep.nci.nih.gov/"
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  version='1.0'>
  <xsl:output method="xml" indent="yes" encoding="ISO-8859-1"/>

  <xsl:template match="/">
    <soapenv:Envelope>
   	<soapenv:Header/>
   	<soapenv:Body>
      
         	<xsl:apply-templates/>
        
      </soapenv:Body>
    </soapenv:Envelope>
  </xsl:template>
  
  
  <xsl:template match="//operation[@name='getAgentsLOV']/criteria">
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
