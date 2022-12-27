package br.com.gmltec.boomslangc2.gui.components;

import java.awt.Component;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;

import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;



public class PalletListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	private Font font = new Font("helvitica", Font.BOLD, 10);

	private Map<String, ImageIcon> imageMap = null;
	
	public PalletListRenderer(List<IEntityType> entModelL) {
		imageMap = createImageMap(entModelL);
	}

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		label.setIcon(imageMap.get((String) value));
		label.setHorizontalTextPosition(JLabel.RIGHT);
		label.setFont(font);
		return label;
	}
	
	
	private Map<String, ImageIcon> createImageMap(List<IEntityType> entModelL) {
		Map<String, ImageIcon> map = new HashMap<>();
		
		for (IEntityType model : entModelL) {
			String id = model.getId();
			String type = model.getClassType();
			String force = "B";
			String imgName = force+"_"+type+"_"+id+".png";
			ImageIcon icon = ImageIconUtil.createImageIcon(imgName, type);
			map.put(id, icon);
		}
		return map;
	}


}
