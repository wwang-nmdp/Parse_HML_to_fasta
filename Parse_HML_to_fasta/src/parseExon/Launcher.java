package parseExon;

import java.io.File;

import databaseAccess.DatabaseUtil;

public class Launcher {

	public static void main(String[] args) throws Exception {
		ParseExon pe = new ParseExon();
		pe.run(new File("TESTHLA-B.clu"), new File("HLA-B.txt"),GeneType.HLA_B);
		
	}

}
