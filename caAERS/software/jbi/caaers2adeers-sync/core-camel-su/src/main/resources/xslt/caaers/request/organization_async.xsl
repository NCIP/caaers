<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        xmlns:org="http://schema.integration.caaers.cabig.nci.nih.gov/organization"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateOrganization>
        <xsl:apply-templates select="Organizations" />
    </com:createOrUpdateOrganization>
  </xsl:template>

  <xsl:template match="Organizations">
	 <org:Organizations>
	 	<xsl:apply-templates  />
	 </org:Organizations>
  </xsl:template>
  
  <xsl:template match="organization">
        <com:organization>
            <xsl:if test="address/street">
                <descriptionText><xsl:value-of select="address/street" /></descriptionText>
            </xsl:if>
            <name><xsl:value-of select="organizationName" /></name>
            <nciInstituteCode><xsl:value-of select="ctepId" /></nciInstituteCode>
            <xsl:if test="address/city">
                <city><xsl:value-of select="address/city" /></city>
            </xsl:if>
            <xsl:if test="address/state">
                <state><xsl:value-of select="address/state" /></state>
            </xsl:if>
            <xsl:if test="address/country">
                <country><xsl:value-of select="address/country" /></country>
            </xsl:if>
            <status><xsl:value-of select="status" /></status>
            <type><xsl:value-of select="organizationType" /></type>
        </com:organization>
  </xsl:template>

</xsl:stylesheet>
