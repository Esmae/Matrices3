if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to Matrix3 master" ]; then
echo "first"
pwd
  ls -l
  echo -e "Starting to update Matrix 3 master\n"
#moving files created in build into home
#moving scripts to be run into home
  cp -R test* $HOME
  cp -R time* $HOME
  cp -R CompPlot* $HOME
  cp -R refBench $HOME
  cp -R normFromRef.py $HOME
 

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  #cloning the relevant repo
  git clone --quiet --branch=master https://${TOKEN}@github.com/Esmae/Matrices3.git  master> /dev/null
  
#normalising the benchmarking data just collected
python normFromRef.py

#attaching the time and date to the data files
  today=$(date +%Y-%m-%d_%H-%M)
  mv testIJK testIJK."$today"
  mv testIKJ testIKJ."$today"
  
#putting the files in local repo
  cp -Rf $HOME/test* master/data
  cp -Rf $HOME/CompPlot* master/figures
  cp -Rf $HOME/refBench master


  
  #need to change the python script if change the name of the folder the clone is going into (currently it's called master)
  #calls the python script that creates the 'with time' plot
  python timePlot50.py
  python timePlot100.py
  python timePlot500.py

  #putting the new 'with time' plot in the repo (possibly overwritting the latest one)
  cp -Rf TimePlot50.png master/figures
  cp -Rf TimePlotTen50.png master/figures
  cp -Rf TimePlot100.png master/figures
  cp -Rf TimePlotTen100.png master/figures
  cp -Rf TimePlot500.png master/figures
  cp -Rf TimePlotTen500.png master/figures
  
  cd master
 #adding the new files and changing files so they are ready to commit 
  git add -f .
#commiting the added changes
  git commit -m "Travis build pushed to Matrix3 master"
#pushes the changes the github on the master branch of Matrices3
  git push -fq https://${TOKEN}@github.com/Esmae/Matrices3.git master > /dev/null

  echo -e "Success? \n"
fi
