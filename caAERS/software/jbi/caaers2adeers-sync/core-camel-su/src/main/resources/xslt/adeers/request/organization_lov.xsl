<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
    <xsl:include href="adeers_req_common.xsl"  />
    <xsl:template match="operation[@name='getOrganizationsLOV']/criteria">
        <ser:getOrganizationsLOV>
            <OrgSearchCriteria>
                <xsl:if test="criterion[@name = 'createdDate']">
                    <createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'lastUpdatedDate']">
                    <lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'ctepId']">
                    <ctepId><xsl:value-of select="criterion[@name = 'ctepId']"/></ctepId>
                </xsl:if>
                <xsl:if test="criterion[@name = 'organizationName']">
                    <organizationName><xsl:value-of select="criterion[@name = 'organizationName']"/></organizationName>
                </xsl:if>
                <xsl:if test="criterion[@name = 'status']">
                    <status><xsl:value-of select="criterion[@name = 'status']"/></status>
                </xsl:if>
            </OrgSearchCriteria>
        </ser:getOrganizationsLOV>
    </xsl:template>

</xsl:stylesheet>
