//Static Slide Menu 6.5 © MaXimuS 2000-2001, All Rights Reserved.
//Site: http://www.absolutegb.com/maximus
//Script featured on Dynamic Drive (http://www.dynamicdrive.com)

NS6 = (document.getElementById && !document.all)
IE = (document.all)
NS = (navigator.appName == "Netscape" && navigator.appVersion.charAt(0) == "4")

tempBar = '';
barBuilt = 0;
ssmItems = new Array();


function dismissLab(labId){

	createAE.dismissLab(labId);

    // update the JS array value
    for (i = 0; i < ssmItems.length; i++){
		if(ssmItems[i][4] == labId){
			ssmItems[i][5] = "true";
		}
	}
    
    vis--;
    $('a'+labId).parentNode.parentNode.style.display = "none";

    if(vis == 0){
		$('basessm').hide();
	}
}

function truebody() {
    return (document.compatMode!="BackCompat")? document.documentElement : document.body
}

moving = setTimeout('null', 1)
function moveOut() {
    if ((NS6 || NS) && parseInt(ssm.left) < 0 || IE && ssm.pixelLeft < 0) {
        clearTimeout(moving);
        moving = setTimeout('moveOut()', slideSpeed);
        slideMenu(10)
    }
    else {
        clearTimeout(moving);
        moving = setTimeout('null', 1)
    }
}
;
function moveBack() {
    clearTimeout(moving);
    moving = setTimeout('moveBack1()', waitTime)
}
function moveBack1() {
    if ((NS6 || NS) && parseInt(ssm.left) > (-menuWidth) || IE && ssm.pixelLeft > (-menuWidth)) {
        clearTimeout(moving);
        moving = setTimeout('moveBack1()', slideSpeed);
        slideMenu(-10)
    }
    else {
        clearTimeout(moving);
        moving = setTimeout('null', 1)
    }
}
function slideMenu(num) {
    if (IE) {
        ssm.pixelLeft += num;
    }
    if (NS6) {
        ssm.left = parseInt(ssm.left) + num + "px";
    }
    if (NS) {
        ssm.left = parseInt(ssm.left) + num;
        bssm.clip.right += num;
        bssm2.clip.right += num;
    }
}

function makeStatic() {
    if (NS || NS6) {
        winY = window.pageYOffset;
    }
    if (IE) {
        winY = truebody().scrollTop;
    }
    if (NS6 || IE || NS) {
        if (winY != lastY && winY > YOffset - staticYOffset) {
            smooth = .2 * (winY - lastY - YOffset + staticYOffset);
        }
        else if (YOffset - staticYOffset + lastY > YOffset - staticYOffset) {
            smooth = .2 * (winY - lastY - (YOffset - (YOffset - winY)));
        }
        else {
            smooth = 0
        }
        if (smooth > 0) smooth = Math.ceil(smooth);
        else smooth = Math.floor(smooth);
        if (IE) bssm.pixelTop += smooth;
        if (NS6) bssm.top = parseInt(bssm.top) + smooth + "px"
        if (NS) bssm.top = parseInt(bssm.top) + smooth
        lastY = lastY + smooth;
        setTimeout('makeStatic()', 1)
    }
}

function buildBar() {
    document.write('<td align="center" width="' + barWidth + '" bgcolor="' + barBGColor + '" valign="' + barVAlign + '" style="background:url(../../images/sidebar/bar.gif) no-repeat top left"><p align="center"><font face="' + barFontFamily + '" Size="' + barFontSize + '" COLOR="' + barFontColor + '"><B>' + tempBar + '</B></font></p></td>')
}

function initSlide() {
    if (NS6) {
        ssm = document.getElementById("thessm").style;
        bssm = document.getElementById("basessm").style;
        bssm.clip = "rect(0 " + document.getElementById("thessm").offsetWidth + " " + document.getElementById("thessm").offsetHeight + " 0)";
        ssm.visibility = "visible";
    }
    else if (IE) {
        ssm = document.all("thessm").style;
        bssm = document.all("basessm").style
        bssm.clip = "rect(0 " + thessm.offsetWidth + " " + thessm.offsetHeight + " 0)";
        bssm.visibility = "visible";
    }
    else if (NS) {
        bssm = document.layers["basessm1"];
        bssm2 = bssm.document.layers["basessm2"];
        ssm = bssm2.document.layers["thessm"];
        bssm2.clip.left = 0;
        ssm.visibility = "show";
    }
    if (menuIsStatic == "yes") makeStatic();
}

