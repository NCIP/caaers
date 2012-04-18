<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:ser="http://services.ctep.nci.nih.gov/"
        version='1.0'>
    <xsl:include href="adeers_req_common.xsl"  />
    <xsl:template match="operation[@name='searchStudy']/criteria">
        <ser:searchStudy>
            <StudySearchCriteria>
                <xsl:if test="criterion[@name = 'createdDate']">
                    <createdDate><xsl:value-of select="criterion[@name = 'createdDate']"/></createdDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'lastUpdatedDate']">
                    <lastUpdatedDate><xsl:value-of select="criterion[@name = 'lastUpdatedDate']"/></lastUpdatedDate>
                </xsl:if>
                <xsl:if test="criterion[@name = 'documentTitle']">
                    <documentTitle><xsl:value-of select="criterion[@name = 'documentTitle']"/></documentTitle>
                </xsl:if>
                <xsl:if test="criterion[@name = 'localDocumentNumber']">
                    <localDocumentNumber><xsl:value-of select="criterion[@name = 'localDocumentNumber']"/></localDocumentNumber>
                </xsl:if>
                <xsl:if test="criterion[@name = 'nciDocumentNumber']">
                    <nciDocumentNumber><xsl:value-of select="criterion[@name = 'nciDocumentNumber']"/></nciDocumentNumber>
                </xsl:if>
            </StudySearchCriteria>
        </ser:searchStudy>
    </xsl:template>
</xsl:stylesheet>