<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <xsl:include href="caaers_response_common.xsl" />
    <xsl:template match="soapenv:Body">
        <entity>organization</entity>
        <operation name="createOrUpdateOrganization">
            <xsl:apply-templates  />
        </operation>
    </xsl:template>

    <xsl:template match="com:createOrUpdateOrganizationResponse">
        <xsl:apply-templates />
    </xsl:template>

</xsl:stylesheet>