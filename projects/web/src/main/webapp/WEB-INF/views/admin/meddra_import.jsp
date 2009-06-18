<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/css/extremecomponents.css"/>">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Import MedDRA Dictionary</title>

<tags:dwrJavascriptLink objects="importMeddra" />
<style type="text/css">
        td.display {
	        background-color: white;
        }
        
    </style>
<script>

var t= 0 ;

	function handleGetData(step){
 	 	call(step)
	}
	
	function call(i){
		var temp = i + ''
		$(temp + '-div').style.display=""
		$(temp +'-indicator').className=''
		importMeddra.handleMedDRA($('meddra_path').value, 
								  i,$('input').value,
								  function(str) {
								  	  if(str != 'Done'){
								  	  	clearStatusLines();
								  	  	var tmp = 11 + '';
								  	  	$(tmp + '-div').style.display = "";
								  	  	$(tmp + '-result').innerHTML=str;
								  	  	$(tmp + '-result').style.display = '';
								  	  	return;
								  	  }
									  var message = "<b> " + str + "</b>" 
									  $(temp+'-indicator').className='indicator'
									  $(temp+'-done').innerHTML=message;
									  $(temp+'-done').style.display="";
									  t= t +1; 
									  if( t < 8){
									  	handleGetData(t);
									  }else{
									  	var tmp = 10 + '';
									  	$(tmp + '-div').style.display = "";
									  	clearStatusLines();
									  	$('import').style.display='none';
									  }
									  
									  });
	}

	function clearStatusLines(){
		for(var i = 0; i < 8; i++){
			var tmp = i + '';
			$(tmp + '-div').style.display = 'none'
		}
	}
	
	function toggle(in_id){
		// Clear all divs
		clearStatusLines();
		for(var i = 8; i < 13; i++){
			$(i + '-div').style.display = 'none';
		}
		
		// check if the meddra name is entered
		if($('input').value == ''){
			$(12 + '-div').style.display = '';
		}else{
			$(12 + '-div').style.display = 'none';
			Effect.toggle(in_id, 'appear');
			$('type').value = in_id;
		}
	}

	function showIf(){
		if ( $('type').value == "import" ){
			$('import').style.display = ""
		}
	}
	
	function deleteMeddra(versionId, versionName){
		// Clear the div corresponding to the meddra files status
		clearStatusLines();
		// Clear other div sections
		for(var d = 8; d < 13; d++){
			d = d + '';
			$(d + '-div').style.display = 'none';
		}
		$('import').style.display = 'none';
		
		var temp = 8 + ''
		$(temp + '-div').style.display="";
		$(temp +'-msg').innerHTML='<b>Deleting ' + versionName + ' :</b>';
		$(temp +'-msg').style.display="";
		$(temp +'-result').style.display='none';
		var image = document.getElementById('8-indicator');
		image.className=''
		importMeddra.handleMedDRADelete(versionId, 
								  function(str) {
								  	  var row = document.getElementById(versionName);
								  	  $(temp + '-indicator').className='indicator';
									  if(str == "Success"){
									  		row.style.display = 'none';
									  		$(temp + '-result').innerHTML="<font color=\"green\"><b>Successfully deleted " + versionName + ".</b></font>";
									  		$(temp + '-result').style.display="";
									  		$(temp + '-msg').style.display = 'none';
									  }else{
									  		$(temp + '-result').innerHTML="<font color=\"red\"><b>Deletion of " + versionName + " Failed. Please verify if there are studies using this version.</b> </font>";
									  		$(temp + '-result').style.display="";
									  		$(temp + '-msg').style.display = 'none';
									  }								  
									  });
		
	}
	
	function testPath(){
	var temp = 9;
		clearStatusLines();
		$('11-result').style.display = 'none';
		if($('meddra_path').value == ''){
			$(temp + '-div').style.display = '';
		}else{
			$(temp + '-div').style.display = 'none';
			call(0)
		}
	}
	
    Event.observe(window, "load", function(){
		showIf()
		Event.observe("import_button","click",function() { testPath() })
		Event.observe("create_button","click",function() { toggle('import') })
		       
    	}           
    )
	
