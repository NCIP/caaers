<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:param name="c2a_operation" />
    <xsl:param name="c2a_correlation_id" />

    <xsl:template match="/">
        <payload>
            <xsl:attribute name="correlationId"><xsl:value-of select="$c2a_correlation_id" /></xsl:attribute>
            <system>caaers</system>
            <response>
                <operation>
                    <xsl:attribute name="name"><xsl:value-of select="$c2a_operation" /></xsl:attribute>
                    <xsl:apply-templates select="soapenv:Envelope/soapenv:Body/soapenv:Fault" />
                    <xsl:apply-templates select="payload/response/operation/errors" />
                </operation>
            </response>
        </payload>
    </xsl:template>
    <xsl:template match="soapenv:Fault">
        <errors>
            <error>
                <xsl:value-of select="faultcode"/> : <xsl:value-of select="faultstring"/>
            </error>
            <xsl:if test="detail/com:SecurityExceptionFault/message">
                <error><xsl:value-of select="detail/com:SecurityExceptionFault/message"/></error>
            </xsl:if>
        </errors>
    </xsl:template>
    <xsl:template match="errors">
        <xsl:copy-of select="."/>
    </xsl:template>

</xsl:stylesheet>