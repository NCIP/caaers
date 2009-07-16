<html>
<head>
<script src="js/prototype.js"></script>

<script src="js/scriptaculous/effects.js"></script>

<script src="js/accordion.js"></script>
<script type="text/javascript"><!--
<%--
Event.observe(window, 'load',  function(){
	var topAccordion = new accordion('mydiv', {
		// The speed of the accordion
		resizeSpeed : 8,
		// The classnames to look for
		classNames : {
			// The standard class for the title bar
		    toggle : 'accordion_toggle',
		    // The class used for the active state of the title bar
		    toggleActive : 'accordion_toggle_active',
		    // The class used to find the content
		    content : 'accordion_content'
		}

		
	});

	var verticalAccordions = $$('.accordion_toggle');
	verticalAccordions.each(function(accordion) {
	    $(accordion.next(0)).setStyle({
	        height: '0px'
	    });
	});
		
   topAccordion.activate($$('#mydiv .accordion_toggle')[0]);
	
		
});
--%>
--></script>
<style type="text/css">
.accordion_toggle {
			display: block;
			background: url(images/accordion_toggle.jpg) no-repeat top right #a9d06a;
			padding: 0 10px 0 10px;
			line-height: 30px;
			color: #ffffff;
			font-weight: normal;
			text-decoration: none;
			outline: none;
			font-size: 12px;
			color: #000000;
			border-bottom: 1px solid #cde99f;
			cursor: pointer;
			margin: 0 0 0 0;
		}
		
		.accordion_toggle_active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}
		
		.accordion_content {
			background-color: #ffffff;
			color: #444444;
			overflow: hidden;
			height:auto;
		}
			
			.accordion_content h2 {
				margin: 15px 0 5px 10px;
				color: #0099FF;
			}

</style>
</head>
<body>

<div >
  <h2>Welcome</h2>
  <a href="#" onclick="{$('third').show()}" >Add New</a>
</div>
<div id="test-accordion">
<div class="accordion_toggle">Title Bar1</div>
<div class="accordion_content">
<br>
<br>
<br>
 Content 1
<br>
<br>

</div>

<div class="accordion_toggle">Title Bar2</div>
<div class="accordion_content">
<br>
<br>
<br>
 Content 2
<br>
<br>
</div>
<div class="accordion_toggle">Title Bar3</div>
<div class="accordion_content">
<br>
<br>
<br>
 I am hidden
<br>
<br>
</div>
<div id="third" style="display:none;">

<div class="accordion_toggle">Title Bar4</div>
<div class="accordion_content">
<br>
<br>
<br>
 I am hidden
<br>
<br>
</div>

</div>


</div>

