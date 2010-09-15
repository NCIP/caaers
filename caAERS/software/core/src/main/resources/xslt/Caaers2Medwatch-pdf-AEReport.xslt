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
	
	<xsl:attribute-set name="normal">
    	<xsl:attribute name="font-size">6.5pt</xsl:attribute>
    	<xsl:attribute name="font-weight">normal</xsl:attribute>
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
													<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
													<fo:block xsl:use-attribute-sets="normal" > 
						  								<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/studySubjectIdentifier"/>
						  							</fo:block> 
													<fo:block padding-top="4mm" font-size="6.5pt" text-align="center">In confidence</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">2. Age at Time <fo:block/>
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text> of Event:</fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														or <fo:leader leader-length="80%" leader-pattern="rule" rule-thickness="0.5pt"/>
                                                    </fo:block>
													    <fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>Date <fo:block/>
                                                        <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														of Birth:
														<fo:inline xsl:use-attribute-sets="normal" > 
																<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/monthString"/>/
																<xsl:if test="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/dayString">
																	<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/dayString"/>/
																</xsl:if>
																<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/yearString"/>					
														</fo:inline>  		
											  		</fo:block> 
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">3.Sex</fo:block>
													<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text> </fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/StudyParticipantAssignment/Participant/gender = 'Female'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
													<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
													Female
													</fo:block>
													<fo:block font-size="6.5pt"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/StudyParticipantAssignment/Participant/gender = 'Male'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>													
													<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
													Male
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border">
													<fo:block xsl:use-attribute-sets="label">4. Weight	</fo:block>
													<fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>	</fo:block>
													<fo:block xsl:use-attribute-sets="label">
													<xsl:choose>
													  <xsl:when test="AdverseEventReport/ParticipantHistory/weight/unit = 'Pound'">
														<fo:inline xsl:use-attribute-sets="normal" text-decoration="underline">
															<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
															<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/quantity"/>
															<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														</fo:inline>
													  </xsl:when>
													  <xsl:otherwise>
														<fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/>
													  </xsl:otherwise>
													</xsl:choose>													
													 	lbs	
													 </fo:block>
													<fo:block font-size="6.5pt" text-align="center"> or	</fo:block>
													<fo:block xsl:use-attribute-sets="label">
													<xsl:choose>
													  <xsl:when test="AdverseEventReport/ParticipantHistory/weight/unit = 'Kilogram'">
														<fo:inline xsl:use-attribute-sets="normal" text-decoration="underline">
															<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
															<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/quantity"/>
															<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														</fo:inline>
													  </xsl:when>
													  <xsl:otherwise>
														<fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/>
													  </xsl:otherwise>
													</xsl:choose>
													 		kgs	
													 </fo:block>
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
														[x]
														Adverse Event <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text></fo:inline>
														<fo:inline font-size="6.5">and/or <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text> </fo:inline> 
														<fo:inline xsl:use-attribute-sets="label">
														[ ]
														Product Problem</fo:inline>
														<fo:inline font-size="6.5" font-style="italic"> (e.g., defects/malfunctions) </fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
										<xsl:for-each select="AdverseEventReport/AdverseEvent">	
										  <xsl:if test="substring(gridId,1,3) = 'PRY'">																				
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
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'DEATH'">[x]</xsl:when>
															<xsl:otherwise>[ ]</xsl:otherwise>
														</xsl:choose>
														Death: 
														
														<!-- if date exists , it goes here -->
														<fo:leader leader-length="30%" leader-pattern="rule" rule-thickness="0.5pt"/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'DISABILITY'">[x]</xsl:when>
															<xsl:otherwise>[ ]</xsl:otherwise>
														</xsl:choose>
														Disability or Permanent Damage													
													</fo:block>
													<fo:block font-size="6.5" font-style="italic">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														
													(mm/dd/yyyy)</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'LIFE_THREATENING'">
																[x]
															</xsl:when>
															<xsl:otherwise>
																[ ]
															</xsl:otherwise>
														</xsl:choose>
														Life-threatening
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
		
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'CONGENITAL_ANOMALY'">
																[x]
															</xsl:when>
															<xsl:otherwise>
																[ ]
															</xsl:otherwise>
														</xsl:choose>
														Congenital Anomaly/Birth Defect													
													</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'HOSPITALIZATION'">
																[x]
															</xsl:when>
															<xsl:otherwise>
																[ ]
															</xsl:otherwise>
														</xsl:choose>
														Hospitalization - initial or prolonged
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'OTHER_SERIOUS'">
																[x]
															</xsl:when>
															<xsl:otherwise>
																[ ]
															</xsl:otherwise>
														</xsl:choose>
														Other Serious (Important Medical Events)												
													</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														<xsl:choose>
															<xsl:when test="Outcome/OutcomeType = 'REQUIRED_INTERVENTION'">
																[x]
															</xsl:when>
															<xsl:otherwise>
																[ ]
															</xsl:otherwise>
														</xsl:choose>
														Required Intervention to Prevent Permanent Impairment/Damage (Devices)													
													</fo:block>																																							
												</fo:table-cell>
											</fo:table-row>	
										   </xsl:if>
										</xsl:for-each>
											<fo:table-row height="9mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="2">
													<fo:block>
														<fo:inline font-size="6.5">3. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Date of Event </fo:inline>
														<fo:inline font-size="6.5">(mm/dd/yyyy) </fo:inline>
													</fo:block>
													<xsl:for-each select="AdverseEventReport/AdverseEvent">	
										  				<xsl:if test="substring(gridId,1,3) = 'PRY'">
										  					<fo:block xsl:use-attribute-sets="normal">
										  					<xsl:call-template name="standard_date">
										  						<xsl:with-param name="date" select="startDate"/>
										  					</xsl:call-template>
										  					</fo:block>
										  				</xsl:if>
										  			</xsl:for-each>		
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="3">
													<fo:block>
														<fo:inline font-size="6.5">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Date of This Report</fo:inline>
														<fo:inline font-size="6.5">(mm/dd/yyyy) </fo:inline>
														<fo:block xsl:use-attribute-sets="normal">
															<xsl:value-of select="java:format(java:java.text.SimpleDateFormat.new ('MM/dd/yyyy'), java:java.util.Date.new())"/>		
														</fo:block>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>												
											<fo:table-row height="70mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">5. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Describe Event or Problem
                                                            <xsl:if test="AdverseEventReport/AdverseEvent/AdverseEventCtcTerm/universal-term">(CTCAE)</xsl:if>
                                                            <xsl:if test="AdverseEventReport/AdverseEvent/AdverseEventMeddraLowLevelTerm/universalTerm">(MedDRA)</xsl:if>
                                                        </fo:inline>
													</fo:block>
														
														
														<xsl:for-each select="AdverseEventReport/AdverseEvent">
																
																<xsl:choose>
																	<xsl:when test="substring(gridId,1,3) = 'PRY'">
																	 <fo:block xsl:use-attribute-sets="normal">
																		Primary AE:<fo:block/>
																		<xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/>
																		<xsl:value-of select="AdverseEventMeddraLowLevelTerm/universalTerm"/><fo:block/>
																		<xsl:value-of select="grade"/><fo:block/>
																		<xsl:value-of select="comments"/><fo:block/>
																	 </fo:block>
																	</xsl:when>	
																	<xsl:otherwise>
																	 <fo:block xsl:use-attribute-sets="normal">
																		AE <xsl:number format="1 "/>:<fo:block/>
																		<xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/>
                                                                        <xsl:value-of select="AdverseEventMeddraLowLevelTerm/universalTerm"/><fo:block/>
																		<xsl:value-of select="grade"/><fo:block/>
																		<xsl:value-of select="comments"/><fo:block/>																		
																	 </fo:block>
																	</xsl:otherwise>	
																</xsl:choose>
																<fo:block/>
																
																<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
														</xsl:for-each>
														<fo:block xsl:use-attribute-sets="normal">
															Description of event:<fo:block/>
															<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/eventDescription"/>		
														</fo:block>	
														<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
														<fo:block xsl:use-attribute-sets="normal">
															Present Status:<fo:block/>
										  		            <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'INTERVENTION_CONTINUES'">Intervention for AE Continues</xsl:if>
											                <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'RECOVERING'">Recovering/Resolving</xsl:if>
											                <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'RECOVERED_WITH_SEQUELAE'">Recovered/Resolved with Sequelae</xsl:if>
											                <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'RECOVERED_WITHOUT_SEQUELAE'">Recovered/Resolved without Sequelae</xsl:if>
											                <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'NOT_RECOVERED'">Not recovered/Not resolved</xsl:if>
											                <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus = 'DEAD'">Fatal/Died</xsl:if>		
										                </fo:block>																								
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="50mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block>
														<fo:inline font-size="6.5">6. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Relevant Tests/Laboratory Data, Including Dates </fo:inline>
													</fo:block>
													<xsl:for-each select="AdverseEventReport/Lab">
														<fo:block xsl:use-attribute-sets="normal">
															<xsl:value-of select="labTerm/term"/> 
															<xsl:value-of select="other"/>
															<fo:block/>
															Base Line value :
															
															<xsl:value-of select="baseline/value"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="units"/>
															<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															(<xsl:call-template name="standard_date">
																        <xsl:with-param name="date" select="baseline/date"/>
										   						</xsl:call-template>)
															<fo:block/>
														</fo:block>
														<fo:block xsl:use-attribute-sets="normal">
															Worst value :
															<xsl:value-of select="nadir/value"/>  <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text><xsl:value-of select="units"/>
															<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															(<xsl:call-template name="standard_date">
																        <xsl:with-param name="date" select="nadir/date"/>
										   						</xsl:call-template>)
															<fo:block/>
														</fo:block>
														<fo:block xsl:use-attribute-sets="normal">
															Recovery value :
															<xsl:value-of select="recovery/value"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="units"/>
															<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															(<xsl:call-template name="standard_date">
																        <xsl:with-param name="date" select="recovery/date"/>
										   						</xsl:call-template>)
															<fo:block/>
														</fo:block>
														<fo:block><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>														
													</xsl:for-each>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="48mm">
												<fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="5">
													<fo:block font-size="6.5" font-style="italic">
														<fo:inline font-size="6.5" font-style="normal">7. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label" font-style="normal">Other Relevant History, Including Preexisting Medical Conditions </fo:inline>
														<fo:inline font-size="6.5" font-style="italic">(e.g., allergies,race, </fo:inline>
														<fo:block/><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>
														pregnancy, smoking and alcohol use, hepatic/renal dysfunction, etc.) 
													</fo:block>
													<fo:block xsl:use-attribute-sets="normal"><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></fo:block>
													<fo:block xsl:use-attribute-sets="normal">Race: <xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/race"/></fo:block>


                                                    <xsl:if test="AdverseEventReport/DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm != '' or
                                                                  AdverseEventReport/DiseaseHistory/otherPrimaryDisease != '' or
                                                                  AdverseEventReport/DiseaseHistory/StudyCondition/Condition/conditionName != '' or
                                                                  AdverseEventReport/DiseaseHistory/MeddraStudyDisease/LowLevelTerm/meddraTerm != ''">
                                                        <fo:block xsl:use-attribute-sets="normal">Disease:
                                                            <xsl:value-of select="AdverseEventReport/DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/>
                                                            <xsl:value-of select="AdverseEventReport/DiseaseHistory/otherPrimaryDisease"/>
                                                            <xsl:value-of select="AdverseEventReport/DiseaseHistory/StudyCondition/Condition/conditionName"/>
                                                            <xsl:value-of select="AdverseEventReport/DiseaseHistory/MeddraStudyDisease/LowLevelTerm/meddraTerm"/>
                                                        </fo:block>

                                                    </xsl:if>

                                                    <xsl:if test="AdverseEventReport/DiseaseHistory/AnatomicSite/name">
                                                        <fo:block xsl:use-attribute-sets="normal">Disease Site: <xsl:value-of select="AdverseEventReport/DiseaseHistory/AnatomicSite/name"/>
                                                        <xsl:if test="AdverseEventReport/DiseaseHistory/otherPrimaryDiseaseSite">
                                                            <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>(<xsl:value-of select="AdverseEventReport/DiseaseHistory/otherPrimaryDiseaseSite"/>)
                                                        </xsl:if>
                                                        </fo:block>
                                                    </xsl:if>

                                                    <xsl:if test="AdverseEventReport/DiseaseHistory/diagnosisDate/monthString != '' or AdverseEventReport/DiseaseHistory/diagnosisDate/yearString != ''">
                                                        <fo:block xsl:use-attribute-sets="normal">Date of initial diagnosis:
                                                            <xsl:value-of select="AdverseEventReport/DiseaseHistory/diagnosisDate/monthString"/>/<xsl:value-of select="AdverseEventReport/DiseaseHistory/diagnosisDate/yearString"/>
                                                        </fo:block>
                                                    </xsl:if>

                                                    <xsl:if test="AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite">
                                                        <fo:block xsl:use-attribute-sets="normal">Metastatic site:
                                                            <xsl:for-each select="AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite">
                                                                    <xsl:value-of select="AnatomicSite/name"/>
                                                                    <xsl:if test="otherSite">:<xsl:value-of select="otherSite"/></xsl:if>
                                                                    <xsl:if test="position() != last()"><xsl:text disable-output-escaping="yes">,&amp;#160;</xsl:text><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></xsl:if>
                                                            </xsl:for-each>
                                                        </fo:block>
                                                    </xsl:if>

                                                    <xsl:if test="AdverseEventReport/SAEReportPreExistingCondition">
                                                        <fo:block xsl:use-attribute-sets="normal">Preexisting Conditions:
                                                                <xsl:for-each select="AdverseEventReport/SAEReportPreExistingCondition">
                                                                    <xsl:value-of select="PreExistingCondition/text"/>
                                                                    <xsl:if test="other"><xsl:value-of select="other"/></xsl:if>
                                                                    <xsl:if test="position() != last()"><xsl:text disable-output-escaping="yes">,&amp;#160;</xsl:text><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text></xsl:if>
                                                                </xsl:for-each>
                                                        </fo:block>
                                                    </xsl:if>
                                                    
                                                    <xsl:if test="AdverseEventReport/SAEReportPriorTherapy">
                                                        <fo:block xsl:use-attribute-sets="normal">Prior Therapies:
                                                                <xsl:for-each select="AdverseEventReport/SAEReportPriorTherapy">
                                                                    <xsl:value-of select="PriorTherapy/text"/>
                                                                    <xsl:if test="PriorTherapyAgent">
                                                                        <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
                                                                        (Agents:
                                                                        <xsl:for-each select="PriorTherapyAgent">
                                                                            <fo:inline font-size="6.5pt" font-style="italic"><xsl:value-of select="ChemoAgent/name"/></fo:inline>
                                                                            <xsl:if test="position() != last()"><xsl:text disable-output-escaping="yes">,&amp;#160;</xsl:text></xsl:if>
                                                                            <xsl:if test="position() = last()">)</xsl:if>
                                                                        </xsl:for-each>
                                                                    </xsl:if>
                                                                    <xsl:if test="position() != last()">
                                                                        <xsl:text disable-output-escaping="yes">,&amp;#160;</xsl:text>
                                                                        <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
                                                                    </xsl:if>
                                                                </xsl:for-each>
                                                        </fo:block>
                                                    </xsl:if>

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
										<xsl:variable name="iter" select="0"/>						
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
													<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
													<fo:block>
														<xsl:variable name="iter" select="$iter+1"/>
														<fo:inline font-size="6.5pt" ># <xsl:number format="1 "/> </fo:inline>
														<fo:inline font-size="6.5pt" >  
															<xsl:value-of select="StudyAgent/Agent/name"/>
															<xsl:value-of select="StudyAgent/otherAgent"/>
														</fo:inline>
													</fo:block>
													</xsl:for-each>
												</fo:table-cell>
											</fo:table-row>																						
											<fo:table-row height="14mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">			
																							
													<fo:block>
														<fo:inline font-size="6.5pt">2. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Dose, Frequency and Route Used </fo:inline>
													</fo:block>
													
											  		<fo:block xsl:use-attribute-sets="normal" > 
											  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/TreatmentAssignment/description"/>
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
													<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
														<fo:block>
															<fo:inline font-size="6.5pt" ># <xsl:number format="1 "/></fo:inline>
															<fo:inline font-size="6.5pt" >  
																	<xsl:call-template name="standard_date">
																	        <xsl:with-param name="date" select="../firstCourseDate"/>
											   						</xsl:call-template>
											   						<xsl:text disable-output-escaping="yes">&amp;#160;   </xsl:text>
												   						to 
												   					<xsl:choose>	
												   						<xsl:when test="lastAdministeredDate">
																			<xsl:call-template name="standard_date">
																			        <xsl:with-param name="date" select="lastAdministeredDate"/>
													   						</xsl:call-template>											   																				
																		</xsl:when>
																		<xsl:otherwise>Unknown</xsl:otherwise>
																	</xsl:choose>
															</fo:inline>
														</fo:block>													
													</xsl:for-each>

												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="14mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block>
														<fo:inline font-size="6.5pt">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Diagnosis for Use </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Indication)</fo:inline>
													</fo:block>
													<!--<xsl:for-each select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/CtepStudyDisease">-->
														<fo:block>
															<!--<fo:inline font-size="6.5pt" text-decoration="underline"># <xsl:number format="1 "/> </fo:inline>-->
															<!--
															<fo:inline font-size="6.5pt">  
																<xsl:value-of select="DiseaseTerm/ctepTerm"/>
															</fo:inline>
															-->
															<fo:inline font-size="6.5pt">  
																<xsl:value-of select="AdverseEventReport/DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/>
																<xsl:value-of select="AdverseEventReport/DiseaseHistory/StudyCondition/Condition/conditionName"/>
																<xsl:value-of select="AdverseEventReport/DiseaseHistory/MeddraStudyDisease/LowLevelTerm/meddraTerm"/>
															</fo:inline>
														</fo:block>
													<!--</xsl:for-each>-->
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2" number-rows-spanned="2">													
													<fo:block font-size="6.5pt" font-weight="bold">
														<fo:inline font-size="6.5pt" font-weight="normal">5. </fo:inline>
														<fo:inline>Event Abated After Use </fo:inline>
														<fo:block/><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>Stopped or Dose Reduced?
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >
														
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'YES'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
													Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'NO'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
													No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'NA'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>													
													</xsl:choose>														
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
													<fo:block/>
													<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">										
														<fo:block>
															<xsl:if test="lotNumber">
																<fo:inline font-size="6.5pt" ># <xsl:number format="1 "/> <xsl:value-of select="lotNumber"/></fo:inline>
																<fo:inline width="10mm" font-size="6.5pt" >  </fo:inline>
															</xsl:if>
														</fo:block>
													</xsl:for-each>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt" >n/a</fo:inline>
														<fo:inline width="10mm" font-size="6.5pt" text-decoration="underline">  </fo:inline>
													</fo:block>

												</fo:table-cell>												
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2" number-rows-spanned="2">													
													<fo:block font-size="6.5pt" font-weight="bold">
														<fo:inline font-size="6.5pt" font-weight="normal">8. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Event Reappeared After </fo:inline>
														<fo:block/><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>Reintroduction?
													</fo:block>
													<fo:block font-size="6.5pt">
														<fo:inline font-size="6.5pt" >
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'YES'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
													Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'NO'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
													No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'NA'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>													
													</xsl:choose>	
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
													<fo:block xsl:use-attribute-sets="normal">n/a</fo:block>	
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="20mm">
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">10. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Concomitant Medical Products and Therapy Dates </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Exclude treatment of event) </fo:inline>
													</fo:block>
													<fo:block xsl:use-attribute-sets="normal">
											  			<xsl:for-each select="AdverseEventReport/ConcomitantMedication">
												  				<xsl:value-of select="name"/>  
												  				<xsl:if test="startDate/monthString">
												  					(<xsl:value-of select="startDate/monthString"/>/<xsl:value-of select="startDate/yearString"/>)
												  				</xsl:if>
												  			<fo:block/>
												  		</xsl:for-each>													
													</fo:block>													
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="5" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">D. SUSPECT MEDICAL DEVICE
													</fo:block>
												</fo:table-cell>
											</fo:table-row>		
											<fo:table-row>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">1. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Brand Name <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text> </fo:inline>
														<fo:inline xsl:use-attribute-sets="normal"><xsl:value-of select="AdverseEventReport/MedicalDevice/brandName"/></fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>																					
											<fo:table-row>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">2. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Common Device Name <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text> </fo:inline>
														<fo:inline xsl:use-attribute-sets="normal"><xsl:value-of select="AdverseEventReport/MedicalDevice/commonName"/></fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="10mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">3. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Manufacturer Name, City and State </fo:inline>
													</fo:block>
													<fo:block xsl:use-attribute-sets="normal">
														<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerName"/> 
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
                                                        <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerCity"/>
                                                        <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
                                                        <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerState"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="1">													
													<fo:block>
														<fo:inline font-size="6.5pt">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Model # </fo:inline>
													</fo:block>
														<fo:block xsl:use-attribute-sets="normal">
													   		<xsl:value-of select="AdverseEventReport/MedicalDevice/modelNumber"/>
													    </fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block xsl:use-attribute-sets="label">Lot # </fo:block>
														<fo:block xsl:use-attribute-sets="normal">
													   		<xsl:value-of select="AdverseEventReport/MedicalDevice/lotNumber"/>
													    </fo:block>														
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2" number-rows-spanned="3">													
													<fo:block>
														<fo:inline font-size="6.5pt">5. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">
														Operator of Device </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/DeviceOperator = 'HEALTH_PROFESSIONAL'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>	
													Health Professional </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/DeviceOperator = 'PATIENT'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>		
													Lay User/Patient </fo:inline>
													</fo:block>
													<fo:block>
														<fo:inline font-size="6.5pt">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/DeviceOperator = 'OTHER'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>	
													Other: </fo:inline>
													</fo:block>
													<fo:block font-size="6.5">
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														if other , text with under line 
													</fo:block>													
												</fo:table-cell>
											</fo:table-row>												
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="1">													
													<fo:block xsl:use-attribute-sets="label">Catalog # </fo:block>
														<fo:block xsl:use-attribute-sets="normal">
													   		<xsl:value-of select="AdverseEventReport/MedicalDevice/catalogNumber"/>
													    </fo:block>														
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block font-size="6.5" font-style="italic">
														<fo:inline xsl:use-attribute-sets="label" font-style="normal">Expiration Date</fo:inline>
														<fo:block/>(mm/dd/yyyy)
														<fo:block/>
														<fo:inline xsl:use-attribute-sets="normal">
															<xsl:call-template name="standard_date">
															        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/expirationDate"/>
									   						</xsl:call-template>
													    </fo:inline>														
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="1">													
													<fo:block xsl:use-attribute-sets="label">Serial # </fo:block>													
													<fo:block xsl:use-attribute-sets="normal">
													   		<xsl:value-of select="AdverseEventReport/MedicalDevice/serialNumber"/>														
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block xsl:use-attribute-sets="label">Other #</fo:block>
														<fo:block xsl:use-attribute-sets="normal">
													   		<xsl:value-of select="AdverseEventReport/MedicalDevice/otherNumber"/>														
													    </fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt">6. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">If Implanted, Give Date </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(mm/dd/yyyy)  </fo:inline>
													</fo:block>
											  		<fo:block xsl:use-attribute-sets="normal" > 
														<xsl:call-template name="standard_date">
														        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/implantedDate"/>
								   						</xsl:call-template>
											  		</fo:block> 													
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block>
														<fo:inline font-size="6.5pt">7. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">If Explanted, Give Date </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(mm/dd/yyyy)  </fo:inline>
													</fo:block>
						  							<fo:block xsl:use-attribute-sets="normal" > 
														<xsl:call-template name="standard_date">
														        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/explantedDate"/>
								   						</xsl:call-template>
											  		</fo:block>							
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="9mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">8. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Is this a Single-use Device that was Reprocessed and Reused on a Patient? </fo:inline>
													</fo:block>
													<fo:block font-size="6.5">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/DeviceReprocessed = 'YES'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>													
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/DeviceReprocessed = 'NO'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>	
														No
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="12mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">9. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">If Yes to Item No. 8, Enter Name and Address of Reprocessor </fo:inline>
													</fo:block>
											  		<fo:block xsl:use-attribute-sets="normal" > 
											  			<xsl:value-of select="AdverseEventReport/MedicalDevice/reprocessorName"/> <fo:block/>
											  			<xsl:value-of select="AdverseEventReport/MedicalDevice/reprocessorAddress"/> 
											  		</fo:block> 													
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="9mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">10. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Device Available for Evaluation? </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Do not send to FDA) </fo:inline>
													</fo:block>
													<fo:block font-size="6.5">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/EvaluationAvailability = 'YES'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>	
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/EvaluationAvailability = 'NO'">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>	
														No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													<xsl:choose>
														<xsl:when test="AdverseEventReport/MedicalDevice/returnedDate">
															[x]
														</xsl:when>
														<xsl:otherwise>
															[ ]
														</xsl:otherwise>
													</xsl:choose>
														Returned to Manufacturer on: 
													<xsl:call-template name="standard_date">
													        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/returnedDate"/>
							   						</xsl:call-template>														
													</fo:block>
													<fo:block font-size="6.5pt" font-style="italic" text-align="right">
													(mm/dd/yyyy)  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text></fo:block>
												</fo:table-cell>
											</fo:table-row>												
											<fo:table-row height="16mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													
													<fo:block>
														<fo:inline font-size="6.5pt">11. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Concomitant Medical Products and Therapy Dates </fo:inline>
														<fo:inline font-size="6.5pt" font-style="italic">(Exclude treatment of event) </fo:inline>
													</fo:block>
													<fo:block xsl:use-attribute-sets="normal">
											  			<xsl:for-each select="AdverseEventReport/ConcomitantMedication">
												  				<xsl:value-of select="name"/>  
												  				<xsl:if test="startDate/monthString">
												  					(<xsl:value-of select="startDate/monthString"/>/<xsl:value-of select="startDate/yearString"/>)
												  				</xsl:if>
												  				
												  			<fo:block/>
												  		</xsl:for-each>													
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="5" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">E. INITIAL REPORTER
													</fo:block>
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="cell-with-right-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt">1. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Name and Address </fo:inline>

													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="3">													
													<fo:block>
														<fo:inline xsl:use-attribute-sets="label">Phone # 
												  			<xsl:for-each select="AdverseEventReport/Reporter/ContactMechanism">
												  				<xsl:if test="key = 'phone'">
												  					<xsl:value-of select="value"/>
												  				</xsl:if>
												  			</xsl:for-each>														
														</fo:inline>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>																						
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="5">													 
														<fo:block xsl:use-attribute-sets="normal">
															<xsl:value-of select="AdverseEventReport/Reporter/firstName"/><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															<xsl:value-of select="AdverseEventReport/Reporter/lastName"/>
                                                            <fo:block/>
															<xsl:value-of select="AdverseEventReport/Reporter/address/street"/>
                                                            <fo:block/>
															<xsl:value-of select="AdverseEventReport/Reporter/address/city"/><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															<xsl:value-of select="AdverseEventReport/Reporter/address/state"/><xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
															<xsl:value-of select="AdverseEventReport/Reporter/address/zip"/>
														</fo:block>														
												</fo:table-cell>
											</fo:table-row>	
											<fo:table-row height="8mm">
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="1">													
													<fo:block>
														<fo:inline font-size="6.5pt">2. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Health Professional? </fo:inline>
													</fo:block>
													<fo:block font-size="6.5">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													[x]
														Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
													[ ]
														No
													</fo:block>													
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block>
														<fo:inline font-size="6.5pt">3. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Occupation </fo:inline>
													</fo:block>
												</fo:table-cell>
												<fo:table-cell xsl:use-attribute-sets="full-border"  number-columns-spanned="2">													
													<fo:block font-size="6.5pt" font-weight="bold">
														<fo:inline font-size="6.5pt" font-style="normal">4. </fo:inline>
														<fo:inline xsl:use-attribute-sets="label">Initial Reporter Also Sent </fo:inline> <fo:block/>
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text>Report to FDA 
													</fo:block>
													<fo:block font-size="6.5">
													<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														[ ]	Yes
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														[ ]No
														<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>
														[ ]Unk.
													</fo:block>														
												</fo:table-cell>
											</fo:table-row>												
										</fo:table-body>									
									</fo:table>	  
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>

                    <fo:block padding-top="15px"></fo:block>

                    <fo:table>
                        <fo:table-column column-width="33%" />
                        <fo:table-column column-width="34%" />
                        <fo:table-column column-width="33%" />
                        <fo:table-body>
                            <fo:table-row>
                                <fo:table-cell>
                                    <fo:block font-size="12" font-weight="bold">MEDWATCH</fo:block>
                                    <fo:block font-size="9" font-weight="bold">FORM FDA 3500A (10/05) <fo:inline font-style="italic" font-weight="normal">(continued)</fo:inline></fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="center">
                                    <fo:block xsl:use-attribute-sets="label">Page ____ of ____</fo:block>
                                </fo:table-cell>
                                <fo:table-cell  >
                                        <fo:table border="1pt solid black">
                                            <fo:table-column column-width="100%" />
                                            <fo:table-body>
                                                <fo:table-row height="10">
                                                    <fo:table-cell xsl:use-attribute-sets="full-border" background-color="black">
                                                        <fo:block xsl:use-attribute-sets="black-heading">FDA USE OLY</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                                <fo:table-row height="30">
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block font-size="6.5" padding-top="1mm">abcc</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>
                    </fo:table>

