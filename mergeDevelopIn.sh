#!/usr/bin/env bash

#This brings develop in the current branch.
#performs a merge and stops
#It is left to the user to check that there are no conflicts
#It is left to the user to finalize the commit and to push it.

sourceBranch=develop
destinationBranch=$(git symbolic-ref --short -q HEAD)

git checkout $sourceBranch
git pull

git checkout $destinationBranch
git merge $sourceBranch --no-ff --no-commit
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "!!!!!!!!!!!!!!check for conflicts and finish the commit !!!!!!!!!!!!!!"
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"

