# DSVtoJsonL

Converts DSV to JsonL

## Installation

Open on any IDE

```bash
mvn clean install
mvn package
```

## Usage

```bash
java -cp DsvToJsonL-1.0-SNAPSHOT.jar org.example.App "sourcePath" "targetPath" "delimeter" "replaceRegex" "replaceWith"
```

## CLI Arguments
(Mandatory Arguments)
1. sourcePath: this is the directory of DSV
2. targetPath: this is the directory of outputFile
3. delimeter

(Optional Arguments)   

4. replaceRegex: Regex to replace on values
5. replaceWith: String to replace Regex
6. ... you can pass more replaceRegex and ReplaceWith arguments for further value cleaning

## Author

John Paul Ebreo