<!-- T2 -->
					<fo:table >
						<fo:table-column column-width="50%" />
						<fo:table-column column-width="2%" />
						<fo:table-column column-width="48%" />

						<fo:table-body>
							<fo:table-row>
								<fo:table-cell>
									<fo:table border="1pt solid black">
										<fo:table-column column-width="17%" />
										<fo:table-column column-width="17%" />
										<fo:table-column column-width="16%" />
										<fo:table-column column-width="16%" />
										<fo:table-column column-width="17%" />
										<fo:table-column column-width="17%" />
										<fo:table-body>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="6" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">F. FOR USE BY USER FACILITY/IMPORTER (Devices Only)</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="3" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">
                                                        1. Check one
                                                    </fo:block>
                                                    <fo:block>
													    <fo:inline xsl:use-attribute-sets="normal">[ ] User Facility<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text>[ ] Importer</fo:inline>
                                                    </fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">2. UF/Importer Report Number</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="20mm">
												<fo:table-cell number-columns-spanned="6" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">3. User Facility or Importer Name/Address</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="10mm">
												<fo:table-cell number-columns-spanned="3" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">4. Contact Person</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="3" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">5. Phone number</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="15mm">
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">6. Date User Facility or Importer Became Aware of Event <fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">7. Type of Report</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ]Initial</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ]Follow-up # <fo:leader leader-length="40%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">8. Date of This Report <fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="1" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">9. Approximate Age of Device</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="5" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">10. Event problem Codes <fo:inline xsl:use-attribute-sets="normal">(Refer to coding manual)</fo:inline></fo:block>
													<fo:block xsl:use-attribute-sets="label">
                                                        Patient Code <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/>
                                                    </fo:block>
													<fo:block xsl:use-attribute-sets="label">
                                                        Device Code <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/>
													</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">11. Report Sent to FDA?</fo:block>
													<fo:block xsl:use-attribute-sets="label">[ ] Yes <fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text><fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
													<fo:block xsl:use-attribute-sets="label">[ ] No</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="4" xsl:use-attribute-sets="full-border" number-rows-spanned="2">
                                                    <fo:block xsl:use-attribute-sets="label">12. Location Where Event Occured</fo:block>
                                                    <fo:table>
                                                        <fo:table-body>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Hospital</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Outpatient Diagnostic Facility</fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Home</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block/></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Nursing Home</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Ambulatory Surgical Facility</fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Outpatient Tretament Facility</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block /></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell number-columns-spanned="2">
                                                                    <fo:block xsl:use-attribute-sets="normal">[ ] Other <fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                                    <fo:block xsl:use-attribute-sets="normal"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160; (Specify)</xsl:text></fo:block>
                                                                </fo:table-cell>
                                                            </fo:table-row>
                                                        </fo:table-body>
                                                    </fo:table>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">13. Report Sent to Manufacturer?</fo:block>
                                                    <fo:block xsl:use-attribute-sets="label">[ ] Yes <fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    <fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text><fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
                                                    <fo:block xsl:use-attribute-sets="label">[ ] No</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row height="20mm">
												<fo:table-cell number-columns-spanned="6" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">14. Manufacturer Name/Address</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="6" background-color="black">
													<fo:block xsl:use-attribute-sets="black-heading">G. ALL MANUFACTURERS</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="4" number-rows-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label" padding-bottom="25mm">1. Contact Office - Name/Address (and manufacturing Site for Devices)</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label" padding-bottom="7mm">2. Phone number</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1">
												<fo:table-cell number-columns-spanned="2" number-rows-spanned="4" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">3. Report Source</fo:block>
													<fo:block xsl:use-attribute-sets="normal"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text><fo:inline font-style="italic" font-weight="normal">(Check all that apply)</fo:inline></fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Foreign</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Study</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Literature</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Consumer</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Health Professional</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] User Facility</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Company Reprezentative</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Distributor</fo:block>
													<fo:block xsl:use-attribute-sets="normal">[ ] Other:</fo:block>
													<fo:block xsl:use-attribute-sets="normal" padding-top="10px"><fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="normal" padding-top="10px"><fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="normal" padding-top="10px"><fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="normal" padding-top="10px"><fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block xsl:use-attribute-sets="normal" padding-top="10px" />
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1" height="10mm">
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">4. Date Received by manufacturer <fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="2" number-rows-spanned="3" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">5.</fo:block>
													<fo:block xsl:use-attribute-sets="normal">(A)NDA # <fo:leader leader-length="25%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
													<fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">IND # <fo:leader leader-length="25%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    <fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">STN # <fo:leader leader-length="25%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    <fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">PMA/510(k) # <fo:leader leader-length="25%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    <fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">Combination Product [ ] Yes</fo:block>
                                                    <fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">Pre-1938 [ ] Yes</fo:block>
                                                    <fo:block padding-top="5mm"></fo:block>
													<fo:block xsl:use-attribute-sets="normal">OTC Product [ ] Yes</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1" height="10mm">
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">6. If IND, Give Protocol #</fo:block>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1" >
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">7. Type Of Report</fo:block>
													<fo:block xsl:use-attribute-sets="label"><xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;</xsl:text><fo:inline font-style="italic" font-weight="normal">(Check all that apply)</fo:inline></fo:block>
                                                    <fo:table>
                                                        <fo:table-body>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] 5-day</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] 30-day</fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] 7-day</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Periodic</fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] 10-day</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Initial</fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                            <fo:table-row>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] 15-day</fo:block></fo:table-cell>
                                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Follow-up # <fo:leader leader-length="25%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block></fo:table-cell>
                                                            </fo:table-row>
                                                        </fo:table-body>
                                                    </fo:table>
												</fo:table-cell>
											</fo:table-row>
											<fo:table-row xsl:use-attribute-sets="tr-height-1" height="15mm">
												<fo:table-cell number-columns-spanned="2" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">9. Manufacturers Report Number</fo:block>
												</fo:table-cell>
												<fo:table-cell number-columns-spanned="4" xsl:use-attribute-sets="full-border">
													<fo:block xsl:use-attribute-sets="label">8. Adverse Event Term(s)</fo:block>
												</fo:table-cell>
											</fo:table-row>
										</fo:table-body>
									</fo:table>
									<fo:block xsl:use-attribute-sets="normal">
													The public reporting burden for this collection of information has been estimated to average 66
