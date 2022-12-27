package br.com.gmltec.boomslangc2.gui;

import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ScenarioEditorGui {
	private JFrame mainFrame;
	private ExerciseEditorPanel exeDefPanel;
	private EditorPanel editorPanel;
	private PalletPanel palletPanel;
	private MapPanel mapPanel;
	
	private IEntityType select_model;
	

	public ScenarioEditorGui(Coordinate central_point) {
		initialize(central_point);
	}

	public static void main(String[] args) {
		Coordinate central_point= new Coordinate(1.863707, 44.836479, 0);
		ScenarioEditorGui mg = new ScenarioEditorGui(central_point);
		mg.createAndShowGUI();

	}

	private void initialize(Coordinate central_point) {
		mainFrame = new JFrame();
		mainFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		mainFrame.setTitle("Boomslang C2 Simulator");
		// frame.setBounds(100, 100, 893, 582);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SET TOP
		exeDefPanel=new ExerciseEditorPanel();
		exeDefPanel.configure();
		exeDefPanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Exercise Panel Definition"));
		mainFrame.getContentPane().add(exeDefPanel, BorderLayout.NORTH);
		
		//SET WEST
		palletPanel=new PalletPanel(this);
		//entPanel.configure();
		palletPanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Entity Pallete"));
		
		mainFrame.getContentPane().add(palletPanel, BorderLayout.WEST);
		
		
		//SET CENTER
		mapPanel=new MapPanel();
		//set center area
		mapPanel.configure(central_point, this);
		mainFrame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		
		//SET SOUTH
		editorPanel=new EditorPanel();
		editorPanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Entity Selection Panel"));
		mainFrame.getContentPane().add(editorPanel, BorderLayout.SOUTH);
	}
	
	public void createAndShowGUI() {
		mainFrame.setVisible(true);
	}

	public void setSelectedEntity(IEntityType model) {
		this.select_model=model;
		System.out.println("selected entity = "+select_model.getId());
		
	}

	

}
