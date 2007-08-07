<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net/el"%>
<%@ taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${tab.longTitle}</title>
<tags:includeScriptaculous/>
  <tags:dwrJavascriptLink objects="createStudy"/>
<script language="JavaScript" type="text/JavaScript">
	var si = [];
	var addIdentifierEditor;
	var jsIdentifier = Class.create();;
	Object.extend(jsIdentifier.prototype, {	
            initialize: function(index) {
            	this.index = index;
            	si[index] = this;            	
            	if($('identifiersLazy['  + index + '].organization'))
            	{        		
            	this.organizationName = "identifiersLazy["  + index + "].organization";
            	AE.createStandardAutocompleter(this.organizationName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            } ;         	
            	this.indicator = "identifiersLazy["  + index + "].primaryIndicator1";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++){
            	 		if(i == this.index) continue;
            	 		$(si[i].indicator).checked = false;
            	 	}
            	 }.bind(this));
            
            
            },sitePopulator: function(autocompleter, text) {
         		createStudy.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteSelector: function(organization) { 
        		return organization.name 
        	}
        	}
        	       
        	
    );
    
    function fireAction(action, selected){
		if(action == 'addIdentifier'){
			addIdentifierEditor.add.bind(addIdentifierEditor)();
		}else{
			document.getElementById('command')._target.name='_noname';
			document.studyIdentifiersForm._action.value=action;
			document.studyIdentifiersForm._selected.value=selected;		
			document.studyIdentifiersForm.submit();
		}
	}
	
	function clearField(field){
		field.value="";
	}
	
	  
    Event.observe(window, "load", function() {
        	
        	<c:forEach varStatus="status" items="${command.identifiers}" var="si">
      		new jsIdentifier(${status.index}, '${si.source}');
      		</c:forEach>
      		
      		
      		//This is added for Add Sysetem Identifiers button
		            new ListEditor("si-section", createStudy, "Identifier", {
		            	addParameters: [1],
		            	addFirstAfter: "single-fields",
		                addCallback: function(nextIndex) {
             	new jsIdentifier(nextIndex);
             }
		            });
		            //This is added for Add Organization Identifiers buttion
		             new ListEditor("si-section", createStudy, "Identifier", {
		                addButton: "add-si-section-org-button",
		                addIndicator: "add-si-section-org-indicator",
		            	addParameters: [2],
		            	addFirstAfter: "single-fields",
		                addCallback: function(nextIndex) { 
             	new jsIdentifier(nextIndex);
             }
            });      	
    });
	
</script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studyIdentifiersForm" hideErrorDetails="true">
    <jsp:attribute name="singleFields">
		<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		<c:forEach varStatus="status" items="${command.identifiers}">	
				  <study:studyIdentifier title="Study Identifier ${status.index + 1}" enableDelete="${status.index > 0}" 
					sectionClass="si-section" removeButtonAction="removeIdentifier" index="${status.index}" identifier="${command.identifiers[status.index]}" />
		</c:forEach>	
		
		    
		<span id="identifierbookmark"></span>
		
    </jsp:attribute>
	<jsp:attribute name="localButtons"> 
	      	<chrome:division title="">          	
	      		<tags:listEditorAddButton divisionClass="si-section" 	label="Add System Identifier" />   
                <tags:listEditorAddButton divisionClass="si-section-org" label="Add Organization Identifier" /> 
            </chrome:division>                                                                                                                                                                                                                                                             
	</jsp:attribute>
	
</tags:tabForm>
</body>
</html>
