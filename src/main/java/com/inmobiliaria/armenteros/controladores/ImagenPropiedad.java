
package com.inmobiliaria.armenteros.controladores;

/**
 *
 * @author Tonna/SA
 */

public class ImagenPropiedad {
    private String imagenBase64;
    private Long propiedadId;

    public ImagenPropiedad(String imagenBase64, Long propiedadId) {
        this.imagenBase64 = imagenBase64;
        this.propiedadId = propiedadId;
    }

    public String getImagenBase64() {
        return imagenBase64;
    }

    public Long getPropiedadId() {
        return propiedadId;
    }
}