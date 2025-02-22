# Lexical Analyzer
This project is the first phase of building a simple programming language for my compiler construction course. The main idea is to use Urdu keywords and define a custom syntax using .mi file extensions. This lexical analyzer reads a .mi file, tokenizes the input, and identifies keywords, data types, operators, constants, and more. The analyzer checks for common programming constructs like integers, floats, booleans, and characters, while using Urdu terms for them.

In this language:

ginti = int
asharia = float
haq = bool
harf = char
sach = true
jhoot = false
dikhao = print
batao = input
To run the program, follow these steps:

Compile the program:
```bash
javac Main.java
//Run it with your .mi file:
java Main <filename>.mi
```
This is an early step in building a full compiler for this custom language.
