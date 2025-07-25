import { useEffect, useState } from "react"
import type { InspectionPlanType, PartType } from "../types"
import getAllPartNumbers from "../services/getAllPartNumbers"
import getInspectionPlan from "../services/getInspectionPlan"


export default function InspectionPlan() {
    const [inspectionPlan, setInspectionPlan] = useState<InspectionPlanType>()
    const [partNumbers, setPartNumbers] = useState<PartType[]>([])
    const [fadeIn, setFadeIn] = useState(false)
    const [fadeOut, setFadeOut] = useState(false)

    useEffect(() => {
        const timer = setTimeout(() => {
            setFadeIn(true)
        }, 1000)
        return () => clearTimeout(timer)
    }, [])


    const gettinInspectionPlan = async (partNumber: string) => {
        const parts = await getAllPartNumbers()
        setPartNumbers(parts)
        const plan = await getInspectionPlan(partNumber)
        setInspectionPlan(plan)
    }


    const handleFadeOut = () => {
        setTimeout(() => {
            setFadeOut(true)
        }, 1000)
    }

    return (
        <div className={`${fadeOut ? "opacity-0" : fadeIn ? "opacity-100" : "opacity-0"} transition-opacity duration-1000`}>
            <h1>Plan de Inspeccion</h1>
        </div>
    )
}
