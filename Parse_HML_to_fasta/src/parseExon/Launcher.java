package parseExon;

import java.io.File;



public class Launcher {

	public static void main(String[] args) throws Exception {
		ParseExon pe = new ParseExon(new File("HLA-A_MMtest.clu"),GeneType.HLA_AC);
		pe.run();
//		
//		ParseExon pe2 = new ParseExonFreq(new File("HLA_A_Group_out.clu"),new File(""),GeneType.HLA_AC);
//		pe2.run();
//		
	}

}
