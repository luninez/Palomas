package com.example.palomasapp.Models;

import java.util.Objects;

public class Imagen {

    private String id;
    private String url;
    private String productoId;
    private String deleteHash;

    public Imagen() {  }

    public Imagen(String url, String productoId, String deleteHash) {
        this.url = url;
        this.productoId = productoId;
        this.deleteHash = deleteHash;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductoId() {
        return productoId;
    }

    public void setProductoId(String productoId) {
        this.productoId = productoId;
    }

    public String getDeleteHash() {
        return deleteHash;
    }

    public void setDeleteHash(String deleteHash) {
        this.deleteHash = deleteHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagen)) return false;
        Imagen imagen = (Imagen) o;
        return Objects.equals(id, imagen.id) &&
                Objects.equals(url, imagen.url) &&
                Objects.equals(productoId, imagen.productoId) &&
                Objects.equals(deleteHash, imagen.deleteHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, productoId, deleteHash);
    }

    @Override
    public String toString() {
        return "Imagen{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", productoId='" + productoId + '\'' +
                ", deleteHash='" + deleteHash + '\'' +
                '}';
    }
}
