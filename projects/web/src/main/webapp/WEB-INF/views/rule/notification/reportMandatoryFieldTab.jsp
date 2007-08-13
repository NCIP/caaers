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
    	  Cross/check the fields that are mandatory in adverse event entry screen when, <b>${command.name}</b> report definition is associated to a adverse event report.
    	  <br> 
    	</jsp:attribute>
		<jsp:attribute name="repeatingFields">
		
		<chrome:division title="Reporter information">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			   		<chrome:division title="Reporter details"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="Reporter info~Reporter details" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%">
			   		<chrome:division title="Physician details" cssClass="paired">
			    	  <rd:renderMandatoryFields key="Reporter info~Physician details" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
		
		<chrome:division title="Medical information">
		   <rd:renderMandatoryFields key="Medical info" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			  	  <td width="50%">
			   		<chrome:division title="Height"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB" >
					 <rd:renderMandatoryFields key="Medical info~Height" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%">
			   		<chrome:division title="Weight" cssClass="paired">
			    	  <rd:renderMandatoryFields key="Medical info~Weight" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			<chrome:division title="Metastatic disease information">
				<rd:renderMandatoryFields key="Medical info~Metastatic disease sites" />
			</chrome:division>
		</chrome:division>
		
		<chrome:division title="Intervention information">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" VALIGN="top">
			  <tr>
			  	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Radiation intervention"  cssClass="paired" style="border-right-width: 1px; border-right-style: solid; border-color: #C9E3EB">
					 <rd:renderMandatoryFields key="Radiation intervention" />
			   		</chrome:division>
			 	  </td>
			 	  <td width="50%" VALIGN="top">
			   		<chrome:division title="Surgery intervention" cssClass="paired">
			    	  <rd:renderMandatoryFields key="Surgery intervention" />
			   		</chrome:division>
			 	  </td>
			  </tr>
			</table>
			
		</chrome:division>
			
		</jsp:attribute>
	</tags:tabForm> 
</body>


</html>