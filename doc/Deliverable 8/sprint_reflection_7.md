#Sprint Reflection #7

Group: Health Informatics 5

| User Story | Task 			       | Assigned to | Estimated Effort             | Priority | Done by | Actual effort | Done?
|:----------:|:-----------------------:|:-----------:|:----------------------------:|
| Story 7/8  | The scripting engine should show usefull parser errors | Matthijs    | 5 hours | High   | Matthijs                   | 5 hours | Yes
|			 | The script must create temporary table for following operations | Matthijs | 5 hours | High | - | - | No
| Story 12 | The operations could be able to be executed on a single chunk | Robin | 10 hours | Medium | Robin | 5 hours | Yes
| 			| There could be simple arithmic operations possible on a chunk like average and sum | Robin | 10 hours | High | Robin | 5 hours | Yes
| Story 20 | The input/script GUI should be coupled to the visualization GUI | Julian | 10 hours | High | Julian | 8 hours | Yes
|			| There should be a histogram of the selected stem plot leaf | Paul | 5 hours | High | Paul | 5 hours | Yes
|			| The State Transition Matrix should be based on codes, not on column | Paul | 5 hours | High | Paul | 5 hours | Yes
|			| The table GUI should contain record numbers | Julian | 1 hours | Medium | Julian | 2 hours | Yes
|			| The table GUI should be resizable | Julian | 2 hours | Medium | Julian | 1 hour | Yes
|			| The table GUI should be scrollable | Julian | 2 hours | Medium | Julian | 1 hour | Yes
|			| It must be possible to export a xml file based on a Table | Julian | 5 hours | High | Julian |8 hours | Yes
|			| There must be a box plot of the selected data | Jan | 5 hours | High | Jan | 5 hours | Yes
| Story 21 | There could be a XML maker | Jan | 15 hours | Medium | Jan |15 hours | Yes
| Story 22 | There should be a manual for this porduct | Paul | 5 hours | Medium | - | - | Yes
| Organisation	 | Update emerging architecture | Jan | 2 hours | Low | - | - | Yes
|  | Make prototype for friday| Paul | 5 hours | Medium | Paul | 7 hours | Yes
|	|	Code base must be checked on duplication | Matthijs | 5 hours | High | Matthijs |15 hours | Yes
|	| Code base could have a Model View Controller pattern | Matthijs | 5 hours | Medium | - | - | Yes
|  | Sprint plan and reflection| Paul (scrum master) | 2 hours | Medium | Paul | 2 hours | Yes
| Not planned | Our data is now serializable | - | - | - | Robin | 5 hours | Yes
|				| TimeValue is now removed | - | - | - | Robin | 5 hours | Yes 

## User Stories

### Story 7

As a researcher I want to provide a custom analysis on my input so that I can filter the study data the way I need.

### Story 8

As a researcher I want to chain different analyses together so that a sequential analysis can be performed.

### Story 12

As a researcher I want to chunk the data so that I obtain a data structure more logical to my next analysis.

### Story 20
As a researcher I want visualize the new data so that I can determine what to analyse next.

### Story 21
As a researcher I want to create a XML file for the input file so that I can import this file into the program

### Story 22
As a researcher I want to have a manual of the program so that I can determine what the program can do.

##Reasons behind priority
The high priority tasks of the user stories are all part of must haves given by the customer. So we must implement those to make our product satisfactory for our customer. The medium tasks are should haves, tasks part of requirements we really want to implement to make our product stand out. There is only one low priority task this week and this is updating the emergent architecture design. We don’t think we are going to change the architecture this sprint so it hasn’t a high priority.
In the organization we also have the task of removing duplicate code, this has a high priority as we want to make our code as efficient as possible. 

##Brief reflection
This sprint we have focused on making it possible to perform operation for each chunk instead of on the whole table. Together with the possibility to do some basic arithmatic operations we can determine per chunk averages and count the number of instances in a given column. This was done by Robin and was completed in less time than the estimated effort so Robin also removed some redundant code and made the table object serializable. Matthijs started with catching exception thrown by the scripting engine and then focused on making the overall maintainability of our code better. This took more time then expected but is absolutely necessary for our project. And therefore the decision was made to not implement the task of creating a new table after an operation because it was not important for the functioning of the program. We already implemented the MVC design pattern so no time was needed. Julian and Jan finished all their tasks in the given time without any problems. Paul finished every task in the given time except the task for creating a manual, this was because Paul was sick for one day but will be finished this weekend. For next sprint we will focus on implementing the feedback of SIG even more and polishing the program for the deadline of this Friday. This will include a few new features but mostly on improving the existing. And finally we will do a lot of manual testing with other people so we can see if the program is working as intended.
