if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to Matrices" ]; then
  echo -e "Starting to update Matrices\n"

  cp -R testIJK $HOME
  cp -R testIKJ $HOME

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  git clone --quiet --branch=testTravis https://${TOKEN}@github.com/Esmae/Matrices.git  testTravis> /dev/null

  cp -Rf $HOME/testIJK testTravis
  cp -Rf $HOME/testIKJ testTravis
  cd testTravis
 

  git add -f .

  git commit -m "Travis build pushed to Matrices"

  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testTravis > /dev/null

  echo -e "Success? \n"
fi
