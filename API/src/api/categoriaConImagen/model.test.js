import { CategoriaConImagen } from '.'

let categoriaConImagen

beforeEach(async () => {
  categoriaConImagen = await CategoriaConImagen.create({ nombre: 'test', picture: 'test' })
})

describe('view', () => {
  it('returns simple view', () => {
    const view = categoriaConImagen.view()
    expect(typeof view).toBe('object')
    expect(view.id).toBe(categoriaConImagen.id)
    expect(view.nombre).toBe(categoriaConImagen.nombre)
    expect(view.picture).toBe(categoriaConImagen.picture)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })

  it('returns full view', () => {
    const view = categoriaConImagen.view(true)
    expect(typeof view).toBe('object')
    expect(view.id).toBe(categoriaConImagen.id)
    expect(view.nombre).toBe(categoriaConImagen.nombre)
    expect(view.picture).toBe(categoriaConImagen.picture)
    expect(view.createdAt).toBeTruthy()
    expect(view.updatedAt).toBeTruthy()
  })
})
