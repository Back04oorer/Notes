## Planning in Agile Software Development
- **Plan-driven** (plan-and-document or heavy-weight)
	- All of the process activities are planned **in advance** and progress is measured against this plan
		- All of the process activities are planned in advance and progress is measured against this plan
		- Plan drives everything and **change is expensive**
	- Agile processes (<mark style="background: #FF5582A6;">light-weight</mark>)
		- Planning is **incremental and continual** as the software is developed
		- Easier to change to reflect changing requirement

## Agile Methods for Estimating Size
#### Estimating Size in Agile Development
图
#### Story Points
- Metric for the **overall size** of a user story, feature, or other piece of work
- A point value to each item is assigned
- Estimation scale:
	- Fibonacci series
	- Subsequent number as twice the number that precede
- Why?
	- Measure of the user story only
	- No emotional measurements
	- Team velocity is considered
	- Team members focus on solving problems based on difficulty not time spent
#### Starting Story Points
- Two approaches:
	- Smallest story (expected) is estimated at 1 story point
	- Medium-sized story assigned a number somewhere in the middle of the range you expect to use
- Estimating in story points completely separates the estimation of effort from the estimation of duration
#### Sprint Planning Session using Story Points
- Start with the most valuable user stories from the product backlog
- Take a story in that list (ideally the smallest one)
- Discuss with the team whether that estimate is accurate
- Keep going through the stories until the team have accumulated enough points to fill the Sprint
#### Why Story Points Work?
- Simple, not magic
- The team is in control of them
- They get your team talking about estimate
- Developers are not scared of them
- They help the team discover exactly what a story means
- They help everyone on the team become genuinely committed
#### Estimating in Ideal Days
- Ideal Days vs Elapsed Days
	- Time to do a development task without any interruptions is **Idea days**
	- Time to do a development task with work interruptions is **Elapsed days**
- Ideal days only is an estimate of size
- Express the estimate as a **whole**
- Techniques
	- Expert opinion(estimates based on opinion
	- Analogy: compare with other stories
	- Disaggregation: splitting a story into smaller tasks
	- Planning Poker
#### Planning Poker
- Aims to avoid individual influence/bias 
- Team discussion with mediation to consider different views
- Team involvement in planning increases commitment
- It combines expert opinion, analogy, and disaggregation into an enjoyable approach to estimating that results in quick but reliable estimates
- **Considering Story Points over Ideal Days**
	- Story points help drive <mark style="background: #FF5582A6;">cross-functional</mark> behavior
	- Story points are a <mark style="background: #FF5582A6;">pure measure</mark> of size
	- Estimating in story points is <mark style="background: #FF5582A6;">typically faster</mark>
	- My ideal days are not your ideal days
- **Re-Estimating**
	- Story points and ideal days helps you know when to re-estimate
	- Re-estimate only when your opinion of the relative size of one or more stories has changed
	- Do not re-estimate solely because <mark style="background: #FF5582A6;">progress is not coming as rapidly as you’d expected</mark>
	- Let velocity take care of most estimation inaccuracies
- **Considering Ideal Days over Story Points**
	- Ideal days are easier to explain outside the team
	- Ideal days are easier to estimate at first
	- Ideal days make velocity predictions easier
## Estimating Progress
#### Velocity
- A measure of a team’s rate of progress
- Sum the number of story points assigned to each user story that the team completed during the iteration
- A team completed 3 stories, 5 SPs each -> velocity = 15
#### Number Of Sprints Estimation
`SUM(all User stories' Story Points)/velocity`
#### Velocity Estimation
- **Historical values**: Same technology?Same domain?Same team?Same Product Owner?Same tool?Same working environment?Were the estimates made by the same people?
- **Run a sprint:** Run an iteration (or two or three) and then estimate velocity from the observed velocity during the one to three iteration
- **Forecasting**:
	- Estimate <mark style="background: #FF5582A6;">daily working hours</mark> for each team member (available hours).
	- Determine the <mark style="background: #FF5582A6;">total number of hours </mark>that will be spent on the project during the iteration.
	- Somewhat randomly select stories and expand them into their basic tasks
	- Repeat until you have identified enough tasks to fill the number of hours in the iteration.
	- Convert the velocity determined in the priore step into a range.
- **Problem:**
	- Inconsistent velocity over long time
		- Hidden challenges not counted?
		- Outside business/stakeholders' pressure?
	- Observe team velocity throughout the Sprints and investigate decrease in average velocity
		- Discuss in the retrospective meetings
#### Tracking Project Progress
- **Burndown Charts**
	- Tracks the completion of development work throughout the Sprint;
	- Should be <mark style="background: #FF5582A6;">visible</mark> to everyone in the team
	- First half of the Sprint planning
	- Good estimation and planning should help the team to burn stories relatively with similar pace
- x-axis: first and end dates of the sprint
- y-axis: story points (0 to 20% more than the total no. of points in the Sprint)
- More work needs to be added (discovered during daily scrum)
