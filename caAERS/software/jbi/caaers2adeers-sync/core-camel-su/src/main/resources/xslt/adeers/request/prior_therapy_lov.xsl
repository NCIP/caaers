<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
  <xsl:include href="adeers_req_common.xsl"  />
  <xsl:template match="operation[@name='getTherapiesLOV']/criteria">
	  <ser:getTherapiesLOV>
	         <TherapiesLOVCriteria>
			   	<xsl:if test="criterion[@name = 'createdDate']">
			   		<createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'lastUpdatedDate']">
			   		<lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
			   	</xsl:if>
                 <xsl:if test="criterion[@name = 'medDRACode']">
                     <medDRACode><xsl:value-of select="criterion[@name = 'medDRACode']"/></medDRACode>
                 </xsl:if>
                 <xsl:if test="criterion[@name = 'status']">
                     <status><xsl:value-of select="criterion[@name = 'status']"/></status>
                 </xsl:if>
                 <xsl:if test="criterion[@name = 'therapyName']">
                     <therapyName><xsl:value-of select="criterion[@name = 'therapyName']"/></therapyName>
                 </xsl:if>
                 <xsl:if test="criterion[@name = 'therapyType']">
                     <therapyType><xsl:value-of select="criterion[@name = 'therapyType']"/></therapyType>
                 </xsl:if>
	   	 	</TherapiesLOVCriteria>
	  </ser:getTherapiesLOV>
  </xsl:template>
  
</xsl:stylesheet>
