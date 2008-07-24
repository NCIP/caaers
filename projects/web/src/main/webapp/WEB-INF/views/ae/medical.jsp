<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@ taglib prefix="ae" tagdir="/WEB-INF/tags/ae" %>
<link rel="stylesheet" type="text/css" href="<c:url value="/css/extremecomponents.css"/>">
<script type="text/javascript" src="/caaers/js/extremecomponents.js"></script>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${tab.longTitle}</title>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <tags:dwrJavascriptLink objects="createAE"/>
    
    <tags:labs labs="${command.assignment.labLoads}"/>
    
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


   function findBSA(){
   	  var ht = $F('aeReport.participantHistory.height.quantity');
   	  var htUOM = $F('aeReport.participantHistory.height.unit');
   	  var wt = $F('aeReport.participantHistory.weight.quantity');
   	  var wtUOM = $F('aeReport.participantHistory.weight.unit');
   	  createAE.calculateBodySurfaceArea(ht, htUOM, wt, wtUOM,function(bsa){
   	    if(bsa > 0) Element.update($('bsa-value'), bsa.toFixed(2));
   	  });
   }

     function chooseDiseaseOrOther(other) {
        var term = '';
        if($('aeReport.diseaseHistory.ctepStudyDisease')){
          term = $F('aeReport.diseaseHistory.ctepStudyDisease')
           if (term != ''){
           createAE.getDiseaseFromStudyDisease(term == '' ? '' : term ,function(diseaseId){
   	    		if ( diseaseId == '190' || diseaseId == '98'){
        		AE.slideAndShow("aeReport.diseaseHistory.otherPrimaryDisease-row")
    			}else{
        		 $('aeReport.diseaseHistory.otherPrimaryDisease').value = ''
       		 	 AE.slideAndHide("aeReport.diseaseHistory.otherPrimaryDisease-row")
    		} });
   	  	   }
        }
    }


	function choosePrimarySiteOrOther() {
        var primaryDiseaseSiteId = $F('aeReport.diseaseHistory.codedPrimaryDiseaseSite')
        if (primaryDiseaseSiteId == '110'){
        	AE.slideAndShow("aeReport.diseaseHistory.otherPrimaryDiseaseSite-row")
        }else{
        	AE.slideAndHide("aeReport.diseaseHistory.otherPrimaryDiseaseSite-row")
        }

    }


    var EnterDiseaseSite = Class.create()
    Object.extend(EnterDiseaseSite.prototype, {
        initialize: function(index, anatomicSiteName) {
            this.index = index
            var cmProperty = "aeReport.diseaseHistory.metastaticDiseaseSites[" + index + "]";
            this.anatomicSiteProperty = cmProperty + ".codedSite"
            this.otherProperty = cmProperty + ".otherSite"

            if (anatomicSiteName) $(this.anatomicSiteProperty + "-input").value = anatomicSiteName

            AE.createStandardAutocompleter(
                this.anatomicSiteProperty, this.termPopulator.bind(this),
                function(anatomicSite) {
                    return anatomicSite.name
                },
                {
                	 afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                	 	   var cmProperty = "aeReport.diseaseHistory.metastaticDiseaseSites[" + index + "]";
            			   var anatomicSiteProperty = cmProperty + ".codedSite"
            			   var otherProperty = cmProperty + ".otherSite"

                		   $(anatomicSiteProperty).value = selectedChoice.id
                		    if (selectedChoice.id == '110'){
        						AE.slideAndShow(otherProperty + "-row")
        					}else{
        						$(otherProperty).value=""
        						AE.slideAndHide(otherProperty + "-row")

        					}
               }
               })

            this.initializeAnatomicOrOther()
            initSearchField()

        },

        termPopulator: function(autocompleter, text) {
            createAE.matchAnatomicSite(text, function(values) {
                autocompleter.setChoices(values)
            })
        },
        initializeAnatomicOrOther: function() {
        	if ($F(this.anatomicSiteProperty) == '110'){
        		AE.slideAndShow(this.otherProperty + "-row")
        	}else{
        		AE.slideAndHide(this.otherProperty + "-row")
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
                initialInputValue: initialAnatomicSite.name,
                afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                		   $('aeReport.diseaseHistory.codedPrimaryDiseaseSite').value = selectedChoice.id
                		    if (selectedChoice.id == '110'){
        						AE.slideAndShow("aeReport.diseaseHistory.otherPrimaryDiseaseSite-row")
        					}else{
        						$("aeReport.diseaseHistory.otherPrimaryDiseaseSite").value=""
        						AE.slideAndHide("aeReport.diseaseHistory.otherPrimaryDiseaseSite-row")

        					}
               }
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
                captureHelpControlEvents();
            },
            deletable: true
        }, 'aeReport.diseaseHistory.metastaticDiseaseSites')

		if($('aeReport.diseaseHistory.ctepStudyDisease')){
        	$('aeReport.diseaseHistory.ctepStudyDisease').observe("change", function() {
            	chooseDiseaseOrOther($('aeReport.diseaseHistory.otherPrimaryDisease').value);
        	})
        }
        if($('aeReport.diseaseHistory.meddraStudyDisease')){
        	$('aeReport.diseaseHistory.meddraStudyDisease').observe("change", function() {
            	chooseDiseaseOrOther($('aeReport.diseaseHistory.otherPrimaryDisease').value);
        	})
		}
        chooseDiseaseOrOther($('aeReport.diseaseHistory.otherPrimaryDisease').value)
        choosePrimarySiteOrOther()
        
        //observe the onChange and onBlur on height and weight
        Event.observe('aeReport.participantHistory.height.quantity','blur' ,findBSA);
   	    Event.observe('aeReport.participantHistory.height.unit','change',findBSA);
   	    Event.observe('aeReport.participantHistory.weight.quantity','blur',findBSA);
   	    Event.observe('aeReport.participantHistory.weight.unit','change',findBSA);

        initSearchField()
        findBSA();
    })

    function showDiseaseSiteTable(tableId, outerTableId) {
        var parameterMap = getParameterMap('command');
        createAE.buildAnatomicSiteTable(parameterMap,tableId,showPopUpTable);
        function showPopUpTable(table) {
                var testDiv = $(tableId);
                var testOuterDiv = $(outerTableId);
                testDiv.innerHTML = table;
                testDiv.show();
                testOuterDiv.show();
        }

    }

            function hideShowAllTable(popupTable) {
               var popupTableDiv = $(popupTable);
                popupTableDiv.hide();
            }


    function fillDiseaseSiteAutoCompletor(diseaseSiteId,tableId) {


        var div = $(tableId + '-outer')
        div.hide()
        createAE.getAnatomicSiteById(diseaseSiteId, function(values) {
            if (tableId == 'primarySiteOfDiseaseTable')
            {
                var ctcTerm = $('aeReport.diseaseHistory.codedPrimaryDiseaseSite-input')
                ctcTerm.value = primarySiteValueSelector(values)
                ctcTerm.className='autocomplete'
                $('aeReport.diseaseHistory.codedPrimaryDiseaseSite').value = diseaseSiteId
            }else{
                var index = tableId.substring(tableId.length - 1, tableId.length)

                var ctcTerm = $('aeReport.diseaseHistory.metastaticDiseaseSites[' + index + '].codedSite-input')
                ctcTerm.value = primarySiteValueSelector(values)
                $('aeReport.diseaseHistory.metastaticDiseaseSites[' + index + '].codedSite').value = diseaseSiteId
                ctcTerm.className='autocomplete'

            }

        });
    }


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
        <div class="value">${command.participant.dateOfBirth}</div>
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
	
	<tags:renderRow field="${fieldGroups['participant'].fields[0]}"/>
	<tags:renderRow field="${fieldGroups['participant'].fields[1]}" />
	<div class="row">
        <div class="label">Body surface area</div>
        <div class="value"><span id="bsa-value">  </span></div>
    </div>
	<tags:renderRow field="${fieldGroups['participant'].fields[2]}"/>
	
	
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <chrome:division title="Disease information" id="diseaseInfo">

            <tags:renderRow field="${fieldGroups['disease'].fields[0]}"/>
			<tags:renderRow field="${fieldGroups['disease'].fields[1]}" style="display: none" />

           <tags:renderRow field="${fieldGroups['disease'].fields[2]}"
                             extraParams="<a href=\"javascript:showDiseaseSiteTable('primarySiteOfDiseaseTable', 'primarySiteOfDiseaseTable-outer')\">Show All</a>" />
			<div id="primarySiteOfDiseaseTable-outer"
			                 style="position: absolute; display: none; left: 640px; width:400px; z-index:99;">
			<table width="100%" class="eXtremeTable" frame="border" border-color="blue" bgcolor="white">
			<tbody>
			<tr class="titleRow">
			  <td align="left" class="title">Select the primary site of disease:</td><td width="20px"><a href="javascript:hideShowAllTable('primarySiteOfDiseaseTable-outer')">
			       <img src="/caaers/images/rule/window-close.gif" id="close-image"/>
			      </a></td>
			</tr>
			<tr>
			<td colspan="2">
			        <div id="primarySiteOfDiseaseTable"  />

			</td>
			</tr>
			</tbody>
			</table>

			</div>
			<tags:renderRow field="${fieldGroups['disease'].fields[3]}" style="display: none"/>
			<tags:renderRow field="${fieldGroups['disease'].fields[4]}"/>

        </chrome:division>

        <c:forEach items="${command.aeReport.diseaseHistory.metastaticDiseaseSites}" varStatus="status">
             <ae:oneMetastaticDiseaseSite index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="metastatic" label="Add a metastatic site" buttonCssClass="ae-list-editor-button"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>