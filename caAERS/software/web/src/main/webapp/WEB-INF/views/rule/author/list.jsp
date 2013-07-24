<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp" %>
<csmauthz:accesscontrol var="hasRuleCreate" objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE"/>
<html>
<head>
    <title>Manage rules</title>
    <tags:dwrJavascriptLink objects="authorRule"/>
    <style type="text/css">

        th.yui-dt-resizeable .yui-dt-resizerliner .yui-dt-liner {
            position: relative;
            font-size: 13px;
            font-weight: bold;
            margin: 0px;
            color: white;
            text-shadow: 0 -1px #2166a1;
            height: auto;
            padding-top: 7px;
            padding-bottom: 7px;
            text-decoration: none
        }

        p.description {
            margin: 0.25em 0 0 1em;
        }
        div.submit {
            margin:0 0 0 145px;
			padding:0;
        }
        .value input[type=text] {
            width: 80%;
        }

        form {
            margin-top: 1em;
        }

        .updated {
            border: #494 solid;
            border-width: 1px 0;
            background-color: #8C8;
            padding: 1em 2em;
            text-align: center;
            margin: 1em 10%;
            font-weight: bold;
            font-size: 1.1em;
        }
		#basic table {
			width:100%;
		}
		.new_rule {
			margin:10px 0 10px 65px;
		}
    </style>
    
    
    
    <script type="text/javascript">
     function showRuleDebugInfo(rsId){
        _e = $('rule-debug-'+ rsId)
        if(Element.visible(_e)){
            _e.hide();
            $('rule-debug').hide();
        }else{
            _e.show();
            $('rule-debug').show();
        }
     }

    Event.observe(window, "load", function() {});
		
		function deployRule(name , divId) {
			try {
				authorRule.deployRuleSet(name, function(values) {
							alert("Successfully Enabled");
							document.getElementById(divId).innerHTML = "<font color='green'>Enabled</font>";
					});
			} catch(e) {alert(e)}
			
		}
		
		
		function unDeployRule(name , divId) {
			try {
				authorRule.unDeployRuleSet(name, function(values) {
							alert("Successfully Disabled");
							document.getElementById(divId).innerHTML = "<font color='red'>Not Enabled</font>";
					});
			} catch(e) {alert(e)}
		}
		
		

	

YAHOO.example.Data = {

    rsList: [
<c:forEach items="${command.ruleSets}" var="rs" varStatus="status">
        {
            rsLevel: "${empty rs.ruleLevel ? 'NA' : rs.ruleLevel.description}",
            rsDescription: "${rs.ruleType.name}",
            rsOrganization: "${empty rs.organization ? 'NA' : rs.organization.fullName}",
            rsStudyID: "${caaers:escapeJS(rs.study.displayName)}",
            rsStatus: "<div id='status-${rs.id}' onclick='showRuleDebugInfo(${rs.id})'>${rs.status}</div>"

            ,
            rsAction: "<select id='action-id' onChange=\"javascript:handleAction(this, '${rs.id}', '${rs.ruleBindURI}', 'status-${rs.id}')\">" +
            			"<option value=\"\">Please select</option>" +
                        "<option value=\"\">View/Edit</option>" +
                       <c:if test="${hasRuleCreate}">
            			"<option value=\"\">Enable</option>" +
            			"<option value=\"\">Disable</option>" +
            			"<option value=\"\">Export</option>" +
            			"<option value=\"\">Delete</option>" +
                       </c:if>
            			"</select>"


        }
        <c:if test="${!status.last}">,</c:if>
</c:forEach>

    ]
};

    /////////////////////////////////

