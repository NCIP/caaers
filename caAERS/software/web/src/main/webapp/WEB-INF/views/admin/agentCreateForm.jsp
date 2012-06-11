<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="agentFacade"/>

<admin:agent3rdLevelMenu selected="create" />

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="singleFields">
        <input type="hidden" name="_action" />

<div class="tabpane">
    <div class="content">

        <%--<chrome:box title="Agent Edit Form" autopad="false">--%>

        <div class="row">
            <div class="label"><ui:label labelProperty="agent.name" text="" path="agent.name"/></div>
            <div class="value"><ui:text path="agent.name" size="60" cssClass="${empty command.agent.name ? 'required' : 'valueOK'} validate-NOTEMPTY$$MAXLENGTH2000" title="Agent name"/></div>
        </div>
        <div class="row">
            <div class="label"><ui:label labelProperty="agent.nscNumber" text="" path="agent.nscNumber" /></div>
            <div class="value"><ui:text path="agent.nscNumber" size="20" readonly="${false && not empty command.agent.nscNumber}"  cssClass="required validate-NOTEMPTY$$MAXLENGTH2000" title="Agent identifier"/></div>
        </div>


        <br>

        <c:set var="divisionTitle">
            <jsp:attribute name="value"><caaers:message code="agents.expected.aes" /></jsp:attribute>
        </c:set>
        <chrome:division collapsable="false" collapsed="false" title="${divisionTitle}">

            <c:set var="versionName" value="${command.terminology.code eq 1 and not empty command.ctcVersion ? command.ctcVersion.name : command.meddraVersion.name}" />
            <c:set var="isMeddra" value="${command.terminology.code eq 2}" />
            <c:set var="terminologyVersionID" value="${command.terminology.code eq 1 and not empty command.ctcVersion ? command.ctcVersion.id : not empty command.meddraVersion ? command.meddraVersion.id  : 0}" />
            <c:set var="meddraVersionID" value="${not empty command.meddraVersion ? command.meddraVersion.id : 0}" />
            <c:set var="terminologyType" value="${command.terminology.code eq 1 and not empty command.ctcVersion ? 'ctep' : not empty command.meddraVersion ? 'meddra' : ''}" />

<%--
            T: ${command.terminology}
            VersionName:(${versionName})
            TerminologyVersion: [${terminologyVersionID}]
            Meddra: [${meddraVersionID}]
            Meddra: [${terminologyType}]
--%>

            <div class="row" id="terminologyRow">
                <div class="label"><caaers:message code="LBL_study.aeTerminology.term" /></div>
                <div class="value"><ui:select options="${terminology}" path="terminology" disabled="${false && command.terminology.code > 0}"/></div>
            </div>

            <div class="row" id="ctcRow" style="display: ${command.terminology.code ne '1' ? 'none' : ''};">
                <div class="label"><caaers:message code="LBL_study.aeTerminology.ctcVersion" /></div>
                <div class="value"><ui:select options="${ctcVersion}" path="ctcVersion" disabled="${false && command.ctcVersion.id > 0}"/></div>
            </div>

            <div class="row" id="meddraRow" style="display: ${command.terminology.code ne '2' ? 'none' : ''};">
                <div class="label"><caaers:message code="LBL_study.aeTerminology.meddraVersion" /></div>
                <div class="value"><ui:select options="${meddraVersion}" path="meddraVersion" disabled="${false && command.meddraVersion.id > 0}" /></div>
            </div>

            <c:set var="_visible" value="${command.terminology.code == 1 and command.ctcVersion.id > 0 or command.terminology.code == 2 and command.meddraVersion.id > 0}" />
			<input type="hidden" name="_finish" />
