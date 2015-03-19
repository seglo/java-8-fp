# Introduction to Functional Programming (FP) principles in Java 8

## [Presentation](http://rawgit.com/seglo/java-8-fp/master/presentation/java8fp.html)

## Examples

### Java 8 FP examples

Located in `src/test/java/seglo`

* `PredicateTests`
* `PipeliningTests`

### name-finder app

Example app using file as a source.

`main()` in `src/main/java/seglo/App`

#### Assumptions

* Names are case sensitive
* Input file has one name per line with no space padding

#### Usage

```
mvn package
java -cp target/java-8-fp-1.0-SNAPSHOT.jar seglo.App names.txt bob jane foo
```

## Run Tests

```
mvn test
```
