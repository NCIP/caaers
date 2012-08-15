Feature: In order report adverse events with interventions
	Users should be able to add ASAELs
	
	Background:
		Given I am logged in as ADMIN
	
	Scenario: Add Single Expected AE to Agent
		When I am on search agent page
		And I search with agent identifier '723227'
		Then I should see agent with name '(161-180)ESO-1 Peptide'
		When I click on the agent
		Then I should see edit agent page
		When I enter terminology 'CTCAE'
		And I enter CTC version '3.0'
		Then I should see the autosuggest box for adding ASAEL
		When I add AE with term 'Adrenal insufficiency'
		And I click save
		Then I should see 'Information saved successfully' message		