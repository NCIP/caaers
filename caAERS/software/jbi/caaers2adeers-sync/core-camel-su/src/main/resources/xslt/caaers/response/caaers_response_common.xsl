<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:param name="c2a_operation" />
    <xsl:param name="c2a_correlation_id" />
    <xsl:template match="soapenv:Envelope">
        <payload>
            <xsl:attribute name="correlationId"><xsl:value-of select="$c2a_correlation_id" /></xsl:attribute>
            <system>caaers</system>
            <response>
                <xsl:apply-templates select="soapenv:Body" />
            </response>
        </payload>
    </xsl:template>
    <xsl:template match="wsError">
        <error>
            <xsl:value-of select="errorCode"/> : <xsl:value-of select="errorDesc"/>
        </error>
    </xsl:template>
    <xsl:template match="com:entityProcessingOutcome">
        <xsl:copy-of select="." />
    </xsl:template>
    <xsl:template match="com:entityProcessingOutcomes">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="soapenv:Fault">
        <status>error</status>
        <errors>
            <error>
                <xsl:value-of select="faultcode"/> : <xsl:value-of select="faultstring"/>
            </error>
            <xsl:if test="detail/com:SecurityExceptionFault/message">
                <error><xsl:value-of select="detail/com:SecurityExceptionFault/message"/></error>
            </xsl:if>
        </errors>
    </xsl:template>
    <xsl:template match="com:CaaersServiceResponse">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="com:ServiceResponse">
        <xsl:if test="not(status)">
            <status>unknown</status>
        </xsl:if>
        <xsl:if test="wsError">
            <status>error</status>
            <errors>
                <xsl:apply-templates select="wsError" />
            </errors>
        </xsl:if>
        <xsl:if test="com:entityProcessingOutcomes">
            <xsl:if test="status">
                <status><xsl:value-of select="status" /></status>
            </xsl:if>
            <data>
                <xsl:apply-templates select="com:entityProcessingOutcomes"/>
            </data>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>