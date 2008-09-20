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
				
		  <fo:static-content flow-name="xsl-region-before">
		    <fo:table>
				<fo:table-column column-width="100%"/>
				<fo:table-body>
				  <fo:table-row>
				    <fo:table-cell>
				    	<fo:block font-size="10pt" font-family="Times New Roman" text-align-last="left">
				    		NCI Contract/Grant No.		 
				    	</fo:block>
				    	<fo:block font-size="10pt" font-family="Times New Roman" text-align-last="left">
				    		IRB Protocol No.		 
				    	</fo:block>
				    </fo:table-cell>
				  </fo:table-row>
				</fo:table-body>				
			</fo:table>									
		  </fo:static-content>
		  
		  <fo:flow flow-name="xsl-region-body">		  	
   			  <fo:block font-size="14pt" font-family="Times New Roman" text-align-last="center" display-align="center">
		      	NCI, DIVISION OF CANCER PREVENTION (DCP)
		      </fo:block>
		      <fo:block font-size="14pt" font-family="Times New Roman" text-align-last="center" display-align="center">
			      SERIOUS ADVERSE EVENT FORM
			  </fo:block>
				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left">
			      REQUIRED FIELDS ON ALL REPORTS
			  </fo:block>
   

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="33%"/>
					<fo:table-column column-width="33%"/>
					<fo:table-column column-width="34%"/>

										
		  			<fo:table-body >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal"> 
						  			<fo:inline font-weight="bold">Date Report Generated : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text> </fo:inline>
						  			<fo:inline>	<xsl:value-of select="java:format(java:java.text.SimpleDateFormat.new ('MM/dd/yyyy'), java:java.util.Date.new())"/>				  </fo:inline>    
				  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold">Sponsor : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<fo:inline>NCI, DCP</fo:inline>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Study (Indication) : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<fo:inline><xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/shortTitle"/></fo:inline>
						  		</fo:block>   
						  		   							
      						</fo:table-cell>
      					</fo:table-row>
      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Date Report Submitted :  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  			<fo:inline>
						  					<xsl:call-template name="standard_date">
						  						<xsl:with-param name="date" select="AdverseEventReport/createdAt"/>
						  					</xsl:call-template>
						  			</fo:inline>
						  		</fo:block>      							
      						</fo:table-cell>
      						
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold">NCI Study # <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<fo:inline><xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/Identifier/value"/></fo:inline>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="label"> 
						  			Agent(s) under Investigation :
						  		</fo:block> 
						  		
						  			<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
						  				<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  					<xsl:value-of select="StudyAgent/Agent/name"/>
						  					<xsl:value-of select="StudyAgent/otherAgent"/>
						  				</fo:block> 
						  			</xsl:for-each>
						  		    							
      						</fo:table-cell>
      					</fo:table-row>

		  			</fo:table-body>
		  		</fo:table>


				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left">
			      A. Study Subject Information 
			  </fo:block>
   

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="25%"/>
					<fo:table-column column-width="25%"/>
					<fo:table-column column-width="25%"/>
					<fo:table-column column-width="25%"/>
										
		  			<fo:table-body >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> 1.  Study Participant # or  PID #  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline> 
						  			 <xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/Identifier/value"/>
						  		</fo:block> 
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> 2.  Year of Birth :  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline> 
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth/yearString" />
						  		</fo:block>
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold">3.  Weight at Time of Event : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>  
									<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/quantity"/>
						  		</fo:block>   
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal">						  		
						  			[<xsl:if test="AdverseEventReport/ParticipantHistory/weight/unit = 'Kilogram'">
						  				x
						  			</xsl:if>] kg <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  				
						  			[ <xsl:if test="AdverseEventReport/ParticipantHistory/weight/unit = 'Pound'">
						  				x
						  			</xsl:if>] lbs <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			
						  			[ <xsl:if test="AdverseEventReport/ParticipantHistory/weight/unit = ''">
						  				x
						  			</xsl:if>] not available
						  		 </fo:block>   							
      						</fo:table-cell>
      						
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold">4. Height at Time of Event :<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline> 
									<xsl:value-of select="AdverseEventReport/ParticipantHistory/height/quantity"/>
						  		</fo:block>      							
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal">						  		
						  			[<xsl:if test="AdverseEventReport/ParticipantHistory/height/unit = 'Centimeter'">
						  				x
						  			</xsl:if>] cm <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  				
						  			[ <xsl:if test="AdverseEventReport/ParticipantHistory/height/unit = 'Inch'">
						  				x
						  			</xsl:if>] in <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			
						  			[ <xsl:if test="AdverseEventReport/ParticipantHistory/height/unit = ''">
						  				x
						  			</xsl:if>] not available
						  		 </fo:block> 

      						</fo:table-cell>
      					</fo:table-row>
		  			</fo:table-body>
		  		</fo:table>


				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		      <fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left">
			      B. Event Information 
			  </fo:block>
   

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="33%"/>
					<fo:table-column column-width="33%"/>
					<fo:table-column column-width="34%"/>

										
		  			<fo:table-body >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			[ x ]  Initial Event Report  
						  		</fo:block>      										  					
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			[  ]  Amendment  
						  		</fo:block> 
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  		<fo:inline font-weight="bold">Gender : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>  
						  		<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text> 
						  		[ <xsl:if test="AdverseEventReport/StudyParticipantAssignment/Participant/gender = 'Male'">
						  				x
						  			</xsl:if>] M <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		[ <xsl:if test="AdverseEventReport/StudyParticipantAssignment/Participant/gender = 'Female'">
						  				x
						  			</xsl:if>] F <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Dose at Event : </fo:inline>  
						  		</fo:block>  
						  		<fo:block xsl:use-attribute-sets="normal" >   
						  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/TreatmentAssignment/description"/>
						  		</fo:block>	  							
      						</fo:table-cell>
      					</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">Event Onset Date : </fo:inline>  
						  			<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
				                    	  <fo:block xsl:use-attribute-sets="normal" >
				                    		<xsl:call-template name="standard_date">
				                        		<xsl:with-param name="date" select="startDate"/>
				                    		</xsl:call-template> 
				                    	  </fo:block>
				                    	</xsl:if>                   					
									</xsl:for-each>		
						  		</fo:block>      				
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			(Month/Day/Year)  
						  		</fo:block> 						  					
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2" number-rows-spanned="1">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Primary Event (diagnosis): (Verbatim AE term) </fo:inline>  
									<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
                        					<fo:block xsl:use-attribute-sets="normal" > 
                        						<xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/> , 
                    							<xsl:value-of select="detailsForOther"/> ,
                    							<xsl:value-of select="grade"/> ,

                    						</fo:block> 
                    					</xsl:if>
									</xsl:for-each>

						  		</fo:block>      							 						  		
      						</fo:table-cell>
      					</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">Event Approx. Time :</fo:inline>  
						  			<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
				                    	  <fo:block xsl:use-attribute-sets="normal" >
				                    		 <xsl:value-of select="eventApproximateTime/hour"/> : <xsl:value-of select="eventApproximateTime/minute"/>
				                    		 <xsl:if test="eventApproximateTime/type = '0'"> AM </xsl:if>
				                    		 <xsl:if test="eventApproximateTime/type = '1'"> PM </xsl:if>
				                    	  </fo:block>
				                    	</xsl:if>                   					
									</xsl:for-each>	
						  		</fo:block>      				
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			(Indicate A.M./P.M.)  
						  		</fo:block> 						  					
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2" number-rows-spanned="2">     							
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> CTCAE category : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
                        					<xsl:value-of select="AdverseEventCtcTerm/ctc-term/CtcCategory/name"/> 
                    					</xsl:if>
									</xsl:for-each>
						  		</fo:block>  
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> CTCAE Term : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
                        					<xsl:value-of select="AdverseEventCtcTerm/ctc-term/term"/> 
                    					</xsl:if>
									</xsl:for-each>
						  		</fo:block>  						  		
      						</fo:table-cell>      						
      					</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> Event Occurred at : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  			<xsl:for-each select="AdverseEventReport/AdverseEvent">
										<xsl:if test="substring(gridId,1,3) = 'PRY'">
						  					<xsl:value-of select="eventLocation"/>
						  				 </xsl:if>
									</xsl:for-each>
						  		</fo:block>      										  					
      						</fo:table-cell>
      					</fo:table-row>
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">  Total dose administered.: </fo:inline>
						  			<fo:block/>
						  			<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
						  				<xsl:value-of select="StudyAgent/Agent/name"/>
										<xsl:value-of select="StudyAgent/otherAgent"/> :<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
										<xsl:value-of select="Dose/amount"/> 
										<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
										<xsl:value-of select="Dose/units"/>
						  			</xsl:for-each>
						  		</fo:block>      				 						  					
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold">Primary Treatment Approx. Time (A.M./P.M.) : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/primaryTreatmentApproximateTime/hour"/> : 
									<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/primaryTreatmentApproximateTime/minute"/>														
						  			<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/primaryTreatmentApproximateTime/type = '0'"> AM </xsl:if>
				            		 <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/primaryTreatmentApproximateTime/type = '1'"> PM </xsl:if>
						  			<fo:block/>  							
									<fo:inline font-weight="bold"> Primary Treatment of Event :<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	
									<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/primaryTreatment"/>	
						  		</fo:block> 
      						</fo:table-cell>
      					</fo:table-row>      					
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Attending Physician (Name) :	<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>		
									<xsl:value-of select="AdverseEventReport/Physician/firstName"/>
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/Physician/lastName"/>
						  			<fo:block/>    
						  		
									<fo:inline font-weight="bold"> Phone/FAX No. :	<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	
					                <xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
					                    <xsl:if test="key = 'phone'">
					                        <xsl:value-of select="value"/>
					                    </xsl:if>
					                </xsl:for-each>									
						  			<fo:block/> 

									<fo:inline font-weight="bold"> Hospital/Clinic :	<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>					
 									<fo:block/>

									<fo:inline font-weight="bold">  Address : </fo:inline>
									<fo:block/>
									
									<xsl:value-of select="AdverseEventReport/Physician/address/street"/>
									<fo:block/>
									<xsl:value-of select="AdverseEventReport/Physician/address/city"/>
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/Physician/address/state"/>	
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/Physician/address/zip"/>					
						  		</fo:block> 						  		
      						</fo:table-cell>
      					</fo:table-row>  
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Describe Event </fo:inline>	(if applicable, include dates of hospitalization for event) :<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text>						
						  		</fo:block>    	
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/eventDescription"/>
						  		</fo:block>
						  							  		
      						</fo:table-cell>
      					</fo:table-row> 
						<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Form Completed by : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/Physician/firstName"/>
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/Physician/lastName"/>	
									<xsl:text disable-output-escaping="yes"> &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold">Title : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	 						
						  		   <fo:block/>  

									<fo:inline font-weight="bold"> e-mail :   <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
					                    <xsl:if test="key = 'e-mail'">
					                        <xsl:value-of select="value"/>
					                    </xsl:if>
					                </xsl:for-each>
									<xsl:text disable-output-escaping="yes"> &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									
									<fo:inline font-weight="bold">Telephone : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
					                    <xsl:if test="key = 'phone'">
					                        <xsl:value-of select="value"/>
					                    </xsl:if>
					                </xsl:for-each>					
						  		</fo:block>    
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 							
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> Investigator Signature : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold"> Date : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>					
									<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold"> Phone No : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 	
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<fo:inline font-weight="bold"> e-mail :  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	 
									<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold"> Telephone : 	<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>	 					
						  		</fo:block>    
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 		
						  		
      						</fo:table-cell>
      					</fo:table-row>      					
		  			</fo:table-body>
		  		</fo:table>
		  		
		  		
	  			<fo:block break-before="page" font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left"> 
		  			ALL FIELDS APPEARING IN THE FOLLOWING PAGES (C F) MUST BE COMPLETED FOR THE INITIAL REPORT; THEREAFTER, FILL IN ONLY SECTIONS THAT PROVIDE  ADDITIONAL/ CORRECTIVE INFORMATION. 
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
	  			<fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left"> 
		  			C.  Site information
		  		</fo:block>		  		
	  			<fo:table border="0.5pt solid black">


										
		  			<fo:table-body >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> 1.  Investigator Name :<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator/SiteInvestigator/Investigator/firstName"/>
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator/SiteInvestigator/Investigator/lastName"/>
									<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold">e-mail : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator/SiteInvestigator/Investigator/emailAddress"/>
						  		</fo:block>      							
      						</fo:table-cell>

      					</fo:table-row>
      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">2.  Fax : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator/SiteInvestigator/Investigator/faxNumber"/>	
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160;&amp;#160; &amp;#160; </xsl:text>
									<fo:inline font-weight="bold">Telephone : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator/SiteInvestigator/Investigator/phoneNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>

      					</fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">3. Site Name and (ID) : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Organization/name"/>	
						  			<xsl:text disable-output-escaping="yes">&amp;#160; </xsl:text>
									(<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Organization/nciInstituteCode"/>	)
						  		</fo:block>      							
      						</fo:table-cell>

      					</fo:table-row>
      					
      					
		  			</fo:table-body>
		  		</fo:table>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
	  			<fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left"> 
		  			D.  Suspect Medication(s)
		  		</fo:block>	
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="40%"/>
					<fo:table-column column-width="8%"/>
					<fo:table-column column-width="9%"/>
					<fo:table-column column-width="9%"/>
					<fo:table-column column-width="8%"/>
					<fo:table-column column-width="9%"/>
					<fo:table-column column-width="9%"/>
					<fo:table-column column-width="8%"/>
										
		  			<fo:table-body >
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="8">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">1.  Study Design :  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  			[ <xsl:if test="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/design = 'BLIND'">x</xsl:if>
						  			 ] Blind  
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text> 
						  			[  <xsl:if test="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/design = 'OPEN_UNBLIND'">x</xsl:if>
						  			] Open/Unblind
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text> 
						  			[  <xsl:if test="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/design = 'PARTIAL'">x</xsl:if>
						  			] Partial Blind
						  		</fo:block>      				
	
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>					  					
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/TreatmentAssignment/description"/>

						  		</fo:block>
      						</fo:table-cell>		  			
		  			</fo:table-row >
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> 2. Study Agent(s) <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  		</fo:block>  
      						</fo:table-cell>		  			
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> Formulation (e.g., tablet, solution)</fo:inline>
						  		</fo:block>      				
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> Lot No. (If known)</fo:inline>
						  		</fo:block>      				
      						</fo:table-cell>
		  			</fo:table-row >
		  			
					<xsl:for-each select="AdverseEventReport/TreatmentInformation/CourseAgent">
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="6mm"> 
							  			<xsl:value-of select="StudyAgent/Agent/name"/>
							  			<xsl:value-of select="StudyAgent/otherAgent"/>
							  		</fo:block>  
	      						</fo:table-cell>		  			
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="formulation"/>
							  		</fo:block>      				
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="lotNumber"/>
							  		</fo:block>      				
	      						</fo:table-cell>
			  			</fo:table-row >
		  			</xsl:for-each>		  				

		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="8">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> 3.  Start Date of Study Agent (Month/Day/Year) :<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  		    <xsl:call-template name="standard_date">
				                        <xsl:with-param name="date" select="AdverseEventReport/TreatmentInformation/firstCourseDate"/>
				                    </xsl:call-template>						  			
						  		</fo:block>      				
      						</fo:table-cell>		  			
		  			</fo:table-row >		  			
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="8">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">4. Was blind broken due to event? <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
                          
						  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/blindBroken = 'false'">x</xsl:if>
						  			] No    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>                             
						  			[ <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/blindBroken = 'true'">x</xsl:if>
						  			] Yes   <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			[ ] NA
						  		</fo:block>      				
      						</fo:table-cell>		  			
		  			</fo:table-row >
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="8">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">5.  Was Study Agent stopped/interrupted/reduced in response to event?  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>

						  			[  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/studyDrugInterrupted = 'false'">x</xsl:if>
						  			] No  
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			[  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/studyDrugInterrupted = 'true'">x</xsl:if>
						  			] Yes 
						  		</fo:block>  
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm">
						  		>> <fo:inline font-weight="bold"> If yes, complete a-e : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  		</fo:block>  
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  		     <fo:inline font-weight="bold"> a.  If stopped, specify date study agent last taken (Month/Day/Year) :      <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									<xsl:call-template name="standard_date">
				                        <xsl:with-param name="date" select="AdverseEventReport/TreatmentInformation/CourseAgent/lastAdministeredDate"/>
				                    </xsl:call-template>	 
				                    <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text> 		     
						  		     [  ] NA
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  		<fo:inline font-weight="bold"> b.  If reduced, specify: New dose - <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  			<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/reducedDose"/> , 
						  			<fo:inline font-weight="bold"> Date reduced </fo:inline> <fo:inline> (Month/Day/Year) - <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  		    <xsl:call-template name="standard_date">
				                        <xsl:with-param name="date" select="AdverseEventReport/AdverseEventResponseDescription/reducedDate"/>
				                    </xsl:call-template>						  			
						  			  
						  			<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text> 
						  			[  ] NA
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm">
						  		     <fo:inline font-weight="bold"> c.  If interrupted, specify total number of days not given : 
						  		     <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
						  		      <xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/daysNotGiven"/>	
						  		     <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;&amp;#160;</xsl:text> 
						  		     [  ] NA 
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  		     <fo:inline font-weight="bold"> d.  Did event abate after study agent was stopped or dose reduced? </fo:inline>
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>           	
						  		     [  ] NA     
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		     [  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'true'">x</xsl:if>
						  		     ] Yes     
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		     [  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventAbate = 'false'">x</xsl:if>
						  		     ] No  
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  		     <fo:inline font-weight="bold"> e.  Did event reappear after study agent was reintroduced ? </fo:inline>    
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>                    	
						  		     [  ] NA     
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		     [  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'true'">x</xsl:if>
						  		     ] Yes    
						  		     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  		      [  <xsl:if test="AdverseEventReport/AdverseEventResponseDescription/eventReappear = 'false'">x</xsl:if>
						  		      ] No 
						  		</fo:block> 
						  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
 						  		  				
      						</fo:table-cell>		  			
		  			</fo:table-row >
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="8">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold">  6. Was patient taking any other medications concomitantly at the time of the event ?	</fo:inline>
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			[ <xsl:if test="AdverseEventReport/ConcomitantMedication/name = ''">
						  			x </xsl:if>] No   
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			[ <xsl:if test="AdverseEventReport/ConcomitantMedication/name != ''">
						  			x </xsl:if>] Yes  
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			>>  If yes, complete below. 
   
						  		</fo:block>   
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										   (DO NOT LIST DRUGS USED TO TREAT EVENT)

						  		</fo:block>    										  					
      						</fo:table-cell>
      				</fo:table-row>		  			
					<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
						  			Drug Name  
						  		</fo:block>      				
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align-last="center"> 
						  			Doses (units, frequency, route, indication for use) 
						  		</fo:block> 						  					
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="label" text-align-last="center"> 
									Start Date
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="4">
						  		<fo:block xsl:use-attribute-sets="label" text-align-last="center"> 
									Stop Date  
						  		</fo:block>      							
						  		<fo:block xsl:use-attribute-sets="normal" text-align-last="center"> 
									or mark (X) if continuing  
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
									Month
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									Day 
						  		</fo:block>      							
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									Year 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									Month
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									Day 
						  		</fo:block>      							
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									Year 
						  		</fo:block>      							
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="normal" > 
									(X) 
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>
      					<xsl:for-each select="AdverseEventReport/ConcomitantMedication">
							<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="name"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="startDate/monthString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="startDate/dayString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="startDate/yearString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>	      							      							      						
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="endDate/monthString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="endDate/dayString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:value-of select="endDate/yearString"/>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">							  		
							  			<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			 	<xsl:if test="endDate = ''">X</xsl:if>
							  			</fo:block>  							  		
	      						</fo:table-cell>
	       					</fo:table-row>
      					</xsl:for-each>      					
	  			</fo:table-body>
		  		</fo:table>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
	  			<fo:block break-before="page" font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left"> 
		  			E. Adverse Event
		  		</fo:block>	
		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="40%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="15%"/>

										
		  			<fo:table-body >
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="6">
						  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
						  			<fo:inline font-weight="bold"> 1.	Relevant Laboratory/Diagnostic Tests  </fo:inline>
						  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
						  			[ ] No tests performed
						  		</fo:block>      				
      						</fo:table-cell>		  			
		  			</fo:table-row >
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="3">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
						  			Date
						  		</fo:block>      				
      						</fo:table-cell>		  			
      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-rows-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
						  			Test
						  		</fo:block>      				
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
						  			Results
						  		</fo:block>      				
      						</fo:table-cell>     						
		  			</fo:table-row >					
		  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Month
						  		</fo:block>      				
      						</fo:table-cell>		  			
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Day
						  		</fo:block>      				
      						</fo:table-cell>
      						<fo:table-cell xsl:use-attribute-sets="small-cell">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Year
						  		</fo:block>      				
      						</fo:table-cell>
       						<fo:table-cell xsl:use-attribute-sets="small-cell" >
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Actual Value
						  		</fo:block>      				
      						</fo:table-cell>     						
       						<fo:table-cell xsl:use-attribute-sets="small-cell" >
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Normal Range
						  		</fo:block>      				
      						</fo:table-cell> 
		  			</fo:table-row >
		  			<xsl:for-each select="AdverseEventReport/Lab">
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="substring(baseline/date,6,2)"/>
							  		</fo:block>      				
	      						</fo:table-cell>		  			
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="substring(baseline/date,9,2)"/>
							  		</fo:block>      				
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="substring(baseline/date,1,4)"/>
							  		</fo:block>      				
	      						</fo:table-cell>
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="labTerm/term"/>
							  			<xsl:value-of select="other"/>
							  		</fo:block>      				
	      						</fo:table-cell>
	       						<fo:table-cell xsl:use-attribute-sets="small-cell" >
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			<xsl:value-of select="nadir/value"/>   <xsl:value-of select="units"/>
							  		</fo:block>      				
	      						</fo:table-cell>     						
	       						<fo:table-cell xsl:use-attribute-sets="small-cell" >
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  			
							  		</fo:block>      				
	      						</fo:table-cell> 
			  			</fo:table-row >
			  		</xsl:for-each>
		  			
	  			</fo:table-body>
		  		</fo:table>

		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 

		  		<fo:table border="0.5pt solid black">
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="80%"/>
		  			<fo:table-body >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" number-columns-spanned="2">
							  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
							  			2.	Relevant Medical History, including preexisting conditions (e.g., allergies, pregnancy, smoking and alcohol use, hepatic/renal dysfunction, medical/surgical history, etc.)
							  		</fo:block>      				
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" >
							  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
							  			Date (if known)
							  		</fo:block>      				
	      						</fo:table-cell>		  			
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" >
							  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" text-align-last="center"> 
							  			Diseases/Surgeries/Treatment
							  		</fo:block>      				
	      						</fo:table-cell>    						
			  			</fo:table-row >					
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" >
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align-last="center"> 
							  			
							  		</fo:block>      				
	      						</fo:table-cell>		  			
	      						<fo:table-cell xsl:use-attribute-sets="small-cell" >
	      							
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align-last="left"> 
							  			<fo:inline font-weight="bold"> Disease : </fo:inline>
							  			<xsl:value-of select="AdverseEventReport/DiseaseHistory/CtepStudyDisease/DiseaseTerm/ctepTerm"/>
							  			<fo:block/>
							  			 <fo:inline font-weight="bold">Primary Site of Disease : </fo:inline>
							  			<xsl:value-of select="AdverseEventReport/DiseaseHistory/AnatomicSite/name"/>
							  			<fo:block/>
							  			 <fo:inline font-weight="bold">Metastatic Disease Site : </fo:inline>
							  			 <xsl:for-each select="AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite">
							  				<xsl:value-of select="AnatomicSite/name"/> , 	
							  			 </xsl:for-each>						  			
							  			<fo:block/>
							  			<fo:inline font-weight="bold">Pre Existing Conditions : </fo:inline>							  		
							  			<xsl:for-each select="AdverseEventReport/SAEReportPreExistingCondition">
							  				<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align-last="left">	
							  					<xsl:value-of select="PreExistingCondition/text"/>
							  					<xsl:value-of select="other"/> , 
							  				</fo:block>
							  			</xsl:for-each>
							  			<fo:block/>
							  			<fo:inline font-weight="bold">Prior Therapies : </fo:inline>
							  			<xsl:for-each select="AdverseEventReport/SAEReportPriorTherapy">
							  				<fo:block xsl:use-attribute-sets="normal" margin-left="2mm" text-align-last="left">	
							  					<xsl:value-of select="PriorTherapy/text"/>
							  					<xsl:value-of select="other"/>
							  				</fo:block>
							  			</xsl:for-each>	
							  		</fo:block>						  		      				
	      						</fo:table-cell>    						
			  			</fo:table-row >
	  				</fo:table-body>
		  		</fo:table>
				<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
		  		<fo:table border="0.5pt solid black">

		  			<fo:table-body >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
							  										  			
										<fo:inline font-weight="bold"> 3. NCI Toxicity GRADE of the Event (use NCI Common Toxicity Criteria) : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>

										<xsl:for-each select="AdverseEventReport/AdverseEvent">
											<xsl:if test="substring(gridId,1,3) = 'PRY'">	
												[ <xsl:if test="substring(grade,1,1)=1">x</xsl:if>] 1
												<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
												[ <xsl:if test="substring(grade,1,1)=2">x</xsl:if>] 2
												<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
												[ <xsl:if test="substring(grade,1,1)=3">x</xsl:if>] 3
												<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
												[ <xsl:if test="substring(grade,1,1)=4">x</xsl:if>] 4
												<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
												[ <xsl:if test="substring(grade,1,1)=5">x</xsl:if>] 5
											</xsl:if>                   					
										</xsl:for-each>
									</fo:block>
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> If not gradable by NCI CTC, check one of the following :  <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
										[  ] Mild     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] Moderate     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] Severe    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] Life-threatening     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] Fatal
							  		</fo:block>      				
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> 4. Why Serious ?<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									</fo:block>
							<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
								<xsl:for-each select="AdverseEventReport/AdverseEvent">
									<xsl:if test="substring(gridId,1,3) = 'PRY'">					
									  			[ <xsl:if test="Outcome/OutcomeType='DEATH'">x </xsl:if>] Results in death <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									  			[ <xsl:if test="Outcome/OutcomeType='LIFE_THREATENING'">x </xsl:if>] Is life-threatening  <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									  			[ <xsl:if test="Outcome/OutcomeType='HOSPITALIZATION'">x </xsl:if>] Requires inpatient hospitalization or prolongation of existing hospitalization <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									  			<fo:block/>
									  			[ <xsl:if test="Outcome/OutcomeType='DISABILITY'">x </xsl:if>] Results in persistent or significant disability/incapacity  <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									  			[ <xsl:if test="Outcome/OutcomeType='CONGENITAL_ANOMALY'">x </xsl:if>] Is a congenital anomaly/birth defect <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									  			[ <xsl:if test="Outcome/OutcomeType='OTHER_SERIOUS'">x </xsl:if>] Other, specify: <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									  			<xsl:value-of select="Outcome/other"/>
									</xsl:if>
								</xsl:for-each>
							</fo:block> 
 									  									  									  									  							  									  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >					
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> 5. Outcome of Event (at time of report) <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									</fo:block>
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
									
										<fo:inline font-weight="bold"> Present Status : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									  	<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='INTERVENTION_CONTINUES'">Intervention for AE continues </xsl:if>  
									  	<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='RECOVERING'">Recovering/Resolving </xsl:if>  										
										<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='RECOVERED_WITH_SEQUELAE'">Recovered/Resolved with Sequelae  </xsl:if> 
										<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='RECOVERED_WITHOUT_SEQUELAE'">Recovered/Resolved without Sequelae </xsl:if>  
										<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='NOT_RECOVERED'">Not recovered/Not resolved </xsl:if>  
										<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='DEAD'">Fatal/Died </xsl:if> 
										
										<fo:block/>										
										<fo:inline font-weight="bold"> Date of recovery or death : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
				                    		<xsl:call-template name="standard_date">
				                        		<xsl:with-param name="date" select="AdverseEventReport/AdverseEventResponseDescription/recoveryDate"/>
				                    		</xsl:call-template>

				                    	<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/presentStatus='DEAD'">
					                    <fo:block/>					                    	
					                    	<fo:inline font-weight="bold"> Autopsy performed ? <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
											<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
											<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/autopsyPerformed='true'">Yes</xsl:if>
											<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/autopsyPerformed='false'">No</xsl:if>										
										</xsl:if>
														                    	
				                    	<xsl:if test="AdverseEventReport/AdverseEventResponseDescription/causeOfDeath">
					                    <fo:block/>					                    	
					                    	<fo:inline font-weight="bold"> Cause of death : <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
											<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
											<xsl:value-of select="AdverseEventReport/AdverseEventResponseDescription/causeOfDeath"/>										
										</xsl:if>
					  				</fo:block>					  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> 6.	Investigator's opinion of the relationship between the event and the study agent  (If more than one event is being reported, list secondary events and corresponding relationship to study agent in the comments section below.) Check applicable box :
										<xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
									</fo:block>
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
								<xsl:for-each select="AdverseEventReport/AdverseEvent">
									<xsl:if test="substring(gridId,1,3) = 'PRY'">
										[ <xsl:if test="substring(attributionSummary,1,1)=1">x</xsl:if>] Unrelated
										<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[ <xsl:if test="substring(attributionSummary,1,1)=2">x</xsl:if>] Unlikely
							            <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>  
							            [ <xsl:if test="substring(attributionSummary,1,1)=3">x</xsl:if>] Possible           
									    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									    [ <xsl:if test="substring(attributionSummary,1,1)=4">x</xsl:if>] Probable
					                    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>  
					                    [ <xsl:if test="substring(attributionSummary,1,1)=5">x</xsl:if>] Definite  
					                 </xsl:if>
					           </xsl:for-each>                     
							  		</fo:block>      							  									  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> Was this event reported by the Investigator to (check all that apply) :   <xsl:text disable-output-escaping="yes">&amp;#160;&amp;#160;</xsl:text></fo:inline>
										[  ] IRB       <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>  
										 [  ] Manufacturer/Distributor        <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
									</fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										[  ] Other Investigators participating in this study, if checked, please list names and institutions
									</fo:block>
     							  									  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >
	  				</fo:table-body>
		  		</fo:table>		
		  		
	  			<fo:block break-before="page" font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="left"> 
		  			F.  Comments/Clarifications:
		  		</fo:block>

		  		<fo:table border="0.5pt solid black">

		  			<fo:table-body >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block font-size="10pt" font-weight="bold" font-family="Times New Roman" text-align-last="center"> 
										FOR NCI USE ONLY
									</fo:block>     				
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> 1.	Date NCI notified of event (Month/Day/Year) : <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
									</fo:block> 							  									  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >					
			  			<fo:table-row height="120mm" >
	      						<fo:table-cell border="0.5pt solid black">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> 2.	Medical Monitor Review :
										<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
									</fo:block>
									<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> Medical Assessment of Event (including drug relationship and expectancy) : <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
							  		</fo:block>      							  									  						
	      						</fo:table-cell>		  			
			  			</fo:table-row >
			  			<fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell xsl:use-attribute-sets="small-cell">
							  		<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> Is this an FDA reportable (7 calendar days) event ?    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										[  ] Yes   <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] No
									</fo:block>
									<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> Is this an FDA reportable (15 calendar days) event ?  <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										[  ] Yes   <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] No
							  		</fo:block>  
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										>> If No, specify reason :
							  		</fo:block>  
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold">Is more information expected ?     <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										 [  ] Yes   <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										  [  ] No
							  		</fo:block>  
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										>> If Yes, specify :
							  		</fo:block>  
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold">Is this event to be communicated to other NCI contractors using this investigational drug ?  <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>      
										[  ] Yes    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] No
							  		</fo:block>  
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										>> If Yes, how?	<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;</xsl:text>
										<fo:inline font-weight="bold">By telephone (attach a TC Form) :	<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										[  ] Yes, attached TC Form    <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										[  ] No
							  		</fo:block>  
									<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
									<xsl:text disable-output-escaping="yes">&amp;#160;  &amp;#160; &amp;#160; &amp;#160; &amp;#160;&amp;#160; &amp;#160; &amp;#160; &amp;#160;&amp;#160; &amp;#160; &amp;#160; &amp;#160;&amp;#160; &amp;#160; &amp;#160; &amp;#160;</xsl:text>
										<fo:inline font-weight="bold">Other (FAX, mail, e-mail, etc.) : 	<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										[  ] Yes, attached a copy of the correspondence   <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text>
										 [  ] No
							  		</fo:block>  	
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 
							  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> 						  									  									  									  									  									  		    							  									  						
									<fo:block xsl:use-attribute-sets="normal" margin-left="2mm"> 
										<fo:inline font-weight="bold"> Medical Monitor :  <xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; </xsl:text></fo:inline>
										Print name  __________________________________________________  Signature ___________________________  Date  ____________
							  		</fo:block> 
	      						</fo:table-cell>		  			
			  			</fo:table-row >
	  				</fo:table-body>
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
