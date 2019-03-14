package com.example.palomasapp.Funcionalidades.Response;

public class ImageResponse {

    private String url;
    private String productoId;
    private String deleteHash;

    public ImageResponse() { }

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
    public String toString() {
        return "ImageResponse{" +
                "url='" + url + '\'' +
                ", productoId='" + productoId + '\'' +
                ", deleteHash='" + deleteHash + '\'' +
                '}';
    }
}
