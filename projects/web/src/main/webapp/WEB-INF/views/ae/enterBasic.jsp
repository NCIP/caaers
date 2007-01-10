<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter basic AE information</title>
    <style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
    </style>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var ctcVersion, ctcCategory;

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
                    sel.options.add(new Option(cat.name, cat.id))
                })
            })
        }

        function clearSelectedTerm() {
            $("ctc-term-input").value = ""
            $("ae.ctcTerm").value = ""
            $("ae.detailsForOther").value = ""
            AE.slideAndHide("details-for-other-row")
        }

        function selectTerm(ctcTerm) {
            $A($("ctc-category").options).each(function(opt) {
                if (opt.value == ctcTerm.category.id) {
                    opt.selected = true
                }
            })

            $("ae.ctcTerm").value = ctcTerm.id
            if (ctcTerm.otherRequired) {
                AE.slideAndShow("details-for-other-row")
            } else {
                AE.slideAndHide("details-for-other-row")
                $("ae.detailsForOther").value = ""
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

            new Autocompleter.DWR("ctc-term-input", "ctc-term-choices",
                termPopulator, {
                valueSelector: termValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    selectTerm(selectedChoice)
                }
            })
        })
    </script>
</head>
<body>
    <h1>Create AE: Enter basic information</h1>

    <div id="instructions">
        You are entering an adverse event for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
    </div>

    <form:form>
        <input type="hidden" name="_target${targetNumber}"/>
        <input type="hidden" name="_page${pageNumber}"/>
        <div class="row">
            <div class="label"><form:label path="ae.detectionDate">Detection date</form:label></div>
            <div class="value"><form:input path="ae.detectionDate"/></div>
        </div>
        <div class="row">
            <div class="label"><form:label path="ae.grade">Grade</form:label></div>
            <div class="value"><form:select path="ae.grade" items="${grades}" itemValue="code"/></div>
        </div>
        <div class="row">
            <div class="label"><form:label path="ae.attribution">Attribution</form:label></div>
            <div class="value"><form:select path="ae.attribution" items="${attributions}" itemValue="code"/></div>
        </div>
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
            <div class="row">
                <div class="label"><label for="ctc-term-input">CTC term</label></div>
                <div class="value">
                    <input size="50" id="ctc-term-input"/>
                    <div id="ctc-term-choices" class="autocomplete"></div>
                    <form:hidden path="ae.ctcTerm"/>
                </div>
                <div class="extra">
                    Type a portion of the CTC term you are looking for.  If you select a category,
                    only terms in that category will be shown.
                </div>
            </div>
            <div class="row" id="details-for-other-row" style="display: none">
                <div class="label"><form:label path="ae.detailsForOther">Other (specify)</form:label></div>
                <div class="value">
                    <form:textarea path="ae.detailsForOther" cols="40" rows="5"/>
                </div>
            </div>
            <div style="text-align: right">
                <input type="submit" value="Submit"/>
            </div>
        </div>
    </form:form>
</body>
</html>