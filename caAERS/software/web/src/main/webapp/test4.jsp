<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome" %>
<html>
<head>
<script src="js/compressed/prototype_jquery_scriptaclous_accordion.js" ></script>
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


    <chrome:accordion id="abc">
        <jsp:attribute name="title">something big</jsp:attribute>
        <jsp:body>
            <p>
		What is it?

		Git is a fast, efficient, distributed version control system ideal for the collaborative development of software.

		GitHub is the easiest (and prettiest) way to participate in that collaboration: fork projects, send pull requests, monitor development, all with ease.
		</p>
        </jsp:body>
    </chrome:accordion>

    <chrome:accordion id="abc">
        <jsp:attribute name="title">
            <!-- Multi columnn title example -->
            <table>
                <tr>
                    <td width="80%">System Admin</td>
                    <td>
                        <table>
                            <tr>
                                <td>Sites 3</td>
                                <td>Study 6</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </jsp:attribute>
        <jsp:body>
            <p>
		What is it?

		Git is a fast, efficient, distributed version control system ideal for the collaborative development of software.

		GitHub is the easiest (and prettiest) way to participate in that collaboration: fork projects, send pull requests, monitor development, all with ease.
		</p>
        </jsp:body>
    </chrome:accordion>
    <chrome:accordion id="xyz">
        <jsp:attribute name="title">hello</jsp:attribute>
        <jsp:body>testing</jsp:body>
    </chrome:accordion>
</div>


</body>
</html>