<%@include file="/WEB-INF/views/taglibs.jsp" %>

<%--<ol>
<c:forEach items="${pastDueReports}" var="rv">
    <li>${rvDTO.rv.report.name} - Due Date: <tags:formatDate value="${rvDTO.rv.dueOn}" /> [${rvDTO.rv.reportStatus}]
&lt;%&ndash;
        <ol>
    <c:forEach items="${aeReport.reports}" var="report">
        <li> Report [Report: ${report.id}, ${report.name}]
            <ul>
            <c:forEach items="${report.reportVersions}" var="version">
                <li> (version:${version.id} ${version.reportStatus})
            </c:forEach>
            </ul>
    </c:forEach>
        </ol>
&ndash;%&gt;
</c:forEach>--%>

<c:if test="${not empty roles.ae_reporter}">

<table width="100%">
    <tr>
        <td valign="top">
                <chrome:boxIPhone title="Task Notifications" style="width:400px;">
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

                <chrome:boxIPhone title="Quick Links" style="width:400px;">
                    <div style="padding-left:2px; padding-right:2px;">
                    <table width="100%" cellpadding="10" cellspacing="0" border="0">
                        <tr>
                            <td id="a1" class="quickLinkBGon" style="border-bottom: 1px #cccccc solid; border-right: 1px #cccccc solid;" width="50%">
                                <div class="quickLinkRow">
                                    <div class="quickLinkPicture"><img src="<c:url value="/images/iphone2/enter-participant.png"/>" align="middle" class="quickLink"></div>
                                    <div class="quickLinkLabel"><a href="<c:url value='/pages/participant/create' />" class="quickLink">Enter Subject</a></div>
                                </div>
                            </td>
                            <td id="a2" class="quickLinkBGon" style="border-bottom: 1px #cccccc solid;" width="50%">
                                <div class="quickLinkRow">
                                    <div class="quickLinkPicture"><img src="<c:url value="/images/iphone2/search-participants.png"/>" align="middle" class="quickLink"></div>
                                    <div class="quickLinkLabel"><a href="<c:url value='/pages/participant/search' />" class="quickLink">Search Subjects</a></div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td id="a3" class="quickLinkBGon" class="quickLinkBGoff" style="border-bottom: 1px #cccccc solid; border-right: 1px #cccccc solid;">
                                <div class="quickLinkRow">
                                    <div class="quickLinkPicture"><img src="<c:url value="/images/iphone2/enter-ae.png"/>" align="middle" class="quickLink"></div>
                                    <div class="quickLinkLabel"><a href="<c:url value='/pages/ae/captureRoutine' />" class="quickLink">Report Adverse Events</a></div>
                                </div>
                            </td>
                            <td id="a4" class="quickLinkBGon" style="border-bottom: 1px #cccccc solid;">
                                <div class="quickLinkRow">
                                    <div class="quickLinkPicture"><img src="<c:url value="/images/iphone2/create-expedited.png"/>" align="middle" class="quickLink"></div>
                                    <div class="quickLinkLabel"><a href="<c:url value='/pages/ae/routingAndReview' />" class="quickLink">Rounting and Review</a></div>
                                </div>
                            </td>
                        </tr>
                    </table>
                    </div>
                </chrome:boxIPhone>

        <td valign="top">
            <chrome:boxIPhone title="Expedited Reports" style="width:500px">
                <jsp:body>

                <div class="subheader">
                    <table width='100%' cellpadding="0" cellspacing="0"><tr>
                    <td><h3>PAST DUE REPORTS ( ${fn:length(pastDueReports)} )</h3>
                    <td align="right"><!-- <img src='../images/iphone2/scroll-down_off.png' id="downOne" class="nextOne"><img src='../images/iphone2/scroll-up_off.png' id="upOne" class="prevOne">-->
                    </tr></table>
                </div>

                <div id="ticker-container">
                <div id="dueReports" class="scroller">

                <c:set var="_DATE" value="" />
                <table border="0" cellpadding="0" cellspacing="0" class="dashboard_table" width="99%">
                    <c:forEach items="${pastDueReports}" var="rvDTO" varStatus="index">
                        <span id="_DescriptionDUE${rvDTO.rv.id}" style="display:none;">
                            <b style="color:yellow;">Report Name: </b>${rvDTO.reportName}<br>
                            <b style="color:yellow;">Study: </b>${rvDTO.studyShortTitle}<br>
                            <b style="color:yellow;">Participant: </b>${rvDTO.subjectFirstName}&nbsp;${rvDTO.subjectLastName}&nbsp(${rvDTO.subjectPrimaryIdentifier})<br>
                            <b style="color:yellow;">Study Site: </b>${rvDTO.studySiteName}&nbsp;(${rvDTO.studySiteCode})<br>
                            <b style="color:yellow;">Course: </b> Cycle #: ${rvDTO.periodCycle}&nbsp;<c:if test="${not empty rvDTO.periodStartDate}">(<fmt:formatDate value="${rvDTO.periodStartDate}" />)</c:if><br>
                        </span>

                        <c:set var="_dateString"><jsp:attribute name="value"><tags:formatDate value="${rvDTO.rv.dueOn}" /></jsp:attribute></c:set>
                        <c:set var="_ID" value="tr_PD_${index.index}" />

                        <c:if test="${_DATE ne _dateString}">
                            <c:set var="_ID" value="${fn:replace(_dateString, '/', '_')}" />
                            <c:set var="_DATE" value="${_dateString}" />
                        </c:if>

                        <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                        <tr class="${ALT}" id="CD_${_ID}">
                            <td><b>${index.count})&nbsp;&nbsp;</b>&nbsp;<a onmouseover="showToolTip($('_DescriptionDUE${rvDTO.rv.id}').innerHTML, '');" onmouseout="tt_Hide();" href="<c:url value="/pages/ae/edit?rvID=${rvDTO.rv.id}&aeReport=${rvDTO.aeReportID}&report=${rvDTO.reportID}" />">${rvDTO.reportName}</a></td>
                            <td align="right" width="40px"><i>Due on:</i></td>
                            <td align="left" width="70px"><span style="color:#ea4b4b"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span></td>
                        </tr>
                    </c:forEach>
                </table>

                </div>
                </div>

                <center>
                    <div style="height:180px; border:0px green solid; margin:10px;"><div style="margin: 2px 0px 2px 0px; width:225px;" id="calendar-container"></div></div>
                </center>
                            
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
                                <c:if test="${rvDTO.rv.submittedOn eq null}">
                                    <td align="right"><i>Due on:</i></td>
                                    <td align="left">
