package xyz.ganguria.web.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.ganguria.web.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
