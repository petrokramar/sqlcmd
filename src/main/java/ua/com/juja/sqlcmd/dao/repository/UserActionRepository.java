package ua.com.juja.sqlcmd.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.juja.sqlcmd.model.UserAction;

public interface UserActionRepository extends CrudRepository<UserAction, Integer> {
}
