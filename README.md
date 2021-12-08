# Project3
Project3.java

This project consists of inputs for the various possible executions. The reader inputs the number of students the array will consist of, then determines which system the inputter would like to continue with, CMS or SMS. After that, the inputter determines what action they’d like done within the two systems. The data given is processed and utilized to create student arrays, student employees, and even added into txt files, depending on the action selected. The main arrays being used are the students array and student employees array, both which are used to keep track of the students and student employees. Next, the main classes in this system are the Student class, Course class, and Student_Employee subclass. The interfaces of CreateStudent and CreateCourse are used to help create Student and Course classes. Finally, the output is consistent, since after each course of action, something is printed out to confirm the requested action/to provide the action. Therefore, this system is able to effectively manage SMS and CMS for the inputter. 

I divided the code into various sections, and denoted these using comments in CAPITALIZED letters. The following are the sections: 
•	Classes – These include Student class, Course class, and Student_Employee subclass
•	Interfaces – These include CreateStudent and CreateCourse interfaces
•	Variable Declaration – most variables were declared in sections throughout the code, however a few of variables such as the Scanner for input collection and the Boolean to end the loop were declared at the beginning. 
•	Array – created student array and student_employee array 
•	Loops – many loops were used to fulfil requirements
•	Requesting Input – receiving input using scanner
•	Menu options – The first list of menu options is determining whether to go to SMS, CMS, or exit. The second list of menu options is the SMS options OR the CMS options, depending on what was selected in the first list. 
•	Exceptions – utilized various exceptions, and try-catch statements to protect the code from crashing. The three exceptions I handled were 2 InputMismatchExceptions and 1 ArrayOutOfBoundsException. 
•	File creation, reading, and writing – to create the file, read, and write into it using the Files class. I chose this particular method of reading/writing just from my previous coding experience and analysis of which method would serve best for the purpose, while requiring the least amount of lines of code.

Lastly, to use this code, simply run the file and the project will be ready for use! Thank you for reading.

