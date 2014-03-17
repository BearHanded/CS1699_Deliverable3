package test.java.resources;

import org.junit.runner.RunWith;

import com.shade.controls.StatsControl;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
public class TestStatCuke 
{
	private float currentScore;
	private float oldScore;
	private float returnedScore;
	private StatsControl controller;
	
	@Given("^a user obtains a new score$")
	public void newScore()
	{
		controller = new StatsControl();
		currentScore = 5f;
	}
	
	@Given("^a user has a score in the system$")
	public void hasScore()
	{
		controller = new StatsControl();
		currentScore = 5f;
		oldScore = 3f;
		//add 3
		controller.add("tester", oldScore);
	}
	
	@Given("^the stats has multiple entries$")
	public void hasMultipleScores()
	{
		controller = new StatsControl();
		currentScore = 5f;
		oldScore = 3f;
		controller.add("tester", 2019f);
		controller.add("tester1", 5f);
		controller.add("tester2", 6f);
		controller.add("tester3", 7f);
		controller.add("tester4", 8f);
	}
	
	@When("^a user adds their recent score$")
	public void addRecent()
	{
		//add 5
		controller.add("tester", currentScore);
	}
	
	@When("^the user replaces old stat$")
	public void replaceStat()
	{
		//replace w/ 5
		controller.replace("tester", currentScore);
	}
	
	@When("^the user requests their stat$")
	public void returnStat()
	{
		returnedScore = controller.getStat("tester");
	}
	
	@When("^the user clears the stats$")
	public void resetStats()
	{
		controller.reset();
	}
	
	@Then("^the new score should be saved$")
	public void checkForNew()
	{
		assert(controller.getStat("tester") == currentScore);
	}
	
	@Then("^the stat should be cumulative of the current and old stat$")
	public void cumulativeCheck()
	{
		assert(controller.getStat("tester") == (currentScore + oldScore));
	}
	
	@Then("^the old stat should be returned$")
	public void checkForOld()
	{
		assert(controller.getStat("tester") == oldScore);
	}
	
	@Then("^no stats should remain in the system$")
	public void checkReset()
	{
		/**
		 * List is private, but if an old stat exists for that player, a cumulative score will be generated.
		 * By checking for a cumulative score vs a score saved, it can be checked that the players score was cleared 
		 */
		controller.add("tester", 2000f);
		assert(controller.getStat("tester") == 2000f);
	}
}
