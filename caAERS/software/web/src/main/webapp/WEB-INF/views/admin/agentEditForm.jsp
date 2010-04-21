<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="agentFacade"/>

<admin:agent3rdLevelMenu selected="search" />

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="singleFields">
        <%--<input type="hidden" name="_finish" value="true"/>--%>

<div class="tabpane">
    <div class="content">

        <%--<chrome:box title="Agent Edit Form" autopad="false">--%>

        <div class="row">
            <div class="label"><ui:label labelProperty="agent.name" text="" path="agent.name" /></div>
            <div class="value"><ui:text path="agent.name" size="60"/></div>
        </div>
        <div class="row">
            <div class="label"><ui:label labelProperty="agent.nscNumber" text="" path="agent.nscNumber" /></div>
            <div class="value"><ui:text path="agent.nscNumber" size="20"/></div>
        </div>


        <br>

        <chrome:division collapsable="false" collapsed="false" title="Agent Specific AE List">

            <c:set var="versionName" value="CTC 4" />
            <c:set var="isMeddra" value="false" />
            <c:set var="terminologyVersion" value="4" />

            <ui:select options="${ctcVersion}" path="${command.ctc}" />

            <tags:aeTermQuery title="Choose CTC terms" isMeddra="${isMeddra}"
                              callbackFunctionName="addTerm"
                              noBackground="true"
                              version="${terminologyVersion}"
                              ignoreOtherSpecify="false" isAjaxable="true"
                              versionName="${versionName}"
                              study="${null}"
                              ctcCategories="${not empty command.ctcVersion ? command.ctcVersion.categories : null}"
                    />


            <tags:table bgColor="#cccccc" contentID="asael_#{agent.id}">
                <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
                    <tr bgcolor="#E4E4E4">
                        <th scope="col" align="left" colspan="2"><b>Term</b></th>
                    </tr>
                    <c:forEach items="${command.agentSpecificTerms}" var="agentTerm" varStatus="status">
                        <c:if test="${!agentTerm.deleted}">
                            <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                                <admin:oneAgentSpecificAE isOtherSpecify="${agentTerm.otherRequired}" index="${status.index}" term="${agentTerm}"/>
                                <td style="text-align:center;" width="50px">
                                     <tags:button id="${status.index}" color="blue" type="button" value="" size="small" icon="x" onclick="removeTerm(${status.index})"/>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                 <tr id="observedBlankRow" style="display:none;"><td></td></tr>
                </table>
            </tags:table>

        </chrome:division>

        <%--</chrome:box>--%>

    </div>
</div>
<script>
    function addTerm(selectedTerms) {
        var listOfTermIDs = new Array();
        $H(selectedTerms).keys().each(function(termID) {
            listOfTermIDs.push(termID);
        }.bind(this));

        agentFacade.addAgentSpecificTerms(${command.agent.id}, 'ctep', listOfTermIDs, function(ajaxOutput) {
            $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
        });
    }

    function removeTerm(_index) {
        if(!confirm( "Are you sure you want to delete this?" )) return false;
        agentFacade.deleteAgentSpecificTerms(_index, function(ajaxOutput) {
            $('termsTable').innerHTML = ajaxOutput.htmlContent;
        });
    }
</script>

    </jsp:attribute>
</tags:tabForm>

