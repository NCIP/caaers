<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp"%>


<chrome:division title="Study Devices" jsAction="goToPage('AgentsTab')">

    <div id="devicesTableDiv"></div>

<script language="JavaScript">

    var devicesColumnDefs = [
        {key:"brandName", label:"Brand name", sortable:true, resizeable:true, minWidth:"150", maxWidth:"300"},
        {key:"commonName", label:"Common name", sortable:true, resizeable:true, minWidth:"200", maxWidth:"200"},
        {key:"deviceType", label:"Device type", sortable:true, resizeable:true, minWidth:"200", maxWidth:"200"},
        {key:"ind_num", label:"IDE #", sortable:true, resizeable:true, minWidth:"150", maxWidth:"300"},
        {key:"inv_drug", label:"Investigational new device?", sortable:true, resizeable:true, minWidth:"150", maxWidth:"300"}
    ];

    var devicesFields = [
        {key:'brandName', parser:"string"},
        {key:'commonName', parser:"string"},
        {key:'deviceType', parser:"string"},
        {key:'ind_num', parser:"string"},
        {key:'inv_drug', parser:"string"}
    ];

    devicesJSONResult = [
        <c:forEach items="${command.study.activeStudyDevices}" var="sd">
            <c:if test="${!sd.retiredIndicator}">
                {
                	<c:set var="indAssociationsString" value="" />
	                <c:if test="${fn:length(sd.studyDeviceINDAssociations) > 0}">
	                    <c:forEach items="${sd.studyDeviceINDAssociations }" var="sai">
	                    	<c:if test="${not empty sai.investigationalNewDrug}">
                            	<c:set var="indAssociationsString" value="${sai.investigationalNewDrug.strINDNo},&nbsp;&nbsp;${sai.investigationalNewDrug.holderName}" />
                         	</c:if>
	                     </c:forEach>
	                </c:if>
                
	                "brandName":"${sd.brandName}", 
	                "commonName":"${sd.commonName}", 
	                "deviceType":"${sd.deviceType}",
	                "ind_num":"${indAssociationsString}",
	                "inv_drug":"${sd.investigationalNewDrugIndicator ? 'Yes' : 'No'}"
	                
	            },
            </c:if>
        </c:forEach>
    ];

    initializeYUITable("devicesTableDiv", devicesJSONResult, devicesColumnDefs, devicesFields);
</script>

</chrome:division>
