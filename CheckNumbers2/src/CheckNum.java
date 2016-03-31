import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;





public class CheckNum {
	
	public static void main(String[] args)
	{
		int a;
		String b = null ;
		System.out.println("Please enter any number: ");
		// b = System.console().readLine();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			b=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a = Integer.parseInt(b);
		System.out.println("your age entered is : "+a);
		if (a < 10 && a > 0 )
		{
			System.out.println("Infant");
		}
		if (a < 20 && a > 10 )
		{
			System.out.println("Teenager");
		}
		if (a < 30 && a > 20 )
		{
			System.out.println("youth");
		}
		if (a < 40 && a > 30 )
		{
			System.out.println("adult");
		}
		if (a > 40 )
		{
			System.out.println("senoir citizen");
		}
	}
	

}
