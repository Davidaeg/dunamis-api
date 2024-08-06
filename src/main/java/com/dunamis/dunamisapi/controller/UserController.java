package com.dunamis.dunamisapi.controller;

import com.dunamis.dunamisapi.dto.UsuarioDTO;
import com.dunamis.dunamisapi.exception.DireccionNotFoundException;
import com.dunamis.dunamisapi.exception.DireccionPersonNotFoundException;
import com.dunamis.dunamisapi.exception.UserNotFoundException;
import com.dunamis.dunamisapi.model.Direccion;
import com.dunamis.dunamisapi.model.Persona;
import com.dunamis.dunamisapi.model.Rol;
import com.dunamis.dunamisapi.model.Usuario;
import com.dunamis.dunamisapi.repository.PersonaRepository;
import com.dunamis.dunamisapi.repository.RolRepository;
import com.dunamis.dunamisapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:5173/", " http://localhost:3000/", "http://localhost:5174/"})
public class UserController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PersonaRepository personaRepository;

    @PostMapping("/user")
    public ResponseEntity<Usuario> newUser(@RequestBody @Valid Map<String, Object> userDatos) {
        try {
            System.out.println("Received user data: " + userDatos);

            Usuario usuario = new Usuario();

            String idPersona = (String) userDatos.get("idPersona");
            Persona persona = personaRepository.findById(idPersona).orElse(null);
            System.out.println("Persona: " + persona);

            String nombreRol = (String) userDatos.get("idRol");
            Rol rol = rolRepository.findByNombre(nombreRol).orElse(null);
            System.out.println("Rol: " + rol);

            if (persona != null && rol != null) {
                usuario.setNombreUsuario((String) userDatos.get("nombreUsuario"));
                usuario.setContrasenna((String) userDatos.get("contrasenna"));
                usuario.setPersona(persona);
                usuario.setRol(rol);
            } else {
                throw new IllegalArgumentException("La persona o el rol no existen");
            }

            System.out.println("Saving: " + usuario.toString());
            Usuario savedUsuario = userRepository.save(usuario);
            return ResponseEntity.ok(savedUsuario);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error de validación", e);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("/users")
    List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    Usuario getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("/usuarioPorPersona/{idPersona}")
    public List<Usuario> obtenerUsuariosPorIdPersona(@PathVariable String idPersona) {
        return userRepository.findByPersona_IdPersona(idPersona);
    }

    @GetMapping("/usuarioPorPersonaDTO/{idPersona}")
    public List<UsuarioDTO> obtenerUsuariosPorIdPersonaDTO(@PathVariable String idPersona) {
        List<Usuario> usuarios = userRepository.findByPersona_IdPersona(idPersona);
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setIdUsuario(usuario.getIdUsuario());
            dto.setNombreUsuario(usuario.getNombreUsuario());
            dto.setContrasenna(usuario.getContrasenna());
            dto.setRolNombre(usuario.getRol().getNombre());
            usuarioDTOs.add(dto);
        }

        return usuarioDTOs;
    }


    @PutMapping("/user/{id}")
    Usuario updateUser(@RequestBody Usuario newUser, @PathVariable int id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNombreUsuario(newUser.getNombreUsuario());
                    user.setContrasenna(newUser.getContrasenna());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/usuariosPersona/{idPersona}")
    public List<Usuario> actualizarUsuariosPorIdPersona(@PathVariable @Valid String idPersona, @RequestBody Map<String, Object> userDatos) {
        List<Usuario> usuarios = userRepository.findByPersona_IdPersona(idPersona);

        if (usuarios.isEmpty()) {
            throw new DireccionPersonNotFoundException(idPersona);
        }

        String nombreUsuario = (String) userDatos.get("nombreUsuario");
        String contrasenna = (String) userDatos.get("contrasenna");
        String nombreRol = (String) userDatos.get("idRol");

        Optional<Rol> optionalRol = rolRepository.findByNombre(nombreRol);
        Rol rol = optionalRol.orElseThrow(() -> new IllegalArgumentException("El rol no existe"));

        for (Usuario usuario : usuarios) {
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasenna(contrasenna);
            usuario.setRol(rol);
            userRepository.save(usuario);
        }

        return usuarios;
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable int id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id " + id + " has been deleted success.";
    }
}
