<?xml version="1.0" encoding="UTF-8"?>

<xsl:transform version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:hl7="urn:hl7-org:v3"
	xmlns:svrl="http://purl.oclc.org/dsdl/svrl">

	<xsl:strip-space elements="*"/>

	<xsl:output method="text" indent="no" />

	<xsl:template match="svrl:schematron-output">
		<xsl:if test="count(svrl:failed-assert) > 0">
			Failed Schematron Validation.
			<xsl:apply-templates />
		</xsl:if>
	</xsl:template>

	<!-- Set the output to be XML with an XML declaration and use indentation -->
	<xsl:template match="svrl:failed-assert">
		  Failed rule <xsl:value-of select="svrl:text/text()" />
		  Expression: <xsl:value-of select="@test" />
		  Location of failure: <xsl:value-of select="@location" />
	</xsl:template>
</xsl:transform>