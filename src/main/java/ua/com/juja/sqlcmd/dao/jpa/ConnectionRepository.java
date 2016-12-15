package ua.com.juja.sqlcmd.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.DatabaseConnection;

public interface ConnectionRepository extends CrudRepository<DatabaseConnection, Integer> {
    DatabaseConnection findByDatabaseNameAndUserName(String databaseName, String userName);
}
