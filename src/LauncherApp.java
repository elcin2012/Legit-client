import javax.swing.SwingUtilities;

public class LauncherApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LauncherFrame frame = new LauncherFrame();
            frame.setVisible(true);
        });
    }
}
