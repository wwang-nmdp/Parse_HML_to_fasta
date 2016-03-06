package unitTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.GLSConverter;

public class GLSConverterTest {
	GLSConverter convert = new GLSConverter();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDecode() throws Exception {
		String input = "HLA-A*01:ABC";
		String outputNoExpand = "HLA-A*01:03/HLA-A*01:04/HLA-A*01:06/HLA-A*01:07/HLA-A*01:08/HLA-A*01:10/HLA-A*01:11/HLA-A*01:17/HLA-A*01:19/HLA-A*01:20/HLA-A*01:23";
		String outputExpand = "HLA-A*01:03/HLA-A*01:04N/HLA-A*01:06/HLA-A*01:07/HLA-A*01:08/HLA-A*01:10/HLA-A*01:11N/HLA-A*01:17/HLA-A*01:19/HLA-A*01:20/HLA-A*01:23";
		assertEquals(outputNoExpand, convert.decode(input, false));
		assertEquals(outputExpand, convert.decode(input, true));
		
	}
	
	@Test
	public void testEncode() throws Exception{
		String input = "HLA-A*01:01/HLA-A*01:02";
		String output = "HLA-A*01:AB";
		assertEquals(output, convert.encode(input));
		
	}

}
