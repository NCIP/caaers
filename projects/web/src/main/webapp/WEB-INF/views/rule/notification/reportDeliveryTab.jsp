<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ruletags" tagdir="/WEB-INF/tags/rule"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <tags:stylesheetLink name="ae"/>
    <tags:includeScriptaculous/>
    <title>Report Delivery Configuration</title>
    <style>
    #name {width:300px}
    </style>
</head>
<body>
    <chrome:division title="Final Report Delivery Configuration">
    <tags:tabForm tab="${tab}" flow="${flow}" willSave="false">
		<jsp:attribute name="singleFields">
		   Direct Recipient (Multiple entries possible)
           <hr />
           <div id="direct">
              <!-- ***** -->
              <div id="name-row" class="row">
    			<div class="label"><label for="name">Name</label></div>
    			<div class="value"><input type="text" value="Service Mix" name="name" id="name"  size="60"/></div>
			  </div>
              <!-- ===== -->
               <!-- ***** -->
              <div id="desc-row" class="row">
    			<div class="label"><label for="desc">Description</label></div>
    			<div class="value"><input type="text" value="Sending to ESB for IRB" name="description" id="description" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Report Format</label></div>
    			<div class="value"><select name="format"><option>PDF</option><option SELECTED>XML</option><option>HTML</option><option>TTF</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="desc-endpoint" class="row">
    			<div class="label"><label for="desc">Address</label></div>
    			<div class="value"><input type="text" value="http://10.2.2.1:7002/smix/url" name="address" id="address" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="address-type-row" class="row">
    			<div class="label"><label for="format">Address Type</label></div>
    			<div class="value"><select name="addressType"><option>eMail</option><option SELECTED>URL</option><option>FAX</option></select></div>
			  </div>
              <!-- ===== -->
           </div>
           <div>==============================================</div>
           <br />
           <div id="direct">
              <!-- ***** -->
              <div id="name-row" class="row">
    			<div class="label"><label for="name">Name</label></div>
    			<div class="value"><input type="text" value="Visu Patlola" name="name" id="name"  size="60"/></div>
			  </div>
              <!-- ===== -->
               <!-- ***** -->
              <div id="desc-row" class="row">
    			<div class="label"><label for="desc">Description</label></div>
    			<div class="value"><input type="text" value="email of Visu" name="description" id="description" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Report Format</label></div>
    			<div class="value"><select name="format"><option>PDF</option><option SELECTED>HTML</option><option>XML</option><option>TTF</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="desc-endpoint" class="row">
    			<div class="label"><label for="desc">Address</label></div>
    			<div class="value"><input type="text" value="visu.patlola@semanticbits.com" name="address" id="address" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="address-type-row" class="row">
    			<div class="label"><label for="format">Address Type</label></div>
    			<div class="value"><select name="addressType"><option>URL</option><option SELECTED>eMail</option><option>FAX</option></select></div>
			  </div>
              <!-- ===== -->
           </div>
           <hr />
             Role based recipient(below)
           <hr />
           <div id="role">
            <!-- ***** -->
              <div id="name-row" class="row">
    			<div class="label"><label for="name">Name</label></div>
    			<div class="value"><input type="text" value="Edmond" name="name" id="name"  size="60"/></div>
			  </div>
              <!-- ===== -->
               <!-- ***** -->
              <div id="desc-row" class="row">
    			<div class="label"><label for="desc">Description</label></div>
    			<div class="value"><input type="text" value="Project Manager in Semanticbits" name="description" id="description" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Report Format</label></div>
    			<div class="value"><select name="format"><option>XML</option><option SELECTED>PDF</option><option>HTML</option><option>TTF</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Role</label></div>
    			<div class="value"><select name="format"><option>Sponsor - Study Personal</option><option>Study Site - Study Mentor</option><option>IRB - Study Cordinator</option><option SELECTED>An Entity - a Role</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="address-type-row" class="row">
    			<div class="label"><label for="format">Address Type</label></div>
    			<div class="value"><select name="addressType"><option>eMail</option><option>URL</option><option SELECTED>FAX</option></select></div>
			  </div>
              <!-- ===== -->
           </div>
           </div>
                      <div>==============================================</div>
             <div id="role">
            <!-- ***** -->
              <div id="name-row" class="row">
    			<div class="label"><label for="name">Name</label></div>
    			<div class="value"><input type="text" value="Ram " name="name" id="name"  size="60"/></div>
			  </div>
              <!-- ===== -->
               <!-- ***** -->
              <div id="desc-row" class="row">
    			<div class="label"><label for="desc">Description</label></div>
    			<div class="value"><input type="text" value="CTO of Semanticbits" name="description" id="description" size="60"/></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Report Format</label></div>
    			<div class="value"><select name="format"><option>PDF</option><option>XML</option><option>HTML</option><option  SELECTED>TFF</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="format-row" class="row">
    			<div class="label"><label for="format">Role</label></div>
    			<div class="value"><select name="format"><option>Sponsor - Study Personal</option><option SELECTED>Study Site - Study Mentor</option><option>IRB - Study Cordinator</option><option >An Entity - a Role</option></select></div>
			  </div>
              <!-- ===== -->
              <!-- ***** -->
              <div id="address-type-row" class="row">
    			<div class="label"><label for="format">Address Type</label></div>
    			<div class="value"><select name="addressType"><option SELECTED>eMail</option><option>URL</option><option >FAX</option></select></div>
			  </div>
              <!-- ===== -->
           </div>
           </div>
		</jsp:attribute>
	</tags:tabForm> 
	</chrome:division>
    
</body>
</html>