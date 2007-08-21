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
        .label { width: 12em;  }
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
            	if(agentName) $(this.agentInputId).value = agentName;
            	
            	AE.createStandardAutocompleter(this.agentPropertyName, 
            		this.agentPopulator.bind(this),
            		this.agentSelector.bind(this)
            	);
            	//observe on the change event on IND Type (usage) dropdown.
				Event.observe('studyAgents['  + index + '].indType',"change", function(event){
					
					if(event.target.value == 2){
					  	createStudy.addIND(index, 0, 2,function(html){
     						new Insertion.After($$(".ind"+index ).last(), html);
     						AE.slideAndShow('studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
     						//setup auto completer
    						jsAgents[index].initINDAutoCompleter(0);
				     	});
					}else if(event.target.value == 1){
						createStudy.addIND(index, 0, 1,function(html){
						var el = $('studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
						if(el){
							el.parentNode.removeChild(el);
							}	
						});
					}else {
					  //0 deletion
					  fireRowDelete(index,0);
					}
					
	 			});
            	
            },initINDAutoCompleter:function(indIndex, indNumber ,indHolderName){
                  var indPropName = "studyAgents["+this.index+"].studyAgentINDAssociations["+indIndex+"].investigationalNewDrug";
                  var indPropInput = $(indPropName + "-input");
                  if(!indPropInput) return; 
                  if(indHolderName)	indPropInput.value = indNumber;
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
        		return ind.strINDNo + '::' + ind.holderName; 
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
     	var indIndex = $$("." + "ind"+index).length - 2;
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
<study:summary />
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="true">
	<jsp:attribute name="instructions">
	  Click on the Add Study Agent button below, in order to add an agent to this study.
	</jsp:attribute>
    <jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			<input type="hidden" name="_selectedInd" value="">
		</div>	
		<p id="instructions">
			
		</p>
		<c:forEach var="sa" varStatus="status" items="${command.studyAgents}">
  	      <study:aStudyChild title="Study Agent ${status.index + 1}" sectionClass="sa-section"
			 removeButtonAction="removeStudyAgent" enableDelete="true" index="${status.index}" >
          </study:aStudyChild>
		</c:forEach>
		<span id="agentbookmark" />
    </jsp:attribute>
    <jsp:attribute name="localButtons">
    <input type="button" onClick="javascript:fireAction('addStudyAgent','0');" 
     name="AddStudyAgent" value="Add Study Agent"><tags:indicator id="sa-add-indicator"/>
    </jsp:attribute>
 </tags:tabForm>
</body>
</html>