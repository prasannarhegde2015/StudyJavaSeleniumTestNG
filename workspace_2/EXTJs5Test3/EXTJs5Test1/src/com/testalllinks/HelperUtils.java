package com.testalllinks;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class HelperUtils {

	/**
	 * @param args
	 */
	public void createCSVFile(String outfilepath, String colnamesarr,
			String colvals) throws Exception

	{
		File file = new File(outfilepath);
		if (!file.exists()) {
			file.createNewFile();

		}
		FileWriter fileWritter = null;
		fileWritter = new FileWriter(file, true);
		BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		FileReader reader = null;
		reader = new FileReader(file);
		BufferedReader buffreader = new BufferedReader(reader);
		String[] arrcolnames = colnamesarr.split(";");
		String[] arrcolvalues = colvals.split(";");
		StringBuilder sb = new StringBuilder();
		char delim = '\u0022';
		for (String incolname : arrcolnames) {
			sb.append(delim + incolname + delim + ",");
		}
		if (getstreamlength(buffreader) == 0) {
			bufferWritter.append(sb.toString());
			bufferWritter.newLine();
		}

		sb.setLength(0);
		for (String incolname : arrcolvalues) {
			sb.append(delim + incolname + delim + ",");
		}
		bufferWritter.append(sb.toString());
		bufferWritter.newLine();
		bufferWritter.close();

	}

	public int getstreamlength(BufferedReader rdr) throws Exception {
		int streeamlength = 0;
		try {
			streeamlength = rdr.readLine().toString().length();
		} catch (Exception e2) {
			streeamlength = 0;
		} finally {
			// return streeamlength;
		}
		return streeamlength;
	}
}
