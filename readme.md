## Java Streams Etudes

[![Language](https://img.shields.io/badge/language-java-red.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/)
[![GitHub license](https://img.shields.io/github/license/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/stargazers)

Do you know how to use Java Stream API?

* If **NO** but you are interested in it then you can use this project as a roadmap of things to learn
* If **YES** then you can have a practice here to check your skills 

Apply you knowledge of _streams_, _lambdas_, _method handlers_ and other functional Java things.

There are also some tasks that will make think even experienced programmers. Try to solve them all.

> **> 30** tasks are available now

### What to do

> Clone repository → implement methods → run tests → fix mistakes

Methods to implement are placed in class `tasks.StreamTasks` in `src` folder. 
Initially they are stubbed with standard `UnsupportedOperationException` (and tests know about that). 
You need to replace such lines with solution consists of stream flow or something related to it
(description and hints for each task are provided).

It's **prohibited** to use standard cycles (`for`, `while`, `do-while`, recursion) at all and 
conditions (`if`, `if-else`, ...) out of stream operations. The main goal of this project is to 
teach you how to use functional approach in practice. One **possible exclusion** from pure functional 
paradigm is declaring and usage of local variables.

### Running tests

Due to this project is orientated to beginners **no dependent libraries** are required.
If you know how to add dependencies and really know for what then you can do it manually 
(but, be pointed, all tasks can be solved using only standard Java library).

> Main requirement is **JVM of 11 version (or higher)**

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

In case you don't know how to solve some task than you can see solution in `tasks.solution.StreamTasksSolution` 
class in `src` folder. This class is used as reference solution to check answers, so you can copy-paste code to the
task and this task will be accepted.

Try to avoid any interaction with solutions until you completed all tasks :)

#### Challenges

You can also use tasks from this project for different challenges. 
For example, you can check how many tasks you can do in **limited time** 
or how much time you will need to **solve them all**, etc.

##### Good luck, have <s>fun</s> progress!

<hr />

Version 0.1.0 is available [here](https://github.com/Shemplo/Java-Streams-Etudes/tree/7206e9138a3c2ae0347d983696dfaf56002485a0)

* The first draft of this project
* It has different 60 tasks

<hr />

Future plans:

* `[ ]` Rise number of tasks to 100
  * `[~]` Add more input generators to test engine
    * `[✓]` For `Map <?, ?>` type 
    * `[✓]` For `List <List <?>>` type (and any enclosure level)
    * `[ ]` Else
  * `[ ]` Add more input variations to test engine (f.e. collection as extension of another parameter that is collection too)
  * `[~]` Сome up with some new task ideas
* `[ ]` Split tasks to several classes by topics
