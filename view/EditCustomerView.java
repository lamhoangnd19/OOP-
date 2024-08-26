package vn.viettuts.qlsv.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.Validator;

public class EditCustomerView extends JFrame {
	private JLabel txtTen;
	private JLabel txtPhoneNumber;
	private JLabel txtGender;
	private JLabel txtDob;
	private JTextField inputTen;
	private JTextField inputPhoneNumber;
	private JRadioButton genderMale;
	private JRadioButton genderFeMale;
	private JSpinner dateSpinner;
	private JButton btnOk;
	private JButton btnCancel;
	private HomeView homeView;
	private KhachHang khachHang;
	public EditCustomerView(HomeView homeView, KhachHang kh) {
		this.homeView = homeView;
		this.khachHang = kh;
        setSize(400, 600);
        setLocation(homeView.getLocation().x + homeView.getWidth() - 400, homeView.getLocation().y);
        setTitle("Cập nhật khách hàng");
        init();
        setting();
	}
	private void init() {
		txtDob = new JLabel("Ngày sinh");
		txtGender = new JLabel("Giới tính");
		txtPhoneNumber = new JLabel("Số điện thoại");
		txtTen = new JLabel("Tên");
		
		inputTen = new JTextField();
		genderFeMale = new JRadioButton();
		genderMale = new JRadioButton();
		genderFeMale.setText("Nữ");
		genderMale.setText("Nam");
		inputPhoneNumber = new JTextField();
		
		btnCancel = new JButton("Hủy bỏ");
		btnOk = new JButton("Cập nhật");
	}
	private void setting() {
	    addWindowListener(new WindowAdapter() {
	        @Override
	        public void windowClosing(WindowEvent e) {
	        	homeView.setEnabled(true);
				homeView.setVisible(true);
	        }

	        @Override
	        public void windowClosed(WindowEvent e) {
	        }
	    });
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
				homeView.setEnabled(true);
				homeView.setVisible(true);
			}
		});
		JPanel panel = new JPanel();
		setLayout(new BorderLayout());
		JLabel label = new JLabel("Cập nhật khách hàng", JLabel.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		panel.setLayout(new GridLayout(11, 1));
		txtTen.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtGender.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtPhoneNumber.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		txtDob.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel.add(label);
		panel.add(txtTen);
		panel.add(inputTen);
		panel.add(txtGender);
		ButtonGroup groupGender = new ButtonGroup();
		groupGender.add(genderMale);
		groupGender.add(genderFeMale);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel1.add(genderMale);
		panel1.add(genderFeMale);
		panel.add(panel1);
		panel.add(txtDob);
        Calendar calendar = Calendar.getInstance();
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -100);
        Date earliestDate = calendar.getTime();
        calendar.add(Calendar.YEAR, 200);
        Date latestDate = calendar.getTime();
        SpinnerDateModel dateModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH);

        // Tạo JSpinner với SpinnerDateModel
        dateSpinner = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
		panel.add(dateSpinner);
		panel.add(txtPhoneNumber);
		panel.add(inputPhoneNumber);
		panel.add(btnOk);
		panel.add(btnCancel);
		btnCancel.setBackground(Color.gray);
		btnOk.setBackground(Color.blue);
		btnOk.setForeground(Color.white);
		btnCancel.setForeground(Color.white);
		panel.setBorder(new EmptyBorder(10,10,10,10));
		add(panel, BorderLayout.CENTER);
		inputTen.setText(khachHang.getName());
		inputPhoneNumber.setText(khachHang.getPhoneNumber());
		if (khachHang.isGender())
			genderMale.setSelected(true);
		else 
			genderFeMale.setSelected(true);
		dateSpinner.setValue(DateTimeFormater.toDate(khachHang.getDob()));
	}
	
	public KhachHang getKhachHang() {
		KhachHang kh = new KhachHang();
		kh.setId(khachHang.getId());
		try {
			Date dob = (Date) dateSpinner.getValue();
			kh.setDob(DateTimeFormater.toLocalDate(dob));
		} catch (Exception e) {
			showMessage("Ngày sinh không hợp lệ", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		String name = inputTen.getText();
		if (Objects.isNull(name) || name.isBlank()) {
			showMessage("Tên khách hàng không được để trống", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		kh.setName(name);
		if (!genderMale.isSelected() && !genderFeMale.isSelected()) {
			showMessage("Giới tính khách hàng không được để trống", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		kh.setGender(genderMale.isSelected());
		String phoneNumber = inputPhoneNumber.getText();
		if (!Validator.isPhoneNumber(phoneNumber)) {
			showMessage("Số điện thoại khách hàng không hợp lệ", JOptionPane.WARNING_MESSAGE);
			return null;
		}
		kh.setPhoneNumber(phoneNumber);
		return kh;
	}
	
	public void showMessage(String message, int option) {
		JOptionPane.showMessageDialog(this, message, "Thông báo", option);
	}
	
	public void onAddCustomer(ActionListener actionListener) {
		this.btnOk.addActionListener(actionListener);
	}
}
