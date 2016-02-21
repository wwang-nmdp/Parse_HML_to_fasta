package parseExon;

import java.io.File;

import databaseAccess.DatabaseUtil;

public class Launcher {

	public static void main(String[] args) throws Exception {
		ParseExon pe = new ParseExon();
		pe.run(new File("HLA_A_Group_out.clu"), new File("HLA-A_Freq.txt"),GeneType.HLA_AC);
		
	}

}
