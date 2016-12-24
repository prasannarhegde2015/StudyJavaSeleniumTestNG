package GmailTest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;
public class Test2 {
	
	@Test
	public void test2() throws IOException
	{
	         File myfile = new File("d:\\test2.txt");
	         FileOutputStream out = new FileOutputStream(myfile);
	         String test = "["+new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date())+"] Test2 Method was executed ";
	         byte[] b = test.getBytes();
	         out.write(b);
	         out.close();
	}  
}
