var RoutingAndReviewHelper = Class.create();
Object.extend(RoutingAndReviewHelper.prototype, {
	
	initialize : function(aFacade, workflowType){
		this.ajaxFacade = aFacade;
		this.workflowType = workflowType;
	},
	enableEditMode : function(id){
		// First of all set the variable edit_comment_id with the correct id of the comment
		$('edit_comment_id').value = id;
		
		// Set the text-area with the contents of the comment to be edited.
		$('enter-comment-text').value = $('userComment-' + id).innerHTML;
		
		// Hide the "Add a Comment" label and display "Edit Comment" label
		$('add-a-comment').hide();
		$('edit-a-comment').show();
		
		// Hide the 'Add' button and enable 'Cancel' and 'Edit' buttons
		$('add-btn').hide();
		$('cancel-btn').show();
		$('edit-btn').show();
	},
	disableEditMode : function(){
		// First of all set the variable edit_comment_id with the correct id of the comment
		$('edit_comment_id').value = '';
		
		// Set the text-area with the contents of the comment to be edited.
		$('enter-comment-text').value = '';
		
		// show the "Add a Comment" label and display "Edit Comment" label
		$('add-a-comment').show();
		$('edit-a-comment').hide();
		
		// show the 'Add' button and enable 'Cancel' and 'Edit' buttons
		$('add-btn').show();
		$('cancel-btn').hide();
		$('edit-btn').hide();
	},
	updateCommentElementContent: function(content){
		$('scrollbar_content').innerHTML = "";
		$('scrollbar_content').innerHTML = content;
		$('enter-comment-text').value = "";
	},
	addComment:function(){
		this.ajaxFacade.addReviewComment($('enter-comment-text').value, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent);
		}.bind(this));
	},
	editComment:function(){
		this.ajaxFacade.editReviewComment($('enter-comment-text').value, $('edit_comment_id').value, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent);
			this.disableEditMode();
		}.bind(this));
	},
	deleteComment:function(id){
		this.ajaxFacade.deleteReviewComment(id, function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent);
			this.disableEditMode();
		}.bind(this));
	},
	retrieveReviewComments : function(){
		this.ajaxFacade.retrieveReviewComments(function(ajaxOutput){
			this.updateCommentElementContent(ajaxOutput.htmlContent);
			}.bind(this)) ;
		$('entire-slider').show();
	},
	retrieveReviewCommentsAndActions : function(){
		try{
			this.ajaxFacade.retrieveReviewCommentsAndActions(function(ajaxOutput){
				this.updateCommentElementContent(ajaxOutput.htmlContent);
				if(this.workflowType == 'reportingPeriod' || this.workflowType == 'reviewAeReport'){
					var sbox = $('sliderWFAction');
					var sboxIndicator = $('sliderWFAction-indicator');
					this.updateSelectBoxContent(sbox, sboxIndicator, ajaxOutput.objectContent);
				}
			}.bind(this)) ;
			$('entire-slider').show();
		}catch(err){
			caaersLog(err);
		}
		
	},
	updateWorkflowActions : function(){
		var sbox = $('sliderWFAction');
		var sboxIndicator = $('sliderWFAction-indicator');

		sboxIndicator.style.display='';
		this.ajaxFacade.retrieveNextTransitions(function(ajaxOutput){
			this.updateSelectBoxContent(sbox, sboxIndicator, ajaxOutput.objectContent);
		}.bind(this));
	},
	updateSelectBoxContent : function(sb, sbIndicator, objectContent){
		if(objectContent){
			sb.innerHTML = '';
			var i = 0;
			for(i = 0; i< objectContent.length; i++){
				var status = objectContent[i];
				var li = new Element('li');
				var opt = new Element('a', {'onclick': 'javascript:advanceWorkflow("'+status+'")', 'href' : '#'}).update(status);
				li.insert(opt);
				sb.insert(li);
			}
		}
	
		sbIndicator.style.display='none';
		if (typeof createDropDowns == 'function') {
			createDropDowns();
		}
	},
	validateAndAdvanceWorkflow: function(){
		var sbox = $('sliderWFAction');
		if(sbox.value == '') return;
		
		this.ajaxFacade.validateAndAdvanceWorkflow(sbox.value, function(ajaxOutput){
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
	advanceWorkflow: function(){
		var sbox = $('sliderWFAction');
		if(sbox.value == '' || sbox.value == 'Please Select') return;
		if(confirm('Are you sure you want to take the action - ' + sbox.value)){
			var sboxIndicator = $('sliderWFAction-indicator');		
			sbox.disable();
			sboxIndicator.style.display='';
			this.ajaxFacade.advanceWorkflow(sbox.value, function(ajaxOutput){
				this.updateSelectBoxContent(sbox, sboxIndicator, ajaxOutput.objectContent);
				this.retrieveReviewComments();
			}.bind(this));
		}else{
			return false;
		}
	},
	collapseAllComments : function(){
		var list = $('comments-id').select('div.division');
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
	expandAllComments : function(){
		var list = $('comments-id').select('div.division');
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