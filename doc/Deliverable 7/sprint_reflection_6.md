#Sprint Reflection #6

Group: Health Informatics 5

| User Story | Task 			       | Assigned to | Estimated Effort             | Priority | Done by | Actual effort | Done?
|:----------:|:-----------------------:|:-----------:|:----------------------------:|
| Story 7/8  | The script must be coupled to the CodingOperation | Matthijs    | 5 hours | High | Matthijs | 5 hours | Yes
|			 | The script must be coupled to the ChunkingOperation | Matthijs | 5 hours | High | Matthijs | 10 hours | Yes 
|			 | The script must be coupled to the CompareOperations | Matthijs | 10 hours | High | Matthijs | 5 hours | Yes
| Story 10   | The chunking operation should use a range instead of an enum | Paul | 10 hours | High | Matthijs | 5 hours | Yes
| Story 13   |	Converting must merge multiple records into one | Robin | 5 hours | Low | - | - | No
| Story 17  | A PatternFactory should be made to make patterns | Robin | 15 hours | High | Robin | 15 hours | Yes
| Story 20 | There should be determined what library should be used to visualize the data | Julian | 5 hours | High | Julian | 3 hours | Yes
|			| After the execution of the script there must be a new GUI for visualization | Julian | 10 hours | High | Julian | 12 hours | Yes
|			| There should be a frequency graph of the selected data | Jan | 10 hours | High | Jan | 10 hours | Yes
|			| The should be a state transistion matrix of the selected data | Paul | 10 hours | Medium | Paul | 10 hours | Yes
|			| There must be a box plot of the selected data | Jan | 5 hours | Medium | - | - | No
|			| There should be a stem leaf plot of the selected data | - | - | - | Paul | 10 hours | Yes
| Organisation	 | Update emerging architecture | Jan | 2 hours | Low | - | - | No
|  | Make prototype for friday| Jan & Julian | 5 hours each | Medium | Robin & Julian | 5 hours each | Yes
|  | Sprint plan and reflection| Paul (scrum master) | 2 hours | Normal | Paul | 2 hours | Yes 

## User Stories

### Story 7

As a researcher I want to provide a custom analysis on my input so that I can filter the study data the way I need.

### Story 8

As a researcher I want to chain different analyses together so that a sequential analysis can be performed.

### Story 13

As a researcher I want to convert the data so that I obtain a data structure more logical to my next analysis.

### Story 17
As a researcher I want to put codes on the data so that I can detect behaviour patterns.

### Story 20
As a researcher I want visualize the new data so that I can determine what to analyse next.

##Reasons behind priority
For this sprint we have given the coupling of the script with the operations, the making of patterns and visualizing our data in two ways our highest priority. Coupling of the script for the data is an essential function of our program and we have started the coupling and hope to finish the other operations this week. For coding and conversion we want to give the user the ability to create codes on behaviour. By making it possible to create whatever pattern in our Pattern factory we will make this possible. Both of these operations are must haves. Furthermore we gave visualization this sprint a high priority given the feedback we got this Friday on what to focus on next. Therefore we need to make a new GUI to make it possible for the user to select the data he wants to visualize. The medium tasks are not necessary for the demo next Friday. Updating the Emergent Architecture Design has a low priority as we will most likely not change much in our core program so there is no big update needed.

###Brief reflection
This sprint we had to implement the last must haves of the analytical part of our product and start implementing some visualizations. We are happy that we could finish all the high priority tasks this week without going over the estimated effort. There were two overall changes in the plan this week. The first one is the task of the chunking operation. Paul was originally assigned to this task but because it was so similar to the task of coupling the script to the chunking operation it was assigned to Matthijs. Paul looked instead at visualizing a stem leaf plot. The second one is the task of creating a demo. Jan was originally assigned to this task, but was sick 2 days this week. So Robin finished this task. This is also the reason that the boxplot and the merging of records wasn’t done this sprint. Finally we decided not to update the emergent architecture design, as our code still has the same design, no updating was needed. For next week we will look at polishing the visualizations and coupling it with the input/script GUI and start on implementing extra features. For example we are starting to make a XML maker for the files. There will also be some code cleaning and some more robust exception handling of the script engine.