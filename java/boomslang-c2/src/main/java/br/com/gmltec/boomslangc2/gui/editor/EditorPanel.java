package br.com.gmltec.boomslangc2.gui.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import br.com.gmltec.boomslangc2.phy.model.Entity;

public class EditorPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private ScenarioEditorGui scenGUI;

	private Hashtable<String, Entity> entDB;

	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	private JPanel btPannel;
	
	private JButton deleteBT;
	private JButton updateBT;
	

	public EditorPanel(ScenarioEditorGui scenGUI) {
		// setLayout(new SpringLayout());
		entDB = new Hashtable<>();
		this.scenGUI = scenGUI;
	}

	public void configure() {
		configureTable();
		configurePannel();
	}

	public void configureTable() {

		model = new DefaultTableModel();
		table = new JTable(model);
		model.addColumn("Entity ID");
		model.addColumn("Team");
		model.addColumn("Behavior Mode");
		model.addColumn("Force");
		model.addColumn("Coordinate");
		model.addColumn("Entity Type");

		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);

		TableColumn teamColumn = table.getColumnModel().getColumn(1);
		TableColumn behaviorModeColumn = table.getColumnModel().getColumn(2);
		TableColumn forceColumn = table.getColumnModel().getColumn(3);

		JComboBox<Object> comboTeamBox = new JComboBox<>();
		comboTeamBox.addItem("BLUE");
		comboTeamBox.addItem("RED");
		comboTeamBox.addItem("GREEN");
		teamColumn.setCellEditor(new DefaultCellEditor(comboTeamBox));

		JComboBox<Object> comboBMBox = new JComboBox<>();
		comboBMBox.addItem("Neutral");
		comboBMBox.addItem("Reactive");
		comboBMBox.addItem("Aggressive");
		behaviorModeColumn.setCellEditor(new DefaultCellEditor(comboBMBox));

		JComboBox<Object> comboForceBox = new JComboBox<>();
		comboForceBox.addItem("Air Force");
		comboForceBox.addItem("Navy Forces");
		comboForceBox.addItem("Land Forces");
		comboForceBox.addItem("Space Forces");
		comboForceBox.addItem("Cyber Forces");
		forceColumn.setCellEditor(new DefaultCellEditor(comboForceBox));

		updateModel(null);

	}

	public void updateModel(Entity entity) {
		// ashtable<String, Entity> entDB;
		model.setNumRows(0);

		if (entity != null) {
			if (entDB.get(entity.getId()) != null) {
				entDB.remove(entity.getId());
			}
			entDB.put(entity.getId(), entity);
		}

		List<Entity> entL = new ArrayList<>(entDB.values());

		for (Entity ent : entL) {
			String coordinate = ent.getPosition().toString();
			String enttype = ent.getEntType().getId();

			model.addRow(new Object[] { ent.getId(), ent.getTeam(), ent.getBehavior_mode(), ent.getForce(), coordinate,
					enttype });
		}
	}

	public void configurePannel() {
		Icon icon = new ImageIcon("src/main/resources/icons/remove.png");
		
		deleteBT = new JButton(icon);
		deleteBT.setText("Delete...");
		
		icon = new ImageIcon("src/main/resources/icons/update.png");
		updateBT = new JButton(icon);
		updateBT.setText("Update Map....");
		
		btPannel = new JPanel();
		scrollPane = new JScrollPane(table);

		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, scrollPane);

		btPannel.add(deleteBT);
		btPannel.add(updateBT);

		add(BorderLayout.SOUTH, btPannel);

		setPreferredSize(new Dimension(140, 180));
		setVisible(true);

		deleteBT.addActionListener(new BtDeleteActionListener());
		updateBT.addActionListener(new BtUpdateActionListener());

	}

	private class BtDeleteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int selectLine = -1;
			selectLine = table.getSelectedRow();
			if (selectLine >= 0) {
				String id = (String) table.getValueAt(selectLine, 0);
				Entity ent = entDB.get(id);
				entDB.remove(id);
				model.removeRow(selectLine);
				scenGUI.updateMap("del", ent.getEntityUIID());

			} else {
				JOptionPane.showMessageDialog(null, "It is required select a line.");
			}
		}
	}
	
	private class BtUpdateActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int total_number_lines = table.getRowCount();
			
			if (total_number_lines  > 0) {
				for (int i=0; i<total_number_lines;i++) {
					
					String id = (String) model.getValueAt(i, 0);
					String team = (String) model.getValueAt(i, 1);
					String behavior_mode = (String) model.getValueAt(i, 2);
					String force= (String) model.getValueAt(i, 3);
					
					Entity entity = entDB.get(id);
					entity.setTeam(team);
					entity.setBehavior_mode(behavior_mode);
					entity.setForce(force);
					
					
					entDB.remove(id);
					
					entDB.put(id, entity);
				}
				
				scenGUI.updateMap(entDB);

			} else {
				JOptionPane.showMessageDialog(null, "It is required at minimum one value.");
			}
		}
	}

}
