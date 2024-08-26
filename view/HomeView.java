package vn.viettuts.qlsv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import vn.viettuts.qlsv.entity.User;
import vn.viettuts.qlsv.view.tab.PanelQuanLyKhachHang;
import vn.viettuts.qlsv.view.tab.PanelQuanLySanPham;
import vn.viettuts.qlsv.view.tab.PanelThongKe;

public class HomeView extends JFrame {
	private JLabel txtUsername;
	private JButton btnLogout;
	private JTabbedPane tabbedPane;
	private User user;
	private PanelQuanLyKhachHang panelQuanLyKhachHang;
	private PanelQuanLySanPham panelQuanLySanPham;
	private PanelThongKe panelThongKe;
	public HomeView(User user) {
		this.user = user;
		setTitle("Trang chủ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());

        int width = screenSize.width;
        int height = screenSize.height - screenInsets.bottom;

        // Thiết lập kích thước và vị trí của JFrame
        setSize(width, height);
        setLocation(0, 0);
        setLocationRelativeTo(null);
        init();
        setting();
	}	
	
	public PanelQuanLyKhachHang getPanelQuanLyKhachHang() {
		return panelQuanLyKhachHang;
	}
	
	public PanelQuanLySanPham getPanelQuanLySanPham() {
		return panelQuanLySanPham;
	}
	
	public PanelThongKe getPanelThongKe() {
		return panelThongKe;
	}
	
	private void init() {
		txtUsername = new JLabel("Hi, " + user.getUsername());
		btnLogout = new JButton("Đăng xuất");
		tabbedPane = new JTabbedPane();
		panelQuanLyKhachHang = new PanelQuanLyKhachHang();
		panelQuanLySanPham = new PanelQuanLySanPham();
		panelThongKe = new PanelThongKe();
	}
	
	private void setting() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel1.add(txtUsername);
		panel1.add(btnLogout);
		panel.add(panel1, BorderLayout.NORTH);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        tabbedPane.setTabPlacement(JTabbedPane.TOP);
        tabbedPane.addTab("Quản lý khách hàng", new ImageIcon("images/customer (2).png"), panelQuanLyKhachHang);
        tabbedPane.addTab("Quản lý sản phẩm", new ImageIcon("images/sneakers.png"), panelQuanLySanPham);
        tabbedPane.addTab("Thống kê", new ImageIcon("images/analytics.png"), panelThongKe);
        panel.add(tabbedPane, BorderLayout.CENTER);
		add(panel, BorderLayout.CENTER);
	}
	
	public void setOnChangedTab(ChangeListener changeListener) {
		tabbedPane.addChangeListener(changeListener);
	}
	
	public void addBtnLogout(ActionListener actionListener) {
		btnLogout.addActionListener(actionListener);
	}
}
