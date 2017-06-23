package jerrevandenberg.main;
import java.io.BufferedReader;
import jerrevandenberg.main.BMICalculation;
import jerrevandenberg.main.CalorieVerbruik;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
 
@Path("/")
public class RESTService {
	@POST
	@Path("/RESTService")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response RESTService(InputStream incomingData) {
		StringBuilder restBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				restBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + restBuilder.toString());
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(restBuilder.toString()).build();
	}
 
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "REST-Service Succesfully initiated!";
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path("/calculateBMI/{length},{weight}")
	@Produces("application/json")
	public Response calculateBMI(@PathParam("length") String varX,
		    @PathParam("weight") String varY) throws JSONException {
		float gewicht = 0.0f;
		float lengte = 0.0f;
		
		try{
			gewicht = Float.parseFloat(varY);
			lengte = Float.parseFloat(varX);
		}
		catch(NumberFormatException nfe){
			JSONObject error = new JSONObject();
			error.put("ErrorMessage", "Onjuiste waarde ingevuld, controleer of de waarde een float, double of int is");
			error.put("InputGewicht", varY);
			error.put("InputLengte", varX);
			return Response.status(200).entity(error.toString()).build();
		}
		if(gewicht <=0 || lengte <=0){
			JSONObject error = new JSONObject();
			error.put("ErrorMessage", "Onjuiste waarde(s) ingevuld, lengte en/of gewicht mag niet nul zijn!");
			error.put("InputGewicht", varY);
			error.put("InputLengte", varX);
			return Response.status(200).entity(error.toString()).build();
		}
		BMICalculation BMI = new BMICalculation(lengte, gewicht);
		float hetBMI = BMI.getBMI();
	
		JSONObject jason = new JSONObject();
		jason.put("ingevuld gewicht", gewicht);
		jason.put("ingevulde lengte", lengte);
		jason.put("BMI uitkomst", hetBMI);
		
		// return HTTP response 200 in case of success
		return Response.status(200).entity(jason.toString()).build();
	}
	
	@GET
	@Path("/calculateVerbruik/{tijd}")
	@Produces("application/json")
	public Response calculateVerbruik(@PathParam("tijd") String varX) throws JSONException {
		int tijd = 0;
		
		try{
			tijd = Integer.parseInt(varX);
		}
		catch(NumberFormatException nfe){
			JSONObject error = new JSONObject();
			error.put("ErrorMessage", "Onjuiste waarde ingevuld, controleer of de ingevulde waarde een int is");
			error.put("InputTijd", varX);
			return Response.status(200).entity(error.toString()).build();
		}
		
		if(tijd < 0){
			JSONObject error = new JSONObject();
			error.put("ErrorMessage", "Onjuiste waarde ingevuld, controleer of de ingevulde tijd niet negatief is");
			error.put("InputTijd", varX);
			return Response.status(200).entity(error.toString()).build();
		}
		CalorieVerbruik cv = new CalorieVerbruik(tijd);
		int totaleVerbruik = cv.getVerbruikteCalorieen();
	
		JSONObject jason = new JSONObject();
		jason.put("ingevulde tijd in minuten", tijd);
		jason.put("gemiddeld gebruik per minuut", cv.getVerbruikPerMinuut());
		jason.put("Totale calorieverbruik", totaleVerbruik);
		
		// return HTTP response 200 in case of success
		return Response.status(200).entity(jason.toString()).build();
	}
 
}

