import { success, notFound } from '../../services/response/'
import { Imagen } from '.'

const uploadService = require('../../services/upload/')

export const create = (req, res, next) =>
  uploadService.uploadFromBinary(req.file.buffer)
    .then(json => Imagen.create({
      productoId: req.body.productoId,
      url: json.data.link,
      deleteHash: json.data.deletehash
    }))
    .then((imagen) => imagen.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Imagen.count(query)
    .then(count => Imagen.find(query, select, cursor)
      .then((imagens) => ({
        count,
        rows: imagens.map((imagen) => imagen.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  Imagen.findById(params.id)
    .then(notFound(res))
    .then((imagen) => imagen ? imagen.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  Imagen.findById(params.id)
    .then(notFound(res))
    .then((imagen) => imagen ? Object.assign(imagen, body).save() : null)
    .then((imagen) => imagen ? imagen.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  Imagen.findById(params.id)
    .then(notFound(res))
    .then((imagen) => imagen ? imagen.remove() : null)
    .then(success(res, 204))
    .catch(next)
