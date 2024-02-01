package fr.charly.cap_entrprise.service;

import fr.charly.cap_entrprise.DTO.RegisterPostDTO;
import fr.charly.cap_entrprise.entity.Gamer;
import fr.charly.cap_entrprise.entity.Moderator;
import fr.charly.cap_entrprise.entity.User;
import fr.charly.cap_entrprise.repository.UserRepository;
import fr.charly.cap_entrprise.service.interfaces.DAOFindByIdInterface;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements DAOFindByIdInterface<User>, UserDetailsService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public User findByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public Gamer create(RegisterPostDTO registerPostDTO) {
        Gamer gamer = new Gamer();
        gamer.setNickname(registerPostDTO.getNickname());
        gamer.setEmail(registerPostDTO.getEmail());
        gamer.setPassword(passwordEncoder.encode(registerPostDTO.getPassword()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        gamer.setBirthAt(LocalDate.parse(registerPostDTO.getBirthAt(), formatter));
        return userRepository.saveAndFlush(gamer);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = findByNickname(username);

        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }




    private List<GrantedAuthority> userGrantedAuthority (User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_GAMER"));
    }


    public User findByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(EntityNotFoundException::new);
    }

}
