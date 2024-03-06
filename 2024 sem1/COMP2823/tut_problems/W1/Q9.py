s = "aabbaac" # input string

k = 3
count = 1
i = 1 # second element

while i < len(s):
	if s[i] == s[i-1]:
		count += 1
	else:
		count = 1

	if count == k:
		print("True")
		break
	i+=1

