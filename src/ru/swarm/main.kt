package ru.swarm

import ru.swarm.Util.*
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import javax.swing.AbstractAction
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JDialog
import javax.swing.JFrame
import javax.swing.JList
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextField
import javax.swing.ScrollPaneConstants
import kotlin.system.exitProcess




var toolkit = Toolkit.getDefaultToolkit()

var window = JFrame("Yath editor")
var width = 250
var height = 425

var user = System.getProperty("user.name")
var none = println("you are $user")
var panel = JPanel()

var properties = JList<String>()
var scroll = JScrollPane(properties)
var add = JButton("add")
var edit = JButton("edit")
var delete = JButton("delete")
var apply = JButton("apply")
var done = JButton("done")
var reset = JButton("reset")
//var buffer = Util().toArrayList(Util.getProperties())
var choose = JButton("choose")

var options = JComboBox<String>(arrayOf(profileDir, bashrcDir, environmentDir,  etcProfileDir, passwdDir, zshrcDir))
var chose = Mode.profile
var buffer = Reader().read(chose)

var isRoot = user.equals("root")

class Choose : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        Writer().write(chose, buffer!!)
        when(options.selectedIndex) {
            0 -> {chose = Mode.profile}
            1 -> {chose = Mode.bashrc}
            2 -> {chose = Mode.etcEnv}
            3 -> {chose = Mode.etcProf}
            4 -> {chose = Mode.etcPass}
            5 -> {chose = Mode.zshrc}
        }
        buffer = Reader().read(chose)
        properties.setListData(Util().toArray(buffer))
    }

}

class Reset : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        when(chose) {
            Mode.bashrc -> {
                var writer = FileWriter(File("/home/$user/.bashrc"))
                writer.write(Util.bashrcDefault)
                writer.flush()
                writer.close()
            }
            Mode.profile -> {
                var writer = FileWriter(File("/home/$user/.profile"))
                writer.write(Util.profileDefault)
                writer.flush()
                writer.close()
            }
            Mode.etcEnv -> {
                var writer = FileWriter(File("/etc/environment"))
                writer.write(Util.etcEnvDefault)
                writer.flush()
                writer.close()
            }
            Mode.etcPass -> {
                var writer = FileWriter(File("/etc/passwd"))
                writer.write(Util.etcPassDefault)
                writer.flush()
                writer.close()
            }
            Mode.etcProf -> {
                var writer = FileWriter(File("/etc/profile"))
                writer.write(Util.etcProfDefault)
                writer.flush()
                writer.close()
            }
            Mode.zshrc -> {
                var writer = FileWriter(File("/home/$user/$zshrcDir"))
                writer.write(Util.etcProfDefault)
                writer.flush()
                writer.close()
            }
        }
        when(options.selectedIndex) {
            0 -> {chose = Mode.profile}
            1 -> {chose = Mode.bashrc}
            2 -> {chose = Mode.etcEnv}
            3 -> {chose = Mode.etcProf}
            4 -> {chose = Mode.etcPass}
            5 -> {chose = Mode.zshrc}
        }
        buffer = Reader().read(chose)
        properties.setListData(Util().toArray(buffer))
    }

}

class Delete : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        buffer?.removeAt(properties.selectedIndex);
        properties.setListData(Util().toArray(buffer))
    }

}

class Add : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        Dialog().isVisible = true
    }

}

class Apply : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        Writer().write(chose, buffer!!)
        when(options.selectedIndex) {
            0 -> {chose = Mode.profile}
            1 -> {chose = Mode.bashrc}
            2 -> {chose = Mode.etcEnv}
            3 -> {chose = Mode.etcProf}
            4 -> {chose = Mode.etcPass}
            5 -> {chose = Mode.zshrc}
        }
        buffer = Reader().read(chose)
    /*var env = File("/etc/environment")
        var envWriter = FileWriter(env)

        envWriter.write(Util.makePath(buffer))
        envWriter.flush()
        envWriter.close()*/
        //Runtime.getRuntime().exec("echo ${Util.makePath(buffer)} > /etc/environment")
        //Runtime.getRuntime().exec("source /etc/profile")
    }

}

class Done : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        //println(Util.makePath(buffer))
        //Runtime.getRuntime().exec("export PATH=${Util.makePath(buffer)}")
        Writer().write(chose, buffer!!)
        when(options.selectedIndex) {
            0 -> {chose = Mode.profile}
            1 -> {chose = Mode.bashrc}
            2 -> {chose = Mode.etcEnv}
            3 -> {chose = Mode.etcProf}
            4 -> {chose = Mode.etcPass}
            5 -> {chose = Mode.zshrc}
        }
        buffer = Reader().read(chose)
        /*var env = File("/etc/environment")
        var envWriter = FileWriter(env)

        envWriter.write(Util.makePath(buffer))
        envWriter.flush()
        envWriter.close()*/
        //Runtime.getRuntime().exec("echo ${Util.makePath(buffer)} > /etc/environment")
        //Runtime.getRuntime().exec("source /etc/profile")
        exitProcess(0)
    }

}

