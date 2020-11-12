## Java Streams Etudes

[![Language](https://img.shields.io/badge/language-java-red.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/)
[![GitHub license](https://img.shields.io/github/license/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/blob/master/LICENSE)
[![GitHub stars](https://img.shields.io/github/stars/Shemplo/Java-Streams-Etudes.svg)](https://github.com/Shemplo/Java-Streams-Etudes/stargazers)

This repository can be useful if you are beginner in _Java Stream API_. 
To check yourself you will need to implement some methods with described logic using knowledge of 
Streams, Lambdas, Method handlers and other functional things.

There are also some tasks that will make think even experienced programmers. Try to solve them all.

### What to do

You just need to implement methods that are declared and stubbed in class `tasks.StreamTasks` in `src` folder.

### Running tests

Due to this project is orientated for beginners **no dependent libraries** are required.

* You will need just a **JVM of 11 version (or higher)**.

Clone this repository to your computer and then run main method from `tasks.StreamTasksMain` 
class in your preferred IDE or type in console:

```bash
javac -sourcepath src -d bin src/tasks/StreamTasksMain.java && java -ea -cp bin tasks.StreamTasksMain
```

Flag `-ea` is required to enable `assert` java key word for tests checker. Otherwise all tests will not be run properly.

#### Check program and verdicts

Check program will run tests for all tasks and then write verdict for each of tasks.
Possible verdicts:

* **accepted** - you solved this task
* **wrong answer** - you almost solved this task (code compiles but logic is wrong)
* **not implemented** - you didn't start to implement this task
* **missed NULL check** - you forgot to check something for `null` reference
* **runtime exception** - something goes extremely bad

Until you solved all tasks with verdict `accepted` the program will finish with non zero exit code.

##### Examples

You can run check program with argument `example` to see how it will look like.

```bash
javac -sourcepath src -d bin src/tasks/StreamTasksMain.java && java -ea -cp bin tasks.StreamTasksMain example
```


In class `tasks.StreamTasksExample` there are some implemented tasks 
(not guaranteed that implementation is correct or in optimal way),
so this class will be tested if you run check program in `example` mode. 

#### Solutions

In case you really don't know how to solve some task than you can see solution in `tasks.solution.StreamTasksSolution` class.

Try to avoid this action :)

#### Challenges

You can also use this repository for different challenges. 
For example, you can check how many tasks you can do in limited time 
or how many tasks you can do with single run of tests (on the first attempt), ...

##### Good luck, have fun!
