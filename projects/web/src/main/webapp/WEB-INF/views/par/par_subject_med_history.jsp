<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
  <head>
	<style type="text/css">
		.divOther{
			font-size:8pt;
	 		border-color:#6E81A6;
	 		border-style:solid;
	 		border-width:1px 0px 0px 0px;
		}
		 div.row div.label { width: 15em; } 
		 div.summary div.row div.label { width: 10em; } 
		 div.summary div.row div.value, div.summary div.row div.extra {margin-left:11em;}
		 div.row div.value, div.row div.extra { margin-left: 16em; }
		 .tablecontent td {
		 	border : 0px;
		 }
		 
	</style>
	<tags:javascriptLink name="extremecomponents"/>
 	<tags:dwrJavascriptLink objects="createAE"/>
	<script type="text/javascript">
		var mHistory = null;
 		var mHistoryClass = Class.create();
 		Object.extend(mHistoryClass.prototype, {
 	 		initialize: function(){ 
 	 		},
 	 		addDetails : function(itemType, src, val, loc, options){
 	 	 		
 	 	 		src.disable();
 	 	 		
 	 	 		var container = $(loc);
 	 	 		var paramHash = new Hash(); //parameters to post to server
 	 	 		paramHash.set('task', 'add');
 	 	 		paramHash.set('currentItem', itemType);
				//add extra options to the parameter list
 	 	 		if(options){
 	 	 			paramHash.set('parentIndex', options.parentIndex);
 	 	 		}
 	 	 		paramHash.set(itemType,val);
 	 	 		this.populateDeafultParameters(itemType, paramHash);
 	 	 		
 	 	 		var url = $('command').action + "?subview"; //make the ajax request
				this.insertContent(container, url, paramHash, function() {
						src.enable();
						});
 	 		},
 	 		removeDetails :function(itemType,index, loc, options){
 	 	 		if(index < 0) return;

				var confirmation = confirm("Do you really want to delete?");
				if(!confirmation) return; //return if not agreed.
								
				var container = $(loc);
				
				var paramHash = new Hash(); //parameters to post to server
 	 	 		paramHash.set('task', 'remove');
 	 	 		paramHash.set('currentItem', itemType);
 	 	 		paramHash.set('index', index);
 	 	 		//add extra options to the parameter list
 	 	 		if(options){
 	 	 	 		if(options.parentIndex >= 0) paramHash.set('parentIndex', options.parentIndex);
 	 	 		}
 	 	 		this.populateDeafultParameters(itemType, paramHash);
 	 	 		
 	 	 		var url = $('command').action + "?subview"; //make the ajax request
 	 	 		var sectionHash = Form.serializeElements(this.formElementsInSection(container), true);
 	 	 		$(loc).innerHTML = '';
				this.insertContent(container, url, paramHash.merge(sectionHash));				
				
 	 	 		
 	 		},
 	 		populateDeafultParameters : function(itemType, paramHash){
 				//will populate the default parameters, to support ajax communication
 				var page = ${tab.number};
 				var target = '_target' + ${tab.number}; 
 				paramHash.set('_page', page);
 				paramHash.set(target, page);
 				paramHash.set('_asynchronous', true);
 				paramHash.set('decorator', 'nullDecorator');
 			},
 			insertContent : function(aContainer, url, params, onCompleteCallBack){
 				//helper method to insert content in a DIV
 				new Ajax.Updater(aContainer, url, {
 					parameters: params.toQueryString() , onComplete: onCompleteCallBack ,insertion: Insertion.Top, evalScripts : true
 				});
 			},
 			formElementsInSection : function(aContainer){
 	 			return aContainer.select('input', 'select', 'textarea');	
 			}
 		});

 		Event.observe(window, "load",setupPage);
		
		function setupPage(){
			mHistory = new mHistoryClass();//create a new mHistory object

			//--for metastatic diseases button 
			Element.observe('metastatic-diseases-btn', 'click', function(e){
			 	this.addDetails('metastaticDiseaseSite', e.element(), null, 'anchorMetastaticDiseases');

			 	//clear the fields
//			 	AE.resetAutocompleter('metastaticDiseaseSite');
		 	}.bind(mHistory));

			//--for pre-existing condition button
		 	Element.observe('pre-cond-btn', 'click', function(e){
/*
			 	var preCondField = $('preExistingCondition');
			    if(preCondField.selectedIndex < 1) {
                    alert('Select a value first.');
                    return
                }
*/
			    this.addDetails('preExistingCondition', e.element(), null, 'anchorPreExistingCondition');
			    preCondField.selectedIndex = 0;
			 	
		 	}.bind(mHistory));
		 	//-- for concomitant medication
		 	Element.observe('concomitantMedication-btn', 'click', function(e){
/*
			 	var conMedField = $('concomitantMedication');
			 	if(conMedField.value == '') {
                     alert('Type a value first.');
                     return
                 }
*/
			 	this.addDetails('concomitantMedication', e.element(), null, 'anchorConcomitantMedication');
			 	conMedField.value = '';
		 	}.bind(mHistory));
		 	
			//-- for prior therapy button
		 	Element.observe('priortherapy-btn','click', function(e){
/*
			 	var priorTherapyField = $('priorTherapy');
			 	if(priorTherapyField.selectedIndex < 1) {
                     alert('Select a value first.');
                     return
                 }
*/
			 	this.addDetails('priorTherapy', e.element(), null, 'anchorPriorTherapy');
			 	// priorTherapyField.selectedIndex = 0;
		 	}.bind(mHistory));

		 	Event.observe('command', 'submit', function(e){

				/* Below is a very ugly tweak did for IE7 if priorTherapyAgents[i]-input='begin', the value of priorTherapyAgents[i], is assumed by spring as its value 
				  But only happens in IE7
				  */
				// AE.resetAutocompleter('metastaticDiseaseSite');
				var i = 0;
				for(i = 0; i < 15; i++){
					var el = 	$('priorTherapyAgents[' + i + ']');
					if(el) el.value = '';
					if(el) $('priorTherapyAgents[' + i + ']-input').value = '';
				}
			});
		}
		function fireAction(type, index, loc, options, id){
			mHistory.removeDetails(type, index, loc, options);
		}
		
		        function showShowAllTable(el, baseName) {

            var parameterMap = getParameterMap('command');
            if (baseName.search("metastaticDiseaseSite")>=0  || baseName == 'codedPrimaryDiseaseSite') {
                createAE.buildAnatomicSiteTable(el, parameterMap, baseName, function(table) {
                    $('showAllDropDownContent').innerHTML = table;
//                    $('showAllDropDown').style.position = 'absolute';
                    try {
                        var _top = Position.cumulativeOffset($(el))[1];
                        var _left = Position.cumulativeOffset($(el))[0];

                        $('showAllDropDown').style.top = (_top-190) + "px";
                        $('showAllDropDown').style.left = (_left - 120) + "px";
                    } catch (e) {
//                        alert('2');
                    }
                    $('showAllDropDown').show();
                });
            } else {
                createAE.buildChemoAgentsTable(el, parameterMap, baseName, function(table) {
                    $('showAllDropDownContent').innerHTML = table;
                    try {
                        var _top = Position.cumulativeOffset($(el))[1];
                        var _left = Position.cumulativeOffset($(el))[0];

                        $('showAllDropDown').style.top = (_top-190) + "px";
                        $('showAllDropDown').style.left = (_left - 120) + "px";
                    } catch (e) {
//                        alert('2');
                    }
                    
                    $('showAllDropDown').show();
                });
            }
        }
        
        function fillDiseaseSiteAutoCompletor(val,baseName, text){
            if (baseName == 'codedPrimaryDiseaseSite') {
                baseName = 'assignment.diseaseHistory.codedPrimaryDiseaseSite'
            }

            if (baseName.indexOf('priorTherapyAgents') >= 0 || baseName.search("metastaticDiseaseSite")>=0) {
                baseName = baseName.replace(/DOT/g, ".") ;
                baseName = baseName.replace(/OPEN/g, "[") ;
                baseName = baseName.replace(/CLOSE/g, "]") ;
            }

            $(baseName).value = val;
		    $(baseName+ "-input").value = text;
		    $(baseName+ "-input").removeClassName('pending-search');
		   hideShowAllTable();
	   }

	   function fillChemoAgentAutoCompletor(val, baseName, text){
	   		if (baseName.indexOf('priorTherapyAgents') >= 0 || baseName.search("metastaticDiseaseSite") >= 0) {
                   baseName = baseName.replace(/DOT/g, ".") ;
                   baseName = baseName.replace(/OPEN/g, "[") ;
                   baseName = baseName.replace(/CLOSE/g, "]") ;
            }
	   		
	        $(baseName).value = val;
		    $(baseName+ "-input").value = text;
		    $(baseName+ "-input").removeClassName('pending-search');
		   hideShowAllTable();
		   
	   }
        
        function hideShowAllTable(){
		  $('showAllDropDown').hide();
	   }
		
	</script>
  </head>
  <body>
  	<div class="summary">
      <div class="row">
          <div class="label">Subject</div>
          <div class="value">${command.participant.fullName}</div>
	  </div>
	  <div class="row">
          <div class="label">Study</div>
          <div class="value">${command.study.shortTitle}</div>
      </div>
	  </div>
  <p><tags:instructions code="instruction_subject_enter.medhist.top"/></p>
   <form:form id="command">
   <div id="showAllDropDown" style="position: absolute; display: none; left: 300px; width:300px; z-index:99; top:0px;">
   <table width="100%" class="eXtremeTable" frame="border" border-color="blue" bgcolor="white">
     <tbody>
       <tr class="titleRow">
         <td align="left" class="title">Select :</td>
         <td align="right"><a href="javascript:hideShowAllTable()"><img src="/caaers/images/rule/window-close.gif" id="close-image"/></a></td>
       </tr>
       <tr>
         <td colspan="2"><div id="showAllDropDownContent"/></td>
       </tr>
     </tbody>
   </table>
 </div>

   <tags:hasErrorsMessage path="*" />

    <tags:tabFields tab="${tab}" />

	<chrome:box id="assignment.general" title="General" collapsable="true">
	   <p><tags:instructions code="instruction_subject_enter.medhist.gen"/></p>
		<tags:hasErrorsMessage path="assignment.baselinePerformance" />
		<a name="anchorGeneral" />
		<div id="anchorGeneral">
			<ui:row path="assignment.baselinePerformance">
				<jsp:attribute name="label">
					<ui:label text="Baseline performance" path="assignment.baselinePerformance" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:select options="${baselinePerformanceOptions}" path="assignment.baselinePerformance" />
				</jsp:attribute>
			</ui:row>
		</div>
	</chrome:box>

	<chrome:box id="assignment.diseaseHistory" title="Disease Information" collapsable="true">
		
     <p><tags:instructions code="instruction_subject_enter.medhist.disease"/></p>
		<tags:hasErrorsMessage path="assignment.diseaseHistory.*" />
		<div id="anchorDiseaseInfo">

			<ui:row path="assignment.diseaseHistory.abstractStudyDisease">
				<jsp:attribute name="label">
					<ui:label path="assignment.diseaseHistory.abstractStudyDisease" text="Disease name" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:select options="${studyDiseasesOptions}" path="assignment.diseaseHistory.abstractStudyDisease">
						<jsp:attribute name="embededJS">
							<%--
							  Note :- If disease is Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS', other disease should be provided
							--%>
							$('assignment.diseaseHistory.abstractStudyDisease').observe('change', function(evt){
								var el = $(evt.element());
								var optionText = el.options[el.selectedIndex].text;
								if(optionText == 'Solid tumor, NOS' || optionText == 'Hematopoietic malignancy, NOS'){
									AE.slideAndShow("assignment.diseaseHistory.otherPrimaryDisease-row")
								}else{
									$('assignment.diseaseHistory.otherPrimaryDisease').value = ''
       		 	 					AE.slideAndHide("assignment.diseaseHistory.otherPrimaryDisease-row")
								}
							});
						</jsp:attribute>
					</ui:select>
				</jsp:attribute>
			</ui:row>

			<ui:row path="assignment.diseaseHistory.otherPrimaryDisease" style="display:none;">
				<jsp:attribute name="label">
					<ui:label path="assignment.diseaseHistory.otherPrimaryDisease" text="Other (disease)" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="assignment.diseaseHistory.otherPrimaryDisease"  />
				</jsp:attribute>
			</ui:row>
