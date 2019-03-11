import { Producto } from '.'

let producto

beforeEach(async () => {
  producto = await Producto.create({ nombre: 'test', precio: 'test', descripcion: 'test', categoriaId: 'test', imagenes: 'test', favorito: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = producto.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(producto.id)
    expect(view.nombre).toBe(producto.nombre)
    expect(view.precio).toBe(producto.precio)
    expect(view.descripcion).toBe(producto.descripcion)
    expect(view.categoriaId).toBe(producto.categoriaId)
    expect(view.imagenes).toBe(producto.imagenes)
    expect(view.favorito).toBe(producto.favorito)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = producto.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(producto.id)
    expect(view.nombre).toBe(producto.nombre)
    expect(view.precio).toBe(producto.precio)
    expect(view.descripcion).toBe(producto.descripcion)
    expect(view.categoriaId).toBe(producto.categoriaId)
    expect(view.imagenes).toBe(producto.imagenes)
    expect(view.favorito).toBe(producto.favorito)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
