<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<%@ page import = "java.util.ArrayList" %>

<html>
<head>
 	<tags:dwrJavascriptLink objects="reviewAeReport"/>
    <script>
	    var routingHelper = new RoutingAndReviewHelper(reviewAeReport, 'reviewAeReport');
	    
	    function addComment(reportId){
			routingHelper.addComment(reportId);
		}
	
		function editComment(id, reportId){
			routingHelper.enableEditMode(id, reportId);
		}
		
		function deleteComment(id, reportId){
			routingHelper.deleteComment(id, reportId);
		}
	
		function cancelEdit(reportId){
			routingHelper.disableEditMode(reportId);
		}
		
		function saveEditedComment(reportId){
			routingHelper.editComment(reportId);
		}
		
		function collapseAllComments(reportId){
			routingHelper.collapseAllComments(reportId);
		}

		function expandAllComments(reportId){
			routingHelper.expandAllComments(reportId);
		}
		
		function advanceWorkflow(entityId, value){
			routingHelper.advanceWorkflow(entityId, value);
		}
		
		function submitReport(reportId){
			var url = '<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=' + reportId + '"/>';
			window.location = url;
		}
		
		function createDropDowns() {
		jQuery(".fg-button").each(function(){
			id = jQuery(this).attr("id");
			options = "options-" + id;
			jQuery("#"+id).menu({
				content: jQuery("#"+options).html(),		
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
	}
		
		Event.observe(window, "load", function(){
	    	if(${command.workflowEnabled}){
            	routingHelper.retrieveReviewCommentsAndActions('${command.reportId}');
 			}
	    });
	</script>
	<style>
		#scrollbar_content {
			overflow:auto;
			width:485px;
			height: 180px;
		}
		#enter-comment-text {
			width:392px;
			height:60%;
		}
		
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
		.fg-menu a:link, .fg-menu a:visited, .fg-menu a:hover, .fg-menu a:active {
			font-size:10pt;
		}
	</style>
</head>
<body>
	 <applet code="ch.randelshofer.pdf.EmbedPDFApplet" archive="<c:url value="/EmbedPDF3.jar"/>" width="100%" height="520">
	 			    <param name="pdf" value="<c:url value="/pages/ae/generateExpeditedfPdf?format=pdf&aeReport=${command.aeReport.id }&reportId=${command.reportId}"/>"/>
   			     	<param name="codebase_lookup" value="false">
   			     	<param name="classloader_cache" value="false">
   			     	<param name="java_arguments" value="-Djnlp.packEnabled=true"/>
   		   		  	<param name="image" value="lib/Splash.gif"/>
   		     		<param name="boxborder" value="false"/>
   	   		  		<param name="centerimage" value="true"/>
	 	
	 </applet>
	
			<chrome:box title="Report Validation" id="popupId" collapsable="true">
				<table width="100%" border="0" cellspacing="0" >
					<tr>
   		 				<th scope="col" align="left"><b>Report</b> </th>
   	 					<th scope="col" align="center"><b>Amendment #</b> </th>
    					<th scope="col" align="center"><b>Ready to submit?</b> </th>
    					<th scope="col" align="left"><b>Status</b> </th>
    					<th scope="col" align="left"><b>Options</b> </th>
    				</tr>
    				<c:forEach items="${command.aeReport.reports}" varStatus="status" var="report">
    					<c:if test="${command.reportId != null}">
    						<c:if test="${report.status ne 'WITHDRAWN' && report.status ne 'REPLACED' && report.status ne 'AMENDED' && report.id == command.reportId}">
    							<ae:reviewAeReportValidation report="${report }" />
    						</c:if>
    					</c:if>
    					<c:if test="${command.reportId == null}">
    						<c:if test="${report.status ne 'WITHDRAWN' && report.status ne 'REPLACED' && report.status ne 'AMENDED'}">
    							<ae:reviewAeReportValidation report="${report }" />
    						</c:if>
    					</c:if>
    				</c:forEach>
				</table>
			</chrome:box>
	
	<c:if test="${command.workflowEnabled}">		
		<chrome:box title="Enter comments">
			<chrome:division>
				<form:form commandName="command">
					<table width="100%">
						<tr>
							<td width="50%" align="right">
								<a name="allBtnCtrl"></a>
								<a href="#allBtnCtrl" onClick="javascript:expandAllComments('${command.reportId }');" ><img src="<c:url value="/images/b-plus.gif"/>" alt=""  /> Expand All</a>
								<a href="#allBtnCtrl"  onClick="javascript:collapseAllComments('${command.reportId }');"><img src="<c:url value="/images/b-minus.gif"/>" alt=""/> Collapse All</a>
							</td>
							<td>
								<div id="add-a-comment-${command.reportId }"><b>Add a Comment</b></div>
								<div id="edit-a-comment-${command.reportId }" style="display:none"><b>Edit Comment</b></div>
							</td>
						</tr>
						<tr>
							<td width="50%">
								<div id="scrollbar_content-${command.reportId }">
							</td>
							<td valign="top" align="right">
								<textarea id="enter-comment-text-${command.reportId }" style="width:392px;height:60%"></textarea>
								<input type="hidden" id="edit_comment_id-${command.reportId }" name="edit_comment_id" value="" />
								<a href="javascript:addComment('${command.reportId }');" id="add-btn-${command.reportId }">
									<img src="<c:url value="/images/sidebar/add_btn.png"/>" alt="Add" />
								</a>
								<a href="javascript:saveEditedComment('${command.reportId }');" id="edit-btn-${command.reportId }" style="display:none">
									<img src="<c:url value="/images/sidebar/edit_btn.png"/>" alt="Edit" />
								</a>
								<a href="javascript:cancelEdit('${command.reportId }');" id="cancel-btn-${command.reportId }" style="display:none">
									<img src="<c:url value="/images/sidebar/cancel_btn.png"/>" alt="Cancel" />
								</a>
								<tags:indicator id="ajax_wait"/>
								<br><br>
								
							</td>
						</tr>
						<tr>
							<td width="50%"/><td>
							<td>
								
							</td>
						</tr>
					</table>
					<div id="entire-slider"/>
				</form:form>
			</chrome:division>
		</chrome:box>
	</c:if>
	<div id="select_report_popup" style="display:none;text-align:left;z-index:1000" >
			</div>	
</body>
</html>
