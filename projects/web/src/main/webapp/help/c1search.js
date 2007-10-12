// global members
var _d2hSecondaryWindowsByTopics = null;
function d2hInitSecondaryWindows()
{
    if (_d2hSecondaryWindowsByTopics == null)
    {
        _d2hSecondaryWindowsByTopics = new Array();
        _d2hSecondaryWindowsByTopics["worddocuments/gloss_term1.htm"] = "d2hWnd_SecondaryPopup";
_d2hSecondaryWindowsByTopics["worddocuments/gloss_term2.htm"] = "d2hWnd_SecondaryPopup";

    }
}

function newDocContext(strTitle, strData)
{
    var elem = "<html><head>"
    if (strTitle != "")
        elem += "<title>" + strTitle + "</title>";
    elem += "</head><body>";
    elem += strData;
    elem += "</body></html>";
    document.clear();
    document.write(elem);
}

function jSearch(doc, strQuery)
{
    display(doc, jExecQuery(strQuery));
}

function jExecQuery(strQuery)
{
	strQuery = strQuery.replace(/\s+|\.|,|\!|"|;|'|\(|\)|\{|\}|\[|\]|\||&|-|\+|=|\/|\\|<|>/g, " ").replace(/^\s+/g, "").replace(/\s+$/g, "").toLowerCase();
    var terms = removeRepeatingTerms(strQuery.split(" "));
    var arr = new Array();
	var strRealQuery = "";
    for (var i = 0; i < terms.length; i++)
    {
        var term = terms[i];
		strRealQuery += term + " ";
        var h = searchInIndex(term);
        if (h)
            arr = arr.concat(h);
    }
    arr = calcHistogram(arr);
    setQuery2EditBox(strRealQuery);
	return getQueryResult(arr);
}

function setQuery2EditBox(query)
{
    var frm = getFrameByName("textprovider");
    if (frm != null)
    {
        var doc = getFrameDocument(frm);
        if (doc != null)
        {
            var elmSearch = getElemById(doc, "query");
            if (elmSearch != null)
                elmSearch.value = query.replace(/\s+$/g, "");
        }
    }
}

function searchInIndex(term)
{
    var wildcard = isWildcard(term);
    if (wildcard)
    {
        var re = new RegExp(getWildcardRegexp(term), "gi");
        var indx;
        var res = null;
        for(var word in g_sWords)
        {
            indx = word.search(re);
            if (indx > -1)
            {
                if (res)
                    res = res.concat(g_sWords[word]);
                else
                    res = g_sWords[word];
            }
        }
        return res;
    }
    else
        return g_sWords[term];
}

function isWildcard(term)
{
    return term.indexOf("?") > -1 || term.indexOf("*") > -1;
}

function removeRepeatingTerms(terms)
{
    var htbl = new Array();
    var res = new Array();
    for (var i = 0; i < terms.length; i++)
        if (!htbl[terms[i]])
        {
            res[res.length] = terms[i];
            htbl[terms[i]] = true;
        }
    return res;
}

function calcHistogram(arr)
{
    var tbl = new Array();
    var id;
    for (var i = 0; i < arr.length; i++)
    {
        id = "x" + arr[i][0];
        if (tbl[id])
        {
            tbl[id][1] += arr[i][1];
            arr[i] = null;
        }
        else
            tbl[id] = arr[i];
    }
    arr.sort(sortByCounterNumber);
    return arr;
}

function sortByCounterNumber(x, y)
{
    if (x == null)
        return 1;
    if (y == null)
        return -1;
    var delta = x[1] - y[1];
    if (delta == 0)
        return 0;
    if (delta < 0)
        return 1;
    return -1;
}
function getQueryResult( arr)
{
    var res = "";
    for (var i = 0; i < arr.length; i++)
        if (arr[i])
            res += getResultItem(i, arr[i]);
    if (res.length == 0)
        res = "No topics found.";
    return res;
}

function getResultItem(id, item)
{
    var res = "";
    try
    {
        var td = g_sTopics[item[0]];
        if (td)
        {
            res = "<div nowrap class=\"clsSearchResultItem\"><a id=\"ri-" + id + "\" href=\"";
            res += td[0];

            res += "\" target=\"right\"";

            res += "onclick=\"return d2hSearchItemSelect(\'" + td[0] + "\', event)\" onmouseover=\"d2hItemOver(event)\" onmouseout=\"d2hItemOut(event)\">";
            res += td[1] + "</a></div>";
        }
    }
    catch (e)
    {
        res = "";
    }
    return res;
}

function display(doc, content)
{
    var elem = doc.forms[1];
    if (elem.parentNode)
        elem.parentNode.setAttribute("nowrap", true);
    waitCursor(doc, true);
    elem.innerHTML = content;
    waitCursor(doc, false);
}

function waitCursor(doc, isWait)
{
}
