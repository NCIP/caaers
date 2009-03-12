<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<page:applyDecorator name="standardNoHeader">
<html>
 <head>
	<tags:stylesheetLink name="standard-form" />
	<style type="text/css">
		body {background: none; text-align:left;}
		div.row div.label {width: 12em;}
	 	div.row div.value, div.row div.extra { margin-left: 13em; }
        div.hr {font-size:1px; height: 1px;}
	</style>
     <script>
         Event.observe(window, "load", function() {
            if ($('reportingPeriod.treatmentAssignmentDescription').value != '')
                $('otherTA').checked = true;
         })
     </script>
    <%--<tags:javascriptLink name="prototype"/>--%>
</head>
<body>

<tags:standardForm title="Course/Cycle Information">
    <jsp:attribute name="instructions" />
    <jsp:attribute name="singleFields">

        <ui:row path="assignment.startDateOfFirstCourse">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[0]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:date path="assignment.startDateOfFirstCourse" field="${fieldGroups.ReportingPeriod.fields[0]}"/></jsp:attribute>
        </ui:row>

        <ui:row path="reportingPeriod.startDate">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[1]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:date path="reportingPeriod.startDate" field="${fieldGroups.ReportingPeriod.fields[1]}"/></jsp:attribute>
        </ui:row>

        <ui:row path="reportingPeriod.endDate">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[2]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:date path="reportingPeriod.endDate" field="${fieldGroups.ReportingPeriod.fields[2]}" /></jsp:attribute>
        </ui:row>

        <ui:row path="reportingPeriod.endDate">
             <jsp:attribute name="label"></jsp:attribute>
             <jsp:attribute name="value"><span style="font-size:8pt;"><b>Note:</b> Enter estimated end date if course/cycle is in-progress.</span></jsp:attribute>
        </ui:row>

        <ui:row path="reportingPeriod.epoch">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[3]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:select path="${fieldGroups.ReportingPeriod.fields[3].propertyName}" options="${fieldGroups.ReportingPeriod.fields[3].attributes.options}" field="${fieldGroups.ReportingPeriod.fields[3]}" /></jsp:attribute>
        </ui:row>

        <ui:row path="reportingPeriod.cycleNumber">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[4]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:text path="${fieldGroups.ReportingPeriod.fields[4].propertyName}" field="${fieldGroups.ReportingPeriod.fields[4]}"/></jsp:attribute>
        </ui:row>

        <tags:table bgColor="#cccccc" contentID="_ta">
            <table width="100%" cellspacing="1" cellpadding="2" style="font-size:10pt;">
                <tr bgcolor="#E4E4E4">
                    <th><b>Treatment Assignment</b></th>
                    <th><b>Description</b></th>
                </tr>
                <c:forEach items="${command.study.treatmentAssignments}" var="ta">
                    <tr bgcolor="white">
                        <td><ui:radio path="reportingPeriod.treatmentAssignment" value="${ta.id}" />&nbsp;${ta.code}
                        <td>${ta.escapedDescription}
                    </tr>
                </c:forEach>
                <tr bgcolor="white">
                    <td><ui:radio path="reportingPeriod.treatmentAssignment" value="" id="otherTA"/>&nbsp;Other
                    <td><ui:textarea rows="1" path="reportingPeriod.treatmentAssignmentDescription" cols="100"></ui:textarea>
                </tr>
            </table>
        </tags:table>
    </jsp:attribute>
    <jsp:attribute name="navButtons"><div align="right"><input type="image" src="<c:url value="/images/blue/save_btn.png" />" id="flow-update" class="tab${tabNumber}" value="Save" alt="Save"/></div></jsp:attribute>

</tags:standardForm>

</body>
</html>
</page:applyDecorator>    