<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="csmauthz" uri="http://csm.ncicb.nci.nih.gov/authz" %>
<link rel="stylesheet" type="text/css"
      href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
    <tags:stylesheetLink name="tabbedflow"/>
    <tags:stylesheetLink name="participant"/>
    <tags:includeScriptaculous/>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

    <style type="text/css">
        /* Override default lable length */
        div.row div.label {
            width: 9em;
        }

        div.row div.value {
            margin-left: 10em;
        }

        div.content {
            padding: 5px 15px;
        }
    </style>
    <script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>

    <tags:dwrJavascriptLink objects="search"/>
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


<jsp:attribute name="singleFields">
<div>
    <input type="hidden" name="_action" value="">
    <input type="hidden" name="_selected" value="">
</div>
<input type="hidden" name="_finish" value="true"/>


</jsp:attribute>
<jsp:attribute name="repeatingFields">


<chrome:division title="Site">
<c:forEach items="${fieldGroups.site.fields}" var="field">
<csmauthz:accesscontrol domainObject="${organization}"
                        hasPrivileges="ACCESS" authorizationCheckName="siteAuthorizationCheck">
    <tags:renderRow field="${field}"/>
</csmauthz:accesscontrol>
</c:forEach>
</chrome:division>

<chrome:division title="Research Staff Details">
<table id="test2" class="single-fields">
    <tr>
        <td>
            <c:forEach begin="0" end="3"
                       items="${fieldGroups.researchStaff.fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </td>
        <td><c:forEach begin="4" end="6"
                       items="${fieldGroups.researchStaff.fields}" var="field">
            <tags:renderRow field="${field}"/>
        </c:forEach>
        </td>
    </tr>

</table>

</chrome:division>

<chrome:division id="staff-details" title="User Role (Check all that apply)">

<div class="leftpanel">


    <div class="row">
        <div class="label">
            Participant cordinator:
        </div>
        <div class="value">
            <input type="checkbox"
                   onclick="this.checked?$('caaersParticipantCordinator').value='true':$('caaersParticipantCordinator').value='false';" ${isCaaersPartcipantCordinator?'checked':'off' }/>
            <input id="caaersParticipantCordinator" type="hidden" name="caaersParticipantCordinator"/>
        </div>
    </div>

    <div class="row">
        <div class="label">
            Study cordinator:
        </div>
        <div class="value">
            <input type="checkbox"
                   onclick="this.checked?$('caaersStudyCordinator').value='true':$('caaersStudyCordinator').value='false';" ${isCaaersStudyCordinator?'checked':'off'} />
            <input id="caaersStudyCordinator" type="hidden" name="caaersStudyCordinator"/>
        </div>
    </div>

    <div class="row">
        <div class="label">
            Adverse event cordinator:
        </div>
        <div class="value">
            <input type="checkbox"
                   onclick="this.checked?$('caaersAECordinator').value='true':$('caaersAECordinator').value='false';" ${isCaaersAECordinator?'checked':'off'} />
            <input id="caaersAECordinator" type="hidden" name="caaersAECordinator"/>
        </div>
    </div>


</div>

</chrome:division>


</jsp:attribute>


</tags:tabForm>
</body>
</html>
