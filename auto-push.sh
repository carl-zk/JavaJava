#!/bin/bash
git add .
echo "commit message:"
read
git commit -m $"$REPLY"
git pull origin master
git push origin master