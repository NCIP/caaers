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
            	this.indicator = "identifiers["  + index + "].primaryIndicator1";
            	 Event.observe(this.indicator, "click", function() {
            	 	for(i = 0; i < si.length; i++){
            	 		if(i == this.index) continue;
            	 		$(si[i].indicator).checked = false;
            	 	}
            	 }.bind(this));
            }            
        	
    });
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
    	<c:forEach varStatus="status" items="${command.studySites}" var="si">
      		new jsIdentifier(${status.index});
      	</c:forEach>
      	addIdentifierEditor = new ListEditor('si-section',createStudy, "Identifier",{
      		 addButton: "xxx",
             addIndicator: "si-add-indicator",
             addFirstAfter: "identifierbookmark",
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
		<p id="instructions">
			Add Identifiers associated with the Study
			<a href="javascript:fireAction('addIdentifier','0');"><img
				src="<c:url value="/images/checkyes.gif"/>" border="0" alt="Add"></a><tags:indicator id="si-add-indicator"/><br>
		</p>
		<c:forEach varStatus="status" items="${command.identifiers}">	
		  <study:aStudyChild title="Study Identifier ${status.index + 1}" enableDelete="${status.index > 0}" 
			sectionClass="si-section" removeButtonAction="removeIdentifier" index="${status.index}" />
		</c:forEach>	
		<span id="identifierbookmark"></span>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
