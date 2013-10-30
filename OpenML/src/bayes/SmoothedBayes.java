package bayes;

import parser.DataSet;
import parser.FeatureObject;

public class SmoothedBayes extends NaiveBayes {

	public SmoothedBayes(DataSet trainingSet) {
		super(trainingSet);
	}

	@Override
	/**
	 * Trains Naive Bayes with smoothing conditions.
	 */
	public void train() {
		calculateExampleLabels();
		addSmoothing();
		populateFeatureTables();
	}

	/**
	 * Runs a Naive Bayes Classifier while taking into account smoothing
	 * conditions.
	 */
	@Override
	public int classify(final FeatureObject testObject) {
		double positiveVote = 1;
		double negativeVote = 1;
		for (int i = 0; i < testObject.getFeatures().getColumnDimension(); i++) {
			if (getPositiveMap()[i].get(testObject.getFeatures().get(0, i)) != null) {
				positiveVote *= (getPositiveMap()[i].get(testObject
						.getFeatures().get(0, i)) / (getNumPositiveExamples() + getPositiveMap()[i]
						.size()));
			} else {
				positiveVote = 0;
			}
			if (getNegativeMap()[i].get(testObject.getFeatures().get(0, i)) != null) {
				negativeVote *= (getNegativeMap()[i].get(testObject
						.getFeatures().get(0, i)) / (getNumNegativeExamples() + getNegativeMap()[i]
						.size()));
			} else {
				negativeVote = 0;
			}
		}
		positiveVote *= (getNumPositiveExamples() / getTrainingSet()
				.getDataSet()[0].getFeatures().getColumnDimension());
		negativeVote *= (getNumNegativeExamples() / getTrainingSet()
				.getDataSet()[0].getFeatures().getColumnDimension());
		if (positiveVote >= negativeVote) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * Adds the smoothing condition by adding 1 to each count for the feature
	 * tables.
	 */
	private void addSmoothing() {
		final FeatureObject[] dataSet = getTrainingSet().getDataSet();
		for (int i = 0; i < dataSet.length; i++) {
			for (int j = 0; j < getPositiveMap().length; j++) {
				final double feature = dataSet[i].getFeatures().get(0, j);
				if (!getPositiveMap()[j].containsKey(feature)) {
					getPositiveMap()[j].put(feature, 1);
				}
				if (!getNegativeMap()[j].containsKey(feature)) {
					getNegativeMap()[j].put(feature, 1);
				}
			}

		}
	}
}
