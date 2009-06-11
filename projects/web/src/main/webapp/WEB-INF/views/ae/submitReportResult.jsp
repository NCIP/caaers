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
   		<tags:dwrJavascriptLink objects="submitReport"/>
   		<script type="text/javascript">
    		var reportIndex = ${empty command.reportIndex ? 'null' : command.reportIndex}
    		var timer;
    		
        	function checkReportSubmissionStatus(){
        		submitReport.fetchReportSubmissionStatus(${command.aeReport.id}, ${command.reportIndex}, function(result){
        			if(result.objectContent == "COMPLETED" || result.objectContent == "FAILED"){
        				$('reportStatusRowId').innerHTML = result.htmlContent;
        				clearTimeout(timer);
        			}else{
        				timer = setTimeout("checkReportSubmissionStatus()" ,30000);
        			}
        			// make the report name as link if the report submission failed.
        			if(result.objectContent == "FAILED"){
        				//$('reportNameId').innerHTML = "<div><a href=\"/pages/ae/edit?aeReport=" + '${command.lastVersion.report.aeReport.id}'
        				// + "\">" + '${command.lastVersion.report.reportDefinition.label}' + "</a></div>";
        				$('report-label').style.display = 'none';
        				$('report-link').style.display = '';
        			}
        		});
        		
        	}
        	
        	
        	function executeAction(aeReportId,url){
				var actions = $("actions-"+aeReportId)
				for ( i=0; i < actions.length; i++){
                  if(actions.options[i].selected && actions.options[i].value != "none"){
                  	window.open(url + "&format="+ actions.options[i].value,"_self")
                  }
               	}
        	}
        	
        	Event.observe(window, "load", function() {
        		//if(${command.lastVersion.reportStatus == 'INPROCESS'})
	        	//	checkReportSubmissionStatus();
        	});
        	
        	
        	function showToolTip(text, title) {
        Tip(text, WIDTH, 300, TITLE, title, SHADOW, false, FADEIN, 300, FADEOUT, 300, STICKY, 1, CLOSEBTN, true, CLICKCLOSE, true);
    }
        	
	    </script>
	</head>
	<body>
		<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>
		<tags:tabForm tab="${tab}" flow="${flow}">
			<jsp:attribute name="singleFields">
				<table class="tablecontent" width="75%">
	    			<tr>
    					<th scope="col" align="left"><b>Report</b> </th>
    					<th scope="col" align="left"><b>Amendment #</b> </th>
    					<th scope="col" align="left"><b>Status</b> </th>
    					<th scope="col" align="left"><b>Actions</b> </th>
    				</tr>
    				<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
	    				<c:if test="${status.index == command.reportIndex}">
	    				<c:set var="reportId" value="${report.id}"/>
   			 				<tr id="reportStatusRowId">
	       			     		<td id="reportNameId">
	       			     			<c:if test="${command.lastVersion.reportStatus == 'COMPLETED' or command.lastVersion.reportStatus == 'INPROCESS'}">
	       			     				<div id="report-label" class="label">${report.reportDefinition.label}</div>
	       			     				<div id="report-link" style="display:none">
	       			     					<a href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}"/>">
												${report.reportDefinition.label}
											</a>
	       			     				</div>
	       			     			</c:if>
	       			     			<c:if test="${command.lastVersion.reportStatus == 'FAILED'}">
	       			     				<div id="report-label" class="label" style="display:none">${report.reportDefinition.label}</div>
	       			     				<div id="report-link">
	       			     					<a href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}"/>">
												${report.reportDefinition.label}
											</a>
	       			     				</div>
	       			     			</c:if>
	            				</td>
    		       			 	<c:if test="${report.reportDefinition.amendable == true}">
	            					<td align="center">
	            						<div class="label">${report.lastVersion.reportVersionId}</div>
	            					</td>
	            				</c:if>
	            				<c:if test="${report.reportDefinition.amendable == false}">
	            					<td/>
	            				</c:if>
	            				<td id="reportSubmissionStatus">
            						<c:if test="${command.lastVersion.reportStatus == 'COMPLETED'}" >
           				 				<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${command.lastVersion.reportStatus}" lastVersion="${command.lastVersion}"/>
            						</c:if>	
            						<c:if test="${command.lastVersion.reportStatus == 'FAILED'}" >
           				 				<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${command.lastVersion.reportStatus}" lastVersion="${command.lastVersion}"/>           			
          				  			</c:if>
          				   			<c:if test="${command.lastVersion.reportStatus == 'INPROCESS'}" >
            			 				<span class="dueOn" >
           				 					<i>Submission to AdEERS in process</i>
           				 				</span>
           				 			</c:if>
			            		</td>
			            		<td>
									<c:if test="${(report.reportDefinition.amendable == false) or (report.isLatestVersion == true)}">
										<c:if test="${(command.lastVersion.reportStatus == 'PENDING') or (command.lastVersion.reportStatus == 'FAILED')}" >
											<a href="<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${report.id}"/>"><img src="<chrome:imageUrl name="../buttons/button_icons/small/check_icon_small.png" />" alt=""/> Submit</a>	
										</c:if>
									</c:if>					
            					</td>
	            			</tr>
	            		</c:if>
	            	</c:forEach>
    			</table>
    			<p>&nbsp;</p>
		    	<table class="tablecontent" width = "40%">
    				<tr>
    					<th scope="col" align="left"><b>View report</b></th>
    				</tr>
    				<tr>
    					<td class="completion-messages">
    					
    						<SELECT id="actions-${command.aeReport.id}" name="actions" onChange="executeAction(${command.aeReport.id},'<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}'/>')">
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
	        				<span class="next">
	            				<input type="image" alt="save »" value="Go to Manage Reports " id="flow-next" src="<c:url value="/images/blue/go_to_manage_reports_btn.png" />"/>
	        				</span>        
						</div>
					</div>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>