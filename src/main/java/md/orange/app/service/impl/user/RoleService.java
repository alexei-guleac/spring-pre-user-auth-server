package md.orange.app.service.impl.user;


import java.util.List;
import md.orange.app.model.data.auth.RoleData;
import md.orange.app.model.entity.Role;
import md.orange.app.model.enums.ERole;

public interface RoleService {

    Role findById(long id);

    Role findByName(ERole eRole);

    RoleData save(RoleData role);

    List<Role> findAll();

}
