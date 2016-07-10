package wordFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

//模仿一个打开过程
public class StartFrame {
	private static JProgressBar progressBar;
	private static JFrame frame;

	public static void main(String[] args) {
		new StartFrame();
		progressBar.setIndeterminate(false);
		for (int i = 0; i <= 100; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			progressBar.setValue(i);
		}
		try {
			Thread.sleep(10);
			new MainFrame().launch();
			frame.dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public StartFrame() {
		frame = new JFrame();
		JLabel label = new JLabel("程序正在加载,请稍等.....");
		label.setPreferredSize(new Dimension(300, 30));
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		frame.setLayout(new BorderLayout());
		frame.add(label, BorderLayout.NORTH);
		frame.add(progressBar, BorderLayout.CENTER);
		initComponent(frame);
	}
	
	 private void initComponent(JFrame frame) {
	 try {
	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	 } catch (ClassNotFoundException | InstantiationException |
	 IllegalAccessException
	 | UnsupportedLookAndFeelException e) {
	 e.printStackTrace();
	 JOptionPane.showMessageDialog(null, "界面异常");
	 }
	 SwingUtilities.updateComponentTreeUI(frame);
	 frame.setVisible(true);
	 frame.setSize(300, 100);
	 Toolkit toolkit = frame.getToolkit();
	 Dimension dimension = toolkit.getScreenSize();
	 int screenWidth = dimension.width;
	 int screenHight = dimension.height;
	 int frameWidth = frame.getWidth();
	 int frameheight = frame.getHeight();
	 frame.setLocation((screenWidth - frameWidth) / 2, (screenHight -
	 frameheight) / 2);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }

}
