package features.geb.pages

import geb.Page
import groovy.lang.MetaClass;

class EditAgentPage extends Page {

	static at = {$(".tab.selected div a", 0).text() == "Enter / Edit Agent"}
	
	static content = {
		terminology { $("select[name=terminology]") }
		ctcVersion { $("select[name=ctcVersion]") }
		asaelAutoSuggest(required: false) { $("input[id=termCode-input]") }
		asaelChoices { $("div#termCode-choices.autocomplete ul li") }
		addAsaelButton { $("button", id: "addSingleTermBtn") }
		saveButton { $("button", id: "flow-next") }
		saveMessage { $("div.message p") }
	}
	
	def addASAEL(asael){
		asaelAutoSuggest << asael
		waitFor {asaelChoices.displayed}
		asaelChoices[0].click()
		addAsaelButton.click()
	}
	
}
