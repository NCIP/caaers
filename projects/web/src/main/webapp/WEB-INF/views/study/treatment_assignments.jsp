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
		addCallback: function(nextIndex) {
  			$('_ITEM_COUNT').value = parseInt($('_ITEM_COUNT').value) + 1;
     	},
		deletable: true,
        removeParameters:['Treatment Assignment'],
        nextIndexCallback : function(){
            return $('_ITEM_COUNT').value;
        }
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
		 <input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.treatmentAssignments)}">
		<c:forEach var="ta" varStatus="status" items="${command.study.treatmentAssignments}">
			<c:if test="${not ta.retired}">
		  		<study:treatmentAssignment
		  			title="${empty ta.code ? '...' : ta.code}"
		  			sectionClass="si-section" 
		  			index="${status.index}" 
		  			treatmentAssignment="${command.study.treatmentAssignments[status.index]}" 
		  			collapsed="true"
		  			readOnly="${not empty ta.code}"
		  			/>
		 	</c:if>
		</c:forEach>	
		    <span id="identifierbookmark"></span>
        <br>
        <tags:listEditorAddButton divisionClass="si-section" label="Add Treatment Assignment" />
        
    </jsp:attribute>
	
</tags:tabForm>
</body>
</html>