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
				$(valueTdId).innerHTML = ajaxOutput.htmlContent;
			}.bind(this));
		}
	},
	
	updateOperator: function(index){
		//alert('update operator');
	},
	
	addCriteria: function(dependentObjectDisplayName){
		alert('addCriteria');
		this.ajaxFacade.addCriteria(dependentObjectDisplayName, function(ajaxOutput){
			alert('returned from the ajax method');
			var blankRowId = dependentObjectDisplayName + '-blank-row';
			alert('blankRowId = ' + blankRowId);
			alert('htmlContent =' + ajaxOutput.htmlContent);
			$(blankRowId).insert({before: ajaxOutput.htmlContent});
			alert('done inserting row');
		});	
	},
	
	deleteCriteria: function(index){
		this.ajaxFacade.deleteCriteria(index, function(ajaxOutput){
			var rowId = 'criteria-' + index;
			$(rowId).style.display = 'none';
		});
	}


});