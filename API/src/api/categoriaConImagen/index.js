import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
import { token, master } from '../../services/passport'
export CategoriaConImagen, { schema } from './model'

const router = new Router()
const { nombre, picture } = schema.tree

/**
 * @api {post} /categoriaConImagenes Create categoria con imagen
 * @apiName CreateCategoriaConImagen
 * @apiGroup CategoriaConImagen
 * @apiParam nombre Categoria con imagen's nombre.
 * @apiParam picture Categoria con imagen's picture.
 * @apiSuccess {Object} categoriaConImagen Categoria con imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria con imagen not found.
 */
router.post('/',
  token({required: true, roles: ['admin']}),
  body({ nombre, picture }),
  create)

/**
 * @api {get} /categoriaConImagenes Retrieve categoria con imagens
 * @apiName RetrieveCategoriaConImagens
 * @apiGroup CategoriaConImagen
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of categoria con imagens.
 * @apiSuccess {Object[]} rows List of categoria con imagens.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  master(),
  query(),
  index)

/**
 * @api {get} /categoriaConImagenes/:id Retrieve categoria con imagen
 * @apiName RetrieveCategoriaConImagen
 * @apiGroup CategoriaConImagen
 * @apiSuccess {Object} categoriaConImagen Categoria con imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria con imagen not found.
 */
router.get('/:id',
  master(),
  show)

/**
 * @api {put} /categoriaConImagenes/:id Update categoria con imagen
 * @apiName UpdateCategoriaConImagen
 * @apiGroup CategoriaConImagen
 * @apiParam nombre Categoria con imagen's nombre.
 * @apiParam picture Categoria con imagen's picture.
 * @apiSuccess {Object} categoriaConImagen Categoria con imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Categoria con imagen not found.
 */
router.put('/:id',
  token({required: true, roles: ['admin']}),
  body({ nombre, picture }),
  update)

/**
 * @api {delete} /categoriaConImagenes/:id Delete categoria con imagen
 * @apiName DeleteCategoriaConImagen
 * @apiGroup CategoriaConImagen
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Categoria con imagen not found.
 */
router.delete('/:id',
  token({required: true, roles: ['admin']}),
  destroy)

export default router
