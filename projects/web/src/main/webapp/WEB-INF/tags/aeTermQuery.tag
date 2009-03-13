<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/ui"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="chrome" tagdir="/WEB-INF/tags/chrome"%>

<%@attribute name="isAjaxable" type="java.lang.Boolean" description="Should be set to true, if this tag is included in the response of an AJAX call, this ensures that the javascript objects defined here are properly enabled" %>
<%@attribute name="isMeddra" required="true" type="java.lang.Boolean" description="Will tell whether the autocompleter should look for MedDRA or CTC" %>
<%@attribute name="version" required="true" type="java.lang.Integer" description="Will tell the version of ctc or meddra to use" %>
<%@attribute name="instructions" fragment="true" %>
<%@attribute name="callbackFunctionName" required="true" description="The call back function in the parent page, that will be invoked with the selected terms"%>
<%@attribute name="ignoreOtherSpecify" type="java.lang.Boolean" description="Must be true if we need to ignore other specify" %>
<%@attribute name="localButtons" fragment="true" description="Extra content to be display in the control area, by default an Add Terms button will be displayed"%>
<%@attribute name="ctcCategories" type="java.util.List" description="The ctc categories that should be displayed within the category box of the popup" %>
<%@attribute name="title" required="false" %> 
<%@attribute name="noBackground" required="false" type="java.lang.Boolean" %>

<tags:dwrJavascriptLink objects="createAE"/>

