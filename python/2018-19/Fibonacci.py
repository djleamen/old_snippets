# Program to display the Fibonacci sequence up to whatever the user provides

# Change this value for a different result
nterms = 10

# Uncomment to take input from the user
# nterms = int(input("How many terms? "))

# First two terms
n1 = 0
n2 = 1
count = 0

# Check if the number of terms is valid
if nterms <= 0:
    print("Please enter a positive integer")
elif nterms == 1: 
    print("Fibonacci sequence up to",nterms,":")
    print(n1)
else:
    print("Fibonacci sequence up to",nterms,":")
    while count < nterms:
        print(n1, end=' , ')
        nth = n1 + n2
        # Update values
        n1 = n2
        n2 = nth
        count += 1
