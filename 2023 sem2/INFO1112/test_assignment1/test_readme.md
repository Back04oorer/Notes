# Test file

## Introduction

#### The 4th line in test.sh set the current time to 22:35:00 27/08/2023.All testcases are based on this time.

#### Positive, negative, and edge test cases will be conducted separately among the three users: ba, cloudlary, and babra.

#### Due to the lack of permissions to modify the time on 'ed', I chose to proceed on my own computer. This has resulted in 'test.sh' being incompatible on 'ed'.

#### Apart from the edge cases, all input and output files are sorted into different folders based on the distinct test functionalities.

#### tasks.md and meeting.md will be rewritten by files in /source(to check functionality in different situations)

## Explanation to testcases
| user     | Type     | function | explanation | file path |
|----------|----------|----------|-------------|--------------|
| ba       | Positive | display | simple display then quit    | test/ba/display/positive_display_1.*     |
| ba  | Positive | display | add some invalid lines in tasks.md and meetings.md     | test/ba/display/positive_display_2.*      |
| ba | Positive | complete tasks | complete a task    | test/ba/complete_task/positive_1.*     |
| ba | Positive | complete tasks | complete tasks | test/ba/complete_task/positive_2.*     |
| ba | Positive | complete tasks | complete tasks    | test/ba/complete_task/positive_3.*    |
| ba | Positive | add meetings | add a meeting    | test/ba/add_meeting/positive_1.*     |
| ba | Positive | add meetings | add 2 meetings    | test/ba/add_meeting/positive_2.*      |
| ba | Positive | complete tasks and add meetings | complete a task then add a meeting    | test/ba/complete_add/positive_1.*     |
| ba | Positive| complete tasks and add meetings| complete a task,add a meeting then complete 2 tasks | test/ba/complete_add/positive_2.*      |
| cloudlary| Negative| display| Empty task.md and meetings.md  | test/cloudlary/display/negative_empty.*   |
| cloudlary| Negative| display| Wrong format lines in tasks.md meetings.md   | test/cloudlary/display/negative_wrong_format.*    |
| cloudlary| Negative| complete task| Empty input   | test/cloudlary/complete_task/negative_empty_input.*  |
| cloudlary| Negative| complete task| Invalid input   | test/cloudlary/complete_task/negative_invalid.*  |
| cloudlary| Negative| add meetings | Empty input   | test/cloudlary/add_meeting/negative_empty_input.*    |
| cloudlary| Negative| complete tasks and add meetings| mutiple invalid inputs   | test/cloudlary/complete_add/negative.*   |
| babra| Edge| complete tasks and add meetings| Invalid input in menu   | test/babra/edge_menu_invalid.*  |