YAHOO.util.Event.addListener(window, "load", function() {
	
    YAHOO.example.CustomSort = function() {

        var myColumnDefs = [
            {key:"rsLevel",             label:"Rule Level",         sortable:true,      resizeable:true},
            {key:"rsDescription",       label:"Rule Set",           sortable:true,      resizeable:true},
            {key:"rsOrganization",      label:"Organization",       sortable:true,      resizeable:true},
            {key:"rsStudyID",           label:"Study",              sortable:true,      resizeable:true},
            {key:"rsStatus",            label:"Status",             sortable:true,      resizeable:true, formatter:"myCustom"},
            {key:"rsAction",            label:"Action",             sortable:false,     resizeable:true}
        ];

        var myCustomFormatter = function(elCell, oRecord, oColumn, oData) {
                        if(oRecord.getData("rsStatus") == "Not Enabled") {
                            elCell.innerHTML = "<font color='red'>" + oData + "</font>";
                        }
                        else {
                            elCell.innerHTML = "<font color='green'>" + oData + "</font>";
                        }
                    };

        var actionFormatter = function(elCell, oRecord, oColumn, oData) {
                            elCell.innerHTML = 'abc';



                    };

        // Add the custom formatter to the shortcuts
        YAHOO.widget.DataTable.Formatter.myCustom = myCustomFormatter;
//        YAHOO.widget.DataTable.Formatter.actionFormatter = actionFormatter;

        var myDataSource = new YAHOO.util.DataSource(YAHOO.example.Data.rsList.slice(0,50));
        myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        myDataSource.responseSchema = {
            fields: ["rsLevel", "rsDescription", "rsOrganization", "rsStudyID", "rsStatus"
                     , "rsAction"
                     ]
        };

        //Create config
        var oConfigs = {
				initialRequest: "results=50",
				draggableColumns:false
			};
        var myDataTable = new YAHOO.widget.DataTable("basic", myColumnDefs, myDataSource, oConfigs);

        return {
            oDS: myDataSource,
            oDT: myDataTable
        };
    }();
});

    /////////////////////////////////
    
    function handleAction(selectElement, id, name, divId){
    	var action = selectElement.options[selectElement.selectedIndex].text;
    	if(action != 'Please select')
    	if(confirm('Are you sure you want to take the action - ' + action + ' ?')){
    		switch (action) {
    	    	case "Please select": break;
        	    case "View/Edit"         : var url = '<c:url value="/pages/rule/${hasRuleCreate ?'create' : 'read'}?from=list&_page=0&${hasRuleCreate ? '_target1=1' : '_target0=0'}" />' + '&ruleSetId=' + id;
                			          window.location = url; 
						              break;
            	case "Enable"       : deployRule(name, divId); break;
               	case "Disable"      : unDeployRule(name, divId);  break;
                case "Export"       : var url = '<c:url value="/pages/rule/export?ruleSetName="/>' + name;
            				          document.location = url;  
            				          break;
            	case "Delete"       : var url = '<c:url value="/pages/rule/util?ruleSetName="/>' + name;
           					          document.location = url;
           					          break; 
            }
    	}
    }

    </script>
</head>
<body>
<chrome:box title="Manage / Import rules" autopad="true">
	<chrome:division title="Manage rules" id="rule-set-id" >
	    <tags:instructions code="listrules" />
    	<div id="basic" class="yui-skin-sam"></div>
		<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE">
		<div class="new_rule">
			<c:set var="create_url"><c:url value="/pages/rule/create"/></c:set>
			<tags:button color="blue" icon="add" size="small" type="button" value="New Rule" markupWithTag="a" href="${create_url}"/>
		</div>
		</csmauthz:accesscontrol>
		</div>
    </chrome:division>
    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Rule:CREATE">
    <chrome:division title="Import rules" id="import-rules-id">
    	<p>
			<tags:instructions code="importxmlrules" />
		</p>
		<c:if test="${command.updated}">
			<p class="updated">${command.message}</p>
		</c:if>
		<form:form action="${action}?CSRF_TOKEN=${CSRF_TOKEN }" enctype="multipart/form-data" cssClass="standard">
			<!-- The following does not work with multipart form hence adding the SCRF token to the form action -->
			<%-- <input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/> --%>
            <div class="row">
                <div class="label">
                    Select XML file
                </div>
                <div class="value">
                	<input type="file" onchange="$('add-rule').removeAttribute('disabled')" name="ruleSetFile1" id="browse_field" size="50"/>
                </div>
            </div>    
        <div class="row submit">
            <tags:button id="add-rule" disabled="disabled" color="green" type="submit" value="Import" size="small" icon="check" />
        </div>
    </form:form>

    </chrome:division>
    </csmauthz:accesscontrol>
</chrome:box>

