
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Main from './components/Main'
import Menu from './components/Menu'
import CreatePartNumber from './components/CreatePartNumber'
import CreateInspPlan from './components/CreateInspPlan'
import CreatePlanForm from './components/CreatePlanForm'
import InspectionPlan from './components/InspectionPlan'

function App() {


  return (

    <BrowserRouter >
      <Routes>
        <Route path='/' element={<Main />} />
        <Route path='/menu' element={<Menu />} />
        <Route path='/create_part_number' element={<CreatePartNumber />} />
        <Route path='/create_inspection_plan' element={<CreateInspPlan />} />
        <Route path='/create_inspection_plan_form' element={<CreatePlanForm />} />
        <Route path='/inspection_plan' element={<InspectionPlan />} />
      </Routes>
    </BrowserRouter>

  )
}

export default App