<%--
            <div id="_BUTTON" style="display:${_visible ? '' : 'none'}">
                <tags:button color="blue" size="small" value="Change terminology" onclick="changeTerminology()"/>
            </div>


            <div id="_ALL" style="display:${_visible ? '' : 'none'}">

            <tags:aeTermQuery title="Choose CTC terms" isMeddra="${isMeddra}"
                              callbackFunctionName="addTerm"
                              noBackground="true"
                              version="${terminologyVersionID}"
                              ignoreOtherSpecify="false" isAjaxable="true"
                              versionName="${versionName}"
                              study="${null}"
                              ctcCategories="${not empty command.ctcVersion ? command.ctcVersion.categories : null}"
                    />


            <tags:table bgColor="#cccccc" contentID="asael_">
                <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
                    <tr bgcolor="#E4E4E4">
                        <th scope="col" align="left" width="10%"><b>Terminology</b></th>
                        <th scope="col" align="left" width="26%"><b>Term</b></th>
                        <th scope="col" align="left" width="8%"><b>Grade 1</b></th>
                        <th scope="col" align="left" width="8%"><b>Grade 2</b></th>
                        <th scope="col" align="left" width="8%"><b>Grade 3</b></th>
                        <th scope="col" align="left" width="8%"><b>Grade 4</b></th>
                        <th scope="col" align="left" width="8%"><b>Grade 5</b></th>
                        <th scope="col" align="left" width="8%"><b>Expected (yes/no)</b></th>
                        <th scope="col" align="left" width="9%"><b>Overall % expected</b></th>
                        <th width="7%">&nbsp;</th>
                    </tr>
                    <c:forEach items="${command.agentSpecificTerms}" var="agentTerm" varStatus="status">
                        <c:if test="${!agentTerm.deleted}">
                            <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                                <admin:oneAgentSpecificAE isOtherSpecify="${agentTerm.otherRequired}" index="${status.index}" term="${agentTerm}"/>
                                <td style="text-align:center;" width="50px">
                                     <tags:button id="${status.index}" color="red" type="button" value="" size="small" icon="x" onclick="removeTerm(${status.index})"/>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                 <tr id="observedBlankRow" style="display:none;"><td></td></tr>
                </table>
            </tags:table>
            </div>--%>
            
        </chrome:division>

        <%--</chrome:box>--%>

    </div>
</div>
<script>
    var v1Index = $('terminology').selectedIndex;
    var vCIndex = $('ctcVersion').selectedIndex;
    var vMIndex = $('meddraVersion').selectedIndex;
    var confirmed = false;
    
    function addTerm(selectedTerms) {
        var listOfTermIDs = new Array();
        $H(selectedTerms).keys().each(function(termID) {
            listOfTermIDs.push(termID);
        }.bind(this));

        agentFacade.addAgentSpecificTerms(${command.agent.id > 0 ? command.agent.id : 0}, '${terminologyType}', listOfTermIDs, function(ajaxOutput) {
            $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
        });
    }

    function removeTerm(_index) {
        if(!confirm( "Are you sure you want to delete this?" )) return false;
        agentFacade.deleteAgentSpecificTerms(_index, function(ajaxOutput) {
            $('asael_').innerHTML = ajaxOutput.htmlContent;
        });
    }

/*
    Event.observe($('terminology'), "change", function() {
        $('command').submit();
    });
*/

    function adjustRows(v1, vC, vM) {
        if (v1.options[v1.selectedIndex].value == 'CTC') {
            showRow('ctcRow');
            hideRow('meddraRow'); vM.selectedIndex = 0;
        } else if (v1.options[v1.selectedIndex].value == 'MEDDRA') {
            hideRow('ctcRow'); vC.selectedIndex = 0;
            showRow('meddraRow');
        } else {
            hideRow('ctcRow'); vC.selectedIndex = 0;
            hideRow('meddraRow'); vM.selectedIndex = 0;
        }
    }

    function checkVersion() {
        var v1 = $('terminology');
        var vC = $('ctcVersion');
        var vM = $('meddraVersion');

    <c:if test="${fn:length(command.agentSpecificTerms) > 0}">
            if (!confirmed && !confirm("Changing the versions will lead to a delete of all ASAEL and all Study Expected AEs.\n\nContinue ?")) {
                $('terminology').selectedIndex = v1Index;
                $('ctcVersion').selectedIndex = vCIndex;
                $('meddraVersion').selectedIndex = vMIndex;
                adjustRows(v1, vC, vM);
                return;
            } else {
                confirmed  = true;
            }
    </c:if>
        
        adjustRows(v1, vC, vM);
        
        if (v1.options[v1.selectedIndex].value == 'CTC' && vC.selectedIndex > 0) {
            changeTerminology();
            return;
        }

        if (v1.options[v1.selectedIndex].value == 'MEDDRA' && vM.selectedIndex > 0) {
            changeTerminology();
            return;
        }

        // $('_ALL').hide();
    }

    Event.observe($('terminology'), "change", function() {
        checkVersion();
    });

    if ($('ctcVersion'))
        Event.observe($('ctcVersion'), "change", function() {
            checkVersion();
        });

    Event.observe($('meddraVersion'), "change", function() {
        checkVersion();
    });

    function changeTerminology() {
        $('command')._action.value = "CHANGE_TERMINOLOGY";
        $('command').submit();
    }
</script>

    </jsp:attribute>
</tags:tabForm>

