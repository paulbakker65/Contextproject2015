#!/bin/sh
if [ $# -ne 1]
then
    echo "Drag and Drop a script file to run the program using that script."
	read -p "Press enter to continue . . ."
else
	SCRIPT="-s \"$1\""
	OUTPUT="-o \"example_scripts/output_files\""
	FILES="-f medical/Q_ADMIRE_metingen_pagevisits_141214.csv medical/settings_website.xml -f medical/Afspraken_geanonimiseerd.csv medical/settings_hospital.xml -f \"medical/measured creatinine/ADMIRE_13.txt\"	medical/settings_statsensor.xml"
	echo "\n"
	echo "Script : '$SCRIPT'"
	echo "Output : '$OUTPUT'"
	echo "Files  : '$FILES'"
	
	cd ../
	java -jar Contextproject2015.jar -f "medical/Q_ADMIRE_metingen_pagevisits_141214.csv" "medical/settings_website.xml" -f "medical/Afspraken_geanonimiseerd.csv" "medical/settings_hospital.xml" -f "medical/measured creatinine/ADMIRE_13.txt" "medical/settings_statsensor.xml" -o "example_scripts/output_files" -s "$1"
	cd example_scripts
fi

