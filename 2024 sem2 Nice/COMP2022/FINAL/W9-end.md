## Propositional Logic
### Syntax
**Proposition:** A proposition is a sentence that declares a fact that is either true, or false, but not both.

**Recursive Process:** An <mark style="background: #FF5582A6;">atom</mark> is a variable of the form $p_1,p_2,p_3...p,q,r,...$. A <mark style="background: #FF5582A6;">formula</mark> is defined by the following recursive process:
- Every atom is a formula
- If $F$ is a formula then $\neg F$ is a formula
- If F,G are formulas then so are $(F \lor G)$ and $(F \land G)$
### Semantics
*refers to how you derive the value of a formula based on the values of its atomic sub-formulas*

**Definition**
- An <mark style="background: #FF5582A6;">assignment</mark> is a function $\alpha$ from atoms to truth values
- The truth value $tv(F,\alpha)$ of a formula $F$ under assignment $\alpha$ is defined by the following recursive process:
![[Pasted image 20241117162502.png]]
- In case $\alpha(F)=1$, we say any of the following
	- $\alpha$ makes $F$ true
	- $\alpha$ satisfies $F$
	- $\alpha$ models $F$
	- also written $\alpha \models F$
- $\top$ and $\bot$ are formulas
	- These symbols are called the <mark style="background: #FF5582A6;">propositional constants</mark>
## Logical Consequences
**Definition:** Say that F is a logical consequence of $E_1,...,E_k$, if every assignment that makes all of the formulas $E_i$(for $1 \leq i \leq k$) true also makes $F$ true. We write this as
$$
E_1,...E_k \models F
$$
**Logical Equivalences:** Two formulas $F$ and $G$ are <mark style="background: #FF5582A6;">logically equivalent</mark> if, $tv(F,\alpha)=tv(G,\alpha)$ for every assignment $\alpha$.

## NNF
**Definition:** A formula $F$ is in <mark style="background: #FF5582A6;">negation normal form(NNF)</mark> is negation only occur immediately in front of atoms.
