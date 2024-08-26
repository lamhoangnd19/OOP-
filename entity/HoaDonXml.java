package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hoadons")
@XmlAccessorType(XmlAccessType.FIELD)
public class HoaDonXml {
	private int maxId = 0;
	private List<HoaDon> hoaDon;
	
	public HoaDonXml() {
		
	}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "maxId: " + maxId + "\n";
		if (hoaDon != null)
			for (HoaDon kh : hoaDon)
				result += "\nhd: " + kh.toString();
		return result;
	}
}
