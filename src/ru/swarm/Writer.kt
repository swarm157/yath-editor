package ru.swarm

import com.sun.tools.javac.Main
import ru.swarm.Util.*
import java.io.File
import java.io.FileWriter

class Writer {
    private fun toExport(buffer: ArrayList<String>) : String {
        var out = "\n"
        for(variable in buffer) {
            println(variable)
            var template= "export PATH=$variable:\$PATH\n"
            println(template)
            out+=template
        }
        return out
    }
    private fun templateOf(chose: Mode) : String {
        when(chose) {
            Mode.etcProf -> {
                return Util.etcProfDefault
            }
            Mode.etcPass -> {
                return Util.etcPassDefault
            }
            Mode.etcEnv -> {
                return Util.etcEnvDefault
            }
            Mode.bashrc -> {
                return Util.bashrcDefault
            }
            Mode.profile -> {
                return  Util.profileDefault
            }
            Mode.zshrc -> {
                return Util.zshrcDefault
            }
        }
    }
    fun write(chose: Mode, buffer: ArrayList<String>) {
        var export = toExport(buffer)
        var out = "${templateOf(chose)}${toExport(buffer)}"
        var target = targetOf(chose)
        var writer = FileWriter(File(target))
        writer.write(out)
        writer.flush()
        writer.close()
    }

    private fun targetOf(chose: Mode): String {
        var user = System.getProperty("user.name")
        when(chose) {
            Mode.etcProf -> {
                return etcProfileDir
            }
            Mode.etcPass -> {
                return passwdDir
            }
            Mode.etcEnv -> {
                return environmentDir
            }
            Mode.bashrc -> {
                return if(!user.equals("root")) "/home/$user/.$bashrcDir" else "/miss.txt"
            }
            Mode.profile -> {
                return if(!user.equals("root")) "/home/$user/.$profileDir" else "/miss.txt"
            }
            Mode.zshrc -> {
                return if(!user.equals("root")) "/home/$user/.zshrc" else "/miss.txt"
            }
        }
    }
}