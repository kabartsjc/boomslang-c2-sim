package br.com.gmltec.boomslangc2.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.com.gmltec.boomslangc2.gui.components.PalletListRenderer;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;
import br.com.gmltec.boomslangc2.phy.utils.EntityTypeUtils;

import java.awt.Dimension;
import java.awt.FlowLayout;

public class PalletPanel extends JPanel  implements ListSelectionListener{
private static final long serialVersionUID = -1640427669915833332L;
	
	private JList <String>list = null;
	
	private String[] iconNames = null;

	private HashMap<String,IEntityType> entTypeDb = null;
	private ScenarioEditorGui scenarioPanel;

	
	public PalletPanel(ScenarioEditorGui scenarioPanel) {
		this.scenarioPanel=scenarioPanel;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		entTypeDb = EntityTypeUtils.loadEntityTypes();
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		List<IEntityType> modelL = sorter(entTypeDb);
		
		
		PalletListRenderer modelpl = new PalletListRenderer(modelL);

    	iconNames = getEntityModelNames(modelL);
    	
    	list = new JList<>(iconNames);
    	list.setCellRenderer(modelpl);
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	list.setSelectedIndex(0);
    	list.setVisibleRowCount(30);
    	list.addListSelectionListener(this);
    	
    	JScrollPane listScrollPane = new JScrollPane(list);
    	listScrollPane.setPreferredSize(new Dimension(200, 600));
		add(listScrollPane, "3, 4, 9, 3, fill, fill");
		
	}
	
	private List<IEntityType> sorter(HashMap<String, IEntityType> entTypeDb) {
		List<IEntityType> entityNoOrderList = new ArrayList<>(entTypeDb.values());
		List<String>entNameL=new ArrayList<>();
		for (IEntityType ent : entityNoOrderList) {
			entNameL.add(ent.getId());
		}
		
		List<String> sortedList = entNameL.stream().sorted().collect(Collectors.toList());     
		
		List<IEntityType> sortedEntTypeDBOrder = new ArrayList<>();
		
		for (String entId:sortedList) {
			IEntityType entity = entTypeDb.get(entId);
			sortedEntTypeDBOrder.add(entity);
		}
		
		return sortedEntTypeDBOrder ;
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		@SuppressWarnings("unchecked")
		JList<String> list = (JList<String>) e.getSource();
		int index = list.getSelectedIndex();
		String name = iconNames[index];
		updateLabel(name);
	}
	
	protected void updateLabel(String name) {
		IEntityType model = this.entTypeDb.get(name);
		this.scenarioPanel.setSelectedEntity(model);
	}
	
	protected static String[] getEntityModelNames(List<IEntityType> entModelL) {
		String nameL[] = new String[entModelL.size()];

		for (int i = 0; i < entModelL.size(); i++) {
			IEntityType ent = entModelL.get(i);
			nameL[i] = ent.getId();
		}
		return nameL;
	}


}
