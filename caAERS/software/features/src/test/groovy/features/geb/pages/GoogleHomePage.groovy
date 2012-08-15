package features.geb.pages

import geb.Page
import groovy.lang.MetaClass;

class GoogleHomePage extends Page {
	static url = "http://google.com/?complete=0"
	static at = { title == "Google" }
	static content = {
		searchField { $("input[name=q]") }
//		searchButton(to: GoogleResultsPage) { $("input[value='Google Search']") }
	}
}