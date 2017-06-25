package model;

public class ServiceProvider {
	private static ClimatService worldService = new ClimatService();

	public static ClimatService getWorldService() {
		return worldService;
	}
}