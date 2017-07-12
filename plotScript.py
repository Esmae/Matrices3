# -*- coding: utf-8 -*-
"""
Plotting as a ln plot the Score/ op/s against 
the size of the array for matrix multiplication
"""

import numpy as np
import matplotlib as mpl
mpl.use("Agg")
from matplotlib import pyplot as mplot
import csv
import time

#declaring the lists
listScoreIJK = []
listScoreIKJ = []
listSizeIJK = []
listSizeIKJ = []


with open('master/normData/testIJKNorm') as csvfile:
    #reading in the testIJK csv file
    reader = csv.DictReader(csvfile)
    i = int(0)
    for row in reader:
        #storing the values in the Score and noRows columns in two seperate lists
        listScoreIJK.append(row['Score'])
        listSizeIJK.append(row['Param: noRows'])
        i=i+1
    #converting the lists to arrays so can plot easily
    arrScoreIJK = np.asarray(listScoreIJK)
    arrSizeIJK = np.asarray(listSizeIJK)
    arrScoreIJK = arrScoreIJK.astype(np.float)
    arrSizeIJK = arrSizeIJK.astype(np.float)
    #logging the values so plot a (roughly) straight line
    #this makes it easier to compare
    arrLnScoreIJK = np.log(arrScoreIJK)
    arrLnSizeIJK = np.log(arrSizeIJK)
    #plotting the graph
    mplot.plot(arrLnSizeIJK,arrLnScoreIJK,'k-',label='IJK')
    
with open('master/normData/testIKJNorm') as csvfile:
    #reading in the testIKJ csv file
    reader = csv.DictReader(csvfile)
    i = int(0)
    for row in reader:
        #storing the values in the Score and noRows columns in two seperate lists
        listScoreIKJ.append(row['Score'])
        listSizeIKJ.append(row['Param: noRows'])
        i=i+1
        #converting the lists to arrays so can plot easily
    arrScoreIKJ = np.asarray(listScoreIKJ)
    arrSizeIKJ = np.asarray(listSizeIKJ)
    arrScoreIKJ = arrScoreIKJ.astype(np.float)
    arrSizeIKJ = arrSizeIKJ.astype(np.float)
    #loggin the values so plot a (roughly) straight line
    #this makes it easier to compare
    arrLnScoreIKJ = np.log(arrScoreIKJ)
    arrLnSizeIKJ = np.log(arrSizeIKJ)
    #plotting the graph
    mplot.plot(arrLnSizeIKJ,arrLnScoreIKJ,'r-',label='IKJ')
    
#labeling the graph
mplot.legend()
mplot.xlabel('ln(Size)')
mplot.ylabel('ln(Score/ op/s)')

#creating the filename with the date
filename = 'CompPlot'
theDate = time.strftime("%d-%m-%Y_-%H-%M")
filename += theDate
filename += '.png'

#saving the plot as a png file
mplot.savefig(filename)
