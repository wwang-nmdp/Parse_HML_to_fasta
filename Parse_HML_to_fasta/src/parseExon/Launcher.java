package parseExon;

import java.io.File;

import databaseAccess.DatabaseUtil;

public class Launcher {

	public static void main(String[] args) {
		ParseExon pe = new ParseExon();
		DatabaseUtil.connectDatabase();
		DatabaseUtil.creatExonTable();
		pe.run(new File("HLA-DRB1_out.clu"));
		DatabaseUtil.cleanUp();

	}

}
