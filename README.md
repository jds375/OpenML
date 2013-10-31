OpenML
======
An open source project for building Machine Learning algorithms, specifically in Java.
<br>
<br>
INPUT:
<br>
Input is in modified SVM-light format. In this modified format all feature vectors must be the same length and have an entry for every point in the feature vector. Thus, "-1 0:4 1:0 2:4" and "1 0:4 2:3" would not be valid inputs for feature vectors because the vectors do not have the same number of features and the second vector is lacking a value at index 1.
<br>
<br>
CURRENT ALGORITHMS:
<br>
-Unweighted K-Nearest Neighbors
<br>
-Weighted K-Nearest Neighbors
<br>
-Regressive K-Nearest Neighbors
<br>
-Primal Perceptron
<br>
-Dual Perceptron
<br>
-Naive Bayes
<br>
-Laplace Smoothed Naive Bayes
