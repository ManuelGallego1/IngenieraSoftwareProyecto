package compraya.api.log;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class ActivityLogService {

    public void logActivity(String message) {
        String logMessage = LocalDateTime.now() + ": " + message;
        System.out.println(logMessage);
        String logFilePath = Paths.get("src/main/java/compraya/api/log/activity.log").toString();
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(logMessage + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
