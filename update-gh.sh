if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to Matrix3 master" ]; then
  echo -e "Starting to update Matrix 3 master\n"

  cp -R test* $HOME
  cp -R time* $HOME
  cp -R CompPlot* $HOME

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  git clone --quiet --branch=master https://${TOKEN}@github.com/Esmae/Matrices3.git  master> /dev/null

  today=$(date +%Y-%m-%d_%H-%M)
  mv testIJK testIJK."$today"
  
  mv testIKJ testIKJ."$today"
  

  cp -Rf $HOME/test* master/data
  cp -Rf $HOME/CompPlot* master/figures

  
  #need to change the python script if change the name of the folder the clone is going into (currently it's called master)
  chmod +x timePlot.py
  python timePlot.py
  
  cd testMatrix
 

  git add -f .

  git commit -m "Travis build pushed to testMatrix"

  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testMatrix > /dev/null

  echo -e "Success? \n"
fi
