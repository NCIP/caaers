<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<html>
	<head>

		<style>
			.yui-pg-page { padding: 5pt; }
			.yui-dt-label .yui-dt-sortable { color: white; }
			.yui-dt table { width: 100%; }
			div.yui-dt-liner a {color : black;}
			tr.yui-dt-even { background-color: #FFF; border-bottom: 1px gray solid;}
			tr.yui-dt-odd { background-color: #EDF5FF; border-bottom: 2px blue dotted; padding: 2px; }
		</style>

		<style type="text/css">
		    div.row div.label { width: 9em; }
		    div.row div.value { margin-left: 10em; }
		</style>

		<title><caaers:message code="user.search.pageTitle"/></title>
		<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
			<tags:dwrJavascriptLink objects="user"/>
		<script language="JavaScript">

			function buildTable(form) {
				$('indicator').className=''
					user.getUserTable($('propFn').value, $('propLn').value, $('propUn').value, ajaxCallBack);
			    $('bigSearch').show();
			}
	
			function ajaxCallBack(jsonResult) {
			    $('indicator').className = 'indicator';
			    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
			}

			var linkFormatter = function(elCell, oRecord, oColumn, oData) {
		        var _userName = oRecord.getData("userName");
		        elCell.innerHTML = "<a href='editUser?userName=" + _userName + "'>" + oData + "</a>";
			};			
		
			var myColumnDefs = [
                {key:"firstName",       label:"First Name",    	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"lastName",        label:"Last Name",     	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"userName",        label:"Username",    	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"emailAddress",	label:"Email Address",  sortable:true,      resizeable:true}
            ];

            var myFields = [
                {key:'firstName',    	parser:"string"},
                {key:'lastName',     	parser:"string"},
                {key:'userName',    	parser:"string"},
                {key:'emailAddress',   	parser:"string"},
                {key:'id',           	parser:"integer"}
            ];
			
		</script>
	</head>
	<body>
		<div class="tabpane">
			<div class="workflow-tabs2">
    			<ul id="" class="tabs autoclear">
                        <li id="thirdlevelnav" class="">
                            <div>
                            	<a href="createUser"><caaers:message code="user.menu.createUser"/></a>
                            </div>
                        </li>
                    	<li id="thirdlevelnav" class="selected">
                    		<div>
                    			<a href="searchUser"><caaers:message code="user.menu.searchUser"/></a>
                    		</div>
                    	</li>
                </ul>
    		</div>
    		
    		<div class="content">
    			<form:form name="searchForm" id="searchForm" method="post">
    				<caaers:message code="user.search.criteriaSection" var="criteriaSectionTitle"/>
    				<chrome:box title="Search User" cssClass="mpaired" autopad="false">
						<div class="row">
			                <div class="label"><caaers:message code="LBL_firstName"/>&nbsp; </div>
			                <div class="value"><input id="propFn" name="firstName" type="text"/></div>
			            </div>
			
			            <div class="row">
			                <div class="label"><caaers:message code="LBL_lastName"/>&nbsp; </div>
			                <div class="value"><input id="propLn" name="lastName" type="text"/></div>
			            </div>
						
						<div class="row">
			                <div class="label"><caaers:message code="LBL_loginId"/>&nbsp; </div>
			                <div class="value"><input id="propUn" name="userName" type="text"/></div>
			            </div>
						
						<div class="row">
							<div class="value" style="float:left;">
						   		<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler'); $('bigSearch').show();"/>
						   		<tags:indicator id="indicator"/>
							</div>
						</div>
        
        			<div class="endpanes"></div>								    					
    				</chrome:box>
    			</form:form>
    			
				<div id="bigSearch" style="display:none;">
			         <br>
			         <form:form id="assembler">
			             <div>
			                 <input type="hidden" name="_prop" id="prop">
			                 <input type="hidden" name="_value" id="value">
			             </div>
			             <caaers:message code="user.search.resultsSection" var="resultsSectionTitle"/>
			             <chrome:box title="${resultsSectionTitle}">
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