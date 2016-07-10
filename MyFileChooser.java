package wordFrame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MyFileChooser extends JFrame {
	JTextField textField, textField1;

	public MyFileChooser(JTextPane textPane, JTabbedPane tabbedPane) {
		setLayout(null);
		JLabel label = new JLabel("文件名");
		label.setBounds(23, 21, 61, 18);
		textField = new JTextField();
		textField.setBounds(89, 19, 145, 22);
		JLabel label1 = new JLabel("文件路径");
		label1.setBounds(23, 71, 61, 18);
		textField1 = new JTextField();
		textField1.setBounds(89, 79, 145, 22);
		JButton button = new JButton("选择");
		button.setBounds(240, 19, 82, 22);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int returnValue = fileChooser.showOpenDialog(getContentPane());
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					textField.setText(file.getName());
					textField1.setText(file.getAbsolutePath());
				}
			}
		});
		JButton button1 = new JButton("打开");// 将文件读进来，并新建一个文件，并命名为打开的文件名字！！
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(textField1.getText().trim());
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(file));
					String str = br.readLine();
					JScrollPane jsp = new JScrollPane(textPane);
					textPane.setText(str);
					jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
					tabbedPane.addTab(file.getName(), jsp);
					tabbedPane.setSelectedComponent(jsp);
					textPane.setVisible(true);
					textPane.requestFocus();
					dispose();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "文件打开异常");
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "IO异常");
				} finally {
					try {
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		button1.setBounds(240, 79, 82, 22);

		add(label);
		add(textField);
		add(label1);
		add(textField1);
		add(button);
		add(button1);

		initComponent(this);
		setBounds(100, 100, 360, 200);
	}

	private void initComponent(JFrame frame) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "界面异常");
		}
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		Toolkit toolkit = frame.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenWidth = dimension.width;
		int screenHight = dimension.height;
		int frameWidth = frame.getWidth();
		int frameheight = frame.getHeight();
		frame.setLocation((screenWidth - frameWidth) / 2, (screenHight - frameheight) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
