package features.steps

import static cucumber.runtime.groovy.Hooks.*
import features.geb.pages.DashboardPage
import features.geb.pages.EditAgentPage
import features.geb.pages.LoginPage
import features.geb.pages.SearchAgentPage
import geb.Browser
import geb.binding.BindingUpdater

import org.openqa.selenium.StaleElementReferenceException

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

def bindingUpdater

Before(){
//	def browser = new Browser(driver: new ChromeDriver())
//	def browser = new Browser(driver: new FirefoxDriver())
//	def browser = new Browser(driver: new HtmlUnitDriver(true))
	def browser = new Browser()
	bindingUpdater = new BindingUpdater (binding, browser)
	bindingUpdater.initialize()
	def agent = null
}

After(){
	bindingUpdater.remove()
}

Given(~'^I am logged in as (.*)$') { String role->
	to LoginPage
	at LoginPage
	page.login role
	at DashboardPage
}

When(~'^I am on search agent page$') { ->
	to SearchAgentPage
	at SearchAgentPage
}

When(~'^I search with agent identifier \'(\\w+)\'$') { String agentId ->
	page.searchAgentIdentifier agentId
}

Then(~'^I should see agent with name \'(.*)\'$') { String agentName ->
	agent = page.findInResult agentName
	assert agent != null
}

When(~'^I click on the agent$') { ->
	agent.click()
}

Then(~'^I should see edit agent page$') { ->
	at EditAgentPage
}

When(~'^I enter terminology \'(.*)\'$') { String terminology->
	page.terminology.value terminology
}

When(~'^I enter CTC version \'(.*)\'$') { String version ->
	waitFor { page.ctcVersion.displayed }
	try{
		page.ctcVersion.value version
	}catch(StaleElementReferenceException e){
		e.printStackTrace()
	}
}

Then(~'^I should see the autosuggest box for adding ASAEL$') { ->
	waitFor { page.asaelAutoSuggest.present }
}

When(~'^I add AE with term \'(.*)\'$') { String asael ->
	page.addASAEL asael
}

When(~'^I click save$') { ->
	page.saveButton.click()
}

Then(~'^I should see \'(.*)\' message$') { String message->
	assert page.saveMessage.text() == message
}

