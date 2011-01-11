<%@include file="/WEB-INF/views/taglibs.jsp" %>
<c:if test="${not empty roles.ae_reporter}">

            <chrome:boxIPhone title="Alerts" style="width:700px;">
            </chrome:boxIPhone>

            <chrome:boxIPhone title="Report Activity" style="width:700px;">
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
                            scrollByDate(y, m, d);
                        }
                    };

                    <jsp:useBean id="now" class="java.util.Date" scope="request" />

                    // -1 - Past Due
                    // 1  - Submitted
                    // 2  - Due on (future)
                    var ACTIVITYDATES = {
                        <c:forEach items="${reportActivity}" var="rvDTO" varStatus="index">
                        <c:if test="${rvDTO.rv.submittedOn ne null}">'<tags:formatDate value="${rvDTO.rv.submittedOn}" />':1,</c:if>
                        <c:if test="${rvDTO.rv.submittedOn eq null && rvDTO.rv.dueOn ne null}">
<%--
                            <c:set var="_t"><jsp:attribute name="value"><fmt:formatDate value="${now}" pattern="yyyy/MM/dd" /></jsp:attribute></c:set>
                            <c:set var="_due"><jsp:attribute name="value"><fmt:formatDate value="${rvDTO.rv.dueOn}" pattern="yyyy/MM/dd" /></jsp:attribute></c:set>
                            <c:if test="${_due le _t}">"<tags:formatDate value="${rvDTO.rv.dueOn}" />":-1,</c:if>
                            <c:if test="${_due gt _t}">"<tags:formatDate value="${rvDTO.rv.dueOn}" />":2,</c:if>
--%>
                            "<tags:formatDate value="${rvDTO.rv.dueOn}" />":-1,
                        </c:if>
<%--
                        "<tags:formatDate value="${rvDTO.rv.dueOn}" />":-1,</c:if>
                        <c:if test="${rvDTO.rv.submittedOn eq null && rvDTO.rv.dueOn ne null && rvDTO.rv.dueOn gt now}">"<tags:formatDate value="${rvDTO.rv.dueOn}" />":2,</c:if>
--%>
                        </c:forEach>"0000/00/00":false}
                    // var AA = {"05/01/2010":1, "05/05/2010":1, "05/10/2010":1,  "05/30/2010":1, "09/27/2008":-1}

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

            <div class="subheader">
                <table width='100%' cellpadding="0" cellspacing="0"><tr>
                <td><h3 class='blue'>REPORT ACTIVITY ( ${fn:length(reportActivity)} )</h3>
                <td align="right"><!--<img src='../images/iphone2/scroll-down_off.png' id="downTwo" class="nextTwo"><img src='../images/iphone2/scroll-up_off.png' id="upTwo" class="prevTwo">-->
                </tr></table>
            </div>
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
                            <td><b>${index.count})&nbsp;&nbsp;</b>&nbsp;<a onmouseover="showToolTip($('_Description${rvDTO.rv.id}').innerHTML, '');" onmouseout="tt_Hide();" href="<c:url value="/pages/ae/edit?rvID=${rvDTO.rv.id}&aeReport=${rvDTO.aeReportID}&report=${rvDTO.reportID}" />">${rvDTO.reportName}</a></td>
                            <c:if test="${rvDTO.rv.submittedOn eq null and rvDTO.rv.dueOn ne null}">
                                <td align="right"><i>Due on:</i></td>
                                <td align="left">
                                    <span style="color:#ea4b4b"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span>
                                </td>
                            </c:if>
                            <c:if test="${rvDTO.rv.submittedOn ne null}">
                                <td align="right" width="100px"><i>Submited on:</i></td>
                                <td align="left" width="70px"><span style="color:green;"><tags:formatDate value="${rvDTO.rv.submittedOn}" /></span></td>
                            </c:if>
                            <c:if test="${rvDTO.rv.submittedOn eq null and rvDTO.rv.dueOn eq null}">
                                <td align="right" width="100px"><i>Withdrawn on:</i></td>
                                <td align="left" width="70px"><span style="color:blue;"><tags:formatDate value="${rvDTO.rv.withdrawnOn}" /></span></td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>

            </div>
            </div>

                </td>
            <tr>
            </table>
            </jsp:body>
            </chrome:boxIPhone>

            <chrome:boxIPhone title="Task Notifications" style="width:700px;">
                <div id="tasksNotifications" class="scrollerTask">
                    <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
                        <tr class="taskTitleRow">
                            <th>Subject
                            <th width="100%">Study
                            <th nowrap>Date Created
                            <th width="60px">Message
                        <c:forEach items="${taskNotifications}" var="task" varStatus="index">
                            <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                            <tr class="${ALT} taskTitleRow" id="prevROW_${index.index}">
                                <td nowrap valign="top">${task.subjectFullName}</td>
                                <td valign="top">${task.studyShortTitle}</td>
                                <td valign="top"><tags:formatDate value="${task.date}" /></td>
                                <td valign="top"><img id="IMG_${index.index}" src='<c:url value="/images/iphone2/plus.gif" />' onclick="flipMessage(${index.index})" style="cursor:pointer;"></td>
                            </tr>
                            <tr class="${ALT} taskTitleRow" style="display:none;" id="MESSAGE_${index.index}">
                                <td valign="top"><b>Message:</b></td>
                                <td colspan="3" valign="top">${fn:replace(task.message, "(http(s)?://(.)*?)[\\s]", "LINK")}</td>
                            </tr>
                            <%--<tr class="${ALT} taskTitleRow"><td colspan="4">&nbsp;</td></tr>--%>
                        </c:forEach>
                    </table>
                    </div>
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
