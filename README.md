# Intrum

## It's important to read this before run

For running tests with examples from .csv I implemented class which rewrite .feature file and inject there data from csv file.
This script is activated with:

```# @csvFile=<<PathToFile>>```
example:
```# @csvFile=src/test/resources/com/krzysztofzerman/examples.csv```

And this line should be line above ```Examples:```

What script do is:
1. Looking for initiation line with *@csvFile* 
2. After ```Examples:``` delete all line with pipe next to each other
3. Input new data to Examples
4. if there are no pipe in next line but data should be provided it will be
4. if file is ending with line ```Examples:``` data will be also added

Thanks to declaration ```@csvFile``` inside feature, script is able to input such data in few places and have control over it.

## How to run it?
First run adding csv to features with:
```mvn compile exec:java``` or just run class src/main/java/krzysztofzerman/PrepareProjectForTests.java in IDE


Tests you can run with:
```mvn test``` or run tests on class src/test/java/com/krzysztofzerman/RunCucumberTest.java in IDE

## Final
Have fun with editing Examples: by just editing CSV file or feature file ;) 

### Used tools
- Cucumber for BDD
- JUnit as test runner
- RestAssured as backend testing tool
- Git as Version Control System
