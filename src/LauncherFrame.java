import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

public class LauncherFrame extends JFrame {
    private final JTextArea logArea;
    private final LauncherLogger logger;
    private final LaunchService launchService;

    public LauncherFrame() {
        setTitle("Custom Minecraft Launcher");
        setSize(620, 420);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("Custom Minecraft Launcher", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        logArea = new JTextArea();
        logArea.setEditable(false);

        logger = new LauncherLogger(logArea);
        launchService = new LaunchService(logger);

        JPanel buttonPanel = createButtonPanel();
        JScrollPane logScrollPane = new JScrollPane(logArea);

        add(titleLabel, BorderLayout.NORTH);
        add(logScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        logger.log("Launcher ready.");
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton launchButton = new JButton("Launch Game");
        JButton settingsButton = new JButton("Settings");
        JButton exitButton = new JButton("Exit");

        launchButton.addActionListener(event -> onLaunchClicked());
        settingsButton.addActionListener(event -> onSettingsClicked());
        exitButton.addActionListener(event -> System.exit(0));

        panel.add(launchButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        return panel;
    }

    private void onLaunchClicked() {
        logger.log("Launch button pressed.");

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                if (!launchService.isJavaInstalled()) {
                    logger.log("Cannot launch: Java is not installed or not available in PATH.");
                    return null;
                }

                launchService.launchGame();
                return null;
            }
        }.execute();
    }

    private void onSettingsClicked() {
        logger.log("Settings button pressed.");
        JOptionPane.showMessageDialog(
                this,
                "Settings are not implemented yet.\nYou can add options like RAM and game path here.",
                "Settings",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
