mport java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SampleCalssG {
	
	@Test
	public void createdata() throws Exception
	{
		String[] colt = {"A" ,"B" ,"C", "D" ,"E" };
		String[] colt2 = {"1111" ,"2222" ,"3333", "4444" ,"5555" ,"6666" ,"7777","8888","9999" };
		File file =new File("d:\\text.csv");
		double kilobytes = 0.0;
		double gigabytes = 0.0;
		while ( gigabytes < 1)
		{
			int cntr1 = -1;int cntr2 = -1;
			cntr1 =  ThreadLocalRandom.current().nextInt(0, 4);
			cntr2 =  ThreadLocalRandom.current().nextInt(0, 8);
			System.out.println(cntr1);
			System.out.println(cntr1);
			String calvla = colt[cntr1]+";"+colt2[cntr1];
			if(file.exists()){
			double bytes = file.length();
			kilobytes = (bytes / 1024);
			double megabytes = (kilobytes / 1024);
			System.out.println("Size"+megabytes);
		    gigabytes = (megabytes / 1024);
			
			}
			
		createCSVFile("d:\\text.csv", "Transaction_status;External_customer_id", calvla);
		}
	}
	
	
	public void createCSVFile(String outfilepath, String colnamesarr,
			String colvals) throws Exception

	{
		File file = new File(outfilepath);
		System.out.println("File path "+outfilepath);
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
