% read data from file
data = dlmread("mydata.txt");

% hierarchical clustering
Z = linkage(data); % gives the tree structure of the data

% draw the data
dendrogram(Z);

% Show more clusters
T = cluster(Z, 'maxclust', 3);