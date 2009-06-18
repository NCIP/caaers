<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<page:applyDecorator name="standardNoHeader">
<html>
<head>
    <title>caAERS || Review Comments Listing</title>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:dwrJavascriptLink objects="createStudy"/>
	<style type="text/css">
		#main{
			text-align: left;
		}
		.cmt {
			margin-left: 10px;
		}
		.standard-buttons{
			text-align: center;
		}
	</style>
	<script>
		function saveComment(){
			var form = document.getElementById('command');
			form.action.value = 'addPopupComment';
			form.submit();
		}
		function editComment(){
			var form = document.getElementById('command');
			form.action.value = 'editPopupComment';
			form.submit();
		}
		function deleteComment(commentId){
			var form = document.getElementById('command');
			form.action.value = 'deletePopupComment';
			var elementId = 'userComment-' + commentId;
			form.commentId.value = commentId;
			form.comment.value = $(elementId).innerHTML;
			form.submit();
		}
		function setupEditComment(commentId){
			$('edit-button').style.display = '';
			$('save-button').style.display = 'none';
			var elementId = 'userComment-' + commentId;
			var form = document.getElementById('command');
			form.commentId.value = commentId;
			form.comment.value = $(elementId).innerHTML; 
		}
		function clear(){
			$('edit-button').style.display = 'none';
			$('save-button').style.display = '';
			var form = document.getElementById('command');
			form.comment.value = '';
		}
	</script>
</head>
<body>
	<tags:standardForm title="Comments">
		<jsp:attribute name="instructions">
			<tags:instructions code="instruction_wf_review_comments_listing"/>
			<input type="hidden" name="action" value="">
			<input type="hidden" name="commentId" path="commentId" value="">
		</jsp:attribute>
		<jsp:attribute name="singleFields">
			<ui:row path="comment">
				<jsp:attribute name="label">New comment</jsp:attribute>
				<jsp:attribute name="value">
					<ui:textarea path="comment" rows="6" cols="90" required="true">
					</ui:textarea>
				</jsp:attribute>
			</ui:row>
			<div class="content standard-buttons autoclear">
					<div class="standard-nav-buttons">
						<c:set var="editImage"><c:url value="/images/edit.png"/></c:set>
						<c:set var="editButton"><c:url value="/images/blue/edit.png"/></c:set>
						<c:set var="deleteImage"><c:url value="/images/checkno.gif"/></c:set>
						<c:set var="saveButton"><c:url value="/images/blue/save_btn.png"/></c:set>
						<c:set var="clearButton"><c:url value="/images/blue/cancel.png"/></c:set>
						
						<a href="javascript:clear()" id="clear-button">
        					<img src="${clearButton }"  alt="Cancel" title="Cancel" style="border:0"/>
        				</a>&nbsp;&nbsp;
						<a href="javascript:saveComment()" id="save-button">
        					<img src="${saveButton }"  alt="Save" title="Save" style="border:0"/>
        				</a>
        				<a href="javascript:editComment()" id="edit-button" style="display:none">
        					<img src="${editButton }"  alt="Edit" title="Edit" style="border:0;display='none"/>
        				</a>
						<%--
						<input value="Clear" onClick="javascript:clear();"/>&nbsp;&nbsp;  
						<input value="Save" id="save-button" onClick="javascript:saveComment();"/> 
						<input value="Edit" id="edit-button" onClick="javascript:editComment();" style="display:none"/>
						 --%>
					</div>
			</div>
			<div class="eXtremeTable" >
				<c:forEach items="${command.previousComments}" var="comment" varStatus="cIndex">
					<div class="${cIndex.index % 2 gt 0 ? 'odd' : 'even' } autoclear cmt" >
						<c:set var="dt"><tags:formatTimeStamp value="${comment.createdDate}" /></c:set>
						<%-- <chrome:division title='${comment.autoGeneratedText } on ${dt }' collapsable="true" id="cmt${cIndex.index}" collapsed="${cIndex.index gt 0}">
						<pre>${comment.userComment}</pre>
						</chrome:division> --%>
					
						<c:choose>
							<c:when test="${comment.editable == true and fn:contains(comment.userId, command.userId)}">
								<chrome:division title='${comment.autoGeneratedText } on ${dt } <a href="javascript:setupEditComment(${comment.id})">(<img src="${editImage}" alt="edit" />edit)</a>
									<a href="javascript:deleteComment(${comment.id})"  alt="delete"><img src="${deleteImage}" /></a>' 
									collapsable="true" id="cmt${cIndex.index}" collapsed="${cIndex.index lt noOfComments}">
									<pre id="userComment-${comment.id}">${comment.userComment}</pre>
								</chrome:division>
							</c:when>
							<c:otherwise>
								<chrome:division title='${comment.autoGeneratedText } on ${dt }' collapsable="true" id="cmt${cIndex.index}" collapsed="${cIndex.index lt noOfComments}">
									<pre id="userComment-${comment.id}">${comment.userComment}</pre>
								</chrome:division>
							</c:otherwise>
						</c:choose>
					
						
					</div>
				</c:forEach>
			</div>
		</jsp:attribute>
	</tags:standardForm>

</body>
</html>
</page:applyDecorator>  