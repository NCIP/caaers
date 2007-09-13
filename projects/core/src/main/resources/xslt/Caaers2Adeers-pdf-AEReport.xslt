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
        

  <xsl:template match="/">
  	
	<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
	    
		<fo:layout-master-set>
		  	<fo:simple-page-master master-name="A4" margin-left="2mm" margin-top="2mm" margin-right="0.25in">
		    	<fo:region-body margin-top="2in"/>
		    	<fo:region-before extent="2in"/>
		    	<fo:region-after extent="0.5in"/>
		  	</fo:simple-page-master>


		</fo:layout-master-set>
		
		<fo:page-sequence master-reference="A4">
			
		<fo:static-content flow-name="xsl-region-after">

				<fo:block font-size="8pt" font-family="arial" text-align-last="right" display-align="right"> 
						Page <fo:page-number/> of <fo:page-number-citation ref-id="content_terminator"/>
				</fo:block>
		</fo:static-content>
				
		  <fo:static-content flow-name="xsl-region-before">
		    <fo:table>
				<fo:table-column column-width="25%"/>
				<fo:table-column column-width="50%"/>
				<fo:table-column column-width="25%"/>
				

				<fo:table-body>
				  <fo:table-row>
				    <fo:table-cell>
				      <fo:block> 
				      	<fo:external-graphic src="url(http://www.hhs.gov/images/system/hlogo.gif)" content-height="3em" content-width="3em"/>
				      </fo:block>
				      <fo:block font-weight="bold" font-size="8pt" font-family="arial" text-align-last="left" display-align="left"> 
				      	Run Date :   
    					<xsl:value-of select="java:format(java:java.text.SimpleDateFormat.new ('MM/d/yyyy h:mm:ss a '), java:java.util.Date.new())"/>				      
				      </fo:block>
				    </fo:table-cell>
				    
				    <fo:table-cell>
				      <fo:block font-weight="bold" font-size="8pt" font-family="arial" text-align-last="center" display-align="center">
				      	Department of Health and Human Services</fo:block>
				      
				      <fo:block space-before="5mm" font-weight="bold" font-size="10pt" font-family="arial" text-align-last="center" display-align="center">
				      	(Site Reported)</fo:block>
				      <fo:block font-weight="bold" font-size="14pt" font-family="arial" text-align-last="center" display-align="center">
				      	Adverse Event Expedited Report</fo:block>
				    </fo:table-cell>

				    <fo:table-cell font-weight="bold" font-size="8pt" font-family="arial" text-align-last="left" display-align="right">
				      <fo:block>Public Health Service</fo:block>
				      <fo:block>National Institutes of Health</fo:block>
				      <fo:block>National Cancer Institute</fo:block>
				      <fo:block>Bethesda, Maryland 20892</fo:block>
				    </fo:table-cell>
				  </fo:table-row>
				</fo:table-body>
				
				</fo:table>
					<fo:block space-after="5pt">
						<fo:leader leader-length="95%" leader-pattern="rule" rule-thickness="2pt"/>
					</fo:block>
					
				<fo:block margin-left="4mm"> 
					<fo:inline xsl:use-attribute-sets="label" > Protocol Number  :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal" > 
				   		<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/shortTitle"/>
				    </fo:inline>	 
					<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; &amp;#160; &amp;#160; &amp;#160; &amp;#160; &amp;#160; </xsl:text>
					<fo:inline xsl:use-attribute-sets="label" > CTC Version  :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal" > 
				   		<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/Ctc/name"/>
				    </fo:inline>
					<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; &amp;#160; &amp;#160; </xsl:text>
					<fo:inline xsl:use-attribute-sets="label" > Principal Investigator :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal"> 						 
						
						<for-each select="AdverseEventReport/StudyParticipantAssignment/StudySite/StudyInvestigator">
							
								<xsl:value-of select="SiteInvestigator/Investigator/firstName"/>
								<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
								<xsl:value-of select="SiteInvestigator/Investigator/lastName"/>
								
							
						</for-each>

					</fo:inline>	
					
					
																				
				</fo:block>

				<fo:block margin-left="4mm"> 
					<fo:inline xsl:use-attribute-sets="label" > Title :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal" > 
						<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Study/longTitle"/> 
					</fo:inline>
				</fo:block>

				<fo:block margin-left="4mm"> 
					<fo:inline xsl:use-attribute-sets="label" > Institution :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal" > 
						<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/StudySite/Organization/name"/> 
				    </fo:inline>	 
					<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; &amp;#160; &amp;#160; &amp;#160;  </xsl:text>
					<fo:inline xsl:use-attribute-sets="label" > Report Type :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal">  </fo:inline>	
					<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; &amp;#160;  </xsl:text>
					<fo:inline xsl:use-attribute-sets="label" > Ticket #:</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal">   <xsl:value-of select="AdverseEventReport/Summary[@id='Ticket number']/value"/> </fo:inline>	
					<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160; &amp;#160;  </xsl:text>
					<fo:inline xsl:use-attribute-sets="label" > Amendment #:</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal">   </fo:inline>																	
				</fo:block>
				
				<fo:block margin-left="4mm"> 
					<fo:inline xsl:use-attribute-sets="label" > Created Date :</fo:inline>
					<fo:inline xsl:use-attribute-sets="normal" > 
						<xsl:call-template name="standard_date">
						        <xsl:with-param name="date" select="AdverseEventReport/detectionDate"/>
   						</xsl:call-template>
					</fo:inline>
				</fo:block>

  


					<fo:block space-after="5pt">
						<fo:leader leader-length="95%" leader-pattern="rule" rule-thickness="2pt"/>
					</fo:block>
									
									
		  </fo:static-content>
		  
		  <fo:flow flow-name="xsl-region-body">		  	
           		
   
		  		<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Reporter Information 
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table>
					<fo:table-column column-width="12%"/>
					<fo:table-column column-width="33%"/>
					<fo:table-column column-width="5%"/>
					<fo:table-column column-width="18%"/>
					<fo:table-column column-width="7%"/>
					<fo:table-column column-width="25%"/>
										
		  			<fo:table-body>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm"> 
						  			Reporter Name :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:value-of select="AdverseEventReport/Reporter/firstName"/> 
									<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>
									<xsl:value-of select="AdverseEventReport/Reporter/lastName"/>
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>
      					
		  			    <fo:table-row>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Phone :  
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
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Fax :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:for-each select="AdverseEventReport/Reporter/ContactMechanism">
						  				<xsl:if test="key = 'fax'">
						  					<xsl:value-of select="value"/>
						  				</xsl:if>
						  			</xsl:for-each>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Email :  
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
		  			  <!-- 
		  			  <fo:table-row><fo:table-cell><fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> </fo:table-cell></fo:table-row>
		  			  	
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" space-before="5mm"> 
						  			Submitter Name :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			name
						  		</fo:block>      							
      						</fo:table-cell>
      					</fo:table-row>
      					
		  			    <fo:table-row>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Phone :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:if test="AdverseEventReport/Reporter/ContactMechanism/key = 'phone'">
						  				<xsl:value-of select="AdverseEventReport/Reporter/ContactMechanism/value"/>
						  			</xsl:if>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Fax :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:if test="AdverseEventReport/Reporter/ContactMechanism/key = 'fax'">
						  				<xsl:value-of select="AdverseEventReport/Reporter/ContactMechanism/value"/>
						  			</xsl:if>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Email :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:if test="AdverseEventReport/Reporter/ContactMechanism/key = 'e-mail'">
						  				<xsl:value-of select="AdverseEventReport/Reporter/ContactMechanism/value"/>
						  			</xsl:if>
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>
		  			  	-->
		  			  <fo:table-row><fo:table-cell><fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> </fo:table-cell></fo:table-row>
		  			  	
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" space-before="5mm"> 
						  			Physician Name :  
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Phone :  
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
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Fax :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:for-each select="AdverseEventReport/Physician/ContactMechanism">
						  				<xsl:if test="key = 'fax'">
						  					<xsl:value-of select="value"/>
						  				</xsl:if>
						  			</xsl:for-each>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Email :  
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
		  			</fo:table-body>
		  		</fo:table>
		  		
				<fo:block>
					<fo:leader leader-length="95%" leader-pattern="rule"/>
				</fo:block>			  		
 
 		  		<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Patient Information 
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="12%"/>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="10%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Patient ID :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/institutionalPatientNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Birth Date :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/StudyParticipantAssignment/Participant/dateOfBirth"/>
			   						</xsl:call-template>						  		
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Gender :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/gender"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>
		  			  
 					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Race :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/race"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Ethnicity :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/StudyParticipantAssignment/Participant/ethnicity"/> 
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>	
     					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Height(<xsl:value-of select="AdverseEventReport/ParticipantHistory/height/unit"/> ) :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/ParticipantHistory/height/quantity"/> 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Weight(<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/unit"/> ) :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/ParticipantHistory/weight/quantity"/>  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Body Surface Area :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>		
						<fo:table-row><fo:table-cell><fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block> </fo:table-cell></fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell number-columns-spanned="4">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Baseline performance status at initiation of protocol - ECOG/Zubrod scale :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/ParticipantHistory/baselinePerformanceStatus"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>	  		

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
									Disease Name :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/DiseaseHistory/CtepStudyDisease/DiseaseTerm/term"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>
		  			  		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
									Disease Name Not Listed	:
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Primary Site of Disease :  
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/DiseaseHistory/AnatomicSite/name"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell number-columns-spanned="2">
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Date of Initial Diagnosis :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/DiseaseHistory/diagnosisDate"/>
			   						</xsl:call-template>							  		 
						  		</fo:block>      							
      						</fo:table-cell>
		  			  </fo:table-row>
		  			  		  			  
		  			  		  			  	  
		  			</fo:table-body>
		  		</fo:table>
		  		
				<fo:block>
					<fo:leader leader-length="95%" leader-pattern="rule"/>
				</fo:block>			  		
	  
		  		<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Course Information
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Treatment Assignment Code :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/treatmentAssignmentCode"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
 					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Description :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/treatmentAssignmentCode"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Start date of first course :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/TreatmentInformation/firstCourseDate"/>
			   						</xsl:call-template>	
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Start date of course associated with Expedited Report :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/TreatmentInformation/AdverseEventCourse/date"/>
			   						</xsl:call-template>						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Start date of primary AE :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Treatment Assignment Code :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			End date of primary AE :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		  			  		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Course Number on which event(s) occurred :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/TreatmentInformation/AdverseEventCourse/number"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Total number of courses to date :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Was Investigational Agent(s) administered on this protocol?:
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>				  			  		  			  	  
		  			</fo:table-body>
		  		</fo:table>
		  		
				<fo:block>
					<fo:leader leader-length="95%" leader-pattern="rule"/>
				</fo:block>
				
  				<fo:block break-before="page" xsl:use-attribute-sets="sub-head" > 
		  			Radiation Intervention
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
    				
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Treatment Arm :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/treatmentArm"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
 					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Description of Treatment Arm :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/description"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Type of Radiation Administration :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/treatmentArm"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Total Dose (to date) :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/dosage"/>  <xsl:value-of select="AdverseEventReport/RadiationIntervention/dosageUnit"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Date of Last Treatment :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/RadiationIntervention/lastTreatmentDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Schedule:
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>  Number of Fractions :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/fractionNumber"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		  			  		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			<xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text>  <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> Number of Elaspsed Days :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/daysElapsed"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Adjustment : 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/RadiationIntervention/adjustment"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		
		  					  		  			  	  
		  			</fo:table-body>
		  		</fo:table>			
		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Surgery Intervention
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
    				
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Treatment Arm :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/SurgeryIntervention/treatmentArm"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
 					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Description of Treatment Arm :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/SurgeryIntervention/description"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Site of Intervention (Category) :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/SurgeryIntervention/AnatomicSite/category"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Site of Intervention :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/SurgeryIntervention/AnatomicSite/name"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Date of Intervention :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/SurgeryIntervention/interventionDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			</fo:table-body>
		  		</fo:table>								  		  		

  				<fo:block break-before="page" xsl:use-attribute-sets="sub-head" > 
		  			Medical Device
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
    				
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
										
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Brand Name :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Common Name :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Device Type :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/deviceType"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			  
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Manufacturer Name :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerName"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Manufacturer City :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerCity"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Manufacturer State/Province :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/manufacturerState"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>

		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Model Number :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Implanted Date :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Explanted Date :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Is this a Single-use Device that was Reprocessed and Reused on a Patient? :
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
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Name of Reprocessor :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/reprocessorName"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Address of Reprocessor :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/reprocessorAddress"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>		  			    		  			    		  			    		  			    		  			    		  			    		  			    		  			    		  			    		  			    		  			    		  			    
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell >
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Device Available for Evaluation? (Do not send to FDA) :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventReport/MedicalDevice/EvaluationAvailability"/>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" margin-left="2mm" > 
						  			Returned Date :
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="AdverseEventReport/MedicalDevice/returnedDate"/>
			   						</xsl:call-template>
						  		</fo:block>      							
      						</fo:table-cell>
		  			    </fo:table-row>			  			    

		  			</fo:table-body>
		  		</fo:table>								  		  		

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Prior Therapies
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
    				
		  		<fo:table>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="20%"/>
								
						
		  			<fo:table-body>
		  			
		 			  		<fo:table-row xsl:use-attribute-sets="tr-height-1" >
		     						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Therapy 
							  		</fo:block>      							
		     						</fo:table-cell>
		     						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Therapy Start Date
							  		</fo:block>      							
		     						</fo:table-cell>
		     						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Therapy End Date
							  		</fo:block>      							
		     						</fo:table-cell>
		     						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Comments 
							  		</fo:block>      							
		     						</fo:table-cell>
		     						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Chemotherapy Agents 
							  		</fo:block>      							
		     						</fo:table-cell>      						      						      						
			  			    </fo:table-row> 
	  			    		  			
		  	      			<!--  <xsl:apply-templates select="AdverseEventReport/AdverseEventPriorTherapy"/> -->
		  	      			
						<xsl:for-each select="AdverseEventReport/AdverseEventPriorTherapy">
			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			<xsl:value-of select="PriorTherapy/text"/><xsl:value-of select="other"/>
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="startDate"/>
			   						</xsl:call-template>
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="endDate"/>
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
			  			    </fo:table-row> 
		  			    </xsl:for-each>  			  
 					


		  			</fo:table-body>
		  		</fo:table>	
		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Pre-Existing Conditions
		  		</fo:block>
				<xsl:for-each select="AdverseEventReport/AdverseEventPreExistingCond">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="PreExistingCondition/text"/><xsl:value-of select="other"/>
						  		</fo:block> 				
				</xsl:for-each>
						  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:block xsl:use-attribute-sets="normal" >  </fo:block>

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Sites of Metastatic Disease
		  		</fo:block>
				<xsl:for-each select="AdverseEventReport/DiseaseHistory/MetastaticDiseaseSite">
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AnatomicSite/name"/><xsl:value-of select="otherSite"/>
						  		</fo:block> 				
				</xsl:for-each>		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:block xsl:use-attribute-sets="normal" >  </fo:block>		  		

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	

  				<fo:block break-before="page" xsl:use-attribute-sets="sub-head" > 
		  			Protocol Agents
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		<fo:block xsl:use-attribute-sets="label" > Treatment Assignment Code : </fo:block>
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

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Concomitant Medications
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		
		  			<xsl:for-each select="AdverseEventReport/ConcomitantMedication/Agent">
			  			<fo:block xsl:use-attribute-sets="normal" > 
			  				<xsl:value-of select="name"/>  
			  			</fo:block>
			  		</xsl:for-each>
		  		
		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					
  				<fo:block xsl:use-attribute-sets="sub-head" > 
		  			Other Contributing Causes
		  		</fo:block>
		  		
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  			<xsl:for-each select="AdverseEventReport/OtherCause">
			  			<fo:block xsl:use-attribute-sets="normal" > 
			  				<xsl:value-of select="text"/>  
			  			</fo:block>
			  		</xsl:for-each>	  		

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	

		  		
  				<fo:block  xsl:use-attribute-sets="sub-head" > 
					<xsl:if test="AdverseEventReport/AdverseEvent/AdverseEventCtcTerm/universal-term">
		  				Adverse Events (CTCAE)
		  			</xsl:if>
		  			<xsl:if test="AdverseEventReport/AdverseEvent/AdverseEventMeddraLowLevelTerm/universalTerm">
		  				Adverse Events (MedDRA)
		  			</xsl:if>
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>


		  		<fo:table>
					<fo:table-column column-width="20%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="15%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
																				
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  		<xsl:if test="AdverseEventReport/AdverseEvent/AdverseEventCtcTerm/universal-term">
						  			CTCAE CATEGORY
						  		</xsl:if>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Adverse Event
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			 Grade
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Hospitalization/ Comments Prolongation of Hospitalization 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Start Date
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			End Date
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Is Primary AE?
						  		</fo:block>      							
      						</fo:table-cell> 
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Comments
						  		</fo:block>      							
      						</fo:table-cell> 
		  			    </fo:table-row>
	  
 					<xsl:for-each select="AdverseEventReport/AdverseEvent"> 
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventCtcTerm/ctc-term/CtcCategory/name"/>
						  			
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="AdverseEventCtcTerm/universal-term"/>
						  			<xsl:value-of select="AdverseEventMeddraLowLevelTerm/universalTerm"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="grade"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="hospitalization"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			-
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			-
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			-
						  		</fo:block>      							
      						</fo:table-cell>  
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="comments"/>
						  		</fo:block>      							
      						</fo:table-cell> 
		  			    </fo:table-row>
					  </xsl:for-each>
		  			</fo:table-body>
		  		</fo:table>	


		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	

  				<fo:block  xsl:use-attribute-sets="sub-head" > 
		  			Attribution for Adverse Events
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>
		  		

		  	<xsl:for-each select="AdverseEventReport/AdverseEvent"> 
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="30%"/>
		  			<fo:table-body>
			
			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			Attribute to 
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="label" > 
							  			<xsl:value-of select="grade"/><xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;   </xsl:text><xsl:value-of select="AdverseEventCtcTerm/universal-term"/>
							  		</fo:block>      							
	      						</fo:table-cell>
			  			    </fo:table-row>

		  			</fo:table-body>
		  		</fo:table>	
		  		<fo:block space-after="0.2pt">
							<fo:leader leader-length="95%" leader-pattern="rule" rule-thickness="0.2pt"/>
				</fo:block>	
		  		<fo:table>
					<fo:table-column column-width="30%"/>
					<fo:table-column column-width="30%"/>
		  			<fo:table-body>
			
			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			Concomitant medications 
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			
							  		</fo:block>      							
	      						</fo:table-cell>
			  			    </fo:table-row>
			  			    <xsl:for-each select="ConcomitantMedicationAttribution"> 
				  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;   </xsl:text><xsl:value-of select="ConcomitantMedication/Agent/name"/> <xsl:value-of select="ConcomitantMedication/other"/>
								  		</fo:block>      							
		      						</fo:table-cell>
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:value-of select="attribution"/>
								  		</fo:block>      							
		      						</fo:table-cell>
				  			    </fo:table-row>
			  			    </xsl:for-each>
			  			    
			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			Other causes 
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  		</fo:block>      							
	      						</fo:table-cell>
			  			    </fo:table-row>

			  			    <xsl:for-each select="OtherCauseAttribution"> 
				  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;   </xsl:text><xsl:value-of select="OtherCause/text"/>
								  		</fo:block>      							
		      						</fo:table-cell>
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:value-of select="attribution"/>
								  		</fo:block>      							
		      						</fo:table-cell>
				  			    </fo:table-row>
			  			    </xsl:for-each>

			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			Course 
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  		</fo:block>      							
	      						</fo:table-cell>
			  			    </fo:table-row>

			  			    <xsl:for-each select="CourseAgentAttribution"> 
				  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:text disable-output-escaping="yes">&amp;#160; &amp;#160;   </xsl:text><xsl:value-of select="CourseAgent/StudyAgent/Agent/name"/>
								  		</fo:block>      							
		      						</fo:table-cell>
		      						<fo:table-cell>
								  		<fo:block xsl:use-attribute-sets="normal" > 
								  			<xsl:value-of select="attribution"/>
								  		</fo:block>      							
		      						</fo:table-cell>
				  			    </fo:table-row>
			  			    </xsl:for-each>


			  			    
			  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  			 
							  		</fo:block>      							
	      						</fo:table-cell>
	      						<fo:table-cell>
							  		<fo:block xsl:use-attribute-sets="normal" > 
							  		</fo:block>      							
	      						</fo:table-cell>
			  			    </fo:table-row>
			  			    			  			    
			  			    			  			    			  			    
		  			</fo:table-body>
		  		</fo:table>					
				
			 </xsl:for-each>	
			 	  			
	  		
	  									  				  		

		  			<fo:block>
						<fo:leader leader-length="95%" leader-pattern="rule"/>
					</fo:block>	
					

  				<fo:block  xsl:use-attribute-sets="sub-head" > 
		  			Abnormal and Relevant Normal Lab Results
		  		</fo:block>
		  		<fo:block> <xsl:text disable-output-escaping="yes">&amp;#160;</xsl:text> </fo:block>


		  		<fo:table>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
					<fo:table-column column-width="10%"/>
																				
		  			<fo:table-body>
		  	      					
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Lab 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Baseline date
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			 Value
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Worst Date 
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Value
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Recovery/Latest Date
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Value	
						  		</fo:block>      							
      						</fo:table-cell> 
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Microbiology Site
						  		</fo:block>      							
      						</fo:table-cell> 
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Date
						  		</fo:block>      							
      						</fo:table-cell> 
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="label" > 
						  			Infectious Agent
						  		</fo:block>      							
      						</fo:table-cell> 
		  			    </fo:table-row>
		  			  
 					 <xsl:for-each select="AdverseEventReport/Lab"> 
		  			    <fo:table-row xsl:use-attribute-sets="tr-height-1" >
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="name"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="baseline/date"/>
			   						</xsl:call-template>						  			
						  		</fo:block> 
						  		     							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="baseline/value"/>
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="nadir/date"/>
			   						</xsl:call-template>						  			
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="nadir/value"/>
						  		</fo:block>      							
      						</fo:table-cell>      						      						      						      						
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
									<xsl:call-template name="standard_date">
									        <xsl:with-param name="date" select="recovery/date"/>
			   						</xsl:call-template>						  			
						  		</fo:block>      							
      						</fo:table-cell>
      						<fo:table-cell>
						  		<fo:block xsl:use-attribute-sets="normal" > 
						  			<xsl:value-of select="recovery/value"/>
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
		  			    </fo:table-row>
					</xsl:for-each>
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
