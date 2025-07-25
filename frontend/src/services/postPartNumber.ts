import type {FormType} from "../types"

export default async function postPartNumber(formData: FormType) {
    const url = "http://localhost:8080"
    try {
        const response = await fetch(`${url}/api/parts`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        if(!response) {
            console.log("Error al enviar los datos a la base de datos")
        } else {
            console.log("Datos enviados a la base de datos correctamente")
            const data = response.json()
            return data
        }
    } catch (error) {
        console.error("Error en el servidor", error)
    }
}
