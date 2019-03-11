import mongoose, { Schema } from 'mongoose'

const productoSchema = new Schema({
  nombre: {
    type: String
  },
  precio: {
    type: Number
  },
  descripcion: {
    type: String
  },
  categoriaId: [{
    type: Schema.Types.ObjectId,
    ref: 'categoria'
  }],
  favorito: {
    type: Boolean
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
      precio: this.precio,
      descripcion: this.descripcion,
      categoriaId: this.categoriaId,
      imagenes: this.imagenes,
      favorito: this.favorito,
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
