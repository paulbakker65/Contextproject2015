#Sprint Reflection #3

Group: Health Informatics 5

| User Story | Task 			       | Assigned to | Estimated Effort             | Actual effort | Done
|:----------:|:-----------------------:|:-----------:|:----------------------------:|:--------------:|:-------:|
| Story 1    | Parser must check the input data | Robin    | 4 hours      | 4 hours | Yes
|			 | Parser must convert the input data into interpretable data| Robin | 4 hours | 6 hours | Yes
|			 | There must be a GUI front-end | Julian | 6 hours | 8 hours | Yes
|			 | There must be a GUI back-end and the main program flow should be adapted |  Julian | 6 hours | 6 hours | Yes
| Story 2    | Determine if we use one central XML-settings file or multiple for each file | Matthijs| 1 hours| 1 hour | Yes
| Story 9 	 | There must be constraints possible |  Matthijs | 4 hours            | 3 hours | Yes
|			 | The contraint command must be represented into script language |Matthijs| 2 hours| 2 hours | Yes |
| Story 10	 | There must be chucking possible in the current data format | Jan & Paul| 8 hours | 10 hours each | Yes |
|			 | The chunk command must be represented into script language | Paul | 2 hours  | 1 hour    | Yes |
|			 | Determine how a chunk should be represented into an output file | Paul| 3 hours | 3 hours | Yes |
| Organisation	 | Update emerging architecture | Matthijs & Jan | 2 hours | 2 hours | Yes
|  | Comment on product planning| Everyone | 0.5 hours per person | 1 hour each| Yes
|  | Fixing Maven and Travis | Jan | 3 hours | 2 hours | Yes
|  | Sprint plan | Paul (scrum master) | 1 hour | 1 hour | Yes

## User Stories

### Story 1

As a researcher I want to input study data into the program so that I can analyze it.

### Story 2

As a researcher I want to specify the input format so that multiple study data sets can be read. 

### Story 9

A a researcher I want to place constraints on the data so that to discard data not relevant to this analysis.

### Story 10

As a researcher I want to obtain chunks of the data so that I can analyze chunks of data individually.

##Brief reflection

Even though this week we only had three work days we did finish all tasks that we had planned to do. Robin implemented the parser and made numerous fixes so we could start implementing transformation operations on the data. Matthijs implemented the constraint operation and had some time left to start working on our scripting language. Julian implemented the GUI for selecting all the individual files on which the data transformations are to be performed and their corresponding XML setting files. The selection of a script file is also implemented in the GUI. After the all input files are specified the GUI calls other parts of the program to create the required datastructures. Paul & Jan have both implemented the chunking operation. Because there were two significantly different implementations that we wanted to explore we implemented them seperately. After testing both implementations and discussing the pros and cons of both implementations we choose one implementation that we think will suite our use-case best. Because we created two different implementations it took more time than anticipated to complete this user story. In the next sprint plan we will split up the tasks into even smaller tasks so that only one person is asigned a specific task. It is then up to that one person to make the descisions and that persons responsibility it is finished on time. The timetable we set this week was feasible and we finished all the tasks assigned to this sprint.
