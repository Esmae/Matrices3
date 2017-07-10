if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to testMatrix" ]; then
  echo -e "Starting to update testMatrix\n"

  cp -R testIJK $HOME
  cp -R testIKJ $HOME
  cp -R CompPlot* $HOME

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  git clone --quiet --branch=testMatrix https://${TOKEN}@github.com/Esmae/Matrices.git  testMatrix> /dev/null

  cp -Rf $HOME/testIJK testMatrix
  cp -Rf $HOME/testIKJ testMatrix
  cp -Rf $HOME/CompPlot* testMatrix
  cd testMatrix
 

  git add -f .

  git commit -m "Travis build pushed to testMatrix"

  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testMatrix > /dev/null

  echo -e "Success? \n"
fi
