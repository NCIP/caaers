


























<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <title>caAERS || Enter Study || Agents</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body>

<!-- ALL DIV start -->

<div id="all">





<!-- end header -->

<div class="tabpane">

<div id="main">
        <div id="main-no-summary-pane" class="pane">
<div class="box "
     >

    <!-- box header -->
    <div class="header"><div class="background-L"><div class="background-R">
      <h2>Agents</h2><div class="hr"></div>
    </div></div></div>
    <!-- end box header -->

    <!-- inner border -->
    <div class="border-T"><div class="border-L"><div class="border-R"><div class="border-B"><div class="border-TL"><div class="border-TR"><div class="border-BL"><div class="border-BR">
        <div class="interior">






    <form id="command" name="studyAgentsForm" method="post" action="/caaers/pages/study/create">

<input type="hidden" name="_target3" id="_target"/>
<input type="hidden" name="_page" value="2" id="_page"/>















    <div class="header" style="border-bottom:1px solid #CCCCCC; color:#2E3257; font-weight:bold; padding:4px 8px 1px;">

    </div>

    <div class="content" id="contentOf-single-fields" style="display:; padding:0px; margin:10px;">


            <p class="instructions">Click  "Add Study Agent" to add one or more agents to this study.</p>






            <div>
			<input type="hidden" name="_action" value="">
			<input type="hidden" name="_selected" value="">
			<input type="hidden" name="_selectedInd" value="">
			<input type="hidden" name="_selectedOther" value="">
		</div>

		<p id="instructions"></p>






























<div class="division sa-section" id="sa-section-0" >
    <div class="header" style="border-bottom:1px solid #CCCCCC; color:#2E3257; font-weight:bold; padding:4px 8px 1px;">





	   		Study Agent 1



    </div>
    <div class="content" id="contentOf-sa-section-0" style="display:; padding:0px; margin:10px;">


aaaaaa












<!-- |  studyAgents[0].agent | -->

<div class="row " id="studyAgents[0].agent-row" >
    <div class="label">

            <label>
                <input id="select-agent-0" name="agentOrOther0" type="radio"/>
                Agent
            </label>


    </div>
    <div class="value">















































































	<input size="70" type="text" id="studyAgents[0].agent-input" title="Agent"  value="Begin typing here..."
	class="autocomplete  "/>


<img id="studyAgents[0].agent-indicator" class="indicator" src="/caaers/images/indicator.white.gif" alt="activity indicator"/>

  <div id="studyAgents[0].agent-choices" class="autocomplete" style="display: none"></div>
  <input id="studyAgents[0].agent" name="studyAgents[0].agent" type="hidden" value=""/>














<!--code : studyAgents[0].agent msgtxt : NA -->













	<script>

   	AE.hash.set('studyAgents[0].agent' , '1');
    $('studyAgents[0].agent-input').observe('focus', function() {
        if($('studyAgents[0].agent').value == '' && AE.hash.get('studyAgents[0].agent') == '1'){
             var el = $('studyAgents[0].agent-input');
			 el.removeClassName('pending-search');
             el.clear();
			 AE.hash.set('studyAgents[0].agent' , '0');
		}
    });

    $('studyAgents[0].agent-input').observe('blur', function() {
			if(AE.hash.get('studyAgents[0].agent') == '0'){
                var el = $('studyAgents[0].agent-input');
                if (el.value == '') {
                el.value = 'Begin typing here...';
                el.addClassName('pending-search');

				AE.hash.set('studyAgents[0].agent' , '1');
			}
        };
    });

    if($('studyAgents[0].agent').value == ''){
		$('studyAgents[0].agent-input').addClassName('pending-search');
	}

	</script>





























    </div>

</div>














<!-- |  studyAgents[0].otherAgent | -->

<div class="row " id="studyAgents[0].otherAgent-row" >
    <div class="label">

            <label>
                <input id="select-other-0" name="agentOrOther0" type="radio"/>
                Other
            </label>


    </div>
    <div class="value">













        <input id="studyAgents[0].otherAgent" name="studyAgents[0].otherAgent" class="validate-MAXLENGTH2000" title="Other" type="text" value="" size="70"/>













































    </div>

</div>




















<!-- |  studyAgents[0].indType | -->

<div class="row " id="studyAgents[0].indType-row" >
    <div class="label">










         <label for="studyAgents[0].indType">






   <span id="studyAgents[0].indType-indicator">

   </span>
            &nbsp;Enter IND information
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[0].indType" name="studyAgents[0].indType" title="Enter IND information"><option value="NA">NA</option><option value="NA_COMMERCIAL">N/A-Commercial Agent</option><option value="IND_EXEMPT">IND-Exempt</option><option value="DCP_IND">DCP IND</option><option value="OTHER">Other IND Holder</option><option value="CTEP_IND">CTEP IND</option></select>




























    </div>

</div>
















<!-- |  studyAgents[0].partOfLeadIND | -->

