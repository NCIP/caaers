<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:output method="xml" indent="yes"/>
  <xsl:template match="/">
    <tns:GetPersonInput xmlns:tns="http://servicemix.apache.org/samples/wsdl-first/types">
          <tns:personId><xsl:apply-templates/></tns:personId>
    </tns:GetPersonInput>
  </xsl:template>
  <xsl:template match="/check-protocol">
  	<xsl:if test="Patient/Name">
  		<xsl:apply-templates select="Patient/Name"/>	
  	</xsl:if>
  	<xsl:if test="not(Patient/Name)">
  		lung-cancer
  	</xsl:if>
  </xsl:template>

</xsl:stylesheet>