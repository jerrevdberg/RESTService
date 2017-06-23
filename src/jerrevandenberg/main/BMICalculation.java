package jerrevandenberg.main;

public class BMICalculation {
private float length;
private float weight;

public BMICalculation(float l, float w){
	this.length = l;
	this.weight = w;
}

public float getLength() {
	return length;
}

public void setLength(float length) {
	this.length = length;
}

public float getWeight() {
	return weight;
}

public void setWeight(float weight) {
	this.weight = weight;
}

public float getBMI(){
	float f = weight / (length * length);
	return f;
}
}
