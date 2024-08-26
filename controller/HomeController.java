package vn.viettuts.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vn.viettuts.qlsv.dao.HoaDonDao;
import vn.viettuts.qlsv.dao.KhachHangDao;
import vn.viettuts.qlsv.dao.SanPhamDao;
import vn.viettuts.qlsv.dao.UserDao;
import vn.viettuts.qlsv.entity.ChiTietHoaDon;
import vn.viettuts.qlsv.entity.HoaDon;
import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.SanPham;
import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.AddCustomerView;
import vn.viettuts.qlsv.view.EditCustomerView;
import vn.viettuts.qlsv.view.HomeView;
import vn.viettuts.qlsv.view.tab.PanelQuanLyKhachHang;
import vn.viettuts.qlsv.view.tab.PanelQuanLySanPham;
import vn.viettuts.qlsv.view.tab.PanelThongKe;

public class HomeController {
	private LoginController loginController;
	private HomeView homeView;
	private SanPhamDao sanPhamDao;
	private KhachHangDao khachHangDao;
	private HoaDonDao hoaDonDao;
	private PanelQuanLyKhachHang panelQuanLyKhachHang;
	private PanelQuanLySanPham panelQuanLySanPham;
	private PanelThongKe panelThongKe;
	private EditCustomerView editCustomerView;
	private AddCustomerView addCustomerView;
	public HomeController(LoginController userDao, HomeView homeView) {
		this.homeView = homeView;
		this.loginController = userDao;
		this.sanPhamDao = new SanPhamDao();
		this.khachHangDao = new KhachHangDao();
		this.hoaDonDao = new HoaDonDao();
		this.panelQuanLyKhachHang = homeView.getPanelQuanLyKhachHang();
		this.panelQuanLySanPham = homeView.getPanelQuanLySanPham();
		this.panelThongKe = homeView.getPanelThongKe();
		this.homeView.setOnChangedTab(new ChangedTab());
		this.panelQuanLyKhachHang.addEventToEditCustomer(new ToEditCustomer());
		this.panelQuanLyKhachHang.addEventToAddCustomer(new ToAddCustomer());
		this.panelQuanLyKhachHang.addEventRemoveCustomer(new RemoveCustomer());
		this.panelQuanLySanPham.addListen(new AddProduct());
		this.panelQuanLySanPham.editListen(new EditProduct());
		this.panelQuanLySanPham.removeListen(new RemoveProduct());
		this.panelQuanLyKhachHang.addEventSaveHoaDon(new SaveHoaDon());
		this.homeView.addBtnLogout(new LogoutController());
	}
	
	public void showHome() {
		homeView.setVisible(true);
		showTabQuanLyKhachHang();
	}
	
	private void showTabQuanLyKhachHang() {
		panelQuanLyKhachHang.setData(khachHangDao.getList(), sanPhamDao.getList());
	}
	
	private void showTabQuanLySanPham() {
		panelQuanLySanPham.setList(sanPhamDao.getList());
	}	
	
	private void showTabThongKe() {
		panelThongKe.setList(hoaDonDao.getList(), khachHangDao.getList(), sanPhamDao.getList());
	}
	
