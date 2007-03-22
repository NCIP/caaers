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

		function addRule() {
			var name = $F('newRuleName');
			if (name != '') {
				try {

					authorRule.addRule(name, function (html) {
						sections.push('rule-' + (sections.length + 1));
						var totalHtml = $('allRules').innerHTML;
						totalHtml = totalHtml + html;
						$('allRules').innerHTML = totalHtml;		
						//Effect.Appear('rule-' + (sections.length + 1));
						createRuleSortable();
					});

				} catch(e) {alert(e)}
			}
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
	
	

	
	function handleFieldOnchange(fieldDropDown) {
		var selectedField = fieldDropDown.options[fieldDropDown.selectedIndex];
		//alert("field name called " + selectedField.text + " has been selected.");
		
		//Fetch the related operators
		
		
		//Fetch the values based on the type of input field.
		
		var domainObjectSelectedIndex = document.getElementById("domain-object-template").selectedIndex;
		var methodCall = document.getElementById(domainObjectSelectedIndex+"_"+selectedField.value+"_fieldValueMethodCall").value;
		try {
			var returnValue = eval(methodCall);
		} catch(e) {alert(e)}

	}

</script>

</head>
<body>
    <p id="instructions">
        You are adding rules for the trigger. You can define any number of Rules for a trigger.
    </p>

    <chrome:division title="Add Rules">

        <form:form cssClass="standard">
            <tags:errors path="*"/>
    
            <tags:tabFields tab="${tab}"/>

						<div id="createNewss">
							<!-- <h3>Create New Rule</h3> -->
							<input type="text" id="newRuleName" size="50">
							<a href="javascript:addRule();" title="Click to Add a new Rule">
								<img src="/caaers/images/rule/new.gif" style="height:20px; border:0px" align="absmiddle"/>
							</a>
						</div>


						<div id="allRules">

							<c:forEach varStatus="ruleStatus" items="${command.ruleSet.rule}">

								<c:set var="ruleCount" value="${ruleStatus.index}" />
								<div id="rule-${ruleCount + 1}" class="section">
										<h3 style="position:relative; float:left" class="handle"">
										<span style="position:relative; float:left">${command.ruleSet.rule[ruleCount].metaData.name}</span>
										<a href="javascript:deleteRule(${ruleCount + 1})">
											<img id="close-image" src="/caaers/images/rule/window-close.gif"  align="absmiddle"  style="position:relative; float:right; height:18px; border:0px"/>
										</a>
										<img src="/caaers/images/chrome/spacer.gif" style="position:relative; float:right;width:5px;height:10px" align="absmiddle" />
										<a href="javascript:toggle(${ruleCount + 1})">
											<img id="toggle-image-${ruleCount + 1}" onclick="" src="/caaers/images/rule/window-minimize.gif" valign="top" align="absmiddle"  style="position:relative; float:right; height:18px; border:0px"/>
										</a>
										</h3>
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

														<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].fieldName" onchange="handleFieldOnchange(this)">
															<form:options items="${ruleUi.condition[0].domainObject[0].field}" itemLabel="displayUri" itemValue="name" />
														</form:select>

														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

														<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].evaluator">
															<form:options items="${ruleUi.operator}" itemLabel="displayUri" itemValue="name" />
														</form:select>

														<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />

														<span id="rule-${ruleCount}-column-${columnCount}-field-value">
															<form:select path="ruleSet.rule[${ruleCount}].condition.column[${columnCount}].fieldConstraint[0].literalRestriction[0].value">
																<option value=""/>Please Select-- </option>
																<form:option value="1"/>
																<form:option value="2"/>
																<form:option value="3"/>
																<form:option value="4"/>
																<form:option value="5"/>
																<form:option value="Hospitalized"/>
																<form:option value="Unexpected"/>
																<form:option value="Unlikely Related"/>
																<form:option value="5"/>
															</form:select>
														</span>

														<a href="javascript:fetchCondition(${ruleCount})">
															<img id="add-column-${ruleCount}" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand; border:0px"/>
														</a>
														<a href="javascript:removeCondition(${ruleCount}, ${columnCount})">
															<img id="remove-column-${ruleCount}" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand;  border:0px"/>
														</a>
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
														<option value=""/>Please Select-- </option>
														<form:option value="1">Pending AE Report 3 day Notification</form:option>
														<form:option value="2">Pending AE Report 5 day Notification</form:option>
														<form:option value="3">Send Email to Site IRB</form:option>
														<form:option value="4">Send Email to Study Research Nurse</form:option>
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
												<div id="action-template"  style="margin-left:200px;">
													<img src="/caaers/images/chrome/spacer.gif" style="width:10px;height:10px" align="absmiddle" />
													<form:select path="ruleSet.rule[${ruleCount}].action.actionId">
														<option value=""/>Please Select-- </option>
														<form:option value="1">Pending AE Report 3 day Notification</form:option>
														<form:option value="2">Pending AE Report 5 day Notification</form:option>
														<form:option value="3">Send Email to Site IRB</form:option>
														<form:option value="4">Send Email to Study Research Nurse</form:option>
													</form:select>
													<a href="javascript:addAction(${ruleCount})">
														<img id="add-action-image" onclick="addAction(${ruleCount})" src="/caaers/images/rule/add_condition.gif" align="absmiddle" style="cursor:hand"/>
													</a>
													<a href="javascript:addAction(${ruleCount})">
														<img id="remove-action-image" onclick="deleteAction(${ruleCount})" src="/caaers/images/rule/remove_condition.gif" align="absmiddle" style="cursor:hand"/>											
													</a>
												</div>
												</c:if>
											</div>
										</div>											
									</div>								

								</c:forEach>

						</div> <!-- closing allRules -->

        </form:form>
		</chrome:division>

</body>
</html>