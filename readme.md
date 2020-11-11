## Java Streams Etudes

This repository can be useful if you are beginner in _Java Stream API_. 
To check yourself you will need to implement some methods with described
logic using knoledge of Streams, Lambdas, Method handlers and other 
functional things.

### Running tests

Due to this project is orientated for beginners **no dependent libraries** are required.

* You will need just a **JVM of 11 version (or higher)**.

Clone this repository to your computer and then run main method from `StreamTasksMain` class in your preferred IDE or type in console:

```bash
javac -sourcepath src -d bin src/tasks/StreamTasksMain.java && java -ea -cp bin tasks.StreamTasksMain
```

Flag `-ea` is required to enable `assert` java key word for tests checker.

#### Check program and verdicts

Check program will run tests for all tasks and then write verdict for each of tasks.
Possible verdicts:

* **accepted** - you solved this task
* **wrong answer** - you almost solved this task (code compiles but login is wrong)
* **not implemented** - you didn't start to implement this task
* **missed NULL check** - you forgot to check something for `null` reference
* **runtime exception** - something goes extremely bad

Until you solved all tasks with verdict `accepted` the program will finish with non zero exit code.

#### Solutions

In case you really don't know how to solve some task than you can see solution in `StreamTasksSolution` class.
Try to avoid this action :)

##### Good luck, have fun!
