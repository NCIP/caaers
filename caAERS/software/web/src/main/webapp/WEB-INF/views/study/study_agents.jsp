<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
.label {
	width: 12em;
}

td#linkPosition a img {
	position: absolute;
	right: 30px;
}
</style>
<!--[if lte IE 6]>
<style>
	#main {
		top:50px;
	}
</style>
<![endif]-->
<tags:dwrJavascriptLink objects="createStudy" />
<tags:js name="ui/ajaxCRUD" />
<script type="text/javascript">
ajaxCRUD = new AJAX_CRUD_HELPER();


function fireAction(action, index) {
    if (action == 'removeStudyAgent') {
        ajaxCRUD._deleteItem('StudyAgent', index, '_SA', ${tab.number});
    } else if (action == 'addStudyAgent') {
        ajaxCRUD._addItem('StudyAgent', null, null, '_SA', null, ${tab.number}, 'Bottom');
    } else if (action == 'addIND') {
        var containerID = '_SA-IND-' + index;
        var opts = new Hash();
        opts.set("index", index);
        ajaxCRUD._addItem('StudyAgentIND', null, null, containerID, opts, ${tab.number}, 'Bottom');
    } else if (action = 'removeIND') {
        var children = $('_SA-IND-' + index).childElements();
        $A(children).each(function(el) {
            el.remove();
        });
    }
}

function toggleAgentOrOther(index){
	var agentRadioSelected = $("select-agent-" + index).checked
	var idPrefix = 'study.studyAgents[' + index + '].';
	var agentField = $(idPrefix + 'agent');
	var agentField_Input = $(idPrefix + 'agent-input');
	var otherField = $(idPrefix + 'otherAgent');
	if(agentRadioSelected){
		if(agentField) agentField_Input.enable();
		if(otherField){ otherField.clear(); otherField.disable(); }
	}else{
		if(agentField) { 
			agentField.clear(); 
			agentField_Input.value = 'Begin typing here...';
			agentField_Input.disable();
		}
		if(otherField) otherField.enable();
		
	}
	
}
</script>
</head>
<body>

<study:summary />

<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="false">
    <jsp:attribute name="instructions">Click  "Add Study Agent" to add one or more agents to this study.</jsp:attribute>
	<jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			<input type="hidden" name="_selectedInd" value="">
			<input type="hidden" name="_selectedOther" value="">
			<input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.studyAgents)}">
		</div>	
		<p id="instructions"></p>

		<div id="_SA">
        <c:forEach var="sa" varStatus="status" items="${command.study.studyAgents}">
          <c:if test="${not sa.retired}">
        	<study:oneStudyAgent studyAgent="${sa}" index="${status.index}" />
		  </c:if>
		</c:forEach>
		</div>

        <div align="left">
            <tags:indicator id="_SA_indicator" />
            <tags:button color="blue" type="button" value="Add Study Agent" size="small" icon="add" onclick="javascript:fireAction('addStudyAgent','0');"/>
        </div>
        
    </jsp:attribute>
    
</tags:tabForm>
</body>
</html>