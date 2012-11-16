<%@include file="/WEB-INF/views/taglibs.jsp" %>

<html>
<c:set var="widthSource" value="6%" scope="request" />
<c:set var="widthTerm" value="22%" scope="request" />
<c:set var="widthGrade" value="14%" scope="request" />
<c:set var="widthStartDate" value="6%" scope="request" />
<c:set var="widthEndDate" value="6%" scope="request" />
<c:set var="widthVerbatim" value="21%" scope="request" />
<c:set var="widthWhySerious" value="12%" scope="request" />
<c:set var="widthAttribution" value="9%" scope="request" />
<c:set var="widthActions" value="4%" scope="request" />
<head>
<title>${tab.longTitle}</title>
<style type="text/css">
    .ae-link-widget{}
    tr.aeWorkedOn td {

    }
    .wgtBtnDiv{
        width: 7em;
    }
    .picker-div{
        height: 100%;
        width: 100%;
    }
    button.omnipotent-button.small, a.omnipotent-button.small{
        font-size: 11px;
    }

</style>

<script type="text/javascript">

AE.linkEvent = new YAHOO.util.CustomEvent('onLink');
AE.unlinkEvent = new YAHOO.util.CustomEvent('onUnlink');
AE.rejectEvent = new YAHOO.util.CustomEvent('onReject');
AE.activeAeWidgets = new Hash();
AE.aeMappingHash = new Hash();
AE.internalAEHash =  new Hash();
AE.externalAEHash =  new Hash();
AE.iUnmapped = [];
AE.eUnmapped = [];
AE.eRejected = [];
AE.matchFormatter = function(elCell, oRecord, oColumn, oData){

   var i = (oData) ? parseInt(oData) : 0;
   if(i > 45) elCell.innerHTML = 'Very High';
   else if(i > 25) elCell.innerHTML = 'High';
   else if(i > 15) elCell.innerHTML = 'Medium';
   else elCell.innerHTML = 'Low';
}
AE.unlink = function (iaeId, eaeId){
//    console.debug("Entering Unlink : { eUnmapped :  [" + AE.eUnmapped.join(",") + "] , iUnmapped : [" + AE.iUnmapped.join(",") + "] , mapping : [" + AE.aeMappingHash.inspect()+ "] }");

    var eaeIdOld = AE.aeMappingHash.unset(iaeId);
    AE.iUnmapped.push(iaeId);
    if(eaeIdOld){
        AE.eUnmapped.push(eaeIdOld);
    }
//    console.debug("Exiting Unlink : { eUnmapped :  [" + AE.eUnmapped.join(",") + "] , iUnmapped : [" + AE.iUnmapped.join(",") + "] , mapping : [" + AE.aeMappingHash.inspect()+ "] }");
};
AE.link = function (iaeId, eaeId){
//    console.debug("Entering Link : { eUnmapped :  [" + AE.eUnmapped.join(",") + "] , iUnmapped : [" + AE.iUnmapped.join(",") + "] , mapping : [" + AE.aeMappingHash.inspect()+ "] }");
    var i = AE.eUnmapped.indexOf(eaeId);
    if(i > -1) AE.eUnmapped.splice(i, 1);

    var eaeIdOld = AE.aeMappingHash.get(iaeId);
    if(eaeIdOld){
        AE.eUnmapped.push(eaeIdOld);
    }

    AE.aeMappingHash.set(iaeId, eaeId)

    var j =  AE.iUnmapped.indexOf(iaeId);
    if(j > -1)AE.iUnmapped.splice(j, 1);
//    console.debug("Exiting Link : { eUnmapped :  [" + AE.eUnmapped.join(",") + "] , iUnmapped : [" + AE.iUnmapped.join(",") + "] , mapping : [" + AE.aeMappingHash.inspect()+ "] }");
};

