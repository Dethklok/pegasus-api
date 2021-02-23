package net.pegasus.api.rest;


import lombok.RequiredArgsConstructor;
import net.pegasus.api.dto.AuthenticationRequestDto;
import net.pegasus.api.dto.AuthenticationResponseDto;
import net.pegasus.api.secutity.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationControllerV1 {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;

  @PostMapping("/signin")
  public AuthenticationResponseDto authenticate(@RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
    try {
      authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword())
      );
    } catch (BadCredentialsException exception) {
      throw new Exception("Incorrect username or password");
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());

    return new AuthenticationResponseDto(jwtService.generateToken(userDetails));
  }

}
