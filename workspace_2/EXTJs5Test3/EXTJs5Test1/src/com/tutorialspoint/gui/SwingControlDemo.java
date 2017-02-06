package com.tutorialspoint.gui;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Date;

import javax.swing.*;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import ExtJSS.IPas.*;
import GmailTest.Test1;
import GmailTest.Test2;

public class SwingControlDemo {

   private JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   private JPanel controlPanel2;
   private JPanel  masterPanel;
   public String cbox1 = "false";
   public String cbox2 = "false";
   public String cbox0 = "false";
   public Boolean res = true;
   public Boolean Noaction = false;
   public Vector<JCheckBox> checkVect = new Vector<JCheckBox>( 2 );
   public SwingControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingControlDemo swingControlDemo = new SwingControlDemo();  
      swingControlDemo.showEventDemo();       
   }
      
   private void prepareGUI(){
      mainFrame = new JFrame("Run UI Test");
      mainFrame.setSize(800,400);
      mainFrame.setLayout(new GridLayout(3, 3));

      headerLabel = new JLabel("",JLabel.CENTER );
      statusLabel = new JLabel("",JLabel.LEFT);        

      
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
	        System.exit(0);
         }        
      });    
      controlPanel = new JPanel();
      masterPanel = new JPanel();
      masterPanel.setLayout(new FlowLayout());
      masterPanel.setSize(800,20);
      controlPanel.setLayout(new FlowLayout());
      controlPanel2 = new JPanel();
      controlPanel2.setLayout( new BoxLayout(controlPanel2, BoxLayout.Y_AXIS));
   
      mainFrame.add(masterPanel);
      mainFrame.add(controlPanel2);
      mainFrame.add(controlPanel);
      
   
      mainFrame.setVisible(true);  
   }

   public  void showEventDemo(){
      headerLabel.setText("Select Junit Tests to Run ..."); 

      JButton okButton = new JButton("Execute Test");
      JButton submitButton = new JButton("Submit");
      JButton cancelButton = new JButton("Close");
      JButton clearButton = new JButton("Clear");
      JCheckBox  checkbox =  new JCheckBox("Select All");
     
      final JCheckBox  checkbox1 =  new JCheckBox("Test1");
      final JCheckBox  checkbox2 =  new JCheckBox("Test2");
      
      okButton.setActionCommand("OK");
      submitButton.setActionCommand("Submit");
      cancelButton.setActionCommand("Cancel");
      clearButton.setActionCommand("Clear");
      checkbox.setActionCommand("All");
      checkbox1.setActionCommand("test1");
      checkbox2.setActionCommand("test2");
      
      okButton.addActionListener(new ButtonClickListener()); 
      submitButton.addActionListener(new ButtonClickListener()); 
      cancelButton.addActionListener(new ButtonClickListener()); 
      clearButton.addActionListener(new ButtonClickListener());
      checkbox.addActionListener(new checkboxchecklistener());
      checkbox1.addActionListener(new checkboxchecklistener());
      checkbox2.addActionListener(new checkboxchecklistener());
      headerLabel.setSize(350,100);
      masterPanel.add(headerLabel);
      
      controlPanel2.add(checkbox,BorderLayout.NORTH);
      controlPanel2.add(checkbox1,BorderLayout.CENTER);
      controlPanel2.add(checkbox2,BorderLayout.SOUTH);
      controlPanel.add(okButton,BorderLayout.SOUTH);
      controlPanel.add(submitButton);
      controlPanel.add(cancelButton); 
      controlPanel.add(clearButton); 
      controlPanel.add(statusLabel); 
      checkVect.insertElementAt(checkbox1, 0);
      checkVect.insertElementAt(checkbox2, 1);
      checkVect.insertElementAt(checkbox, 2);

      mainFrame.setVisible(true);  
   }

   public  class ButtonClickListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
         String command = e.getActionCommand();  
         if( command.equals( "OK" ))  {
            statusLabel.setText("Executing the Test ....");
           // runClient();
          if ( cbox1.equals("true"))
          {
        	  Noaction = false;
        	  runTestCase(Test1.class);
          }
          if ( cbox2.equals("true"))
          {
        	  Noaction = false;
        	  runTestCase(Test2.class);
          }
           if (cbox1.equals("false") && cbox2.equals("false") && cbox0.equals("false"))
           {
        	   // show alert Message of Java
        	   JOptionPane.showMessageDialog(mainFrame, "No Action Will Be Performed", "Test Script Execution",1);
        	   statusLabel.setText("No Action Taken");
        	   Noaction = true;
        	  
           }
           if (res)
           {
        	   if (Noaction == false )
        	   {
            statusLabel.setText("Test Execution Complete : Status = Pass");
        	   }
           }
           else
           {
        	   if (Noaction == false )
        	   {
        	statusLabel.setText("Test Execution Complete : Status = Fail");
        	   }
           }
         }
         else if( command.equals( "Submit" ) )  {
        	 String dt = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(new Date());
            statusLabel.setText("Submit Button clicked....."+dt); 
         }
         else if( command.equals( "Clear" ) )  {
        	 System.out.println("Inside Clear");
            statusLabel.setText(" "); 
            JCheckBox temp = (JCheckBox) checkVect.elementAt( 0 );
			temp.setSelected(false);
			cbox1 = "false";
			temp = (JCheckBox) checkVect.elementAt( 1 );
			cbox2 = "false";
			temp.setSelected(false);
			temp = (JCheckBox) checkVect.elementAt( 2 );
			cbox2 = "false";
			temp.setSelected(false);
         }
         else  {
            statusLabel.setText("Cancel Button clicked.");
            System.exit(0);
         }  	
      }		
   }
   
   private class checkboxchecklistener implements ActionListener{
	   public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			AbstractButton abtn = (AbstractButton) e.getSource();
			if (command.equals("All")) {
				if (abtn.getModel().isSelected()) {
					
					JCheckBox temp = (JCheckBox) checkVect.elementAt( 0 );
					temp.setSelected(true);
					cbox1 = "true";
					temp = (JCheckBox) checkVect.elementAt( 1 );
					cbox2 = "true";
					temp.setSelected(true);
	               
				} else {
					JCheckBox temp = (JCheckBox) checkVect.elementAt( 0 );
					temp.setSelected(false);
					cbox1 = "false";
					temp = (JCheckBox) checkVect.elementAt( 1 );
					cbox2 = "false";
					temp.setSelected(false);
					
				}
			}
			if (command.equals("test1")) {
				if (abtn.getModel().isSelected()) {
					statusLabel.setText("Check Box is checked ");
					cbox1 = "true";
					//runTestCase(Test1.class);
				} else {
					cbox1 = "false";
					statusLabel.setText("Test1 Check Box is unchecked");
				}
			}
			if (command.equals("test2")) {
				if (abtn.getModel().isSelected()) {
					statusLabel.setText("Test2 being executed  ");
					//runTestCase(Test2.class);
					cbox2 = "true";
				} else {
					statusLabel.setText("Test2  Check Box is unchecked");
					cbox2 = "false";
				}
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
   
   
  public void execjunittests()
   {
	   
	@SuppressWarnings("rawtypes")
	ArrayList<Class> testCases = new ArrayList<Class>();

		//Add test cases
		testCases.add(Test1.class);
		testCases.add(Test2.class);

		for ( @SuppressWarnings("rawtypes") Class<?> testCase : testCases)
              {
			
                   runTestCase(testCase);
              }
   }
   
   private  void runTestCase(@SuppressWarnings("rawtypes") Class<?> testCase)
   {
	   System.out.println("Executing: "+testCase.getName());
       Result result = JUnitCore.runClasses(testCase);
       if (result.getFailureCount() == 0 )
       {
    	   System.out.println("All test Cases are Pass ");
       }
       for (Failure failure : result.getFailures())
       {
           System.out.println(failure.toString());
           res = false;
       }
   }
   
   
   
   
}



