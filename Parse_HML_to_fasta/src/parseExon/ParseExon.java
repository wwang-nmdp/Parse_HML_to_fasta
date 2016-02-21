package parseExon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import HLAGene.ExonIntronData;
import HLAGene.HLA_B;
import databaseAccess.DatabaseUtil;
import statics.PolymorphStaticsProessor;

public class    ParseExon{
private GeneType type;
private Scanner scannerAlign;
private Scanner scannerFreq;
private int looper = 50;
private String cDNA;
private ArrayList<Integer> indexIntron = new ArrayList<Integer>();
private ArrayList<Integer> indexExon = new ArrayList<Integer>();
private ArrayList<ExonIntronData> seqList = new ArrayList<ExonIntronData> ();
private ArrayList<BaseFreq> freqList = new ArrayList<BaseFreq>();
private File inputAlign;
private File inputFreq;
private PrintWriter pw;
private static final char DIVIDER = '-';


	public void run(File align, File freq, GeneType type) throws Exception{
		inputAlign = align;
		inputFreq = freq;
		this.type = type;
		
		setPrinter();
		countExonIndex();
		extratExons();
		extraFreq();
		PolymorphStaticsProessor.processPolyMoph(type, freqList, seqList, indexExon, indexIntron, pw);
	}
	private void extraFreq() {
		try {
			scannerFreq = new Scanner(inputFreq);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//skip title
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
		
	}
	private void setPrinter() {
		File output = new File("./testtttt.csv");
		try {
			pw = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void extratExons()  {
		try {
			scannerAlign = new Scanner(inputAlign);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Skip four lines to first row of data
		scannerAlign.nextLine();
		scannerAlign.nextLine();
		scannerAlign.nextLine();
		scannerAlign.nextLine();
		while(scannerAlign.hasNext()){
			try {
				processSample(scannerAlign.nextLine());
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		scannerAlign.close();
	}
	private void processSample(String data) throws Exception {
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
		ei.create(data, indexExon, indexIntron);
		ei.setFullLength(data);
		seqList.add(ei);
	}
		
	
	private void countExonIndex() {
		try {
			scannerAlign = new Scanner(inputAlign);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Skip three lines to reference
		scannerAlign.nextLine();
		scannerAlign.nextLine();
		scannerAlign.nextLine();
		
		cDNA = scannerAlign.nextLine();
		//Find the first divider
		while(cDNA.charAt(looper) != DIVIDER){
			looper ++;
		}
		while(looper < cDNA.length()){
			findIntron();
			findExon();
		}
		scannerAlign.close();
		
	}
	private void findIntron() {
		if(looper >= cDNA.length()){
			return;
		}
		while(looper < cDNA.length() && !Character.isLowerCase(cDNA.charAt(looper))){
			looper++;
		}
		int start = looper;
		while(looper < cDNA.length() && (Character.isLowerCase(cDNA.charAt(looper)) || cDNA.charAt(looper) == DIVIDER) ){
			looper++;
		}
		int end = looper-1;
		indexIntron.add(start);
		indexIntron.add(end);
		System.out.println("intron: "+cDNA.substring(start, end+1));
	}
	
	private void findExon(){
		if(looper >= cDNA.length()){
			return;
		}
		while(looper < cDNA.length() && Character.isLowerCase(cDNA.charAt(looper)) ){
			looper++;
		}
		int start = looper;
		while(looper < cDNA.length() && (!Character.isLowerCase(cDNA.charAt(looper)) || cDNA.charAt(looper) == DIVIDER)){
			looper++;

		}
		int end = looper-1;
		indexExon.add(start);
		indexExon.add(end);
		System.out.println("extron: "+cDNA.substring(start, end+1));
	}
	

}
