package features.steps
import static cucumber.runtime.groovy.Hooks.*

import org.openqa.selenium.StaleElementReferenceException

import features.geb.pages.*
import geb.Browser
import geb.binding.BindingUpdater

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

def bindingUpdater

Before(){
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

When(~'^I am on (.+) page$') { String pageName->
	page = new GroovyClassLoader().loadClass(generateClassName(pageName))
	to page
	at page
}

When(~'^I enter (.+) with value \'(.*)\'$') { String field, String value->
	waitFor { page."${camelize(field)}".displayed }
	try{
		page."${camelize(field)}".value value
	}catch(StaleElementReferenceException e){
		e.printStackTrace()
	}
}

When(~'^I click save$') { ->
	page.saveButton.click()
}

Then(~'^I should see \'(.*)\' message$') { String message->
	assert page.saveMessage.text() == message
}

Then(~'^I should see the (.+) field$') { String fieldName->
	waitFor { page."${camelize(fieldName)}".present }
}

Then(~'^I should see (.+) page$') { String pageName->
	page = new GroovyClassLoader().loadClass(generateClassName(pageName))
	at page
}

def	generateClassName(page){
	"features.geb.pages.${camelize(page, true)}Page"
}

def generateMethodName(methodName){
	camelize(methodName)
}

def camelize(cmdName, firstUpper = false) {
	def words = cmdName.split(" ");
	def firstWord = firstUpper ? capitalize(words[0]) : words[0]
	if (words.size() == 1) return firstWord
	return firstWord + words[1..(words.size() -1)].collect({capitalize(it)}).join()
}

def capitalize(String a){
	String s = a.substring(1)
	return "${a[0].toUpperCase()}${s.toLowerCase()}"
}
	
	