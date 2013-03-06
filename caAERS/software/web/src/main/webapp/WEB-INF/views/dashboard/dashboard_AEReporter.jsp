<%--
Copyright SemanticBits, Northwestern University and Akaza Research

Distributed under the OSI-approved BSD 3-Clause License.
See http://ncip.github.com/caaers/LICENSE.txt for details.
--%>
<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:if test="${not empty roles.ae_reporter or not empty roles.ae_expedited_report_reviewer}">

<chrome:boxIPhone style="width:700px;">
<jsp:attribute name="title"><caaers:message code="dashboard.taskNotifications" /></jsp:attribute>
<jsp:body>
    <div id="tasksNotifications" class="<c:if test="${fn:length(taskNotifications) > 4}">scrollerTask</c:if>">
        <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
            <tr class="taskTitleRow">
                <th nowrap>Date Created</th>
                <th>Subject</th>
                <th>Study</th>
                <th width="90px">Review Status</th>
                <th nowrap>Notification</th>
                <th nowrap>Comments</th>
                <%--<th nowrap>Actions</th>--%>
            </tr>
            <c:forEach items="${taskNotifications}" var="task" varStatus="index">
                <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                <tr class="${ALT} taskTitleRow" id="prevROW_${index.index}">
                    <td valign="top"><tags:formatDate value="${task.date}" /></td>
                    <td nowrap valign="top">${task.subjectFullName}</td>
                    <td valign="top">${task.studyShortTitle}</td>
                    <td valign="top">${task.status}</td>
                    <td valign="top">${task.task}</td>
                    <td valign="top" align="center"><a style="cursor:pointer; border-bottom: none" onClick="displayPopup('reportingPeriod', ${task.reportingPeriodId});"><img src="<chrome:imageUrl name="../editComment.png" />" /></a></td>
                    <%--<td valign="top"><select name="s101"><option value="-">Please select</select></td>--%>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(taskNotifications) == 0}">
                <tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr>
            </c:if>
        </table>
    </div>
</jsp:body>
</chrome:boxIPhone>

<script>
    var link = "<c:url value='/pages/study/edit?studyId=${study.id}' />";
</script>

<chrome:boxIPhone style="width:700px;">
    <jsp:attribute name="title"><caaers:message code="dashboard.safetyReports" /></jsp:attribute>
