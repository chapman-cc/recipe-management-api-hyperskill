package recipes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import recipes.adapters.RecipeUserAdapter;
import recipes.models.RecipeUser;
import recipes.repositories.RecipeUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RecipeUserRepository repository;

    @Autowired
    public UserDetailsServiceImpl(RecipeUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        RecipeUser user = repository
                .findByEmail(s)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new RecipeUserAdapter(user);
    }

}
