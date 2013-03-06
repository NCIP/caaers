<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@ include file="/WEB-INF/views/taglibs.jsp"%>
<page:applyDecorator name="standardNoHeader">
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
	    <tags:dwrJavascriptLink objects="user"/>
		<script language="JavaScript">

			function buildTable(form) {

				var map = new Object();
				map['firstName']=$('propFn').value;
				map['lastName']=$('propLn').value;

                if($('propUn')){
                   map['userName']=$('propUn').value;
                }

				if($('personIdentifier')){
                  map['personIdentifier']=$('propPi').value;
				  map['personType']=$('propPt').value;
				  map['organization']=$('organization').value;
                }
                map['linkType'] = '${command.popupRequestType}';


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
		        var _recordType =  oRecord.getData("recordType") ;
		        var _userName =  oRecord.getData("userName");
                var _linkType = '${command.popupRequestType}' ;

		        elCell.title = _recordType;

                var jsLink = "\"javascript:window.parent.updateAfterLinking(" + _id + ",'" + _userName + "','" + _recordType + "','" + _linkType + "' )\"";
		        if(oData){
		        	elCell.innerHTML = "<a onclick=" + jsLink + " href='#bm' >" + oData + "</a>";
		        }
			};

			var myColumnDefs = [
				{key:"externalId",      label:"",              	  sortable:true,      resizeable:true, formatter: linkFormatter, maxWidth:20, minWidth:20},
                {key:"firstName",       label:"First name",    	  sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"middleName",      label:"Middle name",      sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"lastName",        label:"Last name",     	  sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"number",          label:"Person identifier",sortable:true,      resizeable:true, formatter: linkFormatter},
                {key:"organization",    label:"Organization(s)",  sortable:false,     resizeable:true, formatter: linkFormatter},
                {key:"userName",        label:"Username",    	  sortable:true,      resizeable:true, formatter: linkFormatter}
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
                {key:'externalId',   	parser:"string"}
            ];

		</script>
	</head>
	<body>

		<div class="tabpane">
            <a name="bm"> </a>
    		<div class="content">
    			<form:form name="searchForm" id="searchForm" method="post">
    				<caaers:message code="user.search.criteriaSection" var="criteriaSectionTitle"/>
    				<chrome:box title="Search Criteria" cssClass="mpaired" autopad="false">
						<div class="row">
			                <div class="label"><caaers:message code="LBL_firstName"/>&nbsp; </div>
			                <div class="value"><input id="propFn" name="firstName" type="text"/></div>
			            </div>

			            <div class="row">
			                <div class="label"><caaers:message code="LBL_lastName"/>&nbsp; </div>
			                <div class="value"><input id="propLn" name="lastName" type="text"/></div>
			            </div>
                       <c:if test="${command.personAtrributesShown}">
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
                               <div class="label"><caaers:message code="LBL_personType"/>&nbsp; </div>
                               <div class="value">
                                   <select name="propPt" id="propPt">
                                       <option value="Please Select">Please Select
                                       <option value="ResearchStaff">ResearchStaff
                                       <option value="Investigator">Investigator
                                   </select>
                               </div>
                           </div>

			            </c:if>
                        <c:if test="${command.userAttributesShown}">
                            <div class="row">
                                <div class="label"><caaers:message code="LBL_loginId"/>&nbsp; </div>
                                <div class="value"><input id="propUn" name="userName" type="text"/></div>
                            </div>
                        </c:if>
						<div class="row">
							<div class="value" style="float:left;">
						   		<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler'); $('bigSearch').show();"/>
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
</page:applyDecorator>
