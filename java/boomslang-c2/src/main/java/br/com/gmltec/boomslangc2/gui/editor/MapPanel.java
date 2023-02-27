package br.com.gmltec.boomslangc2.gui.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import br.com.gmltec.boomslangc2.gui.components.EntityUI;
import br.com.gmltec.boomslangc2.gui.components.FancyWaypointRenderer;
import br.com.gmltec.boomslangc2.gui.components.SelectionAdapter;
import br.com.gmltec.boomslangc2.gui.components.SelectionPainter;
import br.com.gmltec.boomslangc2.phy.model.Entity;
import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;

public class MapPanel extends JXMapViewer {
	private static final long serialVersionUID = -1248296922602480848L;

	private EditorGui scenGUI;

	private Random random;

	private Hashtable<String, EntityUI> entDb;

	public MapPanel(EditorGui scenGUI) {
		this.scenGUI = scenGUI;
		random = new Random(System.currentTimeMillis());
	}

	public void configure(Coordinate initPosition, EditorGui mainGUI) {
		entDb = new Hashtable<>();

		final List<TileFactory> factories = new ArrayList<TileFactory>();

		TileFactoryInfo osmInfo = new OSMTileFactoryInfo();
		TileFactoryInfo veInfo = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);

		factories.add(new DefaultTileFactory(osmInfo));
		factories.add(new DefaultTileFactory(veInfo));

		TileFactory firstFactory = factories.get(0);
		setTileFactory(firstFactory);

		setZoom(10);
		setAddressLocation(initPosition);

		SelectionAdapter sa = new SelectionAdapter(this);
		SelectionPainter sp = new SelectionPainter(sa);

		configureListeners(sa, sp);

	}

	private void configureListeners(SelectionAdapter sa, SelectionPainter sp) {
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(this);
		addMouseListener(mia);
		addMouseMotionListener(mia);

		addMouseListener(new CenterMapListener(this));

		addMouseWheelListener(new ZoomMouseWheelListenerCursor(this));

		addMouseListener(sa);
		addMouseMotionListener(sa);

		setOverlayPainter(sp);
	}

	public void setPosition(GeoPosition geo) {
		System.out.println(geo.getLatitude() + ":" + geo.getLongitude());
		Coordinate coord = new Coordinate(geo);
		scenGUI.setSelectPosition(coord);

	}
	
	public void updateEntities(List<Entity> entL) {
		for (Entity ent : entL) {
			String entUID = ent.getEntityUIID();
			
			IEntityType entType = ent.getEntType();
			String team = ent.getTeam();
			String teamLetter="B";
			if (team.equals("RED"))
				teamLetter="R";
			else if (team.equals("GREEN"))
				teamLetter = "G";
			String iconName = teamLetter + "_" + entType.getClassType() + "_" + entType.getId() + ".png";
			Coordinate pos = ent.getPosition();
			
			EntityUI entUI = new EntityUI(entUID, teamLetter, entType.getId(), iconName, pos);
			
			if (entDb.get(entUID) != null) {
				entDb.remove(entUID);
			}
			entDb.put(entUID, entUI);
		}
		
		Set<EntityUI> waypoints = generateSet();
		WaypointPainter<EntityUI> waypointPainter = new WaypointPainter<EntityUI>();
		waypointPainter.setWaypoints(waypoints);

		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		waypointPainter.setRenderer(new FancyWaypointRenderer());
		painters.add(waypointPainter);

		CompoundPainter<JXMapViewer> cp = new CompoundPainter<JXMapViewer>(painters);
		cp.setCacheable(false);

		setOverlayPainter(waypointPainter);

		revalidate();
		repaint();
		
		
	}

	public String updateEntity(IEntityType entType, Coordinate pos) {
		int seq = Math.abs(random.nextInt()%10000);
		String entName = entType.getId() + "_" + seq;
		String iconName = "B" + "_" + entType.getClassType() + "_" + entType.getId() + ".png";
		EntityUI entUI = new EntityUI(entName, "B", entType.getId(), iconName, pos);
		if (entDb.get(entName) != null) {
			entDb.remove(entName);
		}
		entDb.put(entName, entUI);

		Set<EntityUI> waypoints = generateSet();
		WaypointPainter<EntityUI> waypointPainter = new WaypointPainter<EntityUI>();
		waypointPainter.setWaypoints(waypoints);

		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		waypointPainter.setRenderer(new FancyWaypointRenderer());
		painters.add(waypointPainter);

		CompoundPainter<JXMapViewer> cp = new CompoundPainter<JXMapViewer>(painters);
		cp.setCacheable(false);

		setOverlayPainter(waypointPainter);

		revalidate();
		repaint();
		return entName;
	}

	private Set<EntityUI> generateSet() {
		Set<EntityUI> entSet = new HashSet<>();
		List<EntityUI> entL = new ArrayList<>(entDb.values());
		for (EntityUI entUI : entL) {
			entSet.add(entUI);
		}
		return entSet;
	}

	public void deleteEntity(String entName) {
		entDb.remove(entName);

		Set<EntityUI> waypoints = generateSet();

		WaypointPainter<EntityUI> waypointPainter = new WaypointPainter<EntityUI>();
		waypointPainter.setWaypoints(waypoints);

		waypointPainter.setRenderer(new FancyWaypointRenderer());

		setOverlayPainter(waypointPainter);

	}

}
