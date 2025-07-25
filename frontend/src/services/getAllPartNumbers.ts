

export default async function getAllPartNumbers() {
    const url = "http://localhost:8080"
    try {
        const response = await fetch(`${url}/api/parts/all-parts`, {
            method: "GET",
        })
        if(!response.ok) {
            console.log("Error al recibir los datos de la base de datos")
        } else {
            console.log("Datos recibidos correctamente")
            const data = response.json()
            return data
        }
    } catch (error) {
        console.error("Error en el servisor", error)
    }
}
