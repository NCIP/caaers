<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
    <xsl:include href="adeers_req_common.xsl"  />
    <xsl:template match="operation[@name='getCTCAELOV']/criteria">
        <ser:getCTCAELOV>
            <CTCAELOVCriteria>
                <xsl:if test="criterion[@name = 'createdDate']">
                    <createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'lastUpdatedDate']">
                    <lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'aeTerm']">
                    <aeTerm><xsl:value-of select="criterion[@name = 'aeTerm']"/></aeTerm>
                </xsl:if>
                <xsl:if test="criterion[@name = 'ctcAEVersion']">
                    <ctcAEVersion><xsl:value-of select="criterion[@name = 'ctcAEVersion']"/></ctcAEVersion>
                </xsl:if>
            </CTCAELOVCriteria>
        </ser:getCTCAELOV>
    </xsl:template>

</xsl:stylesheet>
