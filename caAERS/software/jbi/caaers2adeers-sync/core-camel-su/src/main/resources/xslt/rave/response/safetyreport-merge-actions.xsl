<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ns1="http://schema.integration.caaers.cabig.nci.nih.gov/aereport">
    <xsl:output omit-xml-declaration="yes" indent="yes" />

    <xsl:template match="@*|node()">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <!--More specific template for Node766 that provides custom behavior -->
    <xsl:template match="ns1:baseReport[position() = 1]">
        <xsl:variable name="brCount" select="count(//ns1:baseReport)" />
        <xsl:variable name="brOne" select="//ns1:baseReport[1]" />
        <xsl:variable name="brTwo" select="//ns1:baseReport[2]" />
        <ns1:baseReport>
            <xsl:if test="$brCount = 1">
              <xsl:apply-templates select="ns1:ReportID" />
              <xsl:apply-templates select="ns1:caseNumber" />
              <xsl:apply-templates select="ns1:reportName" />
              <xsl:apply-templates select="ns1:action" />
              <ns1:actionText><xsl:value-of select="ns1:action"/> the <xsl:value-of select="ns1:reportName"/></ns1:actionText>
              <xsl:apply-templates select="ns1:amendmentNumber" />
              <xsl:apply-templates select="ns1:dueDate" />
            </xsl:if>
            <xsl:if test="$brCount = 2 and ($brOne/ns1:action/text() = 'Withdraw' or $brOne/ns1:action/text() = 'Amend')">
                <xsl:apply-templates select="$brTwo/ns1:ReportID" />
                <xsl:apply-templates select="$brTwo/ns1:caseNumber" />
                <xsl:apply-templates select="$brTwo/ns1:reportName" />
                    <xsl:if test="$brOne/ns1:action/text() = 'Withdraw'">
                        <ns1:action>Replace</ns1:action>
                        <ns1:actionText>Replace the <xsl:value-of select="$brOne/ns1:reportName"/> with the <xsl:value-of select="$brTwo/ns1:reportName"/></ns1:actionText>
                    </xsl:if>
                    <xsl:if test="not($brOne/ns1:action/text() = 'Withdraw')">
                        <xsl:apply-templates select="$brOne/ns1:action" />
                        <ns1:actionText><xsl:value-of select="$brOne/ns1:action"/> the <xsl:value-of select="$brOne/ns1:reportName"/> with a <xsl:value-of select="$brTwo/ns1:reportName"/></ns1:actionText>
                    </xsl:if>
                    <xsl:apply-templates select="$brTwo/ns1:amendmentNumber" />
                    <xsl:apply-templates select="$brTwo/ns1:dueDate" />
            </xsl:if>
            <xsl:if test="$brCount = 2 and not($brOne/ns1:action/text() = 'Withdraw' or $brOne/ns1:action/text() = 'Amend')">
                <xsl:apply-templates select="$brOne/ns1:ReportID" />
                <xsl:apply-templates select="$brOne/ns1:caseNumber" />
                <xsl:apply-templates select="$brOne/ns1:reportName" />
                <xsl:if test="$brTwo/ns1:action/text() = 'Withdraw'">
                    <ns1:action>Replace</ns1:action>
                    <ns1:actionText>Replace the <xsl:value-of select="$brTwo/ns1:reportName"/> with the <xsl:value-of select="$brOne/ns1:reportName"/></ns1:actionText>
                </xsl:if>
                <xsl:if test="not($brTwo/ns1:action/text() = 'Withdraw')">
                    <xsl:apply-templates select="$brTwo/ns1:action" />
                    <ns1:actionText><xsl:value-of select="$brTwo/ns1:action"/> the <xsl:value-of select="$brTwo/ns1:reportName"/> with a <xsl:value-of select="$brOne/ns1:reportName"/></ns1:actionText>
                </xsl:if>
                <xsl:apply-templates select="$brOne/ns1:amendmentNumber" />
                <xsl:apply-templates select="$brOne/ns1:dueDate" />
            </xsl:if>
        </ns1:baseReport>

    </xsl:template>
    <xsl:template match="ns1:baseReport[position() = 2]">

    </xsl:template>

</xsl:stylesheet>