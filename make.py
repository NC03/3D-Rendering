import sys
import os

if __name__ == "__main__":
    if(sys.argv[1] == "buildJar"):
        files = os.listdir("bytecode/")
        string = ""
        for file in files:
            string += "-C bytecode/ \"{}\" ".format(file.replace("$","\$"))
        print("jar -cfe Interactive 3DSimulation.jar "+string)
        os.system("jar -cfe 3DSimulation.jar Interactive "+string)
    elif(sys.argv[1] == "updateVersion"):
        