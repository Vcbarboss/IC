#!/bin/bash

currentpath=${PWD}
clear
now=$(date)
echo -e $now: BEGIN git log extraction: $currentpath \\n 

cd $currentpath

git config diff.renameLimit 999999 


#Extract and format commit files information
for file in *.c *.h
do

	lineNumber=1
	awk "NR > $lineNumber" $file |
	while read line; do
	  git log -L$lineNumber,$lineNumber:$file --name-status --pretty=format:"commit	%H	%an	$f	$lineNumber	%cd"  --date=format:'%d/%m/%Y - %H:%M:%S' --find-renames >> temp.log
	  ((lineNumber++))
	done
done

awk -F$'\t' -f $currentpath/log.awk temp.log > commitinfo.log

#Remove temp file
rm temp.log

git config --unset diff.renameLimit


now=$(date)
echo -e $now: END git log extraction: $currentpath \\n 
