import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LauncherLogger {
    private final JTextArea logArea;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public LauncherLogger(JTextArea logArea) {
        this.logArea = logArea;
    }

    public void log(String message) {
        String timestampedMessage = "[" + LocalDateTime.now().format(formatter) + "] " + message + "\n";

        SwingUtilities.invokeLater(() -> {
            logArea.append(timestampedMessage);
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
}
