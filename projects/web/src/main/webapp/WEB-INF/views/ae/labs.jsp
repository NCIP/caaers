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
    <script type="text/javascript">
        var aeReportId = ${empty command.aeReport.id ? 'null' : command.aeReport.id}
		var EnterLab = Class.create();
		Object.extend(EnterLab.prototype, {
		  initialize: function(index, nameValue, categoryId) {
		  	this.index = index;
		  	this.baseName = 'aeReport.labs[' + index + ']'; 
		  	this.testName = $(this.baseName + '.labTerm');
		  	this.testNameInput = $(this.baseName + '.labTerm-input');
		  	this.categoryInput = $(this.baseName + '.lab-category');
		  	this.categoryId = (categoryId == null || categoryId.length) == 0 ? null : categoryId ;
		  	this.other = $(this.baseName + '.other');;
		  	if(nameValue){
		  		this.testNameInput.value = nameValue;
		  	}
		  	if(this.categoryId){
		  		for (i=0; i < this.categoryInput.options.length; i++){
		  			if (this.categoryInput.options[i].value == this.categoryId){
		  				this.categoryInput.options[i].selected=true;
		  				
		  				if (this.categoryInput.options[i].value == "105"){
		  						AE.slideAndHide($('not-microbiology-'+index))
		  						AE.slideAndShow($('microbiology-'+index))
		  						
		  					}else{		  						
		  						
		  					}
		  				
		  				break
		  			}	
		  		}
		  	}
		  	
		  	AE.registerCalendarPopups("lab-" + this.index)
		  	//register autocompleter.
		  	AE.createStandardAutocompleter(
                this.baseName + '.labTerm', 
                function(autocompleter, text) {
                	cat = $('aeReport.labs[' + index + '].lab-category')
                	catId = ""
                	for (i=0; i < cat.length; i++){
		  			if (cat.options[i].selected == true){
		  				catId = cat.options[i].value
		  			}	
		  			}
                	createAE.matchLabTerms(text, catId , function(values){
                		autocompleter.setChoices(values)	
                	});
                },
                function(labTerm) {
                   	return labTerm.term 
                },
                {
                  afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
                  	$('aeReport.labs[' + index + '].labTerm').value=selectedChoice.id
                  	categoryInput = $('aeReport.labs[' + index + '].lab-category');
                  	for (i=0; i < categoryInput.options.length; i++){
		  				if (categoryInput.options[i].value == selectedChoice.category.id){
		  					categoryInput.options[i].selected=true;
		  					if (categoryInput.options[i].value == "105"){
		  						AE.slideAndHide($('not-microbiology-'+index))
		  						AE.slideAndShow($('microbiology-'+index))
		  						$('aeReport.labs[' + index + '].units').options[0].selected=true
		  						$('aeReport.labs[' + index + '].baseline.value').value=""
		  						$('aeReport.labs[' + index + '].baseline.date').value=""
		  						$('aeReport.labs[' + index + '].nadir.value').value=""
		  						$('aeReport.labs[' + index + '].nadir.date').value=""
		  						$('aeReport.labs[' + index + '].recovery.value').value=""
		  						$('aeReport.labs[' + index + '].recovery.date').value=""
		  					}else{
		  						if ($('not-microbiology-'+index).style.display != ""){
		  						$('aeReport.labs[' + index + '].site').value=""
		  						$('aeReport.labs[' + index + '].labDate').value=""
		  						$('aeReport.labs[' + index + '].infectiousAgent').value=""
		  						AE.slideAndHide($('microbiology-'+index))
		  						AE.slideAndShow($('not-microbiology-'+index))
		  						}
		  					}
		  					break
		  				}	
		  			}
		  			
        		  }
                });
             
             
            //register the radio buttons.
		  	this.categoryInput.observe("change", function(event){
		  		this.testNameInput.value=""
		  	}.bindAsEventListener(this)); 
             
		  	//register the radio buttons.
		  	$('labname-' + this.index).observe("click", function(event){
			  	this.categoryInput.disabled=false;
		  		this.testNameInput.removeAttribute('readOnly')
                this.other.setAttribute('readOnly',true);
		  		this.other.clear();
		  	}.bindAsEventListener(this));
		  	$('labother-' + this.index).observe("click", function(event){
		  		this.categoryInput.options[0].selected=true;
		  		this.categoryInput.disabled=true;
		  		this.other.removeAttribute('readOnly')
                this.testNameInput.setAttribute('readOnly',true);
		  		this.testNameInput.clear();
		  		this.testName.value=''
		  		if ($('not-microbiology-'+index).style.display != ""){
		  		AE.slideAndHide($('microbiology-'+index))
		  		AE.slideAndShow($('not-microbiology-'+index))
		  		}
		  		
		  	}.bindAsEventListener(this));
		  	
		  	this.initializeNameOrOther();
		  },
		  initializeNameOrOther: function() {
              if (this.other.value.length == 0) {
                  $("labname-" + this.index).click()
              } else {
                  $("labother-" + this.index).click()
              }
          }
		});
		
		
        Element.observe(window, "load", function() {
            new ListEditor("lab", createAE, "Lab", {
                addParameters: [aeReportId],
                addFirstAfter: "single-fields",
                addCallback: function(index) {
                    new EnterLab(index,null);
                },
                deletable: true,
                reorderable: true
            }, 'aeReport.labs')
            <c:forEach items="${command.aeReport.labs}" varStatus="status" var="lab">
            	new EnterLab(${status.index},"${lab.labTerm.term}","${lab.labTerm.category.id}");
            </c:forEach>
            
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section15labs">
    <jsp:attribute name="instructions">
    <tags:instructions code="instruction_ae_labs" />
    </jsp:attribute>
    <jsp:attribute name="repeatingFields">
        <c:forEach items="${command.aeReport.labs}" varStatus="status">
            <ae:oneLab index="${status.index}"/>
        </c:forEach>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
        <tags:listEditorAddButton divisionClass="lab" label="Add a lab"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>