Feature: In order report adverse events with interventions
	Users should be able to add ASAELs
	
	Background:
		Given I am logged in as ADMIN
	
	Scenario: Add Single Expected AE to Agent
		When I am on search agent page
		And I search agent with identifier value '723227'
		Then I should see agent with name '(161-180)ESO-1 Peptide'
		When I click on the agent
		Then I should see edit agent page
		When I enter terminology with value 'CTCAE'
		And I enter ctc version with value '3.0'
		Then I should see the asael auto suggest field
		When I add AE with term 'Adrenal insufficiency'
		And I click save
		Then I should see 'Information saved successfully' message		