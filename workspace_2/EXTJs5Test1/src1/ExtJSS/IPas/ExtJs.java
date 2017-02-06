
package ExtJSS.IPas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExtJs {
	
	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome to LNT Infotech");
		WritetoLog("Test");
	}
	
	
	public static void WritetoLog(String strlogmsg) throws IOException {
		File resultcsvfile = new File("c:\\temp\\results.txt");
		if (!resultcsvfile.exists()) {
			try {
				System.out.println("File is not there Creating File");
				resultcsvfile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileWriter writer = null;
		writer = new FileWriter(resultcsvfile, true);
		BufferedWriter bwriter = new BufferedWriter(writer);
		bwriter.append(strlogmsg);
		bwriter.newLine();
		bwriter.close();
	}

	

}
