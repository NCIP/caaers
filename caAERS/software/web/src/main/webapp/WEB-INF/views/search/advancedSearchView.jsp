<%@include file="/WEB-INF/views/taglibs.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<tags:dwrJavascriptLink objects="advSearch"/>
		<script>
		var catSel = null;
		
		var CategorySelector = Class.create();
 	Object.extend(CategorySelector.prototype, {
		initialize: function() {
            this.termList = new Array();
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
         
        beforeShow : function(){
		},

        show: function(){
		},

        addTerm: function(ulID, termID, termText, dependentObjectIndex, attributeIndex) {
            if (catSel.termList[termID]) {
                return;
            }
            ul = document.getElementById(ulID);
            
            elementName = 'searchTargetObject.dependentObject[' + dependentObjectIndex + '].viewColumn[' + attributeIndex + '].selected';
            checkbox = document.createElement("input");
            checkbox.type = 'checkbox';
            //checkbox.name = termText;
            // checkbox.name = 'searchTargetObject.dependentObject[' + dependentObjectIndex + '].viewColumn[' + attributeIndex + '].selected';
            //checkbox.defaultChecked = true;
            //checkbox.value = 'true';
            //checkbox.checked = true;
            checkbox.setAttribute("checked", "");
            //checkbox.setAttribute("onclick", "clickCheckBox('" + elementName + "', '" + termID + "')");
            //checkbox.onclick = function() { clickCheckBox(elementName, termID)};
            //checkbox.addEventListener("onclick", function() { clickCheckBox(elementName, termID)}, false);
            
            checkbox.id = "chkID" + termID;
            checkbox.setAttribute("id", "chk" + termID);
            //hiddenVar = document.createElement("input");
            //hiddenVar.type = 'hidden';
            //hiddenVar.id = elementName;
            //hiddenVar.name = elementName;
            //hiddenVar.value = 'true';
            
            var hiddenVarStr = '<input value="true" type="hidden" name="' + elementName + '" id="' + elementName + '" />';
            
            a = document.createElement("a");
            a.appendChild(document.createTextNode(termText));

            a.id = "addedTerm" + termID;
            a.setAttribute("id", "addedTerm" + termID);

            li = document.createElement("li");
            li.innerHTML = hiddenVarStr;
            li.appendChild(checkbox);
            checkbox.checked = true;
            li.appendChild(a);
            ul.appendChild(li)

			if(Prototype.Browser.IE){
				// TODO.
			}else{
				checkbox.setAttribute("onclick", "clickCheckBox('" + elementName + "', '" + termID + "')");
			}

            catSel.termList[termID] = true;
            //$("liTerm" + termID).addClassName("ae-disabled");
            $("addedTerm" + termID).addClassName("ae-added-terms");
            $("chk" + termID).addClassName("AddedTermXYZ");

        },

        addLIToUL: function(ulID, ilID, ilText, dependentObjectIndex, attributeIndex) {
            ul = document.getElementById(ulID);
            a = document.createElement("a");
            a.appendChild(document.createTextNode(ilText));

            a.setAttribute("onClick", "catSel.addTerm('ae-added-terms', '" + ilID + "', '" + ilText + "'," + dependentObjectIndex + "," + attributeIndex + " )");
            a.onclick = function() {
                eval("catSel.addTerm('ae-added-terms', '" + ilID + "', '" + ilText + "'," + dependentObjectIndex + "," + attributeIndex + " )");
            }
			
            a.setAttribute("id", "liTerm" + ilID);
            a.id = "liTerm" + ilID;
            li = document.createElement("li");
            li.appendChild(a);
            ul.appendChild(li);
            $("liTerm" + ilID).addClassName("ae-category");
            if (catSel.termList[ilID]) {
                $("liTerm" + ilID).addClassName("ae-disabled");
            }
        },
        
        initializeViewAttributes: function(){
        	<c:forEach items="${command.searchTargetObject.dependentObject}" var="dependentObject" varStatus="dependentObjectStatus">
				<c:forEach items="${dependentObject.viewColumn}" var="viewColumn" varStatus="viewColumnStatus">
					<c:if test="${viewColumn.selected == true}">
						catSel.addTerm('ae-added-terms', '${viewColumn.columnAttribute}', '${viewColumn.columnTitle}', ${dependentObjectStatus.index}, ${viewColumnStatus.index});
					</c:if>
				</c:forEach>
			</c:forEach> 
        },

        showTerms: function(id, index, el){
            var selectedCategories = $$('a.ae-category-selected');
            selectedCategories.each(function(el) {
                el.removeClassName("ae-category-selected");
            });
            var selectedCategories = $$('li.li-category-selected');
            selectedCategories.each(function(el) {
                el.removeClassName("li-category-selected");
            });
            $("category_" + id).addClassName("ae-category-selected");
            $("li_" + id).addClassName("li-category-selected");
            $('ae-terms').innerHTML = "";
            catId = id; //$(el).getValue();
            advSearch.getViewColumnsForDependentObject(id, function(viewColumns) {
            	var i = 0;
            	viewColumns.each(function(viewColumn) {
            		catSel.addLIToUL("ae-terms", viewColumn.columnAttribute, viewColumn.columnTitle, index, i++);
            	})
            	
            });
            return;
		}
 	});
	
		//some pages add this tag, via ajax, so they are supposed to
		//call this function directly.
		catSel = new CategorySelector();
 		//var advancedSearchHelper = new AdvancedSearchHelper(advSearch);
 		Event.observe(window, "load", function(){
 			catSel.initializeViewAttributes();
 		});
 		
 		function clickCheckBox(attributeId, termID) {
			$(attributeId).value = $('chk'+termID).checked; 
		}
 		
		</script>
<style>
    ul.ae-category {
        cursor:pointer;
        margin: 5px;
        padding-left: 0px;
		list-style-type:none;
    }
	
	ul#categories li a {
		margin-left:5px;
	}
	
    a.ae-category {
        font-size:9pt;
        cursor:pointer;
        color:black;
    }

    a.ae-category-selected {
        font-size:9pt;
        cursor:pointer;
        line-height:26px;
    }

    li.li-category-selected {
        background-image:url(/caaers/images/chrome/ae-cat-arrow.png);
		background-repeat:no-repeat;
    }

    li.li-category {
    }

    a.ae-category:hover {
        font-size:9pt;
        cursor:pointer;
        color:blue;
		text-decoration:underline;
    }

    ul.ae-added-terms, a.ae-added-terms {
        font-size:9pt;
        cursor:pointer;
        margin: 0px;
        padding-left: 5px;
    }

    #ae-added-terms {
        list-style-type: none;
    }

    a.ae-added-terms:hover {
        cursor:pointer;
    }

    a.ae-disabled {
        font-size:9pt;
        color:#cccccc;
        cursor:pointer;
    }

    a.ae-disabled:hover {
        font-size:9pt;
        color:#cccccc;
        cursor:pointer;
    }
