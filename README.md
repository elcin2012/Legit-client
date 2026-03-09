# Custom Minecraft Launcher (Java Swing)

A beginner-friendly custom launcher with:

- **Launch Game** button to run `start_minecraft.bat`
- **Settings** button (placeholder dialog)
- **Exit** button
- Java installation check before launch
- Console log window showing launch progress

## Project Structure

- `src/LauncherApp.java` - app entry point
- `src/LauncherFrame.java` - Swing GUI
- `src/LaunchService.java` - Java check + batch launch logic
- `src/LauncherLogger.java` - timestamped log helper
- `start_minecraft.bat` - batch file called by the launcher

## How to Run

```bash
javac src/*.java
java -cp src LauncherApp
```

## Notes

- On Windows, `start_minecraft.bat` is executed using `cmd.exe /c`.
- Edit `start_minecraft.bat` and replace the example command with your own Minecraft launch command.
