Command-Line Quiz Game
A simple, interactive quiz game that runs in the command line. It fetches multiple-choice questions from the Open Trivia Database API and tests your knowledge on various subjects.

Prerequisites
To run this project, you will need to have the Java Development Kit (JDK) installed on your machine.

How to Set Up and Run
Follow these instructions to get the project running on your local machine.

1. Clone the Repository
First, clone this repository to your local machine using Git:

git clone <your-repository-url>
cd <repository-folder-name>

2. Compile the Code
Compile all the Java source files from the root directory. This command will place the compiled .class files into the bin directory.

The required json-simple-1.1.1.jar library is included in the lib folder. The classpath (-cp) flag tells the compiler where to find it.

On Windows (PowerShell or Command Prompt):

javac -d bin -cp "lib\json-simple-1.1.1.jar" *.java


3. Run the Game
Execute the main class QuizGame from the compiled bin directory. You must again specify the classpath to include both your compiled code and the required library.

On Windows (PowerShell or Command Prompt):

java -cp "bin;lib\json-simple-1.1.1.jar" QuizGame

The quiz will now start in your terminal!

Project Structure
QuizGame.java: The main entry point of the application. It controls the game flow, handles user input, and displays the score.

QuizService.java: Responsible for fetching the question data from the Open Trivia DB API, parsing the JSON response, and creating Question objects.

Question.java: A simple data class (POJO) that represents a single quiz question, including the question text, the correct answer, and a list of options.

/lib: Contains the required json-simple-1.1.1.jar dependency for parsing JSON.

/bin: The output directory for compiled .class files (this is ignored by Git).
