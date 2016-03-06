import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import ParseData.FastaGenerator;
import databaseAccess.DatabaseUtil;

public class Launcher {
	private static FastaGenerator generetor;

	public static void main(String[] args) {
	
		generetor = new FastaGenerator();
		File folder = new File("./input");
		File outputFolder = new File("./output");
		try {
			if(outputFolder.exists()){
				FileUtils.cleanDirectory(outputFolder);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//connect to database 
//		DatabaseUtil.connectDatabase();
//		DatabaseUtil.createTable();
		File[] inputList = folder.listFiles();
		for(int i = 0 ; i < inputList.length; i++){
			if(inputList[i].getName().contains("xml")){
				process(inputList[i]);
			}
			
		}
		//close database
//		DatabaseUtil.cleanUp();

	}
	
	private static void process(File input){
		try{
			generetor.run(input);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
