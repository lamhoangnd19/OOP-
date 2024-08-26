package vn.viettuts.qlsv.view.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Flow;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vn.viettuts.qlsv.entity.SanPham;
import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.LocalDateTimeAdapter;

public class PanelQuanLySanPham extends JPanel {
	private JTable dsSanPham;
	private DefaultTableModel modelDsSanPham;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnRm;
	private JButton btnChange;
	private JButton btnClear;
	private JTextField inputName;
	private JTextField inputPrice;
	private JTextField inputCount;
	private List<SanPham> sanPhams;
	private JLabel image;
	private BufferedImage selectedImage;
	public PanelQuanLySanPham() {
		init();
		initTable();
		setting();
	}
	private void init() {
		btnAdd = new JButton("Thêm");
		btnEdit = new JButton("Sửa");
		btnRm = new JButton("Xóa");
		btnClear = new JButton("Đặt lại");
		btnChange = new JButton("Chọn ảnh");
		dsSanPham = new JTable();
		inputName = new JTextField();
		inputPrice = new JTextField();
		inputCount = new JTextField();
		image = new JLabel("", JLabel.CENTER);
		modelDsSanPham = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}
	private void initTable() {
		String[] titles = {
			"Mã", "Tên", "Giá", "Số lượng", "Thời gian thêm", "Ảnh sản phẩm"	
		};
		String[][] datas = new String[Objects.isNull(sanPhams) ? 0 : sanPhams.size()][titles.length];
		if (Objects.nonNull(sanPhams))
			for (int i = 0; i < sanPhams.size(); i++) {
				SanPham sp = sanPhams.get(i);
				datas[i][0] = String.valueOf(sp.getId());
				datas[i][1] = sp.getName();
				datas[i][2] = String.valueOf(sp.getPrice());
				datas[i][3] = String.valueOf(sp.getCount());
				datas[i][4] = DateTimeFormater.toString(sp.getTimeCreate());
				datas[i][5] = sp.getUrlImage();
			}
		modelDsSanPham.setDataVector(datas, titles);
	}
	private void setting() {
		dsSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dsSanPham.setRowHeight(50);
		dsSanPham.setModel(modelDsSanPham);
		dsSanPham.setDefaultRenderer(Object.class, new TableCellRenderCustom());
		btnAdd.setBackground(Color.blue);
		btnEdit.setBackground(Color.orange);
		btnRm.setBackground(Color.red);
		btnClear.setBackground(Color.gray);
		btnAdd.setForeground(Color.white);
		btnEdit.setForeground(Color.white);
		btnRm.setForeground(Color.white);
		btnClear.setForeground(Color.white);
		setLayout(new GridLayout(1,2));
		JScrollPane scrollPane = new JScrollPane(dsSanPham);
		add(scrollPane);
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel.setLayout(new GridBagLayout());
		panel.setBorder(new EmptyBorder(50, 50, 50, 50));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1;
		panel.add(new JLabel("Tên sản phẩm"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(inputName, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Giá sản phẩm"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(inputPrice, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Số lượng"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(inputCount, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel("Ảnh sản phẩm"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.FIRST_LINE_START;
		panel.add(btnChange, gbc);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(image, gbc);
		panel1.add(panel, BorderLayout.CENTER);
		panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(btnAdd);
		panel.add(btnEdit);
		panel.add(btnRm);
		panel.add(btnClear);
		panel1.add(panel, BorderLayout.SOUTH);
		add(panel1);
		dsSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				int index = dsSanPham.getSelectedRow();
				if (index < 0 || Objects.isNull(sanPhams) || index >= sanPhams.size()) return;
				SanPham sanPham = sanPhams.get(index);
				inputName.setText(sanPham.getName());
				inputPrice.setText(String.valueOf(sanPham.getPrice()));
				inputCount.setText(String.valueOf(sanPham.getCount()));
				image.setIcon(new ImageIcon(new ImageIcon("images/products/" + sanPham.getUrlImage()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
				image.setAlignmentX(Component.CENTER_ALIGNMENT);
			}
		});
		btnChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select an image file");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                    	selectedImage = ImageIO.read(selectedFile);
                        ImageIcon imageIcon = new ImageIcon(selectedImage);
                        if (Objects.nonNull(selectedImage) && Objects.nonNull(imageIcon)) 	
                        	image.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error loading image: " + ex.getMessage());
                    }
                }	
			}
		});
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clear();
			}
		});
	}
	
	private void clear() {
		dsSanPham.clearSelection();
		inputCount.setText("");
		inputName.setText("");
		inputPrice.setText("");
		image.setIcon(null);
	}
	
	public SanPham newSanPham() {
		SanPham sanPham = new SanPham();
		String name = inputName.getText();
		if (name.isBlank()) {
			showMessage("Tên sản phẩm không được để trống", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		sanPham.setName(name);
		int count = -1;
		try {
			count = Integer.valueOf(inputCount.getText());
		} catch (Exception e) {
		} finally {
			if (count < 0) {
				showMessage("Số lượng sản phẩm không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		}
		sanPham.setCount(count);
		double gia = -1;
		try {
			gia = Double.valueOf(inputPrice.getText());
		} catch (Exception e) {
		} finally {
			if (gia < 0) {
				showMessage("Giá sản phẩm không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		}
		sanPham.setPrice(gia);
	    if (selectedImage == null)
	    {
	    	showMessage("Ảnh sản phẩm không được để trống", JOptionPane.INFORMATION_MESSAGE);
	    	return null;
	    }   
    	LocalDateTime now = LocalDateTime.now();
        String imagePath = now.getYear() + "" + now.getMonthValue() + "" + now.getDayOfMonth() + "" + 
        		now.getHour() + "" + now.getMinute() + "" + now.getSecond() + ".jpg";
        try {
            File outputFile = new File("images/products/" + imagePath);
            ImageIO.write(selectedImage, "png", outputFile);
            sanPham.setUrlImage(imagePath);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(PanelQuanLySanPham.this, "Error saving image: " + ex.getMessage());
            return null;
        }
		return sanPham;
	}
	
	public SanPham getSanPhamSelected() {
		int index = dsSanPham.getSelectedRow();
		if (index < 0) {
	    	showMessage("Vui lòng chọn 1 hàng dữ liệu", JOptionPane.INFORMATION_MESSAGE);
	    	return null;
		}
		return sanPhams.get(index);
	}
	
	public SanPham editSanPham() {
		int index = dsSanPham.getSelectedRow();
		if (index < 0) {
	    	showMessage("Vui lòng chọn 1 hàng dữ liệu", JOptionPane.INFORMATION_MESSAGE);
	    	return null;
		}
		SanPham sanPham = sanPhams.get(index);
		String name = inputName.getText();
		if (name.isBlank()) {
			showMessage("Tên sản phẩm không được để trống", JOptionPane.INFORMATION_MESSAGE);
			return null;
		}
		sanPham.setName(name);
		int count = -1;
		try {
			count = Integer.valueOf(inputCount.getText());
		} catch (Exception e) {
		} finally {
			if (count < 0) {
				showMessage("Số lượng sản phẩm không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		}
		sanPham.setCount(count);
		double gia = -1;
		try {
			gia = Double.valueOf(inputPrice.getText());
		} catch (Exception e) {
		} finally {
			if (gia < 0) {
				showMessage("Giá sản phẩm không hợp lệ", JOptionPane.INFORMATION_MESSAGE);
				return null;
			}
		}
		sanPham.setPrice(gia);
		if (selectedImage != null) {
	    	LocalDateTime now = LocalDateTime.now();
	        String imagePath = now.getYear() + "" + now.getMonthValue() + "" + now.getDayOfMonth() + "" + 
	        		now.getHour() + "" + now.getMinute() + "" + now.getSecond() + ".jpg";
	        try {
	            File outputFile = new File("images/products/" + imagePath);
	            ImageIO.write(selectedImage, "png", outputFile);
	         
	            
	            String filePath = "images/products/" + sanPham.getUrlImage(); // Thay đổi đường dẫn tại đây
	            // Tạo một đối tượng File với đường dẫn đã chỉ định
	            File file = new File(filePath);
	            // Kiểm tra xem tệp tồn tại trước khi xóa
	            if (file.exists()) {
	                // Thực hiện xóa tệp
	                file.delete();
	            }
	            sanPham.setUrlImage(imagePath);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(PanelQuanLySanPham.this, "Error saving image: " + ex.getMessage());
	            return null;
	        }
		}
		return sanPham; 
	}
	
	public void setList(List<SanPham> sanPhams) {
		this.sanPhams = sanPhams;
		initTable();
		clear();
	}
	
	public void showMessage(String message, int option) {
		JOptionPane.showMessageDialog(this, message, "Thông báo", option);
	}
	
	private class TableCellRenderCustom extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {
			// TODO Auto-generated method stub
			Component component = super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
			if (arg5 == 5) {
				JPanel panel = new JPanel();
				panel.add(new JLabel(new ImageIcon(new ImageIcon("images/products/" + arg1.toString()).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH))));
				component = panel;
			}
			if (component instanceof JLabel)
				((JLabel) component).setHorizontalAlignment(JLabel.CENTER);
			if (arg2) {
				component.setBackground(Color.BLUE);
				if (component instanceof JLabel)
					((JLabel) component).setForeground(Color.white);
			} else {
				component.setBackground(Color.WHITE);
				if (component instanceof JLabel)
					((JLabel) component).setForeground(Color.BLACK);
			}
			return component;
		}
	}
	
	public void addListen(ActionListener actionListon) {
		this.btnAdd.addActionListener(actionListon);
	}
	
	public void editListen(ActionListener actionListener) {
		this.btnEdit.addActionListener(actionListener);
	}
	
	public void removeListen(ActionListener actionListener) {
		this.btnRm.addActionListener(actionListener);
	}
}
