<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
  <xsl:include href="adeers_req_common.xsl"  />
  <xsl:template match="operation[@name='getLabsLOV']/criteria">
	  <ser:getLabsLOV>
	         <LabsLOVCriteria>
			   	<xsl:if test="criterion[@name = 'createdDate']">
			   		<createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'lastUpdatedDate']">
			   		<lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
			   	</xsl:if>
                 <xsl:if test="criterion[@name = 'labCategory']">
                     <labCategory><xsl:value-of select="criterion[@name = 'labCategory']"/></labCategory>
                 </xsl:if>
                  <xsl:if test="criterion[@name = 'labName']">
                     <labName><xsl:value-of select="criterion[@name = 'labName']"/></labName>
                 </xsl:if>
	   	 	</LabsLOVCriteria>
	  </ser:getLabsLOV>
  </xsl:template>
  
</xsl:stylesheet>
