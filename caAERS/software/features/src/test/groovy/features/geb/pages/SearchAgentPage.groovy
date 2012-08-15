package features.geb.pages

import geb.Page
import groovy.lang.MetaClass;

class SearchAgentPage extends Page {

	static url = "pages/admin/asaelSearch"
	
	static at = {$(".tab.selected div a", 0).text() == "Search Agents"}
	
	static content = {
		agentName { $("input[name=name]") }
		agentIdentifier { $("input[name=nsc]") }
		//searchAgentButton { $("form", name: "searchForm").find("button") }
		searchAgentButton { $("#searchForm button.omnipotent-button") }
		results(required: false) { $("tbody.yui-dt-data div.yui-dt-liner a") }
	}
	
	def searchAgentIdentifier(agentId){
		agentIdentifier().value agentId
		searchAgentButton().click()
		waitFor { results.present }
	}
	
	def findInResult(agentName){
		results.find{it.text() == agentName}
	}
}