<%--
                                        <c:set var="_t"><jsp:attribute name="value"><fmt:formatDate value="${now}" pattern="yyyy/MM/dd" /></jsp:attribute></c:set>
                                        <c:set var="_due"><jsp:attribute name="value"><fmt:formatDate value="${rvDTO.rv.dueOn}" pattern="yyyy/MM/dd" /></jsp:attribute></c:set>
                                        <c:if test="${_due le _t}"><span style="color:#ea4b4b"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span></c:if>
                                        <c:if test="${rvDTO.rv.dueOn eq null || _due gt _t}"><span style="color:000"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span></c:if>
--%>
                                        <span style="color:#ea4b4b"><tags:formatDate value="${rvDTO.rv.dueOn}" /></span>
                                    </td>
                                </c:if>
                                <c:if test="${rvDTO.rv.submittedOn ne null}">
                                    <td align="right" width="100px"><i>Submited on:</i></td>
                                    <td align="left" width="70px"><span style="color:green;"><tags:formatDate value="${rvDTO.rv.submittedOn}" /></span></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
                </div>

                </jsp:body>
            </chrome:boxIPhone>

    </tr>
</table>

<style type="text/css">
  .reportActivity { background-color: #99cc00; color: #f00; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}
  .pastDue { background-color: #ff6666; color: #ff6666; border:0px solid #000; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}

  .calendar tbody .rowhilite td.pastDue { background: #ff6666; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}
  .calendar tbody .rowhilite td.pastDue.hilite { background: #ff6666; border:1px #BBB solid; padding: 1px 3px 1px 1px;}
  .calendar tbody .rowhilite td.reportActivity { background: #99cc00; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}
  .calendar tbody .rowhilite td.reportActivity.hilite { background: #99cc00; border:1px #BBB solid; padding: 1px 3px 1px 1px;}
  .calendar tbody .reportActivity {font-weight: bold; color:white; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}
  .calendar tbody .pastDue {font-weight: bold; color:white; border:1px #EEEEFF solid; padding: 1px 3px 1px 1px;}
</style>


<style>

    tr.last { border-bottom : 1px black solid; }
    .scroller { height: 70px; overflow-x: hidden; overflow-y: scroll; margin: 0px; }
    .scrollerTask { height: 227px; overflow-x: hidden; overflow-y: scroll; margin: 0px; }
    .scroller h4 { color: #933; display: inline; }
    .scroller div { height: 22px; padding: 8px; margin-top: -1px; }
    .scroller div.first { margin-top: 0; }
    .scrollerdiv.last { margin-bottom: 0; }
    .scroller p { font-size: 11px; margin-left: 0 !important; display: inline; }
</style>


<script>
/*
Event.observe($('upOne'), 'mouseover', function() { $('upOne').src = $('upOne').src.replace('_off', '_on');});
Event.observe($('upOne'), 'mouseout', function() { $('upOne').src = $('upOne').src.replace('_on', '_off');});
Event.observe($('downOne'), 'mouseover', function() { $('downOne').src = $('downOne').src.replace('_off', '_on');});
Event.observe($('downOne'), 'mouseout', function() { $('downOne').src = $('downOne').src.replace('_on', '_off');});

Event.observe($('upTwo'), 'mouseover', function() { $('upTwo').src = $('upTwo').src.replace('_off', '_on');});
Event.observe($('upTwo'), 'mouseout', function() { $('upTwo').src = $('upTwo').src.replace('_on', '_off');});
Event.observe($('downTwo'), 'mouseover', function() { $('downTwo').src = $('downTwo').src.replace('_off', '_on');});
Event.observe($('downTwo'), 'mouseout', function() { $('downTwo').src = $('downTwo').src.replace('_on', '_off');});
*/

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

<style>

    .boxIPhone {background-color: #fff; margin-bottom: 12px; background-image: url(../images/iphone2/box_bg.png); background-repeat: repeat-x; }
    .boxIPhone .header .background-L { background: url(../images/iphone2/box_header_bg_l.png) no-repeat top left; }
    .boxIPhone .header .background-R { background: url(../images/iphone2/box_header_bg_r.png) no-repeat top right; }
    .boxIPhone .subheader {background: url(../images/iphone2/box_subheader_bg.png) repeat-x; margin-left:2px; margin-right:2px; }

    .b-T {}
    .b-L { background: url(../images/iphone2/box_l.png) repeat-y left;}
    .b-R { background: url(../images/iphone2/box_r.png) repeat-y right; }
    .b-B { background: url(../images/iphone2/box_bottom.png) repeat-x bottom; }
    .b-TL {}
    .b-TR {}
    .b-BL { background: url(../images/iphone2/box_bl.png) no-repeat bottom left; }
    .b-BR { background: url(../images/iphone2/box_br.png) no-repeat bottom right; }
    .boxIPhone .header h2 { text-align: left; padding: 7px 0 4px 20px; font: 20px Arial, Helvetica, sans-serif; color: #fff; text-shadow: 0 -2px #064d8c; }

    .boxIPhone .interior {
        margin: 0px 0px 0px 0px;
        padding: 1px;
        padding-bottom: 10px;
        min-height: 28px;
    }

    .boxIPhone.tabbed .interior {
        padding-top: 0;
    }

    .boxIPhone .content {
        padding: 8px;
    }

    .boxIPhone h3 {
        font: bold 15px Arial, Helvetica, sans-serif;
        color: #ea4b4b;
        text-shadow: 0 -1px white;
        padding-top: 8px;
        padding-right: 0px;
        padding-bottom: 7px;
        padding-left: 20px;
    }

    .boxIPhone h3.blue {
        font: bold 15px Arial, Helvetica, sans-serif;
        color: blue;
        text-shadow: 0 -1px white;
        padding-top: 8px;
        padding-right: 0px;
        padding-bottom: 7px;
        padding-left: 20px;
    }

    .dashboard_table {
        font-family: Arial, Helvetica, sans-serif;
        margin: 0 1px 0 2px;
        /*border-collapse: collapse;*/
        border-bottom: 0px solid #dddddd;
        border-top: 0px solid #dddddd;
        padding-top: 1px;

    }

    .dashboard_table td, .dashboard_table th {
        font-size: 11px;
        /*border-right: 1px solid #dddddd;*/
        padding: 5px 7px 4px 4px;
        color: #3a3a3a;
    }

    .dashboard_table a {
        font-weight: bold;
        color: #3a3a3a;
        text-decoration: none;
    }

    .dashboard_table a:hover {
        color: #09589d;
        text-decoration: none;
    }

    .dashboard_table a:active {
        position: relative;
        top: 1px;
        color: #09589d;
        text-decoration: none;
    }

    .dashboard_table th {
        font-size: 12px;
        text-align: left;
        border: 0;
        padding-top: 4px;
        padding-bottom: 4px;
        background: url(images/table-header_bg.png) center no-repeat;
        color: #0c62ac;
    }

    .dashboard_table tr.alt td {
        color: #3a3a3a;
        background-color: #f2f9ff;
    }

    a.quickLink, a.quickLink:visited {
        color : #518EC2;
        font-weight: bold;
        font-size: 14px;
        text-decoration: none;
    }

    img.quickLink {
/*
        padding-right: 20px;
        padding-left: 20px;
*/
    }

    div.quickLinkRow {
        display: block;
        clear: both;
    }

    div.quickLinkRow div.quickLinkPicture {
        float: left;
        width : 40px;
        text-align: right;
    }

    div.quickLinkRow div.quickLinkLabel {
        margin-left: 50px;
        text-align: left;
        vertical-align: middle;
    }

    td.quickLinkBGon {
        background-image: url("../images/iphone2/quickLinkBGon.png")
    }

    td.quickLinkBGoff {
        background-image: url("../images/iphone2/quickLinkBGoff.png")
    }

    tr.taskTitleRow th {
        color : #518EC2;
        font-weight: bold;
    }

    tr.taskTitleRow td, tr.taskTitleRow th {
        border-bottom: 1px #ccc solid;
    }

    a.linkHere, a.linkHere:hover {
        color : blue;
        text-decoration: underline;
    }
</style>
<span id="CC" />

</c:if>