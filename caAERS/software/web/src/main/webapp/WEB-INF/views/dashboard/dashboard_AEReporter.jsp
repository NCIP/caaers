<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:set var="commentsIcon"><img src="<chrome:imageUrl name="../editComment.png" />" /></c:set>
<jsp:useBean id="now" class="java.util.Date" scope="request" />
<c:if test="${not empty roles.ae_reporter or not empty roles.ae_expedited_report_reviewer}">

<chrome:boxIPhone style="width:700px;">
<jsp:attribute name="title"><caaers:message code="dashboard.taskNotifications" /></jsp:attribute>
<jsp:body>
    <div id="tasksNotifications" class="${fn:length(taskNotifications) gt 4 ? 'scrollerTask' : ''}">
        <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
            <tr class="taskTitleRow">
                <th nowrap>Date Created</th>
                <th>Subject</th>
                <th>Study</th>
                <th width="90px">Review Status</th>
                <th nowrap>Notification</th>
                <th nowrap></th>
                <%--<th nowrap>Actions</th>--%>
            </tr>
            <c:forEach items="${taskNotifications}" var="task" varStatus="index">
                <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                <tr class="${ALT} taskTitleRow" id="prevROW_${index.index}">
                    <td valign="top"><tags:formatDate value="${task.date}" /></td>
                    <td nowrap valign="top"><c:out value="${task.subjectFullName}" escapeXml="true" /></td>
                    <td valign="top"><c:out value="${task.primaryStudyIdentifier}" escapeXml="true" /></td>
                    <td valign="top"><c:out value="${task.status}" escapeXml="true" /></td>
                    <td valign="top"><c:out value="${task.task}" escapeXml="true" /></td>
                    <td align="RIGHT">
                        <img src="<c:url value="/images/orange-actions.gif" />?${requestScope.webCacheId}"
                             border="0"  id="report_${index}"
                             onmouseover='showDashboardTaskPortletMenuOptions(this, ${task.entityId}, "${task.entityType}", "${task.aeReportId}", "${task.status}","${index}")'
                             style="cursor: pointer;
                             margin-right: 15px;"></td>
                    <%--<td valign="top"><select name="s101"><option value="-">Please select</select></td>--%>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(taskNotifications) eq 0}">
                <tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr>
            </c:if>
        </table>
    </div>
</jsp:body>
</chrome:boxIPhone>

