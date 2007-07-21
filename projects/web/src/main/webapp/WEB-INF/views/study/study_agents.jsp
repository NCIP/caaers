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
<style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
        td#linkPosition a img { position:absolute; right: 30px; }
        
</style>
<tags:includeScriptaculous/>
<tags:dwrJavascriptLink objects="createStudy"/>
<script type="text/javascript">
	
	var agentListEditor;
	
	function fireAction(action, selected){
		if(action == 'removeStudyAgent' ){
			document.getElementById('command')._target.name='_noname';
			document.studyAgentsForm._action.value=action;
			document.studyAgentsForm._selected.value=selected;		
			document.studyAgentsForm.submit();
		}else{
			agentListEditor.add.bind(agentListEditor)();
		}
	}

	  
	var jsStudyAgent = Class.create();
	Object.extend(jsStudyAgent.prototype, {
            initialize: function(index, agentName) {
            	this.index = index;
            	this.agentName = agentName;
            	this.agentPropertyName = "studyAgents["  + index + "].agent";
            	this.agentInputId = this.agentPropertyName + "-input";
            	if(agentName) $(this.agentInputId).value = agentName;
            	AE.createStandardAutocompleter(this.agentPropertyName, 
            		this.agentPopulator.bind(this),
            		this.agentSelector.bind(this)
            	);
            },            
            agentPopulator: function(autocompleter, text) {
         		createStudy.matchAgents(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	agentSelector: function(agent) { 
        		return agent.nscNumber + "<b> ::</b> " + agent.name 
        	}
        	
    });
	  
    Event.observe(window, "load", function() {
   
        <c:forEach varStatus="status" items="${command.studyAgents}" var="sa">
      		new jsStudyAgent(${status.index}, "${sa.agent.nscNumber}::${sa.agent.name}");
      	</c:forEach>
        agentListEditor = new ListEditor('sa-section',createStudy, "StudyAgent",{
      		 addButton: "xxx",
             addIndicator: "sa-add-indicator",
             addFirstAfter: "agentbookmark",
             addCallback: function(nextIndex) {
                	//initilze auto completer and calendar
          			new jsStudyAgent(nextIndex);
             }
      	});  
                 
      })
     
</script>

</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="true">
    <jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>	
		<p id="instructions">
			Add a Study Agent 	<a href="javascript:fireAction('addStudyAgent','0');">
			<img src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a><tags:indicator id="sa-add-indicator"/>
		</p>
		<c:forEach varStatus="status" items="${command.studyAgents}">	
  			<study:aStudyChild title="Study Agent ${status.index + 1}" enableDelete="true"
			sectionClass="sa-section" removeButtonAction="removeStudyAgent" index="${status.index}" />
		</c:forEach>
		<span id="agentbookmark" />
    </jsp:attribute>
</tags:tabForm>
</body>
</html>