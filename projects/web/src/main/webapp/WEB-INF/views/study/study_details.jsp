<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

	<title>${tab.longTitle}</title>
    <style type="text/css">
     div.row div.label { width: 11em; } 
     div.row div.value { margin-left: 12em; }
     
    </style> 
	<tags:javascriptLink name="hover-display" />

	<tags:includeScriptaculous/>

	<tags:dwrJavascriptLink objects="createStudy"/>

	<script language="JavaScript">

	Event.observe(window, "load", function() {

		Event.observe("terminology.term", "change", function() { showCtcTerms(); showMeddraTerms(); })

		showMeddraTerms()
		function showCtcTerms(){
				if ($('terminology.term').options[0].selected ){
					Effect.toggle($('terminology.ctcVersion-row'), 'slide');
					Effect.toggle($('terminology.meddraVersion-row'), 'slide');
				}
		}
		function showMeddraTerms(){
				if ($('terminology.term').options[1].selected){
					$('terminology.ctcVersion').options[0].selected=true
                        Effect.toggle($('terminology.ctcVersion-row'), 'slide');
					Effect.toggle($('terminology.meddraVersion-row'), 'slide');
				}else{
					$('terminology.meddraVersion-row').style.display="none"
				}
		}

		//Calls CreateStudyAjaxFacade:matchOrganization(..)
		AE.createStandardAutocompleter('primaryFundingSponsorOrganization',
			 function(autocompleter, text) {
				createStudy.matchOrganization(text, function(values) {
				  autocompleter.setChoices(values)
				 })
			 },
			 function(organization) { 
			   return organization.name 
			 }
		);
		AE.createStandardAutocompleter('studyCoordinatingCenter.organization',
			 function(autocompleter, text) {
				createStudy.matchOrganization(text, function(values) {
				  autocompleter.setChoices(values)
				 })
			 },
			 function(organization) { 
			   return organization.name 
			 }
		);

		//populate the name of the associated organization in sponsor & coordinating center field

		$('primaryFundingSponsorOrganization-input').value = '${command.primaryFundingSponsorOrganization.name}';
		$('studyCoordinatingCenter.organization-input').value = '${command.studyCoordinatingCenter.organization.name}';
	});

	</script>

</head>
<body>
<study:summary />

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="true" >
  <jsp:attribute name="repeatingFields">
       		<c:forEach items="${fieldGroups.studyDetails.fields}" var="field" >
			 <tags:renderRow field="${field}"/>
       		</c:forEach>
    <chrome:division title="Adverse event coding terminology" >
		 <c:forEach items="${fieldGroups.scFieldGroup.fields}" var="field" varStatus="status">
		  <tags:renderRow field="${field}"  />
		 </c:forEach>
    </chrome:division>
    <chrome:division title="Coordinating center details" >
		 <c:forEach items="${fieldGroups.ccFieldGroup.fields}" var="field" varStatus="status">
		  <tags:renderRow field="${field}"  />
		 </c:forEach>
    </chrome:division>
    <chrome:division title="Funding sponsor details" >
		 <c:forEach items="${fieldGroups.fsFieldGroup.fields}" var="field" varStatus="status">
		  <tags:renderRow field="${field}"  />
		 </c:forEach>
    </chrome:division>
  </jsp:attribute>

</tags:tabForm>
</body>
</html>
