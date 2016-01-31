import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FastaGeneratorTest {

    private FastaGenerator fg;
    
	@Before
	public void setUp() throws Exception {
		fg = new FastaGenerator();
	}

	@Test
	public void testGllsString() {
		
		String input = "HLA-DRB1*11:01:01+HLA-DRB1*15:01:01:01|HLA-DRB1*11:01:01+HLA-DRB1*15:01:01:02|HLA-DRB1*11:01:01+HLA-DRB1*15:01:01:03|HLA-DRB1*11:01:01+HLA-DRB1*15:01:01:04";
		String gls1 = "HLA-DRB1*11:01:01/HLA-DRB1*11:01:01/HLA-DRB1*11:01:01/HLA-DRB1*11:01:01";
		String gls2 = "HLA-DRB1*15:01:01:01/HLA-DRB1*15:01:01:02/HLA-DRB1*15:01:01:03/HLA-DRB1*15:01:01:04";
		List<String> result = fg.parseGls(input);
		assertEquals(gls1, result.get(0));
		assertEquals(gls2, result.get(1));
	}

}
