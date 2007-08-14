<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<tags:stylesheetLink name="ae" />
<tags:includeScriptaculous />
<tags:dwrJavascriptLink objects="authorRule" />
<tags:dwrJavascriptLink objects="createAE" />

<script type="text/javascript">
      
      Event.observe(window, "load", function() {
				destroyLineItemSortables();
				createLineItemSortables();
				createRuleSortable();
				//Event.observe("add-condition-image", "click", function() { fetchCondition() } );
			});
			
			function createLineItemSortables() {
				for(var i = 0; i < sections.length; i++) {
					Sortable.create(sections[i],{tag:'div',dropOnEmpty: true, containment: sections,only:'lineitem'});
				}
			}

			function destroyLineItemSortables() {
				for(var i = 0; i < sections.length; i++) {
					Sortable.destroy(sections[i]);
				}
			}

			function createRuleSortable() {
				Sortable.create('allRules', {tag:'div',only:'section',handle:'handle'});
			}
			
	</script>

<title>Specify Rules for Trigger</title>

<style>


	div.section,div#createNew {
		border: 1px solid #CCCCCC;
		margin: 30px 5px;
		padding: 0px 0px 10px 0px;
		background-color: #EFEFEF;
	}

	div#createNew input { margin-left: 5px; }

	div#createNew h3, div.section h3{
		font-size: 14px;
		padding: 2px 5px;
		margin: 0 0 10px 0;

		background: url("/caaers/images/rule/window_titlebar.png");

		background-color: #6E81A6;
		display: block;
		color: #FFFFFF;
	}


</style>

