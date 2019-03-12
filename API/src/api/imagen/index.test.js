import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { Imagen } from '.'

const app = () => express(apiRoot, routes)

let imagen

beforeEach(async () => {
  imagen = await Imagen.create({})
})

test('POST /imagenes 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ url: 'test', productoId: 'test', deleteHash: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.url).toEqual('test')
  expect(body.productoId).toEqual('test')
  expect(body.deleteHash).toEqual('test')
})

test('GET /imagenes 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /imagenes/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${imagen.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(imagen.id)
})

test('GET /imagenes/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /imagenes/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${imagen.id}`)
    .send({ url: 'test', productoId: 'test', deleteHash: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(imagen.id)
  expect(body.url).toEqual('test')
  expect(body.productoId).toEqual('test')
  expect(body.deleteHash).toEqual('test')
})

test('PUT /imagenes/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ url: 'test', productoId: 'test', deleteHash: 'test' })
  expect(status).toBe(404)
})

test('DELETE /imagenes/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${imagen.id}`)
  expect(status).toBe(204)
})

test('DELETE /imagenes/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
