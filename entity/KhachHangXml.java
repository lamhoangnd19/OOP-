package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "khachhangs")
@XmlAccessorType(XmlAccessType.FIELD)
public class KhachHangXml {
	private int maxId = 0;
	private List<KhachHang> khachhang;
	public KhachHangXml() {
		
	}
	public KhachHangXml(int maxId, List<KhachHang> khachHangs) {
		super();
		this.maxId = maxId;
		this.khachhang = khachHangs;
	}
	public int getMaxId() {
		return maxId;
	}
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}
	public List<KhachHang> getKhachHangs() {
		return khachhang;
	}
	public void setKhachHangs(List<KhachHang> khachHangs) {
		this.khachhang = khachHangs;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "maxId: " + maxId + "\n";
		if (khachhang != null)
			for (KhachHang kh : khachhang)
				result += "kh: " + kh.toString() + "\n";
		return result;
	}
}
