## Requirements Engineering

#### Plan-and-Document Software Methodologies
**Goal** is to make Software Engineering predicable in <mark style="background: #FF5582A6;">budget</mark> and <mark style="background: #FF5582A6;">schedule</mark>
- Requirements elicitation
- Requirements documentation
- Cost estimation
- Scheduling and monitoring schedule
- Change management for requirements, cost and schedule (<mark style="background: #FF5582A6;">expensive</mark>)
- Ensuring implementation matches requirement features
- Risk analysis and management
#### Requirements Elicitation
##### Techniques
- Interview stakeholders
	- Information discussions and/or formal questions
- Cooperatively create scenarios
	- Initial state, happy and sad paths, concurrent and final states
- Create Use Cases
	- User and system interactions to realize functions (using UML case diagrams
##### Functional Requirements
- Details matter: exactly what information goes in and out
- Interactions between features
- Not only when things go well
##### Non-functional Requirements
- Include performance, security, usability
- different stakeholders often have different thoughts on this
#### Requirements Documentation
- Software Requirements Specifications **(SRS)** process
- Stakeholders to read **SRS** document, build basic prototype, or generate test cases to **check**:
	- Validity
	- Consistency
	- Completeness
	- Feasibility
#### Why Software Projects Fail
- Over-budget, over-time
- Hard to maintain and evolve
- **Useless (unwanted) product features**
	- Project teams felt that many features in the software they built were not used
	- Development teams would build software, and throw it over the wall to their users, and hope some of what they build would stick
#### Requirements in Agile Software Development
*related Manifesto: 2,3,4*
*related Principles: 1,2,3,4,6,7,10,11*
- Work continuously with stakeholders to develop requirements and tests
- Iterative development
	- Short iterations 2-4 weeks each focused on core feature (<mark style="background: #FF5582A6;">Sprints in Scrum</mark>)
	- Maintain working prototype while adding new features
	- Check with stakeholders what’s next to validate building the right software(<mark style="background: #FF5582A6;">Sprint Review</mark>)
- <mark style="background: #FF5582A6;">No standard way to do requirements</mark> in Agile development
	- Normal lightweight “Software Requirements Document”
	- Based On <mark style="background: #FF5582A6;">User Story</mark>
	- Iteratively elaborate small set of the functional requirements (<mark style="background: #FF5582A6;">priority- based</mark>)
	- Create some acceptance tests at the same time as you write the requirements
## BDD
*Behaviour Driven Development*
- **Definition:** A conceptual approach for specifying application’s behaviour and communicating them clearly among 
- Business value (feature) ->  acceptance criteria -> code to deliver it
- Given-When-Then canvas
	- Improves communication among domain experts, users, testers and developers
#### Requirements(BDD)
- User stories to elicit functional requirements
- Low Fidelity User Interfaces (UIs) and Storyboards to elicit UIs(<mark style="background: #FF5582A6;">LoFi UIs</mark>)
- Transfer user stories into acceptance tests
#### User Stories(BDD)
- *Short and concise description* of how the application is expected to be used from the *user’s point of view
	- Functionality that has *value* to the user and/or customer
- Helps stakeholders to *plan* and *prioritize* development
- Improve requirements clarity
- Documents functional requirements as executable scenarios/examples
## User Stories(Agile Development)
- 1-3 **non-technical** sentences written jointly by the stakeholders and developers
- Small enough to implement in one iteration, testable and **must have business value**
- format: As a [stakeholder], I want a [feature], so that [benefit]
#### Why use User Story
- User stories help to build software features that are likely needed
- Effective as it <mark style="background: #FF5582A6;">specifies</mark>
	- Who the user is
	- What the user wants to do
	- Why the user wants to do it
#### Common Mistakes
- Generic user, rather than meaningful role
- No evident business value
#### SMART User Stories(How to write)
- **Specific**: Describe specific details that add value
- **Measurable**: User story should be testable; there are known expected results for some good inputs
- **Achievable**: 
	- Can be implemented in one Agile iteration
	- Subdivide big stories (features) into smaller ones
- **Relevant**: Must have a business value relevant to one or more stakeholders
	- User 5 Whys technique to help drilling down to uncover real business value
- **Timeboxed**: 
	- To estimate amount of time to implement it
	- Stop developing the story if allocated time is over
		- Divide it into smaller ones or reschedule what’s left to new estimate
		- If dividing won’t help discuss with the customer the part of highest value
#### Done/Acceptance Criteria
- Effective way for developers to gauge completion/satisfaction of feature
	-  Concrete definition of “Done” or “Completed"
	- aka Condition of Satisfaction
- Condition of Satisfaction to be written at the **back** of the user story index card
#### Epics
- <mark style="background: #FF5582A6;">Larger bodies of</mark> work that can be broken into smaller tasks (user stories)
	- Often encompass multiple teams, on multiple projects
	- Almost always delivered over a set of Sprints
- **User stories** short/brief requirements or requests written from the end user perspectivE
	- Who, what and why
	- Defines details of an epic
#### Scrum User Story
**Sprint Planning Meeting (part2)**
- 1.Break the defined stories down into task
	- By team members
	- Each task on a separate card
	- Uncompleted stories - put on a single card to plan out the story
- 2.Group the stories and their tasks together
	- Add them to the “ To-Do” of the Sprint Backlog (Task Board)
- 3.Team members to work on the tasks
	- Team members can add additional tasks to the board to finish a story
		- Communicate it in the **Daily Scrum**
-  4.Finishing a task/story
	- The team member to finish the story’s final task verifies that all the conditions of satisfaction are completed with the PO and move it to the “Done”
## User Interfaces, Scenarios and Storyboard
- **Low-Fidelity (Lo-Fi) UIs**
	- Rough (UI) sketch or mock-up of a user story
	- Low-tech approach to UIs and the paper prototype sketch
	- Shows how a UI looks like and how sketches work together as a user interact
	- Effective for engaging nontechnical stakeholders
- **High-Fidelity (Hi-Fi) UIs**
	- Higher level of details that more closely matches the design of the actual UI (also used for documentation
- **Scenarios and Storyboards**
	- **Scenario**: written description of the system interactions from user’s perspectives.
	- **Storyboard**: similar to scenario but it visualizes the interactions
	- **User story** refer to a single feature
		- A feature usually has one or more scenarios that show different ways the feature is used 
- Scenarios Format
	- Scenario: brief description of the scenario 
	- GIVEN: description of context info. or pre-condition 
	- WHEN: description of the action/event 
	- THEN: description of the outcome
- **Implicit vs. Explicit Scenario**
	- **Explicit requirements** explicitly specified as **user stories** developed by stakeholders in BDD
	- **Implicit requirements** are not specified in the user stories
	- **Explicit requirements** correspond to **acceptance tests** and **implicit requirements** correspond to **integration tests**
- **Imperative Scenarios**
	- Tend to have complicated WHEN statements and lots of AND step
		- Ensures that UIs details match customer expectations, e.g., filling in a form
		- Tedious to write such scenarios and not a good practice
	- Scenarios should rather focus on the application’s behaviour
- **Declarative Scenario**
	- Focuses on the feature being described by using the step definitions to make a domain language for the application
		- A domain language is informal but uses terms and concepts specific to your application(add to cart, checkout...) rather than generic terms and concepts specific to the UI(Select,click,press button)
	- By experience, user stories should be written in a domain language that you will have to develop via your step definitions for the application you build
## Tools for Requirements in Agile Development
#### Automated Test
- In BDD requirements and tests are combined together
- Some tools, e.g., Cucumber, JBehave, convert written scenarios to automated tests
- **Acceptance Tests**：To ensure the customer/user is satisfied with the application behaviour
- **Integration Tests**: To ensure that the interfaces between modules have consistent assumptions and communicate correctly
#### Tool Support for Agile SW Dev
- Jira agile is a software tool for planning, tracking and managing software development projects. Supports different agile methods (e.g., Scrum and Kanban)
- Jira Software supports Scrum Sprint planning, stand ups (daily scrums), Sprints and retrospectives.
	- Including backlog management, project and issue tracking, agile reporting
	- Scrum boards visualize all the work in a given Sprint