<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
    
  <tags:dwrJavascriptLink objects="createStudy"/>
<script language="JavaScript" type="text/JavaScript">
	var addTreatmentAssignmentEditor;
	
	function fireAction(action, selected){
		if(action == 'addTreatmentAssignment'){
			addTreatmentAssignmentEditor.add.bind(addTreatmentAssignmentEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.studyTreatmentAssignmentsForm._action.value=action;
			document.studyTreatmentAssignmentsForm._selected.value=selected;		
			document.studyTreatmentAssignmentsForm.submit();
		}
	}
	  
    Event.observe(window, "load", function() {
  	  //This is added for Add Sysetem TreatmentAssignments button
	  new ListEditor("si-section", createStudy, "TreatmentAssignment", {
		addFirstAfter: "identifierbookmark",
		deletable: true,
        removeParameters:['Treatment Assignment']
	   },'study.treatmentAssignments');
		               	
    });
	
</script>

</head>
<body>
<study:summary />
<tags:tabForm tab="${tab}" formName="studyTreatmentAssignmentsForm" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="repeatingFields">
    <p><tags:instructions code="study.study_treatments.top" /></p>
		 <input type="hidden" name="_action" value="">
		 <input type="hidden" name="_selected" value="">
		<c:forEach varStatus="status" items="${command.study.treatmentAssignments}">
		  <study:treatmentAssignment title="Treatment Assignment ${status.index + 1}" sectionClass="si-section" index="${status.index}" identifier="${command.study.treatmentAssignments[status.index]}" />
		</c:forEach>	
		    <span id="identifierbookmark"></span>
    </jsp:attribute>
	<jsp:attribute name="localButtons"> 
	   <chrome:division title="">          	
	   	<tags:listEditorAddButton divisionClass="si-section" label="Add Treatment Assignment" />   
       </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>
	
</tags:tabForm>
</body>
</html>