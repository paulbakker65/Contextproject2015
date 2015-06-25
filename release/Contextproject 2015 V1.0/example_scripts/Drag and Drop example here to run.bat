@echo off
if "%%1" == "" goto usage

set SCRIPT=-s %1
set OUTPUT=-o "example_scripts/output_files"
set FILES=-f "medical/Q_ADMIRE_metingen_pagevisits_141214.csv" "medical/settings_website.xml" -f "medical/Afspraken_geanonimiseerd.csv" "medical/settings_hospital.xml" -f "medical/measured creatinine/ADMIRE_13.txt" "medical/settings_statsensor.xml"
echo.
echo Script : '%SCRIPT%'
echo Output : '%OUTPUT%'
echo Files  : '%FILES%'

cd ../
java -jar Contextproject2015.jar %FILES% %OUTPUT% %SCRIPT%
cd example_scripts
goto eof

:usage
echo Drag and Drop a script file to run the program using that script.
pause

:eof