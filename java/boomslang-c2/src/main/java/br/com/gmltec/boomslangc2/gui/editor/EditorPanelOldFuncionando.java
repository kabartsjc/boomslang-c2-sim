package br.com.gmltec.boomslangc2.gui.editor;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import br.com.gmltec.boomslangc2.gui.components.SpringUtilities;
import br.com.gmltec.boomslangc2.phy.model.Entity;

public class EditorPanelOldFuncionando extends JPanel {
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

	private Hashtable<String, Entity> entDB;

	private boolean DEBUG = false;

	public EditorPanelOldFuncionando(ScenarioEditorGui scenGUI) {
		super(new GridLayout(1, 0));
		entDB = new Hashtable<>();
		this.scenGUI = scenGUI;
	}
	public void configureaaaa() {
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

	public void configure() {
		// setLayout(new SpringLayout());
		JTable table = new JTable(new MyEditorTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		// Create the scroll pane and add the table to it.
		JScrollPane scrollPane = new JScrollPane(table);

		// Set up column sizes.
		initColumnSizes(table);

		// Fiddle with the Sport column's cell editors/renderers.
		setUpOptionColumn(table);

		// Add the scroll pane to this panel.
		add(scrollPane);

	}

	/*
	 * This method picks good column sizes. If all column heads are wider than the
	 * column's cells' contents, then you can just use column.sizeWidthToFit().
	 */
	private void initColumnSizes(JTable table) {
		MyEditorTableModel model = (MyEditorTableModel) table.getModel();
		TableColumn column = null;
		Component comp = null;
		int headerWidth = 0;
		int cellWidth = 0;
		Object[] longValues = model.longValues;
		TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

		for (int i = 0; i < 4; i++) {
			column = table.getColumnModel().getColumn(i);

			comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
			headerWidth = comp.getPreferredSize().width;

			comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(table, longValues[i],
					false, false, 0, i);
			cellWidth = comp.getPreferredSize().width;

			if (DEBUG) {
				System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth
						+ "; cellWidth = " + cellWidth);
			}

			column.setPreferredWidth(Math.max(headerWidth, cellWidth));
		}
	}

	public void setUpOptionColumn(JTable table) {
		//Set up the editor for the sport cells.
		TableColumn teamColumn = table.getColumnModel().getColumn(1);
		TableColumn behaviorModeColumn = table.getColumnModel().getColumn(2);
		TableColumn forceColumn = table.getColumnModel().getColumn(3);
		
		JComboBox <Object> comboTeamBox = new JComboBox<>();
		comboTeamBox.addItem("BLUE");
		comboTeamBox.addItem("RED");
		comboTeamBox.addItem("GREEN");
		teamColumn.setCellEditor(new DefaultCellEditor(comboTeamBox));

		JComboBox <Object> comboBMBox = new JComboBox<>();
		comboBMBox.addItem("Neutral");
		comboBMBox.addItem("Reactive");
		comboBMBox.addItem("Aggressive");
		behaviorModeColumn.setCellEditor(new DefaultCellEditor(comboBMBox));

		JComboBox <Object> comboForceBox = new JComboBox<>();
		comboForceBox.addItem("Air Force");
		comboForceBox.addItem("Navy Forces");
		comboForceBox.addItem("Land Forces");
		comboForceBox.addItem("Space Forces");
		comboForceBox.addItem("Cyber Forces");
		forceColumn.setCellEditor(new DefaultCellEditor(comboForceBox));

	}

	

	public void updateEntityList(Entity entity) {
		if (entDB.contains(entity.getId()) == false)
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
		for (Entity entry : entL) {
			this.idCB.addItem(entry.getId());
		}
		this.idCB.setSelectedItem(ent.getId());

	}

	public void saveEntityInfo() {

	}

	public void createPlan() {

	}

	class MyEditorTableModel extends AbstractTableModel {
		private String[] columnNames = { "Entity ID", "Team", "Behavior Mode", "Force" };

		private Object[][] data = {{"","","",""}};

		public final Object[] longValues = { "Jane", "Kathy", "None of the above", new Integer(20), Boolean.TRUE };

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for each
		 * cell. If we didn't implement this method, then the last column would contain
		 * text ("true"/"false"), rather than a check box.
		 */
		public Class<? extends Object> getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 1) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can change.
		 */
		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;
			fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

}
