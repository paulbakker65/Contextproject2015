# Notes interterview 2015-04-24

##Examples of things user wants to know
 - On what time of the day do patients measure?
 - What is the time between measuring entering the result on the website?
 - What is the behavior during holidays?
 - Extract unexpected patterns

## Examples of patterns
 - Do users follow instructions?
 - Users take multiple measurements and enter only one on the site
 - Users don't enter measurements the same day, but at the end of the week?

##Export
 - User wants to be able to export to text-file. Seperator can be specified by user
 - There may be collumns with derived data

##Visualisation
 - timeline
 - time between sequences
 - (stem leaf)
 - boxplot
 - (histogram)
 - transition matrix
 - Frequency of measurement per month
 - (Markov-Chain) (hard)

##Provided data
 - Program only has to scan 1 person. Calculation on mulitple persons are done in a statistics program
 - Sources:
  - Measurement device (txt)
  - Hospital (xls)
  - Website measurements
  - Website algorithm
 - User must specify that a certain ID in one source, corresponds to the ID in another source

##Import
 - At least text file. It should support multiple kinds of seperators (tabs, space etc).
 - The meaning of a collumn should be defined in a seperate (xml) file, so the user doesnt have to do that each time
 - A GUI to make the collumn file is optional

##On generic use
 - Focus is on ADMIRE, but maybe it can be used for similar self-measuring studies too.
 - The algorithm for calculating the traffic light color may be hardcoded, but making it programmable is better.


##Other points
 - Computation time max +- 1 minute
 - Beware of different date formats
 - Spit out an error if some field contains unexpected data (ie: a cell is a string instead of a number)
 - The user is not your grandma, but someone who knows about statistics
 - Using a scripting engine is up to you
 - There should be an English Manual

