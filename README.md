# PDF Text Extractor

Project to extract text of pdf in original formatation.

## Clone and execute the project

```
git clone https://github.com/Jvnyor/pdf-text-extractor.git
```

## Download Maven dependencies

```
mvn clean install
```

## Generate jar with dependencies

```
mvn clean compile assembly:single
```

## Execute jar generated

```
java -jar target/pdf-text-extractor-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## How to use:

**1** - Insert the .pdf file path to extract the text.

**2** - Insert the .txt file path to save txt file generated.