minutes per response, including the time for reviewing instructions, searching existing data
sources, gathering and maintaining the data needed, and completing and reviewing the
collection of information. Send comments regarding this burden estimate or any other aspect of
this collection of information, including suggestions for reducing this burden to:
									</fo:block>
								</fo:table-cell>
								<fo:table-cell><fo:block/></fo:table-cell>
                                    <fo:table-cell>
                                        <fo:table border="1pt solid black">
                                            <fo:table-column column-width="60%" />
                                            <fo:table-column column-width="40%" />
                                            <fo:table-body>
                                                <fo:table-row xsl:use-attribute-sets="tr-height-1">
                                                    <fo:table-cell number-columns-spanned="2" background-color="black">
                                                        <fo:block xsl:use-attribute-sets="black-heading">H. DEVICE MANUFACTURERS ONLY</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" >
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">1. Type of Reportable Event</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Death</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Serious Injury</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Malfunction</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Other <fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">2. If Follow-up, What Type ?</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Correction</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Additional Information</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Response to FDA Request</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Device Evaluation</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" >
                                                    <fo:table-cell xsl:use-attribute-sets="full-border" number-rows-spanned="2">
                                                        <fo:block xsl:use-attribute-sets="label">3. Device Evaluated By Manufacturer ?</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Not returned to Manufacturer</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Yes [ ] Evaluation Summary Attached</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] No <fo:inline font-style="italic">(Attach page to explain why not) or provide code:</fo:inline></fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal"><fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">4. Device Manufacturer Date <fo:inline xsl:use-attribute-sets="normal">(mm/dd/yyyy)</fo:inline></fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" >
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">5. Labeled For Single Use ?</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Yes [ ] No</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" >
                                                    <fo:table-cell xsl:use-attribute-sets="full-border" number-columns-spanned="2">
                                                        <fo:block xsl:use-attribute-sets="label">6. Evaluation Codes <fo:inline xsl:use-attribute-sets="normal">(Refer to coding manual)</fo:inline></fo:block>
                                                        <fo:block xsl:use-attribute-sets="label">
                                                            Method <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/>
                                                        </fo:block>
                                                        <fo:block xsl:use-attribute-sets="label">
                                                            Results <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/>
                                                        </fo:block>
                                                        <fo:block xsl:use-attribute-sets="label">
                                                            Conclusions <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/> - <fo:leader leader-length="15%" leader-pattern="rule" rule-thickness="0.5pt"/>
                                                        </fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" >
                                                    <fo:table-cell xsl:use-attribute-sets="full-border" number-rows-spanned="2">
                                                        <fo:block xsl:use-attribute-sets="label">7.</fo:block>
                                                        <fo:table>
                                                            <fo:table-body>
                                                                <fo:table-row>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Recall</fo:block></fo:table-cell>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Notification</fo:block></fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Repair</fo:block></fo:table-cell>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Inspection</fo:block></fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Replace</fo:block></fo:table-cell>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Patient Monitoring</fo:block></fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Relabeling</fo:block></fo:table-cell>
                                                                    <fo:table-cell><fo:block xsl:use-attribute-sets="normal">[ ] Modification / Adjustment</fo:block></fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell number-columns-spanned="2">
                                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Other <fo:leader leader-length="60%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                                        <fo:block xsl:use-attribute-sets="normal"><fo:leader leader-length="100%" leader-pattern="rule" rule-thickness="0.5pt"/></fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                            </fo:table-body>
                                                        </fo:table>
                                                    </fo:table-cell>
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">8. Usage of Device</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Initial Use of Device</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Refuse</fo:block>
                                                        <fo:block xsl:use-attribute-sets="normal">[ ] Unknown</fo:block>

                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1">
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">9. If action reported to FDA under 21 USC 360i(f), list correction/removal reporting number:</fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>

                                                <fo:table-row xsl:use-attribute-sets="tr-height-1" height="100mm">
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="label">10. [ ] Additional Manufacturer Narrative</fo:block>
                                                    </fo:table-cell>
                                                    <fo:table-cell xsl:use-attribute-sets="full-border">
                                                        <fo:block xsl:use-attribute-sets="normal">and / or <fo:inline xsl:use-attribute-sets="label">11. [ ] Corrected data</fo:inline></fo:block>
                                                    </fo:table-cell>
                                                </fo:table-row>


                                            </fo:table-body>
                                        </fo:table>
                                        <fo:table>
                                            <fo:table-column column-width="50%"/>
                                            <fo:table-column column-width="50%"/>
                                            <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal">Department of Health and Human Services Food and Drug Administration - MedWatch 10903 New Hampshire Avenue Building 22, Mail Stop 4447 Silver Spring, MD 20993-0002</fo:block></fo:table-cell>
                                                <fo:table-cell><fo:block xsl:use-attribute-sets="normal"><fo:inline xsl:use-attribute-sets="label">OMB Statement:</fo:inline> "An agency may not conduct or sponsor, and a person is not required to respond to, a collection of information unless it displays a currently valid OMB control number."</fo:block></fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell number-columns-spanned="2"><fo:block xsl:use-attribute-sets="label">Please DO NOT RETURN this form to this address.</fo:block></fo:table-cell>
                                            </fo:table-row>
                                            </fo:table-body>
                                        </fo:table>
                                    </fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
<!-- T2 -->

				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>

	<xsl:template name="standard_date">
		<xsl:param name="date" />
		<xsl:if test="$date">
			<!-- Month -->
			<xsl:value-of select="substring($date, 6, 2)" />
			<xsl:text>/</xsl:text>		
			<!-- Day -->
			<xsl:value-of select="substring($date, 9, 2)" />
			<xsl:text>/</xsl:text>
			<!-- Year -->
			<xsl:value-of select="substring($date, 1, 4)" />
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