<div class="row " id="studyAgents[0].partOfLeadIND-row" >
    <div class="label">










         <label for="studyAgents[0].partOfLeadIND">






   <span id="studyAgents[0].partOfLeadIND-indicator">

   </span>
            &nbsp;Lead IND ?
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[0].partOfLeadIND" name="studyAgents[0].partOfLeadIND" title="Lead IND ?"><option value="">Please select</option><option value="false">No</option><option value="true">Yes</option></select>




























    </div>

</div>






 <div id="local-buttons-0" class="local-buttons">

 </div>
 <br />

    </div>
</div>

<div class="division sa-section" id="sa-section-1" >
    <div class="header" style="border-bottom:1px solid #CCCCCC; color:#2E3257; font-weight:bold; padding:4px 8px 1px;">





	   		Study Agent 2



    </div>
    <div class="content" id="contentOf-sa-section-1" style="display:; padding:0px; margin:10px;">


aaaaaa












<!-- |  studyAgents[1].agent | -->

<div class="row " id="studyAgents[1].agent-row" >
    <div class="label">

            <label>
                <input id="select-agent-1" name="agentOrOther1" type="radio"/>
                Agent
            </label>


    </div>
    <div class="value">















































































	<input size="70" type="text" id="studyAgents[1].agent-input" title="Agent"  value="Begin typing here..."
	class="autocomplete  "/>


<img id="studyAgents[1].agent-indicator" class="indicator" src="/caaers/images/indicator.white.gif" alt="activity indicator"/>

  <div id="studyAgents[1].agent-choices" class="autocomplete" style="display: none"></div>
  <input id="studyAgents[1].agent" name="studyAgents[1].agent" type="hidden" value=""/>














<!--code : studyAgents[1].agent msgtxt : NA -->













	<script>

   	AE.hash.set('studyAgents[1].agent' , '1');
    $('studyAgents[1].agent-input').observe('focus', function() {
        if($('studyAgents[1].agent').value == '' && AE.hash.get('studyAgents[1].agent') == '1'){
             var el = $('studyAgents[1].agent-input');
			 el.removeClassName('pending-search');
             el.clear();
			 AE.hash.set('studyAgents[1].agent' , '0');
		}
    });

    $('studyAgents[1].agent-input').observe('blur', function() {
			if(AE.hash.get('studyAgents[1].agent') == '0'){
                var el = $('studyAgents[1].agent-input');
                if (el.value == '') {
                el.value = 'Begin typing here...';
                el.addClassName('pending-search');

				AE.hash.set('studyAgents[1].agent' , '1');
			}
        };
    });

    if($('studyAgents[1].agent').value == ''){
		$('studyAgents[1].agent-input').addClassName('pending-search');
	}

	</script>





























    </div>

</div>














<!-- |  studyAgents[1].otherAgent | -->

<div class="row " id="studyAgents[1].otherAgent-row" >
    <div class="label">

            <label>
                <input id="select-other-1" name="agentOrOther1" type="radio"/>
                Other
            </label>


    </div>
    <div class="value">













        <input id="studyAgents[1].otherAgent" name="studyAgents[1].otherAgent" class="validate-MAXLENGTH2000" title="Other" type="text" value="" size="70"/>













































    </div>

</div>




















<!-- |  studyAgents[1].indType | -->

<div class="row " id="studyAgents[1].indType-row" >
    <div class="label">










         <label for="studyAgents[1].indType">






   <span id="studyAgents[1].indType-indicator">

   </span>
            &nbsp;Enter IND information
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[1].indType" name="studyAgents[1].indType" title="Enter IND information"><option value="NA">NA</option><option value="NA_COMMERCIAL">N/A-Commercial Agent</option><option value="IND_EXEMPT">IND-Exempt</option><option value="DCP_IND">DCP IND</option><option value="OTHER">Other IND Holder</option><option value="CTEP_IND">CTEP IND</option></select>




























    </div>

</div>
















<!-- |  studyAgents[1].partOfLeadIND | -->

<div class="row " id="studyAgents[1].partOfLeadIND-row" >
    <div class="label">










         <label for="studyAgents[1].partOfLeadIND">






   <span id="studyAgents[1].partOfLeadIND-indicator">

   </span>
            &nbsp;Lead IND ?
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[1].partOfLeadIND" name="studyAgents[1].partOfLeadIND" title="Lead IND ?"><option value="">Please select</option><option value="false">No</option><option value="true">Yes</option></select>




























    </div>

</div>






 <div id="local-buttons-1" class="local-buttons">

 </div>
 <br />

    </div>
</div>

<div class="division sa-section" id="sa-section-2" >
    <div class="header" style="border-bottom:1px solid #CCCCCC; color:#2E3257; font-weight:bold; padding:4px 8px 1px;">





	   		Study Agent 3



    </div>
    <div class="content" id="contentOf-sa-section-2" style="display:; padding:0px; margin:10px;">


aaaaaa












<!-- |  studyAgents[2].agent | -->

