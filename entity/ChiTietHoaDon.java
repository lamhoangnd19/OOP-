package vn.viettuts.qlsv.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "chitiethoadon")
public class ChiTietHoaDon {
	private int spId;
	private int count;
	public ChiTietHoaDon(int spId, int count) {
		super();
		this.spId = spId;
		this.count = count;
	}
	public ChiTietHoaDon() {
		
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "spId: " + spId + ";count: " + count;
	}
}
