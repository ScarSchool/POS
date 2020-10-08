javac -h . HelloWorld.java
gcc -fPIC -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -shared HelloWorld.c -o libhello.so
LD_LIBRARY_PATH=. java HelloWorld
