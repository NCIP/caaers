<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
            <soap:Body>
                <soap:Fault>
                    <faultcode>soap:Invalid</faultcode>
                    <faultstring>Unexpected error during message processing</faultstring>
                    <detail><xsl:value-of select="." /></detail>
                </soap:Fault>
            </soap:Body>
        </soap:Envelope>
    </xsl:template>

</xsl:stylesheet>