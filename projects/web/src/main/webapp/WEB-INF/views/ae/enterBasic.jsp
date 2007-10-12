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
            width: 28em;
        }
        .longselect label {
            padding-left: 3.0em;
            text-indent: -2.5em;
        }
        .first-item .delete-control {
            display: none;
        }
        .main-fields .extra {
    		width: 100%
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
            initialize: function(div, ctcTerm) {
                this.div = $(div)
                AESections.concat(this);
                this.initialCtcTerm = ctcTerm;

                this.resetTermText()

                Event.observe(this._ctcCategoryId(), "change", this.clearSelectedTerm.bindAsEventListener(this))

                AE.createStandardAutocompleter(
                    this._ctcTermId(), this.termPopulator.bind(this), termValueSelector, {
                        afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                            this.selectTerm(selectedChoice)
                        }.bind(this)
                    }
                )
                AE.registerCalendarPopups(this.div.id)
            },

            _index: function() { return +this.div.getAttribute("item-index"); },

            _aeProperty:            function() { return "aeReport.adverseEvents[" + this._index() + "]" },
            _ctcDetailsId:          function() { return this._aeProperty() + ".ctc-details" },
            _ctcCategoryId:         function() { return this._aeProperty() + ".ctc-category" },
            _ctcTermId:             function() { return this._aeProperty() + ".adverseEventCtcTerm.ctcTerm" },
            _ctcTermInputId:        function() { return this._ctcTermId() + "-input" },
            _ctcTermChoicesId:      function() { return this._ctcTermId() + "-choices" },
            _ctcTermIndicatorId:    function() { return this._ctcTermId() + "-indicator" },
            _detailsForOtherId:     function() { return this._aeProperty() + ".detailsForOther" },
            _detailsForOtherRowId:  function() { return this._aeProperty() + ".detailsForOther-row" },

            resetTermText: function() {
                if (this.initialCtcTerm) {
                    // select term first to work around a bug in showing the "other" row when the
                    // element is only partially visible
                    this.selectTerm(this.initialCtcTerm)
                    $(this._ctcTermInputId()).value = termValueSelector(this.initialCtcTerm)
                }
            },

            clearSelectedTerm: function() {
                $(this._ctcTermInputId()).value = ""
                $(this._ctcTermId()).value = ""
                $(this._detailsForOtherId()).value = ""
                AE.slideAndHide(this._detailsForOtherRowId())
            },

            selectTerm: function(newCtcTerm) {
                $A($(this._ctcCategoryId()).options).each(function(opt) {
                    if (opt.value == newCtcTerm.category.id) {
                        opt.selected = true
                    }
                })

                $(this._ctcTermId()).value = newCtcTerm.id
                if (newCtcTerm.otherRequired) {
                    if ($(this._ctcDetailsId()).visible()) {
                        AE.slideAndShow(this._detailsForOtherRowId())
                    } else {
                        $(this._detailsForOtherRowId()).show()
                    }
                } else {
                    AE.slideAndHide(this._detailsForOtherRowId())
                    $(this._detailsForOtherId()).value = ""
                }

                this.updateGrades(newCtcTerm.id)
            },

            updateGrades: function(ctcTermId) {
                createAE.getTermGrades(ctcTermId, function(grades) {
                    // Note that row index is 0 to 4, while grade is 1 to 5

                    // update text
                    grades.each(function(grade) {
                        var text = $(this._aeProperty() + ".grade-text-" + (grade.code - 1))
                        text.update(grade.code + ": " + grade.displayName.escapeHTML().gsub("(\\r\\n)|(\\n)|(\\r)", "<br>\n"))
                    }.bind(this))

                    // show & hide
                    var validCodes = grades.collect(function(g) { return g.code })
                    for (var i = 0 ; i <= 4 ; i++) {
                        var row = $(this._aeProperty() + ".grade-row-" + i)
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
                createAE.matchTerms(text, ctcVersion, $F(this._ctcCategoryId()), 10, function(values) {
                    autocompleter.setChoices(values)
                })
            }
        })

        function postReset() {
            AESections.each(function(ae) {
                ae.resetTermText()
            })
        }

        var aesEditor;
        Event.observe(window, "load", function() {
            // do this before any of the behaviors are applied to avoid their onChange side effects
            Event.observe("command", "reset", function() {
                // onReset fires _before_ the reset; delay action so it happens afterward
                setTimeout(postReset, 150)
            })
            aesEditor = new ListEditor("ae-section", createAE, "AdverseEvent", {
                addParameters: [aeReportId],
                addCallback: function(nextIndex) {
                    new AESection("ae-section-" + nextIndex);
                },
                reorderable: true,
                deletable: true
            }, "aeReport.adverseEvents")
            // item-index attribute is added by the list editor and it's needed to start up each AESection
            <c:forEach items="${command.aeReport.adverseEvents}" varStatus="status">
            new AESection("ae-section-${status.index}", initialCtcTerm[${status.index}])
            </c:forEach>
        })
    </script>
</head>
<body>
    <tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section2enteraes">
        <jsp:attribute name="instructions">
        <tags:instructions code="instruction_ae_enterBasics" />
        <tags:instructions code="instruction_ae_enterBasicsNote" heading="Note: " />
        </jsp:attribute>
        <jsp:attribute name="singleFields">
            <div class="report-fields">
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