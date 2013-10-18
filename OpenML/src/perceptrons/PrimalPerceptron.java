package perceptrons;

import utils.JamaUtils;
import Jama.Matrix;

public class PrimalPerceptron {

	private DataSet trainingSet;

	private Matrix weight;

	private double bias = 0;

	private double r = 0;

	public PrimalPerceptron(final DataSet trainingSet) {
		setTrainingSet(trainingSet);
		setWeight(new Matrix(new double[trainingSet.getDataSet()[0]
				.getFeatures().getColumnDimension()], 1));
		calculateR();
	}
	
	/**
	 * Trains the perceptron according to Rosenblat's primal training algorithm.
	 */
	public void train() {
		boolean noError = false;
		while (!noError) {
			noError = true;
			for (int i = 0; i < getTrainingSet().getDataSet().length; i++) {
				FeatureObject trainingObject = getTrainingSet().getDataSet()[i];
				if (calculateMargin(trainingObject) <= 0) {
					setWeight(trainingObject.getFeatures()
							.times(trainingObject.getLabel()).plus(getWeight()));
					setBias(getBias() + trainingObject.getLabel()
							* Math.pow(getR(), 2));
					noError = false;
				}
			}
		}
	}

	/**
	 * Sets the R value for this data set.
	 */
	private void calculateR() {
		for (int i = 0; i < getTrainingSet().getDataSet().length; i++) {
			double currentR = getTrainingSet().getDataSet()[i].getFeatures()
					.normF();
			if (currentR > getR()) {
				setR(currentR);
			}
		}
	}

	/**
	 * @param object
	 * @return The margin for the feature vector of this object with the current
	 *         weight vector and bias value.
	 */
	private double calculateMargin(final FeatureObject object) {
		return (object.getLabel() * (JamaUtils.dotproduct(object.getFeatures(),
				getWeight()) + getBias()));
	}

	public DataSet getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(DataSet trainingSet) {
		this.trainingSet = trainingSet;
	}

	public Matrix getWeight() {
		return weight;
	}

	public void setWeight(Matrix weight) {
		this.weight = weight;
	}

	public double getBias() {
		return bias;
	}

	public void setBias(double bias) {
		this.bias = bias;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}

}
