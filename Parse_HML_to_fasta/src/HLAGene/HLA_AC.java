package HLAGene;

import java.security.InvalidParameterException;
import java.util.List;

public class HLA_AC extends ExonIntronData{

	public HLA_AC(String id) {
		super(id);
	}

	@Override
	public void setExonIntron(String data, List<Integer> indexExon, List<Integer> indexIntron) {
		if(indexExon.size() != 16 || indexIntron.size() != 18){
			throw new InvalidParameterException();
		}
		//8 exons, HLA-A and HLA-C (HLA-AC) contains 8 exons.
		setExon1(filterDivider(data.substring(indexExon.get(0), indexExon.get(1)+1)));
		setExon2(filterDivider(data.substring(indexExon.get(2), indexExon.get(3)+1)));
		setExon3(filterDivider(data.substring(indexExon.get(4), indexExon.get(5)+1)));
		setExon4(filterDivider(data.substring(indexExon.get(6), indexExon.get(7)+1)));
		setExon5(filterDivider(data.substring(indexExon.get(8), indexExon.get(9)+1)));
		setExon6(filterDivider(data.substring(indexExon.get(10), indexExon.get(11)+1)));
		setExon7(filterDivider(data.substring(indexExon.get(12), indexExon.get(13)+1)));
		setExon8(filterDivider(data.substring(indexExon.get(14), indexExon.get(15)+1)));
		
		setFive_UTR(filterDivider(data.substring(indexIntron.get(0), indexIntron.get(1)+1)));
		setIntron1(filterDivider(data.substring(indexIntron.get(2), indexIntron.get(3)+1)));
		setIntron2(filterDivider(data.substring(indexIntron.get(4), indexIntron.get(5)+1)));
		setIntron3(filterDivider(data.substring(indexIntron.get(6), indexIntron.get(7)+1)));
		setIntron4(filterDivider(data.substring(indexIntron.get(8), indexIntron.get(9)+1)));
		setIntron5(filterDivider(data.substring(indexIntron.get(10), indexIntron.get(11)+1)));
		setIntron6(filterDivider(data.substring(indexIntron.get(12), indexIntron.get(13)+1)));
		setIntron7(filterDivider(data.substring(indexIntron.get(14), indexIntron.get(15)+1)));
		setThree_NS(filterDivider(data.substring(indexIntron.get(16), indexIntron.get(17)+1)));
		
	}

	@Override
	public String getCDS() {
		StringBuilder sb = new StringBuilder();
		int index = this.getFive_NS().length()+1;
		//exon1
		sb.append(index);
		sb.append("..");
		index += this.getExon1().length();
		sb.append(index);
		sb.append(";");
		
		//exon2
		index += this.getIntron1().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon2().length();
		sb.append(index);
		sb.append(";");
		
		//exon3
		index += this.getIntron2().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon3().length();
		sb.append(index);
		sb.append(";");
		
		//exon4
		index += this.getIntron3().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon4().length();
		sb.append(index);
		sb.append(";");
		
		//exon5
		index += this.getIntron4().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon5().length();
		sb.append(index);
		sb.append(";");
		
		// exon6
		index += this.getIntron5().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon6().length();
		sb.append(index);
		sb.append(";");
		
		// exon7
		index += this.getIntron6().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon7().length();
		sb.append(index);
		sb.append(";");
		
		//exon8
		index += this.getIntron7().length();
		sb.append(index);
		sb.append("..");
		index += this.getExon8().length();
		sb.append(index);
		
		return sb.toString();
	}

}
