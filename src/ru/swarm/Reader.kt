package ru.swarm

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class Reader {
    fun read(chose: Mode) : ArrayList<String>? {
        var user = System.getProperty("user.name")
        var out = ArrayList<String>()
        when(chose) {
            Mode.bashrc -> {
                if (user.equals("root")) {

                }else {
                    var reader = BufferedReader(FileReader(File("/home/$user/.${Util.bashrcDir}")))
                    var buffer = ""
                    while (reader.ready() && buffer!=null) {
                        buffer = reader.readLine()!!
                        if (buffer.contains("export")) {
                            var variable = buffer.replace("export", "").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                            out.add(variable)
                        }
                    }
                }
            }
            Mode.profile -> {
                if (user.equals("root")) {

                } else {
                    var reader = BufferedReader(FileReader(File("/home/$user/.${Util.profileDir}")))
                    var buffer = ""
                    while (reader.ready() && buffer!=null) {
                        buffer = reader.readLine()
                        if (buffer.contains("export")) {
                            var variable = buffer.replace("export", "").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                            out.add(variable)
                        }
                    }
                }
            }
            Mode.etcEnv -> {
                var reader = BufferedReader(FileReader(File( "/etc/environment")))
                do {
                    var buffer = reader.readLine()!!

                    if(buffer.contains("export")) {
                        var variable =  buffer.replace("export","").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                        out.add(variable)
                    }
                } while (reader.ready() && buffer!=null)
            }
            Mode.etcPass -> {
                var reader = BufferedReader(FileReader(File(Util.passwdDir)))
                do {
                    var buffer = reader.readLine()!!
                    if(buffer.contains("export")) {
                        var variable =  buffer.replace("export","").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                        out.add(variable)
                    }
                } while (reader.ready() && buffer!=null)
            }
            Mode.etcProf -> {
                var reader = BufferedReader(FileReader(File(Util.etcProfileDir)))
                do {
                    var buffer = reader.readLine()!!
                    if(buffer.contains("export")) {
                        var variable =  buffer.replace("export","").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                        out.add(variable)
                    }
                } while (reader.ready() && buffer!=null)
            }
            Mode.zshrc -> {
                if (user.equals("root")) {

                }else {
                    var reader = BufferedReader(FileReader(File("/home/$user/.zshrc")))
                    var buffer = ""
                    while (reader.ready() && buffer!=null) {
                        buffer = reader.readLine()!!
                        if (buffer.contains("export")) {
                            var variable = buffer.replace("export", "").replace("=", "").replace(" ", "").replace("PATH", "").replace(":$", "")
                            out.add(variable)
                        }
                    }
                }
            }
        }
        return out
    }
}