<%--problem begins here--%>
			<ui:row path="assignment.diseaseHistory.codedPrimaryDiseaseSite">
				<jsp:attribute name="label">
					<ui:label path="assignment.diseaseHistory.codedPrimaryDiseaseSite" text="Primary site of disease" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:autocompleter path="assignment.diseaseHistory.codedPrimaryDiseaseSite"
					  initialDisplayValue="${empty command.assignment.diseaseHistory.codedPrimaryDiseaseSite ? 'Begin typing here...' : command.assignment.diseaseHistory.codedPrimaryDiseaseSite.name}">
						<jsp:attribute name="populatorJS">
							function(autocompleter, text) {
                				createAE.matchAnatomicSite(text, function(values) {
                    				autocompleter.setChoices(values)
                				})
            				}
						</jsp:attribute>
						<jsp:attribute name="selectorJS">
							function(obj){
								return obj.name;
							}
						</jsp:attribute>
						<jsp:attribute name="optionsJS">
							{
								afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
									//show the otherPrimaryDiseaseSite box below, using javascript
									$('assignment.diseaseHistory.codedPrimaryDiseaseSite').value = selectedChoice.id
									if(selectedChoice.id == '110'){
										AE.slideAndShow("assignment.diseaseHistory.otherPrimaryDiseaseSite-row");
									}else{
										$("assignment.diseaseHistory.otherPrimaryDiseaseSite").value=""
        								AE.slideAndHide("assignment.diseaseHistory.otherPrimaryDiseaseSite-row")
									}
								}
							}
						</jsp:attribute>
					</ui:autocompleter>
					<%-- The line below used to be an <a> tag but it was causing some bizzare issue in IE causing part of the chrome box to not render.
							I (David) have changed it to a span as a workaround. I tried making it a button, but that caused another bizzare issue in IE.
							In the future if you want to make this a button AND the 'Show All' in the box below this one a button also, you will need to resolve that IE issue--hovering on this button will cause the below button to dissappear.
							Good luck.  --%>
                    <span style="cursor:pointer; color:blue; text-decoration:underline;" onclick="javascript:showShowAllTable('_c1', 'codedPrimaryDiseaseSite')" id="_c1">Show All</span>
                </jsp:attribute>
			</ui:row>
