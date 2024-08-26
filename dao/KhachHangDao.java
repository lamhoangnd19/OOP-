package vn.viettuts.qlsv.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.KhachHangXml;
import vn.viettuts.qlsv.entity.SanPhamXml;
import vn.viettuts.qlsv.utils.FileUtils;

public class KhachHangDao {
    private static final String FILE_NAME = "customer.xml";
    private KhachHangXml khachHangXml;
    public KhachHangDao() {
    	try {
    		readListProducts();
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
    private void readListProducts() {
        khachHangXml = (KhachHangXml) FileUtils.readXMLFile(
    		FILE_NAME, KhachHangXml.class);
        System.out.println(khachHangXml == null ? "null" : khachHangXml.toString());
    }
    public List<KhachHang> getList() {
    	return khachHangXml.getKhachHangs();
    }
    public boolean add(KhachHang kh) {
    	System.err.println(kh.toString());
    	if (Objects.isNull(kh)) return false;
    	kh.setTimeCreate(LocalDateTime.now());
    	kh.setId(khachHangXml.getMaxId() + 1);
    	khachHangXml.setMaxId(khachHangXml.getMaxId() + 1);
    	if (Objects.isNull(khachHangXml.getKhachHangs()))
    		khachHangXml.setKhachHangs(new ArrayList<KhachHang>());
    	khachHangXml.getKhachHangs().add(kh);
    	try {
    		write();
    		return true;
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    	return false;
    }
    public boolean edit(KhachHang kh) {
    	System.err.println(kh.toString());
    	if (Objects.isNull(kh)) return false;
    	if (Objects.isNull(khachHangXml.getKhachHangs())) return false;
    	for (int i = 0; i < khachHangXml.getKhachHangs().size(); i++) {
    		KhachHang k = khachHangXml.getKhachHangs().get(i);
    		if (k.getId() == kh.getId()) {
    	    	kh.setTimeCreate(k.getTimeCreate());
    	    	khachHangXml.getKhachHangs().set(i, kh);
    	    	try {
    	    		write();
    	    		return true;
    	    	} catch (Exception e) {
    				// TODO: handle exception
    	    		System.out.println(e.getMessage());
    	    		return false;
    	    	}		
    		}
    	}
    	return false;
    }
    public boolean remove(KhachHang kh) {
    	if (Objects.isNull(kh)) return false;
    	if (Objects.isNull(khachHangXml.getKhachHangs())) return false;
    	for (int i = 0; i < khachHangXml.getKhachHangs().size(); i++) {
    		KhachHang k = khachHangXml.getKhachHangs().get(i);
    		if (k.getId() == kh.getId()) {
    	    	khachHangXml.getKhachHangs().remove(i);
    	    	try {
    	    		write();
    	    		return true;
    	    	} catch (Exception e) {
    				// TODO: handle exception
    	    		System.out.println(e.getMessage());
    	    		return false;
    	    	}		
    		}
    	}
    	return false;
    }
    private void write() {
        FileUtils.writeXMLtoFile(FILE_NAME, khachHangXml);
    }
}
