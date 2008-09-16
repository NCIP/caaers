<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:java="http://xml.apache.org/xslt/java"
	exclude-result-prefixes="java">

	<xsl:output method="xml" />

	<xsl:attribute-set name="tr-height-1">
		<xsl:attribute name="height">4mm</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="black-heading">
		<xsl:attribute name="font-size">8.5pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>
		<xsl:attribute name="color">white</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="label">
		<xsl:attribute name="font-size">6.5pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>
		<xsl:attribute name="padding-top">1mm</xsl:attribute>

	</xsl:attribute-set>

	<xsl:attribute-set name="cell-with-right-border">
		<xsl:attribute name="border-right-color">black</xsl:attribute>
		<xsl:attribute name="border-right-width">0.5pt</xsl:attribute>
		<xsl:attribute name="border-right-style">double</xsl:attribute>
		<xsl:attribute name="padding-left">1mm</xsl:attribute>
	</xsl:attribute-set>
	
	<xsl:attribute-set name="full-border">
		<xsl:attribute name="border-bottom-color">black</xsl:attribute>
		<xsl:attribute name="border-bottom-width">0.5pt</xsl:attribute>
		<xsl:attribute name="border-bottom-style">double</xsl:attribute>
		<xsl:attribute name="border-right-color">black</xsl:attribute>
		<xsl:attribute name="border-right-width">0.5pt</xsl:attribute>
		<xsl:attribute name="border-right-style">double</xsl:attribute>	
		<xsl:attribute name="padding-left">1mm</xsl:attribute>	
	</xsl:attribute-set>
		
	<xsl:template match="/">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4"
					margin-left="10mm" margin-top="2mm" margin-right="6mm">
					<fo:region-body margin-top="4mm" />
					<fo:region-before extent="0.25in" />
					<fo:region-after extent="0.25in" />
				</fo:simple-page-master>


			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4">

				<fo:flow flow-name="xsl-region-body">
					<fo:table >						
						<fo:table-column column-width="100%" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>	
									<fo:block  font-size="6" text-align="right">
									Form Approved: OMB No. 0910-0291, Expires: 10/31/08 <fo:block/>See OMB statement on reverse.
									</fo:block>
								</fo:table-cell>																
							</fo:table-row>						
						</fo:table-body>
					</fo:table>											
					<fo:table >						
						<fo:table-column column-width="33%" />
						<fo:table-column column-width="34%" />
						<fo:table-column column-width="33%" />
						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:block font-size="8" font-weight="bold">U.S. Department of Health and Human Services</fo:block>
									<fo:block font-size="8">Food and Drug Administration</fo:block>
									<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
									<fo:block font-size="12" font-weight="bold">MEDWATCH</fo:block>
									<fo:block font-size="9" font-weight="bold">FORM FDA 3500A (10/05)</fo:block>
								</fo:table-cell>
								<fo:table-cell text-align="center">
									<fo:block font-size="10" font-family="Goudy">For use by user-facilities,<fo:block/>importers, distributors and manufacturers<fo:block/>for MANDATORY reporting</fo:block>
									<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
									<fo:block xsl:use-attribute-sets="label">Page ____ of ____</fo:block>
								</fo:table-cell>
								<fo:table-cell  >
										<fo:table border="1pt solid black">						
											<fo:table-column column-width="100%" />
											<fo:table-body>
												<fo:table-row height="18" >
													<fo:table-cell xsl:use-attribute-sets="full-border">
														<fo:block font-size="6.5" padding-top="1mm">Mfr Report #</fo:block>
													</fo:table-cell>																
												</fo:table-row>	
												<fo:table-row height="18">
													<fo:table-cell xsl:use-attribute-sets="full-border">
														<fo:block font-size="6.5" padding-top="1mm">UF/Importer Report #</fo:block>
													</fo:table-cell>																
												</fo:table-row>	
												<fo:table-row height="22">
													<fo:table-cell text-align="right" xsl:use-attribute-sets="cell-with-right-border">
														<fo:block font-size="6" font-weight="bold" padding-top="5mm" padding-right="1mm" >FDA Use Only</fo:block>
													</fo:table-cell>																
												</fo:table-row>																														
											</fo:table-body>
										</fo:table>									
								</fo:table-cell>																
							</fo:table-row>						
						</fo:table-body>
					</fo:table>
						
					<fo:table >						
						<fo:table-column column-width="50%" />
						<fo:table-column column-width="2%" />
						<fo:table-column column-width="48%" />

						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:table border="1pt solid black">	
										<fo:table-column column-width="25%" />
										<fo:table-column column-width="17%" />
										<fo:table-column column-width="18%" />	
										<fo:table-column column-width="22%" />
										<fo:table-column column-width="18%" />
										<fo:table-body>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="5" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">A. PATIENT INFORMATION
													</fo:block>
												</fo:table-cell>
											</fo:table-row>								
											<fo:table-row height="25" >
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border">
													
													<fo:block xsl:use-attribute-sets="label">1. Patient Identifier</fo:block>
													<fo:block padding-top="12mm" font-size="6.5pt" text-align="center">In confidence</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">2. Age at Time <fo:block/>
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text> of Event:</fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														or <fo:leader leader-length="80%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														Date <fo:block/> <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text> 
														of Birth:</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">3.Sex</fo:block>
													<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text> </fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG"
														content-height="2mm" content-width="2mm"/>
													<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
													Female
													</fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
													<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
													Male
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">4. Weight	</fo:block>
													<fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>	</fo:block>
													<fo:block xsl:use-attribute-sets="label"><fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/> lbs	</fo:block>
													<fo:block font-size="6.5pt" text-align="center"> or	</fo:block>
													<fo:block xsl:use-attribute-sets="label"><fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/> kgs	</fo:block>
												</fo:table-cell>																								
											</fo:table-row>	
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="5" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">B. ADVERSE EVENT OR PRODUCT PROBLEM
													</fo:block>
												</fo:table-cell>
											</fo:table-row>		
											<fo:table-row height="5mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">1. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Adverse Event <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text></fo:inline>
														<fo:inline font-size="6.5">and/or <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text> </fo:inline> 
														<fo:inline xsl:use-attribute-sets="label">
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Product Problem</fo:inline>
														<fo:inline font-size="6.5" font-style="italic"> (e.g., defects/malfunctions) </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>																						
											<fo:table-row>
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">2. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Outcomes Attributed to Adverse Event</fo:inline>
													</fo:block>
													<fo:block font-size="6.5" font-style="italic">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													(Check all that apply)
													</fo:block>
													
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Death: 
														
														<!-- if date exists , it goes here -->
														<fo:leader leader-length="30%" leader-pattern="rule" rule-thickness="0.5pt"/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Disability or Permanent Damage													
													</fo:block>
													<fo:block font-size="6.5" font-style="italic">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														
													(mm/dd/yyyy)</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Life-threatening
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
		
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Congenital Anomaly/Birth Defect													
													</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Hospitalization - initial or prolonged
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Other Serious (Important Medical Events)												
													</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Required Intervention to Prevent Permanent Impairment/Damage (Devices)													
													</fo:block>																																							
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="9mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="2">
													<fo:block>
														<fo:inline font-size="6.5">3. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Date of Event </fo:inline>
														<fo:inline font-size="6.5">(mm/dd/yyyy) </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="3">
													<fo:block>
														<fo:inline font-size="6.5">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Date of This Report</fo:inline>
														<fo:inline font-size="6.5">(mm/dd/yyyy) </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>												
											<fo:table-row height="70mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">5. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Describe Event or Problem </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="35mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">6. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Relevant Tests/Laboratory Data, Including Dates </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="35mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">7. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Other Relevant History, Including Preexisting Medical Conditions </fo:inline>
														<fo:inline font-size="6.5" font-style="italic">(e.g., allergies,race, pregnancy, smoking and alcohol use, hepatic/renal dysfunction, etc.) </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
										</fo:table-body>
									</fo:table>	
									<fo:block xsl:use-attribute-sets="label">
													Submission of a report does not constitute an admission that medical
													personnel, user facility, importer, distributor, manufacturer or product
													caused or contributed to the event.
									</fo:block>
								</fo:table-cell>
								<fo:table-cell><fo:block/></fo:table-cell>
								<fo:table-cell>
									<fo:table border="1pt solid black">	
										<fo:table-column column-width="30%" />
										<fo:table-column column-width="20%" />	
										<fo:table-column column-width="10%" />	
										<fo:table-column column-width="10%" />	
										<fo:table-column column-width="30%" />							
										<fo:table-body>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="5" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">C. SUSPECT PRODUCT(S)
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="14mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">1. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Name </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Give labeled strength and mfr/labeler)</fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline"> name goes here </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt"> name goes here </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>																						
											<fo:table-row height="14mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt">2. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Dose, Frequency and Route Used </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt">  </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block font-size="6.5pt" font-style="italic">
														<fo:inline font-size="6.5pt" font-style="normal">3. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label" font-style="normal">Therapy Dates </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(If unknown, give</fo:inline>
														<fo:block/>
														duration) from/to (or best estimate)
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt">  </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="14mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block>
														<fo:inline font-size="6.5pt">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Diagnosis for Use </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Indication)</fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt">  </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2" number-rows-spanned="2">													
													<fo:block font-size="6.5pt" font-weight="bold">
														<fo:inline font-size="6.5pt" font-weight="normal">5. </fo:inline>
														<fo:inline>Event Abated After Use </fo:inline>
														<fo:block/><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>Stopped or Dose Reduced?
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >#1 
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Doesn't 														
														</fo:inline>
														<fo:block/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														Apply
														<fo:block/>
														<fo:leader leader-length="90%" leader-pattern="rule" rule-thickness="0.5pt"/>
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >#2
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Doesn't 														
														</fo:inline>
														<fo:block/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														Apply
														<fo:block/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>												
											<fo:table-row height="5mm">
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border"  number-columns-spanned="1">													
													<fo:block>
														<fo:inline font-size="6.5pt">6. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Lot # </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Indication)</fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt">7. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Exp Date # </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Indication)</fo:inline>
													</fo:block>
												</fo:table-cell>				

											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="1">													
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 lot number goes here</fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt">  </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt" text-decoration="underline">#1 exp date goes here</fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">#2 </fo:inline>
														<fo:inline width="10mm" font-size="6.5pt">  </fo:inline>
													</fo:block>
												</fo:table-cell>												
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2" number-rows-spanned="2">													
													<fo:block font-size="6.5pt" font-weight="bold">
														<fo:inline font-size="6.5pt" font-weight="normal">8. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Event Reappeared After </fo:inline>
														<fo:block/><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>Reintroduction?
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >#1 
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Doesn't 														
														</fo:inline>
														<fo:block/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														Apply
														<fo:block/>
														<fo:leader leader-length="90%" leader-pattern="rule" rule-thickness="0.5pt"/>
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >#2
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<fo:external-graphic src="file:/Users/sakkala/tech-workspace/caaers12/core/src/main/resources/xslt/uncheck.JPG" content-height="2mm" content-width="2mm"/>
														Doesn't 														
														</fo:inline>
														<fo:block/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														Apply
														<fo:block/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="9mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block>
														<fo:inline font-size="6.5pt">9. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">NDC# or Unique ID </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="20mm">
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">10. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Concomitant Medical Products and Therapy Dates </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Exclude treatment of event) </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>									
									</fo:table>	  
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template name="standard_date">
		<xsl:param name="date" />
		<xsl:if test="$date">
			<!-- Day -->
			<xsl:value-of select="substring($date, 9, 2)" />
			<xsl:text>/</xsl:text>
			<!-- Month -->
			<xsl:value-of select="substring($date, 6, 2)" />
			<xsl:text>/</xsl:text>
			<!-- Year -->
			<xsl:value-of select="substring($date, 1, 4)" />
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
