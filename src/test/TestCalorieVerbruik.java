package test;

import static org.junit.Assert.*;
import jerrevandenberg.main.CalorieVerbruik;
import junit.framework.Assert;

import org.junit.Test;

public class TestCalorieVerbruik {

	@Test
	public void testGetTijd() {
		CalorieVerbruik cv = new CalorieVerbruik(60);
		
		Assert.assertEquals(cv.getTijdInMinuten(), 60);
	}
	
	@Test
	public void testSetTijd(){
		CalorieVerbruik cv = new CalorieVerbruik(60);
		cv.setTijdInMinuten(40);
		Assert.assertEquals(cv.getTijdInMinuten(), 40);
	}
	@Test
	public void testBerekenVerbruik(){
		CalorieVerbruik cv = new CalorieVerbruik(60);
		int totaalVerbruik = cv.getVerbruikteCalorieen();
		int verwachtteVerbruik = 60 * 50;
		Assert.assertEquals(totaalVerbruik, verwachtteVerbruik);
	}
}
