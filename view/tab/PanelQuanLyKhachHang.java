package vn.viettuts.qlsv.view.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.Flow;

import javax.print.attribute.AttributeSet;
import javax.swing.AbstractCellEditor;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vn.viettuts.qlsv.entity.KhachHang;
import vn.viettuts.qlsv.entity.SanPham;
import vn.viettuts.qlsv.utils.DateTimeFormater;
import vn.viettuts.qlsv.utils.LocalDateAdapter;
import vn.viettuts.qlsv.utils.LocalDateTimeAdapter;
import vn.viettuts.qlsv.view.component.ProductItem;

public class PanelQuanLyKhachHang extends JPanel {
	private JTable tableKhachHang;
	private JTable danhSachSanPham;
	private JTable danhSachMua;
	private DefaultTableModel defaultTableModelKhachHang;
	private DefaultTableModel defaultTableModelSanPham;
	private DefaultTableModel defaultTableModelMua;
	private JButton btnAddKH;
	private JButton btnEditKH;
	private JButton btnRmKH;
	private JLabel txtDsSp;
	private JLabel txtInfoHoaDon;
	private JTextField inputSearch;
	private JComboBox<String> choseSearch;
	private JButton btnResert;
	private JButton btnSave;
	private JLabel txtPriceTotal;
	private List<KhachHang> khachHangs;
	private List<SanPham> sanPhams;
	private Vector<Vector> muas;
	public PanelQuanLyKhachHang() {
		init();
		initTable();
		setting();
	}
	private void init() {
		tableKhachHang = new JTable();
		danhSachMua = new JTable();
		danhSachSanPham = new JTable();
		btnAddKH = new JButton("Thêm");
		btnEditKH = new JButton("Sửa");
		btnRmKH = new JButton("Xóa");
		txtDsSp = new JLabel("Danh sách sản phẩm");
		txtInfoHoaDon = new JLabel("Thông tin hóa đơn");
		btnSave = new JButton("Lưu");
		btnResert = new JButton("Làm mới");
		inputSearch = new JTextField(50);
		choseSearch = new JComboBox<String>();
		txtPriceTotal = new JLabel("Tổng cộng: 0đ", JLabel.RIGHT);
		defaultTableModelKhachHang = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		defaultTableModelSanPham = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		defaultTableModelMua = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return column == 2 || column == 4;
			};
		};
	}
	private void initTable() {
		setupTableKhachHang();
		setupTableSanPham();
		setupTableMua();
	}
	private void setting() {
		String[] tieuChis = new String[] {
			"Mã", "Tên", "Giá"	
		};
		btnResert.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				resert();
			}
		});
		danhSachMua.setDefaultRenderer(Object.class, new CustomRenderTableMua());
		tableKhachHang.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        danhSachMua.setDefaultEditor(Object.class, new CustomEditorItemTable());
		danhSachSanPham.setCellSelectionEnabled(true);
		danhSachSanPham.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		danhSachSanPham.setTableHeader(null);
		danhSachSanPham.setRowHeight(250);
		tableKhachHang.setModel(defaultTableModelKhachHang);
		danhSachSanPham.setModel(defaultTableModelSanPham);
		danhSachMua.setModel(defaultTableModelMua);
		danhSachSanPham.setDefaultRenderer(Object.class, new CustomRenderItemTable());
		choseSearch.setModel(new DefaultComboBoxModel<String>(tieuChis));
		inputSearch.setBorder(new LineBorder(Color.white));
		btnSave.setBackground(Color.blue);
		btnResert.setBackground(Color.gray);
		btnAddKH.setBackground(Color.blue);
		btnEditKH.setBackground(Color.orange);
		btnRmKH.setBackground(Color.red);
		btnAddKH.setForeground(Color.white);
		btnEditKH.setForeground(Color.white);
		btnRmKH.setForeground(Color.white);
		btnSave.setForeground(Color.white);
		btnResert.setForeground(Color.white);
		txtDsSp.setFont(new Font("Arial", Font.BOLD, 16));
		txtInfoHoaDon.setFont(new Font("Arial", Font.BOLD, 16));
		setLayout(new GridLayout(2,1));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JScrollPane scrollPane = new JScrollPane(tableKhachHang);
		panel.add(scrollPane, BorderLayout.CENTER);
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel1.add(btnAddKH);
		panel1.add(btnEditKH);
		panel1.add(btnRmKH);
		panel.add(panel1, BorderLayout.EAST);
		add(panel);
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(txtDsSp);
		JPanel groupInput = new JPanel();
		groupInput.setBorder(new LineBorder(Color.gray));
		groupInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		groupInput.add(new JLabel(new ImageIcon("images/search.png")));
		groupInput.add(inputSearch);	
		panel2.add(groupInput);
		panel2.add(choseSearch);

		panel1.add(panel2, BorderLayout.NORTH);
		scrollPane = new JScrollPane(danhSachSanPham);
		panel1.add(scrollPane, BorderLayout.CENTER);
		panel.add(panel1);
		
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel2.add(txtInfoHoaDon);
		JTextField input = new JTextField();
		input.setEditable(false);
		input.setBorder(null);
		groupInput = new JPanel();
		groupInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		groupInput.add(input);
		panel2.add(groupInput);
		panel2.setBorder(new EmptyBorder(0,0,4,0));
		panel1.add(panel2, BorderLayout.NORTH);
		scrollPane = new JScrollPane(danhSachMua);
		panel1.add(scrollPane, BorderLayout.CENTER);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout());
		groupInput = new JPanel();
		groupInput.setLayout(new FlowLayout(FlowLayout.LEFT));
		groupInput.add(btnSave);
		groupInput.add(btnResert);
		panel2.add(groupInput);
		panel2.add(txtPriceTotal);
		panel1.add(panel2, BorderLayout.SOUTH);
		panel.add(panel1);
		add(panel);		
		
		inputSearch.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				setupTableSanPham();
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		choseSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setupTableSanPham();
			}
		});
	}
	
	public void setData(List<KhachHang> khs, List<SanPham> sps) {
		this.khachHangs = khs;
		this.sanPhams = sps;
		muas = new Vector<Vector>();
		initTable();
	}
	
	public void resert() {
		muas.clear();
		defaultTableModelMua.setRowCount(0);
		danhSachSanPham.clearSelection();
		danhSachMua.clearSelection();	
		tableKhachHang.clearSelection();
		txtPriceTotal.setText("Tổng cộng: 0đ");
	}
	
	private void setupTableKhachHang() {
		String[] rows = {"Mã", "Tên", "Số điện thoại", "Giới tính", "Ngày sinh", "Thời gian tạo"};
		String[][] datas = new String[Objects.nonNull(khachHangs) ? khachHangs.size() : 0][rows.length];
		if (Objects.nonNull(khachHangs))
			for (int i = 0; i < khachHangs.size(); i++) {
				KhachHang kh = khachHangs.get(i);
				datas[i][0] = String.valueOf(kh.getId());
				datas[i][1] = kh.getName();
				datas[i][2] = kh.getPhoneNumber();
				datas[i][3] = kh.isGender() ? "Nam" : "Nữ";
				datas[i][4] = Objects.nonNull(kh.getDob()) ? DateTimeFormater.toString(kh.getDob()) : "";
				datas[i][5] = Objects.nonNull(kh.getTimeCreate()) ? DateTimeFormater.toString(kh.getTimeCreate()) : "";
			}
		defaultTableModelKhachHang.setDataVector(datas, rows);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Đặt renderer cho mỗi cột trong bảng
        for (int i = 0; i < tableKhachHang.getColumnCount(); i++) {
            tableKhachHang.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
	}
	
	private boolean match(SanPham sp, String key, int tieuChi) {
		switch (tieuChi) {
			case 0: return String.valueOf(sp.getId()).contains(key);
			case 1: return sp.getName().contains(key);
			case 2: return String.valueOf(sp.getPrice()).contains(key);
		}
		return false;
	}
	
	private void setupTableSanPham() {
		String keySearch = inputSearch.getText();
		int tieuChi = choseSearch.getSelectedIndex();
		int size = Objects.nonNull(sanPhams) ? sanPhams.size() : 0;
		SanPham[][] datas = new SanPham[size > 0 ? (size % 4 == 0 ? size / 4 : (size / 4 + 1)) : 0][4];
		if (Objects.nonNull(sanPhams)) {
			int indexI = 0, indexY = 0;
			for (SanPham sp : sanPhams) {
				if (!match(sp, keySearch, tieuChi)) continue;
				datas[indexI][indexY] = sp;
				if (indexY == 3)
				{
					indexI++;
					indexY = 0;
				} else 
					indexY++;
			}
			System.out.println(indexI + " " + indexY);
		}
		defaultTableModelSanPham.setDataVector(datas, new String[] {"", "", "", ""});
	}
	
	private void setupTableMua() {
		Vector<String> rows = new Vector<String>();
		rows.add("Mã sản phẩm");
		rows.add("Tên sản phẩm");
		rows.add("Số lượng");
		rows.add("Giá");
		rows.add("Hành động");
		defaultTableModelMua.setDataVector(muas, rows);
	}
	
	private class CustomRenderTableMua extends DefaultTableCellRenderer {
		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {
			// TODO Auto-generated method stub
			if (arg5 == 4) 
				return new JButton("Xóa");
			JLabel jlaebl = (JLabel) super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
			jlaebl.setAlignmentX(Component.CENTER_ALIGNMENT);
			jlaebl.setHorizontalAlignment(JLabel.CENTER);
			return jlaebl;
		}
	}
	
	private class CustomRenderItemTable extends DefaultTableCellRenderer {
		Color selectedBackgroundColor = new Color(0f, 0f, 0f, 0.2f);
		@Override
		public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4,
				int arg5) {
			// TODO Auto-generated method stub
			if (Objects.nonNull(arg1) && arg1 instanceof SanPham) {
				SanPham sp = (SanPham) arg1;
				Component com = new ProductItem(sp);
				if (arg2) {
					com.setBackground(selectedBackgroundColor);	
					if (Objects.isNull(muas) || muas.size() == 0) {
						muas = new Vector<Vector>();
						addRow(sp);
					} else
						for (int i = 0; i < muas.size(); i++) {
							if (Integer.valueOf(muas.get(i).get(0).toString()) == sp.getId()) 
								break;
							if (i == muas.size() - 1) {
								addRow(sp);
							}
						}
				}
				return com;
			}
			return super.getTableCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5);
		}
		private void addRow(SanPham sp) {
			Vector<String> newRow = new Vector<String>();
			newRow.add(String.valueOf(sp.getId()));
			newRow.add(sp.getName());
			newRow.add("0");
			newRow.add("0");
			muas.add(newRow);
			defaultTableModelMua.addRow(newRow);
		}
	}
	
	private void tinhTien() {
		double tong = 0;
		for (Vector vt : muas) {
			tong += Double.valueOf(vt.get(3).toString());
		}
		txtPriceTotal.setText("Tổng cộng: " + String.valueOf(tong) + "đ");
	}
	
	private class CustomEditorItemTable extends AbstractCellEditor implements TableCellEditor {
		JTextField label;
		int row = -1;
		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			if (Objects.nonNull(sanPhams) && row >= 0) {
				int id = Integer.valueOf(muas.get(row).get(0).toString());
				SanPham sp = sanPhams.stream().filter(item -> item.getId() == id).findFirst().get();
				int sl = Integer.valueOf(label.getText());
				if (sl > sp.getCount()) {
					showMessage("Không đủ số lượng", JOptionPane.WARNING_MESSAGE);
					sl = sp.getCount();
					label.setText(String.valueOf(sl));
				}
				muas.get(row).set(3, String.valueOf(sl * sp.getPrice()));
				tinhTien();
			}
			return Objects.isNull(label) ? null : label.getText();
		}

		@Override
		public Component getTableCellEditorComponent(JTable arg0, Object arg1, boolean arg2, int arg3, int arg4) {
			// TODO Auto-generated method stub
			if (arg4 == 2) {
				row = arg3;
				label = new JTextField();
				label.setText(arg1.toString());
				((AbstractDocument) label.getDocument()).setDocumentFilter(new NumericDocumentFilter());
				return label;
			}
			row = -1;
			if (arg4 == 4) {
				JButton btnXoa = new JButton("Xóa");
				btnXoa.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						muas.remove(arg3);
						defaultTableModelMua.removeRow(arg3);
					}
				});
				return btnXoa;
			}
			return null;
		}
		
	}
	
	public void setListKhachHang(List<KhachHang> khs) {
		this.khachHangs = khs;
		setupTableKhachHang();
	}
	
	public KhachHang getKhachHangSelected() {
		int index = tableKhachHang.getSelectedRow();
		if (index >= 0) 
			return khachHangs.get(index);
		return null;
	}
	
    static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
            if (isNumeric(string)) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
            if (isNumeric(text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }

        private boolean isNumeric(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }
    
    public int[][] getHoaDon() {
    	int[][] infos = new int[muas.size()][2];
    	for (int i = 0; i < muas.size(); i++)
    	{
    		infos[i][0] = Integer.valueOf(muas.get(i).get(0).toString());
    		infos[i][1] = Integer.valueOf(muas.get(i).get(2).toString());
    		System.out.println(infos[i][0] + " " + infos[i][1]);
    	}
    	return infos;
    }
    
	public void addEventToAddCustomer(ActionListener actionListener) {
		btnAddKH.addActionListener(actionListener);
	}
	
	public void addEventToEditCustomer(ActionListener actionListener) {
		btnEditKH.addActionListener(actionListener);
	}
	
	public void addEventRemoveCustomer(ActionListener actionListener) {
		btnRmKH.addActionListener(actionListener);
	}
	
	public void showMessage(String message, int option) {
		JOptionPane.showMessageDialog(this, message, "Thông báo", option);
	}
	
	public void addEventSaveHoaDon(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}
}
