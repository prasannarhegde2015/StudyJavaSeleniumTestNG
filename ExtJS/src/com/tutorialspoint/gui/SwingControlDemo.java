package com.tutorialspoint.gui;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import ExtJSS.IPas.*;

public class SwingControlDemo {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;

   public SwingControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingControlDemo swingControlDemo = new SwingControlDemo();  
      swingControlDemo.showEventDemo();       
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("Run UI Test");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.CENTER);        

      statusLabel.setSize(350,100);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   private void showEventDemo(){
      headerLabel.setText("Control in action: Button"); 

      JButton okButton = new JButton("Execute Test");
      JButton submitButton = new JButton("Submit");
      JButton cancelButton = new JButton("Close");
      JCheckBox  checkbox =  new JCheckBox();

      okButton.setActionCommand("OK");
      submitButton.setActionCommand("Submit");
      cancelButton.setActionCommand("Cancel");

      okButton.addActionListener(new ButtonClickListener()); 
      submitButton.addActionListener(new ButtonClickListener()); 
      cancelButton.addActionListener(new ButtonClickListener()); 
      checkbox.addActionListener(new checkboxchecklistener());
      controlPanel.add(okButton);
      controlPanel.add(submitButton);
      controlPanel.add(cancelButton); 
      controlPanel.add(checkbox);

      mainFrame.setVisible(true);  
   }

   private class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         if( command.equals( "OK" ))  {
            statusLabel.setText("Executing the Test ....");
            runClient();
            statusLabel.setText("Test Execution Complete ....");
         }
         else if( command.equals( "Submit" ) )  {
        	 String dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            statusLabel.setText("Submit Button clicked....."+dt); 
         }
         else  {
            statusLabel.setText("Cancel Button clicked.");
            System.exit(0);
         }  	
      }		
   }
   
   private class checkboxchecklistener implements ActionListener{
	   public void actionPerformed(ActionEvent e) {
	         AbstractButton abtn = (AbstractButton)e.getSource();
	         if( abtn.getModel().isSelected())  {
	            statusLabel.setText("Check Box is checked ");
	           // runClient();
	         }
	         else  {
	            statusLabel.setText("Check Box is unchecked");
	         }  	
	      }		
   }

   private static void runClient() {
	   SwingUtilities.invokeLater(new Runnable() {
	       @Override
	       public void run() {
	        	String[] args1={"10"};
	            try {
					MainClass.main(args1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    });
	}
   
   
}