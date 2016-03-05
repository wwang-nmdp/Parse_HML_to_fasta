package parseExon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import HLAGene.ExonIntronData;
import HLAGene.HLA_B;
import config.Config;
import databaseAccess.DatabaseUtil;
import statics.PolymorphStaticsProessor;

public class    ParseExon{
protected GeneType type;
protected Scanner scannerAlign;

//cut the first 100 positions because of the low coverage.
private int looper = 0;
private String refSeq;
protected ArrayList<Integer> indexIntron = new ArrayList<Integer>();
protected ArrayList<Integer> indexExon = new ArrayList<Integer>();

protected ArrayList<BaseFreq> freqList = new ArrayList<BaseFreq>();
protected File inputAlign;
protected PrintWriter pwExon;
protected PrintWriter pwFreq;
private static final char DIVIDER = '-';



	
	/**
	 * Input files contains one alignment and frequency table and genetype.
	 * @param align alignment result from Clustal_Omega output in .Clu format.
	 * @param freq 
	 * @param type
	 * @throws Exception
	 */
		public ParseExon(File align, GeneType type) throws Exception{
			inputAlign = align;
			this.type = type;
		}
		
	public void run(){
		setup();
		process();
	}
	
	public void setup(){
		setPrinter();
		countExonIndex();
		extratExons();
	}
	
	public void process(){
		pwExon.close();
	}
	
	public void setPrinter() {
		File output = new File(Config.exonDataFile);
		try {
			pwExon = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * extra the sequences of exons from alignment results which might contains gaps.
	 */
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
	
	public void processSample(String data) throws Exception {
		if(data.charAt(0) == ' '){
			//If the data is the last line, do not process
			return;
		}
		//split the data by white space or |
		String[] split = data.split(" |\\|");
		ExonIntronData ei = ExonIntronData.buildHLA(type);
		ei.setSampleId(split[0]);
		ei.setGls(split[1]);
		ei.setPhase(split[2]);
		ei.setExonIntron(data, indexExon, indexIntron);
		pwExon.print(ei.getSampleID());
		pwExon.print(",");
		pwExon.print(ei.getGls());
		pwExon.print(",");
		pwExon.print(ei.getPhase());
		pwExon.print(",");
		pwExon.print(ei.getCDS());
		pwExon.print(",");
		pwExon.println(ei.seqToString());
		
	}
		
	/**
	 * find the index of all exon and intron
	 */
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
		
		refSeq = scannerAlign.nextLine();
		String[] data = refSeq.split(" +");
		looper = data[0].length();
		while(looper < refSeq.length()){
			findIntron();
			findExon();
		}
		scannerAlign.close();
		
	}
	private void findIntron() {
		if(looper >= refSeq.length()){
			return;
		}
		while(looper < refSeq.length() && !Character.isLowerCase(refSeq.charAt(looper))){
			looper++;
		}
		int start = looper;
		while(looper < refSeq.length() && (Character.isLowerCase(refSeq.charAt(looper)) || refSeq.charAt(looper) == DIVIDER) ){
			looper++;
		}
		int end = looper-1;
		indexIntron.add(start);
		indexIntron.add(end);
		System.out.println("intron: "+refSeq.substring(start, end+1));
	}
	
	private void findExon(){
		if(looper >= refSeq.length()){
			return;
		}
		while(looper < refSeq.length() && Character.isLowerCase(refSeq.charAt(looper)) ){
			looper++;
		}
		int start = looper;
		while(looper < refSeq.length() && (!Character.isLowerCase(refSeq.charAt(looper)) || refSeq.charAt(looper) == DIVIDER)){
			looper++;

		}
		int end = looper-1;
		indexExon.add(start);
		indexExon.add(end);
		System.out.println("extron: "+refSeq.substring(start, end+1));
	}
	

}
