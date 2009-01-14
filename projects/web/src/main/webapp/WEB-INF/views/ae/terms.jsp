<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
     <style type="text/css">
   
	.cats {
		font-size:9pt;
		font-family: Helvetica,Arial,sans-serif;
	}
	
	.selects {
		top-padding:2px;
		width:90%;
		font-size:10pt;
		font-family: Helvetica,Arial,sans-serif
	}
	.sae {
		color:green;
	}
	
	</style>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <!-- <tags:labs labs="${command.assignment.labLoads}"/> -->
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        var initialCategory ='';
        var ctcTermIdvalues = new Array();
        <c:forEach items="${command.ctcTermIds}" varStatus="status" var="ctcTermId">
	        	ctcTermIdvalues[${status.index}]='${ctcTermId}'
         </c:forEach>

		
		  var EnterMeddra = Class.create()
        Object.extend(EnterMeddra.prototype, {
            initialize: function(index, llt) {
                this.index = index
                var cmProperty = "aeRoutineReport.adverseEvents[" + index + "]";
                this.meddraProperty = cmProperty + ".lowLevelTerm"
                this.lowLevelTerm = llt;
                this.selectMeddra = $("select-meddra-" + this.index)
                this.selectOther = $("select-other-" + this.index)
                this.adverseEvent = cmProperty

                if (this.lowLevelTerm) $(this.meddraProperty + "-input").value = this.lowLevelTerm
                
                this.initializeMeddraOrOther(this.lowLevelTerm, cmProperty, this.index)
                
                Event.observe(this.selectMeddra, "click", this.updateMeddraOrOther.bindAsEventListener(this))
                Event.observe(this.selectOther, "click", this.updateMeddraOrOther.bindAsEventListener(this))
               
               	 AE.createStandardAutocompleter(this.meddraProperty,
					function(autocompleter, text) {
						createAE.matchLowLevelTermsByCode(text, function(values) {
													autocompleter.setChoices(values)

						})
				},
				function(lowLevelTerm) { return lowLevelTerm.fullName });
               
            },   
            
             updateMeddraOrOther: function() {
            	var isMeddra     = this.selectMeddra.checked
                var meddraRowInp = $(this.adverseEvent + ".lowLevelTerm-input")
                var meddraRow    = $(this.adverseEvent + ".lowLevelTerm")
                var other        = $(this.adverseEvent + ".detailsForOther")
                
                if (isMeddra) {
                	
                    other.setAttribute('readOnly',true);
                    other.value=""
                    meddraRowInp.removeAttribute('readOnly');
                } else {
                	meddraRowInp.setAttribute('readOnly',true);
                	meddraRow.value=""
                	meddraRowInp.value=""
                	other.removeAttribute('readOnly');
                }
            	
            },
                               
            initializeMeddraOrOther: function(lowLevelTerm, cmProperty,  index) {
                var meddraRow    = $(cmProperty + ".lowLevelTerm")
                var meddraRowInp = $(cmProperty + ".lowLevelTerm-input")
                var other        = $(cmProperty + ".detailsForOther")
                var selectMeddra =$("select-meddra-" + index)
                var selectOther =$("select-other-" + index)
                
                if (this.lowLevelTerm == null ){
                	other.setAttribute('readOnly',true);
                    other.value=""
                    meddraRowInp.removeAttribute('readOnly');
                    selectMeddra.click()
                
                }else{
            	if (this.lowLevelTerm.length > 0 ){
                    other.setAttribute('readOnly',true);
                    other.value=""
                    meddraRowInp.removeAttribute('readOnly');
                    selectMeddra.click()
                }else{
                	meddraRowInp.setAttribute('readOnly',true);
                	meddraRow.value=""
                	meddraRowInp.value=""
                	other.removeAttribute('readOnly');
                	selectOther.click()
                }
                }
            }
        })


		
        Element.observe(window, "load", function() {
	        
	        <c:forEach items="${command.aeRoutineReport.adverseEvents}" varStatus="status" var="adverseEvent">
	        	<c:if test="${adverseEvent.ctcTerm.otherRequired == 'true' && adverseEvent.report == null }" >
           		new EnterMeddra(${status.index}, '${adverseEvent.lowLevelTerm.fullName}')
           		</c:if>
            </c:forEach>
	        
	        
	        showTerms()
	        Event.observe("cats", "change", function() { showTerms() })
	        
	        //$('termIds').style.display='none';
	       
        })
        
        function addAE(term, theTermId){
	     //alert(term);
	     var terms = $('termIds')
	     var opt1 = new Option(term, theTermId)
	     terms.options.add(opt1)
	     terms.options[0].selected=true;
	     fireAction('addTerm','0');
        }
        
        function contains(object, arrayObject){
        	returnValue = false;
        	arrayObject.each(function(singleObject){
        		if(object == singleObject){
        			returnValue = true
        			return returnValue;
        		}
        	})
        	return returnValue;
        }
        
        function showTerms() {
	        var cats       = $('cats');
	        var terms      = $('ctcTerms');
	        var categoryId ="";
			for ( i=0; i < cats.options.length; i++ ){
				if (cats.options[i].selected == true) {  categoryId = cats.options[i].value; break; }
			}
			if (categoryId != "")
			{       
	        createAE.getTermsByCategory(categoryId, function(ctcTerms) {
                   
                   terms.size= 5;
                   terms.options.length = 0
                   //terms.options[0].selected=true;
                   ctcTerms.each(function(ctcTerm) {
                       var opt = new Option(ctcTerm.fullName, ctcTerm.id)
                       terms.options.add(opt)
                       opt.selected = (contains(ctcTerm.id,ctcTermIdvalues) == true) ? true : false ; 
                   })
               })
           }
        }
        
        function fireAction(action, selected){
			document.getElementById('command')._target.name='_noname';
			document.termsForm._action.value=action;
			document.termsForm._selected.value=selected;		
			document.termsForm.submit();
	}
        
    </script>
