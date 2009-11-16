<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<%@ attribute name="report" type="gov.nih.nci.cabig.caaers.domain.report.Report" required="false" %>
<%@ attribute name="reportingPeriod" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" required="false" %>

<c:if test="${report == null}">
	<c:set var="entity" value="${reportingPeriod}"/>
</c:if>
<c:if test="${reportingPeriod == null}">
	<c:set var="entity" value="${report}"/>
</c:if>
<div>
	<div id="collapse-all-comments">
		<a name="allBtnCtrl"></a>
		<a href="#allBtnCtrl" onClick="javascript:expandAllComments('${entity.id }');" ><img src="<c:url value="/images/b-plus.gif"/>" alt=""  /> Expand All</a>
		<a href="#allBtnCtrl"  onClick="javascript:collapseAllComments('${entity.id }');"><img src="<c:url value="/images/b-minus.gif"/>" alt=""/> Collapse All</a>
	</div>
	<div id="comments-window">
		 <div id="scrollbar_container">  
			<div id="scrollbar_track"><div id="scrollbar_handle"></div></div>  
			<div id="scrollbar_content-${entity.id }">
			</div>  
		</div>
	</div>	
	<div id="right-panel">
		<div id="add-a-comment-${entity.id }">Add a Comment</div>
		<div id="edit-a-comment-${entity.id }" style="display:none">Edit Comment</div>
		<textarea id="enter-comment-text-${entity.id }" style="width:392px;height:60%"></textarea>
		<input type="hidden" id="edit_comment_id-${entity.id }" name="edit_comment_id" value="" />
		<a href="javascript:addComment('${entity.id }');" id="add-btn-${entity.id }">
		<img src="<c:url value="/images/sidebar/add_btn.png"/>" alt="Add" /></a>
		<a href="javascript:saveEditedComment('${entity.id }');" id="edit-btn-${entity.id }" style="display:none"><img src="<c:url value="/images/sidebar/edit_btn.png"/>" alt="Edit" /></a>
		<a href="javascript:cancelEdit('${entity.id }');" id="cancel-btn-${entity.id }" style="display:none"><img src="<c:url value="/images/sidebar/cancel_btn.png"/>" alt="Cancel" /></a>
	</div>
</div>
<div id="reportingPeriod-validation-errors-popup" style="display:none" >
		<chrome:box title="Validation Errors">
			<table width="100%" height="100%">
				<tr>
					<td align="center" style="vertical-align:middle">
						<table id="validation-table" width="100%" height="100%">
						</table>
					</td>
				</tr>
			</table>
		</chrome:box>
	</div>

<script language="JavaScript1.2">
	 
	function addComment(entityId){
		routingHelper.addComment(entityId);
	}
	
	function editComment(id, entityId){
		routingHelper.enableEditMode(id, entityId);
	}
	
	function cancelEdit(entityId){
		routingHelper.disableEditMode(entityId);
	}
	
	function saveEditedComment(entityId){
		routingHelper.editComment(entityId);
	}
	function collapseAllComments(entityId){
		routingHelper.collapseAllComments(entityId);
	}

	function expandAllComments(entityId){
		routingHelper.expandAllComments(entityId);
	}
	
	function deleteComment(id, entityId){
		routingHelper.deleteComment(id, entityId);
	}
	
</script>

