<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="authorRule"/>
    <tags:dwrJavascriptLink objects="createAE"/>

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
				Sortable.create('allRules',{tag:'div',only:'section',handle:'handle'});
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
		
		function addRule() {
				try {

					authorRule.addRule(name, function (html) {
						sections.push('rule-' + (sections.length + 1));
						var columnHolder = getElementHolderDiv();
						columnHolder.innerHTML = html;
						var newRule = columnHolder.childNodes[1].cloneNode(true);
						columnHolder.innerHTML = "";
						$('allRules').appendChild(newRule);
						Effect.Appear('rule-' + (sections.length));
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
								var newColumnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + newNode + '].fieldConstraint[0].fieldName';
								//alert(newColumnId);
								$(newColumnId).value='term';
								
								
								var selectId =  newColumnId.substring(0,newColumnId.lastIndexOf(".")); 
								
										
								var validValueField = document.getElementById(selectId + '.literalRestriction[0].value');

								var newId = validValueField.id; 
								var spanId = newId + '.span';
								//Element.remove(validValueField);
								$(spanId).innerHTML="";
												
								var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="40"/>';
								inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="sponsor-indicator"/>';
								$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';
								
								
								new Autocompleter.DWR(newId, newId + '-choices',
									                termPopulator, {
									                valueSelector: termValueSelector,
									                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									
												//alert(selectedChoice);
												$(newId).value=termValueSelector(selectedChoice);
									                },
						                indicator: "sponsor-indicator"});
													                

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
								columns.removeChild($(column.id + '-br'));
								columns.removeChild(column);
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


	
	function handleFieldOnchange(fieldDropDown, ruleCount) 
	{
		var selectedField = fieldDropDown.options[fieldDropDown.selectedIndex];
		
		
		//alert(selectedField.value);
		// alert("field name called " + selectedField.text + " has been selected: " + fieldDropDown.id);
		
		//Fetch the related operators
				
		//Fetch the values based on the type of input field.
		/*
		var domainObjectSelectedIndex = document.getElementById("domain-object-template").selectedIndex;
		var methodCall = document.getElementById(domainObjectSelectedIndex+"_"+selectedField.value+"_fieldValueMethodCall").value;
		alert(methodCall);
		try {
			var returnValue = eval(methodCall);
			alert(returnValue);
		} catch(e) {alert(e)}
		*/

		//alert(fieldDropDown.id);
		
		var selectId =  fieldDropDown.id.substring(0,fieldDropDown.id.lastIndexOf(".")); 
		//alert(selectId);
		
		var validValueField = document.getElementById(selectId + '.literalRestriction[0].value');
		
		//alert(validValueField);
		//alert(validValueField.id);
		
		//alert(validValueField.innerHTML);
		
		//validValueField.innerHTML = html;
		
		try 
		{
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
				
				for (var i=0; i < divNodes; i++)
				{
					var columnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + i + '].fieldConstraint[0].fieldName';
					
					if($(columnId).value == 'category')
					{
						categoryExist = true;
						break;
					}
				}
				
				if (categoryExist == true)
				{
					var newId = validValueField.id; 
					var spanId = newId + '.span';
					//Element.remove(validValueField);
					$(spanId).innerHTML="";
				
					var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="40"/>';
					inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="sponsor-indicator"/>';
					$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';


					new Autocompleter.DWR(newId, newId + '-choices',
					                termPopulator, {
					                valueSelector: termValueSelector,
					                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
					
											//alert(selectedChoice);
											$(newId).value=termValueSelector(selectedChoice);
					                },
					                indicator: "sponsor-indicator"});
				}
				else
				{
					fieldDropDown.value='category';
					
				                createAE.getCategories(3, function(categories) {

							var newId = validValueField.id; 
							var spanId = newId + '.span';

							var selectArea = '<select id="' + newId + '" name="' + newId +'">';
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
					
					//callback = afterFiveSeconds();
					
					newNode = divNodes;
					callback = true;
					fetchCondition(ruleCount);
					
					//callback=false;
					//var newColumnId = 'ruleSet.rule['+ ruleCount + '].condition.column[' + divNodes + '].fieldConstraint[0].fieldName';
					
					//setTimeout('afterFiveSeconds(newColumnId)',5000)
					
 					
					//$(newColumnId).value='term';

					/*
					var newId = validValueField.id; 
					var spanId = newId + '.span';
					//Element.remove(validValueField);
					$(spanId).innerHTML="";
				
					var inputArea = '<input type="text" id="' + newId + '" name="' + newId +'" size="40"/>';
					inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="sponsor-indicator"/>';
					$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';


					new Autocompleter.DWR(newId, newId + '-choices',
					                termPopulator, {
					                valueSelector: termValueSelector,
					                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
					
											//alert(selectedChoice);
											$(newId).value=termValueSelector(selectedChoice);
					                },
					                indicator: "sponsor-indicator"});
					                
					*/                
				
				}
	
			}
			else if (selectedField.value == 'category')
			{
				                createAE.getCategories(3, function(categories) {

							var newId = validValueField.id; 
							var spanId = newId + '.span';

							var selectArea = '<select id="' + newId + '" name="' + newId +'">';
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

			}
			else
			{
				
				authorRule.getValidValues(fieldDropDown.selectedIndex, 
			                     		function (html) 
			                   			{
			                   				//alert(html);
					                   		//validValueField.innerHTML = html;

									var newId = validValueField.id; 
									var spanId = newId + '.span';

									var selectArea = '<select id="' + newId + '" name="' + newId +'">';
									selectArea += html + '</select>';
						

									//Element.remove(validValueField);
									$(spanId).innerHTML = selectArea;
							   	});
			}
		
		}
		catch(e) 
		{
			alert('Exception');
			alert(e);
		}


	}

	function afterFiveSeconds()
	{
		alert('Hello after 5 seconds: ' + column);
	}

</script>

</head>
<body>
    <p id="instructions">
        Rules can be added by using the Add Rule button. Rules created will belong to the selected RuleSet.
    </p>

    <chrome:division>

        <%--<form:form cssClass="standard">--%>
        <tags:tabForm tab="${tab}" flow="${flow}" >
	<jsp:attribute name="singleFields">

            <tags:errors path="*"/>
    
            <%--<tags:tabFields tab="${tab}"/>--%>

						<%--
						<div id="createNewss">
							<!-- <h3 style="position:relative; float:left">Create New Rule</h3> -->
							<!-- <input type="text" id="newRuleName" size="50"> -->
							<a href="javascript:addRule();" title="Click to Add a new Rule">
								<img src="/caaers/images/rule/new_fact.gif" style="" align="absmiddle"/>
							</a>
							
						</div>
        				--%>
        				
        				<div class="row">
							<div class="label"><label for="ruleSetName">RuleSet Name</label></div>
				            <div class="value">
								<form:input path="ruleSetName" size="40"/>
            				</div>
							<div class="local-buttons">
								<input type="button" value="Add Rule" align="right" onclick="addRule()"/>
							</div>	
        				</div>


						<div id="allRules">

							<c:forEach varStatus="ruleStatus" items="${command.ruleSet.rule}">

								<c:set var="ruleCount" value="${ruleStatus.index}" />
								<div id="rule-${ruleCount + 1}" class="section">
										<h3 style="position:relative; float:left" class="handle"">
										<span style="position:relative; float:left">Rule - (${ruleCount+1})</span>
										<a href="javascript:deleteRule(${ruleCount + 1})">
											<img id="close-image" src="/caaers/images/rule/window-close.gif"  align="absmiddle"  style="position:relative; float:right; height:18px; border:0px"/>
										</a>
										<img src="/caaers/images/chrome/spacer.gif" style="position:relative; float:right;width:5px;height:10px" align="absmiddle" />
										<a href="javascript:toggle(${ruleCount + 1})">
											<img id="toggle-image-${ruleCount + 1}" onclick="" src="/caaers/images/rule/window-minimize.gif" valign="top" align="absmiddle"  style="position:relative; float:right; height:18px; border:0px"/>
										</a>
										</h3>
										<div style="margin-left:50px;">
											<label class="label" for="condition">Name</label>
											<form:input path="ruleSet.rule[${ruleCount}].metaData.name" cssStyle="width:200px"/>
										</div>	
										<br/>
										<div id="rule-condition-action-container-${ruleCount + 1}">
											<div style="margin-left:50px;">
												<label class="label" for="condition">Condition(s)</label>
											</div>
											<div class="row" value="${command.ruleSet.rule[ruleCount]}" id="rule-${ruleCount + 1}-columns">
												<br/>

												<c:forEach varStatus="columnStatus" begin="0" items="${command.ruleSet.rule[ruleCount].condition.column}">
													<c:set var="columnCount" value="${columnStatus.index}"/>
													<div id="rule-${ruleCount}-column-${columnCount}" style="margin-left:200px;" class="lineitem">
														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
														<c:choose>
															<c:when test="${columnCount == 0}">
															<label for="IF">IF</label><img src="/caaers/images/chrome/spacer.gif" style="width:15px;height:1px" align="absmiddle" />
															</c:when>
															<c:otherwise><label for="AND">AND</label></c:otherwise>
														</c:choose>													
														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

														<span>
														<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].objectType">
															<form:options items="${ruleUi.condition[0].domainObject}" itemLabel="displayUri" itemValue="className" />
														</form:select>
														</span>

														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

														<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this, ${ruleCount})">
															<form:options items="${ruleUi.condition[0].domainObject[0].field}" itemLabel="displayUri" itemValue="name" />
														</form:select>

														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

														<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator">
															<form:options items="${ruleUi.operator}" itemLabel="displayUri" itemValue="name" />
														</form:select>

														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />


														<span id="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value.span">
															<%--
															<form:input path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value"/>
															--%>
															
															<c:choose>
																<c:when test='${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName eq "term"}'>
																	
																	
																	<script type="text/javascript">
																		var newId = 'ruleSet.rule[' + ${ruleCount} + '].condition.column[' + ${columnCount} + '].fieldConstraint[0].literalRestriction[0].value'; 
																		var spanId = newId + '.span';
																		var fieldValue = '${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].literalRestriction[0].value}';
																		
																		$(spanId).innerHTML="";
																					
																		var inputArea = '<input type="text" id="' + newId + '" name="' + newId +  '" value="' + fieldValue + '" size="40"/>';
																		inputArea += '<img alt="activity indicator" src="/caaers/images/indicator.white.gif" class="indicator" id="sponsor-indicator"/>';
																		$(spanId).innerHTML = inputArea + '<div id="' + newId + '-choices' + '" class="autocomplete"></div>';
																	
																	
																		new Autocompleter.DWR(newId, newId + '-choices',
																		               termPopulator, {
																		                valueSelector: termValueSelector,
																		                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
																				
																				//alert(selectedChoice);
																				$(newId).value=termValueSelector(selectedChoice);
																		                },
																		               indicator: "sponsor-indicator"});

																	
																	</script>
																</c:when>
																<c:when test='${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName eq "category"}'>
																	<script type="text/javascript">
																		createAE.getCategories(3, function(categories) {
																	
																			var newId = 'ruleSet.rule[' + ${ruleCount} + '].condition.column[' + ${columnCount} + '].fieldConstraint[0].literalRestriction[0].value'; 
																			var spanId = newId + '.span';
																	
																			var fieldValue = '${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].literalRestriction[0].value}';
																			var selectArea = '<select id="' + newId + '" name="' + newId +  '" value="' + fieldValue + '">';
																			selectArea += '</select>';
																					
																			//Element.remove(validValueField);
																	
																								
																			$(spanId).innerHTML = selectArea;
																	
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

																	</script>
																</c:when>
																<c:otherwise>
																	<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value">
																	
																		<c:choose>
																			<c:when test="${empty command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].literalRestriction[0].value}">
																				<form:options items="${ruleUi.condition[0].domainObject[0].field[0].validValue}"  itemLabel="displayUri" itemValue="value"/>
																			</c:when>
																			<c:otherwise>
																				<c:forEach items="${ruleUi.condition[0].domainObject[0].field}" varStatus="selectedField">
																					<c:if test="${command.ruleSet.rule[ruleCount].condition.column[columnCount].fieldConstraint[0].fieldName ==
																	        		      			ruleUi.condition[0].domainObject[0].field[selectedField.index].name}">															
																						<form:options items="${ruleUi.condition[0].domainObject[0].field[selectedField.index].validValue}"  itemLabel="displayUri" itemValue="value"/>
																					</c:if>
																				</c:forEach>
																			</c:otherwise>
																		</c:choose>
																	</form:select>
																</c:otherwise>		
															</c:choose>
														</span>

														<a href="javascript:fetchCondition(${ruleCount})">
															<img id="add-column-${ruleCount}" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand; border:0px"/>
														</a>
														
														<c:if test="${columnCount > 0}">
														<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
															<img id="remove-column-${ruleCount}" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand;  border:0px"/>
														</a>
														</c:if>
													</div>

													<br id="rule-${ruleCount}-column-${columnCount}-br"/>
												</c:forEach>
											</div>

											<div class="row">
												<div  style="margin-left:50px;"><label for="action" class="label">Action(s)</label></div>
												<br/>
												<div id="action-template"  style="margin-left:200px;">
													<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
													<form:select path="ruleSet.rule[${ruleCount}].action.actionId">
														<option value=""/>Please Select </option>
														<c:choose>
															<c:when test='${command.ruleSetName == "AE Assessment RuleSet"}'>
																<form:option value="ROUTINE_AE">Assess as Routine AE</form:option>														
																<form:option value="SERIOUS_ADVERSE_EVENT">Assess as Serious AE</form:option>														
															</c:when>
															<c:when test='${command.ruleSetName == "SAE Reporting RuleSet"}'>
																<form:option value="24HR_NOTIFICATION_5DAY_CALENDAR_REPORT">24 Hour, 5 Calendar Days</form:option>
																<form:option value="10DAY_CALENDAR_REPORT">10 Calendar Days</form:option>
															</c:when>
															<c:otherwise>														
																<form:option value="ROUTINE_AE">Assess as Routine AE</form:option>														
																<form:option value="SERIOUS_ADVERSE_EVENT">Assess as Serious AE</form:option>														
																<form:option value="24HR_NOTIFICATION_5DAY_CALENDAR_REPORT">24 Hour, 5 Calendar Days</form:option>
																<form:option value="10DAY_CALENDAR_REPORT">10 Calendar Days</form:option>
															</c:otherwise>
														</c:choose>	
														<c:forEach items="${notifications}" var="notification">
														<form:option value="${notification.id}">${notification.name}</form:option>
														</c:forEach>
													</form:select>
													<a href="javascript:addAction(${ruleCount})">
														<img id="add-action-image" onclick="addAction(${ruleCount})" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand"/>
													</a>
													
													<a href="javascript:addAction(${ruleCount})">
														<img id="remove-action-image" onclick="deleteAction(${ruleCount})" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand"/>											
													</a>
												</div>
												<c:if test="${ruleCount} == 0" >
												<br/>
												</c:if>
											</div>
										</div>											
									</div>								

								</c:forEach>

						</div> <!-- closing allRules -->

    <%--        </form:form>--%>
</jsp:attribute>
</tags:tabForm> 
		</chrome:division>

</body>
</html>