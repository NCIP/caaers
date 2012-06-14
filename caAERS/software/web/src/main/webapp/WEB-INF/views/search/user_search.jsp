<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<html>
	<head>

        <style>
            div.yui-dt-liner a:hover  {
                color: white;
            }

            .yui-dt table {
                width: 100%;
            }

            .autocomplete {
                width: 510px;
            }

            div.row div.label {
                width: 9em;
            }

            div.row div.value {
                margin-left: 10em;
            }

            .fg-button.submitter {
                font-family: Verdana;
                font-size: 9pt;
            }
        </style>

        <title><caaers:message code="user.search.pageTitle"/></title>
		<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
			<tags:dwrJavascriptLink objects="user"/>
		<script language="JavaScript">

            jQuery(document).ready(function() {
//                doSearch();
            });

            function doSearch() {
                var _name = jQuery.trim(jQuery('#propName').val());
                var _identifier = jQuery.trim(jQuery('#propPi').val());
                var _organization = jQuery.trim(jQuery('#organization').val());
                var _userName = jQuery.trim(jQuery('#propUn').val());
                if (_name == "" && _identifier == "" && _organization == "" && _userName == "") {
                    showFlashErrorMessage(3);
                } else {
                    buildTable('assembler');
                    $('bigSearch').show();
                }
            }

			function buildTable(form) {
				showCoppaSearchDisclaimer();

				var map = new Object();
				map['name']=$('propName').value;
//				map['lastName']=$('propLn').value;
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
			    hideCoppaSearchDisclaimer();
			}

			var linkFormatter = function(elCell, oRecord, oColumn, oData) {
		        var _id = oRecord.getData("id");
		        var _recordType = oRecord.getData("recordType");
		        var _userName = oRecord.getData("userName");
		        elCell.title = _recordType;
		        if(oData){
                    if(_userName){
                       elCell.innerHTML = "<a href='editUser?id=" + _id + "&userName=" + _userName + "&recordType=" + _recordType + "'>" + oData + "</a>";
                    }else{
                        elCell.innerHTML = "<a href='editUser?id=" + _id + "&recordType=" + _recordType + "'>" + oData + "</a>";
                    }

		        }
			};

			var linkFormatterWithNCI = function(elCell, oRecord, oColumn, oData) {
		        var _nr = oRecord.getData("externalId");
		        var _recordType = oRecord.getData("recordType");
		        var _s = "";
		        if (_nr != '') _s = '<img src="<c:url value="/images/chrome/nci_icon_22.png" />">';
		        elCell.innerHTML = _s;
		        elCell.title = _recordType;
			};						
		
			var nameFormatter = function(elCell, oRecord, oColumn, oData) {
		        var _fn = oRecord.getData("firstName");
		        var _mn = oRecord.getData("middleName");
		        var _ln = oRecord.getData("lastName");
                var _recordType = oRecord.getData("recordType");

		        elCell.innerHTML = _fn + "&nbsp;" + _mn + "&nbsp;" + _ln;
		        elCell.title = _recordType;
			};

            function doEdit(id, rt, un) {
                var url = '<c:url value="/pages/admin/editUser?"></c:url>';
                url += "id=" + id;
                url += "&recordType=" + rt;
                if (un != "") url += "&userName=" + un;
                window.location = url;
            }

            var win;
            function hideWin() {
                win.close();
            }

            function doActivate(personId, rt, userName, action) {
                user.activateUser(personId, userName, action, function (_result) {
                    win = new Window({className:"alphacube", width:400, height:80, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
                    win.setContent("success");
                    win.showCenter(true);
                    win.show();
                    jQuery('#_span' + personId).html(actionsRow.interpolate({id:personId, rt:rt, un:userName, active:(action == 'Active' ? "Inactive" : "Active")}));
                    hideWin.delay(2);
                })
            }

            function showMenuOptions(strId, rt, un, active) {
//                alert(strId + "," + rt + "," + un +  "," + active);
                var html_start = "<div><ul style='font-family:tahoma;'>";
                var html_end = "</ul></div>";
                var _editAction = "<li><a class='submitter-blue' href='#' onclick='javascript:doEdit(#{strId}, \"#{rt}\", \"#{un}\")'>Edit</a></li>";
                var _action = "Activate";
                if (active == "Active") {
                    _action = "Deactivate"
                }
                var _activateAction = "<li><a class='submitter-blue' href='#' onclick='javascript:doActivate(#{strId}, \"#{rt}\", \"#{un}\", \"#{active}\")'>" + _action + "</a></li>";
                var html = html_start + _editAction + (un != "" ? _activateAction : "") + html_end;
                var html = html.interpolate({strId:strId, rt:rt, un:un, active:active});
                jQuery('#personnelActions' + strId).menu({
                        content: html,
                        maxHeight: 180,
                        positionOpts: {
                            directionV: 'down',
                            posX: 'left',
                            posY: 'bottom',
                            offsetX: 0,
                            offsetY: 0
                        },
                        showSpeed: 300
                    });
            }

            var actionsRow = "<a onmouseover='showMenuOptions(#{id}, \"#{rt}\", \"#{un}\", \"#{active}\")' id='personnelActions#{id}' class='submitterButton submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all'>Actions<span class='ui-icon ui-icon-triangle-1-s'></span></a>";

			var actionsFormatter = function(elCell, oRecord, oColumn, oData) {
                var _id = oRecord.getData("id");
                var _rt = oRecord.getData("recordType");
                var _un = oRecord.getData("userName");
                var _active = oRecord.getData("active");
		        elCell.innerHTML = "<span id='_span" + _id + "'>" + actionsRow.interpolate({id:_id, rt:_rt, un:_un, active:_active}) + "</span>";
			};

			var fullNameFormatter = function(elCell, oRecord, oColumn, oData) {
                var _firstName = oRecord.getData("firstName");
                var _lastName = oRecord.getData("lastName");
		        elCell.innerHTML = _firstName + '&nbsp;' + _lastName;
			};

			var myColumnDefs = [
				{key:"externalId",      label:"&nbsp;",              	  sortable:true,      resizeable:true, formatter: linkFormatterWithNCI, maxWidth:20, minWidth:20},
                {key:"firstName",       label:"Name",    	      sortable:true,      resizeable:true, formatter:fullNameFormatter},
                {key:"number",          label:"Person identifier",sortable:true,      resizeable:true},
                {key:"organization",    label:"Organization(s)",  sortable:false,     resizeable:true},
                {key:"userName",        label:"Username",    	  sortable:true,      resizeable:true},
                {key:"actions",        label:"Actions",    	  sortable:true,      resizeable:true, formatter: actionsFormatter}
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
                {key:'id',           	parser:"integer"},
                {key:'externalId',   	parser:"string"},
                {key:'active',   	    parser:"string"}
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
    			<tags:instructions code="personUserSearchInstructions" />

                <div class="errors" id="flashErrors" style="display: none;">
                    <span id="command_errors"><caaers:message code="ERR_enter.search.criteria" /></span>
                </div>

    			<form:form name="searchForm" id="searchForm" method="post">
    				<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
    				<caaers:message code="user.search.criteriaSection" var="criteriaSectionTitle"/>
    				<chrome:box title="Search Criteria" cssClass="mpaired" autopad="false">
						<div class="row">
			                <div class="label"><caaers:message code="LBL_Name"/>&nbsp; </div>
			                <div class="value"><input id="propName" name="name" type="text"/></div>
			            </div>

						<div class="row">
			                <div class="label"><caaers:message code="LBL_personIdentifier"/>&nbsp; </div>
			                <div class="value"><input id="propPi" name="personIdentifier" type="text"/></div>
			            </div>			            
			            
			            <div class="row">
							<div class="label"><caaers:message code="LBL_organization"/>&nbsp; </div>
								<div class="value">
									<ui:autocompleter path="organization"
										initialDisplayValue="Begin typing here" enableClearButton="true">
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
				            		<option value="Please Select"><caaers:message code="LBL_please.select" />
									<option value="ResearchStaff"><caaers:message code="LBL_research.staff" />
									<option value="Investigator"><caaers:message code="LBL_investigator" />
								</select>
							</div>
						</div>			            
						
						<div class="row">
							<div class="value" style="float:left;">
						   		<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="doSearch();"/>
						   		<tags:indicator id="indicator"/>
						   		<span id="coppa-search-disclaimer" class="coppa-search-disclaimer" style="display:none;"><caaers:message code="coppa.search.message" /></span>
							</div>
						</div>
        
        
        			<div class="endpanes"></div>								    					
    				</chrome:box>
    			</form:form>
    			
				<div id="bigSearch" style="display:none;">
			         <br>
			         <form:form id="assembler">
			         	<input type="hidden" name="CSRF_TOKEN" value="${CSRF_TOKEN }"/>
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

        <div id="success" class="flash-message info" style="display: none;"><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_user.updated" /></div>

	</body>
</html>