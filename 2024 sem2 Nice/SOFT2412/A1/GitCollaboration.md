# GitHub collaboration

During this development process, we utilized a model similar to the Integration-Manager Model to meet the requirements of agile development. Specifically, the responsibilities of each person in the workflow are as follows:

- **James Zhao**: Developer, frontend, provided API templates for the backend, designed JSON formats, and acted as a code reviewer.

- **Mingyuan Ba**: Integration Manager, responsible for testing, managing GitHub, resolving merge conflicts, and fixing bugs.

- **RuiXiao Dai**: Developer, responsible for developing JSON data management interfaces and fixing related bugs.

- **Zirui Zhou**: Developer, responsible for implementing core functionalities and integrating with the frontend.

In each iteration, team members pushed their code or test cases to a branch other than the main branch in the repository. After the Integration Manager reviewed the code, it was merged into the main branch through a pull request, and the next iteration began. Each iteration included improvements or added new features. The following are the details of each phase:

## Pull Request

When a team member begins working on a specific task, they create a new branch using `git checkout -b <new_branch>` and save their changes using git add and git commit. They then push the changes to the remote repository with `git push origin <new_branch>`. Once the development is complete, the developer submits a `pull request` to merge the new branch into the main branch. The Integration Manager reviews the request, discusses it with the team member, and resolves any conflicts. Depending on the outcome, the branch is either merged directly or sent back for revisions. After the merge is completed, the new branch is deleted, and the team member begins work on the next iteration.

![pull request](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A1/Graphs/pull_request.png?raw=true)

## Issue Handling

When a critical bug is found during testing, the Integration Manager creates a new issue in GitHub, specifying the type of issue and the error, and notifies the team members to make the necessary changes. In addition to bugs found through JUnit testing, we also simulate user interactions with the frontend, attempting to replicate specific user stories. Any issues discovered during this process are reported to the Integration Manager, who assigns the appropriate team member to fix them.

![Issues](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A1/Graphs/Issues.png?raw=true)

If the bug is found in the main branch, the Integration Manager reverts the merge and asks the responsible team member to create a new branch to fix the bug.

![Revert](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A1/Graphs/Revert_1.png?raw=true)

Once the team member completes the fix on the new branch, they submit a pull request to merge the fix into the main branch. The tester then verifies that the bug has been resolved, closes the issue, and the team member deletes the bug-fix branch.

This problem-solving process ensures that bugs in the main branch are fixed as quickly as possible. By having the Integration Manager assign the team member most familiar with the issue to resolve the bug, the fix can be implemented efficiently and without delay.







