package vn.viettuts.qlsv.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXml {
	private int maxId = 0;
	private List<User> user;
	public UserXml() {
	}
	public UserXml(int maxId, List<User> users) {
		super();
		this.maxId = maxId;
		this.user = users;
	}
	public int getMaxId() {
		return maxId;
	}
	public void setMaxId(int maxId) {
		this.maxId = maxId;
	}
	public List<User> getUsers() {
		return user;
	}
	public void setUsers(List<User> users) {
		this.user = users;
	}
}
