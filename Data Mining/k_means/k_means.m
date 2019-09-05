data = dlmread("data.txt");

% k_means
idx = kmeans(data, 3);

% Hierarchical Clustering
Z = linkage(data);

dendrogram(Z);