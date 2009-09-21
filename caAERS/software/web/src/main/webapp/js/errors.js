var Errors = Class.create();
var Errors = {
    list: new Hash(),
    counter : 0,
    showSummary: function() {
        var msgs = "<ul class='errors'>";
        Errors.list.each(function(e) {
          //alert(e.key + ' = "' + e.value + '"');
            msgs += "<li id='TOP_ERROR_" + e.key + "'>" + e.value;
            var pDivision = Errors.findParentDivision(e.key);
            if (pDivision) openDivisionById(pDivision.id);
        });
        msgs += "</ul>";
        if (Errors.counter == 1) {
            var container = $('SERVER_SIDE_ERRORS') || $('TOP_JAVA_SCRIPT_ERRORS');
            if (container) {
                container.innerHTML = msgs;
                container.show();
            }
        }
    },

    push: function(elementID, errorMsg) {
        Errors.list.set(elementID, errorMsg);
        Errors.counter = 1;
    },

    findParentDivision: function(elementID) {
        var pDivision = $(elementID).up(".COLLAPSABLE-DIVISION");
        return pDivision;
    },

    clear: function() {
        Errors.list = new Hash();
        Errors.counter = 0;
    }
}
