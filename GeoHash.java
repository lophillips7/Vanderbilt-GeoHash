package edu.vanderbilt.cs.live2;



public class GeoHash {

	/**
	 * This live session will focus on basic Java and some concepts important to
	 * functional programming, such as recursion.
	 *
	 * This class uses a main() method where we can write our own simple "experiments" to
	 * test how our code works. You are encouraged to modify the main method to play around
	 * with your code and understand it. When you have working code, you can extract it
	 * into a method. When you have working examples with assertions, you can extract them
	 * into tests.
	 *
	 * I have left some sample experiments in main() to help you understand the geohash
	 * algorithm.
	 *
	 *
	 * This class will provide an implementation of GeoHashes:
	 *
	 * https://www.mapzen.com/blog/geohashes-and-you/
	 * https://en.wikipedia.org/wiki/Geohash
	 *
	 *
	 * GeoHash Spatial Precision:
	 *
	 * https://releases.dataone.org/online/api-documentation-v2.0.1/design/geohash.html
	 */


	public static final double[] LATITUDE_RANGE = { -90, 90 };
	public static final double[] LONGITUDE_RANGE = { -180, 180 };

	public static boolean[] geohash1DRecursion(double valueToHash, double[] valueRange, int bitsOfPrecision) {
		boolean[] hashArray = new boolean[] // Initiatlizing a boolean array to add values to and update
		len(hashArray) = bitsOfPrecision // setting a length of the boolean array equal to bitsOfPrecision
		double targetPoint = valueToHash;
		double[] bounds = {valueRange[0], valueRange[1]};
		double midpoint = (bounds[0] + bounds[1])/2;
		boolean bitValue = false;
		double j = 0; // pointer for final array
		if(targetPoint = midpoint) { // Base case
			return t;
		if(targetPoint > midpoint) {
			bounds[0] = midpoint;
			bitValue = true;
			hashArray[j] = bitValue
			if(hashArray[bitsofPrecision - 1] = null){
				j += 1;	
				return geohash1dRecursion()
					} // if
			j += 1;			
		} // if		
		else {
		bitValue = false;
		t[j] = bitValue;
		j += 1;
		if(t[bitsOfPrecision - 1] = null) {
			} // if
		} // else
		} // if
	} // method
		
	public static boolean[] geohash1DLoop(double valueToHash, double[] valueRange, int bitsOfPrecision) {
		boolean[] hashArray = new boolean[] // Initiatlizing a boolean array to add values to and update
		hashArray.length() = bitsOfPrecision // setting a length of the boolean array equal to bitsOfPrecision
		double targetPoint = valueToHash;
		double[] bounds = {valueRange[0], valueRange[1]};
		double midpoint = (bounds[0] + bounds[1])/2;
		boolean bitValue = false;
		for(i = 0, i <= bitsOfPrecision, i++) {
			bitValue = false;
			double midpoint = (bounds[0] + bounds[1])/2;
			if(targetPoint >= midpoint) {
				bitValue = true;
				bounds[0] = midpoint;
				hashArray.append(bitValue);} // if
			else {
				bitValue = false;
				bounds[1] = midpoint
				hashArray.append(bitValue);} // else
			} // for
		return hashArray;
		} // method		

	public static boolean[] geohash2D(double v1, double[] v1range, double v2, double[] v2range, int bitsOfPrecision) {
		boolean[] hashArray1 = new boolean[] // Initiatlizing a boolean array to add values to and update
		hashArray1.length() = bitsOfPrecision // setting a length of the boolean array equal to bitsOfPrecision
		double targetPoint = valueToHash;
		double[] bounds = {valueRange[0], valueRange[1]};
		double midpoint = (bounds[0] + bounds[1])/2;
		boolean bitValue = false;
		for(i = 0, i <= bitsOfPrecision, i++) {
			bitValue = false;
			double midpoint = (bounds[0] + bounds[1])/2;
			if(targetPoint >= midpoint) {
				bitValue = true;
				bounds[0] = midpoint;
				hashArray1.append(bitValue);} // if
			else {
				bitValue = false;
				bounds[1] = midpoint
				hashArray1.append(bitValue);} // else
			} // for
		boolean[] hashArray2 = new boolean[] // Initiatlizing a boolean array to add values to and update
		hashArray2.length() = bitsOfPrecision // setting a length of the boolean array equal to bitsOfPrecision
		double targetPoint = valueToHash;
		double[] bounds = {valueRange[0], valueRange[1]};
		double midpoint = (bounds[0] + bounds[1])/2;
		boolean bitValue = false;
		for(i = 0, i <= bitsOfPrecision, i++) {
			bitValue = false;
			double midpoint = (bounds[0] + bounds[1])/2;
			if(targetPoint >= midpoint) {
				bitValue = true;
				bounds[0] = midpoint;
				hashArray2.append(bitValue);} // if
			else {
				bitValue = false;
				bounds[1] = midpoint
				hashArray2.append(bitValue);} // else
			} // for
		boolean[] finalHashArray = new boolean[] // array interleaved
		p = 0; // pointer for v1 array
		t = 0; // pointer for v2 array
		finalHashArray.length() = 2 * bitsOfPrecision; // setting length
		for(z = 0, z <= bitsOfPrecision, z++) {
			if(finalHashArray[z]%2 = 0) { // append v2[j] if even, v1[j] if odd
			finalHashArray.append(hashArray2[p]);
			p++;} // if
			else {
				finalHashArray.append(hashArray1[t]);
				t++;
			} // else
			
		} // for
		} // method	

