import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Menu() {
  const [fadeIn, setFadeIn] = useState(false);
  const [fadeOut, setFadeOut] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      setFadeIn(true);
    }, 100);
    return () => clearTimeout(timer);
  }, []);

  const handleNavigate = (pag: string) => {
    setFadeOut(true);
    setTimeout(() => {
      navigate(pag);
    }, 1000);
  };

  return (
    <div
      className={`flex flex-col items-center justify-center min-h-screen bg-gray-100 transition-opacity duration-1000 ${
        fadeOut ? "opacity-0" : fadeIn ? "opacity-100" : "opacity-0"
      }`}
    >
      <h1 className="text-4xl font-bold text-blue-800 mb-12">Menú Principal</h1>

      <div className="flex flex-wrap justify-center gap-10 w-full max-w-5xl px-4">
        {/* Opción 1 */}
        <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-sm text-center hover:shadow-xl transition-shadow duration-300">
          <button
            className="bg-blue-500 hover:bg-blue-600 text-white font-semibold text-xl py-6 px-4 rounded-lg w-full transition-transform transform hover:scale-105"
            onClick={() => handleNavigate("/create_inspection_plan")}
          >
            Crear Plan de Inspección
          </button>
        </div>

        {/* Opción 2 */}
        <div className="bg-white shadow-lg rounded-lg p-6 w-full max-w-sm text-center hover:shadow-xl transition-shadow duration-300">
          <button
            className="bg-green-500 hover:bg-green-600 text-white font-semibold text-xl py-6 px-4 rounded-lg w-full transition-transform transform hover:scale-105"
            onClick={() => handleNavigate("/create_part_number")}
          >
            Realizar Inspección
          </button>
        </div>
      </div>
    </div>
  );
}
