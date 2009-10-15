<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
<title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>
	<tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}"
		display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}">
    	<jsp:attribute name="comments">
    		<div id="comments-id" style="display:none;">
    			<tags:routingAndReviewComments />
    		</div>
    	</jsp:attribute>
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>

<script type="text/javascript">
		var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
		var mHistory = null;
 		var mHistoryClass = Class.create();
 		Object.extend(mHistoryClass.prototype, {
 	 		initialize: function(){ 
 	 		},
             
              addDetails : function(itemType, src, val, loc, options){
 	 	 		
 	 	 		// src.disable();
				// this.showIndicator(src.id + "-indicator");	 	 		
 	 	 		var container = $(loc);
 	 	 		var paramHash = new Hash(); //parameters to post to server
 	 	 		paramHash.set('task', 'add');
 	 	 		paramHash.set('currentItem', itemType);
				//add extra options to the parameter list
 	 	 		if(options){
 	 	 			paramHash.set('parentIndex', options.parentIndex);
 	 	 		}
 	 	 		// paramHash.set(itemType, val);
 	 	 		this.populateDeafultParameters(itemType, paramHash);
 	 	 		
 	 	 		var url = $('command').action + "?subview"; //make the ajax request
				this.insertContent(container, url, paramHash, function() {
						// src.enable();
						// this.hideIndicator(src.id + "-indicator");
						}.bind(this));
 	 		},
             
              removeDetails :function(itemType,index, loc, options){
 	 	 		if(index < 0) return;
				
				var confirmation = confirm("Do you really want to delete?");
				if(!confirmation) return; //return if not agreed.
				// this.showIndicator(itemType+"-indicator");				
				var container = $(loc);
                  
                var paramHash = new Hash(); //parameters to post to server
 	 	 		paramHash.set('task', 'remove');
 	 	 		paramHash.set('currentItem', itemType);
 	 	 		paramHash.set('index', index);

//                  alert(itemType);

                //add extra options to the parameter list
 	 	 		if(options){
 	 	 	 		if(options.parentIndex >= 0) paramHash.set('parentIndex', options.parentIndex);
 	 	 		}
 	 	 		this.populateDeafultParameters(itemType, paramHash);
 	 	 		
 	 	 		var url = $('command').action + "?subview"; //make the ajax request
 	 	 		var sectionHash = Form.serializeElements(this.formElementsInSection(container), true);
 	 	 		var newLoc = replaceHtml($(loc) , '');

                this.insertContent(newLoc, url, paramHash.merge(sectionHash), function () {
					// this.hideIndicator(itemType + "-indicator");
				}.bind(this));
 	 	 		
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
                 // alert(params.toQueryString());
                 new Ajax.Updater(aContainer, url, {
 					parameters: params.toQueryString(), onComplete: onCompleteCallBack, insertion: Insertion.Top, evalScripts : true
 				});
 			},
             
             showIndicator :  function(name){
 				var idiv = $(name); 
 	 	 		if(idiv) idiv.removeClassName('indicator');
 			},
 			hideIndicator : function(name){
 				var idiv = $(name); 
 	 	 		if(idiv) idiv.addClassName('indicator');
 			},
 			formElementsInSection : function(aContainer){
 	 			return aContainer.select('input', 'select', 'textarea');	
 			},
 			indicatorImageId :function(itemType){
 	 			if(itemType == '' ) return '';
 	 			if(itemType == '' ) return '';
 	 			if(itemType == '' ) return '';
 	 			if(itemType == '' ) return '';
 	 			if(itemType == '' ) return '';
 			}
 		});

 		Event.observe(window, "load",setupPage);

        function addMetastaticDisease() {
            mHistory.addDetails('metastaticDiseaseSite', null, null, 'anchorMetastaticDiseases');
        }

        function addPreexistingCondition() {
            mHistory.addDetails('preExistingCondition', null, null, 'anchorPreExistingCondition');
        }

        function addConMeds() {
            mHistory.addDetails('concomitantMedication', null, null, 'anchorConcomitantMedication');
        }

        function addPriorTherapy() {
            mHistory.addDetails('priorTherapy', null, null, 'anchorPriorTherapy');
        }

        function setupPage(){
			//only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
 	          	routingHelper.retrieveReviewCommentsAndActions.bind(routingHelper)();
            }
		
			mHistory = new mHistoryClass();//create a new mHistory object

