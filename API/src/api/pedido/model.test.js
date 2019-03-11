import { Pedido } from '.'

let pedido

beforeEach(async () => {
  pedido = await Pedido.create({ estado: 'test', fecha: 'test', productoId: 'test', usuarioId: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = pedido.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(pedido.id)
    expect(view.estado).toBe(pedido.estado)
    expect(view.fecha).toBe(pedido.fecha)
    expect(view.productoId).toBe(pedido.productoId)
    expect(view.usuarioId).toBe(pedido.usuarioId)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = pedido.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(pedido.id)
    expect(view.estado).toBe(pedido.estado)
    expect(view.fecha).toBe(pedido.fecha)
    expect(view.productoId).toBe(pedido.productoId)
    expect(view.usuarioId).toBe(pedido.usuarioId)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
