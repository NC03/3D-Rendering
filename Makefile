
VERSION = 1.2.2

build:
	javac source/*.java -d bytecode/

run_interactive:
	java -cp bytecode/ Interactive
	
run_enivronment:
	java -cp bytecode/ Environment

version:
	echo $(VERSION)

generate_video:
	ffmpeg -r 30 -i preview/video/%03d.png -c:v libx264 -vf fps=30 -pix_fmt yuv420p video_V$(VERSION).mp4