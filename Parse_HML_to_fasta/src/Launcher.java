import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Launcher {
	private static FastaGenerator generetor;

	public static void main(String[] args) {
		GLSConverter gc = new GLSConverter();
		try {
			gc.sendPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		generetor = new FastaGenerator();
//		File folder = new File("./input");
//		File outputFolder = new File("./output");
//		try {
//			if(outputFolder.exists()){
//				FileUtils.cleanDirectory(outputFolder);
//			}
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			
//			
//			e1.printStackTrace();
//		}
//		File[] inputList = folder.listFiles();
//		for(int i = 0 ; i < inputList.length; i++){
//			if(inputList[i].getName().contains("xml")){
//				String fileName = FilenameUtils.removeExtension(inputList[i].getName());
//				process(inputList[i], fileName);
//			}
//			
//		}

	}
	
	private static void process(File input, String outputName){
		
		File output = new File("./output/" + outputName +".fasta");
		try{
			generetor.run(input,output);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
