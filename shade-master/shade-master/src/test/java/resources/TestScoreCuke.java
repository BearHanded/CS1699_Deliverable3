package test.java.resources;
import java.lang.reflect.Field;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.newdawn.slick.SlickException;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;

import com.shade.controls.CounterControl;
import com.shade.controls.ScoreControl;
import com.shade.entities.mushroom.Mushroom;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@SuppressStaticInitializationFor({"com.shade.entities.mushroom.Mushroom"})
//@RunWith(PowerMockRunner.class)
public class TestScoreCuke {
		//Player p;
		//Linkable m1;
		//Linkable m2;
		//Linkable m3;
		Mushroom m1, m2, m3, gm;
		CounterControl cc;
		ScoreControl sc;
		float score = 0;
		int mushroom_count = 0;
		
		@Given("there are (\\d+) normal mushrooms")
		public void number_of_mushrooms(int shrooms) throws SlickException{
			//p = Mockito.mock(Player.class);
			
		  /*try {
				m1 = Mockito.mock(Mushroom.class);
				m2 = Mockito.mock(Mushroom.class);
				m3 = Mockito.mock(Mushroom.class);
		} catch (Throwable t) {
		    System.out.println("Failure during static initialization " + t );
		  }*/
			m1 = Mockito.mock(Mushroom.class);
			m2 = Mockito.mock(Mushroom.class);
			m3 = Mockito.mock(Mushroom.class);
			/*MushroomFactory x = Mockito.mock(MushroomFactory.class);
			m1 = new Mushroom(0,0,null, x);
			m2 = new Mushroom(0,0,null, x);
			m3 = new Mushroom(0,0,null, x);*/
			//p.attach(m1);
			//p.attach(m2);
			//p.attach(m3);
			cc = new CounterControl(0, 0, null, null, null);
			sc = new ScoreControl(0,0,null);
			cc.register(sc);
			sc.startLevel();
			mushroom_count = shrooms;
		}
		
		@Given("there is 1 gold mushroom")
		public void number_of_gold_mushrooms() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException{
			gm = Mockito.mock(Mushroom.class);
			/*Field privateValueField = Mushroom.class.getDeclaredField("type");
			privateValueField.setAccessible(true);
			privateValueField.set(gm, "RARE");*/
			Mockito.stub(gm.isGolden()).toReturn(true);
			cc = new CounterControl(0, 0, null, null, null);
			sc = new ScoreControl(0,0,null);
			sc.startLevel();
			cc.register(sc);
			mushroom_count = 4;
		}
		
		@When("^a user picks them up$")
		public void collect_mushrooms() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
			if(mushroom_count == 1){
				cc.onCollect(m1);
			}
			else if(mushroom_count == 2){
				cc.onCollect(m1);
				cc.onCollect(m2);
			}
			else if(mushroom_count == 3){
				cc.onCollect(m1);
				cc.onCollect(m2);
				cc.onCollect(m3);
			}
			else if(mushroom_count == 4){
				cc.onCollect(gm);
			}
			Field privateValueField = ScoreControl.class.getDeclaredField("levelScore");
			privateValueField.setAccessible(true);
			score = (float) privateValueField.getFloat(sc);
		}
		
		@Then("the user shall receive 0 points")
		public void zero_normal_mushroom(){
			assert(score == 0);
		}
		
		@Then("the user shall receive at least 20 points")
		public void one_normal_mushroom(){
			assert(score >= 20);
		}
		
		@Then("the user shall receive at least 40 points")
		public void two_normal_mushroom(){
			assert(score >= 40);
		}
		
		@Then("the user shall receive at least 60 points")
		public void three_normal_mushroom_or_gold(){
			assert(score >= 60);
		}
}
