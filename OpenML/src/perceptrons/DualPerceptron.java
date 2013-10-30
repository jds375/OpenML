package perceptrons;

import parser.DataSet;
import parser.FeatureObject;
import utils.JamaUtils;

public class DualPerceptron {

	private DataSet trainingSet;

	private int[] alpha;

	private double bias = 0;

	private double r = 0;

	public DualPerceptron(DataSet trainingSet) {
		setTrainingSet(trainingSet);
		setAlpha(new int[trainingSet.getDataSet().length]);
		calculateR();
	}

	/**
	 * Trains the perceptron according to the Dual form.
	 */
	public void train() {
		boolean noError = false;
		while (!noError) {
			noError = true;
			for (int i = 0; i < getTrainingSet().getDataSet().length; i++) {
				FeatureObject trainingObject = getTrainingSet().getDataSet()[i];
				if (calculateMargin(trainingObject) <= 0) {
					getAlpha()[i] = getAlpha()[i] + 1;
					setBias(getBias() + trainingObject.getLabel()
							* Math.pow(getR(), 2));
					noError = false;
				}
			}
		}
	}

	/**
	 * @param object
	 * @return The margin for the feature vector of this object with the current
	 *         alpha vector and bias value.
	 */
	private double calculateMargin(final FeatureObject object) {
		double linearCombination = 0;
		for (int i = 0; i < getTrainingSet().getDataSet().length; i++) {
			FeatureObject trainingObject = getTrainingSet().getDataSet()[i];
			if (getAlpha()[i] != 0) {
				linearCombination += getAlpha()[i]
						* trainingObject.getLabel()
						* JamaUtils.dotproduct(trainingObject.getFeatures(),
								object.getFeatures());
			}
		}
		return object.getLabel() * (linearCombination + getBias());
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

	public DataSet getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(DataSet trainingSet) {
		this.trainingSet = trainingSet;
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

	public int[] getAlpha() {
		return alpha;
	}

	public void setAlpha(int[] alpha) {
		this.alpha = alpha;
	}

}
