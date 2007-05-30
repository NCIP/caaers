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
    .left-align {
    padding-right: 1.2em;
    float: left;
    width: 20em;
	}
	.cats-highlighted {
		font:10px arial;
		background-color:#00ff00;
	}
	.cats {
		font:9px arial;
	}
	
	.selects {
		width:90%;
		font:11px arial;	
	}
	
    .lebanon {
    display:none;
	}
	</style>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        var initialCategory ='';

        Element.observe(window, "load", function() {
	        
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
        You are entering routine Adverse Events for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
        <%--<div class="lebanon"><form:select id="termIds" size="1" path="ctcTermIds"></form:select></div>--%>
        <div>
           <input type="hidden" name="_action" value="">
           <input type="hidden" name="_selected" value="">
        </div>
    </jsp:attribute>
   
    <jsp:attribute name="singleFields" >
    	<form:select id="cats" path="cats" size="5" cssClass="cats">
    	<form:options items="${command.categories}" itemValue="id" itemLabel="name"/>
    	</form:select>
    	<form:select id="ctcTerms" path="ctcTermIds" size="5" cssClass="selects">
    	</form:select>
    	<a href="javascript:fireAction('addTerm','0');"><img
                        src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a>
    	<%--
    	<c:forEach items="${command.categories}" varStatus="status" var="ctcCategory">
    			<span onClick="showTerms('${ctcCategory.name}')" class="cats"><b>${ctcCategory.name} </b>| </span> 
        </c:forEach>
       
        <br> 
    	<c:forEach items="${command.categories}" varStatus="status" var="ctcCategory">
    			<span id="${ctcCategory.name}" style="display:none;">
                <c:forEach items="${ctcCategory.terms}" varStatus="st" var="ctcTerm">
                	
                	<span id="${ctcTerm.term}" onClick="addAE('${ctcTerm.term}','${ctcTerm.id}')">${ctcTerm.term}</span><br>
                	
                </c:forEach>
                </span> 
        </c:forEach>
         --%>
       <hr> 
    </jsp:attribute>
    
    <jsp:attribute name="repeatingFields">
    		<p>
    		<c:if test="${fn:length(command.aeRoutineReport.adverseEvents) > 0}" >
    		<table border="1">
    			<tr>
    				<td><b>Term</b></td>
    				<td><b>Grade</b></td>
    				<td><b>Attribution</b></td>
    				<td><b>Hospitalization</b></td>
    				<td><b>Expected</b></td>
    				
            <c:forEach items="${command.aeRoutineReport.adverseEvents}" var="ae" varStatus="status">
            	<tr>
            		<td>${ae.ctcTerm.term}</td>
            		
            		<td>
            			<form:select path="aeRoutineReport.adverseEvents[${status.index}].grade">
            				<form:options items="${ae.ctcTerm.contextualGrades}" itemValue="grade.name" itemLabel="grade.code"/>
            			</form:select>
            		</td>
            		
            		<td>
            			<form:select path="aeRoutineReport.adverseEvents[${status.index}].attributionSummary" cssClass="cats">
            				<form:options items="${attribution}" itemValue="name" itemLabel="displayName"/>
            			</form:select>
            		</td>
            		
            		<td>
            			<form:select path="aeRoutineReport.adverseEvents[${status.index}].hospitalization" cssClass="cats">
            				<form:options items="${hospitalization}" itemValue="name" itemLabel="displayName"/>
            			</form:select>
            		</td>
            		
            		<td><form:select path="aeRoutineReport.adverseEvents[${status.index}].expected" >
            				<form:option value="true" label="Yes" />
            				<form:option value="false" label="No" />
            			</form:select>	
            		</td>
            		<%--<td><form:input path="aeRoutineReport.adverseEvents[${status.index}].comments" /></td>--%>
            	</tr>	 
            </c:forEach>
            </table></p>
            </c:if>
        </jsp:attribute>
   
</tags:tabForm>
</body>
</html>