function boostrapVariables(){
<tags:noform>
//rejected - external
<c:forEach var="ae" items="${command.rejectedExternalAeList}" varStatus="o" >
    var o_${o.index} = new AE.ae(${ae.id}, "${caaers:escapeJS(ae.externalID)}", ${ae.term.id}, "${caaers:escapeJS(ae.term.code)}",
            "${caaers:escapeJS(ae.term.name)}", "${caaers:escapeJS(ae.term.otherSpecify)}",
            "${caaers:escapeJS(ae.grade)}", "${ae.startDate}", "${ae.endDate}",
            "${caaers:escapeJS(ae.verbatim)}", "${caaers:escapeJS(ae.whySerious)}", "${caaers:escapeJS(ae.attribution)}", "${ae.source}", "${ae.rejected}") ;
    AE.externalAEHash.set(${ae.id}, o_${o.index});
    AE.eRejected.push(${ae.id});
</c:forEach>
            //unmapped - external
<c:forEach var="ae" items="${command.unMappedExternalAeList}" varStatus="p">
    var p_${p.index} = new AE.ae(${ae.id}, "${caaers:escapeJS(ae.externalID)}", ${ae.term.id}, "${caaers:escapeJS(ae.term.code)}",
            "${caaers:escapeJS(ae.term.name)}", "${caaers:escapeJS(ae.term.otherSpecify)}",
            "${caaers:escapeJS(ae.grade)}", "${ae.startDate}", "${ae.endDate}",
            "${caaers:escapeJS(ae.verbatim)}", "${caaers:escapeJS(ae.whySerious)}", "${caaers:escapeJS(ae.attribution)}", "${ae.source}", "${ae.rejected}") ;
    AE.externalAEHash.set(${ae.id}, p_${p.index});
    AE.eUnmapped.push(${ae.id});
</c:forEach>
            //unmapped - internal
<c:forEach var="ae" items="${command.unMappedInternalAeList}" varStatus="q">
    var q_${q.index} = new AE.ae(${ae.id}, "${caaers:escapeJS(ae.externalID)}", ${ae.term.id}, "${caaers:escapeJS(ae.term.code)}",
            "${caaers:escapeJS(ae.term.name)}", "${caaers:escapeJS(ae.term.otherSpecify)}",
            "${caaers:escapeJS(ae.grade)}", "${ae.startDate}", "${ae.endDate}",
            "${caaers:escapeJS(ae.verbatim)}", "${caaers:escapeJS(ae.whySerious)}", "${caaers:escapeJS(ae.attribution)}", "${ae.source}", "${ae.rejected}") ;
    AE.internalAEHash.set(${ae.id}, q_${q.index});
    AE.iUnmapped.push(${ae.id});
</c:forEach>
            //matched - internal and external
<c:forEach var="e" items="${command.matchedAeMapping}" varStatus="r">
    AE.aeMappingHash.set(${e.key.id}, ${e.value.id});
    var ir_${r.index} = new AE.ae(${e.key.id}, "${caaers:escapeJS(e.key.externalID)}", ${e.key.term.id}, "${caaers:escapeJS(e.key.term.code)}",
            "${caaers:escapeJS(e.key.term.name)}", "${caaers:escapeJS(e.key.term.otherSpecify)}",
            "${caaers:escapeJS(e.key.grade)}", "${e.key.startDate}", "${e.key.endDate}",
            "${caaers:escapeJS(e.key.verbatim)}", "${caaers:escapeJS(e.key.whySerious)}", "${caaers:escapeJS(e.key.attribution)}", "${e.key.source}", "${e.key.rejected}") ;
    AE.internalAEHash.set(${e.key.id}, ir_${r.index});
    var er_${r.index} = new AE.ae(${e.value.id}, "${caaers:escapeJS(e.value.externalID)}", ${e.value.term.id}, "${caaers:escapeJS(e.value.term.code)}",
            "${caaers:escapeJS(e.value.term.name)}", "${caaers:escapeJS(e.value.term.otherSpecify)}",
            "${caaers:escapeJS(e.value.grade)}", "${e.value.startDate}", "${e.value.endDate}",
            "${caaers:escapeJS(e.value.verbatim)}", "${caaers:escapeJS(e.value.whySerious)}", "${caaers:escapeJS(e.value.attribution)}", "${e.value.source}", "${e.value.rejected}") ;
    AE.externalAEHash.set(${e.value.id}, er_${r.index});

</c:forEach>

</tags:noform>
}
AE.ae = Class.create({
    initialize: function(id, externalId, termId, termCode, term, otherSpecify, grade, startDate, endDate, verbatim, whyserious, attribution, source, rejected ){
        this.id = id;
        this.externalId =  externalId;
        this.termId = termId;
        this.termCode = termCode;
        this.term = term;
        this.otherSpecify = otherSpecify;
        this.grade = grade;
        this.startDate = startDate;
        this.endDate = endDate;
        this.verbatim = verbatim;
        this.whySerious = whyserious;
        this.attribution = attribution;
        this.source = source;
        this.rejected = rejected;
        this.percent = '';
    },
    match:function(oae){
        var p = 0;
//        if(oae.externalId == this.externalId) return 100;
            if(oae.externalId == this.externalId) p = 46;
            if(oae.term == this.term) p = p+20;
            if(this.equals(oae.startDate , this.startDate)) p = p+ 16;
            if(this.equals(oae.endDate , this.endDate)) p = p+2;
            if(this.equals(oae.grade , this.grade)) p = p+ 10;
            if(this.equals(oae.attribution , this.attribution)) p = p+2;
            if(this.equals(oae.verbatim , this.verbatim)) p = p+2;
            if(this.equals(oae.whySerious , this.whySerious)) p = p+2;

        return '{' +
                'id : ' + this.id + ',' +
                'externalId : "' + this.externalId + '",' +
                'term : "' + this.term + '",'+
                'grade : "' + this.grade + '",'+
                'startDate : "' + this.startDate + '",' +
                'endDate : "' + this.endDate + '",'  +
                'verbatim : "' + this.verbatim + '",' +
                'whySerious : "' + this.whySerious + '",' +
                'attribution : "' + this.attribution + '",'  +
                'percent :' + p   +
                '}';

    },
    sortOnPercent: function(a, b){
        return b.percent - a.percent;
    } ,
    toSring: function(){
        return "id : " + this.id + ", externalId : " + this.externalId;
    },
    equals : function(s1, s2) {
        if(s1){
             if(s2){
                 return s1 == s2;
             }   else {
                 return false;
             }
        } else {
            if(s2){
                return false;
            } else {
                return true;
            }
        }
    }
});

