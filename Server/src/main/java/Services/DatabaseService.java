package Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseService {

    private final DataSource dataSource;

    @Autowired
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Conexión exitosa: " + connection.getCatalog());
        }
    }
}