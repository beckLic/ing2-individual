package org.example.service;

import java.util.Date;
import java.util.List;

public interface HardwareService {
    boolean validarGarantia(String id, Date fechaCompra);
    List<String> getEspecificaciones(String id);
}
