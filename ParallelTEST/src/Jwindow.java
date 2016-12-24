import java.awt.EventQueue;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Jwindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jwindow window = new Jwindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Jwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 523, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textField = new JTextField();
		textField.setBackground(Color.YELLOW);
		textField.setBounds(10, 10, 60, 20);
		textField.setColumns(3);
		
		JButton btnSubmit = new JButton("CheckStatus");
		btnSubmit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String srcfile = textField.getText();
				
				File infile = new File(srcfile);
				
				if ( infile.exists())
				{
					JOptionPane.showMessageDialog(null, "File Exists: "+srcfile.toString());
				}
				else
					
				{
					JOptionPane.showMessageDialog(null, "File Does Not Exist:  "+srcfile.toString());
				}
			}
		});
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.getContentPane().add(textField);
		frame.getContentPane().add(btnSubmit);
	}

}
