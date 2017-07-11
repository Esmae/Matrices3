import numpy as np
import matplotlib as mpl
mpl.use("Agg")
from matplotlib import pyplot as mplot
import csv 

#declaring the lists
listScore = []
listCommit = []

f = open("master/dataWithTime50","a")


with open("time50") as g:
    i = int(1)
    for line in g:
        if i!=1:
            f.write(line)
        i = i+1

f.close()
g.close()


with open("master/dataWithTime50") as csvfile:
    #reading in the data against commits/time file
    reader = csv.DictReader(csvfile)
    i = int(0)
    for row in reader: 
        #storing the values in the Score columns in a list
        listScore.append(row['Score'])
        listCommit.append(i)
        i=i+1
#converting the lists to arrays so can plot
arrScore = np.asarray(listScore)
arrCommit = np.asarray(listCommit)
arrScore = arrScore.astype(np.float)
#plotting the graph

mplot.plot(arrCommit,arrScore,'k')
#labeling the graph
mplot.xlabel('Relative commit number')
mplot.ylabel('Throughput Score/ op/s')

#saving the plot as a png file
mplot.savefig(TimePlot.png)
