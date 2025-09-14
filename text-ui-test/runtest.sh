#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# delete data file from previous run
if [ -e "./data/jackson.txt" ]
then
    rm ./data/jackson.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/jackson/*.java ../src/main/java/jackson/task/*.java ../src/main/java/jackson/command/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin jackson.Jackson < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT
cp EXPECTED_DATA.TXT EXPECTED_DATA-UNIX.TXT
dos2unix ./data/jackson.txt EXPECTED_DATA-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    diff ./data/jackson.txt EXPECTED_DATA-UNIX.TXT
    if [ $? -ne 0 ]
    then
        echo "final data files are different Test result: FAILED"
        exit 1
    fi
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi