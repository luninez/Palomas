import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { CategoriaConImagen } from '.'

const app = () => express(apiRoot, routes)

let categoriaConImagen

beforeEach(async () => {
  categoriaConImagen = await CategoriaConImagen.create({})
})

test('POST /categoriaConImagenes 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ nombre: 'test', picture: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.nombre).toEqual('test')
  expect(body.picture).toEqual('test')
})

test('GET /categoriaConImagenes 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /categoriaConImagenes/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${categoriaConImagen.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(categoriaConImagen.id)
})

test('GET /categoriaConImagenes/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /categoriaConImagenes/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${categoriaConImagen.id}`)
    .send({ nombre: 'test', picture: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(categoriaConImagen.id)
  expect(body.nombre).toEqual('test')
  expect(body.picture).toEqual('test')
})

test('PUT /categoriaConImagenes/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ nombre: 'test', picture: 'test' })
  expect(status).toBe(404)
})

test('DELETE /categoriaConImagenes/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${categoriaConImagen.id}`)
  expect(status).toBe(204)
})

test('DELETE /categoriaConImagenes/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
