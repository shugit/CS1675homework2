import static org.junit.Assert.*;

import org.junit.Test;


public class test {

	@Test
	public void test() {
		ExampleList e = new ExampleList(new In("votes.data"), new In("votes.config"));
		StdOut.println("----------------------------------------\n"+e);
	}

}
