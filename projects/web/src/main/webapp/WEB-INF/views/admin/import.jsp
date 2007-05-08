<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
<tags:includeScriptaculous/>
	<script language="JavaScript" type="text/JavaScript">
	
	function toggle(in_id,out_id)
	{
		if ($(out_id).style.display != "none" ){
			Effect.Shrink(out_id);
		}
		Effect.toggle(in_id, 'appear');
		$('type').value = in_id;
	}
	
	function showIf()
	{
		if ( $('type').value == "study" ){
			$('study').style.display = ""
				
		}
		if (  $('type').value == "participant"){
			$('participant').style.display = ""
		}		
	}
	
	
	Event.observe(window, "load", function() {
		
		showIf()
		Event.observe("study_button","click",function() { toggle('study','participant') })
		Event.observe("participant_button","click",function() { toggle('participant','study') })
	 	
		       
    	}           
    )

</script>

</head>
<body>
<p id="instructions">
        Import Studies/Protocols or Participants into caAERS
</p>
    <tags:tabForm tab="${tab}" flow="${flow}" formName="ImportForm" enctype="multipart/form-data">
        <jsp:attribute name="singleFields">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
	</div>
	
		<input type="button" id="study_button" value="Import Study / Protocol"><br><br>
		<input type="button" id="participant_button" value="Import Participant         ">
		
		 <tags:errors path="*"/>
    	 
		<div style="display: none;position:relative;top:-30px; left:200px;" id = "study"> 
    	<input id="study_file" type="file" name="studyFile" />
		<input id="type" type="hidden" name="type" />
		</div>
		
		<div style="display: none;position:relative;top:-30px; left:200px;" id = "participant"> 
    	<input id="participant_file" type="file" name="participantFile" />
		<input id="type" type="hidden" name="type" />
		</div>
    	
		
		<%--		
		<div style="display: none;position:relative;top:-30px; left:200px;" id = "study">
    	 Please select a Study xml file to import<br><br>
    	<input id="studyFileName" type="hidden" value="${command.studyFileName}" name="studyFileName" />
        <input id="studyFileNamefake" type="file" name="temp" /> 
    	<div style="display: none;" id="study-selected">You have selected the following file ${command.studyFileName}</div>
    	</div>
    	
    	<div style="display: none;position:relative;top:-30px; left:200px;" id = "participant">
    	 Please select a Participant xml file to import<br><br>
    	<input id="participantFileName" type="hidden" value="${command.participantFileName}" name="participantFileName" />
    	<input id="participantFileNamefake" type="file" name="temp" /> 
    	<div style="display: none;" id="participant-selected">You have selected the following file ${command.participantFileName}</div>
    	</div>
    	--%>

 </jsp:attribute>
    </tags:tabForm>    
</body>
</html>