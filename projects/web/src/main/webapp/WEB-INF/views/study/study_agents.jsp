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
<script type="text/javascript"><!--
	
	var agentListEditor;
	var jsAgents = [ ];
	function fireAction(action, selected){
		if(action =='addStudyAgent'){
		   agentListEditor.add.bind(agentListEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.studyAgentsForm._action.value=action;
			document.studyAgentsForm._selected.value=selected;		
			document.studyAgentsForm.submit();
		}
	}
	function fireRowDelete(index, indIndex, id, cssClass){
		//set the _selectedInd and call fireAction.
		document.studyAgentsForm._selectedInd.value=indIndex;
		fireAction('removeInd',index);
	}
	  
	var jsStudyAgent = Class.create();
	Object.extend(jsStudyAgent.prototype, {
            initialize: function(index, agentName) {
            	jsAgents[index] = this;
            	this.index = index;
            	this.agentName = agentName;
            	this.agentPropertyName = "studyAgents["  + index + "].agent";
            	this.agentInputId = this.agentPropertyName + "-input";
            	//this.indName = indName;
            	//this.indPropertyName = "studyAgents["  + index + "].investigationalNewDrugs";
            	//this.indInputId = this.indPropertyName + "-input";
            	if(agentName) $(this.agentInputId).value = agentName;
            	//if(indName) $(this.indInputId).value = indName;
            	
            	AE.createStandardAutocompleter(this.agentPropertyName, 
            		this.agentPopulator.bind(this),
            		this.agentSelector.bind(this)
            	);
            	
            },initINDAutoCompleter:function(indIndex, indNumber ,indHolderName){
                  var indPropName = "studyAgents["+this.index+"].studyAgentINDAssociations["+indIndex+"].investigationalNewDrug";
                  if(indHolderName) $(indPropName + "-input").value = indNumber; 
        		  AE.createStandardAutocompleter(indPropName, 
            		this.indPopulator.bind(this),
            		this.indSelector.bind(this)
            	  );
                   
            },agentPopulator: function(autocompleter, text) {
         		createStudy.matchAgents(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	agentSelector: function(agent) { 
        		return agent.nscNumber + "<b> ::</b> " + agent.name 
        	},
        	indPopulator: function(autocompleter, text) {
         		createStudy.matchINDs(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	indSelector: function(ind) { 
        		return ind.strINDNo; 
        	}
    });
	  
    Event.observe(window, "load", function() {
   
        <c:forEach varStatus="status" items="${command.studyAgents}" var="sa">
      		new jsStudyAgent(${status.index}, "${sa.agent.nscNumber}::${sa.agent.name}" );
      		<c:forEach varStatus="indStatus" items="${sa.studyAgentINDAssociations}" var="sai">
      			jsAgents[${status.index}].initINDAutoCompleter(${indStatus.index},'${sai.investigationalNewDrug.indNumber}','${sai.investigationalNewDrug.holderName}');
      		</c:forEach>
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
      
      //Function to add a row of IND numbers.
     function insertINDRow(index, prop){
     	var indIndex = $$("." + "ind"+index).length - 1;
     	var indRow = 'studyAgents[' + index + '].studyAgentINDAssociations[' + indIndex +'].investigationalNewDrug-row';
     	createStudy.addIND(index, indIndex,function(html){
     		new Insertion.After($$(".ind"+index ).last(), html);
     		AE.slideAndShow(indRow)
     		//setup auto completer
     		jsAgents[index].initINDAutoCompleter(indIndex);
     	});
     }
     
--></script>

</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="true">
    <jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			<input type="hidden" name="_selectedInd" value="">
		</div>	
		<p id="instructions">
			Add a Study Agent 	<a href="javascript:fireAction('addStudyAgent','0');">
			<img src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a><tags:indicator id="sa-add-indicator"/>
		</p>
		<c:forEach varStatus="status" items="${command.studyAgents}">
  	      <study:aStudyChild title="Study Identifier ${status.index + 1}" sectionClass="sa-section"
			 removeButtonAction="removeStudyAgent" enableDelete="true" index="${status.index}" >
			 <jsp:attribute name="localButtons">
			 	<input type="button" value="Add IND #" onClick="insertINDRow(${status.index})" />
			 </jsp:attribute>
          </study:aStudyChild>
		</c:forEach>
		<span id="agentbookmark" />
    </jsp:attribute>
</tags:tabForm>
</body>
</html>