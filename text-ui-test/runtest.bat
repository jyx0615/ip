@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete data file from previous run
if exist .\data\jackson.txt del .\data\jackson.txt

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\jackson\*.java ..\src\main\java\jackson\task\*.java ..\src\main\java\jackson\command\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

:run_test
REM %1 = test index
set TEST_INDEX=%1
set TEST_FOLDER=.\test%TEST_INDEX%

REM delete output from previous run
if exist "%TEST_FOLDER%\ACTUAL.TXT" del "%TEST_FOLDER%\ACTUAL.TXT"

REM run the program, feed commands from input file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin jackson.Jackson < "%TEST_FOLDER%\input.txt" > "%TEST_FOLDER%\ACTUAL.TXT"

REM compare the output to the expected output
FC "%TEST_FOLDER%\ACTUAL.TXT" "%TEST_FOLDER%\EXPECTED.TXT" >NUL
IF ERRORLEVEL 1 (
    echo Test result %TEST_INDEX%: FAILED
    goto :EOF
)

REM compare the data file to the expected data
if exist .\data\jackson.txt (
    FC .\data\jackson.txt "%TEST_FOLDER%\EXPECTED_DATA.TXT" >NUL
    IF ERRORLEVEL 1 (
        echo Test result %TEST_INDEX%: FAILED
        goto :EOF
    )
)

echo Test result %TEST_INDEX%: PASSED
goto :EOF

REM Main execution starts here
call :run_test 1
call :run_test 2
call :run_test 3