<script type="text/javascript">

		var sections = new Array();
		var callback = false;
		var newNode = 0;
		var domainObject = null;
		
		function addRule() {
				
				var organization = '';
				
				//alert ('${command.level}');
				if ('${command.level}' == 'Sponsor' || '${command.level}' == 'SponsorDefinedStudy') {
					organization = '${command.sponsorName}';
				} else {				
					organization = '${command.institutionName}';
				}
				
				//alert (organization);
				
				try {
					
					authorRule.addRule(name, organization, function (html) {
						sections.push('rule-' + (sections.length + 1));
						var columnHolder = getElementHolderDiv();
						columnHolder.innerHTML = html;
						var newRule = columnHolder.childNodes[1].cloneNode(true);
						columnHolder.innerHTML = "";
						$('allRules').appendChild(newRule);
						Effect.Appear(newRule.id);
						createRuleSortable();
					});

				} catch(e) {alert(e)}
		}


		function fetchCondition(ruleCount) {
				 
				try {
					authorRule.addCondition(ruleCount, function(columnContent) {

							
							var columns = $('rule-'+(ruleCount + 1)+'-columns');
							var columnHolder = getElementHolderDiv();
							columnHolder.innerHTML = columnContent;
							var newColumn = columnHolder.childNodes[1].cloneNode(true);	
							
							columnHolder.innerHTML = "";
							columns.appendChild(newColumn);
							var brElement = document.createElement("br");
							brElement.setAttribute("id", newColumn.id + '-br');
							columns.appendChild(brElement);
							Effect.Appear(newColumn.id);
							
							if (callback == true)
							{
							
								var domainObjectDropDownID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].objectType'; 
								var domainObjectIdentifierID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].identifier'; 
								var domainObjectDisplayUriID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].displayUri'; 
							
			                        $(domainObjectDropDownID).value=domainObject.className;
						      		$(domainObjectIdentifierID).value=domainObject.identifier;
						      		$(domainObjectDisplayUriID).value=domainObject.displayUri;
	
							
								var newColumnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].fieldConstraint[0].fieldName';
								var expressionID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].expression';
								var grammerPrefixID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].fieldConstraint[0].grammerPrefix';
								
								$(grammerPrefixID).value=domainObject.field[0].grammer.prefix;
								
								
								//alert(newColumnId);

				 				$(newColumnId).options.length = 0;
				 				$(newColumnId).options.add(new Option("Please select Field", ""));
								
								// Set all the options
								domainObject.field.each(function(field)
								 		{
							 				$(newColumnId).options.add(new Option(field.displayUri, field.name));
										});
								
								$(newColumnId).value='term';
								
								var selectId =  newColumnId.substring(0,newColumnId.lastIndexOf(".")); 
								
								// Reset the operator
								
								domainObject.field.each(function(field)
										{
											if(field.name == 'term')
											{
												var operatorDropDownID = selectId + '.literalRestriction[0].evaluator';
												
												$(operatorDropDownID).options.length = 0;
												$(operatorDropDownID).options.add(new Option("Please select Operator",""));

												field.operator.each(function(operator)
													{
														$(operatorDropDownID).options.add(new Option(operator.displayUri,operator.name));														
														
													});
													
												$(expressionID).value = field.expression;	
											}
										});
										
								var validValueField = document.getElementById(selectId + '.literalRestriction[0].value');

								var newId = validValueField.id; 
								var spanId = newId + '.span';
								//Element.remove(validValueField);
								$(spanId).innerHTML="";
								
								createAE.getTermsByCategory(0, function(terms) {
						                   
										
							
										var selectArea = '<select id="' + newId + '" name="' + newId +'" multiple="multiple" size="3">';
													selectArea += '</select>';
										//			var selectArea = '<select id="' + newId + '" name="' + newId +  '" value="' + fieldValue + '" onchange="onCategoryChange(this, ${ruleCount})">';
										//									selectArea += '</select>';
							
										$(spanId).innerHTML = selectArea;

										var sel = $(newId);	
				        		        
				                	    sel.options.length = 0
				                    
				                    
				                    	terms.each(function(term) {
											var tempT='';
											if (term.select != null) {
												tempT='-' + term.select
											}
				                        	var opt = new Option(term.term + tempT, term.id)
				                        	sel.options.add(opt)
				                    	})
				                })
								
								
								/*				
								var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="60"/>';
								inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="term-indicator"/>';
								$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';
								
								
								new Autocompleter.DWR(newId, newId + '-choices',
									                termPopulator, {
									                valueSelector: termValueSelector,
									                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									
												//alert(selectedChoice);
												$(newId).value=termValueSelector(selectedChoice);
									                },
						                indicator: "term-indicator"});
								*/
								
													                

							}
							callback=false;
							
					});
				
				}catch (e) {
					//alert(e)
				}
		}
		
		function removeCondition(ruleCount, columnCount) {
				
				try {
					authorRule.removeCondition(ruleCount, columnCount, function(deleteStatus) {
							if(deleteStatus) {
								var columns = $('rule-'+(ruleCount+1)+'-columns');
								var column = $('rule-'+(ruleCount)+'-column-'+(columnCount));
								column.style.visibility = "hidden";
								
								//columns.removeChild($(column.id + '-br'));
								//columns.removeChild(column);
								
							} else {
								alert("Delete failed on server " + values)
							}
					});
				
				}catch (e) {
					alert("Exception " + e)
				}	
					
		}
		
		function deleteRule(ruleCount) {
				
				
				try {
					authorRule.removeRule(ruleCount-1, function(deleteStatus) {
							if(deleteStatus) {
									toggle(ruleCount);
									var rules = $('allRules');
									var rule = $('rule-'+ruleCount);
									
									rule.style.visibility="hidden";
									//alert ("ss");
									//rules.removeChild(rule);
									

								
							} else {
								alert("Delete failed on server " + values)
							}
					});
				
				}catch (e) {
					alert("Exception " + e)
				}
				
				
					
		
		}
		
		function getElementHolderDiv() {
			var elementHolderDiv = $('element-holder');
			if(elementHolderDiv == null) {
				elementHolderDiv = document.createElement("div");
				elementHolderDiv.setAttribute("id", "element-holder");
			}
			
			return elementHolderDiv;
		}
		




	
	var toggleArray = new Array();
	
	function toggle(ruleCount) {
		var toggleStatus = toggleArray[ruleCount];
		var imageObj = $('toggle-image-'+ruleCount);
		if(!toggleStatus) {
			$('rule-condition-action-container-'+ruleCount).style.display="none";
			imageObj.src="/caaers/images/rule/window-maximize.gif"
			toggleArray[ruleCount] = true;
		} else {
			AE.slideAndShow($('rule-condition-action-container-'+ruleCount));
			imageObj.src="/caaers/images/rule/window-minimize.gif"
			toggleArray[ruleCount] = false;
		}
	}

				function orgsPopulator(autocompleter, text) 
				{
						
				
					authorRule.matchSites(text, function(values) {
						                    autocompleter.setChoices(values)
						                })

				}
				
	
	
	
				function termPopulator(autocompleter, text) 
				{
						
					var selectedColumnId = autocompleter.element.id;
					
					var startIndex = selectedColumnId.indexOf('[');
					var endIndex = selectedColumnId.indexOf(']', startIndex);
					
					var ruleCount = parseInt(selectedColumnId.substring(startIndex+1, endIndex));

					// Check whether category exists
					var columns = $('rule-'+(ruleCount + 1)+'-columns');
				
				
					var divNodes = 0;
					
					for(var i=0; i < columns.childNodes.length; i++)
					{
						if (columns.childNodes[i].nodeName == 'DIV')
						{
							divNodes++;
						}
					}
							
					var category; 		

					for (var i=0; i < divNodes; i++)
					{
						var columnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].fieldName';
					
						if($(columnId).value == 'category')
						{
							var selectId =  columnId.substring(0,columnId.lastIndexOf(".")); 
		
							var validValueField = document.getElementById(selectId + '.literalRestriction[0].value');
							
							category = validValueField.value;
						}
					}
					
					

					
					createAE.matchTerms(text, 3, category, 10, function(values) {
						                    autocompleter.setChoices(values)
						                })

				}
	
				function termValueSelector(term) 
				{
					//alert(term.fullName);
					return term.fullName;
				}

	function isFieldExists(ruleCount, domainObjectName, fieldName)
	{

				var columns = $('rule-'+(ruleCount + 1)+'-columns');
				
				var divNodes = 0;
				var sameFields = 0;
				var fieldExist = false;

				// Filter all div nodes				
				for(var i=0; i < columns.childNodes.length; i++)
				{
					if (columns.childNodes[i].nodeName == 'DIV')
					{

						if (columns.childNodes[i].style.visibility != 'hidden') {							
							
							var fieldId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + divNodes + '].fieldConstraint[0].fieldName';
							var domainObjectId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + divNodes + '].objectType';
					
							if($(fieldId).value == fieldName && $(domainObjectId).value == domainObjectName)
							{
								sameFields++;
						
							}
						}
					
							divNodes++;
							
						
					}
				}
			
				if (sameFields > 1)
				{
					fieldExist = true;
				}
				
			return fieldExist;
	}
	
	function resetDropDowns()
	{
	}
	
	function handleValueOnselect(operatorDropDown, ruleCount, fieldIndex, multi)
	{
		
	//	alert (fieldIndex);
		
		var selectedOperator = operatorDropDown.options[operatorDropDown.selectedIndex];
		var selectId =  operatorDropDown.id.substring(0,operatorDropDown.id.lastIndexOf(".")); 
		
		// Get the index of Domain Object
		var domainObjectDropDownID = selectId.substring(0,selectId.lastIndexOf("field")) + 'objectType';
		
		var domainObjectSelectedIndex = $(domainObjectDropDownID).selectedIndex;
		//alert (domainObjectSelectedIndex);
		
		var displayUriID = selectId+ '.readableValue';
		
		var values = "";
		//alert (displayUriID);		
		
		try 
		  {
			// Get the domain object
			
			authorRule.getRulesDomainObject(domainObjectSelectedIndex - 1, function(object)
		               {
		                              		domainObject = object;
		                              		
		                    if (multi) {
		                      for (var i = 0; i < operatorDropDown.options.length; i++) {
		                    
		                    	if (operatorDropDown.options[ i ].selected) {
		                    		values = domainObject.field[fieldIndex].validValue[operatorDropDown.options[i].index].readableText + "," + values
		                    	}
		                      }
		                   } else {
		                   		values = domainObject.field[fieldIndex].validValue[operatorDropDown.selectedIndex-1].readableText
		                   }
		                    
		                    
		                    $(displayUriID).value = values;
						
							
		           })
		  }
		catch(e) 
		  {
			alert('Exception');
			alert(e);
		  }
		
	}
	
	function handleOperatorOnchange(operatorDropDown, ruleCount) {
	
		
		var selectedOperator = operatorDropDown.options[operatorDropDown.selectedIndex];
		var selectId =  operatorDropDown.id.substring(0,operatorDropDown.id.lastIndexOf(".")); 
		
		
		
		// Get the index of Domain Object
		var domainObjectDropDownID = selectId.substring(0,selectId.lastIndexOf("field")) + 'objectType';
		
		
		
		var domainObjectSelectedIndex = $(domainObjectDropDownID).selectedIndex;
		//alert (domainObjectSelectedIndex);
		
		//ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].displayUri
		
		var displayUriID = selectId+ '.displayUri';
		
		//alert (displayUriID);
		
		try 
		{
			// Get the domain object
			
			authorRule.getRulesDomainObject(domainObjectSelectedIndex - 1, function(object)
		                              {
		                              		domainObject = object;
								
						//	alert(domainObject.field[0].operator[operatorDropDown.selectedIndex-1].displayUri);
						
							
							$(displayUriID).value = domainObject.field[0].operator[operatorDropDown.selectedIndex-1].readableText;
							
							//$(fieldDisplayUri).value = domainObject.field[fieldDropDown.selectedIndex-1].displayUri;
							
							
		                              })
		}
		catch(e) 
		{
			alert('Exception');
			alert(e);
		}
		
	}
				function orgValueSelector(organization) 
			{
				return organization.name;
			}
			
	function handleFieldOnchange(fieldDropDown, ruleCount, columnCount) 
	{
		var selectedField = fieldDropDown.options[fieldDropDown.selectedIndex];
		
		var selectId =  fieldDropDown.id.substring(0,fieldDropDown.id.lastIndexOf(".")); 
		
		var validValueField = document.getElementById(selectId + '.literalRestriction[0].value');
		
		
		
		
		// Get the index of Domain Object
		var domainObjectDropDownID = selectId.substring(0,selectId.lastIndexOf("."))  + '.objectType';
	//	alert (domainObjectDropDownID);
		var domainObjectSelectedIndex = $(domainObjectDropDownID).selectedIndex;
		
		var domainObjectIdentifierID = selectId.substring(0,selectId.lastIndexOf("."))  + '.identifier';

		var expressionID = selectId.substring(0,selectId.lastIndexOf("."))  + '.expression';

		// Get the index of Operator
		var operatorDropDownID = selectId + '.literalRestriction[0].evaluator';
		
		var grammerPrefix = selectId.substring(0,selectId.lastIndexOf("."))  + '.fieldConstraint[0].grammerPrefix';
		var grammerPostfix = selectId.substring(0,selectId.lastIndexOf("."))  + '.fieldConstraint[0].grammerPostfix';
		var fieldDisplayUri = selectId.substring(0,selectId.lastIndexOf("."))  + '.fieldConstraint[0].displayUri';
		
		
		var tIndex =selectedField.index-1;

		//var domainObject = null;
		
		// check whether the field already exists
		
		if (isFieldExists(ruleCount, $(domainObjectDropDownID).value, selectedField.value))
		{
			alert('Field already exisits');
			
			// Reset Fields, operators and values
			fieldDropDown.value = '';	
			$(expressionID).value='';	
			
			// Reset Operator 
			$(operatorDropDownID).options.length = 0;
			$(operatorDropDownID).options.add(new Option("Please select Operator",""));
							
							
			// Reset the value selection span

			var selectArea = '<select id="' + validValueField.id + '" name="' + validValueField.id +'">';
							
			selectArea += '<option value="">Please select Value</option></select>';

			$(validValueField.id+'.span').innerHTML = selectArea;
			
			
			return;
		}
		
		try 
		{
			// Get the domain object
			
			authorRule.getRulesDomainObject(domainObjectSelectedIndex - 1, function(object)
		                              {
		                              		domainObject = object;
				
								// Reset the operators
								$(operatorDropDownID).options.length=0;
								$(operatorDropDownID).options.add(new Option("Please select Operator", ""));
			
								domainObject.field[fieldDropDown.selectedIndex-1].operator.each(function(operator)
										{
											$(operatorDropDownID).options.add(new Option(operator.displayUri, operator.name));
								                })

								// Reset the expression
								$(expressionID).value=domainObject.field[fieldDropDown.selectedIndex-1].expression;
								
						//	alert(domainObject.field[fieldDropDown.selectedIndex-1].displayUri);
						//	alert(domainObject.field[fieldDropDown.selectedIndex-1].grammer.prefix);
							
							$(grammerPrefix).value = domainObject.field[fieldDropDown.selectedIndex-1].grammer.prefix;
							$(grammerPostfix).value = domainObject.field[fieldDropDown.selectedIndex-1].grammer.postfix;
							
							$(fieldDisplayUri).value = domainObject.field[fieldDropDown.selectedIndex-1].readableText;
							
							
		                              })
		                              
		        // delay code
				var date = new Date();
				var curdate = null;
				 do {curdate = new Date();} 
				 	while (curdate - date < 100);
				 	
				 	//end 
				 	
				 	
				 	
			
			if (selectedField.value == 'term')
			{
				// Check whether category exists
				var columns = $('rule-'+(ruleCount + 1)+'-columns');
				
				//alert(columns.childNodes.length);
				
				
				var divNodes = 0;
				
				for(var i=0; i < columns.childNodes.length; i++)
				{
					if (columns.childNodes[i].nodeName == 'DIV')
					{
						divNodes++;
					}
				}
							
				var categoryExist = false;
				var categoryValueID;
				
				for (var i=0; i < divNodes; i++)
				{
					var columnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].fieldName';
					
					if($(columnId).value == 'category')
					{
						categoryExist = true;
						categoryValueID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].literalRestriction[0].value';
						break;
					}
				}
				
				if (categoryExist == true)
				{
					var newId = validValueField.id; 
					var spanId = newId + '.span';
					//Element.remove(validValueField);
					$(spanId).innerHTML="";
					
					createAE.getTermsByCategory($(categoryValueID).value, function(terms) {
						                   

							var newId = validValueField.id; 
							var spanId = newId + '.span';

							var selectArea = '<select id="' + newId + '" name="' + newId +'" multiple="multiple" size="3">';
										selectArea += '</select>';
				
							//Element.remove(validValueField);

							
							$(spanId).innerHTML = selectArea;

							var sel = $(newId);	
				                
				                    sel.options.length = 0
				                    
				                    
				                    terms.each(function(term) {
										
										var tempT='';
											if (term.select != null) {
												tempT='-' + term.select
											}
											
				                        var opt = new Option(term.term + tempT, term.id)
				                        sel.options.add(opt)
				                    })
				                })
					
					
					
					
				 /**
					var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="60"/>';
					inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="term-indicator"/>';
					$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';


					new Autocompleter.DWR(newId, newId + '-choices',
					                termPopulator, {
					                valueSelector: termValueSelector,
					                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
					
											//alert(selectedChoice);
											$(newId).value=termValueSelector(selectedChoice);
					                },
					                indicator: "term-indicator"});
					**/
					
					
				}
				else
				{
					
					
					fieldDropDown.value='category';
					
				                createAE.getCategories(3, function(categories) {

							var newId = validValueField.id; 
							var spanId = newId + '.span';

							var selectArea = '<select id="' + newId + '" name="' + newId +'" onchange="onCategoryChange(this, '+ruleCount+')">';
										selectArea += '</select>';
				


							//Element.remove(validValueField);

							
							$(spanId).innerHTML = selectArea;

							var sel = $(newId);	
				                
				                    sel.options.length = 0
				                    sel.options.add(new Option("Any", ""))
				                    
				                    categories.each(function(cat) {
				                        var name = cat.name
				                        if (name.length > 45) name = name.substring(0, 45) + "..."
				                        var opt = new Option(name, cat.id)
				                        sel.options.add(opt)
				                    })
				                })
					
					
					// Add a new column for Term
					
					newNode = divNodes;
					callback = true;
					fetchCondition(ruleCount);
					
					// Reset all the dropdowns for 'term'
					

					
				}
	
			}
			else if (selectedField.value == 'category')
			{

				                
				                createAE.getCategories(3, function(categories) {

							var newId = validValueField.id; 
							var spanId = newId + '.span';

							var selectArea = '<select id="' + newId + '" name="' + newId +'" onchange="onCategoryChange(this,' + ruleCount + ',' + tIndex + ',' + false + ')">';
										selectArea += '</select>';
				
				
				
							var hiddenField = '<input type="hidden" id="ruleSet.rule['+ruleCount+'].condition.column['+columnCount+'].fieldConstraint[0].literalRestriction[0].readableValue"' + ' name="ruleSet.rule['+ruleCount+'].condition.column['+columnCount+'].fieldConstraint[0].literalRestriction[0].readableValue"' +'/>'
									
				
				
				
							//Element.remove(validValueField);

							
							$(spanId).innerHTML = selectArea + hiddenField;

							var sel = $(newId);	
				                
				                    sel.options.length = 0
				                    sel.options.add(new Option("Any", ""))
				                    
				                    categories.each(function(cat) {
				                        var name = cat.name
				                        if (name.length > 45) name = name.substring(0, 45) + "..."
				                        var opt = new Option(name, cat.id)
				                        sel.options.add(opt)
				                    })
				                })

			}
			else if (selectedField.value == 'investigationalNewDrugIndicator') {
				//alert ("ind");
							var newId = validValueField.id; 
							var spanId = newId + '.span';

					var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="60"/>';
					inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="term-indicator"/>';
					$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';

	

				new Autocompleter.DWR(newId, newId + '-choices',
                	orgsPopulator, {
                	valueSelector: orgValueSelector,
                	afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									alert (selectedChoice);
                	},
                	indicator: "term-indicator"});

	
					
					
			}
			else
			{
				
				authorRule.getValidValues(domainObjectSelectedIndex-1, fieldDropDown.selectedIndex-1, 
				                     		function (html) 
			                   			{
			                   				//alert(html);
					                   		//validValueField.innerHTML = html;

									var newId = validValueField.id; 
									var spanId = newId + '.span';

									var isMultiSelect=false;
									
									if (domainObject.field[fieldDropDown.selectedIndex-1].fieldValue.inputType == 'multiselect')
									{
										isMultiSelect=true;
									}
									
									var selectArea = '';
									
									
									
									if (isMultiSelect)
									{
										selectArea = '<select id="' + newId + '" name="' + newId +'" multiple="multiple"  size="3"'+' onchange="handleValueOnselect(this,'+ ruleCount +',' + tIndex + ', true)"' +'>';
										
									}
									else
									{
										selectArea = '<select id="' + newId + '" name="' + newId +'" onchange="handleValueOnselect(this,'+ ruleCount +',' + tIndex + ', false)"' +'>';
									}
									
									var hiddenField = '<input type="hidden" id="ruleSet.rule['+ruleCount+'].condition.column['+columnCount+'].fieldConstraint[0].literalRestriction[0].readableValue"' + ' name="ruleSet.rule['+ruleCount+'].condition.column['+columnCount+'].fieldConstraint[0].literalRestriction[0].readableValue"' +'/>'
									selectArea += html + '</select>';
						

									//Element.remove(validValueField);
									$(spanId).innerHTML = selectArea + hiddenField;
									
									//alert ($(spanId).innerHTML );
							   	});
							   	
							   	//onchange="handleValueOnselect(this, ${ruleCount}, ${fieldIndex}, true)">
			}
		
		}
		catch(e) 
		{
			alert('Exception');
			alert(e);
		}


	}
	
	function getCategoryValue(ruleCount)
	{
				
				// Check whether category exists
				var columns = $('rule-'+(ruleCount + 1)+'-columns');
				
				//alert(columns.childNodes.length);
				
				
				var divNodes = 0;
				
				for(var i=0; i < columns.childNodes.length; i++)
				{
					if (columns.childNodes[i].nodeName == 'DIV')
					{
						divNodes++;
					}
				}
							

				var categoryValueID;
				
				for (var i=0; i < divNodes; i++)
				{
					var columnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].fieldName';
					
					if($(columnId).value == 'category')
					{

						categoryValueID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].literalRestriction[0].value';
						break;
					}
				}
				
				//for (var i=0; i< 100000 ; i ++ ) {
					//var a=10;
				//}
				
				//var  ret = $(categoryValueID).value;
				var ret = document.getElementById(categoryValueID).value;
				if (ret == '') {
					ret = 0;
				}
				
				//alert (ret);
				return ret;
	}

	function handleDomainObjectonChange(domainObjectDropDown, ruleCount)
	{
		
		var domainObjectDropDownID = domainObjectDropDown.id;
		
		var prefixID = domainObjectDropDownID.substring(0,domainObjectDropDownID.lastIndexOf("."));
		
		var domainObjectIdentifierID = prefixID + '.identifier'; 
		
		var expressionID = prefixID + '.expression';
		
		var fieldDropDownID = prefixID + '.fieldConstraint[0].fieldName';
		
		var operatorDropDownID = prefixID + '.fieldConstraint[0].literalRestriction[0].evaluator';

		var valueDropDownID = prefixID + '.fieldConstraint[0].literalRestriction[0].value';
		
		var valueDropDownSpanID = prefixID + '.fieldConstraint[0].literalRestriction[0].value.span';
		
		//
		var domainObjectDisplayUri = prefixID + '.displayUri'; 

		
		if (domainObjectDropDown.selectedIndex == 0)
		{
			// Set all the fields to null
							$(domainObjectIdentifierID).value = '';
						
							// Reset expression
							$(expressionID).value='';
							
							// Set the fields
							$(fieldDropDownID).options.length = 0;
							$(fieldDropDownID).options.add(new Option("Please select Field",""));
							
							
							// Reset Operator 
							$(operatorDropDownID).options.length = 0;
							$(operatorDropDownID).options.add(new Option("Please select Operator",""));
							
							
							// Reset the value selection span

							var selectArea = '<select id="' + valueDropDownID + '" name="' + valueDropDownID +'">';
							
									selectArea += '<option value="">Please select Value</option></select>';

							$(valueDropDownSpanID).innerHTML = selectArea;

			return;
		}

		authorRule.getRulesDomainObject(domainObjectDropDown.selectedIndex - 1, function(domainObject)
		                              {
									                              
							
							// Set the identifier Value
							$(domainObjectIdentifierID).value = domainObject.identifier;
							
							$(domainObjectDisplayUri).value = domainObject.displayUri;
							

							
							
						
							// Reset expression
							$(expressionID).value='';
							
							// Set the fields
							$(fieldDropDownID).options.length = 0;
							$(fieldDropDownID).options.add(new Option("Please select Field",""));
							
							domainObject.field.each(function(field){
								$(fieldDropDownID).options.add(new Option(field.displayUri,field.name));
							})
							
							// Reset Operator 
							$(operatorDropDownID).options.length = 0;
							$(operatorDropDownID).options.add(new Option("Please select Operator",""));
							
							
							// Reset the value selection span

							var selectArea = '<select id="' + valueDropDownID + '" name="' + valueDropDownID +'">';
							
									selectArea += '<option value="">Please select Value</option></select>';

							$(valueDropDownSpanID).innerHTML = selectArea;
							
							
							
		                              });
		
		
	}
	
	function onCategoryChange(category, ruleCount, fieldIndex, multi)

	{
		var selectId =   category.id.substring(0,category.id.lastIndexOf(".")); 
		
		var displayUriID = selectId+ '.readableValue';
		
		
		
				              for (var i = 0; i < category.options.length; i++) {
		                    
		                    	if (category.options[ i ].selected) {
		                    		 $(displayUriID).value = category.options[ i ].text
		                    		break;
		                    	}
		                    	
		                      }
		
		
				// Check whether category exists
				var columns = $('rule-'+(ruleCount + 1)+'-columns');
				
				
				
				var divNodes = 0;
				
				for(var i=0; i < columns.childNodes.length; i++)
				{
					if (columns.childNodes[i].nodeName == 'DIV')
					{
						divNodes++;
					}
				}
							
				var termExists = false;
				var termValueID;
				
				for (var i=0; i < divNodes; i++)
				{
					var columnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].fieldName';
					
					if($(columnId).value == 'term')
					{
						termExists = true;
						termValueID = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].literalRestriction[0].value';
						break;
					}
				}
				
				if (termExists)
				{
					
					var catVal = category.value;
					if (category.value == ''){
						catVal = 0;
					}
					//alert(catVal);
					
					createAE.getTermsByCategory(catVal, function(terms) {
						        
						     $(termValueID).value='';   

							var sel = $(termValueID);	
				                
				                
				                    sel.options.length = 0
				                    
				                    
				                    terms.each(function(term) {
										var tempT='';
											if (term.select != null) {
												tempT='-' + term.select
											}
											
				                        var opt = new Option(term.term + tempT, term.id)
				                        sel.options.add(opt)
				                    })
				                })
					
					
				}
	}
	
	
