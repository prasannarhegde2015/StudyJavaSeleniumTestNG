import static org.junit.Assert.fail;
import org.junit.Test;


public class NewUnitTest {
	@Test
	public void test1()
	{
		
		if (mMultiply(10, 30)== 300)
				{
			         System.out.println("test pass");
			         
				}
		else
		{
			 System.out.println("test fail");
			 fail("test failed");
		}
	}
	
    @Test
    public void test2()
	{
		
		if (mAdd(10, 30)== 40)
				{
			         System.out.println("test pass");
			         
				}
		else
		{
			 System.out.println("test fail");
			 fail("test failed");
		}
	}
	public int mMultiply(int a, int b) {
		return a * b;
	}

	public int mAdd(int a, int b) {
		return a + b;
		
	}

	public double mdevide(int a, int b) {
		return a / b;
	}
}


