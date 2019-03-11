import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
import { master } from '../../services/passport';
export Categoria, { schema } from './model'

const router = new Router()
const { nombre } = schema.tree

/**
 * @api {post} /categorias Create categoria
 * @apiName CreateCategoria
 * @apiGroup Categoria
 * @apiPermission master
 * @apiParam {String} access_token Master access_token.
 * @apiParam {String} nombre Categoria's nombre.
 * @apiSuccess {Object} categoria Categoria's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria not found. 
 * @apiError 401 Master access only.
 * @apiError 409 Email already registered.
 */
router.post('/',
  token({required: true, roles: ['admin']}),
  body({ nombre }),
  create)

/**
 * @api {get} /categorias Retrieve categorias
 * @apiName RetrieveCategorias
 * @apiGroup Categoria
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of categorias.
 * @apiSuccess {Object[]} rows List of categorias.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiPermission master
 * @apiParam {String} access_token Master access_token.
 */
router.get('/',
  master(),
  query(),
  index)

/**
 * @api {get} /categorias/:id Retrieve categoria
 * @apiName RetrieveCategoria
 * @apiGroup Categoria
 * @apiSuccess {Object} categoria Categoria's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria not found.
 */
router.get('/:id',
  master(),
  show)

/**
 * @api {put} /categorias/:id Update categoria
 * @apiName UpdateCategoria
 * @apiGroup Categoria
 * @apiParam nombre Categoria's nombre.
 * @apiSuccess {Object} categoria Categoria's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria not found.
 */
router.put('/:id',
  token({required: true, roles: ['admin']}),
  body({ nombre }),
  update)

/**
 * @api {delete} /categorias/:id Delete categoria
 * @apiName DeleteCategoria
 * @apiGroup Categoria
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Categoria not found.
 */
router.delete('/:id',
  token({required: true, roles: ['admin']}),
  destroy)

export default router
