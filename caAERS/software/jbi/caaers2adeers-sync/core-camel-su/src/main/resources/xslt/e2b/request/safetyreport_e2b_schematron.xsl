<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xhtml="http://www.w3.org/1999/xhtml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:schold="http://www.ascc.net/xml/schematron"
                xmlns:iso="http://purl.oclc.org/dsdl/schematron"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:caaers="http://caaers"
                version="2.0">
	<!--Implementers: please note that overriding process-prolog or process-root is 
    the preferred method for meta-stylesheets to use where possible. -->
	<xsl:param name="archiveDirParameter"/>
	<xsl:param name="archiveNameParameter"/>
	<xsl:param name="fileNameParameter"/>
	<xsl:param name="fileDirParameter"/>
	<xsl:variable name="document-uri">
		<xsl:value-of select="document-uri(/)"/>
	</xsl:variable>

	<!--PHASES-->
	<!--PROLOG-->
	<xsl:output xmlns:svrl="http://purl.oclc.org/dsdl/svrl" method="xml"
               omit-xml-declaration="yes" indent="yes"/>

	<!--XSD TYPES FOR XSLT2-->


	<!--KEYS AND FUNCTIONS-->
	<xsl:variable name="map" select="document('lookup.xml')" />

	<xsl:function name="caaers:lookup">
		<xsl:param name="_code" />
		<xsl:param name="_map" />
		<xsl:value-of select="$_map//code[upper-case(text()) = upper-case($_code)]/parent::node()/value" />
	</xsl:function>

	<!--DEFAULT RULES-->


	<!--MODE: SCHEMATRON-SELECT-FULL-PATH-->
	<!--This mode can be used to generate an ugly though full XPath for locators-->
	<xsl:template match="*" mode="schematron-select-full-path">
		<xsl:apply-templates select="." mode="schematron-get-full-path"/>
	</xsl:template>

	<!--MODE: SCHEMATRON-FULL-PATH-->
	<!--This mode can be used to generate an ugly though full XPath for locators-->
	<xsl:template match="*" mode="schematron-get-full-path">
		<xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
		<xsl:text>/</xsl:text>
		<xsl:choose>
			<xsl:when test="namespace-uri()=''">
				<xsl:value-of select="name()"/>
				<xsl:variable name="p_1" select="1+    count(preceding-sibling::*[name()=name(current())])"/>
				<xsl:if test="$p_1&gt;1 or following-sibling::*[name()=name(current())]">[<xsl:value-of select="$p_1"/>]</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>*[local-name()='</xsl:text>
				<xsl:value-of select="local-name()"/>
				<xsl:text>']</xsl:text>
				<xsl:variable name="p_2"
                          select="1+   count(preceding-sibling::*[local-name()=local-name(current())])"/>
				<xsl:if test="$p_2&gt;1 or following-sibling::*[local-name()=local-name(current())]">[<xsl:value-of select="$p_2"/>]</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="@*" mode="schematron-get-full-path">
		<xsl:text>/</xsl:text>
		<xsl:choose>
			<xsl:when test="namespace-uri()=''">@<xsl:value-of select="name()"/>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>@*[local-name()='</xsl:text>
				<xsl:value-of select="local-name()"/>
				<xsl:text>' and namespace-uri()='</xsl:text>
				<xsl:value-of select="namespace-uri()"/>
				<xsl:text>']</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!--MODE: SCHEMATRON-FULL-PATH-2-->
	<!--This mode can be used to generate prefixed XPath for humans-->
	<xsl:template match="node() | @*" mode="schematron-get-full-path-2">
		<xsl:for-each select="ancestor-or-self::*">
			<xsl:text>/</xsl:text>
			<xsl:value-of select="name(.)"/>
			<xsl:if test="preceding-sibling::*[name(.)=name(current())]">
				<xsl:text>[</xsl:text>
				<xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
				<xsl:text>]</xsl:text>
			</xsl:if>
		</xsl:for-each>
		<xsl:if test="not(self::*)">
			<xsl:text/>/@<xsl:value-of select="name(.)"/>
		</xsl:if>
	</xsl:template>
	<!--MODE: SCHEMATRON-FULL-PATH-3-->
	<!--This mode can be used to generate prefixed XPath for humans 
	(Top-level element has index)-->
	<xsl:template match="node() | @*" mode="schematron-get-full-path-3">
		<xsl:for-each select="ancestor-or-self::*">
			<xsl:text>/</xsl:text>
			<xsl:value-of select="name(.)"/>
			<xsl:if test="parent::*">
				<xsl:text>[</xsl:text>
				<xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
				<xsl:text>]</xsl:text>
			</xsl:if>
		</xsl:for-each>
		<xsl:if test="not(self::*)">
			<xsl:text/>/@<xsl:value-of select="name(.)"/>
		</xsl:if>
	</xsl:template>

	<!--MODE: GENERATE-ID-FROM-PATH -->
	<xsl:template match="/" mode="generate-id-from-path"/>
	<xsl:template match="text()" mode="generate-id-from-path">
		<xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
		<xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')"/>
	</xsl:template>
	<xsl:template match="comment()" mode="generate-id-from-path">
		<xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
		<xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')"/>
	</xsl:template>
	<xsl:template match="processing-instruction()" mode="generate-id-from-path">
		<xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
		<xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
	</xsl:template>
	<xsl:template match="@*" mode="generate-id-from-path">
		<xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
		<xsl:value-of select="concat('.@', name())"/>
	</xsl:template>
	<xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
		<xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
		<xsl:text>.</xsl:text>
		<xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')"/>
	</xsl:template>

	<!--MODE: GENERATE-ID-2 -->
	<xsl:template match="/" mode="generate-id-2">U</xsl:template>
	<xsl:template match="*" mode="generate-id-2" priority="2">
		<xsl:text>U</xsl:text>
		<xsl:number level="multiple" count="*"/>
	</xsl:template>
	<xsl:template match="node()" mode="generate-id-2">
		<xsl:text>U.</xsl:text>
		<xsl:number level="multiple" count="*"/>
		<xsl:text>n</xsl:text>
		<xsl:number count="node()"/>
	</xsl:template>
	<xsl:template match="@*" mode="generate-id-2">
		<xsl:text>U.</xsl:text>
		<xsl:number level="multiple" count="*"/>
		<xsl:text>_</xsl:text>
		<xsl:value-of select="string-length(local-name(.))"/>
		<xsl:text>_</xsl:text>
		<xsl:value-of select="translate(name(),':','.')"/>
	</xsl:template>
	<!--Strip characters-->
	<xsl:template match="text()" priority="-1"/>

	<!--SCHEMA SETUP-->
	<xsl:template match="/">
		<svrl:schematron-output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                              title="E2B caAERS Schematron validation"
                              schemaVersion="1.0">
			<xsl:comment>
				<xsl:value-of select="$archiveDirParameter"/>   
				<xsl:value-of select="$archiveNameParameter"/>  
				<xsl:value-of select="$fileNameParameter"/>  
				<xsl:value-of select="$fileDirParameter"/>
			</xsl:comment>
			<svrl:text>Validates a E2B safety report message</svrl:text>
			<svrl:text>(c) Copyright SemanticBits, Northwestern University and Akaza Research</svrl:text>
			<svrl:ns-prefix-in-attribute-values uri="http://www.w3.org/2001/XMLSchema-instance" prefix="xsi"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Present Status</xsl:attribute>
				<xsl:attribute name="name">Present Status: validate present status</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M6"/> 
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Course Agent</xsl:attribute>
				<xsl:attribute name="name">CourseAgent: validate dose unit</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M7"/> 
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Lab</xsl:attribute>
				<xsl:attribute name="name">Lab: validate lab test unit</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M8"/> 
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Patient Summary</xsl:attribute>
				<xsl:attribute name="name">Patient Summary: validate retreated flag</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M9"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Patient Death</xsl:attribute>
				<xsl:attribute name="name">Patient Death: validate autopsy flag</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M10"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">ECOG or ZUBROD basline performance scale</xsl:attribute>
				<xsl:attribute name="name">BaselinePerformance: validate baseline performance number</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M11"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">LANSKY or KARNOFSKY basline performance scale</xsl:attribute>
				<xsl:attribute name="name">BaselinePerformance: validate baseline performance number</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M12"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Adverse Event primary flag</xsl:attribute>
				<xsl:attribute name="name">AE: validate AE primary flag</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M13"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Adverse Event attribution factor-type</xsl:attribute>
				<xsl:attribute name="name">AE Attribution: validate AE attribution factor-type</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M14"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Participant History baseline performance scale</xsl:attribute>
				<xsl:attribute name="name">Participant History: validate baseline performance scale</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M15"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Lab test type value</xsl:attribute>
				<xsl:attribute name="name">Lab: validate test type value</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M16"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Lab test unit and infectiousagent and infectionsite</xsl:attribute>
				<xsl:attribute name="name">Lab: validate test unit and infectiousagent and infectionsite</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M17"/>
			<svrl:active-pattern>
				<xsl:attribute name="document">
					<xsl:value-of select="document-uri(/)"/>
				</xsl:attribute>
				<xsl:attribute name="id">Lab test type and infectiousagent and infectionsite</xsl:attribute>
				<xsl:attribute name="name">Lab: validate test type and infectiousagent and infectionsite</xsl:attribute>
				<xsl:apply-templates/>
			</svrl:active-pattern>
			<xsl:apply-templates select="/" mode="M18"/>
		</svrl:schematron-output>
	</xsl:template>

	<!--SCHEMATRON PATTERNS-->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">E2B Safety Report Message</svrl:text>

	<!--PATTERN Present Status: validate AE present status -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Present Status: validate AE present status </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/reaction[primaryaeflag=1]" priority="1001" mode="M6">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/reaction[primaryaeflag=1]"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(./reactionoutcome, $map//aepresentstatuses)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="reactionoutcome">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching AE present status</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M6"/>
	<xsl:template match="@*|node()" priority="-2" mode="M6">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
	</xsl:template>
	
	<!--PATTERN CourseAgent: validate dose unit -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">CourseAgent: validate dose unit</svrl:text>

	<!--RULE -->
	<xsl:template match="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1']" priority="1001" mode="M7">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="//drug[drugadditional != 'Radiation' and  drugadditional != 'Surgery' and drugadditional != 'Device' and drugcharacterization = '1']"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(./drugcumulativedosageunitextended, $map//uoms)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="drugcumulativedosageunitextended">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching course agent dose unit</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M7"/>
	<xsl:template match="@*|node()" priority="-2" mode="M7">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
	</xsl:template>
	
	<!--PATTERN Lab: validate test unit -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Lab: validate test unit</svrl:text>

	<!--RULE -->
	<xsl:template match="//test" priority="1001" mode="M8">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="//test"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(./testunit, $map//uoms)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="testunit">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching lab test unit</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M8"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M8"/>
	<xsl:template match="@*|node()" priority="-2" mode="M8">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M8"/>
	</xsl:template>
	
	<!--PATTERN Patient Summary: validate retreated flag -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Patient Summary: validate retreated flag</svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/summary/retreatedflag" priority="1001" mode="M9">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/summary/retreatedflag"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//e2byesnos)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test=".">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching retreated flag</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M9"/>
	<xsl:template match="@*|node()" priority="-2" mode="M9">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
	</xsl:template>
	
	<!--PATTERN Patient Death: validate autopsy flag -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Patient Death: validate autopsy flag</svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno" priority="1001" mode="M10">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/patientdeath/patientautopsyyesno"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//e2byesnos)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test=".">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching autopsy flag</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M10"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M10"/>
	<xsl:template match="@*|node()" priority="-2" mode="M10">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M10"/>
	</xsl:template>
	
	<!--PATTERN BaselinePerformace: validate baseline performance number -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">BaselinePerformace: validate baseline performance number</svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient[upper-case(baselineperformancescale)='ECOG' or upper-case(baselineperformancescale)='ZUBROD']" priority="1001" mode="M11">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient[upper-case(baselineperformancescale)='ECOG' or upper-case(baselineperformancescale)='ZUBROD']"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(./baselineperformancenumber, $map//ecog-zubrod-baselinestatuses)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="./baselineperformancenumber">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching baseline performance number from ECOG or ZUBROD scale</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M11"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M11"/>
	<xsl:template match="@*|node()" priority="-2" mode="M11">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M11"/>
	</xsl:template>
	
	<!--PATTERN BaselinePerformace: validate baseline performance number -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">BaselinePerformace: validate baseline performance number</svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient[upper-case(baselineperformancescale)='LANSKY' or upper-case(baselineperformancescale)='KARNOFSKY']" priority="1001" mode="M12">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient[upper-case(baselineperformancescale)='LANSKY' or upper-case(baselineperformancescale)='KARNOFSKY']"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(./baselineperformancenumber, $map//karnofsky-lansky-baselinestatuses)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="./baselineperformancenumber">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching baseline performance number from LANSKY or KARNOFSKY scale</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M12"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M12"/>
	<xsl:template match="@*|node()" priority="-2" mode="M12">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M12"/>
	</xsl:template>
	
		<!--PATTERN flag: validate AE primary flag -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Present Status: validate AE primary flag </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/reaction/primaryaeflag" priority="1001" mode="M13">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/reaction/primaryaeflag"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//yes-no-lov)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="primaryaeflag">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching AE primary flag</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M13"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M13"/>
	<xsl:template match="@*|node()" priority="-2" mode="M13">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M13"/>
	</xsl:template>
	
	<!--PATTERN flag: validate AE attribution factor-type -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Present Status: validate AE primary flag </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/reaction/factortype" priority="1001" mode="M14">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/reaction/factortype"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//factor-types)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="factortype">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching attribution factor type</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M14"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M14"/>
	<xsl:template match="@*|node()" priority="-2" mode="M14">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M14"/>
	</xsl:template>
	
	<!--PATTERN flag: validate patient baseline performance scale -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Participant History: validate baseline performance scale </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/baselineperformancescale" priority="1001" mode="M15">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/baselineperformancescale"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//baseline-performance-scales)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="baselineperformancescale">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching baseline performance scale</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M15"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M15"/>
	<xsl:template match="@*|node()" priority="-2" mode="M15">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M15"/>
	</xsl:template>
	
	<!--PATTERN flag: validate lab test type -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Lab: validate test type value</svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/test/testtype" priority="1001" mode="M16">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/test/testtype"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="caaers:lookup(., $map//lab-testtypes)!=''"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="testtype">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>Unavailable matching lab test type</svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M16"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M16"/>
	<xsl:template match="@*|node()" priority="-2" mode="M16">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M16"/>
	</xsl:template>
	
	<!--PATTERN flag: validate lab test should not have both testunit and infectiousagent and site -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Lab: validate testunit and infectiousagent or infectionsite </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/test" priority="1001" mode="M17">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/test"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="testunit and (infectiousagent or infectionsite)"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="test">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>lab should not have both testunit and infectiousagent or site </svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M17"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M17"/>
	<xsl:template match="@*|node()" priority="-2" mode="M17">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M17"/>
	</xsl:template>
	
	<!--PATTERN flag: validate lab test should not have both testtype and infectiousagent and site -->
	<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">Lab: validate testtype and infectiousagent or infectionsite </svrl:text>

	<!--RULE -->
	<xsl:template match="/ichicsr/safetyreport/patient/test" priority="1001" mode="M18">
		<svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/ichicsr/safetyreport/patient/test"/>

		<!--ASSERT -->
		<xsl:choose>
			<xsl:when test="testtype and (infectiousagent or infectionsite)"/>
			<xsl:otherwise>
				<svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="test">
					<xsl:attribute name="location">
						<xsl:apply-templates select="." mode="schematron-select-full-path"/>
					</xsl:attribute>
					<svrl:text>lab should not have both testtype and infectiousagent or site </svrl:text>
				</svrl:failed-assert>
			</xsl:otherwise>
		</xsl:choose>
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M18"/>
	</xsl:template>
	<xsl:template match="text()" priority="-1" mode="M18"/>
	<xsl:template match="@*|node()" priority="-2" mode="M18">
		<xsl:apply-templates select="*|comment()|processing-instruction()" mode="M18"/>
	</xsl:template>

</xsl:stylesheet>