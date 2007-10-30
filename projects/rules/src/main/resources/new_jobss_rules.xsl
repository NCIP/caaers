<?xml version='1.0'?>
<xsl:stylesheet version="1.0" 
	xmlns:rules="http://caaers.cabig.nci.nih.gov/rules/brxml"
	xmlns:drools="http://drools.org/drools-3.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="xml" indent="yes" encoding="iso-8859-1" />

<xsl:namespace-alias stylesheet-prefix="rules" result-prefix="drools"/>


<xsl:template match="ruleSet" xml:space="preserve">
	<package name="{name}" xmlns="http://drools.org/drools-3.0"
         xmlns:xs="http://www.w3.org/2001/XMLSchema-instance"
         xs:schemaLocation="http://drools.org/drools-3.0 drools-3.0.xsd">

		<global identifier="ruleContext" type="gov.nih.nci.cabig.caaers.rules.runtime.RuleContext" xmlns="http://drools.org/drools-3.0" />
		<global identifier="actionDispatcher" type="gov.nih.nci.cabig.caaers.rules.runtime.action.ActionDispatcher" xmlns="http://drools.org/drools-3.0" />
		
		
		<xsl:apply-templates select="import"/>

		<xsl:apply-templates select="rule"/>

	</package>
</xsl:template>


<xsl:template match="import">
			<import name="{.}" xmlns="http://drools.org/drools-3.0">
			<xsl:apply-templates/>
			</import>
</xsl:template>

<xsl:template match="rule">
	<rule name="{metaData/@name}" xmlns="http://drools.org/drools-3.0">
		<xsl:apply-templates/>
	</rule>
</xsl:template>

<xsl:template match="condition">
	<lhs xmlns="http://drools.org/drools-3.0">
		<xsl:apply-templates select="column"/>
		<xsl:apply-templates select="eval"/>
	</lhs>
</xsl:template>

<xsl:template match="column">
	<column object-type="{@object-type}" identifier="{@identifier}" xmlns="http://drools.org/drools-3.0">
		<xsl:apply-templates/>
	</column>
</xsl:template>


<xsl:template match="field-binding">
	<field-binding field-name="{@field-name}" identifier="{@identifier}" xmlns="http://drools.org/drools-3.0"/>
</xsl:template>

<xsl:template match="field-constraint">
	<field-constraint field-name="{@field-name}" xmlns="http://drools.org/drools-3.0">
		<xsl:apply-templates/>	
	</field-constraint>
</xsl:template>

<xsl:template match="literal-restriction">
	<literal-restriction evaluator="{@evaluator}" value="{@value}" xmlns="http://drools.org/drools-3.0"/>
</xsl:template>

<xsl:template match="or">
	<or xmlns="http://drools.org/drools-3.0">
		<xsl:apply-templates select="eval"/>
	</or>
</xsl:template>

<xsl:template match="eval">
	<eval xmlns="http://drools.org/drools-3.0">
		<xsl:value-of select="."/>
	</eval>
</xsl:template>

<xsl:template match="action">
	<rhs xmlns="http://drools.org/drools-3.0">
	    System.out.println("Value Is:"+"<xsl:value-of select="@actionId"/>");	    
	    
		if(adverseEventEvaluationResult != null) {
			
			String msg = "";
			if (adverseEventEvaluationResult.getMessage() != null) {
				msg = adverseEventEvaluationResult.getMessage() + "||";
			}
			
			adverseEventEvaluationResult.setMessage(msg + "<xsl:value-of select="@actionId"/>");
		}
		 else {
			System.out.println("The Rule Conditions were met. But no action is specified. ");
		}
	</rhs>
</xsl:template>


<xsl:template match="text()">
  <!-- <xsl:value-of select="." />  -->
  </xsl:template>

</xsl:stylesheet><!-- Stylus Studio meta-information - (c) 2004-2006. Progress Software Corporation. All rights reserved.
<metaInformation>
<scenarios ><scenario default="yes" name="Scenario1" userelativepaths="yes" externalpreview="no" url="..\..\..\..\..\..\..\..\..\Documents and Settings\SUJITH\Desktop\RuleSet.xml" htmlbaseurl="" outputurl="" processortype="internal" useresolver="yes" profilemode="0" profiledepth="" profilelength="" urlprofilexml="" commandline="" additionalpath="" additionalclasspath="" postprocessortype="none" postprocesscommandline="" postprocessadditionalpath="" postprocessgeneratedext="" validateoutput="no" validator="internal" customvalidator=""/></scenarios><MapperMetaTag><MapperInfo srcSchemaPathIsRelative="yes" srcSchemaInterpretAsXML="no" destSchemaPath="" destSchemaRoot="" destSchemaPathIsRelative="yes" destSchemaInterpretAsXML="no"/><MapperBlockPosition></MapperBlockPosition><TemplateContext></TemplateContext><MapperFilter side="source"></MapperFilter></MapperMetaTag>
</metaInformation>
-->