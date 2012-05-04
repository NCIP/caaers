<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<title>Search AdEERS Studies</title>

<script language="JavaScript">

    var _ajaxIndicatorHtml= '<img height="13px" width="13px" src="<c:url value="/images/indicator.gif" />" alt="activity indicator" width="20px" height="20px"/>';
    var popupDiv;

    function submitSearch() {
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
                if (operation == "CREATE") text = "Imported";
                jQuery('#studyLink' + _index).html("<b>" + text + "</b>");
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

        <c:if test="${fn:length(studies) > 0}">

            <h2>Found studies</h2>

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
        <c:if test="${fn:length(studies) == 0 && pageContext.request.method eq 'POST'}">
            <div class="row">
                <div class="label"></div>
                <div class="value"><caaers:message code="dashboard.noResults" /></div>
            </div>
        </c:if>
    </form:form>

    <div id="please_wait" style="display: none;">
        <h3>Please wait...</h3>
        <br><br>
        <div>The study is getting processed.</div>
    </div>

    <div id="error_page" style="display: none;">
        <div>There was an error while processing the study...</div>
    </div>

</chrome:box>