<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>

<html>
<head>
  <title>Configure Password Policy</title>
  <style type="text/css">
    div {
     font-weight: bold;
    }

    .nested_section {
     width: 100%;
     margin-left: 20px;
    }

    .required_label {
     text-align: left;
     float: left;
     line-height: 23px;
     margin: 0px 5px;
    }

    .required_value {
     float: left;
     line-height: 20px;
    }
    
    .required_item_heading {
     clear: both;
    }
  </style>
</head>
<body>
  <chrome:box title="Password Policy Configuration" autopad="true">
    <form method="POST" id="passwordPolicyConfigure" action="<c:url value=""/>">
    <chrome:division title="Login Policy">
      <div>
	<div class="nested_section">
	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireMaxPasswordAge" name="requireMaxPasswordAge" type="hidden" />
	    </div>
	    <div class="required_label">Required</div>
	  </div>
	  
	  <div class="nested_section">
	    <div class="row">
	      <div class="required_item_heading">1) Maximum password age policy</div>
	    </div>
	    <div class="nested_section">
	      <div class="row">
		<div class="required_label">Maximum age (days):</div>
		<div class="required_value">
		  <input type="text" size="2" name="maxPasswordAge"/>
		</div>
	      </div>
	      <div class="row">
		<div class="required_label">(The password will expire after the configured number of days)</div>
	      </div>
	    </div>
	  </div>

	</div>

	<div class="nested_section">
	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireNumLoginAttempts" name="requireNumLoginAttempts" type="hidden" />
	    </div>
	    <div class="required_label">Required</div>
	  </div>

	  <div class="nested_section">
	    <div class="row">
	      <div class="required_item_heading">2) Allowed failed login attempts policy</div>
	    </div>
	    <div class="nested_section">
	      <div class="row">
		<div class="required_label">Number of attempts:</div>
		<div class="required_value">
		  <input type="text" size="2" name="maxNumAttempts"/>
		</div>
	      </div>
	      <div class="row">
		<div class="required_label">(After configured number of failed login attempts, the account will be locked)</div>
	      </div>
	    </div>
	  </div>

	</div>

	<div class="nested_section">
	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireLockoutDuration" name="requireLockoutDuration" type="hidden" />
	    </div>
	    <div class="required_label">Required</div>
	  </div>
	  
	  <div class="nested_section">
	    <div class="row">
	      <div class="required_item_heading">3) Lock-out duration</div>
	    </div>
	    <div class="nested_section">
	      <div class="row">
		<div class="required_label">Duration (days):</div>
		<div class="required_value">
		  <input type="text" size="2" name="lockoutDuration"/>
		</div>
	      </div>
	      <div class="row">
		<div class="required_label">(Once triggered, the account will remain locked for the configured number of days)</div>
	      </div>
	    </div>
	  </div>

	</div>
      </div>
    </chrome:division>
	
	
    <chrome:division title="Password Creation Policy">

      <div class="nested_section">
	<div class="row">
	  <div class="required_value">
	    <input type="checkbox" />
	    <input id="requireMinPasswordAge" name="minPasswordAge" type="hidden" />
	  </div>
	  <div class="required_label">Required</div>
	</div>
	
	<div class="nested_section">
	  <div class="row">
	    <div class="required_item_heading">1) Minimum password age policy</div>
	  </div>
	  <div class="nested_section">
	    <div class="row">
	      <div class="required_label">Minimum age (days):</div>
	      <div class="required_value">
		<input type="text" size="2" name="minPasswordAge"/>
	      </div>
	    </div>
	    <div class="row">
	      <div class="required_label">(The password can't be changed before the configured number of days)</div>
	    </div>
	  </div>
	</div>
	
      </div>

      <div class="nested_section">
	<div class="row">
	  <div class="required_value">
	    <input type="checkbox" />
	    <input id="requirePasswordHistoryCount" name="requirePasswordHistoryCount" type="hidden" />
	  </div>
	  <div class="required_label">Required</div>
	</div>
	<div class="nested_section">
	  <div class="row">
	    <div class="required_item_heading">2) Password history policy</div>
	  </div>
	  <div class="nested_section">
	    <div class="row">
	      <div class="required_label">Historical count:</div>
	      <div class="required_value">
		<input type="text" size="2" name="passwordHistoryCount"/>
	      </div>
	    </div>
	    <div class="row">
	      <div class="required_label">(The password can't be reused until the configured number of password changes have been made)</div>
	    </div>
	  </div>
	</div>
      </div>
	
      <chrome:division title="Complexity Requirement" style="margin-left: 20px;">
	<div class="nested_section">
	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireMinPasswordLength" name="requireMinPasswordLength" type="hidden" />
	    </div>
	    <div class="required_label">The minimum length of password should be (characters):</div>
	      <div class="required_value">
		<input type="text" size="2" name="minPasswordLength"/>
	      </div>
	  </div>
	  
	  <div class="row">
	    <div class="required_label">Of the following criteria, password must meet at least:</div>
	    <div class="required_value">
	      <input type="text" size="2" name="minComplexityCriteria" />
	    </div>
	  </div>
	  
	  <div class="nested_section">
	    <div class="row">
	      <div class="required_value">
		<input type="checkbox" />
		<input id="requireUpperCase" name="requireUpperCase" type="hidden" />
	      </div>
	      <div class="required_label">At least one character should be an upper case letter</div>
	    </div>
	    
	    <div class="row">
	      <div class="required_value">
		<input type="checkbox" />
		<input id="requireLowerCase" name="requireLowerCase" type="hidden" />
	      </div>
	      <div class="required_label">At least one character should be a lower case letter</div>
	    </div>

	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireBaseTen" name="requireBaseTen" type="hidden" />
	    </div>
	    <div class="required_label">At least one character should be a base ten digit (0-9)</div>
	  </div>

	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireMinNonAlphanumeric" name="requireNonAlphanumeric" type="hidden" />
	    </div>
	    <div class="required_label">At least one character should be a non-alpha-numeric</div>
	  </div>

	  <div class="row">
	    <div class="required_value">
	      <input type="checkbox" />
	      <input id="requireUniqueSubstrings" name="requireUniqueSubstrings" type="hidden" />
	    </div>
	    <div class="required_label">The password should not contain a substring of</div>
	    <div class="required_value">
	      <input type="text" size="2" name="minUniqueSubstringsLength" />
	    </div>
	    <div class="required_label">characters from the username</div>
	  </div>

	  </div>

	</div>	
      </chrome:division>
    </chrome:division>
  </form>
</chrome:box>
</body>
</html>