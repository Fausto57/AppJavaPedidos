package com.example.reposteria;

public class dataGetSet {
    private String Id, Nombre, Direccion, Pedido;



    public dataGetSet(String id, String nombre, String direccion, String pedido){
        this.Id = id;
        this.Nombre = nombre;
        this.Direccion = direccion;
        this.Pedido = pedido;
    }

    public dataGetSet(){

    }

    public String getId() {
        return Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public String getPedido() {
        return Pedido;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public void setPedido(String pedido) {
        Pedido = pedido;
    }

    @Override
    public String toString() {
        return "dataGetSet{" +
                "Id='" + Id + '\'' +
                ", Nombre='" + Nombre + '\'' +
                ", Direccion='" + Direccion + '\'' +
                ", Pedido='" + Pedido + '\'' +
                '}';
    }
}

