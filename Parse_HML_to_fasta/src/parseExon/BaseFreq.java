package parseExon;

public class BaseFreq {
	private int A;
	private int T;
	private int G;
	private int C;
	private static int total;
	private static int RESOLUTION = 5;
	
	BaseFreq(int a, int t, int g, int c){
		A = a;
		T = t;
		G = g;
		C = c;
	}
	
	public boolean isValid(){
		int zeroCount = 0;
		if(A == 0){
			zeroCount ++;
		}
		if(T == 0){
			zeroCount ++;
		}
		if(G == 0){
			zeroCount ++;
		}
		if(C == 0){
			zeroCount ++;
		}
		if(zeroCount ==3){
			return false;
		}else{
			return true;
		}
	}
//Calculate the base frequency from frequency table
	
	public String getDiff(GeneSection type, int position, char c){
		String check = getFreq(getCount(c));
		if(Double.parseDouble(check) == 0){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(type.toString());
		sb.append(":");
		//fix
		sb.append(position+1);
		setTotal();
		if(c != 'A' && A != 0){
			sb.append('A');
			sb.append ((getFreq(A)));
			sb.append('/');
		}
		if(c != 'T' && T != 0){
			sb.append('T');
			sb.append( (getFreq(T)));
			sb.append('/');
		}
		if(c != 'G' && G != 0){
			sb.append('G');
			sb.append( (getFreq(G)));
			sb.append('/');
		}
		if(c != 'C' && C != 0){
			sb.append('C');
			sb.append((getFreq(C)));
			sb.append('/');
		}
		if(sb.charAt(sb.length()-1) == '/'){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append(">");
		sb.append(c);
		sb.append(check);
		sb.append(";");
		return sb.toString();
	}
	
	private int getCount(char c){
		if(c == 'A'){
			return A;
		}else if(c == 'T'){
			return T;
		}else if(c == 'G'){
			return G;
		}else{
			return C;
		}
		
	}
	
	private String getFreq(int num){
		double result = (double)num/total;
		return String.format("%."+RESOLUTION+"f", result);
	}
	public void setTotal(){
		BaseFreq.total = A+T+G+C;
	}

}
