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
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
       
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
       Submit a specific Report
    </jsp:attribute>
    <jsp:attribute name="singleFields">
    	
    
    	<table class="tablecontent">
    			<tr>
    				<th scope="col" align="left"></b> </th>
    				<th scope="col" align="left"><b>Data complete</b> </th>
    				<th scope="col" align="left"><b>Report Submitted</b> </th>
    				<th scope="col" align="left"><b>Info</b> </th>
    			</tr>
    			<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
    			<tr>
            		<td><div class="label">${report.reportDefinition.name}</div></td>
            		<td><i>Not Implemented</i></td>
            		<td>
            			<c:if test="${ not empty report.submittedOn}" >
            				<center><strong>Submitted on </strong><br/><tags:formatDate value="${report.submittedOn}" /></center>
            			</c:if>
            			<c:if test="${empty report.submittedOn}" >
            				<center>
            				Due on <br> <b><tags:formatDate value="${report.dueOn}" /></b><br>
            				<%--<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportIndex=${status.index}"/>">Submit</a>--%>
            				<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${report.id}"/>">Submit</a>
            				</center>
            			</c:if>
            		</td>
            		<td><i>Not Implemented</i></td>
    			</tr>
    			</c:forEach>    						
    	</table>		
    	
    
     <input type="hidden" name="_finish"/>
    </jsp:attribute>
 
</tags:tabForm>
</body>
</html>