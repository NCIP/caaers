jQuery(function ($) {

/*
    $.fn.getTextAreaHack = function () {
        return new ieTextAreaHack(this);
    };

*/
    ieTextAreaHack = function(target) {
        this.target = jQuery(target);
        this.keyCodeArray = [];
        this.lineArray = [];
        this.leftPos = 0;
        this.autoUpdatePosition = true;
        this.maxLength = 0;
        this.caretPosition = null;
        this.lastPosition = true;
        this.autoKeyCodeArray = true;
        this.trailingEnterCount = 0;
        this.oldCaret = 0;
        this.hasPreviousSelection = false;
        this.selectionStartFrom = 0;
        this.selectionEndTo = 0;
        this.selectionLength = 0;
        this.init();
    };

    ieTextAreaHack.prototype = {
        caretInsideTrailingEnter:function () {
            return (this.trailingEnterCount > 0 &&
                this.caretPosition > this.maxLength - this.trailingEnterCount)
        },
        atTheEnd:function () {
            return this.maxLength == this.caretPosition
        },
        insertKeyCodeArray:function (code) {
            var kca = [];
            for (var i = 0; i < this.caretPosition; i++)
                kca.push(this.keyCodeArray[i]);
            kca.push(code);
            for (var i = this.caretPosition; i < this.keyCodeArray.length; i++)
                kca.push(this.keyCodeArray[i]);
            this.keyCodeArray = kca;
            return this
        },
        rowFromOriginalPosition:function () {
            var s2 = this.originalStart + 1, lineNum = 0;
            while (s2 > 0) s2 -= this.lineArray[lineNum++] + 2;
            if (lineNum > 0)lineNum--;
            return lineNum;
        },
        rowFromCurentPosition:function () {
            var s2 = this.caretPosition + 1, lineNum = 0;
            while (s2 > 0) s2 -= this.lineArray[lineNum++] + 1;
            if (lineNum > 0)lineNum--;
            return lineNum;
        },
        column:function () {
            var s2 = this.caretPosition + 1,
                s3 = s2,
                lineNum = 0;
            while (s2 > 0) {
                s3 = s2;
                s2 -= this.lineArray[lineNum++] + 1;
            }
            if (lineNum > 0)lineNum--;
            return s3 - 1;
        },
        caret:function (lineNum, leftPos) {
            var sp = 1,
                lineNum = (typeof lineNum == "undefined") ? this.rowFromCurentPosition() : lineNum,
                leftPos = (typeof leftPos == "undefined") ? this.column() : leftPos;
            for (var i = 0; i < lineNum; i++) sp += this.lineArray[i] + 1;
            return sp + leftPos - 1;
        },
        moveUp:function () {
            var lineNum = this.rowFromCurentPosition(),
                leftPos = this.column();
            if (lineNum > 0) lineNum--;
            if (leftPos > this.lineArray[lineNum])
                leftPos = this.lineArray[lineNum];
            return this.caret(lineNum, leftPos);
        },
        moveDown:function () {
            var lineNum = this.rowFromCurentPosition(),
                leftPos = this.column();
            if (lineNum < this.lineArray.length - 1) lineNum++;
            if (leftPos > this.lineArray[lineNum])
                leftPos = this.lineArray[lineNum];
            return this.caret(lineNum, leftPos);
        },
        updateLineArray:function () {
            var la = [], d = 0, kca = this.keyCodeArray;
            for (var i = 0; i < kca.length; i++) {
                if (kca[i] == 13) {
                    la.push(d);
                    d = 0;
                } else
                    d++;
            }
            if (kca[kca.length] == 13)la.push(0);
            else la.push(d);
            this.lineArray = la;
        },

        updateContent:function () {
/*
            if (this.autoKeyCodeArray) {
                var d = 0, c = 0, kca = [];
                this.target.html().replace(/[\s\S]/g, function (s) {
                    c = s.charCodeAt(0);
                    if (c != 10)
                        kca.push(s.charCodeAt(0));
                });
                for (var i = 0; i < this.trailingEnterCount; i++)
                    kca.push(13);
                this.maxLength = kca.length;
                this.keyCodeArray = kca;
            }
            this.updateLineArray();
*/
        },

        calOriginalPos:function () {
            var range = document.selection.createRange();
            var stored_range = range.duplicate();
            this.originalText = stored_range.text;
            stored_range.moveToElementText(this.target[0]);
            stored_range.setEndPoint('EndToEnd', range);
            this.originalStart = stored_range.text.length - range.text.length;
            this.selectionLength = range.text.length;
            this.originalEnd = this.originalStart + this.selectionLength;
        },
        updatePosByOriginalPos:function () {
            var row = this.rowFromOriginalPosition();
            this.caretPosition = this.originalStart - row;
            this.selectionStart = this.originalStart - row;
            this.selectionEnd = this.originalEnd - row;
            this.callBack(this);
        },
        updatePos:function () {
            if (this.autoUpdatePosition) {
                var row = this.rowFromOriginalPosition();
                this.caretPosition = this.originalStart - row;
                this.callBack(this);
            }
            if (this.caretPosition != this.oldCaret) {
                if (this.selectionLength > 0) {
                    if (this.hasPreviousSelection == false) {
                        this.hasPreviousSelection = true;
                        this.selectionStartFrom = this.oldCaret;
                    }
                    this.selectionEndTo = this.caretPosition;
                    if (this.selectionEndTo < this.selectionStartFrom) {
                        this.selectionStart = this.selectionEndTo;
                        this.selectionEnd = this.selectionStartFrom;
                    } else {
                        this.selectionStart = this.selectionStartFrom;
                        this.selectionEnd = this.selectionEndTo;
                    }
                } else {
                    this.hasPreviousSelection = false;
                    this.selectionStart = this.caretPosition;
                    this.selectionEnd = this.caretPosition;
                }
                this.oldCaret = this.caretPosition;
                this.callBack(this);
            }
        },
        caretChange:function (callBack) {
            this.callBack = callBack;
            return this
        },
        keyCodeInfo:function () {
            var kca = [], lineNum, leftPos;
            for (var i = 0; i < this.caretPosition; i++)
                kca.push(this.keyCodeArray[i] + ((this.keyCodeArray[i] == 13) ? "<br/>" : ""));
            kca.push("|");
            for (var i = this.caretPosition; i < this.keyCodeArray.length; i++)
                kca.push(this.keyCodeArray[i] + ((this.keyCodeArray[i] == 13) ? "<br/>" : ""));
            return "<span>" + kca.join(" ") + "</span>"
        },
        init:function () {
            var t = this;
            this.updateContent();
            this.target.keydown(function (e) {
                var k = e.which;
                t.calOriginalPos();
                jQuery("span#result3").text(k);
                t.autoKeyCodeArray = false;
                if (k == 37 && t.selectionLength == 0) {//left
                    t.caretPosition--;
                    if (t.caretPosition < 0)t.caretPosition = 0;
                    t.autoUpdatePosition = false;
                } else if (k == 39 && t.selectionLength == 0) {//right
                    t.caretPosition++;
                    if (t.caretPosition > t.maxLength)t.caretPosition = t.maxLength;
                    t.autoUpdatePosition = false;
                } else if (k == 13 && t.selectionLength == 0) {//enter
                    t.autoUpdatePosition = false;
                    if (t.atTheEnd()) {
                        t.keyCodeArray.push(13);
                        t.lineArray.push(0);
                        t.trailingEnterCount++;
                    } else {
                        t.insertKeyCodeArray(13);
                    }
                    t.maxLength++;
                    t.caretPosition++;
                } else if (k == 38 && t.selectionLength == 0) {//up
                    t.caretPosition = t.moveUp();
                    t.autoUpdatePosition = false;
                } else if (k == 40 && t.selectionLength == 0) {//down
                    t.caretPosition = t.moveDown();
                    t.autoUpdatePosition = false;
                } else if (k == 8 && t.caretInsideTrailingEnter() && !this.hasPreviousSelection) {//backspace
                    t.autoKeyCodeArray = true;
                    t.caretPosition--;
                    t.trailingEnterCount--;
                    t.autoUpdatePosition = false;
                } else {
                    if (k == 16)//shift
                        t.autoUpdatePosition = false;
                    else {
                        if (t.atTheEnd()) {
                            t.trailingEnterCount = 0;
                            t.autoKeyCodeArray = true;
                            t.updateContent()
                            t.autoUpdatePosition = true;
                        } else if (t.caretInsideTrailingEnter()) {
                            t.trailingEnterCount = t.maxLength - t.caretPosition;
                            t.autoKeyCodeArray = true;
                            t.autoUpdatePosition = false;
                            t.caretPosition++;
                        } else {
                            t.autoKeyCodeArray = true;
                            t.updateContent()
                            t.autoUpdatePosition = true;
                        }
                    }
                }
            }).keyup(function (e) {
                    t.calOriginalPos();
                    if (e.which != 116) {
                        t.updateContent();
                        t.updatePos();
                    }
                }).click(function () {
                    t.calOriginalPos();
                    t.updateContent();
                    t.updatePosByOriginalPos();
                }).mousedown(function () {
                    t.updateContent();
                });
        }
    };
    /*
         Example:
         jQuery("document").ready(function(){
         var th=new ieTextAreaHack(jQuery("textarea"));
         th.caretChange(function(){
         qa(th);
         });
         function qa(th){
         jQuery("span#CalculatedCaret").text(th.caretPosition);
         jQuery("span#result").html(th.keyCodeInfo());
         jQuery("span#result4").text(th.lineArray.join(" "));
         jQuery("span#result5").text((th.autoUpdatePosition)?"Original":"Calculation");
         jQuery("span#result6").text((th.atTheEnd())?"true":"false");
         jQuery("span#result7").text(th.maxLength);
         jQuery("span#result8").text(th.trailingEnterCount);
         jQuery("span#result9").text(th.caretInsideTrailingEnter());
         jQuery("span#result10").text(th.rowFromCurentPosition());
         jQuery("span#result11").text(th.column());
         jQuery("span#result13").text(th.originalStart);
         jQuery("span#result14").text(th.originalText);
         jQuery("span#result15").text("("+th.selectionStart+","+th.selectionEnd+")");
         jQuery("span#result16").text(th.originalEnd);
         jQuery("span#result17").text(th.selectionLength);
         }
         });
     */
});
