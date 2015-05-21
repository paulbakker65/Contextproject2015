#Sprint Reflection #4

Group: Health Informatics 5

| User Story | Task 			       | Assigned to | Estimated Effort             | Actual effort | Done by | Done|
|:----------:|:-----------------------:|:-----------:|:----------------------------:|:------------------------------:|
| Story 3    | A scripting language must be defined | Matthijs    | 8 hours | 8 hours  | Matthijs | Yes
|			 | A script reader must be made| Matthijs | 8 hours | 8 hours | Matthijs | Yes
|			 | The script must be represented in this program | Robin | 6 hours | 2 hours | Matthijs | Yes
|			 | The script must be selected using a GUI | Julian | 4 hours | 6 hours | Julian | Yes
| Story 11 	 | There must be connections possible |  Julian | 8 hours | 8 hours | Julian |Yes
|			 | The connection command must be represented into script language |Julian| 2 hours| 1 hour | Julian | Yes
| Story 12	 | There must be comparing possible in the current data format | Jan | 12 hours | 10 hours | Jan | Yes
|			 | The compare command must be represented into script language | Jan | 2 hours | 2 hours | Jan | Yes
| Story 13   | There must be converting possible in the current data format |Paul | 12 hours | - | - | No
|			|	The convert command must be represented into script language | Paul | 2 hours   | 2 hours | Robin & Paul | Yes
| Organisation	 | Update emerging architecture | Robin | 2 hours | 3 hours | Robin | Yes
|  | Make demo for friday| Robin | 6 hours | 6 hours | Paul & Robin| Yes
|  | Implementing feedback week 3 | Paul | 2 hours | 2 hours each | Everyone | Yes
|  | Sprint plan | Paul (scrum master) | 1 hours | 2 hours | Paul | Yes
| Unplanned work | There must be coding possible in the current data format | - | - | 8 hours | Robin | Yes
|			|		There must be chunking possible on the current data format | - | - | 4 hours | Paul | Yes
|			|	Exporter must not use a XML-file | - | - | 4 hours | Paul | Tes
|			|	Checkstyle/FindBugs/CMD fixes | - | - | 4 hours each | Julian & Matthijs & Jan | Yes

## User Stories

### Story 3

As a researcher I want want to load my custom analysis from a file so that I don’t have to type in previous analyses.

### Story 11

As a researcher I want to connect different bits of data so that the underlying structure of the data can be analyzed.
### Story 12

A a researcher I want to compare different bits of data so that the differences in the data can be analyzed.

### Story 13

As a researcher I want to convert the data so that to obtain a data structure more logical to my next analysis.

##Brief reflection
For this sprint we had to make some changes in the planning during the week. The reason was that we got requirements from the end user. Those requirements had some different ‘must haves that we hadn’t as high on our requirements list.  Therefore we choose not to implement conversion this sprint, but in the next. Instead we choose to implement codes, as they were a ‘must have’ and were necessary to implement conversion in the first place. We also looked back at chunking as the requirements of chunking differed from our implementation.  There was also a fix to the exporter so it doesn’t rely on an XML-file to export, this was necessary as we started to do transformations on the data. We looked at the feedback given by our TA and started using the required plug-ins in our project. This also meant fixing allot of Checkstyle, FindBugs and CMD errors.  This was the summary of the unplanned work, they have been added to the table with how long each task took. But we also finished almost all our planned work, as seen in the table above. Our planning for those task were good and could be finished in the given time. For the next week we want to execute the parsed script and implement the basics of the final transformations. 