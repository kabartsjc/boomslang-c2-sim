package br.com.gmltec.boomslangc2.gui.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ExerciseEditorPanel  extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField tfExeName;
	private JTextField txtSimuDuration;
	
	private JButton saveBt;
	
	private ScenarioEditorGui scenGUI;
	
	public ExerciseEditorPanel(ScenarioEditorGui scenGUI) {
		this.scenGUI=scenGUI;
	}
	
	public void configure() {
		JLabel lblName = new JLabel("Exercise Name:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(16, 31, 77, 16);
		add(lblName);
		
		tfExeName = new JTextField();
		tfExeName.setBounds(95, 26, 329, 26);
		tfExeName.setEditable(true);
		add(tfExeName);
		tfExeName.setColumns(10);
		
		JLabel lblSimDuration = new JLabel("SIMU DURATION (hour:min):");
		lblSimDuration.setBounds(16, 76, 155, 16);
		add(lblSimDuration);

		txtSimuDuration = new JTextField();
		txtSimuDuration.setBounds(166, 71, 85, 26);
		txtSimuDuration.setEditable(true);
		add(txtSimuDuration);
		txtSimuDuration.setColumns(10);
		
		Icon icon = new ImageIcon("src/main/resources/icons/save.png");
		
		saveBt = new JButton(icon);
		saveBt.setText("Save...");
		add(saveBt);
		
		saveBt.addActionListener(new BtSaveActionListener());
		
		
				
	}
	
	private class BtSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String exeName = tfExeName.getText();
			String simuDuration = txtSimuDuration.getText();
			
			if (exeName!= null && simuDuration !=null) {
				scenGUI.persistMission(exeName, simuDuration);
			
			} else {
				JOptionPane.showMessageDialog(null, "It is required to fill exe name and simu duration.");
			}
		}
	}
	
	
	
	
}
