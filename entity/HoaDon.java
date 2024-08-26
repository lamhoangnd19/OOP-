package vn.viettuts.qlsv.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "hoadon")
public class HoaDon {
	private int id;
	private int khId;
	private double totalPrice;
	private List<ChiTietHoaDon> details;
	public List<ChiTietHoaDon> getDetails() {
		return details;
	}
	public void setDetails(List<ChiTietHoaDon> details) {
		this.details = details;
	}
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime timeCreate;
	public HoaDon() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getKhId() {
		return khId;
	}
	public void setKhId(int khId) {
		this.khId = khId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDateTime getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(LocalDateTime timeCreate) {
		this.timeCreate = timeCreate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String result = "id: " + id + ";khId: " + khId + ";totalPrice: " + totalPrice + ";timeCreate: " + DateTimeFormater.toString(timeCreate);
		if (Objects.nonNull(details))
			for (ChiTietHoaDon cthd : details)
				result += "\n" + cthd.toString();
		return result;
	}
}
