var ajaxCRUD = null;
var AJAX_CRUD_HELPER = Class.create();
Object.extend(AJAX_CRUD_HELPER.prototype, {
    initialize: function() {
    },

    _populateDeafultParameters : function(itemType, paramHash, tabNumber) {
        var target = '_target' + tabNumber;
        paramHash.set('_page', tabNumber);
        paramHash.set(target, tabNumber);
        paramHash.set('_asynchronous', true);
        paramHash.set('decorator', 'nullDecorator');
    },

    _showIndicator:function(indicatorElement) {
        if (indicatorElement) indicatorElement.removeClassName("indicator");
    },

    _hideIndicator:function(indicatorElement) {
        if (indicatorElement) indicatorElement.addClassName("indicator");
    },

    _addItem: function(itemType, src, val, location, options, tabNumber, insertionLocation) {
        var container = $(location);
        var paramHash = new Hash();
        paramHash.set('task', 'add');
        paramHash.set('currentItem', itemType);
        paramHash.set(itemType, val);

        this._populateDeafultParameters(itemType, paramHash, tabNumber);

        var url = $('command').action + "?subview";
        this._showIndicator($(container.id + "_indicator"));
        this._insertContent(container, url, paramHash, function() {this._hideIndicator($(container.id + "_indicator")); AE.registerCalendarPopups();}.bind(this), insertionLocation);
    },

    _deleteItem: function(itemType, index, location, tabNumber) {
        if (index < 0) return;
        var confirmation = confirm("Do you really want to delete?");
        if (!confirmation) return;

        // this.showIndicator(itemType+"-indicator");
        var container = $(location);

        var paramHash = new Hash();
        paramHash.set('task', 'remove');
        paramHash.set('currentItem', itemType);
        paramHash.set('index', index);
        this._populateDeafultParameters(itemType, paramHash, tabNumber);
        var url = $('command').action + "?subview";
        var sectionHash = Form.serializeElements(this.formElementsInSection(container), true);
        this._updateContent(container, url, paramHash.merge(sectionHash), function (transport) {
        }.bind(this));

        if (itemType == 'agent') $('btn-add-agent').show();
    },
    
    formElementsInSection : function(container) {
        return container.select('input', 'select', 'textarea');
    },

    _updateContent: function(container, url, params, onSuccessCallBack) {
        new Ajax.Request(url, {
            parameters: params.toQueryString(),
            onSuccess: function(transport) {
                container.innerHTML = transport.responseText;
                AE.registerCalendarPopups();
            }
        });

    },

    _insertContent: function(container, url, params, onCompleteCallBack, insertionLocation) {
		if (!insertionLocation){
				insertionLocation = 'Top'
			}
        new Ajax.Updater(container, url, {
            parameters: params.toQueryString(), onComplete: onCompleteCallBack, insertion: insertionLocation, evalScripts: true
        });
    }
})
