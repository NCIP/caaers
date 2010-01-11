<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>AE Report Mandatory Fields</title>

<style type="text/css">
   div.row div.label { width: 13em; } 
   div.row div.value { margin-left: 14em; }
   .half {
   		float:left;
		width:46%;
		margin:0 10px;
   }
</style>  
<link rel="shortcut icon" href="../../../images/caaers.ico" type="image/x-icon"/> 
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" hideBox="true">
    	<jsp:attribute name="header">
    		<div class="summary">
    			<caaers:message code="header.reportdefinition.mandatoryfields" arguments="${command.reportDefinition.name},${command.reportDefinition.organization.name}" />
    		</div>
		</jsp:attribute>
    	<jsp:attribute name="instructions">
    	 <tags:instructions code="mandatoryfields" />
    	</jsp:attribute>
		<jsp:attribute name="repeatingFields">
		<chrome:box title="Adverse events">
			<rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION~Adverse events" />
		</chrome:box>
		<chrome:box title="Course & Agent">
				<div class="half">
			   		<chrome:division title="Course"  cssClass="paired" >
					 <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION" />
			   		</chrome:division>
				</div>
				<div class="half">
                    <chrome:division title="Agent" cssClass="paired">
                        <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION~Study Agents" />
                         <rd:renderMandatoryFields key="AGENTS_INTERVENTION_SECTION" />
                    </chrome:division>
				</div>
				<br style="clear:both;"/>
			</chrome:box>
		<chrome:box title="Reporter">
				<div class="half">
			   		<chrome:division title="Reporter details" >
					 <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details" />
					<rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details~Address" />
			   		</chrome:division>
				</div>
				<div class="half">
			   		<chrome:division title="Physician details">
			    	  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details" />
					  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details~Address" />
					</chrome:division>
				</div>
			<br style="clear:both;"/>
		</chrome:box>
		<chrome:box title="Describe event">
		   <div class="half">
					 <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="0" endIndex="5" />
				</div>
			 	  <div class="half">
			    	  <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="6"/>
				</div>
<br style="clear:both;"/>
		</chrome:box>
		<chrome:box title="Subject details">
		   
			<div class="half">
			  	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION" />
			  	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Date of initial diagnosis" heading="Date of initial diagnosis" tabular="true"/>
			</div>
			<div class="half">
		    	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Weight" heading="Weight" tabular="true" />
  	    	      <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Height" heading="Height" tabular="true" />
			</div>
			<br style="clear:both;"/>
			 <div class="half">
					<chrome:division title="Pre-existing conditions">
						<rd:renderMandatoryFields key="PRE_EXISTING_CONDITION_SECTION~Pre-existing conditions" />
					</chrome:division>
					</div>
				<div class="half">
					<chrome:division title="Metastatic disease information">
						<rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Metastatic disease sites" />
				  	</chrome:division>
					</div>
					<br style="clear:both;"/>
		<div class="half">
					<chrome:division title="Prior therapies">
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys" />
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~Therapy start date" heading="Therapy start date" tabular="true"/>
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys~Therapy end date" heading="Therapy end date" tabular="true" />
					</chrome:division>
					</div>
				<div class="half">
					<chrome:division title="Concomitant Medications">
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications" />
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~Start date" heading="Start date" tabular="true" />
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications~End date" heading="End date" tabular="true"/>
					</chrome:division>
</div>
<br style="clear:both;"/>
		</chrome:box>
		
		
		

		<chrome:box title="Other contributing causes">
			<rd:renderMandatoryFields key="OTHER_CAUSE_SECTION~OtherCausess" />
		</chrome:box>
		<chrome:box title="Intervention information">
			<div class="half">
			   		<chrome:division title="Radiation intervention">
					 <rd:renderMandatoryFields key="RADIATION_INTERVENTION_SECTION~RadiationInterventions" />
			   		</chrome:division>
					</div>
			 	  <div class="half">
			   		<chrome:division title="Surgery intervention">
			    	  <rd:renderMandatoryFields key="SURGERY_INTERVENTION_SECTION~SurgeryInterventions" />
			   		</chrome:division>
			 	</div>
			<br style="clear:both;"/>
		</chrome:box>
		
		<chrome:box title="Medical devices">
		<div class="half">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="0" endIndex="10"/>
					 </div>
			 	  <div class="half">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="11" />
			 	  </div>
				  <br style="clear:both;"/>
		</chrome:box>
		
		<chrome:box title="Labs">
		<div class="half">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs" />
					</div>
			 	  <div class="half">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Baseline" heading="Baseline" tabular="true"/>
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Worst" heading="Worst" tabular="true" />
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Recovery" heading="Recovery" tabular="true" />
			 	  </div>
				  <br style="clear:both;"/>
		</chrome:box>
	
		</jsp:attribute>
	</tags:tabForm> 
</body>


</html>
