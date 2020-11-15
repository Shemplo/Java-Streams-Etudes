@echo off

::: compile and run java sources (your implementation and tests)
javac -sourcepath src -d bin src/tasks/StreamTasksMain.java && java -ea -cp bin tasks.StreamTasksMain %*

echo Exit code %errorlevel%

::: wait until you typed some key (to see testing results)
pause