# Lexical Analyzer
This project is the first phase of building a simple programming language for my compiler construction course. The main idea is to use Urdu keywords and define a custom syntax using .mi file extensions. This lexical analyzer reads a .mi file, tokenizes the input, and identifies keywords, data types, operators, constants, and more. The analyzer checks for common programming constructs like integers, floats, booleans, and characters, while using Urdu terms for them.

In this language:

**ginti** = *int*  
**asharia** = *float*  
**haq** = *bool*  
**harf** = *char*  
**sach** = *true*  
**jhoot** = *false*  
**dikhao** = *print*  
**batao** = *input*  

How to run it:
Compile the program:

```bash
javac Main.java //Run it with your .mi file:
```
```bash
java Main <filename>.mi
```
This is an early step in building a full compiler for this custom language. I have also  added operators and parentheses and semi-colons that can be later used for error handling as well such as malformed numbers (3.14.15), or unclosed strings or parentheses, or missing semi colons.


I have left a test.mi file which can be used to test the tokenizer. Here's the result from that file:

Token[type =KEYWORD, value =ginti]  
Token[type =IDENTIFIER, value =age]  
Token[type =OPERATOR, value ==]  
Token[type =PUNCTUATION, value =(]  
Token[type =INT, value =1]  
Token[type =OPERATOR, value =+]  
Token[type =INT, value =2]  
Token[type =PUNCTUATION, value =)]  
Token[type =OPERATOR, value =/]  
Token[type =INT, value =3]  
Token[type =PUNCTUATION, value =;]  
Token[type =KEYWORD, value =asharia]  
Token[type =IDENTIFIER, value =y]  
Token[type =OPERATOR, value ==]  
Token[type =FLOAT, value =3]  
Token[type =PUNCTUATION, value =;]  
Token[type =KEYWORD, value =haq]  
Token[type =IDENTIFIER, value =z]  
Token[type =OPERATOR, value ==]  
Token[type =KEYWORD, value =sach]  
Token[type =PUNCTUATION, value =;]  
Token[type =KEYWORD, value =harf]  
Token[type =IDENTIFIER, value =a]  
Token[type =OPERATOR, value ==]  
Token[type =CHAR, value =r]  
Token[type =PUNCTUATION, value =;]  
Token[type =KEYWORD, value =batao]  
Token[type =PUNCTUATION, value =(]  
Token[type =IDENTIFIER, value =age]  
Token[type =PUNCTUATION, value =)]  
Token[type =PUNCTUATION, value =;]
Token[type =KEYWORD, value =dikhao]
Token[type =PUNCTUATION, value =(]
Token[type =STRING, value =hello user]
Token[type =PUNCTUATION, value =)]
Token[type =PUNCTUATION, value =;]
