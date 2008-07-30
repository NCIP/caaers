<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>
<html>
 <head>
 	<standard:head/>
	<tags:stylesheetLink name="standard-form" />
	<style type="text/css">
		/*override the basic styles */
		body {background: none;}
		div.row div.label {width: 12em;}
	 	div.row div.value, div.row div.extra { margin-left: 13em; }
	
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
	<tags:standardForm title="Reporting Period Details">
		<jsp:attribute name="instructions">
			<p>You can add the details of the Reporting Period here.</p>
		</jsp:attribute>
		<jsp:attribute name="singleFields">
			<c:forEach items="${fieldGroups.ReportingPeriod.fields}" var="field">
               <tags:renderRow field="${field}"/>
            </c:forEach>
		</jsp:attribute>
		<jsp:attribute name="navButtons"><input type="submit" value="Save" /></jsp:attribute>
	</tags:standardForm>

 </body>
</html>