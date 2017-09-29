# Parse_HML_to_fasta
Introduction of Parse_HML_to_fasta.


Parse_HML_to_fasta is a java program. It was developed to parse HLA sequences to a fasta format.

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

For using this tool:
1. Download jar file from runnable folder.
2. In the same folder of jar file, create two directories: input and output.
3. Put all your hml files into input folder.
4. double click jar file.

OR run it by command lines: java -jar hml2fa.jar

