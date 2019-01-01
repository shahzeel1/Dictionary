# Dictionary
This program creates a bunch of records which are stored in an ordered dictionary. The dictionary is implemented using a Binary Search Tree. 
The program provides a simple text base interface that will allow users to interact with
the ordered dictionary through the use of the following commands. 

•get word
If the ordered dictionary has records containing the given word, then each one of these records
(word,type,data) will be processed in the following manner:
– if type = “text”, then print data on the screen,
– if type = “audio”, then play the audio file whose name is stored in data,
– if type = “image”, then display the image stored in the file whose name is stored in data.
If no record in the ordered dictionary contains the specified word in its key attribute then
the program will print a message indicating that the word is not in the dictionary and then
it will print (i) the word stored in the ordered dictionary that immediately precedes word
in lexicographic order (if any), and (ii) the word in the ordered dictionary that immediately
follows word in lexicographic order (if any).


• delete word type
Removes from the ordered dictionary the record with key (word,type), or if no such record
exists, it prints no record in the ordered dictionary has key (word,type).


• put word type data
Inserts the record (word,type,data) into the ordered dictionary if there is no record with key
(word,type) already there; otherwise it prints a record with the given key (word,type) is already in the ordered dictionary.


• list prefix
Here prefix is a string with one or more letters. The program will print all the words (if
any) in the ordered dictionary that start with prefix (if prefix is a word in the ordered
dictionary, it must be printed also). If several records in the dictionary store the same word w
and w starts with prefix, then the word w will be printed as many times as records contain it.

• smallest
Prints the record with smallest key in the ordered dictionary. 


• largest
Prints the record with largest key in the ordered dictionary. 


• finish
This command terminates the program
