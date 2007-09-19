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
		  initialize: function(index, nameValue) {
		  	this.index = index;
		  	this.baseName = 'aeReport.labs[' + index + ']'; 
		  	this.testName = $(this.baseName + '.name');
		  	this.testNameInput = $(this.baseName + '.name-input');
		  	this.other = $(this.baseName + '.other');;
		  	if(nameValue){
		  		this.testNameInput.value = nameValue;
		  	}
		  	
		  	AE.registerCalendarPopups("lab-" + this.index)
		  	//register autocompleter.
		  	AE.createStandardAutocompleter(
                this.baseName + '.name', 
                function(autocompleter, text) {
                	createAE.matchLabTestNames(text, function(values){
                		autocompleter.setChoices(values)	
                	});
                },
                function(lov) { 
                   	return lov.desc 
                },
                {
                  afterUpdateElement: function(inputElement, selectedElement, selectedChoice) {
            		$('aeReport.labs[' + index + '].name').value = selectedChoice.desc;
        		  }
                });
                
		  	//register the radio buttons.
		  	$('labname-' + this.index).observe("click", function(event){
		  		this.testNameInput.enable();
		  		this.other.disable();
		  		this.other.clear();
		  	}.bindAsEventListener(this));
		  	$('labother-' + this.index).observe("click", function(event){
		  		this.other.enable();
		  		this.testName.clear();
		  		this.testNameInput.clear();
		  		this.testNameInput.disable();
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
                    new EnterLab(index);
                }
            })
            <c:forEach items="${command.aeReport.labs}" varStatus="status" var="lab">
            	new EnterLab(${status.index},"${lab.name}");
            </c:forEach>
            
        })
    </script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" pageHelpAnchor="section6diagnostictestandlabresults">
    <jsp:attribute name="instructions">
        You are entering labs for ${command.assignment.participant.fullName} on
        ${command.assignment.studySite.study.shortTitle}.
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