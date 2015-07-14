<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:dwrJavascriptLink objects="createAE"/>

<tags:slider renderComments="${command.associatedToWorkflow }" renderAlerts="${command.associatedToLabAlerts}" reports="${command.selectedReportsAssociatedToWorkflow}" 
		display="${(command.associatedToWorkflow or command.associatedToLabAlerts) ? '' : 'none'}" workflowType="report">
    	<jsp:attribute name="labs">
    		<div id="labs-id" style="display:none;">
    			<tags:labs labs="${command.assignment.labLoads}"/>
    		</div>
    	</jsp:attribute>
    </tags:slider>

    <style type="text/css">
        div.row div.label { width: 16em; }
        div.row div.value { margin-left: 17em;}
        textarea { width: 20em; height: 5em; }
        img._boxImage_ { border : 1px blue dotted;}
    </style>
</head>
<body>
<script language="JavaScript">
    AE.radiationInterventionSize = ${fn:length(command.aeReport.radiationInterventions)};
    AE.surgeryInterventionSize =   ${fn:length(command.aeReport.surgeryInterventions)};
</script>
<script language="JavaScript">
var divisions = new Hash();
var routingHelper = new RoutingAndReviewHelper(createAE, 'aeReport');
var biologicalMap = new Hash();
var behavioralMap =  new Hash();
var geneticMap = new Hash();
var dietaryMap =  new Hash();
var otherAEMap = new Hash();

<c:forEach items="${command.study.activeStudyBiologicalInterventions}" var="i">
    biologicalMap.set("${i.id}", "${i.description}");
</c:forEach>

<c:forEach items="${command.study.activeStudyBehavioralInterventions}" var="i">
   behavioralMap.set("${i.id}", "${i.description}");
</c:forEach>

<c:forEach items="${command.study.activeStudyGeneticInterventions}" var="i">
   geneticMap.set("${i.id}", "${i.description}");
</c:forEach>

<c:forEach items="${command.study.activeStudyDietaryInterventions}" var="i">
   dietaryMap.set("${i.id}", "${i.description}");
</c:forEach>

<c:forEach items="${command.study.activeStudyOtherInterventions}" var="i">
   otherAEMap.set("${i.id}", "${i.description}");
</c:forEach>



function refreshBoxes() {
    registerAll();
    closeAll();
}

function registerAll() {
    var list = $$('div.division');
    divisions = new Hash();
    for (i=0; i<list.length; i++) {
        divisions.set(list[i].id, true);
    }
}

function closeAll() {
    divisions.each(function(pair) {

        var _id = pair.key;
        panelDiv = $("contentOf-" + _id);
        imageId= 'image-' + _id;
        imageSource = $(imageId).src;
        
        CloseDown(panelDiv, arguments[1] || {});
        document.getElementById(imageId).src = imageSource.replace('down','right');

//        alert(pair.key + ' = "' + pair.value + '"');
    });
}

//==================== Will get the description of other intervention ================
function updateOtherInterventionDescription(selbox, dSpanId){
    var v = selbox.getValue();
    $(dSpanId).innerHTML = '';
    if(v){
        createAE.retrieveOtherInterventionDescription(v, function(ajaxOutput){
            if(ajaxOutput.htmlContent !=  null  ) $(dSpanId).innerHTML = ajaxOutput.htmlContent;
        });
    }
}
//====================================================================================

