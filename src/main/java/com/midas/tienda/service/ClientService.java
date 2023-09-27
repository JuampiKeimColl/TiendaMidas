package com.midas.tienda.service;

import com.midas.tienda.entities.Client;
import com.midas.tienda.enums.Rol;
import com.midas.tienda.exceptions.MyException;
import com.midas.tienda.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void registrar(String name, String email, String password, String password2) throws MyException {
        validar(name, email, password, password2);
        Client client = new Client();
        
        client.setName(name);
        client.setEmail(email);
        client.setPassword(new BCryptPasswordEncoder().encode(password));
        darRol(email,client);

        clientRepository.save(client);
    }

    private void validar(String name, String email, String password, String password2) throws MyException {
        if(name.isEmpty() || name == null){
            throw new MyException("El nombre es obligatorio.");
        }
        if(email.isEmpty() || email == null){
            throw new MyException("El email es obligatorio.");
        }
        if(password.isEmpty() || password == null ||password.length()<5){
            throw new MyException("El password es obligatorio y debe tener más de 5 dígitos.");
        }
        if(!password.equals(password2)){
            throw new MyException("Los passwords deben ser iguales.");
        }

    }

    private void darRol(String email, Client client){

        if(email.contains("@midas.com")){
            client.setRol(Rol.ADMIN);
        } else {
            client.setRol(Rol.USER);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.buscarPorEmail(email);

        if(client != null){
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ client.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attributes.getRequest().getSession(true);
            session.setAttribute("usersession", client);

            return new User(client.getEmail(), client.getPassword(),permisos);
        }else{
            return null;
        }
    }
}