<c:forEach items="${command.ruleSets}" var="rs" varStatus="status">
    <div id="rule-debug-${rs.id}" style="display: none;background-color: #c0c0b0;">
        <p>
        RuleSet Id: ${rs.id} <br />
        RuleSet URL: ${rs.ruleBindURI} <br />
        Rule Level: ${empty rs.ruleLevel ? 'NA' : rs.ruleLevel.description}  <br />
        Rule Type : ${rs.ruleType.name} <br />
        Organization : ${empty rs.organization ? 'NA' : rs.organization.fullName} <br />
        Study : ${rs.study.shortTitle} <br />
        Status : ${rs.status}  <br />
        Present in Stage area : ${fn:contains(command.allFromSageArea,rs.ruleBindURI )} <br />
        Present in Deploy area : ${fn:contains(command.allFromDeployArea,rs.ruleBindURI )} <br />
        Present in Runtime area : ${fn:contains(command.allFromRuntimeEngine,rs.ruleBindURI )} <br />
        <c:if test="${rs.status eq 'Enabled' and (not fn:contains(command.allFromRuntimeEngine,rs.ruleBindURI ) )}">
            <br /><b><font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is enabled but is not present in runtime area</font> </b>
        </c:if>
        <c:if test="${rs.status eq 'Enabled' and (not fn:contains(command.allFromDeployArea,rs.ruleBindURI ) )}">
                <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is enabled but is not present in deploy area</font></b>
        </c:if>
        <c:if test="${rs.status eq 'Not Enabled' and (fn:contains(command.allFromRuntimeEngine,rs.ruleBindURI ) )}">
            <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is not enabled but is present in runtime area</font></b>
        </c:if>
        <c:if test="${rs.status eq 'Not Enabled' and (fn:contains(command.allFromDeployArea,rs.ruleBindURI ) )}">
            <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is not enabled but is present in deploy area</font></b>
        </c:if>
        </p>
    </div>
</c:forEach>
<div id="rule-debug" style="display: none; background-color: #c0c0c0;" >
    Below section contains more details of the status of the rules:
    <p>Stage Area (count : ${fn:length(command.allFromSageArea)}):
        <c:forEach var="s" items="${command.allFromSageArea}">
            <li>${s}</li>
        </c:forEach>
    </p>
    <p>Deploy Area (count : ${fn:length(command.allFromDeployArea)}) :
        <c:forEach var="s" items="${command.allFromDeployArea}">
            <li>${s}</li>
        </c:forEach>
    </p>
    <p>Runtime Area (count : ${fn:length(command.allFromRuntimeEngine)}) :
        <c:forEach var="s" items="${command.allFromRuntimeEngine}">
            <li>${s}</li>
        </c:forEach>
    </p>

    <c:forEach var="s" items="${command.allFromDeployArea}">

        <c:if test="${not fn:contains(command.allFromRuntimeEngine, s)}">
            <br /> <font color="red">Alert ::::: rules ${s} is not available in runtime area   </font>
        </c:if>
    </c:forEach>

    <c:forEach items="${command.ruleSets}" var="rs" varStatus="status">
    <c:if test="${rs.status eq 'Enabled' and (not fn:contains(command.allFromRuntimeEngine,rs.ruleBindURI ) )}">
        <br /><b><font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is enabled but is not present in runtime area</font> </b>
    </c:if>
    <c:if test="${rs.status eq 'Enabled' and (not fn:contains(command.allFromDeployArea,rs.ruleBindURI ) )}">
        <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is enabled but is not present in deploy area</font></b>
    </c:if>
    <c:if test="${rs.status eq 'Not Enabled' and (fn:contains(command.allFromRuntimeEngine,rs.ruleBindURI ) )}">
        <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is not enabled but is present in runtime area</font></b>
    </c:if>
    <c:if test="${rs.status eq 'Not Enabled' and (fn:contains(command.allFromDeployArea,rs.ruleBindURI ) )}">
        <br /><b> <font color="red">Looks like rule (${rs.id}, ${rs.ruleBindURI}, ${rs.ruleLevel.description}, ${rs.ruleType.name}, ${rs.organization.nciInstituteCode}) is not enabled but is present in deploy area</font></b>
    </c:if>
    </c:forEach>
    <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_pre_existing_condition_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_pre_existing_condition_section</font> </c:if>
     <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_prior_therapies_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_prior_therapies_section </font> </c:if>
    <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_medical_info_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_medical_info_section</font> </c:if>
     <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_description_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_description_section</font> </c:if>
    <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_treatment_info_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_treatment_info_section</font> </c:if>
     <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_basics_section'  )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_basics_section</font> </c:if>
    <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_study_interventions' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_study_interventions</font> </c:if>
     <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_labs_section')}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_labs_section</font> </c:if>
    <c:if test="${not fn:contains(command.allFromRuntimeEngine,'gov.nih.nci.cabig.caaers.rules.reporting_attribution_section' )}"><font color="red"><br />Missing business rule : gov.nih.nci.cabig.caaers.rules.reporting_attribution_section</font> </c:if>


</div>
</body>
</html>
