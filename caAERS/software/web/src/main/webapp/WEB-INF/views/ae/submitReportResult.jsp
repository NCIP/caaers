<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
	<head>
		<title>${tab.longTitle}</title>
   		<tags:dwrJavascriptLink objects="submitReport"/>
   		<script type="text/javascript">
    		var reportIndex = ${empty command.reportIndex ? 'null' : command.reportIndex}
    		var timer;
    		
        	function checkReportSubmissionStatus(){
        		submitReport.fetchReportSubmissionStatus(${command.aeReport.id}, ${command.reportIndex}, function(result){
        			if(result.objectContent == "COMPLETED" || result.objectContent == "FAILED" || result.objectContent == "WITHDRAW_FAILED"){
        				$('reportStatusRowId').innerHTML = result.htmlContent;
        				clearTimeout(timer);
        			}else{
        				timer = setTimeout("checkReportSubmissionStatus()" ,30000);
        			}
        			// make the report name as link if the report submission failed.
        			if(result.objectContent == "FAILED" || result.objectContent == "WITHDRAW_FAILED"){
        				//$('reportNameId').innerHTML = "<div><a href=\"/pages/ae/edit?aeReport=" + '${command.lastVersion.report.aeReport.id}'
        				// + "\">" + '${command.lastVersion.report.reportDefinition.label}' + "</a></div>";
        				$('report-label').style.display = 'none';
        				$('report-link').style.display = '';
        			}
        		});
        		
        	}
        	
        	

        	
        	Event.observe(window, "load", function() {
	        		jQuery("#export-menu").menu({
					content: jQuery("#actions-${command.aeReport.id}").html(),		
					maxHeight: 180,
					width: 230,
	                positionOpts: {
	                    directionV: 'down',
	                    posX: 'right',
	                    posY: 'bottom',
	                    offsetX: 0,
	                    offsetY: 0
	                },
	                showSpeed: 300
				});
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
				<div style="text-align:right;">
					<a id="export-menu" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all">
					<span class="ui-icon ui-icon-triangle-1-s"></span>
					Export</a>
				</div>
				
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
	       			     					<a href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}&report=${report.id}"/>">
												${report.reportDefinition.label}
											</a>
	       			     				</div>
	       			     			</c:if>
	       			     			<c:if test="${command.lastVersion.reportStatus == 'FAILED' or command.lastVersion.reportStatus == 'WITHDRAW_FAILED'}">
	       			     				<div id="report-label" class="label" style="display:none">${report.reportDefinition.label}</div>
	       			     				<div id="report-link">
	       			     					<a href="<c:url value="/pages/ae/edit?aeReport=${report.aeReport.id}&report=${report.id}"/>">
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
            						<c:if test="${(command.lastVersion.reportStatus == 'FAILED') or (command.lastVersion.reportStatus eq 'WITHDRAW_FAILED')}" >
           				 				<ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${command.lastVersion.reportStatus}" lastVersion="${command.lastVersion}"/>           			
          				  			</c:if>
          				   			<c:if test="${command.lastVersion.reportStatus == 'INPROCESS'}" >
            			 				<span class="dueOn" >
           				 					<i>Submission to AdEERS in process</i>
           				 				</span>
           				 			</c:if>
			            		</td>
			            		<td>
										<c:if test="${(command.lastVersion.reportStatus == 'PENDING') or (command.lastVersion.reportStatus == 'FAILED')}" >
											<c:set var="href"><c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=${report.id}"/></c:set>
											<tags:button color="orange" size="small" value="Submit Again" icon="check" markupWithTag="a" href="${href}" />
										</c:if>
            					</td>
	            			</tr>
	            		</c:if>
	            	</c:forEach>
    			</table>
		
    					<div id="actions-${command.aeReport.id}" style="display:none;">
    						<ul>
     					
     							<c:if test="${command.study.caaersXMLType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=xml'/>"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/> Export caAERS XML</a></li>
     							</c:if>
     							<c:if test="${command.study.adeersPDFType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=pdf'/>"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export AdEERS PDF</a></li>
    		 					</c:if>
     							<c:if test="${command.study.medwatchPDFType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=medwatchpdf'/>"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export MedWatch 3500A PDF</a></li>
     							</c:if>
   			  					<c:if test="${command.study.dcpSAEPDFType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=dcp'/>"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP SAE PDF</a></li>
     							</c:if>
     							<c:if test="${command.study.ciomsPDFType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=cioms'/>"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export CIOMS PDF</a></li>
     							</c:if>
     							<c:if test="${command.study.ciomsSaePDFType}">
     								<li><a href="<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${reportId}&format=ciomssae'/>"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP Safety Report PDF</a></li>
     							</c:if>
 							</ul>
						</div>
    			<input type="hidden" name="_finish"/>		
			</jsp:attribute>
			<jsp:attribute name="tabControls">
				
			</jsp:attribute>
			
		</tags:tabForm>
	</body>
</html>