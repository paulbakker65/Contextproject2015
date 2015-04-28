#Requirement specification

In this project we will develop a tool for combining and processing medical data from multiple sources. Then we will represent that data in a way so it's easy to process for futher statistics. 

##Must have

###Import


I1: The txt file from the Statsensor must be parsed and represented in the database class as a table. 

I2: The csv file from the website mijnnierinzicht.nl containing the data of the measurements of the patients must be parsed and represented in the database class as a table.

I3: The csv file from the website mijnnierinzicht.nl containing the data of the algorithm must be parsed and represented in the database class as a table. 

I4: The csv file from the visit log of the hospital must be parsed and represented in the database class as a table. 

I5: The user can specify which columns in the source files are being used by the program.

I6: The user can specify which delimiter is used for parsing the txt/csv file.

I7: The user can import a script with command to execute on the data.

###Processing data

PD1: The user has the  ability to chuck the data using a few predetermined constraints.

PD2: The user has the ability to create comments.

PD3: We must implement codes for each event to access data with the same.

PD4: We must implement connections which link 

PD5: The user can compare wether the creatinine value of the website, matches with the creatinine from the Stat-sensor

PD6: The user can compare wether a patient measures according to the schedual given by the doctor.

PD7: The user can compare how long it takes for a measurment to be entered in the website.

PD8: The user can compare the frequency of measurements between chunks.

PD9: The user can put constrains on the data by specifying conditions for certain columns

PD10: We must implement a conversion where patterns of events are combined into a higher level event.

PD11: The user can calculate averages of columns on the different chunks.

PD12: The user can count the number of records in a column on the different chunks.

PD13: The user can calculate the sum of the records in a column on the different chunks.

###Export

E1: The data after all the processing must be exported in to a txt/csv file.

E2: The user can specify the delimiter of the exported file.

###Visualization

V1: There must be a visualization of the frequency of measurement per time period as timeline.

V2: There must be a visualization of all the transitions between events as a transition matrix.

V3: Each visualization could be exported as an image file.

##Could have

###Import

I8: The xls file from the website mijnnierinzicht.nl containing the data of the measurements of the patients could be parsed and represented in the database class as a table.

I9: The xls file from the website mijnnierinzicht.nl containing the data of the algorithm could be parsed and represented in the database class as a table.

I10: The xls file from the visit log of the hospital could be parsed and represented in the database class as a table. 

###Processing data

PD14: The feedback algorithm is not hardcoded and can be specified by the user.

PD15: Multiple patients can be processed in one run.

###GUI

G1: There could be a GUI to display the data before and after a process. 











 