CCDA-project
============

Content: 

1. CCDA Generator v1.0
The CCDA Generator reads predefined CSV file and outputs corresponding CCDA document. The current version of generator can only generate single template type as shown below. Thus, when you modify the CSV file, keep in mind that you are generating CCD/HITSP C32 of Summarization of Episode Note. We also considered Meaningful Use 2 data set (v6) document for required data elements and coding system.  

 - ClinicalDocument (TemplateID: 2.16.840.1.113883.10.20.22.1.1)
 - Continuity of Care Document (CCD) / HITSP C32 (TemplateID: 2.16.840.1.113883.10.20.22.1.2)
   - LONIC 34133-9 : Summarization of Episode Note
 - Allergies should be drug related allergies.

Other document types will be supported in the later version. If you want to involve in the CCDA project, please contact at myung.choi@gtri.gatech.edu.

The CCDA generator requires MDHT to compile to executable JAR file. To download runtime libraries, please visit 

  URL: http://sourceforge.net/projects/oht-modeling/files/Releases/Runtime/

When compiling, compile this as runnable (or executable) JAR. CCDA Generator can be run at the commandline as follows,

 java -jar CDAgen.jar [inputfile.csv] > output_filename.xml

usage note:<br/>
output_filename.xml is the output CCDA filename. You can change this to whatever name you want.<br/>
[inputfile.csv] is an optional parameter. If [inputfile.csv] is not specified, then CDAgen.jar will read "proposed CSVformat2_4.csv" as a default input CSV file. "proposed CSVformat2_4.csv" is included in this package. If you want to use different csv file, then [inputfile.csv] needs to be specified with a valid path to the file. 

To see other activities in I3L, please visit http://www.i3l.gatech.edu/
