<%@ include file="/WEB-INF/views/taglibs.jsp" %>

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
	Event.observe(window, "load", function(){
		window.parent.rpCreator.refreshRPCrlOptionsAndShowDetails(${command.reportingPeriod.id}, false, '${command.reportingPeriod.name}');
		$('ok-id').observe("click", function(){
			window.parent.Windows.close(window.parent.rpCreator.win.getId());
		});
	});
</script>

</head>
<body>
<tags:standardForm title="Confirmation">
	<jsp:attribute name="flashMessage">
		<div><b>Course details saved successfully</b></div>
	</jsp:attribute>
	<jsp:attribute name="singleFields">
		<tags:renderRow field="${ReportingPeriod.fields[0]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[0].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[1]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[1].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[2]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[2].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[3]}">
   			  <jsp:attribute name="value">
				${command.reportingPeriod.epoch.name}
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[4]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[4].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
<%--
        <tags:renderRow field="${ReportingPeriod.fields[5]}">
   			  <jsp:attribute name="value">
				${command.reportingPeriod.treatmentAssignment.code}
			  </jsp:attribute>
		</tags:renderRow>
--%>

	</jsp:attribute>
	<jsp:attribute name="navButtons">
		<input id="ok-id" type="image" value="OK" src="<c:url value="/images/blue/ok.png" />"/>
    </jsp:attribute>
</tags:standardForm>

</body>
</html>
</page:applyDecorator>