</body>
<%--
<body>
<div id="test-accordion" class="accordion">
	<div class="accordion-toggle">Main</div>
	<div class="accordion-content">
		<p>
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Suspendisse malesuada mi vel risus. Nulla ipsum risus, malesuada gravida, dapibus et, dapibus rhoncus, orci. Quisque suscipit. Praesent sed tellus facilisis lectus ultrices laoreet. Donec eu orci in metus egestas hendrerit. In hac habitasse platea dictumst. Integer blandit ultricies erat. Nunc viverra blandit velit. Maecenas tristique tortor non ante. In pharetra mi quis metus. Cras urna dolor, volutpat et, tincidunt quis, accumsan a, erat. Donec et dolor at elit congue molestie. In mi sapien, porta ut, cursus placerat, sodales in, libero. Aliquam tempus vestibulum ipsum. Suspendisse ligula orci, dignissim eu, laoreet ut, interdum sit amet, tortor. Vestibulum est lacus, sagittis faucibus, sollicitudin fringilla, pretium non, ipsum. Quisque enim. Nullam tortor mi, posuere et, pellentesque ut, laoreet quis, lectus. Mauris euismod aliquet mi. Pellentesque eu pede vitae nibh imperdiet convallis.
		</p>
		<p>	
			Mauris dictum congue lectus. Fusce erat elit, imperdiet non, aliquam sed, lobortis id, libero. Donec dui erat, sollicitudin sed, blandit eget, aliquam non, mauris. Mauris lobortis. Suspendisse orci metus, lobortis ut, sollicitudin et, laoreet eu, ligula. Pellentesque at tellus sed nunc volutpat convallis. Suspendisse tincidunt, erat ac pretium luctus, dolor purus tincidunt justo, eu semper massa massa ac dui. Morbi vel arcu ut elit placerat consequat. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas est dui, luctus id, tempor a, dapibus lacinia, nunc. In vulputate, ipsum eget tempor aliquam, mauris enim ornare risus, vitae rhoncus purus ligula ut urna. In eu arcu. Aliquam erat volutpat. Donec purus enim, malesuada quis, aliquet vel, dapibus eu, lacus. In laoreet nulla id mi. Cras bibendum semper lacus. Nunc id sapien in ligula consectetuer semper. Nunc enim elit, interdum id, tincidunt et, ultrices eu, arcu.  
		</p>

	</div>
	<div class="accordion-toggle">Why Use Us</div>
	<div class="accordion-content">
		<p>
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec vel justo. Integer ornare dignissim lectus. Nunc tellus. Donec pharetra aliquam neque. Vestibulum ornare tincidunt mauris. Duis ut felis et ipsum feugiat faucibus. Phasellus enim magna, sodales id, mollis vel, fringilla et, felis. Integer placerat, tortor eu blandit eleifend, elit leo fringilla orci, quis tristique leo justo ut quam. Aenean dolor. Donec tempus. Ut dapibus odio vitae ligula.
		</p>
		<p>
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec vel justo. Integer ornare dignissim lectus. Nunc tellus. Donec pharetra aliquam neque. Vestibulum ornare tincidunt mauris. Duis ut felis et ipsum feugiat faucibus. Phasellus enim magna, sodales id, mollis vel, fringilla et, felis. Integer placerat, tortor eu blandit eleifend, elit leo fringilla orci, quis tristique leo justo ut quam. Aenean dolor. Donec tempus. Ut dapibus odio vitae ligula.                            
		</p>

	</div>
	<div class="accordion-toggle">Our Prices</div>
	<div class="accordion-content">
		<p>
		In posuere velit sit amet tortor. Donec elementum ipsum at ante luctus elementum. Duis varius dolor a tortor. Donec mi. Phasellus posuere. Mauris enim erat, commodo et, porta quis, consequat quis, nibh. Maecenas convallis eleifend ante. Phasellus metus metus, tempor sed, rhoncus ac, feugiat a, ante. Morbi sit amet ipsum. Cras eu leo quis pede condimentum tempor. Curabitur dictum elit sed lacus. Sed tortor magna, euismod non, mollis a, egestas nec, quam. Fusce porttitor porttitor nunc. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Fusce faucibus, ipsum vel consequat sodales, odio nulla pretium elit, sit amet tempor magna dolor vitae tellus. Quisque odio.
		</p>
	</div>
	<div class="accordion-toggle">Contact Us</div>

	<div class="accordion-content">
		<p>
		Nulla eget ante. In luctus nunc eu nisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Suspendisse lectus sem, commodo vitae, scelerisque eget, varius vitae, neque. Maecenas sed risus. Pellentesque erat. Morbi varius elit id augue. In ultrices vulputate mauris. Vivamus libero ligula, viverra eget, placerat at, adipiscing at, elit. Quisque sapien eros, fermentum a, cursus vel, dignissim id, massa. Donec hendrerit neque sit amet arcu. Cras adipiscing tincidunt elit. Praesent at enim ac lacus malesuada porttitor. Nullam nec diam eu erat posuere mollis. Cras eget urna. Pellentesque sed arcu. Vestibulum lacinia mattis lacus. Curabitur ornare felis ac eros. Fusce convallis est id nisi.
		</p>
	</div>
</div>

</body>
--%>
</html>