package br.com.gmltec.boomslangV2.core.utils;

import org.geotools.referencing.GeodeticCalculator;

import br.com.gmltec.boomslangV2.core.geo.Coordinate;


public class GeoUtils {
	
	public static double calculateBearing(Coordinate start_location, Coordinate end_location) {
		double bearing = 0;

		GeodeticCalculator calc = new GeodeticCalculator();
		double long1 = start_location.getLongitude();
		double lat1 = start_location.getLatitude();
		
		calc.setStartingGeographicPoint(long1,lat1);
		
		double long2 = end_location.getLongitude();
		double lat2 = end_location.getLatitude();
	
		calc.setDestinationGeographicPoint(long2,lat2);
		bearing = calc.getAzimuth();

		return bearing;
	}
	
	public static boolean isSamePosition(Coordinate currentPos, Coordinate newPos, 
			double hor_error, double vert_error) {
		double new_altitude = newPos.getAltitude();
		
		double curr_altitude = currentPos.getAltitude();
		
		double hor_distance = calculateHorizontalDistanceMeters(currentPos, newPos);
		double vert_dstance = Math.abs(curr_altitude -new_altitude);
		
		if (hor_distance>=hor_error || vert_dstance>=2*vert_error)
			return true;
		
		else 
			return false;
	}
	
	
	
	/**
	 * Returns the destination point from a given point, having travelled the given
	 * distance on the given initial bearing.
	 *
	 * @param {number} lat - initial latitude in decimal degrees (eg. 50.123)
	 * @param {number} lon - initial longitude in decimal degrees (e.g. -4.321)
	 * @param {number} distance - Distance travelled (metres).
	 * @param {number} bearing - Initial bearing (in degrees from north).
	 * @returns {array} destination point as [latitude,longitude] (e.g. [50.123,
	 *          -4.321])
	 *
	 * @example var p = destinationPoint(51.4778, -0.0015, 7794, 300.7); //
	 *          51.5135°N, 000.0983°W
	 */
	public static Coordinate calculateNewPosition(Coordinate start_pos, double distance_mt_horiz,
			double distance_mt_vertical, double bearing_dg_from_north) {
		/**
		 * https://stackoverflow.com/questions/19352921/how-to-use-direction-angle-and-speed-to-calculate-next-times-latitude-and-longi
		 */
		// var radius = 6371e3; // (Mean) radius of earth
		double radius = 6371e3; // (Mean) radius of earth

		// sinφ2 = sinφ1·cosδ + cosφ1·sinδ·cosθ
		// tanΔλ = sinθ·sinδ·cosφ1 / cosδ−sinφ1·sinφ2
		// see mathforum.org/library/drmath/view/52049.html for derivation

		// var φ1 = toRadians(Number(lat));
		double lat_1_radians = Math.toRadians(start_pos.getLatitude());

		// var sinφ1 = Math.sin(φ1), cosφ1 = Math.cos(φ1);
		double var_sin_lat_1 = Math.sin(lat_1_radians);
		double var_cos_lat_1 = Math.cos(lat_1_radians);

		// var λ1 = toRadians(Number(lon));
		double long_1_radians = Math.toRadians(start_pos.getLongitude());

		// var δ = Number(distance) / radius; // angular distance in radians
		double ang_dist_radians = distance_mt_horiz / radius;

		// var sinδ = Math.sin(δ), cosδ = Math.cos(δ);
		double var_sin_ang_dist = Math.sin(ang_dist_radians);
		double var_cos_ang_dist = Math.cos(ang_dist_radians);

		// var θ = toRadians(Number(bearing));
		double bearing_radians = Math.toRadians(bearing_dg_from_north);

		// var sinθ = Math.sin(θ), cosθ = Math.cos(θ);
		double var_sin_bear_radians = Math.sin(bearing_radians);
		double var_cos_bear_radians = Math.cos(bearing_radians);

		// var sinφ2 = sinφ1*cosδ + cosφ1*sinδ*cosθ;
		double var_sin_lat2 = var_sin_lat_1 * var_cos_ang_dist
				+ var_cos_lat_1 * var_sin_ang_dist * var_cos_bear_radians;

		// var φ2 = Math.asin(sinφ2);
		double lat2 = Math.asin(var_sin_lat2);

		// var y = sinθ * sinδ * cosφ1;
		double y = var_sin_bear_radians * var_sin_ang_dist * var_cos_lat_1;

		// var x = cosδ - sinφ1 * sinφ2;
		double x = var_cos_ang_dist - var_sin_lat_1 * var_sin_lat2;

		// var λ2 = λ1 + Math.atan2(y, x);
		double long2 = long_1_radians + Math.atan2(y, x);

		// return [toDegrees(φ2), (toDegrees(λ2)+540)%360-180]; // normalise to
		// −180..+180°

		double final_alt = start_pos.getAltitude() + distance_mt_vertical;
		Coordinate result = new Coordinate(Math.toDegrees(lat2), ((Math.toDegrees(long2) + 540) % 360 - 180),
				final_alt);

		return result;
	}
	
	public static double calculateHorizontalDistanceMeters(Coordinate start_pos, Coordinate end_pos) {
		double dist = 0;
		double lat1 = start_pos.getLatitude();
		double lon1 = start_pos.getLongitude();
		double lat2 = end_pos.getLatitude();
		double lon2 = end_pos.getLongitude();

		// distance between latitudes and longitudes
		double dLat = Math.toRadians(lat2 - lat1);// var Δφ = (lat2-lat1).toRadians();
		double dLon = Math.toRadians(lon2 - lon1); // var Δλ = (lon2-lon1).toRadians();

		// convert to radians
		lat1 = Math.toRadians(lat1); // var φ1 = lat1.toRadians();
		lat2 = Math.toRadians(lat2); // var φ2 = lat2.toRadians();

		// Math.sin(Δφ/2) * Math.sin(Δφ/2) +
		double aJ = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
		// Math.cos(φ1) * Math.cos(φ2) *
				Math.cos(lat1) * Math.cos(lat2) *
				// Math.sin(Δλ/2) * Math.sin(Δλ/2);
						Math.sin(dLon / 2) * Math.sin(dLon / 2);
		// var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double cJ = 2 * Math.atan2(Math.sqrt(aJ), Math.sqrt(1 - aJ));
		double radius = 6371e3; // (Mean) radius of earth

		dist = radius * cJ;

		return dist;
	}

}
