<xsl:stylesheet version="1.0" xmlns:stud="http://schema.integration.caaers.cabig.nci.nih.gov/study"
                xmlns:com="http://schema.integration.caaers.cabig.nci.nih.gov/common"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output indent="no" omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <xsl:apply-templates select="//operation/data" />
    </xsl:template>
    <xsl:template match="data">
       <xsl:apply-templates select="stud:studies" />
       <xsl:apply-templates select="com:entityProcessingOutcome" />
    </xsl:template>
    <xsl:template match="stud:studies">
         <xsl:copy-of select="." />
    </xsl:template>
    <xsl:template match="com:entityProcessingOutcome">
        <xsl:value-of select="dataBaseId" />
    </xsl:template>
</xsl:stylesheet>