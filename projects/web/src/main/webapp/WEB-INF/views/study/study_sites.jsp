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
	
	function fireDelete(selected, trClass){
		var ssfrm = $('command');
		ssfrm._target.name='_noname';
		ssfrm._action.value='removeSite';
		ssfrm._selected.value=selected;		
		ssfrm.submit();
	}
	
	function refreshDeleteButtons(){
	  var deleteBtns = $$('.del-ss-section');
	  if(deleteBtns.length > 1){
	  	//deleteBtns[0].enable();
	  	deleteBtns[0].show();
	  }
	  if(deleteBtns.length == 1){
	  	//deleteBtns[0].disable();
	  	deleteBtns[0].hide();
	  }
	  
	}
	
	var jsStudySite = Class.create();
	Object.extend(jsStudySite.prototype, {
            initialize: function(index, orgName) {
            	this.index = index;
            	this.orgName = orgName;
            	this.sitePropertyName = "studySites["  + index + "].organization";
            	this.siteInputId = this.sitePropertyName + "-input";
            	if(orgName) $(this.siteInputId).value = orgName;
            	AE.createStandardAutocompleter(this.sitePropertyName, 
            		this.sitePopulator.bind(this),
            		this.siteSelector.bind(this)
            	);
            },            
            sitePopulator: function(autocompleter, text) {
         		createStudy.matchOrganization(text, function(values) {
         			autocompleter.setChoices(values)
         		})
        	},
        	
        	siteSelector: function(organization) {
        		 var nciInstituteCode = organization.nciInstituteCode == null ? "" : 
            							 " ( " + organization.nciInstituteCode + " ) ";
			   return organization.name + nciInstituteCode   
        	}
        	
    });
    
    Event.observe(window, "load", function() {
    	<c:forEach varStatus="status" items="${command.studySites}" var="ss">
      		new jsStudySite(${status.index}, '${ss.organization.fullName}');
      	</c:forEach>
      	addSiteEditor = new ListEditor('ss-section',createStudy, "StudySite",{
             addFirstAfter: "ss-table-head",
             addCallback: function(nextIndex) {
                	//initilze auto completer and calendar
                	new jsStudySite(nextIndex);
                	refreshDeleteButtons();
             }
      	});
      	
      	//enable-disable delete buttons
      	refreshDeleteButtons();
      	
    });
	
</script>
<!--[if IE]>
<style>
#thirdlevelnav{
	margin:5px;
	font-size:9pt;
}
</style>
<![endif]-->
</head>
<body>
<study:summary />
<tags:tabForm tab="${tab}" flow="${flow}" formName="studySiteForm" hideErrorDetails="false">
    <jsp:attribute name="singleFields">
		<p><tags:instructions code="study.study_sites.top" /></p>
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
 	    <div align="left" style="margin-left: 50px">
 	    <table width="55%" class="tablecontent">
		  <tr id="ss-table-head" class="amendment-table-head">
			<th width="95%" class="tableHeader"><tags:requiredIndicator />Site</th>
			<th width="5%" class="tableHeader" style=" background-color: none">&nbsp;</th>
 		  </tr>
 		  <c:forEach varStatus="status" items="${command.studySites}">	
 		   <study:oneStudyChildRow cssClass="ss-section" index="${status.index}" />
 		  </c:forEach>
		</table>
 	    </div>
		<br>
    </jsp:attribute>
    <jsp:attribute name="localButtons"> 
	  <tags:listEditorAddButton divisionClass="ss-section" label="Add Study Site" />   
	</jsp:attribute>

</tags:tabForm>
</body>
</html>
