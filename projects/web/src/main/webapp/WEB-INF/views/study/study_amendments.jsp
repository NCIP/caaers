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
	var listEditor;
	var jsAmendment = Class.create();
	Object.extend(jsAmendment.prototype, {	
        initialize: function(index) {
        	this.index = index;
        	this.propertyBase = "studyAmendments[" + index + "]";
        	this.amendmentDateInputId =  this.propertyBase + ".amendmentDate"
        	this.amendmentDateButtonId = this.propertyBase + ".amendmentDate-calbutton"
        	this.irbApprovalInputId = this.propertyBase + ".irbApprovalDate";
            this.irbApprovalButtonId = this.propertyBase + ".irbApprovalDate-calbutton";
			//initialize the date picker.
			Calendar.setup({inputField:this.amendmentDateInputId,ifFormat:"%m/%d/%Y",button:this.amendmentDateButtonId});
			Calendar.setup({inputField:this.irbApprovalInputId,ifFormat:"%m/%d/%Y",button:this.irbApprovalButtonId});
		}
	});
    
    function fireDelete(selected, trClass){
		var frm = document.getElementById('command');
		frm._target.name='_noname';
		frm._action.value='removeAmendment';
		frm._selected.value=selected;		
		frm.submit();
	}
	
  
    Event.observe(window, "load", function() {
        	
      		<c:forEach varStatus="status" items="${command.studyAmendments}" var="amendment">
        		new jsAmendment(${status.index});
      		</c:forEach>
      		
      		//This is added for Add Sysetem Identifiers button
		    new ListEditor("amendment-row", createStudy, "StudyAmendment", {
		    	addFirstAfter: "amendment-table-head",
		        addCallback: function(newIndex) {
					new jsAmendment(newIndex);
            	}
		    });
   });
	
  </script>
   
 </head>
<body>
  <study:summary />
  <tags:tabForm tab="${tab}" flow="${flow}" formName="studyAmendmentFrm" hideErrorDetails="true">
    <jsp:attribute name="instructions">
    <p> Amendment can be added to this study by clicking the Add Amendment button.<br>
    </p>
    </jsp:attribute>
    <jsp:attribute name="singleFields">
	   <input type="hidden" name="_action" value="">
	   <input type="hidden" name="_selected" value="">
	   <table width="100%" class="tablecontent">
		  <tr id="amendment-table-head" class="amendment-table-head">
			<th width="10%" class="tableHeader">Version #</th>
			<th width="22%" class="tableHeader">Date</th>
			<th width="22%" class="tableHeader"><tags:requiredIndicator /> IRB Approval Date</th>
			<th width="41%" class="tableHeader">Comments</th>
			<th width="5%" class="tableHeader">&nbsp;</th>
 		  </tr>
 		  <c:forEach items="${command.studyAmendments}" varStatus="status" >
 		   <study:oneStudyChildRow  index="${status.index}" cssClass="amendment-row" />
 		  </c:forEach>
 	   </table>
    </jsp:attribute>
	<jsp:attribute name="localButtons"> 
	  <tags:listEditorAddButton divisionClass="amendment-row" 	label="Add Amendment" />   
	</jsp:attribute>
  </tags:tabForm>
</body>
</html>
