### P2
- The set is the set of string with n 'a' characters followed by m 'b' characters, where m, n are positive none-zero integers
- ......

### P3
nahhh.....
Base case:  
	empty string/ length 1 : obviously symmetric

Inductive step:
     First check if the first character is same as the last character.If they are same, remove the first and last characters.Otherwise, the function returns 0 ,indicating that the string is not symmetric.
     
