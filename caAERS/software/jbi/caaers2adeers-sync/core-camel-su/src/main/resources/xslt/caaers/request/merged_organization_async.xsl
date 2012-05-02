<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        xmlns:org="http://schema.integration.caaers.cabig.nci.nih.gov/organization"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:mergeOrganization>
        <xsl:apply-templates select="MergedOrganization" />
    </com:mergeOrganization>
  </xsl:template>

  <xsl:template match="MergedOrganization">
	 <org:Organizations>
	 	<com:organization>
	 		<xsl:if test="name != ''">
                <name><xsl:value-of select="name" /></name>
            </xsl:if>
            <xsl:if test="ctepId != ''">
                <nciInstituteCode><xsl:value-of select="nctepIdame" /></nciInstituteCode>
            </xsl:if>
            <mergedOrganization>
				<xsl:if test="mergedTo/address/street != ''">
	                <descriptionText><xsl:value-of select="mergedTo/address/street" /></descriptionText>
	            </xsl:if>
	            <name><xsl:value-of select="mergedTo/organizationName" /></name>
	            <nciInstituteCode><xsl:value-of select="mergedTo/ctepId" /></nciInstituteCode>
	            <xsl:if test="mergedTo/address/city != ''">
	                <city><xsl:value-of select="mergedTo/address/city"/></city>
	            </xsl:if>
	            <xsl:if test="mergedTo/address/state !=''">
	                <state><xsl:value-of select="mergedTo/address/state"/></state>
	            </xsl:if>
	            <xsl:if test="mergedTo/address/country !=''">
	                <country><xsl:value-of select="mergedTo/address/country" /></country>
	            </xsl:if>
	            <status><xsl:value-of select="mergedTo/status" /></status>
	            <type><xsl:value-of select="mergedTo/organizationType" /></type>
            </mergedOrganization>
        </com:organization>
	 </org:Organizations>
  </xsl:template>

</xsl:stylesheet>
