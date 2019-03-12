import { Router } from 'express'
import { middleware as query } from 'querymen'
import { middleware as body } from 'bodymen'
import { create, index, show, update, destroy } from './controller'
import { schema } from './model'
import { token, master } from '../../services/passport'
export Imagen, { schema } from './model'

const router = new Router()
const { url, productoId, deleteHash } = schema.tree

const multer = require('multer')
const storage = multer.memoryStorage()
const upload = multer({storage: storage})

/**
 * @api {post} /imagenes Create imagen
 * @apiName CreateImagen
 * @apiGroup Imagen
 * @apiParam url Imagen's url.
 * @apiParam productoId Imagen's productoId.
 * @apiParam deleteHash Imagen's deleteHash.
 * @apiSuccess {Object} imagen Imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Imagen not found.
 */
router.post('/',
  token({required: true, roles: ['admin']}),
  // body({ url, productoId, deleteHash }),
  upload.single('imagen'),
  create)

/**
 * @api {get} /imagenes Retrieve imagens
 * @apiName RetrieveImagens
 * @apiGroup Imagen
 * @apiUse listParams
 * @apiSuccess {Number} count Total amount of imagens.
 * @apiSuccess {Object[]} rows List of imagens.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 */
router.get('/',
  master(),
  query(),
  index)

/**
 * @api {get} /imagenes/:id Retrieve imagen
 * @apiName RetrieveImagen
 * @apiGroup Imagen
 * @apiSuccess {Object} imagen Imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Imagen not found.
 */
router.get('/:id',
  master(),
  show)

/**
 * @api {put} /imagenes/:id Update imagen
 * @apiName UpdateImagen
 * @apiGroup Imagen
 * @apiParam url Imagen's url.
 * @apiParam productoId Imagen's productoId.
 * @apiParam deleteHash Imagen's deleteHash.
 * @apiSuccess {Object} imagen Imagen's data.
 * @apiError {Object} 400 Some parameters may contain invalid values.
 * @apiError 404 Imagen not found.
 */
router.put('/:id',
  token({required: true, roles: ['admin']}),
  body({ url, productoId, deleteHash }),
  update)

/**
 * @api {delete} /imagenes/:id Delete imagen
 * @apiName DeleteImagen
 * @apiGroup Imagen
 * @apiSuccess (Success 204) 204 No Content.
 * @apiError 404 Imagen not found.
 */
router.delete('/:id',
  token({required: true, roles: ['admin']}),
  destroy)

export default router
