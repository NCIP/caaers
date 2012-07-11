<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="search"/>

<style>


.yui-dt table { width: 100%; }


</style>

<script>

 function textName_OnKeyDown(e){
  		var keynum;                                 
                    if(window.event) // IE                              
                    {
                            keynum = e.keyCode
                    }
                    else if(e.which) // Netscape/Firefox/Opera
                    {
                            keynum = e.which
                    }                   

                    if (keynum == 13) 
                    {
                           buildTable('assembler');
                            return false;
                    }
  
  }


function buildTable(form) {
	$('indicator').className='';
//	showCoppaSearchDisclaimer();
	var name = $('name').value;
	var nsc = $('nsc').value;

/*
	for(var x=0; x < 2; x++) {
		if ( $('prop' + x).value.length > 0 ){
			text = text +  $('prop' + x).value + "";
			type = type +  $('prop' + x).name + '';
		}
	}
*/

	$('name').value = name;
    $('nsc').value = nsc;

	var parameterMap = getParameterMap(form);
	search.getAgentsTable(parameterMap, name, nsc, ajaxCallBack);
    $('bigSearch').show();
}

function ajaxCallBack(jsonResult) {
    $('indicator').className = 'indicator';
    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
    hideCoppaSearchDisclaimer();
}

var linkFormatter = function(elCell, oRecord, oColumn, oData) {
        var orgId = oRecord.getData("id");
        elCell.innerHTML = "<a href='asaelEdit?agentID=" + orgId + "'>" + oData + "</a>";
};

var myColumnDefs = [
    {key:"name",             label:"Name",          sortable:true,      resizeable:true, formatter: linkFormatter},
    {key:"nscNumber",        label:"NSC",           sortable:true,      resizeable:true, minWidth:200, maxWidth:200},
    {key:"retiredIndicator",        label:"Retired",           sortable:true,      resizeable:true, minWidth:200, maxWidth:200, formatter: retireFormatter}
];

var myFields = [
    {key:'id',           parser:"integer"},
    {key:'name',         parser:"string"},
    {key:'nscNumber',    parser:"string"},
    {key:'retiredIndicator',    parser:"string"}
];
</script>



        <admin:agent3rdLevelMenu selected="search" />

<div class="content">

    <form:form name="searchForm" id="searchForm" method="post">
    	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>

            <chrome:box title="Search Criteria" autopad="false">
             <tags:instructions code="admin.agent.search" />
                  <div class="row">
                      <div class="label"><caaers:message code="LBL_agent.name" /></div>
                      <div class="value"><input id="name" type="text" name="name" onkeydown="return textName_OnKeyDown(event)"/></div>
                  </div>
                  <div class="row">
                      <div class="label"><caaers:message code="LBL_agent.nscNumber" /></div>
                      <div class="value"><input id="nsc" type="text" name="nsc" onkeydown="return textName_OnKeyDown(event)"/></div>
                  </div>
<%--
                  <div class="row">
                      <div class="label">NSC</div>
                      <div class="value"><input id="prop1" type="text" name="nsc"/></div>
                  </div>
--%>

                 <div class="row">
                  <div class="value">
                      <tags:button type="button" value="Search" color="blue" icon="search" onclick="buildTable('assembler');" size="small"/>
                      <tags:indicator id="indicator" />
                  </div>
                 </div>
            </chrome:box>

         </form:form>

           <div id="bigSearch" style="display:none;">
               <form:form id="assembler">
               		<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
                   <div>
                       <input type="hidden" name="_name" id="_name">
                       <input type="hidden" name="_nsc" id="_nsc">
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

