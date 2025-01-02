## Agile Methods
### Relationship between different methods 
- <mark style="background: #FFB8EBA6;">All have shared goal</mark>: delivering valuable software iteratively
- Might have some different practices to achieve this goal
	- How coding should be done, how work is arranged daily, team structure and communication
- All have the agile values at their core and share some values, ideas and practices with each other.
![[Notes/2024 sem2 Nice/SOFT2412/Notes/Graphs/w8_1.png]]



### (XP) eXtreme Programming
- Development and delivery of <mark style="background: #FFB8EBA6;">very small increments</mark> of functionality
- <mark style="background: #FFB8EBA6;">Relies on constant code improvement</mark>, user involvement in the development team and pairwise programming. <mark style="background: #FFB8EBA6;">Pairwise programming:</mark> one person writes the code (<mark style="background: #FFB8EBA6;">"driver"</mark>), while the other reviews each line as it’s written, offering feedback and suggestions (<mark style="background: #FFB8EBA6;">"navigator"</mark>)
- Emphasizes <mark style="background: #FFB8EBA6;">Test Driven Development (TDD)</mark> as part of the small development iterations. <mark style="background: #FFB8EBA6;">TDD:</mark> developers write tests before writing the actual code for each feature, ensuring the code meets the expected behavior
#### XP practices
Customer team member, User stories, Acceptance tests, Pair programming, Test-driven development (TDD), Short cycle, Continuous integration,Simple design, Refactoring, Collective ownership

### Scrum
#### Definition
- An Agile method for managing software development focused on delivering products of the highest value
- Focus on <mark style="background: #FFB8EBA6;">continuous improvement</mark> of the **product**, the **team** and the **working environment**
- Scrum is lightweight, simple to understand but <mark style="background: #FFB8EBA6;">difficult to master</mark>
#### <mark style="background: #ADCCFFA6;">Scrum Theory</mark>
- Based on ‘**empirical process control**’ theory
	- Knowledge comes from experience and making decisions based on knowns
	- **Iterative, incremental** approach to optimize predictability and control risks
- Pillars of empirical process control
	- Transparency: significant aspects of the process must be **visible** to those responsible for the outcome
	- Inspection: Scrum users must **frequently inspect** Scrum artifacts and progress toward an iteration goal to detect undesirable variances
	- Adaptation: adjust the aspects of the process that lead to deviation outside the acceptable limits
#### Scrum Values
The Scrum team members should learn and explore the following values as they work with the Scrum roles, events, and artifacts:
- **Commitment**: personally commit to achieving the goals of the Scrum Team
- **Courage:** members can do the right thing and work on tough problems
- **Focus**: everyone focuses on the work of the iteration and the team’s goals
- **Openness**: the team and the stakeholders agree to be open about the work and the challenges with performing the work
- **Respect**: Scrum team members respect each other to be capable, independent people
#### Scrum Practices
- **Teams and their roles**
	- Product Owner, Scrum Master, Dev Team
- **Events**
	- Sprint, Sprint Planning, Daily Scrum, Sprint Review, and Retrospective
- **Artifacts**
	- Product Backlog, Sprint Backlog, Increment
- **Project estimation and Sprint estimation**
- Rules govern(control) the relationships between roles, events and artifacts

#### The Scrum Team
- **Team roles**: Product Owner (<mark style="background: #FFB8EBA6;">one person</mark>), Scrum Master (<mark style="background: #FFB8EBA6;">one person</mark>), Development Team
- **Scrum Team**: Small enough to be agile, **Cross-functional**, **Self-organizing**, Deliver products iteratively and incrementally, maximizing opportunities for feedback
##### Product Owner
- <mark style="background: #FF5582A6;">maximize</mark> the value of the product and the work
	- Talks to customer, understand requirements and its priorities
	- Managing the Product Backlog (only person)
		- Can assign it to the development team, but still accountable
- Managing the <mark style="background: #FF5582A6;">product backlog</mark>:
	- <mark style="background: #FF5582A6;">Record</mark> product backlog items and <mark style="background: #FF5582A6;">order</mark> it
	- <mark style="background: #FF5582A6;">Optimize</mark> the value of the work the development team performs
	- Ensure transparency and clarity of the product backlog
	- Ensure the development team understands product backlog
##### Development Team
- Professionals who do the work of delivering a potentially releasable product at the end of each iteration
- Creates the increment (only by dev team)
- optimal **team size**
	- Two-Pizza team (4-9 members)
	- Less than 3; issues with productivity and interactions
	- More than 9; issues with coordination
- **Self-organizing**: turns Product Backlog into increments of potentially releasable functionality
- **Cross-functiona**l: skills mix necessary to create a product
- **No sub-teams**: regardless of domains that need to be addressed
- Whole team is accountable
- No titles for Dev team members
##### Scrum Master
- Keeps the team focused on using Scrum properly (“servant-leader”)
	- Everyone understands Scrum rules and values (coaching)
	- Remove impediments: identify and remove issues or obstacles that block the development team from completing their work
	- Helps those outside the Scrum team which of their interactions with the Scrum team are/aren’t helpful
	- Maximize the value created by the Scrum team through changing team interactions
- Serves the **Product Owner**
	- Creating mutual understanding of goals, scope and product domain
	- Finding effective ways for managing the product backlog
	- Helping the Scrum team understands the need for clear and concise product backlog items
	- Ensuring the product owner knows how to arrange the Product Backlog to maximize value
	- Understanding and practicing agility
	- Facilitating Scrum events as requested or needed
