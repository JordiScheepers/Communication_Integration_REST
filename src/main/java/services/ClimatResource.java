package services;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import model.Climat;
import model.ClimatService;
import model.Country;
import model.Month;
import model.ServiceProvider;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClimatResource {
	private static ClimatService service = ServiceProvider.getWorldService();

	public static void main(String[] args) {
		// Creates a new Climat resource, will return the ID to the created
		// resource
		// author and title are sent as query parameters e.g.
		// /Climats?author=Foo&title=Bar
		post(new Route("/climats/new") {
			@Override
			public Object handle(Request request, Response response) {
				ArrayList<Climat> result = new ArrayList<Climat>();
				try {
					String countryName = request.queryParams("countryName");
					String monthName = request.queryParams("monthName");
					int maxTemp = Integer.parseInt(request.queryParams("maxTemp"));
					int minTemp = Integer.parseInt(request.queryParams("minTemp"));
					int meanSunDays = Integer.parseInt(request.queryParams("meanSunDays"));
					int meanRainDays = Integer.parseInt(request.queryParams("meanRainDays"));
					int seaTemp = Integer.parseInt(request.queryParams("seaTemp"));
					Climat climat = new Climat();
					climat.setMaxTemp(maxTemp);
					climat.setMeanRainDays(meanRainDays);
					climat.setMeanSunDays(meanSunDays);
					climat.setMinTemp(minTemp);
					climat.setSeaTemp(seaTemp);
					Month month = new Month();
					month.setNameMonth(monthName);
					climat.setMonth(month);
					Country country = new Country();
					country.setNameCountry(countryName);
					climat.setCountry(country);
					service.addClimat(climat);
					response.status(201); // 201 Created
					result.add(climat);
					JsonArray customerArray = buildJsonCountryArray(result);
					return customerArray.toString();
				} catch (RuntimeException E) {
					return E.getMessage();
				}
			}
		});

		// Gets the Climat resource for the provided code/month
		get(new Route("/climats/:code/:month") {
			@Override
			public Object handle(Request request, Response response) {
				try {
					ArrayList<Climat> results = service.getClimat(request.params(":code"), request.params(":month"));
					JsonArray customerArray = buildJsonCountryArray(results);
					return customerArray.toString();
				} catch (RuntimeException E) {
					throw E;
				}
			}
		});

		// Updates the Climat resource for the provided id with new information
		// author and title are sent as query parameters e.g.
		// /Climats/<id>?author=Foo&title=Bar
		put(new Route("/climats/update") {
			@Override
			public Object handle(Request request, Response response) {
				int id = Integer.parseInt(request.params(":id"));
				int maxTemp = Integer.parseInt(request.queryParams("maxTemp"));
				int minTemp = Integer.parseInt(request.queryParams("minTemp"));
				int meanSunDays = Integer.parseInt(request.queryParams("meanSunDays"));
				int meanRainDays = Integer.parseInt(request.queryParams("meanRainDays"));
				int seaTemp = Integer.parseInt(request.queryParams("seaTemp"));
				JsonArray customerArray = buildJsonCountryArray(service.UpdateClimat(id,maxTemp,minTemp,meanSunDays,meanRainDays,seaTemp));
				return customerArray.toString();
			}
		});

		// Deletes the Climat resource for the provided id
		delete(new Route("/climats/:month/:countryName") {
			@Override
			public Object handle(Request request, Response response) {
				boolean result = service.removeClimat(request.params(":countryName"), request.params(":month"));
				if (result == true) {
					return "Climat deleted";
				} else {
					response.status(404); // 404 Not found
					return "Climat not found";
				}
			}
		});

		// Gets all available Climat resources
		get(new Route("/climats/all") {
			@Override
			public Object handle(Request request, Response response) {
				try {
					JsonArray customerArray = buildJsonCountryArray(service.getClimats());
					return customerArray.toString();
				} catch (RuntimeException E) {
					throw E;
				}
			}
		});
	}

	private static JsonArray buildJsonCountryArray(List<Climat> climats) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Climat c : climats) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("MaxTemp", c.getMaxTemp());
			job.add("MeanRain", c.getMeanRainDays());
			job.add("MeanSun", c.getMeanSunDays());
			job.add("MinTemp", c.getMinTemp());
			if (c.getSeaTemp() != null) {
				job.add("SeaTemp", c.getSeaTemp());
			}
			job.add("CountryName", c.getCountry().getNameCountry());
			job.add("MonthName", c.getMonth().getNameMonth());
			jsonArrayBuilder.add(job);
		}
		return jsonArrayBuilder.build();
	}
}
