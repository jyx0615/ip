#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
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

run_test() {
    # $1 = test index
    TEST_INDEX="$1"
    TEST_FOLDER="./test$TEST_INDEX"

    # delete output from previous run
    if [ -e "$TEST_FOLDER/ACTUAL.TXT" ]
    then
        rm "$TEST_FOLDER/ACTUAL.TXT"
    fi

    # run the program, feed commands from input file and redirect the output to the ACTUAL.TXT
    java -classpath ../bin jackson.Jackson < "$TEST_FOLDER/input.txt" > "$TEST_FOLDER/ACTUAL.TXT"

    # convert to UNIX format
    cp "$TEST_FOLDER/EXPECTED.TXT" "$TEST_FOLDER/EXPECTED-UNIX.TXT"
    dos2unix "$TEST_FOLDER/ACTUAL.TXT" "$TEST_FOLDER/EXPECTED-UNIX.TXT"
    cp "$TEST_FOLDER/EXPECTED_DATA.TXT" "$TEST_FOLDER/EXPECTED_DATA-UNIX.TXT"
    dos2unix ./data/jackson.txt "$TEST_FOLDER/EXPECTED_DATA-UNIX.TXT"

    # compare the output to the expected output
    diff "$TEST_FOLDER/ACTUAL.TXT" "$TEST_FOLDER/EXPECTED-UNIX.TXT"
    if [ $? -eq 0 ]
    then
        diff ./data/jackson.txt "$TEST_FOLDER/EXPECTED_DATA-UNIX.TXT"
        if [ $? -ne 0 ]
        then
            echo -e "Test result" $TEST_INDEX" :\033[31mFAILED\033[0m"
            return
        fi
        echo -e "Test result" $TEST_INDEX" :\033[32mPASSED\033[0m"
        return
    else
        echo -e "Test result" $TEST_INDEX" :\033[31mFAILED\033[0m"
        return
    fi
}

# test for functionality and command format
# todo, deadline, event, list, bye
run_test "1"

# test for mark, delete and task restore
# mark, unmark, delete, find
run_test "2"


# test for find and list(filter) commands
run_test "3"
