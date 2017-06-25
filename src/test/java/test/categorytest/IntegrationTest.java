package test.categorytest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import org.junit.Test;
import org.junit.experimental.categories.Categories.IncludeCategory;

import model.Climat;
import model.ClimatService;
import model.ServiceProvider;
import test.utils.MatchParams;

public class IntegrationTest {
	private static ClimatService service = ServiceProvider.getWorldService();

	@Test
	@IncludeCategory(test.categorytest.categorytest.class)
	public void test_validate() {
		try {
			ArrayList<Climat> climats = service.getClimats();
			JsonArray customerArray = buildJsonCountryArray(climats);
			assertThat(customerArray!=null, is(true));
		} catch (RuntimeException E) {
			throw E;
		}

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
