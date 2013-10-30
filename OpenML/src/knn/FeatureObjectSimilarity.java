package knn;

import Jama.Matrix;
import parser.FeatureObject;

public class FeatureObjectSimilarity extends FeatureObject {

	private double similarity;

	public FeatureObjectSimilarity(double label, Matrix features) {
		super(label, features);
	}
	
	public FeatureObjectSimilarity(Matrix features) {
		super(features);
	}

	protected double getSimilarity() {
		return similarity;
	}

	protected void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
