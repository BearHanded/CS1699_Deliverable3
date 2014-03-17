package test.java.resources;



import java.lang.reflect.Field;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.newdawn.slick.state.StateBasedGame;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import com.shade.controls.MeterControl;
import com.shade.entities.Player;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor({"com.shade.controls.MeterControl","com.shade.entities.mushroom.Mushroom"})
public class TestMeterCuke {
    private float luminosity;
    private float lifeBeforeStanding;
    private float lifeAfterStanding;
    
    @Given("^a user is in the shade$")
    public void IAmInShade()
    {
    	luminosity = (float) 1.1;
    }
    @Given("^a user is not in the shade$")
    public void amNotInShade()
    {
    	luminosity = (float) 100.0;
    }
    @When("^a user stands for (\\d+) milliseconds$")
    public void amStandingFor(int time) throws Exception
    {
    	StateBasedGame sbg = Mockito.mock(StateBasedGame.class);
    	Player p = Mockito.mock(Player.class);
    	MeterControl m = new MeterControl(3,4);
		//get health
		Field privateValueField =null;
		privateValueField = MeterControl.class.getDeclaredField("value");
		privateValueField.setAccessible(true);
		float init =  (Float) privateValueField.get(m);
		Mockito.stub(p.getLuminosity()).toReturn((float) luminosity);//value for in shade
		m.track(p);
		m.update(sbg, time);
		lifeBeforeStanding = init;
		lifeAfterStanding = (Float) privateValueField.get(m);
    }


    @Then("^the meter should not decrease$")
    public void meterNotDecreased()
    {
    	assert(lifeAfterStanding<=lifeBeforeStanding);
    }
    @Then("^the meter should increase$")
    public void meterIncreased()
    {
    	assert(lifeAfterStanding>lifeBeforeStanding);
    }
    @Then("^the meter should decrease$")
    public void meterDecreased()
    {
    	assert(lifeAfterStanding<lifeBeforeStanding);
    }
    @Then("^the meter should remain the same$")
    public void meterSame()
    {
    	assert(lifeAfterStanding==lifeBeforeStanding);
    }

}