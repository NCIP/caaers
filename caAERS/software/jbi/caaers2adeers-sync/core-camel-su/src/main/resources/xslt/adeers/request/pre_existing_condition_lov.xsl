<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
  <xsl:include href="adeers_req_common.xsl"  />
  <xsl:template match="operation[@name='getPreExistingConditionsLOV']/criteria">
	  <ser:getPreExistingConditionsLOV>
	         <PreExistingConditionLOVCriteria>
			   	<xsl:if test="criterion[@name = 'createdDate']">
			   		<createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
			   	</xsl:if>
			   	<xsl:if test="criterion[@name = 'lastUpdatedDate']">
			   		<lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
			   	</xsl:if>
                 <xsl:if test="criterion[@name = 'preExistingConditionName']">
                     <preExistingConditionName><xsl:value-of select="criterion[@name = 'preExistingConditionName']"/></preExistingConditionName>
                 </xsl:if>
	   	 	</PreExistingConditionLOVCriteria>
	  </ser:getPreExistingConditionsLOV>
  </xsl:template>
  
</xsl:stylesheet>
