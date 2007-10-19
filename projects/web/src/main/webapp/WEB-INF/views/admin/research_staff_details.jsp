<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>

<html>
<head>
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
       	$('organization-input').value = '${command.organization.name}';
       }
  	 //initialze the auto completer field.
	 AE.createStandardAutocompleter('organization', 
     	function(autocompleter, text) {
    		createIND.matchOrganization(text, function(values) {
      	 		autocompleter.setChoices(values)
      		})
    	},
        function(organization) { 
    		return organization.name 
    	}
     );
     
    }); 

    </script>
</head>
<body>
<div class="tabpane">
<ul id="workflow-tabs" class="tabs autoclear">
    <li class="tab selected">
        <div><a href="createResearchStaff">Create Research Staff</a></div>
    </li>
    <li class="tab">
        <div><a href="searchResearchStaff">Search Research Staff</a></div>
    </li>
</ul>
<br/>

<tags:tabForm tab="${tab}" flow="${flow}" formName="researchStaffForm">
<jsp:attribute name="repeatingFields">
	<input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
	<input type="hidden" name="_finish" value="true"/>

 <chrome:division title="Site">
	<c:forEach items="${fieldGroups.site.fields}" var="field">
	  <csmauthz:accesscontrol domainObject="${organization}"
                        hasPrivileges="ACCESS" authorizationCheckName="siteAuthorizationCheck">
      <tags:renderRow field="${field}"/>
	  </csmauthz:accesscontrol>
	</c:forEach>
 </chrome:division>

 <chrome:division title="Research Staff Details">
  <div class="leftpanel">
            <c:forEach begin="0" end="3"
                       items="${fieldGroups.researchStaff.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
  </div>
  <div class="rightpanel">
        <td>
        <c:if test="${(empty command.id) or (command.id le 0)}">
         <c:forEach begin="4" end="6"
                       items="${fieldGroups.researchStaff.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
    </c:if>
    <c:if test="${!(empty command.id) and (command.id gt 0)}">
         <c:forEach begin="4" end="4"
                       items="${fieldGroups.researchStaff.fields}" var="field">
            <div class="row">
    <div class="label">
        <tags:renderLabel field="${field}"/>
    </div>
    <div class="value"><tags:renderInputs field="${field}" disabled="True"/>
</div>
            </div>
        </c:forEach>
        <c:forEach begin="5" end="6"
                       items="${fieldGroups.researchStaff.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
 </c:if>
        </div>
        
        </td>
</div>
</chrome:division>

<chrome:division id="staff-details" title="User Role (Check all that apply)">

<div class="leftpanel">


    <div class="row">
        <div class="label label2">
            Participant coordinator:
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaersParticipantCordinator').value='true':$('caaersParticipantCordinator').value='false';" ${isCaaersPartcipantCordinator?'checked':'off' }/>
            <input id="caaersParticipantCordinator" type="hidden" name="caaersParticipantCordinator"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Study coordinator:
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaersStudyCordinator').value='true':$('caaersStudyCordinator').value='false';" ${isCaaersStudyCordinator?'checked':'off'} />
            <input id="caaersStudyCordinator" type="hidden" name="caaersStudyCordinator"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Adverse event coordinator:
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaersAECordinator').value='true':$('caaersAECordinator').value='false';" ${isCaaersAECordinator?'checked':'off'} />
            <input id="caaersAECordinator" type="hidden" name="caaersAECordinator"/>
        </div>
    </div>

    <div class="row">
        <div class="label label2">
            Site coordinator:
        </div>
        <div class="value value2">
            <input type="checkbox"
                   onclick="this.checked?$('caaersSiteCordinator').value='true':$('caaersSiteCordinator').value='false';" ${isCaaersSiteCordinator?'checked':'off'} />
            <input id="caaersSiteCordinator" type="hidden" name="caaersSiteCordinator"/>
        </div>
    </div>


</div>

</chrome:division>


</jsp:attribute>


</tags:tabForm>
</body>
</html>
