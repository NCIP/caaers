<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    <style type="text/css">
        .instructions { margin-left: 17.5em; }
        div.row div.label {
            width: 16em;
        }
        div.row div.value {
            margin-left: 17em;
        }
    </style>

    <script type="text/javascript">
		
		function chooseDisease(){
				var term = document.getElementById('aeReport.diseaseHistory.studyDisease').value;
				if(term == -1)
				{
					document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseCode').disabled=false;
				}
				else {
					document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseCode').disabled=true;
				}
				
		}

        var anatomicAutocompleterProps = {
            basename: "aeReport.diseaseHistory.anatomicSite",
            populator: function(autocompleter, text) {
                createAE.matchAnatomicSite(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.name
            }
        }

        function acPostSelect(mode, selectedChoice) {
            Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            $(mode.basename).value = selectedChoice.id;
            $(mode.basename + '-selected').show()
            new Effect.Highlight(mode.basename + "-selected")
			document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode').disabled=true;
        }

        function updateSelectedDisplay(mode) {
            if ($(mode.basename).value) {
                Element.update(mode.basename + "-selected-name", $(mode.basename + "-input").value)
                $(mode.basename + '-selected').show()
            } 
			
        }

        function acCreate(mode) {
            new Autocompleter.DWR(mode.basename + "-input", mode.basename + "-choices",
                mode.populator, {
                valueSelector: mode.valueSelector,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                    acPostSelect(mode, selectedChoice)
                },
                indicator: mode.basename + "-indicator"
            })
            Event.observe(mode.basename + "-clear", "click", function() {
                $(mode.basename + "-selected").hide()
                $(mode.basename).value = ""
                $(mode.basename + "-input").value = ""
				document.getElementById('aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode').disabled=false;
            })
        }

        Event.observe(window, "load", function() {
            acCreate(anatomicAutocompleterProps)
            updateSelectedDisplay(anatomicAutocompleterProps)
        })

        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}

        var EnterConMed = Class.create()
        Object.extend(EnterConMed.prototype, {
            initialize: function(index, anatomicSiteName) {				
                this.index = index
                var cmProperty = "aeReport.diseaseHistory.metastaticDiseaseSite[" + index + "]";
                this.anatomicSiteProperty = cmProperty + ".anatomicSite"
                this.otherProperty = cmProperty + ".otherMetastaticDiseaseSite"

                if (anatomicSiteName) $(this.anatomicSiteProperty + "-input").value = anatomicSiteName
                $("select-anatomicSite-" + this.index)
                    .observe("click", this.updateAnatomicOrOther.bind(this))
                $("select-otherMetastaticDiseaseSite-" + this.index)
                    .observe("click", this.updateAnatomicOrOther.bind(this))

                AE.createStandardAutocompleter(
                    this.anatomicSiteProperty, this.termPopulator.bind(this),
                    function(anatomicSite) { return anatomicSite.name })

                this.initializeAnatomicOrOther()
            },

            termPopulator: function(autocompleter, text) {
	                createAE.matchAnatomicSite(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },

            updateAnatomicOrOther: function() {
                var isAnatomicSite = $("select-anatomicSite-" + this.index).checked
                var anatomicSiteRow = $(this.anatomicSiteProperty + "-row")
                var otherRow = $(this.otherProperty + "-row")
                if (isAnatomicSite) {
                    anatomicSiteRow.removeClassName("disabled")
                    otherRow.addClassName("disabled")
                    anatomicSiteRow.getElementsByClassName("value")[0].enableDescendants()
                    otherRow.getElementsByClassName("value")[0].disableDescendants()
                } else {
                    otherRow.removeClassName("disabled")
                    anatomicSiteRow.addClassName("disabled")
                    otherRow.getElementsByClassName("value")[0].enableDescendants()
                    anatomicSiteRow.getElementsByClassName("value")[0].disableDescendants()
                }
            },

            initializeAnatomicOrOther: function() {
                var otherValue = $(this.otherProperty).value
                if (otherValue.length == 0) {
                    $("select-anatomicSite-" + this.index).click()
                } else {
                    $("select-otherMetastaticDiseaseSite-" + this.index).click()
                }
            }
        })

        Element.observe(window, "load", function() {

            <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSite}" varStatus="status" var="site">
            new EnterConMed(${status.index}, '${site.anatomicSite.name}')
            </c:forEach>
			
			
            new ListEditor("metastatic", createAE, "MetastaticDiseaseSite", {
                addFirstAfter: "meta",
                addParameters: [aeReportId],
                addCallback: function(index) {
                    new EnterConMed(index);
                }
            })
        })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}">
    <jsp:attribute name="singleFields">
    <%-- The database identifier shouldn't be here.
         TODO: it should probably be the primary assigned id
    <div class="row">
        <div class="label">Patient ID</div>
        <div class="value"> ${command.participant.id} </div>
    </div>
     --%>

    <div class="row">
        <div class="label">Birth Date</div>
        <div class="value"><tags:formatDate value="${command.participant.dateOfBirth}"/></div>
    </div>

    <div class="row">
        <div class="label">Race</div>
        <div class="value">${command.participant.race}</div>
    </div>

    <div class="row">
        <div class="label">Ethnicity</div>
        <div class="value">${command.participant.ethnicity}</div>
    </div>

    <div class="row">
        <div class="label">Gender</div>
        <div class="value">${command.participant.gender}</div>
    </div>

    <div class="row">
        <div class="label">Height</div>
        <div class="value"> <input id= "aeReport.participantHistory.height" name="aeReport.participantHistory.height" value= "${command.aeReport.participantHistory.height == 0.0 ? '' : command.aeReport.participantHistory.height}" type="text" /> &nbsp;

            <form:select path="aeReport.participantHistory.heightUnitOfMeasure">
                <form:options items="${heightUnitsRefData}" itemLabel="desc"
                        itemValue="code"/>
            </form:select>
            </div>
    </div>

    <div class="row">
        <div class="label">Weight</div>
        <div class="value"> <input id= "aeReport.participantHistory.weight" name="aeReport.participantHistory.weight" value= "${command.aeReport.participantHistory.weight == 0.0 ? '' : command.aeReport.participantHistory.weight}" type="text" /> &nbsp;
            <form:select path="aeReport.participantHistory.weightUnitOfMeasure">
                <form:options items="${weightUnitsRefData}" itemLabel="desc"
                        itemValue="code"/>
            </form:select>
        </div>
    </div>

    <div class="row">
        <div class="label">Baseline performance status</div>
        <div class="value">
            <form:select path="aeReport.participantHistory.baselinePerformanceStatus">
                <form:options items="${bpsRefData}" itemLabel="desc"
                    itemValue="code"/>
            </form:select>
        </div>

    </div>

    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <chrome:division title="Patient Disease Information">
                <div class="row">
                    <div class="label">Disease Name</div>
                    <div class="value">

                        <select id="aeReport.diseaseHistory.studyDisease" name="aeReport.diseaseHistory.studyDisease" onChange="javascript:chooseDisease();">
                            <option value="">please select--</option>
                            <c:forEach var="studyDisease" varStatus="status" items="${command.study.ctepStudyDiseases}">
                                    <c:if test="${command.aeReport.diseaseHistory.ctepStudyDisease.id == studyDisease.id }">
                                        <option value="${studyDisease.id}" selected="true">${studyDisease.term.term} </option>
                                    </c:if>
                                    <c:if test="${command.aeReport.diseaseHistory.ctepStudyDisease.id != studyDisease.id }">
                                        <option value="${studyDisease.id}">${studyDisease.term.term} </option>
                                    </c:if>

                            </c:forEach>
                        </select>
                    </div>
                </div>

                <p id="instructions" class="instructions">
                    If appropriate Disease Name is not on the list above, provide appropriate Disease Name in the "Disease Name Not Listed" field.</p>
                <div class="row">
                    <div class="label">Disease Name Not Listed</div>
                    <div class="value"> <form:input size="38" path="aeReport.diseaseHistory.otherPrimaryDiseaseCode"/> </div>
                </div>

                <div class="row">
                    <div class="label">Primary Site of Disease</div>
                    <div class="value">
                            <form:hidden path="aeReport.diseaseHistory.anatomicSite"/>
                            <input type="text" size="38" id="aeReport.diseaseHistory.anatomicSite-input" value="${command.aeReport.diseaseHistory.anatomicSite.name}"/>
                            <input type="button" id="aeReport.diseaseHistory.anatomicSite-clear" value="Clear"/>
                            <tags:indicator id="aeReport.diseaseHistory.anatomicSite-indicator"/>
                            <tags:errors path="aeReport.diseaseHistory.anatomicSite"/>
                            <div id="aeReport.diseaseHistory.anatomicSite-choices" class="autocomplete"></div>
                            <p id="aeReport.diseaseHistory.anatomicSite-selected" style="display:none">
                                You've selected the anatomic <span id="aeReport.diseaseHistory.anatomicSite-selected-name"></span>.
                            </p>
                    </div>
                </div>

                <p class="instructions">
                        If the appropriate site is not listed, type the specific site in the "Other Primary Site of Disease" field.
                </p>

                <div class="row">
                    <div class="label">Other Primary Site of Disease</div>
                    <div class="value"> <form:input size="38" path="aeReport.diseaseHistory.otherPrimaryDiseaseSiteCode"/> </div>
                </div>

                <div class="row">
                    <div class="label">Date of initial diagnosis</div>
                    <div class="value"> <tags:dateInput path="aeReport.diseaseHistory.dateOfInitialPathologicDiagnosis"/> </div>
                </div>
				<div id="meta" />
            </chrome:division>

        <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSite}" varStatus="status">
             <ae:oneMetastatic index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="metastatic" label="Add a metastatic disease site"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
