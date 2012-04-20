<xsl:stylesheet
        xmlns:xsl='http://www.w3.org/1999/XSL/Transform'
        xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
        xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study"
        xmlns:s="http://schema.integration.caaers.cabig.nci.nih.gov/lookup"
        version='1.0'>
    <xsl:include href="caaers_request_common.xsl" />

    <xsl:variable name="lk" select="document('')//s:phases"/>
    <xsl:template match="data">
        <stud:updateStudy>
            <xsl:apply-templates select="StudyDetails" />
        </stud:updateStudy>
    </xsl:template>
    <xsl:template match="StudyDetails">
        <stud:studies>
            <stud:study>
               <shortTitle><xsl:value-of select="title" /></shortTitle>
                <x><xsl:value-of select="$lk/s:phases/s:phase[@code='III']" /></x>
            </stud:study>
        </stud:studies>
    </xsl:template>
    <s:lookup>
        <s:phases>
            <s:phase code='III'>Phase 3</s:phase>
        </s:phases>
    </s:lookup>
</xsl:stylesheet>