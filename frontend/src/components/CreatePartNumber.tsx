import { useEffect, useState } from "react"
import type {FormType} from "../types/"
import postPartNumber from "../services/postPartNumber"

const initialStateForm = {
  partNumber: "",
  description: ""
}

export default function CreatePartNumber() {
  const [fadeIn, setFadeIn] = useState(false)
  const [formData, setFormData] = useState<FormType>(initialStateForm)

  useEffect(() => {
    setTimeout(() => {
      setFadeIn(true)
    }, 1000)
  }, [])

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const{name, value} = e.target
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }))
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    console.log(formData)
    await postPartNumber(formData)
    setFormData(initialStateForm)
  }

  return (
    <div className={`flex flex-col items-center justify-center min-h-screen bg-gray-100 duration-1000 ${fadeIn ? "opacity-1000" : "opacity-0"} transition-opacity`}>
      <div className="bg-white shadow-2xl rounded-lg p-8 w-full max-w-md">
        <h1 className="text-center text-3xl font-b mb-6">CREAR NUMERO DE PARTE</h1>
        <div>
          <form
            className="flex flex-col"
            onSubmit={handleSubmit}
          >

            <div className="flex flex-col">
              <label >Número de parte</label>
              <input
                className="border-1 rounded-sm mb-5 p-2"
                name="partNumber"
                type="text"
                placeholder="Escribe el numero de parte"
                onChange={handleChange}
              />
            </div>

            <div className="flex flex-col mb-10">
              <label >Descripción</label>
              <input
                className="border-1 rounded-sm p-2"
                name="description"
                type="text"
                placeholder="Escribe la descripcion"
                onChange={handleChange}
              />
            </div>

            <button
              className="bg-blue-500 p-3 text-white font-bold rounded-lg hover:bg-blue-700 transform transition-all duration-500"
            >Guardar</button>

          </form>
        </div>
      </div>
    </div>
  )
}
