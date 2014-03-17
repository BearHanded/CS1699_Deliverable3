Feature: Meter Control
	
	Scenario: No Lost Health In Shade
		Given a user is in the shade
		When a user stands for 100 milliseconds
		Then the meter should not decrease

	Scenario: Increased Health In Shade
		Given a user is in the shade
		When a user stands for 100 milliseconds
		Then the meter should increase
	
	Scenario: Lost Health Out Of Shade
		Given a user is not in the shade
		When a user stands for 100 milliseconds
		Then the meter should decrease

	Scenario: No Change In Shade Over No Time
		Given a user is in the shade
		When a user stands for 0 milliseconds
		Then the meter should remain the same
		
	Scenario: No Change Out Of Shade Over No Time
		Given a user is not in the shade
		When a user stands for 0 milliseconds
		Then the meter should remain the same
