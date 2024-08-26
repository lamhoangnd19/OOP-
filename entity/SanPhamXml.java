package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sanphams")
@XmlAccessorType(XmlAccessType.FIELD)
public class SanPhamXml {
	private int maxId = 0;
	private List<SanPham> sanpham;
	
	public SanPhamXml() {}

	public int getMaxId() {
		return maxId;
	}

	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}

	public List<SanPham> getSanPhams() {
		return sanpham;
	}

	public void setSanPhams(List<SanPham> sanPhams) {
		this.sanpham = sanPhams;
	}
}
