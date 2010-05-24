<%@include file="/WEB-INF/views/taglibs.jsp" %>

<%--<h1>Dashboard</h1>--%>

<%--<ol>
<c:forEach items="${pastDueReports}" var="rv">
    <li>${rv.report.name} - Due Date: <tags:formatDate value="${rv.dueOn}" /> [${rv.reportStatus}]
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

<table width="100%">
    <tr>
        <td valign="top">
                <chrome:boxIPhone title="Quick Links" style="width:400px">
                </chrome:boxIPhone>
                    
                <chrome:boxIPhone title="Task Notifications" style="width:400px">
                </chrome:boxIPhone>

        <td valign="top">
            <chrome:boxIPhone title="Expedited Reports" style="width:500px">
                <jsp:body>

                <div class="subheader">
                    <table width='100%' cellpadding="0" cellspacing="0"><tr>
                    <td><h3>PAST DUE REPORTS ( ${fn:length(pastDueReports)} )</h3>
                    <td align="right"><img src='../images/iphone2/scroll-down_off.png' id="downOne" class="nextOne"><img src='../images/iphone2/scroll-up_off.png' id="upOne" class="prevOne">
                    </tr></table>
                </div>

                <div id="ticker-container">
                <div id="dueReports" class="scroller">

                <table border="0" cellpadding="0" cellspacing="0" id="dashboard_table" width="99%">
                    <c:forEach items="${pastDueReports}" var="rv" varStatus="index">
                        <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                        <tr class="${ALT}<c:if test="${index.first}"> first</c:if><c:if test="${index.last}"> last</c:if>" style="border-bottom:1px #eeeeee solid;">
                            <td width="276"><b>${index.count})&nbsp;&nbsp;</b> <a href="#">${rv.report.name}</a></td>
                            <td><i>Due Date:</i> <span style="color:#ea4b4b">&nbsp;&nbsp;&nbsp;<tags:formatDate value="${rv.dueOn}" /></span></td>
                        </tr>
                    </c:forEach>
                </table>

                </div>
                </div>

                <center>
                <br>
                <img src="/caaers/images/iphone/dummy-calendar.png">
                <br><br>
                </center>

                <div class="subheader">
                    <table width='100%' cellpadding="0" cellspacing="0"><tr>
                    <td><h3 class='blue'>REPORT ACTIVITY ( ${fn:length(reportActivity)} )</h3>
                    <td align="right"><img src='../images/iphone2/scroll-down_off.png' id="downTwo" class="nextTwo"><img src='../images/iphone2/scroll-up_off.png' id="upTwo" class="prevTwo">
                    </tr></table>
                </div>
                <div id="ticker-container">
                <div id="reportActivity" class="scroller">

                    <table border="0" cellpadding="0" cellspacing="0" id="dashboard_table" width="99%">
                        <c:forEach items="${reportActivity}" var="rv" varStatus="index">
                            <c:set var="ALT" value="${index.count % 2 == 0 ? 'alt' : ''}"></c:set>
                            <tr class="${ALT}<c:if test="${index.first}"> first</c:if><c:if test="${index.last}"> last</c:if>" style="border-bottom:1px #eeeeee solid;">
                                <td width="276"><b>${index.count})&nbsp;&nbsp;</b> <a href="#">${rv.report.name}</a></td>
                                <td><i>Due Date:</i> <span style="color:#ea4b4b">&nbsp;&nbsp;&nbsp;<tags:formatDate value="${rv.dueOn}" /></span></td>
                            </tr>
                        </c:forEach>
                    </table>

                </div>
                </div>

                </jsp:body>
            </chrome:boxIPhone>

    </tr>
</table>

<style>

    tr.last { border-bottom : 1px black solid; }
    .scroller { height: 70px; overflow: hidden; margin: 0px; }
    .scroller h4 { color: #933; display: inline; }
    .scroller div { height: 22px; padding: 8px; margin-top: -1px; }
    .scroller div.first { margin-top: 0; }
    .scrollerdiv.last { margin-bottom: 0; }
    .scroller p { font-size: 11px; margin-left: 0 !important; display: inline; }
</style>


<script>

Event.observe($('upOne'), 'mouseover', function() { $('upOne').src = $('upOne').src.replace('_off', '_on');});
Event.observe($('upOne'), 'mouseout', function() { $('upOne').src = $('upOne').src.replace('_on', '_off');});
Event.observe($('downOne'), 'mouseover', function() { $('downOne').src = $('downOne').src.replace('_off', '_on');});
Event.observe($('downOne'), 'mouseout', function() { $('downOne').src = $('downOne').src.replace('_on', '_off');});

Event.observe($('upTwo'), 'mouseover', function() { $('upTwo').src = $('upTwo').src.replace('_off', '_on');});
Event.observe($('upTwo'), 'mouseout', function() { $('upTwo').src = $('upTwo').src.replace('_on', '_off');});
Event.observe($('downTwo'), 'mouseover', function() { $('downTwo').src = $('downTwo').src.replace('_off', '_on');});
Event.observe($('downTwo'), 'mouseout', function() { $('downTwo').src = $('downTwo').src.replace('_on', '_off');});

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

    .boxIPhone h3, .division h3 {
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

    #dashboard_table {
        font-family: Arial, Helvetica, sans-serif;
        margin: 0 1px 0 2px;
        border-collapse: collapse;
        border-bottom: 0px solid #dddddd;
        border-top: 0px solid #dddddd;
        padding-top: 1px;

    }

    #dashboard_table td, #dashboard_table th {
        font-size: 11px;
        /*border-right: 1px solid #dddddd;*/
        padding: 5px 7px 4px 16px;
        color: #3a3a3a;
    }

    #dashboard_table a {
        font-weight: bold;
        color: #3a3a3a;
        text-decoration: none;
    }

    #dashboard_table a:hover {
        color: #09589d;
        text-decoration: none;
    }

    #dashboard_table a:active {
        position: relative;
        top: 1px;
        color: #09589d;
        text-decoration: none;
    }

    #dashboard_table th {
        font-size: 12px;
        text-align: left;
        border: 0;
        padding-top: 4px;
        padding-bottom: 4px;
        background: url(images/table-header_bg.png) center no-repeat;
        color: #0c62ac;
    }

    #dashboard_table tr.alt td {
        color: #3a3a3a;
        background-color: #f2f9ff;
    }
</style>