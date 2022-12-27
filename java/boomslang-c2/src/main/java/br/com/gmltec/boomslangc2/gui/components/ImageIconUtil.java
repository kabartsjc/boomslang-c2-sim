package br.com.gmltec.boomslangc2.gui.components;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;


public class ImageIconUtil {
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String name, String class_type) {
		// https://en.wikipedia.org/wiki/NATO_Joint_Military_Symbolog
		java.net.URL imgURL = null;
		
		
		//imgURL = ImageIconUtil.class.getClass().getResource("/edu/gmu/c2sim/core/gui/ico/entities/" + imgFile);
		try {
			imgURL = new File("src/main/resources/icons/"+class_type+"/"+name).toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + name);
			return null;
		}
	}


}
