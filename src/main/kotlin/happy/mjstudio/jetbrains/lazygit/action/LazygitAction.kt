package happy.mjstudio.jetbrains.lazygit.action

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.wm.ToolWindowManager
import org.jetbrains.plugins.terminal.TerminalToolWindowManager

class LazygitAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val workingDirectory = project.basePath ?: return

        val terminalManager = TerminalToolWindowManager.getInstance(project)
        val toolWindowManager = ToolWindowManager.getInstance(project)
        val terminalToolWindow = toolWindowManager.getToolWindow("Terminal")

        // Check if terminal tool window was initially visible
        val wasTerminalVisible = terminalToolWindow?.isVisible == true

        // Check if a Lazygit terminal already exists
        val existingLazygitWidget =
            terminalManager.terminalWidgets
                .find { it.terminalTitle.buildTitle().contains("Lazygit") }

        if (existingLazygitWidget != null) {
            // If exists, focus it and move to editor
            existingLazygitWidget.requestFocus()
            moveTerminalToEditor(existingLazygitWidget.component)

            // If terminal wasn't visible initially, close the tool window
            if (!wasTerminalVisible) {
                ApplicationManager.getApplication().invokeLater(
                    { terminalToolWindow?.hide() },
                    ModalityState.NON_MODAL,
                )
            }
        } else {
            // Create new terminal - use the most compatible method
            val terminalWidget = terminalManager.createLocalShellWidget(workingDirectory, "Lazygit", true)

            // Execute lazygit command
            terminalWidget.executeCommand("lazygit")

            // Wait a moment for the terminal to be ready, then move to editor
            ApplicationManager.getApplication().invokeLater({
                terminalWidget.requestFocus()
                moveTerminalToEditor(terminalWidget.component)

                // If terminal wasn't visible initially, close the tool window after moving
                if (!wasTerminalVisible) {
                    ApplicationManager.getApplication().invokeLater(
                        { terminalToolWindow?.hide() },
                        ModalityState.NON_MODAL,
                    )
                }
            }, ModalityState.NON_MODAL)
        }
    }

    private fun moveTerminalToEditor(terminalComponent: java.awt.Component) {
        val actionManager = ActionManager.getInstance()
        val moveToEditorAction = actionManager.getAction("Terminal.MoveToEditor")

        if (moveToEditorAction != null) {
            actionManager.tryToExecute(
                moveToEditorAction,
                null,
                terminalComponent,
                null,
                true,
            )
        }
    }
}
