package com.alura.literalura.model;

public enum Lenguaje {

    ESPANOL("es", "Español"),
    INGLES("en", "Inglés"),
    ITALIANO("it", "Italiano"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués"),
    OTRO("other", "Otro");

    private final String lenguaje;
    private final String tipoLenguaje;

    Lenguaje(String lenguaje, String tipoLenguaje) {
        this.lenguaje = lenguaje;
        this.tipoLenguaje = tipoLenguaje;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public String getTipoLenguaje() {
        return tipoLenguaje;
    }

    public static Lenguaje fromString(String text) {
        for (Lenguaje lenguaje : Lenguaje.values()) {
            if (lenguaje.lenguaje.equalsIgnoreCase(text)) {
                return lenguaje;
            }
        }
        return OTRO;
    }

    public static Lenguaje fromTipoLenguaje(String text) {
        for (Lenguaje datosLenguaje : Lenguaje.values()) {
            if (datosLenguaje.tipoLenguaje.equalsIgnoreCase(text.trim())) {
                return datosLenguaje;
            }
        }
        return OTRO;
    }
}
