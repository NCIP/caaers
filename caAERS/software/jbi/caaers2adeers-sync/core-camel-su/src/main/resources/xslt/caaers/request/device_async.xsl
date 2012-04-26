<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        xmlns:dev="http://schema.integration.caaers.cabig.nci.nih.gov/device"
        version='1.0'>
  <xsl:include href="caaers_request_common.xsl" />
  <xsl:template match="data">
    <com:createOrUpdateDevices>
        <xsl:apply-templates select="Devices" />
    </com:createOrUpdateDevices>
  </xsl:template>

  <xsl:template match="Devices">
      <dev:Devices>
	 	<xsl:apply-templates  />
      </dev:Devices>

  </xsl:template>
  
  <xsl:template match="device">
        <device>
            <commonName><xsl:value-of select="commonName"/></commonName>
            <brandName><xsl:value-of select="brandName"/></brandName>
            <type><xsl:value-of select="category"/></type>
            <ctepDbIdentifier><xsl:value-of select="deviceDbId"/></ctepDbIdentifier>
            <xsl:if test="status">
                <status><xsl:value-of select="status"/></status>
            </xsl:if>
        </device>
  </xsl:template>

</xsl:stylesheet>
