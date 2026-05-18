#!/bin/bash

#Variables
jar_name="library.jar"
jar_path="./out/$jar_name"

cd ..

#Compiling
echo "Compiling..."
javac -d ./out -cp ./src/main/java/ ./src/main/java/com/velohimik/ConsoleBookTracker.java
echo "Compiling is finished successfully"

#Add manifest file
echo "Adding manifest file..."
echo "Main-Class: com.velohimik.ConsoleBookTracker">./out/manifest.mf

#Package to jar file
echo "Packaging..."
jar -cfm $jar_path ./out/manifest.mf -C ./out/ .
echo "Archive $jar_name is created successfully"
