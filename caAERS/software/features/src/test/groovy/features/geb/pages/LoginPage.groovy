package features.geb.pages

import geb.Page
import groovy.lang.MetaClass;

class LoginPage extends Page {

	static url = "public/login"
	
	static at = {title == "caAERS || Enter caAERS"}
	
	static content = {
		username { $("input[name=j_username]") }
		password { $("input[name=j_password]") }
		loginButton { $("input[value='Log in']") }
	}
	
	def login(role){
		if(role.equals("ADMIN")){
			username().value "mayo-super-user"
			password().value "Hello-12"
			loginButton().click()
		}
	}
}
