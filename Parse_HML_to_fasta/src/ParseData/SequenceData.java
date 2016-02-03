package ParseData;

public class SequenceData {
	String sampleId;
	String locus;
	String type;
	String gls;
	String phaseSet;
	String sequence;
	
	public SequenceData(String id){
		sampleId = id;
	}
	
	public void setLocus(String locus){
		this.locus = locus;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setGls(String gls){
		this.gls = gls;
	}
	
	public void setPhaseSet(String ps){
		phaseSet = ps;
	}
	
	public void setSequence(String seq){
		sequence = seq;
	}
	
	public String getSampleId(){
		return "'"+sampleId+"'";
	}
	
	public String getLocus(){
		return "'"+locus+"'";
	}
	
	public String getType(){
		return "'"+type+"'";
	}
	
	public String getGls(){
		return "'"+gls+"'";
	}
	
	public String getPhaseSet(){
		return "'"+phaseSet+"'";
	}

	public String getSequence(){
		return "'"+sequence+"'";
	}
}