 	private class ChangedTab implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
            JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
            int selectedIndex = sourceTabbedPane.getSelectedIndex();
            switch (selectedIndex) {
				case 0:
					showTabQuanLyKhachHang();;
					break;
				case 1: 
					showTabQuanLySanPham();;
					break;
				case 2: 
					showTabThongKe();;
					break;
				default:
					break;
			}
		}
    }
 	
 	class ToAddCustomer implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			addCustomerView = new AddCustomerView(homeView);
 			addCustomerView.setVisible(true);
 			homeView.setEnabled(false);
 			addCustomerView.onAddCustomer(new AddCustomer());
 		}
 	}
 	
 	class ToEditCustomer implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			KhachHang kh = panelQuanLyKhachHang.getKhachHangSelected();
 			if (Objects.isNull(kh)) {
 				panelQuanLyKhachHang.showMessage("Vui lòng chọn 1 hàng dữ liệu", JOptionPane.INFORMATION_MESSAGE);
 				return;
 			}
 			editCustomerView = new EditCustomerView(homeView, kh);
 			editCustomerView.setVisible(true);
 			homeView.setEnabled(false);
 			editCustomerView.onAddCustomer(new EditCustomer());
 		}
 	}

 	class RemoveCustomer implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			KhachHang kh = panelQuanLyKhachHang.getKhachHangSelected();
 			if (Objects.isNull(kh)) {
 				panelQuanLyKhachHang.showMessage("Vui lòng chọn 1 hàng dữ liệu", JOptionPane.INFORMATION_MESSAGE);
 				return;
 			}
			if (Objects.isNull(khachHangDao.getList()))
 				return;
            int result = JOptionPane.showConfirmDialog(panelQuanLyKhachHang, "Bạn có chắc chắn muốn xóa khách hàng này chứ?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
    			if (!khachHangDao.getList().stream().anyMatch(item -> item.getId() == kh.getId()))
    			{
    				panelQuanLyKhachHang.showMessage("Khách hàng không tồn tại", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if (khachHangDao.remove(kh))
    				panelQuanLyKhachHang.setListKhachHang(khachHangDao.getList());
    			else 
    				panelQuanLyKhachHang.showMessage("Xóa thất bại", JOptionPane.ERROR_MESSAGE);
            } 
 		}
 	}
 	
 	class AddCustomer implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			KhachHang kh = addCustomerView.getKhachHang();
 			if (Objects.isNull(kh))
 				return;
 			if (Objects.nonNull(khachHangDao.getList()))
 				if (khachHangDao.getList().stream().anyMatch(item -> item.getPhoneNumber().equals(kh.getPhoneNumber())))
 				{
 					addCustomerView.showMessage("Số điện thoại đã tồn tại", JOptionPane.ERROR_MESSAGE);
 					return;
 				}
 					
 			if (khachHangDao.add(kh)) {
 				addCustomerView.setVisible(false);
 				homeView.setVisible(true);
 				homeView.setEnabled(true);
 				panelQuanLyKhachHang.setListKhachHang(khachHangDao.getList());
 			} else 
 				addCustomerView.showMessage("Thêm thất bại", JOptionPane.ERROR_MESSAGE);
 		}
 	}
 	
 	class EditCustomer implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			KhachHang kh = editCustomerView.getKhachHang();
 			if (Objects.isNull(kh) || Objects.isNull(khachHangDao.getList()))
 				return;
			if (!khachHangDao.getList().stream().anyMatch(item -> item.getId() == kh.getId()))
			{
				editCustomerView.showMessage("Khách hàng không tồn tại", JOptionPane.ERROR_MESSAGE);
				return;
			}
 			if (khachHangDao.getList().stream().anyMatch(item -> item.getId() != kh.getId() && 
				item.getPhoneNumber().equals(kh.getPhoneNumber())))
			{
				editCustomerView.showMessage("Số điện thoại đã tồn tại", JOptionPane.ERROR_MESSAGE);
				return;
			} 					
 			if (khachHangDao.edit(kh)) {
 				editCustomerView.setVisible(false);
 				homeView.setVisible(true);
 				homeView.setEnabled(true);
 				panelQuanLyKhachHang.setListKhachHang(khachHangDao.getList());
 			} else 
 				editCustomerView.showMessage("Cập nhật thất bại", JOptionPane.ERROR_MESSAGE);
 		}
 	}
 	
 	class AddProduct implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			SanPham sanPham = panelQuanLySanPham.newSanPham();
 			if (sanPham == null || Objects.isNull(sanPhamDao.getList()))
 				return;
 			if (sanPhamDao.add(sanPham))
 				panelQuanLySanPham.setList(sanPhamDao.getList());
 			else 
 				panelQuanLySanPham.showMessage("Thêm sản phẩm thất bại", JOptionPane.ERROR_MESSAGE);
 		}
 	}
 	
 	class EditProduct implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			SanPham sanPham = panelQuanLySanPham.editSanPham();
 			if (sanPham == null || Objects.isNull(sanPhamDao.getList()))
 				return;	
 			if (sanPhamDao.edit(sanPham))
 				panelQuanLySanPham.setList(sanPhamDao.getList());
 			else 
 				panelQuanLySanPham.showMessage("Sửa sản phẩm thất bại", JOptionPane.ERROR_MESSAGE);
 		}
 	}
 	
 	class RemoveProduct implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			SanPham sanPham = panelQuanLySanPham.getSanPhamSelected();
 			if (sanPham == null || Objects.isNull(sanPhamDao.getList()))
 				return;
            int result = JOptionPane.showConfirmDialog(panelQuanLySanPham, "Bạn có chắc chắn muốn xóa sản phẩm này chứ?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
    			if (!sanPhamDao.getList().stream().anyMatch(item -> item.getId() == sanPham.getId()))
    			{
    				panelQuanLySanPham.showMessage("Sản phẩm không tồn tại", JOptionPane.ERROR_MESSAGE);
    				return;
    			}
    			if (sanPhamDao.remove(sanPham))
    				panelQuanLySanPham.setList(sanPhamDao.getList());
    			else 
    				panelQuanLySanPham.showMessage("Xóa thất bại", JOptionPane.ERROR_MESSAGE);
            } 
			
 		}
 	}
 	
 	class SaveHoaDon implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			int[][] infos = panelQuanLyKhachHang.getHoaDon();
 			if (infos.length == 0) {
 				panelQuanLyKhachHang.showMessage("Vui lòng chọn ít nhất 1 sản phẩm", JOptionPane.WARNING_MESSAGE);
 				return;
 			}
 			double priceTotal = 0;
 			HoaDon hoaDon = new HoaDon();
 			for (int i = 0; i < infos.length; i++) {
 				SanPham sanPham = null;
 				if (Objects.nonNull(sanPhamDao.getList()))
 					for (SanPham sp : sanPhamDao.getList())
 						if (sp.getId() == infos[i][0])
 						{
 							sanPham = sp;
 							break;
 						}
 				if (Objects.nonNull(sanPham) && sanPham.getCount() >= infos[i][1]) {
 					if (Objects.isNull(hoaDon.getDetails()))
 						hoaDon.setDetails(new ArrayList<ChiTietHoaDon>());
 					hoaDon.getDetails().add(new ChiTietHoaDon(infos[i][0], infos[i][1]));
 					sanPham.setCount(sanPham.getCount() - infos[i][1]);
 					priceTotal += infos[i][1] * sanPham.getPrice();  
 				}
 			}
 			if (priceTotal == 0) {
 				panelQuanLyKhachHang.showMessage("Ít nhất 1 sản phẩm phải có số lượng lớn hơn 1", JOptionPane.WARNING_MESSAGE);
 				return;
 			}
 			hoaDon.setTotalPrice(priceTotal);
 			KhachHang kh = panelQuanLyKhachHang.getKhachHangSelected();
 			if (Objects.isNull(kh)) {
 				panelQuanLyKhachHang.showMessage("Vui lòng chọn khách hàng", JOptionPane.WARNING_MESSAGE);
 				return;	
 			}
 			hoaDon.setKhId(kh.getId());
 			if (sanPhamDao.save() && hoaDonDao.add(hoaDon)) {
 				panelQuanLyKhachHang.showMessage("Thêm hóa đơn thành công", JOptionPane.INFORMATION_MESSAGE);
 				panelQuanLyKhachHang.resert();
 			} else {
 				panelQuanLyKhachHang.showMessage("Thêm hóa đơn thất bại", JOptionPane.ERROR_MESSAGE);
 			}
 		}
 	}
 	
 	private class LogoutController implements ActionListener {
 		@Override
 		public void actionPerformed(ActionEvent arg0) {
 			// TODO Auto-generated method stub
 			homeView.setVisible(false);
 			loginController.showLoginView();
 		}
 	}
}
