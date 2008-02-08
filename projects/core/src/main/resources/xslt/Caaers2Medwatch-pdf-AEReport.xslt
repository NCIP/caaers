<?xml version="1.0"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:java="http://xml.apache.org/xslt/java"
	exclude-result-prefixes="java">

	<xsl:output method="xml" />

	<xsl:attribute-set name="sub-head">
		<xsl:attribute name="font-family">arial</xsl:attribute>
		<xsl:attribute name="font-size">10pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>

	</xsl:attribute-set>

	<xsl:attribute-set name="label">
		<xsl:attribute name="font-family">arial</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
		<xsl:attribute name="font-weight">bold</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="normal">
		<xsl:attribute name="font-family">arial</xsl:attribute>
		<xsl:attribute name="font-size">8pt</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="tr-height-1">
		<xsl:attribute name="height">4mm</xsl:attribute>
	</xsl:attribute-set>


	<xsl:template match="/">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">

			<fo:layout-master-set>
				<fo:simple-page-master master-name="A4"
					margin-left="2mm" margin-top="2mm" margin-right="0.25in">
					<fo:region-body margin-top="0.5in" />
					<fo:region-before extent="0.25in" />
					<fo:region-after extent="0.25in" />
				</fo:simple-page-master>


			</fo:layout-master-set>

			<fo:page-sequence master-reference="A4">

				<fo:static-content flow-name="xsl-region-after">

					<fo:block font-size="8pt" font-family="arial"
						text-align-last="right" display-align="right">
						Page
						<fo:page-number />
						of
						<fo:page-number-citation
							ref-id="content_terminator" />
					</fo:block>
				</fo:static-content>

				<fo:static-content flow-name="xsl-region-before">
					<fo:table>
						<fo:table-column column-width="25%" />
						<fo:table-column column-width="50%" />
						<fo:table-column column-width="25%" />

						<fo:table-body>
							<fo:table-row>

								<fo:table-cell>
									<fo:block font-weight="bold"
										font-size="8pt" font-family="arial" text-align-last="left"
										display-align="left">
										Run Date :
										<xsl:value-of
											select="java:format(java:java.text.SimpleDateFormat.new
						('MM/d/yyyy h:mm:ss a '), java:java.util.Date.new())" />
									</fo:block>
								</fo:table-cell>

								<fo:table-cell>
									<fo:block font-weight="bold"
										font-size="12pt" font-family="arial" text-align-last="center"
										display-align="center">
										MedWatch Online Voluntary
										Submission Form 3500
									</fo:block>
									<fo:block font-weight="bold"
										font-size="12pt" font-family="arial" text-align-last="center"
										display-align="center">
										Generated from CaAERS
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
						</fo:table-body>
					</fo:table>
					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>
				</fo:static-content>

				<fo:flow flow-name="xsl-region-body">

									<fo:block>
										<xsl:text
											disable-output-escaping="yes">
											&amp;#160;
										</xsl:text>
									</fo:block>
									
					<fo:block margin-left="4mm">
						<fo:inline xsl:use-attribute-sets="normal">
							Complete AdEERS Report is available :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							
						</fo:inline>
					</fo:block>

					<fo:block margin-left="4cm">
						<fo:inline xsl:use-attribute-sets="normal">
							Ticket # :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							<xsl:value-of select="AdverseEventReport/Summary[@id='Ticket number']/value"/> 
						</fo:inline>
					</fo:block>

					<fo:block margin-left="4cm">
						<fo:inline xsl:use-attribute-sets="normal">
							Report Type :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							
						</fo:inline>
					</fo:block>

					<fo:block margin-left="4cm">
						<fo:inline xsl:use-attribute-sets="normal">
							Amendment # :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							
						</fo:inline>
					</fo:block>
					<fo:block margin-left="4cm">
						<fo:inline xsl:use-attribute-sets="normal">
							Protocol # :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/Identifier/value"/>
							-
							<xsl:value-of
								select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/shortTitle" />
						</fo:inline>

					</fo:block>


					<fo:block space-after="1pt">
						<fo:leader leader-length="95%"
							leader-pattern="rule" rule-thickness="2pt" />
					</fo:block>

					<fo:block xsl:use-attribute-sets="sub-head">
						A. PATIENT INFORMATION
					</fo:block>
					<fo:block>
						<xsl:text disable-output-escaping="yes">
							&amp;#160;
						</xsl:text>
					</fo:block>
					<fo:table>
						<fo:table-column column-width="30%" />
						<fo:table-column column-width="30%" />


						<fo:table-body>
							<fo:table-row
								xsl:use-attribute-sets="tr-height-1">
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										1. Patient Identifier :
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/Identifier/value"/>
							  		</fo:block>  
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										2. Date of Birth :
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
										<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/month"/>/
										<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/day"/>/
										<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/year"/>					  		
						  		</fo:block> 
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										3. Sex :
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/gender"/>
							  		</fo:block> 
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										4. Weight :
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal">
										<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/quantity"/>  <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
										<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/unit"/>
									</fo:block>
								</fo:table-cell>

							</fo:table-row>


							<fo:table-row>
								<fo:table-cell>
									<fo:block>
										<xsl:text
											disable-output-escaping="yes">
											&amp;#160;
										</xsl:text>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

						</fo:table-body>
					</fo:table>

					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>

					<fo:block xsl:use-attribute-sets="sub-head">
						B. ADVERSE EVENT, PRODUCT PROBLEM OR ERROR
					</fo:block>
					<fo:block>
						<xsl:text disable-output-escaping="yes">
							&amp;#160;
						</xsl:text>
					</fo:block>
					<xsl:for-each
						select="AdverseEventReport/AdverseEvent">
						<fo:table>
							<fo:table-column column-width="30%" />
							<fo:table-column column-width="30%" />
							<fo:table-body>

								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="label">
											Attribute to
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="label">
											<xsl:value-of
												select="grade" />
											<xsl:text
												disable-output-escaping="yes">
												&amp;#160; &amp;#160;
											</xsl:text>
											<xsl:value-of
												select="AdverseEventCtcTerm/universal-term" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
						<fo:block space-after="0.2pt">
							<fo:leader leader-length="95%"
								leader-pattern="rule" rule-thickness="0.2pt" />
						</fo:block>
						<fo:table>
							<fo:table-column column-width="30%" />
							<fo:table-column column-width="30%" />
							<fo:table-body>

								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											Concomitant medications
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">

										</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<xsl:for-each
									select="ConcomitantMedicationAttribution">
									<fo:table-row
										xsl:use-attribute-sets="tr-height-1">
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:text
													disable-output-escaping="yes">
													&amp;#160;
													&amp;#160;
												</xsl:text>
												<xsl:value-of
													select="ConcomitantMedication/Agent/name" />
												<xsl:value-of
													select="ConcomitantMedication/other" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:value-of
													select="attribution" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>

								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											Other causes
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<xsl:for-each
									select="OtherCauseAttribution">
									<fo:table-row
										xsl:use-attribute-sets="tr-height-1">
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:text
													disable-output-escaping="yes">
													&amp;#160;
													&amp;#160;
												</xsl:text>
												<xsl:value-of
													select="OtherCause/text" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:value-of
													select="attribution" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>

								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											Course
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

								<xsl:for-each
									select="CourseAgentAttribution">
									<fo:table-row
										xsl:use-attribute-sets="tr-height-1">
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:text
													disable-output-escaping="yes">
													&amp;#160;
													&amp;#160;
												</xsl:text>
												<xsl:value-of
													select="CourseAgent/StudyAgent/Agent/name" />
											</fo:block>
										</fo:table-cell>
										<fo:table-cell>
											<fo:block
												xsl:use-attribute-sets="normal">
												<xsl:value-of
													select="attribution" />
											</fo:block>
										</fo:table-cell>
									</fo:table-row>
								</xsl:for-each>



								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">

										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
										</fo:block>
									</fo:table-cell>
								</fo:table-row>


							</fo:table-body>
						</fo:table>

					</xsl:for-each>

					<fo:block xsl:use-attribute-sets="normal">
						6. Relevant Tests / Laboratory Data
					</fo:block>
					<fo:block>
						<xsl:text disable-output-escaping="yes">
							&amp;#160;
						</xsl:text>
					</fo:block>


					<fo:table>
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />
						<fo:table-column column-width="10%" />

						<fo:table-body>

							<fo:table-row
								xsl:use-attribute-sets="tr-height-1">
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Lab
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Baseline date
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Value
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Worst Date
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Value
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Recovery/Latest Date
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="label">
										Value
									</fo:block>
								</fo:table-cell>

							</fo:table-row>

							<xsl:for-each
								select="AdverseEventReport/Lab">
								<fo:table-row
									xsl:use-attribute-sets="tr-height-1">
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:value-of select="name" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:call-template
												name="standard_date">
												<xsl:with-param
													name="date" select="baseline/date" />
											</xsl:call-template>
										</fo:block>

									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:value-of
												select="baseline/value" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:call-template
												name="standard_date">
												<xsl:with-param
													name="date" select="nadir/date" />
											</xsl:call-template>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:value-of
												select="nadir/value" />
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:call-template
												name="standard_date">
												<xsl:with-param
													name="date" select="recovery/date" />
											</xsl:call-template>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell>
										<fo:block
											xsl:use-attribute-sets="normal">
											<xsl:value-of
												select="recovery/value" />
										</fo:block>
									</fo:table-cell>

								</fo:table-row>
							</xsl:for-each>
						</fo:table-body>
					</fo:table>

					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>

					<fo:block xsl:use-attribute-sets="normal">
						7. Other Relevant History, Including Pre-existing Medical Conditions (e.g. allergies, race, pregnancy, smoking and alchohol use,liver / kidney problems, etc.)
					</fo:block>
					<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
					<xsl:for-each select="AdverseEventReport/SAEReportPreExistingCondition">
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:value-of select="PreExistingCondition/text"/><xsl:value-of select="other"/>
							  		</fo:block> 				
					</xsl:for-each>
					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>
					<fo:block xsl:use-attribute-sets="sub-head">
						C. PRODUCT AVAILABILITY
					</fo:block>
					<fo:block>
						<fo:inline xsl:use-attribute-sets="normal">
							1. Product Available for Evaluation?
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							<xsl:value-of
								select="AdverseEventReport/MedicalDevice/EvaluationAvailability" />
						</fo:inline>
					</fo:block>
				
					<fo:block break-before="page" xsl:use-attribute-sets="sub-head">
														<fo:block>
										<xsl:text
											disable-output-escaping="yes">
											&amp;#160;
										</xsl:text>
									</fo:block>
						D. SUSPECT PRODUCT(S)
					</fo:block>
					<fo:block>
						<fo:inline xsl:use-attribute-sets="normal">
							1. Name, Strength, Manufacturer (from product label)
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							
						</fo:inline>
					</fo:block>
					<fo:block>
						<fo:inline xsl:use-attribute-sets="normal">
							Treatment Assignment Code :
						</fo:inline>
						<fo:inline xsl:use-attribute-sets="normal">
							<xsl:value-of select="AdverseEventReport/TreatmentInformation/treatmentAssignmentCode"/>
						</fo:inline>
					</fo:block>
					<fo:block>
						<fo:inline xsl:use-attribute-sets="normal">
							Protocol Agents
						</fo:inline>

					</fo:block>
				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
																				
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Agent
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Total Dose Administered this Course
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Last Administered Date
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Comments 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Agent Adjustment
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Agent Delayed
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Delay
						  		</fo:block>      							
      						</fo:table-cell> 
		  			    </fo:table-row>
		  			  
 					<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="StudyAgent/Agent/name"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="totalDoseAdministeredThisCourse"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="lastAdministeredDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="administrationDelayAmount"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="administrationDelayUnits"/>
						  		</fo:block>      							
      						</fo:table-cell>  
		  			    </fo:table-row>
					 </xsl:for-each>
		  			</fo:table-body>
		  		</fo:table>	

				
					<fo:block space-after="1pt">
						<fo:leader leader-length="95%"
							leader-pattern="rule" rule-thickness="2pt" />
					</fo:block>
					<fo:block xsl:use-attribute-sets="sub-head">
						E. SUSPECT MEDICAL DEVICE
					</fo:block>

					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>
	  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
    				
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			1. Brand Name :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/brandName"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
 					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			2. Common Device Name :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/commonName"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			3. Manufacturer Name City and State:
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerName"/> , <xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerCity"/> , <xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerState"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>


		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			4. Model Number :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/modelNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Lot Number :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/lotNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Catalog Number :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/catalogNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Expiration Date :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/expirationDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Serial Number :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/serialNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Other Number :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/otherNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			Operator of Device :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/DeviceOperator"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			5. If Implanted, Give Date (mm/dd/yyyy):
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/implantedDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			7. If Explanted, Give Date (mm/ddyyy):
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/explantedDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			8. Is this a Single-use Device that was Reprocessed and Reused on a Patient? :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/DeviceReprocessed"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" > 
						  			9. If Yes to Item No. 8, Enter Name :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/reprocessorName"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			</fo:table-body>
		  		</fo:table>								  		  		

					
					<fo:block xsl:use-attribute-sets="sub-head">
						F. OTHER (CONCOMITANT) MEDICAL PRODUCTS 	<xsl:text disable-output-escaping="yes"> &amp;#160;</xsl:text>
						Product Names and therapy dates (exclude treatment of event)
					</fo:block>

					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>
					<fo:block xsl:use-attribute-sets="sub-head">
						G. REPORTER
					</fo:block>					

					<fo:block>
						<xsl:text disable-output-escaping="yes">
							&amp;#160;
						</xsl:text>
					</fo:block>
					<fo:table>
						<fo:table-column column-width="30%" />
						<fo:table-column column-width="30%" />


						<fo:table-body>
							<fo:table-row
								xsl:use-attribute-sets="tr-height-1">
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										Name (required)
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal">
										<xsl:value-of
											select="AdverseEventReport/Reporter/firstName" />
										<xsl:text
											disable-output-escaping="yes">
											&amp;#160;
										</xsl:text>
										<xsl:value-of
											select="AdverseEventReport/Reporter/lastName" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										Phone Number
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:for-each select="AdverseEventReport/Reporter/ContactMechanism">
							  				<xsl:if test="key = 'phone'">
							  					<xsl:value-of select="value"/>
							  				</xsl:if>
							  			</xsl:for-each>
							  		</fo:block> 
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										E-mail (need for a confirmation receipt)
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:for-each select="AdverseEventReport/Reporter/ContactMechanism">
							  				<xsl:if test="key = 'e-mail'">
							  					<xsl:value-of select="value"/>
							  				</xsl:if>
							  			</xsl:for-each>
							  		</fo:block>   
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										Physician Name
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
										<xsl:value-of select="AdverseEventReport/Physician/firstName"/> 
										<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
										<xsl:value-of select="AdverseEventReport/Physician/lastName"/>
							  		</fo:block> 
								</fo:table-cell>

							</fo:table-row>

							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										Phone
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
							  				<xsl:if test="key = 'phone'">
							  					<xsl:value-of select="value"/>
							  				</xsl:if>
							  			</xsl:for-each>
							  		</fo:block>  
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block
										xsl:use-attribute-sets="normal" margin-left="2mm">
										Email
									</fo:block>
								</fo:table-cell>
								<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
							  				<xsl:if test="key = 'e-mail'">
							  					<xsl:value-of select="value"/>
							  				</xsl:if>
							  			</xsl:for-each>
							  		</fo:block> 
								</fo:table-cell>

							</fo:table-row>
							<fo:table-row>
								<fo:table-cell>
									<fo:block>
										<xsl:text
											disable-output-escaping="yes">
											&amp;#160;
										</xsl:text>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

						</fo:table-body>
					</fo:table>

					<fo:block>
						<fo:leader leader-length="95%"
							leader-pattern="rule" />
					</fo:block>																									
					<fo:block id="content_terminator" />
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
