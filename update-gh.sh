if [ "$TRAVIS_COMMIT_MESSAGE" != "Travis build pushed to testingUpload" ]; then
  echo -e "Starting to update testingUpload\n"

  cp -R test8 $HOME

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "Travis"
  git clone --quiet --branch=testingUpload https://${TOKEN}@github.com/Esmae/Matrices.git  testingUpload > /dev/null

  cp -Rf $HOME/test8 testingUpload
  cd testingUpload
  echo -e "1"

  git add -f .
  echo -e "2"
  git commit -m "Travis build pushed to testingUpload"
  echo -e "3"
  git push -fq https://${TOKEN}@github.com/Esmae/Matrices.git testingUpload > /dev/null

  echo -e "Success? \n"
fi
