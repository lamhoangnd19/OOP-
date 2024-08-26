package vn.viettuts.qlsv.dao;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.SanPham;
import vn.viettuts.qlsv.entity.SanPhamXml;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.entity.UserXml;
import vn.viettuts.qlsv.utils.FileUtils;

public class SanPhamDao {
    private static final String FILE_NAME = "product.xml";
    private SanPhamXml sanPhamXml;
    public SanPhamDao() {
    	try {
    		readListProducts();
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
    private void readListProducts() {
        sanPhamXml = (SanPhamXml) FileUtils.readXMLFile(
    		FILE_NAME, SanPhamXml.class);
        System.out.println(sanPhamXml == null ? "null" : sanPhamXml.getSanPhams().get(0).toString());
    }
    public List<SanPham> getList() {
    	return sanPhamXml.getSanPhams();
    }
    public boolean add(SanPham kh) {
    	System.err.println(kh.toString());
    	if (Objects.isNull(kh)) return false;
    	kh.setTimeCreate(LocalDateTime.now());
    	kh.setId(sanPhamXml.getMaxId() + 1);
    	sanPhamXml.setMaxId(sanPhamXml.getMaxId() + 1);
    	if (Objects.isNull(sanPhamXml.getSanPhams()))
    		sanPhamXml.setSanPhams(new ArrayList<SanPham>());
    	sanPhamXml.getSanPhams().add(kh);
    	try {
    		write();
    		return true;
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    	return false;
    }
    public boolean edit(SanPham kh) {
    	System.err.println(kh.toString());
    	if (Objects.isNull(kh)) return false;
    	if (Objects.isNull(sanPhamXml.getSanPhams())) return false;
    	for (int i = 0; i < sanPhamXml.getSanPhams().size(); i++) {
    		SanPham k = sanPhamXml.getSanPhams().get(i);
    		if (k.getId() == kh.getId()) {
    	    	kh.setTimeCreate(k.getTimeCreate());
    	    	sanPhamXml.getSanPhams().set(i, kh);
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
    public boolean remove(SanPham kh) {
    	if (Objects.isNull(kh)) return false;
    	if (Objects.isNull(sanPhamXml.getSanPhams())) return false;
    	for (int i = 0; i < sanPhamXml.getSanPhams().size(); i++) {
    		SanPham k = sanPhamXml.getSanPhams().get(i);
    		if (k.getId() == kh.getId()) {
    			try {
		            String filePath = "images/products/" + k.getUrlImage();
		            File file = new File(filePath);
		            if (file.exists()) {
		                file.delete();
		            }
    			} catch (Exception e) {}
    	    	sanPhamXml.getSanPhams().remove(i);
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
        FileUtils.writeXMLtoFile(FILE_NAME, sanPhamXml);
    }
    public boolean save() {
    	try {
    		write();
    		return true;
    	} catch (Exception e) {
			// TODO: handle exception
		}
    	return false;
    }
}
