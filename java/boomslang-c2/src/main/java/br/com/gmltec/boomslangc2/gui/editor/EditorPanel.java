package br.com.gmltec.boomslangc2.gui.editor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import br.com.gmltec.boomslangc2.gui.components.SpringUtilities;

public class EditorPanel extends JPanel {
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

	public EditorPanel(ScenarioEditorGui scenGUI) {
		super();
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
		String[] teamS = { "B", "G", "R" };
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
	
	public void updateEntityList(String entName) {
		
		
	}
	
	public void saveEntityInfo() {
		
	}
	
	public void createPlan() {
		
	}
	

}
