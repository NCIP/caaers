<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
    <title>Import Studies</title>
<style type="text/css">
        div.label {
            width: 35%;
        }
        div.submit {
            text-align: right;
        }
        form {
            width: 20em;
        }
    </style>

	<script language="JavaScript" type="text/JavaScript">

	function validatePage(){
		return true;
	}
	function fireAction(action, selected){
		if(validatePage()){
							
			document.getElementsByName('_finish')[0].name='xyz';				            
			document.studySiteForm._action.value=action;
			document.studySiteForm._selected.value=selected;		
			document.studySiteForm.submit();
		}
	}
	function clearField(field){
		field.value="";
	}
	
	function populateHiddenFileName(){
		
		$("fileName").value = $("fileNamefake").value
		fireAction('addSite','0');
	
			
	}
	
	Event.observe(window, "load", function() {
		
		Event.observe("fileNamefake", "change", function() { populateHiddenFileName() })
	       
            }           
     
        )

</script>

</head>
<body> <br>
<p id="instructions">
        Import Studies/Protocols into caAERS
</p>
    
    <chrome:box autopad="true" title="Import Studies " id="studies">
    <form:form method="post" cssClass="standard" name="studySiteForm">
	<div>		
		<input type="hidden" name="_action" value="">
		<input type="hidden" name="_selected" value="">
		<input type="hidden" name="_finish" value="true">
	</div>
	
    	<tags:errors path="*"/>
    	<b> Please select a Study xml file to import </b>
    	<input id="fileName" type="hidden" name="fileName" />
    	<input id="fileNamefake" type="file" name="temp" />
    	
    	<br><br>
    	<b> Studies You will be importing </b>
    	<br>
    	
    	<table  width="60%" border="1" cellspacing="0" cellpadding="0">
		<br>

					<tr align="center">						
						<td> <b>Study Short Title</b></td>																	
					</tr>																			
				 
				    
					<c:forEach varStatus="status" var="study" items="${command.studies}">
								<tr class="results">						
									<td align="left"><c:out value="${study.shortTitle}"/></td>										
								</tr>
					</c:forEach>				
		</table>
        <input type="submit" value="Save"/>
        </form:form>
    </chrome:box>
</body>
</html>