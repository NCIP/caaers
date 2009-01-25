<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="domainObjectType" required="true" %>


<script language="JavaScript1.2">
	//document.observe('dom:loaded',function(){
	var edit_comment_id = 0;
	Event.observe(window, "load", function(){		
	//example 1
	var scrollbar = new Control.ScrollBar('scrollbar_content','scrollbar_track');
	
	});	
	
</script>

<div>
	<div id="collapse-all-comments">
		<a href=""><img src="/caaers/images/b-plus.gif" alt="" /> Expand All</a>
		<a href=""><img src="/caaers/images/b-minus.gif" alt="" /> Collapse All</a>
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
		<a href="javascript:addComment();" id="add-btn"><img src="/caaers/images/sidebar/add_btn.png" alt="Add" /></a>
		<a href="javascript:saveEditedComment();" id="edit-btn" style="display:none"><img src="/caaers/images/sidebar/add_btn.png" alt="Edit" /></a>
		<a href="javascript:cancelEdit();" id="cancel-btn" style="display:none"><img src="/caaers/images/sidebar/add_btn.png" alt="Cancel" /></a>
	</div>
</div>

<script language="JavaScript1.2">
	 
	
	function addComment(){
		captureAE.addReviewComment(document.getElementById('enter-comment-text').value, function(ajaxOutput){
						document.getElementById('scrollbar_content').innerHTML = "";
						document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
						document.getElementById('enter-comment-text'). value = "";
					}) ;
		//routingAndReview.addComment('${domainObjectType}', getWorkflowEntityId(), '${command.participant.id}', 
		//			document.getElementById('enter-comment-text').value, function(ajaxOutput){
		//				document.getElementById('scrollbar_content').innerHTML = "";
		//				document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
		//				document.getElementById('enter-comment-text'). value = "";
		//			}) ;
	}
	
	function editComment(id){
		// First of all set the variable edit_comment_id with the correct id of the comment
		edit_comment_id = id;
		
		// Set the text-area with the contents of the comment to be edited.
		document.getElementById('enter-comment-text').value = document.getElementById('userComment-' + id).innerHTML;
		
		// Hide the "Add a Comment" label and display "Edit Comment" label
		document.getElementById('add-a-comment').style.display='none';
		document.getElementById('edit-a-comment').style.display='';
		
		// Hide the 'Add' button and enable 'Cancel' and 'Edit' buttons
		document.getElementById('add-btn').style.display='none';
		document.getElementById('cancel-btn').style.display='';
		document.getElementById('edit-btn').style.display='';
	}
	
	function cancelEdit(){
		// First of all clear the value of the edit_comment_id variable
		edit_comment_id = 0;
		
		// clear the contents of the text-area
		document.getElementById('enter-comment-text').value = '';
		
		// Replace the 'Edit Comment' label with 'Add a Comment' label
		document.getElementById('edit-a-comment').style.display='none';
		document.getElementById('add-a-comment').style.display='';
		
		// Hide the 'Edit' and 'Cancel' buttons. Enable the 'Add' button
		document.getElementById('cancel-btn').style.display='none';
		document.getElementById('edit-btn').style.display='none';
		document.getElementById('add-btn').style.display='';
	}
	
	function saveEditedComment(){
		
		captureAE.editReviewComment(document.getElementById('enter-comment-text').value, edit_comment_id, function(ajaxOutput){
						document.getElementById('scrollbar_content').innerHTML = "";
						document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
						document.getElementById('enter-comment-text'). value = "";
						// Call cancelEdit() to adjust the UI back to "add comments" mode.
						cancelEdit();
					}) ;
		//routingAndReview.editComment('domainObjectType', getWorkflowEntityId(), '${command.participant.id}', 
		//			document.getElementById('enter-comment-text').value, edit_comment_id, function(ajaxOutput){
		//				document.getElementById('scrollbar_content').innerHTML = "";
		//				document.getElementById('scrollbar_content').innerHTML = ajaxOutput;
		//				document.getElementById('enter-comment-text'). value = "";
		//				// Call cancelEdit() to adjust the UI back to "add comments" mode.
		//				cancelEdit();
		//			}) ;
	}
	
	function getWorkflowEntityId(){
		if('${domainObjectType}' == 'reportingPeriod')
			return document.getElementById('adverseEventReportingPeriod').value;
	}
</script>