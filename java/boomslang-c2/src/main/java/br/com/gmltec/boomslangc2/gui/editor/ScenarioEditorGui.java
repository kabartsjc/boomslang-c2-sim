package br.com.gmltec.boomslangc2.gui.editor;

import br.com.gmltec.boomslangc2.phy.model.Entity;
import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class ScenarioEditorGui {
	private JFrame mainFrame;
	private ExerciseEditorPanel exeDefPanel;
	private EditorPanel editorPanel;
	private PalletPanel palletPanel;
	private MapPanel mapPanel;
	private Random rand;
	
	private IEntityType select_model;
	private Coordinate selectPosition;
	

	public ScenarioEditorGui(Coordinate central_point) {
		this.rand = new Random();
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
		mapPanel=new MapPanel(this);
		//set center area
		mapPanel.configure(central_point, this);
		mainFrame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		
		//SET BOTTOM
		editorPanel=new EditorPanel(this);
		editorPanel.configure();
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

	public void setSelectPosition(Coordinate geo) {
		this.selectPosition=geo;
		if (this.select_model!=null)
			updateMap("add", null);
	}
	
	public void updateMap(String ops, String entityUIID) {
		if (ops.equals("add")) {
			String entUIName = mapPanel.updateEntity(this.select_model, this.selectPosition);
			String id= select_model.getId()+rand.nextInt()%1000;
			String force = select_model.getClassType();
			
			Entity entity = new Entity(id,entUIName, "BLUE", force,"Neutral",selectPosition,select_model );
			editorPanel.updateModel(entity);
		}
		else {
			mapPanel.deleteEntity(entityUIID);
		}
			
	}
	
	public void updateMap(Hashtable<String, Entity> entDB) {
		List<Entity>entL = new ArrayList<>(entDB.values());
		mapPanel.updateEntities(entL);

	}
	
	
	public Coordinate getCurrentPosition() {
		return this.selectPosition;
	}
	
	public IEntityType getSelectModel() {
		return this.select_model;
	}

	

}
