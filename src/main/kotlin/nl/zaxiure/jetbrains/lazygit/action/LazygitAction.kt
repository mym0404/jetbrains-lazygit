package nl.zaxiure.jetbrains.lazygit.action

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class LazygitAction : AnAction () {

    override fun actionPerformed(e: AnActionEvent) {
        val terminalToolWindowManager = TerminalToolWindowManager.getInstance(e.project!!)

        val terminalWidget = terminalToolWindowManager.createLocalShellWidget(e.project!!.basePath, "Lazygit")
        terminalWidget.executeCommand("lazygit;exit")

        val actionManager = ActionManager.getInstance()
        val action = actionManager.getAction("Terminal.MoveToEditor");
        actionManager.tryToExecute(action, null, terminalWidget.component, null, true)
    }
}