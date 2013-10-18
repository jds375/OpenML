package knn;

public class EuclideanDistance implements SimilarityMeasure {

	public EuclideanDistance() {
	}

	@Override
	public double getSimilarity(FeatureObject object1, FeatureObject object2) {
		double distance = object1.getFeatures().minus(object2.getFeatures())
				.normF();
		if (distance == 0) {
			return 0;
		} else {
			return Math.pow(distance, -1);
		}
	}
}
