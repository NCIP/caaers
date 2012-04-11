<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml"/>
    <xsl:template match="/">
       <payload>
        <system>adeers</system>
       	<response>
       		<entity>agents</entity>
       		<operation type="getAgentsLOV">
       			<xsl:apply-templates />
       		</operation>
       	</response>
       </payload> 
        
       
    </xsl:template>

	<xsl:template match="Agents">
		<data>
			<xsl:copy-of select="."/>
		</data>
	</xsl:template>
   
</xsl:stylesheet>