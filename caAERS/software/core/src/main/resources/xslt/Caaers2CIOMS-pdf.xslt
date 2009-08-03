<?xml version="1.0"?>
<xsl:stylesheet 
     version="1.0" 
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
     xmlns:fo="http://www.w3.org/1999/XSL/Format"
     xmlns:java="http://xml.apache.org/xslt/java" exclude-result-prefixes="java">

  <xsl:output method="xml"/>
  
  <xsl:attribute-set name="sub-head">
    <xsl:attribute name="font-family">arial</xsl:attribute>
    <xsl:attribute name="font-size">10pt</xsl:attribute>
    <xsl:attribute name="font-weight">bold</xsl:attribute>
    <xsl:attribute name="text-decoration">underline</xsl:attribute>
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
  <xsl:attribute-set name="tr-height-2">
    <xsl:attribute name="height">8mm</xsl:attribute>
  </xsl:attribute-set>
  
  <xsl:attribute-set name="small-cell">
    <xsl:attribute name="padding">6pt</xsl:attribute>
    <xsl:attribute name="border">0.5pt solid black</xsl:attribute>
  </xsl:attribute-set>       

  <xsl:template match="/">
  	
	<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	    
		<fo:layout-master-set>
		  	<fo:simple-page-master master-name="A4" margin-left="2mm" margin-top="2mm" margin-right="0.25in">
		    	<fo:region-body margin-top="0.5in"/>
		    	<fo:region-before extent="2in"/>
		    	<fo:region-after extent="0.5in"/>
		  	</fo:simple-page-master>


		</fo:layout-master-set>
		
		<fo:page-sequence master-reference="A4">
			
		<fo:static-content flow-name="xsl-region-after">

				<fo:block font-size="8pt" font-family="arial" text-align-last="right"> 
						Page <fo:page-number/> of <fo:page-number-citation ref-id="content_terminator"/>
				</fo:block>
		</fo:static-content>

		  
		  <fo:flow flow-name="xsl-region-body">		  	
   			  <fo:block font-size="14pt" font-family="Times New Roman" text-align-last="right">
		      	CIOMS FORM
		      </fo:block>

			  		  		
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="50%"/>
					<fo:table-column column-width="50%"/>

		  			<fo:table-body >
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="3" >
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align="center"> 
						  			SUSPECT ADVERSE REACTION REPORT 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align="center"> 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>
      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>
 		  			</fo:table-body>
		  		</fo:table>


				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>

		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="center">
			      I. REACTION INFORMATION 
			  </fo:block>

		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="20%"/>
															
		  			<fo:table-body >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			1. PATIENT INITIALS  
						  		</fo:block>      				
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			(first, last) 
						  		</fo:block> 						  					
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/firstName"/> , <xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/lastName"/>
						  		</fo:block> 
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		1a. COUNTRY
						  		</fo:block>      													  		
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									2. DATE OF BIRTH
						  		</fo:block>      							
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		2a. AGE
						  		</fo:block>      													  		
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		Years
						  		</fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:variable name="currYear" select="java:format(java:java.text.SimpleDateFormat.new ('yyyy'), java:java.util.Date.new())"/>				      
						  			<xsl:variable name="birthYear" select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/yearString"/>				      
						  			<xsl:variable name="age" select="$currYear - $birthYear"/>
						  			<xsl:value-of select="$age"/>
						  		</fo:block>
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell"  number-rows-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		3. SEX
						  		</fo:block> 
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/gender"/>   
						  		</fo:block>   													  		
      						</fo:table-cell>    
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									4-6 REACTION ONSET
						  		</fo:block>      							
      						</fo:table-cell>      						  						     						
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="4">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		8-12 CHECK ALL APPROPRIATE TO ADVERSE REACTION
						  		</fo:block>  
						  		<xsl:variable name="oc" select="AdverseEventReport/Outcome/OutcomeType"/>  

						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>  													  		
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		[ <xsl:if test="$oc='DEATH'">x</xsl:if> ] PATIENT DEAD
						  		</fo:block>  
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		[ <xsl:if test="$oc='HOSPITALIZATION'">x</xsl:if> ] INVOLVED OR PROLONGED INPATIENT HOSPITALISATION
						  		</fo:block>  
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		[ <xsl:if test="$oc='DISABILITY'">x</xsl:if> ] INVOLVED PERSISTENCE OR SIGNIFICANT DISABILITY OR INCAPACITY
						  		</fo:block>  
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		[ <xsl:if test="$oc='LIFE_THREATENING'">x</xsl:if> ] LIFE THREATENING
						  		</fo:block>  
						  								  								  								  		
      						</fo:table-cell> 
      					</fo:table-row>

					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
  
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Day
						  		</fo:block>   
						  		<fo:block xsl:use-attribute-sets="normal">   
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/dayString"/>  
						  		</fo:block>    													  		
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Month
						  		</fo:block>  
						  		<fo:block xsl:use-attribute-sets="normal">   
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/monthString"/>
						  		</fo:block>    													  		
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Year
						  		</fo:block>     
						  		<fo:block xsl:use-attribute-sets="normal">   
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/yearString"/>  
						  		</fo:block> 													  		
      						</fo:table-cell>

      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Day
						  		</fo:block>      													  		
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Month
						  		</fo:block>      													  		
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		Year
						  		</fo:block>      													  		
      						</fo:table-cell>      						
      						      						

      					</fo:table-row>

					<fo:table-row xsl:use-attribute-sets="tr-height-1" >

      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="10" number-rows-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  		7 + 13 DESCRIBE REACTION(S) (including relevant tests/lab data)
						  		</fo:block>     
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  			Lab tests 
						  		</fo:block>	
						  		<xsl:for-each select="AdverseEventReport/Lab">
						  			<fo:block xsl:use-attribute-sets="normal">
						  				<xsl:value-of select="labTerm/term"/>
							  			<xsl:value-of select="other"/> - 
							  			
							  			Baseline value : <xsl:value-of select="baseline/value"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="units"/>, 
							  			Worst Value :	<xsl:value-of select="nadir/value"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="units"/>, 
							  			Recovery value : <xsl:value-of select="recovery/value"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="units"/>
							  		</fo:block>
						  		</xsl:for-each>											  		
      						</fo:table-cell>
      					</fo:table-row>						
    					
		  			</fo:table-body>
		  		</fo:table>
		  		

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="center">
			      II. SUSPECT DRUG(S) INFORMATION 
			  </fo:block>
			  		  		
				  <fo:table border="0.5pt solid black">
						<fo:table-column column-width="50%"/>
						<fo:table-column column-width="30%"/>
						<fo:table-column column-width="20%"/>
	
																
			  			<fo:table-body >
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			14. SUSPECT DRUG(S) {include generic name}  
							  		</fo:block>  
							  		<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  				<xsl:value-of select="StudyAgent/Agent/name"/>
							  			</fo:block>
							  		</xsl:for-each>    										  					
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  		20. DID REACTION ABATE AFTER STOPPING DRUG 
							  		</fo:block> 
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'YES'">x</xsl:if> ] YES 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'NO'">x</xsl:if> ] NO 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'NA'">x</xsl:if> ]  NA   
							  		</fo:block>  													  		
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			15. DAILY DOSE(S) 
							  		</fo:block>      										  					
							  		<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  				<xsl:value-of select="StudyAgent/Agent/name"/> : <xsl:value-of select="Dose/amount"/> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:value-of select="Dose/units"/>
							  			</fo:block>
							  		</xsl:for-each>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		16. ROUTE(S) OF ADMINISTRATION REACTION 
							  		</fo:block>  													  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		21. DID REACTION REAPPEAR AFTER REINTRODUCTION?
							  		</fo:block>  
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'YES'">x</xsl:if> ] YES 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'NO'">x</xsl:if> ] NO 
							  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'NA'">x</xsl:if> ] NA   
							  		</fo:block> 							  															  		
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			17. INDICATION(S) FOR USE
							  		</fo:block>      										  					
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			18. THERAPY DATE(from/to)
							  		</fo:block>      										  					
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			19. THERAPY DURATION
							  		</fo:block>      										  					
	      						</fo:table-cell>
	      				</fo:table-row>
	  			   </fo:table-body >
	  			 </fo:table>		  			  				  				  		  				  		

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="center">
			      III. CONCOMITANT DRUG(S) AND HISTORY 
			  </fo:block>
			  		  		
				  <fo:table border="0.5pt solid black">
			  			<fo:table-body >
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			22. CONCOMITANT DRUG(S) AND DATES OF ADMINISTRATION {exclude those used to treat reaction}
							  		</fo:block>  
							  		<xsl:for-each select="AdverseEventReport/ConcomitantMedication">
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  				<xsl:value-of select="name"/>
							  			</fo:block>
							  		</xsl:for-each>    										  					
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			23. OTHER RELEVANT HISTORY {e.g diagnosis, allergies, pregnancy with last month of period, etc}
							  		</fo:block>
							  		<xsl:for-each select="AdverseEventReport/SAEReportPreExistingCondition">
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm">
							  				<xsl:value-of select="other"/> 
							  				<xsl:value-of select="PreExistingCondition/text"/> 
							  			</fo:block>
							  		</xsl:for-each>      										  					
	      						</fo:table-cell>
	      				</fo:table-row>
	  			   </fo:table-body >
	  			 </fo:table>

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="center">
			      IV. MANUFACTURER INFORMATION
			  </fo:block>
			  		  		
				  <fo:table border="0.5pt solid black">
						<fo:table-column column-width="25%"/>
						<fo:table-column column-width="25%"/>
						<fo:table-column column-width="50%"/>
	
																
			  			<fo:table-body >
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			24a. NAME AND ADDRESS OF MANUFACTURER
							  		</fo:block>      										  					
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerName"/>
							  		</fo:block> 
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerCity"/> , 
							  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerState"/>
							  		</fo:block>
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="4">
							  		<fo:block xsl:use-attribute-sets="normal" > 
  
							  		</fo:block>  													  		
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 

							  		</fo:block>      										  					
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		24b. MFR CONTROL NO.
							  		</fo:block>  													  		
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										24c. DATE RECIEVED BY MANUFACTURER
							  		</fo:block>      										  					
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		24d. REPORT SOURCE
							  		</fo:block>  													  		
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		[] STUDY [] LITERATURE
							  		</fo:block> 
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		[] HEALTH PROFESSIONAL
							  		</fo:block> 
	      						</fo:table-cell>
	      				</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
											DATE OF THIS REPORT
							  		</fo:block>      										  					
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		25a. REPORT TYPE
							  		</fo:block>  													  		
							  		<fo:block xsl:use-attribute-sets="normal" > 
								  		[] INITIAL []FOLLOWUP
							  		</fo:block> 
	      						</fo:table-cell>
	      				</fo:table-row>	      				
	  			   </fo:table-body >
	  			 </fo:table>
  			  <fo:block id="content_terminator"/>    
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

<xsl:template name="left-trim">
  <xsl:param name="s" />
  <xsl:choose>
    <xsl:when test="substring($s, 1, 1) = ''">
      <xsl:value-of select="$s"/>
    </xsl:when>
    <xsl:when test="normalize-space(substring($s, 1, 1)) = ''">
      <xsl:call-template name="left-trim">
        <xsl:with-param name="s" select="substring($s, 2)" />
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:value-of select="$s" />
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template name="right-trim">
  <xsl:param name="s" />
  <xsl:choose>
    <xsl:when test="substring($s, 1, 1) = ''">
      <xsl:value-of select="$s"/>
    </xsl:when>
    <xsl:when test="normalize-space(substring($s, string-length($s))) = ''">
      <xsl:call-template name="right-trim">
        <xsl:with-param name="s" select="substring($s, 1, string-length($s) - 1)" />
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:value-of select="$s" />
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>

<xsl:template name="trim">
  <xsl:param name="s" />
  <xsl:call-template name="right-trim">
    <xsl:with-param name="s">
      <xsl:call-template name="left-trim">
        <xsl:with-param name="s" select="$s" />
      </xsl:call-template>
    </xsl:with-param>
  </xsl:call-template>
</xsl:template>

  
</xsl:stylesheet>
