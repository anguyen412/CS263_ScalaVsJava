# CS_263
Java version: 
```
java version "17.0.14" 2025-01-21 LTS
Java(TM) SE Runtime Environment (build 17.0.14+8-LTS-191)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.14+8-LTS-191, mixed mode, sharing
```

Scala version:
```
Scala code runner version 3.3.0 -- Copyright 2002-2023, LAMP/EPFL
```


Commands for dumping the bytecode:<br>
for scala:
```
javap -c [CLASSNAME].class > [CLASSNAME]_object_bytecode.txt
javap -v [CLASSNAME].class > [CLASSNAME]_cp_and_object_bytecode.txt
javap -c [CLASSNAME]$.class > [CLASSNAME]_class_bytecode.txt
javap -v [CLASSNAME]$.class > [CLASSNAME]_cp_and_class_bytecode.txt
```

for java:
```
javap -c [CLASSNAME].class > [CLASSNAME]_bytecode.txt
javap -v [CLASSNAME].class > [CLASSNAME]_cp_and_bytecode.txt
```


For using JMH:<br>
For Java: <br>
From the highest level of the directory (where the pom.xml file is, in the example it would be the folder java-benchmarks) run the command `mvn clean install` <br>
Then run the tests using: `java -jar target/benchmarks.jar` <br>
Tests should be written as methods inside src/main/java/jenkov/MyBenchmark.java <br>

For Scala: <br>
From the highest level of the directory (where the build.sbt file is, in the example it would be the folder scala-benchmarks) run the command <br>
`sbt jmh:compile` <br>
Then run the tests using `sbt jmh:run`<br>
Test should be written as functions inside src/main/scala/example/MyBenchmark.scala <br>