</style>

	</head>
	<body>
		<tags:tabForm tab="${tab}" flow="${flow}" formName="advancedSearchForm" saveButtonLabel="Save Search" hideBox="true">
			<jsp:attribute name="singleFields">
				<chrome:box title="Select view">
		        <table width="100%" border="0" cellspacing="0" cellpadding="5">
		        <tbody>
        <tr bgcolor="#E4E4E4">
            <td align="left" width="35%"><h2 class="title">Search objects</h2></td>
            <td align="left" width="1px"><img src="<c:url value="/images/chrome/spacer.gif" />"></td>
            <td align="left" width="35%"><h2 class="title">Attributes&nbsp;<span style='font-size:12px;'>(Click to add)</span></h2></td>
            <td align="left" width="1px"><img src="<c:url value="/images/chrome/spacer.gif" />"></td>
            <td align="left" width="30%"><h2 class="title">Selected attributes</h2></td>
        </tr>
        <tr>
            <td align="left" valign="top">
                <div style="overflow:auto; height:460px;">
                <ul id="categories" class="ae-category">
                    <c:forEach var="dependentObject" items="${command.searchTargetObject.dependentObject}" varStatus="dependentObjectStatus">
                    	<c:if test="${dependentObject.hidden == false}">
	                        <li id="li_${dependentObject.displayName}"><a id="category_${dependentObject.displayName}" onclick="catSel.showTerms('${dependentObject.displayName}', ${ dependentObjectStatus.index});" class='ae-category' title="${dependentObject.displayName}">${dependentObject.displayName}</a></li>
	                    </c:if>
                    </c:forEach>
                </ul>
                </div>
            </td>
            <td align="left" bgcolor="gray"></td>
            <td align="left" valign="top">
                <ul id="ae-terms" class="ae-category"></ul>
            </td>
            <td align="left" bgcolor="gray"></td>
            <td align="left" valign="top"><div style="overflow:auto; height:460px;"><ul id="ae-added-terms" class="ae-category"></ul></div></td>
        </tr>
        </tbody>
        </table>
			</chrome:box>
			</jsp:attribute>
			<jsp:attribute name="tabControls">
      			<div class="content buttons autoclear">
          			<div class="flow-buttons">
              			<span class="prev">
              				<tags:button type="submit" value="Back" cssClass="tab0" color="blue" icon="back" id="flow-prev"/>
			  			</span>
			  			<span class="next">
							<tags:button type="submit" icon="Continue" color="green" id="flow-next" value="Search"/>
						</span>
					</div>
      			</div>
			</jsp:attribute>
		</tags:tabForm>
	</body>
</html>