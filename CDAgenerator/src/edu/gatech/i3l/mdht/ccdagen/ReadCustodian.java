package edu.gatech.i3l.mdht.ccdagen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ReadCSV is the class that processes comma delimited files.
 *
 * Adapted from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/ "Example 2"
 *
 * @author	Sungwoo Han
 * @version	1.0
 */

public class ReadCustodian {
	// ArrayList<Demographics> demog;

	private String name, address, city, state, country, zip, phone;

	private String inputFile;

	public ReadCustodian(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		String nextSection = "History";

		try {

			// Store data read from CSV into local variables
			/*
			 * demog = new ArrayList<Demographics>();
			 * ArrayList<String> values = new ArrayList<String>();
			 */
			br = new BufferedReader(new FileReader(csvFile));

			// for (int i = 0; i <10; i++)
			// {
			String reading = "";
			while ((reading = br.readLine()) != null) {
				if (reading.startsWith("Custodian")) {
					// System.out.println(reading);
					int i = 0;
					String[] readIn = line.split(csvSplitBy);
					// br.readLine();
					name = br.readLine().split(csvSplitBy)[1];
					address = br.readLine().split(csvSplitBy)[1];
					city = br.readLine().split(csvSplitBy)[1];
					state = br.readLine().split(csvSplitBy)[1];
					country = br.readLine().split(csvSplitBy)[1];
					zip = br.readLine().split(csvSplitBy)[1];
					phone = br.readLine().split(csvSplitBy)[1];

				}
			}
			/*
			 * while ((line = br.readLine()) != null) {
			 * if (i < 11) {
			 * // use comma as separator
			 * String[] sAry = line.split(csvSplitBy);
			 * // System.out.println(sAry[0]);
			 * if (sAry[0].equalsIgnoreCase("Demographics")) {
			 * continue;
			 * }
			 * values.add(sAry[1]);
			 * 
			 * } else {
			 * // line = null; // this is useless
			 * }
			 * i++;
			 * }
			 */

			// System.out.println(values);

			/*
			 * demog.add(new Demographics(
			 * values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5),
			 * values.get(6), values.get(7), values.get(8), values.get(9), values.get(10)));
			 */
			// System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

			// Loop through list
			// Use data in Medications class to create XML

			/*
			 * for(Demographics m : demog)
			 * {
			 * // Use data in Demographics class to create XML
			 * System.out.println(m.first_name);
			 * System.out.println(m.mi);
			 * System.out.println(m.last_name);
			 * System.out.println(m.gender);
			 * System.out.println(m.dob);
			 * System.out.println(m.address);
			 * System.out.println(m.city);
			 * System.out.println(m.state);
			 * System.out.println(m.zip);
			 * System.out.println(m.phone);
			 * System.out.println(m.email);
			 * 
			 * }
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// System.out.println("Done");
	}
}
