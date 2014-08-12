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

public class ReadEcns {

	private ArrayList<String> visitDate, chiefComplaint, diagnosis, icd9, height, weight, pulse, respiration,
			bloodPressure, notes;

	private String inputFile;

	private int lineCount;

	public ReadEcns(String inputFile) {
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
	 * @return the chiefComplaint
	 */
	public ArrayList<String> getChiefComplaint() {
		return chiefComplaint;
	}

	/**
	 * @param chiefComplaint the chiefComplaint to set
	 */
	public void setChiefComplaint(ArrayList<String> chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	/**
	 * @return the diagnosis
	 */
	public ArrayList<String> getDiagnosis() {
		return diagnosis;
	}

	/**
	 * @param diagnosis the diagnosis to set
	 */
	public void setDiagnosis(ArrayList<String> diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * @return the icd9
	 */
	public ArrayList<String> getIcd9() {
		return icd9;
	}

	/**
	 * @param icd9 the icd9 to set
	 */
	public void setIcd9(ArrayList<String> icd9) {
		this.icd9 = icd9;
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
	 * @return the pulse
	 */
	public ArrayList<String> getPulse() {
		return pulse;
	}

	/**
	 * @param pulse the pulse to set
	 */
	public void setPulse(ArrayList<String> pulse) {
		this.pulse = pulse;
	}

	/**
	 * @return the respiration
	 */
	public ArrayList<String> getRespiration() {
		return respiration;
	}

	/**
	 * @param respiration the respiration to set
	 */
	public void setRespiration(ArrayList<String> respiration) {
		this.respiration = respiration;
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
	 * @return the notes
	 */
	public ArrayList<String> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		lineCount = 0;

		visitDate = new ArrayList<String>();
		chiefComplaint = new ArrayList<String>();
		diagnosis = new ArrayList<String>();
		icd9 = new ArrayList<String>();
		height = new ArrayList<String>();
		weight = new ArrayList<String>();
		pulse = new ArrayList<String>();
		respiration = new ArrayList<String>();
		bloodPressure = new ArrayList<String>();
		notes = new ArrayList<String>();

		try {

			br = new BufferedReader(new FileReader(csvFile));

			String reading = "";

			while ((reading = br.readLine()) != null) {
				if (reading.startsWith("Encounters")) {
					// br.readLine();
					while ((line = br.readLine()).split(",").length != 0) {
						String[] temp = line.split(",");

						visitDate.add(temp[0]);
						chiefComplaint.add(temp[1]);
						diagnosis.add(temp[2]);
						icd9.add(temp[3]);
						height.add(temp[4]);
						weight.add(temp[5]);
						pulse.add(temp[6]);
						respiration.add(temp[7]);
						bloodPressure.add(temp[8]);
						lineCount++;
						if (temp.length > 10) {
							for (int i = 10; i < temp.length; i++) {
								temp[9] = temp[9] + "," + temp[i];

							}
							temp[9] = temp[9].substring(1, temp[9].length() - 1);
						}

						notes.add(temp[9]);

						// System.out.println(visitDate.size());
					}
					// System.out.println(visitDate.size());
				}
				// System.out.println(visitDate.size());
			}
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

		// System.out.println("Done");
	}

}
