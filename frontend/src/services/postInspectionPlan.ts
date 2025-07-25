

export default async function postInspectionPlan(data: string) {
    const url = "http://localhost:8080"
    try {
        const response = await fetch(`${url}/api/plans`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: data
        })
        if(!response.ok) {
            console.log("Error al enviar los datos a la base de datos")
        } else {
            console.log("Datos enviados ala base de datos correctamente")
            const data = response.json()
            return data
        }
    } catch (error) {
        console.error("Error en el servidor", error)
    }
}
