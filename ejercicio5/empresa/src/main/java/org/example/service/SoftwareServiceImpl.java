package org.example.service;


import org.example.dto.SoftwareDTO;
import org.example.mapper.ProductoMapper;
import org.example.entity.Software;
import org.example.repository.SoftwareRepository;
import org.example.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SoftwareServiceImpl implements SoftwareService {

    private final SoftwareRepository softwareRepository;
    private final ProductoMapper productoMapper;

    @Override
    public boolean validarLicencia(String id) {
        Software sw = softwareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        return sw.getVigenciaLicenciaMeses() > 0;
    }

    @Override
    @Transactional
    public SoftwareDTO renovarLicencia(String id, int mesesExtra) {
        Software sw = softwareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        sw.setVigenciaLicenciaMeses(sw.getVigenciaLicenciaMeses() + mesesExtra);
        return (SoftwareDTO) productoMapper.toDTO(softwareRepository.save(sw));
    }

    @Override
    public String generarEnlaceDescarga(String id) {
        Software sw = softwareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Software no encontrado"));
        return sw.getEnlaceDescarga() != null ? sw.getEnlaceDescarga() : "http://descargas.local/default";
    }
}