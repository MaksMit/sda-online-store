package lv.sda.sdaonlinestore.service;

import lv.sda.sdaonlinestore.entity.StoreUser;
import lv.sda.sdaonlinestore.repo.StoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class StoreUserService {
    @Autowired
    StoreUserRepository storeUserRepository;

    public StoreUser findById(Long id) {
        return storeUserRepository.findById(id).get();
    }

    public List<StoreUser> getAllStoreUsers() {
        List<StoreUser> storeUser = new ArrayList<StoreUser>();
        storeUserRepository.findAll().forEach(e -> storeUser.add(e));
        return storeUser;
    }
    public void saveOrUpdate(StoreUser storeUser)
    {
        storeUserRepository.save(storeUser);
    }
    public void update(StoreUser storeUser, Long storeUserId)
    {
        storeUserRepository.save(storeUser);
    }
    public void save(StoreUser storeUser) {
        storeUserRepository.save(storeUser);
    }

    public void delete(StoreUser user) {
        storeUserRepository.delete(user);
    }

    public void deleteById(Long userId) {
        storeUserRepository.deleteById(userId);
    }
}
