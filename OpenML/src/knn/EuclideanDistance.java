package knn;

public class EuclideanDistance implements SimilarityMeasure {

	public EuclideanDistance() {
	}

	@Override
	public double getSimilarity(FeatureObjectSimilarity object1, FeatureObjectSimilarity object2) {
		double distance = object1.getFeatures().minus(object2.getFeatures())
				.normF();
		if (distance == 0) {
			return 0;
		} else {
			return Math.pow(distance, -1);
		}
	}
}
