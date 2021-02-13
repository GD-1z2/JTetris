package fr.caemur.jtetris;

import java.util.Random;

public class Gradient {
	private static final Random rand = new Random();
	
	/**
	 * Generate gradient
	 * @return float[6] {r1, g1, b1, r2, g2, b2}
	 */
	public static float[] getGradient() {
		float[] gradient = new float[6];
		
		//first color
		if (rand.nextBoolean()) { //one color
			gradient[0] = rand.nextFloat()/3;
			gradient[1] = rand.nextFloat()/3;
			gradient[2] = rand.nextFloat()/3;
			
			gradient[rand.nextInt(3)] = rand.nextFloat()/2+.5f;
		} else { //mix two colors
			gradient[0] = rand.nextFloat()/3;
			gradient[1] = rand.nextFloat()/3;
			gradient[2] = rand.nextFloat()/3;
			gradient[rand.nextInt(3)] = rand.nextFloat();
			gradient[rand.nextInt(3)] = rand.nextFloat();
		}
		
		// 2nd color
		if (rand.nextBoolean()) { //lighter
			gradient[3] = gradient[0]*2;
			gradient[4] = gradient[1]*2;
			gradient[5] = gradient[2]*2;
		} else { //darker
			gradient[3] = gradient[0]*.5f;
			gradient[4] = gradient[1]*.5f;
			gradient[5] = gradient[2]*.5f;
		}
		
		return gradient;
	}
}
