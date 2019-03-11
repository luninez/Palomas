import { LineaPedido } from '.'

let lineaPedido

beforeEach(async () => {
  lineaPedido = await LineaPedido.create({ cantidad: 'test', precio: 'test', pedidoId: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = lineaPedido.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(lineaPedido.id)
    expect(view.cantidad).toBe(lineaPedido.cantidad)
    expect(view.precio).toBe(lineaPedido.precio)
    expect(view.pedidoId).toBe(lineaPedido.pedidoId)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = lineaPedido.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(lineaPedido.id)
    expect(view.cantidad).toBe(lineaPedido.cantidad)
    expect(view.precio).toBe(lineaPedido.precio)
    expect(view.pedidoId).toBe(lineaPedido.pedidoId)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
