package vn.viettuts.qlsv.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import vn.viettuts.qlsv.entity.HoaDon;
import vn.viettuts.qlsv.entity.HoaDonXml;
import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.KhachHangXml;
import vn.viettuts.qlsv.utils.FileUtils;

public class HoaDonDao {
    private static final String FILE_NAME = "hoadon.xml";
    private HoaDonXml hoaDonXml;
    public HoaDonDao() {
    	try {
    		readListProducts();
        	Collections.sort(hoaDonXml.getHoaDon(), new Comparator<HoaDon>() {
        		public int compare(HoaDon arg0, HoaDon arg1) {
        			return arg1.getTimeCreate().compareTo(arg0.getTimeCreate());
        		}
        	});
    	} catch (Exception e) {
			// TODO: handle exception
		}
    }
    private void readListProducts() {
        hoaDonXml = (HoaDonXml) FileUtils.readXMLFile(
    		FILE_NAME, HoaDonXml.class);
        System.out.println(hoaDonXml == null ? "null" : hoaDonXml.toString());
    }
    public List<HoaDon> getList() {
    	if (hoaDonXml.getHoaDon() == null) return new ArrayList<HoaDon>();
    	return hoaDonXml.getHoaDon();
    }
    public boolean add(HoaDon hd) {
    	System.err.println(hd.toString());
    	if (Objects.isNull(hd)) return false;
    	hd.setTimeCreate(LocalDateTime.now());
    	hd.setId(hoaDonXml.getMaxId() + 1);
    	hoaDonXml.setMaxId(hoaDonXml.getMaxId() + 1);
    	if (Objects.isNull(hoaDonXml.getHoaDon()))
    		hoaDonXml.setHoaDon(new ArrayList<HoaDon>());
    	hoaDonXml.getHoaDon().add(hd);
    	try {
    		write();
    		return true;
    	} catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    	return false;
    }
    private void write() {
        FileUtils.writeXMLtoFile(FILE_NAME, hoaDonXml);
    }
}
