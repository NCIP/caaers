<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<title>Search AdEERS Studies</title>

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

    function submitSearch() {
        if (!validateInputText()) return;
        popupDiv = new Window({className:"alphacube", width:500, height:125, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent("search_submit");
        popupDiv.showCenter(true);
        popupDiv.show();
        doSearch.delay(1);
    }

    function showPopup() {
        popupDiv = new Window({className:"alphacube", width:500, height:125, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent("please_wait");
        popupDiv.showCenter(true);
        popupDiv.show();
    }

    function showError(_errorMessage) {
        jQuery('#command_errors').html(_errorMessage);
        jQuery('#flashErrors').show();
        hideFlashErrorMessage.delay(10);
/*
        popupDiv = new Window({className:"alphacube", width:300, height:100, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:true, minimizable:false, maximizable:false});
        jQuery('#_errorMessage').html("Some Error goes here: " + _errorMessage);
        popupDiv.setContent('error_page');
        popupDiv.showCenter(true);
        popupDiv.show();
*/
    }

    function doUpdate(id, _index, nciCode, operation) {
        jQuery('#studyLink' + _index).html(_ajaxIndicatorHtml);
        showPopup();
        createStudy.syncStudyWithAdEERS(id, nciCode ,operation, function(_resultId) {
            popupDiv.close();

            if (_resultId.error) {
                showError(_resultId.errorMessage);
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
                var _s = "<a onmouseover='showMenuOptions(#{index}, \"#{action}\", \"#{fsid}\", \"#{ncic}\", \"#{studyId}\")' id='_study#{index}' class='submitterButton submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all'>Actions<span class='ui-icon ui-icon-triangle-1-s'></span></a>";
                _s = _s.interpolate({index:_index, action:"UPDATE", fsid:id, ncic:nciCode, studyId:_dbId})
                jQuery('#studyLink' + _index).html(_s);
                showFlashMessage(flashText);
            }

        });
    }

/*
    function updateStudy(id, _index, nciCode) {
        doUpdate(id, _index, nciCode, "UPDATE");
    }

    function importStudy(id, _index, nciCode) {
        doUpdate(id, _index, nciCode, "CREATE");
    }
*/

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
            var onClickString = "javascript:doUpdate(\"#{id}\", \"#{index}\", \"#{nciCode}\", \"#{operation}\")";

            if (_action == "UPDATE") {
                _items = "<li><a class='submitter-blue' href='#' onclick='" + onClickString.interpolate({id:_fsid, index:strId, nciCode:_ncic, operation:_action}) + "'>Synchronize study with CTEP-ESYS</a></li>";
                _items += "<li><a class='submitter-blue' href='#' onclick='javascript:editStudy(" + _studyId + ")'>Edit Study in caAERS</a></li>";
            } else {
                _items = "<li><a class='submitter-blue' href='#' onclick='" + onClickString.interpolate({id:_fsid, index:strId, nciCode:_ncic, operation:_action}) + "'>Import Study</a></li>";
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

        var actionsRow = "<span id='studyLink#{index}'><a onmouseover='showMenuOptions(#{index}, \"#{action}\", \"#{fsid}\", \"#{ncic}\", \"#{studyId}\")' id='_study#{index}' class='submitterButton submitter fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all'>Actions<span class='ui-icon ui-icon-triangle-1-s'></span></a></span>";

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
                elCell.innerHTML = actionsRow.interpolate({index:_index, action:"CREATE", fsid:fsid, ncic:ncic, studyId:id});
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
            <c:forEach items="${studies}" var="s">
                {
                    "id":"${s.id}",
                    "fsid":"${s.fundingSponsorIdentifierValue}",
                    "ncic":"${s.fundingSponsorIdentifier.organization.nciInstituteCode}",
                    "shortTitle":"${s.shortTitle}",
                    "longTitle":"${s.longTitle}",
                    "action":"${s.status}"
                },
            </c:forEach>
        ];

        initializeYUITable("studiesTableDiv", studiesJSONResult, studiesColumnDefs, studiesFields);

    </script>

    </c:if>

    <c:if test="${fn:length(studies) == 0}"><caaers:message code="dashboard.noResults" /></c:if>

</chrome:box>
</c:if>
<%--<input type="button" onclick="doUpdate(1, 1, 'NCI', 'IMPORT')" value="HIT IT...">--%>
<!--POPUPS-->
<div id="please_wait" style="display: none;" class="flash-message info" >
    <h3><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_please.wait" /></h3>
    <br><br>
    <div><caaers:message code="LBL_study.in.process" /></div>
</div>
<div id="error_page" style="display: none;"><div><caaers:message code="LBL_study.process.error" /></div><br><span id="_errorMessage">.</span></div>
<div id="search_submit" class="flash-message info" style="display: none;"><h3><img src= "<chrome:imageUrl name="../check.png"/>" />&nbsp;<caaers:message code="LBL_please.wait" /></h3><br><br><div><caaers:message code="LBL_study.searching" /></div></div>
