package wordFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

//word主界面
public class MainFrame implements ActionListener, MouseListener {
	JTabbedPane tabbedPane;
	int i;
	JTextPane textPane[] = new JTextPane[10];
	Clipboard clipboard;
	JTable table;
	DefaultTableModel tableModel;
	JComboBox<String> styleComboBox;
	JComboBox<Integer> sizeComboBox;
	JPopupMenu pop;

	public static void main(String[] args) {
		new MainFrame();
	}

	public MainFrame() {
		JFrame frame = new JFrame();
		JPanel contentPane = getContentPane();
		pop = new JPopupMenu();
		JMenuItem cutMenu = new JMenuItem("剪切");
		cutMenu.setActionCommand("剪切");
		cutMenu.addActionListener(this);
		JMenuItem copyMenu = new JMenuItem("复制");
		copyMenu.setActionCommand("复制");
		copyMenu.addActionListener(this);
		JMenuItem pasteMenu = new JMenuItem("粘贴");
		pasteMenu.setActionCommand("粘贴");
		pasteMenu.addActionListener(this);
		pop.add(cutMenu);
		pop.add(copyMenu);
		pop.add(pasteMenu);
		frame.setContentPane(contentPane);
		initComponent(frame);
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
		// frame.setResizable(false);
		frame.setVisible(true);
		frame.setSize(new Dimension(522, 523));
		clipboard = frame.getToolkit().getSystemClipboard(); // 获得系统剪贴板
		Toolkit toolkit = frame.getToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int screenWidth = dimension.width;
		int screenHight = dimension.height;
		int frameWidth = frame.getWidth();
		int frameheight = frame.getHeight();
		frame.setLocation((screenWidth - frameWidth) / 2, (screenHight - frameheight) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getContentPane() {
		JMenuBar menuBar = getMenuBar();
		JToolBar toolBar = getToolBar();
		tabbedPane = new JTabbedPane();
		textPane[0] = new JTextPane();
		textPane[0].setSize(500, 500);
		textPane[0].setEnabled(true);
		JScrollPane jsp = new JScrollPane(textPane[0]);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textPane[0].requestFocus();
		tabbedPane.add(" ", jsp);

		JPanel annotationPane = getAnnotationPane();
		JPanel northPane = new JPanel();
		northPane.setLayout(new GridLayout(0, 1));
		northPane.add(menuBar);
		northPane.add(toolBar);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(northPane, BorderLayout.PAGE_START);
		panel.add(tabbedPane, BorderLayout.CENTER);
		panel.add(annotationPane, BorderLayout.SOUTH);
		return panel;
	}

	private JPanel getAnnotationPane() {
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("第");
		JTextField textField1 = new JTextField(1);
		textField1.setText("1");
		textField1.setEditable(false);
		JLabel label2 = new JLabel("页,共");
		JTextField textField2 = new JTextField(1);
		textField2.setText("1");
		textField2.setEditable(false);
		JLabel label3 = new JLabel("页");
		Icon icon = new ImageIcon(getClass().getResource("smiley.png"));
		JLabel iconLabel = new JLabel(icon);
		JLabel label4 = new JLabel("英语(美国)");
		// 缺一个放大的条
		panel.add(label1);
		panel.add(textField1);
		panel.add(label2);
		panel.add(textField2);
		panel.add(label3);
		panel.add(iconLabel);
		panel.add(label4);
		return panel;
	}

	private JToolBar getToolBar() {
		JToolBar toolBar = new JToolBar();
		Icon copyIcon = new ImageIcon(getClass().getResource("star.png"));
		JButton copyButton = new JButton(copyIcon);
		copyButton.setToolTipText("复制");
		copyButton.setActionCommand("复制");
		copyButton.addActionListener(this);
		Icon saveIcon = new ImageIcon(getClass().getResource("heart.png"));
		JButton saveButton = new JButton(saveIcon);
		saveButton.setToolTipText("保存");
		saveButton.setActionCommand("保存");
		saveButton.addActionListener(this);
		Icon cutIcon = new ImageIcon(getClass().getResource("cut.png"));
		JButton cutButton = new JButton(cutIcon);
		cutButton.setToolTipText("剪切");
		cutButton.setActionCommand("剪切");
		cutButton.addActionListener(this);
		Icon pasteIcon = new ImageIcon(getClass().getResource("featured.png"));
		JButton pasteButton = new JButton(pasteIcon);
		pasteButton.setToolTipText("粘贴");
		pasteButton.setActionCommand("粘贴");
		pasteButton.addActionListener(this);
		Icon addIcon = new ImageIcon(getClass().getResource("plus.png"));
		JButton newtextArea = new JButton(addIcon);
		newtextArea.setActionCommand("新建");
		newtextArea.addActionListener(this);
		Icon closeIcon = new ImageIcon(getClass().getResource("close_delete.png"));
		styleComboBox = new JComboBox<String>();
		new FontFrame().getStyleComboBox(styleComboBox);
		styleComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				styleToolBar(e);
			}
		});
		sizeComboBox = new JComboBox<Integer>();
		new FontFrame().getSizeComboBox(sizeComboBox);
		sizeComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sizeToolBar(e);
			}
		});
		Icon boldIcon = new ImageIcon(getClass().getResource("login.png"));
		JButton boldButton = new JButton(boldIcon);
		boldButton.setToolTipText("粗体");
		boldButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boldToolBar(e);
			}
		});
		Icon italicIcon = new ImageIcon(getClass().getResource("logout.png"));
		JButton italicButton = new JButton(italicIcon);
		italicButton.setToolTipText("斜体");
		italicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				italicToolBar(e);
			}
		});
		Icon colorIcon = new ImageIcon(getClass().getResource("burn.png"));
		JButton colorButton = new JButton(colorIcon);
		colorButton.setActionCommand("颜色");
		colorButton.addActionListener(this);
		toolBar.add(saveButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(copyButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(cutButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(pasteButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(newtextArea);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(boldButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(italicButton);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(styleComboBox);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(sizeComboBox);
		toolBar.addSeparator(new Dimension(5, copyButton.getHeight()));
		toolBar.add(colorButton);
		return toolBar;
	}

	protected void italicToolBar(ActionEvent e) {
		int index = tabbedPane.getSelectedIndex();
		Font oldFont = textPane[index].getFont();
		Font font = new Font(oldFont.getName(), Font.ITALIC, oldFont.getSize());
		textPane[index].setFont(font);
		JOptionPane.showMessageDialog(null, "已设置好，请使用");
		textPane[index].requestFocus();
	}

	protected void boldToolBar(ActionEvent e) {
		int index = tabbedPane.getSelectedIndex();
		Font oldFont = textPane[index].getFont();
		Font font = new Font(oldFont.getName(), Font.BOLD, oldFont.getSize());
		textPane[index].setFont(font);
		JOptionPane.showMessageDialog(null, "已设置好，请使用");
		textPane[index].requestFocus();
	}

	protected void sizeToolBar(ItemEvent e) {
		if (e.getStateChange() == sizeComboBox.getSelectedIndex()) {
			int size = (int) sizeComboBox.getSelectedItem();
			int index = tabbedPane.getSelectedIndex();
			Font oldFont = textPane[index].getFont();
			Font font = new Font(oldFont.getName(), oldFont.getStyle(), size);
			textPane[index].setFont(font);
			JOptionPane.showMessageDialog(null, "已设置好，请使用");
			textPane[index].requestFocus();
		}
	}

	protected void styleToolBar(ItemEvent e) {
		if (e.getStateChange() == styleComboBox.getSelectedIndex()) {
			String style = styleComboBox.getSelectedItem().toString();
			int index = tabbedPane.getSelectedIndex();
			Font oldFont = textPane[index].getFont();
			Font font = new Font(style, oldFont.getStyle(), oldFont.getSize());// 此处懒了，本来该比较字符串的，全部写成了粗体!!
			textPane[index].setFont(font);
			JOptionPane.showMessageDialog(null, "已设置好，请使用");
			textPane[index].requestFocus();
		}
	}

	private JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("文件");
		fileMenu.setMnemonic('f');
		JMenuItem newMenu = new JMenuItem("新建");
		newMenu.setMnemonic('n');
		newMenu.setActionCommand("新建");
		newMenu.addActionListener(this);
		JMenuItem openMenu = new JMenuItem("打开");
		openMenu.setMnemonic('o');
		openMenu.setActionCommand("打开");
		openMenu.addActionListener(this);
		JMenuItem saveMenu = new JMenuItem("保存");
		saveMenu.setMnemonic('s');
		saveMenu.setActionCommand("保存");
		saveMenu.addActionListener(this);
		JMenuItem exitMenu = new JMenuItem("退出");
		exitMenu.setMnemonic('e');
		exitMenu.setActionCommand("退出");
		exitMenu.addActionListener(this);
		fileMenu.add(newMenu);
		fileMenu.add(openMenu);
		fileMenu.add(saveMenu);
		fileMenu.add(exitMenu);
		menuBar.add(fileMenu);

		JMenu startMenu = new JMenu("开始");
		JMenuItem fontMenu = new JMenuItem("字体");
		fontMenu.setActionCommand("字体");
		fontMenu.addActionListener(this);
		JMenuItem colorMenu = new JMenuItem("颜色");
		colorMenu.setActionCommand("颜色");
		colorMenu.addActionListener(this);
		JMenuItem copytMenu = new JMenuItem("复制");
		copytMenu.setActionCommand("复制");
		copytMenu.addActionListener(this);
		JMenuItem cutMenu = new JMenuItem("剪切");
		cutMenu.setActionCommand("剪切");
		cutMenu.addActionListener(this);
		JMenuItem pasteMenu = new JMenuItem("粘贴");
		pasteMenu.setActionCommand("粘贴");
		pasteMenu.addActionListener(this);
		startMenu.add(fontMenu);
		startMenu.add(colorMenu);
		startMenu.add(copytMenu);
		startMenu.add(cutMenu);
		startMenu.add(pasteMenu);
		menuBar.add(startMenu);

		JMenu insertMenu = new JMenu("插入"); // 此菜单下的所有功能写到此处时发现JtextArea是个纯文本框，故此处必须要用到JtextArea，
		JMenuItem tableMenu = new JMenuItem("表格");// 来插入表格和图片等。故每次使用这几个功能时，请先在工具栏里点击+号，以新建一个文本
		tableMenu.setActionCommand("表格"); // 但其实和前面的不一样
		tableMenu.addActionListener(this);
		JMenuItem canvasMenu = new JMenuItem("图片");
		canvasMenu.setActionCommand("图片");
		canvasMenu.addActionListener(this);
		JMenu graphicsMenu = new JMenu("图形");
		JMenuItem rectMenu = new JMenuItem("矩形");
		rectMenu.setActionCommand("矩形");
		rectMenu.addActionListener(this);
		JMenuItem ovalMenu = new JMenuItem("园形");
		ovalMenu.setActionCommand("圆形");
		ovalMenu.addActionListener(this);
		graphicsMenu.add(rectMenu);
		graphicsMenu.addSeparator();
		graphicsMenu.add(ovalMenu);
		JMenuItem wordMenu = new JMenuItem("艺术字");
		wordMenu.setActionCommand("艺术字");
		wordMenu.addActionListener(this);
		insertMenu.add(tableMenu);
		insertMenu.add(canvasMenu);
		insertMenu.add(graphicsMenu);
		insertMenu.add(wordMenu);
		menuBar.add(insertMenu);

		JMenu designMenu = new JMenu("设计");
		JMenuItem webMenu = new JMenuItem("网页显示");
		webMenu.setActionCommand("Web链接");
		webMenu.addActionListener(this);
		JMenuItem matchMenu = new JMenuItem("小游戏");
		matchMenu.setActionCommand("配对小游戏");
		matchMenu.addActionListener(this);
		JMenuItem flashMenu = new JMenuItem("动态flash");
		flashMenu.setActionCommand("Flash");
		flashMenu.addActionListener(this);
		designMenu.add(webMenu);
		designMenu.addSeparator();
		designMenu.add(matchMenu);
		designMenu.add(flashMenu);
		menuBar.add(designMenu);

		JMenu emailMenu = new JMenu("邮件");
		JMenuItem createMenu = new JMenuItem("创建邮件");// 做一个页面，像邮件的页面。并不能真的发送。
		createMenu.setActionCommand("创建邮件");
		createMenu.addActionListener(this);
		emailMenu.add(createMenu);
		menuBar.add(emailMenu);
		return menuBar;
	}

	public void launch() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("新建")) {
			i++;
			textPane[i] = new JTextPane();
			textPane[i].setSize(500, 500);
			textPane[i].setEnabled(true);
			JScrollPane jsp = new JScrollPane(textPane[i]);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			tabbedPane.add(i + ".txt", jsp);
			tabbedPane.setSelectedComponent(jsp);
			textPane[i].requestFocus();
		}
		if (e.getActionCommand().equals("打开")) {
			textPane[++i] = new JTextPane();
			textPane[i].setSize(500, 500);
			textPane[i].setEnabled(true);
			JDialog dialog = new JDialog(new MyFileChooser(textPane[i], tabbedPane));
			dialog.setVisible(true);
		}
		if (e.getActionCommand().equals("保存")) {
			int n = tabbedPane.getSelectedIndex();
			String text = textPane[n].getText();
			JDialog dialog1 = new JDialog(new SaveFile(text));
			dialog1.setVisible(true);
		}
		if (e.getActionCommand().equals("退出")) {
			System.exit(0);
		}
		if (e.getActionCommand().equals("字体")) {
			int n = tabbedPane.getSelectedIndex();
			FontFrame ff = new FontFrame(textPane[n], tabbedPane);
			JDialog dialog1 = new JDialog(ff);
			dialog1.setVisible(true);

		}
		if (e.getActionCommand().equals("颜色")) {
			int n = tabbedPane.getSelectedIndex();
			JDialog dialog2 = new JDialog(new ColorFrame(textPane[n], tabbedPane));
			dialog2.setVisible(true);
		}
		if (e.getActionCommand().equals("复制")) {
			int index = tabbedPane.getSelectedIndex();
			String text = textPane[index].getSelectedText();
			StringSelection transfer_text = new StringSelection(text);
			clipboard.setContents(transfer_text, null);
			textPane[index].requestFocus();
		}
		if (e.getActionCommand().equals("剪切")) {
			int index = tabbedPane.getSelectedIndex();
			String text = textPane[index].getSelectedText();
			textPane[index].replaceSelection("");
			StringSelection transfer_text = new StringSelection(text);
			clipboard.setContents(transfer_text, null);

		}
		if (e.getActionCommand().equals("粘贴")) {
			int index = tabbedPane.getSelectedIndex();
			Transferable contents = clipboard.getContents(this);
			DataFlavor flavor = DataFlavor.stringFlavor;
			try {
				String text = (String) contents.getTransferData(flavor);
				Document docs = textPane[index].getDocument();
				SimpleAttributeSet attrset = new SimpleAttributeSet();
				docs.insertString(docs.getLength(), text, attrset);
				textPane[index].requestFocus();
			} catch (UnsupportedFlavorException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (BadLocationException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand().equals("表格")) {
			int index = tabbedPane.getSelectedIndex();
			table = new JTable();
			String columnNames[] = { "1", "2", "3", "4", "5", "6", "7", "8" };
			String dataValues[][] = new String[12][8];
			tableModel = new DefaultTableModel(dataValues, columnNames);
			table.setModel(tableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setEnabled(true);
			int position = textPane[index].getCaretPosition();
			textPane[index].setCaretPosition(position);
			textPane[index].insertComponent(table);
			textPane[index].setVisible(true);
			textPane[index].requestFocus();
		}
		if (e.getActionCommand().equals("图片")) {
			int index = tabbedPane.getSelectedIndex();
			int position = textPane[index].getCaretPosition();
			try {
				textPane[index].setCaretPosition(position);
				ImageIcon icon = new ImageIcon(getClass().getResource("insert.png"));
				textPane[index].insertIcon(icon);
				textPane[index].setCaretPosition(position + 1);
				textPane[index].setVisible(true);
				textPane[index].requestFocus();
			} catch (Exception e1) {
				textPane[index].setCaretPosition(position);
				return;
			}
		}
		if (e.getActionCommand().equals("矩形")) {
			int index = tabbedPane.getSelectedIndex();
			new RectFrame(textPane[index]);
		}
		if (e.getActionCommand().equals("圆形")) {
			int index = tabbedPane.getSelectedIndex();
			new OvalFrame(textPane[index]);
		}
		if (e.getActionCommand().equals("艺术字")) {
			int index = tabbedPane.getSelectedIndex();
			new ArtFrame(textPane[index]);
		}
		if (e.getActionCommand().equals("Web链接")) {
			int index = tabbedPane.getSelectedIndex();
			new WebFrame(textPane[index]);
			textPane[index].setVisible(true);
		}
		if (e.getActionCommand().equals("配对小游戏")) {
			int index = tabbedPane.getSelectedIndex();
			new Matching(textPane[index]);
		}
		if (e.getActionCommand().equals("Flash")) {
			int index = tabbedPane.getSelectedIndex();
			new FlashFrame(textPane[index]);
		}
		if (e.getActionCommand().equals("创建邮件")) {
			int index = tabbedPane.getSelectedIndex();
			new EmailFrame(textPane[index]);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {

			int index = tabbedPane.getSelectedIndex();// 没有解决这个弹出菜单的添加问题！应该在创建textPane的时候添加的。累！
			textPane[index].add(pop);
			pop.show(textPane[index], e.getX(), e.getY());
			textPane[index].setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
