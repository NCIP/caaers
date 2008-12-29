<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<title>${tab.longTitle}</title>

    <style type="text/css">
        div.row div.label { width: 11em; }
        div.row div.value { margin-left: 12em; }
    </style>

    <tags:javascriptLink name="hover-display" />
	<tags:dwrJavascriptLink objects="createStudy"/>

	<script language="JavaScript">

	Event.observe(window, "load", function() {

        if ($('study.aeTerminology.term').options[0].selected) {
            $('study.aeTerminology.ctcVersion-row').style.display = "";
            $('study.otherMeddra-row').style.display = "";
            $('study.aeTerminology.meddraVersion-row').style.display = "none";
            $('study.aeTerminology.meddraVersion').options.selectedIndex = 0;
        } else {
            $('study.aeTerminology.ctcVersion-row').style.display = "none";
            $('study.otherMeddra-row').style.display = "none";
            $('study.aeTerminology.meddraVersion-row').style.display = "";
            $('study.aeTerminology.ctcVersion').options.selectedIndex = 0;
        }
        Event.observe("study.aeTerminology.term", "change", function() { showTerms(); })
		
		function showTerms(){
			$('study.aeTerminology.meddraVersion-row').style.display="none";
			$('study.aeTerminology.ctcVersion-row').style.display="none";
			$('study.otherMeddra-row').style.display="none";
			if($('study.aeTerminology.term').options[0].selected){
				Effect.toggle($('study.aeTerminology.ctcVersion-row'), 'slide');
				Effect.toggle($('study.otherMeddra-row'), 'slide');
				$('study.aeTerminology.meddraVersion').options.selectedIndex = 0;
			}else{
				Effect.toggle($('study.aeTerminology.meddraVersion-row'), 'slide');
				$('study.aeTerminology.ctcVersion').options.selectedIndex = 0;
				$('study.otherMeddra').options.selectedIndex = 0;
			}
		}
		
		Event.observe("study.diseaseTerminology.diseaseCodeTerm", "change", function(){ showDiseaseMeddraTerms(); })
		
		function showDiseaseMeddraTerms(){
			var row = document.getElementById('diseaseMeddraOption');
			if ($('study.diseaseTerminology.diseaseCodeTerm').options[1].selected){
                row.style.display = '';
			} else{
                row.style.display = 'none';
			}
		}
        showDiseaseMeddraTerms();

		//Calls CreateStudyAjaxFacade:matchOrganization(..)
		AE.createStandardAutocompleter('study.primaryFundingSponsorOrganization',
			 function(autocompleter, text) {
				createStudy.matchOrganization(text, function(values) {
				  autocompleter.setChoices(values)
				 })
			 },
			 function(organization) { 
				 var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
			   return organization.name + nciInstituteCode

			 }

		);

        AE.createStandardAutocompleter('study.studyCoordinatingCenter.organization',
                function(autocompleter, text) {
                    createStudy.matchOrganization(text, function(values) {
                        autocompleter.setChoices(values)
                    })

                },

                function(organization) {
                    var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
                    return organization.name + nciInstituteCode
                }
                );

		//populate the name of the associated organization in sponsor & coordinating center field
		<c:if test="${not empty command.study.primaryFundingSponsorOrganization.fullName}">
		    $('study.primaryFundingSponsorOrganization-input').value = '${command.study.primaryFundingSponsorOrganization.fullName}';
		</c:if>
		<c:if test="${not empty command.study.studyCoordinatingCenter.organization.fullName}">
		    $('study.studyCoordinatingCenter.organization-input').value = '${command.study.studyCoordinatingCenter.organization.fullName}';
		</c:if>
	});

	</script>
     
</head>
<body>
<study:summary/>

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
  <jsp:attribute name="repeatingFields">
      
        <p><tags:instructions code="study.study_details.top"/></p>
        <c:forEach items="${fieldGroups.studyDetails.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>

    <%----%>
    <chrome:division title="Adverse event coding terminology">

        <c:forEach items="${fieldGroups.scFieldGroup.fields}" var="field" varStatus="status" begin="0" end="0">
            <tags:renderRow field="${field}"/>
        </c:forEach>

        <c:forEach var="i" begin="1" end="3">
            <ui:row path="${fieldGroups.scFieldGroup.fields[i].propertyName}">
                <jsp:attribute name="label"><ui:label required="true" path="${fieldGroups.scFieldGroup.fields[i].propertyName}" text="${fieldGroups.scFieldGroup.fields[i].displayName}"/></jsp:attribute>
                <jsp:attribute name="value"><ui:select path="${fieldGroups.scFieldGroup.fields[i].propertyName}" required="false" options="${fieldGroups.scFieldGroup.fields[i].attributes.options}" /></jsp:attribute>
            </ui:row>
        </c:forEach>

    </chrome:division>
    <%----%>

    <chrome:division title="Disease coding terminology">
        <c:forEach begin="0" end="0" items="${fieldGroups.sdcFieldGroup.fields}" var="field" varStatus="status">
            <tags:renderRow field="${field}"/>
        </c:forEach>
        <div id="diseaseMeddraOption" style="display:none">
            <c:forEach begin="1" end="1" items="${fieldGroups.sdcFieldGroup.fields}" var="field" varStatus="status">
                <ui:row path="${field.propertyName}">
                    <jsp:attribute name="label"><ui:label required="true" path="${field.propertyName}" text="${field.displayName}"/></jsp:attribute>
                    <jsp:attribute name="value"><ui:select path="${field.propertyName}" required="false" options="${field.attributes.options}" /></jsp:attribute>
                </ui:row>
            </c:forEach>
        </div>
    </chrome:division>
    
     <chrome:division title="Study method details">
         <c:forEach items="${fieldGroups.dcpFieldGroup.fields}" var="field" varStatus="status">
             <tags:renderRow field="${field}"/>
         </c:forEach>
     </chrome:division>

     <chrome:division title="Expedited report formats">
         <c:forEach items="${fieldGroups.rfFieldGroup.fields}" var="field" varStatus="status">
             <tags:renderRow field="${field}"/>
         </c:forEach>
     </chrome:division>
        
    <chrome:division title="Coordinating center details">
        <c:forEach items="${fieldGroups.ccFieldGroup.fields}" var="field" varStatus="status">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </chrome:division>

      <chrome:division title="Funding sponsor details">
          <c:forEach items="${fieldGroups.fsFieldGroup.fields}" var="field" varStatus="status">
              <tags:renderRow field="${field}"/>
          </c:forEach>
      </chrome:division>
  </jsp:attribute>

</tags:tabForm>

</body>
</html>
