<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>

    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <tags:slider renderComments="${command.workflowEnabled}" renderAlerts="false" display="">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments domainObjectType="aeReport"/>
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        var descArray = new Array();
        
        function afterCheck(checkBoxId){
	     	//alert ($(checkBoxId).checked)   
	        
        }
        
    	function check(checkBoxId, spanId){
	     	if ($(checkBoxId).checked){
	     			$(checkBoxId).checked=false
	     			//$(spanId).style.color="black"
		     	}else {
			     	$(checkBoxId).checked=true
			     	//$(spanId).style.color="green"
			        //$(spanId).style.font="bold"

		     	}
        }
        
         Element.observe(window, "load", function() {
         	
         	//push the description into the array
			<c:forEach items="${treatmentAssignments}" var="ta">
        		descArray.push("${ta.escapedDescription}");
        	</c:forEach>	
        	
        	// treatment dropdown.
			$('aeRoutineReport.treatmentAssignment').observe("change", function(event){
				selIndex = $('aeRoutineReport.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					$('aeRoutineReport.treatmentAssignmentDescription').value = descArray[selIndex-1];
				}else{
					$('aeRoutineReport.treatmentAssignmentDescription').clear();
				}
			}); 
			
			 //set the initial value of the description text area. 
            	selIndex = $('aeRoutineReport.treatmentAssignment').selectedIndex;
				if(selIndex > 0){
					$('aeRoutineReport.treatmentAssignmentDescription').value = descArray[selIndex-1];
				}else{
					$('aeRoutineReport.treatmentAssignmentDescription').clear();
				}
         
         })
       
    </script>
    <style type="text/css">
    
    .left-align {
    padding-right: 1.2em;
    float: left;
    font: arial 10px;
    width: 17em;
	}
	
	div.row div.label {
    	width: 16em;
    }
    div.row div.value {
    	margin-left: 18em;
    }
	</style>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
        Please select the CTC Categories for ${participantSummaryLine} on
        ${studySummaryLine}.
        <div class="row">
        	<div class="label">
                CTC Version	
            </div>
            <div class="value">
            	${term}
            </div>
        </div>    
    </jsp:attribute>
    <jsp:attribute name="singleFields">
   <chrome:division title="Periods of Observation " id="observation_period">
   		<div class="row">
        	<div class="label">
                <tags:requiredIndicator/>From	
            </div>
            <div class="value">
            	<tags:dateInput path="aeRoutineReport.startDate"/>
            	&nbsp;&nbsp;&nbsp;<tags:requiredIndicator/><b>To&nbsp;&nbsp;&nbsp;</b>  <tags:dateInput path="aeRoutineReport.endDate"/>
            </div>
        </div>
   </chrome:division>
   
   <chrome:division title="Treatment Assignment Code" id="treatment_assignment_code">
   		<div class="row">
        	<div class="label">
                Treatment assignment code	
            </div>
            <div class="value">
            	<form:select path="aeRoutineReport.treatmentAssignment">
            		<form:option value=" " label="Please select" />
            		<form:options items="${treatmentAssignments}" itemValue="id" itemLabel="code"/>
        		</form:select>        
            </div>
        </div>
        
        <div class="row" >
        	<div class="label">
                Description	
            </div>
            <div class="value">
            	<textarea id="aeRoutineReport.treatmentAssignmentDescription" rows="2" cols="65" name="fake" disabled="true"></textarea>  
            </div>
        </div>
   </chrome:division> 
   <chrome:division title="CTC categories" id="ctc_category">
    <c:forEach items="${ctcCats}" varStatus="status" var="category">
    		<c:if test='${status.index % 10 == 0}'>
    		    </div>
   				<div class="left-align">
			</c:if>
            <span onClick="afterCheck('${category.name}')" class=""><form:checkbox  id="${category.name}" path="ctcCatIds" value="${category.id}" /></span>
    	    <span  id="${status.index}" style="font:10px arial;" onClick="check('${category.name}','${status.index}')">${category.name}</span><br>
    </c:forEach>
    </chrome:division> 
 </jsp:attribute>
   
</tags:tabForm>
</body>
</html>