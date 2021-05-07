package at.scarc.springbootjparest.services;

import at.scarc.springbootjparest.models.User;
import at.scarc.springbootjparest.repositories.MailRepository;
import at.scarc.springbootjparest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repo;

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }

    public List<User> getByUserType(String usertype) {
        return repo.findByUserType(usertype);
    }

    @Override
    public User getOne(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public User createOrUpdateOne(User entity) {
        return repo.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        try {
            repo.deleteById(id);
        } catch(Exception ex) {

        }
        return true;
    }
}
