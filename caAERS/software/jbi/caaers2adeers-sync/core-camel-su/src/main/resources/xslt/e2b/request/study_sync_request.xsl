<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <payload>
            <system>adeers</system>
            <request>
                <entity>study</entity>
                <operation mode="async" name="updateStudy">
                    <criteria>
                        <criterion name="nciDocumentNumber"><xsl:value-of select="/ichicsr/safetyreport/primarysource/sponsorstudynumb"/></criterion>
                    </criteria>
                </operation>
            </request>
        </payload>
    </xsl:template>
</xsl:stylesheet>