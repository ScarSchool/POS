javafx="C:/javalibs/javafx-sdk-11.0.2/lib"
javafxModules="javafx.controls,javafx.fxml"
bin="./out/production/plain-javafx" # replace plain-javafx with your project name !!!

echo 'Compiling the javafx app...'
javac -d $bin --module-path $javafx --add-modules $javafxModules ./src/**/*.java

echo 'Copying the fxml files...'
cd ./src
cp -r --parents ./**/*.fxml ../$bin
cd ..

echo 'Running the javafx app...'
java -cp $bin --module-path $javafx --add-modules $javafxModules sample.Main