	public static boolean[] geohash(double lat, double lon, int bitsOfPrecision) {
		return geohash2D(lat, LATITUDE_RANGE, lon, LONGITUDE_RANGE, bitsOfPrecision);
	}

	// This is a helper method that will make printing out
	// geohashes easier
	
	public static String toHashString(boolean[] geohash) {
		String hashString = "";
		for (boolean b : geohash) {
			hashString += (b ? "1" : "0");
		}
		return hashString;
	}

	// This is a convenience method to make it easy to get a string of 1s and 0s for a
	// geohash
	public static String geohashString(double valueToHash, double[] valueRange, int bitsOfPrecision) {
		return toHashString(geohash1D(valueToHash,valueRange,bitsOfPrecision));
	}

	// Faux testing for now
	public static void assertEquals(String v1, String v2) {
		if(!v1.contentEquals(v2)) {
			throw new RuntimeException(v1 + " != " + v2);
		}
	}

	public static void main(String[] args) {

		// Example of hand-coding a 3-bit geohash

		// 1st bit of the geohash
		double longitude = 0.0;
		double[] bounds = {LONGITUDE_RANGE[0], LONGITUDE_RANGE[1]};
		double midpoint = (bounds[0] + bounds[1]) / 2;
		boolean bit = false;

		if (longitude >= midpoint) {
			bit = true;
			bounds[0] = midpoint;
		}
		else {
			bit = false;
			bounds[1] = midpoint;
		}

		// 2nd bit of the geohash
		boolean bit2 = false;
		midpoint = (bounds[0] + bounds[1]) / 2;
		if (longitude >= midpoint) {
			bit2 = true;
			bounds[0] = midpoint;
		}
		else {
			bit2 = false;
			bounds[1] = midpoint;
		}

		// 3rd bit of the geohash
		boolean bit3 = false;
		midpoint = (bounds[0] + bounds[1]) / 2;
		if (longitude >= midpoint) {
			bit3 = true;
			bounds[0] = midpoint;
		}
		else {
			bit3 = false;
			bounds[1] = midpoint;
		}
		// Continue this process for however many bits of precision we need...


		// Faux testing for now
		assertEquals("100", toHashString(new boolean[] {bit, bit2, bit3}));

		// If you can get the 1D geohash to pass all of these faux tests, you should be in
		// good shape to complete the 2D version.
		assertEquals("00000", geohashString(LONGITUDE_RANGE[0], LONGITUDE_RANGE, 5));
		assertEquals("00000", geohashString(LATITUDE_RANGE[0], LATITUDE_RANGE, 5));
		assertEquals("11111", geohashString(LONGITUDE_RANGE[1], LONGITUDE_RANGE, 5));
		assertEquals("11111", geohashString(LATITUDE_RANGE[1], LATITUDE_RANGE, 5));
		assertEquals("10000", geohashString(0, LONGITUDE_RANGE, 5));
		assertEquals("11000", geohashString(90.0, LONGITUDE_RANGE, 5));
		assertEquals("11100", geohashString(135.0, LONGITUDE_RANGE, 5));
		assertEquals("11110", geohashString(157.5, LONGITUDE_RANGE, 5));
		assertEquals("11111", geohashString(168.75, LONGITUDE_RANGE, 5));
		assertEquals("01111", geohashString(-1, LONGITUDE_RANGE, 5));
		assertEquals("00111", geohashString(-91.0, LONGITUDE_RANGE, 5));
		assertEquals("00011", geohashString(-136.0, LONGITUDE_RANGE, 5));
		assertEquals("00001", geohashString(-158.5, LONGITUDE_RANGE, 5));
		assertEquals("00000", geohashString(-169.75, LONGITUDE_RANGE, 5));
	}
}
