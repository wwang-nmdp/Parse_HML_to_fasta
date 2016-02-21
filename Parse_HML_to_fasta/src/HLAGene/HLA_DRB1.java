package HLAGene;

import java.util.List;

public class HLA_DRB1 extends ExonIntronData{

	public HLA_DRB1(String id) {
		super(id);
	}

	@Override
	public void setExonIntron(String data, List<Integer> indexExon, List<Integer> indexIntron) {
		
		setExon2(filterDivider(data.substring(indexExon.get(0), indexExon.get(1)+1)));
		setExon3(filterDivider(data.substring(indexExon.get(2), indexExon.get(3)+1)));
		
		setIntron1(filterDivider(data.substring(indexIntron.get(0), indexIntron.get(1)+1)));
		setIntron2(filterDivider(data.substring(indexIntron.get(2), indexIntron.get(3)+1)));
		setIntron3(filterDivider(data.substring(indexIntron.get(4), indexIntron.get(5)+1)));
		
	}

	@Override
	public String getCDS() {
		// TODO Auto-generated method stub
		return "";
	}

}
