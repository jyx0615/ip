# Jackson project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Jackson_. Given below are instructions on how to use it.


## Quick Start

### For End Users

1. Ensure you have **Java 17** on your computer
2. Download the latest `jackson.jar` from the [releases page](https://github.com/jyx0615/ip/releases)
3. Copy the file to the folder you want to use as the home folder for Jackson
4. Open a command terminal, navigate to the folder containing the jar file
5. Run the application using: `java -jar jackson.jar`
6. Type commands and press Enter to execute them (e.g., `help` for available commands)
7. Refer to the [User Guide](https://jyx0615.github.io/ip/) for detailed usage instructions


## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Jackson.java` file, right-click it, and choose `Run Jackson.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello! I'm Jackson.
      ____.              __                         
      |    |____    ____ |  | __  __________   ____  
      |    \__  \ _/ ___\|  |/ / /  ___/  _ \ /    \ 
   /\__|    |/ __ \\  \___|    <  \___ (  <_> )   |  \
   \________(____  /\___  >__|_ \/____  >____/|___|  /
               \/     \/     \/     \/           \/ 
   What can I do for you?
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
