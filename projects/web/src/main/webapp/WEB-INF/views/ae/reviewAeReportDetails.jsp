<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<%@ page import = "java.util.ArrayList" %>

<html>
<head>
	<tags:includePrototypeWindow />
 	<tags:stylesheetLink name="tabbedflow"/>
 	<tags:stylesheetLink name="ae"/>
 	<tags:javascriptLink name="routing_and_review" />
 	<tags:dwrJavascriptLink objects="reviewRP,createAE,reviewAeReport"/>
    <script>
	    var routingHelper = new RoutingAndReviewHelper(reviewAeReport, 'reviewAeReport');
	    
	    function addComment(){
			routingHelper.addComment();
		}
	
		function editComment(id){
			routingHelper.enableEditMode(id);
		}
		
		function deleteComment(id){
			routingHelper.deleteComment(id);
		}
	
		function cancelEdit(){
			routingHelper.disableEditMode();
		}
		
		function saveEditedComment(){
			routingHelper.editComment();
		}
		
		function collapseAllComments(){
			routingHelper.collapseAllComments();
		}

		function expandAllComments(){
			routingHelper.expandAllComments();
		}
		
		function submitReport(reportId){
			var url = '<c:url value="/pages/ae/submitReport?aeReport=${command.aeReport.id}&reportId=' + reportId + '"/>';
			window.location = url;
		}
		
		Event.observe(window, "load", function(){
	    	if(${command.workflowEnabled}){
            	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
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
	</style>
</head>
<body>
	 <applet code="ch.randelshofer.pdf.EmbedPDFApplet" archive="<c:url value="/EmbedPDF3.jar"/>" width="100%" height="520">
	 					    <param name="pdf" value="<c:url value="/pages/ae/generateExpeditedfPdf?format=pdf&aeReport=${command.aeReport.id }"/>"/>
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
								<a href="#allBtnCtrl" onClick="javascript:expandAllComments();" ><img src="<c:url value="/images/b-plus.gif"/>" alt=""  /> Expand All</a>
								<a href="#allBtnCtrl"  onClick="javascript:collapseAllComments();"><img src="<c:url value="/images/b-minus.gif"/>" alt=""/> Collapse All</a>
							</td>
							<td>
								<div id="add-a-comment"><b>Add a Comment</b></div>
								<div id="edit-a-comment" style="display:none"><b>Edit Comment</b></div>
							</td>
						</tr>
						<tr>
							<td width="50%">
								<div id="scrollbar_content">
							</td>
							<td valign="top" align="right">
								<textarea id="enter-comment-text"></textarea>
								<input type="hidden" id="edit_comment_id" name="edit_comment_id" value="" />
								<a href="javascript:addComment();" id="add-btn">
									<img src="<c:url value="/images/sidebar/add_btn.png"/>" alt="Add" />
								</a>
								<a href="javascript:saveEditedComment();" id="edit-btn" style="display:none">
									<img src="<c:url value="/images/sidebar/edit_btn.png"/>" alt="Edit" />
								</a>
								<a href="javascript:cancelEdit();" id="cancel-btn" style="display:none">
									<img src="<c:url value="/images/sidebar/cancel_btn.png"/>" alt="Cancel" />
								</a><br><br>
								<b>Next Action</b>&nbsp;&nbsp;
								<select id="sliderWFAction" onChange="routingHelper.validateAndAdvanceWorkflow();">
									<option value="">Please select</option>
								</select>
								<img id="sliderWFAction-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
				
							
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
