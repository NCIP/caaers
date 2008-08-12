<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">

<html>
<head>
  <title>${tab.longTitle}</title>

  <tags:dwrJavascriptLink objects="assignParticipant"/>
  <tags:dwrJavascriptLink objects="createAE, createStudy"/>


<script type="text/javascript">
Event.observe(window,'load',initializeAllEvents);
function initializeAllEvents(event)
{
    Event.observe('AddMedication', 'click', addMedication);
}

function addMedication(event)
{
    alert("addMedication");
    return;
<%--
              <tags:tabMethod  method="addMedication"
                         viewName="study/ajax/addMedicationFields" 
                         divElement="'medicationListCell'" 
                         formName="'command'"
                         onComplete="initializeAllEvents"/>
--%>
}
</script>

</head>
<body>


<form:form name="studyDetails">

<%--<tags:tabForm tab="${tab}" flow="${flow}" title="Study Details" willSave="false">--%>

<!-- BOX START -->
<chrome:box title="${tab.longTitle}" >


<chrome:division title="Disease Information" collapsable="true" id="mh-disease">

</chrome:division>

<chrome:division title="Metastatic Site" collapsable="true" id="mh-meta">
AAA
</chrome:division>

<chrome:division title="Pre-Existing Conditions" collapsable="true" id="mh-pre">
</chrome:division>

<chrome:division title="Con-Meds" collapsable="true" id="mh-conmeds">
AAA
</chrome:division>

<chrome:division title="Prior-Therapies" collapsable="true" id="mh-therapies">
AAA
</chrome:division>

</chrome:box>
<!-- BOX END -->


<%--</tags:tabForm>--%>

</form:form>

</html>
