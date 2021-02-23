package net.pegasus.api.service;

import lombok.RequiredArgsConstructor;
import net.pegasus.api.domain.User;
import net.pegasus.api.repository.UserRepository;
import net.pegasus.api.secutity.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository
      .findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

    return new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getRoles());
  }

}
