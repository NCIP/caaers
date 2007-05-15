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
        var initialCtcTerm = [ ]
        <c:forEach items="${command.aeReport.adverseEvents}" var="ae" varStatus="aeStatus">
            <c:if test="${not empty ae.ctcTerm}">
            initialCtcTerm[${aeStatus.index}] = {
                id: ${ae.ctcTerm.id},
                category: {
                    id: ${ae.ctcTerm.category.id},
                    ctc: {
                        id: ${ae.ctcTerm.category.ctc.id}
                    }
                },
                fullName: '${ae.ctcTerm.fullName}',
                otherRequired: ${ae.ctcTerm.otherRequired}
            }
            </c:if>
        </c:forEach>

        var AESections = [ ]

        function ctcVersion() {
            return $F("ctc-version");
        }

        function termValueSelector(term) {
            return term.fullName;
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
                this.ctcTermId = this.aeProperty + ".ctcTerm"
                this.ctcTermInputId = this.aeProperty + ".ctcTerm-input"
                this.ctcTermChoicesId = this.aeProperty + ".ctcTerm-choices"
                this.ctcTermIndicatorId = this.aeProperty + ".ctcTerm-indicator"
                this.detailsForOtherId = this.aeProperty + ".detailsForOther"
                this.detailsForOtherRowId = this.aeProperty + ".detailsForOther-row"

                this.resetTermText()
                this.ctcVersionChanged(null, true)

                Event.observe("ctc-version", "change", this.ctcVersionChanged.bindAsEventListener(this, false))
                Event.observe(this.ctcCategoryId, "change", this.clearSelectedTerm.bindAsEventListener(this))

                AE.createStandardAutocompleter(
                    this.aeProperty + ".ctcTerm", this.termPopulator.bind(this), termValueSelector, {
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

            // TODO: for performance, we should probably embed these in the page
            // since there are only a few dozen of them.
            updateCategories: function() {
                createAE.getCategories(ctcVersion(), function(categories) {
                    var sel = $(this.ctcCategoryId)
                    sel.options.length = 0
                    sel.options.add(new Option("Any", ""))
                    categories.each(function(cat) {
                        var name = cat.name
                        if (name.length > 45) name = name.substring(0, 45) + "..."
                        var opt = new Option(name, cat.id)
                        sel.options.add(opt)
                        if (this.initialCtcTerm && this.initialCtcTerm.category.id == opt.value) {
                            opt.selected = true;
                        }
                    }.bind(this))
                }.bind(this))
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
                    // update text
                    grades.each(function(grade) {
                        console.log("Updating text for ")
                        console.log(grade)
                        console.log(this.aeProperty + ".grade-text-" + grade.code)
                        var text = $(this.aeProperty + ".grade-text-" + grade.code)
                        text.update(grade.code + ": " + grade.displayName.gsub("(\\r\\n)|(\\n)|(\\r)", "<br>\n"))
                    }.bind(this))

                    // show & hide
                    var validCodes = [0].concat(grades.collect(function(g) { return g.code }))
                    for (var i = 0 ; i <= 5 ; i++) {
                        var row = $(this.aeProperty + ".grade-row-" + i)
                        if (validCodes.include(i)) {
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
                createAE.matchTerms(text, ctcVersion(), $F(this.ctcCategoryId), 10, function(values) {
                    autocompleter.setChoices(values)
                })
            },

            ctcVersionChanged: function(evt, atLoad) {
                if (ctcVersion()) {
                    this.updateCategories()
                    if (!atLoad) {
                        this.clearSelectedTerm()
                    }
                    $(this.ctcDetailsId).enableDescendants()
                } else {
                    $(this.ctcDetailsId).disableDescendants()
                }
            }
        })

        function updateCtcVersion() {
            if (initialCtcTerm) {
                $A($("ctc-version").options).each(function(opt) {
                    initialCtcTerm.each(function(term) {
                        if (term && term.category && opt.value == term.category.ctc.id) {
                            opt.selected = true
                        }
                    })
                })
            }
        }

        function postReset() {
            AESections.each(function(ae) {
                ae.resetTermText()
            })
        }

        Event.observe(window, "load", function() {
            // do this before any of the behaviors are applied to avoid their onChange side effects
            updateCtcVersion()
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
    <tags:tabForm tab="${tab}" flow="${flow}">
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
                    <div class="label"><label for="ctc-version">CTC version</label></div>
                    <div class="value">
                        <select id="ctc-version">
                            <option value="">Please select --</option>
                            <c:forEach items="${ctcVersions}" var="ctc">
                                <option value="${ctc.id}" <c:if test="${command.aeReport.adverseEvents[0].ctcTerm.category.ctc.id == ctc.id}">selected</c:if>>${ctc.name}</option>
                            </c:forEach>
                        </select>
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