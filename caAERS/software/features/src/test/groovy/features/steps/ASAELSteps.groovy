package features.steps

import static cucumber.runtime.groovy.Hooks.*

import org.openqa.selenium.StaleElementReferenceException

import features.geb.pages.EditAgentPage

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Then(~'^I should see agent with name \'(.*)\'$') { String agentName ->
	agent = page.findInResult agentName
	assert agent != null
}

When(~'^I click on the agent$') { ->
	agent.click()
}

When(~'^I search agent with identifier value \'(\\w+)\'$') { String value ->
	page.searchAgentIdentifier value
}

When(~'^I add AE with term \'(.*)\'$') { String asael ->
	page.addASAEL asael
}