<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:include href="caaers_response_common.xsl" />
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="soapenv:Body">
        <entity>study</entity>
        <operation name="createStudyResponse">
            <xsl:apply-templates  />
        </operation>
    </xsl:template>

    <xsl:template match="stud:createStudyResponse">
        <xsl:apply-templates />
    </xsl:template>

</xsl:stylesheet>