package org.example.service;


import org.example.dto.SoftwareDTO;

public interface SoftwareService {
    boolean validarLicencia(String id);
    SoftwareDTO renovarLicencia(String id, int mesesExtra);
    String generarEnlaceDescarga(String id);
}
