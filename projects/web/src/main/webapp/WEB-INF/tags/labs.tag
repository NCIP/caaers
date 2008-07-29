<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ec" uri="http://www.extremecomponents.org" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ attribute name="labs" type="java.util.List" required="true" %>

<script language="JavaScript1.2">
    var vis = 0;
</script>

<c:set var="vis" value="0" />
<c:forEach items="${labs}" var="alab" varStatus="status">
    <c:if test="${alab.dismissed == false}">
        <script language="JavaScript1.2">vis++;</script>
        <c:set var="vis" value="1" />
    </c:if>
</c:forEach>

<c:if test="${vis > 0}">

<div id="sideBar" style="top:200px; z-index:100;">

	<a id="sideBarTab"><img src="<c:url value="/images/sidebar/slide-button.gif" />" alt="" title="sideBar" /></a>

	<div id="sideBarContents" style="display:none;">
		<div id="sideBarContentsInner" style="padding: 5px;">

            <table width="290px" cellspacing="0" cellpadding="1" style="font-size:10px;" border="0">
                <tr>
                    <td><b>Lab</b></td>
                    <td><b>Date</b></td>
                    <td><b>Value</b></td>
                    <td><b>Unit</b></td>
                    <td></td>
                </tr>
                <c:forEach items="${labs}" var="alab" varStatus="status">
                <c:if test="${!alab.dismissed}">
                <tr id="LAB_${alab.id}" onMouseOver="bgColor='yellow'" onMouseOut="bgColor='#cccccc'">
                    <td>${alab.name}</td>
                    <td>${fn:substring(alab.labDate, 0, 10)}</td>
                    <td>${alab.result}</td>
                    <td>${alab.units}</td>
                    <td align="center"><a onClick="dismissLab(${alab.id})" style="cursor:pointer;"><img src="<c:url value="/images/checkno.gif" />" border="0"></a></td>
                </tr>                           
                </c:if>

                </c:forEach>
            </table>

        </div>
	</div>

    <script language="JavaScript1.2">
        var YOffset = 150;
        var staticYOffset = 30;

        function dismissLab(labId) {

            if (confirm("Are you sure ?")) {
                createAE.dismissLab(labId);
                $('LAB_' + labId).style.display = "none";
                vis--;
//                alert("vis=" + vis);
                if(vis == 0) {
                    $('sideBar').hide();
                }
            }
        }

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

                if (IE) $("sideBar").style.pixelTop += smooth;
                if (NS6) $("sideBar").style.top = parseInt($("sideBar").style.top) + smooth + "px"
                if (NS) $("sideBar").style.top = parseInt($("sideBar").style.top) + smooth

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

</c:if>
