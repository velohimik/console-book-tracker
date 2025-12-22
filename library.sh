#!/bin/bash

jar_name="library.jar"
jar_path="./out/$jar_name"

echo "Compiling..."
javac -d ./out -cp ./src/ ./src/example/app/Main.java
echo "Compiling is finished successfuly"

echo "Adding manifest file..."
echo "Main-Class: example.app.Main">./out/manifest.mf
echo "Packaging..."
jar -cfm $jar_path ./out/manifest.mf -C ./out/ .
echo "Archive $jar_name is created successfully"