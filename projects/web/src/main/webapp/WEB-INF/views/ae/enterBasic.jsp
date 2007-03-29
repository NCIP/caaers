<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Enter basic AE information</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var ctcVersion, initialCtcTerm;
        <c:set var="currentCtcTerm" value="${command.aeReport.adverseEvents[0].ctcTerm}"/>
        <c:if test="${not empty command.aeReport.adverseEvents[0].ctcTerm}">
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
        var ctcTermId = "${fieldGroups.ctcTerm0.fields[0].propertyName}"
        var ctcTermInputId = "${fieldGroups.ctcTerm0.fields[0].attributes.textfieldId}"
        var ctcTermIndicatorId = "${fieldGroups.ctcTerm0.fields[0].propertyName}-indicator"
        var detailsForOtherId = "${fieldGroups.ctcOther0.fields[0].propertyName}"
        var detailsForOtherRowId = "${fieldGroups.ctcOther0.fields[0].propertyName}-row"

        function ctcVersionSelector(atLoad) {
            ctcVersion = $F("ctc-version")
            if (ctcVersion) {
                updateCategories()
                if (atLoad) {
                    $("ctc-details").show()
                } else {
                    clearSelectedTerm()
                    AE.slideAndShow("ctc-details")
                }
            } else {
                AE.slideAndHide("ctc-details")
            }
        }

        function ctcCategorySelector() {
            clearSelectedTerm();
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
                if ($("ctc-details").visible()) {
                    AE.slideAndShow(detailsForOtherRowId)
                } else {
                    $(detailsForOtherRowId).show()
                }
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
            // do this before any of the behaviors are applied to avoid their onChange side effects
            if (initialCtcTerm) {
                $A($("ctc-version").options).each(function(opt) {
                    if (opt.value == initialCtcTerm.category.ctc.id) {
                        opt.selected = true
                    }
                })
                // select term first to work around a bug in showing the "other" row when the
                // element is only partially visible
                selectTerm(initialCtcTerm)
                ctcVersionSelector(true)
                $(ctcTermInputId).value = "${currentCtcTerm.fullName}"
            }

            Event.observe("ctc-version", "change", function() { ctcVersionSelector(false) })
            Event.observe("ctc-category", "change", ctcCategorySelector)

            new Autocompleter.DWR(ctcTermInputId, "${fieldGroups.ctcTerm0.fields[0].attributes.choicesId}",
                termPopulator, {
                valueSelector: termValueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    selectTerm(selectedChoice)
                },
                indicator: ctcTermIndicatorId
            })
        })
    </script>
</head>
<body>
    <form:form cssClass="standard">
        <tags:tabFields tab="${tab}"/>

        <chrome:division title="Report">
            <p id="instructions">
                You are entering an adverse event report for ${command.assignment.participant.fullName} on
                ${command.assignment.studySite.study.shortTitle}.
            </p>

            <div class="report-fields">
                <c:forEach items="${fieldGroups.report.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
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
            </div>
        </chrome:division>

        <chrome:division title="Adverse Event 1">
            <tags:errors path="*"/>

            <div id="ctc-details" style="display: none">
                <div class="row">
                    <div class="label"><label for="ctc-category">CTC category</label></div>
                    <div class="value">
                        <select id="ctc-category"></select>
                    </div>
                </div>
                <tags:renderRow field="${fieldGroups.ctcTerm0.fields[0]}"/>
                <tags:renderRow field="${fieldGroups.ctcOther0.fields[0]}" cssStyle="display: none"/>
            </div>

            <div class="main-fields">
                <c:forEach items="${fieldGroups.main0.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
            </div>
        </chrome:division>
    </form:form>
</body>
</html>