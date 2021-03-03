package io.radston12.util;

import java.awt.Color;

public class ColorHelper {

	public static Color getColor(final String hex, final float opacity) 
	{
	   final float[] rgb = getRGB(hex);
	   return new Color((float) (rgb[0] / 256),(float) (rgb[1] / 256),(float) (rgb[2] / 256),opacity);
	}
	
	private static float[] getRGB(final String rgb){
	    final float[] ret = new float[3];
	    ret[0] = (Integer.valueOf( rgb.substring( 1, 3 ), 16));
	    ret[1] = Integer.valueOf( rgb.substring( 3, 5 ), 16 );
	    ret[2] = Integer.valueOf( rgb.substring( 5, 7 ), 16 );
	    return ret;
	}
	
}
