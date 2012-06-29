load fisheriris;
% species meas

% punto a
group = meas(:,1:2);
[k correct] = helper(species, group, group, species);
plot(k,correct, 'r')
hold on;

%punto b
group = meas(:,3:4);
[k correct] = helper(species, group, group, species);
plot(k,correct, 'b')
hold on;

%punto c
group = meas;
[k correct] = helper(species, group, group,species);
plot(k,correct, 'g');
hold on;

title({'Porcentaje de clasificaciones correctas';'método de k-vecinos'});
xlabel('k - cantidad de vecinos');
ylabel('clasificaciones incorrectas');
legend('ancho y largo de sépalos','ancho y largo de pétalos',...
    '4 variables','Location','Southwest');

