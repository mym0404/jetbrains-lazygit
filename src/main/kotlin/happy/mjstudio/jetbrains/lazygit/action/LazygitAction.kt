package happy.mjstudio.jetbrains.lazygit.action

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class LazygitAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val workingDirectory = project.basePath ?: return

        val terminalManager = TerminalToolWindowManager.getInstance(project)

        // Create terminal in tool window first
        val terminalWidget = terminalManager.createLocalShellWidget(workingDirectory, "Lazygit", true)

        // Execute lazygit command
        terminalWidget.executeCommand("lazygit")

        // Move terminal to editor tab
        val actionManager = ActionManager.getInstance()
        val moveToEditorAction = actionManager.getAction("Terminal.MoveToEditor")

        // Execute the move action with the terminal widget as context
        if (moveToEditorAction != null) {
            actionManager.tryToExecute(
                moveToEditorAction,
                null,
                terminalWidget.component,
                null,
                true,
            )
        }
    }
}
