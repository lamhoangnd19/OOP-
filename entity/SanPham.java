package vn.viettuts.qlsv.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.LocalDateTimeAdapter;

@XmlRootElement(name = "sanpham")
@XmlAccessorType(XmlAccessType.FIELD)
public class SanPham {
	private int id;
	private String name;
	private double price;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	private LocalDateTime timeCreate;
	private String urlImage;
	private int count;
	public SanPham() {
	}
	public SanPham(int id, String name, double price, LocalDateTime timeCreate, String urlImage, int count) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.timeCreate = timeCreate;
		this.urlImage = urlImage;
		this.count = count;
	}
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public LocalDateTime getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(LocalDateTime timeCreate) {
		this.timeCreate = timeCreate;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
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
		return "id: " + id + ";name: " + name + ";price: " + price + ";urlImage: " + urlImage + ";count: " + count + ";timeCreate: " + DateTimeFormater.toString(timeCreate);
	}
}
