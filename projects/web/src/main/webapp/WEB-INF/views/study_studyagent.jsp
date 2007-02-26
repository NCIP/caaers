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
</style>
<tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createStudy"/>
    <script type="text/javascript">
    
    	function validatePage(){
			return true;
			}
			
	function fireAction(action, selected){
	if(validatePage()){
		document.getElementsByName('_target4')[0].name='_target3';
		document.studyAgentsForm._action.value=action;
		document.studyAgentsForm._selected.value=selected;		
		document.studyAgentsForm.submit();
	}
	}

		function clearField(field){
           field.value="";
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
        
        
		function getLayer(SRC){
			var tDIV = document.getElementById(SRC)             
            tDIV.style.cursor = "hand"
			tDIV.style.visibility = "visible"
		}

		function floatLayer(SRC){
			var tDIV = document.getElementById(SRC)
            //tDIV.style.top = window.event.clientY + 20
			
		}
			
		function hideLayer(SRC){
			var tDIV = document.getElementById(SRC)
            tDIV.style.visibility='hidden'
		}

          
   
        var agentAutocompleterProps = {
            basename: "agent",
            populator: function(autocompleter, text) {
                createStudy.matchAgents(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.nscNumber + " :: " + obj.name
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
            Element.update("flow-next", "Continue &raquo;")
        })
       
    </script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">	
				<form:form method="post" name="studyAgentsForm" cssClass="standard">
				<chrome:division id="study-details">
				<tags:tabFields tab="${tab}"/>
				
				<div>		
					<input type="hidden" name="_action" value="">
					<input type="hidden" name="_selected" value="">
				</div>
				
				<div id="Tip1" 
					style="position: absolute; 
									 width: 200px; 
									 left:285px; 
									 top:188px; 
									 visibility: hidden; 
									 z-index:1; 
									 padding: 4px; 
									 background-color: #FFFFCC; 
									 BORDER: 1px solid #000000">Investigational New Drug Identifier</div>
									 
				<div id="Tip2" 
					style="position: absolute; 
									 width: 200px; 
									 left:585px; 
									 top:188px; 
									 visibility: hidden; 
									 z-index:1; 
									 padding: 4px; 
									 background-color: #FFFFCC; 
									 BORDER: 1px solid #000000">Investigational New Drug Indicator</div>					 


				
				
				<table  width="70%" border="0" cellspacing="0" cellpadding="3">
				<br>

					<tr align="center">
						<td width="20%">										
							<b><a href="javascript:fireAction('addStudyAgent','0');"><img
								src="/caaers/images/checkyes.gif" border="0" alt="Add"></a></b> 
						</td>
						<td> <b>Agent<span class="red">*</span> </b></td>
						<!-- <td> <b>IND Identifier<span class="red">*</span> </b></td> -->
						<td><a href="javascript:void(0)"  style="color:black;" onmouseover="getLayer('Tip1')" onmousemove="floatLayer('Tip1')" onmouseout="hideLayer('Tip1')"> <b>IND Identifier<span class="red">*</span> </b></a></td>													
						<td><b> Start Date </b></td>
						<td><b> End Date </b></td>
						<!-- <td> <b>IND Indicator </b></td> -->
						<td><a href="javascript:void(0)" style="color:black;" onmouseover="getLayer('Tip2')" onmousemove="floatLayer('Tip2')" onmouseout="hideLayer('Tip2')"> <b>IND Indicator</b></a></td>
					</tr>																			
				 
				    <tr>
					<td> &nbsp;</td>
					</tr>
					
					
					<c:forEach  items="${command.studyAgents}" varStatus="status"> 
				    
					<tr align="center" class="results">
					
					<td>
						<a href="javascript:fireAction('removeStudyAgent',${status.index});">
							<img src="/caaers/images/checkno.gif" border="0" alt="remove">
						</a>										
					</td>
				
					<td>
						<form:hidden id="agent${status.index}" path="studyAgents[${status.index}].agent"/>
                    	<form:input size="25" id="agent${status.index}-input" path="studyAgents[${status.index}].agentAsString"/>
                    	<tags:indicator id="agent${status.index}-indicator"/>
                    	<div id="agent${status.index}-choices" class="autocomplete"></div>
                    	<input type="button" id="agent${status.index}-clear" value="Clear"/>
                    	<%--
                    	<p id="agent${status.index}-selected" style="display: none">
                        	You've selected the agent <span id="agent${status.index}-selected-name"></span>.
                    	</p>
                    	--%>
					</td>
		
					<td valign="top"><form:input  onchange="checkIndicator(${status.index})" path="studyAgents[${status.index}].investigationalNewDrugIdentifier" /></td>
				    <td><tags:dateInput path="studyAgents[${status.index}].participation.startDate"/></td>
				    <td><tags:dateInput path="studyAgents[${status.index}].participation.endDate"/></td>
					<td valign="top"><form:checkbox path="studyAgents[${status.index}].investigationalNewDrugIndicator"/></td>
					
					</tr>
						
					
					</c:forEach>
																	
					</table>
				</chrome:division>
				</form:form>		
<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
