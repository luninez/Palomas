import mongoose, { Schema } from 'mongoose'

const categoriaConImagenSchema = new Schema({
  nombre: {
    type: String
  },
  picture: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

categoriaConImagenSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      nombre: this.nombre,
      picture: this.picture,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('CategoriaConImagen', categoriaConImagenSchema)

export const schema = model.schema
export default model
