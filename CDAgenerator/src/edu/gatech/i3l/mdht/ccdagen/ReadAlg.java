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

public class ReadAlg {

	private ArrayList<String> start_date, end_date, subs, subs_code, reaction, reaction_code, severity, severity_code,
			status, status_code;

	private String inputFile;

	private String empty;

	private int lineCount;

	/**
	 * @return the status_code
	 */
	public ArrayList<String> getStatus_code() {
		return status_code;
	}

	/**
	 * @param status_code the status_code to set
	 */
	public void setStatus_code(ArrayList<String> status_code) {
		this.status_code = status_code;
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
	 * @return the end_date
	 */
	public ArrayList<String> getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date the end_date to set
	 */
	public void setEnd_date(ArrayList<String> end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return the subs
	 */
	public ArrayList<String> getSubs() {
		return subs;
	}

	/**
	 * @param subs the subs to set
	 */
	public void setSubs(ArrayList<String> subs) {
		this.subs = subs;
	}

	/**
	 * @return the subs_code
	 */
	public ArrayList<String> getSubs_code() {
		return subs_code;
	}

	/**
	 * @param subs_code the subs_code to set
	 */
	public void setSubs_code(ArrayList<String> subs_code) {
		this.subs_code = subs_code;
	}

	/**
	 * @return the reaction
	 */
	public ArrayList<String> getReaction() {
		return reaction;
	}

	/**
	 * @param reaction the reaction to set
	 */
	public void setReaction(ArrayList<String> reaction) {
		this.reaction = reaction;
	}

	/**
	 * @return the reaction_code
	 */
	public ArrayList<String> getReaction_code() {
		return reaction_code;
	}

	/**
	 * @param reaction_code the reaction_code to set
	 */
	public void setReaction_code(ArrayList<String> reaction_code) {
		this.reaction_code = reaction_code;
	}

	/**
	 * @return the severity
	 */
	public ArrayList<String> getSeverity() {
		return severity;
	}

	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(ArrayList<String> severity) {
		this.severity = severity;
	}

	/**
	 * @return the severity_code
	 */
	public ArrayList<String> getSeverity_code() {
		return severity_code;
	}

	/**
	 * @param severity_code the severity_code to set
	 */
	public void setSeverity_code(ArrayList<String> severity_code) {
		this.severity_code = severity_code;
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
	 * @return the empty
	 */
	public String getEmpty() {
		return empty;
	}

	/**
	 * @param empty the empty to set
	 */
	public void setEmpty(String empty) {
		this.empty = empty;
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

	public ReadAlg(String inputFile) {
		this.inputFile = inputFile;
		run();

	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";

		try {

			start_date = new ArrayList<String>();
			end_date = new ArrayList<String>();
			subs = new ArrayList<String>();
			subs_code = new ArrayList<String>();
			reaction = new ArrayList<String>();
			reaction_code = new ArrayList<String>();
			severity = new ArrayList<String>();
			severity_code = new ArrayList<String>();
			status = new ArrayList<String>();
			status_code = new ArrayList<String>();

			// System.out.println("before the while loop: " + visitDate.size());

			br = new BufferedReader(new FileReader(csvFile));

			String reading = "";
			String empty = "";
			lineCount = 0;

			while ((reading = br.readLine()) != null) {
				if (reading.startsWith("Allergies")) {
					String[] temp_empty = reading.split(",");
					empty = temp_empty[1];
					// System.out.println(empty);
					if (empty.startsWith("0")) {
						subs.add("List");
						subs.add("No known Allergies");
					} else {
						// br.readLine();
						// System.out.println((line = br.readLine()).split(",").length);
						while ((line = br.readLine()).split(",").length != 0) {
							String[] temp = line.split(",");

							start_date.add(temp[0]);
							end_date.add(temp[1]);
							subs.add(temp[2]);
							subs_code.add(temp[3]);
							reaction.add(temp[4]);
							reaction_code.add(temp[5]);
							severity.add(temp[6]);
							severity_code.add(temp[7]);
							status.add(temp[8]);
							status_code.add(temp[9]);

							lineCount++;
							// System.out.println(temp.length);
							// System.out.println(visitDate.size());

						}
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
