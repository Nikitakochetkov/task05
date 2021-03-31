package net.codejava.CodeJavaApp.repository;

import net.codejava.CodeJavaApp.entity.User;
import net.codejava.CodeJavaApp.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("dima03@gamil.com");
        user.setPassword("dima98");
        user.setFirstName("Dima");
        user.setLastName("Petuxov");

        User savedUser = repo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        Assertions.assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

//    @Test
//    public void findUserByEmail() {
//        String email = "dima03@gamil.com";
//
//        User user = repo.findByEmail(email);
//
//        Assertions.assertThat(user).isNotNull();
//    }
}
