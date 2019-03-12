import { success, notFound } from '../../services/response/'
import { Imagen } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  Imagen.create(body)
    .then((imagen) => imagen.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  Imagen.count(query)
    .populate('productoId')
    .exec()
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
