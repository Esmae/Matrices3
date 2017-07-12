if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to Matrix3 master" ]; then
  echo -e "Starting to update Matrix 3 master\n"
#moving files created in build into home
  cp -R test* $HOME
  cp -R time* $HOME
  cp -R CompPlot* $HOME
  cp -R refBench $HOME
 

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  #cloning the relavant repo
  git clone --quiet --branch=master https://${TOKEN}@github.com/Esmae/Matrices3.git  master> /dev/null
  
  #don't really want to do this
  echo -e "calling python script from clone"
  chmod +x master/normFromRef.py
  python master/normFromRef.py
  

#attaching the time and date to the data files
  today=$(date +%Y-%m-%d_%H-%M)
  mv testIJK testIJK."$today"
  mv testIKJ testIKJ."$today"
  
#putting the files in local repo
  cp -Rf $HOME/test* master/data
  cp -Rf $HOME/CompPlot* master/figures
  cp -Rf $HOME/refBench master


  
  #need to change the python script if change the name of the folder the clone is going into (currently it's called master)
  chmod +x timePlot50.py
  chmod +x timePlot100.py
  chmod +x timePlot500.py
  #calls the python script that creates the 'with time' plot
  echo -e "calling timePlot50"
  python timePlot50.py
  echo -e "calling norm.py"
  python normFromRef.py
  python timePlot100.py
  python timePlot500.py
  cp -R TimePlot50.png $HOME
  cp -R TimePlotTen50.png $HOME
  cp -R TimePlot100.png $HOME
  cp -R TimePlotTen100.png $HOME
  cp -R TimePlot500.png $HOME
  cp -R TimePlotTen500.png $HOME
  #putting the new 'with time' plot in the repo (possibly overwritting the latest one)
  cp -Rf $HOME/TimePlot50.png master/figures
  cp -Rf $HOME/TimePlotTen50.png master/figures
  cp -Rf $HOME/TimePlot100.png master/figures
  cp -Rf $HOME/TimePlotTen100.png master/figures
  cp -Rf $HOME/TimePlot500.png master/figures
  cp -Rf $HOME/TimePlotTen500.png master/figures
  
  cd master
 #adding the new files and changing files so they are ready to commit 
  git add -f .
#commiting the added changes
  git commit -m "Travis build pushed to Matrix3 master"
#pushes the changes the github on the master branch of Matrices3
  git push -fq https://${TOKEN}@github.com/Esmae/Matrices3.git master > /dev/null

  echo -e "Success? \n"
fi
