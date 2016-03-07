# HML2fa
How to use this application:

This application is used to convert HML file into fasta file. In this process, it also can encode or decode the GL string.
Two kinds HML file are supported: 1. the regular HML file with all elements 2. the HML without sequence element.

The output file name is created from input name. For example, the corresponding output name of "abc.hml" is "abc.fasta".

	1. Set up: Before run the app. In the same directory, please create an input folder, and put all the HML files in this folder. 
	
2. Execute:
		a. For parseing consensus sequences only, run command "java -jar hml2fa.jar". Because there is no argument for encode or decode. 
       You can also double click the .jar to run, and check the output files in output folder.
		b. Encode GL string. Run command "java  -jar hml2fa.jar  encode".
		c. Decode GL string and expand. Run command " java -jar  hml2fa.jar  decode true". 
		d. Decode GL string and do not expand. Run command " java -jar hml2fa.jar  decode false" .
	3. After the execution completed, an output folder is generated and contains all the output files.


The Structure of application:

	1. Launcher: This class contains main function, so it is the entrance of the application.
		a. Parse the argument and create FastaGenerator.
		b. Create output folder. If it existed, then clear all the files in this folder.
		c. Go through all the input files of the input folder and process them with FastaGenerator.
	2. FastaGenerator. This class parse the HML file and save the information into fasta file.
	Method run(File input, File output):  Process one input file and generate an output file.
   3. GLSConverter. This class can encode or decode GL string.
	Method encode(String gls): Use http post request to encode GL string.
	Method decode(String gls, boolean expand): Use http get request to decode GL String.







Introduction of HML2fa.


HML2fa is a java program. It was developed to parse HLA consensus sequences to a fasta format.

The fasta format is begins with a single line description or identifier, followed by lines of sequence data.
The identifier include sample ID (‘id’), Locus (‘locus’), Type (‘type’), glstring (‘gls’) and Phase-set (‘ps’). 

Each section divided by vertical bar “ | ”.

For example: 
>id|1235-1677-5|locus|HLA-DRB1|type|03:01:01:01|gls|HLA-DRB1*03:01:01:01/HLA-DRB1*03:01:01:01|PS1

The program is able to transform the HLA genome ambiguity to alleles ambiguity.

For example:
A HLA genome ambiguity:
HLA-DRB1*04:07:01+HLA-DRB1*08:02:01|HLA-DRB1*04:92+HLA-DRB1*08:02:01
Transform to alleles ambiguity:
HLA-DRB1*04:07:01/ HLA-DRB1*04:92   and   HLA-DRB1*08:02:01/ HLA-DRB1*08:02:01

The program also fixes the multiple phase sets issue. The sequences of multiple phase sets were combined to one sequence with “ - ”.

For example:
HML sequences:
<phase-set="1">
               <sequence>TGTTTTCCCGTGTTTGGATTCCTAGAG>
<phase-set="2">
               <sequence>TGTTTTCCCTCGTTTGGATTCCTAGAG>
<phase-set="1">
               <sequence>TCAACGAGTTTCCCCTCACAAACACCA>
<phase-set="2">
               <sequence>TCAACGAGTTTCCCCTCACAAACACCA>

Fasta sequence:

>id|1235-1677-5|locus|HLA-DRB1|type|03:01:01:01|gls|HLA-DRB1*03:01:01:01/HLA-DRB1*03:01:01:01|PS1
TGTTTTCCCGTGTTTGGATTCCTAGAG-TCAACGAGTTTCCCCTCACAAACACCA

>id|1235-1677-5|locus|HLA-DRB1|type|04:07:01G|gls|HLA-DRB1*04:07:01/HLA-DRB1*04:92|PS2
TGTTTTCCCTCGTTTGGATTCCTAGAG-TCAACGAGTTTCCCCTCACAAACACCA

It also can encode or decode the GL string.

Fix the bugs to deal with incomplete HML files, such as missing consensus sequences on HML file.



