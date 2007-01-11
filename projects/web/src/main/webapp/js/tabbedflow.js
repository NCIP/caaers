AE.tabbedFlowSelectPage = function(click) {
    Event.stop(click)
    var a = Event.element(click)
    var tabclass = Element.classNames(a).detect(function(cls) { return cls.slice(0, 3) == "tab" })
    var targetPage = tabclass.slice(3)
    $('flowredirect-target').name = "_target" + targetPage
    $('flowredirect').submit()
}

Event.observe(window, "load", function() {
    $$("li.tab a").each(function(a) {
        Event.observe(a, "click", AE.tabbedFlowSelectPage)
    })
    if ($("flow-prev")) Event.observe("flow-prev", "click", AE.tabbedFlowSelectPage)
    if ($("flow-next")) Event.observe("flow-next", "click", function(click) {
        Event.stop(click)
        $("command").submit(); // command is the default ID for a form created with form:form
    })
})