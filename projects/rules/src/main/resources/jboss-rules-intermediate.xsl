<?xml version="1.0"?>
<xsl:stylesheet 
		xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xmlns:rules="http://caaers.cabig.nci.nih.gov/rules/brxml"
		version="1.0">
	
	<xsl:output method="xml" indent="yes"/> 
	
	
	<xsl:key name="x" match="rules:ruleSet/rules:rule/rules:condition/rules:column" use="concat(@identifier,../../@id)"/>
	<xsl:template match="/"> 
				<xsl:apply-templates/>
	</xsl:template>
	
	<!--  test -->
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
					<xsl:if test="generate-id(.) = generate-id(key('x', concat(@identifier,$rId)))">
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
			<xsl:variable name="ct" select="count(rules:field-constraint/rules:literal-restriction/rules:value)"/>
			
			<eval>
				<!-- for otional field , TO-DO mantain opional list and get from there ... -->
				<xsl:if test="rules:field-constraint/@field-name = 'attributionSummary'">adverseEvent.getAttributionSummary() != null &amp;&amp; </xsl:if>
				
				<!--  this is commenetd because if no attribution is selected no rules will get fired .  
				<xsl:if test="rules:field-constraint/@field-name = 'attributionSummary'"><xsl:choose><xsl:when test="../../../rules:field-constraint/rules:literal-restriction/@evaluator = '=='">adverseEvent.getAttributionSummary() != null &amp;&amp; </xsl:when><xsl:otherwise>adverseEvent.getAttributionSummary() != null || </xsl:otherwise></xsl:choose></xsl:if>
				-->
				
				<xsl:for-each select="rules:field-constraint/rules:literal-restriction/rules:value">				
							<xsl:choose>
								<xsl:when  test="(contains(../../../rules:expression,'intValue()') = false) and (.!='true') and  (.!='false') and (../../../rules:field-constraint/rules:literal-restriction/@evaluator = '!=')" >!</xsl:when>
							</xsl:choose>
							<xsl:choose>							
								<xsl:when  test="../../../rules:expression">
									<xsl:value-of select="../../../@identifier"/>.<xsl:value-of select="../../../rules:expression"/>
								</xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="../../../@identifier"/>.<xsl:value-of select="../../../rules:field-constraint/@field-name"/>
								</xsl:otherwise>						
							</xsl:choose>

							
							<xsl:choose>
								<xsl:when  test="(contains(../../../rules:expression,'intValue()') = false) and (. != 'true') and  (. != 'false')" >.equals("</xsl:when>
								
								<xsl:when test="../../../rules:expression = 'getCtcTerm().getCategory().getId().intValue()'"> > 0 </xsl:when>
								<xsl:otherwise><xsl:value-of select="../../../rules:field-constraint/rules:literal-restriction/@evaluator"/></xsl:otherwise>							
							</xsl:choose>
							
							<xsl:choose>
								<xsl:when test="../../../rules:expression = 'getCtcTerm().getCategory().getId().intValue()'"></xsl:when>
								<xsl:otherwise><xsl:value-of select="."/></xsl:otherwise>
							</xsl:choose>
							
							<xsl:if test= "(contains(../../../rules:expression,'intValue()') = false)  and ( . != 'true') and (. != 'false')">")</xsl:if>
							<xsl:if test="$ct != position()"> <xsl:choose> <xsl:when test="../../../rules:field-constraint/rules:literal-restriction/@evaluator = '=='"> || </xsl:when><xsl:otherwise> &amp;&amp; </xsl:otherwise></xsl:choose></xsl:if>
				</xsl:for-each>
			</eval>	
			
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