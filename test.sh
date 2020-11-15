#!/bin/bash

# compile and run java sources (your implementation and tests)
javac -sourcepath src -d bin src/tasks/StreamTasksMain.java && java -ea -cp bin tasks.StreamTasksMain $*

echo Exit code $?