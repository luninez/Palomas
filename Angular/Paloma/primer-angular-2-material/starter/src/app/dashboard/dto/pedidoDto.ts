export class PedidoDto {
    id: string;
    estado: string;
    fecha: string;
    usuarioId: string;

    constructor(estado: string, fecha: string, usuarioId: string) {
        this.estado = estado;
        this.fecha = fecha;
        this.usuarioId = usuarioId;
    }
}