<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
<title>Research Staff</title>
    <tags:stylesheetLink name="tabbedflow"/>
    <tags:includeScriptaculous/>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

    <style type="text/css">
        /* Override default lable length */
        div.row div.label2 {
            width: 16em;
        }

        div.row div.value2 {
            margin-left: 17em;
        }

        div.content {
            padding: 5px 15px;
        }
    </style>

    <tags:dwrJavascriptLink objects="createIND"/>
    <script type="text/javascript">
    Event.observe(window, "load", function() {
       if('${command.organization.name}'){
    	   if($('organization')){
    		   $('organization-input').value = '${command.organization.fullName}';
    	   }
       }
       
  	 //initialze the auto completer field.
  	 if($('organization')){
  		AE.createStandardAutocompleter('organization', 
  		     	function(autocompleter, text) {
  		    		createIND.matchOrganization(text, function(values) {
  		      	 		autocompleter.setChoices(values)
  		      		})
  		    	},
  		        function(organization) { 
  		    		 var nciInstituteCode = organization.nciInstituteCode == null ? "" : 
  		            							 " ( " + organization.nciInstituteCode + " ) ";
  					   return organization.name + nciInstituteCode  
  		    	}
  		);
  	 }
	 
     
    }); 

    </script>
</head>
<body>
<div class="tabpane">

    <div class="workflow-tabs2">


<ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab selected">
        <div><a href="createResearchStaff">Create Research Staff</a></div>
    </li>
    <li id="thirdlevelnav" class="tab">
        <div><a href="searchResearchStaff">Search Research Staff</a></div>
    </li>
</ul>
        </div>
<br/>
<tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm">
<jsp:attribute name="repeatingFields">
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>
<p>
<tags:instructions code="researchstaffdetails" />
</p>
 <chrome:division title="Site">
	<c:forEach items="${fieldGroups.site.fields}" var="field">
	  <csmauthz:accesscontrol domainObject="${organization}"
                        hasPrivileges="ACCESS" authorizationCheckName="siteAuthorizationCheck">
      <tags:renderRow field="${field}"/>
	  </csmauthz:accesscontrol>
	</c:forEach>
 </chrome:division>

 <chrome:division title="Details">
  <div class="leftpanel">
            <c:forEach begin="0" end="3"
                       items="${fieldGroups.researchStaff.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
  </div>
  <div class="rightpanel">
    <td>
         <c:forEach begin="4" end="6"
                       items="${fieldGroups.researchStaff.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    
	<c:if test="${authenticationMode == 'webSSO'}">
		<tags:renderRow field="${fieldGroups.researchStaff.fields[7]}"/>
	</c:if>
    </td>
  </div>
</chrome:division>

<chrome:division id="staff-details" title="User Role (Check all that apply)">

<div class="leftpanel">


    <div class="row">
        <div class="label label2">
            Subject coordinator
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaers_participant_cd').value='true':$('caaers_participant_cd').value='false';" ${caaers_participant_cd ? 'checked':'' }/>
            <input id="caaers_participant_cd" type="hidden" name="caaers_participant_cd"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Study coordinator
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaers_study_cd').value='true':$('caaers_study_cd').value='false';" ${caaers_study_cd ? 'checked':''} />
            <input id="caaers_study_cd" type="hidden" name="caaers_study_cd"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Adverse event coordinator
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaers_ae_cd').value='true':$('caaers_ae_cd').value='false';" ${caaers_ae_cd ? 'checked':''} />
            <input id="caaers_ae_cd" type="hidden" name="caaers_ae_cd" value="${caaers_ae_cd}"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Site coordinator
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaers_site_cd').value='true':$('caaers_site_cd').value='false';" ${caaers_site_cd ? 'checked' :''} />
            <input id="caaers_site_cd" type="hidden" name="caaers_site_cd" value="${caaers_site_cd}"/>
        </div>
    </div>


</div>

</chrome:division>


</jsp:attribute>


</tags:tabForm>
</body>
</html>
