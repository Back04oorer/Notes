#!/bin/bash

for Filename in "$1"/*;do
    if [ -f "$Filename" ];then 
	echo "$Filename is a file"
    elif [ -d "$Filename" ]; then
	echo "$Filename is a directory"
    fi
done

 
