package com.tutorialspoint.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calctest {

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JLabel firstarg;
	private JLabel secondarg;
	private JLabel restul;
	private JPanel controlPanel;
	private JPanel controlPanel2;
	private JTextField txtinput1;
	private JTextField txtinput2;
	private JTextField txtinput3;

	public Calctest() {
		prepareGUI();
	}

	public static void main(String[] args) {
		try {
			Class.forName("jodd.swingspy.SwingSpy").getMethod("install").invoke(null);
		} catch (Exception ex) {
			System.err.println("SwingSpy is not installed... " + ex.toString());
		}
		Calctest icalctest = new Calctest();
		icalctest.showEventDemo();
	}

	private void prepareGUI() {
		mainFrame = new JFrame("Java Swing CalCulator");
		mainFrame.setSize(400, 400);
		mainFrame.setLayout(new GridLayout(4, 1));

		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);

		statusLabel.setSize(350, 100);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel2 = new JPanel();
		controlPanel2.setLayout(new GridLayout(3, 2));
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel2);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.setVisible(true);
	}

	private void showEventDemo() {
		headerLabel.setText("Simple Arithmetic Calculator");

		firstarg = new JLabel("First", JLabel.LEFT);
		secondarg = new JLabel("Second", JLabel.LEFT);
		restul = new JLabel("Result", JLabel.LEFT);

		txtinput1 = new JTextField(10);
		txtinput2 = new JTextField(10);
		txtinput3 = new JTextField(10);
		txtinput3.setEditable(false);
		JButton addButton = new JButton("Add (+)");
		JButton substractButton = new JButton("Substract (-)");
		JButton multipybuttonButton = new JButton("Multiply (*)");
		JButton divideButton = new JButton("Divide (/) ");

		addButton.setActionCommand("add");
		substractButton.setActionCommand("substract");
		multipybuttonButton.setActionCommand("multiply");
		divideButton.setActionCommand("divide");

		addButton.addActionListener(new ButtonClickListener());
		substractButton.addActionListener(new ButtonClickListener());
		multipybuttonButton.addActionListener(new ButtonClickListener());
		divideButton.addActionListener(new ButtonClickListener());

		controlPanel2.add(firstarg);
		controlPanel2.add(txtinput1);
		controlPanel2.add(secondarg);
		controlPanel2.add(txtinput2);
		controlPanel2.add(restul);
		controlPanel2.add(txtinput3);

		controlPanel.add(addButton);
		controlPanel.add(substractButton);
		controlPanel.add(multipybuttonButton);
		controlPanel.add(divideButton);

		mainFrame.setVisible(true);
	}

	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("add")) {
				statusLabel.setText("Executing the Test ....");

				String arg1 = txtinput1.getText();
				String arg2 = txtinput2.getText();
				try {
					int intarg1 = Integer.parseInt(arg1);
					int intarg2 = Integer.parseInt(arg2);
					int intres = intarg1 + intarg2;
					txtinput3.setText(String.valueOf(intres));
					statusLabel.setText("Addition Result ....");
					statusLabel.setForeground(Color.GREEN);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					statusLabel.setText("Input Only numbers ....for first and second");
					statusLabel.setForeground(Color.RED);
				}

			} else if (command.equals("substract")) {
				statusLabel.setText("Executing the Test ....");
				statusLabel.setText("Test Execution Complete ....");
				String arg1 = txtinput1.getText();
				String arg2 = txtinput2.getText();
				try {
					int intarg1 = Integer.parseInt(arg1);
					int intarg2 = Integer.parseInt(arg2);
					int intres = intarg1 - intarg2;
					txtinput3.setText(String.valueOf(intres));
					statusLabel.setText("substraction result ....");
					statusLabel.setForeground(Color.GREEN);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					statusLabel.setText("Input Only numbers ....for first and second");
					statusLabel.setForeground(Color.RED);
				}
			} else if (command.equals("multiply")) {
				statusLabel.setText("Executing the Test ....");
				statusLabel.setText("Test Execution Complete ....");
				String arg1 = txtinput1.getText();
				String arg2 = txtinput2.getText();
				try {
					int intarg1 = Integer.parseInt(arg1);
					int intarg2 = Integer.parseInt(arg2);
					int intres = intarg1 * intarg2;
					txtinput3.setText(String.valueOf(intres));
					statusLabel.setText("multiplication  result ....");
					statusLabel.setForeground(Color.GREEN);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					statusLabel.setText("Input Only numbers ....for first and second");
					statusLabel.setForeground(Color.RED);
				}

			} else if (command.equals("divide")) {
				statusLabel.setText("Executing the Test ....");
				statusLabel.setText("Test Execution Complete ....");
				String arg1 = txtinput1.getText();
				String arg2 = txtinput2.getText();
				try {
					float intarg1 = Float.parseFloat(arg1);
					float intarg2 = Float.parseFloat(arg2);
					float intres = -1;
					if (intarg2 != 0) {
						intres = intarg1 / intarg2;
					} else {
						statusLabel.setText("Divide by zero Error!!");
					}
					txtinput3.setText(String.valueOf(intres));
					statusLabel.setText("division  result ....");
					statusLabel.setForeground(Color.GREEN);
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					statusLabel.setText("Input Only numbers ....for first and second");
					statusLabel.setForeground(Color.RED);
				}
			} else {
				statusLabel.setText("Invlaid Operation");
				System.exit(0);
			}
		}
	}
}
