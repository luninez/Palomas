import mongoose, { Schema } from 'mongoose'

const uploadService = require('../../services/upload')

const imagenSchema = new Schema({
  productoId: {
    type: Schema.Types.ObjectId,
    ref: 'Producto'
  },
  url: {
    type: String
  },
  deleteHash: {
    type: String
  }
}, {
  timestamps: true,
  toJSON: {
    virtuals: true,
    transform: (obj, ret) => { delete ret._id }
  }
})

imagenSchema.pre('remove', {query: true}, function (next) {
  console.log('Eliminando la imagen' + this.url)
  uploadService.deleteImage(this.deleteHash)
  return next()
})

imagenSchema.methods = {
  view (full) {
    const view = {
      // simple view
      id: this.id,
      url: this.url,
      productoId: this.productoId,
      deleteHash: this.deleteHash,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    }

    return full ? {
      ...view
      // add properties for a full view
    } : view
  }
}

const model = mongoose.model('Imagen', imagenSchema)

export const schema = model.schema
export default model
