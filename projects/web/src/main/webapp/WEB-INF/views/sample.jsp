<!-- This page is the view for a JSP & tags version of docs/Visual Design/new_template. -->

<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>

<chrome:division>
    <p class="instructions">This is a brief instructional message.</p>
    <div class="row">
        <div class="label"><label for="input0"><span class="required-indicator">*</span>Lorem</label></div>
        <div class="value"><input type="text" id="input0" /></div>
    </div>
    <div class="row error">
        <div class="label"><label for="input1">Ipsum dolor sit amet</label></div>
        <div class="value">
            <input type="text" value="consectetuer" id="input1" />
            <ul class="errors">
                <li>There was some trouble with ipsum</li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="label"><label for="input2"><span class="required-indicator">*</span>Elit</label></div>
        <div class="value"><textarea id="input2" rows="4" cols="30"></textarea></div>
    </div>
    <div class="row">
        <div class="label">Vestibulum</div>
        <div class="value">
            <input type="text" value="18.4">
            ante
            <select>
                <option>felis</option>
                <option>augue</option>
                <option>dignissim</option>
            </select>
        </div>
    </div>
    <div class="row">
        <div class="label">Morbi</div>
        <div class="value">
            <label><input type="radio" name="radio"> At</label>
            <label><input type="radio" name="radio"> Nibh</label>
            <label><input type="radio" name="radio" checked> Sed</label>
        </div>
    </div>
</chrome:division>

<chrome:division title="Vivamus A">
    <div class="row">
        <div class="label">Scelerisque sapien</div>
        <div class="value">
            <input type="text">
            eu
            <input type="text">
        </div>
    </div>
    <div class="row">
        <div class="label">Faucibus orci</div>
        <div class="value">
            <input type="text">
            eu
            <input type="text">
        </div>
    </div>
</chrome:division>

<chrome:division title="Vivamus B">
    <div class="row">
        <div class="label">Scelerisque sapien</div>
        <div class="value">
            <input type="text" disabled>
            eu
            <input type="text" disabled>
        </div>
    </div>
    <div class="row">
        <div class="label">Faucibus orci</div>
        <div class="value">
            <input type="text" value="9 * 6" disabled>
            eu
            <input type="text" value="42" disabled>
        </div>
    </div>
</chrome:division>

</body>
</html>