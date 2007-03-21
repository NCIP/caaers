<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<html>
<head>
<style type="text/css">
        /* TODO: all these are temporary */
        .division {
            float: left;
            width: 45%;
            margin: 1em;
        }
        .division-content {
            padding: 1em;
        }
         #studyDiseases td.label { font-weight: bold; float: left; margin-left: 0.5em; margin-right: 0.5em; width:12em; padding: 1px; }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<style type="text/css">
        .label { width: 12em; text-align: right; padding: 4px; }
</style>
<tags:includeScriptaculous />
<tags:dwrJavascriptLink objects="createStudy" />
<script type="text/javascript">
    
    	function validatePage(){
			return true;
			}
			
	function fireAction(action, selected){
	if(validatePage()){
		addDiseasesToCart()
		document.getElementsByName('_target4')[0].name='_target3';
		document.studyDiseasesForm._action.value=action;
		document.studyDiseasesForm._selected.value=selected;		
		document.studyDiseasesForm.submit();
	}
	}

		function clearField(field){
           field.value="";
           }
           
        function hover(index)
        {
	        //var sel = $("disease-sub-category")
	        //alert (sel.value)
	       
        }   
           
      
   
        var agentAutocompleterProps = {
            basename: "disease",
            populator: function(autocompleter, text) {
                createStudy.matchDiseaseCategories(text, '' , function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.name + "<b> ::</b> " + obj.name
            }
        }
        
        var diseaseAutocompleterProps = {
            basename: "disease",
            populator: function(autocompleter, text) {
                createStudy.matchDiseaseCategories(text, '' , function(values) {
                    autocompleter.setChoices(values)
                })
            },
            valueSelector: function(obj) {
                return obj.name // + "<b> ::</b> " + obj.id
            }
        }
        
              
        function acPostSelect(mode, selectedChoice) {
            //Element.update(mode.basename + "-selected-name", mode.valueSelector(selectedChoice))
            updateCategories(selectedChoice.id);
            //$(mode.basename).value = selectedChoice.id;
            //$(mode.basename + '-selected').show()
            //new Effect.Highlight(mode.basename + "-selected")
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
            })
        }
        
        
         function updateCategories(id) {
            createStudy.matchDiseaseCategoriesByParentId(id, function(categories) {
                var sel = $("disease-sub-category")
                sel.size= categories.length < 10 ? categories.length + 2 : 10 ;
                //sel.size= 10
                sel.options.length = 0
                sel.options.add(new Option("All", ""))
                sel.options[0].selected=true;
                categories.each(function(cat) {
                    var opt = new Option(cat.name, cat.id)
                    sel.options.add(opt)
                })
                showDiseases()
            })
            
        }
        
        function showDiseases() {
	        var categoryId = $("disease-sub-category").value
	        var subCategorySelect = $("disease-sub-category")
	        // If all is selected
	        if ( subCategorySelect.value == "" ){
		        var diseaseTermSelect = $("disease-term")
		        diseaseTermSelect.options.length = 0
		        diseaseTermSelect.size = 10
		        diseaseTermSelect.options.add(new Option("All", ""))
		        diseaseTermSelect.options[0].selected=true;
		        //alert(subCategorySelect.length);
		        for ( i=1; i < subCategorySelect.length; i++){
			        var catId = subCategorySelect.options[i].value
			        
			        createStudy.matchDiseaseTermsByCategoryId(catId, function(diseases) {
                	
                		diseases.each(function(cat) {
                    	var opt = new Option(cat.ctepTerm, cat.id)
                    	diseaseTermSelect.options.add(opt)
                		})
            		})  
		        }
		        
	        }
	        else {
	        	createStudy.matchDiseaseTermsByCategoryId(categoryId, function(diseases) {
                	var sel = $("disease-term")
                	sel.size= diseases.length + 2;
                	sel.options.length = 0
                	sel.options.add(new Option("All", ""))
                	sel.options[0].selected=true;
                	diseases.each(function(cat) {
                    	var opt = new Option(cat.term, cat.id)
                    	sel.options.add(opt)
                	})
            	})
        	}  
        }
        
        /**
         * Copy Diseases from  [Diseases MultiSelect] 
         *   to the [Selected Diseases MultiSelect] 
         *
         */
        function addDiseasesToCart() {
	        var diseaseTerm = $("disease-term");
	        var diseaseSelected = $("disease-sel");
	        var diseaseSelectedHidden = $("disease-sel-hidden");
	        if ( diseaseSelected.options[0].value == "" ){
	        	diseaseSelected.options.length = 0
        	}
        	// If all is selected  in the [Diseases MultiSelect]
	        if (diseaseTerm.options[0].selected ){
		        for ( i=1; i < diseaseTerm.length; i++)
		        {
			     	var opt = new Option(diseaseTerm.options[i].text, diseaseTerm.options[i].value)
			     	var opt1 = new Option(diseaseTerm.options[i].text, diseaseTerm.options[i].value)
			     	diseaseSelected.options.add(opt)
			     	diseaseSelectedHidden.options.add(opt1)	 
		        } 
	        }
	        // If anything other than all is selected
	        else {
		        for ( i=1; i < diseaseTerm.length; i++)
		        {
			        if (diseaseTerm.options[i].selected) {
				    var opt = new Option(diseaseTerm.options[i].text, diseaseTerm.options[i].value)
			        //var opt1 = new Option(diseaseTerm.options[i].text, diseaseTerm.options[i].value)
			        diseaseSelected.options.add(opt)
			        //diseaseSelectedHidden.options.add(opt1)
			        }        
	       		}
	        }
	        // Copy over [Selected Diseases MultiSelect] to [Hidden Selected Diseases MultiSelect]
	        //selectAll(diseaseSelectedHidden)
	        synchronizeSelects(diseaseSelected,diseaseSelectedHidden);
        }
        
        function synchronizeSelects(selectFrom, selectTo)
        {
	        // Delete everything from the target
	        selectTo.options.length=0;
	        // iterate over the source and add to target
	        for ( i=0; i < selectFrom.length; i++){
		        	var opt = new Option(selectFrom.options[i].text, selectFrom.options[i].value)
		        	selectTo.options.add(opt)
			     	selectTo.options[i].selected=true;
		        }      
        }
        
        function removeDiseasesFromCart()
        {
	        var diseaseSelected = $("disease-sel");
	        var diseaseSelectedHidden = $("disease-sel-hidden");
	        
	        for ( i=0; i < diseaseSelected.length; i++)
		        {
			       if (diseaseSelected.options[i].selected) {
			          diseaseSelected.options[i] = null
			        }        
	       		}
	     	synchronizeSelects(diseaseSelected,diseaseSelectedHidden)   
	        
        }
        
        function populateSelectsOnLoad()
        {
	        
	        if ( $('disease-input').value.length > 0 )
	        { 
	        	createStudy.matchDiseaseCategories($('disease-input').value, '' , function(values) {
		        		updateCategories(values[0].id);
                })
        	}
        }

        Event.observe(window, "load", function() {
	        $('disease-sel').style.display='none';
	        $('disease-sel-hidden').style.display='none';
            
            acCreate(diseaseAutocompleterProps)
            updateSelectedDisplay(diseaseAutocompleterProps)
            
            Event.observe("disease-sub-category", "change", function() { showDiseases() })
            populateSelectsOnLoad();
                      
            Element.update("flow-next", "Continue &raquo;")
        })
       
    </script>
