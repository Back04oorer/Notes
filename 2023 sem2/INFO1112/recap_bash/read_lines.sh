#!/bin/bash

line_number=1
cat input.txt | while read line; do
    if [ -z "$line" ]; then 
	   echo "$line_number: Empty line"
    else
	   echo "$line_number:$line"
    fi
    ((line_number++))
done
