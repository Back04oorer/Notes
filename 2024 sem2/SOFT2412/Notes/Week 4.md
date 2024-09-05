## 1. Configuration Management(CM)

- Configuration management (CM) is concerned with the policies, processes and tools for <mark style="background: #FF5582A6;">managing changing software system</mark>.

- Track of what changes and component versions incorporated into each system version
- Essential for team projects to control changes made by different developers


## 2. CM Activities
##### System building 
<mark style="background: #FF5582A6;">Assembling</mark> program components, data and libraries, then <mark style="background: #FF5582A6;">compiling</mark> these to create an <mark style="background: #FF5582A6;">executable system</mark>.

##### Version management
<mark style="background: #FF5582A6;">keeping track of multiple versions </mark>of system components and ensuring that changes made to components by different developers <mark style="background: #FF5582A6;">do not interfere</mark> with each other.

##### Change management
<mark style="background: #FF5582A6;">keeping track of requests for changes to the software</mark> from customers and developers,
working out the costs and impact of changes, and <mark style="background: #FF5582A6;">deciding the changes should be implemented.
</mark>

##### Release management
<mark style="background: #FF5582A6;">preparing software for external release</mark> and keeping track of the system versions that <mark style="background: #FF5582A6;">have been released for customers</mark>
![[Graphs/w4_1.png]]


## Multi-version System Development

![[Graphs/w4_2.png]]



## Version Management (VM)

- Keep track of different versions of software components or configuration items and the systems in which these components are used.
- Ensuring changes made by different developers to these versions <mark style="background: #FF5582A6;">do not interfere with each other</mark>
- The process of managing code-lines and baselines
#### Codeline
a sequence of versions of <mark style="background: #FF5582A6;">source code</mark> with later versions in the sequence derived from earlier versions

#### Baseline
A definition of a specific system:
Specifies the component version that are included in the system plus a specification of the libraries used, configuration files, etc.
