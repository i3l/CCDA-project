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

public class ReadImmu {

	private ArrayList<String> date, vaccine, vaccine_code, product, product_code, dose_Qty, unit, status;

	private String inputFile;

	private int lineCount;

	/**
	 * @return the vaccine_code
	 */
	public ArrayList<String> getVaccine_code() {
		return vaccine_code;
	}

	/**
	 * @param vaccine_code the vaccine_code to set
	 */
	public void setVaccine_code(ArrayList<String> vaccine_code) {
		this.vaccine_code = vaccine_code;
	}

	/**
	 * @return the product
	 */
	public ArrayList<String> getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(ArrayList<String> product) {
		this.product = product;
	}

	/**
	 * @return the product_code
	 */
	public ArrayList<String> getProduct_code() {
		return product_code;
	}

	/**
	 * @param product_code the product_code to set
	 */
	public void setProduct_code(ArrayList<String> product_code) {
		this.product_code = product_code;
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
	 * @return the vaccine
	 */
	public ArrayList<String> getVaccine() {
		return vaccine;
	}

	/**
	 * @param vaccine the vaccine to set
	 */
	public void setVaccine(ArrayList<String> vaccine) {
		this.vaccine = vaccine;
	}

	/**
	 * @return the dose_Qty
	 */
	public ArrayList<String> getDose_Qty() {
		return dose_Qty;
	}

	/**
	 * @param dose_Qty the dose_Qty to set
	 */
	public void setDose_Qty(ArrayList<String> dose_Qty) {
		this.dose_Qty = dose_Qty;
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
	 * @return the status
	 */
	public ArrayList<String> getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(ArrayList<String> status) {
		this.status = status;
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

	public ReadImmu(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		lineCount = 0;

		try {
			date = new ArrayList<String>();
			vaccine = new ArrayList<String>();
			vaccine_code = new ArrayList<String>();
			product = new ArrayList<String>();
			product_code = new ArrayList<String>();
			dose_Qty = new ArrayList<String>();
			unit = new ArrayList<String>();
			status = new ArrayList<String>();

			br = new BufferedReader(new FileReader(csvFile));
			String reading = "";

			// System.out.println(br.readLine());

			while ((reading = br.readLine()) != null) {
				// System.out.println(reading);
				if (reading.startsWith("Immunizations")) {
					while ((line = br.readLine()) != null) {
						if (line.split(",").length != 0) {
							if (line.startsWith("Medications")) {
								break;
							} else {
								String[] temp = line.split(",");
								// System.out.println(temp[0]);
								vaccine.add(temp[0]);
								vaccine_code.add(temp[1]);
								product.add(temp[2]);
								product_code.add(temp[3]);
								date.add(temp[4]);
								dose_Qty.add(temp[5]);
								unit.add(temp[6]);
								status.add(temp[7]);

								lineCount++;
							}
							// System.out.println(visitDate.size());
						}
					}
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
