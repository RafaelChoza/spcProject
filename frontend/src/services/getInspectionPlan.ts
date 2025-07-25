

export default async function getInspectionPlan(partNumber: string) {
    const url = "http://localhost:8080"
    try {
        const response = await fetch(`${url}/api/plans/${partNumber}`, {
            method: "GET"
        })
    if(!response.ok) {
        console.log("Error al recibir los datos de la base de datos")
    } else {
        console.log("Datos recibidos correctamente")
        const data = response.json()
        return data
    }
    } catch (error) {
        console.error("Error del servidor", error)
    }
}
