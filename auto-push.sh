#!/bin/sh
git add .
echo "commit info:"
read info
git commit -m $info
git pull origin master
git push origin master