//==================== Will get the study device ================
function updateMedicalDevice(i, studyDeviceId){
   var baseName = 'aeReport.medicalDevices[' + i + '].';
   createAE.retrieveStudyDevice(studyDeviceId, function(ajaxOutput){
      var d = ajaxOutput.objectContent;

      [ "manufacturerName" , "manufacturerCity", "manufacturerState",  "catalogNumber", "modelNumber"].each(function(n){
         var e = $(baseName + n);
         if(e)e.value = d[n];
      });


       ["brandName", "commonName", "deviceType" ].each(function(n){
           var e = $(baseName + n);
           if(e)e.innerHTML = d[n];
       });
      
   });
}
//====================================================================================

    Event.observe(window, "load", setupPage);
    divisions = new Hash(); 

    function setupPage(){
         //only show the workflow tab, if it is associated to workflow
            var associatedToWorkflow = ${command.associatedToWorkflow};
            if(associatedToWorkflow){
            	<c:forEach items="${command.selectedReportsAssociatedToWorkflow}" var="report" varStatus="status">
	 	          	routingHelper.retrieveReviewCommentsAndActions('${report.id}');
 	          	</c:forEach>
            }

        interventionInstance = new InterventionClass();
        <c:if test="${command.investigationalAgentAdministeredForPreviousReports}">
            if($('aeReport.treatmentInformation.investigationalAgentAdministered').value == '') {
                $('aeReport.treatmentInformation.investigationalAgentAdministered').value = 'true';
            }
         </c:if>
    }

    function addAgent() {
        interventionInstance._addItem('agent', null, null, '_agents');
    }
    function addRadiation() {
        if(AE.radiationInterventionSize > 0) return;
        interventionInstance._addItem('radiation', null, null, '_radiations');
        AE.radiationInterventionSize = AE.radiationInterventionSize + 1;
        $('btn-add-radiation').hide();
    }
    function addDevice() {
        interventionInstance._addItem('device', null, null, '_devices');
    }
    function addSurgery() {
        if(AE.surgeryInterventionSize > 0 ) return;
        interventionInstance._addItem('surgery', null, null, '_surgeries');
        AE.surgeryInterventionSize = AE.surgeryInterventionSize + 1;
        $('btn-add-surgery').hide();
    }
    function addBehavioral() {
        interventionInstance._addItem('behavioral', null, null, '_behaviorals');
    }

    function addBiological() {
        interventionInstance._addItem('biological', null, null, '_biologicals');
    }

    function addGenetic() {
        interventionInstance._addItem('genetic', null, null, '_genetics');
    }

    function addDietary() {
        interventionInstance._addItem('dietary', null, null, '_dietaries');
    }

    function addOtherAE() {
        interventionInstance._addItem('otherAE', null, null, '_otherAEs');
    }

    function fireAction(itemType, index, location, elementId, css) {
        interventionInstance._deleteItem(itemType, index, location);
        if(itemType == 'radiation'){
            AE.radiationInterventionSize = AE.radiationInterventionSize - 1;
        }

        if(itemType == 'surgery'){
            AE.surgeryInterventionSize = AE.surgeryInterventionSize - 1 ;
        }

        if(AE.radiationInterventionSize > 0){
            $('btn-add-radiation').show();
        }
        if(AE.surgeryInterventionSize > 0){
            $('btn-add-surgery').show();
        }
    }

    var interventionInstance = null;
 	var InterventionClass = Class.create();
    Object.extend(InterventionClass.prototype, {
        initialize: function() {
        },

        _populateDeafultParameters : function(itemType, paramHash) {
            var page = ${tab.number};
            var target = '_target' + ${tab.number};
            paramHash.set('_page', page);
            paramHash.set(target, page);
            paramHash.set('_asynchronous', true);
            paramHash.set('decorator', 'nullDecorator');
            paramHash.set('CSRF_TOKEN', AE.CSRF_TOKEN);
        },

        _addItem: function(itemType, src, val, location, options) {
            refreshBoxes();
            var container = $(location);
            var paramHash = new Hash(); 
            paramHash.set('task', 'add');
            paramHash.set('currentItem', itemType);
            paramHash.set(itemType, val);

            this._populateDeafultParameters(itemType, paramHash);

            var url = $('command').action;
            if(url.indexOf('?') > 0) {
                url = url + "&subview";
            }else {
                url = url + "?subview";
            }
            this._insertContent(container, url, paramHash, function() {}.bind(this));
        },

        formElementsInSection : function(container) {
            return container.select('input', 'select', 'textarea');
        },

        _deleteItem: function(itemType, index, location) {
            if (index < 0) return;
            var confirmation = confirm("Do you really want to delete?");
            if (!confirmation) return;

            // this.showIndicator(itemType+"-indicator");
            var container = $(location);

            var paramHash = new Hash();
            paramHash.set('task', 'remove');
            paramHash.set('currentItem', itemType);
            paramHash.set('index', index);
            this._populateDeafultParameters(itemType, paramHash);
            var url = $('command').action;
            if(url.indexOf('?') > 0) {
                url = url + "&subview";
            }else {
                url = url + "?subview";
            }
            var sectionHash = Form.serializeElements(this.formElementsInSection(container), true);
            this._updateContent(container, url, paramHash.merge(sectionHash), function (transport) {
            }.bind(this));

            if (itemType == 'agent') $('btn-add-agent').show();
        },

        _updateContent: function(container, url, params, onSuccessCallBack) {
            new Ajax.Request(url, {
                parameters: params.toQueryString(),
                onSuccess: function(transport) {
                    container.innerHTML = transport.responseText;
                    AE.registerCalendarPopups();
//                    refreshBoxes();
                }
            });

        },

        _insertContent: function(container, url, params, onCompleteCallBack) {
            new Ajax.Updater(container, url, {
                parameters: params.toQueryString(), onComplete: onCompleteCallBack, insertion: Insertion.Top, evalScripts: true
            });
        }
    })