- Serves the Dev team
	- To be self-organization and cross-functionality
	- To create high-value products;
	- Removing impediments to the Dev team’s progress
	- Facilitating Scrum events as requested or needed
	- Coaching the Dev team (in organizational environments in which Scrum is not yet fully adopted and understood)

#### Scrum Events
- Used to create regularity and minimize the need for meetings
- All events are time-boxed (max. duration)
- Cannot be changed once an iteration (sprint) has started
- Designed to enable transparency and to provide a formal opportunity to inspect and adapt work
##### The Sprint
- A development iteration (one cycle): Useable and potentially releasable product increment is created
- Time-boxed (typically <mark style="background: #FFB8EBA6;">2-4 weeks</mark>)
	- Too long sprints may lead to changes in the definition
- Sprints have consistent durations during the product development
- Consists of the Sprint Planning, Daily Scrum, the Development Work, the Sprint Review and the Sprint Retrospective
##### Sprint Planning
- Identify the Sprint Goal (items from the “Product Backlog”
- Identify work to be done to deliver this
- Two-parts meeting (Scrum Master, Product Owner and Dev team)
	- **Before meeting**: Product Owner prepares prioritized list of most valuable items
	- **Meeting part 1 (max. 4 hours):** Product Owner & Dev team select items to be delivered at the end of the sprint based on their value and on the team’s estimate of how much work needed
	- **Meeting part 2 (max. 4 hours)**: Dev team (with the Product Owner’s help) figure out the individual tasks they’ll use to implement those items
- **Output:** Sprint Backlog (the items selected by the team for development) 
##### <mark style="background: #ADCCFFA6;">Scrum Iteration Process</mark>
- Sprint (development iteration)
	- Timeboxed (typically 2–4 weeks – no more than one month)
	- Create a “Done” usable, potentially releasable product
- A Sprint (Scrum iteration) contains a list of tasks and work product outputs that will be done in defined duration
	- At the beginning of the Sprint duration, each team member has a pretty good idea of what they will be working on
	- Management should not add new work product outputs to the Sprint – should be add to the Product Backlog instead. (SM duty)
##### Daily Scrum meeting
- To ensure problems and obstacles are <mark style="background: #FF5582A6;">visible</mark> to the team
- Time-boxed 15 minutes (<mark style="background: #FF5582A6;">same time and place</mark> each day)
- All team members including Scrum Master and Product Owner must attend
- Interested stakeholders may attend as observers
- Each briefly answers three questions:
	- What did I do yesterday that helped the development team meet the Sprint Goal?
	- What will I do today to help the development team meet the Sprint Goal?
	- Do I see any obstacles that prevent me or the Dev team to meeting the Sprint Goal?
- <mark style="background: #FF5582A6;">No problem-solving during the meeting</mark>
	- Follow-up meetings if further discussion is required

##### The Sprint Review
- Informal meeting at end of the Sprint
	- Dev team demonstrates working software to <mark style="background: #FFB8EBA6;">customers/stakeholders</mark>
		- Items actually done – completed, tested & accepted by the product owner
		- <mark style="background: #FFB8EBA6;">Only functional working software</mark> – not architecture, database design etc
	- Stakeholders share their feedback, ideas, feelings, thoughts about the demo
	- The Product Owner explains “Done” and not “Done” items
- **Output**: revised Product Backlog and probable items for next iteration
##### Retrospectives
Opportunity for the Scrum team to <mark style="background: #FF5582A6;">inspect itself</mark> and <mark style="background: #FF5582A6;">create plan</mark> for improvements
**Purpose**:
- Inspect how the last Sprint went with regards to people, relationships, process, and tools;
- Identify and order the major items that went well and potential improvement
- Create a plan for implementing improvements to the way the Scrum Team does its work
**Meeting**
- The Scrum Master and the Dev. team (maybe product owner)
- Each person answer two questions
	- What went well during the Sprint?
	- What can be improved in the future?
- The Scrum Master notes improvements that should be added to the Product Backlog (non-functional items)
- **Output**: <mark style="background: #FF5582A6;">identified improvements</mark> to be implemented in the next Sprint (adaptation)


#### Scrum  Artifacts
##### Product Backlog
- Set of all features and sub-features (items) needed to build the product 
	- Features, functions, requirements, enhancements and fixes identified from previous Sprints
- Maintained by the <mark style="background: #FFB8EBA6;">Product Owner</mark> in collaboration with customers and team
- The source of the product requirements
	- Evolves over the time and never complete (dynamic)
- The items ordered by priority – value to the customer
	- To deliver some value to the customer in each iteration, put the most important things early
- Managers <mark style="background: #FFB8EBA6;">should not</mark> estimate  Product Backlog Item
##### Sprint Backlog
- Set of **Product Backlog** items **selected** for the Sprint, and a plan for delivering the product increment and realize the Sprint Goal.
- The Dev. team to forecasts next items to be implemented
- Includes at least one high-priority improvement identified from previous Sprint
- The Dev. team adds new work to the Sprint Backlog
- The estimated remaining work is updated once an item is completed
- Visible to anyone and to be modified by the Dev. team
##### The Increment
- Collection of the Product Backlog items that meet definition of “Done” by the end of the Sprint
- Definition of “Done”
	-  Team’s shared <mark style="background: #FF5582A6;">agreement</mark> on the criteria that a Product Backlog item (<mark style="background: #FF5582A6;">user story</mark>) must meet before it is considered done
	-  Work will not be counted toward the end of the Sprint if it does not meet the criteria