<script>
    var link = "<c:url value='/pages/study/edit?studyId=${study.id}' />";

 // TASK PORTLET ACTIONS

 	var curWin;
 	function displayPopup(ety, etyId) {
 	    var url = "ae/listReviewComments?entity=#{entity}&entityId=#{entityId}&subview".interpolate({entity:ety, entityId:etyId});
 	    curWin = new Window({className:"alphacube",destroyOnClose:true,title:"",url: url, width: 950, height: 400,   recenterAuto:true});
 	    curWin.showCenter(true);
 	}

 	function displayEditLink(url) {
 	    window.location = url;
 	}
 	

 	function showDashboardTaskPortletMenuOptions(_element, entityId,entityType, aeReportId, aeStatus,index) {
 		var _el = jQuery(_element);
 		var html="";
 		var label = 'Review';
		<c:if test="${isStaff}"> 
			if (aeStatus == 'Draft/Incomplete'){
		 		label = 'Edit'
			}
		</c:if>
 		if(entityType == 'report'){
 				
 			var _element = $('report_' + index);
 			var reportURL = '<c:url value= "/pages/ae/reviewResolver" />';
 			reportURL = reportURL
 					+ '?aeReport=' + aeReportId
 					+ '&report=' + entityId
 					+ '&viewOnly=true&src=RoutingReview';
 					
 			var _optionDetails = "";

 			
 			if ( aeStatus != 'COMPLETED') {
 				_optionDetails = '<li><a class="submitter-blue" href="#" onclick="displayEditLink('+ "'" + reportURL + "'" + ')"> <img src="<chrome:imageUrl name="../review.png" />"/>' + label +  '</a></li>';
 			}

 			var _comments =  '<li >' +
                     '<a style="cursor:pointer; border-bottom: none" href="#" onClick="displayPopup(\'report\',' + entityId + ');">${commentsIcon}Comment</a>' +
                     '</li>';
 		 			
 		 	_optionDetails = _optionDetails + _comments;
 	
 			 html = "<div><ul style='font-family:tahoma;'>" + _optionDetails
 				+ "</ul></div>";
 				
 			  _el.menu({
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
 				
 		} else if (entityType == 'reportingPeriod') {

 			var reportingPeriodPageURL = '<c:url value= "/pages/ae/reviewResolver" />';
 			reportingPeriodPageURL = reportingPeriodPageURL
 					+ '?adverseEventReportingPeriod=' + entityId
 					+ '&src=RoutingReview';

 			var _optionDetails = '<li><a class="submitter-blue" href="#" onclick="displayEditLink('
 					+ "'" + reportingPeriodPageURL + "'" + ')"><img src="<chrome:imageUrl name="../review.png" />"/>' + label +  '</a></li>';

			var _comments =  '<li >' +
                    '<a style="cursor:pointer; border-bottom: none" href="#" onClick="displayPopup(\'reportingPeriod\',' + entityId + ');">${commentsIcon}Comment</a>' +
                    '</li>';
			 			
			_optionDetails = _optionDetails + _comments;
 		 	
 					
 			html = "<div><ul style='font-family:tahoma;'>" + _optionDetails
 					+ "</ul></div>";
 			_el.menu({
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
 		
 	}
</script>

<chrome:boxIPhone style="width:700px;">
    <jsp:attribute name="title"><caaers:message code="dashboard.safetyReports" /> (<span id="safety-rep-cnt">0</span>)</jsp:attribute>
<jsp:body>

<table width="100%">
<tr>
    <td valign="top">
        <center>
            <div style="height:180px; border:0px green solid; margin:3px;"><div style="margin: 2px 0px 2px 0px; width:225px;" id="calendar-container"></div></div>
        </center>
    </td>
    <td valign="top">

    <script type="text/javascript">

        function flipMessage(id) {
            var msgID = "#MESSAGE_" + id;
            var picID = "#IMG_" + id;
            var prID = "#prevROW_" + id;

            if (jQuery(msgID).is(':hidden')) {
                jQuery(msgID).show();
                jQuery(picID).attr("src", jQuery(picID).attr("src").replace('plus', 'minus'));
                jQuery(prID).removeClass('taskTitleRow');
            }
            else {
                jQuery(msgID).hide();
                jQuery(picID).attr("src", jQuery(picID).attr("src").replace('minus', 'plus'));
                jQuery(prID).addClass('taskTitleRow');
            }
        }

        function dateChanged(calendar) {

            // Beware that this function is called even if the end-user only
            // changed the month/year.  In order to determine if a date was
            // clicked you can use the dateClicked property of the calendar:
            if (calendar.dateClicked) {
                var y = calendar.date.getFullYear();
                var m = calendar.date.getMonth() + 1;     // integer, 1..12
                var d = calendar.date.getDate();      // integer, 1..31
                var dKey =  ((m < 10 ? '0' : '') + m) + '/' + ( ( d < 10 ? '0' : '') + d ) + '/' + y
                jQuery('#reportActivity').scrollTo(dKey)
            }
        };




        AE.safetyTblTemplate = '<table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">  <tr> <th>Report name</th>  <th>Study/Subject</th>  <th>Status</th>  </tr> #{sfDataRows}</table>';
        AE.safetyTblDataRowTemplate = '<tr class="#{trCss}" style="border-bottom:1px #eeeeee solid;" ><td><a href="#{href}" onmouseover="showToolTip(#{toolTip});" onmouseout="tt_Hide();" >#{sfReportName}</a></td><td>#{sfStudySubject}</td><td><i>#{statusType}</i><span style="#{statusCss}">#{statusDate}</span></td></tr>';
        AE.noSafetyDataTblDataRowTemplate = '<tr><td colspan="3">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<caaers:message code="dashboard.noResults" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>' +
                '<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>' +
                '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>' +
                '<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>'
        AE.indicatorSafetyDataTblDataRowTemplate = '<tr><td colspan="3"><img src="<tags:imageUrl name="indicator.gif" />" /></td></tr>'
        AE.safetyReportTootipTemplate = '<span>' +
        '<b style="color:yellow;">Report Name: </b>#{sfReportName}<br> '  +
        '<b style="color:yellow;">Study: </b>#{studyShortTitle} <br> ' +
        '<b style="color:yellow;">Participant: </b>#{subjectName}<br>' +
        '<b style="color:yellow;">Study Site: </b> #{studySiteName} <br>' +
        '<b style="color:yellow;">Course: </b> Cycle #: #{courseName}<br>' +
        '</span>'

        AE.reportEditLinkTemplate = '<c:url value="/pages/ae/reviewResolver"/>?rvID=#{rvId}&aeReport=#{aeReportId}&report=#{reportId}&src=RoutingReview';
        AE.reportViewLinkTemplate = '<c:url value="/pages/ae/list" />?assignment=#{assignmentId}';
        function ourDateStatusFunc(date, year, month, day) {
            var y = '_' + year
            var d = (day < 10 ?  '_0': '_') + day
            var m = (month < 9 ? '0' : '') + (month + 1)


            var dKey = 'd_'+ m + d + y;

            if(day == 1)  {
                AE.safetyReportIndexMap = $H();
                AE.safetyReportDateIndexMap = $H();
                var dataRows =  ''
                $('reportActivity').innerHTML = AE.safetyTblTemplate.interpolate({sfDataRows:AE.indicatorSafetyDataTblDataRowTemplate})
                createAE.fetchSafetyReports(date,{ async:false, callback: function(safetyReports){
                    var i = 0;

                    //fetch the safety reports from server
                    if(safetyReports.length > 0) {

                        var trCss = ''
                        $A(safetyReports).each(function(rv){

                            AE.safetyReportIndexMap.set(rv.id, rv)
                            var rvKey = 'd_' + rv.statusDisplayDate.replace('/', '_').replace('/' , '_')
                            var rvCurrState =  AE.safetyReportDateIndexMap.get(rvKey)
                            if(rvCurrState == null){rvCurrState = false}
                            AE.safetyReportDateIndexMap.set(rvKey, rvCurrState || rv.due)

                            var stDate = rv.statusDisplayDate.escapeHTML()
                            var stType = ''
                            var stCss = '';
                            var reportName = rv.reportName.escapeHTML()
                            var studyId = rv.studyPrimaryIdentifier != null ? rv.studyPrimaryIdentifier.escapeHTML():''
                            var subjectId = rv.subjectPrimaryIdentifier != null ? rv.subjectPrimaryIdentifier.escapeHTML():''
                            var studyTitle = rv.studyShortTitle.escapeHTML()
                            var subjectName = rv.subjectDisplayName.escapeHTML()
                            var studySiteName = rv.studySiteDisplayName.escapeHTML()
                            var courseName = rv.courseDisplayName.escapeHTML()
                            var assignmentId = rv.assignmentID;
                            var reportId= rv.reportID;
                            var aeReportId = rv.aeReportID;
                            var toolTip = rv.id
                            var href = ""
                            trCss = rvKey + (i%2 > 0 ? ' ' : ' alt')
                            if(rv.due){  stType = 'Due on: ';stCss='color:#e74f4f'; href=AE.reportEditLinkTemplate.interpolate({rvId:rv.id, reportId:reportId, assignmentId:assignmentId, aeReportId: aeReportId}); }
                            if(rv.withdrawn) { stType = 'Withdrawn on: '; stCss='color:#2e83bb'; href=AE.reportViewLinkTemplate.interpolate({rvId:rv.id, reportId:reportId, assignmentId:assignmentId, aeReportId: aeReportId});}
                            if(rv.complete) { stType = 'Submitted on: ';stCss='color:#66a811'; href=AE.reportViewLinkTemplate.interpolate({rvId:rv.id, reportId:reportId, assignmentId:assignmentId, aeReportId: aeReportId});}
                            dataRows =  dataRows + AE.safetyTblDataRowTemplate.interpolate({sfReportName: reportName,
                                sfStudySubject : studyId + '<br/>' + subjectId,
                                statusDate: stDate,
                                trCss: trCss,
                                statusCss: stCss,
                                toolTip: toolTip ,
                                href:href,
                                statusType:stType
                            })
                            i++
                        });
                        var abcd =     AE.safetyReportDateIndexMap.toJSON()

                    } else {
                        dataRows =  AE.noSafetyDataTblDataRowTemplate
                    }

                    $('reportActivity').innerHTML = AE.safetyTblTemplate.interpolate({sfDataRows:dataRows})
                    $('safety-rep-cnt').innerHTML = safetyReports.length;
                }
            }
                );
            }
            if( AE.safetyReportDateIndexMap == null) return false;
            var xxx =     AE.safetyReportDateIndexMap.toJSON()

            var  hasDue = AE.safetyReportDateIndexMap.get(dKey)
            if(hasDue == null) return false;
            if(hasDue) return  "pastDue"; else return 'reportActivity';
            return false
        }


        function initCalendar() {
            Calendar.setup({
                firstDay     :1,
                flat         : "calendar-container", // ID of the parent element
                flatCallback : dateChanged,           // our callback function
                dateStatusFunc : ourDateStatusFunc,
                weekNumbers : true,
                showOthers  : false
            });
        }

        Event.observe(window, 'load', function() {
            initCalendar();

            jQuery("td.quickLinkBGon").mouseover(function() {
                jQuery(this).removeClass('quickLinkBGon');
                jQuery(this).addClass('quickLinkBGoff');
            });

            jQuery("td.quickLinkBGon").mouseout(function() {
                jQuery(this).removeClass('quickLinkBGoff');
                jQuery(this).addClass('quickLinkBGon');
            });
        });

        function showToolTip(rvId) {

            var rv =  AE.safetyReportIndexMap.get(rvId)

            var reportName = rv.reportName.escapeHTML()
            var studyTitle = rv.studyShortTitle.escapeHTML()
            var subjectName = rv.subjectDisplayName.escapeHTML()
            var studySiteName = rv.studySiteDisplayName.escapeHTML()
            var courseName = rv.courseDisplayName.escapeHTML()
            var text = AE.safetyReportTootipTemplate.interpolate({
                sfReportName: reportName, studyShortTitle :  studyTitle,   subjectName :  subjectName, studySiteName: studySiteName,courseName: courseName
            })

            Tip(text,
                    WIDTH, 300,
                    TITLE, '',
                    SHADOW, false,
                    FADEIN, 300,
                    FADEOUT, 300,
                    STICKY, 1,
                    CLOSEBTN, false,
                    CLICKCLOSE, false,
                    OPACITY, 90,
                    FONTCOLOR, "#fff",
                    BORDERCOLOR, "#444",
                    BGCOLOR, "#444",
                    PADDING, 15,
                    FONTSIZE, "11px"
            );
        }


    </script>

<div id="ticker-container">
<div id="reportActivity" class="scroller">

</div>
</div>

    </td>
<tr>
</table>


</jsp:body>
</chrome:boxIPhone>

<script>
jQuery(function( $ ){
	$('#dueReports').serialScroll({
        next: 'img.nextOne',
        prev: 'img.prevOne',
		items:'tr',
		duration:500,
        cycle:false,
		force:true,
		axis:'y',
		easing:'linear',
		step:2
	});
	$('#reportActivity').serialScroll({
        next: 'img.nextTwo',
        prev: 'img.prevTwo',
		items:'tr',
		duration:500,
        cycle:false,
		force:true,
		axis:'y',
		easing:'linear',
		step:2
	});
	$('#tasksNotifications').serialScroll({
		items:'tr',
		duration:500,
        cycle:false,
		force:true,
		axis:'y',
		easing:'linear',
		step:2
	});
});

</script>

</c:if>