</head>
<body>
<!-- MAIN BODY STARTS HERE -->
<chrome:body title="${flow.name}: ${tab.longTitle}">
	<form:form method="post" name="studyDiseasesForm" cssClass="standard">
		<%--<chrome:division id="study-details"> --%>
			<%--<tags:tabFields tab="${tab}" /> --%>

			<div>
				 <input type="hidden" name="_action" value=""> 
				 <input type="hidden" name="_selected" value="">
			</div>
			<chrome:division title="Disease" id="disease">
					
					Search for a Disease Category<br>
					<input:hidden id="disease" /> 
					<form:input size="45" id="disease-input"  path="diseaseCategoryAsText" /> 
					<tags:indicator id="disease-indicator" />
					<div id="disease-choices" class="autocomplete"></div>
					<input type="button" id="disease-clear" value="Clear" />
					<p id="disease-selected" style="display: none"></p>
					
					<br><br>Select a Sub Category<br>
					<select multiple size="1" onmouseover="javascript:hover()" style="width:400px" id="disease-sub-category">
						<option value="">Choose a Category First</option>
					</select> 
					
					<br><br>Diseases<br>
					<select multiple size="1" style="width:400px" id="disease-term">
						<option value="">Choose a Category First</option>
					</select> <span id="disease-selected-name"></span> <a
						href="javascript:fireAction('addStudyDisease','0');"><img
						src="/caaers/images/checkyess.gif" border="0" alt="Add"></a> <br>

					<select multiple size="10" id="disease-sel">
						<option value="">No Selected Diseases</option>
					</select> <form:select id="disease-sel-hidden" size="1"
						path="diseaseTermIds">
					</form:select>
			
			
			</chrome:division>
			<chrome:division title="Selected Disease - ${fn:length(command.studyDiseases)} " id="diseases">
			 <div STYLE="  font-size: 12px; overflow: auto;">
					<TABLE border="0"  width="100%" id="studyDetails">
						<tr>
							<td >Disease Term</td>
							<td style="width:55px;" >Primary</td>
						</tr>
					</TABLE>
					</div>



					<div STYLE=" height: 400px;  font-size: 12px; overflow: auto;">
					<table border="1" width="96%" id="studyDetails">
						<c:forEach items="${command.studyDiseases}" var="studyDisease"
							varStatus="status">
							<tr>
								<td ><a href="javascript:fireAction('removeStudyDisease',${status.index});">X</a>&nbsp;
									 ${studyDisease.diseaseTerm.ctepTerm}</td>
								<td style="width:35px;">
								<form:checkbox  path="studyDiseases[${status.index}].leadDisease" /></td>
								</td>
							</tr>

						</c:forEach>
					</table>
					</DIV>
				
			</chrome:division>

	</form:form>
	<!-- MAIN BODY ENDS HERE -->
</chrome:body>
</body>
</html>
