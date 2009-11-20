var RoutingAndReviewHelper = Class.create();
Object.extend(RoutingAndReviewHelper.prototype, {
	
	initialize : function(aFacade, workflowType){
		this.ajaxFacade = aFacade;
		this.workflowType = workflowType;
	},
	enableEditMode : function(id, entityId){
		// First of all set the variable edit_comment_id with the correct id of the comment
		var editCommentId = 'edit_comment_id-' + entityId;
		$(editCommentId).value = id;
		
		// Set the text-area with the contents of the comment to be edited.
		var commentTextAreaId = 'enter-comment-text-' + entityId;
		$(commentTextAreaId).value = $('userComment-' + id).innerHTML;
		
		// Hide the "Add a Comment" label and display "Edit Comment" label
		var addCommentId = 'add-a-comment-' + entityId;
		var editCommentId = 'edit-a-comment-' + entityId;
		$(addCommentId).hide();
		$(editCommentId).show();
		
		// Hide the 'Add' button and enable 'Cancel' and 'Edit' buttons
		var addButtonId = 'add-btn-' + entityId;
		var editButtonId = 'edit-btn-' + entityId;
		var cancelButtonId = 'cancel-btn-' + entityId;
		$(addButtonId).hide();
		$(cancelButtonId).show();
		$(editButtonId).show();
	},
	disableEditMode : function(entityId){
		// First of all set the variable edit_comment_id with the correct id of the comment
		var editCommentId = 'edit_comment_id-' + entityId;
		$(editCommentId).value = '';
		
		// Set the text-area with the contents of the comment to be edited.
		var commentTextAreaId = 'enter-comment-text-' + entityId;
		$(commentTextAreaId).value = '';
		
		// show the "Add a Comment" label and display "Edit Comment" label
		var addCommentId = 'add-a-comment-' + entityId;
		var editCommentId = 'edit-a-comment-' + entityId;
		$(addCommentId).show();
		$(editCommentId).hide();
		
		// show the 'Add' button and enable 'Cancel' and 'Edit' buttons
		var addButtonId = 'add-btn-' + entityId;
		var editButtonId = 'edit-btn-' + entityId;
		var cancelButtonId = 'cancel-btn-' + entityId;
		$(addButtonId).show();
		$(cancelButtonId).hide();
		$(editButtonId).hide();
	},
	updateCommentElementContent: function(content, entityId){
		var commentContentId = 'scrollbar_content-' + entityId;
		$(commentContentId).innerHTML = "";
		$(commentContentId).innerHTML = content;
		var commentTextAreaId = 'enter-comment-text-' + entityId;
		$(commentTextAreaId).value = "";
	},
	addComment:function(entityId){
		var commentTextAreaId = 'enter-comment-text-' + entityId;
		this.ajaxFacade.addReviewComment($(commentTextAreaId).value, entityId, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent, entityId);
		}.bind(this));
	},
	editComment:function(entityId){
		var commentTextAreaId = 'enter-comment-text-' + entityId;
		var editCommentId = 'edit_comment_id-' + entityId;
		this.ajaxFacade.editReviewComment($(commentTextAreaId).value, $(editCommentId).value, entityId, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent, entityId);
			this.disableEditMode(entityId);
		}.bind(this));
	},
	deleteComment:function(id, entityId){
		this.ajaxFacade.deleteReviewComment(id, entityId, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent, entityId);
			this.disableEditMode(entityId);
		}.bind(this));
	},
	retrieveReviewComments : function(entityId){
		this.ajaxFacade.retrieveReviewComments(function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent, entityId);
			}.bind(this)) ;
		$('entire-slider').show();
	},
	retrieveReviewCommentsAndActions : function(entityId){
		try{
			this.ajaxFacade.retrieveReviewCommentsAndActions(entityId,function(ajaxOutput){
				this.updateCommentElementContent(ajaxOutput.htmlContent, entityId);
				if(this.workflowType == 'reviewAeReport'){
					var sbox = $('sliderWFAction');
					var sboxIndicator = $('sliderWFAction-indicator');
					this.updateSelectBoxContent(entityId, sbox, sboxIndicator, ajaxOutput.objectContent);
				}
			}.bind(this)) ;
			$('entire-slider').show();
		}catch(err){
			caaersLog(err);
		}
		
	},
	updateWorkflowActions : function(reportId){
		var sboxId = 'sliderWFAction-' + reportId;
		var sbox = $(sboxId);
		var sboxIndicatorId = 'sliderWFAction-indicator-' + reportId;
		var sboxIndicator = $(sboxIndicatorId);

		sboxIndicator.style.display='';
		this.ajaxFacade.retrieveNextTransitions(reportId, function(ajaxOutput){
			this.updateSelectBoxContent(reportId, sbox, sboxIndicator, ajaxOutput.objectContent);
		}.bind(this));
	},
	updateSelectBoxContent : function(entityId , sb, sbIndicator, objectContent){
		if(objectContent){
			sb.innerHTML = '';
			var i = 0;
			for(i = 0; i< objectContent.length; i++){
				var status = objectContent[i];
				var li = new Element('li');
				var opt = new Element('a', {'onclick': 'javascript:advanceWorkflow("'+ entityId + '","'+status+'")', 'href' : '#', 'style' : 'z-index=1'}).update(status);
				li.insert(opt);
				sb.insert(li);
			}
		}
		sbIndicator.style.display='none';
		if (typeof createDropDowns == 'function') {
			createDropDowns();
		}
	},
	validateAndAdvanceWorkflow: function(reportId){
		var sbox = $('sliderWFAction');
		if(sbox.value == '') return;
		
		this.ajaxFacade.validateAndAdvanceWorkflow(sbox.value, reportId, function(ajaxOutput){
			if(ajaxOutput.objectContent){
				var i = 0;
				var popupContent = '';
				for(i = 0; i< ajaxOutput.objectContent.length; i++){
					var error = ajaxOutput.objectContent[i];
					popupContent = popupContent + '<tr><td width="10%"/><td width="80%" align="left">' + error + '</td><td width="10%"/></tr>';
				}
				popupContent = popupContent + '<tr><tr><tr><td width="10%"/><td align="left" width="80%"><font color="red">Note: Please save if you have unsaved data.</font></td><td width="10%"/></tr>';
				$('validation-table').innerHTML = popupContent;
				var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"validation-popup-id", width:500,  height:230, top: 330, left: 500});
        		contentWin.setContent( 'reportingPeriod-validation-errors-popup' );
        		contentWin.showCenter(true);
        		contentWin.setZIndex(1000);
        			popupObserver = {
 		     			onDestroy: function(eventName, win) {
      						if (win == contentWin) {
      							$('reportingPeriod-validation-errors-popup').style.display='none';
		      					contentWin = null;
      							Windows.removeObserver(this);
      							sbox.selectedIndex = 0;
      						}
      					}
      				}
			        Windows.addObserver(popupObserver);
			}else{
				this.advanceWorkflow();
			}
		}.bind(this));
	},
	advanceWorkflow: function(entityId, value){
		var sbox = $('sliderWFAction');
		if(value == '' || value == 'Please Select') return;
		if(confirm('Are you sure you want to take the action - ' + value)){
			var sboxIndicator = $('sliderWFAction-indicator');		
			//sbox.disable();
			sboxIndicator.style.display='';
			this.ajaxFacade.advanceWorkflow(entityId, value, function(ajaxOutput){
				this.updateSelectBoxContent(entityId, sbox, sboxIndicator, ajaxOutput.objectContent);
				this.retrieveReviewComments(entityId);
			}.bind(this));
		}else{
			return false;
		}
	},
	collapseAllComments : function(entityId){
		var commentsSectionId = 'scrollbar_content-' + entityId;
		var list = $(commentsSectionId).select('div.division');
		$A(list).each(function(el){
			 var _id = el.id;
		     var panelDiv = $("contentOf-" + _id);
		     var imageId= 'image-' + _id;
		     var img = $(imageId)
		     var imageSource = img.src;
		        
		     CloseDown(panelDiv, arguments[1] || {});
		     img.src = imageSource.replace('down','right');
		});
		
	},
	expandAllComments : function(entityId){
		var commentsSectionId = 'scrollbar_content-' + entityId;
		var list = $(commentsSectionId).select('div.division');
		$A(list).each(function(el){
			var _id = el.id;
		     var panelDiv = $("contentOf-" + _id);
		     var imageId= 'image-' + _id;
		     var img = $(imageId)
		     var imageSource = img.src;
		        
		     OpenUp(panelDiv, arguments[1] || {});
		     img.src = imageSource.replace('right','down');
		});
		
	}
	
});