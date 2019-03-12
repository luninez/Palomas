import mongoose, { Schema } from 'mongoose'

const imagenSchema = new Schema({
  url: {
    type: String
  },
  productoId: {
    type: Schema.Types.ObjectId,
    ref: 'producto'
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