function buildMenu(n, headerName) {
    
    if(vis > 0){
        // n = n;
	    if (IE || NS6) {
	        document.write('<DIV ID="basessm" style="visibility:hidden;Position : Absolute ;Left : ' + XOffset + 'px ;Top : ' + YOffset + 'px ;Z-Index : 20;width:' + (menuWidth + barWidth + 10) + 'px"><DIV ID="thessm" style="Position : Absolute ;Left : ' + (-menuWidth) + 'px ;Top : 0 ;Z-Index : 20;" onmouseover="moveOut()" onmouseout="moveBack()">')
	    }
	
	    if (NS) {
	        document.write('<LAYER name="basessm1" top="' + YOffset + '" LEFT=' + XOffset + ' visibility="show"><ILAYER name="basessm2"><LAYER visibility="hide" name="thessm" bgcolor="' + menuBGColor + '" left="' + (-menuWidth) + '" onmouseover="moveOut()" onmouseout="moveBack()">')
	    }
	
	    if (NS6) {
	        document.write('<table border="0" cellpadding="0" cellspacing="0" width="' + (menuWidth + barWidth + 0) + 'px"><TR><TD>')
	    }
	    
	    document.write('<table border="0" cellpadding="0" cellspacing="0" width="' + (menuWidth + barWidth) + 'px">');
	
	    // header
	    document.write("<tr><td class=\"ssmItems\" style='background:url(../../images/sidebar/header.gif); padding-left:7px; border-right:1px " + menuBGColor + " solid; height:18px;'><b>" + headerName + "</b></td><tr>");
	    document.write("<tr><td bgcolor='" + linkBGColor + "' style='border:1px " + menuBGColor + " solid;' valign='top'>");
	
	    document.write('<table border="0" cellpadding="0" cellspacing="0" width="' + (menuWidth-2) + '">');
		document.write('<TR bgcolor="' + hdrBGColor + '" height=' + hdrHeight + '>')
			document.write('<TD><b>Lab</b></TD>');
			document.write('<TD><b>Date</b></TD>');
			document.write('<TD><b>Value</b></TD>');
			document.write('<TD><b>Unit</b></TD>');
			document.write('<TD></TD>');
		    document.write('</TR>');
		
	 	for (i = 0; i < ssmItems.length; i++){

            if (ssmItems[i][5] == "true") st = "style='display:none;'"; else st = "";
            document.write('<TR ' + st + ' onMouseOver="bgColor=\'' + linkOverBGColor + '\'" onMouseOut="bgColor=\'' + linkBGColor + '\'" bgColor=\"' + linkBGColor + '\">');
			for(j=0; j<4; j++){
//				document.write('<td HEIGHT="' + hdrHeight + 'px" ' + 'ALIGN="' + hdrAlign + '" ' + 'VALIGN="' + hdrVAlign + '" ' + 'WIDTH="' + ssmItems[1][0] + '" ' + 'COLSPAN="' + ssmItems[1][0] + '">&nbsp;<font face="' + linkFontFamily + '" Size="' + linkFontSize + '" ' + 'COLOR="' + linkFontColor + '"><b>' + ssmItems[i][j] + '</b></font></td>');
				document.write('<td>&nbsp;<font size=' + linkFontSize + '>' + ssmItems[i][j] + '</font></td>');
			}
			document.write('<td align="left" valign="top"><a id="a'+ ssmItems[i][4] + '" href="#" onClick="dismissLab('+ssmItems[i][4]+')"><img src="../../images/checkno.gif" border="0"></a></td>');
			document.write('</TR>');
		}
	
	    document.write('</table>');
	
	    document.write('</td>');
	    document.write('<td align="left" valign="top"><img src="../../images/sidebar/bar.gif"></td>');
	    document.write('</table>');
	
	    if (NS6) {
	        document.write('</TD></TR></TABLE>')
	    }
	
	    if (IE || NS6) {
	        document.write('</DIV></DIV>')
	    }
	
	    if (NS) {
	        document.write('</LAYER></ILAYER></LAYER>')
	    }
	
	    theleft = -menuWidth;
	    lastY = 0;
	    setTimeout('initSlide();', 1)
	}
}
