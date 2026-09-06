package org.example.service;


import org.example.entity.Hardware;
import org.example.repository.HardwareRepository;
import org.example.service.HardwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    @Override
    public boolean validarGarantia(String id, Date fechaCompra) {
        Hardware hw = hardwareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));

        // Lógica simple: sumamos los meses de garantía a la fecha de compra y comparamos con hoy
        long mesesEnMilisegundos = hw.getGarantiaMeses() * 30L * 24L * 60L * 60L * 1000L;
        Date finGarantia = new Date(fechaCompra.getTime() + mesesEnMilisegundos);
        Date hoy = new Date();

        return hoy.before(finGarantia);
    }

    @Override
    public List<String> getEspecificaciones(String id) {
        Hardware hw = hardwareRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hardware no encontrado"));
        return hw.getEspecificaciones();
    }
}
