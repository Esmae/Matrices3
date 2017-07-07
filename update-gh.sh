if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to testMatrices" ]; then
  echo -e "Starting to update testMatrices\n"

  cp -R testIJK $HOME
  cp -R testIKJ $HOME

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  git clone --quiet --branch=testMatrix https://${TOKEN}@github.com/Esmae/Matrices.git  testMatrix> /dev/null

  cp -Rf $HOME/testIJK testMatrix
  cp -Rf $HOME/testIKJ testMatrix
  cd testMatrix
 

  git add -f .

  git commit -m "Travis build pushed to testMatrices"

  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testMatrices > /dev/null

  echo -e "Success? \n"
fi
