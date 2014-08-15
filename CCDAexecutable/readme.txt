USAGE: 

java -jar CDAgen.jar > output.xml

This reads in default "proposed CSVformat2_4.csv" as an input CSV file. 
If you want to use different CSV file, do as follow,

<b>java -jar CDAgen.jar [your_new_csv_file] > output.xml</b>

The name of output.xml also can be changed to whatever you want.

Installation Note:

CDAgen.jar is a runnable Jar file, which can be executed at the command line if you have a Java platform. 
Please make sure if you have Java Runtime Environment is installed. Some JAVA installations do not
configure the path to installed JAR. In this case, please add the path to a path system variable.

Once you download ZIP frile for CCDA generator, unzip the downloaded document. Using command line tool, 
change directory (cd) to the unziped folder. Then, follow the instruction above. Unless you are manually 
speicifying a name for CSV file, you need to run the jar file within the same direcotry where default
CSV file exists.
