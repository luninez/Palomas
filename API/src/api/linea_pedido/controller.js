import { success, notFound } from '../../services/response/'
import { LineaPedido } from '.'

export const create = ({ bodymen: { body } }, res, next) =>
  LineaPedido.create(body)
    .then((lineaPedido) => lineaPedido.view(true))
    .then(success(res, 201))
    .catch(next)

export const index = ({ querymen: { query, select, cursor } }, res, next) =>
  LineaPedido.count(query)
    .then(count => LineaPedido.find(query, select, cursor)
      .then((lineaPedidos) => ({
        count,
        rows: lineaPedidos.map((lineaPedido) => lineaPedido.view())
      }))
    )
    .then(success(res))
    .catch(next)

export const show = ({ params }, res, next) =>
  LineaPedido.findById(params.id)
    .then(notFound(res))
    .then((lineaPedido) => lineaPedido ? lineaPedido.view() : null)
    .then(success(res))
    .catch(next)

export const update = ({ bodymen: { body }, params }, res, next) =>
  LineaPedido.findById(params.id)
    .then(notFound(res))
    .then((lineaPedido) => lineaPedido ? Object.assign(lineaPedido, body).save() : null)
    .then((lineaPedido) => lineaPedido ? lineaPedido.view(true) : null)
    .then(success(res))
    .catch(next)

export const destroy = ({ params }, res, next) =>
  LineaPedido.findById(params.id)
    .then(notFound(res))
    .then((lineaPedido) => lineaPedido ? lineaPedido.remove() : null)
    .then(success(res, 204))
    .catch(next)
