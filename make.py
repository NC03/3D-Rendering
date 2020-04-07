import sys
import os
import re

if __name__ == "__main__":
    if(sys.argv[1] == "buildJar"):
        files = os.listdir("bytecode/")
        string = ""
        for file in files:
            string += "-C bytecode/ \"{}\" ".format(file.replace("$","\$"))
        print("jar -cfe Interactive 3DSimulation.jar "+string)
        os.system("jar -cfe 3DSimulation.jar Interactive "+string)
    elif(sys.argv[1] == "updateVersion"):
        code_files = os.listdir("source/")
        f = open("README.md","r")
        txt = f.read()
        f.close()
        m = re.search(r"Version (\d+\.\d+\.\d+)",txt)
        version = m.group(1)

        f = open("Makefile","r")
        txt = f.read()
        f.close()
        m = re.search(r"VERSION = (\d+\.\d+\.)(\d+)",txt)
        f = open("Makefile","w")
        f.write(txt[:m.start()] + "VERSION = "+version + txt[m.end():])
        f.close()

        for file in code_files:
            f = open("source/{}".format(file),"r")
            txt = f.read()
            f.close()
            m = re.search(r"@version (\d+\.\d+\.)(\d+)",txt)
            f = open("source/{}".format(file),"w")
            f.write(txt[:m.start()] + "@version "+version + txt[m.end():])
            f.close()

            