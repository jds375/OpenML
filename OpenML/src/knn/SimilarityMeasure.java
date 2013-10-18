package knn;

public interface SimilarityMeasure {

	/**
	 * Computes the similarity between two objects according to their feature
	 * vectors.
	 * @param object1 - An object to compare against.
	 * @param object2 - An object to compare against.
	 * @return The similarity between the two objects.
	 */
	public double getSimilarity(final FeatureObject object1,
			final FeatureObject object2);

}
