package knn;

import Jama.Matrix;

class FeatureObject {
	
	private double label;
	
	private Matrix features;
	
	private double similarity;
	
	public FeatureObject(final double label, final Matrix features) {
		this.setLabel(label);
		this.setFeatures(features);
	}
	
	public FeatureObject(final Matrix features) {
		this.setFeatures(features);
	}

	public double getLabel() {
		return label;
	}

	public void setLabel(double label) {
		this.label = label;
	}

	public Matrix getFeatures() {
		return features;
	}

	public void setFeatures(Matrix features) {
		this.features = features;
	}

	protected double getSimilarity() {
		return similarity;
	}

	protected void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

}
