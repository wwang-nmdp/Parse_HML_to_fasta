package parseExon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import databaseAccess.DatabaseUtil;

public class    ParseExon{
private static final char DIVIDER = '-';
private static int EXON_NUMBER = 8;
private Scanner scanner;
private int looper = 50;
private String cDNA;
private ArrayList<Integer> indexIntron = new ArrayList<Integer>();
private ArrayList<Integer> indexExon = new ArrayList<Integer>();
private File input;
private String PL;
private PrintWriter pw;
private int start;
private int end;

	public void run(File file){
		input = file;
	
		
		openFile();
		setPrinter();
		
		countExonIndex();
		processPLString();
		extratExons();
	}
	private void setPrinter() {
		File output = new File("./exonStatics.csv");
		try {
			pw = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void openFile() {
		try {
			scanner = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void processPLString() {
		while(scanner.hasNext()){
			String data = scanner.nextLine();
			if(data.charAt(0) != ' '){
				continue;
			}else{
				PL = data;
				return;
			}
		}
		
	}
	private void extratExons() {
		scanner.close();
		openFile();
		//Skip four lines to first row of data
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();
		pw.println("sampleId,gls,phase_set,exon1,exon2,exon3,exon4,exon5,exon6,exon7,exon8");
		while(scanner.hasNext()){
			processSample(scanner.nextLine());
		}
		scanner.close();
		pw.close();
	}
	private void processSample(String data) {
		if(data.charAt(0) == ' '){
			//If the data is the last line, do not process
			return;
		}
		//split the data by white space or |
		String[] split = data.split(" |\\|");
		ExonIntronData ei = new ExonIntronData(split[0]);
		ei.setSampleId(split[1]);
		ei.setGls(split[2]);
		ei.setPhase(split[3]);
		pw.print(split[1]);
		pw.print(",");
		pw.print(split[2]);
		pw.print(",");
		pw.print(split[3]);
		pw.print(",");

		if(indexExon.size() == 16 ){
			//8 exons
			ei.setExon1(filterDivider(data.substring(indexExon.get(0), indexExon.get(1)+1)));
			ei.setExon2(filterDivider(data.substring(indexExon.get(2), indexExon.get(3)+1)));
			ei.setExon3(filterDivider(data.substring(indexExon.get(4), indexExon.get(5)+1)));
			ei.setExon4(filterDivider(data.substring(indexExon.get(6), indexExon.get(7)+1)));
			ei.setExon5(filterDivider(data.substring(indexExon.get(8), indexExon.get(9)+1)));
			ei.setExon6(filterDivider(data.substring(indexExon.get(10), indexExon.get(11)+1)));
			ei.setExon7(filterDivider(data.substring(indexExon.get(12), indexExon.get(13)+1)));
			ei.setExon8(filterDivider(data.substring(indexExon.get(14), indexExon.get(15)+1)));
			
			ei.setFive_NS(filterDivider(data.substring(indexIntron.get(0), indexIntron.get(1)+1)));
			ei.setIntron1(filterDivider(data.substring(indexIntron.get(2), indexIntron.get(3)+1)));
			ei.setIntron2(filterDivider(data.substring(indexIntron.get(4), indexIntron.get(5)+1)));
			ei.setIntron3(filterDivider(data.substring(indexIntron.get(6), indexIntron.get(7)+1)));
			ei.setIntron4(filterDivider(data.substring(indexIntron.get(8), indexIntron.get(9)+1)));
			ei.setIntron5(filterDivider(data.substring(indexIntron.get(10), indexIntron.get(11)+1)));
			ei.setIntron6(filterDivider(data.substring(indexIntron.get(12), indexIntron.get(13)+1)));
			ei.setIntron7(filterDivider(data.substring(indexIntron.get(14), indexIntron.get(15)+1)));
			ei.setThree_NS(filterDivider(data.substring(indexIntron.get(16), indexIntron.get(17)+1)));
		}else if(indexExon.size() == 14){
			//7 exons
			ei.setExon1(filterDivider(data.substring(indexExon.get(0), indexExon.get(1)+1)));
			ei.setExon2(filterDivider(data.substring(indexExon.get(2), indexExon.get(3)+1)));
			ei.setExon3(filterDivider(data.substring(indexExon.get(4), indexExon.get(5)+1)));
			ei.setExon4(filterDivider(data.substring(indexExon.get(6), indexExon.get(7)+1)));
			ei.setExon5(filterDivider(data.substring(indexExon.get(8), indexExon.get(9)+1)));
			ei.setExon6(filterDivider(data.substring(indexExon.get(10), indexExon.get(11)+1)));
			ei.setExon7(filterDivider(data.substring(indexExon.get(12), indexExon.get(13)+1)));
		
			
			ei.setFive_NS(filterDivider(data.substring(indexIntron.get(0), indexIntron.get(1)+1)));
			ei.setIntron1(filterDivider(data.substring(indexIntron.get(2), indexIntron.get(3)+1)));
			ei.setIntron2(filterDivider(data.substring(indexIntron.get(4), indexIntron.get(5)+1)));
			ei.setIntron3(filterDivider(data.substring(indexIntron.get(6), indexIntron.get(7)+1)));
			ei.setIntron4(filterDivider(data.substring(indexIntron.get(8), indexIntron.get(9)+1)));
			ei.setIntron5(filterDivider(data.substring(indexIntron.get(10), indexIntron.get(11)+1)));
			ei.setIntron6(filterDivider(data.substring(indexIntron.get(12), indexIntron.get(13)+1)));
			ei.setThree_NS(filterDivider(data.substring(indexIntron.get(14), indexIntron.get(15)+1)));
		} else{
			//6 exons
			ei.setExon1(filterDivider(data.substring(indexExon.get(0), indexExon.get(1)+1)));
			ei.setExon2(filterDivider(data.substring(indexExon.get(2), indexExon.get(3)+1)));
			ei.setExon3(filterDivider(data.substring(indexExon.get(4), indexExon.get(5)+1)));
			ei.setExon4(filterDivider(data.substring(indexExon.get(6), indexExon.get(7)+1)));
			ei.setExon5(filterDivider(data.substring(indexExon.get(8), indexExon.get(9)+1)));
			ei.setExon6(filterDivider(data.substring(indexExon.get(10), indexExon.get(11)+1)));
		
			ei.setFive_NS(filterDivider(data.substring(indexIntron.get(0), indexIntron.get(1)+1)));
			ei.setIntron1(filterDivider(data.substring(indexIntron.get(2), indexIntron.get(3)+1)));
			ei.setIntron2(filterDivider(data.substring(indexIntron.get(4), indexIntron.get(5)+1)));
			ei.setIntron3(filterDivider(data.substring(indexIntron.get(6), indexIntron.get(7)+1)));
			ei.setIntron4(filterDivider(data.substring(indexIntron.get(8), indexIntron.get(9)+1)));
			ei.setIntron5(filterDivider(data.substring(indexIntron.get(10), indexIntron.get(11)+1)));
			ei.setThree_NS(filterDivider(data.substring(indexIntron.get(12), indexIntron.get(13)+1)));
			
		}
		if(PL != null){
			caculatePL(data, ei);
		}
		
		
		try {
			DatabaseUtil.insertExonData(ei);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void caculatePL(String data, ExonIntronData ei) {
		start = ei.getFive_NS().length()+1;
		end = start + ei.getExon1().length() -1;
		printRange();
		
		ei.setExon1_pl(filte(data.substring(indexExon.get(0), indexExon.get(1)+1), PL.substring(indexExon.get(0), indexExon.get(1)+1)));
		
		pw.print(",");
		start = end +1;
		end = start + ei.getExon2().length() -1;
		printRange();
		
		ei.setExon2_pl(filte(data.substring(indexExon.get(2), indexExon.get(3)+1), PL.substring(indexExon.get(2), indexExon.get(3)+1)));
		pw.print(",");
		start = end +1;
		end = start + ei.getExon3().length() -1;
		printRange();
		ei.setExon3_pl(filte(data.substring(indexExon.get(4), indexExon.get(5)+1), PL.substring(indexExon.get(4), indexExon.get(5)+1)));
		pw.print(",");
		start = end +1;
		end = start + ei.getExon4().length() -1;
		printRange();
		ei.setExon4_pl(filte(data.substring(indexExon.get(6), indexExon.get(7)+1), PL.substring(indexExon.get(6), indexExon.get(7)+1)));
		pw.print(",");
		start = end +1;
		end = start + ei.getExon5().length() -1;
		printRange();
		ei.setExon5_pl(filte(data.substring(indexExon.get(8), indexExon.get(9)+1), PL.substring(indexExon.get(8), indexExon.get(9)+1)));
		pw.print(",");
		start = end +1;
		end = start + ei.getExon6().length() -1;
		printRange();
		ei.setExon6_pl(filte(data.substring(indexExon.get(10), indexExon.get(11)+1), PL.substring(indexExon.get(10), indexExon.get(11)+1)));
		pw.print(",");
		
		if(indexExon.size() >= 14){
			start = end +1;
			end = start + ei.getExon7().length() -1;
			printRange();
			ei.setExon7_pl(filte(data.substring(indexExon.get(12), indexExon.get(13)+1), PL.substring(indexExon.get(12), indexExon.get(13)+1)));
			pw.print(",");
		}
		
		if(indexExon.size() == 16){
			start = end +1;
			end = start + ei.getExon8().length() -1;
			printRange();
			ei.setExon8_pl(filte(data.substring(indexExon.get(14), indexExon.get(15)+1), PL.substring(indexExon.get(14), indexExon.get(15)+1)));
		}
		
		pw.println();
	}
	private void printRange() {
		pw.print(start);
		pw.print(DIVIDER);
		pw.print(end);
		pw.print(" ");
	}
	
	private String filterDivider(String seq){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < seq.length(); i++){
			if(seq.charAt(i) != DIVIDER){
				sb.append(seq.charAt(i));
			}
		}
		return sb.toString();
	}
	
	private String filte(String data, String pattern) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < data.length() ; i++){
			if(pattern.charAt(i) == '*'){
				sb.append('*');
			}else{
				sb.append(data.charAt(i));
				pw.print(i+start);
				pw.print(data.charAt(i));
			}
		}
		return filterDivider(sb.toString());
	}
	private void countExonIndex() {
		//Skip three lines to reference
		scanner.nextLine();
		scanner.nextLine();
		scanner.nextLine();
		
		cDNA = scanner.nextLine();
		//Find the first divider
		while(cDNA.charAt(looper) != DIVIDER){
			looper ++;
		}
		while(looper < cDNA.length()){
			findIntron();
			findExon();
		}
		
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
