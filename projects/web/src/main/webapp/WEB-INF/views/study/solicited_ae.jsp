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
 	
 	.sae .deletecol {
		padding-left: 2em;
	    padding-right: 2em;

 	}
 	
	.sae th.term {
		width: 22em;
		height : 3em;
		vertical-align: CENTER;
	    horizontal-align: center;
	}
	.sae th.epoch {
		width: 10em;
		vertical-align: top;
	}
	.sae th.action {
		border-width:1px 1px 1px 1px;
		border-color:#6E81A6;
	 	border-style:solid;
	 	color: black;
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

	
	.sae .NoRows {
		font-style: normal;
		text-align: center;
		padding-top: 2em;
	    padding-bottom: 2em;
		
		color:red;
		
		
	}
	
  </style>
  
  <script type="text/javascript">
   Event.observe(window, 'load', function() {
     Event.observe('flow-prev', 'click', checkForm);
     Event.observe('flow-next', 'click', checkForm);
     Event.observe('flow-update', 'click', checkForm);
     
     var listOfTermIds = updateTermIds();
     var termIDArray = $A(listOfTermIds);
     termIDArray.each( registerDeleteButtons );
   }); 
    
    function registerDeleteButtons(termID)
    {
      Event.observe("button-"+termID.value,'click',handleDelete);
    }
    
    function updateTermIds()
    {
	     var listOfTermIds = $$('.eachRowTermID');
	     if(listOfTermIds.length != 0){
	       Element.hide('NoRows');
	     }
	     else
	     {
	       Element.show('NoRows');
	     }
	     $('err-section').innerHTML = "";
	     return listOfTermIds;
    
    }
    function handleDelete(event)
    {
                var buttonid = Event.element(event).id;
                var trid = buttonid.gsub(/button/,'tr');
                if(!confirm( "Are you sure you want to delete this?" ))
                  return false;   
                Element.remove(trid);
                updateTermIds(); 
    }
    function checkForm(event)
    {
      var listOfTermIds = $$('.eachRowTermID');
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
        var ckbox1 = "ck1-"+listOfTermIds[i].value;
        var ckbox2 = "ck2-"+listOfTermIds[i].value;
        var ckbox3 = "ck3-"+listOfTermIds[i].value;
        if( !$(ckbox1).checked && !$(ckbox2).checked && !$(ckbox3).checked )
        {
          $('err-section').innerHTML += '<li>Please select at least one checkbox for Solicited Adverse Event ' + $("name-"+listOfTermIds[i].value).innerHTML + '</li>';
          Event.stop(event);
        }
      }
    }

    function isTermAgainAdded( termID )
    {
      var listOfTermIds = $$('.eachRowTermID');
      for(var i = 0 ; i < listOfTermIds.length ; i++)
      {
        if( termID == listOfTermIds[i].value)
        {
          return true;
        }      
      } 
      return false;
    }
    
  	function myCallback(selectedTerms){
  	
	    var listOfTermIDs = new Array();
	    var listOfTerms = new Array();
	  	
	  		$H(selectedTerms).keys().each( function(termID) {
	  		
	  		var term = $H( selectedTerms ).get(termID);
	  		if( !isTermAgainAdded(termID))
	  		{
	  		  listOfTermIDs.push( termID );
	  		  listOfTerms.push(term );
	        }
	  	   });
	  
	   	createStudy.addSolicitedAE(listOfTermIDs, listOfTerms, function(responseStr)
	   	{
	   	
   	      var listOfTermIds = $$('.eachRowTermID');
          var termIDArray = $A(listOfTermIds);
          termIDArray.each( unRegisterDeleteButtons );
            new Insertion.Before('specialLastRow', responseStr);
   	      listOfTermIds = updateTermIds();
          termIDArray = $A(listOfTermIds);
          termIDArray.each( registerDeleteButtons );
		  
	   	});

    function unRegisterDeleteButtons(termID)
    {
      Event.stopObserving("button-"+termID.value,'click',handleDelete);
    }
    
   	
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
		<p>
		  <ul id="err-section" class="errors">
         </ul>
		  
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
    			<tr id="specialLastRow" class="bottom" >
    				<td colspan="5" align='center'><span id='NoRows' class='NoRows'>You have no solicited adverse events added in the list !</span></td>
    			</tr>			
  			</tbody>
  			
  		</table>	
	  		<!--  end of body  -->
  		<tags:tabControls tab="${tab}" flow="${flow}" />
  	</chrome:box>
  </form:form>
 
</body>
</html>
