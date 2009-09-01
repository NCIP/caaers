var Errors = Class.create();
var Errors = {
    list: new Hash(),
    showSummary: function() {
        var msgs = "<ul class='errors'>";
        Errors.list.each(function(e) {
          //alert(e.key + ' = "' + e.value + '"');
            msgs += "<li id='TOP_ERROR_" + e.key + "'>" + e.value;
            var pDivision = Errors.findParentDivision(e.key);
            if (pDivision) openDivisionById(pDivision.id);
        });
        msgs += "</ul>";
        if ($('TOP_JAVA_SCRIPT_ERRORS')) {
            $('TOP_JAVA_SCRIPT_ERRORS').innerHTML = msgs;
            $('TOP_JAVA_SCRIPT_ERRORS').show();
        }
    },

    push: function(elementID, errorMsg) {
        Errors.list.set(elementID, errorMsg);
    },

    findParentDivision: function(elementID) {
        var pDivision = $(elementID).up(".division");
        return pDivision;
    }
}
