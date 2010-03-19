<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<title>${tab.longTitle}</title>

    <style type="text/css">
        div.row div.label { width: 11em; }
        div.row div.value { margin-left: 12em; }
    </style>

	<tags:dwrJavascriptLink objects="createStudy"/>

	<script language="JavaScript">

        function _update(d) {
            jQuery('#' + d + '_autocompleter').show();
            jQuery('#' + d + '_readonly').hide();
        }

        function _cancel(d) {
            jQuery('#' + d + '_autocompleter').hide();
            jQuery('#' + d + '_readonly').show();
        }


	Event.observe(window, "load", function() {

        if ($('study.aeTerminology.term').options[0].selected) {
            $('study.aeTerminology.ctcVersion-row').style.display = "";
            $('study.otherMeddra-row').style.display = "";
            $('study.aeTerminology.meddraVersion-row').style.display = "none";
            $('study.aeTerminology.meddraVersion').options.selectedIndex = 0;
            if ($('study.aeTerminology.ctcVersion').options.selectedIndex == 0) $('study.aeTerminology.ctcVersion').addClassName("required"); else $('study.aeTerminology.ctcVersion').addClassName("valueOK");
            $('study.aeTerminology.meddraVersion').removeClassName("required");
        } else {
            $('study.aeTerminology.ctcVersion-row').style.display = "none";
            $('study.otherMeddra-row').style.display = "none";
            $('study.aeTerminology.meddraVersion-row').style.display = "";
            $('study.aeTerminology.ctcVersion').options.selectedIndex = 0;
            if ($('study.aeTerminology.meddraVersion').options.selectedIndex == 0) $('study.aeTerminology.meddraVersion').addClassName("required"); else $('study.aeTerminology.meddraVersion').addClassName("valueOK");
            $('study.aeTerminology.ctcVersion').removeClassName("required");
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
                $('study.aeTerminology.ctcVersion').addClassName("required");
                $('study.aeTerminology.meddraVersion').removeClassName("required");
			}else{
				Effect.toggle($('study.aeTerminology.meddraVersion-row'), 'slide');
				$('study.aeTerminology.ctcVersion').options.selectedIndex = 0;
				$('study.otherMeddra').options.selectedIndex = 0;
                $('study.aeTerminology.ctcVersion').removeClassName("required");
                $('study.aeTerminology.meddraVersion').addClassName("required");

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
                    createStudy.restrictOrganizations(text, function(values) {
                      autocompleter.setChoices(values)
                     })
                 },
                 function(organization) {

                        var image;
                        if(organization.externalId != null){
                                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
                        } else {
                                  image = '';
                        }

                     var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";
                   return image + "" +organization.name + nciInstituteCode

                 }

            );


            AE.createStandardAutocompleter('study.studyCoordinatingCenter.organization',
                function(autocompleter, text) {
                    createStudy.restrictOrganizations(text, function(values) {
                        autocompleter.setChoices(values)
                    })

                },

                function(organization) {

	                var image;
	            	if(organization.externalId != null){
	                          image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>';
	                } else {
	                          image = '';
	                }

                    var nciInstituteCode = organization.nciInstituteCode == null ? "" : " ( " + organization.nciInstituteCode + " ) ";

                    return image + "" +organization.name + nciInstituteCode
                }
                );


		//populate the name of the associated organization in sponsor & coordinating center field
		<c:if test="${not empty command.study.primaryFundingSponsorOrganization.fullName}">
		    $('study.primaryFundingSponsorOrganization-input').value = "${command.study.primaryFundingSponsorOrganization.fullName}";
		</c:if>
		<c:if test="${not empty command.study.studyCoordinatingCenter.organization.fullName}">
		    $('study.studyCoordinatingCenter.organization-input').value = "${command.study.studyCoordinatingCenter.organization.fullName}";
		</c:if>
	});

	</script>

</head>
<body>
<study:summary/>

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
  <jsp:attribute name="repeatingFields">
    <input type="hidden" name="_action" value="">
      
        <p><tags:instructions code="study.study_details.study.top"/></p>
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
                <jsp:attribute name="label"><ui:label required="${i < 3}" path="${fieldGroups.scFieldGroup.fields[i].propertyName}" text="${fieldGroups.scFieldGroup.fields[i].displayName}"/></jsp:attribute>
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





    <c:set var="hasCC" value="${command.study.studyCoordinatingCenter.organization != null}" />
    <c:set var="hasFS" value="${command.study.primaryFundingSponsor.organization != null}" />

    <c:set var="ccField" value="${fieldGroups.ccFieldGroup.fields[0]}" />
    <c:set var="fsField" value="${fieldGroups.fsFieldGroup.fields[0]}" />


    <chrome:division title="Coordinating center details">
        <c:if test="${hasCC}">
            <div class="row" id="_cc_readonly">
                <div class="label"><ui:label path="${ccField.propertyName}" labelProperty="${ccField.attributes.labelProperty}" text="${ccField.displayName}" mandatory="${ccField.attributes.mandatory}" required="${ccField.required}"/></div>
                <div class="value">${command.study.studyCoordinatingCenter.organization}&nbsp;<tags:button icon="edit" size="small" value="Update" color="blue" onclick="_update('_cc');" type="button"/></div>
            </div>
        </c:if>


        <div class="row" style="display: ${hasCC ? 'none' : ''}" id="_cc_autocompleter">
            <div class="label"><ui:label path="${ccField.propertyName}" labelProperty="${ccField.attributes.labelProperty}" text="${ccField.displayName}" mandatory="${ccField.attributes.mandatory}" required="${ccField.required}"/></div>
            <div class="value"><ui:autocompleter path="${ccField.propertyName}" />&nbsp;<c:if test="${hasCC}"><tags:button icon="x" size="small" value="Cancel" color="blue" onclick="_cancel('_cc');" type="button"/></c:if></div>
        </div>

        <tags:renderRow field="${fieldGroups.ccFieldGroup.fields[1]}" />
    </chrome:division>

    <chrome:division title="Funding sponsor details">

        <c:if test="${hasFS}">
            <div class="row" id="_fs_readonly">
                <div class="label"><ui:label path="${ccField.propertyName}" labelProperty="${ccField.attributes.labelProperty}" text="${ccField.displayName}" mandatory="${ccField.attributes.mandatory}" required="${ccField.required}"/></div>
                <div class="value">${command.study.primaryFundingSponsor.organization}&nbsp;<tags:button icon="edit" size="small" value="Update" color="blue" onclick="_update('_fs');" type="button"/></div>
            </div>
        </c:if>


        <div class="row" style="display: ${hasFS ? 'none' : ''}" id="_fs_autocompleter">
            <div class="label"><ui:label path="${fsField.propertyName}" labelProperty="${fsField.attributes.labelProperty}" text="${fsField.displayName}" mandatory="${fsField.attributes.mandatory}" required="${fsField.required}"/></div>
            <div class="value"><ui:autocompleter path="${fsField.propertyName}" />&nbsp;<c:if test="${hasFS}"><tags:button icon="x" size="small" value="Cancel" color="blue" onclick="_cancel('_fs');" type="button"/></c:if></div>
        </div>

        <tags:renderRow field="${fieldGroups.fsFieldGroup.fields[1]}" />
    </chrome:division>




      
  </jsp:attribute>

</tags:tabForm>

</body>
</html>
