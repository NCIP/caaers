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

	.lastRowValue {
	 font-style: normal;
		text-align: center;
		color:red; 
	}
	
  </style>
  
  <script type="text/javascript">
  
   var setOfWindowsOpened = new Array();
  
   Event.observe(window, 'load', function() {
     Event.observe('flow-prev', 'click', checkForm);
     Event.observe('flow-next', 'click', checkForm);
     Event.observe('flow-update', 'click', checkForm);
     
     Event.observe('epochs[0].descriptionText-id', 'click', editInstruction);
     Event.observe('epochs[1].descriptionText-id', 'click', editInstruction);
     Event.observe('epochs[2].descriptionText-id', 'click', editInstruction);
     
     Event.observe('ck1', 'click', selectAll);
     Event.observe('ck2', 'click', selectAll);
     Event.observe('ck3', 'click', selectAll);
     
    
     var listOfTermIds = updateTermIds();
     var termIDArray = $A(listOfTermIds);
     termIDArray.each( registerDeleteButtons );
   }); 
    
    function selectAll(event)
    {
       var selectall_ckbox = Event.element(event).id;
       var all_ckboxes = $$('.'+selectall_ckbox);
       for(var i = 0 ; i < all_ckboxes.length ; i++)
       {
         if( $(selectall_ckbox).checked )
          $(all_ckboxes)[i].checked = true;
         else
          $(all_ckboxes)[i].checked = false;  
       }   
	     
    }
    function editInstruction(event)
    {
      var event_id = Event.element(event).id;
      var div_id = event_id.gsub(/(\w+)-id/,'#{1}-div');
      var window_id = "window-"+div_id;
      
      if( setOfWindowsOpened.indexOf(window_id) != -1 )
      {
        return;
      }
      else
      {
        setOfWindowsOpened.push( window_id );
      }
      
      
      var xtop = getX(event_id,event);
      var xleft = getY(event_id,event);
      
      
      var win = new Window({ id: window_id , className: "alphacube", closable : false, minimizable : false, maximizable : false, title: "Edit Instruction here", height:200, width: 450, top: xtop, left: xleft});
      
      win.setDestroyOnClose(); 
      win.setContent( div_id );
      Event.observe(div_id + "-button",'click',function(event){
           
           setOfWindowsOpened = setOfWindowsOpened.without("window-"+div_id);
           Windows.close("window-"+div_id);
      });
      win.show();      
    }
    
    function getX(event_id, event)
    {
      var xtop = Event.pointerY(event);
      
       return xtop + 30;
    }
    
    function getY(event_id, event)
    {
      var xleft = Event.pointerX(event);
      
      if(event_id == 'epochs[0].descriptionText-id')
           xleft = 50;
      else if( event_id == 'epochs[1].descriptionText-id' )
           xleft =  Event.pointerX(event) - 60;         
      else if( event_id == 'epochs[2].descriptionText-id' )
           xleft =  Event.pointerX(event) + 300;         
       return xleft;
    }
    
    
    
    function registerDeleteButtons(termID)
    {
      Event.observe("button-"+termID.value,'click',handleDelete);
    }
    
    function updateTermIds()
    {
	     var listOfTermIds = $$('.eachRowTermID');
	     if(listOfTermIds.length != 0){
	      $('lastRowSpan').hide();
	     }
	     else
	     {
	       $('lastRowSpan').show();
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
		            	<div class="index"><tags:inplaceTextField propertyName="epochs[0].name" /></div>
		            	<div class="inst"><a href="#jumphere" id="epochs[0].descriptionText-id">Edit Instruction</a></div>
		            	<div><input id="ck1" type="checkbox" /></div>
		            	<tags:popupEditInstruction propertyName="epochs[0].descriptionText"></tags:popupEditInstruction>
  		                <a name="jumphere" />
            		</th>
            		<th class="epoch">
                		<div class="index"><tags:inplaceTextField propertyName="epochs[1].name" /></div>
                		<div class="inst"><a href="#jumphere" id="epochs[1].descriptionText-id">Edit Instruction</a></div>
                		<div><input id="ck2" type="checkbox" /></div>
		            	<tags:popupEditInstruction propertyName="epochs[1].descriptionText"></tags:popupEditInstruction>
  		    		</th>
            		<th class="epoch">
                		<div class="index"><tags:inplaceTextField propertyName="epochs[2].name" /></div>
                		<div class="inst"><a href="#jumphere" id="epochs[2].descriptionText-id">Edit Instruction</a></div>
                		<div><input id="ck3" type="checkbox" /></div>
		            	<tags:popupEditInstruction propertyName="epochs[2].descriptionText"></tags:popupEditInstruction>
  		    		</th>
            		<th class="action"> </th>
    			</tr>
    			 <c:forEach  varStatus="status" var="eachRow" items="${listOfSolicitedAERows}" >
    			    <study:oneSolicitedAERow index="${status.index}" eachRow="${eachRow}" />
    			 </c:forEach>
    			<tr id="specialLastRow" class="bottom" >
    				<td colspan="5" align='center'><span id='lastRowSpan' class='lastRowValue' style="display:none;">You have no solicited adverse events added in the list !</span></td>
    			</tr>			
  			</tbody>
  			
  		</table>	
	  		<!--  end of body  -->
  		<tags:tabControls tab="${tab}" flow="${flow}" />
  	</chrome:box>
  </form:form>
 
</body>
</html>
