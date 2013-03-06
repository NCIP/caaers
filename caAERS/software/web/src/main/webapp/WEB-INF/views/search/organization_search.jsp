<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Search Organizations</title>
<tags:dwrJavascriptLink objects="search"/>

<style>
.yui-pg-page { padding: 5pt; }
.yui-dt-label .yui-dt-sortable { color: white; }
.yui-dt table { width: 100%; }
div.yui-dt-liner a {color : black;}
tr.yui-dt-even { background-color: #FFF; border-bottom: 1px gray solid;}
tr.yui-dt-odd { background-color: #EDF5FF; border-bottom: 2px blue dotted; padding: 2px; }
</style>

<script>

function buildTable(form) {
	$('indicator').className='';
	showCoppaSearchDisclaimer();
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
	search.getOrganizationTable(parameterMap, type, text, ajaxCallBack);
    $('bigSearch').show();
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

var linkFormatterWithNCI = function(elCell, oRecord, oColumn, oData) {
        var _nr = oRecord.getData("externalId");
        var _s = "";
        if (_nr != '') _s = '<img src="<c:url value="/images/chrome/nci_icon_22.png" />">';
        elCell.innerHTML = _s;
};

var linkFormatter = function(elCell, oRecord, oColumn, oData) {
        var orgId = oRecord.getData("id");
        elCell.innerHTML = "<a href='editOrganization?organizationId=" + orgId + "'>" + oData + "</a>";
};

var myColumnDefs = [
    {key:"externalId",        label:"",              sortable:true,      resizeable:true, formatter: linkFormatterWithNCI, maxWidth:20, minWidth:20},
    {key:"name",                    label:"Name",                          sortable:true,      resizeable:true, formatter: linkFormatter},
    {key:"nciInstituteCode",        label:"Assigned Identifier",           sortable:true,      resizeable:true, minWidth:200, maxWidth:200}
];

var myFields = [
    {key:'id',                  parser:"integer"},
    {key:'name',                parser:"string"},
    {key:'nciInstituteCode',    parser:"string"},
    {key:'externalId',    parser:"string"}
];

</script>
</head>
<body>

<div class="tabpane">
<div class="workflow-tabs2">
 <ul id="" class="tabs autoclear">
    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Organization:CREATE">
    <li id="thirdlevelnav" class="tab"><div><a href="createOrganization">Create Organization</a></div></li>
    </csmauthz:accesscontrol>
    <csmauthz:accesscontrol objectPrivilege="gov.nih.nci.cabig.caaers.domain.Organization:READ">
    <li id="thirdlevelnav" class="tab selected"><div><a href="searchOrganization">Search Organization</a></div></li>
    </csmauthz:accesscontrol>
 </ul>
	<tags:pageHelp propertyKey="searchOrganization" />
 </div>
 <div class="content">
  <form:form name="searchForm" id="searchForm" method="post">



   <chrome:box title="Search Criteria" cssClass="mpaired" autopad="false">
   	<tags:instructions code="organizationsearch" />
		    <div class="row">
		    	<div class="label">Name</div>
		    	<div class="value"><input id="prop0" name="name" type="text"/></div>
		    </div>
		    
		    <div class="row">
		    	<div class="label">Assigned Identifier</div>
		    	<div class="value"><input id="prop1" type="text" name="nciInstituteCode"/></div>
		    </div>

           <div class="row">
        	<div class="value">
        		<tags:button type="button" value="Search" color="blue" icon="search" onclick="buildTable('assembler');" size="small"/>
	            <tags:indicator id="indicator" />
	            <span id="coppa-search-disclaimer" class="coppa-search-disclaimer" style="display:none;"><caaers:message code="coppa.search.message" /></span>
        	</div>
           </div>
   </chrome:box>

	<div class="endpanes" />
	<div class="endpanes" />


   </form:form>

     <div id="bigSearch" style="display:none;">
         <form:form id="assembler">
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
 
</div>
</body>
</html>
