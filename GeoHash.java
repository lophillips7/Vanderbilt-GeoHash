/* An attempt to solve the first coding problem from Vanderbilt's M.S. in Comp Sci program using both for loops and recursion 
 * Lowranche Phillips
 */

package edu.vanderbilt.cs.live2;
import static edu.vanderbilt.cs.live2.GeoHash.LATITUDE_RANGE;
import static edu.vanderbilt.cs.live2.GeoHash.LONGITUDE_RANGE;
import static edu.vanderbilt.cs.live2.GeoHash.assertEquals;
import static edu.vanderbilt.cs.live2.GeoHash.geohash;
import static edu.vanderbilt.cs.live2.GeoHash.toHashString;

import java.io.*;


public class GeoHash {

	/**
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

	public static boolean[] geohash1DLoop(double valueToHash, double[] valueRange, int bitsOfPrecision) {
		
		boolean[] hashArray = new boolean[bitsOfPrecision]; // Initiatlizing a boolean array to add values to and update
		double targetPoint = valueToHash;
		double[] bounds = {valueRange[0], valueRange[valueRange.length - 1]};
		double midpoint = (bounds[0] + bounds[1])/2;
		boolean bitValue = false;
		
		for(int i = 0; i < bitsOfPrecision; i++) {
			bitValue = false;
			midpoint = (bounds[0] + bounds[1])/2;
			if(targetPoint >= midpoint) {
				bitValue = true;
				bounds[0] = midpoint;
				hashArray[i] = bitValue;} // if
			else {
				bitValue = false;
				bounds[1] = midpoint;
				hashArray[i] = bitValue;} // else
			} // for
		return hashArray;
		} // method		

	public static boolean[] geohash2DLoop(double v1, double[] v1range, double v2, double[] v2range, int bitsOfPrecision) {
		
		boolean[] hashArray1 = new boolean[bitsOfPrecision/2 + 1]; // initialize a boolean array to add values to and update
		boolean[] hashArray2 = new boolean[bitsOfPrecision/2]; // initialize a boolean array2 to add values to and update	
		double targetPoint1 = v1;
		double targetPoint2 = v2;
		double[] bound1 = {v1range[0], v1range[1]};
		double[] bound2 = {v2range[0], v2range[1]};
		double midpoint1 = (bound1[0] + bound1[1])/2;
		double midpoint2 = (bound2[0] + bound2[1])/2;
		boolean bitValue = false;
		
		if(bitsOfPrecision % 2 != 0) {
			for(int i = 0; i <= bitsOfPrecision/2; i++) {
				bitValue = false;
				midpoint1 = (bound1[0] + bound1[1])/2;
				if(targetPoint1 >= midpoint1) {
					bitValue = true;
					bound1[0] = midpoint1;
					hashArray1[i] = bitValue;} // if
				else {
					bitValue = false;
					bound1[1] = midpoint1;
					hashArray1[i] = bitValue;} // else
				} // for
			bitValue = false;
		} // if
		else {
		// first array
			for(int i = 0; i < bitsOfPrecision/2 ; i++) {
				bitValue = false;
				midpoint1 = (bound1[0] + bound1[1])/2;
				if(targetPoint1 >= midpoint1) {
					bitValue = true;
					bound1[0] = midpoint1;
					hashArray1[i] = bitValue;} // if
				else {
					bitValue = false;
					bound1[1] = midpoint1;
					hashArray1[i] = bitValue;} // else
				} // for
				bitValue = false;
				} // else
		
		// second array
		for(int i = 0; i < bitsOfPrecision/2; i++) {
			bitValue = false;
			midpoint2 = (bound2[0] + bound2[1])/2;
			if(targetPoint2 >= midpoint2) {
				bitValue = true;
				bound2[0] = midpoint2;
				hashArray2[i] = bitValue;} // if
			else {
				bitValue = false;
				bound2[1] = midpoint2;
				hashArray2[i] = bitValue;} // else
			} // for
		
		// making final array
		boolean[] finalHashArray = new boolean[bitsOfPrecision]; // array interleaved
		int t = 0; // pointer for v1 array
		int p = 0; // pointer for v2 array
		
		//combining arrays
		for(int z = 0; z < bitsOfPrecision; z++) {
			if(z%2 == 0) { // append v2[j] if even, v1[j] if odd
			finalHashArray[z] = hashArray1[p];
			p++;} // if
			else {
				finalHashArray[z] = hashArray2[t];
				t++;
			} // else	
						
		} // for
		return finalHashArray;
		} // method	

	public static boolean[] geohash(double lat, double lon, int bitsOfPrecision) {
		return geohash2DLoop(lat, LATITUDE_RANGE, lon, LONGITUDE_RANGE, bitsOfPrecision);
	}

	// This is a helper method that will make printing out
	// geohashes easier
	
	public static String toHashString(boolean[] geohash) {
		String hashString = "";
		for (boolean b : geohash) {
			hashString += (b ? "1" : "0");
		}
		System.out.println(hashString);
		return hashString;
	}

	// This is a convenience method to make it easy to get a string of 1s and 0s for a
	// geohash
	public static String geohashString(double valueToHash, double[] valueRange, int bitsOfPrecision) {
		return toHashString(geohash1DLoop(valueToHash,valueRange,bitsOfPrecision));
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
		
		// 2d testing
        assertEquals("0000000000", toHashString(geohash(LATITUDE_RANGE[0], LONGITUDE_RANGE[0], 10)));
        assertEquals("0101010101", toHashString(geohash(LATITUDE_RANGE[0], LONGITUDE_RANGE[1], 10)));
        assertEquals("01010101010", toHashString(geohash(LATITUDE_RANGE[0], LONGITUDE_RANGE[1], 11)));
        assertEquals("01010101010", toHashString(geohash(LATITUDE_RANGE[0], LONGITUDE_RANGE[1], 11)));
        assertEquals("1010101011", toHashString(geohash(LATITUDE_RANGE[1], -158.5, 10)));
        assertEquals("10101010111", toHashString(geohash(LATITUDE_RANGE[1], -158.5, 11)));
        assertEquals("10101010111111", toHashString(geohash(LATITUDE_RANGE[1], -158.5, 14)));
        assertEquals("11111111111111", toHashString(geohash(LATITUDE_RANGE[1], LONGITUDE_RANGE[1], 14)));

	}
	}

