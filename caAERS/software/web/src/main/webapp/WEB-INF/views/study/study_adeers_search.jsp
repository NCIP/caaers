<%@include file="/WEB-INF/views/taglibs.jsp" %>
<tags:dwrJavascriptLink objects="createStudy"/>

<title>Search AdEERS Studies</title>

<script language="JavaScript">

    var _ajaxIndicatorHtml= '<img height="13px" width="13px" src="<c:url value="/images/indicator.gif" />" alt="activity indicator" width="20px" height="20px"/>';

    function submitSearch() {
        jQuery("#searchForm").submit();
    }

    function doUpdate(id, _index, operation) {
        jQuery('#studyLink' + _index).html(_ajaxIndicatorHtml);
        createStudy.syncStudyWithAdEERS(id, operation, function(_resultId) {
            jQuery('#studyLink' + _index).html(_resultId);
        });
    }

    function updateStudy(id, _index) {
        doUpdate(id, _index, "UPDATE");
    }

    function importStudy(id, _index) {
        doUpdate(id, _index, "CREATE");
    }

</script>

<chrome:box title="Search AdEERS Studies" autopad="true">

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

            <h2>Found Studies in AdEERS</h2>

            <div id="studiesTableDiv"></div>

        <script language="JavaScript">


            var actionFormatter = function(elCell, oRecord, oColumn, oData) {


                var _index = this.getRecordIndex(oRecord);
                var action = oRecord.getData("action");
                var id = oRecord.getData("id");
                var fsid = oRecord.getData("fsid");
                var st = oRecord.getData("shortTitle");

                if (action == "UPDATE")
                    elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='updateStudy(\"" + fsid +"\", " + _index + ")'>Update</a></span>";
                else
                    elCell.innerHTML = "<span id='studyLink" + _index + "'><a href='#' onclick='importStudy(\"" + fsid +"\", "+ _index + ")' id='studyLink" + _index + "'>Import</a></span>";
            };

            var studiesColumnDefs = [
                {key:"fsid", label:"Identifier", sortable:true, resizeable:true, minWidth:100},
                {key:"shortTitle", label:"Short title", sortable:true, resizeable:true, minWidth:300},
                {key:"longTitle", label:"Long title", sortable:true, resizeable:true, minWidth:500},
                {key:"action", label:"Action", sortable:true, resizeable:true, minWidth:100, formatter:actionFormatter}
            ];

            var studiesFields = [
                {key:'id', parser:"string"},
                {key:'fsid', parser:"string"},
                {key:'shortTitle', parser:"string"},
                {key:'longTitle', parser:"string"},
                {key:'action', parser:"string"}
            ];

            studiesJSONResult = [
                <c:forEach items="${studies}" var="s">
                    {
                        "id":"${s.fundingSponsorIdentifierValue}",
                        "fsid":"${s.fundingSponsorIdentifierValue}",
                        "shortTitle":"${s.shortTitle}",
                        "longTitle":"${s.longTitle}",
                        "action":"${s.status}"
                    },
                </c:forEach>
            ];

            initializeYUITable("studiesTableDiv", studiesJSONResult, studiesColumnDefs, studiesFields);

        </script>

        </c:if>

    </form:form>
</chrome:box>