<jsp:body>
<c:if test="${fn:length(reportActivity) > 0}">
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
                scrollByDate(y, m, d);
            }
        };

        <jsp:useBean id="now" class="java.util.Date" scope="request" />

        var ACTIVITYDATES = {
            <c:forEach items="${reportActivity}" var="rvDTO" varStatus="index">
                <c:set var="_s" value="${rvDTO.rv.reportStatus}" />
                <c:choose>
                    <c:when test="${_s eq 'COMPLETED' or _s eq 'AMENDED'}">'<tags:formatDate value="${rvDTO.rv.submittedOn}" />':1,</c:when>
                    <c:when test="${_s eq 'PENDING' or _s eq 'INPROCESS' or _s eq 'FAILED'}">'<tags:formatDate value="${rvDTO.rv.dueOn}" />':-1,</c:when>
                </c:choose>
            </c:forEach>"0000/00/00":false}

        function scrollByDate(y, m, d) {
            if (m < 10) m = '0' + m;
            if (d < 10) d = '0' + d;
            // $('CC').innerHTML = y + '-' + m + '-' + d;

            var _el1 = 'AB_' +  m + '_' + d + '_' + y;
            var _el2 = 'CD_' +  m + '_' + d + '_' + y;
            // alert(_el1 + '/' + _el2);
            if ($(_el2)) {
                jQuery('#dueReports').scrollTo(jQuery('#' + _el2));
            } else {
                var cdClosestRed = findClosestTo(-1, y, m - 1, d);
                cdClosestRed = "CD_" + cdClosestRed.replace(/\//gi, "_");
                jQuery('#dueReports').scrollTo(jQuery('#' + cdClosestRed));
                // $('CC').innerHTML = cdClosestRed;
            }

            if ($(_el1)) {
                jQuery('#reportActivity').scrollTo(jQuery('#' + _el1))
            } else {
                var abClosestGreen = findClosestTo(0, y, m - 1, d);
                abClosestGreen = "AB_" + abClosestGreen.replace(/\//gi, "_");
                jQuery('#reportActivity').scrollTo(jQuery('#' + abClosestGreen))
            };
        }
        /*
        *
        * */
        function days_between(date1, date2) {
            // The number of milliseconds in one day
            var ONE_DAY = 1000 * 60 * 60 * 24

            // Convert both dates to milliseconds
            var date1_ms = date1.getTime()
            var date2_ms = date2.getTime()

            // Calculate the difference in milliseconds
            var difference_ms = Math.abs(date1_ms - date2_ms)

            // Convert back to days and return
            return Math.round(difference_ms/ONE_DAY)
        }

        /*
        * lookupValue - 0 for SKIP, -1 PastDueDate, 1 - Submitted
        *
        * */
        function findClosestTo(lookupValue, y, m, d) {
            var diff = 0;
            var closest = "";
            var selectedDate = new Date(y, m, d);
            var selectedTime = selectedDate.getTime();
            var compared = false;

            $H(ACTIVITYDATES).each(function(pair) {
                if (pair.value == lookupValue || lookupValue == 0) {
                    var d = new Date(pair.key);
                    var _time = d.getTime();

                    if (!compared || Math.abs(selectedTime - _time) < diff) {
                        compared =  true;
                        closest = pair.key;
                        diff = (selectedTime - _time);
                    }
                }
            });

            return closest;
        }

        function ourDateStatusFunc(date, year, month, day) {
            var _m = month + 1; var _d = day; var _y = year;
            if (_m < 10) _m = '0' + _m; if (_d < 10) _d = '0' + _d;
            if (ACTIVITYDATES[_m + '/' + _d + '/' + _y] == 1) return "reportActivity";
            if (ACTIVITYDATES[_m + '/' + _d + '/' + _y] == -1) return "pastDue";
            return false;
            // other dates are enabled
            // return true if you want to disable other dates
        };

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
            scrollByDate(<fmt:formatDate value="${now}" pattern="yyyy"/>, <fmt:formatDate value="${now}" pattern="MM"/>, <fmt:formatDate value="${now}" pattern="dd"/>);

            jQuery("td.quickLinkBGon").mouseover(function() {
                jQuery(this).removeClass('quickLinkBGon');
                jQuery(this).addClass('quickLinkBGoff');
            });

            jQuery("td.quickLinkBGon").mouseout(function() {
                jQuery(this).removeClass('quickLinkBGoff');
                jQuery(this).addClass('quickLinkBGon');
            });
        });

        function showToolTip(text, title) {
            Tip(text,
                    WIDTH, 300,
                    TITLE, title,
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

    <c:set var="_DATE" value="" />
    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
        <c:forEach items="${reportActivity}" var="rvDTO" varStatus="index">
            <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
            <%--<c:if test="${index.first}"> first</c:if><c:if test="${index.last}"> last</c:if>--%>

            <%--
                Computing the ID of the row
                id the date is the same as previews row them display the ID based on index
                otherwise ID is based on date's value, for scrolling by ID
            --%>
            <c:set var="_dateString"><jsp:attribute name="value"><c:if test="${rvDTO.rv.submittedOn ne null}"><tags:formatDate value="${rvDTO.rv.submittedOn}" /></c:if><c:if test="${rvDTO.rv.submittedOn eq null}"><tags:formatDate value="${rvDTO.rv.dueOn}" /></c:if></jsp:attribute></c:set>
            <c:set var="_ID" value="tr_RA_${index.index}" />

            <c:if test="${_DATE ne _dateString}">
                <c:set var="_ID" value="${fn:replace(_dateString, '/', '_')}" />
                <c:set var="_DATE" value="${_dateString}" />
            </c:if>

            <span id="_Description${rvDTO.rv.id}" style="display:none;">
                <b style="color:yellow;">Report Name: </b>${rvDTO.reportName}<br>
                <b style="color:yellow;">Study: </b>${rvDTO.studyShortTitle}<br>
                <b style="color:yellow;">Participant: </b>${rvDTO.subjectFirstName}&nbsp;${rvDTO.subjectLastName}&nbsp(${rvDTO.subjectPrimaryIdentifier})<br>
                <b style="color:yellow;">Study Site: </b>${rvDTO.studySiteName}&nbsp;(${rvDTO.studySiteCode})<br>
                <b style="color:yellow;">Course: </b> Cycle #: ${rvDTO.periodCycle}&nbsp;<c:if test="${not empty rvDTO.periodStartDate}">(<fmt:formatDate value="${rvDTO.periodStartDate}" />)</c:if><br>
            </span>
            <tr class="${ALT}" style="border-bottom:1px #eeeeee solid;" id="AB_${_ID}">
                <c:set var="_s" value="${rvDTO.rv.reportStatus}" />

                <td>&nbsp;<a <c:if test="${_s eq 'COMPLETED' or _s eq 'WITHDRAWN'}"></c:if> onmouseover="showToolTip($('_Description${rvDTO.rv.id}').innerHTML, '');" onmouseout="tt_Hide();" <c:if test="${_s ne 'COMPLETED' and _s ne 'WITHDRAWN'}">href="<c:url value="/pages/ae/edit?rvID=${rvDTO.rv.id}&aeReport=${rvDTO.aeReportID}&report=${rvDTO.reportID}" />"</c:if><c:if test="${_s eq 'COMPLETED' or _s eq 'WITHDRAWN'}">href="<c:url value="/pages/ae/list?assignment=${rvDTO.assignmentID}" />"</c:if> >${rvDTO.reportName}</a></td>
                <%--<td>${rvDTO.rv.reportStatus}</td>--%>
                <c:choose>
                    <c:when test="${_s eq 'PENDING' or _s eq 'INPROCESS' or _s eq 'FAILED'}">
                        <td><i>Due on:</i></td>
                        <td><span style="color:#e74f4f"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span></td>
                    </c:when>
                    <c:when test="${_s eq 'WITHDRAWN' or _s eq 'REPLACED'}">
                        <td><i>Withdrawn on:</i></td>
                        <td><span style="color:#2e83bb;"><tags:formatDate value="${rvDTO.rv.withdrawnOn}" /></span></td>
                    </c:when>
                    <c:when test="${_s eq 'COMPLETED' or _s eq 'AMENDED'}">
                        <td><i>Submited on:</i></td>
                        <td><span style="color:#66a811;"><tags:formatDate value="${rvDTO.rv.submittedOn}" /></span></td>
                    </c:when>
                </c:choose>
<%--
                <c:if test="${(rvDTO.rv.submittedOn eq null or rvDTO.rv.reportStatus ne 'COMPLETED') and rvDTO.rv.dueOn ne null}">
                    <td align="right"><i>Due on:</i></td>
                    <td align="left">
                        <span style="color:#e74f4f"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span>
                    </td>
                </c:if>
                <c:if test="${rvDTO.rv.submittedOn ne null and rvDTO.rv.reportStatus eq 'COMPLETED'}">
                    <td align="right" width="100px"><i>Submited on:</i></td>
                    <td align="left" width="70px"><span style="color:#66a811;"><tags:formatDate value="${rvDTO.rv.submittedOn}" /></span></td>
                </c:if>
                <c:if test="${rvDTO.rv.submittedOn eq null and rvDTO.rv.dueOn eq null}">
                    <td align="right" width="100px"><i>Withdrawn on:</i></td>
                    <td align="left" width="70px"><span style="color:#2e83bb;"><tags:formatDate value="${rvDTO.rv.withdrawnOn}" /></span></td>
                </c:if>
--%>
            </tr>
        </c:forEach>
    </table>

</div>
</div>

    </td>
<tr>
</table>
</c:if>
<c:if test="${fn:length(reportActivity) == 0}">
    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%"><tr><td colspan="5"><caaers:message code="dashboard.noResults" /></td></tr></table>
</c:if>
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

var curWin;
function displayPopup(ety, etyId) {
    var url = "ae/listReviewComments?entity=#{entity}&entityId=#{entityId}&subview".interpolate({entity:ety, entityId:etyId});
    curWin = new Window({className:"alphacube",destroyOnClose:true,title:"",url: url, width: 950, height: 400,   recenterAuto:true});
    curWin.showCenter(true);
}

</script>

</c:if>
