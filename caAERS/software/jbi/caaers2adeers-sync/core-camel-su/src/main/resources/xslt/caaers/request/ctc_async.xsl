<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        xmlns:ctc="http://schema.integration.caaers.cabig.nci.nih.gov/ctc"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateCtcs>
        <xsl:apply-templates select="CTCAELOV" />
    </com:createOrUpdateCtcs>
  </xsl:template>

  <xsl:template match="CTCAELOV">
      <ctc:Ctcs>
      	<ctc>
      		<name><xsl:value-of select="@version"/></name>
      		<xsl:apply-templates select="ctcaeCategory"/>
	 	</ctc>
      </ctc:Ctcs>
  </xsl:template>
  
  <xsl:template match="ctcaeCategory">
  	<ctcCategory>
       <name><xsl:value-of select="@category"/></name>
       <xsl:apply-templates select="ctcadverseEvent"/>
    </ctcCategory>
  </xsl:template>
  
  <xsl:template match="ctcadverseEvent">
  	<ctcTerm>
       <ctepCode><xsl:value-of select="medDRACode"/></ctepCode>
       <ctepTerm><xsl:value-of select="@aeTerm"/></ctepTerm>
       <term><xsl:value-of select="@aeTerm"/></term>
       <xsl:apply-templates select="ctcGrade"/>
    </ctcTerm>
  </xsl:template>
  
  <xsl:template match="ctcGrade">
  	<ctcGrade>
       <text><xsl:value-of select="gradeDescription"/></text>
       <grade><xsl:value-of select="@grade"/></grade>
    </ctcGrade>
  </xsl:template>

</xsl:stylesheet>
