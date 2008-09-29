<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="standard" tagdir="/WEB-INF/tags/standard"%>

<html>
<head>
 <standard:head/>
 <tags:stylesheetLink name="standard-form" />

 <style type="text/css">
  	#confirmbox {  width: 98%;}
  	body {background: none; text-align:left;}
  	div.row div.label {width: 12em;}
	div.row div.value, div.row div.extra { margin-left: 13em; }
	div.row div.value { font-weight:normal;	white-space: normal; }
</style>
<script>
	Event.observe(window, "load", function(){
		window.parent.rpCreator.refreshRPCrlOptionsAndShowDetails(${command.reportingPeriod.id}, ${command.editFlow});
		
		$('ok-id').observe("click", function(){
			window.parent.Windows.close(window.parent.rpCreator.win.getId());			
		});
	});
</script>

</head>
<body>
<tags:standardForm title="Confirmation">
	<jsp:attribute name="flashMessage">
			<div><b>Evaluation period details saved successfully</b></div>
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
				${command.reportingPeriod.epoch.name}
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[3]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[3].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[4]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[4].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[5]}">
   			  <jsp:attribute name="value">
				${command.reportingPeriod.treatmentAssignment.code}
			  </jsp:attribute>
		</tags:renderRow>
        <tags:renderRow field="${ReportingPeriod.fields[6]}">
   			  <jsp:attribute name="value">
				<ui:value propertyName="${ReportingPeriod.fields[6].propertyName}" />
			  </jsp:attribute>
		</tags:renderRow>

	</jsp:attribute>
	<jsp:attribute name="navButtons">
		<input id="ok-id" type="button" value="OK" />
	</jsp:attribute>
</tags:standardForm>

</body>
</html>
