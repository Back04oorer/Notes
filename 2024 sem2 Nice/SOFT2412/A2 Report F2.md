# Assignment 2 VSAS - Report
---
- **[[#Contributions]]**
- **[[#Sprint Goals]]**
- **[[#User Stories and Product Backlog]]**
- **[[#Scrum Events]]**
- **[[#Agile Tools and CI/CD Pipeline Practices]]**
- **[[#Source Code and Version]]**
- **[[#Appendix (Testing bullets)]]**
# Contributions
---
## Mingyuan Ba
*(SID: 530157791)*
Scrum Master, Tester. As the Scrum Master, I am responsible for maintaining the Scrum board on GitHub, handling conflicts during merges, and ensuring the smooth implementation of tasks. Additionally, I organise meetings, set the discussion topics for Scrum meetings, and help coordinate communication between front-end and back-end developers. As a tester, during this sprint, I focused on testing the functions at the database interface layer and contributed to the database design from a testing perspective. After identifying bugs, I reported issues through GitHub and provided suggestions.
## James Zhao
*(SID: 530820415)*
As for the contribution on this software development, I've contributed in these fields:
- **Product Manager:** Came up with user stories, design functions and user interface to be further breakdown and distributed by scrum manager
- Designing and development of the frontend interface, logic and functions
- Decided the structure for frontend and API framework and provided basic API template for backend developer to further develop. Designed corresponding authentication and encryption frame to be used in the project.
- Code reviewing on basic logics in low-level classes, API functions. Finding issues and help with solving issues.
- Setting up Jenkins webhook and auto build + test integration
## Zirui Zhou
*(SID: 530175119)*
I am responsible for designing and implementing the backend APIs for users and admin interactions to access, upload, and download the scrolls. To build up the APIs, I used the methods in `SQLiteJDBC.java` to access the database. I also worked on integrating these APIs with the frontend, ensuring that the users can interact smoothly with the database.
## Ruixiao Dai
*(SID: 530596462)*
I am responsible for designing the database architecture and developing functions that facilitate interaction with the database. These functions enable various operations, such as updating, deleting, and adding values to the tables. Each function is designed to return specific status codes: 0 for successful operations, 1 for connection errors, and 2 for constraint violations, allowing others to easily understand the outcome of each operation. 
In addition to my database responsibilities, I am also tasked with creating and maintaining the README file with live action captured in gif. This document serves as an essential guide, providing comprehensive instructions on how to install, use and test the application. 

# Sprint Goals
---
## Sprint 1:
**From Assignment released ~ Oct 9th**:
- Identify all the initial features
- Setup scrum board and Jenkins
- Come up with initial user stories base on the features, integrate into pages and design the routing layout, breakdown into suitable size task items to be distributed accordingly
- User: sign up, log in, log in anonymously
- Admin: besides normal user functions, should also be able to view user management page
- Anonymous Logged User: View-only
- All types of log in should have appropriate identification, authentication and indication on pages
- Files (Scrolls) can be extracted from database and displayed in list, with frontend side filtering connected with backend functions
- All the frontend pages developed (`React` with `Vite`)
- Backend functions implemented (`Java`) with SQL database (`SQLite`), and encapsulated with suitable API framework (`Javalin`)
- Test backend functions, aiming for coverage around half

**Insights on next round:**
- During this sprint, we identified a few features that are still missing and can be added to improve the usability of our system.
	After attending the week 10 lab, we were asked to add the following features to our project:
	- **Editable File Names**: Enabling users to edit the names of files they have uploaded for better organisation and flexibility.
	- **User Profile and Avatar**: Adding a user profile section that allows users to upload an avatar, enhancing personalisation and user experience.
	- **Admin Access Statistics**: Implementing a feature for admins to view detailed statistics on access, providing insights into system usage and user activity.
	- **Admin File Modification**: Allowing admins to modify any file, giving them full control over the scrolls and data within the system.
	  
## Sprint 2:
From **October 10th ~ October 16th**:
- Implementing the profile system, allowing users to create and manage their profiles.
- Adding the user avatar functionality, enabling users to upload and update their avatars.
- Implementing file preview functionality, allowing users to preview files before downloading.
- Enhancing the download feature, ensuring users can download their files smoothly.
- Completing the upload functionality for scrolls, including the ability to upload files efficiently.

**Insights on Next Round:**
- **Anonymous User File Upload**:
  - Allow anonymous users to upload files, but restrict the upload to only `.txt` files that are up to 10 lines in length.
  - The anonymous users will not have the permission to delete the files that they upload.
- **Admin Access to Anonymous Files**:
  - Ensure that only admins have full access to view, delete, or modify these files uploaded by anonymous users.
  - Restrict the anonymous users to only basic file upload actions.

## Sprint 3:
From **October 17th ~ October 23rd**:
- Anonymous User File Upload: Enable anonymous users to upload '.txt' files that are limited to 10 lines in length.
- Anonymous File Restrictions: Ensure that anonymous users can only upload files, and cannot delete them.
- Ensure that only admins have full access to view, delete, or modify these files uploaded by anonymous users.
- Restrict the anonymous users to only basic file upload actions.


# User Stories and Product Backlog
---
## Methodologies and setup

**Scrum board Overview (early snapshot):**![[Screenshot 2024-10-09 at 8.57.26 AM.png]]
**Mid-development task aggregated view (scrum board task in list view)**![[Screenshot 2024-10-24 at 4.43.30 pm.png]]
### **Link to the final scrum board:** https://github.sydney.edu.au/orgs/SOFT2412-COMP9412-2024Sem2/projects/264/views/1

In our agile development process, we leverage GitHub Projects' built-in board functionality to serve as our Scrum board and product backlog. The digital project board, as shown in the image, provides a visual representation of our project's progress and serves as a central hub for our development workflow.

1. Product Backlog Integration:
   - Our GitHub Projects board serves as our product backlog, containing all user stories and tasks for the project.
   - The "User Story" and "Todo" columns effectively represent our product backlog items at different levels of granularity.

2. Story Breakdown:
   - We start with high-level user stories, which are then broken down into smaller, actionable tasks (GitHub Issues).
   - This breakdown process is ongoing, ensuring our backlog always contains appropriately sized work items.

3. Scrum Board Functionality:
   - The same board doubles as our Scrum board, with columns for "In Progress," "Testing," and "Done."
   - This allows us to visualise the flow of work from the backlog through to completion within a single interface.

4. Methodology Overlapping:
   - Our approach recognises the overlap between the Scrum methodology and the concept of a product backlog. By integrating these on a single board, we maintain a cohesive view of our project's progress and priorities.

5. Dynamic Refinement:
   - We continuously refine our backlog, adding new items, breaking down existing ones, and reprioritising as needed.
   - This process ensures our backlog always reflects the current state and priorities of the project.

6. Precision in Process:
   - The integration of our product backlog with the Scrum board helps us maintain precision in our development process.
   - We can easily track the progression of items from backlog to completion, ensuring nothing falls through the cracks.

## User Story Illustration

### Sprint 1:

| Page Number | Page                         | Description                                                                                                                                                                                                                                                                                                                                            |
| ----------- | ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| #1          | login Page                   | user may choose between signup, login, anonymous login using tabs. Fill designated form and jump to library page                                                                                                                                                                                                                                       |
| #2          | library                      | user may swap between upload and download within the page. One is for user to upload files in the scroll storage system. Later one for user be able to view all the scrolls and be able to download (Anonymous user should be in view-only mode)<br>User may filter scrolls with filters (search bar) eg. dates of upload, scroll id, upload by (user) |
| #3          | admin - user management page | Admin user be able to access this page from top bar component and can view all the user information                                                                                                                                                                                                                                                    |
| #4          | upload page                  | Extra page from #2, illustrating the design of upload section.                                                                                                                                                                                                                                                                                         |

**Key changes made during this iteration:**
- **Page#4** removed, upload page became a section in the library page instead of a seperate page, highlighting simplicity in interface, and users will be able to instantly view file uploads within a single page
- Design of buttons / layout was changed to better fit all the required components
**Outcomes from the User stories**:
- All the initial pages were done in frontend and integrated with backend. With most of the functionalities implemented besides file previewing, downloading and uploading.
- Concluded missing functionalities and issues to be updated / implemented / tested during the next sprint.

*(Attached page illustrations)*
![[IMG_5338.jpg]]![[IMG_5337.jpg]]![[IMG_5336.jpg]]![[IMG_5335.jpg]]

### Sprint 2:
**User Profile System:**
- Users should have access to their own profile page, for viewing their own non-credential information. They should be able to edit their own information through the profile page as well, perhaps with an edit button some indication. They should also be able to edit password.
- Admin can do the same as normal user, with extra features - they are allowed to access any users' profile page from the user management page, and they are allowed to modify any data for any user. They may also promote a normal user's role from `user` to `admin`, this cannot be reverted (as the promoted account is also `admin` now)
- Logout button can be migrated to this page for simplicity in the library page
**Avatar System:**
- User Avatar is going to be displayed besides username library page.
- User should be able to upload avatar in their profile page, but also can just display a default user icon when one doesn't have an avatar uploaded to the database
**Scroll Page (as for editing scroll details and previewing scroll):**
- Can be accessed by users from library page by clicking on the corresponding scroll tab
- Detailed stats being displayed on the page, similar to the profile page, provide editing function including updating the actual data of the scroll and changing the name of the scroll.
- Preview of the file directly displayed on the page - determine the type of the file and display them.
**Upload and Download Changes:**
- User should be able to use the implemented version of upload and download function instead of the dummy one from the previous version. Additionally, able to see changes in the library and this function correctly supports interaction on statistics of the scroll.
### Sprint 3:
####  Anonymous Post
 **As an anonymous user**
- I would want to be able to upload a scroll file,
- But only in txt format and with a maximum of 10 lines,
- So that I can share my content using the scroll system to others within a reasonable limit.

 **Acceptance Criteria**
- The file must be in .txt format.
- The uploaded file cannot exceed 10 lines of content.
- After uploading, the user will receive a confirmation message.

# Scrum Events
---
## Scrum Meetings

| Date (2024)   | Duration | Topic                                                                                                     |
| :------------ | :------- | :-------------------------------------------------------------------------------------------------------- |
| ***Sprint1*** |          |                                                                                                           |
| Sep 30        | 15 mins  | Sprint Planning: Established the fundamental structure and functionalities for the Virtual Scroll System. |
| Oct 1         | 15 mins  | Initial Front-End Development Based on Lo-Fi Prototype.                                                   |
| Oct 2         | 15 mins  | Return value for update, add, delete, and search functionality.                                           |
| Oct 3         | 15 mins  | Return value for update, add, delete, and access operations.                                              |
| Oct 4         | 15 mins  | AccessID and ScrollID Assignment Mechanism Requirement.                                                   |
| Oct 5         | 15 mins  | API on returning a list of scrolls' descriptions (uploaded user, date, filename).                         |
| Oct 6         | 15 mins  | User Role Verification and Feature Allocation.                                                            |
| Oct 9         | 30 mins  | Report on first round of sprint, concluded missing/new features to be implemented                         |
| ***Sprint2*** |          |                                                                                                           |
| Oct 14        | 2.5 hrs  | Sprint Planning, set up goals                                                                             |
| Oct 15        | 30 mins  | Daily Standup                                                                                             |
| Oct 16        | 15 mins  | Daily Standup                                                                                             |
| Oct 17        | 50 mins  | Sprint Retrospective + Review                                                                             |
| ***Sprint3*** |          |                                                                                                           |
| Oct 21        | 70 mins  | Sprint Planning, set up goals                                                                             |
| Oct 22        | 30 mins  | Daily Standup                                                                                             |
| Oct 23        | 50 mins  | Sprint Retrospective + Review                                                                             |
### Sprint Planning
#### Sprint 1:
In Sprint 1, our team focused on establishing the fundamental structure of the Virtual Scroll Access System and implementing the basic functionalities. We assigned the tasks for each team member with the goal of setting up the database, backend APIs, and frontend integration.
#### Sprint 2:
**Time:From 9:30 PM to 12:00 AM on October 14th** 
In this session, we used Planning Poker to estimate story points and velocity. 
First, we selected the simplest completed user story, anonymous login, as the baseline (1 point). Then, based on this baseline, we assigned points to the user stories in Sprint 1:

![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/PP_Sprint1_1.jpg?raw=true)

After the first round of voting, we did not reach a consensus on any user stories except for the anonymous login. For each user story, we asked the team members who voted as outliers to share their opinions and explain why they chose that point. For the user stories without consensus, we continued voting in rounds until we reached an agreement:

![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/PP_Sprint1_final.jpg?raw=true)

Based on this, we estimated that our **velocity** is 38, which represents the amount of work, in story points, that we can complete in one sprint (iteration).
Then we estimated the unfinished User Stories. Using the same mechanism, we estimated that the remaining User Stories have a total of 50 story points:

![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/PP_Sprint2_final.jpg?raw=true)

Finally, we selected the following User Stories as the tasks to be completed in this Sprint.

| User Story     | Story Points |
| -------------- | ------------ |
| Profile System | 8            |
| Upload         | 3            |
| Download       | 5            |
| Avatar         | 2            |
| Preview        | 20           |
| Total          | 38           |
#### Sprint 3:
**Time:From 7:30 PM to 8:40 AM on October 14th** 
At the start of the meeting, we estimated the velocity for this sprint based on the previous sprint’s velocity and the time available for this sprint. In the previous sprint, we achieved a velocity of 50 story points per sprint despite spending more time than usual. Considering the impact of other tasks, we assumed that the number of story points we could complete in this sprint would be 30.
Next, we estimated the newly introduced User Story, _Anonymous Post_, proposed by the user. First, we clarified the specific requirements of this User Story and then proceeded with the first round of voting.

In the first round, we did not reach a consensus, and there was a significant deviation in the estimates.
![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Sprint3/Graphs/Plan_round_1.jpg?raw=true)

We allowed the outliers to explain their reasoning, and in the second round, we successfully reached an agreement
![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Sprint3/Graphs/Plan_round_2.jpg?raw=true)

Based on the results of the second round, we concluded that the story points to be completed in this sprint would be 13.

### Daily Scrum
#### Sprint 1:
Our team held daily 15-minute stand-up meetings on Zoom to discuss the progress and set up the daily goals for each team member. By holding daily stand-up meetings, we ensured that all our team members kept working on the project and were able to solve the emerging issues. We also held in-person meetings at Fisher Library on 9/30, 10/2, 10/5, 10/6, and 10/9. Each meeting lasted 3 hours. During the meetings, we discussed more detailed task allocation and the specific problems we had during the task, then we solved the problems efficiently.
#### Sprint 2:
Our team held 15-minute stand-up meetings in person on Oct 15 and Oct 16 to discuss the project progress and set daily goals for each team member. Through these daily stand-up meetings, we ensured that all team members continued to make progress on the project and were able to solve any emerging issues in a timely manner. We mainly used this time to track progress and ensure that we could achieve our goals within the scheduled timeline.
#### Sprint 3:
Since we only had one User Story to complete in this Sprint, we held a 30-minute stand-up on October 22nd, mainly to discuss the final objectives and progress.Through this stand-up, we gained a clearer understanding of the current progress, which helped us in reviewing and wrapping up the project.

### Scrum Reviews
#### Sprint 1:
The goals identified earlier were all completed, and experiment with frontend integration and actual usage, some of low-level implementations and database design need extra testcases to ensure its robustness. Overall it meets the ideal timeline our team discussed and produced in earlier rounds of meetings.

During this sprint, we identified a few features that are still missing and can be added to improve the usability of our system.
After attending the interview session for **Sprint 1** (week 10 lab), we were asked to add the following features to our project:
- **Editable File Names**: Enabling users to edit the names of files they have uploaded for better organisation and flexibility.  
- **User Profile and Avatar**: Adding a user profile section that allows users to upload an avatar, enhancing personalisation and user experience.
- **Admin Access Statistics**: Implementing a feature for admins to view detailed statistics on access, providing insights into system usage and user activity.
- **Admin File Modification**: Allowing admins to modify any file, giving them full control over the scrolls and data within the system.

Our development process followed the Scrum Principles closely:
- **Empirical Process Control**: Maintained the transparency by sharing progress in daily stand-up meetings and adapting our process based on feedback.
- **Self-organisation**: Each team member had their own tasks and work, which ensures the accountability and collaboration.
- **Collaboration**: All the meetings we held ensured the collaboration, which helped us to solve problems efficiently.
- **Value-Based Prioritisation**: We prioritised the key value to our Virtual Scroll Access System. All the key functionalities have been achieved in order to satisfy the basic needs according to the user-story and feedback.
- **Time-boxing**: All of our meetings had time limits. We did not try to solve specific problems during the stand-up meetings.
- **Iterative Development**: As we held daily stand-up meetings, we improved the Virtual Scroll Access System periodically.
#### Sprint 2:
During this sprint, our team successfully implemented all the features outlined in the Sprint Goal as planned, including improvements to the Scroll management system, file upload and download functionality, editing users' information, uploading avatars, and providing detailed statistics on access for admin users.
##### Stakeholder Feedback:
The feedback from the stakeholders was positive, especially for the new functionalities related to users' information edition and avatar, and admin access controls.
##### Burn-down Chart Analysis:
The **Burn-down Chart** (as shown below) clearly demonstrates our team's progress during the sprint:
- **Y-axis (Story Points)** represents the remaining workload.
- **X-axis (Time)** represents the time elapsed during the Sprint.

Each **downward** movement in the chart indicates the completion of a **user story**, while each **upward** movement represents the addition of new user stories. 

In this sprint, we completed the user stories in the following order:
1. **Profile system**  
2. **Avatar**
3. **AP (User)**
4. **AP (Scroll)**
5. **Preview**
6. **Download**
7. **Upload**
8. **Stats**
9. **Edit**

The first upward movement occurred when we added the user stories for **AP (User)** and **AP (Scroll)**, which contributed an additional 5 points. The second upward movement occurred when we added the **Stats** and **Edit** user stories, totally 8 points.

Our sprint progression followed the sequence:  
**38 - 8 - 2 + 5 - 2 - 3 - 20 - 5 - 3 + 7 - 5 - 2**
This reflects the completion of user stories and the incorporation of new ones throughout the sprint.

Compared to the Ideal Burndown Line, we demonstrated higher efficiency (reflected in a steeper slope). However, due to the addition of 12 Story Points mid-sprint, our actual completion time ended up being about the same as the expected completion time.

![Burndown Chart](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/Sprint2%20bDchart.jpg?raw=true)

#### Sprint 3:
During this sprint (**Sprint 3**), our team successfully implemented all the features outlined in the Sprint Goal as planned, including the anonymous user file upload feature, and the feature of allowing access to anonymous files.
##### Stakeholder Feedback:
The feedback from the stakeholders was positive, especially for the new functionalities related to anonymous user file upload.
##### Burndown Chart Analysis:
The **Burndown Chart** (as shown below) clearly demonstrates our team's progress during the sprint:
- **Y-axis (Story Points)** represents the remaining workload.
- **X-axis (Time)** represents the time elapsed during the Sprint.

Each **downward** movement in the chart indicates the completion of a **user story**, while each **upward** movement represents the addition of new user stories. 

In this sprint, we completed the user stories in the following order:
1. Anonymous Post

Our sprint progression followed the sequence:  **13-13**, This reflects the completion of user stories and the incorporation of new ones throughout the sprint.

Compared to the Ideal Burndown Line, we completed all User Stories one day ahead of schedule.
![Burndown Chart](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Sprint3/Graphs/burndown_chart.png?raw=true)
##### Velocity Chart Analysis:
The **Velocity Chart (as shown below) represents our team's story points completed over 3 sprints.
- **Y-axis (Story Points)** indicates the total story points achieved in each sprint.
- **X-axis(Time in Sprint) represents the sequence of the sprints.

Each **downward** movement shows our team's progress across the sprints:
1. **Sprint 1**: We started with 38 story points and completed all planned tasks.
2. **Sprint 2**: We reached the peak of 50 story points, reflecting the addition of new features: such as, profile system, avatar, and admin access control.
3. **Sprint 3**: The velocity decreased to 13 story points, as the team focused more on refining the existing features, including user permissions and anonymous user file uploads.

![Velocity Chart](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Sprint3/Graphs/velocity_chart.png?raw=true)

### Scrum Retrospective - Challenges and Issues
#### Sprint 1:
**Major Issue - Issue #5**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/issues/5
We had a debate regarding to the parameters for update functions, initially one teammate suggests it will only pass in values that will be updated, but the member responsible for DB construction said it's not practical as java doesn't support keyword argument, and it could complicate things. So ultimately we decide to pass in every value in the function regardless of what the user trying to update. A function will be provided to assign default values if user doesn't want to change some of the fields.

**Major Issue - Issue #6**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/issues/6
We had a debate regarding the return value of the search function for our underlying database system. One teammate proposed using a JSON array, while another suggested a user/scroll object. After careful consideration, we decided to implement the user/scroll object as the return type. This choice offers a more compact format that is easier to parse. Additionally, we will create a dedicated function to convert the user/scroll object into a JSON array when passing values to the database, ensuring compatibility with frontend requirements.
#### Sprint 2:
**Major Issue on blob encryption - `base64` encoding:**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/pull/91
We encountered an issue when a user attempted to upload an avatar, the front end cannot parse it properly though it seems that data was successfully fetched from backend. This was discovered at early stage of avatar function development so the code with issue was never uploaded. 
Our backend is designed to return the avatar as a byte array, but JSON doesn't natively support the storage of binary data. Though it seems it returned something to the front end, the data was broken. Additionally, transmitting binary data can introduce potential security vulnerabilities. 

To address this, we decided to implement `base64` encoding. This approach converts the binary data into a string format, making it compatible with JSON serialization. By using `base64`, we can ensure that the avatar data is safely encoded and can be reliably transmitted over the network while also mitigating security risks associated with handling raw binary data. This solution allows us to maintain data integrity and security while providing a seamless experience for users uploading their avatars.

**End User experience Issue - Issue #102:**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/issues/102
We encounter an issue at the demo when updating user's username, the system will crash as the current user session is fetched by username, changing it will result into the program not finding the  session. This was fixed by storing the buffer at the frontend so the session information won't be lost even the user change their username.
#### Sprint 3:
**Implementation Detail Discussion on Anonymous User Uploading with Restrictions Issue105:**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/issues/105
We initially considered the option of creating a new table specifically for storing posts made by guest users. While this approach seemed viable at first, we quickly realised that it would complicate our overall procedure and make maintenance significantly more challenging. After discussions, we decided to create an anonymous user with user role  `anon_user`. This can achieve the same functionality and only require minimal changes to the existing database design. By adopting this approach, we ensure that managing anonymous posts remains straightforward and efficient, allowing us to maintain a cleaner and more organised systems.

**Implementation on number of Lines checking - Issue106:**
https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/issues/106
When addressing the line check for anonymous users who can upload text files with a maximum of 10 lines, we first agreed that counting the number of escape characters (`\n`) would be an effective way to determine the number of lines in a given file. Following this, we discussed the most appropriate place to perform this line check. 

Initially, we considered implementing the check on the backend. However, this approach introduced the complexities of encoding and decoding data, and since the frontend already manages the encoding and decoding processes, handling it again on the backend would create redundancy. 

After discussion, we concluded that it would be more efficient to perform the line check directly on the frontend. This decision simplifies the design of the system and improves the performance of the system by avoid doing repetitive tasks.

# Agile Tools and CI/CD Pipeline Practices
---

| Tool            | Functionality                                                                                                                                   |
| --------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| Gradle          | for build automation and dependency management.                                                                                                 |
| GitHub          | version control and collaborative code hosting.                                                                                                 |
| GitHub Projects | GitHub Projects as our Scrum board for agile project management.                                                                                |
| Jenkins         | Jenkins for continuous integration and continuous delivery (CI/CD).                                                                             |
| JUnit           | Unit testing our Java code. With Jacoco test report plugins and Jenkins together producing reports continuously to see changes in code coverage |

1. Gradle:
   Gradle is our build automation tool. It helps us manage project dependencies, compile source code, and package our application. Gradle's flexible build scripts allow us to customise our build process to suit our project's specific needs.

2. GitHub:
   GitHub serves as our central code repository. We use it for version control, allowing multiple team members to collaborate on the codebase simultaneously. GitHub's branching and pull request features support our agile development process by facilitating code reviews and maintaining a clean main branch.

3. GitHub Projects:
   We leverage GitHub Projects as our Scrum board. This tool helps us implement agile methodologies by organising our work into sprints. We use it to create and track user stories, tasks, and bugs, providing visibility into our project's progress and helping us manage our backlog effectively.

4. Jenkins:
   Jenkins is the cornerstone of our CI/CD pipeline. We use it to automate the build and deployment processes with linkage using webhook. Jenkins pulls our code from GitHub, builds it using Gradle, runs our JUnit tests, and if all tests pass, ideally in an industry environment, it can deploy our application to the appropriate environment. Yet for now we are using it with manual inspection to keep our application usable and swiftly acknowledging if a specific version of application is available to use (functionally correct). This automation helps us detect integration issues early and ensures that our main branch is always in a deployable state.

5. JUnit:
   JUnit is our chosen framework for writing and running unit tests. We use it to ensure the correctness of individual components of our Java code. By integrating JUnit tests into our Jenkins pipeline, we can automatically verify that new changes don't break existing functionality.

In our agile process, these tools work together to support our development workflow
The setup enables us to maintain a rapid, iterative development process while ensuring code quality and project visibility.

1. We break down project requirements into user stories and tasks, which we manage on our GitHub Projects Scrum board. This is always the case for both initial round of development and later sprints when we receive additional features.
2. Developers create feature branches in GitHub to work on these tasks.
3. As code is pushed to GitHub, Jenkins automatically triggers a build using Gradle and runs our JUnit tests.
4. If the build and tests pass, the code can be merged into the main branch through a pull request.
5. This cycle repeats for each sprint, allowing us to continuously integrate and deliver new features.

Jenkins setup: auto build and test coverage monitoring
![[jenkins1.png]]
Jenkins curve view update on sprint 2:
![[Screenshot 2024-10-17 at 8.07.42 PM.png]]
![[17171729155249_.pic 1.jpg]]
![[17181729155260_.pic.jpg]]
Jenkins Curve view update on sprint 3:
![[Screenshot 2024-10-24 at 5.24.33 pm.png]]

**Final test conclusion and visual analytics is in Appendix - Testing**
# Source Code and Version
---
## Sprint 1:
*Assignment release ~ Oct 9th*
**Tag:** https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/releases/tag/sprint1
## Sprint 2:
*Oct 10th ~ Oct 16th*
**Tag**: https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/releases/tag/sprint2
## Sprint 3:
Oct 17th ~ Oct 23rd
**Tag:** https://github.sydney.edu.au/SOFT2412-COMP9412-2024Sem2/Jing_Lab06_Group03_A2/releases/tag/sprint3

# Appendix - UML Diagram
---
![[Screenshot 2024-10-24 at 4.52.13 pm.png]]![[Screenshot 2024-10-24 at 4.52.23 pm.png]]
# Appendix - Testing
---
### Structure and Instruction
In this project, we use JaCoCo to measure test coverage. The specific workflow is as follows:
- Write unit tests.
- Run `gradle test` to execute the tests.
- Run `gradle jacocoTestReport` to generate the test coverage report.
- Review the generated report to ensure that most of the code logic is covered. If certain parts are not covered, write additional tests to improve the coverage.

Additionally, since the main function involves API-related interfaces, refer to Ed's post `#662`, we excluded `Main.java` from the test report.

Here is the JaCoCo Test Report coverage:

![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/jacocoReport_1.png?raw=true)

![My Image](https://github.com/Back04oorer/Notes/blob/main/2024%20sem2/SOFT2412/A2/Graphs/jacocoReport_2.png?raw=true)
#### Sprint 1
In this Sprint, the tests primarily used JUnit to test some of the functionalities of `SQLiteJDBC.java`. The main goal was to test whether `SQLiteJDBC.java` can provide a stable connection to the database and whether it can offer effective interfaces for implementing specific functionalities. Ideally, developers implementing specific features should be able to perform necessary operations without directly connecting to the database.

Since the logic concerning the `User` functionality has not been fully defined, only partial content was tested in this sprint. This section explains the rationale behind the design of each JUnit test, the types of tests (Normal, Edge cases), the inputs, and the expected outputs.
#### Sprint 2
In this sprint, we primarily focused on verifying the accuracy of functions such as Update and ViewAll. Some issues have not yet been resolved and will be addressed in Sprint 3.
#### Sprint 3
In this test, we aimed to cover as much of the code in `SQLiteJDBC.java` as possible, focusing primarily on the normalisation of the values returned by the code and the handling of exceptions.
### JUnit Test Cases

##### testDBConnect1
- **TestType**: Normal
- **Description**: Test to check if a valid database connection can be established and if the connection settings are correctly applied.
- **Input**: Database file `"src/test/resources/databases/db_1.db"`
- **Expected Output**:
    - The connection object should not be null.
    - Auto-commit should be disabled.
- **Result**: Pass
---
##### testDBConnect2
- **TestType**: Edge
- **Description**: Test to check if a non-existent database file is handled correctly.
- **Input**: Database file `"src/test/resources/databases/notexist.db"`
- **Expected Output**: Connection object should be null.
- **Result**: Pass
---
##### testAddUser1

- **TestType**: Normal
- **Description**: Test to verify that a user can be successfully added to the database, and that the user information is correctly stored and retrievable.
- **Input**:
    - Database file `"src/test/resources/databases/db_1.db"`
    - User information: `user_id = "testAddUser1"`, `username = "Junk Dog"`, `phone_number = "18258803672"`, `full_name = "Mingyuan Ba"`, `email = "2450707828@qq.com"`, `passwd = "111111"`, `role = "user"`
- **Expected Output**: User should be successfully inserted into the database and retrieved with correct details.
- **Result**: Pass
---
##### testAddUser2

- **TestType**: Edge (Primary Key Constraint)
- **Description**: Test to check if the database correctly enforces the Primary Key (PK) constraint when trying to insert a user with an existing user_id.
- **Input**: User with `user_id = "u002"` (existing ID).
- **Expected Output**: Status code `2` indicating a PK constraint violation.
- **Result**: Pass
---
##### testAddUser3

- **TestType**: Edge (Unique Constraint)
- **Description**: Test to check if the database correctly enforces a unique constraint when adding a user with the same `user_id` but different username.
- **Input**: User with `user_id = "u002"`, `username = "Alex"` (existing ID with a different username).
- **Expected Output**: Status code `2` indicating a unique constraint violation.
- **Result**: Pass
---
##### testAddUser4
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check if the database handles a bad or invalid connection string properly.
- **Input**: Invalid database file `"src/test/resources/databases/invalid_db_string.db"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass
---
##### testIfUserExists1
- **TestType**: Normal
- **Description**: Test to verify that the database correctly checks for the existence of an existing user in the database.
- **Input**: `user_id = "u001"`
- **Expected Output**: Status code `0` indicating the user exists.
- **Result**: Pass
---
##### testIfUserExists2

- **TestType**: Edge
- **Description**: Test to verify that the database correctly handles a query for a non-existent user.
- **Input**: `user_id = "nobody"`
- **Expected Output**: Status code `2` indicating the user does not exist.
- **Result**: Pass
---
##### testIfUserExists3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the database handles the scenario where a connection to a non-existent database is attempted when querying for a user.
- **Input**: Database file `"src/test/resources/databases/nothingHere.db"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass
---
##### testIfUsernameExist1
- **TestType**: Normal
- **Description**: Test to verify if a valid username exists in the database.
- **Input**: Username `"alex_jones"`
- **Expected Output**: Status code `0` indicating the username exists.
- **Result**: Pass
---
##### testIfUsernameExist2
- **TestType**: Edge
- **Description**: Test to verify how the system handles checking for a non-existent username.
- **Input**: Username `"ImNotHere"`
- **Expected Output**: Status code `2` indicating the username does not exist.
- **Result**: Pass
---
##### testIfUsernameExist3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles checking a username when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/NotHere.db"`, Username `"alex_jones"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass
---
##### testGetUserId1
- **TestType**: Normal
- **Description**: Test to verify that the correct user ID is returned when querying by a valid username.
- **Input**: Username `"emily_white"`
- **Expected Output**: User ID `"u004"`
- **Result**: Pass
---
##### testGetUserId2
- **TestType**: Edge
- **Description**: Test to check how the system handles querying a user ID for a non-existent username.
- **Input**: Username `"OMG"`
- **Expected Output**: Result should be null indicating no user found.
- **Result**: Pass
---
##### testGetUserId3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a user ID when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/TOOBAD.db"`, Username `"emily_white"`
- **Expected Output**: Result should be null indicating a failure to retrieve data.
- **Result**: Pass
---
##### testGetPassWd1
- **TestType**: Normal
- **Description**: Test to verify the correct password is returned when querying by a valid user ID.
- **Input**: User ID `"u001"`
- **Expected Output**: Password `"password123"`
- **Result**: Pass
---
##### testGetPassWd2
- **TestType**: Edge
- **Description**: Test to check how the system handles querying the password for a non-existent user ID.
- **Input**: User ID `"FaNna"`
- **Expected Output**: Result should be null indicating no password found.
- **Result**: Pass
---
##### testGetPassWd3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a password when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/SYF.db"`, User ID `"u001"`
- **Expected Output**: Result should be null indicating a failure to retrieve data.
- **Result**: Pass
---
##### testIfAccessExist1
- **TestType**: Normal
- **Description**: Test to verify if a valid access record exists in the database.
- **Input**: Access ID `"a001"`
- **Expected Output**: Status code `0` indicating the access record exists.
- **Result**: Pass
---
##### testIfAccessExist2
- **TestType**: Edge
- **Description**: Test to verify how the system handles checking for a non-existent access record.
- **Input**: Access ID `"a010"`
- **Expected Output**: Status code `2` indicating the access record does not exist.
- **Result**: Pass
---
##### testIfAccessExist3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles checking an access record when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/so_sad.db"`, Access ID `"a010"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass
---
##### testUpdateScroll1
- **TestType**: Normal
- **Description**: Test to verify that a scroll record can be updated successfully with a valid input.
- **Input**:
    - Scroll ID `"001"`
    - Scroll name `"CaiJiaWu"`
    - Scroll description `"Scroll of Wisdom"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-01"`
- **Expected Output**: Status code `0` indicating successful update.
- **Result**: Pass
---
##### testUpdateScroll2
- **TestType**: Edge
- **Description**: Test to verify how the system handles updating a scroll record with invalid data.
- **Input**:
    - Scroll ID `"002"`
    - Scroll name `"Monkeyyyyyyy"`
    - Scroll description `"NAHHH"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `2` indicating an error with the update.
- **Result**: Pass
---
##### testUpdateScroll3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles updating a scroll record when the database connection is invalid.
- **Input**:
    - Invalid database `"src/test/resources/databases/badbad.db"`
    - Scroll ID `"003"`
    - Scroll name `"Monkeyyyyyyy"`
    - Scroll description `"Scroll of Power"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `1` indicating a failure to connect to the database.
- **Result**: Pass
---
##### testUpdateScroll4
- **TestType**: Edge
- **Description**: Test to verify how the system handles updating a scroll record with specific data.
- **Input**:
    - Scroll ID `"004"`
    - Scroll name `"Mystic Scroll"`
    - Scroll description `"Ancient Scroll"`
    - Blob data representing a `.mp4` file.
    - Scroll date `"2024-06-03"`
- **Expected Output**: Status code `2` indicating an error with the update.
- **Result**: Pass
---
##### testUpdateAddScroll1
- **TestType**: Normal
- **Description**: Test to verify that a scroll record can be added successfully with valid inputs.
- **Input**:
    - Scroll ID `"010"`
    - Scroll name `"Scroll of Rubin"`
    - User ID `"u004"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `0` indicating successful addition.
- **Result**: Pass
---
##### testUpdateAddScroll2
- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll record with an invalid user ID.
- **Input**:
    - Scroll ID `"010"`
    - Scroll name `"Scroll of Rubin"`
    - User ID `"bibabu"` (invalid)
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating a failure due to invalid user ID.
- **Result**: Pass
---
##### testUpdateAddScroll3
- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll record with an invalid scroll ID.
- **Input**:
    - Scroll ID `"011"`
    - Scroll name `"Scroll of Power"`
    - User ID `"u011"` (invalid)
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating failure due to invalid scroll ID.
- **Result**: Pass
---
##### testUpdateAddScroll4
- **TestType**: Edge
- **Description**: Test to verify how the system handles adding a scroll with a duplicate scroll ID.
- **Input**:
    - Scroll ID `"001"` (duplicate)
    - Scroll name `"Scroll of Death"`
    - User ID `"u011"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `2` indicating failure due to a duplicate scroll ID.
- **Result**: Pass
---
##### testUpdateAddScroll5
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles adding a scroll record when the database connection is invalid.
- **Input**:
    - Invalid database `"src/test/resources/databases/badbad.db"`
    - Scroll ID `"001"`
    - Scroll name `"Scroll of Death"`
    - User ID `"u011"`
    - Blob data representing a `.jpg` image.
    - Scroll date `"2024-06-10"`
- **Expected Output**: Status code `1` indicating failure to connect to the database.
- **Result**: Pass
---
##### testGetSingleUser1
- **TestType**: Normal
- **Description**: Test to verify that a single user's information can be retrieved from the database.
- **Input**: User ID `"u002"`
- **Expected Output**: User object with correct details for `user_id = "u002"`, `username = "jane_smith"`, `phone_number = "0987654321"`, etc.
- **Result**: Pass
---
##### testGetSingleUser2
- **TestType**: Edge
- **Description**: Test to check how the system handles querying for a non-existent user.
- **Input**: User ID `"DoesNotExist"`
- **Expected Output**: Result should be null indicating no user found.
- **Result**: Pass
---
##### testGetSingleUser3
- **TestType**: Edge (Bad Connection)
- **Description**: Test to check how the system handles querying a user when the database connection is invalid.
- **Input**: Invalid database `"src/test/resources/databases/Bad.db"`, User ID `"DoesNotExist"`
- **Expected Output**: Result should be null indicating failure to retrieve data.
- **Result**: Pass
---
##### testInsertAccess1
- **TestType**: Normal
- **Description**: Test to verify that an access record can be inserted successfully with valid inputs.
- **Input**:
    - Access ID `"a006"`
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"U"`
- **Expected Output**: Status code `0` indicating successful insertion.
- **Result**: Pass
---
##### testInsertAccess2
- **TestType**: Edge
- **Description**: Test to verify how the system handles inserting an access record with invalid access type.
- **Input**:
    - Access ID `"a006"`
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"XX"` (invalid)
- **Expected Output**: Status code `1` indicating failure due to invalid access type.
- **Result**: Pass
---
##### testInsertAccess3
- **TestType**: Edge
- **Description**: Test to verify how the system handles inserting an access record with a duplicate access ID.
- **Input**:
    - Access ID `"a003"` (duplicate)
    - Scroll ID `"006"`
    - User ID `"u002"`
    - Access date `"2024-06-10"`
    - Access type `"U"`
- **Expected Output**: Status code `2` indicating failure due to duplicate access ID.
- **Result**: Pass
--- 
##### testInsertAccess4
- **TestType**: Edge
- **Description**: Test to verify insertion with specific inputs.
- **Input**: Access ID `"a003"`, Scroll ID `"1"`, User ID `"u002"`, Access date `"2024-06-10"`, Access type `"U"`
- **Expected Output**: Status code `1` indicating an error with the insertion.
- **Result**: Pass
--- 
##### testViewAllUser1
- **TestType**: Normal
- **Description**: Test to verify the correct retrieval of all users from the database.
- **Input**:
    - Database: `src/test/resources/databases/db_1_src.db`
- **Expected Output**: Array list of 5 users, with the first user having:
    - User ID: `"u001"`
    - Username: `"john_doe"`
    - Full name: `"John Doe"`
    - Email: `"john.doe@example.com"`
    - Password: `null`
    - Role: `"admin"`
- **Result**: Pass

---

##### testViewAllUser2

- **TestType**: Bad Connection
- **Description**: Test to check system behavior when attempting to retrieve users from a non-existent database file.
- **Input**:
    - Database: `src/test/resources/databases/NOTHERE`
- **Expected Output**: Empty user list, indicating no data found.
- **Result**: Pass

---
##### testUpdateUser1
- **TestType**: Normal
- **Description**: Test to verify the successful update of all user attributes except the `user_id`.
- **Input**:
    - User ID: `"u001"`
    - Username: `"john_doe"`
    - Full Name: `"Brian Ba"`
    - Email: `"2450707828@qq.com"`
    - Password: `"password"`
    - Role: `"user"`
- **Expected Output**: Status code `0`, indicating the update was successful.
- **Result**: Pass
---
##### testUpdateUser2
- **TestType**: Edge
- **Description**: Test to verify unique constraint of the username when updating user details.
- **Input**:
    - Original User ID: `"u001"`, New Username: `"jane_smith"`, Email: `"john.doe@example.com"`
- **Expected Output**: Status code `2`, indicating failure due to a duplicate username.
- **Result**: Pass
---
##### testUpdateUser3
- **TestType**: Edge
- **Description**: Test to verify the system's handling of an update attempt for a non-existent user.
- **Input**:
    - Original User ID: `"u001"`
    - New Username: `"NotMe"`
    - Full Name: `"NotMe"`
    - Email: `"john.doe@example.com"`
    - Password: `"password123"`
    - Role: `"admin"`
- **Expected Output**: Status code `2`, indicating the update failed due to user mismatch or conflict.
- **Result**: Pass
---
##### testUpdateUser4
- **TestType**: Edge
- **Description**: Test to verify successful update of all attributes for a different user.
- **Input**:
    - User ID: `"u009"`, Username: `"john_doe"`, Full Name: `"John Doe"`, Email: `"john.doe@example.com"`
- **Expected Output**: Status code `0`, indicating success.
- **Result**: Pass
---
##### testUpdateUser5
- **TestType**: Edge
- **Description**: Test to verify primary key constraint when updating the `user_id` to an existing one.
- **Input**:
    - Original User ID: `"u002"`, New User ID: `"u001"`
- **Expected Output**: Status code `2`, indicating primary key conflict.
- **Result**: Pass
---
##### testUpdateUser6
- **TestType**: Edge
- **Description**: Test to verify the system's behavior when attempting to update a non-existent `user_id`.
- **Input**:
    - User ID: `"u009"`
    - Username: `"john_doe"`
    - Full Name: `"junk_dog"`
    - Email: `"john.doe@example.com"`
    - Password: `"password123"`
    - Role: `"admin"`
- **Expected Output**: Status code `2`, indicating failure due to no such `user_id` existing.
- **Result**: Pass
---
##### testUpdateUser7
- **TestType**: Edge
- **Description**: Test to verify behavior when updating a user in a non-existent database.
- **Input**:
    - Database: `NotHere.db`
- **Expected Output**: Status code `1`, indicating database connection failure.
- **Result**: Pass
---
##### testDeleteUser1
- **TestType**: Normal
- **Description**: Test to verify the successful deletion of an existing user.
- **Input**:
    - User ID: `"u001"`
- **Expected Output**: Status code `0`, indicating successful deletion.
- **Result**: Pass
---
##### testDeleteUser2

- **TestType**: Edge
- **Description**: Test to verify failure when attempting to delete a non-existent user.
- **Input**:
    - User ID: `"u009"`
- **Expected Output**: Status code `2`, indicating user does not exist.
- **Result**: Pass
---
##### testDeleteUser3
- **TestType**: Edge
- **Description**: Test to verify failure when attempting to delete a user from a non-existent database.
- **Input**:
    - Database: `NotMe.db`
- **Expected Output**: Status code `1`, indicating connection failure.
- **Result**: Pass
---
##### testDeleteScroll1
- **TestType**: Normal
- **Description**: Test to verify the successful deletion of a scroll.
- **Input**:
    - Scroll ID: `"003"`
- **Expected Output**: Status code `0`, indicating successful deletion.
- **Result**: Pass
---
##### testDeleteScroll2
- **TestType**: Edge
- **Description**: Test to verify failure when attempting to delete a non-existent scroll.
- **Input**:
    - Scroll ID: `"010"`
- **Expected Output**: Status code `2`, indicating no such scroll exists.
- **Result**: Pass
---
##### testDeleteScroll3
- **TestType**: Edge
- **Description**: Test to verify failure when attempting to delete a scroll in a non-existent database.
- **Input**:
    - Database: `BADBAD.db`
- **Expected Output**: Status code `1`, indicating connection failure.
- **Result**: Pass
---
##### testStatForAccess1
- **TestType**: Normal
- **Description**: Test to verify the correct retrieval of access statistics.
- **Input**:
    - Database: `db_7.db`
- **Expected Output**: Array list of 5 access records.
- **Result**: Pass
---
##### testStatForAccess2
- **TestType**: Edge
- **Description**: Test to verify failure when attempting to retrieve access statistics from a non-existent database.
- **Input**:
    - Database: `bad.db`
- **Expected Output**: Empty access list, indicating no data found.
- **Result**: Pass
---
##### testGetNextScrollId1
- **TestType**: Normal
- **Description**: Test to verify the retrieval of the next scroll ID.
- **Input**:
    - Database: `db_7.db`
- **Expected Output**: Next scroll ID is `8`.
- **Result**: Pass
---
##### testGetNextScrollId2
- **TestType**: Normal
- **Description**: Test to verify the retrieval of the next scroll ID from a different database.
- **Input**:
    - Database: `db_7.db`
- **Expected Output**: Next scroll ID is `11`.
- **Result**: Pass
---
##### testGetNextScrollId3
- **TestType**: Edge
- **Description**: Test to verify failure when attempting to retrieve the next scroll ID from a non-existent database.
- **Input**:
    - Database: `badbad.db`
- **Expected Output**: Next scroll ID is `-1`, indicating failure.
- **Result**: Pass
---
##### testGetAnon1
- **TestType**: Normal
- **Description**: Test to verify the correct retrieval of the anonymous user ID.
- **Input**:
    - Database: `db_8.db`
- **Expected Output**: Anonymous user ID is `"u005"`.
- **Result**: Pass
---
##### testGetAnon2
- **TestType**: Normal
- **Description**: Test to verify that the system correctly handles no available anonymous user.
- **Input**:
    - Database: `db_8.db`
- **Expected Output**: `null`, indicating no anonymous user.
- **Result**: Pass
