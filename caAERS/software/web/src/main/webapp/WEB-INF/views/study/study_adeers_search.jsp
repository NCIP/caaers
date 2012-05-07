<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<title>Search AdEERS Studies</title>

<script language="JavaScript">

    var _ajaxIndicatorHtml= '<img height="13px" width="13px" src="<c:url value="/images/indicator.gif" />" alt="activity indicator" width="20px" height="20px"/>';
    var popupDiv;

    <%--ToDo  THIS METHOS HAS TO BE MOVED TO A COMMON PLACE FO RREUSE --%>
    function showFlashMessage(text) {
        jQuery("#autoRemoveElementMesage").html(text);
        jQuery("#autoRemoveElement").show();
        hideFlashMessage.delay(3);
    }

<%--ToDo  THIS METHOS HAS TO BE MOVED TO A COMMON PLACE FO RREUSE --%>
    function hideFlashMessage() {
        jQuery("#autoRemoveElement").fadeOut('slow', function() {});
    }

    function submitSearch() {
        popupDiv = new Window({className:"alphacube", width:300, height:100, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent("search_submit");
        popupDiv.showCenter(true);
        popupDiv.show();
        jQuery("#searchForm").submit();
    }

    function showPopup() {
        popupDiv = new Window({className:"alphacube", width:300, height:100, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:false, minimizable:false, maximizable:false});
        popupDiv.setContent('please_wait');
        popupDiv.showCenter(true);
        popupDiv.show();
    }

    function showError() {
        popupDiv = new Window({className:"alphacube", width:300, height:100, zIndex:100, resizable:false, recenterAuto:true, draggable:false, closable:true, minimizable:false, maximizable:false});
        popupDiv.setContent('error_page');
        popupDiv.showCenter(true);
        popupDiv.show();
    }

    function doUpdate(id, _index, nciCode, operation) {
        jQuery('#studyLink' + _index).html(_ajaxIndicatorHtml);
        showPopup();
        createStudy.syncStudyWithAdEERS(id, nciCode ,operation, function(_resultId) {
            popupDiv.close();

            if (_resultId.error) {
                showError();
                jQuery('#studyLink' + _index).html("<b>Error</b>");
            } else {
                var text = "Updated";
                var flashText = "<caaers:message code="LBL_study.updated" />";
                if (operation == "CREATE") {
                    text = "Imported";
                    flashText = "<caaers:message code="LBL_study.imported" />";
                }
                jQuery('#studyLink' + _index).html("<b>" + text + "</b>");
                showFlashMessage(flashText);
            }

        });
    }

    function updateStudy(id, _index, nciCode) {
        doUpdate(id, _index, nciCode, "UPDATE");
    }

    function importStudy(id, _index, nciCode) {
        doUpdate(id, _index, nciCode, "CREATE");
    }

</script>

<chrome:box title="Search CTEP Studies" autopad="true">

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


        var actionFormatter = function(elCell, oRecord, oColumn, oData) {


            var _index = this.getRecordIndex(oRecord);
            var action = oRecord.getData("action");
            var id = oRecord.getData("id");
            var fsid = oRecord.getData("fsid");
            var ncic = oRecord.getData("ncic");
            var st = oRecord.getData("shortTitle");

            if (action == "UPDATE")
                elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='updateStudy(\"" + fsid +"\", " + _index + ", \"" + ncic + "\")'>Update</a></span>";
            else
                elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='importStudy(\"" + fsid +"\", "+ _index + ", \"" + ncic  + "\")' id='studyLink" + _index + "'>Import</a></span>";
        };

        var studiesColumnDefs = [
            {key:"fsid", label:"Identifier", sortable:true, resizeable:true, minWidth:100},
            {key:"shortTitle", label:"Title", sortable:true, resizeable:true, minWidth:700},
            {key:"action", label:"Action", sortable:true, resizeable:true, minWidth:100, formatter:actionFormatter}
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
                    "id":"${s.fundingSponsorIdentifierValue}",
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

<!--POPUPS-->
<div id="please_wait" style="display: none;">
    <h3><caaers:message code="LBL_please.wait" /></h3>
    <br><br>
    <div><caaers:message code="LBL_study.in.process" /></div>
</div>
<div id="error_page" style="display: none;"><div><caaers:message code="LBL_study.process.error" /></div></div>
<div id="search_submit" style="display: none;"><h3><caaers:message code="LBL_please.wait" /></h3><br><br><div><caaers:message code="LBL_study.searching" /></div></div>
