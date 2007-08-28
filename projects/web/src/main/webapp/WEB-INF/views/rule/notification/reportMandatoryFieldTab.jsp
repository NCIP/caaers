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
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" >
    	<jsp:attribute name="instructions">
    	  Select the fields of adverse event entry screen which are to be made mandatory, 
    	  when <b>${command.name}</b> report definition is associated to a adverse event report.
    	  <i> <br/> <b>TODO:</b> need a better instruction...</i>
    	  <br> 
    	</jsp:attribute>
		<jsp:attribute name="repeatingFields">
		<chrome:division title="Adverse events">
			<rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION" />
			<rd:renderMandatoryFields key="ADVERSE_EVENT_SECTION~Adverse events" />
		</chrome:division>
		<chrome:division title="Reporter information">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			   		<chrome:division title="Reporter details"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Reporter details" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%">
			   		<chrome:division title="Physician details" cssClass="paired">
			    	  <rd:renderMandatoryFields key="REPORTER_INFO_SECTION~Physician details" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
		
		<chrome:division title="Medical information">
		   <rd:renderMandatoryFields key="MEDICAL_INFO_SCECTION" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			   		<chrome:division title="Height"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB" >
					 <rd:renderMandatoryFields key="MEDICAL_INFO_SCECTION~Height" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%">
			   		<chrome:division title="Weight" cssClass="paired">
			    	  <rd:renderMandatoryFields key="MEDICAL_INFO_SCECTION~Weight" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			<chrome:division title="Metastatic disease information">
				<rd:renderMandatoryFields key="MEDICAL_INFO_SCECTION~Metastatic disease sites" />
			</chrome:division>
		</chrome:division>
		
		<chrome:division title="Intervention information">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Radiation intervention"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="RADIATION_INTERVENTION_SECTION" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Surgery intervention" cssClass="paired">
			    	  <rd:renderMandatoryFields key="SURGERY_INTERVENTION_SECTION" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
		
		<chrome:division title="Treatment Information">
			
			<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<chrome:division title=""  cssClass="paired" >
					 <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<chrome:division title="" cssClass="paired" style="border-left-width: 1px; border-left-style: solid; border-color: #C9E3EB">
			    	  <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION~Course Agents" />
			   		  <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION~Course Agents~Dose" tabular="true" heading="Dosage" singleRow="true"/>
			   		  <rd:renderMandatoryFields key="TREATMENT_INFO_SECTION~Course Agents~ModifiedDose" tabular="true" heading="Modified dose" singleRow="true"/>
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
		<chrome:division title="Labs">
		<rd:renderMandatoryFields key="LABS_SECTION~Labs" />
		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Baseline" heading="Baseline" singleRow="true" tabular="true" />
		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Worst" heading="Wrost" singleRow="true" tabular="true" />
		<rd:renderMandatoryFields key="LABS_SECTION~Labs~Recovery" heading="Recovery" singleRow="true" tabular="true" />
		</chrome:division>
		
		<chrome:division title="Prior Therapies">
		<rd:renderMandatoryFields key="PRIOR_THERAPIES_SECTION~Prior Therapys" />
		</chrome:division>
		
		<chrome:division title="Pre-Existing Condictions">
		<rd:renderMandatoryFields key="PRE_EXISTING_CONDITION_SECTION~AdverseEventPreExistingConds" />
		</chrome:division>
		
		<chrome:division>
		<rd:renderMandatoryFields key="CONCOMITANT_MEDICATION_SECTION~ConcomitantMedications" />
		</chrome:division>
		
		<chrome:division title="Other contributing causes">
			<rd:renderMandatoryFields key="OTHER_CAUSE_SECTION~OtherCausess" />
		</chrome:division>
		</jsp:attribute>
	</tags:tabForm> 
</body>


</html>