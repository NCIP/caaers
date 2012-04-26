<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
  <xsl:include href="adeers_req_common.xsl"  />
  <xsl:template match="operation[@name='getDevicesLOV']/criteria">
	  <ser:getDevicesLOV>
	         <DevicesLOVCriteria>
			   	<xsl:if test="criterion[@name = 'createdDate']">
			   		<createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'lastUpdatedDate']">
			   		<lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
			   	</xsl:if>
                 <xsl:if test="criterion[@name = 'deviceName']">
                     <deviceName><xsl:value-of select="criterion[@name = 'deviceName']"/></deviceName>
                 </xsl:if>
                 <xsl:if test="criterion[@name = 'status']">
                     <status><xsl:value-of select="criterion[@name = 'status']"/></status>
                 </xsl:if>
	   	 	</DevicesLOVCriteria>
	  </ser:getDevicesLOV>
  </xsl:template>
  
</xsl:stylesheet>
