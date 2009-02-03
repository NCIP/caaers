<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="domainObjectType" required="true" %>
<div>
	<div id="collapse-all-comments">
		<a name="allBtnCtrl"></a>
		<a href="#allBtnCtrl" onClick="javascript:expandAllComments();" ><img src="<c:url value="/images/b-plus.gif"/>" alt=""  /> Expand All</a>
		<a href="#allBtnCtrl"  onClick="javascript:collapseAllComments();"><img src="<c:url value="/images/b-minus.gif"/>" alt=""/> Collapse All</a>
	</div>
	<div id="comments-window">
		 <div id="scrollbar_container">  
			<div id="scrollbar_track"><div id="scrollbar_handle"></div></div>  
			<div id="scrollbar_content">
			</div>  
		</div>
	</div>	
	<div id="right-panel">
		<div id="add-a-comment">Add a Comment</div>
		<div id="edit-a-comment" style="display:none">Edit Comment</div>
		<textarea id="enter-comment-text"></textarea>
		<input type="hidden" id="edit_comment_id" name="edit_comment_id" value="" />
		<a href="javascript:addComment();" id="add-btn">
		<img src="<c:url value="/images/sidebar/add_btn.png"/>" alt="Add" /></a>
		<a href="javascript:saveEditedComment();" id="edit-btn" style="display:none"><img src="<c:url value="/images/sidebar/edit_btn.png"/>" alt="Edit" /></a>
		<a href="javascript:cancelEdit();" id="cancel-btn" style="display:none"><img src="<c:url value="/images/sidebar/cancel_btn.png"/>" alt="Cancel" /></a>
		<div id="wf-action-div">
			<div id="wf-action-label">
			Next Action
			</div>
			<div id="wf-action-value">
			&nbsp;&nbsp;
			<select id="sliderWFAction" onChange="routingHelper.advanceWorkflow();">
				<option value="">Please select</option>
			</select>
			<img id="sliderWFAction-indicator" src="<c:url value="/images/indicator.white.gif"/>" alt="activity indicator" style="display:none;"/>
			</div>
			 
			
		</div>
	</div>
</div>

<script language="JavaScript1.2">
	 
	
	function addComment(){
		routingHelper.addComment();
	}
	
	function editComment(id){
		routingHelper.enableEditMode(id);
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
	
	function getWorkflowEntityId(){
		if('${domainObjectType}' == 'reportingPeriod')
			return document.getElementById('adverseEventReportingPeriod').value;
	}
</script>