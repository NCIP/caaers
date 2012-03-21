<%@include file="/WEB-INF/views/taglibs.jsp"%>

<style>
    #yui-dt0-th-nsc {width: 140px;}
    #yui-dt0-th-inv_drug {width: 180px;}
    #yui-dt0-th-lead_ind {width: 130px;}
</style>
<chrome:division title="Agents">

    <div id="agentsTableDiv"></div>

<script language="JavaScript">

    var agentIndNumFormatter = function(elCell, oRecord, oColumn, oData) {
            var orgId = oRecord.getData("id");
            elCell.innerHTML = "<a href='asaelEdit?agentID=" + orgId + "'>" + oData + "</a>";
    };

    var agentsColumnDefs = [
        {key:"name", label:"Agent name", sortable:true, resizeable:true},
        {key:"nsc", label:"Agent NSC number", sortable:true, resizeable:true},
        {key:"ind_ind", label:"IND indicator", sortable:true, resizeable:true},
        {key:"ind_num", label:"IND #", sortable:true, resizeable:true},
        {key:"inv_drug", label:"Investigational new drug?", sortable:true, resizeable:true},
        {key:"lead_ind", label:"Part of lead IND?", sortable:true, resizeable:true}
    ];

    var agentsFields = [
        {key:'name', parser:"string"},
        {key:'nsc', parser:"string"},
        {key:'ind_ind', parser:"string"},
        {key:'ind_num', parser:"string"},
        {key:'inv_drug', parser:"string"},
        {key:'lead_ind', parser:"string"}
    ];

    agentsJSONResult = [
        <c:forEach items="${command.study.studyAgents}" var="studyAgent">
            <c:if test="${not studyAgent.retired}">
                {

                    <c:if test="${fn:length(studyAgent.studyAgentINDAssociations) > 0}">
                        <c:forEach items="${studyAgent.studyAgentINDAssociations }" var="sai">
                            <c:set var="indAssociationsString" value="${sai.investigationalNewDrug.strINDNo},&nbsp;&nbsp;${sai.investigationalNewDrug.holderName}" />
                        </c:forEach>
                    </c:if>

                    "name":"<c:out value="${studyAgent.agentName}" escapeXml="true" />",
                    "nsc":"<c:out value="${studyAgent.agent.nscNumber}" escapeXml="true" />",
                    "ind_ind":"<c:out value="${studyAgent.indType.displayName}" escapeXml="true" />",
                    "ind_num":"${indAssociationsString}",
                    "inv_drug":"${studyAgent.investigationalNewDrugIndicator ? 'Yes' : 'No'}",
                    "lead_ind":"${studyAgent.partOfLeadIND ? 'Yes' : 'No' }"
                },
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("agentsTableDiv", agentsJSONResult, agentsColumnDefs, agentsFields);
</script>

</chrome:division>