<script type="text/javascript">

 	//This object will store the reference of the Window, will also contains function
 	//that will be called from the loaded page, that has to interact with the parent page(opener page)
 	
 	//Note:- for some reason, the javascript embeded inside javascript tag in the child page (the page loaded in the linline popup window via AJAX)
 	//is not properly executing, so I modified it to work based on inline-hidden div.
 	var CategorySelector = Class.create();
 	Object.extend(CategorySelector.prototype, {
		initialize: function(meddra, ver, ignoreOtherSpecify) {
			this.win = null;
			this.isMeddra = meddra;
			this.version = ver;
			this.ignoreOtherSpecify = ignoreOtherSpecify;
            this.termList = new Array();
        },
		
		showWindow:function(wUrl, wTitle, wWidth, wHeight){
			win = new Window({
                className:"alphacube",
                destroyOnClose:true,
                title:wTitle,
                width:wWidth,
                height:wHeight,
                onShow:this.show.bind(this),
                recenterAuto:true,
                resizable: false,
                minimizable : false,
                maximizable: false,
                onBeforeShow:this.beforeShow.bind(this)
            });
			this.win = win;
			win.setContent('chooseCategory');
            win.showCenter(true);
		},
         
        initializeAutoCompleter: function() {

			AE.createStandardAutocompleter('termCode', 
            		function(autocompleter, text){
            			if(this.options.categorySelector.isMeddra){
            				createAE.matchLowLevelTermsByCode(this.options.categorySelector.version,text, function(values) {
            					if(catSel.ignoreOtherSpecify){
                    				var vals = [];
                    				values.each(function(aterm){
                        				if(aterm.fullName.indexOf('Other (Specify') < 0){
                        					 vals.push(aterm);
                    					}
                        			});
                    				autocompleter.setChoices(vals);
                				}else{
                					autocompleter.setChoices(values);
                    			}								
							});
            			}else{
            				createAE.matchTerms(text, this.options.categorySelector.version, '', 25 , function(values){
                				if(catSel.ignoreOtherSpecify){
                    				var vals = [];
                    				values.each(function(aterm){
                        				if(aterm.fullName.indexOf('Other (Specify') < 0){
                        					 vals.push(aterm);
                    					}
                        			});
                    				autocompleter.setChoices(vals);
                				}else{
                					autocompleter.setChoices(values);
                    			}
            				});
            			}
            		},
            		function(aterm) {
            			return aterm.fullName;
            		}, 
            		{categorySelector:this}
            	);
		},

        finishSingleTermSelection:function(){
			var selTermMap = new Hash();
			var termElement = $('termCode');
			var termElementInput = $('termCode-input');
			
			var termId = termElement.getValue();
			if(termId) selTermMap.set(termId, termElementInput.getValue());
			
			termElement.clear();
			termElementInput.clear();
			${callbackFunctionName}(selTermMap); //need to refactor, this is a rude way of calling a function
			
		},

        finishMultiTermsSelection:function() {

            var selTermMap = new Hash();
            var selectedTerms = $$('input.AddedTermXYZ');
            selectedTerms.each(function(el) {
                if (el.checked) //alert(el.name);
                selTermMap.set(el.value, el.name);
            });
            Windows.close(this.win.getId());
            ${callbackFunctionName}(selTermMap);

            catSel.termList = new Array();
            $('ae-terms').innerHTML = "";
            $('ae-added-terms').innerHTML = "";
            
            return;
		},
         
        cancelTermsSelection:function(){
			Windows.close(this.win.getId());
			//reset the category and terms
			terms.options.length=0;
			categories.selectedIndex = -1;
		},

        beforeShow : function(){
		},

        show: function(){
		},

        addTerm: function(ulID, termID, termText) {
            if (catSel.termList[termID]) {
                return;
            }
            ul = document.getElementById(ulID);
            
            checkbox = document.createElement("input");
            checkbox.type = 'checkbox';
            checkbox.name = termText;
            checkbox.defaultChecked = true;
            checkbox.value = termID;
            checkbox.id = "chkID" + termID;
            checkbox.setAttribute("id", "chk" + termID);

            a = document.createElement("a");
            a.appendChild(document.createTextNode(termText));

            a.id = "addedTerm" + termID;
            a.setAttribute("id", "addedTerm" + termID);

            li = document.createElement("li");
            li.appendChild(checkbox);
            li.appendChild(a);
            ul.appendChild(li)

            catSel.termList[termID] = true;
            $("liTerm" + termID).addClassName("ae-disabled");
            $("addedTerm" + termID).addClassName("ae-added-terms");
            $("chk" + termID).addClassName("AddedTermXYZ");
            
        },

        addLIToUL: function(ulID, ilID, ilText) {
            ul = document.getElementById(ulID);
            a = document.createElement("a");
            a.appendChild(document.createTextNode(ilText));

            a.setAttribute("onClick", "catSel.addTerm('ae-added-terms', " + ilID + ", '" + ilText + "')");
            a.onclick = function() {
                eval("catSel.addTerm('ae-added-terms', " + ilID + ", '" + ilText + "')");
            }

            a.setAttribute("id", "liTerm" + ilID);
            a.id = "liTerm" + ilID;
            
            li = document.createElement("li");
            li.appendChild(a);
            ul.appendChild(li);

            // setting the styles
            $("liTerm" + ilID).addClassName("ae-category");
            if (catSel.termList[ilID]) {
                $("liTerm" + ilID).addClassName("ae-disabled");
            }
        },

        showTerms: function(id, ignoreOtherSpecify, el){

            $('ae-terms').innerHTML = "";

            catId = id; //$(el).getValue();
            createAE.getTermsByCategory(catId, function(ctcTerms) {
                ctcTerms.each(function(ctcTerm) {
                    if (!(ignoreOtherSpecify && ctcTerm.fullName.indexOf('Other (Specify') > 0)) {
                        var ctcFullName = (ctcTerm.fullName.length > 70 ? ctcTerm.fullName.substring(0, 70) + "..." : ctcTerm.fullName );
                        catSel.addLIToUL("ae-terms", ctcTerm.id, ctcFullName);
                    }
                })
            });

            return;
		},

        showCategoryBox:function(){
	 			this.showWindow('', '${title}', 850, 530 );
	 	}
 	});
	
	function initalizeCategorySelector(){
		//some pages add this tag, via ajax, so they are supposed to
		//call this function directly.
		catSel = new CategorySelector(${isMeddra}, ${version}, ${ignoreOtherSpecify});
	 	catSel.initializeAutoCompleter();
	}
 	
 	<c:if test="${isAjaxable}">
         initalizeCategorySelector.defer();
 	</c:if>

