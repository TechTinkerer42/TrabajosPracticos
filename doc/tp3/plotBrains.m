clear
load brains.txt
pTot = brains(:,1);
pCer = brains(:,2);
clf
plot(pTot, pCer, '*b');
x = [0:3000];
y = 1.98 .* x + 2.55;
hold on
plot(x, y, 'r')