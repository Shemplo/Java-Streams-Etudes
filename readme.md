## Java Streams Etudes

[![Language](https://img.shields.io/badge/language-java-red.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/)
[![GitHub license](https://img.shields.io/github/license/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/stargazers)

Do you know how to use Java Stream API?

* If **YES** then you can have a practice here to check your skills 
* If **NO** but you are interested in it then you can use this project as a roadmap of things to learn

You will need to implement some methods with described logic using knowledge of 
_streams_, _lambdas_, _method handlers_ and other functional Java things.

There are also some tasks that will make think even experienced programmers. Try to solve them all.

> **2** tasks are now available for implementation

### What to do

0. Clone this repository to your computer.

1. Implement methods that are declared and stubbed in class `tasks.StreamTasks` in `src` folder.
2. Run tests as it is described bellow.

### Running tests

Due to this project is orientated for beginners **no dependent libraries** are required.

* You need just a **JVM of 11 version (or higher)**.

Run main method from `tasks.StreamTasksMain` class in your preferred IDE or run a script file `test.(cmd|sh)`.

Flag `-ea` is required to enable `assert` key word for tests checker. Otherwise all tests will not be run properly.

#### Check program and verdicts

Check program will run tests for all tasks and then write verdict for each of tasks.

Possible verdicts:

* **accepted** - you solved this task
* **wrong answer** - you almost solved this task (code compiles but logic is wrong)
* **not implemented** - you didn't start to implement this task
* **missed NULL check** - you forgot to check something for `null` reference
* **runtime exception** - something goes extremely bad

Until you solved all tasks with verdict `accepted` the program will finish with non-zero exit code.

##### Examples

You can run check program with argument `example`. 
For that add `example` word to passing arguments in IDE or to the end of running scripts:

`test.cmd example` (for Windows) and `./test.sh example` (for Unix)

There are some implemented tasks in class `tasks.StreamTasksExample`, so they would be tested in `example` mode.

> Not guaranteed that implementation is correct or in optimal way ;)

#### Solutions

In case you really don't know how to solve some task than you can see solution in `tasks.solution.StreamTasksSolution` class.

Try to avoid this action :)

#### Challenges

You can also use this repository for different challenges. 
For example, you can check how many tasks you can do in **limited time** 
or how many tasks you can do with **single run** of tests (on the first attempt), ...

##### Good luck, have <s>fun</s> progress!
