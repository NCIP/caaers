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
	var addSiteEditor;
	function fireAction(action, selected){
		if(action == 'addSite'){
			addSiteEditor.add.bind(addSiteEditor)();
		}else {
			//addSiteEditor.delete.bind(addSiteEditor)();
			var ssfrm = $('command');
			ssfrm._target.name='_noname';
			ssfrm._action.value=action;
			ssfrm._selected.value=selected;		
			ssfrm.submit();
		}
	}

	var jsStudySite = Class.create();
	Object.extend(jsStudySite.prototype, {
            initialize: function(index, orgName) {
            	this.index = index;
            	this.orgName = orgName;
            	this.sitePropertyName = "studySites["  + index + "].organization";
            	this.irbApprovalInputId = "studySites["  + index + "].irbApprovalDate";
            	this.irbApprovalButtonId = "studySites["  + index + "].irbApprovalDate-calbutton";
            	this.siteInputId = this.sitePropertyName + "-input";
            	if(orgName) $(this.siteInputId).value = orgName;
            	AE.createStandardAutocompleter(this.sitePropertyName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            	Calendar.setup({inputField:this.irbApprovalInputId,ifFormat:"%m/%d/%Y",button:this.irbApprovalButtonId});
            },            
            sitePopulator: function(autocompleter, text) {
         		createStudy.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteSelector: function(organization) { 
        		return organization.name 
        	}
        	
    });
    
    Event.observe(window, "load", function() {
    	<c:forEach varStatus="status" items="${command.studySites}" var="ss">
      		new jsStudySite(${status.index}, '${ss.organization.name}');
      	</c:forEach>
      	addSiteEditor = new ListEditor('ss-section',createStudy, "StudySite",{
      		 addButton: "xxx",
             addIndicator: "ss-add-indicator",
             addFirstAfter: "sitebookmark",
             addCallback: function(nextIndex) {
                	//initilze auto completer and calendar
                	new jsStudySite(nextIndex);
             }
      	});
      	
    });

</script>
</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" formName="studySiteForm" hideErrorDetails="true">
    <jsp:attribute name="repeatingFields">
   	
		<p id="instructions">&nbsp;&nbsp;
		Click on the Add Study Site button below in order to associate a study site to this study.
		 <br>
		</p>
		<div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
		</div>
		<c:forEach varStatus="status" items="${command.studySites}">	
			<study:aStudyChild title="Study Site ${status.index + 1}" enableDelete="${status.index > 0}"
			    sectionClass="ss-section" removeButtonAction="removeSite" index="${status.index}" />
		</c:forEach>
			<span id="sitebookmark"></span>
    </jsp:attribute>
    <jsp:attribute name="localButtons">
    	<input type="button" onClick="javascript:fireAction('addSite','0');" 
     		name="AddStudySite" value="Add Study Site"><tags:indicator id="ss-add-indicator"/>
    </jsp:attribute>
</tags:tabForm>
</body>
</html>
