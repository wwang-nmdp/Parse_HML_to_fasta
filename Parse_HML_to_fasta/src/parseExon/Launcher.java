package parseExon;

import java.io.File;

import databaseAccess.DatabaseUtil;

public class Launcher {

	public static void main(String[] args) {
		ParseExon pe = new ParseExon();
		pe.run(new File("HLA-DQB1_out.clu"));

	}

}
