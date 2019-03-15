import { success, notFound } from '../../services/response/'
import { CategoriaConImagen } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  CategoriaConImagen.create(body)
    .then((categoriaConImagen) => categoriaConImagen.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  CategoriaConImagen.count(query)
    .then(count => CategoriaConImagen.find(query, select, cursor)
      .then((categoriaConImagens) => ({
        count,
        rows: categoriaConImagens.map((categoriaConImagen) => categoriaConImagen.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  CategoriaConImagen.findById(params.id)
    .then(notFound(res))
    .then((categoriaConImagen) => categoriaConImagen ? categoriaConImagen.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  CategoriaConImagen.findById(params.id)
    .then(notFound(res))
    .then((categoriaConImagen) => categoriaConImagen ? Object.assign(categoriaConImagen, body).save() : null)
    .then((categoriaConImagen) => categoriaConImagen ? categoriaConImagen.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  CategoriaConImagen.findById(params.id)
    .then(notFound(res))
    .then((categoriaConImagen) => categoriaConImagen ? categoriaConImagen.remove() : null)
    .then(success(res, 204))
    .catch(next)