</script>

<c:set var="hasSurgery" value="${command.study.surgeryPresent}" />
<c:set var="hasDevice" value="${command.study.devicePresent}" />
<c:set var="hasRadiation" value="${command.study.radiationPresent}" />
<c:set var="hasAgent" value="${command.study.drugAdministrationPresent}" />
<c:set var="hasBehavioral" value="${command.study.behavioralInterventionPresent}" />
<c:set var="hasBiological" value="${command.study.biologicalInterventionPresent}" />
<c:set var="hasGenetic" value="${command.study.geneticInterventionPresent}" />
<c:set var="hasDietary" value="${command.study.dietaryInterventionPresent}" />
<c:set var="hasOther" value="${command.study.otherInterventionPresent}" />

<div class="row">
    <div class="summarylabel">Treatment</div>
    <div class="summaryvalue shorty">${command.aeReport.treatmentInformation.treatmentDescription != null ? command.aeReport.treatmentInformation.treatmentDescription : command.aeReport.treatmentInformation.treatmentAssignment.description}</div>
</div>

<form:form id="command">
	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
        <chrome:flashMessage/>
        <tags:hasErrorsMessage />
        <tags:jsErrorsMessage/>
    
    <c:if test="${hasAgent}">
        <chrome:box title="Agents" collapsable="true">
            <jsp:attribute name="additionalTitle" />
            <jsp:body>
            	<tags:renderRow field="${fieldGroups.agentAdministered.fields[0]}"/>
                <div style="padding-left:20px;">
                    <tags:button cssClass="foo" id="btn-add-agent" color="blue" value="Add" icon="Add" type="button" onclick="addAgent();" size="small"/>
                    <tags:indicator id="agent_AjaxIndicator" />
                <div id="_agents">
					
                    <c:set var="size" value="${fn:length(command.aeReport.treatmentInformation.courseAgents)}" />
                    <c:forEach items="${command.aeReport.treatmentInformation.courseAgents}" varStatus="status" var="_oagent">
                        <c:set var="newIndex" value="${size - (status.index + 1)}" />
                        <c:set var="agent" value="${command.aeReport.treatmentInformation.courseAgents[newIndex]}" />
                        <c:set var="collapsed" value="${agent.studyAgent != null}" />
                        <c:if test="${!agent.studyAgent.retiredIndicator}">
                        	<ae:oneCourseAgent index="${newIndex}" agent="${agent}" collapsed="${collapsed}"/>
                        </c:if>	
                    </c:forEach>
                </div>
                </div>
            </jsp:body>
        </chrome:box>
    </c:if>

    <c:if test="${hasDevice}">
        <chrome:box title="Devices" collapsable="true">
            <jsp:attribute name="additionalTitle" />
            <jsp:body>
                <tags:renderRow field="${fieldGroups.ideAdministered.fields[0]}"/>
                <div style="padding-left:20px;">
                   <tags:button cssClass="foo" id="btn-add-device" color="blue" value="Add" icon="Add" type="button" onclick="addDevice();" size="small"/>
                    <tags:indicator id="device_AjaxIndicator" />
                <div id="_devices">
                <c:set var="size" value="${fn:length(command.aeReport.medicalDevices)}" />
                <c:forEach items="${command.aeReport.medicalDevices}" varStatus="status" var="_odevice">
                    <c:set var="newIndex" value="${size - (status.index + 1)}" />
                    <c:set var="device" value="${command.aeReport.medicalDevices[newIndex]}"  />
                    <ae:oneMedicalDevice index="${newIndex}" device="${device}" collapsed="true"/>
                </c:forEach>
            </div>
            </div>
            </jsp:body>
        </chrome:box>
    </c:if>

    <c:if test="${hasRadiation}">
        <chrome:box title="Radiation" collapsable="true">
            <jsp:attribute name="additionalTitle"/>
            <jsp:body>
                <div style="padding-left:20px;">
                    <tags:button cssClass="foo" id="btn-add-radiation" color="blue" value="Add" icon="Add" type="button" onclick="addRadiation();" size="small"/>
                    <tags:indicator id="radiation_AjaxIndicator" />
                <div id="_radiations">
                    <c:set var="size" value="${fn:length(command.aeReport.radiationInterventions)}" />
                    <c:forEach items="${command.aeReport.radiationInterventions}" varStatus="status" var="_oradiation">
                        <c:set var="newIndex" value="${size - (status.index + 1)}" />
                        <c:set var="radiation" value="${command.aeReport.radiationInterventions[newIndex]}" />
                        <ae:oneRadiationIntervention index="${newIndex}" radiation="${radiation}" collapsed="true"/>
                    </c:forEach>
                </div>
                </div>
            </jsp:body>
        </chrome:box>
    </c:if>

    <c:if test="${hasSurgery}">
        <chrome:box title="Surgeries" collapsable="true">
            <jsp:attribute name="additionalTitle"/>
            <jsp:body>
                <div style="padding-left:20px;">
                    <tags:button cssClass="foo" id="btn-add-surgery" color="blue" value="Add" icon="Add" type="button" onclick="addSurgery();" size="small"/>
                    <tags:indicator id="surgery_AjaxIndicator" />
                <div id="_surgeries">
                    <c:set var="size" value="${fn:length(command.aeReport.surgeryInterventions)}" />
                    <c:forEach items="${command.aeReport.surgeryInterventions}" varStatus="status" var="_osurgery">
                        <c:set var="newIndex" value="${size - (status.index + 1)}" />
                        <c:set var="surgery" value="${command.aeReport.surgeryInterventions[newIndex]}" />
                        <ae:oneSurgeryIntervention index="${newIndex}" surgery="${surgery}" collapsed="true"/>
                    </c:forEach>
                </div>
                </div>
            </jsp:body>
        </chrome:box>
    </c:if>
	
    <c:if test="${hasBehavioral}">
        <chrome:box title="Behaviorals" collapsable="true">
            <jsp:attribute name="additionalTitle"/>
            <jsp:body>
                <div style="padding-left:20px;">
                    <tags:button cssClass="foo" id="btn-add-behavioral" color="blue" value="Add" icon="Add" type="button" onclick="addBehavioral();" size="small"/>
                    <tags:indicator id="behavioral_AjaxIndicator" />
                <div id="_behaviorals">
                    <c:set var="size" value="${fn:length(command.aeReport.behavioralInterventions)}" />
                    <c:forEach items="${command.aeReport.behavioralInterventions}" varStatus="status" var="_obehavioral">
                        <c:set var="newIndex" value="${size - (status.index + 1)}" />
                        <c:set var="behavioral" value="${command.aeReport.behavioralInterventions[newIndex]}" />
                        <ae:oneBehavioralIntervention index="${newIndex}" behavioral="${behavioral}" collapsed="true"/>
                    </c:forEach>
                </div>
                </div>
            </jsp:body>
        </chrome:box>
    </c:if>

    <c:if test="${hasBiological}">
        <chrome:box title="Biologicals" collapsable="true">
            <jsp:attribute name="additionalTitle"/>
            <jsp:body>
                <div style="padding-left:20px;">
                    <tags:button cssClass="foo" id="btn-add-biological" color="blue" value="Add" icon="Add" type="button" onclick="addBiological();" size="small"/>
                    <tags:indicator id="biological_AjaxIndicator" />
                <div id="_biologicals">
                    <c:set var="size" value="${fn:length(command.aeReport.biologicalInterventions)}" />
                    <c:forEach items="${command.aeReport.biologicalInterventions}" varStatus="status" var="_obiological">
                        <c:set var="newIndex" value="${size - (status.index + 1)}" />
                        <c:set var="biological" value="${command.aeReport.biologicalInterventions[newIndex]}" />
                        <ae:oneBiologicalIntervention index="${newIndex}" biological="${biological}" collapsed="true"/>
                    </c:forEach>
                </div>
                </div>
            </jsp:body>
        </chrome:box>
    </c:if>

    <c:if test="${hasGenetic}">
            <chrome:box title="Genetics" collapsable="true">
                <jsp:attribute name="additionalTitle"/>
                <jsp:body>
                    <div style="padding-left:20px;">
                        <tags:button cssClass="foo" id="btn-add-genetic" color="blue" value="Add" icon="Add" type="button" onclick="addGenetic();" size="small"/>
                        <tags:indicator id="genetic_AjaxIndicator" />
                    <div id="_genetics">
                        <c:set var="size" value="${fn:length(command.aeReport.geneticInterventions)}" />
                        <c:forEach items="${command.aeReport.geneticInterventions}" varStatus="status" var="_ogenetic">
                            <c:set var="newIndex" value="${size - (status.index + 1)}" />
                            <c:set var="genetic" value="${command.aeReport.geneticInterventions[newIndex]}" />
                            <ae:oneGeneticIntervention index="${newIndex}" genetic="${genetic}" collapsed="true"/>
                        </c:forEach>
                    </div>
                    </div>
                </jsp:body>
            </chrome:box>
        </c:if>

    <c:if test="${hasDietary}">
            <chrome:box title="Dietaries" collapsable="true">
                <jsp:attribute name="additionalTitle"/>
                <jsp:body>
                    <div style="padding-left:20px;">
                        <tags:button cssClass="foo" id="btn-add-Dietary" color="blue" value="Add" icon="Add" type="button" onclick="addDietary();" size="small"/>
                        <tags:indicator id="dietary_AjaxIndicator" />
                    <div id="_dietaries">
                        <c:set var="size" value="${fn:length(command.aeReport.dietaryInterventions)}" />
                        <c:forEach items="${command.aeReport.dietaryInterventions}" varStatus="status" var="_odietary">
                            <c:set var="newIndex" value="${size - (status.index + 1)}" />
                            <c:set var="dietary" value="${command.aeReport.dietaryInterventions[newIndex]}" />
                            <ae:oneDietaryIntervention index="${newIndex}" dietary ="${dietary}" collapsed="true"/>
                        </c:forEach>
                    </div>
                    </div>
                </jsp:body>
            </chrome:box>
        </c:if>


    <c:if test="${hasOther}">
            <chrome:box title="otherAEs" collapsable="true">
                <jsp:attribute name="additionalTitle"/>
                <jsp:body>
                    <div style="padding-left:20px;">
                        <tags:button cssClass="foo" id="btn-add-OtherAE" color="blue" value="Add" icon="Add" type="button" onclick="addOtherAE();" size="small"/>
                        <tags:indicator id="otherAE_AjaxIndicator" />
                    <div id="_otherAEs">
                        <c:set var="size" value="${fn:length(command.aeReport.otherAEInterventions)}" />
                        <c:forEach items="${command.aeReport.otherAEInterventions}" varStatus="status" var="_otherAE">
                            <c:set var="newIndex" value="${size - (status.index + 1)}" />
                            <c:set var="otherAE" value="${command.aeReport.otherAEInterventions[newIndex]}" />
                            <ae:oneOtherAEIntervention index="${newIndex}" otherAE = "${otherAE}" collapsed="true"/>
                        </c:forEach>
                    </div>
                    </div>
                </jsp:body>
            </chrome:box>
        </c:if>


<%--
    <c:if test="${hasBehavioral}">
        <chrome:box title="Behavioral" collapsable="true"></chrome:box>
    </c:if>
--%>
        <ae:reportingContext allReportDefinitions="${command.applicableReportDefinitions}" selectedReportDefinitions="${command.selectedReportDefinitions}" />
    <tags:tabControls flow="${flow}" tab="${tab}" />
    <tags:tabFields tab="${tab}" />
</form:form>

</body>
</html>
