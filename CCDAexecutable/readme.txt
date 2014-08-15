USAGE: 

java -jar CDAgen.jar > output.xml

This reads "proposed CSVformat2_4.csv" as an input CSV file if CSV fileanme is not specified.

If you want to use different CSV file, do as follow,

java -jar CDAgen.jar [your_new_csv_file] > output.xml

The name of output.xml also can be changed to whatever you want.

Installation Note:

CDAgen.jar is a runnable Jar file, which can be executed at the command line if you have a Java platform. 
Please make sure if you have Java Runtime Environment is installed. Some JAVA installations do not
configure the path to the installed JAR. In this case, please add the path to JAR file into a path system variable.

Once you download ZIP frile for CCDA generator, unzip the downloaded document. Using command line tool, 
change directory (cd) to the unziped folder. Then, follow the instruction above. 

Unless you are manually providing name of the CSV file as a input variable, you need to run the JAR file within the 
direcotry where you are running the JAR file from.
