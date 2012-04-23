<?xml version="1.0"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0">
    <xsl:output method="text"/>

    <!-- A -->
    <xsl:key name="state-lookup" match="state" use="abbr"/>

    <!-- B -->
    <xsl:variable name="states-top" select="document('test2.xml')//states"/>
    <xsl:template match="label">
        
        <xsl:value-of select="name"/>
        <xsl:text> of </xsl:text>
        <xsl:variable name="_jj">ID</xsl:variable>
        biju [<xsl:value-of select="$states-top//abbr[text() = $_jj]/../name" /> ]
        <!-- C -->
        <xsl:apply-templates select="$states-top">
            <xsl:with-param name="curr-label" select="."/>
        </xsl:apply-templates>
    </xsl:template>

    <!-- D -->
    <xsl:template match="states">
        <xsl:param name="curr-label"/>
        <xsl:value-of select="key('state-lookup', $curr-label/address/state)/name"/>  xx
    </xsl:template>
</xsl:stylesheet>