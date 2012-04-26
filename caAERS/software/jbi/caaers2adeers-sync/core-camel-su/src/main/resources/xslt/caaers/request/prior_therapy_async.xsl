<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdatePriorTherapy>
        <xsl:apply-templates select="Therapies" />
    </com:createOrUpdatePriorTherapy>
  </xsl:template>

  <xsl:template match="Therapies">
      <com:PriorTherapies>
	 	<xsl:apply-templates  />
      </com:PriorTherapies>

  </xsl:template>
  
  <xsl:template match="therapies">
        <xsl:if test="status/text() = 'Active'">
            <com:priorTherapy>
                <text><xsl:value-of select="therapyName"/></text>
                <meddraCode><xsl:value-of select="medDRACode"/></meddraCode>
                <meddraTerm><xsl:value-of select="category"/></meddraTerm>
                <therapyType><xsl:value-of select="therapyType"/></therapyType>
            </com:priorTherapy>
        </xsl:if>
  </xsl:template>

</xsl:stylesheet>
