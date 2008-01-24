
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>AEs for ${command.participant.fullName} on ${command.study.shortTitle}</title>
    <tags:stylesheetLink name="extremecomponents"/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <style type="text/css">
        .notify-unit.success {
            color: #090;
        }

        .notify-unit.failure {
            color: #900;
        }
        
        
    </style>
    <script type="text/javascript">
    
    	function toggleImage(id){
			imageStr=document.getElementById(id).src;
			//	alert(imageStr);
			if(imageStr.indexOf('plus')==-1)
				document.getElementById(id).src=imageStr.replace('minus','plus');
			else
				document.getElementById(id).src=imageStr.replace('plus','minus');	
			//	alert(document.getElementById(id).src)
		}
		
		function withdraw(aeReportId,reportId) {
            AE.showIndicator("notify-indicator-" + aeReportId)
            createAE.withdrawReportVersion(aeReportId, reportId, function(result) {
                AE.hideIndicator("notify-indicator-" + aeReportId)
                 var status = $("status"+reportId)
         		 var statusData = "<span class='submittedOn' ><i>Withdrawn <\/i><\/span>";
          
          		var action = $("action"+reportId)
          		actionData = $("amend"+reportId).innerHTML;
          
          		Element.update(status, statusData)
          		Element.update(action, actionData)
            })
        }
    
    
        function notifyPsc(aeReportId) {
            AE.showIndicator("notify-indicator-" + aeReportId)
            createAE.pushAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-" + aeReportId)
                var unit = $("notify-unit-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }
		

		function notifyPscRoutineEvent(aeReportId) {

            AE.showIndicator("notify-indicator-routine-" + aeReportId)
            createAE.pushRoutineAdverseEventToStudyCalendar(aeReportId, function(result) {
                AE.hideIndicator("notify-indicator-routine-" + aeReportId)
                var unit = $("notify-unit-routine-" + aeReportId)
                if (result) {
                    Element.update(unit, "Notified")
                    Element.addClassName(unit, "success")
                } else {
                    Element.update(unit, "Notification failed")
                    Element.addClassName(unit, "failure")
                }
            })
        }
        
        function executeAction(aeReportId,url){
			
			var actions = $("actions-"+aeReportId)
			 for ( i=0; i < actions.length; i++)
               {
                  if (actions.options[i].selected && actions.options[i].value != "none") {
                     window.open(url + "&format="+ actions.options[i].value,"_self")
                   }
               }
        }

		
        Event.observe(window, "load", function() {
            
            $$("a.notify").each(function(a) {
                Event.observe(a, "click", function(e) {
                    Event.stop(e);
                    var aeReportId = Event.element(e).id.substring(7)
                    notifyPsc(aeReportId)
                })
            })

            $$("a.notify-routine").each(function(a) {
                Event.observe(a, "click", function(e) {
                    Event.stop(e);
                    var roReportId = Event.element(e).id.substring(14)
                    notifyPscRoutineEvent(roReportId)
                })
            })
        })
    </script>
</head>
<body>
<c:if test="${not empty configuration.map.pscBaseUrl}">
<p>
    View this person's schedule in the <a href="${configuration.map.pscBaseUrl}/pages/cal/schedule?assignment=${command.assignment.gridId}" class="sso">study calendar</a>.
</p>
</c:if>

<h2>Expedited Reports<c:if test="${command.study.status ne 'Administratively Complete'}">
<a href="<c:url value="/pages/ae/create?participant=${command.participant.id}&study=${command.study.id}&action=create"/>">( create )</a>
</c:if>
</h2>
<!-- STUDY SEARCH RESULTS START HERE -->
<div class="eXtremeTable" >
<table width="80%" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
	<c:if test="${fn:length(command.assignment.aeReports) > 0}">
		<thead>
		<tr align="center" class="label">
			<td class="tableHeader"></td>
			<td class="tableHeader">Term ( * refers to Primary AE data )</td>
			<td class="tableHeader">Primary AE start date</td>
			<td class="tableHeader">Actions</td>
		</tr>
		</thead>
	</c:if>
	<%int i=0; %>
	<c:forEach items="${command.assignment.aeReports}" var="report" varStatus="statusReport">
		<% String currClass=i%2==0? "odd":"even"; %>
		<%--
		<c:choose>
			<c:when test="${!empty subjectId}">
				<c:set var="documentLocation" value="${url}?participant=${subjectId }&resumeFlow=true&_page=1&_target3=3&studySite=" />
			</c:when>
			<c:otherwise>
				<c:set var="documentLocation" value="${url}?studySiteId=" />	
			</c:otherwise>
		</c:choose>
		--%>
		<tr align="center" id="row<%= i++ %>" class="<%= currClass %>" onMouseOver="this.className='highlight'"
				onMouseOut="this.className='<%= currClass %>'">
			<td width="2%" onClick="
				<c:choose>
					<c:when test="${fn:length(report.reports) > 0}">
						new Element.toggle('studySites-table-${statusReport.index }');
							toggleImage('image-open-${statusReport.index }');
					</c:when>
					<c:otherwise>
					</c:otherwise>
				</c:choose>
			">
					<c:if test="${fn:length(report.reports) > 0}">
						<img id="image-open-${statusReport.index }" src="<c:url value="/images/b-plus.gif"/>" border="0" alt="expand">
					</c:if>
			</td>
			<td align="left">
					<c:if test="${report.areAllReportsSubmitted == false}">
					<a href="<c:url value="/pages/ae/edit?aeReport=${report.id}"/>">
            		</c:if>
            			<table width="100%" class="tablecontent">
                    			<c:forEach items="${report.adverseEvents}" var="adverseEvent" varStatus="termStatus">
                    				<c:choose>
									<c:when test="${termStatus.index == 0}">
										<tr>
										<td width="80%" class='${statusReport.index % 2  == 0 ? "odd" : "even"}'>* ${adverseEvent.adverseEventTerm.universalTerm}<br /></td>
										<td class='${statusReport.index % 2  == 0 ? "odd" : "even"}' >* grade  ${adverseEvent.grade.code}</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
										<td width="80%" class='${statusReport.index % 2  == 0 ? "odd" : "even"}'>${adverseEvent.adverseEventTerm.universalTerm}<br /></td>
										<td class='${statusReport.index % 2  == 0 ? "odd" : "even"}' >&nbsp;&nbsp;grade  ${adverseEvent.grade.code}</td>
										</tr>
									</c:otherwise>
									</c:choose>
    							</c:forEach>
                		
                		</table>
                	<c:if test="${report.areAllReportsSubmitted == false}">	
            		</a>
            		</c:if>
			</td>
			
			<td><tags:formatDate value="${report.adverseEvents[0].startDate}"/></td>
			
			<td width="20%">
					
					<SELECT id="actions-${report.id}" name="actions" onChange="executeAction(${report.id},'<c:url value='/pages/ae/generateExpeditedfPdf?aeReport=${report.id}'/>')">
     					<OPTION selected label="none" value="none">None</OPTION>
     					<OPTION label="pdf" value="pdf">AdEERS PDF</OPTION>
     					<OPTION label="medwatchpdf" value="medwatchpdf">MedWatch PDF</OPTION>
     					<OPTION label="dcp" value="dcp">DCP SAE form</OPTION>
     					<OPTION label="xml" value="xml">caAERS XML</OPTION>
					</SELECT>
					
					|
					
					<c:if test="${report.notificationMessagePossible}">
                		<span class="notify-unit" id="notify-unit-${report.id}">
                    	<a id="notify-${report.id}" class="notify" href="#">notify PSC</a>
                    	<tags:indicator id="notify-indicator-${report.id}"/>
                		</span>
           			</c:if>
					
					<%--
           			<a href="<c:url value="/pages/ae/generateExpeditedfPdf?aeReport=${report.id}&format=pdf"/>">AdEERS PDF</a> <br/>
           			<a href="<c:url value="/pages/ae/generateExpeditedfPdf?aeReport=${report.id}&format=medwatchpdf"/>">MedWatch PDF</a> <br/>
           			<a href="<c:url value="/pages/ae/generateExpeditedfPdf?aeReport=${report.id}&format=dcp"/>">DCP SAE form</a> <br/>
                   	<a href="<c:url value="/pages/ae/generateExpeditedfPdf?aeReport=${report.id}&format=xml"/>">caAERS XML</a>
           			--%>
			</td>
			
		</tr>
		<c:if test="${fn:length(report.reports) > 0}">
			<tr id="studySites-table-${statusReport.index }" style="display:none;">
				<td colspan="1">&nbsp;</td>
				<td colspan="5" height="0" class>
					<div id="studySites-${statusReport.index }">
					<table width="75%" height="0" border="0" cellspacing="0" cellpadding="0" class="tableRegion">
						<thead>
						<tr>
							<td class="tableHeader">Report</td>
							<td class="tableHeader">Report Id</td>
							<td class="tableHeader">Report version</td>
							<td class="tableHeader">Data complete</td>
							<td class="tableHeader">Status</td>
							<td class="tableHeader">Actions</td>
						</tr>
						</thead>
						<%int j=i*100; %>
						<c:forEach items="${report.reports}" var="theReport" varStatus="siteIndex">
						<% String currClassJ=j%2==0? "odd":"even"; %>
							<tr align="center" id="row<%= j++ %>" class="<%= currClassJ %>" onMouseOver="this.className='highlight'"
									onMouseOut="this.className='<%= currClass %>'" 
									>
									
								<td width="20%">${theReport.reportDefinition.name}</td>
								<td width="10%">${theReport.lastVersion.reportVersionId}</td>
								<td width="20%">${fn:length(theReport.reportVersions) -1}</td>
								<td width="10%"><i>
									<c:if test="${command.reportsSubmittable[theReport.id]}" >
										Complete
									</c:if>
									<c:if test="${not command.reportsSubmittable[theReport.id]}" >
										Incomplete
									</c:if>	
									</i>
								</td>
								<td width="20%" id="status${theReport.id}">
									<c:if test="${theReport.lastVersion.reportStatus == 'PENDING'}" >
										<span class="dueOn" >
											<c:if test="${not empty theReport.lastVersion.dueOn}" >
            								<i>Due on</i> <br> <b><tags:formatDate value="${theReport.lastVersion.dueOn}" /></b>
            								</c:if>
            								<c:if test="${ empty theReport.lastVersion.dueOn}" >
            								<i>Amendment Due</i>
            								</c:if>
            							</span>
            						</c:if>
            						<c:if test="${theReport.lastVersion.reportStatus == 'WITHDRAWN'}" >
										<span class="submittedOn" >
            								<i>Withdrawn</i>
            							</span>
            						</c:if>
            						<c:if test="${theReport.lastVersion.reportStatus == 'COMPLETED'}" >
            							<span class="submittedOn" >
            								<i>Submitted on </i><br> <b><tags:formatDate value="${theReport.lastVersion.submittedOn}" /></b>
            							</span>
            						</c:if>
								</td>
								<td width="50%" id="action${theReport.id}">
									<c:if test="${command.reportsSubmittable[theReport.id]}" >
										<c:if test="${theReport.lastVersion.reportStatus == 'PENDING'}" >
											<center>
												<a href="<c:url value="/pages/ae/submitReport?aeReport=${report.id}&reportId=${theReport.id}&from=list"/>">Submit</a> |	
												<a href="#" onClick="withdraw(${report.id},${theReport.id})">Withdraw</a>
											</center>
										</c:if>
										
										<c:if test="${ theReport.reportDefinition.amendable and ((theReport.lastVersion.reportStatus == 'WITHDRAWN') or (theReport.lastVersion.reportStatus == 'COMPLETED') )}" >
											<center>
												<a href="<c:url value="/pages/ae/edit?aeReport=${report.id}&reportId=${theReport.id}"/>">Amend</a>
											</center>
										</c:if>
										
										
            						</c:if>
								</td>
							</tr>
							<!-- This is a hack a better way to pass this to javscript -->
							<span id="amend${theReport.id}" style="display:none;">
								<center>
            						<a href="<c:url value="/pages/ae/edit?aeReport=${report.id}&reportId=${theReport.id}"/>">Amend</a>
            					</center>
							</span>
						</c:forEach>
					</table>
					</div>
				</td>
			</tr>
		</c:if>
	</c:forEach>
</table>


<%--

<c:set var="ecImagePath"><c:url value="/images/table/*.gif"/></c:set>
<ec:table
    items="command.assignment.aeReports"
    var="report" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass=""
    filterable="false">
    <ec:row>
        <ec:column property="adverseEvents[0].adverseEventTerm.universalTerm" title="Term">
            <a href="<c:url value="/pages/ae/edit?aeReport=${report.id}"/>">
            <c:choose>
                <c:when test="${not empty report.adverseEvents[0].adverseEventTerm}">
                    <c:forEach items="${report.adverseEvents}" var="adverseEvent">
                		${adverseEvent.adverseEventTerm.universalTerm}<br />
                
    				</c:forEach>
                </c:when>
                <c:when test="${not empty report.labs}">
                    [Lab-based incomplete AE]
                </c:when>
                <c:otherwise>
                    [Incomplete AE]
                </c:otherwise>
            </c:choose>
            </a>
        </ec:column>
        <ec:column property="createdAt" title="Start date">
            <tags:formatDate value="${report.adverseEvents[0].startDate}"/>
        </ec:column>
        <ec:column property="adverseEvents[0].grade.code" title="Grade"/>
        <ec:column property="adverseEvents[0].attribution.code" title="Attribution"/>
        <ec:column title="Notify Study Calendar" sortable="false" filterable="false" property="dc">
            <c:if test="${report.notificationMessagePossible}">
                <span class="notify-unit" id="notify-unit-${report.id}">
                    <a id="notify-${report.id}" class="notify" href="#">Notify</a>
                    <tags:indicator id="notify-indicator-${report.id}"/>
                </span>
            </c:if>
        </ec:column>
    </ec:row>
</ec:table>
--%>

<br>
<h2>Routine AEs
<c:if test="${command.study.status ne 'Administratively Complete'}"><a href="<c:url value="/pages/ae/createRoutine?participant=${command.participant.id}&study=${command.study.id}&action=create"/>">( create )</a></c:if>
</h2>
<% int ind= 0; %>
<ec:table
    items="command.assignment.aeRoutineReports"
    var="routineReport" imagePath="${ecImagePath}"
    showPagination="false"
    cellspacing="0" cellpadding="0" border="0" width="80%"
    style="" styleClass=""
    filterable="false">
    <ec:row>
        <ec:column property="adverseEvents[0].adverseEventTerm.universalTerm" title="Term">
            <a href="<c:url value="/pages/ae/editRoutine?aeRoutineReport=${routineReport.id}"/>">
            <c:choose>
                <c:when test="${not empty routineReport.adverseEvents[0].adverseEventTerm}">
                	<c:forEach items="${routineReport.adverseEvents}" var="adverseEvent">
                		<table width="100%" class="tablecontent">
                		<tr>
                		<td width="90%" class='<% out.println(ind % 2 == 0 ? "odd" : "even");  %>'>${adverseEvent.adverseEventTerm.universalTerm}</td>
 						<td class='<% out.println(ind % 2 == 0 ? "odd" : "even");  %>' >grade  ${adverseEvent.grade.code}</td></tr>
 						</table>	
    				</c:forEach>
                    
                </c:when>
                <c:otherwise>
                    [Incomplete AE]
                </c:otherwise>
            </c:choose>
            <% ind++; %>
            </a>
        </ec:column>
        <ec:column property="startDate" title="Start date">
            <tags:formatDate value="${routineReport.startDate}"/>
        </ec:column>

        <ec:column title="Actions" sortable="false" filterable="false" property="dc">

         <c:if test="${routineReport.notificationMessagePossible}">

           <span class="notify-unit" id="notify-unit-routine-${routineReport.id}">

            <a id="notify-routine${routineReport.id}" class="notify-routine" href="#">notify PSC</a>

            <tags:indicator id="notify-indicator-routine-${routineReport.id}"/>

           </span>

         </c:if>

        </ec:column>
    </ec:row>
</ec:table>
</body>
</html>