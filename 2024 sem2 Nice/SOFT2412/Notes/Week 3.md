## Git
-  `git config user.name <name>` 
	- Define author name to be used for all commits in current repo
- `git commit --ammend`
	- Replace the last commit with the staged changes and last commit combined. Use with nothing staged to edit the last commit’s message.
- `git rebase <base>` 
	- Rebase the current branch onto `<base>`. `<base>` can be a commit ID, branch name, a tag, or a relative reference to HEAD.
- `git checkout -b <branch>` 
	- Create and check out a new branch named `branch`
- `git merge <branch>`
	- Merge `<branch>` into the current branch.
- `git fetch <remote> <branch>`
	- Fetches a specific  `<branch>`, from the repo. Leave off to fetch all remote refs
- `git pull <remote>` 
	- Fetch the specified remote’s copy of current branch and immediately merge it into the local copy![[Pasted image 20241113132153.png]]
- `git push <remote> <branch>`
	- Push the branch to `<remote>`, along with necessary commits and objects. Creates named branch in the remote repo if it doesn’t exist.
- `git revert <commit>` 
	- Create new commit that undoes all of the changes made in `<commit>`, then apply it to the current branch.
## Distributed Git
#### Centralized VCSs
**Single collaboration model (centralized workflow)**
- Every developer is a node working on a central shared repo. and sync. to it
- git branching: hundreds of developers work on a single repo. via many branches simultaneously
- Create a repo. and give every developer push access (git will manage versioning)
- if two users make changes to the same file, the user who submits later needs to first pull the changes made by the previous user and merge them locally to ensure the consistency of the commit history and the files.
#### Distributed VCS
- Every developer can be
	- Node: contribute code to other repos.
	- Shared repo: maintain a public repo. on which others can base their work and which they can contribute to
- Wide range of workflow possibilities for projects/teams
- Dictator and Lieutenants Model
	- **Lieutenants**: various integration managers oversee certain parts of the repo.
	- **Benevolent dictator**: All Lieutenants have one integration manager
	- The benevolent dictator pushes from their directory to a reference repo(blessed repo). from which all the collaborators need to pull
	- Regular developers work on their topic branch and rebase their work on top of master. The master branch is that of the reference repository to which the dictator pushes
	- Lieutenants merge the developers' topic branches into their master branch.
	- The dictator merges the lieutenants' master branches into the dictator’s master branch.
	- Finally, the dictator pushes that master branch to the reference repository so the other developers can rebase on it
#### Contributing to a Project
- Active contributor count
- Project workflow
	- Centralized with equal write access to main code-line?
	- Does the project have a maintainer or integration manager who check all commits
	- Is a lieutenant system in place and do you have to submit your work to them first?
- Commit access: how the type of commit access would affect the contribution workflow
	- Do you have write-access?
	- If not, is there a policy on how the contributed work is accepted?
	- How much work a developer may contribute at a time? and how often?
- Commit  guidelines
	- No whitespace errors: git diff --check
	- Commit logically separate changeset
	- Use quality commit message