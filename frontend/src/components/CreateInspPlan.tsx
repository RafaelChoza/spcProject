import { useEffect, useState } from "react";

export default function CreateInspPlan() {
  const [fadeIn, setFadeIn] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setFadeIn(true);
    }, 500);
    return () => clearTimeout(timer);
  }, []);

  return (
    <div
      className={`flex flex-col items-center justify-center min-h-screen bg-gray-100 transition-opacity duration-1000 ${
        fadeIn ? "opacity-100" : "opacity-0"
      }`}
    >
      <div className="bg-white shadow-lg rounded-lg p-8 w-full max-w-md text-center">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">
          CREAR PLAN DE INSPECCIÓN
        </h1>

        <div className="mb-6">
          <p className="text-gray-700 mb-2">
            Paso 1. CREAR EL NÚMERO DE PARTE (SI NO EXISTE)
          </p>
          <button className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded transition-colors duration-300">
            CREAR NÚM DE PARTE
          </button>
        </div>

        <div>
          <p className="text-gray-700 mb-2">Paso 2. CREAR PLAN DE INSPECCIÓN</p>
          <button className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-2 px-4 rounded transition-colors duration-300">
            CREAR PLAN DE INSPECCIÓN
          </button>
        </div>
      </div>
    </div>
  );
}
