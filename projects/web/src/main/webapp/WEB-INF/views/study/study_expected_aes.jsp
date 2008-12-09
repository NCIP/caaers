<%@ include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<head>
	<title>${tab.longTitle}</title>

    <tags:includePrototypeWindow />
    <tags:javascriptLink name="hover-display" />
	<tags:dwrJavascriptLink objects="createStudy"/>

    <script>
       var catSel = null;
       var RPCreatorClass = Class.create();
       var deleteIndex = 0;

       function removeTerm(index) {
           if(!confirm( "Are you sure you want to delete this?" )) return false;

           createStudy.removeStudyTerm(index, function(ajaxOutput) {
               $('termsDiv').innerHTML = ajaxOutput.htmlContent;
           });
       }

       Object.extend(RPCreatorClass.prototype, {
           initialize : function() {
               this.win = null;
               return;
           },

           rpEditCtrlClick:function() {
               if (this.rpCtrl.value > 0) this.displayRPPopup();
           },

           addStudyTerm:function(selectedTerms) {
               var listOfTermIDs = new Array();

               $H(selectedTerms).keys().each(function(termID) {
                   listOfTermIDs.push(termID);
               }.bind(this));


               var notAddedTerms = new Array();
               createStudy.addExpectedAE(listOfTermIDs, function(ajaxOutput) {
                   var notAddedTerms = ajaxOutput.objectContent;
                   if (notAddedTerms != null) {
                       //show alert message for terms not added
                       var errMsg = '';
                       var i;
                       for (i = 0; i < notAddedTerms.length; i++) errMsg = errMsg + (i > 0 ? ',' : '') + notAddedTerms[i];
                       alert("err="+errMsg);
                       if (errMsg != '') alert(errMsg + " - is already present.");
                   }

                   $('observedBlankRow').insert({after: ajaxOutput.htmlContent});
                   // if ($('observedEmptyRow')) $('observedEmptyRow').remove();

               }.bind(this));
           },

           isTermAgainAdded:function(termID) {
               //will tell wheter the term is already present
               $$('.eachRowTermID').each(function(aTerm) {
                   if (termID == aTerm.value()) return true;
               });
               return false;
           },

           initializeOtherMeddraAutoCompleters: function(listOfTermIDs) {
               listOfTermIDs.each(function(aTermId) {
                   var acEls = $$('om' + aTermId);
               }.bind(this));
           }
       });


       /*
           Create an instance of the RPCreatorClass, by passing 'adverseEventReportingPeriod' which is the ID of Reporting Period select element.
       */
        var rpCreator = null;
        Event.observe(window, "load", function(){
        rpCreator = new RPCreatorClass();

        });

       function checkSubmittedAEs(event){
       }

    </script>

    
</head>
<body>


<!-- STUDY SUMMARY -->
    <study:summary />
<!-- STUDY SUMMARY -->

<tags:tabForm tab="${tab}" flow="${flow}" hideErrorDetails="false">
    <jsp:attribute name="singleFields">
        <study:expectedAEs />
    </jsp:attribute>
</tags:tabForm>

</body>
</html>
