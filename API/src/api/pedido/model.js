import mongoose, { Schema } from 'mongoose'

const pedidoSchema = new Schema({
  estado: {
    type: String
  },
  fecha: {
    type: String
  },
  usuarioId: {
    type: Schema.Types.ObjectId,
    ref: 'User'
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

pedidoSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      estado: this.estado,
      fecha: this.fecha,
      productoId: this.productoId,
      usuarioId: this.usuarioId,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Pedido', pedidoSchema)

export const schema = model.schema
export default model