</script>

</head>
<body>
<p id="instructions">Rules can be added by using the Add Rule
button. Rules created will belong to the selected RuleSet.</p>

<chrome:division>

	<%--<form:form cssClass="standard">--%>
	<tags:tabForm tab="${tab}" flow="${flow}">
		<jsp:attribute name="singleFields">

			<tags:errors path="*" />

			<%--<tags:tabFields tab="${tab}"/>--%>


			<div class="row">
			<div class="label"><label for="ruleSetName">RuleSet
			Name</label></div>
			<div class="value"><form:input path="ruleSetName" size="40" readonly="readonly"/>
			</div>
			<!--
			<div class="local-buttons"><input type="button"
				value="Add Rule" align="right" onclick="addRule()" /></div>
			</div>
			-->


			<div id="allRules"><c:forEach varStatus="ruleStatus"
				items="${command.ruleSet.rule}">

				<c:set var="ruleCount" value="${ruleStatus.index}" />
				<div id="rule-${ruleCount + 1}" class="section">
				<h3 style="position:relative; float:left" class="handle""><span
					style="position:relative; float:left">Rule -
				(${ruleCount+1})</span> <a href="javascript:deleteRule(${ruleCount + 1})">
				<img id="close-image" src="/caaers/images/rule/window-close.gif"
					align="absmiddle"
					style="position:relative; float:right; height:18px; border:0px" />
				</a> <img src="/caaers/images/chrome/spacer.gif"
					style="position:relative; float:right;width:5px;height:10px"
					align="absmiddle" /> <a href="javascript:toggle(${ruleCount + 1})">
				<img id="toggle-image-${ruleCount + 1}" onclick=""
					src="/caaers/images/rule/window-minimize.gif" valign="top"
					align="absmiddle"
					style="position:relative; float:right; height:18px; border:0px" />
				</a></h3>
				<div style="margin-left:50px;"><label class="label"
					for="condition">Name</label> <form:input
					path="ruleSet.rule[${ruleCount}].metaData.name"
					cssStyle="width:200px" /></div>
				<br />
				<div id="rule-condition-action-container-${ruleCount + 1}">
				<div style="margin-left:50px;"><label class="label"
					for="condition">Condition(s)</label></div>
				<div class="row" value="${command.ruleSet.rule[ruleCount]}"
					id="rule-${ruleCount + 1}-columns"><br />

				<c:forEach varStatus="columnStatus" begin="0"
					items="${command.ruleSet.rule[ruleCount].condition.column}">
					<c:set var="columnCount" value="${columnStatus.index}" />

					<div id="rule-${ruleCount}-column-${columnCount}"
						style="margin-left:200px; 
						
						<c:if test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].markedDelete}">
							visibility:hidden
						</c:if>"
						
						 class="lineitem"><img
						src="/caaers/images/chrome/spacer.gif"
						style="width:10px;height:10px" align="absmiddle" /> <c:choose>
						<c:when test="${columnCount == 0}">
							<label for="IF">IF</label>
							<img src="/caaers/images/chrome/spacer.gif"
								style="width:15px;height:1px" align="absmiddle" />
						</c:when>
						<c:otherwise>
							<label for="AND">AND</label>
						</c:otherwise>
					</c:choose> <img src="/caaers/images/chrome/spacer.gif"
						style="width:10px;height:10px" align="absmiddle" /> <span>
					<form:select
						path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType"
						onchange="handleDomainObjectonChange(this, ${ruleCount})">
						<form:option value="">Please select Domain Object</form:option>
						<form:options items="${ruleUi.condition[0].domainObject}"
							itemLabel="displayUri" itemValue="className" />
					</form:select> 
					
					<!-- set domain-object display-uri to column -->
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].displayUri" />
						
															
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].identifier" />
						
						
					</span> <img src="/caaers/images/chrome/spacer.gif"
						style="width:10px;height:10px" align="absmiddle" /> <form:select
						path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName"
						onchange="handleFieldOnchange(this, ${ruleCount}, ${columnCount})">
						<form:option value="">Please select Field</form:option>

						<c:forEach items="${ruleUi.condition[0].domainObject}"
							varStatus="selectedField">
							<c:set var="selectedIndex" value="${selectedField.index}" />
							<c:if
								test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].objectType ==
												        		      			ruleUi.condition[0].domainObject[selectedIndex].className}">
								<form:options
									items="${ruleUi.condition[0].domainObject[selectedIndex].field}"
									itemLabel="displayUri" itemValue="name" />
							</c:if>
						</c:forEach>

					</form:select> 
					
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].expression" />
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].grammerPrefix" />
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].displayUri" />
					<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].displayUri" />

					<img src="/caaers/images/chrome/spacer.gif"
						style="width:10px;height:10px" align="absmiddle" /> <form:select
						path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator"
						onchange="handleOperatorOnchange(this, ${ruleCount})">
						<form:option value="">Please select operator</form:option>



						<c:forEach items="${ruleUi.condition[0].domainObject}"
							varStatus="selectedDomainObject">
							<c:set var="domainObjectIndex"
								value="${selectedDomainObject.index}" />

							<c:if
								test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].objectType ==
												        		      			ruleUi.condition[0].domainObject[domainObjectIndex].className}">
								<c:forEach
									items="${ruleUi.condition[0].domainObject[domainObjectIndex].field}"
									varStatus="selectedField">
									<c:set var="fieldIndex" value="${selectedField.index}" />


									<c:if
										test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName ==
												        		      					ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].name}">
										<form:options
											items="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].operator}"
											itemLabel="displayUri" itemValue="name" />
									</c:if>
								</c:forEach>
							</c:if>
						</c:forEach>

					</form:select> <img src="/caaers/images/chrome/spacer.gif"
						style="width:10px;height:10px" align="absmiddle" /> <span
						id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value.span">

					<c:choose>
						<c:when
							test='${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName eq "category"}'>


							<script type="text/javascript">
																		//alert ("calling cats....");
																		function wait(delay){
																				string="pauseforalert("+delay+");";
																				setTimeout(string,delay);
																		}
																		function pauseforalert(delay){
																				alert("Ok "+delay/1000+" seconds have elapsed");
																		}

																		var fieldValue;
																		createAE.getCategories(3, function(categories) {
																	
																			var newId = 'ruleSet.rule[' + ${ruleCount} + '].condition.column[' + ${columnCount} + '].fieldConstraint[0].literalRestriction[0].value'; 
																			var spanId = newId + '.span';
																	
																		 fieldValue = '${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].literalRestriction[0].value[0]}';
																			//alert (fieldValue);
																			var selectArea = '<select id="' + newId + '" name="' + newId +  '" value="' + fieldValue + '" onchange="onCategoryChange(this, ${ruleCount})">';
																			selectArea += '</select>';
						
						
													var hiddenField = '<input type="hidden" id="ruleSet.rule['+${ruleCount}+'].condition.column['+${columnCount}+'].fieldConstraint[0].literalRestriction[0].readableValue"' + ' name="ruleSet.rule['+${ruleCount}+'].condition.column['+${columnCount}+'].fieldConstraint[0].literalRestriction[0].readableValue"' +'/>'

													
																			//Element.remove(validValueField);
																	
																								
																			$(spanId).innerHTML = selectArea + hiddenField;
																	
																			var sel = $(newId);	
																					                
																			sel.options.length = 0
																			sel.options.add(new Option("Any", ""))
																					                    
																			var index = 0;		                    
																			categories.each(function(cat) {
																				 var name = cat.name
																				 if (name.length > 45) name = name.substring(0, 45) + "..."
																					    var opt = new Option(name, cat.id)
																					    sel.options.add(opt)
																					    index++;
																					    
																					    if (cat.id == fieldValue)
																					    {
																					    	sel.options[index].selected=true;
																					    }
																					    
																		               })
																		        
																	           })
																	      //give some delay ...
																	  //    wait(2000);
																	     //alert('ss');

																	</script>


						</c:when>
					
						<c:when
							test='${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName eq "term"}'>



							<script type="text/javascript">
												// force 1sec delay for ajax to make sure categories are loaded.. this is just  TEMP FIX 
											   setTimeout("loadTermsBasedOnCategory()",1000);	


												function loadTermsBasedOnCategory() {
	
																		var newId = 'ruleSet.rule[' + ${ruleCount} + '].condition.column[' + ${columnCount} + '].fieldConstraint[0].literalRestriction[0].value'; 
																		var spanId = newId + '.span';
																		var fieldValue = '${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].literalRestriction[0].value}';
																		
																		$(spanId).innerHTML="";
																		
																		// Check whether category exists
																	//	alert (categoryValue);
																		var categoryValue = getCategoryValue(${ruleCount});
																		//alert (categoryValue);
																	
																		createAE.getTermsByCategory(categoryValue, function(terms) {
						                   


																				var selectArea = '<select id="' + newId + '" name="' + newId +'" multiple="multiple" size="3">';
																				selectArea += '</select>';
				
							
																				$(spanId).innerHTML = selectArea;

																				var sel = $(newId);	
				                
														                    sel.options.length = 0
				                    
				                    											var index = 0;	
															                    terms.each(function(term) {
																				var tempT='';
																					if (term.select != null) {
																						tempT='-' + term.select
																					}
													                        		var opt = new Option(term.term + tempT, term.id)
				                    									    			sel.options.add(opt)
				                    									    		   
				                    									    		    		if (fieldValue.indexOf(term.id) != -1)
																					    		{
																					    			sel.options[index].selected=true;
																					    		}
																					    	index++;
																					    
				                    											})
				                										})
					
	
	
												}											

															
							</script>

						</c:when>

						<c:otherwise>
							<c:choose>
								<c:when
									test="${empty command.ruleSet.rule[ruleCount].condition.column[columnCount].objectType ||
																		                empty command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName}">
									<form:select
										path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"
										multiple="false">
										<form:option value="">Please select value</form:option>
									</form:select>

								</c:when>
								<c:otherwise>
									<c:forEach items="${ruleUi.condition[0].domainObject}"
										varStatus="selectedDomainObject">
										<c:set var="domainObjectIndex"
											value="${selectedDomainObject.index}" />

										<c:if
											test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].objectType ==
												        		      						ruleUi.condition[0].domainObject[domainObjectIndex].className}">
											<c:forEach
												items="${ruleUi.condition[0].domainObject[domainObjectIndex].field}"
												varStatus="selectedField">
												<c:set var="fieldIndex" value="${selectedField.index}" />


												<c:if
													test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName ==
												        		      							ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].name}">

													<c:choose>
														<c:when
															test='${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].fieldValue.inputType == "multiselect"}'>
															<form:select
																path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"
																multiple="multiple" size="3"
																onchange="handleValueOnselect(this, ${ruleCount}, ${fieldIndex}, true)">
																<form:options
																	items="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}"
																	itemLabel="displayUri" itemValue="value" />
															</form:select>
															<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].readableValue" />
														</c:when>
														<c:otherwise>
															<form:select
																path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"
																multiple="false"
																onchange="handleValueOnselect(this, ${ruleCount}, ${fieldIndex}, false)">
																<form:option value="">Please select value</form:option>
																<form:options
																	items="${ruleUi.condition[0].domainObject[domainObjectIndex].field[fieldIndex].validValue}"
																	itemLabel="displayUri" itemValue="value" />
															</form:select>
															<form:hidden path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].readableValue" />
														</c:otherwise>
													</c:choose>

												</c:if>
											</c:forEach>
										</c:if>
									</c:forEach>
								</c:otherwise>
							</c:choose>

						</c:otherwise>
					</c:choose> </span> <a href="javascript:fetchCondition(${ruleCount})"> <img
						id="add-column-${ruleCount}"
						src="/caaers/images/rule/add_condition.gif" align="absmiddle"
						style="cursor:hand; border:0px" /> </a> <c:if
						test="${columnCount > 0}">
						<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
						<img id="remove-column-${ruleCount}"
							src="/caaers/images/rule/remove_condition.gif" align="absmiddle"
							style="cursor:hand;  border:0px" /> </a>
					</c:if></div>

					<br id="rule-${ruleCount}-column-${columnCount}-br" />
				</c:forEach></div>

				<div class="row">
				<div style="margin-left:50px;"><label for="action"
					class="label">Action(s)</label></div>
				<br />
					<div id="action-template" style="margin-left:200px;"><img
					src="/caaers/images/chrome/spacer.gif"
					style="width:10px;height:10px" align="absmiddle" /> 
				<form:select path="ruleSet.rule[${ruleCount}].action.actionId">
					<option value="" />Please Select Action</option>
					<c:choose>
						<c:when test='${command.ruleSetName == "AE Assesment Rules"}'>
							<form:option value="ROUTINE_AE">Assess as Routine AE</form:option>
							<form:option value="SERIOUS_ADVERSE_EVENT">Assess as Serious AE</form:option>
						</c:when>
						<c:when test='${command.ruleSetName == "Report Scheduling Rules"}'>
								<c:forEach var="reportDefinition" items="${command.reportDefinitions}">
									<form:option value="${reportDefinition.name}">${reportDefinition.name}</form:option>
								</c:forEach>	
						</c:when>
						<c:otherwise>
							<form:option value="ROUTINE_AE">Assess as Routine AE</form:option>
							<form:option value="SERIOUS_ADVERSE_EVENT">Assess as Serious AE</form:option>
								<c:forEach var="reportDefinition" items="${command.reportDefinitions}">
									<form:option value="${reportDefinition.name}">${reportDefinition.name}</form:option>
								</c:forEach>	
						</c:otherwise>
					</c:choose>
					<c:forEach items="${notifications}" var="notification">
						<form:option value="${notification.id}">${notification.name}</form:option>
					</c:forEach>
				</form:select></div>
				<c:if test="${ruleCount} == 0">
					<br />
				</c:if></div>
				</div>
				</div>

			</c:forEach>
			
			
			</div>
			<!-- closing allRules -->
			<div class="local-buttons"><input type="button"
				value="Add Rule" align="right" onclick="addRule()" /></div>
			</div>
		</jsp:attribute>
	</tags:tabForm>
	
</chrome:division>

</body>
</html>
