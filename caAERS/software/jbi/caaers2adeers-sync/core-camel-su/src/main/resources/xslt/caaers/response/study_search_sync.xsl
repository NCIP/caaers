<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study">

    <xsl:template match="/">
        <xsl:apply-templates select="/payload/response/operation/data" />
    </xsl:template>
    <xsl:template match="data">
        <payload>
        <system>caaers</system>
        <response>
            <operation name="searchStudy">
                <xsl:apply-templates />
            </operation>
        </response>
        </payload>
    </xsl:template>
    <xsl:template match="Documents">
        <data>
            <stud:studies>
                <xsl:apply-templates select="document" />
            </stud:studies>
        </data>
    </xsl:template>
    <xsl:template match="document">
        <stud:study>
            <shortTitle><xsl:value-of select="documentTitle" /></shortTitle>
            <fundingSponsor>
                <organizationAssignedIdentifier>
                    <value><xsl:value-of select="nciDocumentNumber" /></value>
                </organizationAssignedIdentifier>
                <stud:studyFundingSponsor>
                    <stud:organization>
                        <name><xsl:value-of select="sponsorOrganization"/></name>
                        <nciInstituteCode><xsl:value-of select="sponsorOrganization"/></nciInstituteCode>
                    </stud:organization>
                </stud:studyFundingSponsor>
            </fundingSponsor>
        </stud:study>
    </xsl:template>
</xsl:stylesheet>