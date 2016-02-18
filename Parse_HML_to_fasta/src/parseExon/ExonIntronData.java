package parseExon;

import java.util.ArrayList;

public class ExonIntronData {
	private static final char DIVIDER = '-';
	private String id;
	private String sampleID;
	private String gls;
	private String phaseSet;
	private String five_NS = "";
	private String exon1 = "";
	private String intron1 = "";
	private String exon2 = "";
	private String intron2 = "";
	private String exon3 = "";
	private String intron3 = "";
	private String exon4 = "";
	private String intron4 = "";
	private String exon5 = "";
	private String intron5 = "";
	private String exon6 = "";
	private String intron6 = "";
	private String exon7 = "";
	private String intron7 = "";
	private String exon8 = "";
	private String three_NS = "";
	
	private String exon1_pl = "";
	private String exon2_pl = "";
	private String exon3_pl = "";
	private String exon4_pl = "";
	private String exon5_pl = "";
	private String exon6_pl = "";
	private String exon7_pl = "";
	private String exon8_pl = "";

	
	public ExonIntronData(String id){
		this.id = id;
	}
	
	public String getID(){
		return id;
		
	}
	public void setSampleId(String sID){
		sampleID = sID;
	}
	
	public String getSampleID(){
		return sampleID;
	}
	
	public void setGls(String gls){
		this.gls = gls;
	}
	
	public String getGls(){
		return gls;
	}
	
	
	public void setPhase(String phase){
		this.phaseSet = phase;
	}
	
	public String getPhase(){
		return phaseSet;
	}

	public String getFive_NS() {
		return five_NS;
	}

	public void setFive_NS(String five_NS) {
		this.five_NS = five_NS;
	}

	public String getExon1() {
		return exon1;
	}

	public void setExon1(String exon1) {
		this.exon1 = exon1;
	}

	public String getIntron1() {
		return intron1.toLowerCase();
	}

	public void setIntron1(String intron1) {
		this.intron1 = intron1;
	}

	public String getExon2() {
		return exon2;
	}

	public void setExon2(String exon2) {
		this.exon2 = exon2;
	}

	public String getIntron2() {
		return intron2.toLowerCase();
	}

	public void setIntron2(String intron2) {
		this.intron2 = intron2;
	}

	public String getExon3() {
		return exon3;
	}

	public void setExon3(String exon3) {
		this.exon3 = exon3;
	}

	public String getIntron3() {
		return intron3.toLowerCase();
	}

	public void setIntron3(String intron3) {
		this.intron3 = intron3;
	}

	public String getExon4() {
		return exon4;
	}

	public void setExon4(String exon4) {
		this.exon4 = exon4;
	}

	public String getIntron4() {
		return intron4.toLowerCase();
	}

	public void setIntron4(String intron4) {
		this.intron4 = intron4;
	}

	public String getExon5() {
		return exon5;
	}

	public void setExon5(String exon5) {
		this.exon5 = exon5;
	}

	public String getIntron5() {
		return intron5.toLowerCase();
	}

	public void setIntron5(String intron5) {
		this.intron5 = intron5;
	}

	public String getExon6() {
		return exon6;
	}

	public void setExon6(String exon6) {
		this.exon6 = exon6;
	}

	public String getIntron6() {
		return intron6.toLowerCase();
	}

	public void setIntron6(String intron6) {
		this.intron6 = intron6;
	}

	public String getExon7() {
		return exon7;
	}

	public void setExon7(String exon7) {
		this.exon7 = exon7;
	}

	public String getIntron7() {
		return intron7.toLowerCase();
	}

	public void setIntron7(String intron7) {
		this.intron7 = intron7;
	}

	public String getExon8() {
		return exon8;
	}

	public void setExon8(String exon8) {
		this.exon8 = exon8;
	}

	public String getThree_NS() {
		return three_NS.toLowerCase();
	}

	public void setThree_NS(String three_NS) {
		this.three_NS = three_NS;
	}
	
	
	
	

	public String getExon1_pl() {
		return exon1_pl;
	}

	public void setExon1_pl(String exon1_pl) {
		this.exon1_pl = exon1_pl;
	}

	public String getExon2_pl() {
		return exon2_pl;
	}

	public void setExon2_pl(String exon2_pl) {
		this.exon2_pl = exon2_pl;
	}

	public String getExon3_pl() {
		return exon3_pl;
	}

	public void setExon3_pl(String exon3_pl) {
		this.exon3_pl = exon3_pl;
	}

	public String getExon4_pl() {
		return exon4_pl;
	}

	public void setExon4_pl(String exon4_pl) {
		this.exon4_pl = exon4_pl;
	}

	public String getExon6_pl() {
		return exon6_pl;
	}

	public void setExon6_pl(String exon6_pl) {
		this.exon6_pl = exon6_pl;
	}

	public String getExon5_pl() {
		return exon5_pl;
	}

	public void setExon5_pl(String exon5_pl) {
		this.exon5_pl = exon5_pl;
	}

	public String getExon7_pl() {
		return exon7_pl;
	}

	public void setExon7_pl(String exon7_pl) {
		this.exon7_pl = exon7_pl;
	}

	public String getExon8_pl() {
		return exon8_pl;
	}

	public void setExon8_pl(String exon8_pl) {
		this.exon8_pl = exon8_pl;
	}
}
