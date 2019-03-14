import mongoose, { Schema } from 'mongoose'

const lineaPedidoSchema = new Schema({
  cantidad: {
    type: Number
  },
  precio: {
    type: Number
  },
  pedidoId: {
    type: Schema.Types.ObjectId,
    ref: 'Pedido'
  },
  productoId: {
    type: Schema.Types.ObjectId,
    ref: 'Producto'
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

lineaPedidoSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      cantidad: this.cantidad,
      precio: this.precio,
      pedidoId: this.pedidoId,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('LineaPedido', lineaPedidoSchema)

export const schema = model.schema
export default model
