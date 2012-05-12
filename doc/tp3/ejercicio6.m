load fisheriris;
t1 = 1:50;
t2 = 51:100;
t3 = 101:150;
m = [
	mean(meas(t1, :))
	mean(meas(t2, :))
	mean(meas(t3, :))
];
c=corrcoef(m);
[i j] = find(c>0.9 & ~(c==1));
% muestra que se correlacionan el 4,1 con el 3,1
n = 4;
sh = (n-1) * std(m) / n;

% vector de medias
v = meas(t3,:) - ones(50,1) * mean(meas(t3,:));
sum(v.^2) / 50
% media de medias para conjunto de especies
muHatMean = sum(m) ./ 3;
sigmaHatMean = sum((m - (ones(3,1) * muHatMean)).^2)/3;
