#### Software Quality Assurance
#### Software quality
- **For User**: Satisfying end use’s needs; correct behaviour, easy to use, does not crash, etc
- **For Developer**Easy to the developers to debug and enhance
#### Software Quality Assurance
- Ensuring software under development have high quality and creating processes and standards in organization that lead to high quality software
- Software quality is often determined through **Testing**
#### What is Software Testing?
- Software process to
	- Demonstrate that software meets its requirements
	- Find incorrect or undesired behavior caused by defects/bugs
- Different system properties
	- Functional: performs all expected functions properly
	- Non-functional: secure, performance, usability
#### Testing Objectives
- Objectives should be stated precisely and quantitatively to measure and control the test process
- Testing completeness is never been feasible
	- So many test cases possible - exhaustive testing is so expensive!
	- Risk-driven or risk management strategy to increase our confidence
- How much testing is enough?
	- Select test cases sufficient for a specific purpose (test adequacy criteria)
	- Coverage criteria and graph theories used to analyse test effectiveness
#### Tests Modeling
- Testing modelled as input test data and output test results
- **defective testing**: Tests that cause defects/problems
- **validation testing:** Tests that lead to expected correct behavior
#### Who Does Testing?
- Developers test their own code
- Developers in a team test one another’s code
- Many methodologies also have specialist role of tester
- Real users, doing real work
#### When is Testing happening?
- Agile Software Development
	- Testing is at the heart of agile practice
	- Continuous intergration
	- Daily unit testing
#### Software Testing Process
Design, execute and manage test plans and activities
- Select and prepare suitable test cases
- Selection of suitable test techniques
- Test plans execution and analys
- Root cause analysis and problem-solving
- Trade-off analysis (schedule, resources, test coverage or adequacy)
Test effectiveness and efficiency
- Available resources, schedule, knowledge and skills of involved people
- Software design and development practices

**Defects**
- Syntax error
- Runtime error
- Logic erro
- Timing error

**Regression Testing**
- Verifies that a software behaviour has not changed by incremental changes to the software.
- Continuously perform the same tests on iterative versions to ensure that new features do not break existing functionality or damage other non-functional requirements.
**Black Box Testing**: Treats the software system as a black box. The tester does not know the internal workings of the system and only focuses on whether the input yields the correct output. Commonly used for System and Acceptance Testing.
**White Box Testing**: The tester is aware of the system’s internal workings and can selectively choose test cases and methods based on this knowledge. Commonly used for Unit and Integration Testing.
#### Test Cases Design
Identify groups of inputs with common characteristics
For each partition, choose tests on the boundaries and close to the midpoint

- Use testing guidelines based on previous experience of the kinds of errors often made
- Understanding developers thinking

#### Code Coverage
- The extent to which a source code has been executed by a set of tests
- Usually measured as percentage, e.g., 70% coverage
- Different criteria to measure coverage

- Software criticality determines coverage level
- Extremely high coverage for safety-critical (dependable) software
	- Government/standardization organizations
	- 