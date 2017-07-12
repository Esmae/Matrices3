#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Normalising the benchmarking data with respect to the reference benchmark run


"""

import csv
#set default normalisation to be 1, i.e. no normalisation
normalise = float(1.0)

#reading in the actual normalisation
ref = open("refBench", "rb")
reader = csv.reader(ref)
i = 0
for row in reader:
    if i != 0:
        #skipping the first row as this is the header
        #the file should only contain 1 other row
        normalise = row[4]
    i = i + 1
        
ref.close()
        

noNorm = open("testIJK", "rb")
reader = csv.reader(noNorm)
norm = open("master/normData/testIJKNorm", "wb")
writer = csv.writer(norm)


i = 0
for row in reader:
    if i != 0:
        #normalising the score
        row[4] = str(float(row[4])/float(normalise))
        #normalising the error associated with the score
        row[5] = str(float(row[5])/float(normalise))
    writer.writerow(row)
    i = i + 1

#closing the files so they are updated 
noNorm.close()    
norm.close()
