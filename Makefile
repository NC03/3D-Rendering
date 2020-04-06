

build:
	javac source/*.java -d bytecode/

run_interactive:
	java -cp bytecode/ Interactive
	
run_enivronment:
	java -cp bytecode/ Environment