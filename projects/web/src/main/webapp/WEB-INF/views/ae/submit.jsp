<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${tab.longTitle}</title>

    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        
        function fireAction(action, selected){
       
      	document.getElementById('command')._target.name='_noname';
        document.viewReport._action.value=action;
        document.viewReport._selected.value=selected;
        document.viewReport.submit();
    }
       
    </script>
    <style type="text/css">
        td.completion-messages p {
            margin-top: 0;
        }
        td.completion-messages h4 {
            padding: 6px 0 2px 0;
        }
        td.completion-messages ul {
            padding: 0;
            margin: 0;
        }
        td.completion-messages ul li {
            padding: 0;
            margin: 0;
            margin-left: 1em;
        }
    </style>
</head>
<body>
<tags:tabForm formName="viewReport" tab="${tab}" flow="${flow}">
    <jsp:attribute name="instructions">
       Submit a specific Report
    </jsp:attribute>
    <jsp:attribute name="singleFields">
    	<input type="hidden" name="_action" value="">
        <input type="hidden" name="_selected" value="">
    
    	<table class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b>Report</b> </th>
    				<th scope="col" align="left"><b>Report ID</b> </th>
    				<th scope="col" align="left"><b>Report version</b> </th>
    				<th scope="col" align="left"><b>Ready to submit?</b> </th>
    				<th scope="col" align="left"><b>Status</b> </th>
    				<th scope="col" align="left"><b>Actions</b> </th>
    			</tr>
    			<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
    			<tr>    				
            		<td><div class="label">${report.reportDefinition.name}</div></td>
            		<td><div class="label">${report.lastVersion.reportVersionId}</div></td>
            		<td><div class="label">v${fn:length(report.reportVersions) -1}</div></td>
            		<td class="completion-messages">
                        <c:choose>
                            <c:when test="${empty reportMessages[report.id].submittable}" >
                                Yes
                            </c:when>
                            <c:otherwise>
                                <p>Not yet.  Remaining to complete:</p>
                                <c:forEach items="${reportMessages[report.id].messages}" var="sectionEntry">
                                    <h4>${sectionEntry.key.displayName} section</h4>
                                    <c:forEach items="${sectionEntry.value}" var="msg">
                                        <ul>
                                            <li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
                                        </ul>
                                    </c:forEach>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
            		<td>
            			<c:if test="${report.lastVersion.reportStatus == 'PENDING'}" >
							<span class="dueOn" >
								<c:if test="${not empty report.lastVersion.dueOn}" >
            						<i>Due on</i> <br> <b><tags:formatDate value="${report.lastVersion.dueOn}" /></b>
            					</c:if>
            					<c:if test="${ empty report.lastVersion.dueOn}" >
            						<i>Amendment Due</i>
            					</c:if>
            				</span>
            			</c:if>
            			<c:if test="${report.lastVersion.reportStatus == 'WITHDRAWN'}" >
							<span class="submittedOn" >
            						<i>Withdrawn</i>
            				</span>
            			</c:if>
            			<c:if test="${report.lastVersion.reportStatus == 'COMPLETED'}" >
            				<span class="submittedOn" >
            					<i>Submitted on </i><br> <b><tags:formatDate value="${report.lastVersion.submittedOn}" /></b>
            				</span>
            			</c:if>	
            		
            			<%--
            			<c:if test="${ not empty report.lastVersion.submittedOn}" >
            				<center><strong>Submitted on </strong><br/><tags:formatDate value="${report.lastVersion.submittedOn}" /></center>
            			</c:if>
            			<c:if test="${empty report.lastVersion.submittedOn}" >
            				<center>
            				Due on <br> <b><tags:formatDate value="${report.lastVersion.dueOn}" /></b><br>
            				</center>
            			</c:if>
            			--%>
            		</td>
            		<td>
            			<c:if test="${report.dataMissing == 'false'}" >
							<c:if test="${report.lastVersion.reportStatus == 'PENDING'}" >
								<center>
									<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${report.id}"/>">Submit</a> |	
									<%-- <a href="#" onClick="withdraw(${command.aeReport.id},${report.id})">Withdraw</a> --%>
									<a href="javascript:fireAction('withdraw',${report.id});">Withdraw</a>
								</center>
							</c:if>
							
							<c:if test="${report.lastVersion.reportStatus == 'WITHDRAWN'}" >
								<center>
									<a href="<c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&reportId=${report.id}"/>">Amend</a>
								</center>
							</c:if>
							
							<c:if test="${report.lastVersion.reportStatus == 'COMPLETED'}" >
								<center>
									<a href="<c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&reportId=${report.id}"/>">Amend</a>
								</center>
							</c:if>
						</c:if>
            		
            		</td>
    			</tr>
    			</c:forEach>    						
    	</table>		
    	
    
     <input type="hidden" name="_finish"/>
    </jsp:attribute>
 
</tags:tabForm>
</body>
</html>