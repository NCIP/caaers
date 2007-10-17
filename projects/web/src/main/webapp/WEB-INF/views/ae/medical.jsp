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
        div.row div.label {
            width: 16em;
        }

        div.row div.value, div.row div.extra {
            margin-left: 17em;
        }
    </style>

    <script type="text/javascript">
    var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
    var initialAnatomicSite = {
        <c:if test="${not empty command.aeReport.diseaseHistory.codedPrimaryDiseaseSite}">
        id: ${command.aeReport.diseaseHistory.codedPrimaryDiseaseSite.id},
        name: '${command.aeReport.diseaseHistory.codedPrimaryDiseaseSite.name}'
        </c:if>
    }
    
     /*

    function chooseDiseaseOrOther() {
        var term = $('aeReport.diseaseHistory.ctepStudyDisease').value;
        $('aeReport.diseaseHistory.otherPrimaryDisease').disabled = (term != "");
        $('aeReport.diseaseHistory.meddraStudyDisease').disabled = (term != "");
    }
    */
    
   
    
     function chooseDiseaseOrOther() {
        var term = $('aeReport.diseaseHistory.ctepStudyDisease').value;
        var meddraterm = $('aeReport.diseaseHistory.meddraStudyDisease').value;
        
        
        $('aeReport.diseaseHistory.meddraStudyDisease').disabled = (term != "");
        
         if (term != "" || meddraterm !=""){
        	$('aeReport.diseaseHistory.otherPrimaryDisease').disabled = true; 
         }else{
         	$('aeReport.diseaseHistory.otherPrimaryDisease').disabled = false; 
         }
         
        $('aeReport.diseaseHistory.ctepStudyDisease').disabled = (meddraterm != "");
    }

    var EnterDiseaseSite = Class.create()
    Object.extend(EnterDiseaseSite.prototype, {
        initialize: function(index, anatomicSiteName) {
            this.index = index
            var cmProperty = "aeReport.diseaseHistory.metastaticDiseaseSites[" + index + "]";
            this.anatomicSiteProperty = cmProperty + ".codedSite"
            this.otherProperty = cmProperty + ".otherSite"

            if (anatomicSiteName) $(this.anatomicSiteProperty + "-input").value = anatomicSiteName
            $("select-codedSite-" + this.index)
                .observe("click", this.updateAnatomicOrOther.bind(this))
            $("select-otherSite-" + this.index)
                .observe("click", this.updateAnatomicOrOther.bind(this))

            AE.createStandardAutocompleter(
                this.anatomicSiteProperty, this.termPopulator.bind(this),
                function(anatomicSite) {
                    return anatomicSite.name
                })

            this.initializeAnatomicOrOther()
        },

        termPopulator: function(autocompleter, text) {
            createAE.matchAnatomicSite(text, function(values) {
                autocompleter.setChoices(values)
            })
        },

        updateAnatomicOrOther: function() {
            var isAnatomicSite = $("select-codedSite-" + this.index).checked
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
                $("select-codedSite-" + this.index).click()
            } else {
                $("select-otherSite-" + this.index).click()
            }
        }
    })

    function primarySiteValueSelector(obj) {
        return obj.name;
    }

    Event.observe(window, "load", function() {
        AE.createStandardAutocompleter("aeReport.diseaseHistory.codedPrimaryDiseaseSite",
            function(autocompleter, text) {
                createAE.matchAnatomicSite(text, function(values) {
                    autocompleter.setChoices(values)
                })
            },
            primarySiteValueSelector, {
                initialInputValue: initialAnatomicSite.name
            }
        )
    })

    Element.observe(window, "load", function() {
        <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSites}" varStatus="status" var="site">
            new EnterDiseaseSite(${status.index}, '${site.codedSite.name}')
        </c:forEach>

        new ListEditor("metastatic", createAE, "MetastaticDiseaseSite", {
            addFirstAfter: "diseaseInfo",
            addParameters: [aeReportId],
            addCallback: function(index) {
                new EnterDiseaseSite(index);
            },
            deletable: true
        }, 'aeReport.diseaseHistory.metastaticDiseaseSites')

        $('aeReport.diseaseHistory.ctepStudyDisease').observe("change", function() {
            chooseDiseaseOrOther();
        })
        
        $('aeReport.diseaseHistory.meddraStudyDisease').observe("change", function() {
            chooseDiseaseOrOther();
        })

        chooseDiseaseOrOther()
    })

    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section6patientdetails">
	<jsp:attribute name="instructions">
	 <tags:instructions code="instruction_ae_patientDetails" />
	</jsp:attribute>
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

    <c:forEach items="${fieldGroups['participant'].fields}" var="field">
        <tags:renderRow field="${field}"/>
    </c:forEach>

    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <chrome:division title="Disease information" id="diseaseInfo">
            <%--
                <div class="row">
                    <div class="label">Disease Name</div>
                    <div class="value">

                        <select id="aeReport.diseaseHistory.studyDisease" name="aeReport.diseaseHistory.studyDisease" onChange="javascript:chooseDisease();">
                            <option value="">please select--</option>
                            <c:forEach var="studyDisease" varStatus="status" items="${command.study.ctepStudyDiseases}">
                            	<option value="${studyDisease.id}" ${command.aeReport.diseaseHistory.ctepStudyDisease.id == studyDisease.id  ? 'SELECTED' : ''}>${studyDisease.term.term} </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <p class="instructions">
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
                --%>
            <c:forEach items="${fieldGroups['disease'].fields}" var="field">
                <tags:renderRow field="${field}"/>
            </c:forEach>
        </chrome:division>

        <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSites}" varStatus="status">
             <ae:oneMetastaticDiseaseSite index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="metastatic" label="Add a metastatic site"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
