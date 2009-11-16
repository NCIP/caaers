<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@attribute name="reports" required="false" type="java.util.List"%>
<%@attribute name="reportingPeriod" required="false" type="gov.nih.nci.cabig.caaers.domain.AdverseEventReportingPeriod" %>
<%@attribute name="labs" fragment="true" %>
<%@attribute name="workflowType" type="java.lang.String" required="true" %>
<%@attribute name="renderComments" type="java.lang.Boolean" required="true" description="True, if comments is to be displayed" %>
<%@attribute name="renderAlerts" type="java.lang.Boolean" required="true" description="True, if alerts is to be displayed" %>
<%@attribute name="display" type="java.lang.String" required="true" %>

<script language="JavaScript1.2">
	Event.observe(window, "load", function(){		
		new Control.Tabs('slider-tabs');
	});	
	
</script>

<div id="entire-slider" style="top:200px; z-index:1; display:${display}">

	<a id="sideBarTab"><img src="<c:url value="/images/sidebar/main_tab.png" />" alt="" title="sideBar" /></a>
	<!--BEGIN Slider -->
		<div id="slider-pane" style="display:none;">
			<ul id="slider-tabs" class="subsection_tabs">
				<c:if test="${renderComments}">
					<c:if test="${workflowType eq 'report'}">
						<c:forEach items="${reports}" var="report">
							<li id="slidertab-comments" class="tab-class"><a href="#report-id-${report.id }">${report.label }</a></li>
						</c:forEach>
					</c:if>
					<c:if test="${workflowType == 'reportingPeriod'}">
						<li id="slidertab-comments" class="tab-class"><a href="#reportingPeriod-id-${reportingPeriod.id }">${reportingPeriod.name }</a></li>
					</c:if>
				</c:if>
				<c:if test="${renderAlerts and not empty labs}">  
					<li id="slidertab-labs" class="tab-class"><a href="#labs-id">Labs</a></li>
				</c:if>  
			</ul>
			<div id="slider-content">
				<c:if test="${renderComments}">
					<c:if test="${workflowType eq 'report'}">
						<c:forEach items="${reports}" var="report">
							<div id="report-id-${report.id }" style="display:none;">
    							<tags:routingAndReviewComments report="${report }"/>
    						</div>
    					</c:forEach>
    				</c:if>
    				<c:if test="${workflowType == 'reportingPeriod'}">
    					<div id="reportingPeriod-id-${reportingPeriod.id }" style="display:none">
	    					<tags:routingAndReviewComments reportingPeriod="${reportingPeriod }"/>
    					</div>
    				</c:if>
				</c:if>
				<c:if test="${renderAlerts and not empty labs}">
						<jsp:invoke fragment="labs"/>
				</c:if>
			</div>
			<ul id="window-controls">
				<li>
					<a href="" id="one-fourth">1/4</a>
				</li>
				<li>
					<a href="" id="one-half">1/2</a>
				</li>
				<li>
					<a href="" id="maximize">Maximize</a>
				</li>
			</ul>
		</div>
	<!-- END Slider -->
	
    <script language="JavaScript1.2">
        var YOffset = 150;
        var staticYOffset = 30;

        NS6 = (document.getElementById && !document.all)
        IE = (document.all)
        NS = (navigator.appName == "Netscape" && navigator.appVersion.charAt(0) == "4")

        tempBar = '';
        barBuilt = 0;
        ssmItems = new Array();

        function truebody() {
            return (document.compatMode!="BackCompat")? document.documentElement : document.body
        }

        function makeStatic() {
            if (NS || NS6) {
                winY = window.pageYOffset;
            }
            if (IE) {
                winY = truebody().scrollTop;
            }
            if (NS6 || IE || NS) {
                if (winY != lastY && winY > YOffset - staticYOffset) {
                    smooth = .2 * (winY - lastY - YOffset + staticYOffset);
                }
                else if (YOffset - staticYOffset + lastY > YOffset - staticYOffset) {
                    smooth = .2 * (winY - lastY - (YOffset - (YOffset - winY)));
                }
                else {
                    smooth = 0
                }
                if (smooth > 0) smooth = Math.ceil(smooth);
                else smooth = Math.floor(smooth);

                if (IE) $("entire-slider").style.pixelTop += smooth;
                if (NS6) $("entire-slider").style.top = parseInt($("entire-slider").style.top) + smooth + "px"
                if (NS) $("entire-slider").style.top = parseInt($("entire-slider").style.top) + smooth

                lastY = lastY + smooth;
                setTimeout('makeStatic()', 1)
            }
        }

        function initSlide() {
            if (true || menuIsStatic == "yes") makeStatic();
        }

        function buildMenu() {
            lastY = 0;
            setTimeout('initSlide();', 1)
        }
        
        buildMenu();

    </script>

</div>