<%--problem ends here--%>
			<ui:row path="assignment.diseaseHistory.otherPrimaryDiseaseSite" style="display:none;">
				<jsp:attribute name="label">
					<ui:label path="assignment.diseaseHistory.otherPrimaryDiseaseSite" text="Other (site of primary disease)" required="true" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:text path="assignment.diseaseHistory.otherPrimaryDiseaseSite" />
				</jsp:attribute>
			</ui:row>

			<ui:row path="assignment.diseaseHistory.diagnosisDate">
				<jsp:attribute name="label">
					<ui:label path="assignment.diseaseHistory.diagnosisDate" text="Date of initial diagnosis" />
				</jsp:attribute>
				<jsp:attribute name="value">
					<ui:splitDate path="assignment.diseaseHistory.diagnosisDate" />
				</jsp:attribute>
			</ui:row>
		</div>
	</chrome:box>


	<chrome:box id="assignment.diseaseHistory.metastaticDiseaseSites" title="Metastatic Disease Site" collapsable="true">
    <p><tags:instructions code="instruction_subject_enter.medhist.meta"/></p>
		<tags:hasErrorsMessage path="assignment.diseaseHistory.metastaticDiseaseSites.*" />
		
		<table class="tablecontent" width="80%" style="padding-left:50px;">
			<tr>
				<td style="padding-left:50px;">
                    &nbsp;
                    <input id="metastatic-diseases-btn" type="button" value="Add"/>
                    <%--<a href="#anchorMetastaticDiseasesSection" onClick="showShowAllTable('_c2', 'metastaticDiseaseSite')" id="_c2">Show All</a>--%>
                </td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left:50px;">
					<a name="anchorMetastaticDiseases" />
					<div id="anchorMetastaticDiseases">
						<c:set var="size" value="${fn:length(command.assignment.diseaseHistory.metastaticDiseaseSites)}" />
						<c:forEach items="${command.assignment.diseaseHistory.metastaticDiseaseSites}" var="mds" varStatus="status">
							<c:set var="newIndex" value="${size - (status.index + 1)}" />
							<c:set var="mSite" value="${command.assignment.diseaseHistory.metastaticDiseaseSites[newIndex]}" />
							<par:oneMetastaticDiseaseSite index="${newIndex}" anatomicSite="${mSite.codedSite}"/>
						</c:forEach>
					</div>
                </td>
			</tr>
		</table>
	</chrome:box>

	<chrome:box id="assignment.preExistingConditions" title="Pre-existing Conditions" collapsable="true">
    <p><tags:instructions code="instruction_subject_enter.medhist.pre"/></p>
		<tags:hasErrorsMessage path="assignment.preExistingConditions.*" />
		<table width="100%">
			<tr>
				<td width="100%" style="padding-left:50px;">
                    &nbsp;
                    <input id="pre-cond-btn" type="button" value="Add"/>
                </td>
				<td width="10%">

				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left:50px;">
					<a name="anchorPreExistingCondition" />
					<div id="anchorPreExistingCondition">
						<c:set var="size" value="${fn:length(command.assignment.preExistingConditions)}" />
						<c:forEach items="${command.assignment.preExistingConditions}" varStatus="status">
							<c:set var="newIndex" value="${size - (status.index + 1)}" />
							<c:set var="pCond" value="${command.assignment.preExistingConditions[newIndex]}" />
							<par:onePreExistingCondition index="${newIndex}" preExistingCondition="${pCond.preExistingCondition}" />
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
	</chrome:box>

	<chrome:box id="assignment.concomitantMedications" title="ConMeds" collapsable="true">
    <p><tags:instructions code="instruction_subject_enter.medhist.conmeds"/></p>
		<tags:hasErrorsMessage path="assignment.concomitantMedications.*" />
		<tags:hasErrorsMessage path="concomitantMedication" />
		<table class="tablecontent" width="80%">
			<tr>
				<td width="90%" style="padding-left:50px;">
                    &nbsp;
                    <input id="concomitantMedication-btn" type="button" value="Add"/>
                </td>
				<td width="10%">

				</td>
			</tr>
			<tr>
				<td colspan="2" style="padding-left:50px;">
					<a name="anchorConcomitantMedication" />
					<div id="anchorConcomitantMedication">
						<c:set var="size" value="${fn:length(command.assignment.concomitantMedications)}" />
						<c:forEach items="${command.assignment.concomitantMedications}" varStatus="status">
							<c:set var="newIndex" value="${size - (status.index + 1)}" />
							<c:set var="conMed" value="${command.assignment.concomitantMedications[newIndex]}" />
							<par:oneConcomitantMedication index="${newIndex}" concomitantMedication="${conMed}" collapsed="true" />
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
	</chrome:box>

	<chrome:box id="assignment.priorTherapies" title="Prior Therapies" collapsable="true">
    <p><tags:instructions code="instruction_subject_enter.medhist.pt"/></p>
		<tags:hasErrorsMessage path="assignment.priorTherapies.*" />
<%--
		<tags:hasErrorsMessage path="priorTherapyAgents" />
		<tags:hasErrorsMessage path="priorTherapy" />
--%>

		<table class="tablecontent" width="80%">
			<tr>
				<td colspan="2" style="padding-left:50px;">
                    <input id="priortherapy-btn" type="button" value="Add"/>
					<a name="anchorPriorTherapy" />
					<div id="anchorPriorTherapy">
						<c:set var="size" value="${fn:length(command.assignment.priorTherapies)}" />
						<c:forEach items="${command.assignment.priorTherapies}" varStatus="status">
							<c:set var="newIndex" value="${size - (status.index + 1)}" />
							<c:set var="ptherapy" value="${command.assignment.priorTherapies[newIndex]}" />
							<par:onePriorTherapy index="${newIndex}" priorTherapy="${ptherapy}" collapsed="true" />
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>
	</chrome:box>

    <tags:tabControls flow="${flow}" tab="${tab}" />
    <c:if test="${_finish}"><input type="hidden" name="_finish"/></c:if>
   </form:form>
  </body>
</html>