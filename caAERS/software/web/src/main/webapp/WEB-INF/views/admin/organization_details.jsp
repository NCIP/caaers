<%@ include file="/WEB-INF/views/taglibs.jsp"%>

<html>
<head>
<title>Create Organization</title>

 	
 	<script type="text/javascript">
 		function displayRemoteOrgs(){
 			var contentWin = new Window({className:"alphacube", destroyOnClose:true, id:"remoteorg-popup-id", width:550,  height:200, top: 30, left: 300});
 			contentWin.setContent( 'display_remote_org' );
 	        contentWin.showCenter(true);
 	       popupObserver = {
 	      			onDestroy: function(eventName, win) {
 	      				if (win == contentWin) {
 	      					$('display_remote_org').style.display='none';
 	      					
 	      					contentWin = null;
 	      					Windows.removeObserver(this);
 	      				}
 	      			}
 	      		}
 	        Windows.addObserver(popupObserver);
 		}


 		Event.observe(window, "load", function(){
 			if(${fn:length(command.externalOrganizations) gt 0}){
 				displayRemoteOrgs();
 			}
 			
 		});

		function submitRemoteOrgForSave(){
			var form = document.getElementById('command');
			form._action.value="saveRemoteOrg";
			form.submit();
		}

		function selectOrg(selectedIndex){
			var form = document.getElementById('command')
			form._selected.value=selectedIndex;
			document.getElementById('save-yes').disabled = false;
		}
		
		function syncOrganization(){
			var form = document.getElementById('command');
			form._action.value="syncOrganization";
			form.submit();
		}

 	</script>
 	
</head>

<body>

<div id="display_remote_org" style="display:none;text-align:left" >
	<chrome:box title="Please select an Organization to be saved in caAERS" id="popupId">
		<div class="eXtremeTable">
          <table width="100%" border="0" cellspacing="0"  class="tableRegion">
            <thead>
              <tr align="center" class="label">
              	<td/>
                <td class="tableHeader">Organization Name</td>
                <td class="tableHeader">Assigned Identifier</td>
              </tr>
            </thead>
            <c:forEach items="${command.externalOrganizations}"  var="remOrg" varStatus="rdStatus">
              <tr>
              	<td><input type="radio" name="remoteorgradio" value=${rdStatus.index} id="remoteorg-radio" onClick="javascript:selectOrg('${rdStatus.index}');"/></td>
                <td align="left">${remOrg.name}</td>
                <td align="left">${remOrg.nciInstituteCode}</td>
              </tr>
            </c:forEach>
          </table>
		</div>
		<br><br>
   		<table width="100%">	
   			<tr>
   				<td align="left">
   					<input type="submit" value="Cancel" id="save-no" onClick="javascript:window.parent.Windows.close('remoteorg-popup-id');"/>
   				</td>
   				<td align="right">
    				
    				<input type="submit" disabled value="Ok" id="save-yes" onClick="javascript:window.parent.submitRemoteOrgForSave();"/>
   				</td>
   			<tr>	
   		</table>
	</chrome:box>
</div>

<div class="tabpane">

  <div class="workflow-tabs2">
  <ul id="" class="tabs autoclear">
    <li id="thirdlevelnav" class="tab selected"><div>
        <a href="createOrganization">Create/Edit Organization</a>
    </div></li>
    <li id="thirdlevelnav" class="tab"><div>
        <a href="searchOrganization">Search Organization</a>
    </div></li>
  </ul>
    </div>      

 


<tags:tabForm tab="${tab}" flow="${flow}"  formName="organizationForm">
		 <jsp:attribute name="singleFields">
		 	<tags:instructions code="organizationdetails" />
            <div>
				<input type="hidden" name="_action" value="">
				<input type="hidden" name="_selected" value="">
			</div>
		    <input type="hidden" name="_finish" value="true"/>
		
    		<c:forEach  items="${fieldGroups.organization.fields}" var="field">
            	<tags:renderRow field="${field}"/>
            </c:forEach>
            
            <c:if test="${(command.id gt 0) }">
            	<div class="row">
            		<div class="value">
            			<input type="submit" value="Sync" id="sync-org" onClick="javascript:syncOrganization();"/>
            		</div>
            	</div>
            </c:if>
                	
        </jsp:attribute>
    	
    
</tags:tabForm>
 </body>
</html>
