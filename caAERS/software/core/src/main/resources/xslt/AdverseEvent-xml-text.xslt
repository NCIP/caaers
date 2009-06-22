<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text"/>

<xsl:template match="/">
	<xsl:apply-templates/>
</xsl:template>

<xsl:template match="AdverseEvent">
	<xsl:value-of select="AdverseEventCtcTerm/universal-term"/> 
	<xsl:text disable-output-escaping="yes">&#009;</xsl:text> <xsl:value-of select="grade"/> 
	<xsl:text disable-output-escaping="yes">&#009;</xsl:text> <xsl:value-of select="hospitalization"/>
	<xsl:text disable-output-escaping="yes">&#009;</xsl:text> <xsl:value-of select="expected"/>
	<xsl:text disable-output-escaping="yes">&#009;</xsl:text> <xsl:value-of select="startDate"/>
	<xsl:text disable-output-escaping="yes">&#009;</xsl:text> <xsl:value-of select="endDate"/>
	<xsl:text>&#10;</xsl:text>
</xsl:template>

</xsl:stylesheet>
