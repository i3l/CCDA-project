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

public class ReadProbs {

	private ArrayList<String> problems, snomed, from, to, status;

	private String inputFile;

	private int lineCount;

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

	public ReadProbs(String inputFile) {
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
	 * @return the problems
	 */
	public ArrayList<String> getProblems() {
		return problems;
	}

	/**
	 * @param problems the problems to set
	 */
	public void setProblems(ArrayList<String> problems) {
		this.problems = problems;
	}

	/**
	 * @return the snomed
	 */
	public ArrayList<String> getSnomed() {
		return snomed;
	}

	/**
	 * @param snomed the snomed to set
	 */
	public void setSnomed(ArrayList<String> snomed) {
		this.snomed = snomed;
	}

	/**
	 * @return the from
	 */
	public ArrayList<String> getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(ArrayList<String> from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public ArrayList<String> getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(ArrayList<String> to) {
		this.to = to;
	}

	private void run() {

		String csvFile = this.inputFile;
		BufferedReader br = null;
		String line = "";

		problems = new ArrayList<String>();
		snomed = new ArrayList<String>();
		to = new ArrayList<String>();
		from = new ArrayList<String>();
		status = new ArrayList<String>();
		try {

			// Store data read from CSV into local variables

			br = new BufferedReader(new FileReader(csvFile));

			String reading = "";

			lineCount = 0;

			while ((reading = br.readLine()) != null) {
				// System.out.println(reading);
				if (reading.startsWith("Problem")) {
					// br.readLine();

					while ((line = br.readLine()) != null && (line.split(",").length != 0)) {

						// System.out.println("this is line: " + line);
						String[] temp = line.split(",");
						// System.out.println("this is temp : " + temp[0]);
						// System.out.println("this is temp : " + temp[1]);
						problems.add(temp[0]);
						snomed.add(temp[1]);
						from.add(temp[2]);
						to.add(temp[3]);
						status.add(temp[4]);
						lineCount++;
						// System.out.println("line.split.length: " + (br.readLine()).split(",").length);
					}

					// System.out.println(history.size());
				}
				// System.out.println(history.size());
			}
			// String[] temp = br.readLine().split(csvSplitBy);
			// System.out.println(temp[0]);
			// line = br.readLine();
			// lineCount = line.split(",").length;
			// while (lineCount != 0) {
			// System.out.println(lineCount);
			// System.out.println(history);
			// String[] temp = br.readLine().split(csvSplitBy);
			// history.add(temp[0]);
			// term.add(temp[1]);

			// while (!(br.readLine().equals(",,,,,")) || j < 3) {
			// String[] temp = br.readLine().split(csvSplitBy);
			// System.out.println(temp[0]);
			// // history.add(temp[0]);
			// // term.add(temp[1]);
			// j++;
			// }

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
		// System.out.println(history.size());
		// System.out.println("Done");
	}
}
