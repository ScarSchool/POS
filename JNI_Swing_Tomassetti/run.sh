src="./src"
native="./native"
libs="./lib"
bin="./bin"

mainFile="$src/Main.java"
mainClass="Main"

nativeFile="$native/NativeTableModel.c"
listFile="$native/customList.c"

nativelib="$libs/NativeTableModel.o"
listlib="$libs/customList.o"


nativeOutputLib="$libs/libNativeTableModel.so"

modulePaths=/opt/javafx-sdk-12.0.1/lib
modules="--module-path $modulePaths --add-modules=ALL-MODULE-PATH"

javac -Xlint:unchecked -sourcepath $src $modules $mainFile -d $bin -h $native

mkdir -p $libs
g++ -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -c $nativeFile -o $nativelib
g++ -fPIC -c $listFile -o $listlib
gcc -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared $nativelib $listlib -o $nativeOutputLib

java -cp $bin $modules -Djava.library.path=$libs $mainClass
