<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes" indent="yes" />

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!--More specific template for Node766 that provides custom behavior -->
    <xsl:template match="recommendedActions[position() = 1]">

        <xsl:variable name="raCount" select="count(//recommendedActions)" />
        <xsl:variable name="raOne" select="//recommendedActions[1]" />
        <xsl:variable name="raTwo" select="//recommendedActions[2]" />
        <recommendedActions>
            <xsl:if test="$raCount = 1">
              <xsl:apply-templates select="action" />
              <actionText><xsl:value-of select="action"/> the <xsl:value-of select="report"/></actionText>
              <xsl:apply-templates select="report" />
              <xsl:apply-templates select="status" />
              <xsl:apply-templates select="due" />
              <xsl:apply-templates select="dueDate" />
            </xsl:if>
            <xsl:if test="$raCount = 2 and ($raOne/action/text() = 'Withdraw' or $raOne/action/text() = 'Amend')">
                    <xsl:if test="$raOne/action/text() = 'Withdraw'">
                        <action>Replace</action>
                        <actionText>Replace the <xsl:value-of select="$raOne/report"/> with the <xsl:value-of select="$raTwo/report"/></actionText>
                    </xsl:if>
                    <xsl:if test="not($raOne/action/text() = 'Withdraw')">
                        <xsl:apply-templates select="$raOne/action" />
                        <actionText><xsl:value-of select="$raOne/action"/> the <xsl:value-of select="$raOne/report"/> with a <xsl:value-of select="$raTwo/report"/></actionText>
                    </xsl:if>
                    <report><xsl:value-of select="$raTwo/report"/></report>
                    <status><xsl:value-of select="$raTwo/status"/></status>
                    <due><xsl:value-of select="$raTwo/due" /></due>
                    <dueDate><xsl:value-of select="$raTwo/dueDate" /></dueDate>
            </xsl:if>
            <xsl:if test="$raCount = 2 and not($raOne/action/text() = 'Withdraw' or $raOne/action/text() = 'Amend')">
                <xsl:if test="$raTwo/action/text() = 'Withdraw'">
                    <action>Replace</action>
                    <actionText>Replace the <xsl:value-of select="$raTwo/report"/> with the <xsl:value-of select="$raOne/report"/></actionText>
                </xsl:if>
                <xsl:if test="not($raTwo/action/text() = 'Withdraw')">
                    <xsl:apply-templates select="$raTwo/action" />
                    <actionText><xsl:value-of select="$raTwo/action"/> the <xsl:value-of select="$raTwo/report"/> with a <xsl:value-of select="$raOne/report"/></actionText>
                </xsl:if>
                <report><xsl:value-of select="$raOne/report"/></report>
                <status><xsl:value-of select="$raOne/status"/></status>
                <due><xsl:value-of select="$raOne/due" /></due>
                <dueDate><xsl:value-of select="$raOne/dueDate" /></dueDate>
            </xsl:if>
        </recommendedActions>

    </xsl:template>
    <xsl:template match="recommendedActions[position() = 2]">

    </xsl:template>

</xsl:stylesheet>