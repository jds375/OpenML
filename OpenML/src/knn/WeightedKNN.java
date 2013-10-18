package knn;

import java.util.HashMap;

public class WeightedKNN extends UnweightedKNN {

	public WeightedKNN(DataSet trainingSet, int k,
			SimilarityMeasure similarityMeasure) {
		super(trainingSet, k, similarityMeasure);
	}

	@Override
	protected HashMap<Double, Double> createVoteTable() {
		HashMap<Double, Double> voteTable = new HashMap<Double, Double>();
		for (int i = 0; i < getK(); i++) {
			double kNNSimilarity = getTrainingSet().getDataSet()[getTrainingSet()
					.getDataSet().length - i - 1].getSimilarity();
			double kNNLabel = getTrainingSet().getDataSet()[getTrainingSet()
					.getDataSet().length - i - 1].getLabel();
			if (voteTable.containsKey(kNNLabel)) {
				voteTable
						.put(kNNLabel, voteTable.get(kNNLabel) + kNNSimilarity);
			} else {
				voteTable.put(kNNLabel, kNNSimilarity);
			}
		}
		return voteTable;
	}

}
