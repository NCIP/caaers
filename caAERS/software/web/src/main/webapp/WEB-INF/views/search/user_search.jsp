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
				var map = new Object();

				map['firstName']=$('propFn').value;
				map['lastName']=$('propLn').value;
				map['userName']=$('propUn').value;
				map['personIdentifier']=$('propPi').value;	
				map['personType']=$('propPt').value;
				map['organization']=$('organization').value;	

				$('indicator').className=''
					user.getResults(map, ajaxCallBack);
			    $('bigSearch').show();
			}
	
			function ajaxCallBack(jsonResult) {
			    $('indicator').className = 'indicator';
			    initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
			}

			var linkFormatter = function(elCell, oRecord, oColumn, oData) {
		        var _id = oRecord.getData("id");
		        var _recordType = oRecord.getData("recordType");
		        var _userName = oRecord.getData("userName");
		        elCell.innerHTML = "<a href='editUser?id=" + _id + "&userName=" + _userName + "&recordType=" + _recordType + "'>" + oData + "</a>";
			};			
		
			var myColumnDefs = [
                {key:"firstName",       label:"First Name",    	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"middleName",      label:"Middle Name",    sortable:true,      resizeable:true},
                {key:"lastName",        label:"Last Name",     	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"number",          label:"Person Identifier",     	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"organization",    label:"Organization(s)",sortable:false,     resizeable:true},
                {key:"userName",        label:"Username",    	sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"emailAddress",	label:"Email Address",  sortable:true,      resizeable:true, formatter: linkFormatter}
            ];

            var myFields = [
                {key:'firstName',    	parser:"string"},
                {key:'middleName',    	parser:"string"},
                {key:'lastName',     	parser:"string"},
                {key:'number',     	    parser:"string"},
                {key:'organization',    parser:"string"},
                {key:'userName',    	parser:"string"},
                {key:'emailAddress',   	parser:"string"},
                {key:'recordType',   	parser:"string"},
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
			                <div class="label"><caaers:message code="LBL_personIdentifier"/>&nbsp; </div>
			                <div class="value"><input id="propPi" name="personIdentifier" type="text"/></div>
			            </div>			            
			            
			            <div class="row">
							<div class="label"><caaers:message code="LBL_organization"/>&nbsp; </div>
								<div class="value">
									<ui:autocompleter path="organization"
										initialDisplayValue="Begin typing here..." enableClearButton="true">
										<jsp:attribute name="populatorJS">
											function(autocompleter, text) {
							         				user.restrictOrganization(text, function(values) {
							    					autocompleter.setChoices(values)
							  					})
							     			}
										</jsp:attribute>
										<jsp:attribute name="selectorJS">
											function(organization){
								        		var image;            	
										    	if(organization.externalId != null){
										                  image = '&nbsp;<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>&nbsp';
										        } else {
										                  image = '';
										        }
								    			return image + organization.name + " (" + organization.nciInstituteCode + ")";
											}
										</jsp:attribute>
									</ui:autocompleter>
								</div>
			            </div>			            
			            
						<div class="row">
			                <div class="label"><caaers:message code="LBL_loginId"/>&nbsp; </div>
			                <div class="value"><input id="propUn" name="userName" type="text"/></div>
			            </div>
			            
						<div class="row">
			            	<div class="label"><caaers:message code="LBL_personType"/>&nbsp; </div>
			            	<div class="value">
				            	<select name="propPt" id="propPt">
				            		<option value="Please Select">Please Select
									<option value="ResearchStaff">Research Staff
									<option value="Investigator">Investigator
								</select>
							</div>
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