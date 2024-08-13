# Week 1
### 1.Software Process(Software Develop Process)
##### 1.1 Common activities
- Specification (software / system requirements)
- Design and implementation
- Validation (testing)
- Evolution

Software processes are complex and, rely on <mark style="background: #FF5582A6;">people making decisions and judgements</mark>

Activities are complex and include sub-activities.
### 2.Software Process Models
##### 2.1 Definition
- Also called Software Development Lifecycle (SDLC)
- Description of a process from particular perspective 
- Describe the <mark style="background: #FF5582A6;">activities and their sequence</mark> but may not the roles of people 

##### 2.2 Representative Software Process Models
- Waterfall Models
- Spiral Models
	- Incremental Development risk-driven 
	- Incremental: Add <mark style="background: #FF5582A6;">new functionalities</mark>
- Agile Model
	- Iterative incremental process for rapid software development
	- Iterative: <mark style="background: #FF5582A6;">Refining and Improving</mark> the existing system
- The Rational Unified Process (RUP or UP)
	- Bring together elements of different process models
	- Phases of the model in timer, process activities, good practices
### 3.WaterFall Model - Heavy-Weight Model
##### 3.1 Definitions
- linear/sequential model
![[Graphs/w1_1.png]]

| Development activities                                                     | Teams                                                                               |
| -------------------------------------------------------------------------- | ----------------------------------------------------------------------------------- |
| Divide the work into stages                                                | A separate team of specialists for each stage                                       |
| At each stage, the work is passed from one team to another                 | Some coordination is required for the handoff from team to team - using "documents" |
| At the end of all of the stages, you have a software product ready to ship | As each team finishes, they are assigned to a new product                           |
|                                                                            |                                                                                     |

