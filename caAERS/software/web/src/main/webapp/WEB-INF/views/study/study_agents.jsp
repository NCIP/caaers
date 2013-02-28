<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
.label {
	width: 12em;
}

td#linkPosition a img {
	position: absolute;
	right: 30px;
}
</style>
<!--[if lte IE 6]>
<style>
	#main {
		top:50px;
	}
</style>
<![endif]-->
<tags:dwrJavascriptLink objects="createStudy" />
<tags:js name="ui/ajaxCRUD" />
<script type="text/javascript">
ajaxCRUD = new AJAX_CRUD_HELPER();

// ----------------------------------------------------------------------------------------------------------------
function fireAction(action, index) {
    switch (action) {
        case "addOtherIntervention":
            ajaxCRUD._addItem('OtherIntervention', null, null, '_otherInterventions', null, ${tab.number}, 'Top');
            break;
        case "removeOtherIntervention":
            ajaxCRUD._deleteItem('OtherIntervention', index, '_otherInterventions', ${tab.number});
            break;
        case "addStudyDevice":
            ajaxCRUD._addItem('StudyDevice', null, null, '_devices', null, ${tab.number}, 'Top');
            break;
        case "removeStudyDevice":
            ajaxCRUD._deleteItem('StudyDevice', index, '_devices', ${tab.number});
            break;
        case "removeStudyAgent":
            ajaxCRUD._deleteItem('StudyAgent', index, '_SA', ${tab.number});
            break;
        case "addStudyAgent":
            ajaxCRUD._addItem('StudyAgent', null, null, '_SA', null, ${tab.number}, 'Top');
            break;
        case "addDeviceIND":
            var containerID = '_Device-IND-' + index;
            var opts = new Hash();
            opts.set("index", index);
            ajaxCRUD._addItem('StudyDeviceIND', null, null, containerID, opts, ${tab.number}, 'Bottom');
            break; 
        case "removeDeviceIND":
            var children = $('_Device-IND-' + index).childElements();
            $A(children).each(function(el) {
                el.remove();
            });
            break;    
        case "addIND":
            var containerID = '_SA-IND-' + index;
            var opts = new Hash();
            opts.set("index", index);
            ajaxCRUD._addItem('StudyAgentIND', null, null, containerID, opts, ${tab.number}, 'Bottom');
            break;
        case "removeIND":
            var children = $('_SA-IND-' + index).childElements();
            $A(children).each(function(el) {
                el.remove();
            });
            break;
    }
}

// ----------------------------------------------------------------------------------------------------------------

function updateDeviceBoxTitleFromOther(index) {
    updateDeviceBoxTitle("titleOf_StudyDevice_" + index, $("study.studyDevices[" + index + "].otherCommonName").value, $("study.studyDevices[" + index + "].otherBrandName").value, $("study.studyDevices[" + index + "].otherDeviceType").value);
}

// ----------------------------------------------------------------------------------------------------------------

function updateDeviceBoxTitle(id, _commonName, _brandName, _type) {
    var s = "";
    if (_commonName != null && jQuery.trim(_commonName) != '') s += (_commonName + ", ");
    if (_brandName != null && jQuery.trim(_brandName) != '') s += (_brandName + ", ");
    if (_type != null && jQuery.trim(_type) != '') s += (_type + ", ");
    $(id).innerHTML = s.substring(0, s.length - 2);
}

// ----------------------------------------------------------------------------------------------------------------

function updateOtherInterventionBoxTitle(id, _name) {
    $(id).innerHTML = _name
}

// ----------------------------------------------------------------------------------------------------------------

function toggleDeviceOrOther(index) {
    var deviceRadioSelected = $("radioDevice" + index).checked
    var idPrefix = 'study.studyDevices[' + index + '].';

    var _field = $(idPrefix + 'device');
    var _fieldHelper = $(idPrefix + 'device.autocompleter');
    var _field_input = $(idPrefix + 'device-input');
    var _otherField= $(idPrefix + 'otherDevice');

    if (deviceRadioSelected) {
        if (_field_input) {
            _field_input.enable();
            _field_input.addClassName('pending-search');
            AE.hash.set(_field.id , '1');
        }
        if (_otherField) _otherField.hide();
        if (_fieldHelper) _fieldHelper.show();
    } else {
        if (_field_input) {
            _field_input.value = 'Begin typing here';
            _field_input.disable();
            _field.clear();
/*
            $(_fieldHelper.id + '.type').innerHTML = '';
            $(_fieldHelper.id + '.commonName').innerHTML = '';
            $(_fieldHelper.id + '.brandName').innerHTML = '';
*/
        }
        if (_otherField) _otherField.show();
        if (_fieldHelper) _fieldHelper.hide();
    }
}

