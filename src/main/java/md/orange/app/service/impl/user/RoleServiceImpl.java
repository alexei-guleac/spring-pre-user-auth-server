package md.orange.app.service.impl.user;


import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import md.orange.app.mapper.RoleMapper;
import md.orange.app.model.data.auth.RoleData;
import md.orange.app.model.entity.Role;
import md.orange.app.model.enums.ERole;
import md.orange.app.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleMapper roleMapper;

  private final String ROLE_NOT_FOUND_BY_ID = "Role with id : %d was not found.";

  private final String ROLE_NOT_FOUND_BY_NAME = "Role with name : %d was not found.";

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public Role findById(long id) {
    return roleRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_BY_ID));
  }

  @Override
  public Role findByName(ERole eRole) {
    return roleRepository.findByRole(eRole)
        .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND_BY_NAME));
  }

  @Override
  public RoleData save(RoleData roleDto) {
    Role role = roleMapper.map(roleDto);
    return roleMapper.map(roleRepository.save(role));
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
