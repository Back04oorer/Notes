#!/bin/bash

directory=$1
File_count=0
Dir_count=0

for item in "$directory"/*; do
	echo "Processing: $item"
    if [ -f "$item" ]; then
        File_count=$((File_count + 1))

        if [[ $item == *.txt ]]; then 
            chmod a=r "$item"
        fi
    elif [ -d "$item" ]; then
        Dir_count=$((Dir_count + 1))
    fi
done

echo "Number of files: $File_count"
echo "Number of directories: $Dir_count"
