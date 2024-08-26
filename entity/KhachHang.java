package vn.viettuts.qlsv.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.LocalDateAdapter;
import vn.viettuts.qlsv.utils.LocalDateTimeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "khachhang")
public class KhachHang {
	private int id;
	private String name;
	private String phoneNumber;
	private boolean gender;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate dob;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime timeCreate;
    public KhachHang() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDateTime getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(LocalDateTime timeCreate) {
		this.timeCreate = timeCreate;
	}
	public KhachHang(int id, String name, String phoneNumber, boolean gender, LocalDate dob, LocalDateTime timeCreate) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
		this.dob = dob;
		this.timeCreate = timeCreate;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id: " + id + ";name: " + name + ";phoneNumber: " + phoneNumber + ";gender: " + gender + ";dob: " + DateTimeFormater.toString(dob) + ";timeCreate: " + DateTimeFormater.toString(timeCreate);
	}
}
