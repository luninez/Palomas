import { Imagen } from '.'

let imagen

beforeEach(async () => {
  imagen = await Imagen.create({ url: 'test', productoId: 'test', deleteHash: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = imagen.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(imagen.id)
    expect(view.url).toBe(imagen.url)
    expect(view.productoId).toBe(imagen.productoId)
    expect(view.deleteHash).toBe(imagen.deleteHash)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = imagen.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(imagen.id)
    expect(view.url).toBe(imagen.url)
    expect(view.productoId).toBe(imagen.productoId)
    expect(view.deleteHash).toBe(imagen.deleteHash)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
