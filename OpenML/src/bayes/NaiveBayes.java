package bayes;

import java.util.HashMap;

import parser.DataSet;
import parser.FeatureObject;

public class NaiveBayes {

	private DataSet trainingSet;
	private double numPositiveExamples;
	private double numNegativeExamples;
	private HashMap<Double, Integer>[] positiveMap;
	private HashMap<Double, Integer>[] negativeMap;

	@SuppressWarnings("unchecked")
	public NaiveBayes(final DataSet trainingSet) {
		this.setTrainingSet(trainingSet);
		positiveMap = new HashMap[trainingSet.getDataSet()[0].getFeatures()
				.getColumnDimension()];
		negativeMap = new HashMap[trainingSet.getDataSet()[0].getFeatures()
				.getColumnDimension()];
		for (int i = 0; i < positiveMap.length; i++) {
			positiveMap[i] = new HashMap<Double, Integer>();
			negativeMap[i] = new HashMap<Double, Integer>();
		}
	}

	/**
	 * @param testObject
	 * @return The label predicted by the Naive Bayes classifier. If the
	 *         probability of a 1 and -1 are equal, it will output a 1.
	 */
	public int classify(final FeatureObject testObject) {
		double positiveVote = 1;
		double negativeVote = 1;
		for (int i = 0; i < testObject.getFeatures().getColumnDimension(); i++) {
			if (positiveMap[i].get(testObject.getFeatures().get(0, i)) != null) {
				positiveVote *= (positiveMap[i].get(testObject.getFeatures()
						.get(0, i)) / numPositiveExamples);
			} else {
				positiveVote = 0;
			}
			if (negativeMap[i].get(testObject.getFeatures().get(0, i)) != null) {
				negativeVote *= (negativeMap[i].get(testObject.getFeatures()
						.get(0, i)) / numNegativeExamples);
			} else {
				negativeVote = 0;
			}
			if ((positiveVote == 0) || (negativeVote == 0)) {
				break;
			}
		}
		positiveVote *= (numPositiveExamples
				/ trainingSet.getDataSet()[0].getFeatures()
						.getColumnDimension());
		negativeVote *= (numNegativeExamples
				/ trainingSet.getDataSet()[0].getFeatures()
						.getColumnDimension());
		if (positiveVote >= negativeVote) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Trains the Naive Bayes model.
	 */
	public void train() {
		calculateExampleLabels();
		populateFeatureTables();
	}

	/**
	 * Goes through the training set and fills up the lookup table for how many
	 * times a specific feature appears given a label. This allows us to look up
	 * #(X_i=x_i,y) for y=+1 or y=-1.
	 */
	protected void populateFeatureTables() {
		final FeatureObject[] dataSet = getTrainingSet().getDataSet();
		for (int i = 0; i < dataSet.length; i++) {
			for (int j = 0; j < positiveMap.length; j++) {
				final double feature = dataSet[i].getFeatures().get(0, j);
				if (dataSet[i].getLabel() == 1) {
					if (positiveMap[j].containsKey(feature)) {
						positiveMap[j].put(feature,
								positiveMap[j].get(feature) + 1);
					} else {
						positiveMap[j].put(feature, 1);
					}
				} else {
					if (negativeMap[j].containsKey(feature)) {
						negativeMap[j].put(feature,
								negativeMap[j].get(feature) + 1);
					} else {
						negativeMap[j].put(feature, 1);
					}
				}
			}
		}
	}

	/**
	 * Calculates the values for numPositiveExamples and numNegativeExamples.
	 */
	protected void calculateExampleLabels() {
		final FeatureObject[] dataSet = getTrainingSet().getDataSet();
		for (int i = 0; i < dataSet.length; i++) {
			if (dataSet[i].getLabel() == 1) {
				numPositiveExamples++;
			} else {
				numNegativeExamples++;
			}
		}
		assert ((numPositiveExamples != 0) && (numNegativeExamples != 0));
	}

	public DataSet getTrainingSet() {
		return trainingSet;
	}

	public void setTrainingSet(DataSet trainingSet) {
		this.trainingSet = trainingSet;
	}

	public double getNumPositiveExamples() {
		return numPositiveExamples;
	}

	public void setNumPositiveExamples(double numPositiveExamples) {
		this.numPositiveExamples = numPositiveExamples;
	}

	public double getNumNegativeExamples() {
		return numNegativeExamples;
	}

	public void setNumNegativeExamples(double numNegativeExamples) {
		this.numNegativeExamples = numNegativeExamples;
	}
	
	public HashMap<Double, Integer>[] getPositiveMap() {
		return positiveMap;
	}

	public void setPositiveMap(HashMap<Double, Integer>[] positiveMap) {
		this.positiveMap = positiveMap;
	}

	public HashMap<Double, Integer>[] getNegativeMap() {
		return negativeMap;
	}

	public void setNegativeMap(HashMap<Double, Integer>[] negativeMap) {
		this.negativeMap = negativeMap;
	}
}
