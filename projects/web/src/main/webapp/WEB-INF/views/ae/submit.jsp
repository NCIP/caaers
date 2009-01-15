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
    <tags:slider>
   		<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			Here is the comments's DIV
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>
    <link rel="stylesheet" type="text/css" href="/caaers/css/slider.css" />
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        
        function fireAction(action, selected){
       
      	document.getElementById('command')._target.name='_noname';
        document.viewReport._action.value=action;
        document.viewReport._selected.value=selected;
        document.viewReport.submit();
    }
    
    Event.observe(window, "load", function() {
    	 $('flow-next').value="Go to Manage Reports ";
    })
       
       function executeAction(aeReportId,url){
			
			var actions = $("actions-"+aeReportId)
			 for ( i=0; i < actions.length; i++)
               {
                  if (actions.options[i].selected && actions.options[i].value != "none") {
                     window.open(url + "&format="+ actions.options[i].value,"_self")
                   }
               }
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
<tags:tabForm formName="viewReport" tab="${tab}" flow="${flow}" pageHelpAnchor="section18submit">
    <jsp:attribute name="instructions">
       Submit the report once it is complete. You can also withdraw the report completely, or amend it from this page.
    </jsp:attribute>
    <jsp:attribute name="singleFields">
    	<input type="hidden" name="_action" value="">
        <input type="hidden" name="_selected" value="">
        
    	<c:if test="${not reportMessages[command.ZERO].submittable}">
    		<table class="tablecontent" width="75%">
    			<tr>
    				<th scope="col" align="left"><b>Report Validation Errors</b></th>
    			</tr>
    			<tr>
    				<td class="completion-messages">
    				
    				 <c:forEach items="${reportMessages[command.ZERO].messages}" var="sectionEntry">
                       <h4>${sectionEntry.key.displayName} section</h4>
                       <c:forEach items="${sectionEntry.value}" var="msg">
                          <ul>
                           <li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
                         </ul>
                       </c:forEach>
                     </c:forEach>
    				</td>
    			</tr>
    		</table>
    		<p>&nbsp;  </p>
    	</c:if>

		
    		    	
    	<table class="tablecontent">
    			<tr>
    				<th scope="col" align="left"><b>Report</b> </th>
    				<th scope="col" align="left"><b>Report Version</b> </th>
    				<th scope="col" align="left"><b>Ready to submit?</b> </th>
    				<th scope="col" align="left"><b>Status</b> </th>
    				<th scope="col" align="left"><b>Actions</b> </th>
    			</tr>
    			<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
    			<tr>    				
            		<td><div class="label">${report.reportDefinition.label}</div></td>
            		<c:if test="${report.reportDefinition.amendable == true}">
	            		<td align="center"><div class="label">${report.lastVersion.reportVersionId}</div></td>
	            	</c:if>
	            	<c:if test="${report.reportDefinition.amendable == false}">
	            		<td/>
	            	</c:if>
            		<td class="completion-messages">
						
                        <c:choose>
                            <c:when test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable}" >
                                Yes
                            </c:when>
                            <c:otherwise>
								<c:if test="${report.status ne 'COMPLETED'}">
                                <p>Not yet.  Remaining to complete:</p>
                                <c:forEach items="${reportMessages[report.id].messages}" var="sectionEntry">
                                    <h4>${sectionEntry.key.displayName} section</h4>
                                    <c:forEach items="${sectionEntry.value}" var="msg">
                                        <ul>
                                            <li>${msg.text} <c:if test="${not empty msg.property}"><!-- (${msg.property}) --></c:if></li>
                                        </ul>
                                    </c:forEach>
                                </c:forEach>
								</c:if>
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
            			<c:if test="${report.lastVersion.reportStatus == 'FAILED'}" >
             				<span class="dueOn" >
            					<i>Submission to AdEERS failed </i>
            				</span>           			
            			</c:if>
             			<c:if test="${report.lastVersion.reportStatus == 'INPROCESS'}" >
             				<span class="dueOn" >
            					<i>Submission to AdEERS in process</i>
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
            			<c:if test="${reportMessages[command.ZERO].submittable and reportMessages[report.id].submittable}" >
							<c:if test="${(report.reportDefinition.amendable == false) or (report.isLatestVersion == true)}">
								<c:if test="${(report.lastVersion.reportStatus == 'PENDING') or (report.lastVersion.reportStatus == 'FAILED')}" >
									<center>
										<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${report.id}"/>">Submit</a> |	
										<%-- <a href="#" onClick="withdraw(${command.aeReport.id},${report.id})">Withdraw</a> --%>
										<a href="javascript:fireAction('withdraw',${report.id});">Withdraw</a>
									</center>
								</c:if>
							
								<c:if test="${report.reportDefinition.amendable and ( (report.lastVersion.reportStatus == 'WITHDRAWN') or (report.lastVersion.reportStatus == 'COMPLETED') )}" >
									<center>
										<a href="<c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&reportId=${report.id}&action=amendReport"/>">Amend</a>
									</center>
								</c:if>
							</c:if>					
						
						</c:if>
            		
            		</td>
    			</tr>
    			</c:forEach>    						
    	</table>		
    	<p>&nbsp;</p>
    	<table class="tablecontent" width = "40%">
    			<tr>
    				<th scope="col" align="left"><b>View report</b></th>
    			</tr>
    			<tr>
    				<td class="completion-messages">
    				
    				 <SELECT id="actions-${command.aeReport.id}" name="actions" onChange="executeAction(${command.aeReport.id},'<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}'/>')">
     					<OPTION selected label="none" value="none">None</OPTION>
     					
     					<c:if test="${command.study.caaersXMLType}">
     						<OPTION label="xml" value="xml">caAERS XML</OPTION>
     					</c:if>
     					<c:if test="${command.study.adeersPDFType}">
     						<OPTION label="pdf" value="pdf">AdEERS PDF</OPTION>
     					</c:if>
     					<c:if test="${command.study.medwatchPDFType}">
     						<OPTION label="medwatchpdf" value="medwatchpdf">MedWatch 3500A PDF</OPTION>
     					</c:if>
     					<c:if test="${command.study.dcpSAEPDFType}">
     						<OPTION label="dcp" value="dcp">DCP SAE PDF</OPTION>
     					</c:if>
     					<c:if test="${command.study.ciomsPDFType}">
     						<OPTION label="cioms" value="cioms">CIOMS PDF</OPTION>
     					</c:if>
     					<c:if test="${command.study.ciomsSaePDFType}">
     						<OPTION label="ciomssae" value="ciomssae">DCP Safety Report PDF</OPTION>
     					</c:if>
 					</SELECT>
     					
    				</td>
    			</tr>
    		</table>
    
     <input type="hidden" name="_finish"/>
    </jsp:attribute>
	<jsp:attribute name="tabControls">
  	<div class="content buttons autoclear">
    		<div class="local-buttons"></div>
	    	<div class="flow-buttons">
	        <span class="prev">
	                <input type="image" alt="« Save &amp; Back" value="« saveback" class="tab11" id="flow-prev" src="/caaers/images/blue/saveback_btn.png"/>
	        </span>
	        <span class="next">
	            <input type="image" alt="save »" value="Go to Manage Reports " id="flow-next" src="<c:url value="/images/blue/go_to_manage_reports_btn.png" />"/>
	        </span>
	    	</div>
	</div>
	</jsp:attribute>
</tags:tabForm>
</body>
</html>