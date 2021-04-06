package lv.sda.sdaonlinestore.repo;

import lv.sda.sdaonlinestore.entity.StoreUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreUserRepository extends CrudRepository <StoreUser,Long>{
}
