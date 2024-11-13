package com.challenge.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoBook(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Author autor,
        @JsonAlias("languages") String idioma,
        @JsonAlias("download_count") double numeroDeDescargas) {
}
