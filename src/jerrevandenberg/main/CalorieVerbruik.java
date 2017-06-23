package jerrevandenberg.main;

public class CalorieVerbruik {
private int tijdInMinuten;
private final int verbruikPerMinuut = 50;

public CalorieVerbruik(int tim){
	this.tijdInMinuten = tim;
}

public int getTijdInMinuten() {
	return tijdInMinuten;
}

public void setTijdInMinuten(int tijdInMinuten) {
	this.tijdInMinuten = tijdInMinuten;
}

public int getVerbruikPerMinuut() {
	return verbruikPerMinuut;
}

public int getVerbruikteCalorieen(){
	int verbruiktTotaal = tijdInMinuten * verbruikPerMinuut;
	return verbruiktTotaal;
}
}


