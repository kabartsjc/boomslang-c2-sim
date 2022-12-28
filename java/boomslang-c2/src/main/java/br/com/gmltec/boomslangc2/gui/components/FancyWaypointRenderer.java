/*
 * WaypointRenderer.java
 *
 * Created on March 30, 2006, 5:24 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.com.gmltec.boomslangc2.gui.components;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointRenderer;



/**
 * A fancy waypoint painter
 * @author Martin Steiger
 */
public class FancyWaypointRenderer implements  WaypointRenderer<EntityUI>
{
    private static final Log log = LogFactory.getLog(FancyWaypointRenderer.class);

    /**
     * Uses a default waypoint image
     */
    public FancyWaypointRenderer()
    {
    
    }

    @Override
    public void paintWaypoint(Graphics2D g, JXMapViewer viewer, EntityUI w)
    {
        g = (Graphics2D)g.create();
        URL imgURL = null;
        
        try {
			imgURL =  new File("src/main/resources/icons/" + w.getIconName()).toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
        if (imgURL == null)
            return;

        BufferedImage origImage=null;
		try {
			origImage = ImageIO.read(imgURL);
		} catch (IOException ex) {
			log.warn("couldn't read waypoint_white.png", ex);
			return;
		}
        
        Point2D point = viewer.getTileFactory().geoToPixel(w.getPosition(), viewer.getZoom());

        int x = (int)point.getX();
        int y = (int)point.getY();

        g.drawImage(origImage, x -origImage.getWidth() / 2, y -origImage.getHeight(), null);
                     
        g.dispose();
    }

    
}
