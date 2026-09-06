package org.example.service;


import org.example.dto.UsuarioDTO;
import org.example.dto.UsuarioLoginDTO;
import org.example.mapper.UsuarioMapper;
import org.example.entity.Usuario;
import org.example.repository.UsuarioRepository;
import org.example.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDTO login(UsuarioLoginDTO loginDto) {
        // Lógica de Negocio: Validar credenciales en texto plano (Didáctico)
        Usuario usuario = usuarioRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getPassword().equals(loginDto.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Retornamos el DTO de salida (sin password)
        return usuarioMapper.toDTO(usuario);
    }
}
