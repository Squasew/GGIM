/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ggim.model;

/**
 * Clase que gestiona los posibles estados  que una {@link incidencias.entity.Incidencia} puede estar.
 * @author isma
 */
public enum EstadoIncidencia {
    /**
     * incidencia resuleta
     */
    Resuelta,
    /**
     * incidencia preocesada
     */
    Procesada,
    /**
     * incidencia en espera
     */
    Espera;
}
