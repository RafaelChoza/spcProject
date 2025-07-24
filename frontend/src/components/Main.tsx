import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Main() {
  const [hoveringButton, setHoveringButton] = useState(false);
  const [fadeOut, setFadeOut] = useState(false);
  const [fadeIn, setFadeIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const timer = setTimeout(() => {
      setFadeIn(true);
    }, 100);
    return () => clearTimeout(timer);
  }, []);

  const handleNavigate = () => {
    setFadeOut(true);
    setTimeout(() => {
      navigate("/menu");
    }, 1000);
  };

  return (
    <div
      className={`flex h-screen transition-opacity duration-1000 ${
        fadeOut ? "opacity-0" : fadeIn ? "opacity-100" : "opacity-0"
      }`}
    >
      {/* Columna izquierda */}
      <div
        className={`flex items-center justify-center bg-blue-700 transition-all duration-500 ${
          hoveringButton ? "w-[30%]" : "w-[20%]"
        }`}
      >
        {/* Puedes agregar un ícono o logo aquí si lo deseas */}
      </div>

      {/* Columna derecha */}
      <div className="w-[80%] bg-gray-100 flex flex-col justify-center items-center p-8">
        <div className="bg-white shadow-xl rounded-lg p-10 w-full max-w-xl text-center">
          <h1 className="text-4xl font-bold text-blue-800 mb-6">
            CONTROL DE PROCESO ESTADÍSTICO (SPC)
          </h1>
          <p className="text-gray-700 mb-6">
            Bienvenido al sistema SPC. Presiona el botón para comenzar.
          </p>
          <button
            className="bg-yellow-400 hover:bg-yellow-500 text-white font-semibold py-3 px-6 rounded-lg text-lg transition-transform transform hover:scale-105"
            onMouseEnter={() => setHoveringButton(true)}
            onMouseLeave={() => setHoveringButton(false)}
            onClick={handleNavigate}
          >
            Iniciar
          </button>
        </div>
      </div>
    </div>
  );
}
