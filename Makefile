

build:
	javac source/*.java -d bytecode/

run:
	java -cp bytecode/ Environment