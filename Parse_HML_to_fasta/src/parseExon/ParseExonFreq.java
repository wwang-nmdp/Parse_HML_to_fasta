package parseExon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import HLAGene.ExonIntronData;
import config.Config;
import statics.PolymorphStaticsProessor;

public class ParseExonFreq extends ParseExon{
	private File inputFreq;
	private Scanner scannerFreq;
	protected ArrayList<ExonIntronData> seqList = new ArrayList<ExonIntronData> ();

	/**
	 * Input files contains one alignment and frequency table and genetype.
	 * @param align alignment result from Clustal_Omega output in .Clu format.
	 * @param freq 
	 * @param type
	 * @throws Exception
	 */
		public  ParseExonFreq(File align, File freq, GeneType type) throws Exception{
			super(align, type);
			inputFreq = freq;	
		}
	public void setup(){
		super.setup();
		extraFreq();
	}
	
	public void setPrinter(){
		super.setPrinter();
		File output = new File(Config.freqFile);
		try {
			pwFreq = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void extraFreq() {
		try {
			scannerFreq = new Scanner(inputFreq);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//skip title: 8 lines
		for(int i = 0; i < 8; i++){
			scannerFreq.nextLine();
		}
		while(scannerFreq.hasNextLine()){
			String line = scannerFreq.nextLine();
			Scanner lineSc = new Scanner(line);
			if(!lineSc.hasNextInt()){
				lineSc.close();
				return;
			}
			lineSc.nextInt();	
			freqList.add(new BaseFreq(lineSc.nextInt(), lineSc.nextInt(), lineSc.nextInt(), lineSc.nextInt()));
			lineSc.close();
		}
		scannerFreq.close();
		
	}
	public void process(){
		PolymorphStaticsProessor.processPolyMoph(type, freqList, seqList, indexExon, indexIntron, pwFreq);
	}
	
	public void processSample(String data) throws Exception{
		if(data.charAt(0) == ' '){
			//If the data is the last line, do not process
			return;
		}
		//split the data by white space or |
		String[] split = data.split(" |\\|");
		ExonIntronData ei = ExonIntronData.buildHLA(type, split[0]);
		ei.setSampleId(split[1]);
		ei.setGls(split[2]);
		ei.setPhase(split[3]);
		ei.setExonIntron(data, indexExon, indexIntron);
		ei.setFullLength(data);
		seqList.add(ei);
	}
}
