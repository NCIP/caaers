<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdatePreExistingCondition>
        <xsl:apply-templates select="PreExistingConditions" />
    </com:createOrUpdatePreExistingCondition>
  </xsl:template>

  <xsl:template match="PreExistingConditions">
	 <com:PreExistingConditions>
	 	<xsl:apply-templates  />
	 </com:PreExistingConditions>
  </xsl:template>
  
  <xsl:template match="existingConditions">
        <com:preExistingCondition>
            <text><xsl:value-of select="conditionName"/></text>
            <meddraLltCode><xsl:value-of select="medDRACode" /></meddraLltCode>
            <meddraLlt><xsl:value-of select="categoryDescription" /></meddraLlt>
        </com:preExistingCondition>
  </xsl:template>

</xsl:stylesheet>
