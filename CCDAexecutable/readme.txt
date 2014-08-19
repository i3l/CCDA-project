USAGE: 

java -jar CDAgen.jar > output.xml

This reads "proposed CSVformat2_4.csv" as an input CSV file if CSV fileanme is not specified.

If you want to use different CSV file, do as follow,

java -jar CDAgen.jar [your_new_csv_file] > output.xml

The name of output.xml also can be changed to whatever you want.


Installation Note:

Note: These installation instructions assume a Windows PC.  MAC users will need to interpret them for that OS.

CDAgen.jar is a executable JAR (Java ARchive) file, that can be run at the command line if you have the Java Runtime Environment (JRE) installed.
First, please verify that you have the (JRE) installed by typing "java -version" at the command line. 
If it is not installed, you can download it for free for installation at: http://java.com/en/download/index.jsp.

Note: Some JRE installations do not configure the path to the installed JRE in the Windows Environment's System variables' Path statement. 
In this case, please add the path to your JRE directory into the Path system variable. Instructions for doing that are at:http://www.computerhope.com/issues/ch000549.htm.
For help finding the JRE refer to this: http://stackoverflow.com/questions/3930383/jre-installation-directory-in-windows.

First download the ZIP file for CCDA generator and then unzip the downloaded document. Using command line tool, 
change directory (cd) to the folder where the generator is located. Then, follow the Usage instructions above. 

Note: Unless you are manually providing the name of the CSV file (including its directory) as an input variable (e.g. "directory\your_CSV_file_name.csv"), you need to locate the CSV file in the same directory where CDAgen.jar is installed.
