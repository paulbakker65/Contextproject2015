cd ..

java -jar Contextproject2015.jar -f "medical/Q_ADMIRE_metingen_pagevisits_141214.csv" "medical/settings_website.xml" -f "medical/Afspraken_geanonimiseerd.csv" "medical/settings_hospital.xml" -f "medical/measured creatinine/ADMIRE_13.txt" "medical/settings_statsensor.xml" -o "example_cli/output_files" -s "example_cli/script.txt"