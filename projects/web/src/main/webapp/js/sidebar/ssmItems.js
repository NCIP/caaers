<!--

/*
Configure menu styles below
NOTE: To edit the link colors, go to the STYLE tags and edit the ssm2Items colors
*/
YOffset = 150; // no quotes!!
XOffset = 0;
staticYOffset = 30; // no quotes!!
slideSpeed = 20 // no quotes!!
waitTime = 100; // no quotes!! this sets the time the menu stays out for after the mouse goes off it.
menuBGColor = "#999999";
menuIsStatic = "yes"; //this sets whether menu should stay static on the screen
menuWidth = 320; // Must be a multiple of 10! no quotes!!
menuCols = 2;
hdrFontFamily = "verdana";
hdrFontSize = "2";
hdrFontColor = "white";
hdrBGColor = "#658dbf";
hdrAlign = "left";
hdrVAlign = "center";
hdrHeight = "15";
linkFontFamily = "Verdana";
linkFontSize = "1";
linkBGColor = "#eeeeee";
linkBGColorSelected = "#417691";
linkOverBGColor = "#417691";
linkTarget = "";
linkAlign = "Left";
barBGColor = "#999999";
barFontFamily = "Verdana";
barFontSize = "2";
barFontColor = "white";
barVAlign = "center";
barWidth = 20; // no quotes!!
barText = "TABS"; // <IMG> tag supported. Put exact html for an image to show.

///////////////////////////
     
// the items are genearted runtime in the [V]iew

// ssmItems[...]=[name, link, target, colspan, endrow?] - leave 'link' and 'target' blank to make a header
/*
ssmItems[0] = ["Create registration"] //create header
ssmItems[1] = ["Select Subject & Study", "#", ""]
ssmItems[2] = ["Select Subject & Study", "#", ""]
ssmItems[3] = ["Select Subject & Study", "#", ""]
ssmItems[4] = ["Select Subject & Study", "#", ""]
*/

//ssmItems[4] = ["Message Forum", "http://www.codingforums.com", "_new"]
//ssmItems[7] = ["FAQ", "http://www.dynamicdrive.com/faqs.htm", "", 1, "no"] //create two column row
//ssmItems[8] = ["Email", "http://www.dynamicdrive.com/contact.htm", "",1]

//-->

    //ssmItems[0]=["Lab","Date","Value","Unit", "#", ""]
    //ssmItems[1]=["ANC","7/29/2007","3.73","mm3", "#", ""]
	buildMenu(0, "");
	