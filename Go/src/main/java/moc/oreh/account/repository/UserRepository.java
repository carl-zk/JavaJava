package moc.oreh.account.repository;

import moc.oreh.account.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepository extends BaseRepository<User> {
    public UserRepository() {
        System.out.println("userR-> " + this.getClass().getName());
    }
}

