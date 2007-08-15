<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    font: arial 10px;
    width: 17em;
	}
	</style>

    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        
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
       
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
        Please select the CTC Categories for ${participantSummaryLine} on
        ${studySummaryLine}.
    </jsp:attribute>
    <jsp:attribute name="singleFields">
   <b>Periods of Observation </b><br>
   <div style="margin-left: 7.5em;">
   <b><tags:requiredIndicator/>From:&nbsp;&nbsp;</b>  <tags:dateInput path="aeRoutineReport.startDate"/>
   <b><tags:requiredIndicator/>To:&nbsp;&nbsp;</b>  <tags:dateInput path="aeRoutineReport.endDate"/>
   </div>
   <hr> 
    <c:forEach items="${ctcCats}" varStatus="status" var="category">
    		<c:if test='${status.index % 10 == 0}'>
    		    </div>
   				<div class="left-align">
			</c:if>
            <span onClick="afterCheck('${category.name}')" class=""><form:checkbox  id="${category.name}" path="ctcCatIds" value="${category.id}" /></span>
    	    <span  id="${status.index}" style="font:10px arial;" onClick="check('${category.name}','${status.index}')">${category.name}</span><br>
    </c:forEach>
    </jsp:attribute>
 
   
</tags:tabForm>
</body>
</html>