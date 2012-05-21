<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateLabs>
        <xsl:apply-templates select="LabCategories" />
    </com:createOrUpdateLabs>
  </xsl:template>

  <xsl:template match="LabCategories">
	 <com:labCategories>
	 	<xsl:apply-templates  />
	 </com:labCategories>
  </xsl:template>
  
  <xsl:template match="labCategory">
	 <com:labCategory>
	 	<category><xsl:value-of select="category"/></category>
	 	<com:labs>
	 		<xsl:apply-templates />
	 	</com:labs>
	 </com:labCategory>
  </xsl:template>
  
  <xsl:template match="lab">
        <com:lab>
            <term><xsl:value-of select="labName"/></term>
        </com:lab>
  </xsl:template>

</xsl:stylesheet>
