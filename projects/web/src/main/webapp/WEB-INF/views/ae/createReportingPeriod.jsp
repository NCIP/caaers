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

 	var descArray = new Array();
 	<c:forEach items="${command.study.treatmentAssignments}" var="ta">
 	    descArray.push("${ta.escapedDescription}");
 	</c:forEach>

 	Event.observe(window, "load", function(){
 	 	//make the TAC description non editable
 		$('reportingPeriod.treatmentAssignment.description').setAttribute('readOnly',true);

 		//catch the events on treatment assignment dropdown.
		$('reportingPeriod.treatmentAssignment').observe("change", function(event){
			selIndex = $('reportingPeriod.treatmentAssignment').selectedIndex;
			$('reportingPeriod.treatmentAssignment.description').clear();
			if(selIndex > 0){
				$('reportingPeriod.treatmentAssignment.description').value = descArray[selIndex-1];
			}
		});
 	})
 </script>
</head>
<body>

<tags:standardForm title="Evaluation Period Details">
    <jsp:attribute name="instructions" />
    <jsp:attribute name="singleFields">
        <c:forEach items="${fieldGroups.ReportingPeriod.fields}" var="field">
           <tags:renderRow field="${field}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="navButtons"><input type="submit" value="Save" /></jsp:attribute>
</tags:standardForm>

</body>
</html>
</page:applyDecorator>    