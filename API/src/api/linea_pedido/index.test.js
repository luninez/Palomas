import request from 'supertest'
import { apiRoot } from '../../config'
import express from '../../services/express'
import routes, { LineaPedido } from '.'

const app = () => express(apiRoot, routes)

let lineaPedido

beforeEach(async () => {
  lineaPedido = await LineaPedido.create({})
})

test('POST /linea_pedidos 201', async () => {
  const { status, body } = await request(app())
    .post(`${apiRoot}`)
    .send({ cantidad: 'test', precio: 'test', pedidoId: 'test' })
  expect(status).toBe(201)
  expect(typeof body).toEqual('object')
  expect(body.cantidad).toEqual('test')
  expect(body.precio).toEqual('test')
  expect(body.pedidoId).toEqual('test')
})

test('GET /linea_pedidos 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}`)
  expect(status).toBe(200)
  expect(Array.isArray(body.rows)).toBe(true)
  expect(Number.isNaN(body.count)).toBe(false)
})

test('GET /linea_pedidos/:id 200', async () => {
  const { status, body } = await request(app())
    .get(`${apiRoot}/${lineaPedido.id}`)
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(lineaPedido.id)
})

test('GET /linea_pedidos/:id 404', async () => {
  const { status } = await request(app())
    .get(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})

test('PUT /linea_pedidos/:id 200', async () => {
  const { status, body } = await request(app())
    .put(`${apiRoot}/${lineaPedido.id}`)
    .send({ cantidad: 'test', precio: 'test', pedidoId: 'test' })
  expect(status).toBe(200)
  expect(typeof body).toEqual('object')
  expect(body.id).toEqual(lineaPedido.id)
  expect(body.cantidad).toEqual('test')
  expect(body.precio).toEqual('test')
  expect(body.pedidoId).toEqual('test')
})

test('PUT /linea_pedidos/:id 404', async () => {
  const { status } = await request(app())
    .put(apiRoot + '/123456789098765432123456')
    .send({ cantidad: 'test', precio: 'test', pedidoId: 'test' })
  expect(status).toBe(404)
})

test('DELETE /linea_pedidos/:id 204', async () => {
  const { status } = await request(app())
    .delete(`${apiRoot}/${lineaPedido.id}`)
  expect(status).toBe(204)
})

test('DELETE /linea_pedidos/:id 404', async () => {
  const { status } = await request(app())
    .delete(apiRoot + '/123456789098765432123456')
  expect(status).toBe(404)
})
