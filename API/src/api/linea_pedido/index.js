import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
import { token } from '../../services/passport'
export LineaPedido, { schema } from './model'

const router = new Router()
const { cantidad, precio, pedidoId, productoId } = schema.tree

/**
 * @api {post} /linea_pedidos Create linea pedido
 * @apiName CreateLineaPedido
 * @apiGroup LineaPedido
 * @apiParam cantidad Linea pedido's cantidad.
 * @apiParam precio Linea pedido's precio.
 * @apiParam pedidoId Linea pedido's pedidoId.
 * @apiSuccess {Object} lineaPedido Linea pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Linea pedido not found.
 */
router.post('/',
  token({required: true}),
  body({ cantidad, precio, pedidoId, productoId }),
  create)

/**
 * @api {get} /linea_pedidos Retrieve linea pedidos
 * @apiName RetrieveLineaPedidos
 * @apiGroup LineaPedido
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of linea pedidos.
 * @apiSuccess {Object[]} rows List of linea pedidos.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  token({required: true}),
  query(),
  index)

/**
 * @api {get} /linea_pedidos/:id Retrieve linea pedido
 * @apiName RetrieveLineaPedido
 * @apiGroup LineaPedido
 * @apiSuccess {Object} lineaPedido Linea pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Linea pedido not found.
 */
router.get('/:id',
  token({required: true}),
  show)

/**
 * @api {put} /linea_pedidos/:id Update linea pedido
 * @apiName UpdateLineaPedido
 * @apiGroup LineaPedido
 * @apiParam cantidad Linea pedido's cantidad.
 * @apiParam precio Linea pedido's precio.
 * @apiParam pedidoId Linea pedido's pedidoId.
 * @apiSuccess {Object} lineaPedido Linea pedido's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Linea pedido not found.
 */
router.put('/:id',
  token({required: true}),
  body({ cantidad, precio, pedidoId, productoId }),
  update)

/**
 * @api {delete} /linea_pedidos/:id Delete linea pedido
 * @apiName DeleteLineaPedido
 * @apiGroup LineaPedido
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Linea pedido not found.
 */
router.delete('/:id',
  token({required: true}),
  destroy)

export default router