class Edit : AbstractAction() {
    override fun actionPerformed(e: ActionEvent?) {
        DialogEdit().isVisible = true
        window.isFocusable = false
    }

}

class DialogEdit : JDialog() {
    lateinit var field: JTextField;
    var ok = JButton("ok")
    var cancel = JButton("cancel")

    inner class Cancel(var dialog: JDialog, var jTextField: JTextField, ) : AbstractAction() {
        override fun actionPerformed(e: ActionEvent?) {
            dialog.isVisible = false
            jTextField.text = ""
            window.isFocusable = true
        }
    }
    inner class Ok(var dialog: JDialog, var jTextField: JTextField, ) : AbstractAction() {
        override fun actionPerformed(e: ActionEvent?) {
            dialog.isVisible = false
            buffer?.set(properties.selectedIndex, jTextField.text)
            jTextField.text = ""
            properties.setListData(Util().toArray(buffer))
            window.isFocusable = true
        }

    }

    inner class WS : WindowListener {
        override fun windowOpened(e: WindowEvent?) {

        }

        override fun windowClosing(e: WindowEvent?) {

        }

        override fun windowClosed(e: WindowEvent?) {
            window.isFocusable = true
        }

        override fun windowIconified(e: WindowEvent?) {

        }

        override fun windowDeiconified(e: WindowEvent?) {

        }

        override fun windowActivated(e: WindowEvent?) {

        }

        override fun windowDeactivated(e: WindowEvent?) {

        }

    }

    init {
        setSize(300, 200)
        setLocation(toolkit.screenSize.width/3, toolkit.screenSize.height/3)
        addWindowListener(WS())
        field = JTextField(20)
        field.text = properties.selectedValue
        var panel = JPanel()
        add(panel)
        panel.add(field)
        panel.add(ok)
        panel.add(cancel)
        title = "edit value"
        ok.addActionListener(this.Ok(this, field))
        cancel.addActionListener(Cancel(this, field))
    }
}

class Dialog : JDialog() {
    lateinit var field: JTextField;
    var ok = JButton("ok")
    var cancel = JButton("cancel")
    inner class Cancel(var dialog: JDialog, var jTextField: JTextField, ) : AbstractAction() {
        override fun actionPerformed(e: ActionEvent?) {
            dialog.isVisible = false
            jTextField.text = ""
        }

    }
    inner class Ok(var dialog: JDialog, var jTextField: JTextField, ) : AbstractAction() {
        override fun actionPerformed(e: ActionEvent?) {
            dialog.isVisible = false
            buffer?.add(jTextField.text)
            jTextField.text = ""
            properties.setListData(Util().toArray(buffer))
        }
    }
    init {

        setSize(300, 200)
        setLocation(toolkit.screenSize.width/3, toolkit.screenSize.height/3)
        field = JTextField(20)
        var panel = JPanel()
        add(panel)
        panel.add(field)
        panel.add(ok)
        ok.addActionListener(Ok(this, field))
        cancel.addActionListener(Cancel(this, field))
        panel.add(cancel)
        title = "add value"
    }
}

fun main(args: Array<String>) {


    window.isResizable = false
    window.setSize(width, height)
    window.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    window.setLocation(toolkit.screenSize.width/3, toolkit.screenSize.height/3)

    scroll.verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
    scroll.horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
    scroll.maximumSize = Dimension(width, 300)
    scroll.preferredSize = Dimension(width, 300)
    scroll.minimumSize = Dimension(width, 300)


    window.add(panel)
    panel.add(options)
    panel.add(choose)
    panel.add(scroll)
    panel.add(add)
    panel.add(edit)
    panel.add(delete)
    panel.add(reset)
    panel.add(apply)
    panel.add(done)
    reset.addActionListener(Reset())
    add.addActionListener(Add())
    edit.addActionListener(Edit())
    delete.addActionListener(Delete())
    apply.addActionListener(Apply())
    done.addActionListener(Done())
    choose.addActionListener(Choose())

    properties.setListData(Util().toArray(buffer))
    /*var temp = System.getProperties().propertyNames().toList().toTypedArray()
    var temp2 = arrayOfNulls<String>(temp.size)
    for ((i, t) in temp.withIndex()) {
        temp2[i] = System.getProperty(t.toString())
    }
    properties.setListData(temp2)*/

    window.isVisible = true
}