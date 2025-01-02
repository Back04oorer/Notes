
## CI
**Definition:**
“A software development **practice** where members of a team integrate their work **frequently**, usually each person integrates **at least daily** - leading to **multiple integrations per day**. Each integration is verified by an **automated build** (including test) to detect **integration errors** as quickly as possible-Martin Fowler, 2006
**Goal:**  ensure that the software is always in a working state
**Objectives:** 
- Minimize the duration and effort required by each integration
- Be able to deliver product version suitable for release at any moment
- To <mark style="background: #FF5582A6;">achieve</mark> these objectives:
	- Integration procedure which is reproducible
	- Largely automated integration
**Costs:** Bugs are caught much earlier in the delivery process when they are cheaper to fix, providing significant cost and time savings
#### Implementation
- version control tool (e.g., CVS, SVN, Git)
- automated build process
- Commitment and discipline from development teams (practice
- Configuration of the system build and testing processes
- Use of CI server to automate the process of integration, testing and reporting of test results
#### CI WorkFlow
1. A developer check-out the system mainline from the VC system
2. Build the system and run automated tests to ensure the build system passes
	1. Tests not passed, notify the developer who check-in the last baseline of the system
3. They make the changes to the system component
4. They build the system in a private workspace and rerun system tests
	1. if the tests fail, they continue editing
5. System passed tests; they check it into the build system server
6. Build the system on the build server and run the tests
	1. Changes committed by other developers since last check-out, these need to be checked out and tests should pass on the developer’s private space
7. Commit the changes they have made in the system mainline
#### CI Practices
**Automation Practices**
- Regular Check-in: Developers to check in their code into the mainline regularly 
	- Small changes less likely to break the build
- Create a comprehensive automated test suite
	- Unit Testing
	- Component Testing: test the behavior of several components
	- Acceptance Testing: test if the application meets the acceptance criteria set by end user
- Keep the build and test process short
	- CI will likely miss multiple commits if it takes too long
	- Lead to less check-ins as developers wait for the software to build and tests to run
- Developers to manage their development workspace
	- They should be able to build, run automated tests and deploy on their local machines using the same processes used in the CI
- Use CI software
	- CI activities often has 2 components
		- CI Workflow execution
		- CI results visualization and reporting
**Team Practices**
- Don’t Check in on a broken build
- Always run all commit tests locally, or get your CI server to do it for you
- Wait for commit Tests to pass before moving on
- Never Go Home on a Broken Build
- Always be prepared to revert to the previous revision
- Time-box fixing before reverting
	- Set a team rule; fixed it within X-minute or revert
- Do not comment out failing tests
- Take responsibility for all breakages that result from your changes
## Continuous Delivery
**Definitions:**
- Continuous Delivery is a software development discipline where you build software in such away that the software can be **released** to production **at any time**
- **Release new changes** to customers quickly and in reproducible way
- Automate the release process so you can deploy your application at any time
**Motivation:** 
- Our highest priority is to satisfy the customer through **early and continuous delivery of valuable software**
- Cycle time in hours not months, faster iteration
- Quality should be built-in to the process
- Late feedback is expensive!
**Deployment Pipeline**
- An automated implementation of the application’s build, deploy, test and release process
- Has its foundations in the process of CI
## Continuous Deployment
**Definitions:**
- The practice of releasing every good build to users – a more accurate name might have been 'continuous release
**Continuous Delivery Vs Continuous Deployment**：
- Continuous Deployment (CD) goes a step further than Continuous Delivery. In Continuous Delivery, deploying to the production environment still requires <mark style="background: #FF5582A6;">manual</mark> intervention, and it’s up to the team to decide how frequently updates are pushed to production. In contrast, Continuous Deployment <mark style="background: #FF5582A6;">automates</mark> this process as much as possible, so if the build and tests pass, the update is <mark style="background: #FF5582A6;">immediately deployed</mark> to production. This approach also significantly raises the requirements for configuration and testing standards.
**Deployment Automation:**
- Deployments should tend towards being fully automated
	- Pick version & environment
	- Press “**deploy**” button
- Automated deployment scripts should be up-to-date
- **Don’t** depend on the **deployment expert**
- Automated deployment process:
	- Cheap and easy to test
	- Fully auditable
	- Should be the only way in which the software is ever deployed

## Jenkins
**Definition:**
- Jenkins is an automation server to automate tasks related to building, testing, and delivering or deploying software .
- Jenkins pipeline supports implementing and <mark style="background: #FF5582A6;">integrating CD pipelines into Jenkins</mark>
	- CD pipeline is an automated expression of your delivery process
	- Written in *Jenkinsfile*
- Building, testing and deploying activities are defined as <mark style="background: #FF5582A6;">stages</mark> and <mark style="background: #FF5582A6;">steps</mark>
- Jenkins allow composing different steps (commands) to model simple and complex automation processes
**Special steps:**
- **Retry**: retrying steps a number of times
- **Timeout**: exiting if a step takes long times
- **Clean-up:** to run clean-up steps or perform **post** actions based on the outcome of the pipeline when it finishes executing
**Execution Environment**
- The **agent** directive specifies how to execute pipeline
	- All steps contained within the block are queued for execution
	- A workspace is allocated which will contain files checked out from source control and any additional files for the pipeline
- Pipeline is designed to use **Docker** images and containers to run inside
	- No need to configure various tools and dependencies on agents manually
**Recording Tests and Artifact**
- JUnit already bundled with Jenkins
- Jenkins can record and aggregate all test results and artifacts
	- Reported through using the **post** section
	- A pipeline that has failing tests will be marked as **UNSTABLE**
- Jenkins stores files generated during the execution of the pipeline (artifacts)
**Cleaning and Notification**
- **Post** section can be used to specify clean up tasks, finalization or notifications
	- Post section will run at the end of the pipeline execution
	- lean up our workspace, delete unneeded directories
- **Notifications** can be set-up to
	- Send emails
	- Post on Slack or other
- Notification can set-up when things are failing, unstable, or even succeeding

