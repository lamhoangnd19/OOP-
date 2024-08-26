package vn.viettuts.qlsv.view.tab;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import vn.viettuts.qlsv.entity.ChiTietHoaDon;
import vn.viettuts.qlsv.entity.HoaDon;
import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.SanPham;
import vn.viettuts.qlsv.utils.DateTimeFormater;

public class PanelThongKe extends JPanel {
	private JTable tableHoaDon;
	private DefaultTableModel defaultTableModel;
	private List<HoaDon> hoaDons;
	private JLabel idKhachHangMuaNhieuNhat;
	private JLabel tenKhachHangMuaNhieuNhat;
	private JLabel tongSoTienMua;
	private JLabel tongSoHoaDon;
	private JLabel tongSoSanPham;
	private JLabel idSanPhamBanChayNhat;
	private JLabel tenSanPhamBanChayNhat;
	private JLabel soLuongBanRaSanPhamBanChayNhat;
	private JLabel tongDoanhThu;
	private JLabel lb1;
	private JLabel lb2;
	private JLabel tongBanRa;
	public PanelThongKe() {
		init();
		setupTable();
	}
	private void init() {
		lb2 = new JLabel("Sản phẩm bán chạy nhất");
		lb1 = new JLabel("Khách hàng mua nhiều nhất");
		tongDoanhThu = new JLabel("Tổng doanh thu: ");
		tongBanRa = new JLabel("Tổng số sản phẩm bán ra: ");
		idKhachHangMuaNhieuNhat = new JLabel("   Id: ");
		tenKhachHangMuaNhieuNhat = new JLabel("   Tên: ");
		tongSoTienMua = new JLabel("   Tổng số tiền đã thanh toán: ");
		tongSoHoaDon = new JLabel("   Tổng số hóa đơn: ");
		tongSoSanPham = new JLabel("   Tổng số sản phẩm mua: ");
		idSanPhamBanChayNhat = new JLabel("   Id: ");
		tenSanPhamBanChayNhat = new JLabel("   Tên: ");
		soLuongBanRaSanPhamBanChayNhat = new JLabel("   Số lượng bán ra: ");
		tableHoaDon = new JTable();
		defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		tableHoaDon.setModel(defaultTableModel);
		JPanel panel = new JPanel();
		setLayout(new GridLayout(1,2));
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Thống kê");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		setFontAll(new Font("Arial", Font.PLAIN, 14));
		panel.add(title);
		panel.add(tongDoanhThu);
		panel.add(tongBanRa);
		panel.add(lb1);
		panel.add(idKhachHangMuaNhieuNhat);
		panel.add(tenKhachHangMuaNhieuNhat);
		panel.add(tongSoTienMua);
		panel.add(tongSoHoaDon);
		panel.add(tongSoSanPham);
		panel.add(lb2);
		panel.add(idSanPhamBanChayNhat);
		panel.add(tenSanPhamBanChayNhat);
		panel.add(soLuongBanRaSanPhamBanChayNhat);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		title = new JLabel("Danh sách hóa đơn");
		title.setFont(new Font("Arial", Font.BOLD, 18));
		panel.add(title, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane(tableHoaDon);
		panel.add(scrollPane, BorderLayout.CENTER);
		add(panel);
	}
	
	private void setFontAll(Font font) {
		idKhachHangMuaNhieuNhat.setFont(font);
		tenKhachHangMuaNhieuNhat.setFont(font);
		tongSoTienMua.setFont(font);
		tongSoHoaDon.setFont(font);
		tongSoSanPham.setFont(font);
		idSanPhamBanChayNhat.setFont(font);
		tenSanPhamBanChayNhat.setFont(font);
		soLuongBanRaSanPhamBanChayNhat.setFont(font);
		tongDoanhThu.setFont(font);
		lb1.setFont(font);
		lb2.setFont(font);
		tongBanRa.setFont(font);
	}
	
	private void setupTable() {
		String[] rows = new String[] {
			"Mã", "Mã khách hàng", 
			"Sản phẩm - Số lượng", "Tổng tiền",
			"Thời gian tạo"
		};
		String[][] datas = new String[Objects.isNull(hoaDons) ? 0 : hoaDons.size()][rows.length];
		if (hoaDons != null)
			for (int i = 0; i < hoaDons.size(); i++) {
				HoaDon hd = hoaDons.get(i);
				datas[i][0] = String.valueOf(hd.getId());
				datas[i][1] = String.valueOf(hd.getKhId());
				datas[i][2] = hd.getDetails().stream().map(item -> item.getSpId() + " - " + item.getCount()).collect(Collectors.joining(";"));
				datas[i][3] = String.valueOf(hd.getTotalPrice());
				datas[i][4] = DateTimeFormater.toString(hd.getTimeCreate());
			}
		defaultTableModel.setDataVector(datas, rows);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Đặt renderer cho mỗi cột trong bảng
        for (int i = 0; i < tableHoaDon.getColumnCount(); i++) {
            tableHoaDon.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
	
	public void setList(List<HoaDon> hoaDons, List<KhachHang> khachHangs, List<SanPham> sanPhams) {
		this.hoaDons = hoaDons;
		setupTable();
		if (Objects.isNull(hoaDons)) return;
		double tong = 0;
		int tongBanRa = 0;
		Map<Integer, Double> khs = new HashMap<Integer, Double>();
		Map<Integer, Integer> sp = new HashMap<Integer, Integer>();
		for (HoaDon hd : hoaDons) {
			tong += hd.getTotalPrice();
			if (Objects.isNull(khs.get(hd.getKhId())))
				khs.put(hd.getKhId(), 0.0);
			khs.replace(hd.getKhId(), khs.get(hd.getKhId()) + hd.getTotalPrice());
			for (ChiTietHoaDon cthd : hd.getDetails()) {
				tongBanRa += cthd.getCount();
				if (Objects.isNull(sp.get(cthd.getSpId())))
					sp.put(cthd.getSpId(), 0);
				sp.replace(cthd.getSpId(), sp.get(cthd.getSpId()) + cthd.getCount());
			}
		}
		double khMax = -99999;
		int idKhMax = -1;
		int spMax = -99999;
		int idSpMax = -1;
		for (int id : khs.keySet()) {
			if (khs.get(id) >= khMax) {
				khMax = khs.get(id);
				idKhMax = id;
			}
		}
		for (int id : sp.keySet()) {
			if (sp.get(id) >= spMax) {
				spMax = sp.get(id);
				idSpMax = id;
			}
		}		
		SanPham sph = null;
		KhachHang khh = null;
		if (Objects.nonNull(khachHangs))
			for (KhachHang kh : khachHangs)
				if (kh.getId() == idKhMax)
				{
					khh = kh;
					break;
				}
		if (Objects.nonNull(sanPhams))
			for (SanPham kh : sanPhams)
				if (kh.getId() == idSpMax)
				{
					sph = kh;
					break;
				}
		this.tongBanRa.setText("Tổng số sản phẩm bán ra: " + tongBanRa);
		tongDoanhThu.setText("Tổng doanh thu: " + tong);
		if (Objects.nonNull(khh)) {
			idKhachHangMuaNhieuNhat.setText("   Id: " + khh.getId());
			tenKhachHangMuaNhieuNhat.setText("   Tên: " + khh.getName());
			tongSoTienMua.setText("   Tổng số tiền đã thanh toán: " + khs.get(khh.getId()));
			int tongHoaDon = 0;
			int tongSpMua = 0;
			for (HoaDon hd : hoaDons) {
				tongHoaDon += hd.getKhId() == khh.getId() ? 1 : 0;
				tongSpMua += hd.getKhId() == khh.getId() ? hd.getDetails().stream()
                        .mapToInt(item -> item.getCount())
                        .sum() : 0;
			}
			tongSoHoaDon.setText("   Tổng số hóa đơn: " + tongHoaDon);
			tongSoSanPham.setText("   Tổng số sản phẩm mua: " + tongSpMua);
		}
		if (Objects.nonNull(sph)) {
			idSanPhamBanChayNhat.setText("   Id: " + sph.getId());
			tenSanPhamBanChayNhat.setText("   Tên: " + sph.getName());
			soLuongBanRaSanPhamBanChayNhat.setText("   Số lượng bán ra: " + sp.get(sph.getId()));
		}
	}
	
	public void showMessage(String message, int option) {
		JOptionPane.showMessageDialog(this, message, "Thông báo", option);
	}
}
