set datafile separator ";"
set grid
set title "{/=20 Execution time}"
set ylabel "Time (milliseconds)
set xlabel "Number of edges"
set key left top
plot "output/execTimes.csv" using 1:2 title "Execution Time"
set term png size 500, 500
set output "output/execTimeGraph.png"
replot
set term x11