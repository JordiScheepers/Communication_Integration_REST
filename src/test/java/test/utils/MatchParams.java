package test.utils;

import java.util.ArrayList;

import model.Climat;
import model.ClimatService;
import model.ServiceProvider;

public class MatchParams {
	private static ClimatService service = ServiceProvider.getWorldService();
	public static boolean matchParams(String county, String month) {
		ArrayList<Climat> climats = service.getClimats();
		boolean matchingName = false;
		for (Climat c : climats) {
			if (c.getCountry().getNameCountry().equals(county) && c.getMonth().getNameMonth().equals(month)) {
				matchingName = true;

			}
		}
		return matchingName;
	}
}
