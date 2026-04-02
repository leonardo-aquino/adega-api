package adega.projeto.com.demo.security;

import adega.projeto.com.demo.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        return funcionarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado: " + cpf));
    }
}
