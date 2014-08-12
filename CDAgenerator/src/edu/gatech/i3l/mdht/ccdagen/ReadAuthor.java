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

public class ReadAuthor {
	// ArrayList<Demographics> demog;

	private String time, prefix, first_name, last_name, address, city, state, zip, country, phone;

	private String inputFile;

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * @param first_name the first_name to set
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * @param last_name the last_name to set
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	private int lineCount;

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
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

	/**
	 * @return the first_name
	 */

	public ReadAuthor(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		String nextSection = "Encounters";

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
			// lineCount = 0;

			while ((reading = br.readLine()) != null) {
				if (reading.startsWith("Author")) {

					time = br.readLine().split(csvSplitBy)[1];
					prefix = br.readLine().split(csvSplitBy)[1];
					first_name = br.readLine().split(csvSplitBy)[1];
					last_name = br.readLine().split(csvSplitBy)[1];

					address = br.readLine().split(csvSplitBy)[1];
					city = br.readLine().split(csvSplitBy)[1];
					state = br.readLine().split(csvSplitBy)[1];
					zip = br.readLine().split(csvSplitBy)[1];
					country = br.readLine().split(csvSplitBy)[1];

					phone = br.readLine().split(csvSplitBy)[1];
				}
			}
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