</head>
<body>

<tags:tabForm tab="${tab}" flow="${flow}" formName="termsForm">
 			
    <jsp:attribute name="instructions">
        <tags:instructions code="instructions_routine_ae_routineAe" heading="Instructions: " /> 
        <div>
           <input type="hidden" name="_action" value="">
           <input type="hidden" name="_selected" value="">
        </div>
    </jsp:attribute>
   
    <jsp:attribute name="singleFields" >
    	<strong>CTC Categories</strong><br>
    	<form:select id="cats" path="cats" size="5" cssClass="cats">
    	<form:options items="${command.categories}" itemValue="id" itemLabel="name"/>
    	</form:select>
    	<br><br>
    	<strong>CTC Terms</strong><br>
    	<form:select id="ctcTerms" path="ctcTermIds" size="5" cssClass="selects">
    	</form:select>
    	<input style="float:right;" type="button" value="Add" onClick="javascript:fireAction('addTerm','0');" /><br>
    	<br><br>
    	
       <hr> 
    </jsp:attribute>
    
    <jsp:attribute name="repeatingFields">
    		<center>
    		<c:if test="${fn:length(command.aeRoutineReport.adverseEvents) > 0}" >
    		<table width="100%" class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Term:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Grade:</b> </th>
    				<th scope="col" align="left"><b> <tags:requiredIndicator/>Attribution:</b> </th>
    				<th scope="col" align="left"><b>Hospitalization or prolongation of existing hospitalization?</b> </th>
    				<th scope="col" align="left"><b>Expected:</b> </th>
    			</tr>
    				
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" var="ae" varStatus="status">
            	<tr>
            		<td>
            			<c:choose>
						 	<c:when test="${ae.report != null}">
   								<span class="sae"><c:out value="${ae.adverseEventTerm.universalTerm}" /></span>
							</c:when>
						    <c:otherwise>
						    	<c:out value="${ae.ctcTerm.term}" />
							</c:otherwise>
						</c:choose>
            			
            			<c:if test="${ae.ctcTerm.otherRequired == 'true' && ae.report == null }" >
            				<center>
            				<div class="row">
            					<div class="label"><input id="select-meddra-${status.index}" name="meddraOrVerbatim${status.index}" type="radio"/>Other (MedDRA)</div>
            					<div class="value">
            						<form:hidden  path="aeRoutineReport.adverseEvents[${status.index}].lowLevelTerm" />
            						<input type="text" id="aeRoutineReport.adverseEvents[${status.index}].lowLevelTerm-input" class="autocomplete"/>
                    				<tags:indicator id="aeRoutineReport.adverseEvents[${status.index}].lowLevelTerm-indicator"/>
                    				<div id="aeRoutineReport.adverseEvents[${status.index}].lowLevelTerm-choices" class="autocomplete"></div>
            					</div>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].lowLevelTerm"/>
            				</div>	
            				<div class="row">
            					<div class="label"><input id="select-other-${status.index}" name="meddraOrVerbatim${status.index}" type="radio"/>Other (verbatim)</div>
            					<div class="value">            						
            						<form:input path="aeRoutineReport.adverseEvents[${status.index}].detailsForOther" />
            					</div>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].detailsForOther"/>
            				</div>	
            				</center>
            			</c:if>
            		</td>
            		<td>
            			<c:choose>
						 	<c:when test="${ae.report != null}">
   								<span class="sae"><c:out value="${ae.grade}" /></span>
							</c:when>
						    <c:otherwise>
						    	<form:select path="aeRoutineReport.adverseEvents[${status.index}].grade">
            					<form:option value=" " label="Please select" />
            					<c:if test="${fn:length(ae.ctcTerm.contextualGrades) == 0}" >
            						<form:options items="${grade}" itemValue="name" itemLabel="code"/>
            					</c:if>
            					<form:options items="${ae.ctcTerm.contextualGrades}" itemValue="grade.name" itemLabel="grade.code"/>
            					</form:select>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].grade"/>
							</c:otherwise>
						</c:choose>
            		</td>
            		
            		<td>
            			<c:choose>
						 	<c:when test="${ae.report != null}">
   								<span class="sae"><c:out value="${ae.attributionSummary}" /></span>
							</c:when>
						    <c:otherwise>
						    	<form:select path="aeRoutineReport.adverseEvents[${status.index}].attributionSummary">
            					<form:option value=" " label="Please select" />
            					<form:options items="${attribution}" itemValue="name" itemLabel="displayName"/>
            					</form:select>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].attributionSummary"/>
							</c:otherwise>
						</c:choose>
            		</td>
            		
            		<td>
            			<c:choose>
						 	<c:when test="${ae.report != null}">
   								<span class="sae"><c:out value="${ae.hospitalization}" /></span>
							</c:when>
						    <c:otherwise>
						    	<form:select path="aeRoutineReport.adverseEvents[${status.index}].hospitalization">
            					<form:option value=" " label="Please select" />
            					<form:options items="${hospitalization}" itemValue="name" itemLabel="displayName"/>
            					</form:select>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].hospitalization"/>
							</c:otherwise>
						</c:choose>
            		</td>
            		
            		<td>
            			<c:choose>
						 	<c:when test="${ae.report != null}">
   								<span class="sae"><c:out value="${ae.expected == true ? 'Yes' : 'No' }" /></span>
							</c:when>
						    <c:otherwise>
						    	<form:select path="aeRoutineReport.adverseEvents[${status.index}].expected" >
            						<form:option value="" label="Please select" />
            						<form:option value="true" label="Yes" />
            						<form:option value="false" label="No" />
            					</form:select>
            					<tags:errors path="aeRoutineReport.adverseEvents[${status.index}].expected"/>
							</c:otherwise>
						</c:choose>
            		</td>
            	</tr>	 
            </c:forEach>
            </table>
            </c:if>
            </center>
        </jsp:attribute>
   
</tags:tabForm>
</body>
</html>
