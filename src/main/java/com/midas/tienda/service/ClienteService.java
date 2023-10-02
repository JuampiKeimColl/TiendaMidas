package com.midas.tienda.service;

import com.midas.tienda.persistence.entity.Cliente;
import com.midas.tienda.enums.Rol;
import com.midas.tienda.exceptions.MiException;
import com.midas.tienda.persistence.repository.ClienteRepository;
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
public class ClienteService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void registrar(String name, String email, String password, String password2) throws MiException {
        validar(name, email, password, password2);
        Cliente cliente = new Cliente();
        
        cliente.setClienteName(name);
        cliente.setEmail(email);
        cliente.setPassword(new BCryptPasswordEncoder().encode(password));
        darRol(email,cliente);

        clienteRepository.save(cliente);
    }

    @Transactional
    public void modificarCliente(String name, String email, String password, String password2) throws MiException {
        validar(name, email, password, password2);
        Cliente cliente = new Cliente();

        cliente.setClienteName(name);
        cliente.setEmail(email);
        cliente.setPassword(new BCryptPasswordEncoder().encode(password));
        darRol(email,cliente);

        clienteRepository.save(cliente);
    }

    private void validar(String name, String email, String password, String password2) throws MiException {
        if(name.isEmpty()){
            throw new MiException("El nombre es obligatorio.");
        }
        if(email.isEmpty()){
            throw new MiException("El email es obligatorio.");
        }
        if(password.length() < 5){
            throw new MiException("El password es obligatorio y debe tener más de 5 dígitos.");
        }
        if(!password.equals(password2)){
            throw new MiException("Los passwords deben ser iguales.");
        }

    }

    public List<Cliente> listarClientes(){

        return clienteRepository.findAll();
    }

    private void darRol(String email, Cliente cliente){

        if(email.contains("@midas.com")){
            cliente.setRol(Rol.ADMIN);
        } else {
            cliente.setRol(Rol.USER);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.buscarPorEmail(email);

        if(cliente != null){
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ cliente.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attributes.getRequest().getSession(true);
            session.setAttribute("usersession", cliente);

            return new User(cliente.getEmail(), cliente.getPassword(),permisos);
        }else{
            return null;
        }
    }
}