<div class="row " id="studyAgents[2].agent-row" >
    <div class="label">

            <label>
                <input id="select-agent-2" name="agentOrOther2" type="radio"/>
                Agent
            </label>


    </div>
    <div class="value">















































































	<input size="70" type="text" id="studyAgents[2].agent-input" title="Agent"  value="Begin typing here..."
	class="autocomplete  "/>


<img id="studyAgents[2].agent-indicator" class="indicator" src="/caaers/images/indicator.white.gif" alt="activity indicator"/>

  <div id="studyAgents[2].agent-choices" class="autocomplete" style="display: none"></div>
  <input id="studyAgents[2].agent" name="studyAgents[2].agent" type="hidden" value=""/>














<!--code : studyAgents[2].agent msgtxt : NA -->













	<script>

   	AE.hash.set('studyAgents[2].agent' , '1');
    $('studyAgents[2].agent-input').observe('focus', function() {
        if($('studyAgents[2].agent').value == '' && AE.hash.get('studyAgents[2].agent') == '1'){
             var el = $('studyAgents[2].agent-input');
			 el.removeClassName('pending-search');
             el.clear();
			 AE.hash.set('studyAgents[2].agent' , '0');
		}
    });

    $('studyAgents[2].agent-input').observe('blur', function() {
			if(AE.hash.get('studyAgents[2].agent') == '0'){
                var el = $('studyAgents[2].agent-input');
                if (el.value == '') {
                el.value = 'Begin typing here...';
                el.addClassName('pending-search');

				AE.hash.set('studyAgents[2].agent' , '1');
			}
        };
    });

    if($('studyAgents[2].agent').value == ''){
		$('studyAgents[2].agent-input').addClassName('pending-search');
	}

	</script>





























    </div>

</div>














<!-- |  studyAgents[2].otherAgent | -->

<div class="row " id="studyAgents[2].otherAgent-row" >
    <div class="label">

            <label>
                <input id="select-other-2" name="agentOrOther2" type="radio"/>
                Other
            </label>


    </div>
    <div class="value">













        <input id="studyAgents[2].otherAgent" name="studyAgents[2].otherAgent" class="validate-MAXLENGTH2000" title="Other" type="text" value="" size="70"/>













































    </div>

</div>




















<!-- |  studyAgents[2].indType | -->

<div class="row " id="studyAgents[2].indType-row" >
    <div class="label">










         <label for="studyAgents[2].indType">






   <span id="studyAgents[2].indType-indicator">

   </span>
            &nbsp;Enter IND information
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[2].indType" name="studyAgents[2].indType" title="Enter IND information"><option value="NA">NA</option><option value="NA_COMMERCIAL">N/A-Commercial Agent</option><option value="IND_EXEMPT">IND-Exempt</option><option value="DCP_IND">DCP IND</option><option value="OTHER">Other IND Holder</option><option value="CTEP_IND">CTEP IND</option></select>




























    </div>

</div>
















<!-- |  studyAgents[2].partOfLeadIND | -->

<div class="row " id="studyAgents[2].partOfLeadIND-row" >
    <div class="label">










         <label for="studyAgents[2].partOfLeadIND">






   <span id="studyAgents[2].partOfLeadIND-indicator">

   </span>
            &nbsp;Lead IND ?
        </label>




    </div>
    <div class="value">






























        <select id="studyAgents[2].partOfLeadIND" name="studyAgents[2].partOfLeadIND" title="Lead IND ?"><option value="">Please select</option><option value="false">No</option><option value="true">Yes</option></select>




























    </div>

</div>






 <div id="local-buttons-2" class="local-buttons">

 </div>
 <br />

    </div>
</div>



		<span id="agentbookmark" />

    </div>
</div>





 <!-- BEGIN tags\tabControls.tag -->











<div class="content buttons autoclear">
    <div class="local-buttons">
        <div align=right>


<img id="sa-add-indicator" class="indicator" src="/caaers/images/indicator.white.gif" alt="activity indicator"/>
            <input type="button" onClick="javascript:fireAction('addStudyAgent','0');" name="AddStudyAgent" value="Add Study Agent">
        </div>
    </div>
    <div class="flow-buttons">
        <span class="prev">

                <input type="image" src="/caaers/images/blue/back_btn.png" id="flow-prev" class="tab1" value="&laquo; back" alt="&laquo; Back"/>

        </span>
        <span class="next">








            <input type="image" src="/caaers/images/blue/continue_btn.png" id="flow-next" value="continue &raquo;" alt="continue &raquo;"/>
        </span>
    </div>
</div>
<!-- END tags\tabControls.tag -->



	</form>


        </div>
    </div></div></div></div></div></div></div></div>
    <!-- end inner border -->
</div>
</div>

</div>

</div>


<form action="#" id="sso-form" target="_blank" method="post">
    <input type="hidden" name="gridProxy" value=""/>
</form>


<div id="build-name">caAERS v. 1.5-SNAPSHOT (2008-09-29 04:01:44)</div>

</div>

<!-- ALL DIV end -->

</body>
</html>
<!-- END decorators\tabbedflow.jsp -->