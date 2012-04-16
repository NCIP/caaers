<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:ser="http://services.ctep.nci.nih.gov/">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <payload>
            <system>adeers</system>
            <response>
                <xsl:apply-templates  />
            </response>
        </payload>
    </xsl:template>

    <xsl:template match="soapenv:Fault">
        <errors>
            <error>
                <xsl:value-of select="faultcode"/> : <xsl:value-of select="faultstring"/>
            </error>
        </errors>
    </xsl:template>
</xsl:stylesheet>