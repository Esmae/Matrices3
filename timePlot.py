f = open("master/dataWithTime50","a")


with open("master/time50") as g:
    i = int(1)
    for line in g:
        if i!=1:
            f.write(line)
        i = i+1

f.close()
g.close()
