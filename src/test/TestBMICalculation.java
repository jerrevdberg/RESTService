package test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import jerrevandenberg.main.BMICalculation;
public class TestBMICalculation {
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetLength(){
		BMICalculation bmic = new BMICalculation(1.70f, 80f);
		float testLength = bmic.getLength();
		
		Assert.assertEquals(testLength, 1.70f, 0.00001f);
	}
	
	@Test
	public void testSetLength(){
		BMICalculation bmic = new BMICalculation(1.70f, 80f);
		bmic.setLength(1.80f);
		
		Assert.assertEquals(bmic.getLength(), 1.80f, 0.00001f);
	}
	
	@Test
	public void testCalculateBMI(){
		BMICalculation bmic = new BMICalculation(1.70f, 80f);
		float calculatedBMI = bmic.getBMI();
		float expectedBMI = 80f / (1.70f * 1.70f);
		
		Assert.assertEquals(calculatedBMI, expectedBMI, 0.00001f);
	}
	
}

