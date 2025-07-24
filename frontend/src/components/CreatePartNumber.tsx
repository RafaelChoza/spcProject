import { useEffect, useState } from "react"


export default function CreatePartNumber() {
    const [fadeIn, setFadeIn] = useState(false)

    useEffect(() => {
        setTimeout(() => {
            setFadeIn(true)
        }, 1000)
    }, [])
  return (
    <div className={`duration-1000 ${fadeIn ? "opacity-1000" : "opacity-0"} transition-opacity`}>
        <h1>CREAR NUMERO DE PARTE</h1>
    </div>
  )
}
