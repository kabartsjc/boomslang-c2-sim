package br.com.gmltec.boomslangc2.gui.editor;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import br.com.gmltec.boomslangc2.gui.components.SpringUtilities;
import br.com.gmltec.boomslangc2.phy.model.Entity;

public class EditorPanelOld extends JPanel {
	private ScenarioEditorGui scenGUI;

	private JLabel idLabel;
	private JComboBox idCB;

	private JLabel teamLabel;
	private JComboBox<String> teamCB;

	private JLabel behaviorModeLabel;
	private JComboBox<String> behaviorModeCB;

	private JLabel forceLabel;
	private JComboBox<String> forceCB;

	private JButton createPlanBT;
	private JButton saveBT;
	
	private Hashtable <String, Entity>entDB;

	public EditorPanelOld(ScenarioEditorGui scenGUI) {
		super();
		entDB = new Hashtable<>();
		this.scenGUI = scenGUI;
	}

	public void coinfigureN() {
		setLayout(new SpringLayout());

	}

	public void configure() {
		setLayout(new SpringLayout());
		
		

		idLabel = new JLabel("Entity ID:");
		add(idLabel);

		String[] idList = {};
		idCB = new JComboBox<>(idList);
		add(idCB);

		teamLabel = new JLabel("Team:");
		String[] teamS = { "BLUE", "GREEN", "RED" };
		add(teamLabel);

		teamCB = new JComboBox<>(teamS);
		add(teamCB);

		behaviorModeLabel = new JLabel("Behavior Mode:");
		String behModS[] = { "neutral", "reactive", "aggressive" };
		add(behaviorModeLabel);

		behaviorModeCB = new JComboBox<String>(behModS);
		add(behaviorModeCB);

		forceLabel = new JLabel("Force");
		add(forceLabel);

		String forceS[] = { "Air Force", "Army", "Navy", "Cyber Forces", "Space Forces" };
		forceCB = new JComboBox<String>(forceS);
		add(forceCB);

		Icon icon = new ImageIcon("src/main/resources/icons/plan_icon.png");
		createPlanBT = new JButton(icon);
		createPlanBT.setText("create plan...");

		icon = new ImageIcon("src/main/resources/icons/save.png");
		saveBT = new JButton(icon);
		saveBT.setText("save ...");

		add(saveBT);
		add(createPlanBT);

		SpringUtilities.makeCompactGrid(this, 5, 2, 2, 2, 2, 2);

	}
	
	public void updateEntityList(Entity entity) {
		if (entDB.contains(entity.getId())==false)
				entDB.put(entity.getId(), entity);
		else {
			entDB.remove(entity.getId());
			entDB.put(entity.getId(), entity);
		}
		loadEntity(entity);
	}
	
	@SuppressWarnings("unchecked")
	private void loadEntity(Entity ent) {
		this.idCB.removeAllItems();
		
		List<Entity> entL = new ArrayList<>(this.entDB.values());
		for (Entity entry:entL) {
			this.idCB.addItem(entry.getId());
		}
		this.idCB.setSelectedItem(ent.getId());
		
	}
	
	public void saveEntityInfo() {
	
	}
	
	public void createPlan() {
		
	}
	

}
