import { useEffect, useState } from "react";
import type { PartType, DimensionType } from "../types";
import postInspectionPlan from "../services/postInspectionPlan";
import getAllPartNumbers from "../services/getAllPartNumbers";

export default function CreatePlanForm() {
  const [fadeIn, setFadeIn] = useState(false);
  const [partNumber, setPartNumber] = useState("");
  const [version, setVersion] = useState("");
  const [allPartNumbers, setAllPartNumbers] = useState<PartType[]>([]);
  const [dimensions, setDimensions] = useState<DimensionType[]>([
    { name: "", lowerLimit: 0, upperLimit: 0 },
  ]);

  useEffect(() => {
    const timer = setTimeout(() => {
      setFadeIn(true);
    }, 1000);
    return () => clearTimeout(timer);
  }, []);

  useEffect(() => {
    const gettingAllParts = async () => {
      try {
        const parts = await getAllPartNumbers();
        setAllPartNumbers(parts);
      } catch (error) {
        console.log("Error del servidor", error);
      }
    };
    gettingAllParts();
  }, []);

  const handleDimensionChange = (
    index: number,
    field: keyof DimensionType,
    value: string
  ) => {
    const updated = [...dimensions];
    if (field === "lowerLimit" || field === "upperLimit") {
      updated[index][field] = Number(value);
    } else {
      updated[index][field] = value;
    }
    setDimensions(updated);
  };

  const addDimension = () => {
    setDimensions([...dimensions, { name: "", lowerLimit: 0, upperLimit: 0 }]);
  };

  const removeDimension = (index: number) => {
    const updated = dimensions.filter((_, i) => i !== index);
    setDimensions(updated);
  };

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const payload = {
      partNumber,
      version,
      dimensions,
    };

    const data = JSON.stringify(payload, null, 2);
    await postInspectionPlan(data);
    console.log("JSON generado:", data);
  };

  return (
    <div
      className={`flex flex-col items-center justify-center min-h-screen bg-gray-100 duration-1000 ${
        fadeIn ? "opacity-100" : "opacity-0"
      } transition-opacity`}
    >
      <div className="bg-white shadow-2xl rounded-lg p-8 w-full max-w-2xl">
        <h1 className="text-center text-3xl font-bold mb-6">
          CREAR EL PLAN DE INSPECCIÓN
        </h1>
        <form className="flex flex-col gap-4" onSubmit={handleSubmit}>
          <div>
            <label>Número de Parte</label>
            <select
              className="border p-2 w-full"
              value={partNumber}
              onChange={(e) => setPartNumber(e.target.value)}
              required
            >
              <option value="">Seleccione un número de parte</option>
              {allPartNumbers.map((part) => (
                <option key={part.id} value={part.partNumber}>
                  {part.partNumber}
                </option>
              ))}
            </select>
          </div>

          <div>
            <label>Versión</label>
            <input
              type="text"
              className="border p-2 w-full"
              value={version}
              onChange={(e) => setVersion(e.target.value)}
              required
            />
          </div>

          <hr className="my-4" />

          <h2 className="text-xl font-semibold">Dimensiones</h2>

          {dimensions.map((dim, index) => (
            <div key={index} className="border p-4 rounded-md mb-4 bg-gray-50">
              <div className="mb-2">
                <label>Nombre de Dimensión</label>
                <input
                  type="text"
                  className="border p-2 w-full"
                  value={dim.name}
                  onChange={(e) =>
                    handleDimensionChange(index, "name", e.target.value)
                  }
                  required
                />
              </div>

              <div className="mb-2">
                <label>Límite Inferior</label>
                <input
                  type="number"
                  className="border p-2 w-full"
                  min="0"
                  step="0.0001"
                  value={dim.lowerLimit}
                  onChange={(e) =>
                    handleDimensionChange(index, "lowerLimit", e.target.value)
                  }
                  required
                />
              </div>

              <div className="mb-2">
                <label>Límite Superior</label>
                <input
                  type="number"
                  className="border p-2 w-full"
                  min="0"
                  step="0.0001"
                  value={dim.upperLimit}
                  onChange={(e) =>
                    handleDimensionChange(index, "upperLimit", e.target.value)
                  }
                  required
                />
              </div>

              <button
                type="button"
                onClick={() => removeDimension(index)}
                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700"
              >
                Eliminar
              </button>
            </div>
          ))}

          <button
            type="button"
            onClick={addDimension}
            className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-700"
          >
            Agregar Dimensión
          </button>
          <button
            type="submit"
            className="bg-blue-600 rounded-sm text-white px-4 py-2 hover:bg-blue-700"
          >
            Guardar
          </button>
        </form>
      </div>
    </div>
  );
}
