package knn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class UnweightedKNN {

	private final DataSet trainingSet;
	private int k;
	private final SimilarityMeasure similarityMeasure;

	public UnweightedKNN(final DataSet trainingSet, final int k,
			final SimilarityMeasure similarityMeasure) {
		this.trainingSet = trainingSet;
		this.k = k;
		this.similarityMeasure = similarityMeasure;
	}

	/**
	 * @param testObject
	 * @return The predicted label for a given test instance.
	 */
	public double classify(final FeatureObject testObject) {
		computeSimilarities(testObject);
		sortByMostSimilar();
		return predictedLabel(createVoteTable());
	}
	
	/**
	 * @param voteTable
	 * @return The label in the vote table with the most votes.
	 */
	private double predictedLabel(final HashMap<Double, Double> voteTable) {
		Iterator<Entry<Double, Double>> tableIterator = voteTable.entrySet().iterator();
		double bestLabel = 0;
		double mostVotes = 0;
		while (tableIterator.hasNext()) {
			Entry<Double, Double> labelVotePair = tableIterator.next();
			if (labelVotePair.getValue() > mostVotes) {
				bestLabel = labelVotePair.getKey();
				mostVotes = labelVotePair.getValue();
			}
		}
		return bestLabel;
	}

	/**
	 * @return A hash-table populated with the labels of the k nearest neighbors
	 *         as keys, which are mapped to the number of times they appear
	 *         amongst the k nearest neighbors.
	 */
	protected HashMap<Double, Double> createVoteTable() {
		HashMap<Double, Double> voteTable = new HashMap<Double, Double>();
		for (int i = 0; i < k; i++) {
			double kNNLabel = trainingSet.getDataSet()[trainingSet.getDataSet().length
					- i - 1].getLabel();
			if (voteTable.containsKey(kNNLabel)) {
				voteTable.put(kNNLabel, voteTable.get(kNNLabel) + 1);
			} else {
				voteTable.put(kNNLabel, 1.0);
			}
		}
		return voteTable;
	}

	/**
	 * Sorts the training set so that it is in ascending order.
	 */
	protected void sortByMostSimilar() {
		Arrays.sort(trainingSet.getDataSet(), new Comparator<FeatureObject>() {
			@Override
			public int compare(FeatureObject arg0, FeatureObject arg1) {
				if (arg0.getSimilarity() > arg1.getSimilarity()) {
					return 1;
				} else if (arg0.getSimilarity() < arg1.getSimilarity()) {
					return -1;
				} else {
					return 0;
				}
			}
		});
	}

	/**
	 * Given a test object calculates the similarity between the test object and
	 * all training examples according to the specified similarity function.
	 * Each training object is then assigned this similarity value.
	 * 
	 * @param testObject
	 */
	protected void computeSimilarities(final FeatureObject testObject) {
		for (int i = 0; i < trainingSet.getDataSet().length; i++) {
			FeatureObject trainingObject = trainingSet.getDataSet()[i];
			double similarity = similarityMeasure.getSimilarity(trainingObject,
					testObject);
			trainingObject.setSimilarity(similarity);
		}
	}

	public DataSet getTrainingSet() {
		return trainingSet;
	}

	public int getK() {
		return k;
	}
	
	public void setK(final int k) {
		this.k = k;
	}

	public SimilarityMeasure getSimilarityMeasure() {
		return similarityMeasure;
	}
}
