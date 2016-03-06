package main;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class Launcher {
	private static FastaGenerator generetor;

	public static void main(String[] args) {
		Mode mode;
		if(args.length == 0){
			mode = Mode.None;
			generetor = new FastaGenerator(mode);
		}else if (args.length ==2 && args[0].toLowerCase().equals("decode")){
			mode = Mode.Decode;
			String expand = args[1].toLowerCase();
			if(expand.equals("f") || expand.equals("false")){
				generetor = new FastaGenerator(mode, false);
			}else if (expand.equals("t") || expand.equals("true")){
				generetor = new FastaGenerator(mode, true);
			}else{
				System.out.println("Illegal argument, must set true or false for expand.");
				return;
			}
			
		}else if (args[0].toLowerCase().equals("encode")){
			mode = Mode.Encode;
			generetor = new FastaGenerator(mode);
		}else{
			System.out.println("Illegal argument, must be null, encode, or decode.");
			return;
		}
		
		File folder = new File("./input");
		File outputFolder = new File("./output");
		try {
			if (outputFolder.exists()) {
				FileUtils.cleanDirectory(outputFolder);
			}else{
				outputFolder.mkdir();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File[] inputList = folder.listFiles();
		for (int i = 0; i < inputList.length; i++) {
			if (inputList[i].getName().contains("xml")) {
				String fileName = FilenameUtils.removeExtension(inputList[i].getName());
				process(inputList[i], fileName);
			}

		}

	}

	private static void process(File input, String outputName) {

		File output = new File("./output/" + outputName + ".fasta");
		try {
			generetor.run(input, output);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
