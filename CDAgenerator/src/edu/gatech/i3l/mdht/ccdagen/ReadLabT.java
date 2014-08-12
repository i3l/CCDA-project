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

public class ReadLabT {

	private ArrayList<String> visitDate, test, loinc, value, unit, shortName;

	private String inputFile;

	private int lineCount;

	public ReadLabT(String inputFile) {
		this.inputFile = inputFile;
		run();

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

	/**
	 * @return the visitDate
	 */
	public ArrayList<String> getVisitDate() {
		return visitDate;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(ArrayList<String> visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the test
	 */
	public ArrayList<String> getTest() {
		return test;
	}

	/**
	 * @param test the test to set
	 */
	public void setTest(ArrayList<String> test) {
		this.test = test;
	}

	/**
	 * @return the loinc
	 */
	public ArrayList<String> getLoinc() {
		return loinc;
	}

	/**
	 * @param loinc the loinc to set
	 */
	public void setLoinc(ArrayList<String> loinc) {
		this.loinc = loinc;
	}

	/**
	 * @return the value
	 */
	public ArrayList<String> getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(ArrayList<String> value) {
		this.value = value;
	}

	/**
	 * @return the unit
	 */
	public ArrayList<String> getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(ArrayList<String> unit) {
		this.unit = unit;
	}

	/**
	 * @return the shortName
	 */
	public ArrayList<String> getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(ArrayList<String> shortName) {
		this.shortName = shortName;
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		try {

			visitDate = new ArrayList<String>();
			test = new ArrayList<String>();
			loinc = new ArrayList<String>();
			value = new ArrayList<String>();
			unit = new ArrayList<String>();
			shortName = new ArrayList<String>();

			// System.out.println("before the while loop: " + visitDate.size());

			br = new BufferedReader(new FileReader(csvFile));

			String reading = "";
			lineCount = 0;

			while ((reading = br.readLine()) != null) {
				if (reading.startsWith("Results")) {
					// br.readLine();
					// System.out.println((line = br.readLine()).split(",").length);
					while ((line = br.readLine()).split(",").length != 0) {
						String[] temp = line.split(",");

						visitDate.add(temp[0]);
						test.add(temp[1]);
						loinc.add(temp[2]);
						value.add(temp[3]);
						unit.add(temp[4]);
						shortName.add(temp[5]);
						lineCount++;
						// System.out.println(temp.length);
						// System.out.println(visitDate.size());

					}
					// System.out.println(visitDate.size());
				}
				// System.out.println(visitDate.size());
			}
			// System.out.println(visitDate.get(0));
			// System.out.println(visitDate.size());
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

	}

}
