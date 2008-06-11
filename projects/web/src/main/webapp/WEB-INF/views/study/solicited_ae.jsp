<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="study" tagdir="/WEB-INF/tags/study" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <title>${tab.longTitle}</title>
  <tags:includeScriptaculous/>
  <tags:includePrototypeWindow />
  <tags:dwrJavascriptLink objects="createStudy"/>
  
  <script language="JavaScript" type="text/JavaScript">

  </script>
  <tags:stylesheetLink name="pw_default" />
  <tags:stylesheetLink name="pw_alphacube" />
  
  <style type="text/css">
  	
  	.sae, .query {
		border-spacing: 0;
		border-collapse: collapse;
 		margin: 1em 2em;
 	}
 	.query td.one {
 		padding-right: 2em;
 	}
 	.query td.three {
 	padding-left: 2em;
 	}
 	.query td.two {
 	 border-color:#6E81A6;
	 border-style:solid;
	 border-width:0px 1px 0px 1px;
	 height : 1em;
	 padding-left: 1em;
	 padding-right: 1em;
	 
 	}
 	
 	.sae col.term {
 		text-align: center;
 	}
 	
 	.sae col.epoch {
 		text-align: center;
 	}
 	
	.sae th.term {
		width: 22em;
		height : 3em;
		vertical-align: bottom;
		text-align: left;
	}
	.sae th.epoch {
		width: 10em;
		vertical-align: top;
	}
  	tr.head th.epoch, .sae tr.data td {
	 border-color:#6E81A6;
	 border-style:solid;
	 border-width:0px 1px 0px 1px;
	 height : 2em;
	}
	
	tr.head {
		border-width:1px 0px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	
	}
	tr.bottom {
		border-width:0px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	
	}
	.sae .inst {
		font-style: normal;
		font-weight: normal;
	}
	
  </style>
  
  <script type="text/javascript">
  	function myCallback(selectedTerms){
  	
    var listOfTermIDs = new Array();
    var listOfTerms = new Array();
  	
  		$H(selectedTerms).keys().each( function(termID) {
  		
  		var term = $H( selectedTerms ).get(termID);
  		
  		listOfTermIDs.push( termID );
  		listOfTerms.push(term );

  	   });
  
   	createStudy.addSolicitedAE(listOfTermIDs, listOfTerms, function(responseStr)
   	{
   	  new Insertion.Before('specialLastRow', responseStr);
   	});
   	
   	
 
  	}
  </script>
</head>
<body>
 
  <study:summary />
 
  <form:form name="solicitedAEForm">
  	
  	<tags:aeTermQuery title="Choose CTC terms" isMeddra="${not empty command.aeTerminology.meddraVersion}"  callbackFunctionName="myCallback" version="${not empty command.aeTerminology.meddraVersion ? command.aeTerminology.meddraVersion.id : command.aeTerminology.ctcVersion.id}" />
  	
  	<!--  Idea is copied from tabForm.tag -->
  	<chrome:box title="${tab.shortTitle}" >
  		<chrome:flashMessage/>
  		<tags:tabFields tab="${tab}"/>
  		<tags:hasErrorsMessage />
  		<p id="instructions">
			&nbsp;&nbsp;Check the boxes under the epoch/treatment type, to associate the term to it.
		</p>
		<input type="hidden" name="_action" value="">
  		<!--  start of body -->
  		
  		
  		<table id="sae-0" class="sae">
  			<col class="term"/>
    		<colgroup>
		      <col class="epoch"/>
		      <col class="epoch"/>
       		  <col class="epoch"/>
    		</colgroup>
    		<col class="action"/>
    		<tbody>
    			<tr class="head">
        			<th class="term">Adverse event term</th>
		            <th class="epoch">
		            	<div class="index">Pre-treatment</div>
		            	<div class="inst">Instruction</div>
            		</th>
            		<th class="epoch">
                		<div class="index">Treatment</div>
                		<div class="inst">Instruction</div>
            		</th>
            		<th class="epoch">
                		<div class="index">Post-treatment</div>
                		<div class="inst">Instruction</div>
            		</th>
            		<th class="action"> </th>
    			</tr>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
    			<tr id="specialLastRow" class="bottom">
    				<td colspan="5"></td>
    			</tr>			
  			</tbody>
  			
  		</table>	
	  		<!--  end of body  -->
  		<tags:tabControls tab="${tab}" flow="${flow}" />
  	</chrome:box>
  </form:form>
 
</body>
</html>
