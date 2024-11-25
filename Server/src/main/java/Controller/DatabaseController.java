package Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Services.DatabaseService;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/test-db")
    public String testDatabase() {
        try {
            databaseService.testConnection();
            return "Conexión a la base de datos exitosa";
        } catch (Exception e) {
            return "Error al conectar a la base de datos: " + e.getMessage();
        }
    }
}