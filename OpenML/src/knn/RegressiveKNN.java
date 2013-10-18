package knn;

public class RegressiveKNN extends UnweightedKNN {

	public RegressiveKNN(DataSet trainingSet, int k,
			SimilarityMeasure similarityMeasure) {
		super(trainingSet, k, similarityMeasure);
	}

	@Override
	public double classify(final FeatureObject testObject) {
		computeSimilarities(testObject);
		sortByMostSimilar();
		return predictedLabel();
	}

	/**
	 * @return The predicted label using regression.
	 */
	public double predictedLabel() {
		double numerator = 0;
		double denominator = 0;
		for (int i = 0; i < getK(); i++) {
			double kNNSimilarity = getTrainingSet().getDataSet()[getTrainingSet()
					.getDataSet().length - i - 1].getSimilarity();
			double kNNLabel = getTrainingSet().getDataSet()[getTrainingSet()
					.getDataSet().length - i - 1].getLabel();
			numerator += kNNLabel * kNNSimilarity;
			denominator += kNNSimilarity;
		}
		return numerator / denominator;
	}

}
