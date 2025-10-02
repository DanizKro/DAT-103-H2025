#!/bin/bash
#1
echo "Input the name of the file to be processed: " filename

#2
read filename

#3
rm -rf bashscript
mkdir bashscript

#4
cp "$filename" bashscript/grieg.txt

#5
ls bashscript

#6
echo "------------------------------------------------------"

#7
file="bashscript/grieg.txt"
header="catalogue" #henter ut hele linjen med grp
grep "$header" $filename > bashscript/temp.txt
grep "$header" -v $filename >> bashscript/temp.txt
mv bashscript/temp.txt bashscript/grieg.txt

#8 shows the document to terminal
echo "New header on grieg.txt: "
cat bashscript/grieg.txt

#9
echo "------------------------------------------------------"

#10 sort decending order year
echo "Sorted on decending order by year:"
tail -n +2 bashscript/grieg.txt | sort -t',' -k3,3nr

#11
echo "------------------------------------------------------"

#12 Show only lines that have "stage" as genre.
echo "Show only lines that have "Stage" as genre(by column):"
awk -F',' '$2=="Stage"' bashscript/grieg.txt

#13
echo "------------------------------------------------------"

#14 prints only lines where colum 3(year) is > 1870
echo "Show only lines that have "Year" after 1870:"
awk -F',' '$3 > 1870' bashscript/grieg.txt

#15
echo "------------------------------------------------------"

#16
echo "Copy all lines that have "Vocal" as genre to a new file vocal.txt:"
grep "Vocal" bashscript/grieg.txt > bashscript/vocal.txt
cat bashscript/vocal.txt

#17
echo "------------------------------------------------------"

#18
echo "Copy all lines that have "Piano" as genre to a new file piano.txt:"
grep "Piano" bashscript/grieg.txt > bashscript/piano.txt
cat bashscript/piano.txt

#19
echo "------------------------------------------------------"

#20
for file in bashscript/*.txt; do
    #save numer of lines in ALL files (bashscript/*.txt) with command line code
    n=$(wc -l < "$file")

    # Only act if n < 3
    if [ "$n" -lt 3 ]; then
        # Extract filename without directory and extension
        fileName=$(basename "$file" .txt)

        # Loop i from 1 to n to create copies
        for ((i=1; i<=n; i++)); do
            cp "$file" "bashscript/${fileName}-$i.txt"
        done #ending of a for-loop
    fi #ending of a if-statement
done

#21
wc bashscript/*.txt