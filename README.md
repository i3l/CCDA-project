CCDA-project
============

Content: 

1. CCDA Generator v1.0
The CCDA Generator reads predefined CSV file and outputs CCDA. Current version is fixed to the following document. Thus, when you modify the CSV file, keep in mind that you are generating Summarization of Episode Note.

 - ClinicalDocument (TemplateID: 2.16.840.1.113883.10.20.22.1.1)
 - Continuity of Care Document (CCD) / HITSP C32 (TemplateID: 2.16.840.1.113883.10.20.22.1.2)
   - LONIC 34133-9 : Summarization of Episode Note
 - Allergies should be drug related allergies.

Other document types will be supported in the later version. If you want to involve in the CCDA project, please contact at myung.choi@gtri.gatech.edu.

The CCDA generator requires MDHT to compile to executable JAR file. To download runtime libraries, please visit 

  URL: http://sourceforge.net/projects/oht-modeling/files/Releases/Runtime/