AE.aeWidget = Class.create({
    initialize: function(widgetId, aeId1, aeId2){
        AE.activeAeWidgets.set(aeId1, this);
        AE.linkEvent.subscribe(this.onLinkEvent, this, true);
        AE.unlinkEvent.subscribe(this.onUnlinkEvent, this, true);
        AE.rejectEvent.subscribe(this.onRejectEvent, this, true);
        this.aePickerData = [];
        this.widgetId = widgetId;
        this.ae1 = AE.internalAEHash.get(aeId1);
        this.ae2 = AE.externalAEHash.get(aeId2);
        this.aePickerVisible = false;
        this.containerId = 'aewd-' + widgetId + '-t2div' ;
        this.iaeRowId = 'aewd-' + widgetId + '-tr-iae' ;
        this.eaeRowId = 'aewd-' + widgetId + '-tr-eae' ;
        this.noMatchRowId = 'aewd-' + widgetId + '-tr-nomatch' ;
        this.editBtnDiv = 'aewd-' + widgetId + '-div-edit';
        this.cancelBtnDiv = 'aewd-' + widgetId + '-div-cancel';
        this.linkBtnDiv = 'aewd-' + widgetId + '-div-link';
        this.unlinkBtnDiv = 'aewd-' + widgetId + '-div-unlink';
        this.findBtnDiv = 'aewd-' + widgetId + '-div-find';
        this.linkBtnId = 'aewd-' + widgetId + '-btn-link';
        this.unlinkBtnId = 'aewd-' + widgetId + '-btn-unlink';
    },
    iaeRowHighlight  : function(){
        $(this.iaeRowId).addClassName("aeWorkedOn")
    },
    iaeRowUnHighlight :function(){
        $(this.iaeRowId).removeClassName("aeWorkedOn")
    },
    hideEditButton :function(){
        $(this.editBtnDiv).hide();
        $(this.cancelBtnDiv).show();
    },
    showEditButton :function(){
        $(this.editBtnDiv).show();
        $(this.cancelBtnDiv).hide();
    },
    hideExternalAeRow :function(){
        $(this.eaeRowId).hide();
    },
    refreshExternalAeRow : function(){
        var tds = $$("#" + this.eaeRowId + " td.eae");

        if(tds){
            if(this.ae2){
                tds[0].innerHTML = this.ae2.source;
                tds[1].innerHTML = this.ae2.term;
                tds[2].innerHTML = this.ae2.grade;
                tds[3].innerHTML = this.ae2.startDate;
                tds[4].innerHTML = this.ae2.endDate;
                tds[5].innerHTML = this.ae2.verbatim;
                tds[6].innerHTML = this.ae2.whySerious;
                tds[7].innerHTML = this.ae2.attribution;
            }

        }
        this.showEditButton();
        $(this.eaeRowId).show();
    },
    hideNoMatchRow: function(){
        $(this.noMatchRowId).hide();
        $(this.findBtnDiv).hide();
        $(this.cancelBtnDiv).show();
    },
    showNoMatchRow: function(){
        $(this.noMatchRowId).show();
        $(this.findBtnDiv).show();
        $(this.cancelBtnDiv).hide();
    },
    definePickerTable : function(){
        var colDefs = [
            {key:"term", label: "Term", sortable:true, resizeable:true},
            {key:"grade", label: "Grade", sortable:true, resizeable:false},
            {key:"startDate", label: "Start",formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC},resizeable:false},
            {key:"endDate", label: "End",formatter:YAHOO.widget.DataTable.formatDate, sortable:true, sortOptions:{defaultDir:YAHOO.widget.DataTable.CLASS_DESC},resizeable:false},
            {key:"verbatim", label: "Verbatim", sortable:true, resizeable:true},
            {key:"whySerious", label: "Serious?", sortable:false, resizeable:false},
            {key:"attribution", label: "Attribution", sortable:false, resizeable:false},
            {key:"percent", label: "Match" ,formatter:AE.matchFormatter, sortable:true, resizeable:false} ,
            {key:"percent", label: "Score" , sortable:false, resizeable:false}
        ];
        var ds = new YAHOO.util.DataSource(this.aePickerData);
        ds.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;
        ds.responseSchema = {
            fields: ["id","term","grade","startDate","endDate", "verbatim", "whySerious", "attribution", "percent"]
        };

        this.aePickerTbl = new YAHOO.widget.ScrollingDataTable(this.containerId, colDefs, ds, {height:"12em", width:'890px'});
        this.aePickerTbl.set("selectionMode", "single");
        this.aePickerTbl.subscribe("rowClickEvent", this.onAePickerRowSelection, this, this);
        this.aePickerTbl.subscribe("rowMouseoverEvent", this.aePickerTbl.onEventHighlightRow, this, this);
        this.aePickerTbl.subscribe("rowMouseoutEvent", this.aePickerTbl.onEventUnhighlightRow, this, this);
    } ,
    generatePickerTableData : function(){
        var ueaeArr = [];
        this.aePickerData.length = 0;
        var ae1 = this.ae1;
        var ae2 = this.ae2;
        $A(AE.eUnmapped).each(function(i){
            var eae = AE.externalAEHash.get(i);
            var _str1 = eae.match(ae1)  ;
            ueaeArr.push(_str1.evalJSON());
        });
        ueaeArr.sort(ae1.sortOnPercent);
        if(ae2){
            var _str2 =   ae2.match(ae1);
            this.aePickerData.push(_str2.evalJSON());
        }
        this.aePickerData = this.aePickerData.concat(ueaeArr);
//        var _debugStr = " Widget : " + this.widgetId + " picker data [" ;
//        $A(this.aePickerData).each(function (x){ _debugStr = _debugStr + ", " + x.id + "->" + x.externalId});
//        _debugStr = _debugStr + "] \n UnMapped AE :" + AE.eUnmapped.inspect() ;
//        console.debug(_debugStr);

    },
    showAePickerRow : function(selectedAeId){
        this.aePickerVisible = true;
        $(this.containerId).show();
        if(!this.aePickerTbl){
            this.generatePickerTableData();
            this.definePickerTable();
        }else {
            this.aePickerTbl.onDataReturnInitializeTable(null, {results : this.aePickerData}, null)
        }

        if(selectedAeId){
            if(this.aePickerTbl){
                this.selectRowByAeId(selectedAeId);
            }
        }
        this.iaeRowHighlight();
    },
    hideAePickerRow : function(){
        this.aePickerVisible = false;
        $(this.containerId).hide();
        this.toggleLinkUnlinkBtn(null);
        this.iaeRowUnHighlight();
    },
    selectRowByAeId:function(aeId){
        var oRecords = this.aePickerTbl.getRecordSet().getRecords();
        var matchedAeId = null;
        for(i = 0; i < oRecords.length; i++){
            if(oRecords[i].getData("id") == aeId){
                this.aePickerTbl.selectRow(i);
                matchedAeId = aeId;
                break;
            }
        }
        this.toggleLinkUnlinkBtn(matchedAeId);
    },
    getNewlySelectedAe:function(){
        var sRows = this.aePickerTbl.getSelectedRows();
        if(sRows && sRows.length > 0){
            var sRow = sRows[0];
            var sRecord = this.aePickerTbl.getRecord(sRow);
            return sRecord.getData("id");
        }
        return null;

    },
    toggleLinkUnlinkBtn :function(ae2Id){

        var showLinkBtn = false;
        var showUnlinkBtn = false;
        if(ae2Id != null){
              if(this.ae2 != null){
                   if(this.ae2.id == ae2Id) {
                       showUnlinkBtn = true;
                   } else {
                       showLinkBtn = true;
                   }
              }  else {
                 showLinkBtn = true;
              }

        }
        showLinkBtn ?  $(this.linkBtnDiv).show() : $(this.linkBtnDiv).hide();
        showUnlinkBtn ?  $(this.unlinkBtnDiv).show() : $(this.unlinkBtnDiv).hide();

    },
    onAePickerRowSelection :function (oArg){
        this.aePickerTbl.onEventSelectRow.apply(this.aePickerTbl, arguments);
        var sRecord = this.aePickerTbl.getRecord(oArg.target);
        var selectedValue = sRecord.getData("id");
        this.toggleLinkUnlinkBtn(selectedValue);
    },

    onUnlinkEvent : function (e, oArgs){
        try{
            if(this.aePickerTbl) {
                this.generatePickerTableData();
            }
            if(this.aePickerVisible){
                var selectedAeId = this.getNewlySelectedAe();
                this.showAePickerRow(selectedAeId);
            }
        } catch (err){
            alert("error occurred" + err)
        }

    },
    onLinkEvent : function (e, oArgs){
        try{
            if(this.aePickerTbl) {
                this.generatePickerTableData();
            }
            if(this.aePickerVisible){
                var selectedAeId = this.getNewlySelectedAe();
                this.showAePickerRow(selectedAeId);
            }
        } catch (err){
            alert("error occurred" + err)
        }

    },
    onRejectEvent : function (e, oArgs){
        alert("onRejectEvent, " + this.widgetId + ", " + e + ", " + oArgs);
    },

    onUnlinkBtnClick : function(unlinkBtn){
        this.hideAePickerRow();
        this.showNoMatchRow();
        AE.unlink(this.ae1.id, this.ae2.id);
        if(this.ae2) {
            var curWidgetId = this.widgetId;
            var iaeId = this.ae1.id;
            var eaeId = this.ae2.id;
            this.ae2 = null;
            AE.unlinkEvent.fire({srcWidgetId : curWidgetId,  iae : iaeId, eaeOld : eaeId , eaeNew : null });
        }


    },
    onLinkBtnClick : function(linkBtn){

        var curWidgetId = this.widgetId;
        var iaeId = this.ae1.id;
        var oldAe2Id = this.ae2 ? this.ae2.id : null;
        var newAe2Id = this.getNewlySelectedAe();
        var eae = AE.externalAEHash.get(newAe2Id);
        this.ae2 = eae;
        this.refreshExternalAeRow();
        this.hideAePickerRow();
        AE.link(this.ae1.id, newAe2Id);
        AE.linkEvent.fire({srcWidgetId : curWidgetId, iae : iaeId , eaeOld : oldAe2Id ,  eaeNew : newAe2Id})
    },
    onEditBtnClick: function(theBtn){
        this.hideExternalAeRow();
        this.hideEditButton();
        this.showAePickerRow(this.ae2.id);

    },
    onFindBtnClick : function(fndBtn){
        this.hideNoMatchRow();
        this.showAePickerRow(null);
    },
    onCancelBtnClick : function(fndBtn){
        this.hideAePickerRow()
        if(this.ae2){
            this.refreshExternalAeRow();
        } else{
            this.showNoMatchRow();
        }
    }

});

