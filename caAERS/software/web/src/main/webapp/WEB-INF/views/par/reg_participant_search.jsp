<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
    <title>Search for a Subject</title>
    <tags:dwrJavascriptLink objects="createParticipant"/>

    <style>
        .yui-pg-page { padding: 5pt; }
        .yui-dt-label .yui-dt-sortable { color: white; }
        .yui-dt table { width: 100%; }
        div.yui-dt-liner a {color : black;}

        tr.yui-dt-even { background-color: #FFF;}
        tr.yui-dt-odd { background-color: #EDF5FF;}
               
    </style>
    
    <script type="text/javascript">

        function navRollOver(obj, state) {
            document.getElementById(obj).className = (state == 'on') ? 'resultsOver' : 'results';
        }
        
        function onKey(e) {
            var keynum = getKeyNum(e);

            if (keynum == 13) {
                Event.stop(e);
                buildTable('assembler');
            } else return;
        }

        function buildTable(form) {

            var text = $F('searchText');

            if (text == '') {
                $('error').innerHTML = "<font color='#FF0000'>Provide at least one character in the search field.</font>";
            } else {
                $('indicator').show();
                
                $('error').innerHTML = ""
                $('indicator').className = ''
                var type = $('searchType').options[$('searchType').selectedIndex].value;

                var parameterMap = getParameterMap(form);
                createParticipant.getParticipantTable(parameterMap, type, text, test)

                $('bigSearch').show();

            }
        }

        function selectParticipant(selectedParticipant){
             $('command').participant.value = selectedParticipant;
        }

        function test(jsonResult) {
            $('indicator').className='indicator'
            //document.getElementById('tableDiv').innerHTML = jsonResult;
            initializeYUITable("tableDiv", jsonResult, myColumnDefs, myFields);
            hideCoppaSearchDisclaimer();
        }

        var linkFormatter = function(elCell, oRecord, oColumn, oData) {
                var orgId = oRecord.getData("id");
                elCell.innerHTML = "<a href='asaelEdit?agentID=" + orgId + "'>" + oData + "</a>";
        };

        var radioFormatter = function(elCell, oRecord, oColumn, oData) {

                var _ss = 0;
                var _checked = "";
                var _id = oRecord.getData("id");

                <c:if test="${command.participant.id > 0}">
                    _ss =  ${command.participant.id};
                </c:if>

                if (_ss == _id) {
                    _checked = "checked";
                }

                elCell.innerHTML = "<input " + _checked + " type=\"radio\" onclick=\"selectParticipant(this.value)\" value=\"" + _id + "\" id=\"participant" + _id + "\" name=\"participant\">&nbsp;" + oData;
        };

        var myColumnDefs = [
            {key:"primaryIdentifierValue", label:"Primary ID", sortable:true, resizeable:true, formatter: radioFormatter},
            {key:"firstName", label:"First Name", sortable:true, resizeable:true},
            {key:"lastName", label:"Last Name", sortable:true, resizeable:true},
            {key:"studySubjectIdentifiersCSV", label:"Study Subject Identifiers", sortable:true, resizeable:true},
            {key:"gender", label:"Gender", sortable:true, resizeable:true},
            {key:"race", label:"Race", sortable:true, resizeable:true},
            {key:"ethnicity", label:"Ethnicity", sortable:true, resizeable:true}
        ];

        var myFields = [
            {key:'id', parser:"string"},
            {key:'firstName', parser:"string"},
            {key:'lastName', parser:"string"},
            {key:'primaryIdentifierValue', parser:"string"},
            {key:'studySubjectIdentifiersCSV', parser:"string"},
            {key:'gender', parser:"string"},
            {key:'race', parser:"string"},
            {key:'ethnicity', parser:"string"}
        ];

    </script>


</head>
<body>


  <form:form id="searchForm" method="post" cssClass="standard">
        <tags:hasErrorsMessage hideErrorDetails="${hideErrorDetails}"/>
        <tags:jsErrorsMessage/>
      
		<table border="0" cellspacing="2" cellpadding="2" class="search" width="100%">
        <p><tags:instructions code="instruction_subject_as2s.searchsub"/></p>
        <tr>
            
            <td class="searchType">Search for subject by</td>
            <td>
                <form:select path="searchType">
                    <form:options items="${participantSearchType}" itemLabel="desc" itemValue="code"/>
                </form:select>
            </td>
            <td><form:input path="searchText" id="searchText" size="30" onkeydown="onKey(event);"/></td>
            <c:set var="targetPage" value="${assignType == 'study' ? '_target1' : '_target0'}"/>
            <td width="100%">
                <tags:button color="blue" type="button" value="Search" size="small" icon="search" onclick="buildTable('assembler');"/>
                <img src="<c:url value="/images/alphacube/progress.gif" />" style="display:none;" id="indicator">
            </td>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td class="notation">
                <div id="error"></div>
            </td>

        </tr>

    </table>
  </form:form>

<div id="bigSearch" style="display:none;">
    <form:form id="assembler">
        <div>
            <input type="hidden" name="_prop" id="prop">
            <input type="hidden" name="_value" id="value">
        </div>
        <chrome:box title="Results">
            <p><tags:instructions code="instruction_subject_as2s.searchsubresults"/></p>
            <chrome:division id="single-fields">
                <div id="tableDiv">
                    <c:out value="${assembler}" escapeXml="false"/>
                </div>
            </chrome:division>
        </chrome:box>
    </form:form>
</div>

  <script>
      Event.observe(window, "load", function(){
          buildTable('assembler', false);
      })
  </script>
  

<form:form id="command">
	    <form:hidden path="participant"/>
     <tags:tabFields tab="${tab}"/>
     <tags:tabControls tab="${tab}" flow="${flow}"/>
</form:form>

</body>
</html>

<!-- END views\par\reg_participant_search.jsp -->
