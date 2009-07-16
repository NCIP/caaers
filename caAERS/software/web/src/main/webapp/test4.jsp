<html>
<head>
<script src="js/prototype.js"></script>

<script src="js/scriptaculous/effects.js"></script>

<script src="js/accordion.js"></script>
<script type="text/javascript"><!--
var myAccordion;

function showMe(){
	$('newdc').show()
	var last = $$('.accordion-toggle').last();
	alert(last);
	//last.fire('click');
	myAccordion.expand(last);
}
Event.observe(window, 'load',  function(){
	myAccordion = new Accordion("test-accordion", 2);
});
--></script>
<style type="text/css">
.accordion-toggle {
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
		
		.accordion-toggle-active {
			background: url(images/accordion_toggle_active.jpg) no-repeat top right #e0542f;
			color: #ffffff;
			border-bottom: 1px solid #f68263;
		}
		
		.accordion-content {
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
  <a href="#" onclick="showMe()" >Are you a JS geek?</a>
</div>
<div id="test-accordion" class="accordion">
	<div class="accordion-toggle">At last</div>
	<div class="accordion-content">
		<p>
			Hai.... 
		</p>
		<p>	
		  It is working!!!!
		</p>

	</div>
	<div class="accordion-toggle">Hello</div>
	<div class="accordion-content">
		<p>
			Lets go for coffee
		</p>
		<p>
			are you comming?
		</p>

	</div>
	<div class="accordion-toggle">Something Big</div>
	<div class="accordion-content">
		<p>
		What is it?

		Git is a fast, efficient, distributed version control system ideal for the collaborative development of software.

		GitHub is the easiest (and prettiest) way to participate in that collaboration: fork projects, send pull requests, monitor development, all with ease. 
		</p>
	</div>
	<div id="newdc" style="display:none">
	<div class="accordion-toggle">Mr. Geek</div>

	<div class="accordion-content">
		<p>
		 Yes You are!!!!
		 So get up from your seat!!!!
		</p>
	</div>
	</div>
</div>

</body>
</html>