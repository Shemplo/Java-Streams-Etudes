## Java Streams Etudes

This repository can be useful if you are beginner in _Java Stream API_. 
To check yourself you will need to implement some methods with described
logic using knoledge of Streams, Lambdas, Method handlers and other 
functional things.

### Running tests

Due to this project is orientated for begginers **no dependent libraries** are required.

* You will need just a **JVM of 11 version (or higher)**.

Clone this repository to your computer and then run main method from `StreamTasksMain` class in your preferred IDE or type in console:

```bash
javac -d bin/ src/*.java && java -ea -cp bin/ StreamTasksMain
```

Flag `-ea` is required to enable `assert` java key word for tests checker.

#### Possible cases:

* If you implemented successfully all methods in `StreamTasks` class you will see message `All tests completed!`
* If you missed to implement some method you will see message like `Method 'task[N]' is not implemened`
* If you implemented wrong login you will see message about an error

#### Solutions

In case you really don't know how to solve some task than you can see solution in `StreamTasksSolution` class.
Try to avoid this action :)

Remark: _code in `*Main` class is not optimal due to it shouldn't give hints_. 
The most proper solutions are placed in `StreamTasksSolution` class.

##### Good luck, have fun!
