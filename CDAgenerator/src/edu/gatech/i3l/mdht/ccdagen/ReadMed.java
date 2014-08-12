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

public class ReadMed {

	private ArrayList<String> visitDate, drug, start_date, stop_date, status, route, route_code, type, type_code,
			rxNorm, does_qty, rate_qty, rate_qtyUnit, sig;

	private String inputFile;

	private int lineCount;

	public ReadMed(String inputFile) {
		this.inputFile = inputFile;
		run();
	}

	/**
	 * @return the visitDate
	 */
	public ArrayList<String> getVisitDate() {
		return visitDate;
	}

	/**
	 * @return the route_code
	 */
	public ArrayList<String> getRoute_code() {
		return route_code;
	}

	/**
	 * @param route_code the route_code to set
	 */
	public void setRoute_code(ArrayList<String> route_code) {
		this.route_code = route_code;
	}

	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(ArrayList<String> visitDate) {
		this.visitDate = visitDate;
	}

	/**
	 * @return the drug
	 */
	public ArrayList<String> getDrug() {
		return drug;
	}

	/**
	 * @param drug the drug to set
	 */
	public void setDrug(ArrayList<String> drug) {
		this.drug = drug;
	}

	/**
	 * @return the start_date
	 */
	public ArrayList<String> getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date the start_date to set
	 */
	public void setStart_date(ArrayList<String> start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return the stop_date
	 */
	public ArrayList<String> getStop_date() {
		return stop_date;
	}

	/**
	 * @param stop_date the stop_date to set
	 */
	public void setStop_date(ArrayList<String> stop_date) {
		this.stop_date = stop_date;
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
	 * @return the route
	 */
	public ArrayList<String> getRoute() {
		return route;
	}

	/**
	 * @param route the route to set
	 */
	public void setRoute(ArrayList<String> route) {
		this.route = route;
	}

	/**
	 * @return the type
	 */
	public ArrayList<String> getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ArrayList<String> type) {
		this.type = type;
	}

	/**
	 * @return the type_code
	 */
	public ArrayList<String> getType_code() {
		return type_code;
	}

	/**
	 * @param type_code the type_code to set
	 */
	public void setType_code(ArrayList<String> type_code) {
		this.type_code = type_code;
	}

	/**
	 * @return the rxNorm
	 */
	public ArrayList<String> getRxNorm() {
		return rxNorm;
	}

	/**
	 * @param rxNorm the rxNorm to set
	 */
	public void setRxNorm(ArrayList<String> rxNorm) {
		this.rxNorm = rxNorm;
	}

	/**
	 * @return the does_qty
	 */
	public ArrayList<String> getDoes_qty() {
		return does_qty;
	}

	/**
	 * @param does_qty the does_qty to set
	 */
	public void setDoes_qty(ArrayList<String> does_qty) {
		this.does_qty = does_qty;
	}

	/**
	 * @return the rate_qty
	 */
	public ArrayList<String> getRate_qty() {
		return rate_qty;
	}

	/**
	 * @param rate_qty the rate_qty to set
	 */
	public void setRate_qty(ArrayList<String> rate_qty) {
		this.rate_qty = rate_qty;
	}

	/**
	 * @return the rate_qtyUnit
	 */
	public ArrayList<String> getRate_qtyUnit() {
		return rate_qtyUnit;
	}

	/**
	 * @param rate_qtyUnit the rate_qtyUnit to set
	 */
	public void setRate_qtyUnit(ArrayList<String> rate_qtyUnit) {
		this.rate_qtyUnit = rate_qtyUnit;
	}

	/**
	 * @return the sig
	 */
	public ArrayList<String> getSig() {
		return sig;
	}

	/**
	 * @param sig the sig to set
	 */
	public void setSig(ArrayList<String> sig) {
		this.sig = sig;
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

			visitDate = new ArrayList<String>();
			drug = new ArrayList<String>();
			start_date = new ArrayList<String>();
			stop_date = new ArrayList<String>();
			status = new ArrayList<String>();
			route = new ArrayList<String>();
			route_code = new ArrayList<String>();
			type = new ArrayList<String>();
			type_code = new ArrayList<String>();
			rxNorm = new ArrayList<String>();
			does_qty = new ArrayList<String>();
			rate_qty = new ArrayList<String>();
			rate_qtyUnit = new ArrayList<String>();
			sig = new ArrayList<String>();

			br = new BufferedReader(new FileReader(csvFile));
			String reading = "";

			// System.out.println(br.readLine());

			while ((reading = br.readLine()) != null) {
				// System.out.println(reading);
				if (reading.startsWith("Medications")) {
					while ((line = br.readLine()) != null) {
						if (line.split(",").length != 0) {
							if (line.startsWith("Problem")) {
								break;
							} else {
								String[] temp = line.split(",");
								// System.out.println(temp[0]);
								visitDate.add(temp[0]);
								drug.add(temp[1]);
								start_date.add(temp[2]);
								stop_date.add(temp[3]);
								status.add(temp[4]);
								route.add(temp[5]);
								route_code.add(temp[6]);
								type.add(temp[7]);
								type_code.add(temp[8]);
								rxNorm.add(temp[9]);
								does_qty.add(temp[10]);
								rate_qty.add(temp[11]);
								rate_qtyUnit.add(temp[12]);
								sig.add(temp[13]);
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
