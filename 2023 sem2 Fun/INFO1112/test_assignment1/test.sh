#!/bin/bash

#set time
sudo date "082722352023.00"

#ba all positive cases
echo "Now set User as ba, set home directory as /Users/bamingyuan/Desktop/test/ba"

USER=ba
export USER
HOME=/Users/bamingyuan/Desktop/test/ba
export HOME
cd ~

#1 simple display(p1)
cat source/meetings_source1.md > meetings.md
cat source/tasks_source1.md > tasks.md

echo "Difference for simple display(positive 1):"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < display/positive_display_1.in) display/positive_display_1.out

echo "======================================================================"

#2 simple display(p2)
cat source/meetings_source2.md > meetings.md
cat source/tasks_source2.md > tasks.md

echo "Difference for simple display(positive 2):"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < display/positive_display_2.in) display/positive_display_2.out

echo "======================================================================"

#3 complete task(p1)
cat source/meetings_source1.md > meetings.md
cat source/tasks_source1.md > tasks.md

echo "Complete a task(positive 1)"

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_task/positive_1.in) complete_task/positive_1.out

echo "======================================================================"

#4 complete task(p2)
cat source/meetings_source2.md > meetings.md
cat source/tasks_source2.md > tasks.md

echo "Complete a task(positive 2)"

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_task/positive_2.in) complete_task/positive_2.out


echo "======================================================================"
#4 complete task(p3)
cat source/meetings_source2.md > meetings.md
cat source/tasks_source2.md > tasks.md

echo "Complete a task(positive 3)"

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_task/positive_3.in) complete_task/positive_3.out


echo "======================================================================"
#6 add meeting(p1)
cat source/meetings_source1.md > meetings.md
cat source/tasks_source1.md > tasks.md

echo "add meeting(positive 1)"

echo "Difference between outputs in terminal:"

diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < add_meeting/positive_1.in) add_meeting/positive_1.out



echo "======================================================================"
#7 add meeting(p2)
cat source/meetings_source2.md > meetings.md
cat source/tasks_source2.md > tasks.md

echo "add meeting(positive 2)"
echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < add_meeting/positive_2.in) add_meeting/positive_2.out



echo "======================================================================"
#8 complete and add

cat source/meetings_source1.md > meetings.md
cat source/tasks_source1.md > tasks.md

echo "complete tasks and add meeting(positive 1)"

echo "Difference between outputs in terminal:"

diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_add/positive_1.in) complete_add/positive_1.out

echo "======================================================================"
#9 complete and add

cat source/meetings_source2.md > meetings.md
cat source/tasks_source2.md > tasks.md

echo "complete tasks and add meeting(positive 2)"

echo "Difference between outputs in terminal:"

diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_add/positive_2.in) complete_add/positive_2.out



echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

USER=cloudlary
export USER
HOME=/Users/bamingyuan/Desktop/test/cloudlary
export HOME
cd ~
echo "Now set home directory to:"
pwd

echo "======================================================================"
#1 display(empty file)
cat source/tasks_empty.md > kid/tasks.md
cat source/meetings_empty.md > kid/meetings.md

echo "Difference for simple display(negative_empty):"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < display/negative_empty.in)  display/negative_empty.out

echo "======================================================================"
#2 display(wrong format file)
cat source/tasks_wrong_format.md > kid/tasks.md
cat source/meetings_wrong_format.md > kid/meetings.md

echo "Difference for simple display(negative wrong format file):"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < display/negative_wrong_format.in)  display/negative_wrong_format.out

echo "======================================================================"
#3 complete task(negative empty input)
echo "complete task(negative empty input)"
cat source/tasks_source1.md > kid/tasks.md
cat source/meetings_source1.md > kid/meetings.md

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_task/negative_empty_input.in) complete_task/negative_empty_input.out




echo "======================================================================"
#4 complete task(negative invalid input)
echo "complete task(negative empty input)"
cat source/tasks_source1.md > kid/tasks.md
cat source/meetings_source1.md > kid/meetings.md

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_task/negative_invalid.in) complete_task/negative_invalid.out



echo "======================================================================"
#5 complete task(negative empty input/invalid input)
echo "add meeting(negative invalid&empty)"
cat source/tasks_source1.md > kid/tasks.md
cat source/meetings_source1.md > kid/meetings.md

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < add_meeting/negative_empty_input.in) add_meeting/negative_empty_input.out


echo "======================================================================"
#6 complete task and add meeting(negative empty input/invalid input)
echo "complete task and add meeting(negative invalid&empty)"
cat source/tasks_source1.md > kid/tasks.md
cat source/meetings_source1.md > kid/meetings.md

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < complete_add/negative.in)  complete_add/negative.out





echo " "
echo " "
echo " "
echo " "
echo " "
echo " "

USER=cloudlary
export USER
HOME=/Users/bamingyuan/Desktop/test/babra
export HOME
cd ~
echo "Now set home directory to:"
pwd

echo "======================================================================"
#invalid option of menu(edge 1)
echo "invalid option of menu(edge 1)"
cat source/tasks_source1.md > tasks.md
cat source/meetings_source1.md > meetings.md

echo "Difference between outputs in terminal:"
diff <(python3 /Users/bamingyuan/Desktop/test/jafr.py /Users/bamingyuan/Desktop/test/passwd < edge_menu_invalid.in) edge_menu_invalid.out

echo "======================================================================"

#reset time
sudo ntpdate pool.ntp.org

