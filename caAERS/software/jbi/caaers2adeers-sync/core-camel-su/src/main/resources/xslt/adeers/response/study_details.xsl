<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                xmlns:ser="http://services.ctep.nci.nih.gov/">
    <xsl:include href="adeers_response_common.xsl"/>
    <xsl:param name="c2a_operation" />
    <xsl:template match="/">
        <xsl:apply-templates />
    </xsl:template>
    <xsl:template match="soapenv:Body">
        <entity>study</entity>
        <operation>
            <xsl:attribute name="name"><xsl:value-of select="$c2a_operation"/></xsl:attribute>
            <xsl:apply-templates />
        </operation>
    </xsl:template>
    <xsl:template match="ser:getStudyDetailsResponse">
        <xsl:if test="StudyDetails">
            <xsl:apply-templates  select="StudyDetails"/>
        </xsl:if>
    </xsl:template>
    <xsl:template match="StudyDetails">
        <data>
            <xsl:copy-of select="."/>
        </data>
    </xsl:template>
</xsl:stylesheet>