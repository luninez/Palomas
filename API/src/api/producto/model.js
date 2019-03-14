import mongoose, { Schema } from 'mongoose'

const productoSchema = new Schema({
  nombre: {
    type: String
  },
  descripcion: {
    type: String
  },
  precio: {
    type: Number
  },
  favorito: {
    type: Boolean
  },
  categoriaId: {
    type: Schema.Types.ObjectId,
    ref: 'Categoria'
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

productoSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      descripcion: this.descripcion,
      precio: this.precio,
      favorito: this.favorito,
      categoriaId: this.categoriaId,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Producto', productoSchema)

export const schema = model.schema
export default model
