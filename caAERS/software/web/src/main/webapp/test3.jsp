<html>
<head>
<script src="js/prototype.js"></script>

<script src="js/scriptaculous/effects.js"></script>

<script src="js/accordion.js"></script>
<script type="text/javascript"><!--

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

--></script>
<style type="text/css">
.accordion_toggle {
			display: block;
			height: 30px;
			width: 680px;
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
<div id="mydiv">
<h2 class="accordion_toggle">Title Bar1</h2>
<div class="accordion_content">
<br>
<br>
<br>
 Content 1
<br>
<br>

</div>

<h2 class="accordion_toggle">Title Bar2</h2>
<div class="accordion_content">
<br>
<br>
<br>
 Content 2
<br>
<br>
</div>

<div id="third" style="display:none;">

<h2 class="accordion_toggle">Title Bar3</h2>
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
</html>