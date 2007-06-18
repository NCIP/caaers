<?xml version="1.0"?>
<xsl:stylesheet 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:rules="http://caaers.cabig.nci.nih.gov/rules/brxml"
		version="1.0">
	
	<xsl:output method="xml" indent="yes"/> 
	<xsl:key name="x" match="rules:ruleSet/rules:rule/rules:condition/rules:column" use="concat(@object-type,../../@id)"/>
	<xsl:template match="/"> 
				<xsl:apply-templates/>
	</xsl:template>
	
	
	<xsl:template match="rules:ruleSet">
	
	 	<ruleSet> 
 			<id> <xsl:value-of select="rules:id"/>	</id>
			<name> <xsl:value-of select="rules:name"/> </name>
        	<description> <xsl:value-of select="rules:description"/> </description>
         	<import><xsl:value-of select="rules:import"/></import>
         	
  			<xsl:apply-templates select="rules:rule"/>
  			

  		</ruleSet>
	
	</xsl:template>
	
	<xsl:template match="rules:rule">
	  <rule>

	  	
		<xsl:attribute name="id"><xsl:value-of select="@id"/></xsl:attribute>
	  	<xsl:variable name="rId" select="@id"/>
	  	
	  	
		<condition>
				
				
				<xsl:for-each select="rules:condition/rules:column"> 
		
		<!-- display IDS ... testing
					<id> <xsl:value-of select="generate-id(.)"/> -  <xsl:value-of select="generate-id(key('x', concat(@object-type,$rId)))"/>
					</id>
		-->
					<xsl:if test="generate-id(.) = generate-id(key('x', concat(@object-type,$rId)))">
						<column>
							<xsl:copy-of select="@*"/>			
						</column>
					</xsl:if>
					
				</xsl:for-each>
				<xsl:apply-templates select="rules:condition/rules:column"/>
		</condition>
		
		<xsl:apply-templates select="rules:action"/>
		<xsl:apply-templates select="rules:metaData"/>
		
	 </rule>
	</xsl:template>
	
	<xsl:template match="rules:condition/rules:column">		
		<xsl:if test="rules:field-constraint/rules:literal-restriction/rules:value">
			<!--<or>-->
				<xsl:for-each select="rules:field-constraint/rules:literal-restriction/rules:value">	
					
					<!--<xsl:if test="rules:field-constraint">-->
						
						<eval>			
							<xsl:choose>
								<xsl:when  test="../../../rules:expression">
									<xsl:value-of select="../../../@identifier"/>.<xsl:value-of select="../../../rules:expression"/>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="../../../@identifier"/>.<xsl:value-of select="../../../rules:field-constraint/@field-name"/>
								</xsl:otherwise>						
							</xsl:choose>
							
							<xsl:value-of select="../../../rules:field-constraint/rules:literal-restriction/@evaluator"/>
							<xsl:value-of select="."/>
						</eval>
								
				</xsl:for-each>
			<!--</or>-->
		</xsl:if>		
	</xsl:template>

	<xsl:template match="rules:rule/rules:action">
		<action>
			<xsl:copy-of select="@* | node()"/>
		</action>
	  
	</xsl:template>	
	
	<xsl:template match="rules:rule/rules:metaData">
		<metaData>
			<xsl:copy-of select="@* | node()"/>
		</metaData>
	  
	</xsl:template>
	

					
</xsl:stylesheet>