var AdvancedSearchHelper = Class.create();
Object.extend(AdvancedSearchHelper.prototype, {

	initialize : function(aFacade){
		this.ajaxFacade = aFacade;
	},
	
	updateSearchTargetObject: function(){
		$('criteria-section-id').style.display = 'none';
		$('targetObjectProgessIndicator').style.display = '';
		this.ajaxFacade.updateSearchTargetObject($('target-object-id').value , function(ajaxOutput) {
			$('criteria-section-id').innerHTML = ajaxOutput.htmlContent;
			//new Insertion.Top($('criteria-section-id'), ajaxOutput.htmlContent);
			$('criteria-section-id').style.display='';
			$('targetObjectProgessIndicator').style.display = 'none';
		}.bind(this));
	},
	
	updateAttribute: function(index){
		var attributeId = 'attribute-' + index;
		var attributeValue = $(attributeId).value;
		var operatorSelectId = 'operator-' + index;
		var selectElement = $(operatorSelectId);
		var valueTdId = 'value-td-' + index;
		selectElement.options.length = 0;
		selectElement.options[0] = new Option('Please select', 'none');
			
			
		
		// This is if the value is set to 'Please select'
		if(attributeValue == 'none'){
			$(valueTdId).innerHTML = '';		
		}else{
			this.ajaxFacade.updateAttribute(attributeValue, index, function(ajaxOutput){
				// here we have to update the dropdown options of the respective operator dropdown with the the values in ajaxOutput
				// and the html content of the value td should be updated with the htmlContent of ajaxOutput
				for(i = 0; i< ajaxOutput.objectContent.length; i++){
					var operator = ajaxOutput.objectContent[i];
					selectElement.options[i+1] = new Option(operator.displayUri, operator.name);
				}
				//$(valueTdId).innerHTML = ajaxOutput.htmlContent;
				new Insertion.Top($(valueTdId), ajaxOutput.htmlContent);
			}.bind(this));
		}
	},
	
	updateOperator: function(index){
		//alert('update operator');
	},
	
	addCriteria: function(dependentObjectDisplayName){
		this.ajaxFacade.addCriteria(dependentObjectDisplayName, function(ajaxOutput){
			var blankRowId = dependentObjectDisplayName + '-blank-row';
			$(blankRowId).insert({before: ajaxOutput.htmlContent});
		});	
	},
	
	deleteCriteria: function(index){
		this.ajaxFacade.deleteCriteria(index, function(ajaxOutput){
			var rowId = 'criteria-' + index;
			$(rowId).style.display = 'none';
		});
	},
	
	saveSearch: function(){
		if($('searchName').value == '')
			alert('Search name is required');
		else{
			var searchName = $('searchName').value;
			var searchDescription = $('searchDescription').value;
			alert('searchName = ' + searchName);
			alert('searchDescription = ' + searchDescription);
			advSearch.saveSearch(searchName, searchDescription, function(ajaxOutput){
				window.parent.Windows.close('save-popup-id');
				alert('Search saved successfully');
				//$('save-popup-id').destroy();
			});
		} 
	},
	
	deleteSavedSearch: function(searchName){
		if(confirm('Are you sure you want to delete the search - ' + searchName)){
			advSearch.deleteSearch(searchName, function(ajaxOutput){
				alert('Search deleted successfully');
				$('shortSavedSearchListTable').innerHTML = ajaxOutput.htmlContent;
			});
		}else
			return;
	},
	
	renderFullSearchList: function(){
		var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"search-list-popup-id", width:500,  height:330, top: 150, left: 400});
        contentWin.setContent( 'search_list_popup' );
        contentWin.showCenter(true);
        popupObserver = {
      			onDestroy: function(eventName, win) {
      				if (win == contentWin) {
      					$('search_list_popup').style.display='none';
      					
      					contentWin = null;
      					Windows.removeObserver(this);
      				}
      			}
      		}
        Windows.addObserver(popupObserver);
	},
	
	renderSaveSearchPopup: function(){
		var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"save-popup-id", width:500,  height:330, top: 150, left: 400});
        contentWin.setContent( 'save_search_popup' );
        contentWin.showCenter(true);
        popupObserver = {
      			onDestroy: function(eventName, win) {
      				if (win == contentWin) {
      					$('save_search_popup').style.display='none';
      					
      					contentWin = null;
      					Windows.removeObserver(this);
      				}
      			}
      		}
        Windows.addObserver(popupObserver);
	
	},

});