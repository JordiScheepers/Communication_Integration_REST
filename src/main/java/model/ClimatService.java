package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClimatService {
	private static final Logger LOGGER = Logger.getLogger("ClimatServiceImpl");

	public ArrayList<Climat> getClimats() {
		URL csvFile = ClimatService.class.getClassLoader().getResource("data.csv");
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		int countryIndex = 0;
		int monthIndex = 0;
		ObjectFactory OF = new ObjectFactory();
		ArrayList<Climat> climats = new ArrayList<Climat>();
		ArrayList<Country> countries = new ArrayList<Country>();
		String countrienames = "Afganistan Belgium China Duitsland Fillepijnen Griekenland";
		String[] countrySplitted = countrienames.split(" ");
		int index = 0;
		while (index < 6) {
			Country country = OF.createCountry();
			country.setNameCountry(countrySplitted[index]);
			index++;
			countries.add(country);

		}

		try {

			br = new BufferedReader(new FileReader(csvFile.getPath()));

			while ((line = br.readLine()) != null) {
				String[] arrayline = line.split(cvsSplitBy);

				Climat climat = OF.createClimat();
				Month month = OF.createMonth();

				arrayline[0] = arrayline[0].replaceAll("\\s+", "");
				arrayline[1] = arrayline[1].replaceAll("\\s+", "");
				arrayline[2] = arrayline[2].replaceAll("\\s+", "");
				arrayline[3] = arrayline[3].replaceAll("\\s+", "");
				arrayline[4] = arrayline[4].replaceAll("\\s+", "");
				month.setNameMonth(arrayline[0]);
				climat.setMaxTemp(Integer.parseInt(arrayline[1]));
				climat.setMinTemp(Integer.parseInt(arrayline[2]));
				climat.setMeanRainDays(Integer.parseInt(arrayline[3]));
				climat.setMeanSunDays(Integer.parseInt(arrayline[4]));

				if (monthIndex == 12) {
					monthIndex = 0;
					countryIndex++;
					monthIndex++;
				} else {
					monthIndex++;
				}
				climat.setMonth(month);
				climat.setCountry(countries.get(countryIndex));
				climats.add(climat);
			}

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
		return climats;

	}

	public ArrayList<Climat> getClimat(String code, String month) {
		ArrayList<Climat> climats = this.getClimats();
		ArrayList<Climat> results = new ArrayList<Climat>();
		for (Climat c : climats) {
			if (c.getCountry().getNameCountry().equals(code) && c.getMonth().getNameMonth().equals(month)) {
				results.add(c);
			}
		}
		return results;
	}

	public boolean removeClimat(String code, String month) {
		boolean result = false;
		ArrayList<Climat> climats = this.getClimats();
		Climat climat = null;
		for (Climat c : climats) {
			if (c.getCountry().getNameCountry().equals(code) && c.getMonth().getNameMonth().equals(month)) {
				climat = c;
				result = true;
			}
		}
		climats.remove(climat);
		return result;
	}

	public void addClimat(Climat c) {
		ArrayList<Climat> climats = this.getClimats();
		climats.add(c);
	}

	public ArrayList<Climat> UpdateClimat(int index, int maxTemp, int minTemp, int meanSunDays, int meanRainDays, int seaTemp) {
		ArrayList<Climat> climats = this.getClimats();
		ArrayList<Climat> results = new ArrayList<Climat>();
		Climat climat = climats.get(index);
		climat.setMaxTemp(maxTemp);
		climat.setMeanRainDays(meanRainDays);
		climat.setMeanSunDays(meanSunDays);
		climat.setMinTemp(minTemp);
		climat.setSeaTemp(seaTemp);
		results.add(climat);
		return results;
	}
}
