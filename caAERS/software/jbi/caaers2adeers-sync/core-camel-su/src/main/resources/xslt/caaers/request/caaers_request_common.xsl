<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        version='1.0'>

    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
        <soapenv:Envelope>
            <soapenv:Body>
                <xsl:apply-templates select="payload/response/operation/data" />
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>

</xsl:stylesheet>