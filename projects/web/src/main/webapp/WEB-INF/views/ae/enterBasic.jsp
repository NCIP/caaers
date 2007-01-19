<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Enter basic AE information</title>
    <style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
    </style>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var ctcVersion, ctcCategory, initialCtcTerm;
        <c:set var="currentCtcTerm" value="${command.aeReport.primaryAdverseEvent.ctcTerm}"/>
        <c:if test="${not empty command.aeReport.primaryAdverseEvent.ctcTerm}">
        initialCtcTerm = {
            id: ${currentCtcTerm.id},
            category: {
                id: ${currentCtcTerm.category.id},
                ctc: {
                    id: ${currentCtcTerm.category.ctc.id}
                }
            },
            otherRequired: ${currentCtcTerm.otherRequired}
        }
        </c:if>
        var ctcTermId = "${tab.fieldGroups.ctcTerm[0].propertyName}"
        var ctcTermInputId = "${tab.fieldGroups.ctcTerm[0].textfieldId}"
        var detailsForOtherId = "${tab.fieldGroups.ctcOther[0].propertyName}"
        var detailsForOtherRowId = "${tab.fieldGroups.ctcOther[0].propertyName}-row"

        function ctcVersionSelector() {
            ctcVersion = $F("ctc-version")
            if (ctcVersion) {
                updateCategories()
                clearSelectedTerm()
                AE.slideAndShow("ctc-details")
            } else {
                AE.slideAndHide("ctc-details")
            }
        }

        function ctcCategorySelector() {
            clearSelectedTerm();
            ctcCategory = $("ctc-category")
        }

        function updateCategories() {
            var categories = createAE.getCategories(ctcVersion, function(categories) {
                var sel = $("ctc-category")
                sel.options.length = 0
                sel.options.add(new Option("Any", ""))
                categories.each(function(cat) {
                    var opt = new Option(cat.name, cat.id)
                    sel.options.add(opt)
                    if (initialCtcTerm && initialCtcTerm.category.id == opt.value) {
                        opt.selected = true;
                    }
                })
            })
        }

        function clearSelectedTerm() {
            $(ctcTermInputId).value = ""
            $(ctcTermId).value = ""
            $(detailsForOtherId).value = ""
            AE.slideAndHide(detailsForOtherRowId)
        }

        function selectTerm(ctcTerm) {
            $A($("ctc-category").options).each(function(opt) {
                if (opt.value == ctcTerm.category.id) {
                    opt.selected = true
                }
            })

            $(ctcTermId).value = ctcTerm.id
            if (ctcTerm.otherRequired) {
                AE.slideAndShow(detailsForOtherRowId)
            } else {
                AE.slideAndHide(detailsForOtherRowId)
                $(detailsForOtherId).value = ""
            }
        }

        function termPopulator(autocompleter, text) {
            createAE.matchTerms(text, ctcVersion, $F('ctc-category'), 10, function(values) {
                autocompleter.setChoices(values)
            })
        }

        function termValueSelector(term) {
            return term.fullName;
        }

        Event.observe(window, "load", function() {
            Event.observe("ctc-version", "change", ctcVersionSelector)
            Event.observe("ctc-category", "change", ctcCategorySelector)

            new Autocompleter.DWR(ctcTermInputId, "${tab.fieldGroups.ctcTerm[0].choicesId}",
                termPopulator, {
                valueSelector: termValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    selectTerm(selectedChoice)
                }
            })

            if (initialCtcTerm) {
                $A($("ctc-version").options).each(function(opt) {
                    if (opt.value == initialCtcTerm.category.ctc.id) {
                        opt.selected = true
                    }
                })
                ctcVersionSelector()
                selectTerm(initialCtcTerm)
                $(ctcTermInputId).value = "${currentCtcTerm.fullName}"
            }
        })
    </script>
</head>
<body>
    <chrome:body title="${flow.name}: ${tab.longTitle}">

    <p id="instructions">
        You are entering an adverse event for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
    </p>

    <chrome:division title="Primary AE">
        <form:form cssClass="standard">
            <tags:errors path="*"/>
    
            <tags:tabFields tab="${tab}"/>
            <c:forEach items="${tab.fieldGroups.main}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
<%--
            <div class="row">
                <div class="label"><form:label path="ae.detectionDate">Detection date</form:label></div>
                <div class="value"><form:input path="ae.detectionDate"/></div>
            </div>
            <div class="row">
                <div class="label"><form:label path="ae.grade">Grade</form:label></div>
                <div class="value"><form:select path="ae.grade" items="${grades}" itemValue="name"/></div>
            </div>
            <div class="row">
                <div class="label"><form:label path="ae.attribution">Attribution</form:label></div>
                <div class="value"><form:select path="ae.attribution" items="${attributions}" itemValue="name"/></div>
            </div>
--%>
            <div class="row">
                <div class="label"><label for="ctc-version">CTC version</label></div>
                <div class="value">
                    <select id="ctc-version">
                        <option value="">Please select --</option>
                        <c:forEach items="${ctcVersions}" var="ctc">
                            <option value="${ctc.id}">${ctc.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div id="ctc-details" style="display: none">
                <div class="row">
                    <div class="label"><label for="ctc-category">CTC category</label></div>
                    <div class="value">
                        <select id="ctc-category"></select>
                    </div>
                </div>
                <tags:renderRow field="${tab.fieldGroups.ctcTerm[0]}"/>
<%--
                <div class="row">
                    <div class="label"><label for="ae.ctcTerm-input">CTC term</label></div>
                    <div class="value">
                        <input size="50" id="ae.ctcTerm-input"/>
                        <div id="ae.ctcTerm-choices" class="autocomplete"></div>
                        <form:hidden path="ae.ctcTerm"/>
                    </div>
                    <div class="extra">
                        Type a portion of the CTC term you are looking for.  If you select a category,
                        only terms in that category will be shown.
                    </div>
                </div>
--%>
                <tags:renderRow field="${tab.fieldGroups.ctcOther[0]}" cssStyle="display: none"/>
            </div>
        </form:form>
    </chrome:division>
        
    </chrome:body>
</body>
</html>