package org.example.mapper;


import org.example.dto.HardwareDTO;
import org.example.dto.ProductoDTO;
import org.example.dto.SoftwareDTO;
import org.example.entity.Hardware;
import org.example.entity.Producto;
import org.example.entity.Software;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto entity) {
        if (entity == null) return null;

        if (entity instanceof Software) {
            Software s = (Software) entity;
            SoftwareDTO dto = new SoftwareDTO();
            mapearBase(s, dto);
            dto.setClaveLicencia(s.getClaveLicencia());
            dto.setVersion(s.getVersion());
            dto.setVigenciaLicenciaMeses(s.getVigenciaLicenciaMeses());
            dto.setEnlaceDescarga(s.getEnlaceDescarga());
            return dto;
        } else if (entity instanceof Hardware) {
            Hardware h = (Hardware) entity;
            HardwareDTO dto = new HardwareDTO();
            mapearBase(h, dto);
            dto.setNumeroSerie(h.getNumeroSerie());
            dto.setGarantiaMeses(h.getGarantiaMeses());
            dto.setEspecificaciones(h.getEspecificaciones());
            return dto;
        }
        return null;
    }

    private void mapearBase(Producto entity, ProductoDTO dto) {
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setMarca(entity.getMarca());
        dto.setEliminado(entity.isEliminado());
        if (entity.getPromociones() != null) {
            dto.setPromocionesNombres(entity.getPromociones().stream()
                    .map(p -> p.getNombre()).toList());
        }
    }
}
