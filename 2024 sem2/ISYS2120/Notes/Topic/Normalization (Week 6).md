## Redundancy
| StudentId | Department | Class |
| --------- | ---------- | ----- |
| 101       | Compsci    | 1     |
| 102       | Math       | 2     |
| 101       | Compsci    | 3     |
Suppose the FD : $StudentId \rightarrow Department$

**Update:** If Student 101 changes his department from Compsci to Art, we have to update 2 records in this case.

**Insert**: If Student 101 attends another class, we need to know the Department when inserting the record
## Functional Dependency
#### Definition 
$X,Y$ are each a set if columns. A FD $X \rightarrow Y$ holds over relation $R$ if, for every allowable instance R of $R$ :
$$
	\forall r,s \in R, r.X =s.X \implies r.y=s.y
$$

 <mark style="background: #FFB8EBA6;">Express</mark>: there is only one ... which is associated to a given ...
#### Remarks
- A FD is an assertion about the schema of a  relation not about  a particular instance
	- It must be fulfilled by all allowable instance relations
- If we loot at an instance, we cannot tell for sure that FD holds

---
## Candidate Key

#### Definition
A set of columns $X$ makes up a <mark style="background: #FFB8EBA6;">candidate key</mark> for $R$ if and only if both of the following conditions hold:
- $\forall A \in R, X \rightarrow A$
- $\nexists X' \subset X, \forall A \in R, X' \rightarrow A$
#### Primary Key
Choose one candidate key as the primary key
#### Superkey
A superkey is a column or set of columns that includes a candidate key
- A candidate key, plus perhaps extra columns
- $X$ is a superkey for $R$ means: $\forall A \in R$, we have $X \rightarrow A$; this is also written as $X \rightarrow R$

---
## Deducing FDs
#### Closure
- $F^+$: closure of F is the set of all FDs that are implied by F
- Attribute Closure:  Suppose $X$ is a attribute set of relation $R$, then the Attribute Closure of $X$:
$$
X^+ = \{A | X \rightarrow A \text{ can be implied by } F\}
$$
#### Armstrong’s Axioms
- Reflexivity rule: if $X \subseteq Y, then Y \rightarrow X$
- Augmentation rule: If $X \rightarrow Y$, then $XZ \rightarrow YZ$
- Transitivity rule: If $X \rightarrow Y \land Y \rightarrow Z$, then $X \rightarrow Z$
- Union rule: If $X \rightarrow Y \land X \rightarrow Z$, then $X \rightarrow YZ$
- Decomposition rule: If $X \rightarrow YZ$ then $X \rightarrow Y$ and $X \rightarrow Z$
- Pseudo-transitivity rule: If $X \rightarrow Y \land SY \rightarrow Z$, then $XS \rightarrow Z$
## Computing the Closure of Attributes
#### Method
- Initialization: Start with a given set of attributes $X = \{A_1,A_2,A_3...A_n\}$. Initialize the result set with these attributes, $S = X$.
- If a FD of the form $M \rightarrow N$ such that $M \subseteq S \land N \nsubseteq S$ is found, we add $N$ into the result set $S$
- Repeat last step until no more attributes can be added to result.
- The final set result $S$ is the correct value of $X^*$
#### Checking Superkey
This method can be used to check if a set A of attributes is a <mark style="background: #FFB8EBA6;">superkey</mark> of R:
- Calculate $A^+$
- If $A^*$ contains all columns of R, then A is a superkey
#### Checking candidate key
To check if A is a <mark style="background: #FFB8EBA6;">candidate key</mark>
- $\forall B \subset A$ where $|B| = |A|-1$
	- Calculate $B^*$
	- If $B^+ = R$, then $A$ is not a minimal superkey, and so $A$ is not candidate key
- If $B^+$ is not $R$, for every subset B we consider, then $A$ is a candidate key for $R$

## Normal Forms
#### BCNF
**Definition:** A relation schema $R$ with a set of  functional dependencies $F$ is in $BCNF$ if , for every non-trivial FD $X \rightarrow Y$ in to closure of. $F$ (denoted $F^+$), one of the following conditions holds:
	- $Y \subseteq X$
	- $X$ is a superkey of $R$ 
#### Binary relation
If a relation only has 2 attributes, it is in BCNF.
#### Algorithm for BCNF Normalization
Consider relation $R$ with FDs $F$, which is not in BCNF. Take some fd $X \rightarrow Y$ which violates BCNF
- $Y' = Y - X$
- $R_1 = R - Y'$
- $R_2 = X \cup Y'$
- recursively
#### Decomposition of Relational Schema
**Definition**: Let $R(A_1,A_2,...,A_n)$ be a relation schema with attribute set $\{A_1,A_2,...,A_n\}$. A decomposition
of $R$ into new relations $R_1,R_2...R_k$ must satisfy the following conditions:
- $\forall i \in \{1,2,...,k\}$, $R_i \subset R$
- $\bigcup_{i=1}^k S_i = \{A_1,A_2,...A_n\}$

<mark style="background: #FFB8EBA6;">PS</mark>: Some attributes $A_j$ may appear in multiple relations.
#### Lossless-Join Decomposition
**Definition**: Let $R(A_1,A_2,...,A_n)$ be a relation schema with attribute set $\{A_1,A_2,...,A_n\}$. If we decompose $R$ into two relations $R_1$ and $R_2$. The decomposition is said to be **lossless-join** if every instance of $R$ that satisfies a set of functional dependencies $F$, the following holds:
$$
\Pi_X(R) \bowtie \Pi_Y(R) = R 
$$
<mark style="background: #FFB8EBA6;">Remarks</mark>:
- This can be extended to decomposition into 3 or more relations.

**Checking Lossless-Join Decomposition**: The decomposition of $R$(with fds F) into $R_1$ based columns $X$ and $R_2$ based on columns $Y$ is lossless-join if and only if the fds F imply either:
- $X \cap Y \rightarrow X$
- $X \cap Y \rightarrow Y$
- or both
#### Dependency Preserving Decomposition Definition
**Definition:** Given a relation R with schema *R* and set of FDs $F$, A decomposition of *R* into *S* and *T* is a dependency preserving decomposition if 
$$
(F_{S} \cup F_{T})^+ = F^+
$$





