
| Operation      | Symbols   | Example                                                    |
| -------------- | --------- | ---------------------------------------------------------- |
| Projection     | $\Pi$     | $\Pi_{name,country}(Student)$                              |
| Selection      | $\sigma$  | $\sigma_{country = 'AUS'}(Student)$                        |
| Cross-product  | $\times$  | $R \times S$                                               |
| Joins          | $\bowtie$ | Student $\bowtie_{family\_name=last\_name}$ Lecturer       |
| Natural Join   | $\bowtie$ | $R \bowtie S$                                              |
| Rename         | $\rho$    | $\rho_{x}(E)$, $\rho_{x(a1 \rightarrow a2)}(E)$            |
| Set Difference | -         | tuples in relation 1, but not in relation 2                |
| Division       | /         | $R/s:= \{<a> \| \forall <b> \in S: \exists <a,b> \in R \}$ |

