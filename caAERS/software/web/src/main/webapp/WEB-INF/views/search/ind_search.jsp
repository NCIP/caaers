<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Search IND#</title>

<title>${tab.longTitle}</title>
<tags:dwrJavascriptLink objects="search"/>

<style>
.yui-dt table { width: 100%; }

</style>

<script>

function buildTable(form) {
	$('indicator').className=''
	var type = "";
	var text = "";

	for(var x=0; x < 2; x++) {
	
		if ( $('prop'+x).value.length > 0 ){
			text = text +  $('prop'+x).value + ",";
			type = type +  $('prop'+x).name +',';
		}
	}
	
	$('prop').value=type
	$('value').value=text
	
	var parameterMap = getParameterMap(form);		
	search.getINDTable(parameterMap, type, text, ajaxCallBack);
    $('bigSearch').show();
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

var myColumnDefs = [
    {key:"indNumber",               label:"IND #",                          sortable:true,      resizeable:true, minWidth:200, maxWidth:200},
    {key:"holderName",              label:"Sponsor Name",                   sortable:true,      resizeable:true}
];

var myFields = [
    {key:'indNumber',               parser:"string"},
    {key:'holderName',              parser:"string"}
];

</script>
</head>
<body>
<csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.InvestigationalNewDrug:CREATE" var="_hasCreate"/>

    <div class="workflow-tabs2">
        <ul id="" class="tabs autoclear">
            <c:if test="${_hasCreate}">
              <li id="thirdlevelnav" class="tab"><div><a href="createIND">Create IND#</a></div></li>
            </c:if>
            <li id="thirdlevelnav" class="tab selected"><div><a href="#">Search IND#</a></div></li>
        </ul>
        <tags:pageHelp propertyKey="searchIND"/>
    </div>

    <div class="content">
        <form:form name="searchForm" id="searchForm" method="post">
        	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
            
            <chrome:box title="Investigational New Drug Criteria" cssClass="mpaired" autopad="false">
            	<tags:instructions code="indsearch"/>
                <div class="row">
                    <div class="label"> IND #</div>
                    <div class="value"><input id="prop0" name="strINDNumber" type="text"/></div>
                </div>

                <div class="row">
                    <div class="label"> IND holder</div>
                    <div class="value"><input id="prop1" type="text" name="sponsorName"/></div>
                </div>

                <div class="endpanes"></div>
                <div class="row">
					<div class="value">
	                    <tags:button type="button" value="Search" color="blue" icon="search" onclick="buildTable('assembler');" size="small"/>
	                    <tags:indicator id="indicator"/>
					</div>
                </div>
                <div class="endpanes"></div>
            </chrome:box>


        </form:form>
        <div id="bigSearch" style="display:none;">
            <form:form id="assembler">
            	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
                <div>
                    <input type="hidden" name="_prop" id="prop">
                    <input type="hidden" name="_value" id="value">
                </div>
                <chrome:box title="Search Results">
                    <chrome:division id="single-fields">
                        <div id="tableDiv">
                            <c:out value="${assembler}" escapeXml="false"/>
                        </div>
                    </chrome:division>
                </chrome:box>
            </form:form>
        </div>
    </div>

</body>
</html>
