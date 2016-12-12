package ua.com.juja.sqlcmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.juja.sqlcmd.dao.DataSet;
import ua.com.juja.sqlcmd.dao.manager.DatabaseManager;
import ua.com.juja.sqlcmd.dao.repository.jpa.ConnectionRepository;
import ua.com.juja.sqlcmd.dao.repository.jpa.UserActionRepository;
import ua.com.juja.sqlcmd.dao.repository.jpa.UserRepository;
import ua.com.juja.sqlcmd.model.DatabaseConnection;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserAction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.*;

@Component
public class ServiceImpl implements Service {

    @Autowired
    private DatabaseManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private UserActionRepository userActionRepository;

    @Autowired
    EntityManager em;

    @Autowired
    EntityManagerFactory emf;

//    @Autowired
//    private EntityManagerFactory emf;

    @Override
    public void connect(String databaseName, String userName, String password) {
        manager.connect(databaseName, userName, password);
    }

    @Override
    public boolean isConnected() {
        return manager.isConnected();
    }

    @Override
    public String currentDatabase() {
        return manager.currentDatabase();
    }

    @Override
    public Set<String> getDatabaseNames() {
        return manager.getDatabaseNames();
    }

    @Override
    public void createDatabase(String databaseName) {
        //TODO check exist database
        manager.createDatabase(databaseName);
    }

    @Override
    public void dropDatabase(String databaseName) {
        manager.dropDatabase(databaseName);
    }

    @Override
    public Set<String> getTableNames() {
        return manager.getTableNames();
    }

    @Override
    public void createTable(String tableName, String query) {
        manager.createTable(tableName, query);
    }

    @Override
    public void clearTable(String tableName) {
        manager.clearTable(tableName);
    }

    @Override
    public void dropTable(String tableName) {
        manager.dropTable(tableName);
    }

    @Override
    public Set<String> getTableColumns(String tableName) {
        return manager.getTableColumns(tableName);
    }

    @Override
    public List<List<String>> getTableData(String tableName) {
        Set<String> columns = manager.getTableColumns(tableName);
        List<DataSet> data = manager.getTableData(tableName);
        List<List<String>> tableData = new ArrayList<>();
        for (DataSet set : data) {
            List<Object> row = set.getValues();
            List<String> tableRow = new ArrayList<>();
            for (Object o: row) {
                tableRow.add(o.toString());
            }
            tableData.add(tableRow);
        }
        return tableData;
    }

    @Override
    public Map<String, String> getRecordData(String tableName, int id) {
        DataSet set = manager.getRecordData(tableName, id);
        Map<String, Object> data = set.getData();
        Map<String, String> record = new TreeMap<>();
        for (Map.Entry<String, Object> entry : data.entrySet()) {
            record.put(entry.getKey(), entry.getValue().toString());
        }
        record.remove("id");
        return record;
    }

    @Override
    public void createRecord(String tableName, Map<String, String[]> parameters) {
        DataSet dataSet = new DataSet();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (!("tableName".equals(entry.getKey()) || "id".equals(entry.getKey()))) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.createRecord(tableName, dataSet);
    }

    @Override
    public void deleteRecord(String tableName, int id) {
        manager.deleteRecord(tableName, id);
    }

    @Override
    public void updateRecord(String tableName, int id, Map<String, String[]> parameters) {
        DataSet dataSet = new DataSet();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            if (!("tableName".equals(entry.getKey()) || "id".equals(entry.getKey()))) {
                dataSet.put(entry.getKey(), entry.getValue()[0]);
            }
        }
        manager.updateRecord(tableName, id, dataSet);
    }

    @Override
    public List<List<String>> executeQuery(String query) {
        List<List<String>> result = new ArrayList<>();
        List<DataSet> data = manager.executeQuery(query);
        if (!data.isEmpty()) {
            Set<String> columns = new LinkedHashSet<>(data.get(0).getNames());
            List<String> columnRow = new ArrayList<>();
            columnRow.addAll(columns);
            result.add(columnRow);
            for (DataSet set : data) {
                List<Object> row = set.getValues();
                List<String> tableRow = new ArrayList<>();
                for (Object o: row) {
                    tableRow.add(o.toString());
                }
                result.add(tableRow);
            }
        }
        return result;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public DatabaseConnection saveDatabaseConnection(DatabaseConnection connection) {
        return connectionRepository.save(connection);
    }

    @Override
    @Transactional
    public UserAction saveUserAction(String description) {
        //TODO refactor!!!!
//        User repositoryUser = userRepository.findByLogin("test");
        User user = new User();
//        if (repositoryUser == null) {
//            user.setId(179);
            user.setLogin("test");
            user.setPassword("2222");
            user.setEmail("1@1.com");
//        } else {
//            user.setPassword(repositoryUser.getPassword());
//            user.setLogin(repositoryUser.getLogin());
//            user.setPassword(repositoryUser.getPassword());
//            user.setEmail(repositoryUser.getEmail());
//        }
//        User user2 = new User();
////        if (repositoryUser == null) {
//        user2.setLogin("test8");
//        user2.setPassword("22228");
//        user2.setEmail("1@18.com");
//        em = emf.createEntityManager();
//        em.getTransaction().begin();
//        emf.createEntityManager().persist(user2);
//        em.getTransaction().commit();
//        EntityManager em = emf.createEntityManager()
//        em.merge(user);
//        em.p
//        DatabaseConnection connection = connectionRepository.findByDatabaseNameAndUserName("database1", "database user 1");
//        if (connection == null) {
            DatabaseConnection connection = new DatabaseConnection();
            connection.setDatabaseName("database 1");
            connection.setUserName("database user 1");
//        }

        UserAction action = new UserAction();
        action.setUser(user);
        action.setDatabaseConnection(connection);
        action.setAction(description);
        action.setDate(new Date());
        return userActionRepository.save(action);
    }
}
