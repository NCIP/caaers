<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
	<head>
		<title>${tab.longTitle}</title>
   		<tags:dwrJavascriptLink objects="submitReport"/>
   		<script type="text/javascript">
    		var timer;
    		
        	function checkReportSubmissionStatus(){
        		submitReport.fetchReportSubmissionStatus(${command.aeReport.id}, ${command.report.id}, function(result){
        			if(result.objectContent == "COMPLETED" || result.objectContent == "FAILED" || result.objectContent == "WITHDRAW_FAILED"){
        				$('reportStatusRowId').innerHTML = result.htmlContent;
        				clearTimeout(timer);
        			}else{
        				timer = setTimeout("checkReportSubmissionStatus()" ,30000);
        			}
        			// make the report name as link if the report submission failed.
        			if(result.objectContent == "FAILED" || result.objectContent == "WITHDRAW_FAILED"){
        				//$('reportNameId').innerHTML = "<div><a href=\"/pages/ae/edit?aeReport=" + '${command.report.lastVersion.report.aeReport.id}'
        				// + "\">" + '${command.report.lastVersion.report.reportDefinition.label}' + "</a></div>";
        				//$('report-label').style.display = 'none';
        				$('report-link').style.display = '';
        			}
        		});
        		
        	}
        	
            function refreshThePage() {
                jQuery("#_target").attr("name", "_target2");
                jQuery("#_page").attr("value", "2");
                var _e = document.getElementsByName("_finish")[0];
                jQuery(_e).attr('disabled', '');
                $('command').submit();
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
	<!--[if IE]>
        <style>
			* {
				zoom:1;
			}
			#taskbar ul, #floatingTaskbar ul {
				margin:0 0 0 -8px;
			}
			#floatingTaskbar li.lte4 a.gt18 {
				top:0;
				position:absolute;
			}
			#secondlevelnav_listNotificationController {
				left:696px;
			}
        </style>
    <![endif]-->
	</head>
	<body>
		<script type="text/javascript" src="<c:url value="/js/wz_tooltip/wz_tooltip.js" />"></script>
		<tags:tabForm tab="${tab}" flow="${flow}">
			<jsp:attribute name="singleFields">
				<div id="actions-${command.aeReport.id}" style="display:none;">
    						<ul>
     							<c:set var="exportOptionsCount" value="0"/>
     							<%--<c:if test="${command.study.caaersXMLType}">--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=xml'/>','_self')"><img src="<chrome:imageUrl name="../blue/xml-icon.png"/>" alt=""/> Export caAERS XML</a></li>
									<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
<%--
     							</c:if>
     							<c:if test="${command.study.adeersPDFType}">
--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=pdf'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export AdEERS PDF</a></li>
    		 						<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
<%--
								</c:if>
     							<c:if test="${command.study.medwatchPDFType}">
--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=medwatchpdf'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export MedWatch 3500A PDF</a></li>
     								<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
<%--
								</c:if>
   			  					<c:if test="${command.study.dcpSAEPDFType}">
--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=dcp'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP SAE PDF</a></li>
     								<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
<%--
								</c:if>
     							<c:if test="${command.study.ciomsPDFType}">
--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=cioms'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export CIOMS PDF</a></li>
     								<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
<%--
								</c:if>
     							<c:if test="${command.study.ciomsSaePDFType}">
--%>
     								<li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=ciomssae'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/> Export DCP Safety Report PDF</a></li>
     								<c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
								<%--</c:if>--%>

                                <%--
                                DISPLAY ALL SUCTOME REPORTS ASSIATES TO DATA COLLECTION
                                --%>

                                <c:if test="${command.report.reportDefinition.reportFormatType.code == 7}">
                                    <li><a href="#" onclick="javascript:window.open('<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${command.aeReport.id}&reportId=${command.reportId}&format=customPDF'/>','_self')"><img src="<chrome:imageUrl name="../blue/pdf.png"/>" alt=""/>(Custom) ${command.report.reportDefinition.label}</a></li>
                                    <c:set var="exportOptionsCount" value="${exportOptionsCount + 1}"/>
                                </c:if>

 							</ul>
						</div>

					<c:if test="${exportOptionsCount > 0}">
						<div style="text-align:right;">
                            <img id="export-menu" class="actionsButton" src='<c:url value="/images/orange-export.gif" />' border='0' style='cursor:pointer;'>
						</div>
					</c:if>
                        <div id="reportStatusRowId">
                           
                            <c:if test="${report.reportDefinition.amendable == true}">
                                <div class="row">
                                    <div class="label">
                                        Amendment
                                    </div>
                                    <div class="value">
                                        ${report.lastVersion.reportVersionId}
                                    </div>
                                </div>
                            </c:if>

                            <c:if test="${(command.report.lastVersion.reportStatus == 'FAILED') or (command.report.lastVersion.reportStatus eq 'WITHDRAW_FAILED')}">
                                <div class="error-box message"><p>${command.report.lastVersion.statusAsString}</p></div>
                            </c:if>

                            <div class="row">
                                <div class="label">Submission Status</div>
                                <div id="reportSubmissionStatus" class="value">
                                    <c:if test="${command.report.lastVersion.reportStatus == 'COMPLETED'}">
                                        <ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${command.report.lastVersion.reportStatus}" lastVersion="${command.report.lastVersion}"/>
                                    </c:if>
                                    <c:if test="${(command.report.lastVersion.reportStatus == 'FAILED') or (command.report.lastVersion.reportStatus eq 'WITHDRAW_FAILED')}">
                                        <ae:oneListReportSubmissionStatus theReport="${report}" reportStatus="${command.report.lastVersion.reportStatus}" lastVersion="${command.report.lastVersion}"/>
                                    </c:if>
                                    <c:if test="${command.report.lastVersion.reportStatus == 'INPROCESS'}">
                                        <span class="dueOn"><i>Submission to AdEERS in process</i></span>
                                    </c:if>
                                </div>
                            </div>
                            <c:if test="${(command.report.lastVersion.reportStatus == 'PENDING') or (command.report.lastVersion.reportStatus == 'FAILED')}">
                                <div style="float:left;">
                                    <c:set var="href">
                                        <c:url value="/pages/ae/edit?aeReport=${command.aeReport.id}&report=${command.report.id}"/>
                                    </c:set>
									<tags:button color="blue" value="Edit Report" icon="back" markupWithTag="a" href="${href}" />
                                </div>
                                <br style="clear:both;"/>
                            </c:if>
                        </div>
    			<input type="hidden" name="_finish"/>		
			</jsp:attribute>
			<jsp:attribute name="tabControls">
				
			</jsp:attribute>
			
		</tags:tabForm>
	</body>
</html>