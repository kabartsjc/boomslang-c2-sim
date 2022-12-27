package br.com.gmltec.boomslangc2.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;

import br.com.gmltec.boomslangc2.gui.components.SelectionAdapter;
import br.com.gmltec.boomslangc2.gui.components.SelectionPainter;
import br.com.gmltec.boomslangc2.phy.model.geo.Coordinate;

public class MapPanel  extends  JXMapViewer{
	private static final long serialVersionUID = -1248296922602480848L;


	public void configure(Coordinate initPosition, ScenarioEditorGui mainGUI) {
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
		System.out.println(geo.getLatitude()+":"+geo.getLongitude());
		
	}

}
