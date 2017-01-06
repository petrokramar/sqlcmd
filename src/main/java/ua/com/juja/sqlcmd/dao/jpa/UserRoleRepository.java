package ua.com.juja.sqlcmd.dao.jpa;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
    void deleteByUser(User user);
}
