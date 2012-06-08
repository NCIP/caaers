<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<title>Search AdEERS Studies</title>

<style>
    div.yui-dt-liner a:hover  {
        color: white;
    }
</style>
<script language="JavaScript">

    var _ajaxIndicatorHtml= '<img height="13px" width="13px" src="<c:url value="/images/indicator.gif" />" alt="activity indicator" width="20px" height="20px"/>';
    var popupDiv;

    <%--ToDo  THIS METHOS HAS TO BE MOVED TO A COMMON PLACE FOR REUSE --%>
    function showFlashMessage(text) {
        jQuery("#autoRemoveElementMesage").html(text);
        jQuery("#autoRemoveElement").show();
        hideFlashMessage.delay(5);
    }

    <%--ToDo  THIS METHOS HAS TO BE MOVED TO A COMMON PLACE FOR REUSE --%>
    function hideFlashMessage() {
        jQuery("#autoRemoveElement").fadeOut('slow', function() {});
    }

    function hideFlashErrorMessage() {
        jQuery("#flashErrors").fadeOut('slow', function() {});
    }

    function doSearch() {
        jQuery("#searchForm").submit();
    }

    function validateInputText() {
        if (jQuery.trim(jQuery("#searchText").val()) == "") {
            jQuery("#flashErrors").show();
            return false;
        }
        jQuery("#flashErrors").hide();
        return true;
    }

    /**
    * BEN, alphacube is the skin of the Windows that opnes... that gray one with an orange bar on it
     * you need to create a new SKIN which we can use here instead of alphacube
     */
    function submitSearch() {
        if (!validateInputText()) return;
        popupDiv = new Window({className:"alphacube", width:750, height:115, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent("search_submit");
        popupDiv.showCenter(true);
        popupDiv.show();
        doSearch.delay(1);
    }

    function showPopup() {
        popupDiv = new Window({className:"alphacube", width:750, height:115, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent("please_wait");
        popupDiv.showCenter(true);
        popupDiv.show();
        return popupDiv;
    }

    function showSuccessPopup(_messageText) {
        jQuery("#_messageText").html(_messageText);
        showConfigurableTimerPopup("success_message", 5, 750, 115, true, true, true, false, false);
    }

    function showError(_errorMessage) {
/*
        jQuery('#command_errors').html(_errorMessage);
        jQuery('#flashErrors').show();
        hideFlashErrorMessage.delay(10);
*/
        popupDiv = new Window({className:"alphacube", width:750, height:115, zIndex:100, resizable:true, recenterAuto:true, draggable:true, closable:true, minimizable:false, maximizable:false});
        jQuery('#_errorMessage').html(_errorMessage);
        popupDiv.setContent('error_page');
        popupDiv.showCenter(true);
        popupDiv.show();
    }

    function doUpdate(id, _index, nciCode, operation, studyDbId) {
//        jQuery('#studyLink' + _index).html(_ajaxIndicatorHtml);
        p = showPopup();
        createStudy.syncStudyWithAdEERS(id, studyDbId, function(_resultId) {
            popupDiv.close();

            if (_resultId.error) {
                showError(_resultId.errorMessage);
//                showError("This is not a real error message. Testing... stay cool, it will go away soon.");
//                jQuery('#studyLink' + _index).html("<b>Error</b>");
            } else {
                var _dbId = _resultId.objectContent;
                var text = "Updated";
                var flashText = "<caaers:message code="LBL_study.updated" />";
                if (operation == "CREATE") {
                    text = "Imported";
                    flashText = "<caaers:message code="LBL_study.created" />";
                }
//                jQuery('#studyLink' + _index).html("<b>" + text + "</b>");
                var _s = "<img src='<c:url value="/images/orange-actions.gif" />' border='0' onmouseover='showMenuOptions(#{index}, \"#{action}\", \"#{fsid}\", \"#{ncic}\", \"#{studyId}\")' id='_study#{index}' style='cursor:pointer;'>";
                _s = _s.interpolate({index:_index, action:"UPDATE", fsid:id, ncic:nciCode, studyId:_dbId})
                jQuery('#studyLink' + _index).html(_s);
                var _p = showSuccessPopup(flashText);
                hideSuccessPopup
            }

        });
    }


    function editStudy(id) {
        window.location = "<c:url value="/pages/study/edit?studyId=" />" + id;
    }

</script>

<chrome:box title="Search CTEP Studies" autopad="true">


    <div class="errors" id="flashErrors" style="display: none;">
        <span id="command_errors">Please enter some search criteria.</span>
    </div>

    <div id="autoRemoveElement" style="display: none;">
        <div id="flash-message" class="info">
            <img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<span id="autoRemoveElementMesage" />
        </div>
    </div>

    <p><tags:instructions code="study.adEERSsearch.top"/></p>
    <form:form name="searchForm" id="searchForm" method="post">

        <c:if test="${not empty requestScope['org.springframework.validation.BindingResult.command'].allErrors}">
            <div class="errors">
            <form:errors />
            </div>
        </c:if>

        <div class="row">
            <div class="label">Search</div>
            <div class="value"><form:input path="searchText" />&nbsp;<tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="submitSearch();"/></div>
        </div>
    </form:form>

</chrome:box>

<c:if test="${pageContext.request.method eq 'POST'}">
<chrome:box title="Results">
    <c:if test="${fn:length(studies) > 0}">

        <div id="studiesTableDiv"></div>

    <script language="JavaScript">

        function showMenuOptions(strId, _action, _fsid, _ncic, _studyId) {
            var _items = "<li>ERROR</li>";
            var onClickString = "javascript:doUpdate(\"#{id}\", \"#{index}\", \"#{nciCode}\", \"#{operation}\", #{studyId})";

            if (_action == "UPDATE") {
                _items = "<li><a class='submitter-blue' href='#' onclick='" + onClickString.interpolate({id:_fsid, index:strId, nciCode:_ncic, operation:_action, studyId: _studyId}) + "'>Synchronize study with CTEP-ESYS</a></li>";
                _items += "<li><a class='submitter-blue' href='#' onclick='javascript:editStudy(" + _studyId + ")'>Edit Study in caAERS</a></li>";
            } else {
                _items = "<li><a class='submitter-blue' href='#' onclick='" + onClickString.interpolate({id:_fsid, index:strId, nciCode:_ncic, operation:_action,studyId: _studyId}) + "'>Import Study</a></li>";
            }

            var html = "<div><ul style='font-family:tahoma;'>" + _items + "</ul></div>";
//            var html = html.interpolate({strId:strId, rt:rt, un:un});
            jQuery('#_study' + strId).menu({
                    content: html,
                    maxHeight: 180,
                    width: 230,
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

        var actionsRow = "<span id='studyLink#{index}'><img src='<c:url value="/images/orange-actions.gif" />' border='0' onmouseover='showMenuOptions(#{index}, \"#{action}\", \"#{fsid}\", \"#{ncic}\", \"#{studyId}\")' id='_study#{index}' style='cursor: pointer;'></span>";
        var actionsRowOneItem = "<span id='studyLink#{index}'><img src='<c:url value="/images/orange-import.gif" />' border='0' onclick='doUpdate(\"#{fsid}\", \"#{index}\", \"#{ncic}\", \"CREATE\")' id='_study#{index}' style='cursor: pointer;'></span>";

        var actionFormatter = function(elCell, oRecord, oColumn, oData) {
            var _index = this.getRecordIndex(oRecord);
            var action = oRecord.getData("action");
            var id = oRecord.getData("id");
            var fsid = oRecord.getData("fsid");
            var ncic = oRecord.getData("ncic");
            var st = oRecord.getData("shortTitle");

            if (action == "UPDATE") {
                elCell.innerHTML = actionsRow.interpolate({index:_index, action:"UPDATE", fsid:fsid, ncic:ncic, studyId:id});
                // elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='updateStudy(\"" + fsid +"\", " + _index + ", \"" + ncic + "\")'>Update</a></span>";
            } else {
                elCell.innerHTML = actionsRowOneItem.interpolate({index:_index, action:"CREATE", fsid:fsid, ncic:ncic, studyId:id});
                // elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='importStudy(\"" + fsid +"\", "+ _index + ", \"" + ncic  + "\")' id='studyLink" + _index + "'>Import</a></span>";
            }
        };

        var studiesColumnDefs = [
            {key:"fsid", label:"Identifier", sortable:true, resizeable:true, minWidth:100},
            {key:"shortTitle", label:"Title", sortable:true, resizeable:true, minWidth:700},
            {key:"action", label:"&nbsp;", sortable:true, resizeable:true, minWidth:100, formatter:actionFormatter}
        ];

        var studiesFields = [
            {key:'id', parser:"string"},
            {key:'fsid', parser:"string"},
            {key:'ncic', parser:"string"},
            {key:'shortTitle', parser:"string"},
            {key:'longTitle', parser:"string"},
            {key:'action', parser:"string"}
        ];

        studiesJSONResult = [
            <c:forEach items="${studies}" var="s" varStatus="i">
                {
                    "id":"${s.id}",
                    "fsid":"${s.fundingSponsorIdentifierValue}",
                    "ncic":"${s.fundingSponsorIdentifier.organization.nciInstituteCode}",
                    "shortTitle":"<c:out value="${s.shortTitle}" escapeXml="true" />",
                    "longTitle":"<c:out value="${s.longTitle}" escapeXml="true" />",
                    "action":"${s.status}"
                }
                <c:if test="${!i.last}">,</c:if>
            </c:forEach>
        ];

        initializeYUITable("studiesTableDiv", studiesJSONResult, studiesColumnDefs, studiesFields);

    </script>

    </c:if>

    <c:if test="${fn:length(studies) == 0}"><caaers:message code="dashboard.noResults" /></c:if>

</chrome:box>
</c:if>
<!--POPUPS-->
<div id="please_wait" style="display: none;" class="info-box message" ><p><caaers:message code="LBL_please.wait" /><br><caaers:message code="LBL_study.in.process" /></p></div>
<div id="success_message" style="display: none;" class="success-box message" ><p id="_messageText"></p></div>
<div id="search_submit" class="info-box message" style="display: none;"><p><caaers:message code="LBL_please.wait" /><br><caaers:message code="LBL_study.searching" /></p></div>
<div id="error_page" class="error-box message" style="display: none;"><p><caaers:message code="LBL_study.process.error" /><br><span id="_errorMessage">.</span></p></div>

<!--
EXAMPLES
<div class="info-box message"><p>This is a friendly little information notification. Now you know!</p></div>
<div class="success-box message"><p>This is a friendly little success notification. You did it, yay!</p></div>
<div class="warning-box message"><p>This is a friendly little warning notification. You should be careful!</p></div>
<div class="error-box message"><p>This is a friendly little error notification. Uh, oh… now what?</p></div>
-->