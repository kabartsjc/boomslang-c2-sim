package br.com.gmltec.boomslang.core.simu;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

public class DistributionUtils {
	private AbstractRealDistribution distribution;
	
	
	
	public void configureNormalDistrUtils(double MEAN_HEIGHT, double STANDARD_DEVIATION) {
		distribution =  new NormalDistribution(MEAN_HEIGHT, STANDARD_DEVIATION);
	}
	
	public double calculateProbability(double heightLowerExclusive) {
		double sample = distribution.sample();
		
		double heightUpperInclusive = sample;
		if (heightLowerExclusive>= sample) {
			heightUpperInclusive = heightLowerExclusive;
			heightLowerExclusive = sample;
		}
				
		return distribution.cumulativeProbability(heightLowerExclusive, heightUpperInclusive);
	}

	
	
	
	public static void main (String args[]) {
		
		DistributionUtils distUtils = new DistributionUtils();
		distUtils.configureNormalDistrUtils(50.0, 7.11);
		for (int i=0; i<100; i++) {
			double values = distUtils.calculateProbability(i);
			System.out.println("i="+i + " - values=" + values);
		}
		
		
		
	
	}

}
