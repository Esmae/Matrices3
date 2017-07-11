if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to Matrix3 master" ]; then
  echo -e "Starting to update Matrix 3 master\n"

  cp -R testIJK $HOME
  cp -R testIKJ $HOME
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
  cd testMatrix
  
  chmod +x timePlot.py
  python timePlot.py
 

  git add -f .

  git commit -m "Travis build pushed to testMatrix"

  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testMatrix > /dev/null

  echo -e "Success? \n"
fi
