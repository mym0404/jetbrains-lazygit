package nl.zaxiure.jetbrains.lazygit.action

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.sh.run.ShRunner
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class LazygitAction : AnAction () {

    override fun actionPerformed(e: AnActionEvent) {
        ApplicationManager.getApplication().getService(ShRunner::class.java).run(e.project!!, "lazygit;exit", e.project?.basePath!!, "Lazygit", true)
        val terminalToolWindowManager = TerminalToolWindowManager.getInstance(e.project!!)

        val terminalWidget =  terminalToolWindowManager.terminalWidgets.find { terminal -> terminal.terminalTitle.buildTitle() == "Lazygit" }


        if (terminalWidget != null) {
            val actionManager = ActionManager.getInstance()
            val action = actionManager.getAction("Terminal.MoveToEditor");
            actionManager.tryToExecute(action, null, terminalWidget?.component, null, true)
        }
    }
}