</script>
</head>
<body>
	<chrome:box title="Import MedDRA Dictionary" autopad="true">
   <p>
    <tags:instructions code="importmeddra" />
   </p>
		<table id="test" class="tablecontent">
    		<tr id="organization-section">
    			<th class="tableHeader" width=300>Version Name</th>
    			<th class="tableHeader" width=100>Action</th>
    		</tr>
    		
    		<c:forEach items="${meddraVersions}" var="meddraVersion">
			<tr id="${meddraVersion.name}">
				<td><c:out value="${meddraVersion.name}"/></td>
				<td><tags:button id="${meddraVersion.id}" type="button" value="Delete" size="small" color="blue" icon="x" onclick="deleteMeddra('${meddraVersion.id}', '${meddraVersion.name}');"/><br><br></td>
			</c:forEach>
			<tr>
				<td>
					<input type="text" id="input" />
				</td>
				<td>
                    <tags:button id="create_button" type="button" value="Create" size="small" color="blue" icon="add"/><br><br>
				</td>
			</tr>
        </table>
        <br>
        <div style="display: none" id = "import">
        <b>MedDRA Files Folder:</b><br>
    	<input id="meddra_path" type="text" />&nbsp;&nbsp;&nbsp;
		<input id="import_button" type="button" value="Import" />
		</div>
        <div style="display: none;position:relative;top:-30px; left:200px;" id = "meddra">
    		<input id="meddra_file" type="file" name="meddraFile" />
			<input id="type" type="hidden" name="type" />
		</div>
        <div id="0-div" style="display:none">
			Importing System Organ Classes :
			<tags:indicator id="0-indicator" />
			<span id="0-done" style="display:none">Done</span>
		</div>
		<div id="1-div" style="display:none">
			Importing High Level Group Terms :
			<tags:indicator id="1-indicator" />
			<span id="1-done" style="display:none">Done</span>
		</div>
		<div id="2-div" style="display:none">
			Importing System Organ Class & High Level Group Term relationships :
			<tags:indicator id="2-indicator" />
			<span id="2-done" style="display:none">Done</span>
		</div>
		<div id="3-div" style="display:none">
			Importing High Level Terms :
			<tags:indicator id="3-indicator" />
			<span id="3-done" style="display:none">Done</span>
		</div>
		<div id="4-div" style="display:none">
			Importing High Level Group Term & High Level Term relationships :
			<tags:indicator id="4-indicator" />
			<span id="4-done" style="display:none">Done</span>
		</div>
		<div id="5-div" style="display:none">
			Importing Preferred Terms :
			<tags:indicator id="5-indicator" />
			<span id="5-done" style="display:none">Done</span>
		</div>
		<div id="6-div" style="display:none">
			Importing High Level Term & Preferred Term relationships :
			<tags:indicator id="6-indicator" />
			<span id="6-done" style="display:none">Done</span>
		</div>
		<div id="7-div" style="display:none">
			Importing Low Level Terms :
			<tags:indicator id="7-indicator" />
			<span id="7-done" style="display:none">Done</span>
		</div>
		<!-- This div is for displaying the status of delete action -->
		<div id="8-div" style="display:none">
			<span id="8-msg" style="display:none"></span>
			<tags:indicator id="8-indicator" />
			<span id="8-result" style="display:none"></span>
		</div>
		<!-- This div is for displaying error message incase the path is blank -->
		<div id="9-div" style="display:none">
			<br>
			<font color="red"><b>Please paste the path of the MedDRA files in the input </b></font> 
		</div>
		<!-- This div is for displaying the success status of import action -->
		<div id="10-div" style="display:none">
			<font color="green"><b>Import Successful.</b></font>&nbsp;&nbsp;
			<input id="import_button" type="button" value="Refresh" onClick="javascript:window.location='importMeddra';"/>
		</div>
		<!-- This div is for displaying the failure status of import action -->
		<div id="11-div" style="display:none">
			<br>
			<font color="red"><b><span id="11-result" style="display:none"></span></b></font>
		</div>
		<!-- This div is for displaying error message incase new version name is blank -->
		<div id="12-div" style="display:none">
			<font color="red"><b>Please enter the version name.</b></font>
		</div> 
	</chrome:box>
	
</body>