</script>

<chrome:box title="Find &amp; Add AEs" noBackground="${noBackground}">
  		<div style="display:inline;">
  			<tags:autocompleter displayName="abcd" propertyName="termCode" size="70" initialDisplayValue="Begin typing here"/>
			<tags:button size="small" color="blue" icon="add" id="addSingleTermBtn" type="button" value="Add"  onclick="catSel.finishSingleTermSelection();" />
  		</div>
		<div style="margin:0 20px; display:inline;">or</div>
  		<c:if test="${not isMeddra}">
  			<tags:button size="small" type="button" color="blue" icon="window" value="Add Multiple" id="addMultiTermBtn" onclick="catSel.showCategoryBox();"/>
  		</c:if>
  	</chrome:box>



      <!-- the hidden window for category popup -->
  	<div style="display:none">
	<c:if test="${not isMeddra}">

    <div id="chooseCategory">

        <table width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr bgcolor="#E4E4E4">
            <td align="left" width="35%"><h2 class="title">Categories</h2></td>
            <td align="left" width="1px"><img src="<c:url value="/images/chrome/spacer.gif" />"></td>
            <td align="left" width="35%"><h2 class="title">Terms&nbsp;<span style='font-size:12px;'>(Click to add)</span></h2></td>
            <td align="left" width="1px"><img src="<c:url value="/images/chrome/spacer.gif" />"></td>
            <td align="left" width="30%">&nbsp;</td>
        </tr>
        <tr>
            <td align="left" valign="top">
                <ul class="ae-category">
                    <c:forEach var="cat" items="${empty ctcCategories ? command.study.ctcCategories : ctcCategories}">
                        <li><a onclick='catSel.showTerms(${cat.id}, catSel.ignoreOtherSpecify);' class='ae-category' title="${cat.name}">${cat.name}</a><br>
                    </c:forEach>
                </ul>
            </td>
            <td align="left" bgcolor="gray"></td>
            <td align="left" valign="top">
                <ul id="ae-terms" class="ae-category"></ul>
            </td>
            <td align="left" bgcolor="gray"></td>
            <td align="left" valign="top"><ul id="ae-added-terms" class="ae-category"></ul></td>
        </tr>
        <tr>
            <td colspan="4" style="text-align:right;">
            </td>
            <td colspan="1" style="text-align:center;">
                    <c:if test="${empty localButtons}">
                        <tags:button color="green" value="Add Terms" icon="add" onclick="catSel.finishMultiTermsSelection()" />
                    </c:if>
                     <jsp:invoke fragment="localButtons"/>
            </td>
        </tr>
        </table>
        
    </div>
	</c:if>
          
    <c:if test="${isMeddra}">
		<p>Addition of multiple terms is only supported for CTC terminology</p>
	</c:if>
  	
	</div>
<!-- the hidden window for category popup -->

<style>
    ul.ae-category {
        cursor:pointer;
        margin: 5px;
        padding-left: 12px;
    }

    a.ae-category {
        font-size:8pt;
        cursor:pointer;
        color:black;
    }

    a.ae-category:hover {
        font-size:8pt;
        cursor:pointer;
        color:blue;
    }

    ul.ae-added-terms, a.ae-added-terms {
        font-size:8pt;
        cursor:pointer;
        margin: 0px;
        padding-left: 5px;
    }

    a.ae-added-terms:hover {
        font-size:8pt;
        cursor:pointer;
        color: blue;
        margin: 0px;
        padding-left: 5px;
    }

    a.ae-disabled {
        font-size:8pt;
        color:#cccccc;
        cursor:pointer;
    }

    a.ae-disabled:hover {
        font-size:8pt;
        color:#cccccc;
        cursor:pointer;
    }
</style>