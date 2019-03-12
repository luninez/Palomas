import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
import { token } from '../../services/passport'
export Pedido, { schema } from './model'

const router = new Router()
const { estado, fecha, usuarioId } = schema.tree

/**
 * @api {post} /pedidos Create pedido
 * @apiName CreatePedido
 * @apiGroup Pedido
 * @apiParam estado Pedido's estado.
 * @apiParam fecha Pedido's fecha.
 * @apiParam productoId Pedido's productoId.
 * @apiParam usuarioId Pedido's usuarioId.
 * @apiSuccess {Object} pedido Pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Pedido not found.
 */
router.post('/',
  token({required: true}),
  body({ estado, fecha, usuarioId }),
  create)

/**
 * @api {get} /pedidos Retrieve pedidos
 * @apiName RetrievePedidos
 * @apiGroup Pedido
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of pedidos.
 * @apiSuccess {Object[]} rows List of pedidos.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  token({required: true}),
  query(),
  index)

/**
 * @api {get} /pedidos/:id Retrieve pedido
 * @apiName RetrievePedido
 * @apiGroup Pedido
 * @apiSuccess {Object} pedido Pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Pedido not found.
 */
router.get('/:id',
  token({required: true}),
  show)

/**
 * @api {put} /pedidos/:id Update pedido
 * @apiName UpdatePedido
 * @apiGroup Pedido
 * @apiParam estado Pedido's estado.
 * @apiParam fecha Pedido's fecha.
 * @apiParam productoId Pedido's productoId.
 * @apiParam usuarioId Pedido's usuarioId.
 * @apiSuccess {Object} pedido Pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Pedido not found.
 */
router.put('/:id',
  token({required: true}),
  body({ estado, fecha, usuarioId }),
  update)

/**
 * @api {delete} /pedidos/:id Delete pedido
 * @apiName DeletePedido
 * @apiGroup Pedido
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Pedido not found.
 */
router.delete('/:id',
  token({required: true}),
  destroy)

export default router
