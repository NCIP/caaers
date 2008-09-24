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
<tags:includeScriptaculous />
<tags:dwrJavascriptLink objects="createStudy" />
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
            	
            	this.otherProperty = "studyAgents["  + index + "].otherAgent";
            	
            	
                if (agentName) $(this.agentPropertyName + "-input").value = agentName

                $("select-agent-" + this.index)
                    .observe("click", this.updateAgentOrOther.bind(this))
                $("select-other-" + this.index)
                    .observe("click", this.updateAgentOrOther.bind(this))
            	
            	this.agentInputId = this.agentPropertyName + "-input";
            	if(agentName) $(this.agentInputId).value = agentName;
            	
            	AE.createStandardAutocompleter(this.agentPropertyName, 
            		this.agentPopulator.bind(this),
            		this.agentSelector.bind(this)
            	);
            	
            	
            	//disable part of lead IND if necessary
            	var indTypeValue = $('studyAgents['  + index + '].indType').value;
            	if(indTypeValue == 'NA' || indTypeValue == 'NA_COMMERCIAL' || indTypeValue == 'IND_EXEMPT'){
					  $('studyAgents[' + this.index + '].partOfLeadIND').value='false';
					  $('studyAgents[' + this.index + '].partOfLeadIND').disable();
					  $('studyAgents[' + this.index + '].partOfLeadIND-row').hide();
				}
            	
            	//observe on the change event on IND Type (usage) dropdown.
				Event.observe('studyAgents['  + index + '].indType',"change", function(event){
					
					//disable the part of lead IND
					if(event.target.value == 'NA' || event.target.value == 'NA_COMMERCIAL' || event.target.value == 'IND_EXEMPT'){
					  $('studyAgents[' + index + '].partOfLeadIND').value='false';
					  $('studyAgents[' + index + '].partOfLeadIND').disable();
					  $('studyAgents[' + index + '].partOfLeadIND-row').hide();
					}else{
					  $('studyAgents[' + index + '].partOfLeadIND-row').show();
					  $('studyAgents[' + index + '].partOfLeadIND').enable();
					}
					
					//enable disable lead IND
					if(event.target.value == 'OTHER'){
					  	createStudy.addIND(index, 0, 2,function(html){
     						new Insertion.After($('studyAgents[' + index + '].indType-row'), html);
     						AE.slideAndShow('studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
     						//setup auto completer
    						jsAgents[index].initINDAutoCompleter(0);
				     	});
					}else if(event.target.value == 'CTEP_IND'){
						createStudy.addIND(index, 0, 1,function(html){
							var el = $('studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
							if(el){
								el.parentNode.removeChild(el);
							}	
						});
					}else if(event.target.value == 'DCP_IND'){
						createStudy.addIND(index, 0, 5,function(html){
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
             this.initializeAgentOrOther();	
            }, updateAgentOrOther: function() {
                var isPriorTherapy = $("select-agent-" + this.index).checked
                var agentRow = $(this.agentPropertyName + "-row")
                var otherRow = $(this.otherProperty + "-row")
                if (isPriorTherapy) {
               		$(this.agentInputId).disabled=false
                    $(this.otherProperty).value=""
                    $(this.otherProperty).disabled=true
                    otherRow.getElementsByClassName("value")[0]=""
                } else { 
                	$(this.otherProperty).disabled=false               	
                    $(this.agentInputId).value=""
                    $(this.agentPropertyName).value=""
                    $(this.agentInputId).disabled=true
                    
                }
            },initializeAgentOrOther: function() {
                var otherValue = $(this.otherProperty).value
                if (otherValue.length == 0) {
                    $("select-agent-" + this.index).click()
                } else {
                    $("select-other-" + this.index).click()
                }
            },
            initINDAutoCompleter:function(indIndex, indNumber ,indHolderName){
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
      		new jsStudyAgent(${status.index}, "${sa.agent.nscNumber}${sa.agent.name ne null ? '::':''}${sa.agent.name}" );
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
             },
             deletable: true,
             removeParameters:['Study Agent']
      	},"studyAgents");  
                 
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

<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="false">

    <jsp:attribute name="instructions">
    Click  "Add Study Agent" to add one or more agents to this study.

	</jsp:attribute>
	<jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			<input type="hidden" name="_selectedInd" value="">
			<input type="hidden" name="_selectedOther" value="">
		</div>	
		<p id="instructions">
			
		</p>
		<c:forEach var="sa" varStatus="status" items="${command.studyAgents}"> 
		<study:oneStudyAgent title="Study Agent ${status.index + 1}"
				sectionClass="sa-section" index="${status.index}">
		  </study:oneStudyAgent>		
		</c:forEach>
		
		<span id="agentbookmark" />
    
	
	</jsp:attribute>
	<jsp:attribute name="localButtons" >
        <div align=right>
            <tags:indicator id="sa-add-indicator" />
            <input type="button" onClick="javascript:fireAction('addStudyAgent','0');" name="AddStudyAgent" value="Add Study Agent">
        </div>
    </jsp:attribute>
    
</tags:tabForm>
</body>
</html>