<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        version='1.0'>


    <xsl:template match="/">
        <soapenv:Envelope>
            <soapenv:Header xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
                <wsse:Security>
                    <wsse:UsernameToken>
                        <wsse:Username>SYSTEM_ADMIN</wsse:Username>
                        <wsse:Password>Hello-12</wsse:Password>
                    </wsse:UsernameToken>
                </wsse:Security>
            </soapenv:Header>
            <soapenv:Body>
                <xsl:apply-templates select="payload/response/operation/data" />
            </soapenv:Body>
        </soapenv:Envelope>
    </xsl:template>

</xsl:stylesheet>