boostrapVariables();
ValidationManager.submitPreProcess = function(){

    $('rejectedExternalAeStr').value = AE.eRejected.join('_');
    $('unmappedInternalAeStr').value = AE.iUnmapped.join('_');
    $('unmappedExternalAeStr').value = AE.eUnmapped.join('_');
    $('matchedAeMappingStr').value = AE.aeMappingHash.toQueryString();
    return false;
}


</script>

</head>
<body>
<tags:tabForm tab="${tab}" flow="${flow}" hideTabControls="${command.noExternalAes}">
<jsp:attribute name="singleFields">
<div class="eXtremeTable" >
    <table class="tableRegion" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
        <tr class="label" align="center">
            <td class="tableHeader" width="${widthSource}">&nbsp;&nbsp;&nbsp;</td>
            <td class="tableHeader" width="${widthTerm}"> Term</td>
            <td class="tableHeader" width="${widthGrade}">Grade</td>
            <td class="tableHeader" width="${widthStartDate}">Start</td>
            <td class="tableHeader" width="${widthEndDate}">End</td>
            <td class="tableHeader" width="${widthVerbatim}">Verbatim</td>
            <td class="tableHeader" width="${widthWhySerious}">Serious?</td>
            <td class="tableHeader" width="${widthAttribution}">Attribution</td>
            <td class="tableHeader" width="${widthActions}"></td>
        </tr>
        </thead>
        <tbody>

        <c:set var="_addFiller" value="false" />

        <c:forEach var="e" items="${command.matchedAeMapping}" varStatus="x">
            <c:if test="${_addFiller}">
                <ae:matchedAERow fillerRow="true"/>
            </c:if>
            <ae:matchedAERow ae1="${e.key}" ae2="${e.value}" />
            <c:set var="_addFiller" value="true" />
        </c:forEach>
        <c:forEach var="e" items="${command.unMappedInternalAeList}">
            <c:if test="${_addFiller}">
                <ae:matchedAERow fillerRow="true"/>
            </c:if>
            <ae:matchedAERow ae1="${e}" />
            <c:set var="_addFiller" value="true" />
        </c:forEach>

        </tbody>
    </table>
     <form:hidden id="matchedAeMappingStr" path="matchedAeMappingStr"  />
     <form:hidden id="unmappedInternalAeStr" path="unmappedInternalAeStr"  />
     <form:hidden id="unmappedExternalAeStr" path="unmappedExternalAeStr"  />
     <form:hidden id="rejectedExternalAeStr" path="rejectedExternalAeStr"  />
    </jsp:attribute>
    </tags:tabForm>
</body>
</html>