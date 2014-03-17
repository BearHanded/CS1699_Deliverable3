Feature: Stat Controller
	
	Scenario: Add A New Stat
		Given a user obtains a new score
		When a user adds their recent score
		Then the new score should be saved

	Scenario: Update A User Stat
		Given a user has a score in the system
		When a user adds their recent score
		Then the stat should be cumulative of the current and old stat

	Scenario: Replace A User Stat
		Given a user has a score in the system
		When the user replaces old stat
		Then the new score should be saved

	Scenario: Retrieve A User Stat
		Given a user has a score in the system
		When the user requests their stat
		Then the old stat should be returned

	Scenario: Reset All Stats
		Given the stats has multiple entries
		When the user clears the stats
		Then no stats should remain in the system