// ----------------------------------------------------------------------------------------------------------------
function toggleAgentOrOther(index) {
	var agentRadioSelected = $("select-agent-" + index).checked
	var idPrefix = 'study.studyAgents[' + index + '].';
	var agentField = $(idPrefix + 'agent');
	var agentField_Input = $(idPrefix + 'agent-input');
	var otherField = $(idPrefix + 'otherAgent');
	if(agentRadioSelected){
		if(agentField) agentField_Input.enable();
		if(otherField){ otherField.clear(); otherField.disable(); }
	}else{
		if(agentField) { 
			agentField.clear(); 
			agentField_Input.value = 'Begin typing here';
			agentField_Input.disable();
		}
		if(otherField) otherField.enable();
	}
}

</script>
</head>
<body>

<study:summary />

<chrome:flashMessage/>

<form:form id="command" name="command">
<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
    <chrome:box  title="Agents">
<%--<tags:tabForm tab="${tab}" flow="${flow}" formName="studyAgentsForm" hideErrorDetails="false">--%>
    <input type="hidden" id="_ITEM_COUNT" name="_ITEM_COUNT" value="${fn:length(command.study.studyAgents)}">

    <div style="padding-left:0px;">
    <p id="instructions"></p>
    <tags:hasErrorsMessage hideErrorDetails="false"/>
        <div align="left">
            <tags:indicator id="_SA_indicator" />
            <tags:button color="blue" type="button" value="Add Agent" size="small" icon="add" onclick="javascript:fireAction('addStudyAgent','0');"/>
        </div>

		<div id="_SA">
        <c:set var="size" value="${fn:length(command.study.studyAgents)}" />
        <c:forEach var="sa" varStatus="status" items="${command.study.studyAgents}">
            <c:set var="newIndex" value="${size - (status.index + 1)}" />
            <c:if test="${!command.study.studyAgents[newIndex].retiredIndicator}">
        	    <study:oneStudyAgent studyAgent="${command.study.studyAgents[newIndex]}" index="${newIndex}" />
		    </c:if>
        </c:forEach>
		</div>
    </div>

    </chrome:box>

    <chrome:box title="Devices" collapsable="true">
        <jsp:attribute name="additionalTitle" />
        <jsp:body>
            <div style="padding-left:20px;">
               <tags:button cssClass="foo" id="btn-add-device" color="blue" value="Add Device" icon="Add" type="button" onclick="fireAction('addStudyDevice');" size="small"/>
                <tags:indicator id="device_AjaxIndicator" />
            <div id="_devices">
            <c:set var="size" value="${fn:length(command.study.studyDevices)}" />
            <c:forEach items="${command.study.studyDevices}" varStatus="status" var="sd">
                <c:set var="newIndex" value="${size - (status.index + 1)}" />
                <c:if test="${!command.study.studyDevices[newIndex].retiredIndicator}">
                    <study:oneStudyDevice index="${newIndex}" studyDevice="${command.study.studyDevices[newIndex]}" collapsed="true"/>
                </c:if>
            </c:forEach>
        </div>
        </div>
        </jsp:body>
    </chrome:box>

    <chrome:box title="Other Interventions">
        <jsp:attribute name="additionalTitle" />
        <jsp:body>
            <div style="padding-left:20px;">
               <tags:button cssClass="foo" id="btn-add-otherIntervention" color="blue" value="Add Other Intervention" icon="Add" type="button" onclick="fireAction('addOtherIntervention');" size="small"/>
                <tags:indicator id="otherIntervention_AjaxIndicator" />
            <div id="_otherInterventions">
            <c:set var="size" value="${fn:length(command.study.otherInterventions)}" />
            <c:forEach items="${command.study.otherInterventions}" varStatus="status" var="oi">
                <c:set var="newIndex" value="${size - (status.index + 1)}" />
                <c:if test="${!command.study.otherInterventions[newIndex].retiredIndicator}">
                    <study:oneOtherIntervention index="${newIndex}" otherIntervention="${command.study.otherInterventions[newIndex]}" collapsed="true"/>
                </c:if>
            </c:forEach>
        </div>
        </div>
        </jsp:body>
    </chrome:box>
    
    <tags:tabControls flow="${flow}" tab="${tab}" />
    <tags:tabFields tab="${tab}" />

</form:form>
<%--</tags:tabForm>--%>
</body>
</html>