##### 3.2 Waterfall Model Phases
- <mark style="background: #FF5582A6;">Requirement</mark>'s analysis and definition
	- Deliverable: Requirements doc. (<mark style="background: #FF5582A6;">How we produce this doc?</mark> : ### 4.Requirements Engineering Process)
- System and software <mark style="background: #FF5582A6;">design</mark>
	- Deliverable: Design document based on requirements doc.
- <mark style="background: #FF5582A6;">Implementation </mark> and unit <mark style="background: #FF5582A6;">testing</mark>
	- Deliverable: Code and test it for system components(using design doc.)
- <mark style="background: #FF5582A6;">Integration</mark> and system <mark style="background: #FF5582A6;">testing</mark>
	- Deliverable:  Software components are integrated and the resulting system is tested
- Operation and maintenance

##### 3.3 Advantages
- Easy to understand and implement
- Identified deliverables(成果) and milestones(里程碑) 明确的成果物和里程碑
##### 3.4 Disadvantages
- Intensive documenting and planning
- Discovering issues in later phases should lead to returning to earlier phase
### 4.Requirements Engineering Process
 *How we can conduct a requirement document*
![[Graphs/w1_2.png]]

- Requirements elicitation and analysis: 需求获取和分析
- System descriptions: what the system should be
- Requirements specification: detailed specification.*If we meet any problem, always go back to the previous stage* <mark style="background: #FF5582A6;">Requirements elicitation and analysis</mark>
- Requirements validation: 需求验证. Whether this **Requirements specification**  can actually be done or actually be finished within schedule, within budget.
- User and System requirements

### 5.Planning in Software Development

- Plan-driven (plan-and-document / heavy-weight)
	- Activities are planned <mark style="background: #FF5582A6;">in advance</mark> and progress is <mark style="background: #FF5582A6;">measured against this plan</mark>
	- Plan drives everything and <mark style="background: #FF5582A6;">change is expensive</mark>
- Agile processes (light-weight)
	- Planning is incremental and continual as the software is developed
	- Easier to change to reflect changing requirements
- Most Software processes include elements of both plan-driven and agile
- Each approach is suitable for different types of software
	- No right or wrong software processes
##### Planning in Waterfall Model
-  Difficulty of accommodating change after the process is underway
- Inflexible partitioning of the project into distinct stages makes it difficult to respond to changing customer requirements
- <mark style="background: #FF5582A6;">Mostly used for</mark> larger systems engineering projects where a system is developed at <mark style="background: #FF5582A6;">several sites</mark>
	- The plan-driven nature of the waterfall model helps coordinate the work
### 6. Software Evolution
- Software is inherently flexible and can change
- As requirements change through changing business circumstances, the software that supports the business must also evolve(演进) and change
- Business software needs to respond to rapidly changing mar
	- Time-to-market
- Plan-driven software development processes are not suitable for certain types of SW systems

### 7. Rational Unified Process (RUP or UP)
![[Graphs/w1_3.png]]
- Is a incremental and iterative process
- Software development process utilizing <mark style="background: #FF5582A6;">iterative and risk-driven approach</mark> to develop <mark style="background: #FF5582A6;">OO software systems </mark>
- Iterative incremental development 每个迭代周期都会在已有系统的基础上添加新的功能或特性，使系统逐步完善
- Iterative evolutionary development 系统在每次迭代中不断演化和改进，不仅仅是增加新功能，还包括优化和调整已有功能

### 8. Agile Development Model (briefly)
##### Goal
Intend to develop systems more quickly with limited time spent on analysis and design

##### 敏捷宣言（Agile Manifesto）
- Individuals and interactions over processes and tools **个人和互动胜过流程和工具**
- Working software over comprehensive documentation
- Customer collaboration over contract negotiation **客户合作胜过合同谈判**
- Responding to change over following a plan

##### Agile Process
- Agile advocates(倡导者) believe:
	- Current SW development processes are too heavy-weight or cumbersome(复杂)
	- Current software development is too rigid(僵化)
		- Incomplete or changing requirement
	- More active customer involvement needed

- Agile methods are considered
	- Light-weighted
	- People-based rather than Plan-based

-  Several agile methods
	- No single agile method
	- Extreme Programming(XP) 极限编程, Scrum 一种框架

- Agile Manifesto closest to a definition
	- Set of principles
	- Developed by Agile Alliance 什么是tmd 敏捷联盟啊我日
后面在放屁,它考了我吃

##### Agile - Requirements process
- There is no 'standard way' to do requirements in Agile development
	- Could be a normal 'Software Requirements Document'
	- But it is better to be more lightweight
	- Based on user stories
	- In each iteration, elaborate a small set of the functional requirements (the high-priority behavior
	- For some key requirements, create some acceptance tests at the same time as you write the requirements

##### Agile - Questions and Challenges?
- Documentation
	- Still important in an Agile development 
	- If it is the only kind of communication in your project, it isn’t good 
	- Real working code is more valuable than document
- Development plans
	- Still important in an Agile project 
	- the format of an Agile development schedule is a bit different 
	- Development plan includes “iterations” 
	- Each iteration gives the team a chance to incorporate what they learn
后面纯在放屁




# Week 2

### 1.Activities and artifacts
##### 1.1 Software Artifact - Software Requirements Specification
- Eliciting these from users <mark style="background: #FF5582A6;">was a lot of effort</mark>
- Different formats in various process methodologies
	- In agile, often as<mark style="background: #FF5582A6;"> “User stories” (plain text, stylized format</mark>, can be manipulated by tools)
	- In <mark style="background: #FF5582A6;">traditional methodologies</mark>, often <mark style="background: #FF5582A6;">huge Word documents</mark> in a standard template
		- Software Requirements Specification (SRS)
		- Signed off as obligation on the developers
- <mark style="background: #FF5582A6;">Track changes</mark> and <mark style="background: #FF5582A6;">versions</mark> of requirements is very important (<mark style="background: #FF5582A6;">Evolution</mark>)

##### 1.2 Software Artifacts - Code
- Spread over different files, and in a directory structure
- Language conventions or requirements
	- E.g., Java has a file for each class
		- Package hierarchy
- Documentation may be derived from the source code
- <mark style="background: #FF5582A6;">Maintainability</mark> and <mark style="background: #FF5582A6;">extensibility</mark> <mark style="background: #FF5582A6;">non-functional properties</mark> for a software
	- <mark style="background: #FF5582A6;">Design</mark> and <mark style="background: #FF5582A6;">architecture</mark> play important role
	- E.g., Model-View-Controller
	- Layered architecture
- evolve rapidly

##### 1.3 Artifacts
- Items that represent work done, in ways that others can use 
	- Codes,Requirements specifications, meeting minutes(会议记录)
- Artifact go through evolution 
- The Artifacts have value and need to be preserved(保存), communicated, maintained, protected from unauthorized access, etc.

### 2.Version Control
##### 2.1 What is Version Control?
A method for <mark style="background: #FF5582A6;">recording changes to a file</mark> or set of files over time so that you can recall specific versions later
- aka revision control and source control
- Create, maintain and track history of changes during the SDLC for all Artifacts


##### 2.2 What is Version Control System (VCS)?
Software tools to software teams to <mark style="background: #FF5582A6;">manage changes</mark> to source code over time
- Keep track of every modification to code in a repository
- Revert selected files back to a previous state
- Compare changes over time
- See who last modified something that might be causing a problem
- Who introduced an issue and when
- compare earlier versions of the code to help fix bugs while minimizing disruption(中断,干扰) to all team members

##### 2.3 Local Version Control
- Programmers long ago developed local VCSs;
- E.g., Revision Control System (RCS) early VCS
- RCS works by keeping patch sets (i.e., the <mark style="background: #FF5582A6;">differences between files</mark>)
- RCS 的工作原理是通过保留补丁集（即文件之间的差异）来进行版本控制

##### 2.4 Centralized Version Control (CVC)
- CVCSs support collaborative development
- A single server contains all versioned files and a number of clients check-out files from it
- Better than local VCS
	- Everyone is updated
	- Easier admin - fine-grained(细粒度)的 control
- Single point of failure
	- Developer’s work interrupted!
	- Hard disk becomes corrupted, and no proper/up-to-date backups? <mark style="background: #FF5582A6;">entire history lost</mark>!
- Users can only check out the snapshot of the project.Users can not fully mirror the project

##### 2.5 Distributed Version Control (DVC)
- Developers fully mirror the repository <mark style="background: #FF5582A6;">including the full history</mark>
- Several remote repositories
	- Developers can collaborate with different groups of people in different ways simultaneously with the same project
	- Can setup several types of workflows (not possible in CVC) 

### 3.Version Control with Git
##### 3.1 Delta-based VCSs (<mark style="background: #FF5582A6;">Differences</mark>)
- VCSs that store as a set of files and the changes made to each file over time
	- Example: CVS(Concurrent Versioning System) and subversion
 ![[Graphs/w2_1.png]]
##### 3.2 Git - Snapshots Not Differences
 Git thinks about its data as a streams of snapshots of a small file system Git doesn’t store unchanged files, it just link to previous identical file already stored   
  ![[Graphs/w2_2.png]]
File A + $\Delta 1$ = A1
$\Delta 1$ : Delta-based
A1 : snapshot-based

##### 3.3 Git - Basics
- Nearly Every operation is local
- Git has built-in Integrity
	- Using check-sum (SHA- 1 hash) before it is stored and is reference it
	- Git stores everything in its database by the hash value of its contents
- Git generally only adds data
- Git has three states
	- <mark style="background: #FF5582A6;">Modified</mark> means that you have changed the file but have not committed it to your database yet.
	- <mark style="background: #FF5582A6;">Staged</mark> means that you have marked a modified file in its current version to go into your next commit snapshot.
	- <mark style="background: #FF5582A6;">Committed</mark> means that the data is safely stored in your local database.

##### 3.4 Git - Structure
- Working directory (tree)
	- A single checkout of one version of the project
	- These files are pulled out of the compressed database and placed on disk for you to use or modify
		**目录树（Tree）**：在Git中，一个“tree”对象代表一个目录，它包含指向该目录中所有文件（blob对象）和子目录（tree对象）的指针。每个指针都由一个文件或子目录的名称和一个SHA-1哈希值组成。
-  Staging area (index)
	- A file stores information about what will go into next commit
		**索引（Index）**：索引是一个文件，存储了暂存区的状态。它包含了即将被提交的文件的信息，比如文件名、路径、文件内容的哈希值等。
-  Git directory (repository)
	- Metadata and object database
	- What is copied when you clone a repository from another computer

### 4. Git Concepts and Scenarios

##### 4.1 Git Repository (Repo)
A special directory contains project files 
- Where git stores and tracks files (source code) 
- Can be created or cloned  
- Git adds special sub-directory to store history of changes about the project’s files and directories

- Creating a git repo on your local machine
```shell
git init
```

- Clone an existing git repository from elsewhere
- a full copy of all data that the server has
```Shell
git clone
```

##### 4.2 Metadata
- Each version(<mark style="background: #FF5582A6;">commit</mark>) should have:
	- Unique name to refer to it
		- Latest version: <mark style="background: #FF5582A6;">Head</mark>
	- Date
	- Author
-  How might you use metadata?
	- Who, When did what – Who to explain the c
	- Who to explain the change
	- Who takes responsibility (fix issue)

##### 4.3 Git-Recording Changes to a Repo
![[Graphs/w2_3.png]]

##### 4.4 Git-branching
- Diverging from the main line of development and continue messing with that main line 
	- Expensive process; often requires you to create a new code directory

- Git branching is lightweight (nearly instantaneous) 
	- With every commit, Git stores a commit object that contains 
		- A pointer to the staged snapshot, author’s name and email, commit message, and commit/commits before this commit, parent/parents commits
	![[Graphs/w2_4.png]]

- If you make some changes and commit again, the <mark style="background: #FF5582A6;">next commit stores a pointer to the commit that came immediately before it.</mark>

##### 4.5 Git Branching – Pointer’s Perspective
- lightweight movable pointer to one of the commits
- Default branch called <mark style="background: #FF5582A6;">“master”</mark>
- As you start making commits, you’re given a master branch that points to the last commit you made
- Every time you commit, the master branch pointer moves forward automatically

![[Graphs/w2_5.png]]
##### 4.6 Git – Creating a New Branch
- Example: create a new branch called testing in our project
```shell
git branch testing
```
- When you create a new branch, a new pointer will be created – pointing to the same commit we are <mark style="background: #FF5582A6;">currently at</mark>
![[Graphs/w2_6.png]]
- Git knows which branch is the current by maintaining a special pointer called <mark style="background: #FF5582A6;">“HEAD”</mark>
- Creating a branch in Git does not switch the HEAD to the new branch
![[Graphs/w2_7.png]]
##### Git - Switching to another Branch (1)

You can switch to an existing branch using Git checkout command
```shell
git checkout testing
```



