<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="agentFacade"/>

<admin:agent3rdLevelMenu />

<div class="tabpane">
    <div class="content">

        <chrome:box title="Agent" autopad="false">

        <div class="row">
            <div class="label"><ui:label labelProperty="agent.name" text="" path="command.agent.name" /></div>
            <div class="value">${command.agent.name}</div>
        </div>
        <div class="row">
            <div class="label"><ui:label labelProperty="agent.nscNumber" text="" path="command.agent.nscNumber" /></div>
            <div class="value">${command.agent.nscNumber}</div>
        </div>


        <br>

        <chrome:division collapsable="false" collapsed="false" title="Agent Specific AE List">

            <tags:table bgColor="#cccccc" contentID="asael_#{agent.id}">
                <table id="termsTable" width="100%" border="0" cellspacing="1" cellpadding="3">
                    <tr bgcolor="#E4E4E4">
                        <th scope="col" align="left" colspan="2"><b>Term</b></th>
                    </tr>
                    <c:forEach items="${command.agentSpecificTerms}" var="agentTerm" varStatus="status">
                        <tr class="agentSpecific-section ${status.index % 2 gt 0 ? 'odd' : 'even'}" id="AGENT_TERM_-${status.index}" bgcolor="white">
                            <td>${agentTerm.fullName}
                        </tr>
                    </c:forEach>
                </table>
            </tags:table>

        </chrome:division>

        </chrome:box>

    </div>
</div>

