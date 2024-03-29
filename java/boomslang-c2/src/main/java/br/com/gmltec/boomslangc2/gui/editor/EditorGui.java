package br.com.gmltec.boomslangc2.gui.editor;

import br.com.gmltec.boomslangc2.phy.model.Entity;
import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;
import br.com.gmltec.boomslangc2.phy.utils.MissionUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class EditorGui {
	private JFrame mainFrame;
	private ExercisePanel exercisePanel;
	private EntitySelectionPanel entitySelectionPanel;
	private EntityPalletPanel entityPalletPanel;
	private MapPanel mapPanel;
	private Random rand;
	
	private IEntityType select_model;
	private Coordinate selectPosition;
	

	public EditorGui(Coordinate central_point) {
		this.rand = new Random();
		initialize(central_point);
	}

	public static void main(String[] args) {
		Coordinate central_point= new Coordinate(1.863707, 44.836479, 0);
		EditorGui mg = new EditorGui(central_point);
		mg.createAndShowGUI();

	}

	private void initialize(Coordinate central_point) {
		mainFrame = new JFrame();
		mainFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
		mainFrame.setTitle("Boomslang C2 Simulator");
		// frame.setBounds(100, 100, 893, 582);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//SET TOP
		exercisePanel=new ExercisePanel(this);
		exercisePanel.configure();
		exercisePanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Exercise Panel Definition"));
		mainFrame.getContentPane().add(exercisePanel, BorderLayout.NORTH);
		
		//SET WEST
		entityPalletPanel=new EntityPalletPanel(this);
		//entPanel.configure();
		entityPalletPanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Entity Pallete"));
		
		mainFrame.getContentPane().add(entityPalletPanel, BorderLayout.WEST);
		
		
		//SET CENTER
		mapPanel=new MapPanel(this);
		//set center area
		mapPanel.configure(central_point, this);
		mainFrame.getContentPane().add(mapPanel, BorderLayout.CENTER);
		
		//SET BOTTOM
		entitySelectionPanel=new EntitySelectionPanel(this);
		entitySelectionPanel.configure();
		entitySelectionPanel.setBorder(
	            BorderFactory.createTitledBorder(
	            BorderFactory.createEtchedBorder(
	                    EtchedBorder.RAISED, Color.GRAY
	                    , Color.DARK_GRAY), "Entity Selection Panel"));
		mainFrame.getContentPane().add(entitySelectionPanel, BorderLayout.SOUTH);
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
			entitySelectionPanel.updateModel(entity);
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

	public void persistMission(String exeName, String simuDurationStr) {
		String[] result = simuDurationStr.split(":");
		try {
			long simuH = Long.parseLong(result[0]);
			long simuM = Long.parseLong(result[1]);
			
			long duration_time_sec = simuH*60*60 + simuM*60;
			
			List<Entity>entList = entitySelectionPanel.getInformations();
			
			boolean result_persist = MissionUtils.persistMission(exeName, duration_time_sec, entList);
			
			
			if (result_persist==true) {
				JOptionPane.showMessageDialog(null, "Nice Job - Mission Saved.","Boomslang C2 System", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else {
				JOptionPane.showMessageDialog(null, "An error happens, please contact the maintenance!","Boomslang C2 System", JOptionPane.ERROR_MESSAGE);
			}
			
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "An error happens, please contact the maintenance!","Boomslang C2 System", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		
		
	}

	

}
