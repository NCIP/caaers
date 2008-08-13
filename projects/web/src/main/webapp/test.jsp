<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<script src="js/prototype.js"></script>
<script src="js/common.js"></script>

<script src="js/scriptaculous/effects.js"></script>
<script src="js/scriptaculous/slider.js"></script>
<script src="js/scriptaculous/builder.js"></script>
<script src="js/scriptaculous/controls.js"></script>
<script src="js/scriptaculous/dragdrop.js"></script>

<script src="js/common-scriptaculous.js"></script>

TEST.JSP

<script type="text/javascript">
  var _collapsedELs = new Array();
  Event.observe(window, "load", function() {
        for (var i in _collapsedELs) {
            if ($(i))
                // alert('key is: ' + i + ', value is: ' + _collapsedELs[i]);
                $(i).hide();
        }
  });
</script>

<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<table width="100%" border="1">
    <tr bgcolor="yellow">
        <th>
        <th>One
        <th>Two
        <th>Three
        <th>Four
        <th>Five
    <tr id="_tr_0">
        <td><chrome:collapsableElement targetID="_tr_1" />
        <td>One
        <td>Two
        <td>Three
        <td>Four
        <td>Five
    <tr id="_tr_1">
        <td><chrome:collapsableElement targetID="_tr_2" collapsed="true"/>
        <td>1
        <td>2
        <td>3
        <td>4
        <td>5
    <tr id="_tr_2">
        <td>
        <td colspan="5">
                            <table width="100%" border="0" style="border:1px red dotted;">
                                <tr bgcolor="brown">
                                    <th>
                                    <th>One
                                    <th>Two
                                    <th>Three
                                    <th>Four
                                    <th>Five
                                <tr id="_tr_2_0">
                                    <td><chrome:collapsableElement targetID="_tr_2_1" collapsed="true"/>
                                    <td>One
                                    <td>Two
                                    <td>Three
                                    <td>Four
                                    <td>Five
                                <tr id="_tr_2_1">
                                    <td><chrome:collapsableElement targetID="_tr_2_2" collapsed="true"/>
                                    <td>One
                                    <td>Two
                                    <td>Three
                                    <td>Four
                                    <td>Five
                                <tr id="_tr_2_2">
                                    <td>
                                    <td colspan="5">Content
                            </table>

</table>


<%--<div id="divOne">
    DIV ONE
    <div id="sOne">ALPHA</div>
</div>

<span id="divTwo">
    DIV TWO
</span>

<input type="button" id="add-divOne-button" value="One button.">


<script>

var ABCD = Class.create();
    Object.extend(ABCD.prototype, {
    initialize: function() {
        
    },

    addContent: function() {
        alert('AAA');
        // return "<div id='divOne1'>HTML CONTENT</div>";
    }
})
    new ListEditor("divOne", new ABCD(), "Content", {addFirstAfter:"sOne", addCallback:function(newIndex) {var newIndex = 0;} });

</script>--%>

</html>