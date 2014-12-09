<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:aer="http://schema.integration.caaers.cabig.nci.nih.gov/aereport"
                xmlns:wsse="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd">
    <xsl:param name="c2a_correlation_id" />
    <xsl:output omit-xml-declaration="yes" indent="yes" />
    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>
    <xsl:template match="aer:report">
        <xsl:copy-of select="./*"/>
        <aer:correlationId><xsl:value-of select="$c2a_correlation_id" /></aer:correlationId>
    </xsl:template>
</xsl:stylesheet>