/*
			//--for metastatic diseases button
			Element.observe('metastatic-diseases-btn', 'click', function(e){
			 	this.addDetails('metastaticDiseaseSite', e.element(), null, 'anchorMetastaticDiseases');
		 	}.bind(mHistory));

            //--for pre-existing condition button
		 	Element.observe('pre-cond-btn', 'click', function(e){
			    this.addDetails('preExistingCondition', e.element(), null, 'anchorPreExistingCondition');
		 	}.bind(mHistory));
            
            //-- for concomitant medication
		 	Element.observe('concomitantMedication-btn', 'click', function(e){
			 	this.addDetails('concomitantMedication', e.element(), null, 'anchorConcomitantMedication');
		 	}.bind(mHistory));
		 	
			//-- for prior therapy button
		 	Element.observe('priortherapy-btn','click', function(e){
			 	this.addDetails('priorTherapy', e.element(), null , 'anchorPriorTherapy');
		 	}.bind(mHistory));
*/

			Event.observe('command', 'submit', function(e){

				/* Below is a very ugly tweak did for IE7 if priorTherapyAgents[i]-input='begin', the value of priorTherapyAgents[i], is assumed by spring as its value 
				  But only happens in IE7
				  */
				AE.resetAutocompleter('metastaticDiseaseSite');
				var i = 0;
				for(i = 0; i < 15; i++){
					var el = 	$('priorTherapyAgents[' + i + ']');
					if(el) el.value = '';
					if(el) $('priorTherapyAgents[' + i + ']-input').value = '';
				}
			});
			
		 	//-- find the bsa
		 	findBSA();

            //
            var el = $('aeReport.diseaseHistory.abstractStudyDisease');
            var optionText = el.options[el.selectedIndex].text;
            if (optionText == 'Solid tumor, NOS' || optionText == 'Hematopoietic malignancy, NOS') {
                AE.slideAndShow("${fieldGroups['disease'].fields[1].propertyName}-row")
            } else {
                $('${fieldGroups['disease'].fields[1].propertyName}').value = ''
                AE.slideAndHide("${fieldGroups['disease'].fields[1].propertyName}-row")
            }
            

		}
		function fireAction(type, index, loc, options, id){
            mHistory.removeDetails(type, index, loc, options);
		}

	   function findBSA(){
	   	  var ht = $F('aeReport.participantHistory.height.quantity');
	   	  var htUOM = $F('aeReport.participantHistory.height.unit');
	   	  var wt = $F('aeReport.participantHistory.weight.quantity');
	   	  var wtUOM = $F('aeReport.participantHistory.weight.unit');
	   	  createAE.calculateBodySurfaceArea(ht, htUOM, wt, wtUOM,function(bsa){
	   	    if(bsa > 0) Element.update($('bsa-value'), bsa.toFixed(4));
	   	  });
	   }


        function showShowAllTable(el, baseName) {

            var parameterMap = getParameterMap('command');

            if (baseName.search("metastaticDiseaseSite")>=0 || baseName == 'codedPrimaryDiseaseSite') {
                    createAE.buildAnatomicSiteTable(el, parameterMap, baseName, function(table) {
                    $('showAllDropDownContent').innerHTML = table;
//                    $('showAllDropDown').style.position = 'absolute';
                    try {
                        var _top = Position.cumulativeOffset($(el))[1];
                        var _left = Position.cumulativeOffset($(el))[0];

                        $('showAllDropDown').style.top = (_top - 190) + "px";
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

        function fillDiseaseSiteAutoCompletor(val, baseName, text){
            if (baseName == 'codedPrimaryDiseaseSite') {
                baseName = 'aeReport.diseaseHistory.codedPrimaryDiseaseSite'
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

	   function fillChemoAgentAutoCompletor(val, baseName, text) {

            if (baseName.indexOf('priorTherapyAgents') >= 0 || baseName.search("metastaticDiseaseSite") >=0) {
                baseName = baseName.replace(/DOT/g, ".") ;
                baseName = baseName.replace(/OPEN/g, "[") ;
                baseName = baseName.replace(/CLOSE/g, "]") ;
            }

           $(baseName+ "-input").value = text;
           $(baseName).value = val;
           $(baseName+ "-input").removeClassName('pending-search');

           hideShowAllTable();
	   }
	   
	   function hideShowAllTable() {
		  $('showAllDropDown').hide();
	   }
	   
	</script>
<style>
.tablecontent td {
	border:0px;
}
</style>
</head>
<body>
<p><tags:instructions code="instruction_ae_patientdetails"/></p>
<form:form id="command">
<chrome:flashMessage/>
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
  <tags:tabFields tab="${tab}" />
  <tags:hasErrorsMessage />
  <tags:jsErrorsMessage/>
  <chrome:box id="aeReport.participantHistory" title="General" collapsable="true">
    <p><tags:instructions code="instruction_ae_patientdetails_general"/></p>
		<a name="anchorGeneral" />
		<div id="anchorGeneral">
			<table width="100%">
			<tr>
			 
			  <td width="50%">
				<tags:renderRow field="${fieldGroups['general'].fields[0]}"/>
				<ui:row path="${fieldGroups['participant'].fields[0].propertyName}">
					<jsp:attribute name="label">
						<tags:renderLabel field="${fieldGroups['participant'].fields[0]}" />
					</jsp:attribute>
					<jsp:attribute name="value">
						<tags:renderInputs field="${fieldGroups['participant'].fields[0]}" />
					</jsp:attribute>
					<jsp:attribute name="embededJS">
						Event.observe('aeReport.participantHistory.height.quantity','blur' ,findBSA);
		   	    		Event.observe('aeReport.participantHistory.height.unit','change',findBSA);
					</jsp:attribute>
				</ui:row>
				<ui:row path="${fieldGroups['participant'].fields[1].propertyName}">
					<jsp:attribute name="label">
						<tags:renderLabel field="${fieldGroups['participant'].fields[1]}" />
					</jsp:attribute>
					<jsp:attribute name="value">
						<tags:renderInputs field="${fieldGroups['participant'].fields[1]}" />
					</jsp:attribute>
					<jsp:attribute name="embededJS">
						Event.observe('aeReport.participantHistory.weight.quantity','blur',findBSA);
		   	    		Event.observe('aeReport.participantHistory.weight.unit','change',findBSA);
					</jsp:attribute>
				</ui:row>
				
				<div class="row">
			        <div class="label">Body surface area</div>
			        <div class="value"><span id="bsa-value">  </span></div>
			    </div>
				
			</td>
			<td>
	    		<div class="row">
	        		<div class="label">Birth date</div>
	        		<div class="value">${command.participant.dateOfBirth}</div>
	    		</div>
	
	    		<div class="row">
	        		<div class="label">Race</div>
	        		<div class="value">${command.participant.race}</div>
	    		</div>
	
	    		<div class="row">
	        		<div class="label">Ethnicity</div>
	        		<div class="value">${command.participant.ethnicity}</div>
	    		</div>
	
		   		<div class="row">
		        	<div class="label">Gender</div>
		        	<div class="value">${command.participant.gender}</div>
		   		</div>
			</td>
		  </tr>
		 </table>
		</div>
	</chrome:box>
	  


    <chrome:box id="aeReport.diseaseHistory" title="Disease Information" collapsable="true">
      <tags:instructions code="instruction_ae_patientdetails_diseaseinfo"/>
    <a name="anchorDiseaseInfo"></a>
    <div id="anchorDiseaseInfo">
      <ui:row path="${fieldGroups['disease'].fields[0].propertyName}">
        <jsp:attribute name="label">
          <tags:renderLabel field="${fieldGroups['disease'].fields[0]}" />
        </jsp:attribute>
        <jsp:attribute name="value">
          <tags:renderInputs field="${fieldGroups['disease'].fields[0]}" />
        </jsp:attribute>
        <jsp:attribute name="embededJS">
          <%-- 
					  Note :- If disease is Disease Name is  'Solid tumor, NOS' or 'Hematopoietic malignancy, NOS', other disease should be provided 
					--%>
          $('${fieldGroups['disease'].fields[0].propertyName}').observe('change', function(evt){
          var el = $(evt.element());
          var optionText = el.options[el.selectedIndex].text;
          if(optionText == 'Solid tumor, NOS' || optionText == 'Hematopoietic malignancy, NOS'){
          AE.slideAndShow("${fieldGroups['disease'].fields[1].propertyName}-row")
          }else{
          $('${fieldGroups['disease'].fields[1].propertyName}').value = ''
          AE.slideAndHide("${fieldGroups['disease'].fields[1].propertyName}-row")
          }
          }); </jsp:attribute>
      </ui:row>

      <tags:renderRow field="${fieldGroups['disease'].fields[1]}" style="display: none" />
      <c:set var="cpsField" value="${fieldGroups['disease'].fields[2]}" />
      <c:set var="opsField" value="${fieldGroups['disease'].fields[3]}" />
      <ui:row path="${cpsField.propertyName}">
        <jsp:attribute name="label">
          <tags:renderLabel field="${cpsField}" />
        </jsp:attribute>
        <jsp:attribute name="value">
          <ui:autocompleter path="${cpsField.propertyName}"
					  initialDisplayValue="${empty command.aeReport.diseaseHistory.codedPrimaryDiseaseSite ? 'Begin typing here...' : command.aeReport.diseaseHistory.codedPrimaryDiseaseSite.name}" enableClearButton="true">
            <jsp:attribute name="populatorJS"> function(autocompleter, text) {
              createAE.matchAnatomicSite(text, function(values) {
              autocompleter.setChoices(values)
              })
              } </jsp:attribute>
            <jsp:attribute name="selectorJS"> function(obj){
              return obj.name;
              } </jsp:attribute>
            <jsp:attribute name="optionsJS"> {
              afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
              //show the otherPrimaryDiseaseSite box below, using javascript
              $('${cpsField.propertyName}').value = selectedChoice.id
              if(selectedChoice.id == '110'){
              AE.slideAndShow("${opsField.propertyName}-row");
              }else{
              $("${opsField.propertyName}").value=""
              AE.slideAndHide("${opsField.propertyName}-row")
              }
              }
              } </jsp:attribute>
          </ui:autocompleter>
          <a style='cursor:pointer; floating:right; color:blue; text-decoration:underline;' onClick="showShowAllTable('_c1', 'codedPrimaryDiseaseSite')" id="_c1">Show All</a> </jsp:attribute>

      </ui:row>
      <tags:renderRow field="${opsField}" style="${command.aeReport.diseaseHistory.codedPrimaryDiseaseSite.id eq 110 ? '' :'display:none;'}"/>
      <tags:renderRow field="${fieldGroups['disease'].fields[4]}" />
    </div>
    
  </chrome:box>





  <a name="anchorMetastaticDiseasesSection" />
  <chrome:box id="aeReport.diseaseHistory.metastaticDiseaseSites" title="Metastatic Disease Site" collapsable="true" >
      <jsp:body>
        <p><tags:instructions code="instruction_ae_patientdetails_metadiseasesite"/></p>

          <div style="padding-left:20px;">
              <tags:button cssClass="foo" id="metastatic-diseases-btn" color="blue" value="Add" icon="Add" type="button" onclick="addMetastaticDisease();" size="small"/>
          <div id="_metastatic">
              <tags:indicator id="metastatic-diseases-btn-indicator" />

              <!--<a name="anchorMetastaticDiseases"/>-->
              <tags:indicator id="metastaticDiseaseSite-indicator"/>
              <div id="anchorMetastaticDiseases">
                  <c:set var="size" value="${fn:length(command.aeReport.diseaseHistory.metastaticDiseaseSites)}"/>
                  <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSites}" var="mds" varStatus="status">
                      <c:set var="newIndex" value="${size - (status.index + 1)}"/>
                      <c:set var="mSite" value="${command.aeReport.diseaseHistory.metastaticDiseaseSites[newIndex]}"/>
                      <ae:oneMetastaticDiseaseSite index="${newIndex}" anatomicSite="${mSite.codedSite}" otherSite="${mSite.otherSite}"/>
                  </c:forEach>
              </div>
          </div>
          </div>

      </jsp:body>
  </chrome:box>






  <chrome:box id="aeReport.saeReportPreExistingConditions" title="Pre-Existing Conditions" collapsable="true">
        <jsp:body>
            <p><tags:instructions code="instruction_ae_patientdetails_precond"/></p>
            <div style="padding-left:20px;">
                <tags:button cssClass="foo" id="pre-cond-btn" color="blue" value="Add" icon="Add" type="button" onclick="addPreexistingCondition();" size="small"/>
                <tags:indicator id="pre-cond-btn-indicator" />

            <div id="_preExistingConditions">
                <a name="anchorPreExistingCondition"/>
                <tags:indicator id="preExistingCondition-indicator"/>
                <div id="anchorPreExistingCondition">
                    <c:set var="size" value="${fn:length(command.aeReport.saeReportPreExistingConditions)}" />
                    <c:forEach items="${command.aeReport.saeReportPreExistingConditions}" varStatus="status">
                      <c:set var="newIndex" value="${size - (status.index + 1)}" />
                      <c:set var="pCond" value="${command.aeReport.saeReportPreExistingConditions[newIndex]}" />
                      <ae:onePreExistingCond index="${newIndex}" preExistingCondition="${pCond.preExistingCondition}" otherValue="${pCond.other}"/>
                    </c:forEach>
                </div>
            </div>
            </div>

      </jsp:body>
  </chrome:box>






  <chrome:box id="aeReport.concomitantMedications" title="Concomitant Medications" collapsable="true">
      <jsp:body>

  <p><tags:instructions code="instruction_ae_patientdetails_conmeds"/></p>

      <div style="padding-left:20px;">
      <div id="">
          <tags:button cssClass="foo" id="concomitantMedication-btn" color="blue" value="Add" icon="Add" type="button" onclick="addConMeds();" size="small"/>
          <tags:indicator id="concomitantMedication-btn-indicator"/>

          <a name="anchorConcomitantMedication"/>
          <tags:indicator id="preExistingCondition-indicator"/>
          <div id="anchorConcomitantMedication">
              <c:set var="size" value="${fn:length(command.aeReport.concomitantMedications)}" />
              <c:forEach items="${command.aeReport.concomitantMedications}" varStatus="status">
                  <c:set var="newIndex" value="${size - (status.index + 1)}" />
                  <c:set var="conMed" value="${command.aeReport.concomitantMedications[newIndex]}" />
                  <ae:oneConMed index="${newIndex}" concomitantMedication="${conMed}" collapsed="true" />
              </c:forEach>
          </div>
      </div>
      </div>
          
      </jsp:body>
  </chrome:box>





<chrome:box id="aeReport.saeReportPriorTherapies" title="Prior Therapies" collapsable="true">
    <jsp:body>

    <p><tags:instructions code="instruction_ae_patientdetails_priortherapies"/></p>

    <div style="padding-left:20px;">
    <div id="priorTherapy">
    <c:set var="size" value="${fn:length(command.aeReport.saeReportPriorTherapies)}" />
    
        <tags:button id="priortherapy-btn" 
        	cssClass="foo"
        	color="blue" value="Add" 
        	icon="Add" 
        	type="button" 
        	onclick="addPriorTherapy();" 
        	size="small" 
        	disabled="${(size gt 0 and command.aeReport.saeReportPriorTherapies[0].priorTherapy.id eq _priorTherapy_nopriortherapy_id) ? 'true' : ''}"/>
        <!--<input id="priortherapy-btn" type="button" value="Add" ${size gt 0 and command.aeReport.saeReportPriorTherapies[0].priorTherapy.id eq _priorTherapy_nopriortherapy_id}/>-->
        <tags:indicator id="priortherapy-btn-indicator" />

        <a name="anchorPriorTherapy"/>
        <tags:indicator id="priorTherapy-indicator" />
        <div id="anchorPriorTherapy">
            
            <c:forEach items="${command.aeReport.saeReportPriorTherapies}" varStatus="status">
              <c:set var="newIndex" value="${size - (status.index + 1)}" />
              <c:set var="ptherapy" value="${command.aeReport.saeReportPriorTherapies[newIndex]}" />
              <ae:onePriorTherapy index="${newIndex}" priorTherapy="${ptherapy}" collapsed="true" showNoPriorTherapy="${(size eq 1 and newIndex eq 0) and (empty ptherapy.priorTherapy or empty ptherapy.priorTherapy.text or ptherapy.priorTherapy.id eq _priorTherapy_nopriortherapy_id)}" />
            </c:forEach>
        </div>
    </div>
    </div>

    </jsp:body>
</chrome:box>
        <ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
  <tags:tabControls flow="${flow}" tab="${tab}" />
</form:form>
</body>
</html>
