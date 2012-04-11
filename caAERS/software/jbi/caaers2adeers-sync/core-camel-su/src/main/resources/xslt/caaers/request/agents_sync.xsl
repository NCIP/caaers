<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
  xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
  xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
  xmlns:ws="http://ws.caaers.cabig.nci.nih.gov/"
  xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
  xmlns:com1="http://webservice.caaers.cabig.nci.nih.gov"
  xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
  version='1.0'>

  <xsl:output method="xml" indent="yes" encoding="ISO-8859-1"/>

  <xsl:template match="/">
	<soapenv:Envelope>
	    <soapenv:Header>
            <!-- Will get the username and password form parameters -->
	      <wsse:Security>
	         <wsse:UsernameToken>
	            <wsse:Username>SYSTEM</wsse:Username>
	            <wsse:Password>Hello-12</wsse:Password>
	         </wsse:UsernameToken>
	      </wsse:Security>
	   	</soapenv:Header>
   		<soapenv:Body>
         	<xsl:apply-templates select="payload/response/operation/data" />
   		</soapenv:Body>
   </soapenv:Envelope>
  </xsl:template>
  
  
  <xsl:template match="Agents">
	 <com:Agents>
	 	<xsl:apply-templates  />
	 </com:Agents>
  </xsl:template>
  
  <xsl:template match="agent">
        <com1:agent>
            <name><xsl:value-of select="agentName"/></name>
            <descriptionText><xsl:value-of select="agentName"/></descriptionText>
            <nscNumber><xsl:value-of select="nscNumber"/></nscNumber>
            <status><xsl:value-of select="agentStatus"/></status>
        </com1:agent>
  </xsl:template>
</xsl:stylesheet>
