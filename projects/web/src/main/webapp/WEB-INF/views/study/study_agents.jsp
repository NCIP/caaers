<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

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
			
	function fireAction(action, selected){
		document.getElementById('command')._target.name='_noname';
		document.studyAgentsForm._action.value=action;
		document.studyAgentsForm._selected.value=selected;		
		document.studyAgentsForm.submit();
	}

	function clearField(field){
         field.value="";
    }
          
    function hover(index)
    {
      var formID = 'agent' + index + '-input'; 
      if ($(formID).value.length > 4)
      {
   		$(formID).title=$(formID).value;
   	  }
   	  else 
   	  {
    	$(formID).title="";   
   	   }
     }   
         
      /*
       * Used to check the INDIndicator checkbox if 
       * INDIdentifier contains text
       */
         
      function checkIndicator(index)
      {
    	var indIndicator ='studyAgents[' + index + '].investigationalNewDrugIndicator1'
    	var indIdentifier = 'studyAgents[' + index + '].investigationalNewDrugIdentifier'
    	if ( $(indIdentifier).value.length > 0 )
    		{
    			$(indIndicator).checked=true;
    		} 
      } 
 
      var agentAutocompleterProps = {
          basename: "agent",
          populator: function(autocompleter, text) {
              createStudy.matchAgents(text, function(values) {
                  autocompleter.setChoices(values)
              })
          },
          valueSelector: function(obj) {
              return obj.nscNumber + "<b> ::</b> " + obj.name
          }
      }
      
            
      function acPostSelect(mode, selectedChoice) {
          //Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
          $(mode.basename).value = selectedChoice.id;
          //$(mode.basename + '-selected').show()
          //new Effect.Highlight(mode.basename + "-selected")
      }

      function updateSelectedDisplay(mode) {
          if ($(mode.basename).value) {
              Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
              $(mode.basename + '-selected').show()
          }
      }

      function acCreate(mode) {
          new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
              mode.populator, {
              valueSelector: mode.valueSelector,
              afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                  acPostSelect(mode, selectedChoice)
              },
              indicator: mode.basename + "-indicator"
          })
          Event.observe(mode.basename + "-clear", "click", function() {
              //$(mode.basename + "-selected").hide()
              $(mode.basename).value = ""
              $(mode.basename + "-input").value = ""
          })
      }

      Event.observe(window, "load", function() {
       index = 0;
       autoCompleteId = 'agent' + index ;
       while( $(autoCompleteId)  )
       { 
     // change the basename property to agent0 ,agent1 ...
        agentAutocompleterProps.basename=autoCompleteId
           acCreate(agentAutocompleterProps)
           index++
           autoCompleteId= 'agent' + index  ; 
          }           
      })
     
   </script>
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm">
    <jsp:attribute name="singleFields">
		<div>		
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>	
		<p id="instructions">
			Add a Study Agent 	<a href="javascript:fireAction('addStudyAgent','0');">
			<img src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a>
		</p>
		
		<table class="tablecontent">
		<tr>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>Agent:</b> </th>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>IND&nbsp;Identifier:</b> </th>
			<th scope="col" align="left"><b> <span class="red">*</span><em></em>IND&nbsp;Indicator:</b> </th>
			<th scope="col" align="left" class="specalt"></th>
		</tr>

		<c:forEach  items="${command.studyAgents}" varStatus="status">		
			<tr>
				<td class="alt" id="linkPosition" rowspan="1">				
					<form:hidden id="agent${status.index}" path="studyAgents[${status.index}].agent"/>
                 	<form:input size="50" id="agent${status.index}-input" onmouseover="javascript:hover(${status.index})" 
                 		path="studyAgents[${status.index}].agentAsString"/>
                 	<tags:indicator id="agent${status.index}-indicator"/>
                 	<div id="agent${status.index}-choices" class="autocomplete"></div>
                 	<input type="button" id="agent${status.index}-clear" value="Clear"/>			
				</td>
				<td class="alt"><form:input  onchange="checkIndicator(${status.index})" path="studyAgents[${status.index}].investigationalNewDrugIdentifier" />
					<tags:hoverText description="Investigational New Drug Identifier"/></td>
				<td class="alt"><form:checkbox path="studyAgents[${status.index}].investigationalNewDrugIndicator"/>
					<tags:hoverText description="Investigational New Drug Indicator"/></td>
				<td class="alt">
					<a href="javascript:fireAction('rem>oveStudyAgent',${status.index});">
						<img src="<c:url value="/images/checkno.gif"/>" border="0" alt="remove"></a></td>
			</tr>
		</c:forEach>
	</table>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>