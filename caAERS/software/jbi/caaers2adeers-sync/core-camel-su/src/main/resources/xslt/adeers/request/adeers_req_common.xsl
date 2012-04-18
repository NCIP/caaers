<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        version='1.0'>
    <xsl:template match="/">
        <soapenv:Envelope>
            <soapenv:Header/>
            <soapenv:Body>
                <xsl:apply-templates select="/payload/request/operation"/>
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>
</xsl:stylesheet>