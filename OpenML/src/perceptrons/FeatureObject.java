package perceptrons;

import Jama.Matrix;

class FeatureObject {
	
	private double label;
	
	private Matrix features;
	
	public FeatureObject(final double label, final Matrix features) {
		assert((label == 1) || (label == -1));
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
}
