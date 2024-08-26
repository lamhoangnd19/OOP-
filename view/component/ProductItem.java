package vn.viettuts.qlsv.view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.viettuts.qlsv.entity.SanPham;

public class ProductItem extends JPanel {
	private JLabel img;
	private JLabel ten;
	private JLabel gia;
	public ProductItem(SanPham sanPham) {
		setBorder(new EmptyBorder(5,5,5,5));
		ten = new JLabel(sanPham.getName(), JLabel.CENTER);
		ten.setAlignmentX(Component.CENTER_ALIGNMENT);
		gia = new JLabel(String.valueOf(sanPham.getPrice()) + "đ", JLabel.CENTER);
		gia.setAlignmentX(Component.CENTER_ALIGNMENT);
		img = new JLabel(new ImageIcon(new ImageIcon("images/products/" + sanPham.getUrlImage()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)), JLabel.CENTER);
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		ten.setFont(new Font("Arial", Font.PLAIN, 16));
		gia.setFont(new Font("Arial", Font.PLAIN, 14));		
		gia.setForeground(Color.blue);
		add(img);
		add(ten);
		add(gia);
	}
}
