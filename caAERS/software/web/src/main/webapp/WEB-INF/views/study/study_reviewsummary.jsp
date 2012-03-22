<%@include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/caaers/css/solicited_ae.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<tags:dwrJavascriptLink objects="createStudy"/>
<title>${tab.longTitle}</title>
<style type="text/css">
       .label { width: 12em; padding: 1px;  margin-right: 0.5em; } 
       div.row div.value  {white-space:normal;}
	   #studyDetails td.label { font-weight: bold; float: left; margin-left: 0.5em; margin-right: 0.5em; width:12em; padding: 1px; }

       .yui-dt-label .yui-dt-sortable {
           color: black;
           text-decoration: none;
           /*border: 1px solid #ACACAC;*/
                      padding: 3px 7px;
                      text-shadow: 0 1px white;

       }

       #agentsTableDiv table {
/*
            color:red;
            border-collapse:separate;
            border-spacing: 0px;
            border-color: red;
*/
       }

       th.yui-dt-resizeable .yui-dt-resizerliner .yui-dt-liner a:hover {
           color:black;
       }

       th.yui-dt-resizeable .yui-dt-resizerliner .yui-dt-liner {

           background: url("../../images/table/tablecontent_header.jpg") repeat-x scroll center center transparent;
           border: 1px solid #000;
           padding: 2px 2px;
           text-shadow: 0 1px white;

           position: relative;
           font-size: 13px;
           font-weight: bold;
           margin: 0px;
           color: white;
           text-shadow: 0 -1px #2166a1;
           height: auto;
           padding-top: 2px;
           padding-bottom: 2px;
           text-decoration: none
       }

</style>
<!--[if lte IE 6]>
<style>
	#main {
		top:50px;
	}
</style> 
<![endif]-->

</head>
<body>

<script language="javascript">

    var tabsHash = new Hash();
    <c:forEach items="${flow.tabs}" var="atab" varStatus="status">
    <csmauthz:accesscontrol domainObject="${atab}" authorizationCheckName="tabAuthorizationCheck">
        tabsHash.set('${atab.class.simpleName}','${atab.number}');
    </csmauthz:accesscontrol>
    </c:forEach>

    function goToPage(s) {
        $('_target').name = '_target' + tabsHash.get(s);
        $('command').submit();
    }
</script>

<tags:instructions code="study.study_overview.top" />
<c:if test="${command.study.id > 0}">
    <div class="instructions row">
    	<div class="value">
    		<a href='<c:url value="/pages/study/export?id=${command.study.id}" />'><img src="<c:url value="/images/blue/xml-icon.png"/> " border=""> Export XML</a>
    	</div>
    </div>
</c:if>


<tags:tabForm tab="${tab}" flow="${flow}" formName="review" hideBox="true" hideTabControls="${flow.tabCount < 2}">
    <jsp:attribute name="repeatingFields">
       <c:if test="${(empty command.study.id) or ( command.study.id le 0) }"><input type="hidden" name="_finish" value="true"/></c:if>
	   <div class="summary">

        <chrome:division>

        		<div class="row">
                	<div class="label">Primary identifier</div>
                	<div class="value"><c:out value="${command.study.primaryIdentifier.value}" escapeXml="true" /> </div>
            	</div>
           		<div class="row">
                	<div class="label">Title</div>
                	<div class="value"><c:out value="${command.study.shortTitle}" escapeXml="true" /></div>
            	</div>
            	<div class="row">
                	<div class="label">Funding sponsor</div>
                	<c:if test="${command.study.primaryFundingSponsorOrganization.externalId != null}">
                		<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                	</c:if>                	
                	<div class="value"><c:out value="${command.study.primaryFundingSponsorOrganization.name}" escapeXml="true" />  </div>
            	</div>
				<div class="row">
                	<div class="label">Coordinating center</div>
                	<div class="value">
                	<c:if test="${command.study.studyCoordinatingCenter.organization.externalId != null}">
                		<img src="<chrome:imageUrl name="nci_icon_22.png"/>" alt="NCI data" width="17" height="16" border="0" align="middle"/>
                	</c:if>
                	<c:out value="${command.study.studyCoordinatingCenter.organization.name}" escapeXml="true" />
                	</div>
            	</div>

        		<div class="row">
                	<div class="label">Phase code</div>
                	<div class="value">${command.study.phaseCode} </div>
            	</div>
            	<div class="row">
                	<div class="label">Terminology</div>
                	<div class="value">${command.study.aeTerminology.term} </div>
            	</div>	
            	<div class="row">
                	<div class="label">Terminology Version</div>
                	<div class="value">${command.study.aeTerminology.term eq 'CTC' ? command.study.aeTerminology.ctcVersion.name : command.study.aeTerminology.meddraVersion.name} </div>
            	</div>
            	<c:if test="${command.study.aeTerminology.term eq 'CTC'}">
            	<div class="row">
	            	<div class="label">Other MedDRA</div>
	            	<div class="value"><c:out value="${command.study.otherMeddra.name}" escapeXml="true" /> </div>
	            </div>
                </c:if>
                <div class="row">
                    <div class="label"><caaers:message code="LBL_study.aeTermUnique"  /></div>
                    <div class="value">${command.study.aeTermUnique ? 'Yes' : 'No'}</div>
                </div>
            	<div class="row">
                	<div class="label"><caaers:message code="LBL_study.verbatimFirst" /></div>
                	<div class="value">${command.study.verbatimFirst == null ? '' : command.study.verbatimFirst ? 'Yes' : 'No'}</div>
            	</div>
            	<div class="row">
                	<div class="label">Data Entry Status</div>
                	<div id="data-entry-status-div" class="value">${command.dataEntryStatus} </div>
            	</div>		
       </chrome:division>

    <%@ include file="study_summary_agents.jsp" %>
    <%@ include file="study_summary_treatmentAssignments.jsp" %>
    <%@ include file="study_summary_sites.jsp" %>
    <%@ include file="study_summary_investigators.jsp" %>
    <%@ include file="study_summary_personnel.jsp" %>
    <%@ include file="study_summary_diseases.jsp" %>
    <%@ include file="study_summary_expected_aes.jsp" %>
    <%@ include file="study_summary_devices.jsp" %>
    <%@ include file="study_summary_other_interventions.jsp" %>
    <%@ include file="study_summary_identifiers.jsp" %>

	<chrome:division title="Evaluation Period Types & Solicited Adverse Events" jsAction="goToPage('SolicitedAdverseEventTab')">
  		<study:solicitedAETable displayOnly="true" />
    </chrome:division>

	</div>
    </jsp:attribute>
    <jsp:attribute name="localButtons"></jsp:attribute>
</tags:tabForm>

</body>
</html>
