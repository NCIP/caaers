<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="search"/>

<script>
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
	search.getAgentsTable(parameterMap, name, nsc, showTable);
    $('bigSearch').show();
}
</script>

<div class="tabpane">


        <admin:agent3rdLevelMenu selected="search" />

<div class="content">

    <form:form name="searchForm" id="searchForm" method="post">

            <chrome:box title="Search Agents" autopad="false">
             <tags:instructions code="admin.agent.search" />
                  <div class="row">
                      <div class="label"><caaers:message code="LBL_agent.name" /></div>
                      <div class="value"><input id="name" type="text" name="name"/></div>
                  </div>
                  <div class="row">
                      <div class="label"><caaers:message code="LBL_agent.nscNumber" /></div>
                      <div class="value"><input id="nsc" type="text" name="nsc"/></div>
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

          <div class="endpanes" />
          <div class="endpanes" />


         </form:form>

           <div id="bigSearch" style="display:none;">
               <form:form id="assembler">
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

</div>