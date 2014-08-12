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

public class ReadDemog {
	// ArrayList<Demographics> demog;

	private String first_name, mi, last_name, gender, race, ethinic_group, dob, address, city, state, country, zip,
			phone, email;

	private String inputFile;

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
	 * @return the first_name
	 */

	public ReadDemog(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	/**
	 * @return the race
	 */
	public String getRace() {
		return race;
	}

	/**
	 * @param race the race to set
	 */
	public void setRace(String race) {
		this.race = race;
	}

	/**
	 * @return the ethinic_group
	 */
	public String getEthinic_group() {
		return ethinic_group;
	}

	/**
	 * @param ethinic_group the ethinic_group to set
	 */
	public void setEthinic_group(String ethinic_group) {
		this.ethinic_group = ethinic_group;
	}

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
	 * @return the mi
	 */
	public String getMi() {
		return mi;
	}

	/**
	 * @param mi the mi to set
	 */
	public void setMi(String mi) {
		this.mi = mi;
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

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
				if (reading.startsWith("Demographics")) {
					// System.out.println(reading);
					int i = 0;
					String[] readIn = line.split(csvSplitBy);
					// br.readLine();
					first_name = br.readLine().split(csvSplitBy)[1];
					mi = br.readLine().split(csvSplitBy)[1];
					last_name = br.readLine().split(csvSplitBy)[1];
					gender = br.readLine().split(csvSplitBy)[1];
					race = br.readLine().split(csvSplitBy)[1];
					ethinic_group = br.readLine().split(csvSplitBy)[1];
					dob = br.readLine().split(csvSplitBy)[1];
					address = br.readLine().split(csvSplitBy)[1];
					city = br.readLine().split(csvSplitBy)[1];
					state = br.readLine().split(csvSplitBy)[1];
					country = br.readLine().split(csvSplitBy)[1];
					zip = br.readLine().split(csvSplitBy)[1];
					phone = br.readLine().split(csvSplitBy)[1];
					email = br.readLine().split(csvSplitBy)[1];
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
