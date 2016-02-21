package statics;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import HLAGene.ExonIntronData;
import parseExon.BaseFreq;
import parseExon.GeneSection;
import parseExon.GeneType;

public class PolymorphStaticsProessor {
	private static ArrayList<BaseFreq> freq;
	private static ArrayList<ExonIntronData> list;
	private static List<Integer> indexExon;
	private static List<Integer> indexIntron;
	private static PrintWriter pw;
	private static GeneType geneType;
	private static int[] dividerCounter;
	private static final char DIVIDER = '-';
	
	public static void processPolyMoph(GeneType gt, ArrayList<BaseFreq> freq, ArrayList<ExonIntronData> list,List<Integer> indexExon, List<Integer> indexIntron, PrintWriter pw){
		PolymorphStaticsProessor.freq = freq;
		PolymorphStaticsProessor.list = list;
		PolymorphStaticsProessor.indexExon = indexExon;
		PolymorphStaticsProessor.indexIntron = indexIntron;
		PolymorphStaticsProessor.pw = pw;
		geneType = gt;
		for(ExonIntronData data: list){
			countDivider(indexExon, data);
			pw.print(data.getID());
			pw.print(",");
			pw.print(data.getCDS());
			pw.print(",");
			findPolymorphism(data);
			pw.println(",");
		}
		pw.close();
	}
	
	 static void findPolymorphism(ExonIntronData data) {
		 switch(geneType){
		 case HLA_B:
			 processSection(GeneSection.US, Math.max(200, indexIntron.get(0)), indexIntron.get(1), data);
				processSection(GeneSection.e1, indexExon.get(0), indexExon.get(1), data);
				processSection(GeneSection.i1, indexIntron.get(2), indexIntron.get(3),data);
				processSection(GeneSection.e2, indexExon.get(2), indexExon.get(3), data);
				processSection(GeneSection.i2, indexIntron.get(4), indexIntron.get(5),data);
				processSection(GeneSection.e3, indexExon.get(4), indexExon.get(5), data);
				processSection(GeneSection.i3, indexIntron.get(6), indexIntron.get(7),data);
				processSection(GeneSection.e4, indexExon.get(6), indexExon.get(7), data);
				processSection(GeneSection.i4, indexIntron.get(8), indexIntron.get(9),data);
				processSection(GeneSection.e5, indexExon.get(8), indexExon.get(9), data);
				processSection(GeneSection.i5, indexIntron.get(10), indexIntron.get(11),data);
				processSection(GeneSection.e6, indexExon.get(10), indexExon.get(11), data);
				processSection(GeneSection.i6, indexIntron.get(12), indexIntron.get(13),data);
				processSection(GeneSection.e7, indexExon.get(12), indexExon.get(13), data);
				processSection(GeneSection.DS, indexIntron.get(14), indexIntron.get(15),data);
				break;
		 case HLA_AC:
			 processSection(GeneSection.US, Math.max(200, indexIntron.get(0)), indexIntron.get(1), data);
				processSection(GeneSection.e1, indexExon.get(0), indexExon.get(1), data);
				processSection(GeneSection.i1, indexIntron.get(2), indexIntron.get(3),data);
				processSection(GeneSection.e2, indexExon.get(2), indexExon.get(3), data);
				processSection(GeneSection.i2, indexIntron.get(4), indexIntron.get(5),data);
				processSection(GeneSection.e3, indexExon.get(4), indexExon.get(5), data);
				processSection(GeneSection.i3, indexIntron.get(6), indexIntron.get(7),data);
				processSection(GeneSection.e4, indexExon.get(6), indexExon.get(7), data);
				processSection(GeneSection.i4, indexIntron.get(8), indexIntron.get(9),data);
				processSection(GeneSection.e5, indexExon.get(8), indexExon.get(9), data);
				processSection(GeneSection.i5, indexIntron.get(10), indexIntron.get(11),data);
				processSection(GeneSection.e6, indexExon.get(10), indexExon.get(11), data);
				processSection(GeneSection.i6, indexIntron.get(12), indexIntron.get(13),data);
				processSection(GeneSection.e7, indexExon.get(12), indexExon.get(13), data);
				processSection(GeneSection.i7, indexIntron.get(14), indexIntron.get(15),data);
				processSection(GeneSection.e8, indexExon.get(14), indexExon.get(15), data);
				processSection(GeneSection.DS, indexIntron.get(16), indexIntron.get(17),data);
			 break;
		 
		 }
		 
	 }
	 
		private static void countDivider(List<Integer> indexExon, ExonIntronData data) {
			String seq = data.getFullLength();
			dividerCounter = new int[seq.length()];
			int count = 0;
			for(int i = indexExon.get(0); i < seq.length(); i++){
				if(seq.charAt(i) == DIVIDER){
					count++;
				}
				dividerCounter[i] = count;
			}
			count = 0;
			for(int i = indexExon.get(0); i >=0; i--){
				if(seq.charAt(i) == DIVIDER){
					count++;
				}
				dividerCounter[i] = count;
			}
		}
		/**
		 * process each sections such as US, DS, exons and introns.
		 * @param type us, ds, e1, i1,
		 * @param start start point, start with zero
		 * @param end end point
		 * @param data data processed
		 */
		public static void processSection(GeneSection type, int start, Integer end, ExonIntronData data) {	
			for(int i = start; i<= end; i++){
				if(i<freq.size() && freq.get(i).isValid()){
					char orignal = data.getFullLength().charAt(i);
					String change = freq.get(i).getDiff(type, adjustPosition(i), orignal);
					if(!change.equals("")){
						pw.print(change);
						//System.out.println(change);
					}
					
				}else{
					continue;
				}
			}
			
		}
		
		
		protected static int adjustPosition(int i) {
			if(i > indexExon.get(0)){
				return i - indexExon.get(0) - dividerCounter[i]; 
			}else{
				return i-indexExon.get(0) + dividerCounter[i];
			}
		}

}
