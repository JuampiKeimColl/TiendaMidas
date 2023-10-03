package com.midas.nuevatienda.service;

import com.midas.nuevatienda.dto.ClienteDTO;
import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.exceptions.ContrasenasDiferentesException;
import com.midas.nuevatienda.exceptions.EmailInvalidoException;
import com.midas.nuevatienda.exceptions.NombreInvalidoException;
import com.midas.nuevatienda.mapper.ClienteMapper;
import com.midas.nuevatienda.persistence.entity.Cliente;
import com.midas.nuevatienda.persistence.entity.enums.Rol;
import com.midas.nuevatienda.persistence.repository.ClienteRepository;
import com.midas.nuevatienda.request.RegistroRequest;
import com.midas.nuevatienda.util.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteDTO crearClienteUser(RegistroRequest registroRequest) throws BaseException{
        return commonUserRegistration(registroRequest,Rol.USER);
    }

    public ClienteDTO crearClienteAdmin(RegistroRequest registroRequest) throws BaseException {
        return commonUserRegistration(registroRequest,Rol.ADMIN);
    }

    private ClienteDTO commonUserRegistration(RegistroRequest registroRequest,Rol rol) throws BaseException {
        validarCliente(registroRequest);
        Cliente cliente = ClienteMapper.INSTANCE.toCliente(registroRequest, rol);
        return ClienteMapper.INSTANCE.toClienteDTO(clienteRepository.save(cliente));
    }


    public List<Cliente> listarUsuarios(){
        return clienteRepository.findAll();
    }

    public List<Cliente> findByRol(Rol rol){
        return clienteRepository.findAllByClienteRol(rol);
    }

    public void asignarRol(Rol rol){
        Cliente cliente = new Cliente();
        cliente.setRol(rol);

    }

    public void validarCliente(RegistroRequest registroRequest) throws BaseException {
        if(Strings.isEmpty(registroRequest.getName())){
            throw new NombreInvalidoException();
        }

        if(!registroRequest.getPassword().equals(registroRequest.getPassword2())){
            throw new ContrasenasDiferentesException();
        }

        if(Strings.isEmpty(registroRequest.getEmail()) || !EmailUtils.validateEmail(registroRequest.getEmail())){
            throw new EmailInvalidoException();
        }
    }

    public Cliente loginUsuario(String email, String password) {
        return clienteRepository.findUsuarioPassword(email, password);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Cliente cliente = clienteRepository.buscarPorEmail(email);
//
//        if(cliente != null){
//            List<GrantedAuthority> permisos = new ArrayList();
//            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+ cliente.getRol().toString());
//            permisos.add(p);
//
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attributes.getRequest().getSession(true);
//            session.setAttribute("usersession", cliente);
//
//            return new User(cliente.getEmail(), cliente.getPassword(),permisos);
//        }else{
//            return null;
//        }
//    }
}
