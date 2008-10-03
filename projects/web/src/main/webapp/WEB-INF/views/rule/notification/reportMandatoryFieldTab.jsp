<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="rd" tagdir="/WEB-INF/tags/report" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <title>AE Report Mandatory Fields</title>

<style type="text/css">
   div.row div.label { width: 13em; } 
   div.row div.value { margin-left: 14em; }
</style>  
<link rel="shortcut icon" href="../../../images/caaers.ico" type="image/x-icon"/> 
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" >
    	<jsp:attribute name="instructions">
    	 <tags:instructions code="mandatoryfields" />
    	</jsp:attribute>
		<jsp:attribute name="repeatingFields">
		<chrome:division title="Adverse event">
			<rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION~Adverse events" />
		</chrome:division>
		<chrome:division title="Course and agent">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<chrome:division title=""  cssClass="paired" >
					 <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<chrome:division title="" cssClass="paired" style="border-left-width: 1px; border-left-style: solid; border-color: #C9E3EB">
			    	  <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION~Study Agents" />
			    	  <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION~Study Agents~Modified dose" heading="Modified dose" tabular="true" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
		</chrome:division>
		<chrome:division title="Reporter">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			   		<chrome:division title="Reporter details"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details" />
					<rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details~Address" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%">
			   		<chrome:division title="Physician details" cssClass="paired">
			    	  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details" />
					  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details~Address" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
		</chrome:division>
		<chrome:division title="Describe event">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
					 <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="0" endIndex="5" />
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			    	  <rd:renderMandatoryFields key="DESCRIPTION_SECTION" startIndex="6"/>
			 	  </td>
			  </tr>
			</table>
		</chrome:division>
		<chrome:division title="Subject details">
		   
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			  	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION" />
			 	  </td>
			 	  <td width="50%">
		    	  <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Weight" heading="Weight" tabular="true" />
  	    	      <rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Height" heading="Height" tabular="true" />
			 	  </td>
			  </tr>
			 <tr>
				<td>
					<chrome:division title="Pre-existing conditions">
						<rd:renderMandatoryFields key="PRE_EXISTING_CONDITION_SECTION~Pre-existing conditions" />
					</chrome:division>
				</td>
				<td>
					<chrome:division title="Metastatic disease information">
						<rd:renderMandatoryFields key="MEDICAL_INFO_SECTION~Metastatic disease sites" />
				  	</chrome:division>
				</td>
			</tr>
			 <tr>
				<td>
					<chrome:division title="Prior therapies">
						<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys" />
					</chrome:division>
				</td>
				<td>
					<chrome:division title="Concomitant Medications">
						<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~Medications" />
					</chrome:division>
				</td>
			</tr>
			</table>
		</chrome:division>
		
		
		

		<chrome:division title="Other contributing causes">
			<rd:renderMandatoryFields key="OTHER_CAUSE_SECTION~OtherCausess" />
		</chrome:division>
		<chrome:division title="Intervention information">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Radiation intervention"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="RADIATION_INTERVENTION_SECTION~RadiationInterventions" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Surgery intervention" cssClass="paired">
			    	  <rd:renderMandatoryFields key="SURGERY_INTERVENTION_SECTION~SurgeryInterventions" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
		
		<chrome:division title="Medical devices">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="0" endIndex="10"/>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		 <rd:renderMandatoryFields key="MEDICAL_DEVICE_SECTION~MedicalDevices" startIndex="11" />
			 	  </td>
			  </tr>
			</table>
		</chrome:division>
		
		<chrome:division title="Labs">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs" />
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Baseline" heading="Baseline" tabular="true"/>
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Worst" heading="Worst" tabular="true" />
					<rd:renderMandatoryFields key="LABS_SECTION~Labs~Recovery" heading="Recovery" tabular="true" />
			 	  </td>
			  </tr>
			</table>
		</chrome:division>
	
		</jsp:attribute>
	</tags:tabForm> 
</body>


</html>
