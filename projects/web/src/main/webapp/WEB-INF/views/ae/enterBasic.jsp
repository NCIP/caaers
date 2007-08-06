<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>

<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>Enter basic AE information</title>
    <tags:stylesheetLink name="ae"/>
    <style type="text/css">
        /* This is intended to apply to the grade longselect only */
        .longselect {
            width: 20em;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
    </style>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
        var ctcVersion = ${command.assignment.studySite.study.ctcVersion.id}
        var initialCtcTerm = [ ]
        <c:forEach items="${command.aeReport.adverseEvents}" var="ae" varStatus="aeStatus">
            <c:if test="${not empty ae.adverseEventCtcTerm.ctcTerm}">
            initialCtcTerm[${aeStatus.index}] = {
                id: ${ae.adverseEventCtcTerm.term.id},
                category: {
                    id: ${ae.adverseEventCtcTerm.term.category.id},
                    ctc: {
                        id: ${ae.adverseEventCtcTerm.term.category.ctc.id}
                    }
                },
                fullName: '${ae.adverseEventCtcTerm.term.fullName}',
                fullNameWithMedDRA : '${ae.adverseEventCtcTerm.term.fullNameWithMedDRA}',
                otherRequired: ${ae.adverseEventCtcTerm.term.otherRequired}
            }
            </c:if>
        </c:forEach>

        var AESections = [ ]

        function ctcVersion() {
            return ctcVersion;
        }

        function termValueSelector(term) {
            return term.fullNameWithMedDRA;
        }

        var AESection = Class.create();
        Object.extend(AESection.prototype, {
            initialize: function(index, ctcTerm) {
                AESections[index] = this;
                this.index = index
                this.initialCtcTerm = ctcTerm;

                this.ctcDetailsId = "ctc-details-" + index;
                this.ctcCategoryId = "ctc-category-" + index
                this.aeProperty = "aeReport.adverseEvents[" + index + "]";
                var ctcTermBase = this.aeProperty + ".adverseEventCtcTerm.term"
                this.ctcTermId = ctcTermBase
                this.ctcTermInputId = ctcTermBase + "-input"
                this.ctcTermChoicesId = ctcTermBase + "-choices"
                this.ctcTermIndicatorId = ctcTermBase + "-indicator"
                this.detailsForOtherId = this.aeProperty + ".detailsForOther"
                this.detailsForOtherRowId = this.aeProperty + ".detailsForOther-row"

                this.resetTermText()

                Event.observe(this.ctcCategoryId, "change", this.clearSelectedTerm.bindAsEventListener(this))

                AE.createStandardAutocompleter(
                    this.ctcTermId, this.termPopulator.bind(this), termValueSelector, {
                        afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                            this.selectTerm(selectedChoice)
                        }.bind(this)
                    }
                )
            },

            resetTermText: function() {
                if (this.initialCtcTerm) {
                    // select term first to work around a bug in showing the "other" row when the
                    // element is only partially visible
                    this.selectTerm(this.initialCtcTerm)
                    $(this.ctcTermInputId).value = termValueSelector(this.initialCtcTerm)
                }
            },

            clearSelectedTerm: function() {
                $(this.ctcTermInputId).value = ""
                $(this.ctcTermId).value = ""
                $(this.detailsForOtherId).value = ""
                AE.slideAndHide(this.detailsForOtherRowId)
            },

            selectTerm: function(newCtcTerm) {
                $A($(this.ctcCategoryId).options).each(function(opt) {
                    if (opt.value == newCtcTerm.category.id) {
                        opt.selected = true
                    }
                })

                $(this.ctcTermId).value = newCtcTerm.id
                if (newCtcTerm.otherRequired) {
                    if ($(this.ctcDetailsId).visible()) {
                        AE.slideAndShow(this.detailsForOtherRowId)
                    } else {
                        $(this.detailsForOtherRowId).show()
                    }
                } else {
                    AE.slideAndHide(this.detailsForOtherRowId)
                    $(this.detailsForOtherId).value = ""
                }

                this.updateGrades(newCtcTerm.id)
            },

            updateGrades: function(ctcTermId) {
                createAE.getTermGrades(ctcTermId, function(grades) {
                    // Note that row index is 0 to 4, while grade is 1 to 5

                    // update text
                    grades.each(function(grade) {
                        var text = $(this.aeProperty + ".grade-text-" + (grade.code - 1))
                        text.update(grade.code + ": " + grade.displayName.escapeHTML().gsub("(\\r\\n)|(\\n)|(\\r)", "<br>\n"))
                    }.bind(this))

                    // show & hide
                    var validCodes = grades.collect(function(g) { return g.code })
                    for (var i = 0 ; i <= 4 ; i++) {
                        var row = $(this.aeProperty + ".grade-row-" + i)
                        if (validCodes.include(i + 1)) {
                            row.enableDescendants()
                            row.show()
                        } else {
                            row.hide()
                            row.disableDescendants()
                        }
                    }
                }.bind(this))
            },

            termPopulator: function(autocompleter, text) {
                createAE.matchTerms(text, ctcVersion, $F(this.ctcCategoryId), 10, function(values) {
                    autocompleter.setChoices(values)
                })
            }
        })

        function postReset() {
            AESections.each(function(ae) {
                ae.resetTermText()
            })
        }

        Event.observe(window, "load", function() {
            // do this before any of the behaviors are applied to avoid their onChange side effects
            Event.observe("command", "reset", function() {
                // onReset fires _before_ the reset; delay action so it happens afterward
                setTimeout(postReset, 150)
            })
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status">
            new AESection(${status.index}, initialCtcTerm[${status.index}])
            </c:forEach>
            new ListEditor("ae-section", createAE, "AdverseEvent", {
                addParameters: [aeReportId],
                addCallback: function(nextIndex) {
                    new AESection(nextIndex);
                }
            })
        })
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enterbasicaeinformation">
        <jsp:attribute name="instructions">
            You are entering an adverse event report for ${command.assignment.participant.fullName} on
            ${command.assignment.studySite.study.shortTitle}.
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <div class="report-fields">
                <c:forEach items="${fieldGroups.report.fields}" var="field">
                    <tags:renderRow field="${field}"/>
                </c:forEach>
                <div class="row">
                    <div class="label">CTC version</div>
                    <div class="value">
                        ${command.assignment.studySite.study.terminology.ctcVersion.name}
                    </div>
                </div>
            </div>
        </jsp:attribute>
        <jsp:attribute name="repeatingFields">
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status">
                <ae:oneAdverseEvent index="${status.index}"/>
            </c:forEach>
        </jsp:attribute>
        <jsp:attribute name="localButtons">
            <tags:listEditorAddButton divisionClass="ae-section" label="Add another AE"/>
        </jsp:attribute>
    </tags:tabForm>
</body>
</html>