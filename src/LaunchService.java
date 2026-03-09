import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class LaunchService {
    private final LauncherLogger logger;

    public LaunchService(LauncherLogger logger) {
        this.logger = logger;
    }

    public boolean isJavaInstalled() {
        logger.log("Checking Java installation...");

        ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            String output = readOutput(process);
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                logger.log("Java detected successfully.");
                if (!output.isBlank()) {
                    logger.log("Java version details: " + output.replace("\n", " | "));
                }
                return true;
            }

            logger.log("Java check failed. Exit code: " + exitCode);
            if (!output.isBlank()) {
                logger.log("Details: " + output.replace("\n", " | "));
            }
            return false;
        } catch (IOException e) {
            logger.log("Unable to run Java check: " + e.getMessage());
            return false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log("Java check was interrupted.");
            return false;
        }
    }

    public void launchGame() {
        File batchFile = new File("start_minecraft.bat");

        if (!batchFile.exists()) {
            logger.log("Batch file not found: " + batchFile.getAbsolutePath());
            return;
        }

        logger.log("Starting launcher batch file...");

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batchFile.getAbsolutePath());
        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();
            String output = readOutput(process);
            int exitCode = process.waitFor();

            if (!output.isBlank()) {
                logger.log("Batch output: " + output.replace("\n", " | "));
            }

            if (exitCode == 0) {
                logger.log("Minecraft launch command finished successfully.");
            } else {
                logger.log("Minecraft launch command failed. Exit code: " + exitCode);
            }
        } catch (IOException e) {
            logger.log("Failed to start batch file: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.log("Game launch was interrupted.");
        }
    }

    private String readOutput(Process process) throws IOException {
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }
        }

        return output.toString().trim();
    }
}
