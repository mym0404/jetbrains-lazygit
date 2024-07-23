# LazyGit in Editor Panel for any IntelliJ IDE

![Build](https://github.com/Zaxiure/jetbrains-lazygit/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/24917.svg)](https://plugins.jetbrains.com/plugin/24917)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/24917.svg)](https://plugins.jetbrains.com/plugin/24917)

<!-- Plugin description -->
Since any IntelliJ IDE doesn't really support an easy way to immediately open a terminal in the 'editor' panel
I've decided to create one which opens LazyGit as an editor panel terminal.
The only requirement of this plugin is that you have LazyGit installed, and you'll be able to add keybinds either
in any IntelliJ IDE or through ideavimrc with action 'LazyGit.Open'. When you exit lazygit the terminal itself also exits.

For now this is all the plugin does, maybe in the future I'll add an option to add any TUI you want.
<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:
  
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "jetbrains-lazygit"</kbd> >
  <kbd>Install</kbd>
  
- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/24917) and install it by clicking the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/24917/versions) from JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/Zaxiure/jetbrains-lazygit/releases/latest) and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>


---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
