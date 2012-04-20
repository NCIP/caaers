<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- elements: create a new element with the same name, but no namespace -->
	<xsl:template match="//payload | payload//*">
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()" />
		</xsl:element>
	</xsl:template>
	
	<!-- attributes, commments, processing instructions, text: copy as is -->
	<xsl:template match="@*|comment()|processing-instruction()|text()">
		<xsl:copy-of select="." />
	</xsl:template>
	
</xsl:stylesheet>