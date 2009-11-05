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
<script type="text/javascript">
	var agentListEditor;
	var jsAgents = [ ];
	function fireAction(action, selected){
		if(action =='addStudyAgent'){
		   agentListEditor.add.bind(agentListEditor)();
		}else{
			//document.getElementById('command')._target.name='_noname';
			document.studyAgentsForm._action.value=action;
			document.studyAgentsForm._selected.value=selected;		
			// document.studyAgentsForm.submit();
		}
	}
	function fireRowDelete(index, indIndex, id, cssClass){
		//set the _selectedInd and call fireAction.
		document.studyAgentsForm._selectedInd.value=indIndex;
		fireAction('removeInd',index);
	}
	  
	var jsStudyAgent = Class.create();
    var INDs = 0;
	Object.extend(jsStudyAgent.prototype, {
            initialize: function(index, agentName) {
            	jsAgents[index] = this;
            	this.index = index;
            	this.agentName = agentName;
            	this.agentPropertyName = "study.studyAgents["  + index + "].agent";
            	this.otherProperty = "study.studyAgents["  + index + "].otherAgent";
				this.indType = $('study.studyAgents['  + index + '].indType');
                this.agent_radio = $("select-agent-" + this.index);
                
				if(this.agent_radio)this.agent_radio.observe("click", this.updateAgentOrOther.bind(this))
				
				this.other_radio = $("select-other-" + this.index)
				if(this.other_radio)this.other_radio.observe("click", this.updateAgentOrOther.bind(this))
            	
            	this.agentInputId = this.agentPropertyName + "-input";
            	this.agentInput = $(this.agentInputId);
				if(this.agentInput)
					this.otherPropertyInput = $(this.otherProperty);
            	
            	if(agentName) {
                	if (this.agentInput) 
                    	this.agentInput.value = agentName;
                	else
                    	$(this.agentPropertyName).innerHTML = agentName;  
            	}

            	if(this.agentInput){
                	
            		AE.createStandardAutocompleter(this.agentPropertyName, 
            			this.agentPopulator.bind(this),
            			this.agentSelector.bind(this)
            		);
            	}
				if(this.indType){

	            	//disable part of lead IND if necessary
	            	var indTypeValue = $('study.studyAgents['  + index + '].indType').value;
	            	if(indTypeValue == 'NA' || indTypeValue == 'NA_COMMERCIAL' || indTypeValue == 'IND_EXEMPT'){
						  $('study.studyAgents[' + this.index + '].partOfLeadIND').value='false';
						  $('study.studyAgents[' + this.index + '].partOfLeadIND').disable();
						  $('study.studyAgents[' + this.index + '].partOfLeadIND-row').hide();
					}

	             	
	            	//observe on the change event on IND Type (usage) dropdown.
					this.indType.observe("change", function(event){
						
						//disable the part of lead IND
						if(event.target.value == 'NA' || event.target.value == 'NA_COMMERCIAL' || event.target.value == 'IND_EXEMPT'){
						  $('study.studyAgents[' + index + '].partOfLeadIND').value='false';
						  $('study.studyAgents[' + index + '].partOfLeadIND').disable();
						  $('study.studyAgents[' + index + '].partOfLeadIND-row').hide();
						}else{
						  $('study.studyAgents[' + index + '].partOfLeadIND-row').show();
						  $('study.studyAgents[' + index + '].partOfLeadIND').enable();
						}
						
						//enable disable lead IND
						if(event.target.value == 'OTHER'){
						  	if (INDs > 0) return;
                            createStudy.addIND(index, 0, 2,function(html){
	     						new Insertion.After($('study.studyAgents[' + index + '].indType-row'), html);
	     						AE.slideAndShow('study.studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
	     						//setup auto completer
	    						jsAgents[index].initINDAutoCompleter(0);
                                INDs++;
					     	});

						}else if(event.target.value == 'CTEP_IND'){
							createStudy.addIND(index, 0, 1,function(html){
								var el = $('study.studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
								if(el){
									el.parentNode.removeChild(el);
								}	
							});
                            INDs = 0;
						}else if(event.target.value == 'DCP_IND'){
							createStudy.addIND(index, 0, 5,function(html){
								var el = $('study.studyAgents[' + index + '].studyAgentINDAssociations[0].investigationalNewDrug-row')
								if(el){
									el.parentNode.removeChild(el);
								}	
							});
                            INDs = 0;
						}else {
						  //0 deletion
						  fireRowDelete(index,0);
						}
						
		 			});
					
				}
           
             this.initializeAgentOrOther();	

             }, 

            updateAgentOrOther: function() {
                var isAgentSpecified = $("select-agent-" + this.index).checked
                var agentRow = $(this.agentPropertyName + "-row")
                var otherRow = $(this.otherProperty + "-row")
                if (isAgentSpecified) {
               		if(this.agentInput) this.agentInput.disabled=false
                    if(this.otherPropertyInput) this.otherPropertyInput.value=""
                    if(this.otherPropertyInput) this.otherPropertyInput.disabled=true
                    otherRow.getElementsByClassName("value")[0]=""
                } else { 
                	if(this.otherPropertyInput) this.otherPropertyInput.disabled=false               	
                	if(this.agentInput) this.agentInput.value=""
                    $(this.agentPropertyName).value=""
                    if(this.agentInput) this.agentInput.disabled=true
                    
                }
            },initializeAgentOrOther: function() {
                this.otherValue = '';
                if(this.otherPropertyInput){
                	if(this.otherPropertyInput.value){
                		 $("select-other-" + this.index).click()
                	}else{
                		 $("select-agent-" + this.index).click()
                	}
                }
            },
            initINDAutoCompleter:function(indIndex, indNumber ,indHolderName){
                  var indPropName = "study.studyAgents["+this.index+"].studyAgentINDAssociations["+indIndex+"].investigationalNewDrug";
                  var indPropInput = $(indPropName + "-input");
                  if(!indPropInput) return; 
                  if(indHolderName)	indPropInput.value = indNumber;
        		  AE.createStandardAutocompleter(indPropName, 
            		this.indPopulator.bind(this),
            		this.indSelector.bind(this)
            	  );
                   
            },
            agentPopulator: function(autocompleter, text) {
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
   
        <c:forEach varStatus="status" items="${command.study.studyAgents}" var="sa">
        	<c:if test="${not sa.retired}">
      		new jsStudyAgent(${status.index}, "${sa.agent.nscNumber}${sa.agent.name ne null ? '::':''}${sa.agent.name}" );
      		<c:forEach varStatus="indStatus" items="${sa.studyAgentINDAssociations}" var="sai">
      			jsAgents[${status.index}].initINDAutoCompleter(${indStatus.index},'${sai.investigationalNewDrug.indNumber}','${sai.investigationalNewDrug.holderName}');
      		</c:forEach>
      		</c:if>
      	</c:forEach>
        agentListEditor = new ListEditor('sa-section',createStudy, "StudyAgent",{
      		 addButton: "xxx",
             addIndicator: "sa-add-indicator",
             addFirstAfter: "agentbookmark",
             addCallback: function(nextIndex) {
                	//initilze auto completer and calendar
          			new jsStudyAgent(nextIndex);
          			$('_ITEM_COUNT').value = parseInt($('_ITEM_COUNT').value) + 1;
             },
             deletable: true,
             removeParameters:['Study Agent'],
             nextIndexCallback : function(){
                 return $('_ITEM_COUNT').value;
             }
      	},"study.studyAgents");
                 
      })
      
      //Function to add a row of IND numbers.
     function insertINDRow(index, prop){
     	var indIndex = $$("." + "ind"+index).length - 2;
     	var indRow = 'study.studyAgents[' + index + '].studyAgentINDAssociations[' + indIndex +'].investigationalNewDrug-row';
     	createStudy.addIND(index, indIndex,function(html){
     		new Insertion.After($$(".ind"+index ).last(), html);
     		AE.slideAndShow(indRow)
     		//setup auto completer
     		jsAgents[index].initINDAutoCompleter(indIndex);
     		
     	});
     }
     
</script>
 
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
			<input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.studyAgents)}">
		</div>	
		<p id="instructions"></p>
        <c:forEach var="sa" varStatus="status" items="${command.study.studyAgents}">
        	<c:if test="${not sa.retired}">
        		<c:set var="_agentName" value="${sa.agentName}" />
        		<c:if test="${not empty sa.agent}">
        			<c:set var="_agentName" 
        				value="${sa.agent.nscNumber}${sa.agent.name ne null ? '::':''}${sa.agent.name}" />
        		</c:if>
				<study:oneStudyAgent readOnly="${sa.agentName ne 'no-agent-name'}"
					title="${_agentName}"
					sectionClass="sa-section" index="${status.index}" 
					collapsed="${sa.agentName ne 'no-agent-name'}"/>
			</c:if>
		</c:forEach>

        <span id="agentbookmark"></span>

        <div align="left">
            <tags:indicator id="sa-add-indicator" />
            <tags:button color="blue" type="button" value="Add Study Agent" size="small" icon="add" onclick="javascript:fireAction('addStudyAgent','0');"/>
        </div>
        
    </jsp:attribute>
    
</tags:tabForm>
</body>
</html>