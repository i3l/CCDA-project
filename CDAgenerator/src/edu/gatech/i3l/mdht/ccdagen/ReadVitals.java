package edu.gatech.i3l.mdht.ccdagen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadCSV is the class that processes comma delimited files.
 *
 * Adapted from http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/ "Example 2"
 *
 * @author	Sungwoo Han
 * @version	1.0
 */

public class ReadVitals {

	private ArrayList<String> date, height, height_unit, weight, weight_unit, bloodPressure, bloodPressure_unit;

	private String inputFile;

	private int lineCount;

	public ReadVitals(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	/**
	 * @return the date
	 */
	public ArrayList<String> getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(ArrayList<String> date) {
		this.date = date;
	}

	/**
	 * @return the height
	 */
	public ArrayList<String> getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(ArrayList<String> height) {
		this.height = height;
	}

	/**
	 * @return the height_unit
	 */
	public ArrayList<String> getHeight_unit() {
		return height_unit;
	}

	/**
	 * @param height_unit the height_unit to set
	 */
	public void setHeight_unit(ArrayList<String> height_unit) {
		this.height_unit = height_unit;
	}

	/**
	 * @return the weight
	 */
	public ArrayList<String> getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(ArrayList<String> weight) {
		this.weight = weight;
	}

	/**
	 * @return the weight_unit
	 */
	public ArrayList<String> getWeight_unit() {
		return weight_unit;
	}

	/**
	 * @param weight_unit the weight_unit to set
	 */
	public void setWeight_unit(ArrayList<String> weight_unit) {
		this.weight_unit = weight_unit;
	}

	/**
	 * @return the bloodPressure
	 */
	public ArrayList<String> getBloodPressure() {
		return bloodPressure;
	}

	/**
	 * @param bloodPressure the bloodPressure to set
	 */
	public void setBloodPressure(ArrayList<String> bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	/**
	 * @return the bloodPressure_unit
	 */
	public ArrayList<String> getBloodPressure_unit() {
		return bloodPressure_unit;
	}

	/**
	 * @param bloodPressure_unit the bloodPressure_unit to set
	 */
	public void setBloodPressure_unit(ArrayList<String> bloodPressure_unit) {
		this.bloodPressure_unit = bloodPressure_unit;
	}

	/**
	 * @return the lineCount
	 */
	public int getLineCount() {
		return lineCount;
	}

	/**
	 * @param lineCount the lineCount to set
	 */
	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		lineCount = 0;

		try {

			date = new ArrayList<String>();
			height = new ArrayList<String>();
			height_unit = new ArrayList<String>();
			weight = new ArrayList<String>();
			weight_unit = new ArrayList<String>();
			bloodPressure = new ArrayList<String>();
			bloodPressure_unit = new ArrayList<String>();

			br = new BufferedReader(new FileReader(csvFile));
			String reading = "";

			// System.out.println(br.readLine());

			while ((reading = br.readLine()) != null) {
				// System.out.println(reading);
				if (reading.startsWith("Vital")) {
					while ((line = br.readLine()) != null && (line.split(",").length != 0)) {

						String[] temp = line.split(",");
						// System.out.println(temp[0]);
						date.add(temp[0]);
						height.add(temp[1]);
						height_unit.add(temp[2]);
						weight.add(temp[3]);
						weight_unit.add(temp[4]);
						bloodPressure.add(temp[5]);
						bloodPressure_unit.add(temp[6]);

						lineCount++;
					}
					// System.out.println(visitDate.size());

					// System.out.println(visitDate.size());
				}
				// System.out.println(visitDate.size());
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
