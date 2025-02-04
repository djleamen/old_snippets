# Program to sort alphabetically the words form a string provided by the user

# change this value for a different result
my_str = "the quick brown fox jumped over the lazy dog"

# uncomment to take input from the user
#my_str = input("Enter a string: ")

# breakdown the string into a list of words
words = my_str.split()

# sort the list
words.sort()

# display the sorted words

print("The sorted words are:")
for word in words:
   print(word)
