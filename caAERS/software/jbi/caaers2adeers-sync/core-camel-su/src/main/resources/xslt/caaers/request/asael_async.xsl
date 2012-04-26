<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
        xmlns:asa="http://schema.integration.caaers.cabig.nci.nih.gov/asael"
        version='1.0'>
    <xsl:include href="caaers_request_common.xsl" />
    <xsl:variable name="map" select="document('lookup.xml')"/>
    <xsl:template match="data">
        <com:createOrUpdateASAEL>
            <xsl:apply-templates select="ASAEL" />
        </com:createOrUpdateASAEL>
    </xsl:template>

    <xsl:template match="ASAEL">
        <asa:Asael>
            <xsl:apply-templates select="agent"  />
        </asa:Asael>
    </xsl:template>

    <xsl:template match="agent">
        <asaelAgent>
            <com:agent>
                <name><xsl:value-of select="name"/></name>
                <nscNumber><xsl:value-of select="nsc"/></nscNumber>
            </com:agent>
            <xsl:apply-templates select="adverseEventList/adverseEvent" />
            <status>Active</status>
        </asaelAgent>
    </xsl:template>
    <xsl:template match="adverseEvent">
        <asa:expectedAECtcTerm>
            <ctepTerm><xsl:value-of select="aeTerm"/></ctepTerm>
            <category><xsl:value-of select="category"/></category>
            <xsl:variable name="_ctcVer" select="ctcVersion" />
            <ctcVersion>
                <xsl:call-template name="lookup"><xsl:with-param name="_map" select="$map//ctcversions" /><xsl:with-param name="_code" select="$_ctcVer" /></xsl:call-template>
            </ctcVersion>
            <status><xsl:value-of select="status"/></status>
        </asa:expectedAECtcTerm>

    </xsl:template>


    <xsl:template name="lookup">
        <xsl:param name="_map" />
        <xsl:param name="_code" />
        <xsl:value-of select="$_map//code[text() = $_code]/parent::node()/value"/>
    </xsl:template>

</xsl:stylesheet>
