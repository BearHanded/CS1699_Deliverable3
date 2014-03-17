Feature: Counter Control

	Scenario: No Mushroom Pickups
		Given there are 0 normal mushrooms
		When a user picks them up
		Then the user shall receive 0 points

	Scenario: Pick up 1 Normal Mushroom
		Given there are 1 normal mushrooms
		When a user picks them up
		Then the user shall receive at least 20 points

	Scenario: Pick up 2 Normal Mushrooms
		Given there are 2 normal mushrooms
		When a user picks them up
		Then the user shall receive at least 40 points

	Scenario: Pick up 3 Normal Mushrooms
		Given there are 3 normal mushrooms
		When a user picks them up
		Then the user shall receive at least 60 points

	Scenario: Pick up 1 Gold Mushroom
		Given there is 1 gold mushroom
		When a user picks them up
		Then the user shall receive at least 60 points