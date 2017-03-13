package com.tutorialspoint.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jubula.client.AUT;
import org.eclipse.jubula.client.AUTAgent;
import org.eclipse.jubula.client.MakeR;
import org.eclipse.jubula.client.launch.AUTConfiguration;
import org.eclipse.jubula.toolkit.enums.ValueSets.InteractionMode;
import org.eclipse.jubula.toolkit.enums.ValueSets.Operator;
import org.eclipse.jubula.toolkit.swing.SwingComponents;
import org.eclipse.jubula.toolkit.swing.config.SwingAUTConfiguration;
import org.eclipse.jubula.toolkit.swt.SwtComponents;
import org.eclipse.jubula.toolkit.swt.components.ButtonComponent;
import org.eclipse.jubula.toolkit.swt.components.TextInputComponent;
import org.eclipse.jubula.tools.AUTIdentifier;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JubulaTest {
	AUT m_aut;
	AUTAgent m_agent;

	@BeforeTest
	public void beforetest() {
		System.out
				.println("Test Initiation..started." + new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss").format(new Date()));
		m_agent = MakeR.createAUTAgent("localhost", 60000);
		m_agent.connect();
		final String autoid = "Jbtest_01";
		final String workingDir = "E:\\Prasanna\\JavaTutorial\\Projects\\SimpleCalc";
		AUTConfiguration config = new SwingAUTConfiguration(autoid, autoid, "Calc.cmd", workingDir, null);
		System.out.println("Getting APP Ready...!");
		AUTIdentifier id = m_agent.startAUT(config);
		System.out.println("Got ID...");
		if (id != null) {
			System.out.println("Trying to connect ...!!");
			m_aut = m_agent.getAUT(id, SwingComponents.getToolkitInformation());
			m_aut.connect();
			System.out.println("Connected ...!!");
		} else {
			System.out.println("AUT Start has failed");
		}
		System.out.println(
				"Test Initiation..completed." + new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss").format(new Date()));
	}

	@Test
	public void doaction() {
		System.out.println("Test Started...." + new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss").format(new Date()));
		TextInputComponent tb1 = (TextInputComponent) SwtComponents.createTextInputComponent(OM.FirstTB1);
		TextInputComponent tb2 = (TextInputComponent) SwtComponents.createTextInputComponent(OM.SecondTB);
		TextInputComponent tb3 = (TextInputComponent) SwtComponents.createTextInputComponent(OM.REsultTB);
		ButtonComponent adbtn = (ButtonComponent) SwtComponents.createButton(OM.AddBtn);
		m_aut.execute(tb1.inputText("100"), "Enter 23 in First");
		m_aut.execute(tb2.inputText("57"), "Enter 57 in Second");
		m_aut.execute(adbtn.click(1, InteractionMode.primary), "Clicked Add Button");
		m_aut.execute(tb3.checkText("157", Operator.equals), "CheckValue");
		System.out.println("Test Completed.." + new SimpleDateFormat("dd-MMM-yyyy_hh_mm_ss").format(new Date()));

	}

	@AfterTest
	public void DeActivate() {
		if (m_aut.isConnected())
			m_aut.disconnect();
		if (m_agent.isConnected())
			m_agent.disconnect();
	}

}
