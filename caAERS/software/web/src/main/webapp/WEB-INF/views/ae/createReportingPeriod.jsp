<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<page:applyDecorator name="standardNoHeader">

<html>
 <head>
	<tags:css name="standard-form" />
	<style type="text/css">
		body {background: none; text-align:left; font-size:10pt;}
		div.row div.label {width: 16em;}
	 	div.row div.value, div.row div.extra { margin-left: 17em; }
		td {
        	text-align:left;
			padding:5px;
        }
		button td {
			padding:0;
		}
	</style>
     <script>
         var countTA = ${fn:length(command.study.activeTreatmentAssignments)};
         Event.observe(window, "load", function() {
            if ($('reportingPeriod.treatmentAssignmentDescription').value != '' || countTA == 0)
                $('otherTA').checked = true;
         });

         function clearOtherTAC() {
             $('reportingPeriod.treatmentAssignmentDescription').value = '';
         }

         function editFirstCourseDate() {
             if (confirm("<caaers:message code="LBL_change1tCourseDate" />")) {
                 $('CRP_readOnlyField').hide();
                 $('CRP_editField').show();
             }
         }
         
          function deleteCourse(){
          	var form = document.getElementById('command');
          	if(confirm('Are you sure you want to delete the course?')){
	          	form._action.value = 'delete';
    	      	form.submit();
    	    }
         }
     </script>
    <%--<tags:javascriptLink name="prototype"/>--%>
</head>
<body>
<tags:standardForm title="Course/Cycle Information">
    <jsp:attribute name="instructions" />
    <jsp:attribute name="singleFields">
		<input type="hidden" name="_action" id="_action" value="">
        <caaers:message code="LBL_assignment.startDateOfFirstCourse" var="x" />
        <ui:row path="assignment.startDateOfFirstCourse">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[0]}" /></jsp:attribute>
             <jsp:attribute name="value">

                 <c:set var="x"><jsp:attribute name="value"><caaers:value path="assignment.startDateOfFirstCourse" /></jsp:attribute></c:set>

                 <div id="CRP_editField" style="display: ${(x eq '') ? 'block' : 'none'}">
                        <ui:date path="assignment.startDateOfFirstCourse" field="${fieldGroups.ReportingPeriod.fields[0]}" title="${x}"/>
                 </div>

                 <div id="CRP_readOnlyField" style="display: ${(x ne '') ? 'block' : 'none'}">
                     ${x} <a onclick="editFirstCourseDate()" style="cursor:pointer; color:blue;"><i><caaers:message code="LBL_ClickToEdit" /></i></a>
                 </div>
             </jsp:attribute>
        </ui:row>

        <caaers:message code="LBL_reportingPeriod.startDate" var="x" />
        <ui:row path="reportingPeriod.startDate">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[1]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:date path="reportingPeriod.startDate" field="${fieldGroups.ReportingPeriod.fields[1]}" title="${x}"/></jsp:attribute>
        </ui:row>

        <caaers:message code="LBL_reportingPeriod.endDate" var="x" />
        <ui:row path="reportingPeriod.endDate">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[2]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:date path="reportingPeriod.endDate" field="${fieldGroups.ReportingPeriod.fields[2]}" title="${x}"/></jsp:attribute>
        </ui:row>

        <caaers:message code="LBL_reportingPeriod.epoch" var="x" />
        <ui:row path="reportingPeriod.epoch">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[3]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:select path="${fieldGroups.ReportingPeriod.fields[3].propertyName}" options="${fieldGroups.ReportingPeriod.fields[3].attributes.options}" field="${fieldGroups.ReportingPeriod.fields[3]}" title="${x}"/></jsp:attribute>
        </ui:row>

        <caaers:message code="LBL_reportingPeriod.cycleNumber" var="x" />
        <ui:row path="reportingPeriod.cycleNumber">
             <jsp:attribute name="label"><tags:renderLabel field="${fieldGroups.ReportingPeriod.fields[4]}" /></jsp:attribute>
             <jsp:attribute name="value"><ui:text path="${fieldGroups.ReportingPeriod.fields[4].propertyName}" field="${fieldGroups.ReportingPeriod.fields[4]}" title="${x}"/></jsp:attribute>
        </ui:row>
<center>
    <caaers:renderFilter elementID="reportingPeriod.treatmentAssignment">
        <tags:table bgColor="#cccccc" contentID="_ta">
            <table width="100%" cellspacing="1" cellpadding="2" style="font-size:10pt;">
                <tr bgcolor="#E4E4E4">
                    <th><b><ui:label path="reportingPeriod.cycleNumber" text="" labelProperty="reportingPeriod.treatmentAssignment" required="${reportingPeriod_treatmentAssignment_required}"/></b></th>
                    <th><b><ui:label path="reportingPeriod.cycleNumber" text="" labelProperty="reportingPeriod.description" required="${reportingPeriod_treatmentAssignment_required}"/></b></th>
                </tr>
                <c:forEach items="${command.study.activeTreatmentAssignments}" var="ta">
                    <tr bgcolor="white">
                        <td><ui:radio path="reportingPeriod.treatmentAssignment" value="${ta.id}" onclick="clearOtherTAC();"/>&nbsp;${ta.code}
                        <td>${ta.htmlEscapedDescription}
                    </tr>
                </c:forEach>
                <tr bgcolor="white">
                    <td><ui:radio path="reportingPeriod.treatmentAssignment" value="" id="otherTA" />&nbsp;Other
                    <td><ui:textarea rows="2" path="reportingPeriod.treatmentAssignmentDescription" cols="100"></ui:textarea>
                </tr>
            </table>
        </tags:table>
    </caaers:renderFilter>
</center>
    </jsp:attribute>
	<jsp:attribute name="navButtons">
	      	<div class="content buttons autoclear">
    	      <div class="flow-buttons">
    	      	  <span class="prev">
				  	<tags:button color="red" type="button" onclick="javascript:deleteCourse()" cssClass="tab${tabNumber}" value="Delete" icon="delete"/>
				  </span>
				  <span class="next">
				  	<tags:button color="green" type="submit" id="flow-update" cssClass="tab${tabNumber}" value="Save" icon="save"/>
				  </span>
          </div>
      </div>
  </jsp:attribute>


</tags:standardForm>

</body>
</html>
</page:applyDecorator>    